package org.androidgraal.gradle

import org.androidgraal.common.Host
import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.androidgraal.substrate.SubstrateExecutor
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import java.io.File

@CacheableTask
abstract class NativeImageCompileTask : BaseTask() {

    @get:Internal
    abstract val toolchain: Property<ToolchainLayout>

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val toolchainDir: Provider<File>
        get() = toolchain.map(ToolchainLayout::root)

    @get:Classpath
    abstract val imageClasspath: ConfigurableFileCollection

    @get:Input
    abstract val imageName: Property<String>

    @get:Input
    abstract val mainClass: Property<String>

    @get:Input
    abstract val target: Property<Target>

    @get:Input
    abstract val buildArgs: ListProperty<String>

    @get:Input
    abstract val jvmArgs: ListProperty<String>

    @get:Input
    abstract val systemProperties: MapProperty<String, String>

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val configurationFileDirectories: ConfigurableFileCollection

    @get:Input
    abstract val verbose: Property<Boolean>

    @get:Input
    abstract val quickBuild: Property<Boolean>

    @get:Input
    abstract val useLLVM: Property<Boolean>

    @get:Internal
    abstract val workDir: DirectoryProperty

    @get:OutputDirectory
    abstract val objectsDir: DirectoryProperty

    fun setup(
        variantName: String,
        extension: AndroidGraalExtension,
        classpath: Provider<out FileCollection>,
        toolchain: Provider<ToolchainLayout>,
        target: Target,
    ) {
        group = "build"
        description = "Compiles the native image into relocatable objects for ${target.abi.abiString} ($variantName)."
        this.taskDir.convention(layout.buildDirectory.dir("androidgraal/$variantName/native-image"))
        this.workDir.convention(taskDir.dir("work"))
        this.objectsDir.convention(taskDir.dir("objects"))
        this.toolchain.convention(toolchain)
        this.target.convention(target)
        this.imageClasspath.convention(classpath)
        this.imageName.convention(extension.imageName)
        this.mainClass.convention(extension.mainClass)
        this.buildArgs.convention(extension.buildArgs)
        this.jvmArgs.convention(extension.jvmArgs)
        this.systemProperties.convention(extension.systemProperties)
        this.configurationFileDirectories.convention(extension.configurationFileDirectories)
        this.verbose.convention(extension.verbose)
        this.quickBuild.convention(extension.quickBuild)
        this.useLLVM.convention(extension.useLLVM)
    }

    override fun execute() {
        val config = SubstrateExecutor.Config(
            toolchain = toolchain.get(),
            imageName = imageName.get(),
            mainClass = mainClass.get(),
            classpath = imageClasspath.files.toList(),
            workDir = workDir.get().asFile,
            objectsDir = objectsDir.get().asFile,
            buildArgs = buildArgs.get(),
            jvmArgs = jvmArgs.get(),
            systemProperties = systemProperties.get(),
            configurationFileDirectories = configurationFileDirectories.files.toList(),
            verbose = verbose.get(),
            quickBuild = quickBuild.get(),
            useLLVM = useLLVM.get(),
            target = target.get(),
            host = Host.current(),
        )
        val compiled = SubstrateExecutor(config, runner()).compile()
        compiled.objects.forEach { logger.info("android-graal: {} ({} bytes)", it, it.length()) }
    }
}
