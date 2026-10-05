plugins {
    id("androidgraal.graal-module")
}

graalModule {
    sdkProjects = listOf("org.graalvm.nativeimage", "com.oracle.svm.core.annotate")
    sdkArtifact = "nativeimage"
}

description = "The org.graalvm.nativeimage module of the SDK the android-graal toolchain is built from (UPL, Oracle)."

dependencies {
    api(project(":java:graal:word"))
}

tasks.compileJava {
    options.release = 17
}
