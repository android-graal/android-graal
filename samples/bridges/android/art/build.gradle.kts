plugins {
    alias(libs.plugins.android.application)
    id("org.androidgraal.art")
    id("androidgraal.base")
}

android {
    namespace = "org.graalvm.android.bridges"
    compileSdk = 36
    ndkVersion = "30.0.16248370"

    defaultConfig {
        applicationId = "org.graalvm.android.bridges"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        ndk {
            abiFilters += "arm64-v8a"
        }
    }

    // Below minSdk 23 AGP compresses the libraries by default.
    packaging {
        jniLibs {
            useLegacyPackaging = false
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

androidGraal {
    imageName = "bridges"
}

dependencies {
    implementation(project(":android:contract"))
    nativeImage(project(":android:svm"))
}
