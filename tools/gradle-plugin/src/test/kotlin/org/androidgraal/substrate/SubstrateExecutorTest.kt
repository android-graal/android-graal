package org.androidgraal.substrate

import org.androidgraal.common.Host
import org.androidgraal.common.Target
import org.androidgraal.common.ToolchainLayout
import org.apache.commons.io.FileUtils
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SubstrateExecutorTest {

    private val tmp: File = createTempDirectory("substrate-test").toFile()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `command is the pinned LLVM flag set, in order, with every option switched on`() {
        val executor = SubstrateExecutor(
            SubstrateExecutor.Config(
                toolchain = ToolchainLayout(File("/toolchain")),
                imageName = "hello",
                mainClass = "HelloWorld",
                classpath = listOf(File("/build/nativeImage.jar"), File("/libs/extra.jar")),
                workDir = File("/build/work"),
                objectsDir = File("/build/objects"),
                buildArgs = listOf("-H:+PrintAnalysisCallTree"),
                jvmArgs = listOf("-Xmx6g"),
                systemProperties = mapOf("androidgraal.smoke" to "yes"),
                configurationFileDirectories = listOf(File("/build/config")),
                verbose = true,
                quickBuild = true,
                useLLVM = true,
                target = Target.AARCH64,
                host = Host.LINUX_X86_64,
            ),
            noRunner,
        )

        assertEquals(
            listOf(
                "/toolchain/graalvm/lib/svm/bin/native-image",
                "--shared",
                "--tool:llvm-backend",
                "-H:+UnlockExperimentalVMOptions",
                "-Dsvm.platform=org.graalvm.nativeimage.Platform\$ANDROID_AARCH64",
                "-Dsvm.targetArch=aarch64",
                "-Djdk.internal.foreign.CABI=LINUX_AARCH_64",
                "-Dandroidgraal.smoke=yes",
                "-H:+UseCAPCache",
                "-H:CAPCacheDir=/toolchain/targets/aarch64-linux-android/capcache",
                "-H:TempDirectory=/build/work",
                "-o",
                "/build/work/hello",
                "-march=armv8-a",
                "-H:+ReportExceptionStackTraces",
                "-H:+AddAllCharsets",
                "-H:+IncludeAllLocales",
                "--initialize-at-run-time=java.net.DefaultInterface",
                "-H:+ExitAfterRelocatableImageWrite",
                "--verbose",
                "-Ob",
                "-J-Xmx6g",
                "-H:ConfigurationFileDirectories=/build/config",
                "-H:+PrintAnalysisCallTree",
                "-cp",
                "/build/nativeImage.jar" + File.pathSeparator + "/libs/extra.jar",
                "HelloWorld",
            ),
            executor.command(),
        )
    }

    @Test
    fun `command is the pinned LIR flag set, without the LLVM-only flags even on a macOS arm64 host`() {
        val config = minimalConfig(File("/toolchain"), Host.MACOS_AARCH64).copy(
            workDir = File("/build/work"),
            objectsDir = File("/build/objects"),
            useLLVM = false,
        )

        assertEquals(
            listOf(
                "/toolchain/graalvm/lib/svm/bin/native-image",
                "--shared",
                "-H:+UnlockExperimentalVMOptions",
                "-Dsvm.platform=org.graalvm.nativeimage.Platform\$ANDROID_AARCH64",
                "-Dsvm.targetArch=aarch64",
                "-Djdk.internal.foreign.CABI=LINUX_AARCH_64",
                "-H:+UseCAPCache",
                "-H:CAPCacheDir=/toolchain/targets/aarch64-linux-android/capcache",
                "-H:TempDirectory=/build/work",
                "-o",
                "/build/work/hello",
                "-march=armv8-a",
                "-H:+ReportExceptionStackTraces",
                "-H:+AddAllCharsets",
                "-H:+IncludeAllLocales",
                "--initialize-at-run-time=java.net.DefaultInterface",
                "-H:+ExitAfterRelocatableImageWrite",
                "-cp",
                "/build/nativeImage.jar",
                "HelloWorld",
            ),
            SubstrateExecutor(config, noRunner).command(),
        )
    }

    @Test
    fun `the optional flags are absent by default`() {
        val command = SubstrateExecutor(minimalConfig(File("/toolchain")), noRunner).command()

        for (flag in listOf("--verbose", "-Ob")) {
            assertTrue(flag !in command, "$flag must not be there by default: $command")
        }
        assertTrue(command.none { it.startsWith("-H:ConfigurationFileDirectories") }, "$command")
        assertTrue(command.none { it.startsWith("-J") }, "$command")
        assertTrue("--tool:llvm-backend" in command, "$command")
    }

    @Test
    fun `the boot module check is off only with LLVM on a macOS arm64 host`() {
        val flag = "-H:CheckBootModuleDependencies=0"
        val macos = SubstrateExecutor(
            minimalConfig(File("/toolchain"), Host.MACOS_AARCH64),
            noRunner,
        ).command()
        val linux = SubstrateExecutor(minimalConfig(File("/toolchain"), Host.LINUX_X86_64), noRunner).command()
        val macosLir = SubstrateExecutor(
            minimalConfig(File("/toolchain"), Host.MACOS_AARCH64).copy(useLLVM = false),
            noRunner,
        ).command()

        assertTrue(flag in macos, "$macos")
        assertTrue(flag !in linux, "$linux")
        assertTrue(flag !in macosLir, "$macosLir")
    }

    @Test
    fun `compile collects the LLVM objects and the linker's input lists out of the temp directory`() {
        val config = minimalConfig(tmp.resolve("toolchain"))
        val runner = ProcessRunner { _, workingDir ->
            writeBuilderOutput(workingDir.resolve("SVM-1"))
            0
        }

        val compiled = SubstrateExecutor(config, runner).compile()

        assertEquals(
            listOf(config.objectsDir.resolve("hello.o"), config.objectsDir.resolve("llvm.o")),
            compiled.objects,
        )
        assertEquals(listOf("DATA", "CODE"), compiled.objects.map { it.readText() })
        assertTrue("run_main" in compiled.exportedSymbols.readText())
        assertEquals(listOf("java", "zip", "jvm"), compiled.staticLibraries)
        assertEquals(listOf("m", "dl", "z"), compiled.libraries)
    }

    @Test
    fun `compile collects the one LIR object`() {
        val config = minimalConfig(tmp.resolve("toolchain")).copy(useLLVM = false)
        val runner = ProcessRunner { _, workingDir ->
            writeBuilderOutput(workingDir.resolve("SVM-1"), llvm = false)
            0
        }

        val compiled = SubstrateExecutor(config, runner).compile()

        assertEquals(listOf(config.objectsDir.resolve("hello.o")), compiled.objects)
        assertEquals("DATA", compiled.objects.single().readText())
        assertFalse(config.objectsDir.resolve(CompiledImage.LLVM_OBJECT).exists())
        assertEquals(listOf("java", "zip", "jvm"), compiled.staticLibraries)
    }

    @Test
    fun `compile refuses more than one temp directory`() {
        val config = minimalConfig(tmp.resolve("toolchain"))
        val runner = ProcessRunner { _, workingDir ->
            writeBuilderOutput(workingDir.resolve("SVM-1"))
            writeBuilderOutput(workingDir.resolve("SVM-2"))
            0
        }

        val failure = assertFailsWith<IllegalStateException> { SubstrateExecutor(config, runner).compile() }

        assertTrue(failure.message!!.startsWith("expected one SVM-* directory"), failure.message)
    }

    @Test
    fun `compile names the exit code when the builder fails`() {
        val config = minimalConfig(tmp.resolve("toolchain"))
        val runner = ProcessRunner { _, _ -> 1 }

        val failure = assertFailsWith<IllegalStateException> { SubstrateExecutor(config, runner).compile() }

        assertEquals("native-image failed with exit code 1", failure.message)
    }

    private fun minimalConfig(toolchainRoot: File, host: Host = Host.LINUX_X86_64) = SubstrateExecutor.Config(
        toolchain = ToolchainLayout(toolchainRoot),
        imageName = "hello",
        mainClass = "HelloWorld",
        classpath = listOf(File("/build/nativeImage.jar")),
        workDir = tmp.resolve("work"),
        objectsDir = tmp.resolve("objects"),
        target = Target.AARCH64,
        host = host,
    )

    private fun writeBuilderOutput(temp: File, llvm: Boolean = true) {
        temp.mkdirs()
        temp.resolve("hello.o").writeText("DATA")
        if (llvm) {
            temp.resolve("llvm").mkdirs()
            temp.resolve("llvm").resolve(CompiledImage.LLVM_OBJECT).writeText("CODE")
        }
        temp.resolve(CompiledImage.EXPORTED_SYMBOLS).writeText("{\nglobal:\n\"run_main\";\nlocal: *;\n};")
        temp.resolve(CompiledImage.STATIC_LIBRARIES).writeText("java\nzip\njvm\n")
        temp.resolve(CompiledImage.LIBRARIES).writeText("m\ndl\nz\n")
    }

    private val noRunner = ProcessRunner { _, _ -> 0 }
}
