package org.androidgraal.substrate

import org.androidgraal.common.Host
import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.apache.commons.io.FileUtils
import java.io.File

class SubstrateExecutor(private val config: Config, private val runner: ProcessRunner) {

    data class Config(
        val toolchain: ToolchainLayout,
        val target: Target,
        val host: Host,
        val imageName: String,
        val mainClass: String,
        val classpath: List<File>,
        val workDir: File,
        val objectsDir: File,
        val buildArgs: List<String> = emptyList(),
        /** For the builder's JVM. */
        val jvmArgs: List<String> = emptyList(),
        /** Seen by the image builder, not by the running image. */
        val systemProperties: Map<String, String> = emptyMap(),
        val configurationFileDirectories: List<File> = emptyList(),
        val verbose: Boolean = false,
        val quickBuild: Boolean = false,
        val useLLVM: Boolean = true,
    )

    fun command(): List<String> {
        val target = config.target
        val work = config.workDir.absolutePath
        return listOf(
            config.toolchain.nativeImage.absolutePath,
            "--shared",
            *argsIf(config.useLLVM, "--tool:llvm-backend"),
            "-H:+UnlockExperimentalVMOptions",
            // The macOS arm64 `llvm-shadowed`/`javacpp-shadowed` jars graal pins lack `Multi-Release: true`,
            // load as automatic modules and fail the boot module check.
            *argsIf(config.useLLVM && config.host == Host.MACOS_AARCH64, "-H:CheckBootModuleDependencies=0"),
            "-Dsvm.platform=${target.platform}",
            "-Dsvm.targetArch=${target.arch}",
            "-Djdk.internal.foreign.CABI=${target.cabi}",
            *config.systemProperties.map { (key, value) -> "-D$key=$value" }.toTypedArray(),
            "-H:+UseCAPCache",
            "-H:CAPCacheDir=" + config.toolchain.target(target).capCache.absolutePath,
            "-H:TempDirectory=$work",
            "-o",
            config.workDir.resolve(config.imageName).absolutePath,
            "-march=${target.march}",
            "-H:+ReportExceptionStackTraces",
            "-H:+AddAllCharsets",
            "-H:+IncludeAllLocales",
            // The builder runs on the host JDK, whose macOS `DefaultInterface` caches the host's default
            // `NetworkInterface`; graal initializes it at run time only for Darwin targets.
            "--initialize-at-run-time=java.net.DefaultInterface",
            "-H:+ExitAfterRelocatableImageWrite",
            *argsIf(config.verbose, "--verbose"),
            *argsIf(config.quickBuild, "-Ob"),
            *config.jvmArgs.map { "-J$it" }.toTypedArray(),
            *argsIf(
                config.configurationFileDirectories.isNotEmpty(),
                "-H:ConfigurationFileDirectories=" +
                    config.configurationFileDirectories.joinToString(",") { it.absolutePath },
            ),
            *config.buildArgs.toTypedArray(),
            "-cp",
            config.classpath.joinToString(File.pathSeparator) { it.absolutePath },
            config.mainClass,
        )
    }

    fun compile(): CompiledImage {
        FileUtils.deleteDirectory(config.workDir)
        FileUtils.deleteDirectory(config.objectsDir)
        config.workDir.mkdirs()
        config.objectsDir.mkdirs()

        runner.runOrFail("native-image", command(), config.workDir)

        val temp = tempDirectory()
        copy(temp.resolve("${config.imageName}.o"), CompiledImage.imageObject(config.objectsDir, config.imageName))
        if (config.useLLVM) {
            copy(
                temp.resolve(LLVM_DIR).resolve(CompiledImage.LLVM_OBJECT),
                config.objectsDir.resolve(CompiledImage.LLVM_OBJECT),
            )
        }
        listOf(CompiledImage.EXPORTED_SYMBOLS, CompiledImage.STATIC_LIBRARIES, CompiledImage.LIBRARIES)
            .forEach { copy(temp.resolve(it), config.objectsDir.resolve(it)) }
        return CompiledImage.load(config.objectsDir, config.imageName, config.useLLVM)
    }

    /** The `SVM-<millis>` directory the builder creates under `-H:TempDirectory`. */
    private fun tempDirectory(): File {
        val candidates = config.workDir.listFiles().orEmpty()
            .filter { it.isDirectory && it.name.startsWith(TEMP_PREFIX) }
        check(candidates.size == 1) {
            "expected one $TEMP_PREFIX* directory under ${config.workDir}, found $candidates"
        }
        return candidates.single()
    }

    private fun copy(source: File, target: File) {
        check(source.isFile) { "native-image did not produce $source" }
        source.copyTo(target, overwrite = true)
    }

    private companion object {
        const val TEMP_PREFIX = "SVM-"

        /** The LLVM backend's subdirectory of the `SVM-*` directory. */
        const val LLVM_DIR = "llvm"
    }
}
