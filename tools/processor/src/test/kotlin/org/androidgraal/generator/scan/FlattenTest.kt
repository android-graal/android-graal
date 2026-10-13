package org.androidgraal.generator.scan

import org.androidgraal.generator.Compiled
import org.androidgraal.generator.ErrorAt
import org.androidgraal.generator.GeneratorFixtures
import org.androidgraal.generator.SERVICE
import org.androidgraal.generator.assertErrors
import org.androidgraal.generator.scan.Annotations.ART
import org.androidgraal.generator.scan.Annotations.IN
import org.androidgraal.generator.scan.Annotations.ROOT
import org.androidgraal.generator.scan.Annotations.SVM
import org.apache.commons.io.FileUtils
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FlattenTest {

    private val tmp: File = createTempDirectory("flatten").toFile()

    private val fixtures = GeneratorFixtures(tmp)

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `a flattened record crosses on either side, its records and enums join the model, and both bridges compile`() {
        val compiled = compile(
            "    void draw(Shape shape);\n",
            "    void draw(Shape shape, @$IN int[] values);\n",
            mapOf(
                "Shape" to "public record Shape(int x, Kind kind, String name, Inner inner) {\n}\n",
                "Inner" to
                    "public record Inner(long a, char c, boolean b, byte y, short z, double d, Deep deep) {\n}\n",
                "Deep" to "public record Deep(int q) {\n}\n",
                "Kind" to "public enum Kind { ROUND, SQUARE }\n",
            ),
        )
        val generated = compiled.generate()

        assertEquals(
            listOf(
                "  draw draw(fix.core.Shape, int[]): (record, array in) -> void",
                "  draw draw(fix.core.Shape): (record) -> void",
                "enum fix.core.Kind as ordinal of ROUND, SQUARE",
                "record fix.core.Deep: q int",
                "record fix.core.Inner: a long, c char, b boolean, y byte, z short, d double, deep fix.core.Deep",
                "record fix.core.Shape: x int, kind fix.core.Kind, name java.lang.String, inner fix.core.Inner",
            ),
            generated.model.lines().filter { "draw" in it || it.startsWith("enum") || it.startsWith("record") },
            generated.model,
        )
        val endPoint = generated.art.getValue("fix/core/generated/\$PeerArt.java")
        assertTrue("Shape v0 = p0 ? new Shape(a0_x, a0_kind < 0 ? null : KIND_VALUES[a0_kind]," in endPoint, endPoint)
        assertTrue("Deep v0_inner_deep = p0_inner_deep ? new Deep(a0_inner_deep_q) : null;" in endPoint, endPoint)
        fixtures.compileBridges(compiled, generated, "flatten")
    }

    @Test
    fun `a record result fails`() {
        compile("    Point made();\n", "", mapOf("Point" to "public record Point(int x) {\n}\n")).assertErrors(
            ErrorAt(
                "fix.core.Service.made() returns fix.core.Point, a record crosses as a parameter only",
                SERVICE,
                "made()",
            ),
        )
    }

    @Test
    fun `a component a flattened record cannot hold fails naming the record, the component and its type`() {
        val box = listOf(
            "fix.core.Box.values is int[], a record crossing by value holds primitives, String, enums and records" +
                " only",
            "fix.core.Box.service is fix.core.Service, a record crossing by value holds primitives, String, enums" +
                " and records only",
            "fix.core.Box.text is java.lang.StringBuilder, a record crossing by value holds primitives, String," +
                " enums and records only",
            "fix.core.Box.names is java.lang.String[], a record crossing by value holds primitives, String, enums" +
                " and records only",
        ).map { ErrorAt(it, "fix/core/Box.java", "record Box(") }
        compile(
            "    void take(Box box);\n    void pair(Odd odd);\n",
            "",
            mapOf(
                "Box" to
                    "public record Box(int[] values, Service service, StringBuilder text, String[] names, Box self) {\n}\n",
                "Odd" to "public record Odd(Even even) {\n}\n",
                "Even" to "public record Even(Odd odd) {\n}\n",
            ),
        ).assertErrors(
            *box.toTypedArray(),
            ErrorAt(
                "fix.core.Box.self is fix.core.Box, a record cannot contain itself",
                "fix/core/Box.java",
                "record Box(",
            ),
            ErrorAt(
                "fix.core.Even.odd is fix.core.Odd, a record cannot contain itself",
                "fix/core/Even.java",
                "record Even(",
            ),
        )
    }

    private fun compile(service: String, peer: String, types: Map<String, String>): Compiled {
        val withPeer = peer.isNotEmpty()
        val sources = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n@$ROOT\n@$SVM\npublic interface Service {\n$service" +
                (if (withPeer) "    void attach(Peer p);\n" else "") + "}\n",
        )
        val peers = if (withPeer) {
            mapOf("fix/core/Peer.java" to "package fix.core;\n\n@$ART\npublic interface Peer {\n$peer}\n")
        } else {
            emptyMap()
        }
        return fixtures.compile(
            "flatten",
            sources + peers + types.map { (name, body) -> "fix/core/$name.java" to "package fix.core;\n\n$body" },
        )
    }
}
