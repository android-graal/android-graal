import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeOutput
import org.androidgraal.common.Toolchain

plugins {
    id("androidgraal.native")
}

description = "The LLVM fork substratevm's LLVM backend runs (llc, opt, llvm-link, clang, ld.lld, ...)."

val hostCMake = nativeHost.cmake()

/** The shipped tools as ninja targets: `ld.lld` is a link the `lld` target produces. */
val tools = Toolchain.LLVM_TOOLS.map { if (it == "ld.lld") "lld" else it }

val cmakeDir: File = layout.buildDirectory.dir("llvm").get().asFile
val binDir: File = cmakeDir.resolve("bin")

val buildLlvm = tasks.register<Script>("buildLlvm") {
    group = "android-graal"
    description = "Configures and builds vendor/llvm-project for the host."

    val llvm = source("llvm", nativeHost.vendor.llvm)
    val cmake = source("cmake", hostCMake.cmake)
    val ninja = source("ninja", hostCMake.ninja)
    val build = root("llvmBuild", cmakeDir)
    output("bin", binDir)

    env("CC", "/usr/bin/clang")
    env("CXX", "/usr/bin/clang++")
    progress("cmake -G Ninja")
    exec(
        cmake, "-G", "Ninja", "-S", "$llvm/llvm", "-B", build,
        "-DCMAKE_MAKE_PROGRAM=$ninja",
        "-DCMAKE_BUILD_TYPE=Release",
        "-DLLVM_ENABLE_ASSERTIONS=ON",
        "-DLLVM_ENABLE_PROJECTS=clang;lld",
        "-DLLVM_TARGETS_TO_BUILD=AArch64;ARM",
        "-DLLVM_INCLUDE_TESTS=OFF",
        "-DLLVM_INCLUDE_EXAMPLES=OFF",
        "-DLLVM_INCLUDE_BENCHMARKS=OFF",
        "-DLLVM_ENABLE_ZSTD=OFF",
        "-DCMAKE_OSX_ARCHITECTURES=arm64",
        // Each clang or lld link takes gigabytes of memory.
        "-DLLVM_PARALLEL_LINK_JOBS=2",
    )
    progress("ninja ${tools.joinToString(" ")}")
    exec(listOf(ninja, "-C", build) + tools)
}

nativeOutput(Native.Llvm.bin, binDir, buildLlvm)

tasks.assemble {
    dependsOn(buildLlvm)
}
