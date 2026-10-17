plugins {
    alias(libs.plugins.android.application)
    id("org.androidgraal.art")
    id("androidgraal.base")
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
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

androidGraal {
    imageName = "hello"
}

dependencies {
    nativeImage(project(":android:svm"))
}
