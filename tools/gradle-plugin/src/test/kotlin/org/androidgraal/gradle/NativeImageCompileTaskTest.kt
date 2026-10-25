package org.androidgraal.gradle

import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NativeImageCompileTaskTest {

    private val project: Project = ProjectBuilder.builder().build()

    private val extension: AndroidGraalExtension =
        project.extensions.create("androidGraal", AndroidGraalExtension::class.java)

    private val options: TargetOptions = project.objects.newInstance(TargetOptions::class.java, "aarch64")

    private val jar = project.file("lib.jar")

    private val toolchain = ToolchainLayout(project.file("toolchain"))

    private val task: NativeImageCompileTask = project.tasks.register("compile", NativeImageCompileTask::class.java) {
        it.setup(
            "debug",
            extension,
            options,
            project.files(jar),
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
    fun `the task directory is below the variant's and the ABI's build directory`() {
        assertEquals(project.file("build/androidgraal/debug/arm64-v8a/native-image"), task.taskDir.get().asFile)
        assertEquals(project.file("build/androidgraal/debug/arm64-v8a/native-image/work"), task.workDir.get().asFile)
        assertEquals(
            project.file("build/androidgraal/debug/arm64-v8a/native-image/objects"),
            task.objectsDir.get().asFile,
        )
    }

    @Test
    fun `each target has its own task directory`() {
        val arm32 = project.tasks.register("compileArm32", NativeImageCompileTask::class.java) {
            it.setup("debug", extension, options, project.files(jar), project.provider { toolchain }, Target.ARM32)
        }.get()

        assertEquals(project.file("build/androidgraal/debug/armeabi-v7a/native-image"), arm32.taskDir.get().asFile)
    }

    @Test
    fun `work and objects follow the task directory`() {
        task.taskDir.set(project.file("other"))

        assertEquals(project.file("other/work"), task.workDir.get().asFile)
        assertEquals(project.file("other/objects"), task.objectsDir.get().asFile)
    }

    @Test
    fun `the image name comes from the extension, the settings from the target`() {
        extension.imageName.set("hello")
        options.buildArgs.set(listOf("-H:+Foo"))
        options.jvmArgs.set(listOf("-Xmx4g"))
        options.systemProperties.set(mapOf("a" to "b"))
        options.configurationFileDirectories.from("config")
        options.verbose.set(true)
        options.quickBuild.set(true)
        options.useLLVM.set(false)
        options.includeLocales.set(listOf("de", "fr"))
        options.addAllCharsets.set(false)

        assertEquals("hello", task.imageName.get())
        assertEquals(listOf("-H:+Foo"), task.buildArgs.get())
        assertEquals(listOf("-Xmx4g"), task.jvmArgs.get())
        assertEquals(mapOf("a" to "b"), task.systemProperties.get())
        assertEquals(setOf(project.file("config")), task.configurationFileDirectories.files)
        assertTrue(task.verbose.get())
        assertTrue(task.quickBuild.get())
        assertFalse(task.useLLVM.get())
        assertEquals(listOf("de", "fr"), task.includeLocales.get())
        assertFalse(task.addAllCharsets.get())
    }

    @Test
    fun `a task setting beats the target`() {
        options.verbose.set(true)

        task.verbose.set(false)
        assertFalse(task.verbose.get())

        task.verbose.set(null as Boolean?)
        assertTrue(task.verbose.get())
    }

    @Test
    fun `a task-level LIR backend fails a target it cannot compile for`() {
        val arm32 = project.tasks.register("compileArm32", NativeImageCompileTask::class.java) {
            it.setup("debug", extension, options, project.files(jar), project.provider { toolchain }, Target.ARM32)
        }.get()
        arm32.useLLVM.set(false)

        val failure = assertFailsWith<GradleException> { arm32.run() }

        assertEquals(
            "useLLVM = false selects native-image's LIR backend, which does not compile for armeabi-v7a",
            failure.cause?.message,
        )
    }

    @Test
    fun `setup passes toolchain, target and classpath through`() {
        assertEquals(toolchain, task.toolchain.get())
        assertEquals(Target.AARCH64, task.target.get())
        assertEquals(setOf(jar), task.imageClasspath.files)
    }
}
