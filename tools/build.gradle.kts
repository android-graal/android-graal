import org.androidgraal.buildlogic.redirectToSubprojects

plugins {
    id("androidgraal.format")
}

tasks.register("publishToMavenLocal") {
    group = "publishing"
    description = "Publishes every tools artifact to the local Maven repository."
}

redirectToSubprojects("build", "spotlessApply", "publishToMavenLocal")
