plugins {
    base
    alias(libs.plugins.spotless)
}

spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("build/**", "*/build/**")
        ktlint(libs.versions.ktlint.get()).setEditorConfigPath(file("../.editorconfig"))
    }
}

tasks.named("build") {
    dependsOn(":common:build", ":conventions:build")
}
