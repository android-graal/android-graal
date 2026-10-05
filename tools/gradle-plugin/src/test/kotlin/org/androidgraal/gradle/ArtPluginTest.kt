package org.androidgraal.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.artifacts.FileCollectionDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.ProjectDependency
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
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
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
    fun `the image classpath asks for plain JVM runtime jars`() {
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
        assertEquals(5, attributes.keySet().size, "${attributes.keySet()}")
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
    }

    @Test
    fun `a contract reaches the app with its art bridge at run time`() {
        val root = ProjectBuilder.builder().withProjectDir(createTempDirectory("art-root").toFile().also { dirs += it })
            .build()
        val contract = ProjectBuilder.builder().withParent(root).withName("contract").build()
        val app = ProjectBuilder.builder().withParent(root).withName("app").build()
        app.pluginManager.apply("org.androidgraal.art")
        app.pluginManager.apply("com.android.application")

        app.dependencies.add("contract", app.dependencies.project(mapOf("path" to contract.path)))

        fun selectors(configuration: String) = app.configurations.getByName(configuration).allDependencies
            .withType(ProjectDependency::class.java).filter { it.path == ":contract" }
            .map { d -> d.capabilitySelectors.map { it.displayName } }
        assertEquals(listOf(emptyList()), selectors("implementation"))
        assertEquals(listOf(listOf("feature 'art'")), selectors("runtimeOnly"))
        assertEquals(setOf(emptyList(), listOf("feature 'art'")), selectors(CONTRACT_JARS_CONFIGURATION_NAME).toSet())
        assertFalse(app.configurations.getByName(CONTRACT_JARS_CONFIGURATION_NAME).isTransitive)
    }

    @Test
    fun `each variant's Java-resource merge takes the contract jars as a non-incremental input`() {
        evaluateAndroid("")
        val jar = project.file("contract.jar")
        val marker = project.configurations.dependencyScope("marker").get()
        project.dependencies.add(marker.name, project.files(jar))
        project.configurations.getByName(CONTRACT_JARS_CONFIGURATION_NAME).extendsFrom(marker)

        listOf("Debug", "Release").forEach { variant ->
            val inputs = project.tasks.getByName("merge${variant}JavaResource").inputs as TaskInputsInternal
            val contracts = mutableListOf<Pair<InputBehavior, Any?>>()
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
                        if (propertyName == ArtPlugin.CONTRACTS_INPUT_PROPERTY) contracts += behavior to value.call()
                    }
                },
            )
            val (behavior, files) = contracts.single()
            assertEquals(InputBehavior.NON_INCREMENTAL, behavior)
            assertEquals(setOf(jar), project.files(files).files)
        }
    }

    @Test
    fun `a contract module reaches the app with its art bridge at run time`() {
        project.pluginManager.apply("com.android.application")

        project.dependencies.add("contract", "org.example:contract:1.0")

        fun selectors(configuration: String) = project.configurations.getByName(configuration).allDependencies
            .withType(ModuleDependency::class.java).filter { it.group == "org.example" && it.name == "contract" }
            .map { d -> d.capabilitySelectors.map { it.displayName } }
        assertEquals(listOf(emptyList()), selectors("implementation"))
        assertEquals(listOf(listOf("feature 'art'")), selectors("runtimeOnly"))
    }

    @Test
    fun `a file on contract fails the app's configuration, where AGP reads runtimeOnly`() {
        project.pluginManager.apply("com.android.application")
        project.dependencies.add("contract", project.files("contract.jar"))

        val failure = assertFails { evaluateAndroid("") }

        val messages = generateSequence(failure) { it.cause }.mapNotNull { it.message }.toList()
        assertTrue(messages.any { "a contract is a project or a module" in it }, "$messages")
    }

    @Test
    fun `a debuggable variant configures at Java 11`() {
        evaluateAndroid("", "VERSION_11")

        assertTrue("nativeImageCompileDebug" in project.tasks.names)
    }

    @Test
    fun `the art runtime is an implementation dependency once com android application applies`() {
        val art = "org.androidgraal:art:$PLUGIN_VERSION"

        project.pluginManager.apply("com.android.application")

        val implementation = project.configurations.getByName("implementation").dependencies
            .map { "${it.group}:${it.name}:${it.version}" }
        assertTrue(art in implementation, "$implementation")
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
