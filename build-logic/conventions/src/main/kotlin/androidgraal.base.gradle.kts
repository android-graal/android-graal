import org.androidgraal.buildlogic.redirectToSubprojects
import org.androidgraal.buildlogic.repositoryRoot

plugins {
    base
    id("com.diffplug.spotless")
}

val repositoryRoot = repositoryRoot()

fun rootFile(name: String): File {
    val file = repositoryRoot.resolve(name)
    if (!file.isFile) {
        throw GradleException("no $name in $repositoryRoot")
    }
    return file
}

val editorConfig = rootFile(".editorconfig")
val eclipseConfig = rootFile(".eclipse-java-format.properties")

spotless {
    kotlin {
        target("*.gradle.kts", "src/**/*.kt")
        val ktlintVersion = the<VersionCatalogsExtension>().named("libs").findVersion("ktlint")
            .orElseThrow { GradleException("no ktlint version in the libs catalog") }
        ktlint(ktlintVersion.requiredVersion).setEditorConfigPath(editorConfig)
    }
    java {
        target("src/*/java/**/*.java")
        importOrder()
        removeUnusedImports("cleanthat-javaparser-unnecessaryimport")
        eclipse().configFile(eclipseConfig)
        trimTrailingWhitespace()
    }
}

redirectToSubprojects("build", "spotlessApply", "clean")
