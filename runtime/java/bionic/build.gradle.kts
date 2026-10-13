plugins {
    id("androidgraal.java")
}

description = "Bindings of bionic, Android's C library, for the image."

dependencies {
    api(project(":java:graal:nativeimage"))
    api(project(":java:graal:word"))
}
