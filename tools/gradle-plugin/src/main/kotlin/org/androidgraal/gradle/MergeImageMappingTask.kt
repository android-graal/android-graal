package org.androidgraal.gradle

import org.androidgraal.substrate.ObfuscationMapping
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity

@CacheableTask
abstract class MergeImageMappingTask : BaseTask("mapping") {

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val appMapping: RegularFileProperty

    @get:InputFile
    @get:PathSensitive(PathSensitivity.NONE)
    abstract val imageMapping: RegularFileProperty

    @get:OutputFile
    abstract val mapping: RegularFileProperty

    fun setup(variantName: String, imageMapping: Provider<RegularFile>) {
        group = "build"
        description = "Appends the image's R8 mapping to the app's ($variantName)."
        this.variantName.convention(variantName)
        this.imageMapping.convention(imageMapping)
    }

    override fun execute() {
        val merged = ObfuscationMapping.merge(appMapping.get().asFile.readText(), imageMapping.get().asFile.readText())
        mapping.get().asFile.writeText(merged)
    }
}
