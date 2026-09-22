import org.androidgraal.buildlogic.Compiler
import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.buildlogic.nativeOutput
import org.androidgraal.common.Target

plugins {
    id("androidgraal.native")
}

description = "liblibchelper.a, libjvm.a and libsvm_container.a compiled for aarch64-linux-android."

val substratevm: File = nativeHost.vendor.graal.resolve("substratevm")

val target = Target.AARCH64

val fallbacksFile: FileCollection = nativeInput(Native.Graal.jvmFuncsFallbacks)
val jdkIncludeDir: FileCollection = nativeInput(Native.Jdk.include)

val staticLibs: File = layout.buildDirectory.dir("lib").get().asFile

// commonCFlags and the two container lists are the "linux" cflags of jvm.posix and libcontainer in
// vendor/graal/substratevm/mx.substratevm/suite.py, minus -g and -gdwarf-5.
val commonCFlags = listOf(
    "-fPIC",
    "-O2",
    "-ffunction-sections",
    "-fdata-sections",
    "-fvisibility=hidden",
    "-D_FORTIFY_SOURCE=0",
    "-D_GNU_SOURCE",
)
val containerCFlags = listOf(
    "-O2", "-fvisibility=hidden", "-fPIC",
    "-DNATIVE_IMAGE", "-DLINUX", "-DINCLUDE_SUFFIX_COMPILER=_gcc",
    "-D__STDC_FORMAT_MACROS", "-D__STDC_LIMIT_MACROS", "-D__STDC_CONSTANT_MACROS",
)
val containerCxxFlags = listOf("-fno-rtti", "-fno-exceptions", "-std=c++14")
val containerIncludes = listOf(
    "src/java.base/share/native/include",
    "src/java.base/unix/native/include",
    "src/hotspot",
    "src/hotspot/share",
    "src/hotspot/os/linux",
    "src/hotspot/os/posix",
    "src/hotspot/os/posix/include",
    "src/svm",
    "src/svm/share",
)

val buildSvmStaticLibs = tasks.register<Script>("buildSvmStaticLibs") {
    group = "android-graal"
    description = "Compiles the three SubstrateVM support libraries for ${target.triple}."

    val objDir: File = layout.buildDirectory.dir("obj").get().asFile

    val fallbacks = input("fallbacks", fallbacksFile)
    val jdkInclude = input("jdkInclude", jdkIncludeDir)
    val chelperDir = source("libchelper", substratevm.resolve("src/com.oracle.svm.native.libchelper"))
    val jvmPosixDir = source("jvmPosix", substratevm.resolve("src/com.oracle.svm.native.jvm.posix"))
    val containerDir = source("libcontainer", substratevm.resolve("src/com.oracle.svm.native.libcontainer"))
    source("ndk", nativeHost.ndk.root)
    val obj = root("obj", objDir)
    val lib = output("lib", staticLibs)

    val toolchain = rel(nativeHost.ndk.toolchain)
    val clang = Compiler("$toolchain/bin/clang", "$toolchain/bin/clang++", "$toolchain/bin/llvm-ar")
    val targetFlag = "--target=${target.clangTarget}${nativeHost.androidApi}"

    progress("Compiling with NDK ${nativeHost.ndk.version()}")
    delete(obj)
    delete(lib)
    mkdir(lib)
    staticLib(
        "$lib/liblibchelper.a",
        "$obj/libchelper",
        listOf("$chelperDir/src"),
        clang,
        listOf("$chelperDir/include"),
        listOf(targetFlag) + commonCFlags + listOf("-D_LITTLE_ENDIAN"),
        emptyList(),
    )
    staticLib(
        "$lib/libjvm.a",
        "$obj/jvm",
        listOf("$jvmPosixDir/src", fallbacks),
        clang,
        listOf(jdkInclude, "$jdkInclude/android"),
        listOf(targetFlag) + commonCFlags,
        emptyList(),
    )
    staticLib(
        "$lib/libsvm_container.a",
        "$obj/container",
        listOf("$containerDir/src"),
        clang,
        containerIncludes.map { "$containerDir/$it" },
        listOf(targetFlag) + containerCFlags,
        containerCxxFlags,
    )
}

nativeOutput(Native.Svm.staticLibs, staticLibs, buildSvmStaticLibs)

tasks.assemble {
    dependsOn(buildSvmStaticLibs)
}
