plugins {
    id("androidgraal.base")
}

tasks.register("publishToMavenLocal") {
    group = "publishing"
    description = "Publishes every runtime library to the local Maven repository."
    dependsOn(
        ":java:hosted:publishToMavenLocal",
        ":java:shared:publishToMavenLocal",
        ":java:svm:publishToMavenLocal",
        ":java:art:publishToMavenLocal",
        ":java:gles:publishToMavenLocal",
        ":java:graal:word:publishToMavenLocal",
        ":java:graal:nativeimage:publishToMavenLocal",
    )
}
