import org.androidgraal.common.Side

plugins {
    id("androidgraal.java")
}

description = "The runtime code both sides of android-graal's bridges share: object identity and exception marshalling."

tasks.compileJava {
    options.release = Side.sharedRelease()
}
