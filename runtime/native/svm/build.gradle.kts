import org.androidgraal.buildlogic.Compiler
import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.buildlogic.nativeOutput
import org.androidgraal.common.Target

plugins {
    id("androidgraal.native")
}

description = "liblibchelper.a, libjvm.a, libsvm_container.a and libandroidgraal.a compiled for every target."

val substratevm: File = nativeHost.vendor.graal.resolve("substratevm")

val fallbacksDir: FileCollection = nativeInput(Native.Graal.jvmFuncsFallbacks)
val jdkIncludeDirs: Map<Target, FileCollection> =
    Target.entries.associateWith { nativeInput(Native.Jdk.include.getValue(it)) }

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

for (target in Target.entries) {
    val task = tasks.register<Script>("buildSvmStaticLibs${target.title}") {
        group = "android-graal"
        description = "Compiles the three SubstrateVM support libraries and libandroidgraal.a for ${target.triple}."

        val fallbacks = input("fallbacks", fallbacksDir)
        val jdkInclude = input("jdkInclude", jdkIncludeDirs.getValue(target))
        val chelperDir = source("libchelper", substratevm.resolve("src/com.oracle.svm.native.libchelper"))
        val jvmPosixDir = source("jvmPosix", substratevm.resolve("src/com.oracle.svm.native.jvm.posix"))
        val containerDir = source("libcontainer", substratevm.resolve("src/com.oracle.svm.native.libcontainer"))
        val androidgraalDir = source("androidgraal", file("src/androidgraal"))
        source("ndk", nativeHost.ndk.root)
        val obj = root("obj", dir("obj"))
        val lib = output(Native.Svm.staticLibs.getValue(target), dir("lib"))

        val toolchain = rel(nativeHost.ndk.toolchain)
        val clang = Compiler("$toolchain/bin/clang", "$toolchain/bin/clang++", "$toolchain/bin/llvm-ar")
        val targetFlag = "--target=${target.clangTarget}${nativeHost.androidApi}"

        progress("Compiling with NDK ${nativeHost.ndk.version()}")
        delete(obj)
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
        staticLib(
            "$lib/libandroidgraal.a",
            "$obj/androidgraal",
            listOf(androidgraalDir),
            clang,
            emptyList(),
            listOf(targetFlag) + commonCFlags,
            emptyList(),
        )
    }
    nativeOutput(Native.Svm.staticLibs.getValue(target), task)
    tasks.assemble { dependsOn(task) }
}
