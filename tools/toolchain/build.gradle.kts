import org.androidgraal.buildlogic.HostModule
import org.androidgraal.buildlogic.MultiHostComponent
import org.androidgraal.buildlogic.Native
import org.androidgraal.buildlogic.nativeInput
import org.androidgraal.common.Host
import org.androidgraal.common.Target
import org.androidgraal.common.Toolchain
import org.androidgraal.common.ToolchainLayout
import org.androidgraal.common.ToolchainVersion
import org.gradle.api.attributes.AttributeContainer
import org.gradle.api.attributes.Usage
import org.gradle.api.component.SoftwareComponentFactory
import org.gradle.nativeplatform.MachineArchitecture
import org.gradle.nativeplatform.OperatingSystemFamily
import javax.inject.Inject

plugins {
    base
    id("androidgraal.publishing")
}

description = "Assembles the android-graal toolchain from the projects under runtime/native."

val graalvmHomeInput = nativeInput(Native.Graal.home)
val llvmBinInput = nativeInput(Native.Llvm.bin)
val jdkStaticLibsInput = nativeInput(Native.Jdk.staticLibs)
val svmStaticLibsInput = nativeInput(Native.Svm.staticLibs)
val capCacheInput = nativeInput(Native.CapCache.dir)

fun cloneCommit(clone: File): String {
    val exec = providers.exec {
        workingDir(clone)
        commandLine("git", "rev-parse", "HEAD")
    }
    return exec.standardOutput.asText.get().trim()
}

val target = Target.AARCH64

val writeVersion = tasks.register<WriteProperties>("writeVersion") {
    group = "android-graal"
    description = "Writes the toolchain's VERSION file."
    destinationFile = layout.buildDirectory.file("version/${Toolchain.VERSION}")
    properties(
        ToolchainVersion(
            toolchainVersion = project.version.toString(),
            host = Host.current().toString(),
            graalCommit = cloneCommit(nativeHost.vendor.graal),
            llvmCommit = cloneCommit(nativeHost.vendor.llvm),
            jdkCommit = cloneCommit(nativeHost.vendor.labsOpenjdk),
            ndkVersion = nativeHost.ndk.version(),
            androidApi = nativeHost.androidApi,
        ).toProperties(),
    )
}

val assembleToolchain = tasks.register<Sync>("assembleToolchain") {
    group = "android-graal"
    description = "Assembles the android-graal toolchain directory (GraalVM home + our LLVM tools + target libraries)."

    into(layout.buildDirectory.dir("toolchain"))
    // The stock LLVM component is not shipped: the backend runs our tools from `lib/llvm/bin`.
    // The `bin` launchers are links to `lib/svm/bin`, where they resolve `../../../bin/java`.
    from(graalvmHomeInput) {
        into("graalvm")
        exclude("lib/llvm/**", "bin/native-image", "bin/native-image-configure", "bin/native-image-utils")
    }
    from(graalvmHomeInput.elements.map { it.single().asFile.resolve("lib/llvm") }) {
        into("graalvm/lib/llvm")
        include("3rd_party_license_llvm-toolchain.txt")
    }
    from(llvmBinInput) {
        into("graalvm/lib/llvm/bin")
        include(Toolchain.LLVM_TOOLS)
    }
    from(jdkStaticLibsInput) {
        into("targets/${target.triple}/lib")
        include(Toolchain.JDK_LIBS)
    }
    from(svmStaticLibsInput) {
        into("targets/${target.triple}/svm")
        include(Toolchain.SVM_LIBS)
    }
    from(capCacheInput) {
        into("targets/${target.triple}/capcache")
        include(Toolchain.CAP_CACHE)
    }
    from(writeVersion)
    // The GraalVM home carries read-only files that a copy cannot overwrite in place.
    eachFile { permissions { unix(if (file.canExecute()) "rwxr-xr-x" else "rw-r--r--") } }

    doLast {
        ToolchainLayout(destinationDir).validate()
    }
}

fun hostArtifactId(host: Host): String = "toolchain-$host"

val toolchainZip = tasks.register<Zip>("toolchainZip") {
    group = "android-graal"
    description = "Packs the android-graal toolchain into a distributable zip."
    from(assembleToolchain)
    archiveBaseName = hostArtifactId(Host.current())
    destinationDirectory = layout.buildDirectory.dir("distributions")
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
    // Gradle stores every entry as 0644 otherwise.
    eachFile { permissions { unix(if (file.canExecute()) "rwxr-xr-x" else "rw-r--r--") } }
}

fun AttributeContainer.toolchain(host: Host) {
    attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Toolchain.USAGE))
    attribute(
        OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE,
        objects.named(OperatingSystemFamily::class.java, host.osFamily()),
    )
    attribute(
        MachineArchitecture.ARCHITECTURE_ATTRIBUTE,
        objects.named(MachineArchitecture::class.java, host.machineArchitecture()),
    )
}

val hostToolchainElements = configurations.consumable("hostToolchainElements") {
    description = "The packed android-graal toolchain."
    attributes { toolchain(Host.current()) }
    outgoing.artifact(toolchainZip)
}

interface InjectedComponents {
    @get:Inject
    val factory: SoftwareComponentFactory
}

val componentFactory = objects.newInstance<InjectedComponents>().factory

val hostToolchain = componentFactory.adhoc("hostToolchain")
hostToolchain.addVariantsFromConfiguration(hostToolchainElements.get()) {}
components.add(hostToolchain)

val hostModules = Host.PUBLISHED.map { host ->
    val attributes = configurations.detachedConfiguration().attributes.apply { toolchain(host) }
    HostModule(project.group.toString(), hostArtifactId(host), project.version.toString(), attributes)
}

val rootComponent = MultiHostComponent("toolchain", hostModules.toSet())
components.add(rootComponent)

publishing {
    publications {
        register<MavenPublication>("toolchain") {
            from(rootComponent)
        }
        register<MavenPublication>("hostToolchain") {
            artifactId = hostArtifactId(Host.current())
            from(hostToolchain)
        }
        withType<MavenPublication>().configureEach {
            pom {
                name = "android-graal toolchain"
                description = "GraalVM Native Image with the LLVM backend plus the aarch64-linux-android" +
                    " target libraries used by the org.androidgraal.art plugin."
            }
        }
    }
}
