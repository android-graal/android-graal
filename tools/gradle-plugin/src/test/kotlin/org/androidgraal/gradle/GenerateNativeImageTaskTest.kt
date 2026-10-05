package org.androidgraal.gradle

import kotlin.test.Test
import kotlin.test.assertTrue

class GenerateNativeImageTaskTest {

    @Test
    fun `the source lies in the given package and imports Image`() {
        val source = GenerateNativeImageTask.source("com.example.game", "game")

        assertTrue(source.startsWith("package com.example.game;\n\nimport org.androidgraal.art.Image;\n\n"), source)
        assertTrue("public static final String NAME = \"game\";" in source, source)
        assertTrue("public static <T> T createRoot(Class<T> root)" in source, source)
        assertTrue("public static void releaseProxy(Object proxy)" in source, source)
    }
}
