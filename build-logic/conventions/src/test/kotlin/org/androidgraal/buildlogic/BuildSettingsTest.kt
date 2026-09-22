package org.androidgraal.buildlogic

import org.gradle.api.GradleException
import org.semver4j.Semver
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class BuildSettingsTest {

    private val repositoryRoot = File("/repository")

    @Test
    fun `the gradle property wins for group and version, the root gradle properties is the fallback`() {
        val settings = settings(gradleProperty = { if (it == "androidgraal.version") "9.9.9" else null })

        assertEquals("org.androidgraal", settings.group)
        assertEquals("9.9.9", settings.version)
    }

    @Test
    fun `a missing group fails naming the key`() {
        val failure = assertFailsWith<GradleException> {
            settings(gradleProperties = "androidgraal.version=0.1.0-SNAPSHOT\nandroidgraal.android.api=23\n")
        }

        assertEquals("no androidgraal.group in the root gradle.properties", failure.message)
    }

    @Test
    fun `the android api is a global setting the gradle property overrides`() {
        assertEquals(23, settings().androidApi)
        assertEquals(
            24,
            settings(gradleProperty = { if (it == "androidgraal.android.api") "24" else null }).androidApi,
        )
    }

    @Test
    fun `a missing android api fails naming the key`() {
        val failure = assertFailsWith<GradleException> {
            settings(gradleProperties = "androidgraal.group=org.androidgraal\nandroidgraal.version=0.1.0\n")
        }

        assertEquals("no androidgraal.android.api in the root gradle.properties", failure.message)
    }

    @Test
    fun `an android api that is not a number fails naming the key`() {
        val failure = assertFailsWith<GradleException> {
            settings(gradleProperties = GLOBAL + "androidgraal.android.api=api23\n")
        }

        assertEquals("androidgraal.android.api: \"api23\" is not a number", failure.message)
    }

    @Test
    fun `a gradle property does not serve a machine key`() {
        val gradleProperty: (String) -> String? = {
            when (it) {
                "sdk.dir" -> "/property/sdk"
                "cmake.dir" -> "/property/cmake"
                else -> null
            }
        }

        val fromFile = settings(gradleProperty = gradleProperty)
        assertEquals(File("/file/sdk"), fromFile.sdkDir)
        assertNull(fromFile.cmakeDir)

        val fromEnvironment = settings(
            localProperties = null,
            gradleProperty = gradleProperty,
            environment = environment("ANDROID_HOME" to "/environment/sdk"),
        )
        assertEquals(File("/environment/sdk"), fromEnvironment.sdkDir)
        assertNull(fromEnvironment.cmakeDir)
    }

    @Test
    fun `every directory takes local properties before the environment`() {
        val variables = environment(
            "ANDROID_HOME" to "/environment/sdk",
            "ANDROID_NDK_HOME" to "/environment/ndk",
            "CMAKE_HOME" to "/environment/cmake",
        )

        val fromFile = settings(
            localProperties = "sdk.dir=/file/sdk\nndk.dir=/file/ndk\ncmake.dir=/file/cmake\n",
            environment = variables,
        )
        assertEquals(File("/file/sdk"), fromFile.sdkDir)
        assertEquals(File("/file/ndk"), fromFile.ndkDir)
        assertEquals(File("/file/cmake"), fromFile.cmakeDir)

        val fromEnvironment = settings(localProperties = null, environment = variables)
        assertEquals(File("/environment/sdk"), fromEnvironment.sdkDir)
        assertEquals(File("/environment/ndk"), fromEnvironment.ndkDir)
        assertEquals(File("/environment/cmake"), fromEnvironment.cmakeDir)
    }

    @Test
    fun `without a local properties key and without the environment the optional settings are null`() {
        val settings = settings()

        assertNull(settings.ndkDir)
        assertNull(settings.ndkVersion)
        assertNull(settings.cmakeDir)
        assertNull(settings.cmakeVersion)
    }

    @Test
    fun `a relative directory resolves against the repository root, an absolute one stays`() {
        val relative = settings(localProperties = "sdk.dir=sdk\nndk.dir=ndk\ncmake.dir=cmake\n")
        assertEquals(File(repositoryRoot, "sdk"), relative.sdkDir)
        assertEquals(File(repositoryRoot, "ndk"), relative.ndkDir)
        assertEquals(File(repositoryRoot, "cmake"), relative.cmakeDir)

        val absolute = settings(localProperties = "sdk.dir=/opt/sdk\nndk.dir=/opt/ndk\ncmake.dir=/opt/cmake\n")
        assertEquals(File("/opt/sdk"), absolute.sdkDir)
        assertEquals(File("/opt/ndk"), absolute.ndkDir)
        assertEquals(File("/opt/cmake"), absolute.cmakeDir)
    }

    @Test
    fun `the sdk directory fails without a local properties key and without the environment`() {
        val failure = assertFailsWith<GradleException> { settings(localProperties = null) }

        assertEquals("no Android SDK: neither sdk.dir nor \$ANDROID_HOME is set", failure.message)
    }

    @Test
    fun `the versions are parsed and a malformed one fails naming the key`() {
        val settings = settings(
            localProperties = "sdk.dir=/file/sdk\nandroidgraal.ndk.version=27.0.1\nandroidgraal.cmake.version=3.31.6\n",
        )
        assertEquals(Semver.parse("27.0.1"), settings.ndkVersion)
        assertEquals(Semver.parse("3.31.6"), settings.cmakeVersion)

        val failure = assertFailsWith<GradleException> {
            settings(localProperties = "sdk.dir=/file/sdk\nandroidgraal.cmake.version=3.31\n")
        }
        assertEquals(
            "androidgraal.cmake.version: \"3.31\" is not a version (major.minor.patch[-prerelease])",
            failure.message,
        )

        val ndk = assertFailsWith<GradleException> {
            settings(localProperties = "sdk.dir=/file/sdk\nandroidgraal.ndk.version=r27\n")
        }
        assertEquals(
            "androidgraal.ndk.version: \"r27\" is not a version (major.minor.patch[-prerelease])",
            ndk.message,
        )
    }

    private fun settings(
        gradleProperties: String = GLOBAL + "androidgraal.android.api=23\n",
        localProperties: String? = "sdk.dir=/file/sdk\n",
        gradleProperty: (String) -> String? = { null },
        environment: (String) -> String? = { null },
    ) = BuildSettings(gradleProperties, localProperties, gradleProperty, environment, repositoryRoot)

    private fun environment(vararg variables: Pair<String, String>): (String) -> String? {
        val values = variables.toMap()
        return { values[it] }
    }

    private companion object {
        const val GLOBAL = "androidgraal.group=org.androidgraal\nandroidgraal.version=0.1.0-SNAPSHOT\n"
    }
}
