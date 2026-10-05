package org.androidgraal.generator.emit

import kotlin.test.Test
import kotlin.test.assertEquals

class JniNamesTest {

    @Test
    fun `JNI mangling escapes underscores, nested classes and non-ASCII names`() {
        assertEquals(
            "Java_fix_under_1score_Snake_1Case_run_10",
            jniName("fix.under_score.Snake_Case", "run_0"),
        )
        assertEquals("Java_fix_Outer_00024Inner_go0", jniName("fix.Outer\$Inner", "go0"))
        assertEquals("Java_fix_Gr_000fc_000dfe_z_000e4hle0", jniName("fix.Grüße", "zähle0"))
    }
}
