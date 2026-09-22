import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeOutput

plugins {
    id("androidgraal.native")
}

description = "The labsjdk that builds vendor/graal and cross-compiles vendor/labs-openjdk."

val vendor = nativeHost.vendor

val jdkDir: File = layout.buildDirectory.dir("jdk").get().asFile
val bootJdkHome: File = jdkDir.resolve("labsjdk")

val fetchJdk = tasks.register<Script>("fetchJdk") {
    group = "android-graal"
    description = "Fetches the labsjdk that vendor/graal/common.json pins."

    val mx = source("mx", vendor.mx)
    val commonJson = source("commonJson", vendor.graal.resolve("common.json"))
    val mxOut = root("mxOutput", layout.buildDirectory.dir("mx").get().asFile)
    val to = output("jdk", jdkDir)

    mkdir(to)
    workDir(to)
    env("MX_ALT_OUTPUT_ROOT", mxOut)
    env("PYTHONDONTWRITEBYTECODE", "1")
    env("MX_PYTHON", "python3")
    progress("fetch-jdk labsjdk-ce-latest")
    // A build-cache restore leaves the alias as a directory, which mx refuses to replace.
    delete("$to/labsjdk")
    exec(
        "$mx/mx", "-y", "--no-warning", "fetch-jdk",
        "--configuration", commonJson,
        "--to", to, "--alias", "labsjdk", "--strip-contents-home", "labsjdk-ce-latest",
    )
}

nativeOutput(Native.LabsJdk.home, bootJdkHome, fetchJdk)

tasks.assemble {
    dependsOn(fetchJdk)
}
