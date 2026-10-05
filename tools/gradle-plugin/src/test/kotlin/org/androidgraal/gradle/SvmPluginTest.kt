package org.androidgraal.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SvmPluginTest {

    private val project: Project = ProjectBuilder.builder().build().also {
        it.pluginManager.apply("org.androidgraal.svm")
    }

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
    fun `the hosted annotations are compile only at the plugin's version`() {
        val hosted = "org.androidgraal:hosted:$PLUGIN_VERSION"
        val compileOnly = project.declared("compileOnly")
        val implementation = project.declared("implementation")

        assertTrue(hosted in compileOnly, "$compileOnly")
        assertFalse(hosted in implementation, "$implementation")
    }

    @Test
    fun `a contract reaches the image module with its svm bridge at run time`() {
        val root = ProjectBuilder.builder().build()
        val contract = ProjectBuilder.builder().withParent(root).withName("contract").build()
        val image = ProjectBuilder.builder().withParent(root).withName("image").build()
        image.pluginManager.apply("org.androidgraal.svm")

        image.dependencies.add("contract", image.dependencies.project(mapOf("path" to contract.path)))

        fun selectors(configuration: String) = image.configurations.getByName(configuration).allDependencies
            .withType(ProjectDependency::class.java).filter { it.path == ":contract" }
            .map { d -> d.capabilitySelectors.map { it.displayName } }
        assertEquals(listOf(emptyList()), selectors("implementation"))
        assertEquals(listOf(listOf("feature 'svm'")), selectors("runtimeOnly"))
    }

    @Test
    fun `a contract module reaches the image module with its svm bridge at run time`() {
        project.dependencies.add("contract", "org.example:contract:1.0")

        fun selectors(configuration: String) = project.configurations.getByName(configuration).allDependencies
            .withType(ModuleDependency::class.java).filter { it.group == "org.example" && it.name == "contract" }
            .map { d -> d.capabilitySelectors.map { it.displayName } }
        assertEquals(listOf(emptyList()), selectors("implementation"))
        assertEquals(listOf(listOf("feature 'svm'")), selectors("runtimeOnly"))
    }

    @Test
    fun `a file on contract fails the runtime classpath`() {
        project.dependencies.add("contract", project.files("contract.jar"))

        val failure = assertFails { project.configurations.getByName("runtimeClasspath").files }

        val messages = generateSequence(failure) { it.cause }.mapNotNull { it.message }.toList()
        assertTrue(messages.any { "a contract is a project or a module" in it }, "$messages")
    }

    private fun Project.languageVersion(): Property<JavaLanguageVersion> =
        extensions.getByType(JavaPluginExtension::class.java).toolchain.languageVersion

    private fun Project.declared(configuration: String): List<String> =
        configurations.getByName(configuration).allDependencies.map { "${it.group}:${it.name}:${it.version}" }
}
