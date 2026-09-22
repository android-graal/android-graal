import org.androidgraal.buildlogic.redirectToBuilds

plugins {
    id("androidgraal.format")
}

redirectToBuilds(listOf("hello"), "build", "spotlessApply")
