plugins {
    id("androidgraal.graal-module")
}

graalModule {
    sdkProjects = listOf("org.graalvm.word")
    sdkArtifact = "word"
}

description = "The org.graalvm.word module of the SDK the android-graal toolchain is built from (UPL, Oracle)."

tasks.compileJava {
    options.release = 17
}
