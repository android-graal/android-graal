package org.androidgraal.gradle

import org.androidgraal.common.Ndk
import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NativeImageLinkTaskTest {

    private val project: Project = ProjectBuilder.builder().build()

    private val extension: AndroidGraalExtension =
        project.extensions.create("androidGraal", AndroidGraalExtension::class.java)

    private val toolchain = ToolchainLayout(project.file("toolchain"))

    private val ndkRoot = project.file("ndk")

    private val objects = project.layout.projectDirectory.dir("objects")

    private val task: NativeImageLinkTask = project.tasks.register("link", NativeImageLinkTask::class.java) {
        it.setup(
            "debug",
            extension,
            project.provider { toolchain },
            Target.AARCH64,
            project.provider { Ndk(ndkRoot) },
            26,
            project.provider { objects },
        )
    }.get()

    @Test
    fun `setup sets group and description`() {
        assertEquals("build", task.group)
        assertEquals("Links the native image for ${Target.AARCH64.abi.abiString} (debug).", task.description)
    }

    @Test
    fun `the task directory is below the variant's build directory`() {
        assertEquals(project.file("build/androidgraal/debug/link"), task.taskDir.get().asFile)
        assertEquals(project.file("build/androidgraal/debug/link/jniLibs"), task.outputDir.get().asFile)
    }

    @Test
    fun `jniLibs follows the task directory`() {
        task.taskDir.set(project.file("other"))

        assertEquals(project.file("other/jniLibs"), task.outputDir.get().asFile)
    }

    @Test
    fun `the settings come from the extension`() {
        extension.imageName.set("hello")
        extension.useLLVM.set(false)

        assertEquals("hello", task.imageName.get())
        assertFalse(task.useLLVM.get())
    }

    @Test
    fun `a task setting beats the extension`() {
        extension.useLLVM.set(true)

        task.useLLVM.set(false)
        assertFalse(task.useLLVM.get())

        task.useLLVM.set(null as Boolean?)
        assertTrue(task.useLLVM.get())
    }

    @Test
    fun `the NDK gives the files and clang`() {
        assertEquals(ndkRoot, task.ndkDir.get())
    }

    @Test
    fun `setup passes toolchain, target, minSdk and objects through`() {
        assertEquals(toolchain, task.toolchain.get())
        assertEquals(Target.AARCH64, task.target.get())
        assertEquals(26, task.minSdk.get())
        assertEquals(objects.asFile, task.objectsDir.get().asFile)
    }
}
