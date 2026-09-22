import org.androidgraal.buildlogic.redirectToSubprojects

plugins {
    id("androidgraal.format")
}

tasks.register("publishToMavenLocal") {
    group = "publishing"
}

redirectToSubprojects("build", "spotlessApply")
