plugins {
    id("androidgraal.java")
}

description = "Bindings of bionic, Android's C library, for the image."

dependencies {
    compileOnlyApi(project(":java:graal:nativeimage"))
    compileOnlyApi(project(":java:graal:word"))
}
