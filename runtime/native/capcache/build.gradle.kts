import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.buildlogic.nativeOutput
import org.androidgraal.common.Target

plugins {
    id("androidgraal.native")
}

description = "The checked-in C annotation processor cache of every target."

val vendor = nativeHost.vendor

val graalvmHomeDir: FileCollection = nativeInput(Native.Graal.home)
val llvmBinDir: FileCollection = nativeInput(Native.Llvm.bin)

val capQueryScope = configurations.dependencyScope("capQuery")
val capQueryPath = configurations.resolvable("capQueryPath") {
    extendsFrom(capQueryScope.get())
    isTransitive = false
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.JAR))
        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        attribute(
            TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
            objects.named(TargetJvmEnvironment.STANDARD_JVM),
        )
    }
}
dependencies {
    capQueryScope(project(":java:svm"))
    capQueryScope(project(":java:bionic"))
}
val capQueryClasspath: FileCollection = files(capQueryPath)

val queryOutput = "query"
val binOutput = "bin"

for (target in Target.entries) {
    val capCache = layout.projectDirectory.dir("src/${target.triple}")
    val abi = target.abi.abiString

    nativeOutput(Native.CapCache.dir.getValue(target), capCache)

    val generateCapQueries = tasks.register<Script>("generateCapQueries${target.title}") {
        group = "android-graal"
        description = "Generates the C annotation query programs for ${target.triple} with" +
            " -H:+ExitAfterQueryCodeGeneration."

        val graalvmHome = input("graalvmHome", graalvmHomeDir)
        val llvmBin = input("llvmBin", llvmBinDir)
        val capQuery = classpath("capQuery", capQueryClasspath)
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
            "-cp",
            capQuery,
        )
    }

    val compileCapQueries = tasks.register<Script>("compileCapQueries${target.title}") {
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

    tasks.register<Script>("regenerateCapCache${target.title}") {
        group = "android-graal"
        description = "Runs the query programs on the connected device, which must run $abi, and rewrites" +
            " src/${target.triple}/*.cap."

        outputs.upToDateWhen { false }
        outputs.cacheIf { false }

        val bin = input("bin", compileCapQueries.map { it.getOutput(binOutput) })
        val adb = source("adb", nativeHost.sdk.adb)
        val cap = output("cap", dir("cap"))
        val src = root("src", capCache.asFile)

        val remote = "/data/local/tmp/capq/${target.triple}"
        progress("checking that the device runs $abi")
        val abis = capture("abis", adb, "shell", "getprop ro.product.cpu.abilist")
        expect(abis, "(.*,)?${Regex.escape(abi)}(,.*)?", "the device's ABI list $abis lacks $abi")
        progress("running the query programs on the device")
        exec(adb, "shell", "rm -rf $remote && mkdir -p $remote")
        forEach(bin, "*") { each ->
            exec(adb, "push", each.path, "$remote/")
            // Below API 24 adb shell exits with 0 whatever the command's status.
            val exit = capture(
                "exit",
                adb,
                "shell",
                "cd $remote && chmod 755 ${each.name} && ./${each.name} > ${each.name}.out 2> ${each.name}.err;" +
                    " echo \$?",
            )
            expect(exit, "0", "${each.name} exited with $exit, see $remote/${each.name}.err on the device")
            exec(adb, "pull", "$remote/${each.name}.out", "$cap/${each.name}.cap")
        }
        rsync(cap, src)
    }
}
