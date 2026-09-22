includeBuild("../build-logic")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "runtime"

include("native:labsjdk")
include("native:llvm")
include("native:graal")
include("native:jdk")
include("native:svm")
include("native:capcache")
