package org.androidgraal.common

import org.semver4j.Semver
import java.io.File
import java.util.Properties

/** The toolchain's `VERSION` file. */
data class ToolchainVersion(
    val toolchainVersion: String,
    val host: String,
    val graalCommit: String,
    val llvmCommit: String,
    val jdkCommit: String,
    val ndkVersion: Semver,
    val androidApi: Int,
) {

    fun toProperties(): Map<String, String> = linkedMapOf(
        "toolchain.version" to toolchainVersion,
        "host" to host,
        "graal.commit" to graalCommit,
        "llvm.commit" to llvmCommit,
        "jdk.commit" to jdkCommit,
        "ndk.version" to ndkVersion.toString(),
        "android.api" to androidApi.toString(),
    )

    companion object {

        fun read(file: File): ToolchainVersion {
            val properties = Properties()
            file.bufferedReader().use(properties::load)
            fun value(key: String): String = properties.getProperty(key)
                ?: error("no $key in $file -- not an android-graal toolchain ${Toolchain.VERSION} file")
            return ToolchainVersion(
                toolchainVersion = value("toolchain.version"),
                host = value("host"),
                graalCommit = value("graal.commit"),
                llvmCommit = value("llvm.commit"),
                jdkCommit = value("jdk.commit"),
                ndkVersion = Semver.parse(value("ndk.version"))!!,
                androidApi = value("android.api").toInt(),
            )
        }
    }
}
