package org.androidgraal.gradle

import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NativeImageCompileTaskTest {

    private val project: Project = ProjectBuilder.builder().build()

    private val extension: AndroidGraalExtension =
        project.extensions.create("androidGraal", AndroidGraalExtension::class.java)

    private val jar = project.file("lib.jar")

    private val toolchain = ToolchainLayout(project.file("toolchain"))

    private val task: NativeImageCompileTask = project.tasks.register("compile", NativeImageCompileTask::class.java) {
        it.setup(
            "debug",
            extension,
            project.provider { project.files(jar) },
            project.provider { toolchain },
            Target.AARCH64,
        )
    }.get()

    @Test
    fun `setup sets group and description`() {
        assertEquals("build", task.group)
        assertEquals(
            "Compiles the native image into relocatable objects for ${Target.AARCH64.abi.abiString} (debug).",
            task.description,
        )
    }

    @Test
    fun `the task directory is below the variant's build directory`() {
        assertEquals(project.file("build/androidgraal/debug/native-image"), task.taskDir.get().asFile)
        assertEquals(project.file("build/androidgraal/debug/native-image/work"), task.workDir.get().asFile)
        assertEquals(project.file("build/androidgraal/debug/native-image/objects"), task.objectsDir.get().asFile)
    }

    @Test
    fun `work and objects follow the task directory`() {
        task.taskDir.set(project.file("other"))

        assertEquals(project.file("other/work"), task.workDir.get().asFile)
        assertEquals(project.file("other/objects"), task.objectsDir.get().asFile)
    }

    @Test
    fun `the settings come from the extension`() {
        extension.imageName.set("hello")
        extension.mainClass.set("org.example.Main")
        extension.buildArgs.set(listOf("-H:+Foo"))
        extension.jvmArgs.set(listOf("-Xmx4g"))
        extension.systemProperties.set(mapOf("a" to "b"))
        extension.configurationFileDirectories.from("config")
        extension.verbose.set(true)
        extension.quickBuild.set(true)
        extension.useLLVM.set(false)

        assertEquals("hello", task.imageName.get())
        assertEquals("org.example.Main", task.mainClass.get())
        assertEquals(listOf("-H:+Foo"), task.buildArgs.get())
        assertEquals(listOf("-Xmx4g"), task.jvmArgs.get())
        assertEquals(mapOf("a" to "b"), task.systemProperties.get())
        assertEquals(setOf(project.file("config")), task.configurationFileDirectories.files)
        assertTrue(task.verbose.get())
        assertTrue(task.quickBuild.get())
        assertFalse(task.useLLVM.get())
    }

    @Test
    fun `a task setting beats the extension`() {
        extension.verbose.set(true)

        task.verbose.set(false)
        assertFalse(task.verbose.get())

        task.verbose.set(null as Boolean?)
        assertTrue(task.verbose.get())
    }

    @Test
    fun `setup passes toolchain, target and classpath through`() {
        assertEquals(toolchain, task.toolchain.get())
        assertEquals(Target.AARCH64, task.target.get())
        assertEquals(setOf(jar), task.imageClasspath.files)
    }
}
