package org.androidgraal.generator

import com.google.testing.compile.Compilation
import com.google.testing.compile.CompilationSubject.assertThat
import com.google.testing.compile.Compiler.javac
import com.google.testing.compile.JavaFileObjects
import org.androidgraal.common.Side
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.scan.Annotated
import org.androidgraal.generator.scan.Annotations
import org.androidgraal.generator.scan.Discovery
import org.androidgraal.generator.scan.Errors
import org.androidgraal.generator.scan.Interfaces
import org.androidgraal.processor.BridgeProcessor
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.JavaFileObject
import kotlin.test.assertEquals
import kotlin.test.fail

internal val CONTRACT_CLASSPATH: List<File> = classpathOf("androidgraal.test.contractClasspath")
internal val SVM_CLASSPATH: List<File> = classpathOf("androidgraal.test.svmClasspath")
internal val ART_CLASSPATH: List<File> = classpathOf("androidgraal.test.artClasspath")

internal const val CONTRACT_PACKAGE = "fix.core"

internal const val GENERATED = "fix/core/generated"

internal const val SERVICE = "fix/core/Service.java"

internal const val NO_KIND =
    ", only primitives, String, enums, records, directed primitive arrays and registered interfaces cross"

internal class GeneratorFixtures(private val root: File) {

    fun set(name: String): Compiled =
        compileSets(name, javaSources("generator/$name/contract"), files("generator/$name/image"), null)

    fun compile(name: String, sources: Map<String, String>, marked: String? = CONTRACT_PACKAGE): Compiled =
        compileSets(name, sources, emptyMap(), marked)

    fun compileSets(
        name: String,
        contract: Map<String, String>,
        image: Map<String, String> = emptyMap(),
        marked: String? = CONTRACT_PACKAGE,
    ): Compiled {
        val contractClasses = compileSet("$name-contract", contract, CONTRACT_CLASSPATH, Side.sharedRelease())
        val imageClasses = if (image.isEmpty()) {
            null
        } else {
            compileSet("$name-image", image, listOf(contractClasses) + SVM_CLASSPATH, Side.SVM.release)
        }
        return Compiled(
            contract.filterKeys { it.endsWith(".java") },
            contractClasses,
            imageClasses,
            marked,
            root.resolve("$name-bridges"),
        )
    }

    fun javac(name: String, sources: Map<String, String>, classpath: List<File>, options: List<String>): File {
        val compilation = javac().withClasspath(
            classpath,
        ).withOptions(options + "-proc:none").compile(javaFiles(sources))
        assertThat(compilation).succeeded()
        return write(compilation, name)
    }

    fun write(compilation: Compilation, name: String): File {
        val out = root.resolve(name).apply { deleteRecursively() }
        compilation.generatedFiles().forEach { file ->
            val path = file.toUri().path.removePrefix("/").substringAfter('/')
            out.resolve(path).apply { parentFile.mkdirs() }.writeBytes(file.openInputStream().use { it.readBytes() })
        }
        return out
    }

    fun compileBridges(
        compiled: Compiled,
        generated: Generated,
        name: String,
        artOptions: List<String> = emptyList(),
    ): File {
        javac("$name-svm", generated.svm, compiled.svmClasspath, listOf("--release", "${Side.SVM.release}"))
        return javac(
            "$name-art",
            generated.art,
            compiled.artClasspath,
            listOf("--release", "${Side.ART.release}", "-Xlint:all,-overloads", "-Werror") + artOptions,
        )
    }

    private fun compileSet(name: String, set: Map<String, String>, classpath: List<File>, release: Int): File {
        val out = javac(name, set.filterKeys { it.endsWith(".java") }, classpath, listOf("--release", "$release"))
        set.filterKeys { !it.endsWith(".java") }.forEach { (path, text) ->
            out.resolve(path).apply { parentFile.mkdirs() }.writeText(text)
        }
        return out
    }
}

internal class Generated(
    val art: Map<String, String>,
    val artResources: Map<String, String>,
    val svm: Map<String, String>,
    val model: String,
)

internal class Compiled(
    private val sources: Map<String, String>,
    val contract: File,
    val image: File?,
    val marked: String?,
    val out: File,
) {
    val svmClasspath: List<File> = svmClasspath(contract)

    val artClasspath: List<File> = artClasspath(contract)

    fun process(classpath: List<File> = emptyList(), vararg others: Processor): Compilation {
        return javac().withProcessors(listOf(BridgeProcessor()) + others)
            .withClasspath(CONTRACT_CLASSPATH + classpath)
            .withOptions(
                "--release",
                "${Side.sharedRelease()}",
                "-A${BridgeProcessor.OUT}=${out.path}",
            )
            .compile(javaFiles(sources + packageInfo()))
    }

    fun generate(classpath: List<File> = emptyList()): Generated {
        val model = ModelReport()
        assertThat(process(classpath, model)).succeeded()
        return Generated(
            texts(out.resolve("art/java")),
            texts(out.resolve("art/resources")),
            texts(out.resolve("svm/java")),
            model.report,
        )
    }

    private fun packageInfo(): Map<String, String> {
        if (marked == null) return emptyMap()
        val path = marked.replace('.', '/') + "/package-info.java"
        if (path in sources) return emptyMap()
        return mapOf(path to "@${Annotations.BRIDGE}\npackage $marked;\n")
    }

    fun source(path: String): JavaFileObject =
        javaFiles(mapOf(path to (sources + packageInfo()).getValue(path))).single()
}

internal fun assertErrors(compilation: Compilation, vararg messages: String) {
    assertEquals(messages.map { "android-graal: $it" }, compilation.errors().map { it.getMessage(null) })
}

internal class ErrorAt(val message: String, val file: String, val line: String)

internal fun Compiled.assertErrors(vararg errors: ErrorAt) {
    val compilation = process()
    assertEquals(errors.map { "android-graal: ${it.message}" }, compilation.errors().map { it.getMessage(null) })
    errors.forEach {
        assertThat(compilation).hadErrorContaining(it.message).inFile(source(it.file)).onLineContaining(it.line)
    }
}

private fun svmClasspath(contract: File): List<File> = listOf(contract) + CONTRACT_CLASSPATH + SVM_CLASSPATH

private fun artClasspath(contract: File): List<File> = listOf(contract) + CONTRACT_CLASSPATH + ART_CLASSPATH

private fun classpathOf(property: String): List<File> {
    val value = System.getProperty(property) ?: fail("no $property, set by the test task")
    return value.split(File.pathSeparator).map(::File)
}

private fun javaFiles(sources: Map<String, String>): List<JavaFileObject> =
    sources.map { (path, text) -> JavaFileObjects.forSourceString(path.removeSuffix(".java").replace('/', '.'), text) }

private fun texts(dir: File): Map<String, String> {
    return dir.walkTopDown().filter { it.isFile }
        .associate { it.relativeTo(dir).invariantSeparatorsPath to it.readText() }.toSortedMap()
}

private class ModelReport : AbstractProcessor() {

    var report = ""

    private val annotated = Annotated()

    override fun getSupportedAnnotationTypes(): Set<String> = Annotations.ALL

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(annotations: Set<TypeElement>, round: RoundEnvironment): Boolean {
        for (a in annotations) annotated.add(a, round.getElementsAnnotatedWith(a))
        if (!round.processingOver()) return false
        val errors = Errors()
        val registrations = Discovery.registrations(annotated, errors)
        val interfaces = Interfaces(registrations, processingEnv.elementUtils, processingEnv.typeUtils, errors)
        interfaces.analyze()
        if (errors.isNotEmpty()) return false
        val model = BridgeModel.of(
            registrations.roots,
            registrations.packageName,
            interfaces.proxies,
            interfaces.enums,
            interfaces.records,
        )
        report = model.report()
        return false
    }
}

internal fun javaSources(path: String): Map<String, String> = files(path).filterKeys { it.endsWith(".java") }

internal fun files(path: String): Map<String, String> {
    val dir = File("src/test/fixtures/$path")
    if (!dir.exists()) return emptyMap()
    return dir.walkTopDown().filter { it.isFile }.sorted()
        .associate { it.relativeTo(dir).invariantSeparatorsPath to it.readText() }
}
