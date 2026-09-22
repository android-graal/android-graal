import java.util.Properties

plugins {
    `embedded-kotlin`
    `java-library`
}

description = "Names, path arithmetic and checks of the android-graal toolchain, shared by the build and the plugin."

// Gradle applies a gradle.properties to its own build only.
val rootProperties = Properties().apply {
    rootDir.parentFile.resolve("gradle.properties").inputStream().use(::load)
}

fun setting(key: String): String = providers.gradleProperty(key).orNull
    ?: rootProperties.getProperty(key)
    ?: throw GradleException("no $key in the root gradle.properties")

group = setting("androidgraal.group")

kotlin {
    jvmToolchain(17)
}

dependencies {
    // `embedded-kotlin` puts the standard library on the compile classpath only.
    implementation(embeddedKotlin("stdlib"))
    api(libs.jnigen.commons)
    api(libs.semver4j)
    testImplementation(kotlin("test"))
    testImplementation(libs.commons.io)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
