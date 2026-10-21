package org.androidgraal.substrate

import com.android.tools.r8.ClassFileResourceProvider
import com.android.tools.r8.CompilationMode
import com.android.tools.r8.Diagnostic
import com.android.tools.r8.DiagnosticsHandler
import com.android.tools.r8.JdkClassFileProvider
import com.android.tools.r8.OutputMode
import com.android.tools.r8.R8
import com.android.tools.r8.R8Command
import com.android.tools.r8.StringConsumer
import java.io.File
import java.io.OutputStream
import java.io.PrintStream

class ImageShrinker(private val config: Config) {

    data class Config(
        val program: List<File>,
        val jdkHome: File,
        val rules: List<File>,
        val outputJar: File,
        val imageMapping: File,
        val configuration: File,
        /** AGP's `android.enableR8.fullMode`. */
        val fullMode: Boolean,
        /** AGP's `android.r8.strictFullModeForKeepRules`. */
        val strictFullModeForKeepRules: Boolean,
        val debuggable: Boolean,
    )

    fun command(diagnostics: DiagnosticsHandler, library: ClassFileResourceProvider): R8Command {
        return R8Command.builder(diagnostics)
            .addProgramFiles(config.program.map(File::toPath))
            .addLibraryResourceProvider(library)
            .addProguardConfigurationFiles(config.rules.map(File::toPath))
            .setProguardMapOutputPath(config.imageMapping.toPath())
            .setProguardConfigurationConsumer(StringConsumer.FileConsumer(config.configuration.toPath()))
            .setOutput(config.outputJar.toPath(), OutputMode.ClassFile, true)
            .setMode(if (config.debuggable) CompilationMode.DEBUG else CompilationMode.RELEASE)
            .setProguardCompatibility(!config.fullMode)
            .enableLegacyFullModeForKeepRules(!config.strictFullModeForKeepRules)
            .build()
    }

    fun shrink(log: OutputStream) {
        listOf(config.outputJar, config.imageMapping, config.configuration).forEach {
            it.delete()
            it.parentFile.mkdirs()
        }
        val diagnostics = LogDiagnostics(PrintStream(log, true))
        (JdkClassFileProvider.fromJdkHome(config.jdkHome.toPath()) as JdkClassFileProvider).use { library ->
            R8.run(command(diagnostics, library))
        }
    }

    private class LogDiagnostics(private val out: PrintStream) : DiagnosticsHandler {
        override fun error(diagnostic: Diagnostic) =
            DiagnosticsHandler.printDiagnosticToStream(diagnostic, "Error", out)

        override fun warning(diagnostic: Diagnostic) =
            DiagnosticsHandler.printDiagnosticToStream(diagnostic, "Warning", out)

        override fun info(diagnostic: Diagnostic) = DiagnosticsHandler.printDiagnosticToStream(diagnostic, "Info", out)
    }
}
