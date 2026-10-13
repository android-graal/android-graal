package org.androidgraal.gradle

import kotlin.test.Test
import kotlin.test.assertTrue

class GenerateNativeImageTaskTest {

    @Test
    fun `the source lies in the given package and starts the image by name alone`() {
        val source = GenerateNativeImageTask.source("com.example.game", "game")

        assertTrue(source.startsWith("package com.example.game;\n\nimport org.androidgraal.art.Image;\n\n"), source)
        assertTrue("public static final String NAME = \"game\";" in source, source)
        assertTrue("public static void start() {\n        Image.start(NAME);\n    }" in source, source)
        assertTrue("public static <T> T createRoot(Class<T> root)" in source, source)
        assertTrue("public static void releaseProxy(Object proxy)" in source, source)
    }

    @Test
    fun `the manifest declares the application provider under the app's own authority`() {
        val manifest = GenerateNativeImageTask.MANIFEST

        assertTrue(
            manifest.startsWith("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\">\n"),
            manifest,
        )
        assertTrue("android:name=\"org.androidgraal.art.ApplicationProvider\"" in manifest, manifest)
        assertTrue("android:authorities=\"\${applicationId}.androidgraal\"" in manifest, manifest)
        assertTrue("android:exported=\"false\"" in manifest, manifest)
    }
}
