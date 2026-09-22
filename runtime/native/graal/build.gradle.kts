import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.Script
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.buildlogic.nativeOutput

plugins {
    id("androidgraal.native")
}

description = "The GraalVM home mx builds from vendor/graal, plus the generated JvmFuncsFallbacks.c."

val vendor = nativeHost.vendor

val bootJdkHome: FileCollection = nativeInput(Native.LabsJdk.home)

val graalvmHome: File = layout.buildDirectory.dir("graalvm").get().asFile
val jvmFuncsFallbacks: File = layout.buildDirectory.file("fallbacks/JvmFuncsFallbacks.c").get().asFile

val buildGraal = tasks.register<Script>("buildGraal") {
    group = "android-graal"
    description = "Runs `mx build` on a copy of vendor/graal and copies the GraalVM home out of it."

    val bootJdk = input("bootJdk", bootJdkHome)
    val mxOutput = root("mxOutput", layout.buildDirectory.dir("mx").get().asFile)
    val srcDir = layout.buildDirectory.dir("src").get().asFile
    val clones = root("clones", srcDir)

    val graalOutput = output("graalvmHome", graalvmHome)
    val fallbackOutput = output("fallbacks", jvmFuncsFallbacks.parentFile)

    progress("copying the clones")
    val graal = sourceCopy("graal", vendor.graal, srcDir.resolve("graal"))
    val mx = sourceCopy("mx", vendor.mx, srcDir.resolve("mx"))

    // mx writes every suite's output to `<MX_ALT_OUTPUT_ROOT>/<suite>`.
    env("MX_ALT_OUTPUT_ROOT", mxOutput)
    // git apply inside another repository's worktree skips paths outside the current directory. mx
    // runs it in the graal copy, and git honours a ceiling only from strictly below it.
    env("GIT_CEILING_DIRECTORIES", clones)
    env("PYTHONDONTWRITEBYTECODE", "1")
    env("MX_PYTHON", "python3")
    env("JAVA_HOME", bootJdk)
    workDir("$graal/substratevm")

    progress("mx build")
    exec("$mx/mx", "--java-home", bootJdk, "build")
    progress("copying the GraalVM home out of mxbuild")
    val home = capture("home", "$mx/mx", "graalvm-home")
    rsync(home, graalOutput)
    progress("copying JvmFuncsFallbacks.c")
    copy(
        "$mxOutput/substratevm",
        fallbackOutput,
        "**/JvmFuncsFallbacks.c",
    )
}

nativeOutput(Native.Graal.home, graalvmHome, buildGraal)
nativeOutput(Native.Graal.jvmFuncsFallbacks, jvmFuncsFallbacks, buildGraal)

tasks.assemble {
    dependsOn(buildGraal)
}
