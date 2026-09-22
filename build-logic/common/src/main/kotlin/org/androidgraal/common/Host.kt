package org.androidgraal.common

import com.badlogic.gdx.jnigen.commons.Architecture
import com.badlogic.gdx.jnigen.commons.Architecture.Bitness
import com.badlogic.gdx.jnigen.commons.HostDetection
import com.badlogic.gdx.jnigen.commons.Os

enum class Host(val os: Os, val architecture: Architecture, val bitness: Bitness, val ndkTag: String) {
    MACOS_AARCH64(Os.MacOsX, Architecture.ARM, Bitness._64, "darwin-x86_64"),
    MACOS_X86_64(Os.MacOsX, Architecture.x86, Bitness._64, "darwin-x86_64"),
    LINUX_X86_64(Os.Linux, Architecture.x86, Bitness._64, "linux-x86_64"),
    WINDOWS_X86_64(Os.Windows, Architecture.x86, Bitness._64, "windows-x86_64"),
    ;

    /** Gradle's `OperatingSystemFamily` name. */
    fun osFamily(): String = when (os) {
        Os.MacOsX -> "macos"
        Os.Linux -> "linux"
        Os.Windows -> "windows"
        else -> error("not a host OS: $os")
    }

    /** Gradle's `MachineArchitecture` name. */
    fun machineArchitecture(): String = when {
        architecture == Architecture.ARM && bitness == Bitness._64 -> "aarch64"
        architecture == Architecture.x86 && bitness == Bitness._64 -> "x86-64"
        else -> error("not a host architecture: $architecture $bitness")
    }

    override fun toString(): String = "${osFamily()}-${machineArchitecture()}"

    companion object {
        val PUBLISHED: Set<Host> = setOf(MACOS_AARCH64)

        fun current(): Host = of(HostDetection.os, HostDetection.architecture, HostDetection.bitness)

        internal fun of(os: Os, architecture: Architecture, bitness: Bitness): Host =
            entries.find { it.os == os && it.architecture == architecture && it.bitness == bitness }
                ?: throw IllegalArgumentException("unsupported host: $os $architecture $bitness")
    }
}
