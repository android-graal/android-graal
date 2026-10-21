plugins {
    id("androidgraal.java")
}

description = "The image side of android-graal's runtime: calls into ART, the threads that call, object identity."

dependencies {
    api(project(":java:shared"))
    compileOnlyApi(project(":java:graal:nativeimage"))
    compileOnlyApi(project(":java:graal:word"))
    compileOnlyApi(project(":java:graal:svm-shared"))
}

spotless {
    java {
        targetExclude("src/main/java/org/androidgraal/svm/jni/JNI.java")
    }
}

tasks.processResources {
    from(nativeHost.vendor.graal.resolve("sdk/LICENSE.md")) {
        into("META-INF")
        rename { "LICENSE-jniutils" }
    }
}
