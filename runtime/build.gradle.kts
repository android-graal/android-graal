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
        ":java:bionic:publishToMavenLocal",
        ":java:art:publishToMavenLocal",
        ":java:graal:word:publishToMavenLocal",
        ":java:graal:nativeimage:publishToMavenLocal",
        ":java:graal:svm-shared:publishToMavenLocal",
    )
}
