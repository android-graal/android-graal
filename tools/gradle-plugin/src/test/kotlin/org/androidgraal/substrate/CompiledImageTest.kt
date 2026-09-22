package org.androidgraal.substrate

import org.apache.commons.io.FileUtils
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class CompiledImageTest {

    private val tmp: File = createTempDirectory("compiled-image-test").toFile()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `the LLVM backend has the image object, then the LLVM object`() {
        writeLists()

        val compiled = CompiledImage.load(tmp, "hello", useLLVM = true)

        assertEquals(listOf(tmp.resolve("hello.o"), tmp.resolve("llvm.o")), compiled.objects)
        assertEquals(tmp.resolve("exported_symbols.list"), compiled.exportedSymbols)
    }

    @Test
    fun `the LIR backend has only the image object`() {
        writeLists()

        val compiled = CompiledImage.load(tmp, "hello", useLLVM = false)

        assertEquals(listOf(tmp.resolve("hello.o")), compiled.objects)
    }

    @Test
    fun `load reads only the library lists`() {
        writeLists()

        CompiledImage.load(tmp, "hello", useLLVM = true)

        assertFalse(tmp.resolve("hello.o").exists())
        assertFalse(tmp.resolve("llvm.o").exists())
    }

    @Test
    fun `the library lists are read trimmed and without blank lines`() {
        tmp.resolve(CompiledImage.STATIC_LIBRARIES).writeText("java\n\n  jvm \n")
        tmp.resolve(CompiledImage.LIBRARIES).writeText("m\ndl\n")

        val compiled = CompiledImage.load(tmp, "hello", useLLVM = true)

        assertEquals(listOf("java", "jvm"), compiled.staticLibraries)
        assertEquals(listOf("m", "dl"), compiled.libraries)
    }

    private fun writeLists() {
        tmp.resolve(CompiledImage.STATIC_LIBRARIES).writeText("java\n")
        tmp.resolve(CompiledImage.LIBRARIES).writeText("m\n")
    }
}
