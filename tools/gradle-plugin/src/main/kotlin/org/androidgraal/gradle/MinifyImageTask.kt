package org.androidgraal.gradle

import org.androidgraal.common.ToolchainLayout
import org.androidgraal.substrate.ImageShrinker
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import java.io.File

@CacheableTask
abstract class MinifyImageTask : BaseTask("r8") {

    @get:Internal
    abstract val toolchain: Property<ToolchainLayout>

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    val jdkModules: Provider<File>
        get() = toolchain.map { it.graalvmHome.resolve("lib").resolve("modules") }

    @get:Classpath
    abstract val imageClasspath: ConfigurableFileCollection

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val proguardFiles: ConfigurableFileCollection

    @get:Input
    abstract val fullMode: Property<Boolean>

    @get:Input
    abstract val strictFullModeForKeepRules: Property<Boolean>

    @get:Input
    abstract val debuggable: Property<Boolean>

    @get:OutputFile
    abstract val imageJar: RegularFileProperty

    @get:OutputFile
    abstract val imageMapping: RegularFileProperty

    @get:OutputFile
    abstract val configuration: RegularFileProperty

    fun setup(
        variantName: String,
        extension: AndroidGraalExtension,
        classpath: FileCollection,
        toolchain: Provider<ToolchainLayout>,
        fullMode: Provider<Boolean>,
        strictFullModeForKeepRules: Provider<Boolean>,
        debuggable: Boolean,
    ) {
        group = "build"
        description = "Shrinks, optimizes and obfuscates the image classpath with R8 ($variantName)."
        this.variantName.convention(variantName)
        this.toolchain.convention(toolchain)
        this.imageClasspath.convention(classpath)
        this.proguardFiles.convention(extension.proguardFiles)
        this.fullMode.convention(fullMode)
        this.strictFullModeForKeepRules.convention(strictFullModeForKeepRules)
        this.debuggable.convention(debuggable)
        this.imageJar.convention(taskDir.file("image.jar"))
        this.imageMapping.convention(taskDir.file("mapping.txt"))
        this.configuration.convention(taskDir.file("configuration.txt"))
    }

    override fun execute() {
        val config = ImageShrinker.Config(
            program = imageClasspath.files.toList(),
            jdkHome = toolchain.get().graalvmHome,
            rules = proguardFiles.files.toList(),
            outputJar = imageJar.get().asFile,
            imageMapping = imageMapping.get().asFile,
            configuration = configuration.get().asFile,
            fullMode = fullMode.get(),
            strictFullModeForKeepRules = strictFullModeForKeepRules.get(),
            debuggable = debuggable.get(),
        )
        ImageShrinker(config).shrink(log)
    }
}
