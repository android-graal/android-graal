import org.androidgraal.buildlogic.redirectToBuilds

plugins {
    id("androidgraal.base")
}

tasks.register("publishToMavenLocal") {
    group = "publishing"
    description = "Publishes the tools, runtime, emulation and integrations artifacts to the local Maven repository."
}

redirectToBuilds(
    listOf("build-logic", "tools", "runtime", "emulation", "samples", "integrations"),
    "build",
    "spotlessApply",
    "clean",
)
redirectToBuilds(listOf("tools", "runtime", "emulation", "integrations"), "publishToMavenLocal")
