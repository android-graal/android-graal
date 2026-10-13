package org.androidgraal.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.artifacts.FileCollectionDependency
import org.gradle.api.attributes.Attribute
import org.gradle.api.attributes.AttributeContainer
import org.gradle.api.attributes.Bundling
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.attributes.Usage
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.internal.TaskInputsInternal
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.internal.fingerprint.DirectorySensitivity
import org.gradle.internal.fingerprint.FileNormalizer
import org.gradle.internal.fingerprint.LineEndingSensitivity
import org.gradle.internal.properties.InputBehavior
import org.gradle.internal.properties.InputFilePropertyType
import org.gradle.internal.properties.PropertyValue
import org.gradle.internal.properties.PropertyVisitor
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import java.util.zip.ZipOutputStream
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ArtPluginTest {

    private val project: Project = ProjectBuilder.builder().build().also {
        it.pluginManager.apply("org.androidgraal.art")
    }

    private val dirs = mutableListOf<File>()

    @AfterTest
    fun cleanUp() = dirs.forEach(FileUtils::deleteDirectory)

    @Test
    fun `the plugin fails without com android application`() {
        val failure = runCatching { (project as ProjectInternal).evaluate() }.exceptionOrNull()

        val messages = generateSequence(failure) { it.cause }.mapNotNull { it.message }.toList()
        assertTrue(messages.any { "com.android.application" in it }, "$messages")
    }

    @Test
    fun `the image classpath asks for plain JVM runtime jars of the svm side`() {
        val bucket = project.configurations.getByName("nativeImage")
        val classpath = project.configurations.getByName("nativeImageRuntimeClasspath")

        assertFalse(bucket.isCanBeResolved)
        assertFalse(bucket.isCanBeConsumed)
        assertTrue(classpath.isCanBeResolved)
        assertFalse(classpath.isCanBeConsumed)
        assertTrue(bucket in classpath.extendsFrom)
        val attributes = classpath.attributes
        assertEquals(Usage.JAVA_RUNTIME, attributes.named(Usage.USAGE_ATTRIBUTE))
        assertEquals(Category.LIBRARY, attributes.named(Category.CATEGORY_ATTRIBUTE))
        assertEquals(LibraryElements.JAR, attributes.named(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE))
        assertEquals(Bundling.EXTERNAL, attributes.named(Bundling.BUNDLING_ATTRIBUTE))
        assertEquals(
            TargetJvmEnvironment.STANDARD_JVM,
            attributes.named(TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE),
        )
        assertEquals("svm", attributes.getAttribute(SIDE_ATTRIBUTE))
        assertEquals(6, attributes.keySet().size, "${attributes.keySet()}")
    }

    @Test
    fun `the plugin applies jvm-ecosystem but not java-base`() {
        assertTrue(project.pluginManager.hasPlugin("org.gradle.jvm-ecosystem"))
        assertFalse(project.plugins.hasPlugin(JavaBasePlugin::class.java))
    }

    @Test
    fun `each variant registers the image tasks and NativeImage, nothing else of ours`() {
        evaluateAndroid("")

        val ours = listOf("checkImage", "nativeImage", "generateNativeImage")
        assertEquals(
            listOf("Debug", "Release").flatMap { variant ->
                listOf(
                    "checkImageClasspath",
                    "nativeImageCompile",
                    "nativeImageLink",
                    "generateNativeImage",
                ).map { it + variant }
            }.sorted(),
            project.tasks.names.filter { name -> ours.any { name.startsWith(it) } }.sorted(),
        )
    }

    @Test
    fun `no variant compiles against the image classpath`() {
        project.file("image.jar").writeText("")
        project.dependencies.add("nativeImage", project.files("image.jar"))

        evaluateAndroid("")

        listOf("debug", "release").forEach { variant ->
            assertEquals(emptyList(), fileDependencies("${variant}CompileClasspath"))
        }
    }

    @Test
    fun `checkImageClasspath reads the image classpath and gates the image compile`() {
        project.file("image.jar").writeText("")
        project.dependencies.add("nativeImage", project.files("image.jar"))

        evaluateAndroid("")

        val check = project.tasks.getByName("checkImageClasspathDebug") as CheckImageClasspathTask
        val build = project.layout.buildDirectory.get().asFile
        assertEquals(setOf(project.file("image.jar")), check.imageClasspath.files)
        assertEquals(build.resolve("androidgraal/debug/checkImageClasspath/report.txt"), check.report.get().asFile)
        val image = project.tasks.getByName("nativeImageCompileDebug") as NativeImageCompileTask
        assertEquals(setOf(project.file("image.jar")), image.imageClasspath.files)
        assertTrue(check in image.taskDependencies.getDependencies(image))
    }

    @Test
    fun `generateNativeImage takes the image name and the namespace`() {
        project.extensions.getByType(AndroidGraalExtension::class.java).imageName.set("my-game")

        evaluateAndroid("")

        val generate = project.tasks.getByName("generateNativeImageDebug") as GenerateNativeImageTask
        assertEquals("my-game", generate.imageName.get())
        assertEquals("a.b", generate.packageName.get())
        generate.run()
        assertTrue(generate.outputDir.get().asFile.resolve("a/b/NativeImage.java").isFile)
        assertEquals(GenerateNativeImageTask.MANIFEST, generate.manifestFile.get().asFile.readText())
    }

    @Test
    fun `each variant's main manifest merge depends on the generated manifest`() {
        evaluateAndroid("")

        listOf("Debug", "Release").forEach { variant ->
            val generate = project.tasks.getByName("generateNativeImage$variant") as GenerateNativeImageTask
            val merge = project.tasks.getByName("process${variant}MainManifest")
            assertTrue(generate in merge.taskDependencies.getDependencies(merge))
        }
    }

    @Test
    fun `every runtime classpath of every variant and its tests asks for the art side, no compile classpath does`() {
        evaluateAndroid("")

        val sides = project.configurations.names
            .filter { it.endsWith("RuntimeClasspath") || it.endsWith("CompileClasspath") }
            .associateWith { project.configurations.getByName(it).attributes.getAttribute(SIDE_ATTRIBUTE) }
        assertEquals(
            mapOf(
                "debugAndroidTestCompileClasspath" to null,
                "debugAndroidTestRuntimeClasspath" to "art",
                "debugCompileClasspath" to null,
                "debugRuntimeClasspath" to "art",
                "debugUnitTestCompileClasspath" to null,
                "debugUnitTestRuntimeClasspath" to "art",
                "nativeImageRuntimeClasspath" to "svm",
                "releaseCompileClasspath" to null,
                "releaseRuntimeClasspath" to "art",
            ),
            sides.toSortedMap(),
        )
        assertTrue(project.dependencies.attributesSchema.hasAttribute(SIDE_ATTRIBUTE))
    }

    @Test
    fun `the plugin registers no contract scope`() {
        project.pluginManager.apply("com.android.application")

        assertEquals(null, project.configurations.findByName("contract"))
    }

    @Test
    fun `each variant's Java-resource merge takes its runtime classpath's project jars, non-incrementally`() {
        val root = ProjectBuilder.builder().withProjectDir(createTempDirectory("art-root").toFile().also { dirs += it })
            .build()
        val lib = "org.example:lib:1.0"
        val repository = root.file("repository")
        repository.resolve("org/example/lib/1.0").also { it.mkdirs() }.resolve("lib-1.0.pom").writeText(
            "<project><modelVersion>4.0.0</modelVersion><groupId>org.example</groupId><artifactId>lib</artifactId>" +
                "<version>1.0</version></project>",
        )
        val contract = ProjectBuilder.builder().withParent(root).withName("contract").build()
        contract.group = "org.example"
        contract.pluginManager.apply("org.androidgraal.contract")
        val app = ProjectBuilder.builder().withParent(root).withName("app").build().also { it.projectDir.mkdirs() }
        app.repositories.maven { it.url = repository.toURI() }
        app.configurations.configureEach { c ->
            c.resolutionStrategy.eachDependency { if (it.requested.name != contract.name) it.useTarget(lib) }
        }
        app.pluginManager.apply("org.androidgraal.art")
        app.pluginManager.apply("com.android.application")
        app.dependencies.add("implementation", app.dependencies.project(mapOf("path" to contract.path)))
        app.dependencies.add("implementation", lib)
        evaluateAndroid("", project = app)
        // Resolving the merge input runs AGP's identity transform over the contract's jars, which must exist.
        listOf("contract.jar", "contract-art.jar").forEach { name ->
            ZipOutputStream(contract.file("build/libs/$name").also { it.parentFile.mkdirs() }.outputStream()).close()
        }

        listOf("Debug", "Release").forEach { variant ->
            val inputs = app.tasks.getByName("merge${variant}JavaResource").inputs as TaskInputsInternal
            val jars = mutableListOf<Pair<InputBehavior, Any?>>()
            inputs.visitRegisteredProperties(
                object : PropertyVisitor {
                    override fun visitInputFileProperty(
                        propertyName: String,
                        optional: Boolean,
                        behavior: InputBehavior,
                        directorySensitivity: DirectorySensitivity,
                        lineEndingSensitivity: LineEndingSensitivity,
                        fileNormalizer: FileNormalizer?,
                        value: PropertyValue,
                        filePropertyType: InputFilePropertyType,
                    ) {
                        if (propertyName == ArtPlugin.CONTRACTS_INPUT_PROPERTY) jars += behavior to value.call()
                    }
                },
            )
            val (behavior, files) = jars.single()
            assertEquals(InputBehavior.NON_INCREMENTAL, behavior)
            assertEquals(setOf("contract.jar", "contract-art.jar"), app.files(files).files.map { it.name }.toSet())
        }
    }

    @Test
    fun `a debuggable variant configures at Java 11`() {
        evaluateAndroid("", "VERSION_11")

        assertTrue("nativeImageCompileDebug" in project.tasks.names)
    }

    @Test
    fun `the art runtime and emulation's art are implementation dependencies once com android application applies`() {
        project.pluginManager.apply("com.android.application")

        val implementation = project.configurations.getByName("implementation").dependencies
            .map { "${it.group}:${it.name}:${it.version}" }
        assertTrue("org.androidgraal:art:$PLUGIN_VERSION" in implementation, "$implementation")
        assertTrue("org.androidgraal.emulation:art:$PLUGIN_VERSION" in implementation, "$implementation")
    }

    @Test
    fun `console is off by default`() {
        val compile = project.tasks.register("compile", NativeImageCompileTask::class.java).get()
        val link = project.tasks.register("link", NativeImageLinkTask::class.java).get()

        assertFalse(compile.console.get())
        assertFalse(link.console.get())
    }

    /** [source] and [target] name `JavaVersion` constants. */
    private fun evaluateAndroid(
        more: String,
        source: String = "VERSION_17",
        target: String = source,
        project: Project = this.project,
    ) {
        project.pluginManager.apply("com.android.application")
        val script = project.file("android.gradle")
        script.writeText(
            """
            android {
                namespace = 'a.b'
                compileSdk = 36
                defaultConfig {
                    minSdk = 21
                    ndk { abiFilters 'arm64-v8a' }
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.$source
                    targetCompatibility = JavaVersion.$target
                }
            """.trimIndent() + "\n" + more + "\n}\n",
        )
        project.apply(mapOf("from" to script))
        (project as ProjectInternal).evaluate()
    }

    private fun fileDependencies(configuration: String): List<FileCollectionDependency> =
        project.configurations.getByName(configuration).allDependencies.filterIsInstance<FileCollectionDependency>()

    private fun <T : Named> AttributeContainer.named(key: Attribute<T>) = getAttribute(key)?.name
}
