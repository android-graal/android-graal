import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.buildlogic.nativeOutput
import org.androidgraal.common.Target

plugins {
    id("androidgraal.native")
}

description = "The checked-in C annotation processor cache for aarch64-linux-android."

val vendor = nativeHost.vendor

val graalvmHomeDir: FileCollection = nativeInput(Native.Graal.home)
val llvmBinDir: FileCollection = nativeInput(Native.Llvm.bin)

val target = Target.AARCH64

val capCache: Directory = layout.projectDirectory.dir("src/${target.triple}")

nativeOutput(Native.CapCache.dir, capCache)

val queryOutput = "query"
val binOutput = "bin"

val generateCapQueries = tasks.register<Script>("generateCapQueries") {
    group = "android-graal"
    description = "Generates the C annotation query programs with -H:+ExitAfterQueryCodeGeneration."

    val graalvmHome = input("graalvmHome", graalvmHomeDir)
    val llvmBin = input("llvmBin", llvmBinDir)
    val query = output(queryOutput, dir("query"))

    progress("native-image -H:+ExitAfterQueryCodeGeneration")
    exec(
        "$graalvmHome/bin/native-image",
        "--shared",
        "-H:Name=capquery",
        "--tool:llvm-backend",
        "-H:+UnlockExperimentalVMOptions",
        "-H:CheckBootModuleDependencies=0",
        "-Dsvm.platform=${target.platform}",
        "-Dsvm.targetArch=${target.arch}",
        "--libc=bionic",
        "-H:+ExitAfterQueryCodeGeneration",
        "-H:QueryCodeDir=$query",
        "-J-Dllvm.bin.dir=$llvmBin/",
    )
}

val compileCapQueries = tasks.register<Script>("compileCapQueries") {
    group = "android-graal"
    description = "Compiles the query programs for ${target.triple} with the NDK clang."

    val graalvmHome = input("graalvmHome", graalvmHomeDir)
    val query = input("query", generateCapQueries.map { it.getOutput(queryOutput) })
    val jdkSrc = source("jdkSrc", vendor.labsOpenjdk.resolve("src"))
    val svmSrc = source("svmSrc", vendor.graal.resolve("substratevm/src"))
    source("ndk", nativeHost.ndk.root)
    val bin = output(binOutput, dir("bin"))

    val cc = "${rel(nativeHost.ndk.toolchain)}/bin/clang"
    progress("compiling the query programs")
    forEach(query, "*.c") { each ->
        exec(
            cc, "--target=${target.clangTarget}${nativeHost.androidApi}", "-O1", "-fPIE", "-pie",
            "-I$jdkSrc/java.base/unix/native/include",
            "-I$jdkSrc/java.base/share/native/include",
            "-I$svmSrc/com.oracle.svm.native.libchelper/include",
            "-I$graalvmHome/lib/svm/clibraries/include",
            "-o", "$bin/${each.stem}", each.path,
        )
    }
}

tasks.register<Script>("regenerateCapCache") {
    group = "android-graal"
    description = "Runs the query programs on a connected device and rewrites src/${target.triple}/*.cap."

    outputs.upToDateWhen { false }
    outputs.cacheIf { false }

    val bin = input("bin", compileCapQueries.map { it.getOutput(binOutput) })
    val adb = source("adb", nativeHost.sdk.adb)
    val cap = output("cap", dir("cap"))
    val src = root("src", capCache.asFile)

    val remote = "/data/local/tmp/capq"
    progress("running the query programs on the device")
    exec(adb, "shell", "rm -rf $remote && mkdir -p $remote")
    forEach(bin, "*") { each ->
        exec(adb, "push", each.path, "$remote/")
        exec(
            adb,
            "shell",
            "chmod 755 $remote/${each.name} && cd $remote &&" +
                " ./${each.name} > ${each.name}.out 2> ${each.name}.err; echo exit=\$?",
        )
        exec(adb, "pull", "$remote/${each.name}.out", "$cap/${each.name}.cap")
    }
    rsync(cap, src)
}
