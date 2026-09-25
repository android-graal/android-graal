package org.androidgraal.gradle

import org.androidgraal.substrate.ProcessRunner
import org.apache.commons.io.output.CloseShieldOutputStream
import org.apache.commons.io.output.TeeOutputStream
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.ProjectLayout
import org.gradle.api.provider.Property
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import org.gradle.work.DisableCachingByDefault
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject

@DisableCachingByDefault(because = "each subclass declares its own caching")
abstract class BaseTask : DefaultTask() {

    @get:Internal
    abstract val taskDir: DirectoryProperty

    @get:Internal
    val logFile: File get() = taskDir.file("$name.log").get().asFile

    @get:Internal
    abstract val console: Property<Boolean>

    @get:Inject
    protected abstract val execOperations: ExecOperations

    @get:Inject
    protected abstract val providers: ProviderFactory

    @get:Inject
    protected abstract val layout: ProjectLayout

    private var log: OutputStream? = null

    init {
        console.convention(providers.gradleProperty(CONSOLE_PROPERTY).map { it != "false" }.orElse(false))
    }

    @TaskAction
    fun run() {
        val file = logFile
        try {
            file.parentFile.mkdirs()
            file.delete()
            FileOutputStream(file).buffered().use { stream ->
                log = if (console.get()) TeeOutputStream(stream, System.out) else stream
                execute()
            }
        } catch (e: Throwable) {
            logger.error("=== $file")
            if (file.isFile) {
                file.forEachLine { logger.error(it) }
            }
            logger.error("=== end of ${file.name}")
            throw GradleException("$name failed, see $file", e)
        } finally {
            log = null
        }
    }

    protected abstract fun execute()

    protected fun runner(): ProcessRunner {
        val out = checkNotNull(log) { "$path: runner() is only available while the task action runs" }
        return ProcessRunner { command, workingDir ->
            out.write("=== $workingDir$ ${command.joinToString(" ")}\n".toByteArray())
            out.flush()
            // Gradle closes each stream from its own forwarder thread.
            execOperations.exec {
                it.commandLine = command
                it.workingDir = workingDir
                it.standardOutput = CloseShieldOutputStream.wrap(out)
                it.errorOutput = CloseShieldOutputStream.wrap(out)
                it.isIgnoreExitValue = true
            }.exitValue
        }
    }
}

private const val CONSOLE_PROPERTY = "androidgraal.console"
