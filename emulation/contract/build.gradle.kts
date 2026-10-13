plugins {
    id("org.androidgraal.contract")
    id("androidgraal.publishing")
}

group = "${nativeHost.settings.group}.emulation"

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
