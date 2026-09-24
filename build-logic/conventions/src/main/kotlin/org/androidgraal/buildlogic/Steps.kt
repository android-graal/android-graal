package org.androidgraal.buildlogic

import org.apache.commons.io.output.CloseShieldOutputStream
import org.apache.commons.io.output.TeeOutputStream
import org.gradle.api.GradleException
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.OutputStream
import java.io.Serializable
import java.nio.file.FileSystems
import java.nio.file.Path

class RunContext private constructor(
    internal val script: Script,
    internal val log: OutputStream,
    internal var workDir: String?,
    internal val environment: MutableMap<String, String>,
    internal val variables: MutableMap<String, String>,
) {

    internal constructor(script: Script, log: OutputStream) :
        this(script, log, null, linkedMapOf(), linkedMapOf())

    internal fun forEach(bindings: Map<String, String>) = RunContext(
        script,
        log,
        workDir,
        LinkedHashMap(environment),
        LinkedHashMap(variables).apply { putAll(bindings) },
    )

    internal fun unwrap(value: String): String {
        val result = substitute(script.unwrap(value), variables)
        val unbound = UNBOUND.find(result)
            ?: return result
        throw GradleException("${script.name}: ${unbound.value} is not bound in \"$value\"")
    }
}

sealed interface Step : Serializable {
    fun run(context: RunContext)
}

data class ProgressStep(val message: String) : Step {

    override fun run(context: RunContext) {
        context.script.logger.lifecycle("> $message")
        context.log.line("=== $message")
    }
}

data class EnvStep(val key: String, val value: String) : Step {

    override fun run(context: RunContext) {
        context.environment[key] = value
    }
}

data class WorkDirStep(val path: String) : Step {

    override fun run(context: RunContext) {
        context.workDir = path
    }
}

data class ExecStep(val command: List<String>) : Step {

    override fun run(context: RunContext) {
        context.execute(command, capture = false)
    }
}

data class CaptureStep(val name: String, val command: List<String>) : Step {

    override fun run(context: RunContext) {
        val output = context.execute(command, capture = true)
            ?: throw GradleException("${context.script.name}: ${command.first()} printed nothing to capture as $name")
        context.variables[name] = output
        context.log.line("=== $name = $output")
    }
}

data class MkdirStep(val path: String) : Step {

    override fun run(context: RunContext) {
        File(context.unwrap(path)).mkdirs()
    }
}

data class DeleteStep(val path: String) : Step {

    override fun run(context: RunContext) {
        val target = File(context.unwrap(path))
        context.script.fileSystemOperations.delete { delete(target) }
    }
}

data class CopyStep(val source: String, val target: String, val includes: List<String>) : Step {

    override fun run(context: RunContext) {
        val sourceFile = File(context.unwrap(source))
        val targetDir = File(context.unwrap(target))
        if (includes.isEmpty()) {
            context.script.fileSystemOperations.copy {
                from(sourceFile)
                into(targetDir)
            }
        } else {
            val matched = context.script.objectFactory.fileTree().from(sourceFile)
            matched.setIncludes(includes)
            val files = matched.files
            context.script.fileSystemOperations.copy {
                from(files)
                into(targetDir)
            }
        }
        context.log.line("=== copied $sourceFile -> $targetDir")
    }
}

data class RsyncStep(val from: String, val into: String, val excludes: List<String>) : Step {

    override fun run(context: RunContext) {
        val command = listOf("rsync", "-a", "--delete") +
            excludes.map { "--exclude=$it" } + listOf("$from/", "$into/")
        // GNU rsync creates only the last component of the destination.
        File(context.unwrap(into)).mkdirs()
        context.execute(command, capture = false)
    }
}

data class Compiler(val cc: String, val cxx: String, val ar: String) : Serializable

data class Compilation(val source: File, val objectFile: File)

data class StaticLibStep(
    val archive: String,
    val objDir: String,
    val sources: List<String>,
    val compiler: Compiler,
    val includes: List<String>,
    val cflags: List<String>,
    val cxxflags: List<String>,
) : Step {

    override fun run(context: RunContext) {
        val product = context.unwrap(archive)
        val entries = sources.map { File(context.unwrap(it)) }
        val compilations = compilations(entries, File(context.unwrap(objDir)))
        if (compilations.isEmpty()) {
            throw GradleException("$product: no C or C++ source in $entries")
        }
        includes.map { File(context.unwrap(it)) }.forEach {
            if (!it.isDirectory) {
                throw GradleException("$product: no include directory $it")
            }
        }

        context.log.line("=== $product: ${compilations.size} sources")
        for (compilation in compilations) {
            compilation.objectFile.parentFile.mkdirs()
            context.execute(compile(compilation), capture = false)
        }
        context.execute(archiveCommand(compilations), capture = false)
    }

    internal fun compile(compilation: Compilation): List<String> {
        val cpp = compilation.source.extension == "cpp"
        val driver = if (cpp) compiler.cxx else compiler.cc
        val flags = cflags + includes.map { "-I$it" } + (if (cpp) cxxflags else emptyList())
        return listOf(driver) + flags + listOf("-c", compilation.source.path, "-o", compilation.objectFile.path)
    }

    internal fun archiveCommand(compilations: List<Compilation>): List<String> =
        listOf(compiler.ar, "rcs", archive) + compilations.map { it.objectFile.path }
}

data class ForEachStep(val dir: String, val glob: String, val steps: List<Step>) : Step {

    override fun run(context: RunContext) {
        val directory = File(context.unwrap(dir))
        val matches = matchingChildren(directory, glob)
        context.log.line("=== $directory/$glob: ${matches.size} match(es)")
        for (match in matches) {
            val iteration = context.forEach(eachBindings(match))
            for (step in steps) {
                step.run(iteration)
            }
        }
    }
}

internal fun matchingChildren(dir: File, glob: String): List<File> {
    val matcher = FileSystems.getDefault().getPathMatcher("glob:$glob")
    return dir.listFiles().orEmpty()
        .filter { matcher.matches(Path.of(it.name)) }
        .sortedBy { it.name }
}

internal fun compilations(entries: List<File>, objDir: File): List<Compilation> {
    return entries.flatMap { entry ->
        if (!entry.exists()) {
            throw GradleException("$entry does not exist")
        }
        val base = if (entry.isDirectory) entry else entry.parentFile
        entry.walkTopDown()
            .filter { it.isFile && it.extension in SOURCE_EXTENSIONS }
            .sortedBy { it.path }
            .map { Compilation(it, File(objDir, "${it.relativeTo(base).path.substringBeforeLast('.')}.o")) }
    }
}

private val SOURCE_EXTENSIONS = setOf("c", "cpp")

internal fun eachBindings(match: File): Map<String, String> = mapOf(
    "each.path" to match.absolutePath,
    "each.name" to match.name,
    "each.stem" to match.name.substringBeforeLast('.'),
)

internal fun substitute(value: String, variables: Map<String, String>): String =
    variables.entries.fold(value) { text, (name, bound) -> text.replace(sentinel(name), bound) }

private val UNBOUND = Regex("@\\{[^}]*}")

private fun RunContext.execute(command: List<String>, capture: Boolean): String? {
    val resolved = command.map(::unwrap)
    val directory = workDir?.let { File(unwrap(it)) } ?: script.defaultWorkDir
    directory.mkdirs()
    log.line("")
    val commandLine = resolved.joinToString(" ")
    log.line("=== $directory$ $commandLine")
    val startedAt = System.nanoTime()
    val resolvedEnvironment = environment.mapValues { unwrap(it.value) }
    val standard = CloseShieldOutputStream.wrap(log)
    val captured = if (capture) ByteArrayOutputStream() else null
    // Gradle closes each stream from its own forwarder thread.
    script.execOperations.exec {
        workingDir(directory)
        commandLine(resolved)
        environment(resolvedEnvironment)
        standardOutput = if (captured != null) TeeOutputStream(standard, captured) else standard
        errorOutput = CloseShieldOutputStream.wrap(log)
    }
    val seconds = (System.nanoTime() - startedAt) / 1_000_000_000.0
    log.line("=== $commandLine took %.1f s".format(seconds))

    if (captured == null)
        return null

    return captured.toString(Charsets.UTF_8).lines().lastOrNull { it.isNotBlank() }?.trim()
}

internal fun OutputStream.line(text: String) {
    write((text + "\n").toByteArray())
    flush()
}
