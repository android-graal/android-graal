plugins {
    id("androidgraal.java-base")
    id("androidgraal.publishing")
    `java-library`
}

publishing {
    publications {
        register<MavenPublication>("java") {
            from(components["java"])
        }
    }
}
