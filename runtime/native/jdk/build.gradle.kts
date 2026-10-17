import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.buildlogic.nativeOutput
import org.androidgraal.common.Target

plugins {
    id("androidgraal.native")
}

description = "The JDK static libraries and headers, cross-compiled from vendor/labs-openjdk for every target."

val androidApi: Int = nativeHost.androidApi

val bootJdkHome: FileCollection = nativeInput(Native.LabsJdk.home)

for (target in Target.entries) {
    val conf = "android-${target.arch}-api$androidApi"
    val task = tasks.register<Script>("buildJdkStaticLibs${target.title}") {
        group = "android-graal"
        description = "Configures and builds the `static-libs` images of vendor/labs-openjdk for $conf."

        val bootJdk = input("bootJdk", bootJdkHome)
        val labsOpenjdk = source("labsOpenjdk", nativeHost.vendor.labsOpenjdk)
        val ndkSrc = source("ndk", nativeHost.ndk.root)
        // configure started outside the source root builds into its working directory.
        val outputDir = root("jdkBuild", dir("jdk"))
        val libDir = output(Native.Jdk.staticLibs.getValue(target), dir("lib"))
        val includeDir = output(Native.Jdk.include.getValue(target), dir("include"))

        val toolchain = rel(nativeHost.ndk.toolchain)
        mkdir(outputDir)
        workDir(outputDir)
        progress("configure $conf, NDK ${nativeHost.ndk.version()}")
        exec(
            "bash", "$labsOpenjdk/configure",
            "--with-conf-name=$conf",
            "--enable-headless-only",
            "--with-boot-jdk=$bootJdk",
            "--with-build-jdk=$bootJdk",
            "--with-toolchain-path=$toolchain/bin",
            "--with-sysroot=$toolchain/sysroot",
            "--with-jvm-variants=minimal",
            "--openjdk-target=${target.clangTarget}$androidApi",
            "--with-build-user=android-graal",
            "--with-version-opt=android-graal",
        )
        progress("make static-libs")
        exec("make", "static-libs")
        progress("collecting the static libraries and headers")
        copy(outputDir, libDir, "support/native/**/static/*.a")
        copy("$outputDir/jdk/include", includeDir)
    }
    nativeOutput(Native.Jdk.staticLibs.getValue(target), task)
    nativeOutput(Native.Jdk.include.getValue(target), task)
    tasks.assemble { dependsOn(task) }
}
