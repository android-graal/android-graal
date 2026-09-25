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

    @Test
    fun `the compile task takes its settings from the extension`() {
        val jar = project.file("lib.jar")
        project.dependencies.add("nativeImage", project.files(jar))
        val extension = project.extensions.getByType(AndroidGraalExtension::class.java)
        extension.imageName.set("hello")
        extension.mainClass.set("org.example.Main")
        extension.buildArgs.set(listOf("-H:+Foo"))
        extension.jvmArgs.set(listOf("-Xmx4g"))
        extension.systemProperties.set(mapOf("a" to "b"))
        extension.configurationFileDirectories.from("config")
        extension.verbose.set(true)
        extension.quickBuild.set(true)
        extension.useLLVM.set(false)

        val task = project.tasks.register("compile", NativeImageCompileTask::class.java).get()

        assertEquals("hello", task.imageName.get())
        assertEquals("org.example.Main", task.mainClass.get())
        assertEquals(listOf("-H:+Foo"), task.buildArgs.get())
        assertEquals(listOf("-Xmx4g"), task.jvmArgs.get())
        assertEquals(mapOf("a" to "b"), task.systemProperties.get())
        assertEquals(setOf(project.file("config")), task.configurationFileDirectories.files)
        assertTrue(task.verbose.get())
        assertTrue(task.quickBuild.get())
        assertFalse(task.useLLVM.get())
        assertEquals(setOf(jar), task.imageClasspath.files)
    }

    @Test
    fun `the compile task's directories follow its task directory`() {
        val task = project.tasks.register("compile", NativeImageCompileTask::class.java).get()

        task.taskDir.set(project.file("first"))
        assertEquals(project.file("first/work"), task.workDir.get().asFile)
        assertEquals(project.file("first/objects"), task.objectsDir.get().asFile)
        task.taskDir.set(project.file("second"))
        assertEquals(project.file("second/work"), task.workDir.get().asFile)
        assertEquals(project.file("second/objects"), task.objectsDir.get().asFile)
    }

    @Test
    fun `the link task follows its task directory and the extension`() {
        val extension = project.extensions.getByType(AndroidGraalExtension::class.java)
        extension.imageName.set("hello")
        extension.useLLVM.set(false)

        val task = project.tasks.register("link", NativeImageLinkTask::class.java).get()

        assertEquals("hello", task.imageName.get())
        assertFalse(task.useLLVM.get())
        task.taskDir.set(project.file("first"))
        assertEquals(project.file("first/jniLibs"), task.outputDir.get().asFile)
        task.taskDir.set(project.file("second"))
        assertEquals(project.file("second/jniLibs"), task.outputDir.get().asFile)
    }

    @Test
    fun `console is off by default`() {
        val compile = project.tasks.register("compile", NativeImageCompileTask::class.java).get()
        val link = project.tasks.register("link", NativeImageLinkTask::class.java).get()

        assertFalse(compile.console.get())
        assertFalse(link.console.get())
    }

    @Test
    fun `a task setting beats the extension`() {
        project.extensions.getByType(AndroidGraalExtension::class.java).verbose.set(true)

        val task = project.tasks.register("compile", NativeImageCompileTask::class.java).get()
        task.verbose.set(false)
        assertFalse(task.verbose.get())

        task.verbose.set(null as Boolean?)
        assertTrue(task.verbose.get())
    }

    private fun <T : Named> AttributeContainer.named(key: Attribute<T>) = getAttribute(key)?.name
}
