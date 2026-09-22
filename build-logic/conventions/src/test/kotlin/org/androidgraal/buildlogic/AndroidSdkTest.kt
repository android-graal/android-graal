package org.androidgraal.buildlogic

import org.androidgraal.common.Host
import org.androidgraal.common.Target
import org.gradle.api.GradleException
import org.semver4j.Semver
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AndroidSdkTest {

    private val tmp: File = createTempDirectory("android-sdk-test").toFile()

    @AfterTest
    fun cleanUp() {
        tmp.deleteRecursively()
    }

    @Test
    fun `ndk takes the newest without a version and the exact revision with one`() {
        val sdk = sdk("find-sdk")
        ndk(File(sdk.root, "ndk/27.0.1"), "27.0.1")
        val beta = ndk(File(sdk.root, "ndk/29.0.14033849"), "29.0.14033849-beta4")
        val release = ndk(File(sdk.root, "ndk/30.0.16248370"))

        assertEquals(release, sdk.ndk(null).root)
        assertEquals(beta, sdk.ndk(Semver.parse("29.0.14033849-beta4")).root)

        val failure = assertFailsWith<GradleException> { sdk.ndk(Semver.parse("29.0.14033849")) }
        assertTrue(failure.message!!.contains("27.0.1, 29.0.14033849-beta4, 30.0.16248370"), failure.message)
    }

    @Test
    fun `ndk fails on an sdk without an ndk directory`() {
        val failure = assertFailsWith<GradleException> { sdk("no-ndk-sdk").ndk(null) }
        assertTrue(failure.message!!.startsWith("no NDK under"), failure.message)
    }

    @Test
    fun `the newest ndk is the highest revision under the sdk`() {
        val sdk = sdk("sdk")
        ndk(File(sdk.root, "ndk/27.0.1"), "27.0.1")
        ndk(File(sdk.root, "ndk/30.0.16248370"))
        File(sdk.root, "ndk/unpacked").mkdirs()

        assertEquals(listOf("27.0.1", "30.0.16248370"), sdk.ndks().map { it.version().toString() })
        assertEquals(Semver.parse("30.0.16248370"), sdk.ndk(null).version())
    }

    @Test
    fun `the newest ndk reads the revision, not the directory name`() {
        val preview = sdk("preview-sdk")
        ndk(File(preview.root, "ndk/27.0.1"), "27.0.1")
        val beta = ndk(File(preview.root, "ndk/29.0.14033849"), "29.0.14033849-beta4")

        val newest = preview.ndk(null)
        assertEquals(Semver.parse("29.0.14033849-beta4"), newest.version())
        assertEquals(beta, newest.root)

        val release = ndk(File(preview.root, "ndk/30.0.16248370"))
        assertEquals(release, sdk("preview-sdk").ndk(null).root)
    }

    @Test
    fun `ndks is empty without an ndk directory`() {
        val sdk = sdk("empty-sdk")

        assertEquals(emptyList(), sdk.ndks())
        assertFailsWith<GradleException> { sdk.ndk(null) }
    }

    @Test
    fun `cmake takes the newest without a version and the exact revision with one`() {
        val sdk = sdk("cmake-sdk")
        val old = cmake(File(sdk.root, "cmake/3.22.1"), "3.22.1")
        val new = cmake(File(sdk.root, "cmake/3.31.6"), "3.31.6")
        File(sdk.root, "cmake/unpacked/bin").mkdirs()

        assertEquals(mapOf(Semver.parse("3.22.1")!! to old, Semver.parse("3.31.6")!! to new), sdk.cmakes())
        assertEquals(new, sdk.cmake(null))
        assertEquals(old, sdk.cmake(Semver.parse("3.22.1")))
        assertNull(sdk.cmake(Semver.parse("3.30.0")))
    }

    @Test
    fun `cmake is null without a package`() {
        val sdk = sdk("no-cmake-sdk")

        assertEquals(emptyMap(), sdk.cmakes())
        assertNull(sdk.cmake(null))
        assertNull(sdk.cmake(Semver.parse("3.31.6")))
    }

    @Test
    fun `a package whose revision is no version is skipped`() {
        val sdk = sdk("legacy-cmake-sdk")
        cmake(File(sdk.root, "cmake/3.10.2.4988404"), "3.10.2.4988404")
        val good = cmake(File(sdk.root, "cmake/3.31.6"), "3.31.6")

        assertEquals(mapOf(Semver.parse("3.31.6")!! to good), sdk.cmakes())
        assertEquals(good, sdk.cmake(null))
    }

    @Test
    fun `the newest cmake reads the revision, not the directory name`() {
        val sdk = sdk("cmake-revision-sdk")
        val newer = cmake(File(sdk.root, "cmake/a"), "3.31.6")
        cmake(File(sdk.root, "cmake/b"), "3.22.1")

        assertEquals(newer, sdk.cmake(null))
    }

    private fun sdk(name: String): AndroidSdk {
        val root = File(tmp, name)
        root.mkdirs()
        return AndroidSdk(root)
    }

    private fun ndk(root: File, revision: String = "30.0.16248370"): File {
        val toolchain = File(root, "toolchains/llvm/prebuilt/${Host.current().ndkTag}")
        File(toolchain, "bin").mkdirs()
        File(toolchain, "sysroot/usr/lib/${Target.AARCH64.triple}/23").mkdirs()
        root.mkdirs()
        File(root, "source.properties").writeText("Pkg.Revision = $revision\n")
        return root
    }

    private fun cmake(root: File, revision: String): File {
        File(root, "bin").mkdirs()
        File(root, "source.properties").writeText("Pkg.Revision = $revision\n")
        return File(root, "bin")
    }
}
