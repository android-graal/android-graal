package org.androidgraal.buildlogic

import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File

/** The first directory at or above this one holding `gradle/libs.versions.toml`, the catalog marking the root. */
fun File.repositoryRoot(): File {
    return generateSequence(this) { it.parentFile }
        .firstOrNull { it.resolve("gradle/libs.versions.toml").isFile }
        ?: throw GradleException("no gradle/libs.versions.toml above $this")
}

fun Project.repositoryRoot(): File = rootDir.repositoryRoot()
