package org.androidgraal.common

import kotlin.test.Test
import kotlin.test.assertEquals

class TargetTest {

    @Test
    fun `the title is the name with only its first letter upper case`() {
        assertEquals(listOf("Aarch64", "Arm32"), Target.entries.map { it.title })
    }
}
