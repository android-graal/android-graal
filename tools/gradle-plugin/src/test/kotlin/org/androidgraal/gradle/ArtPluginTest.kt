package org.androidgraal.gradle

import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.attributes.Attribute
import org.gradle.api.attributes.AttributeContainer
import org.gradle.api.attributes.Bundling
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.attributes.Usage
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ArtPluginTest {

    private val project: Project = ProjectBuilder.builder().build().also {
        it.pluginManager.apply("org.androidgraal.art")
    }

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

    private fun <T : Named> AttributeContainer.named(key: Attribute<T>) = getAttribute(key)?.name
}
