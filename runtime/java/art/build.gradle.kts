import org.androidgraal.common.Side

plugins {
    id("androidgraal.java")
}

description = "The ART side of android-graal's runtime: starting the image, the threads that enter it, object identity."

dependencies {
    api(project(":java:shared"))
}

tasks.compileJava {
    options.release = Side.ART.release
}
