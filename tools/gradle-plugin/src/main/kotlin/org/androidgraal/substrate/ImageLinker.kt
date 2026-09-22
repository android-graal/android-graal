package org.androidgraal.substrate

import org.androidgraal.common.Target
import org.androidgraal.common.Toolchain
import org.androidgraal.common.ToolchainLayout
import org.apache.commons.io.FileUtils
import java.io.File

class ImageLinker(private val config: Config, private val runner: ProcessRunner) {

    data class Config(
        val toolchain: ToolchainLayout,
        val target: Target,
        val clang: File,
        val api: Int,
        val imageName: String,
        val compiled: CompiledImage,
        val useLLVM: Boolean,
        val outputDir: File,
    )

    fun command(): List<String> = listOf(
        config.clang.absolutePath,
        "--target=${config.target.clangTarget}${config.api}",
        "-shared",
        "-o",
        image().absolutePath,
        *config.compiled.objects.map { it.absolutePath }.toTypedArray(),
        "-Wl,--version-script," + config.compiled.exportedSymbols.absolutePath,
        "-Wl,--no-undefined",
        *argsIf(!config.useLLVM, "-Wl,--gc-sections"),
        "-Wl,--start-group",
        *archives().map { it.absolutePath }.toTypedArray(),
        "-Wl,--end-group",
        // `dlopen("lib<name>.so")` finds the already loaded image by its `DT_SONAME`.
        "-Wl,-soname,lib${config.imageName}.so",
        *libraries().map { "-l$it" }.toTypedArray(),
    )

    fun link(): File {
        val image = image()
        val command = command()
        FileUtils.deleteDirectory(config.outputDir)
        image.parentFile.mkdirs()

        runner.runOrFail(config.clang.name, command, image.parentFile)

        check(image.isFile) { "the linker did not produce $image" }
        return image
    }

    private fun image(): File =
        config.outputDir.resolve(config.target.abi.abiString).resolve("lib${config.imageName}.so")

    private fun archives(): List<File> = config.compiled.staticLibraries.mapNotNull(::archive)

    private fun libraries(): List<String> {
        val withoutArchive = config.compiled.staticLibraries.filter { archive(it) == null }
        return (withoutArchive + config.compiled.libraries).distinct()
    }

    private fun archive(name: String): File? {
        val targetLayout = config.toolchain.target(config.target)
        return when (val file = "lib$name.a") {
            in Toolchain.JDK_LIBS -> targetLayout.jdkLibs.resolve(file)
            in Toolchain.SVM_LIBS -> targetLayout.svmLibs.resolve(file)
            else -> null
        }
    }
}
