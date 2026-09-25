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

val buildGraal = tasks.register<Script>("buildGraal") {
    group = "android-graal"
    description = "Runs `mx build` on a copy of vendor/graal and copies the GraalVM home out of it."

    val bootJdk = input("bootJdk", bootJdkHome)
    val mxOutput = root("mxOutput", dir("mx"))
    val srcDir = dir("src")
    val clones = root("clones", srcDir)

    val graalOutput = output(Native.Graal.home, dir("graalvm"))
    val fallbackOutput = output(Native.Graal.jvmFuncsFallbacks, dir("fallbacks"))

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
    val graalvmDist = capture("graalvmDist", "$mx/mx", "--java-home", bootJdk, "graalvm-dist-name")
    exec("$mx/mx", "--java-home", bootJdk, "build", "--dependencies", graalvmDist)
    progress("copying the GraalVM home out of mxbuild")
    val home = capture("graalvmHome", "$mx/mx", "graalvm-home")
    rsync(home, graalOutput)
    progress("copying JvmFuncsFallbacks.c")
    copy("$mxOutput/substratevm", fallbackOutput, "**/JvmFuncsFallbacks.c")
}

nativeOutput(Native.Graal.home, buildGraal)
nativeOutput(Native.Graal.jvmFuncsFallbacks, buildGraal)

tasks.assemble {
    dependsOn(buildGraal)
}
