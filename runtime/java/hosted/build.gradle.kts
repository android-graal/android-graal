import org.androidgraal.common.Side

plugins {
    id("androidgraal.java")
}

description = "android-graal's build-time annotations, read by its annotation processor and its Gradle plugin."

tasks.compileJava {
    options.release = Side.sharedRelease()
}
