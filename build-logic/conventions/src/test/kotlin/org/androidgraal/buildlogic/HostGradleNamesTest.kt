package org.androidgraal.buildlogic

import org.androidgraal.common.Host
import org.gradle.nativeplatform.MachineArchitecture
import org.gradle.nativeplatform.OperatingSystemFamily
import kotlin.test.Test
import kotlin.test.assertEquals

class HostGradleNamesTest {

    @Test
    fun `every host spells Gradle's own OS family and architecture names`() {
        val expected = mapOf(
            Host.MACOS_AARCH64 to (OperatingSystemFamily.MACOS to MachineArchitecture.ARM64),
            Host.MACOS_X86_64 to (OperatingSystemFamily.MACOS to MachineArchitecture.X86_64),
            Host.LINUX_X86_64 to (OperatingSystemFamily.LINUX to MachineArchitecture.X86_64),
            Host.WINDOWS_X86_64 to (OperatingSystemFamily.WINDOWS to MachineArchitecture.X86_64),
        )

        assertEquals(Host.entries.toSet(), expected.keys)
        for ((host, names) in expected) {
            assertEquals(names, host.osFamily() to host.machineArchitecture(), "$host")
        }
    }
}
