import org.androidgraal.buildlogic.redirectToSubprojects

plugins {
    id("org.androidgraal.contract") apply false
    id("androidgraal.base")
}

tasks.register("publishToMavenLocal") {
    group = "publishing"
    description = "Publishes the Android emulation to the local Maven repository."
}

tasks.register("publish") {
    group = "publishing"
    description = "Publishes the Android emulation to the androidgraal repository."
}

redirectToSubprojects("publishToMavenLocal", "publish")
