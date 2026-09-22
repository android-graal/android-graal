package org.androidgraal.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.file.ProjectLayout
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import java.io.File
import javax.inject.Inject

/** The step list is the fingerprint, so every path a step names enters as a sentinel, never absolute. */
@CacheableTask
abstract class Script :
    DefaultTask(),
    StepScope {

    @get:Inject
    abstract val execOperations: ExecOperations

    @get:Inject
    abstract val fileSystemOperations: FileSystemOperations

    @get:Inject
    abstract val objectFactory: ObjectFactory

    @get:Inject
    abstract val projectLayout: ProjectLayout

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val inputFiles: ConfigurableFileCollection

    private val roots = linkedMapOf<String, String>()

    private val artifacts = linkedMapOf<String, FileCollection>()

    private val reserved = mutableSetOf<String>()

    private val forbidden = mutableListOf<String>()

    private val builder = StepBuilder(::checkPortable, ::reserve)

    @get:Input
    val steps: List<Step> get() = builder.steps

    @get:Internal
    val logFile: File get() = projectLayout.buildDirectory.file("$name.log").get().asFile

    @get:Internal
    val defaultWorkDir: File get() = projectLayout.projectDirectory.asFile

    /** Scratch: neither input nor output. */
    fun root(alias: String, file: File): String {
        reserve(alias)
        roots[alias] = file.absoluteFile.normalize().path
        return sentinel(alias)
    }

    /** The sentinel form of [file], which must live under a registered root. */
    fun rel(file: File): String {
        val path = file.absoluteFile.normalize().path
        val root = roots.entries
            .filter { path == it.value || path.startsWith(it.value + File.separator) }
            .maxByOrNull { it.value.length }
            ?: throw GradleException("$path is under no root registered in $name")
        return sentinel(root.key) + path.removePrefix(root.value)
    }

    /** Something on this machine the task reads; a directory contributes its tree minus `.git`. */
    fun source(alias: String, file: File): String {
        if (!file.exists()) {
            throw GradleException("$name: no $file to declare as $alias")
        }
        if (file.isFile) {
            inputFiles.from(file)
        } else {
            val tree = objectFactory.fileTree().from(file)
            tree.exclude(".git", ".git/**")
            inputFiles.from(tree)
        }
        return root(alias, file)
    }

    /** Another task's artifact; the sentinel stands for its single file. */
    fun input(name: String, files: FileCollection): String {
        inputFiles.from(files)
        artifacts[name] = files
        reserve(name)
        return sentinel(name)
    }

    /** Declares [dir] as an output directory under [alias] and registers it as a root. */
    fun output(alias: String, dir: File): String {
        outputs.dir(dir)
        return root(alias, dir)
    }

    /** The source is declared as `<alias>.source`, the copy as [alias]. */
    fun sourceCopy(alias: String, dir: File, into: File): String {
        val from = source("$alias.source", dir)
        val copy = root(alias, into)
        rsync(from, copy, ".git")
        return copy
    }

    /** Prefixes of this machine no step may contain, whether or not a root covers them. */
    fun forbid(vararg prefixes: File) {
        prefixes.mapTo(forbidden) { it.absoluteFile.normalize().path }
    }

    override fun progress(message: String) = builder.progress(message)

    override fun exec(vararg args: String) = builder.exec(*args)

    override fun exec(command: List<String>) = builder.exec(command)

    override fun env(key: String, value: String) = builder.env(key, value)

    override fun workDir(path: String) = builder.workDir(path)

    override fun mkdir(path: String) = builder.mkdir(path)

    override fun delete(path: String) = builder.delete(path)

    override fun copy(from: String, into: String, vararg include: String) = builder.copy(from, into, *include)

    override fun rsync(from: String, into: String, vararg exclude: String) = builder.rsync(from, into, *exclude)

    override fun staticLib(
        archive: String,
        objDir: String,
        sources: List<String>,
        compiler: Compiler,
        includes: List<String>,
        cflags: List<String>,
        cxxflags: List<String>,
    ) = builder.staticLib(archive, objDir, sources, compiler, includes, cflags, cxxflags)

    override fun capture(name: String, vararg command: String): String = builder.capture(name, *command)

    override fun forEach(dir: String, glob: String, body: StepScope.(Each) -> Unit) = builder.forEach(dir, glob, body)

    @TaskAction
    fun runScript() {
        val log = logFile
        log.parentFile.mkdirs()
        log.delete()
        try {
            log.outputStream().buffered().use { out ->
                val context = RunContext(this, out)
                for (step in steps) {
                    step.run(context)
                }
            }
        } catch (e: Throwable) {
            logger.error("android-graal: $name failed; log: $log")
            if (log.isFile) {
                log.forEachLine { logger.error(it) }
            }
            throw e
        }
    }

    internal fun unwrap(value: String): String {
        var result = value
        for ((alias, path) in roots) {
            result = result.replace(sentinel(alias), path)
        }
        for ((name, files) in artifacts) {
            val token = sentinel(name)
            if (result.contains(token)) {
                result = result.replace(token, files.singleFile.absolutePath)
            }
        }
        return result
    }

    private fun reserve(alias: String) {
        if (!reserved.add(alias)) {
            throw GradleException("$alias is already registered in $name")
        }
    }

    private fun checkPortable(value: String) {
        for ((alias, path) in roots) {
            if (value.contains(path)) {
                throw GradleException(
                    "$name: \"$value\" contains the absolute path of root $alias; use rel(...)",
                )
            }
        }
        for (prefix in forbidden) {
            if (value.contains(prefix)) {
                throw GradleException(
                    "$name: \"$value\" contains the machine-specific path $prefix; declare it",
                )
            }
        }
    }
}
