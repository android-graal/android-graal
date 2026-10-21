package org.androidgraal.substrate

import com.android.tools.r8.retrace.Retrace
import org.apache.commons.io.FileUtils
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class ObfuscationMappingTest {

    private val tmp: File = createTempDirectory("obfuscation-mapping-test").toFile()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `merge keeps the app's header with the joined bodies' hash, then the app's body, then the image's`() {
        val app = mapping("app", "d.E -> f:\n    int x -> a\n")
        val image = mapping("image", "a.B -> c:\n# {\"id\":\"sourceFile\",\"fileName\":\"B.java\"}\n")
        val body = "d.E -> f:\n    int x -> a\na.B -> c:\n# {\"id\":\"sourceFile\",\"fileName\":\"B.java\"}"

        assertEquals(
            header("app", ObfuscationMapping.sha256(body)) + body,
            ObfuscationMapping.merge(app, image),
        )
    }

    @Test
    fun `R8's retrace verifies the hash of each part and of the merge, not the app's hash over both bodies`() {
        val app = tmp.resolve("app.txt").apply {
            writeText(mapping("app", "d.E -> f:\n    1:1:void run():10:10 -> a\n"))
        }
        val image = tmp.resolve("image.txt").apply { writeText(mapping("image", "a.B -> c:\n")) }
        val merged = tmp.resolve("merged.txt").apply {
            writeText(ObfuscationMapping.merge(app.readText(), image.readText()))
        }
        val concatenated = tmp.resolve("concatenated.txt").apply {
            writeText(app.readText() + ObfuscationMapping(image.readText()).body)
        }

        listOf(app, image, merged).forEach { Retrace.run(arrayOf("--verify-mapping-file-hash", it.path)) }
        assertFails { Retrace.run(arrayOf("--verify-mapping-file-hash", concatenated.path)) }
    }

    private fun mapping(id: String, body: String): String = header(id, ObfuscationMapping.sha256(body)) + body

    private fun header(id: String, hash: String): String {
        return "# compiler: R8\n" +
            "# compiler_version: 9.1.31\n" +
            "# {\"id\":\"com.android.tools.r8.mapping\",\"version\":\"2.2\"}\n" +
            "# pg_map_id: $id\n" +
            ObfuscationMapping.MAP_HASH + hash + "\n"
    }
}
