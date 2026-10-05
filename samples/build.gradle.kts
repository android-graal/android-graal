import org.androidgraal.buildlogic.redirectToBuilds

plugins {
    id("androidgraal.base")
}

redirectToBuilds(listOf("hello", "bridges", "catalogue", "gameloop", "gl"), "build", "spotlessApply", "clean")
