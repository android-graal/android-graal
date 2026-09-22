package org.androidgraal.buildlogic

import org.gradle.api.Project

fun Project.redirectToSubprojects(vararg taskNames: String) {
    val targets = subprojects.filter { it.buildFile.isFile }
    for (name in taskNames) {
        tasks.named(name) {
            dependsOn(targets.map { "${it.path}:$name" })
        }
    }
}

fun Project.redirectToBuilds(builds: List<String>, vararg taskNames: String) {
    for (name in taskNames) {
        tasks.named(name) {
            dependsOn(builds.map { gradle.includedBuild(it).task(":$name") })
        }
    }
}
