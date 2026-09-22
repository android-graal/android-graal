package org.androidgraal.gradle

import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
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
    fun `the native image API is compile only`() {
        val compileOnly = project.declared("compileOnly")
        val runtimeClasspath = project.declared("runtimeClasspath")

        assertTrue(NATIVE_IMAGE_API in compileOnly, "$compileOnly")
        assertFalse(NATIVE_IMAGE_API in runtimeClasspath, "$runtimeClasspath")
    }

    private fun Project.languageVersion(): Property<JavaLanguageVersion> =
        extensions.getByType(JavaPluginExtension::class.java).toolchain.languageVersion

    private fun Project.declared(configuration: String): List<String> =
        configurations.getByName(configuration).allDependencies.map { "${it.group}:${it.name}:${it.version}" }

    private companion object {
        const val NATIVE_IMAGE_API = "org.graalvm.sdk:nativeimage:25.2.4"
    }
}
