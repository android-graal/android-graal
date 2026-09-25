package org.androidgraal.gradle

import org.androidgraal.common.Ndk
import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.androidgraal.substrate.CompiledImage
import org.androidgraal.substrate.ImageLinker
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.Directory
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import java.io.File

@CacheableTask
abstract class NativeImageLinkTask : BaseTask() {

    @get:Internal
    abstract val toolchain: Property<ToolchainLayout>

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val toolchainDir: Provider<File>
        get() = toolchain.map(ToolchainLayout::root)

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val objectsDir: DirectoryProperty

    @get:Input
    abstract val imageName: Property<String>

    @get:Input
    abstract val target: Property<Target>

    @get:Input
    abstract val useLLVM: Property<Boolean>

    @get:Internal
    abstract val ndk: Property<Ndk>

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val ndkDir: Provider<File>
        get() = ndk.map(Ndk::root)

    @get:Input
    abstract val minSdk: Property<Int>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    fun setup(
        variantName: String,
        extension: AndroidGraalExtension,
        toolchain: Provider<ToolchainLayout>,
        target: Target,
        ndk: Provider<Ndk>,
        minSdk: Int,
        objectsDir: Provider<Directory>,
    ) {
        group = "build"
        description = "Links the native image for ${target.abi.abiString} ($variantName)."
        this.taskDir.convention(layout.buildDirectory.dir("androidgraal/$variantName/link"))
        this.outputDir.convention(taskDir.dir("jniLibs"))
        this.toolchain.convention(toolchain)
        this.target.convention(target)
        this.imageName.convention(extension.imageName)
        this.useLLVM.convention(extension.useLLVM)
        this.ndk.convention(ndk)
        this.minSdk.convention(minSdk)
        this.objectsDir.convention(objectsDir)
    }

    override fun execute() {
        val minSdk = minSdk.get()
        val ndk = ndk.get()
        ndk.validate(minSdk)

        val imageName = imageName.get()
        val config = ImageLinker.Config(
            toolchain = toolchain.get(),
            clang = ndk.clang,
            api = minSdk,
            imageName = imageName,
            compiled = CompiledImage.load(objectsDir.get().asFile, imageName, useLLVM.get()),
            useLLVM = useLLVM.get(),
            outputDir = outputDir.get().asFile,
            target = target.get(),
        )
        val image = ImageLinker(config, runner()).link()
        logger.info("android-graal: {} ({} bytes)", image, image.length())
    }
}
