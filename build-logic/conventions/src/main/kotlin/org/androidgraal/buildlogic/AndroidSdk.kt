package org.androidgraal.buildlogic

import org.androidgraal.common.Ndk
import org.androidgraal.common.SourceProperties
import org.gradle.api.GradleException
import org.semver4j.Semver
import java.io.File

class AndroidSdk(val root: File) {

    init {
        if (!root.isDirectory) throw GradleException("no Android SDK at $root")
    }

    private val ndkDir: File = root.resolve("ndk")

    fun ndks(): List<Ndk> {
        return (ndkDir.listFiles() ?: emptyArray())
            .filter { it.resolve(SourceProperties.FILE_NAME).isFile }
            .sortedBy { it.name }
            .map { Ndk(it) }
    }

    fun ndk(version: Semver?): Ndk {
        if (version == null) {
            return ndks().maxByOrNull { it.version() } ?: throw GradleException("no NDK under $ndkDir")
        }
        return ndks().firstOrNull { it.version() == version }
            ?: throw GradleException(
                "no NDK $version under $ndkDir; installed: " +
                    ndks().joinToString { it.version().toString() }.ifEmpty { "none" },
            )
    }

    val adb: File = root.resolve("platform-tools/adb")

    private val cmakeDir: File = root.resolve("cmake")

    fun cmakes(): Map<Semver, File> {
        return (cmakeDir.listFiles() ?: emptyArray())
            .filter { it.resolve(SourceProperties.FILE_NAME).isFile }
            .sortedBy { it.name }
            .mapNotNull { dir ->
                SourceProperties.revisionOrNull(dir.resolve(SourceProperties.FILE_NAME))
                    ?.let { it to dir.resolve("bin") }
            }
            .toMap()
    }

    fun cmake(version: Semver?): File? =
        if (version == null) cmakes().maxByOrNull { it.key }?.value else cmakes()[version]
}
