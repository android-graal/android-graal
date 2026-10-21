package org.androidgraal.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.component.ProjectComponentIdentifier
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SvmPluginTest {

    private val project: Project = ProjectBuilder.builder().build().also {
        it.pluginManager.apply("org.androidgraal.svm")
    }

    private val dirs = mutableListOf<File>()

    @AfterTest
    fun cleanUp() = dirs.forEach(FileUtils::deleteDirectory)

    @Test
    fun `the plugin applies java-library`() {
        assertTrue(project.plugins.hasPlugin(JavaLibraryPlugin::class.java))
    }

    @Test
    fun `the toolchain defaults to Java 25`() {
        assertEquals(JavaLanguageVersion.of(25), project.languageVersion().get())
    }

    @Test
    fun `a toolchain set before the plugin applies wins`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.languageVersion().set(JavaLanguageVersion.of(21))

        project.pluginManager.apply("org.androidgraal.svm")

        assertEquals(JavaLanguageVersion.of(21), project.languageVersion().get())
    }

    @Test
    fun `every Java compile targets release 25 unless the build sets its own`() {
        val compileJava = project.tasks.named("compileJava", JavaCompile::class.java).get()
        val compileTestJava = project.tasks.named("compileTestJava", JavaCompile::class.java).get()

        assertEquals(25, compileJava.options.release.get())
        assertEquals(25, compileTestJava.options.release.get())
        compileJava.options.release.set(21)
        assertEquals(21, compileJava.options.release.get())
    }

    @Test
    fun `no GraalVM SDK artifact is declared, the svm runtime brings ours`() {
        val declared = listOf("compileOnly", "implementation", "runtimeOnly", "annotationProcessor")
            .flatMap { project.declared(it) }

        assertTrue(declared.none { it.startsWith("org.graalvm.sdk:") }, "$declared")
    }

    @Test
    fun `the svm runtime is an implementation dependency at the plugin's version`() {
        val svm = "org.androidgraal:svm:$PLUGIN_VERSION"
        val implementation = project.declared("implementation")
        val compileOnly = project.declared("compileOnly")

        assertTrue(svm in implementation, "$implementation")
        assertFalse(svm in compileOnly, "$compileOnly")
    }

    @Test
    fun `the emulated Android classes are an implementation dependency at the plugin's version`() {
        assertEquals(listOf(emptyList()), project.emulationSelectors("implementation"))
    }

    @Test
    fun `the Android declarations are compile only at the plugin's version`() {
        assertEquals(listOf(listOf("feature 'declarations'")), project.emulationSelectors("compileOnly"))
    }

    @Test
    fun `the hosted annotations are compile only at the plugin's version`() {
        val hosted = "org.androidgraal:hosted:$PLUGIN_VERSION"
        val compileOnly = project.declared("compileOnly")
        val implementation = project.declared("implementation")

        assertTrue(hosted in compileOnly, "$compileOnly")
        assertFalse(hosted in implementation, "$implementation")
    }

    @Test
    fun `SubstrateVM's shared annotations come through the svm runtime, the plugin declares none`() {
        val declared = project.configurations.flatMap { c -> c.dependencies.map { "${it.group}:${it.name}" } }

        assertFalse("org.androidgraal.graal:svm-shared" in declared, "$declared")
    }

    @Test
    fun `the SDK's word and nativeimage reach the tests' runtime alone, their compile comes through the svm runtime`() {
        val testRuntimeOnly = project.declared("testRuntimeOnly")
        val others = listOf("compileOnly", "implementation", "runtimeOnly", "api", "testImplementation")
            .flatMap { project.declared(it) }

        listOf("word", "nativeimage").forEach {
            val module = "org.androidgraal.graal:$it:$PLUGIN_VERSION"
            assertTrue(module in testRuntimeOnly, "$testRuntimeOnly")
            assertFalse(module in others, "$others")
        }
    }

    @Test
    fun `every source set's runtime classpath asks for the svm side, no compile classpath does`() {
        val sourceSets = project.extensions.getByType(JavaPluginExtension::class.java).sourceSets
        sourceSets.create("extra")

        val sides = sourceSets
            .flatMap { listOf(it.runtimeClasspathConfigurationName, it.compileClasspathConfigurationName) }
            .associateWith { project.configurations.getByName(it).attributes.getAttribute(SIDE_ATTRIBUTE) }
        assertEquals(
            mapOf(
                "extraCompileClasspath" to null,
                "extraRuntimeClasspath" to "svm",
                "compileClasspath" to null,
                "runtimeClasspath" to "svm",
                "testCompileClasspath" to null,
                "testRuntimeClasspath" to "svm",
            ),
            sides,
        )
        assertTrue(project.dependencies.attributesSchema.hasAttribute(SIDE_ATTRIBUTE))
    }

    @Test
    fun `a contract reaches the image module's runtime classpath with its svm bridge, its compile classpath alone`() {
        val root = ProjectBuilder.builder().withProjectDir(createTempDirectory("svm-root").toFile().also { dirs += it })
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
        val image = ProjectBuilder.builder().withParent(root).withName("image").build()
        image.repositories.maven { it.url = repository.toURI() }
        image.configurations.configureEach { c ->
            c.resolutionStrategy.eachDependency { if (it.requested.name != contract.name) it.useTarget(lib) }
        }
        image.pluginManager.apply("org.androidgraal.svm")

        image.dependencies.add("implementation", image.dependencies.project(mapOf("path" to contract.path)))

        fun variants(classpath: String) = image.configurations.getByName(classpath).incoming.resolutionResult
            .allComponents.filter { (it.id as? ProjectComponentIdentifier)?.projectPath == contract.path }
            .flatMap { component -> component.variants.map { it.displayName } }
        fun files(classpath: String) = image.configurations.getByName(classpath).incoming
            .artifactView { view -> view.componentFilter { it is ProjectComponentIdentifier } }.files.toList()
        assertEquals(listOf("svmRuntimeElements"), variants("runtimeClasspath"))
        assertEquals(listOf("contract.jar", "contract-svm.jar"), files("runtimeClasspath").map { it.name })
        assertEquals(listOf("apiElements"), variants("compileClasspath"))
        val contractClasses = contract.layout.buildDirectory.dir("classes/java/main").get().asFile
        assertEquals(listOf(contractClasses), files("compileClasspath"))
    }

    @Test
    fun `the plugin registers no contract scope`() {
        assertEquals(null, project.configurations.findByName("contract"))
    }

    private fun Project.languageVersion(): Property<JavaLanguageVersion> =
        extensions.getByType(JavaPluginExtension::class.java).toolchain.languageVersion

    private fun Project.emulationSelectors(configuration: String): List<List<String>> {
        return configurations.getByName(configuration).allDependencies.withType(ModuleDependency::class.java)
            .filter { "${it.group}:${it.name}:${it.version}" == "org.androidgraal.emulation:svm:$PLUGIN_VERSION" }
            .map { d -> d.capabilitySelectors.map { it.displayName } }
    }

    private fun Project.declared(configuration: String): List<String> =
        configurations.getByName(configuration).allDependencies.map { "${it.group}:${it.name}:${it.version}" }
}
