package org.androidgraal.common

import com.badlogic.gdx.jnigen.commons.Architecture
import com.badlogic.gdx.jnigen.commons.Architecture.Bitness
import com.badlogic.gdx.jnigen.commons.Os
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class HostTest {

    @Test
    fun `detection maps to the supported hosts`() {
        assertEquals(Host.MACOS_AARCH64, Host.of(Os.MacOsX, Architecture.ARM, Bitness._64))
        assertEquals(Host.MACOS_X86_64, Host.of(Os.MacOsX, Architecture.x86, Bitness._64))
        assertEquals(Host.LINUX_X86_64, Host.of(Os.Linux, Architecture.x86, Bitness._64))
        assertEquals(Host.WINDOWS_X86_64, Host.of(Os.Windows, Architecture.x86, Bitness._64))
    }

    @Test
    fun `a host prints as the operating system family and the machine architecture`() {
        assertEquals("macos-aarch64", Host.MACOS_AARCH64.toString())
        assertEquals("macos-x86-64", Host.MACOS_X86_64.toString())
        assertEquals("linux-x86-64", Host.LINUX_X86_64.toString())
        assertEquals("windows-x86-64", Host.WINDOWS_X86_64.toString())
    }

    @Test
    fun `every host has Gradle's operating system family and machine architecture`() {
        assertEquals(
            listOf(
                "macos" to "aarch64",
                "macos" to "x86-64",
                "linux" to "x86-64",
                "windows" to "x86-64",
            ),
            Host.entries.map { it.osFamily() to it.machineArchitecture() },
        )
    }

    @Test
    fun `ndkTag is the NDK's host directory name`() {
        assertEquals("darwin-x86_64", Host.MACOS_AARCH64.ndkTag)
        assertEquals("darwin-x86_64", Host.MACOS_X86_64.ndkTag)
        assertEquals("linux-x86_64", Host.LINUX_X86_64.ndkTag)
        assertEquals("windows-x86_64", Host.WINDOWS_X86_64.ndkTag)
    }

    @Test
    fun `an unsupported combination fails by name`() {
        val arm = assertFailsWith<IllegalArgumentException> { Host.of(Os.Linux, Architecture.ARM, Bitness._64) }
        assertEquals("unsupported host: Linux ARM _64", arm.message)
        val riscv = assertFailsWith<IllegalArgumentException> { Host.of(Os.Linux, Architecture.RISCV, Bitness._64) }
        assertEquals("unsupported host: Linux RISCV _64", riscv.message)
    }
}
