import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.buildlogic.nativeOutput
import org.androidgraal.common.Target

plugins {
    id("androidgraal.native")
}

description = "The JDK static libraries and headers, cross-compiled from vendor/labs-openjdk for aarch64-linux-android."

val target = Target.AARCH64

val androidApi: Int = nativeHost.androidApi
val conf = "android-${target.arch}-api$androidApi"

val bootJdkHome: FileCollection = nativeInput(Native.LabsJdk.home)

val staticLibs: File = layout.buildDirectory.dir("lib").get().asFile
val include: File = layout.buildDirectory.dir("include").get().asFile

val buildJdkStaticLibs = tasks.register<Script>("buildJdkStaticLibs") {
    group = "android-graal"
    description = "Configures and builds the `static-libs` images of vendor/labs-openjdk for $conf."

    val bootJdk = input("bootJdk", bootJdkHome)
    val labsOpenjdk = source("labsOpenjdk", nativeHost.vendor.labsOpenjdk)
    val ndkSrc = source("ndk", nativeHost.ndk.root)
    // configure started outside the source root builds into its working directory.
    val outputDir = root("jdkBuild", layout.buildDirectory.dir("jdk").get().asFile)
    val libDir = output("lib", staticLibs)
    val includeDir = output("include", include)

    val toolchain = rel(nativeHost.ndk.toolchain)
    mkdir(outputDir)
    workDir(outputDir)
    progress("configure $conf, NDK ${nativeHost.ndk.version()}")
    exec(
        "bash", "$labsOpenjdk/configure",
        "--with-conf-name=$conf",
        "--with-android-api-level=$androidApi",
        "--enable-headless-only",
        "--with-boot-jdk=$bootJdk",
        "--with-build-jdk=$bootJdk",
        "--with-toolchain-path=$toolchain/bin",
        "--with-sysroot=$toolchain/sysroot",
        "--with-toolchain-type=clang",
        "--with-jvm-variants=minimal",
        "--host=${target.triple}",
        "--target=${target.triple}",
    )
    progress("make static-libs")
    exec("make", "static-libs")
    progress("collecting the static libraries and headers")
    delete(libDir)
    copy(outputDir, libDir, "support/native/**/static/*.a")
    delete(includeDir)
    copy("$outputDir/jdk/include", includeDir)
}

nativeOutput(Native.Jdk.staticLibs, staticLibs, buildJdkStaticLibs)
nativeOutput(Native.Jdk.include, include, buildJdkStaticLibs)

tasks.assemble {
    dependsOn(buildJdkStaticLibs)
}
