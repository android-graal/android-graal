package org.androidgraal.common

import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ToolchainLayoutTest {

    @TempDir
    lateinit var tmp: File

    @Test
    fun `validate accepts a complete toolchain`() {
        val layout = ToolchainLayout(fakeToolchain())

        layout.validate()
    }

    @Test
    fun `validate names the missing CAP cache`() {
        val layout = ToolchainLayout(fakeToolchain())
        val targetLayout = layout.target(Target.AARCH64)
        targetLayout.capCache.resolve("PosixDirectives.cap").delete()

        val failure = assertFailsWith<IllegalStateException> { layout.validate() }

        assertTrue("no PosixDirectives.cap in ${targetLayout.capCache}" in failure.message!!, failure.message)
    }

    @Test
    fun `validate names the missing JDK library`() {
        val layout = ToolchainLayout(fakeToolchain())
        val targetLayout = layout.target(Target.AARCH64)
        targetLayout.jdkLibs.resolve("libnio.a").delete()

        val failure = assertFailsWith<IllegalStateException> { layout.validate() }

        assertTrue("no libnio.a in ${targetLayout.jdkLibs}" in failure.message!!, failure.message)
    }

    @Test
    fun `validate names the missing SVM library`() {
        val layout = ToolchainLayout(fakeToolchain())
        val targetLayout = layout.target(Target.AARCH64)
        targetLayout.svmLibs.resolve("libjvm.a").delete()

        val failure = assertFailsWith<IllegalStateException> { layout.validate() }

        assertTrue("no libjvm.a in ${targetLayout.svmLibs}" in failure.message!!, failure.message)
    }

    @Test
    fun `validate refuses a toolchain built on another host`() {
        val root = fakeToolchain()
        val layout = ToolchainLayout(root)
        layout.versionFile.writeText(VERSION.replace("host=${Host.current()}", "host=solaris-sparc"))

        val failure = assertFailsWith<IllegalStateException> { layout.validate() }

        assertTrue(failure.message!!.startsWith("toolchain built for solaris-sparc"), failure.message)
    }

    @Test
    fun `checkApi refuses a minSdk below the toolchain's android api`() {
        val layout = ToolchainLayout(fakeToolchain())

        val failure = assertFailsWith<IllegalStateException> { layout.checkApi(21) }

        assertEquals("minSdk 21 is below the toolchain's android.api 23: ${layout.versionFile}", failure.message)
    }

    @Test
    fun `checkApi accepts a minSdk equal to the toolchain's android api`() {
        ToolchainLayout(fakeToolchain()).checkApi(23)
    }

    @Test
    fun `checkApi accepts a minSdk above the toolchain's android api`() {
        ToolchainLayout(fakeToolchain()).checkApi(30)
    }

    private fun fakeToolchain(): File {
        val root = tmp.resolve("toolchain")
        val layout = ToolchainLayout(root)
        layout.nativeImage.parentFile.mkdirs()
        layout.nativeImage.writeText("#!/bin/sh\n")
        layout.versionFile.writeText(VERSION)
        layout.llvmBin.mkdirs()
        Toolchain.LLVM_TOOLS.forEach { layout.llvmBin.resolve(it).writeText("") }
        for (target in Target.entries) {
            val targetLayout = layout.target(target)
            listOf(targetLayout.jdkLibs, targetLayout.svmLibs, targetLayout.capCache)
                .forEach { it.mkdirs() }
            Toolchain.JDK_LIBS.forEach { targetLayout.jdkLibs.resolve(it).writeText("") }
            Toolchain.SVM_LIBS.forEach { targetLayout.svmLibs.resolve(it).writeText("") }
            Toolchain.CAP_CACHE.forEach { targetLayout.capCache.resolve(it).writeText("") }
        }
        return root
    }
}

private val VERSION = """
    toolchain.version=test
    host=${Host.current()}
    graal.commit=0000000000000000000000000000000000000000
    llvm.commit=0000000000000000000000000000000000000000
    jdk.commit=0000000000000000000000000000000000000000
    ndk.version=30.0.16248370
    android.api=23

""".trimIndent()
