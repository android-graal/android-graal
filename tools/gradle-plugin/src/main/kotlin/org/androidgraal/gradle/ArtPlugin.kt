package org.androidgraal.gradle

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.ApplicationVariant
import com.android.build.api.variant.Variant
import org.androidgraal.common.Host
import org.androidgraal.common.Ndk
import org.androidgraal.common.Target
import org.androidgraal.common.Toolchain
import org.androidgraal.common.ToolchainLayout
import org.gradle.api.GradleException
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.attributes.Bundling
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.attributes.Usage
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.plugins.JvmEcosystemPlugin
import org.gradle.api.provider.Provider
import org.gradle.nativeplatform.MachineArchitecture
import org.gradle.nativeplatform.OperatingSystemFamily
import java.io.File
import java.io.StringReader
import java.util.Locale
import java.util.Properties

class ArtPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(ANDROID_GRAAL_EXTENSION_NAME, AndroidGraalExtension::class.java)
        extension.verbose.convention(false)
        extension.quickBuild.convention(false)
        extension.useLLVM.convention(true)

        project.pluginManager.apply(JvmEcosystemPlugin::class.java)

        val nativeImage = project.configurations.dependencyScope(NATIVE_IMAGE_CONFIGURATION_NAME) {
            it.description = "Dependencies compiled into the native image."
        }
        // No java-base here, so we request what it would put on every runtimeClasspath and apply jvm-ecosystem for the
        // matching rules.
        val runtimeClasspath = project.configurations.resolvable(NATIVE_IMAGE_RUNTIME_CLASSPATH_CONFIGURATION_NAME) {
            it.extendsFrom(nativeImage.get())
            it.attributes.attribute(
                Usage.USAGE_ATTRIBUTE,
                project.objects.named(Usage::class.java, Usage.JAVA_RUNTIME),
            )
            it.attributes.attribute(
                Category.CATEGORY_ATTRIBUTE,
                project.objects.named(Category::class.java, Category.LIBRARY),
            )
            it.attributes.attribute(
                LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE,
                project.objects.named(LibraryElements::class.java, LibraryElements.JAR),
            )
            it.attributes.attribute(
                Bundling.BUNDLING_ATTRIBUTE,
                project.objects.named(Bundling::class.java, Bundling.EXTERNAL),
            )
            it.attributes.attribute(
                TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                project.objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM),
            )
        }

        val toolchainBucket = project.configurations.dependencyScope(TOOLCHAIN_CONFIGURATION_NAME) {
            it.description = "The android-graal toolchain the image is built with."
            it.defaultDependencies { dependencies ->
                dependencies.add(project.dependencies.create("$TOOLCHAIN_MODULE:$PLUGIN_VERSION"))
            }
        }
        val toolchainClasspath = project.configurations.resolvable(TOOLCHAIN_CLASSPATH_CONFIGURATION_NAME) {
            it.extendsFrom(toolchainBucket.get())
            it.attributes.attribute(
                Usage.USAGE_ATTRIBUTE,
                project.objects.named(Usage::class.java, Toolchain.USAGE),
            )
            it.attributes.attribute(
                OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE,
                project.objects.named(OperatingSystemFamily::class.java, Host.current().osFamily()),
            )
            it.attributes.attribute(
                MachineArchitecture.ARCHITECTURE_ATTRIBUTE,
                project.objects.named(MachineArchitecture::class.java, Host.current().machineArchitecture()),
            )
        }

        project.dependencies.registerTransform(UnpackToolchain::class.java) {
            it.from.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, ArtifactTypeDefinition.ZIP_TYPE)
            it.from.attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage::class.java, Toolchain.USAGE))
            it.to.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, Toolchain.ARTIFACT_TYPE)
        }

        project.plugins.withId(ANDROID_APPLICATION) {
            configureAndroid(project, extension, runtimeClasspath, toolchainClasspath)
        }

        project.afterEvaluate {
            if (!it.plugins.hasPlugin(ANDROID_APPLICATION)) {
                throw GradleException(
                    "org.androidgraal.art must be applied to a module that also applies" +
                        " $ANDROID_APPLICATION",
                )
            }
        }
    }

    private fun configureAndroid(
        project: Project,
        extension: AndroidGraalExtension,
        runtimeClasspath: NamedDomainObjectProvider<out Configuration>,
        toolchainClasspath: NamedDomainObjectProvider<out Configuration>,
    ) {
        val androidComponents =
            project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)

        val ndkRoot = androidComponents.sdkComponents.ndkDirectory
        val console = project.providers.gradleProperty(CONSOLE_PROPERTY).map { it != "false" }.orElse(false)

        androidComponents.onVariants(androidComponents.selector().all()) { variant: ApplicationVariant ->
            val target = target(variant, project.extensions.getByType(ApplicationExtension::class.java))
            val ndk = Ndk(ndkRoot.get().asFile)
            ndk.validate(variant.minSdk.apiLevel)

            val capitalized = variant.name.replaceFirstChar { it.titlecase(Locale.ROOT) }
            val toolchain = toolchain(project, extension, toolchainClasspath, variant.minSdk.apiLevel)
            val name = extension.imageName.orNull
                ?: throw GradleException("androidGraal { imageName } is required")
            val mainClass = extension.mainClass.orNull
                ?: throw GradleException("androidGraal { mainClass } is required")

            val compileTask = project.tasks.register(
                "nativeImageCompile$capitalized",
                NativeImageCompileTask::class.java,
            ) { task ->
                task.group = "build"
                task.description = "Compiles lib$name.so into relocatable objects" +
                    " for ${target.abi.abiString} (${variant.name})."
                task.toolchain.set(toolchain)
                task.imageClasspath.from(runtimeClasspath)
                task.imageName.set(name)
                task.mainClass.set(mainClass)
                task.target.set(target)
                task.buildArgs.set(extension.buildArgs)
                task.jvmArgs.set(extension.jvmArgs)
                task.systemProperties.set(extension.systemProperties)
                task.configurationFileDirectories.from(extension.configurationFileDirectories)
                task.verbose.set(extension.verbose)
                task.quickBuild.set(extension.quickBuild)
                task.useLLVM.set(extension.useLLVM)
                task.taskDir.set(project.layout.buildDirectory.dir("androidgraal/${variant.name}/native-image"))
                task.console.set(console)
                task.workDir.set(task.taskDir.dir("work"))
                task.objectsDir.set(task.taskDir.dir("objects"))
            }

            val linkTask = project.tasks.register(
                "nativeImageLink$capitalized",
                NativeImageLinkTask::class.java,
            ) { task ->
                task.group = "build"
                task.description = "Links lib$name.so for ${target.abi.abiString} (${variant.name})."
                task.toolchain.set(toolchain)
                task.objectsDir.set(compileTask.flatMap { it.objectsDir })
                task.imageName.set(name)
                task.target.set(target)
                task.useLLVM.set(extension.useLLVM)
                task.ndkFiles.from(ndk.root)
                task.clang.set(ndk.clang.toString())
                task.minSdk.set(variant.minSdk.apiLevel)
                task.taskDir.set(project.layout.buildDirectory.dir("androidgraal/${variant.name}/link"))
                task.console.set(console)
                task.outputDir.set(task.taskDir.dir("jniLibs"))
            }

            // AGP packages every .so under <dir>/<abi>/ of a generated jniLibs directory.
            val jniLibs = variant.sources.jniLibs
                ?: throw GradleException("variant '${variant.name}' has no jniLibs sources to add lib$name.so to")
            jniLibs.addGeneratedSourceDirectory(linkTask, NativeImageLinkTask::outputDir)
        }
    }

    private fun toolchain(
        project: Project,
        extension: AndroidGraalExtension,
        toolchainClasspath: NamedDomainObjectProvider<out Configuration>,
        minSdk: Int,
    ): Provider<ToolchainLayout> {
        val configured = configuredToolchainDir(project, extension)
        val directory = if (configured != null) {
            project.providers.provider { configured }
        } else {
            resolvedToolchainDir(toolchainClasspath)
        }
        return directory.map { root ->
            ToolchainLayout(root).also {
                it.validate()
                it.checkApi(minSdk)
            }
        }
    }

    private fun configuredToolchainDir(project: Project, extension: AndroidGraalExtension): File? {
        val fromLocalProperties = localProperty(project, TOOLCHAIN_LOCAL_PROPERTY)
        if (fromLocalProperties != null) return project.layout.settingsDirectory.dir(fromLocalProperties).asFile
        val fromEnvironment = project.providers.environmentVariable(TOOLCHAIN_ENV_VAR).orNull
        if (fromEnvironment != null) return project.file(fromEnvironment)
        return extension.toolchainDir.orNull?.asFile
    }

    private fun resolvedToolchainDir(
        toolchainClasspath: NamedDomainObjectProvider<out Configuration>,
    ): Provider<File> {
        return toolchainClasspath.flatMap { configuration ->
            configuration.incoming.artifactView {
                it.attributes.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, Toolchain.ARTIFACT_TYPE)
            }.files.elements.map { elements ->
                val directories = elements.map { it.asFile }
                if (directories.size != 1) {
                    throw GradleException(
                        "expected exactly one android-graal toolchain on" +
                            " $TOOLCHAIN_CLASSPATH_CONFIGURATION_NAME, got $directories\n$TOOLCHAIN_SOURCES",
                    )
                }
                directories.single()
            }
        }
    }

    private fun localProperty(project: Project, key: String): String? {
        val text = project.providers
            .fileContents(project.layout.settingsDirectory.file("local.properties"))
            .asText.orNull ?: return null
        return Properties().apply { load(StringReader(text)) }.getProperty(key)
    }

    /** AGP packages the union of the `abiFilters` of `defaultConfig`, flavors and build type. */
    private fun target(variant: Variant, android: ApplicationExtension): Target {
        val abis = linkedSetOf<String>()
        abis.addAll(android.defaultConfig.ndk.abiFilters)
        variant.productFlavors.forEach { (_, flavor) ->
            abis.addAll(android.productFlavors.getByName(flavor).ndk.abiFilters)
        }
        abis.addAll(android.buildTypes.getByName(variant.buildType!!).ndk.abiFilters)
        val supported = Target.entries.map { it.abi.abiString }
        if (abis.isEmpty()) {
            throw GradleException(
                "org.androidgraal.art needs the module's ABI:" +
                    " set ndk { abiFilters += \"<abi>\" } to one of $supported",
            )
        }
        val unsupported = abis - supported
        if (unsupported.isNotEmpty()) {
            throw GradleException(
                "org.androidgraal.art supports $supported, but variant '${variant.name}' asks for" +
                    " $unsupported in the ndk.abiFilters of its defaultConfig, flavors or build type",
            )
        }
        return Target.entries.single { it.abi.abiString in abis }
    }

    companion object {
        private const val ANDROID_GRAAL_EXTENSION_NAME = "androidGraal"
        private const val ANDROID_APPLICATION = "com.android.application"
        private const val NATIVE_IMAGE_CONFIGURATION_NAME = "nativeImage"
        private const val NATIVE_IMAGE_RUNTIME_CLASSPATH_CONFIGURATION_NAME = "nativeImageRuntimeClasspath"
        private const val TOOLCHAIN_CONFIGURATION_NAME = "androidGraalToolchain"
        private const val TOOLCHAIN_CLASSPATH_CONFIGURATION_NAME = "androidGraalToolchainClasspath"

        private const val TOOLCHAIN_LOCAL_PROPERTY = "androidgraal.toolchain.dir"
        private const val TOOLCHAIN_ENV_VAR = "ANDROID_GRAAL_TOOLCHAIN"
        private const val CONSOLE_PROPERTY = "androidgraal.console"

        private const val TOOLCHAIN_MODULE = "org.androidgraal:toolchain"

        private val TOOLCHAIN_SOURCES = """
            The toolchain is taken from the first of:
              $TOOLCHAIN_LOCAL_PROPERTY=<path>            in the build's local.properties
              $TOOLCHAIN_ENV_VAR=<path>               in the environment
              androidGraal { toolchainDir = file("...") }  in the module's build script
              $TOOLCHAIN_CONFIGURATION_NAME("$TOOLCHAIN_MODULE:<version>") as a dependency
        """.trimIndent()
    }
}
