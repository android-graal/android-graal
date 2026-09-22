dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "android-graal"

includeBuild("build-logic")
includeBuild("tools")
includeBuild("runtime")
includeBuild("samples")
