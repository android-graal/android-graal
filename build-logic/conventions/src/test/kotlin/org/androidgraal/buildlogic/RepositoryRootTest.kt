package org.androidgraal.buildlogic

import org.gradle.api.GradleException
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RepositoryRootTest {

    private val tmp: File = createTempDirectory("repository-root-test").toFile()

    @AfterTest
    fun cleanUp() {
        tmp.deleteRecursively()
    }

    @Test
    fun `the catalog marks the root above a nested directory`() {
        val root = File(tmp, "repo")
        File(root, "gradle").mkdirs()
        File(root, "gradle/libs.versions.toml").writeText("")
        val nested = File(root, "samples/hello").apply { mkdirs() }

        assertEquals(root, nested.repositoryRoot())
        assertEquals(root, root.repositoryRoot())
    }

    @Test
    fun `no catalog above fails`() {
        val nested = File(tmp, "a/b").apply { mkdirs() }

        val failure = assertFailsWith<GradleException> { nested.repositoryRoot() }
        assertEquals("no gradle/libs.versions.toml above $nested", failure.message)
    }
}
