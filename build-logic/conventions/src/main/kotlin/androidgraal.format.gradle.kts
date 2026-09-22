import org.androidgraal.buildlogic.repositoryRoot

plugins {
    base
    id("com.diffplug.spotless")
}

val repositoryRoot = repositoryRoot()
val editorConfig = repositoryRoot.resolve(".editorconfig")
if (!editorConfig.isFile) {
    throw GradleException("no .editorconfig in $repositoryRoot")
}

spotless {
    kotlin {
        target("*.gradle.kts", "src/**/*.kt")
        val ktlintVersion = the<VersionCatalogsExtension>().named("libs").findVersion("ktlint")
            .orElseThrow { GradleException("no ktlint version in the libs catalog") }
        ktlint(ktlintVersion.requiredVersion).setEditorConfigPath(editorConfig)
    }
}
