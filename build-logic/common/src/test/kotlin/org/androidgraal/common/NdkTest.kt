package org.androidgraal.common

import org.apache.commons.io.FileUtils
import org.semver4j.Semver
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class NdkTest {

    private val tmp: File = createTempDirectory("ndk-test").toFile()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `version is the revision of source properties`() {
        assertEquals(Semver.parse("30.0.16248370"), Ndk(ndk(tmp.resolve("ndk"))).version())
    }

    @Test
    fun `validate accepts a level the sysroot has and names one it has not`() {
        val ndk = Ndk(ndk(tmp.resolve("ndk")))

        ndk.validate(23)

        val failure = assertFailsWith<IllegalStateException> { ndk.validate(21) }
        assertTrue(failure.message!!.contains("${Target.AARCH64.triple}/21"), failure.message)
    }

    @Test
    fun `validate fails without the host toolchain`() {
        val root = tmp.resolve("bare")
        root.mkdirs()
        root.resolve("source.properties").writeText("Pkg.Revision = 30.0.16248370\n")

        val failure = assertFailsWith<IllegalStateException> { Ndk(root).validate(23) }
        assertTrue(failure.message!!.contains("no NDK host toolchain"))
    }

    private fun ndk(root: File): File {
        val toolchain = root.resolve("toolchains/llvm/prebuilt/${Host.current().ndkTag}")
        toolchain.resolve("bin").mkdirs()
        toolchain.resolve("sysroot/usr/lib/${Target.AARCH64.triple}/23").mkdirs()
        root.mkdirs()
        root.resolve("source.properties").writeText("Pkg.Revision = 30.0.16248370\n")
        return root
    }
}
