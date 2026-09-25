plugins {
    alias(libs.plugins.android.application)
    id("org.androidgraal.art")
    id("androidgraal.format")
}

android {
    namespace = "org.graalvm.android.hello"
    compileSdk = 36
    ndkVersion = "30.0.16248370"

    defaultConfig {
        applicationId = "org.graalvm.android.hello"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        ndk {
            abiFilters += "arm64-v8a"
        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1+"
        }
    }

    // The API 23 system image wants the image extracted to the app's lib dir, not mmapped from the APK.
    packaging {
        jniLibs {
            useLegacyPackaging = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

androidGraal {
    imageName = "hello"
    mainClass = "HelloWorld"
}

dependencies {
    nativeImage(project(":android:svm"))
}
