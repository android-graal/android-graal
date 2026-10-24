package org.androidgraal.gradle

import org.androidgraal.substrate.ProcessRunner
import org.apache.commons.io.FileUtils
import org.gradle.api.GradleException
import org.gradle.api.tasks.Internal
import org.gradle.testfixtures.ProjectBuilder
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BaseTaskTest {

    private val tmp: File = createTempDirectory("base-task-test").toFile()

    private val task: ToolTask = ProjectBuilder.builder().withProjectDir(tmp).build()
        .tasks.register("tool", ToolTask::class.java) {
            it.taskDir.set(tmp.resolve("build/task"))
        }.get()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `the log holds the command header and the output`() {
        var exit = -1
        task.action = { exit = it.run(listOf(ECHO, "hello"), tmp) }

        task.run()

        assertEquals(0, exit)
        assertEquals(tmp.canonicalFile.resolve("build/task/tool.log"), task.logFile.canonicalFile)
        assertEquals(
            listOf(
                "=== tool started <time>",
                "=== $tmp$ $ECHO hello",
                "hello",
                "=== exit 0 after <s> s",
                "=== tool finished after <s> s",
            ),
            task.timedLog(),
        )
    }

    @Test
    fun `every process of one execution lands in one log`() {
        task.action = {
            it.run(listOf(ECHO, "one"), tmp)
            it.run(listOf(ECHO, "two"), tmp)
        }

        task.run()

        assertEquals(
            listOf(
                "=== tool started <time>",
                "=== $tmp$ $ECHO one",
                "one",
                "=== exit 0 after <s> s",
                "=== $tmp$ $ECHO two",
                "two",
                "=== exit 0 after <s> s",
                "=== tool finished after <s> s",
            ),
            task.timedLog(),
        )
    }

    @Test
    fun `standard output and standard error both land in the log`() {
        val java = ProcessHandle.current().info().command().get()
        val classes = File(BaseTaskTest::class.java.protectionDomain.codeSource.location.toURI())
        val main = "org.androidgraal.gradle.BothStreams"
        var exit = -1
        task.action = { exit = it.run(listOf(java, "-cp", classes.path, main), tmp) }

        task.run()

        assertEquals(1, exit)
        val lines = task.timedLog()
        assertEquals(listOf("=== tool started <time>", "=== $tmp$ $java -cp $classes $main"), lines.take(2))
        assertEquals(listOf("=== exit 1 after <s> s", "=== tool finished after <s> s"), lines.takeLast(2))
        assertTrue("to standard output" in lines, "$lines")
        assertTrue("to standard error" in lines, "$lines")
    }

    @Test
    fun `a failing process throws and the log survives`() {
        task.action = {
            it.run(listOf(ECHO, "before"), tmp)
            it.runOrFail("false", listOf(FALSE), tmp)
        }

        val failure = assertFailsWith<GradleException> { task.run() }

        assertEquals("${task.name} failed, see ${task.logFile}", failure.message)
        assertEquals("false failed with exit code 1", failure.cause?.message)
        assertEquals(
            listOf(
                "=== tool started <time>",
                "=== $tmp$ $ECHO before",
                "before",
                "=== exit 0 after <s> s",
                "=== $tmp$ $FALSE",
                "=== exit 1 after <s> s",
                "=== tool finished after <s> s",
            ),
            task.timedLog(),
        )
    }

    @Test
    fun `the task clears a stale log`() {
        task.logFile.parentFile.mkdirs()
        task.logFile.writeText("stale\n")
        task.action = { it.run(listOf(ECHO, "fresh"), tmp) }

        task.run()

        assertEquals(
            listOf(
                "=== tool started <time>",
                "=== $tmp$ $ECHO fresh",
                "fresh",
                "=== exit 0 after <s> s",
                "=== tool finished after <s> s",
            ),
            task.timedLog(),
        )
    }

    @Test
    fun `console is off without the property`() {
        assertFalse(task.console.get())
    }

    @Test
    fun `the console tee repeats the log on standard output`() {
        task.console.set(true)
        task.action = { it.run(listOf(ECHO, "shown"), tmp) }

        val printed = captureSystemOut { task.run() }

        assertEquals(task.logFile.readText(), printed)
    }

    @Test
    fun `without the console tee nothing reaches standard output`() {
        task.action = { it.run(listOf(ECHO, "hidden"), tmp) }

        val printed = captureSystemOut { task.run() }

        assertEquals("", printed)
        assertEquals(
            listOf(
                "=== tool started <time>",
                "=== $tmp$ $ECHO hidden",
                "hidden",
                "=== exit 0 after <s> s",
                "=== tool finished after <s> s",
            ),
            task.timedLog(),
        )
    }

    @Test
    fun `the task directory sits under the variant when one is set`() {
        val project = ProjectBuilder.builder().withProjectDir(tmp.resolve("layout")).build()
        val plain = project.tasks.register("plain", ToolTask::class.java).get()
        val perVariant = project.tasks.register("perVariant", ToolTask::class.java) {
            it.variantName.set("debug")
        }.get()

        assertEquals(project.layout.buildDirectory.dir("androidgraal/tool").get(), plain.taskDir.get())
        assertEquals(project.layout.buildDirectory.dir("androidgraal/debug/tool").get(), perVariant.taskDir.get())
    }

    @Test
    fun `the runner exists only while the task action runs`() {
        val failure = assertFailsWith<IllegalStateException> { task.execute() }

        assertTrue(task.path in failure.message.orEmpty(), failure.message)
    }

    private fun ToolTask.timedLog(): List<String> =
        logFile.readLines().map { it.replace(TIME, "<time>").replace(SECONDS, "after <s> s") }

    private fun captureSystemOut(block: () -> Unit): String {
        val original = System.out
        val captured = ByteArrayOutputStream()
        System.setOut(PrintStream(captured, true))
        try {
            block()
        } finally {
            System.setOut(original)
        }
        return captured.toString(Charsets.UTF_8)
    }
}

abstract class ToolTask : BaseTask("tool") {
    @get:Internal
    var action: (ProcessRunner) -> Unit = {}

    public override fun execute() = action(runner())
}

private const val ECHO = "/bin/echo"

private const val FALSE = "/usr/bin/false"

private val TIME = Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2}|Z)$")

private val SECONDS = Regex("after \\d+\\.\\d s$")
