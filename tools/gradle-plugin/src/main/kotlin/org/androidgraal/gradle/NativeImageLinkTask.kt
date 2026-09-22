package org.androidgraal.gradle

import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.androidgraal.substrate.CompiledImage
import org.androidgraal.substrate.ImageLinker
import org.gradle.api.file.ConfigurableFileCollection
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

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val ndkFiles: ConfigurableFileCollection

    @get:Internal
    abstract val clang: Property<String>

    @get:Input
    abstract val minSdk: Property<Int>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    override fun execute() {
        val imageName = imageName.get()
        val config = ImageLinker.Config(
            toolchain = toolchain.get(),
            clang = File(clang.get()),
            api = minSdk.get(),
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
