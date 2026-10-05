package org.androidgraal.generator.scan

import com.google.testing.compile.CompilationSubject.assertThat
import org.androidgraal.generator.ErrorAt
import org.androidgraal.generator.GeneratorFixtures
import org.androidgraal.generator.assertErrors
import org.androidgraal.generator.scan.Annotations.PACKAGE
import org.apache.commons.io.FileUtils
import java.io.File
import javax.tools.Diagnostic
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class BridgeMarkerTest {

    private val tmp: File = createTempDirectory("bridge-marker").toFile()

    private val fixtures = GeneratorFixtures(tmp)

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `a contract without @Bridge fails telling what to write`() {
        val compilation = fixtures.compile("undeclared", CORE, marked = null).process()

        assertErrors(compilation, MISSING)
        assertEquals(listOf(Diagnostic.NOPOS), compilation.errors().map { it.lineNumber })
    }

    @Test
    fun `a contract carrying no annotation at all never runs the processor and gets no bridge`() {
        val plain =
            mapOf("fix/core/Plain.java" to "package fix.core;\n\npublic interface Plain {\n    void ping();\n}\n")
        val compiled = fixtures.compile("unannotated", plain, marked = null)

        val compilation = compiled.process()

        assertThat(compilation).succeeded()
        assertFalse(compiled.out.exists())
    }

    @Test
    fun `two packages with @Bridge fail on each`() {
        val compiled = fixtures.compile("twice", CORE + packageInfo("fix.core") + packageInfo("fix.other"))

        compiled.assertErrors(
            ErrorAt(
                "fix.core carries @Bridge: a contract has one",
                "fix/core/package-info.java",
                "package fix.core;",
            ),
            ErrorAt(
                "fix.other carries @Bridge: a contract has one",
                "fix/other/package-info.java",
                "package fix.other;",
            ),
        )
    }

    @Test
    fun `a contract with nothing @Art or @Svm fails`() {
        val compilation = fixtures.compile("plain", mapOf("fix/Plain.java" to "package fix;\n\nclass Plain {\n}\n"))
            .process()

        assertErrors(compilation, "nothing of the contract is @Art or @Svm")
    }

    private fun packageInfo(packageName: String): Pair<String, String> =
        packageName.replace('.', '/') + "/package-info.java" to "@$PACKAGE.Bridge\npackage $packageName;\n"

    private companion object {
        const val MISSING = "no package of the contract carries @Bridge"

        val CORE = mapOf(
            "fix/core/Service.java" to
                "package fix.core;\n\n@$PACKAGE.Root\n@$PACKAGE.Svm\npublic interface Service {\n    void ping();\n}\n",
        )
    }
}
