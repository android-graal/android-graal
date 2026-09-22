import org.androidgraal.buildlogic.redirectToSubprojects

plugins {
    alias(libs.plugins.android.application) apply false
    id("org.androidgraal.art") apply false
    id("androidgraal.format")
}

redirectToSubprojects("build", "spotlessApply")
