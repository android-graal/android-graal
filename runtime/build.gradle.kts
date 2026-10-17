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

tasks.register("publish") {
    group = "publishing"
    description = "Publishes every runtime library to the androidgraal repository."
    dependsOn(
        ":java:hosted:publish",
        ":java:shared:publish",
        ":java:svm:publish",
        ":java:bionic:publish",
        ":java:art:publish",
        ":java:graal:word:publish",
        ":java:graal:nativeimage:publish",
        ":java:graal:svm-shared:publish",
    )
}
