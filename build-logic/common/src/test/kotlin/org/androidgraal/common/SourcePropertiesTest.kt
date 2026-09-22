package org.androidgraal.common

import org.apache.commons.io.FileUtils
import org.semver4j.Semver
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class SourcePropertiesTest {

    private val tmp: File = createTempDirectory("source-properties-test").toFile()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `a version is read as one`() {
        val file = sourceProperties("cmake", "Pkg.Revision = 3.31.6\n")

        assertEquals(Semver.parse("3.31.6"), SourceProperties.revisionOrNull(file))
        assertEquals(Semver.parse("3.31.6"), SourceProperties.revision(file))
    }

    @Test
    fun `a four component revision is null and fails the strict read`() {
        val file = sourceProperties("legacy", "Pkg.Revision = 3.10.2.4988404\n")

        assertNull(SourceProperties.revisionOrNull(file))

        val failure = assertFailsWith<IllegalStateException> { SourceProperties.revision(file) }
        assertEquals(
            "Pkg.Revision \"3.10.2.4988404\" in $file is not a version (major.minor.patch[-prerelease])",
            failure.message,
        )
    }

    @Test
    fun `a missing revision fails`() {
        val file = sourceProperties("keyless", "Pkg.Path = cmake\n")

        val failure = assertFailsWith<IllegalStateException> { SourceProperties.revision(file) }
        assertEquals("no Pkg.Revision in $file", failure.message)
    }

    private fun sourceProperties(name: String, text: String): File {
        val dir = tmp.resolve(name)
        dir.mkdirs()
        val file = dir.resolve(SourceProperties.FILE_NAME)
        file.writeText(text)
        return file
    }
}
