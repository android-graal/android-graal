package org.androidgraal.gradle

import org.androidgraal.substrate.ProcessRunner
import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import org.gradle.work.DisableCachingByDefault
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@DisableCachingByDefault(because = "each subclass declares its own caching")
abstract class BaseTask : DefaultTask() {

    @get:Internal
    val logFile: File get() = projectLayout.buildDirectory.file("$name.log").get().asFile

    @get:Inject
    protected abstract val execOperations: ExecOperations

    @get:Inject
    protected abstract val projectLayout: ProjectLayout

    @TaskAction
    fun run() {
        logFile.delete()
        try {
            execute()
        } catch (e: Throwable) {
            if (logFile.isFile) {
                logger.error("android-graal: $path failed; log: $logFile")
                logFile.forEachLine { logger.error(it) }
            }
            throw e
        }
    }

    protected abstract fun execute()

    protected fun runner(): ProcessRunner {
        return ProcessRunner { command, workingDir ->
            val log = logFile
            log.parentFile.mkdirs()
            FileOutputStream(log, true).buffered().use { out ->
                out.write("=== $workingDir$ ${command.joinToString(" ")}\n".toByteArray())
                execOperations.exec {
                    it.commandLine = command
                    it.workingDir = workingDir
                    it.standardOutput = out
                    it.errorOutput = out
                    it.isIgnoreExitValue = true
                }.exitValue
            }
        }
    }
}
