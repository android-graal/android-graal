package org.androidgraal.buildlogic

import com.badlogic.gdx.jnigen.commons.Os
import org.androidgraal.common.Host
import org.gradle.api.GradleException
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CMakeTest {

    private val tmp: File = createTempDirectory("cmake-test").toFile()

    private val sdk: AndroidSdk = AndroidSdk(File(tmp, "sdk").apply { mkdirs() })

    @AfterTest
    fun cleanUp() {
        tmp.deleteRecursively()
    }

    @Test
    fun `cmake dir wins over the sdk package and the path`() {
        val own = bin(File(tmp, "own"), "cmake", "ninja")
        val path = bin(File(tmp, "path"), "cmake", "ninja")
        sdkPackage("3.31.6")

        val found = CMake.find(settings("cmake.dir=${File(tmp, "own")}"), sdk, listOf(path))

        assertEquals(exe(own, "cmake"), found.cmake)
        assertEquals(exe(own, "ninja"), found.ninja)
    }

    @Test
    fun `a cmake dir without a cmake fails and never falls through`() {
        bin(File(tmp, "own"))
        bin(File(tmp, "path"), "cmake", "ninja")

        val failure = assertFailsWith<GradleException> {
            CMake.find(settings("cmake.dir=${File(tmp, "own")}"), sdk, listOf(File(tmp, "path/bin")))
        }

        assertEquals("no cmake in ${File(tmp, "own/bin")}", failure.message)
    }

    @Test
    fun `the newest sdk package is taken before the path`() {
        sdkPackage("3.22.1")
        val newest = sdkPackage("3.31.6")
        val path = bin(File(tmp, "path"), "cmake", "ninja")

        val found = CMake.find(settings(), sdk, listOf(path))

        assertEquals(exe(newest, "cmake"), found.cmake)
        assertEquals(exe(newest, "ninja"), found.ninja)
    }

    @Test
    fun `an explicit version takes exactly that sdk package`() {
        val old = sdkPackage("3.22.1")
        sdkPackage("3.31.6")

        val found = CMake.find(settings("androidgraal.cmake.version=3.22.1"), sdk, emptyList())

        assertEquals(exe(old, "cmake"), found.cmake)
    }

    @Test
    fun `an explicit version that is not installed fails although cmake is on the path`() {
        sdkPackage("3.31.6")
        val path = bin(File(tmp, "path"), "cmake", "ninja")

        val failure = assertFailsWith<GradleException> {
            CMake.find(settings("androidgraal.cmake.version=3.22.1"), sdk, listOf(path))
        }

        assertEquals("no cmake 3.22.1 under ${File(sdk.root, "cmake")}; installed: 3.31.6", failure.message)
    }

    @Test
    fun `without a package the path is searched in order`() {
        val first = bin(File(tmp, "first"))
        val second = bin(File(tmp, "second"), "cmake", "ninja")

        val found = CMake.find(settings(), sdk, listOf(first, second))

        assertEquals(exe(second, "cmake"), found.cmake)
    }

    @Test
    fun `nothing anywhere fails naming every place`() {
        val failure = assertFailsWith<GradleException> {
            CMake.find(settings(), sdk, listOf(bin(File(tmp, "path"))))
        }

        assertEquals(
            "no cmake: no cmake.dir or \$CMAKE_HOME, no package under ${File(sdk.root, "cmake")}, none on PATH",
            failure.message,
        )
    }

    @Test
    fun `ninja comes from the path when none sits next to cmake`() {
        val own = bin(File(tmp, "own"), "cmake")
        val path = bin(File(tmp, "path"), "ninja")

        val found = CMake.find(settings("cmake.dir=${File(tmp, "own")}"), sdk, listOf(path))

        assertEquals(exe(own, "cmake"), found.cmake)
        assertEquals(exe(path, "ninja"), found.ninja)
    }

    @Test
    fun `no ninja fails naming the cmake it looked next to`() {
        val own = bin(File(tmp, "own"), "cmake")

        val failure = assertFailsWith<GradleException> {
            CMake.find(settings("cmake.dir=${File(tmp, "own")}"), sdk, listOf(bin(File(tmp, "path"))))
        }

        assertEquals("no ninja: none next to ${exe(own, "cmake")}, none on PATH", failure.message)
    }

    private fun settings(local: String = ""): BuildSettings = BuildSettings(
        "androidgraal.group=org.androidgraal\nandroidgraal.version=0.1.0-SNAPSHOT\nandroidgraal.android.api=23\n",
        "sdk.dir=${sdk.root}\n$local\n",
        { null },
        { null },
        tmp,
    )

    private fun sdkPackage(revision: String): File {
        val root = File(sdk.root, "cmake/$revision")
        root.mkdirs()
        File(root, "source.properties").writeText("Pkg.Revision = $revision\n")
        return bin(root, "cmake", "ninja")
    }

    private fun bin(root: File, vararg names: String): File {
        val bin = File(root, "bin")
        bin.mkdirs()
        names.forEach { exe(bin, it).writeText("") }
        return bin
    }

    private fun exe(bin: File, name: String): File {
        val fileName = if (Host.current().os == Os.Windows) "$name.exe" else name
        return File(bin, fileName)
    }
}
