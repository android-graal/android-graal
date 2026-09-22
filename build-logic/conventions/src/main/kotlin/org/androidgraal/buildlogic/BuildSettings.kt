package org.androidgraal.buildlogic

import org.gradle.api.GradleException
import org.semver4j.Semver
import java.io.File
import java.io.StringReader
import java.util.Properties

class BuildSettings(
    val gradleProperties: String,
    val localProperties: String?,
    val gradleProperty: (String) -> String?,
    val environment: (String) -> String?,
    val repositoryRoot: File,
) {

    private val global = Properties().apply { load(StringReader(gradleProperties)) }

    private val local = Properties().apply {
        localProperties?.let { load(StringReader(it)) }
    }

    private fun required(key: String): String {
        return gradleProperty(key)
            ?: global.getProperty(key)
            ?: throw GradleException("no $key in the root gradle.properties")
    }

    val group: String = required("androidgraal.group")

    val version: String = required("androidgraal.version")

    val androidApi: Int = int("androidgraal.android.api")

    val sdkDir: File = directory("sdk.dir", "ANDROID_HOME")
        ?: throw GradleException("no Android SDK: neither sdk.dir nor \$ANDROID_HOME is set")

    val ndkDir: File? = directory("ndk.dir", "ANDROID_NDK_HOME")

    val ndkVersion: Semver? = semver("androidgraal.ndk.version")

    val cmakeDir: File? = directory("cmake.dir", "CMAKE_HOME")

    val cmakeVersion: Semver? = semver("androidgraal.cmake.version")

    private fun int(key: String): Int {
        val text = required(key)
        return text.toIntOrNull() ?: throw GradleException("$key: \"$text\" is not a number")
    }

    private fun directory(key: String, variable: String): File? {
        val text = local.getProperty(key) ?: environment(variable) ?: return null
        return repositoryRoot.resolve(text)
    }

    private fun semver(key: String): Semver? {
        val text = local.getProperty(key) ?: return null
        return Semver.parse(text)
            ?: throw GradleException("$key: \"$text\" is not a version (major.minor.patch[-prerelease])")
    }
}
