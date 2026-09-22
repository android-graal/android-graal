plugins {
    id("androidgraal.publishing")
    id("org.gradle.kotlin.embedded-kotlin")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    "testImplementation"(kotlin("test"))
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<Jar>().configureEach {
    manifest.attributes["Implementation-Version"] = version
}
