package org.androidgraal.common

import java.io.File

/** The layout of an android-graal toolchain at [root]; constructing one touches no file. */
class ToolchainLayout(val root: File) {

    val graalvmHome: File = root.resolve("graalvm")

    /** The launcher resolves the JDK relative to its own directory, so only this path works. */
    val nativeImage: File =
        graalvmHome.resolve("lib").resolve("svm").resolve("bin").resolve("native-image")

    /** Where `LLVMToolchain.getLLVMBinDir()` looks: `<java.home>/lib/llvm/bin`. */
    val llvmBin: File = graalvmHome.resolve("lib").resolve("llvm").resolve("bin")

    val versionFile: File = root.resolve(Toolchain.VERSION)

    fun target(target: Target): TargetLayout = TargetLayout(root.resolve("targets").resolve(target.triple))

    class TargetLayout(val root: File) {
        val jdkLibs: File = root.resolve("lib")

        val svmLibs: File = root.resolve("svm")

        val capCache: File = root.resolve("capcache")
    }

    fun version(): ToolchainVersion = ToolchainVersion.read(versionFile)

    fun checkApi(minSdk: Int) {
        val api = version().androidApi
        check(minSdk >= api) { "minSdk $minSdk is below the toolchain's android.api $api: $versionFile" }
    }

    fun validate() {
        check(versionFile.isFile) {
            "not an android-graal toolchain (no ${Toolchain.VERSION} file): $versionFile"
        }
        val version = version()
        check(version.toolchainVersion.isNotBlank()) { "no toolchain.version in $versionFile" }
        check(version.host == Host.current().toString()) {
            "toolchain built for ${version.host}, this host is ${Host.current()}: $versionFile"
        }
        check(nativeImage.isFile) { "no native-image at $nativeImage" }
        for (tool in Toolchain.LLVM_TOOLS) {
            check(llvmBin.resolve(tool).isFile) { "no LLVM tool at ${llvmBin.resolve(tool)}" }
        }
        for (target in Target.entries) {
            val targetLayout = target(target)
            for (library in Toolchain.JDK_LIBS) {
                check(targetLayout.jdkLibs.resolve(library).isFile) {
                    "no $library in ${targetLayout.jdkLibs}"
                }
            }
            for (library in Toolchain.SVM_LIBS) {
                check(targetLayout.svmLibs.resolve(library).isFile) {
                    "no $library in ${targetLayout.svmLibs}"
                }
            }
            for (cap in Toolchain.CAP_CACHE) {
                check(targetLayout.capCache.resolve(cap).isFile) {
                    "no $cap in ${targetLayout.capCache}"
                }
            }
        }
    }
}
