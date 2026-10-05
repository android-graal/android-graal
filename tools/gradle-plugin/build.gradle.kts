plugins {
    id("androidgraal.kotlin")
    `java-gradle-plugin`
    alias(libs.plugins.shadow)
}

description =
    "The Gradle plugins org.androidgraal.art, org.androidgraal.svm and org.androidgraal.contract: build an Android" +
    " app's image module as a GraalVM native image, with the bridges of its contracts."

val shade = configurations.dependencyScope("shade")
val shadeClasspath = configurations.resolvable("shadeClasspath") {
    extendsFrom(shade.get())
    exclude(group = "org.jetbrains.kotlin")
}
configurations {
    compileOnly { extendsFrom(shade.get()) }
    testImplementation { extendsFrom(shade.get()) }
}

dependencies {
    shade("$group:common")
    shade(libs.commons.io)
    shade(libs.asm)
    shade(libs.asm.commons)
    compileOnly(libs.android.gradle.api)
    testRuntimeOnly(libs.android.gradle)
}

val generatePluginVersion = tasks.register("generatePluginVersion") {
    description = "Generates a Kotlin file holding the plugin's group and version."
    val pluginGroup = project.group.toString()
    val pluginVersion = version.toString()
    val outputDir = layout.buildDirectory.dir("generated/pluginVersion")
    inputs.property("group", pluginGroup)
    inputs.property("version", pluginVersion)
    outputs.dir(outputDir)
    doLast {
        val file = outputDir.get().file("org/androidgraal/gradle/PluginVersion.kt").asFile
        file.parentFile.mkdirs()
        file.writeText(
            "package org.androidgraal.gradle\n\n" +
                "internal const val PLUGIN_GROUP = \"$pluginGroup\"\n\n" +
                "internal const val PLUGIN_VERSION = \"$pluginVersion\"\n",
        )
    }
}
kotlin.sourceSets.main { kotlin.srcDir(generatePluginVersion) }

shadow {
    addShadowVariantIntoJavaComponent = false
}

tasks.shadowJar {
    configurations = listOf(shadeClasspath.get())
    archiveClassifier = ""
    mergeServiceFiles()
    enableAutoRelocation = true
    relocationPrefix = "org.androidgraal.gradle.shadow"
    // Keeps own packages out of the auto-relocation.
    relocate("org.androidgraal", "org.androidgraal")
    exclude("META-INF/versions/**")
    exclude("module-info.class")
    addMultiReleaseAttribute = false
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    failOnDuplicateEntries = true
}

tasks.jar {
    archiveClassifier = "plain"
}

// SO that composite buulds/samples see the final jar
listOf(configurations.apiElements, configurations.runtimeElements).forEach { elements ->
    elements.configure {
        outgoing.artifacts.clear()
        outgoing.variants.clear()
        outgoing.artifact(tasks.shadowJar)
    }
}

gradlePlugin {
    plugins {
        register("art") {
            id = "org.androidgraal.art"
            implementationClass = "org.androidgraal.gradle.ArtPlugin"
        }
        register("svm") {
            id = "org.androidgraal.svm"
            implementationClass = "org.androidgraal.gradle.SvmPlugin"
        }
        register("contract") {
            id = "org.androidgraal.contract"
            implementationClass = "org.androidgraal.gradle.ContractPlugin"
        }
    }
}
