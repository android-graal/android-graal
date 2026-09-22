import org.androidgraal.buildlogic.redirectToBuilds

plugins {
    id("androidgraal.format")
}

tasks.register("publishToMavenLocal") {
    group = "publishing"
    description = "Publishes the tools and runtime artifacts to the local Maven repository."
}

redirectToBuilds(listOf("build-logic", "tools", "runtime", "samples"), "build", "spotlessApply")
redirectToBuilds(listOf("tools", "runtime"), "publishToMavenLocal")
