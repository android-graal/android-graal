package org.androidgraal.gradle

import org.androidgraal.substrate.ProcessRunner
import org.apache.commons.io.FileUtils
import org.gradle.api.tasks.Internal
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class BaseTaskTest {

    private val tmp: File = createTempDirectory("base-task-test").toFile()

    private val task: ToolTask = ProjectBuilder.builder().withProjectDir(tmp).build()
        .tasks.register("tool", ToolTask::class.java).get()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `the log holds the command header and the output`() {
        var exit = -1
        task.action = { exit = it.run(listOf(ECHO, "hello"), tmp) }

        task.run()

        assertEquals(0, exit)
        assertEquals(tmp.canonicalFile.resolve("build/tool.log"), task.logFile.canonicalFile)
        assertEquals(listOf("=== $tmp$ $ECHO hello", "hello"), task.logFile.readLines())
    }

    @Test
    fun `every process of one execution lands in one log`() {
        task.action = {
            it.run(listOf(ECHO, "one"), tmp)
            it.run(listOf(ECHO, "two"), tmp)
        }

        task.run()

        assertEquals(listOf("=== $tmp$ $ECHO one", "one", "=== $tmp$ $ECHO two", "two"), task.logFile.readLines())
    }

    @Test
    fun `standard output and standard error both land in the log`() {
        val existing = tmp.resolve("present.txt").apply { writeText("") }
        val missing = tmp.resolve("missing.txt")
        var exit = -1
        task.action = { exit = it.run(listOf(LS, existing.path, missing.path), tmp) }

        task.run()

        assertTrue(exit != 0, "ls must fail on $missing")
        val lines = task.logFile.readLines()
        assertEquals("=== $tmp$ $LS $existing $missing", lines.first())
        assertTrue(existing.path in lines, "$lines")
        assertTrue(lines.any { missing.path in it && it.startsWith("ls:") }, "$lines")
    }

    @Test
    fun `a failing process throws and the log survives`() {
        task.action = {
            it.run(listOf(ECHO, "before"), tmp)
            it.runOrFail("false", listOf(FALSE), tmp)
        }

        val failure = assertFailsWith<IllegalStateException> { task.run() }

        assertEquals("false failed with exit code 1", failure.message)
        assertEquals(listOf("=== $tmp$ $ECHO before", "before", "=== $tmp$ $FALSE"), task.logFile.readLines())
    }

    @Test
    fun `the task clears a stale log`() {
        task.logFile.parentFile.mkdirs()
        task.logFile.writeText("stale\n")
        task.action = { it.run(listOf(ECHO, "fresh"), tmp) }

        task.run()

        assertEquals(listOf("=== $tmp$ $ECHO fresh", "fresh"), task.logFile.readLines())
    }
}

abstract class ToolTask : BaseTask() {
    @get:Internal
    var action: (ProcessRunner) -> Unit = {}

    override fun execute() = action(runner())
}

private const val ECHO = "/bin/echo"

private const val FALSE = "/usr/bin/false"

private const val LS = "/bin/ls"
