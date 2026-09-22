package org.androidgraal.common

import org.apache.commons.io.FileUtils
import org.semver4j.Semver
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ToolchainVersionTest {

    private val tmp: File = createTempDirectory("toolchain-version-test").toFile()

    private val version = ToolchainVersion(
        toolchainVersion = "0.1.0-SNAPSHOT",
        host = "macos-aarch64",
        graalCommit = "d373d42abd7be7589f3c3e9f0dcf78394814cef0",
        llvmCommit = "3f7d8cf270530adac0d0976cd9a866f6a236def2",
        jdkCommit = "484105df8dab6489c1030865cf194fa8a39663aa",
        ndkVersion = Semver.parse("30.0.16248370")!!,
        androidApi = 23,
    )

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `read keeps every field of the file`() {
        val file = tmp.resolve(Toolchain.VERSION)
        file.writeText(
            """
            toolchain.version=0.1.0-SNAPSHOT
            host=macos-aarch64
            graal.commit=d373d42abd7be7589f3c3e9f0dcf78394814cef0
            llvm.commit=3f7d8cf270530adac0d0976cd9a866f6a236def2
            jdk.commit=484105df8dab6489c1030865cf194fa8a39663aa
            ndk.version=30.0.16248370
            android.api=23

            """.trimIndent(),
        )

        assertEquals(version, ToolchainVersion.read(file))
    }
}
