package org.androidgraal.buildlogic

interface StepScope {

    fun progress(message: String)

    fun exec(vararg args: String)

    fun exec(command: List<String>)

    /** Added to the inherited environment of every following [exec]. */
    fun env(key: String, value: String)

    /** Working directory of every following [exec]; the project directory by default. */
    fun workDir(path: String)

    fun mkdir(path: String)

    fun delete(path: String)

    /**
     * With [include] patterns the matching files are copied flat into [into]; without them [from]
     * follows Gradle's copy semantics, so a directory contributes its contents and a file itself.
     */
    fun copy(from: String, into: String, vararg include: String)

    /** `rsync -a --delete --exclude=<x>... <from>/ <into>/`. */
    fun rsync(from: String, into: String, vararg exclude: String)

    /** Each [sources] entry is a file or a directory walked for `*.c` and `*.cpp`; [cxxflags] are added for C++. */
    fun staticLib(
        archive: String,
        objDir: String,
        sources: List<String>,
        compiler: Compiler,
        includes: List<String>,
        cflags: List<String>,
        cxxflags: List<String>,
    )

    /** Runs [command] like [exec]; the returned sentinel stands for the last non-empty line of its output. */
    fun capture(name: String, vararg command: String): String

    /**
     * Runs [body] once per direct child of [dir] whose file name matches [glob], in name order; what it
     * sets with [env] or [workDir] ends with the iteration.
     */
    fun forEach(dir: String, glob: String, body: StepScope.(Each) -> Unit)
}

object Each {

    /** The absolute path of the match. */
    val path: String = sentinel("each.path")

    val name: String = sentinel("each.name")

    val stem: String = sentinel("each.stem")
}

class StepBuilder(private val check: (String) -> Unit, private val reserve: (String) -> Unit) : StepScope {

    private val stepList = mutableListOf<Step>()

    val steps: List<Step> get() = stepList

    override fun progress(message: String) {
        check(message)
        stepList += ProgressStep(message)
    }

    override fun exec(vararg args: String) = exec(args.toList())

    override fun exec(command: List<String>) {
        require(command.isNotEmpty()) { "exec needs at least the executable" }
        command.forEach(check)
        stepList += ExecStep(command)
    }

    override fun env(key: String, value: String) {
        check(value)
        stepList += EnvStep(key, value)
    }

    override fun workDir(path: String) {
        check(path)
        stepList += WorkDirStep(path)
    }

    override fun mkdir(path: String) {
        check(path)
        stepList += MkdirStep(path)
    }

    override fun delete(path: String) {
        check(path)
        stepList += DeleteStep(path)
    }

    override fun copy(from: String, into: String, vararg include: String) {
        check(from)
        check(into)
        include.forEach(check)
        stepList += CopyStep(from, into, include.toList())
    }

    override fun rsync(from: String, into: String, vararg exclude: String) {
        check(from)
        check(into)
        exclude.forEach(check)
        stepList += RsyncStep(from, into, exclude.toList())
    }

    override fun staticLib(
        archive: String,
        objDir: String,
        sources: List<String>,
        compiler: Compiler,
        includes: List<String>,
        cflags: List<String>,
        cxxflags: List<String>,
    ) {
        require(sources.isNotEmpty()) { "staticLib needs at least one source" }
        check(archive)
        check(objDir)
        sources.forEach(check)
        listOf(compiler.cc, compiler.cxx, compiler.ar).forEach(check)
        includes.forEach(check)
        cflags.forEach(check)
        cxxflags.forEach(check)
        stepList += StaticLibStep(archive, objDir, sources, compiler, includes, cflags, cxxflags)
    }

    override fun capture(name: String, vararg command: String): String {
        require(command.isNotEmpty()) { "capture needs at least the executable" }
        reserve(name)
        command.forEach(check)
        stepList += CaptureStep(name, command.toList())
        return sentinel(name)
    }

    override fun forEach(dir: String, glob: String, body: StepScope.(Each) -> Unit) {
        check(dir)
        val nested = StepBuilder(check, reserve)
        nested.body(Each)
        stepList += ForEachStep(dir, glob, nested.steps)
    }
}

internal fun sentinel(alias: String): String = "@{$alias}"
