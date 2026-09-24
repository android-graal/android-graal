package org.androidgraal.buildlogic

import org.gradle.api.GradleException
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Project
import org.gradle.api.artifacts.ConsumableConfiguration
import org.gradle.api.attributes.Usage
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.project

sealed class NativeProject(val name: String) {

    val path = ":native:$name"

    protected fun artifact(name: String) = NativeArtifact(this, name)
}

class NativeArtifact(val project: NativeProject, val name: String) {

    val usage = "android-graal-${project.name}-$name"
}

object Native {

    object LabsJdk : NativeProject("labsjdk") {
        val home = artifact("home")
    }

    object Llvm : NativeProject("llvm") {
        val bin = artifact("bin")
    }

    object Graal : NativeProject("graal") {
        val home = artifact("home")
        val jvmFuncsFallbacks = artifact("jvmFuncsFallbacks")
    }

    object Jdk : NativeProject("jdk") {
        val staticLibs = artifact("staticLibs")
        val include = artifact("include")
    }

    object Svm : NativeProject("svm") {
        val staticLibs = artifact("staticLibs")
    }

    object CapCache : NativeProject("capcache") {
        val dir = artifact("dir")
    }
}

fun Project.nativeOutput(artifact: NativeArtifact, file: Any): NamedDomainObjectProvider<ConsumableConfiguration> {
    if (path != artifact.project.path) {
        throw GradleException("${artifact.project.path} produces ${artifact.name}, $path cannot")
    }
    return configurations.consumable("${artifact.name}Elements") {
        attributes {
            attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, artifact.usage))
        }
        outgoing.artifact(file)
    }
}

fun Project.nativeOutput(
    artifact: NativeArtifact,
    task: TaskProvider<out Script>,
): NamedDomainObjectProvider<ConsumableConfiguration> = nativeOutput(artifact, task.map { it.getOutput(artifact) })

fun Project.nativeInput(artifact: NativeArtifact): FileCollection {
    val name = artifact.project.name + artifact.name.replaceFirstChar { it.uppercaseChar() }
    val bucket = configurations.dependencyScope(name)
    val resolvable = configurations.resolvable("${name}Path") {
        extendsFrom(bucket.get())
        attributes {
            attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, artifact.usage))
        }
    }
    val notation: Any = if (findProject(artifact.project.path) != null) {
        dependencies.project(artifact.project.path)
    } else {
        "$group:${artifact.project.name}"
    }
    dependencies.add(bucket.name, notation)
    return files(resolvable)
}
