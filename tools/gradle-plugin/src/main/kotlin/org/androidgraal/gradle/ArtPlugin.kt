package org.androidgraal.gradle

import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.ApplicationVariant
import com.android.build.api.variant.Variant
import org.androidgraal.common.Host
import org.androidgraal.common.Ndk
import org.androidgraal.common.Side
import org.androidgraal.common.Target
import org.androidgraal.common.Toolchain
import org.androidgraal.common.ToolchainLayout
import org.gradle.api.GradleException
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.component.ProjectComponentIdentifier
import org.gradle.api.artifacts.type.ArtifactTypeDefinition
import org.gradle.api.attributes.Usage
import org.gradle.api.file.FileCollection
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JvmEcosystemPlugin
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.ClasspathNormalizer
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
        project.dependencies.attributesSchema.attribute(SIDE_ATTRIBUTE)

        val nativeImage = project.configurations.dependencyScope(NATIVE_IMAGE_CONFIGURATION_NAME) {
            it.description = "Dependencies compiled into the native image."
        }
        // No java-base here, so we request what it would put on every runtimeClasspath and apply jvm-ecosystem for the
        // matching rules.
        val runtimeClasspath = project.configurations.resolvable(NATIVE_IMAGE_RUNTIME_CLASSPATH_CONFIGURATION_NAME) {
            it.extendsFrom(nativeImage.get())
            it.configureAsRuntimeClasspath(project.objects)
            it.requestSide(Side.SVM)
        }

        val toolchainBucket = project.configurations.dependencyScope(TOOLCHAIN_CONFIGURATION_NAME) {
            it.description = "The android-graal toolchain the image is built with."
            it.defaultDependencies { dependencies ->
                dependencies.add(project.dependencies.create(Modules.TOOLCHAIN))
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
            project.dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, Modules.runtime(Side.ART))
            project.dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, Modules.EMULATION_ART)
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

        val ndk = androidComponents.sdkComponents.ndkDirectory.map { Ndk(it.asFile) }
        val imageClasspath = project.files(runtimeClasspath)
        val fullMode = agpSwitch(project, R8_FULL_MODE)
        val strictFullModeForKeepRules = agpSwitch(project, R8_STRICT_FULL_MODE_FOR_KEEP_RULES)

        androidComponents.onVariants(androidComponents.selector().all()) { variant: ApplicationVariant ->
            variant.components.forEach { it.runtimeConfiguration.requestSide(Side.ART) }
            val android = project.extensions.getByType(ApplicationExtension::class.java)
            val targets = targets(variant, android)
            val withoutLir = targets.filter { !it.lirBackend }.map { it.abi.abiString }
            if (!extension.useLLVM.get() && withoutLir.isNotEmpty()) {
                throw GradleException(
                    "$ANDROID_GRAAL_EXTENSION_NAME { useLLVM = false } selects native-image's LIR backend," +
                        " which does not compile for $withoutLir of variant '${variant.name}'",
                )
            }

            val capitalized = variant.name.replaceFirstChar { it.titlecase(Locale.ROOT) }
            val toolchain = toolchain(project, extension, toolchainClasspath, variant.minSdk.apiLevel)

            val checkImageClasspath = project.tasks.register(
                "checkImageClasspath$capitalized",
                CheckImageClasspathTask::class.java,
            ) {
                it.setup(variant.name, imageClasspath)
            }

            val compiledClasspath: FileCollection = if (variant.isMinifyEnabled) {
                val minify = project.tasks.register(
                    "minifyImage${capitalized}WithR8",
                    MinifyImageTask::class.java,
                ) {
                    it.setup(
                        variant.name,
                        extension,
                        imageClasspath,
                        toolchain,
                        fullMode,
                        strictFullModeForKeepRules,
                        variant.debuggable,
                    )
                }
                val mergeMapping = project.tasks.register(
                    "mergeImageMapping$capitalized",
                    MergeImageMappingTask::class.java,
                ) {
                    it.setup(variant.name, minify.flatMap { m -> m.imageMapping })
                }
                variant.artifacts.use(mergeMapping)
                    .wiredWithFiles(MergeImageMappingTask::appMapping, MergeImageMappingTask::mapping)
                    .toTransform(SingleArtifact.OBFUSCATION_MAPPING_FILE)
                project.files(minify.flatMap { it.imageJar })
            } else {
                imageClasspath
            }

            val generateNativeImage = project.tasks.register(
                "generateNativeImage$capitalized",
                GenerateNativeImageTask::class.java,
            ) {
                it.setup(variant.name, extension, variant.namespace)
            }

            val java = variant.sources.java
                ?: throw GradleException("variant '${variant.name}' has no Java sources")
            java.addGeneratedSourceDirectory(generateNativeImage, GenerateNativeImageTask::outputDir)
            variant.sources.manifests.addGeneratedManifestFile(
                generateNativeImage,
                GenerateNativeImageTask::manifestFile,
            )

            // AGP packages every .so under <dir>/<abi>/ of a generated jniLibs directory.
            val jniLibs = variant.sources.jniLibs
                ?: throw GradleException("variant '${variant.name}' has no jniLibs sources")

            for (target in targets) {
                val compileTask = project.tasks.register(
                    "nativeImageCompile$capitalized${target.title}",
                    NativeImageCompileTask::class.java,
                ) {
                    it.setup(variant.name, extension, compiledClasspath, toolchain, target)
                    it.dependsOn(checkImageClasspath)
                }

                val linkTask = project.tasks.register(
                    "nativeImageLink$capitalized${target.title}",
                    NativeImageLinkTask::class.java,
                ) {
                    it.setup(
                        variant.name,
                        extension,
                        toolchain,
                        target,
                        ndk,
                        variant.minSdk.apiLevel,
                        compileTask.flatMap { c -> c.objectsDir },
                    )
                }

                jniLibs.addGeneratedSourceDirectory(linkTask, NativeImageLinkTask::outputDir)
            }

            // AGP's incremental Java-resource merge keys a project's jars by project path alone, so a contract jar
            // and its bridge share one entry of its state. So we supply the project files directly as input
            val projectJars = project.configurations.named("${variant.name}RuntimeClasspath").map { classpath ->
                classpath.incoming.artifactView { view ->
                    view.componentFilter { id -> id is ProjectComponentIdentifier }
                    view.attributes {
                        it.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, JAVA_RESOURCES_ARTIFACT_TYPE)
                    }
                }.files
            }
            project.tasks.named { it == "merge${capitalized}JavaResource" }.configureEach {
                it.inputs.files(projectJars).withPropertyName(CONTRACTS_INPUT_PROPERTY)
                    .withNormalizer(ClasspathNormalizer::class.java)
            }
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

    /** One of AGP's boolean options, which default to `true`. */
    private fun agpSwitch(project: Project, name: String): Provider<Boolean> =
        project.providers.gradleProperty(name).map { parseAgpSwitch(name, it) }.orElse(true)

    private fun localProperty(project: Project, key: String): String? {
        val text = project.providers
            .fileContents(project.layout.settingsDirectory.file("local.properties"))
            .asText.orNull ?: return null
        return Properties().apply { load(StringReader(text)) }.getProperty(key)
    }

    /** AGP packages the union of the `abiFilters` of `defaultConfig`, flavors and build type. */
    private fun targets(variant: Variant, android: ApplicationExtension): List<Target> {
        val abis = linkedSetOf<String>()
        abis.addAll(android.defaultConfig.ndk.abiFilters)
        variant.productFlavors.forEach { (_, flavor) ->
            abis.addAll(android.productFlavors.getByName(flavor).ndk.abiFilters)
        }
        abis.addAll(android.buildTypes.getByName(variant.buildType!!).ndk.abiFilters)
        val supported = Target.entries.map { it.abi.abiString }
        if (abis.isEmpty()) {
            throw GradleException(
                "org.androidgraal.art needs the module's ABIs:" +
                    " add them to ndk { abiFilters }, out of $supported",
            )
        }
        val unsupported = abis - supported
        if (unsupported.isNotEmpty()) {
            throw GradleException(
                "org.androidgraal.art supports $supported, but variant '${variant.name}' asks for" +
                    " $unsupported in the ndk.abiFilters of its defaultConfig, flavors or build type",
            )
        }
        return Target.entries.filter { it.abi.abiString in abis }
    }

    companion object {
        private const val ANDROID_APPLICATION = "com.android.application"
        private const val NATIVE_IMAGE_CONFIGURATION_NAME = "nativeImage"
        private const val NATIVE_IMAGE_RUNTIME_CLASSPATH_CONFIGURATION_NAME = "nativeImageRuntimeClasspath"
        private const val TOOLCHAIN_CONFIGURATION_NAME = "androidGraalToolchain"
        private const val TOOLCHAIN_CLASSPATH_CONFIGURATION_NAME = "androidGraalToolchainClasspath"

        internal const val CONTRACTS_INPUT_PROPERTY = "androidgraal.contracts"
        private const val JAVA_RESOURCES_ARTIFACT_TYPE = "android-java-res"

        internal const val R8_FULL_MODE = "android.enableR8.fullMode"
        internal const val R8_STRICT_FULL_MODE_FOR_KEEP_RULES = "android.r8.strictFullModeForKeepRules"

        /** As AGP parses a boolean option set as a Gradle property. */
        internal fun parseAgpSwitch(name: String, value: String): Boolean {
            return value.lowercase().toBooleanStrictOrNull() ?: throw IllegalArgumentException(
                "Cannot parse project property $name='$value' of type '${value.javaClass}' as boolean." +
                    " Expected 'true' or 'false'.",
            )
        }

        private const val TOOLCHAIN_LOCAL_PROPERTY = "androidgraal.toolchain.dir"
        private const val TOOLCHAIN_ENV_VAR = "ANDROID_GRAAL_TOOLCHAIN"

        private val TOOLCHAIN_SOURCES = """
            The toolchain is taken from the first of:
              $TOOLCHAIN_LOCAL_PROPERTY=<path>            in the build's local.properties
              $TOOLCHAIN_ENV_VAR=<path>               in the environment
              $ANDROID_GRAAL_EXTENSION_NAME { toolchainDir = file("...") }  in the module's build script
              $TOOLCHAIN_CONFIGURATION_NAME("$PLUGIN_GROUP:toolchain:<version>") as a dependency
        """.trimIndent()
    }
}
