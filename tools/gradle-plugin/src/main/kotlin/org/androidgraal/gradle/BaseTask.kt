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
abstract class BaseTask(private val dirName: String) : DefaultTask() {

    @get:Internal
    abstract val taskDir: DirectoryProperty

    @get:Internal
    val logFile: File get() = taskDir.file("$name.log").get().asFile

    @get:Internal
    abstract val variantName: Property<String>

    @get:Internal
    abstract val console: Property<Boolean>

    @get:Inject
    protected abstract val execOperations: ExecOperations

    @get:Inject
    protected abstract val providers: ProviderFactory

    @get:Inject
    protected abstract val layout: ProjectLayout

    private var stream: OutputStream? = null

    @get:Internal
    protected val log: OutputStream
        get() = checkNotNull(stream) { "$path: log is only available while the task action runs" }

    init {
        console.convention(providers.gradleProperty(CONSOLE_PROPERTY).map { it != "false" }.orElse(false))
        taskDir.convention(
            layout.buildDirectory.dir(
                providers.provider { listOfNotNull("androidgraal", variantName.orNull, dirName).joinToString("/") },
            ),
        )
    }

    @TaskAction
    fun run() {
        val file = logFile
        try {
            file.parentFile.mkdirs()
            file.delete()
            FileOutputStream(file).buffered().use { out ->
                stream = if (console.get()) TeeOutputStream(out, System.out) else out
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
            stream = null
        }
    }

    protected abstract fun execute()

    protected fun runner(): ProcessRunner {
        val out = log
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
