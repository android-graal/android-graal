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

class NativeHostTest {

    private val tmp: File = createTempDirectory("native-host-test").toFile()

    private val sdkRoot: File = File(tmp, "sdk")

    @AfterTest
    fun cleanUp() {
        tmp.deleteRecursively()
    }

    @Test
    fun `the ndk resolves at construction, cmake on each call`() {
        ndk(File(sdkRoot, "ndk/30.0.16248370"), "30.0.16248370")

        val host = host(settings())
        assertEquals(File(tmp, "vendor/graal"), host.vendor.graal)

        assertEquals(Semver.parse("30.0.16248370"), host.ndk.version())
        assertEquals(23, host.androidApi)

        val failure = assertFailsWith<GradleException> { host.cmake() }
        assertEquals(
            "no cmake: no cmake.dir or \$CMAKE_HOME, no package under ${File(sdkRoot, "cmake")}, none on PATH",
            failure.message,
        )
    }

    @Test
    fun `ndk dir takes an ndk outside the sdk`() {
        ndk(File(sdkRoot, "ndk/30.0.16248370"), "30.0.16248370")
        val own = ndk(File(tmp, "own-ndk"), "27.0.12077973")

        val host = host(settings("ndk.dir=$own\n"))

        assertEquals(own, host.ndk.root)
    }

    @Test
    fun `ndk dir accepts the matching version and fails on a differing one`() {
        val own = ndk(File(tmp, "own-ndk"), "27.0.12077973")

        val host = host(settings("ndk.dir=$own\nandroidgraal.ndk.version=27.0.12077973\n"))
        assertEquals(own, host.ndk.root)

        val failure = assertFailsWith<GradleException> {
            host(settings("ndk.dir=$own\nandroidgraal.ndk.version=30.0.16248370\n"))
        }
        assertEquals(
            "ndk.dir $own is NDK 27.0.12077973, androidgraal.ndk.version says 30.0.16248370",
            failure.message,
        )
    }

    @Test
    fun `an ndk from ndk dir is validated too`() {
        val own = ndk(File(tmp, "own-ndk"), "27.0.12077973")
        val level =
            File(own, "toolchains/llvm/prebuilt/${Host.current().ndkTag}/sysroot/usr/lib/${Target.AARCH64.triple}/23")
        level.delete()

        val failure = assertFailsWith<IllegalStateException> { host(settings("ndk.dir=$own\n")) }
        assertEquals("the NDK cannot link for API level 23: no $level", failure.message)
    }

    private fun host(settings: BuildSettings): NativeHost {
        sdkRoot.mkdirs()
        return NativeHost(tmp, settings, AndroidSdk(sdkRoot), emptyList())
    }

    private fun settings(local: String = ""): BuildSettings = BuildSettings(
        "androidgraal.group=org.androidgraal\nandroidgraal.version=0.1.0-SNAPSHOT\nandroidgraal.android.api=23\n",
        "sdk.dir=$sdkRoot\n$local",
        { null },
        { null },
        tmp,
    )

    private fun ndk(root: File, revision: String): File {
        val toolchain = File(root, "toolchains/llvm/prebuilt/${Host.current().ndkTag}")
        File(toolchain, "bin").mkdirs()
        File(toolchain, "sysroot/usr/lib/${Target.AARCH64.triple}/23").mkdirs()
        File(root, "source.properties").writeText("Pkg.Revision = $revision\n")
        return root
    }
}
