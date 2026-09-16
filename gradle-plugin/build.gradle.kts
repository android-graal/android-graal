plugins {
    `java-gradle-plugin`
}

group = "org.androidgraal"
version = "0.1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

gradlePlugin {
    plugins {
        create("androidGraalNativeImage") {
            id = "org.androidgraal.native-image"
            implementationClass = "org.androidgraal.gradle.AndroidGraalPlugin"
        }
    }
}
