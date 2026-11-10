plugins {
    alias(libs.plugins.android.library)
    id("androidgraal.publishing")
}

group = "${nativeHost.settings.group}.emulation"

description = "The ART side of android-graal's Android emulation: what the image asks of the application."

android {
    namespace = "org.androidgraal.android.art"
    compileSdk = nativeHost.compileSdk
    enableKotlin = false

    defaultConfig {
        minSdk = nativeHost.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    publishing {
        singleVariant("release")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    api("${nativeHost.settings.group}:art:$version")
    api(project(":contract"))
    compileOnly(libs.androidx.annotation)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate { from(components["release"]) }
        }
    }
}
