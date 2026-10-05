package org.androidgraal.generator.scan

import org.androidgraal.generator.Compiled
import org.androidgraal.generator.ErrorAt
import org.androidgraal.generator.GENERATED
import org.androidgraal.generator.GeneratorFixtures
import org.androidgraal.generator.SERVICE
import org.androidgraal.generator.assertErrors
import org.androidgraal.generator.scan.Annotations.ART
import org.androidgraal.generator.scan.Annotations.IN
import org.androidgraal.generator.scan.Annotations.OUT
import org.androidgraal.generator.scan.Annotations.ROOT
import org.androidgraal.generator.scan.Annotations.STATIC_BUFFER
import org.androidgraal.generator.scan.Annotations.SVM
import org.apache.commons.io.FileUtils
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DirectedArrayTest {

    private val tmp: File = createTempDirectory("directed-array").toFile()

    private val fixtures = GeneratorFixtures(tmp)

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `an array parameter without a direction fails`() {
        compile(peer = "    void fill(float[] v);\n").assertErrors(
            ErrorAt(
                "fix.core.Peer.fill(float[]).v crosses float[] without a direction, annotate it @In or @Out",
                "fix/core/Peer.java",
                "fill(",
            ),
        )
    }

    @Test
    fun `strings and array results cross fresh or static on either side, and both bridges compile`() {
        val shapes = "    String name(String s, @$IN int[] values);\n" +
            "    int[] ints(int count);\n" +
            "    boolean[] flags(int count);\n" +
            "    int count(@$STATIC_BUFFER(capacity = 32) String s);\n" +
            "    @$STATIC_BUFFER(capacity = 32)\n    String label(int value);\n" +
            "    @$STATIC_BUFFER(capacity = 8)\n    double[] values();\n"
        val generated = compile(service = shapes, peer = shapes).generate()

        val lines = listOf(
            "  count count(java.lang.String): (String static 32) -> primitive int",
            "  flags flags(int): (primitive) -> array boolean[]",
            "  ints ints(int): (primitive) -> array int[]",
            "  label label(int): (primitive) -> String static 32",
            "  name name(java.lang.String, int[]): (String, array in) -> String",
            "  values values(): () -> array double[] static 8",
        )
        assertEquals(
            lines + lines,
            generated.model.lines().filter { it.startsWith("  ") && "attach" !in it },
            generated.model,
        )
        val artProxy = source(generated.art, "ServiceSvm")
        val imageProxy = source(generated.svm, "PeerArt")
        assertTrue("private static native long name(long thread, long handle, ByteBuffer transport);" in artProxy)
        assertTrue("private static native void values(long thread, long handle, long block);" in artProxy)
        assertTrue("\"(JJIJ)V\"" !in imageProxy && "\"(JJI)Ljava/nio/ByteBuffer;\"" in imageProxy, imageProxy)
        assertTrue("\"(J)Ljava/nio/ByteBuffer;\"" !in imageProxy && "\"(JI)Ljava/nio/ByteBuffer;\"" in imageProxy)
        assertTrue("\"(JJ)V\"" in imageProxy && "\"(JJ)I\"" in imageProxy, imageProxy)
        fixtures.compileBridges(last!!, generated, "directed")
    }

    @Test
    fun `a directed array crosses one way, on either side`() {
        val model = compile(
            service = "    void fill(@$IN float[] values);\n",
            peer = "    void fill(@$OUT int[] values, int count);\n",
        ).generate().model

        assertEquals(
            listOf(
                "  fill fill(int[], int): (array out, primitive) -> void",
                "  fill fill(float[]): (array in) -> void",
            ),
            model.lines().filter { it.startsWith("  ") && "fill" in it },
            model,
        )
    }

    @Test
    fun `a directed array and a length cross on either side, and both bridges compile`() {
        val generated = compile(
            service = "    int total(@$IN int[] values, int count);\n",
            peer = "    void squares(@$OUT int[] values, int n);\n",
        ).generate()

        assertEquals(
            listOf(
                "  squares squares(int[], int): (array out, primitive) -> void",
                "  total total(int[], int): (array in, primitive) -> primitive int",
            ),
            generated.model.lines().filter { it.startsWith("  ") && ("squares" in it || "total" in it) },
            generated.model,
        )
        fixtures.compileBridges(last!!, generated, "directed")
    }

    @Test
    fun `a static buffer has a capacity, on either side, and both bridges compile`() {
        val generated = compile(
            service = "    float sum(@$IN @$STATIC_BUFFER(capacity = 16) float[] values);\n" +
                "    void fill(@$OUT @$STATIC_BUFFER(capacity = 8) int[] values);\n",
            peer = "    void fill(@$OUT @$STATIC_BUFFER(capacity = 8) int[] values);\n" +
                "    float sum(@$IN @$STATIC_BUFFER(capacity = 4) float[] values);\n",
        ).generate()

        assertEquals(
            listOf(
                "  fill fill(int[]): (array out static 8) -> void",
                "  sum sum(float[]): (array in static 4) -> primitive float",
                "  fill fill(int[]): (array out static 8) -> void",
                "  sum sum(float[]): (array in static 16) -> primitive float",
            ),
            generated.model.lines().filter { it.startsWith("  ") && ("fill" in it || "sum" in it) },
            generated.model,
        )
        fixtures.compileBridges(last!!, generated, "directed")
    }

    @Test
    fun `a static buffer crosses as its block's address, fresh arrays as one transport of blocks`() {
        val generated = compile(
            service = "    float mix(@$IN @$STATIC_BUFFER(capacity = 4) float[] hot, @$IN byte[] bytes, int count," +
                " @$OUT long[] longs, @$IN boolean[] flags);\n",
            peer = "    void mix(@$OUT boolean[] flags, @$IN @$STATIC_BUFFER(capacity = 2) double[] hot," +
                " @$IN char[] chars);\n",
        ).generate()
        val artProxy = source(generated.art, "ServiceSvm")
        val entryPoints = source(generated.svm, "ServiceSvm")
        val imageProxy = source(generated.svm, "PeerArt")
        val endPoints = source(generated.art, "PeerArt")

        val wire = "long handle, ByteBuffer transport, long a0, int a2)"
        assertTrue("private static native float mix(long thread, $wire;" in artProxy, artProxy)
        assertTrue("int n1 = a1 == null ? Blocks.NULL : a1.length;" in artProxy, artProxy)
        assertTrue("int o3 = Blocks.size(n1, 1);" in artProxy, artProxy)
        assertTrue("int o4 = o3 + Blocks.size(n3, 8);" in artProxy, artProxy)
        assertTrue("Transport.setBlockHeader(transport, o4, n4, 1);" in artProxy, artProxy)
        assertTrue("long handle, JNI.JObject transport, long a0, int a2)" in entryPoints, entryPoints)
        assertTrue(
            "int n1 = address == 0 ? Blocks.NULL : Transport.getBlockLength(address);" in entryPoints,
            entryPoints,
        )
        assertTrue("int o3 = Blocks.size(n1, 1);" in entryPoints, entryPoints)
        assertTrue("\"(JJIJ)V\"" in imageProxy, imageProxy)
        assertTrue("private static void mix(long handle, long transport, int bytes, long a1)" in endPoints, endPoints)
        fixtures.compileBridges(last!!, generated, "directed")
    }

    @Test
    fun `a static buffer on anything but a directed array, a String or such a result, or of no capacity, fails`() {
        compile(
            service = "    void count(@$STATIC_BUFFER(capacity = 4) int n);\n" +
                "    void empty(@$IN @$STATIC_BUFFER(capacity = 0) int[] values);\n" +
                "    void plain(@$STATIC_BUFFER(capacity = 4) int[] values);\n" +
                "    void text(@$STATIC_BUFFER(capacity = 0) String s);\n" +
                "    @$STATIC_BUFFER(capacity = 4)\n    int size();\n" +
                "    @$STATIC_BUFFER(capacity = -1)\n    String none();\n",
        ).assertErrors(
            ErrorAt(
                "fix.core.Service.count(int).n has @StaticBuffer, only a directed array or a String has a static" +
                    " buffer",
                SERVICE,
                "count(",
            ),
            ErrorAt(
                "fix.core.Service.empty(int[]).values has @StaticBuffer(capacity = 0), a static buffer holds at least" +
                    " one element",
                SERVICE,
                "empty(",
            ),
            ErrorAt(
                "fix.core.Service.plain(int[]).values crosses int[] without a direction, annotate it @In or @Out",
                SERVICE,
                "plain(",
            ),
            ErrorAt(
                "fix.core.Service.text(java.lang.String).s has @StaticBuffer(capacity = 0), a static buffer holds at" +
                    " least one element",
                SERVICE,
                "text(",
            ),
            ErrorAt(
                "fix.core.Service.size() has @StaticBuffer, only a directed array or a String has a static buffer",
                SERVICE,
                "size()",
            ),
            ErrorAt(
                "fix.core.Service.none() has @StaticBuffer(capacity = -1), a static buffer holds at least one element",
                SERVICE,
                "none()",
            ),
        )
    }

    @Test
    fun `a direction on a parameter that is no primitive array fails, and so do both directions`() {
        compile(
            service = "    void name(@$IN String s);\n    void both(@$IN @$OUT int[] values);\n" +
                "    void mode(@$IN int n);\n",
        ).assertErrors(
            ErrorAt(
                "fix.core.Service.name(java.lang.String).s has a direction, only a primitive array has one",
                SERVICE,
                "name(",
            ),
            ErrorAt(
                "fix.core.Service.both(int[]).values is both @In and @Out, a directed array crosses one way",
                SERVICE,
                "both(",
            ),
            ErrorAt("fix.core.Service.mode(int).n has a direction, only a primitive array has one", SERVICE, "mode("),
        )
    }

    private var last: Compiled? = null

    private fun source(sources: Map<String, String>, name: String): String =
        sources.getValue("$GENERATED/\$$name.java").replace(Regex("\\s+"), " ")

    private fun compile(service: String = "", peer: String = ""): Compiled {
        val withPeer = peer.isNotEmpty()
        val sources = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n@$ROOT\n@$SVM\npublic interface Service {\n$service" +
                (if (withPeer) "    void attach(Peer p);\n" else "") + "}\n",
        ) + if (withPeer) {
            mapOf("fix/core/Peer.java" to "package fix.core;\n\n@$ART\npublic interface Peer {\n$peer}\n")
        } else {
            emptyMap()
        }
        return fixtures.compile("directed", sources).also { last = it }
    }
}
