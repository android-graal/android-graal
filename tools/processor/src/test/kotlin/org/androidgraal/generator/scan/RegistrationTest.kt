package org.androidgraal.generator.scan

import org.androidgraal.generator.Compiled
import org.androidgraal.generator.GENERATED
import org.androidgraal.generator.Generated
import org.androidgraal.generator.GeneratorFixtures
import org.androidgraal.generator.NO_KIND
import org.androidgraal.generator.assertErrors
import org.androidgraal.generator.scan.Annotations.PACKAGE
import org.apache.commons.io.FileUtils
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegistrationTest {

    private val tmp: File = createTempDirectory("registration").toFile()

    private val fixtures = GeneratorFixtures(tmp)

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `a root in a parameter of an ART type reaches ART as the proxy create made`() {
        val generated = compile().generate()

        assertEquals(
            listOf(
                "root fix.core.Hello",
                "fix.core.Both implemented on ART",
                "fix.core.Both implemented on the image",
                "fix.core.Display implemented on ART",
                "fix.core.Gadget implemented on the image",
                "fix.core.Hello implemented on the image",
            ),
            generated.model.lines().filter { it.startsWith("root") || " implemented on " in it },
        )
        val endPoints = art(generated, "\$DisplayArt")
        assertTrue("Hello v0 = HANDLES.receive(h0, \$HelloSvm.PROXIES);" in endPoints, endPoints)
        assertTrue("receiver.attach(v0);" in endPoints, endPoints)
    }

    @Test
    fun `a type the image implements reaches ART as its proxy without being a root`() {
        val generated = compile().generate()

        assertTrue("$GENERATED/\$GadgetSvm.java" in generated.art && "$GENERATED/\$GadgetSvm.java" in generated.svm)
        assertFalse(generated.art.keys.any { "GadgetArt" in it } || generated.svm.keys.any { "GadgetArt" in it })
        val endPoints = art(generated, "\$DisplayArt")
        assertTrue("Gadget v0 = HANDLES.receive(h0, \$GadgetSvm.PROXIES);" in endPoints, endPoints)
        assertTrue("receiver.gadget(v0);" in endPoints, endPoints)
    }

    @Test
    fun `an ART type handed back to ART gets no image class and resolves to ART's own object`() {
        val generated = compile().generate()

        assertFalse(generated.art.keys.any { "DisplaySvm" in it } || generated.svm.keys.any { "DisplaySvm" in it })
        val endPoints = art(generated, "\$DisplayArt")
        assertTrue("Display v0 = HANDLES.<Display>receive(h0, null);" in endPoints, endPoints)
        assertTrue("receiver.same(v0)" in endPoints, endPoints)
        val proxy = generated.svm.getValue("fix/core/generated/\$DisplayArt.java")
        assertTrue("r, \$DisplayArt.PROXIES);" in proxy, proxy)
    }

    @Test
    fun `a type registered on both sides gets both classes, and each side receives the other's proxy`() {
        val generated = compile().generate()

        listOf(generated.art, generated.svm).forEach {
            assertTrue("$GENERATED/\$BothArt.java" in it && "$GENERATED/\$BothSvm.java" in it, "${it.keys}")
        }
        assertTrue("r, \$BothSvm.PROXIES);" in art(generated, "\$HelloSvm"))
        assertTrue("HANDLES.receive(h0, \$BothArt.PROXIES)" in generated.svm.getValue("$GENERATED/\$HelloSvm.java"))
    }

    @Test
    fun `a proxy method fences its receiver and every reference argument`() {
        val generated = compile().generate()

        val image = generated.svm.getValue("$GENERATED/\$DisplayArt.java")
        assertEquals(3, Regex("Reference\\.reachabilityFence\\(this\\);").findAll(image).count(), image)
        assertEquals(3, Regex("Reference\\.reachabilityFence\\(a0\\);").findAll(image).count(), image)
        val art = art(generated, "\$HelloSvm")
        assertEquals(2, Regex("Image\\.reachabilityFence\\(this\\);").findAll(art).count(), art)
        assertEquals(2, Regex("Image\\.reachabilityFence\\(a0\\);").findAll(art).count(), art)
    }

    @Test
    fun `an argument is fenced when only a subtype of its type has a proxy class on its side`() {
        val compiled = fixtures.compile(
            "subtype",
            mapOf(
                "fix/core/Hello.java" to """
                    package fix.core;

                    @$PACKAGE.Root
                    @$PACKAGE.Svm
                    public interface Hello {
                        Derived derived();

                        boolean take(Base base);
                    }
                """.trimIndent(),
                "fix/core/Base.java" to
                    "package fix.core;\n\n@$PACKAGE.Art\npublic interface Base {\n    void put(Top top);\n}\n",
                "fix/core/Derived.java" to
                    "package fix.core;\n\n@$PACKAGE.Svm\npublic interface Derived extends Base {}\n",
                "fix/core/Top.java" to
                    "package fix.core;\n\n@$PACKAGE.Svm\npublic interface Top {\n    int value();\n}\n",
                "fix/core/Bottom.java" to
                    "package fix.core;\n\n@$PACKAGE.Art\npublic interface Bottom extends Top {}\n",
            ),
        )
        val generated = compiled.generate()

        val art = art(generated, "\$HelloSvm")
        assertTrue("Image.reachabilityFence(a0);" in art, art)
        val image = generated.svm.getValue("$GENERATED/\$BaseArt.java")
        assertTrue("Reference.reachabilityFence(a0);" in image, image)
        fixtures.compileBridges(compiled, generated, "subtype")
    }

    @Test
    fun `a contract method named reachabilityFence crosses and its proxy fences through Image`() {
        val compiled = fixtures.compile(
            "fence",
            mapOf(
                "fix/core/Hello.java" to
                    "package fix.core;\n\n@$PACKAGE.Root\n@$PACKAGE.Svm\npublic interface Hello {\n" +
                    "    void reachabilityFence(Hello other);\n}\n",
            ),
        )
        val generated = compiled.generate()

        val art = art(generated, "\$HelloSvm")
        assertTrue("Image.reachabilityFence(this);" in art && "Image.reachabilityFence(a0);" in art, art)
        assertFalse(Regex("[^.]reachabilityFence\\((this|a0)\\)").containsMatchIn(art), art)
        fixtures.compileBridges(compiled, generated, "fence")
    }

    @Test
    fun `both bridges compile`() {
        val compiled = compile()
        val generated = compiled.generate()

        fixtures.compileBridges(compiled, generated, "registered")
    }

    @Test
    fun `an unannotated type fails together with the other errors`() {
        val compiled = compile(
            gadget = GADGET.replace("@$PACKAGE.Svm\n", ""),
            display = DISPLAY.replace("}\n", "    void fill(Object[] values);\n}\n"),
        )

        assertErrors(
            compiled.process(),
            "fix.core.Display.gadget(fix.core.Gadget).gadget crosses fix.core.Gadget by reference, which is not" +
                " bridged: annotate it @Art or @Svm",
            "fix.core.Display.fill(java.lang.Object[]).values cannot cross java.lang.Object[]$NO_KIND",
        )
    }

    @Test
    fun `a type annotated inside a contract type registers and generates`() {
        val compiled = fixtures.compile(
            "nested",
            mapOf(
                "fix/core/Hello.java" to """
                    package fix.core;

                    @$PACKAGE.Root
                    @$PACKAGE.Svm
                    public interface Hello {
                        Session open(Point at);

                        @$PACKAGE.Svm
                        interface Session {
                            void close();
                        }

                        record Point(int x, int y) {
                        }
                    }
                """.trimIndent(),
            ),
        )
        val generated = compiled.generate()

        assertEquals(
            listOf(
                "fix.core.Hello implemented on the image",
                "fix.core.Hello.Session implemented on the image",
                "record fix.core.Hello.Point: x int, y int",
            ),
            generated.model.lines().filter { " implemented on " in it || it.startsWith("record") },
        )
        listOf(generated.art, generated.svm).forEach { assertTrue("$GENERATED/\$SessionSvm.java" in it, "${it.keys}") }
        fixtures.compileBridges(compiled, generated, "nested")
    }

    private fun art(generated: Generated, name: String): String = generated.art.getValue("$GENERATED/$name.java")

    private fun compile(gadget: String = GADGET, display: String = DISPLAY): Compiled = fixtures.compile(
        "registered",
        mapOf(
            "fix/core/Hello.java" to "package fix.core;\n\n@$PACKAGE.Root\n@$PACKAGE.Svm\npublic interface Hello {\n" +
                "    void show(Display display);\n    Both swap(Both both);\n}\n",
            "fix/core/Display.java" to display,
            "fix/core/Gadget.java" to gadget,
            "fix/core/Both.java" to
                "package fix.core;\n\n@$PACKAGE.Art\n@$PACKAGE.Svm\npublic interface Both {\n    int value();\n}\n",
        ),
    )

    private companion object {
        const val GADGET = "package fix.core;\n\n@$PACKAGE.Svm\npublic interface Gadget {\n    int value();\n}\n"

        const val DISPLAY = "package fix.core;\n\n@$PACKAGE.Art\npublic interface Display {\n" +
            "    void attach(Hello hello);\n    Display same(Display display);\n    void gadget(Gadget gadget);\n}\n"
    }
}
