package org.androidgraal.generator.emit

import org.androidgraal.common.Side
import org.androidgraal.generator.ART_CLASSPATH
import org.androidgraal.generator.Compiled
import org.androidgraal.generator.GeneratorFixtures
import org.androidgraal.generator.SVM_CLASSPATH
import org.androidgraal.generator.files
import org.apache.commons.io.FileUtils
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.net.URLClassLoader
import java.util.zip.ZipFile
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.test.fail

class GeneratedSourcesTest {

    private val tmp: File = createTempDirectory("generated-sources").toFile()

    private val fixtures = GeneratorFixtures(tmp)

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `the sample's sources`() {
        val generated = bridges().generate()

        val files = generated.svm.mapKeys { "svm/${it.key}" } + generated.art.mapKeys { "art/${it.key}" } +
            generated.artResources.mapKeys { "art-resources/${it.key}" } + ("model.txt" to generated.model)
        assertGolden("generator-bridges", files)
    }

    @Test
    fun `both bridges compile, and javac -h names every ART native as the image's entry points`() {
        val compiled = bridges()
        val generated = compiled.generate()
        val headers = tmp.resolve("generated-art")
        fixtures.compileBridges(compiled, generated, "generated", listOf("-h", headers.path))

        val natives = headers.walkTopDown().filter { it.name.endsWith(".h") }
            .flatMap { Regex("JNICALL (Java_\\w+)").findAll(it.readText()).map { m -> m.groupValues[1] } }
            .toSortedSet()
        val entryPoints = generated.svm.values
            .flatMap { Regex("CEntryPoint\\(\\s*name = \"(Java_\\w+)\"").findAll(it).map { m -> m.groupValues[1] } }
            .toSortedSet()
        assertEquals(entryPoints, natives)
        assertTrue("Java_org_graalvm_android_bridges_generated_Bridge_initialize" in natives, "$natives")
        assertTrue("Java_org_graalvm_android_bridges_generated_Bridge_instantiateHello" in natives, "$natives")
    }

    @Test
    fun `the image module compiles against the contract alone and names its root's provider`() {
        val image = bridges().image!!

        assertEquals(
            "org.graalvm.android.bridges.svm.HelloImage\n",
            image.resolve("META-INF/services/org.graalvm.android.bridges.core.Hello").readText(),
        )
        assertTrue(image.resolve("org/graalvm/android/bridges/svm/HelloImage.class").isFile)
    }

    @Test
    fun `create before the image starts fails naming NativeImage start, and initialize runs once`() {
        val compiled = bridges()
        val classes = fixtures.javac(
            "before-start",
            compiled.generate().art,
            compiled.artClasspath,
            listOf("--release", "${Side.ART.release}"),
        )
        val loader = URLClassLoader(
            (listOf(classes) + compiled.artClasspath).map { it.toURI().toURL() }.toTypedArray(),
            ClassLoader.getPlatformClassLoader(),
        )

        val bridges = loader.loadClass("org.graalvm.android.bridges.generated.Bridge")
        val hello = loader.loadClass("org.graalvm.android.bridges.core.Hello")
        val image = loader.loadClass("org.androidgraal.art.Image")

        val failure = assertFailsWith<InvocationTargetException> {
            image.getMethod("createRoot", Class::class.java).invoke(null, hello)
        }.targetException
        assertTrue(failure is IllegalStateException, "$failure")
        assertEquals("the image has not started: call NativeImage.start() first", failure.message)
        val contract = bridges.getConstructor().newInstance()
        assertEquals(listOf(hello), (bridges.getMethod("roots").invoke(contract) as Array<*>).toList())
        val create = bridges.getMethod("create", Class::class.java)
        assertEquals(
            "java.lang.Runnable is no root of org.graalvm.android.bridges.generated.Bridge",
            assertFailsWith<InvocationTargetException> {
                create.invoke(contract, Runnable::class.java)
            }.targetException.message,
        )
        assertEquals(
            failure.message,
            assertFailsWith<InvocationTargetException> { create.invoke(contract, hello) }.targetException.message,
        )
        val initialize = bridges.getMethod("initialize")
        assertEquals(
            failure.message,
            assertFailsWith<InvocationTargetException> { initialize.invoke(contract) }.targetException.message,
        )
        assertEquals(
            "org.graalvm.android.bridges.generated.Bridge.initialize() ran already",
            assertFailsWith<InvocationTargetException> { initialize.invoke(contract) }.targetException.message,
        )
    }

    @Test
    fun `every ART native matches its image entry point and every image lookup an ART end point`() {
        val compiled = bridges()
        val generated = compiled.generate()
        val image = classes(
            listOf(
                fixtures.javac(
                    "wire-svm",
                    generated.svm,
                    compiled.svmClasspath,
                    listOf("--release", "${Side.SVM.release}"),
                ),
            ),
        )
        val art = classes(
            listOf(
                fixtures.javac(
                    "wire-art",
                    generated.art,
                    compiled.artClasspath,
                    listOf("--release", "${Side.ART.release}"),
                ),
            ),
        )
        val entryPoints = image.values.flatMap {
            it.methods
        }.mapNotNull { m -> m.entryPoint?.let { it to m.descriptor } }
            .toMap()

        var natives = 0
        art.forEach { (name, info) ->
            info.methods.filter { it.native }.forEach { m ->
                natives++
                val symbol = jniName(name.replace('/', '.'), m.name)
                val entry = entryPoints[symbol] ?: fail("no entry point $symbol")
                assertTrue(matches(m.descriptor, entry), "$symbol: ART ${m.descriptor}, image $entry")
            }
        }
        var lookups = 0
        generated.svm.values.forEach { source ->
            val owner =
                Regex("findClass\\(env, \"([\\w/$]+)\"\\)").find(source)?.groupValues?.get(1) ?: return@forEach
            Regex("staticMethod\\(env, endPoints, \"(\\w+)\", \"([^\"]+)\"\\)").findAll(source).forEach { m ->
                lookups++
                val (method, descriptor) = m.destructured
                val target = art.getValue(owner).methods.singleOrNull { it.name == method }
                assertEquals(descriptor, target?.descriptor, "$owner.$method")
                assertTrue(target!!.endPoint, "$owner.$method is no @EndPoint")
            }
        }
        assertTrue(natives >= 39 && lookups >= 22, "$natives natives, $lookups lookups")
    }

    @Test
    fun `every native of ART's runtime is an entry point of the image's runtime`() {
        val art = classes(ART_CLASSPATH)
        val entryPoints = classes(SVM_CLASSPATH).values.flatMap { it.methods }
            .mapNotNull { m -> m.entryPoint?.let { it to m.descriptor } }.toMap()

        val natives = art.flatMap { (name, info) ->
            info.methods.filter { it.native }.map { m ->
                val symbol = jniName(name.replace('/', '.'), m.name)
                val entry = entryPoints[symbol] ?: fail("no entry point $symbol")
                assertTrue(matches(m.descriptor, entry), "$symbol: ART ${m.descriptor}, image $entry")
                symbol
            }
        }
        assertEquals(
            listOf(
                "Java_org_androidgraal_art_Image_attachThread",
                "Java_org_androidgraal_art_Image_createIsolate",
                "Java_org_androidgraal_art_Image_currentThread",
                "Java_org_androidgraal_art_Image_detachThreadOnExit",
                "Java_org_androidgraal_art_Image_dropExport",
                "Java_org_androidgraal_art_Image_getIsolate",
                "Java_org_androidgraal_art_Image_initializeIsolate",
                "Java_org_androidgraal_art_Image_setPendingException",
                "Java_org_androidgraal_art_Transport_alloc",
                "Java_org_androidgraal_art_Transport_free",
                "Java_org_androidgraal_art_Transport_threadAlloc",
                "Java_org_androidgraal_art_Transport_view",
                "Java_org_androidgraal_art_Transport_viewBlock",
            ),
            natives.sorted(),
        )
        val declared = art.getValue("org/androidgraal/art/Image").methods
        listOf(
            "setPendingException" to "(Ljava/nio/ByteBuffer;)V",
            "handleUncaughtException" to "(Ljava/nio/ByteBuffer;)V",
            "dropExport" to "(J)V",
        ).forEach { (n, d) ->
            assertTrue(declared.any { it.name == n && it.descriptor == d && it.endPoint }, "Image.$n$d")
        }
    }

    @Test
    fun `generated ART code evaluates a lambda or method reference only in field initialisers and initialize`() {
        bridges().generate().art.forEach { (path, source) ->
            val perCall = methods(source).filter { " void initialize()" !in it.lineSequence().first() }
            assertEquals(emptyList(), perCall.filter { it.contains("::") || it.contains("->") }, path)
        }
    }

    @Test
    fun `generated image code holds a JNI reference only for a fresh result ART allocated, deleted at once`() {
        val generated = bridges().generate()
        val held = listOf("JNI.JString", "pushFrame", "popFrame", "Art.view")
        val received = Regex(
            "JNI\\.JObject view = Art\\.callObject\\([^;]*\\);\\n" +
                "\\s*long r = Art\\.address\\(thread\\.env, view\\);\\n" +
                "\\s*Art\\.deleteLocal\\(thread\\.env, view\\);",
        )
        var results = 0
        generated.svm.forEach { (path, source) ->
            assertEquals(emptyList(), held.filter { it in source }, path)
            results += received.findAll(source).count()
            val rest = received.replace(source, "").replace(Regex("JNI\\.JObject transport[,)]"), "")
            assertEquals(emptyList(), listOf("JNI.JObject", "deleteLocal").filter { it in rest }, path)
        }
        assertTrue(results >= 2, "$results fresh results ART allocates")
    }

    @Test
    fun `every transport a call allocates is freed after the call, on either side`() {
        val generated = bridges().generate()
        val sides = listOf(
            Triple(generated.art, "Transport.alloc(thread, bytes)", "Transport.free(thread, transport);"),
            Triple(generated.svm, "Transport.alloc(bytes)", "Transport.free(transport);"),
        )
        sides.forEach { (sources, alloc, free) ->
            val allocating = sources.values.flatMap { methods(it) }.filter { alloc in it }
            allocating.forEach { m ->
                val after = m.substringAfter("} finally {")
                assertTrue(m.indexOf(alloc) < m.indexOf("try {") && free in after, m)
            }
            assertTrue(allocating.size >= 4, "${allocating.size} methods allocate $alloc")
        }
    }

    @Test
    fun `the sample crosses every kind both ways`() {
        val kinds = mutableMapOf<String, MutableSet<String>>()
        var side = ""
        bridges().generate().model.lines().forEach { line ->
            if (" implemented on " in line) side = line.substringAfter(" implemented on ").substringBefore(" as ")
            val crossing = Regex("^  \\w+ .*: \\((.*)\\) -> (\\w+)").find(line) ?: return@forEach
            val (parameters, result) = crossing.destructured
            kinds.getOrPut(side) { sortedSetOf() } += parameters.split(", ").filter { it.isNotEmpty() } + result
        }

        val expected = sortedSetOf(
            "String",
            "String static 16",
            "array",
            "array in",
            "array in static 16",
            "array out",
            "array out static 16",
            "ordinal",
            "primitive",
            "record",
            "reference",
            "void",
        )
        assertEquals<Map<String, Set<String>>>(mapOf("ART" to expected, "the image" to expected), kinds)
    }

    private fun bridges(): Compiled = fixtures.set("bridges")

    private fun methods(source: String): List<String> {
        val methods = mutableListOf<String>()
        val lines = source.lines()
        var i = 0
        while (i < lines.size) {
            val line = lines[i]
            val indent = line.length - line.trimStart().length
            var last = i
            while (last + 1 < lines.size && continued(lines, last)) last++
            if (lines[last].trimEnd().endsWith(") {") && STATEMENTS.none { line.trim().startsWith(it) }) {
                val end = (last + 1 until lines.size).first { lines[it] == " ".repeat(indent) + "}" }
                methods += lines.subList(i, end + 1).joinToString("\n")
                i = end + 1
            } else {
                i = last + 1
            }
        }
        return methods
    }

    private fun continued(lines: List<String>, i: Int): Boolean =
        lines[i].trimEnd().endsWith(",") && !lines[i].trim().startsWith("\"")

    private class MethodInfo(
        val name: String,
        val descriptor: String,
        val native: Boolean,
        val entryPoint: String?,
        val endPoint: Boolean,
    )

    private class ClassFile(val methods: List<MethodInfo>)

    private fun classes(classpath: List<File>): Map<String, ClassFile> {
        val files = classpath.flatMap { classes ->
            if (classes.isDirectory) {
                classes.walkTopDown().filter { it.name.endsWith(".class") }.map { it.readBytes() }.toList()
            } else {
                ZipFile(classes).use { zip ->
                    zip.entries().toList().filter { it.name.endsWith(".class") && it.name != "module-info.class" }
                        .map { zip.getInputStream(it).use { input -> input.readBytes() } }
                }
            }
        }
        return files.associate { bytes -> read(bytes) }
    }

    private fun read(bytes: ByteArray): Pair<String, ClassFile> {
        val methods = mutableListOf<MethodInfo>()
        var name = ""
        ClassReader(bytes).accept(
            object : ClassVisitor(Opcodes.ASM9) {
                override fun visit(v: Int, a: Int, n: String, s: String?, sup: String?, i: Array<out String>?) {
                    name = n
                }

                override fun visitMethod(
                    access: Int,
                    methodName: String,
                    descriptor: String,
                    signature: String?,
                    exceptions: Array<out String>?,
                ): MethodVisitor {
                    val info = arrayOfNulls<String>(1)
                    var endPoint = false
                    return object : MethodVisitor(Opcodes.ASM9) {
                        override fun visitAnnotation(d: String, visible: Boolean): AnnotationVisitor? {
                            if (d == "Lorg/androidgraal/art/EndPoint;") endPoint = true
                            if (d != "Lorg/graalvm/nativeimage/c/function/CEntryPoint;") return null
                            return object : AnnotationVisitor(Opcodes.ASM9) {
                                override fun visit(key: String?, value: Any?) {
                                    if (key == "name") info[0] = value as String
                                }
                            }
                        }

                        override fun visitEnd() {
                            val native = access and Opcodes.ACC_NATIVE != 0
                            methods += MethodInfo(methodName, descriptor, native, info[0], endPoint)
                        }
                    }
                }
            },
            ClassReader.SKIP_CODE,
        )
        return name to ClassFile(methods)
    }

    private fun matches(art: String, image: String): Boolean {
        val artTypes = Type.getArgumentTypes(art).toList() + Type.getReturnType(art)
        val imageTypes = Type.getArgumentTypes(image).drop(2) + Type.getReturnType(image)
        return artTypes.size == imageTypes.size && artTypes.zip(imageTypes).all { (a, i) -> wire(a) == wire(i) }
    }

    private fun wire(type: Type): String = when {
        type.sort != Type.OBJECT && type.sort != Type.ARRAY -> type.descriptor
        type.sort == Type.ARRAY -> type.descriptor
        else -> "object"
    }
}

private val STATEMENTS = listOf("if", "for", "try", "synchronized", "switch", "while", "}")

internal fun assertGolden(name: String, actual: Map<String, String>) {
    val golden = File("src/test/golden/$name").takeIf { it.exists() }
    val expected = golden?.walkTopDown()?.filter { it.isFile }
        ?.associate { it.relativeTo(golden).invariantSeparatorsPath to it.readText() }
        .orEmpty()
    val differing = (expected.keys + actual.keys).filter { expected[it] != actual[it] }.sorted()
    if (differing.isEmpty()) return
    val out = File("build/golden-actual/$name")
    out.deleteRecursively()
    actual.forEach { (path, text) ->
        val file = out.resolve(path)
        file.parentFile.mkdirs()
        file.writeText(text)
    }
    fail("golden $name differs in $differing; the actual files are in ${out.absolutePath}")
}
