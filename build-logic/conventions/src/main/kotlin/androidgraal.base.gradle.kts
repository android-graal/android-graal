import org.androidgraal.buildlogic.AndroidSdk
import org.androidgraal.buildlogic.BuildSettings
import org.androidgraal.buildlogic.NativeHost
import org.androidgraal.buildlogic.repositoryRoot

plugins {
    id("androidgraal.format")
}

val repositoryRoot = repositoryRoot()

// Gradle applies a gradle.properties to its own build only.
val settings = BuildSettings(
    providers.fileContents(
        layout.projectDirectory.file(repositoryRoot.resolve("gradle.properties").absolutePath),
    ).asText.get(),
    providers.fileContents(
        layout.projectDirectory.file(repositoryRoot.resolve("local.properties").absolutePath),
    ).asText.orNull,
    { providers.gradleProperty(it).orNull },
    { providers.environmentVariable(it).orNull },
    repositoryRoot,
)

group = settings.group
version = settings.version

val searchPath = providers.environmentVariable("PATH").orNull.orEmpty()
    .split(File.pathSeparator)
    .filter { it.isNotEmpty() }
    .map(::File)

extensions.add("nativeHost", NativeHost(repositoryRoot, settings, AndroidSdk(settings.sdkDir), searchPath))
