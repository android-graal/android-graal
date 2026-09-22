package org.androidgraal.gradle

import org.androidgraal.common.ToolchainLayout
import org.gradle.api.artifacts.transform.InputArtifact
import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.file.ArchiveOperations
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.work.DisableCachingByDefault
import javax.inject.Inject

@DisableCachingByDefault(because = "Unpacking is cheaper than a build cache round trip of the unpacked toolchain")
abstract class UnpackToolchain : TransformAction<TransformParameters.None> {

    @get:InputArtifact
    @get:PathSensitive(PathSensitivity.NAME_ONLY)
    abstract val zip: Provider<FileSystemLocation>

    @get:Inject
    abstract val archives: ArchiveOperations

    @get:Inject
    abstract val fileSystem: FileSystemOperations

    override fun transform(outputs: TransformOutputs) {
        val zipFile = zip.get().asFile
        val output = outputs.dir("toolchain")
        fileSystem.copy { spec ->
            spec.from(archives.zipTree(zipFile))
            spec.into(output)
        }
        ToolchainLayout(output).validate()
    }
}
