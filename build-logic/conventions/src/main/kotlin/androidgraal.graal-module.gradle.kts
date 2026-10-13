import org.androidgraal.buildlogic.GraalModule
import org.androidgraal.buildlogic.NativeHost

plugins {
    id("androidgraal.java")
}

val graalModule = extensions.create<GraalModule>("graalModule")
val nativeHost = the<NativeHost>()

group = "${nativeHost.settings.group}.graal"

sourceSets.main {
    val dirs = graalModule.sdkProjects.map { projects ->
        projects.map { nativeHost.vendor.graal.resolve("sdk/src/$it/src") }
    }
    java.srcDir(dirs)
    resources.srcDir(dirs)
    resources.exclude("**/*.java", "**/*.md")
}

tasks.processResources {
    from(nativeHost.vendor.graal.resolve("sdk/LICENSE.md")) {
        into("META-INF")
        rename { "LICENSE" }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

val graalVersion = the<VersionCatalogsExtension>().named("libs").findVersion("graal")
    .orElseThrow { GradleException("no graal version in the libs catalog") }
val sdkCapability = graalModule.sdkArtifact.map { "org.graalvm.sdk:$it:${graalVersion.requiredVersion}" }

// Declaring a capability drops the implicit one.
for (elements in listOf("apiElements", "runtimeElements")) {
    configurations.named(elements) {
        outgoing.capability("${project.group}:${project.name}:${project.version}")
        outgoing.capability(sdkCapability)
    }
}

publishing {
    publications.named<MavenPublication>("java") {
        suppressPomMetadataWarningsFor("apiElements")
        suppressPomMetadataWarningsFor("runtimeElements")
        pom {
            name = graalModule.sdkArtifact.map { "org.graalvm.$it" }
            description = provider { project.description }
            licenses {
                license {
                    name = "The Universal Permissive License (UPL), Version 1.0"
                    url = "https://oss.oracle.com/licenses/upl/"
                }
            }
        }
    }
}
