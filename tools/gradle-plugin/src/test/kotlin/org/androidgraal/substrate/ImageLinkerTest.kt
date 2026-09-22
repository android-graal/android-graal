package org.androidgraal.substrate

import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.apache.commons.io.FileUtils
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ImageLinkerTest {

    private val tmp: File = createTempDirectory("image-linker-test").toFile()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `command is the pinned link line for the two LLVM objects`() {
        val toolchain = fakeToolchain()
        val targetLayout = ToolchainLayout(toolchain).target(Target.AARCH64)
        val objects = tmp.resolve("objects")

        assertEquals(
            listOf(
                "$tmp/ndk/clang",
                "--target=aarch64-linux-android23",
                "-shared",
                "-o",
                "$tmp/jniLibs/arm64-v8a/libhello.so",
                "$objects/hello.o",
                "$objects/llvm.o",
                "-Wl,--version-script,$objects/exported_symbols.list",
                "-Wl,--no-undefined",
                "-Wl,--start-group",
                "${targetLayout.svmLibs}/liblibchelper.a",
                "${targetLayout.svmLibs}/libsvm_container.a",
                "${targetLayout.jdkLibs}/libnio.a",
                "${targetLayout.jdkLibs}/libnet.a",
                "${targetLayout.jdkLibs}/libjava.a",
                "${targetLayout.jdkLibs}/libzip.a",
                "${targetLayout.svmLibs}/libjvm.a",
                "-Wl,--end-group",
                "-Wl,-soname,libhello.so",
                "-lm",
                "-ldl",
                "-lz",
            ),
            ImageLinker(config(toolchain, objects), noRunner).command(),
        )
    }

    @Test
    fun `command is the pinned link line for the one LIR object, with section garbage collection`() {
        val toolchain = fakeToolchain()
        val targetLayout = ToolchainLayout(toolchain).target(Target.AARCH64)
        val objects = tmp.resolve("objects")

        assertEquals(
            listOf(
                "$tmp/ndk/clang",
                "--target=aarch64-linux-android23",
                "-shared",
                "-o",
                "$tmp/jniLibs/arm64-v8a/libhello.so",
                "$objects/hello.o",
                "-Wl,--version-script,$objects/exported_symbols.list",
                "-Wl,--no-undefined",
                "-Wl,--gc-sections",
                "-Wl,--start-group",
                "${targetLayout.svmLibs}/liblibchelper.a",
                "${targetLayout.svmLibs}/libsvm_container.a",
                "${targetLayout.jdkLibs}/libnio.a",
                "${targetLayout.jdkLibs}/libnet.a",
                "${targetLayout.jdkLibs}/libjava.a",
                "${targetLayout.jdkLibs}/libzip.a",
                "${targetLayout.svmLibs}/libjvm.a",
                "-Wl,--end-group",
                "-Wl,-soname,libhello.so",
                "-lm",
                "-ldl",
                "-lz",
            ),
            ImageLinker(config(toolchain, objects, useLLVM = false, libraries = listOf("dl", "z")), noRunner).command(),
        )
    }

    @Test
    fun `section garbage collection only without LLVM`() {
        val toolchain = fakeToolchain()
        val objects = tmp.resolve("objects")

        val llvm = ImageLinker(config(toolchain, objects, useLLVM = true), noRunner).command()
        val lir = ImageLinker(config(toolchain, objects, useLLVM = false), noRunner).command()

        assertFalse("-Wl,--gc-sections" in llvm, "$llvm")
        assertTrue("-Wl,--gc-sections" in lir, "$lir")
    }

    @Test
    fun `a static library with an archive is linked from the toolchain`() {
        val toolchain = fakeToolchain()
        val targetLayout = ToolchainLayout(toolchain).target(Target.AARCH64)
        val config = config(toolchain, tmp.resolve("objects"), staticLibraries = listOf("java"), libraries = listOf())

        val command = ImageLinker(config, noRunner).command()

        assertTrue("${targetLayout.jdkLibs}/libjava.a" in command, "$command")
    }

    @Test
    fun `a static library without an archive is linked as -l, once`() {
        val config = config(
            fakeToolchain(),
            tmp.resolve("objects"),
            staticLibraries = listOf("m", "java", "ffi"),
            libraries = listOf("dl", "m"),
        )

        val command = ImageLinker(config, noRunner).command()

        assertEquals(listOf("-lm", "-lffi", "-ldl"), command.filter { it.startsWith("-l") })
    }

    @Test
    fun `link packages what the linker produced`() {
        val toolchain = fakeToolchain()
        val config = config(toolchain, tmp.resolve("objects"))
        val stale = config.outputDir.resolve(ABI).resolve("stale.so")
        stale.parentFile.mkdirs()
        stale.writeText("old")
        val runner = ProcessRunner { command, _ ->
            File(command[command.indexOf("-o") + 1]).writeText("ELF")
            0
        }

        val image = ImageLinker(config, runner).link()

        assertEquals(config.outputDir.resolve(ABI).resolve("libhello.so"), image)
        assertEquals("ELF", image.readText())
        assertFalse(stale.exists(), "$stale must be gone")
    }

    @Test
    fun `link names the exit code when the linker fails`() {
        val config = config(fakeToolchain(), tmp.resolve("objects"))
        val runner = ProcessRunner { _, _ -> 1 }

        val failure = assertFailsWith<IllegalStateException> { ImageLinker(config, runner).link() }

        assertEquals("clang failed with exit code 1", failure.message)
    }

    private fun config(
        toolchain: File,
        objects: File,
        useLLVM: Boolean = true,
        staticLibraries: List<String> = listOf("libchelper", "svm_container", "m", "nio", "net", "java", "zip", "jvm"),
        libraries: List<String> = listOf("m", "dl", "z"),
    ): ImageLinker.Config {
        val compiled = CompiledImage(
            objects = listOfObjects(objects, useLLVM),
            exportedSymbols = objects.resolve(CompiledImage.EXPORTED_SYMBOLS),
            staticLibraries = staticLibraries,
            libraries = libraries,
        )
        return ImageLinker.Config(
            toolchain = ToolchainLayout(toolchain),
            clang = tmp.resolve("ndk").resolve("clang"),
            api = 23,
            imageName = "hello",
            compiled = compiled,
            useLLVM = useLLVM,
            outputDir = tmp.resolve("jniLibs"),
            target = Target.AARCH64,
        )
    }

    private fun listOfObjects(objects: File, useLLVM: Boolean): List<File> {
        return if (useLLVM) {
            listOf(objects.resolve("hello.o"), objects.resolve("llvm.o"))
        } else {
            listOf(objects.resolve("hello.o"))
        }
    }

    private fun fakeToolchain(): File = tmp.resolve("toolchain")

    private val noRunner = ProcessRunner { _, _ -> 0 }
}

private val ABI = Target.AARCH64.abi.abiString
