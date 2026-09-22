package org.androidgraal.buildlogic

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ScriptTest {

    private val tmp: File = createTempDirectory("script-test").toFile()

    private val project: Project = ProjectBuilder.builder().withProjectDir(File(tmp, "project")).build()

    @AfterTest
    fun cleanUp() {
        tmp.deleteRecursively()
    }

    @Test
    fun `a glob matches the direct children of a directory, in name order`() {
        val dir = File(tmp, "query")
        dir.mkdirs()
        listOf("second.c", "first.c", "header.h").forEach { File(dir, it).writeText("") }
        File(dir, "nested").mkdirs()
        File(dir, "nested/deep.c").writeText("")

        assertEquals(
            listOf(File(dir, "first.c"), File(dir, "second.c")),
            matchingChildren(dir, "*.c"),
        )
        assertEquals(
            listOf("first.c", "header.h", "nested", "second.c"),
            matchingChildren(dir, "*").map { it.name },
        )
    }

    @Test
    fun `the each sentinels bind into a command`() {
        val match = File(tmp, "JavaNetHttpCookie.c")

        val command = listOf("clang", "-o", "bin/${Each.stem}", Each.path, "# ${Each.name}")
            .map { substitute(it, eachBindings(match)) }

        assertEquals(
            listOf("clang", "-o", "bin/JavaNetHttpCookie", match.absolutePath, "# JavaNetHttpCookie.c"),
            command,
        )
    }

    @Test
    fun `forEach collects the steps of its body`() {
        val builder = StepBuilder({}, {})

        builder.exec("outer")
        builder.forEach("@{query}", "*.c") { each ->
            progress("compiling")
            exec("clang", each.path)
        }

        assertEquals(
            listOf(
                ExecStep(listOf("outer")),
                ForEachStep(
                    "@{query}",
                    "*.c",
                    listOf(ProgressStep("compiling"), ExecStep(listOf("clang", Each.path))),
                ),
            ),
            builder.steps,
        )
    }

    @Test
    fun `exec takes a list like a vararg`() {
        val command = listOf("clang", "-c", "a.c", "-o", "a.o")
        val builder = StepBuilder({}, {})

        builder.exec(*command.toTypedArray())
        builder.exec(command)

        val (fromVararg, fromList) = builder.steps
        assertEquals(ExecStep(command), fromVararg)
        assertEquals(fromVararg, fromList)
    }

    @Test
    fun `staticLib collects the step`() {
        val builder = StepBuilder({}, {})
        val clang = Compiler("@{ndk}/bin/clang", "@{ndk}/bin/clang++", "@{ndk}/bin/llvm-ar")

        builder.staticLib(
            "@{lib}/libjvm.a",
            "@{obj}/jvm",
            listOf("@{jvmPosix}/src", "@{fallbacks}"),
            clang,
            listOf("@{jdkInclude}"),
            listOf("-fPIC"),
            listOf("-std=c++14"),
        )

        assertEquals(
            listOf(
                StaticLibStep(
                    "@{lib}/libjvm.a",
                    "@{obj}/jvm",
                    listOf("@{jvmPosix}/src", "@{fallbacks}"),
                    clang,
                    listOf("@{jdkInclude}"),
                    listOf("-fPIC"),
                    listOf("-std=c++14"),
                ),
            ),
            builder.steps,
        )
    }

    @Test
    fun `static lib sources walk a directory and take a file as is`() {
        val src = File(tmp, "src")
        File(src, "sub/deep").mkdirs()
        listOf("a.c", "h.h", "sub/c.c", "sub/deep/b.cpp").forEach { File(src, it).writeText("") }
        val generated = File(tmp, "generated/JvmFuncsFallbacks.c")
        generated.parentFile.mkdirs()
        generated.writeText("")
        val objDir = File(tmp, "obj")

        assertEquals(
            listOf(
                Compilation(File(src, "a.c"), File(objDir, "a.o")),
                Compilation(File(src, "sub/c.c"), File(objDir, "sub/c.o")),
                Compilation(File(src, "sub/deep/b.cpp"), File(objDir, "sub/deep/b.o")),
                Compilation(generated, File(objDir, "JvmFuncsFallbacks.o")),
            ),
            compilations(listOf(src, generated), objDir),
        )
    }

    @Test
    fun `a static lib compiles C and C++ with their flags, then archives`() {
        val src = File(tmp, "src")
        src.mkdirs()
        listOf("a.c", "b.cpp").forEach { File(src, it).writeText("") }
        val objDir = File(tmp, "obj")
        val step = StaticLibStep(
            "lib.a",
            objDir.path,
            listOf(src.path),
            Compiler("cc", "c++", "ar"),
            listOf("inc"),
            listOf("-f"),
            listOf("-x"),
        )
        val (c, cpp) = compilations(listOf(src), objDir)

        assertEquals(
            listOf("cc", "-f", "-Iinc", "-c", "$src/a.c", "-o", "$objDir/a.o"),
            step.compile(c),
        )
        assertEquals(
            listOf("c++", "-f", "-Iinc", "-x", "-c", "$src/b.cpp", "-o", "$objDir/b.o"),
            step.compile(cpp),
        )
        assertEquals(
            listOf("ar", "rcs", "lib.a", "$objDir/a.o", "$objDir/b.o"),
            step.archiveCommand(listOf(c, cpp)),
        )
    }

    @Test
    fun `a missing entry fails by name`() {
        val gone = File(tmp, "gone")

        val failure = assertFailsWith<GradleException> {
            compilations(listOf(gone), File(tmp, "obj"))
        }

        assertEquals("$gone does not exist", failure.message)
    }

    @Test
    fun `a static lib without sources fails`() {
        val src = File(tmp, "headers")
        src.mkdirs()
        File(src, "h.h").writeText("")
        val step = StaticLibStep(
            "lib.a",
            File(tmp, "obj").path,
            listOf(src.path),
            Compiler("cc", "c++", "ar"),
            emptyList(),
            emptyList(),
            emptyList(),
        )

        val failure = assertFailsWith<GradleException> {
            step.run(RunContext(script(), ByteArrayOutputStream()))
        }

        assertEquals("lib.a: no C or C++ source in [$src]", failure.message)
    }

    @Test
    fun `a missing include directory fails by name`() {
        val src = File(tmp, "src")
        src.mkdirs()
        File(src, "a.c").writeText("")
        val missing = File(tmp, "missing")
        val step = StaticLibStep(
            "lib.a",
            File(tmp, "obj").path,
            listOf(src.path),
            Compiler("cc", "c++", "ar"),
            listOf(missing.path),
            emptyList(),
            emptyList(),
        )

        val failure = assertFailsWith<GradleException> {
            step.run(RunContext(script(), ByteArrayOutputStream()))
        }

        assertEquals("lib.a: no include directory $missing", failure.message)
    }

    @Test
    fun `rsync creates the missing parents of its destination`() {
        val src = File(tmp, "src")
        src.mkdirs()
        File(src, "a.c").writeText("a")
        val copy = File(tmp, "missing/parent/copy")

        RsyncStep(src.path, copy.path, emptyList()).run(RunContext(script(), ByteArrayOutputStream()))

        assertEquals("a", File(copy, "a.c").readText())
    }

    @Test
    fun `a source directory declares its tree, minus git, and a root`() {
        val clone = File(tmp, "clone")
        File(clone, "src").mkdirs()
        File(clone, "src/a.c").writeText("")
        File(clone, ".git").mkdirs()
        File(clone, ".git/config").writeText("")
        val script = script()

        assertEquals("@{clone}", script.source("clone", clone))
        assertEquals(setOf(File(clone, "src/a.c")), script.inputFiles.files)
        assertEquals("@{clone}/src/a.c", script.rel(File(clone, "src/a.c")))
    }

    @Test
    fun `a source file declares the file itself`() {
        val cmake = File(tmp, "cmake/bin/cmake")
        cmake.parentFile.mkdirs()
        cmake.writeText("")
        val script = script()

        assertEquals("@{cmake}", script.source("cmake", cmake))
        assertEquals(setOf(cmake), script.inputFiles.files)
    }

    @Test
    fun `an output declares the directory and a root`() {
        val lib = File(tmp, "build/lib")
        val script = script()

        assertEquals("@{lib}", script.output("lib", lib))
        assertEquals(setOf(lib), script.outputs.files.files)
        assertEquals("@{lib}/libjvm.a", script.rel(File(lib, "libjvm.a")))
    }

    @Test
    fun `a source copy declares the origin and copies it to the root`() {
        val origin = File(tmp, "clone")
        origin.mkdirs()
        File(origin, "a.c").writeText("")
        val copy = File(tmp, "build/src/clone")
        val script = script()

        assertEquals("@{clone}", script.sourceCopy("clone", origin, copy))
        assertEquals(listOf(RsyncStep("@{clone.source}", "@{clone}", listOf(".git"))), script.steps)
        assertEquals(setOf(File(origin, "a.c")), script.inputFiles.files)
        assertEquals("@{clone.source}/a.c", script.rel(File(origin, "a.c")))
        assertEquals("@{clone}/a.c", script.rel(File(copy, "a.c")))
        assertEquals("$origin $copy", script.unwrap("@{clone.source} @{clone}"))
    }

    @Test
    fun `a source must exist`() {
        val script = script()

        val failure = assertFailsWith<GradleException> { script.source("gone", File(tmp, "gone")) }
        assertEquals("${script.name}: no ${File(tmp, "gone")} to declare as gone", failure.message)
    }

    @Test
    fun `a file under no root has no sentinel`() {
        val script = script()
        val stray = File(tmp, "stray")

        val failure = assertFailsWith<GradleException> { script.rel(stray) }

        assertEquals("$stray is under no root registered in ${script.name}", failure.message)
    }

    @Test
    fun `a forbidden prefix is rejected where no root covers it`() {
        val script = script()
        script.forbid(tmp)

        val failure = assertFailsWith<GradleException> { script.exec("clang", "-I$tmp/include") }

        assertEquals(
            "${script.name}: \"-I$tmp/include\" contains the machine-specific path $tmp; declare it",
            failure.message,
        )
    }

    @Test
    fun `the innermost root spells a subpath`() {
        val script = script()
        val outer = File(tmp, "outer")

        script.root("outer", outer)
        script.output("lib", File(outer, "lib"))

        assertEquals("@{outer}/other", script.rel(File(outer, "other")))
        assertEquals("@{lib}/libjvm.a", script.rel(File(outer, "lib/libjvm.a")))
    }

    @Test
    fun `the log outlives the processes that write into it`() {
        val script = script()
        script.exec("/bin/echo", "first")
        val second = script.capture("second", "/bin/echo", "second")
        script.exec("/bin/echo", second)
        script.progress("after the processes")

        script.runScript()

        val log = script.logFile.readLines()
        assertEquals(listOf("first", "second", "second"), log.filter { it.isNotEmpty() && !it.startsWith("=== ") })
        assertEquals("=== after the processes", log.last())
    }

    private fun script(): Script = project.tasks.register("script", Script::class.java).get()
}
