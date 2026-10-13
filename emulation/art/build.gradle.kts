import org.androidgraal.common.Side

plugins {
    id("androidgraal.java")
}

group = "${nativeHost.settings.group}.emulation"

description = "The ART side of android-graal's Android emulation: what the image asks of the application."

dependencies {
    api("${nativeHost.settings.group}:art:$version")
    api(project(":contract"))
    compileOnly(files(nativeHost.sdk.androidJar(nativeHost.androidApi)))
}

tasks.compileJava {
    options.release = Side.ART.release
}
