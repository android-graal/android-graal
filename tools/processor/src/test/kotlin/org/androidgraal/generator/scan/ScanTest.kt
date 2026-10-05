package org.androidgraal.generator.scan

import com.google.testing.compile.CompilationSubject.assertThat
import org.androidgraal.generator.ErrorAt
import org.androidgraal.generator.GENERATED
import org.androidgraal.generator.GeneratorFixtures
import org.androidgraal.generator.NO_KIND
import org.androidgraal.generator.SERVICE
import org.androidgraal.generator.assertErrors
import org.androidgraal.generator.scan.Annotations.PACKAGE
import org.androidgraal.shared.Foreign
import org.apache.commons.io.FileUtils
import java.io.File
import java.lang.reflect.Modifier
import java.security.MessageDigest
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import javax.tools.Diagnostic
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class ScanTest {

    private val tmp: File = createTempDirectory("bridge-generator").toFile()

    private val fixtures = GeneratorFixtures(tmp)

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `the sample's model has its registrations`() {
        val model = fixtures.set("bridges").generate().model.lines()

        assertEquals(
            listOf(
                "org.graalvm.android.bridges.core.Allocations implemented on ART",
                "org.graalvm.android.bridges.core.Display implemented on ART",
                "org.graalvm.android.bridges.core.Display implemented on the image",
                "org.graalvm.android.bridges.core.Frame implemented on the image",
                "org.graalvm.android.bridges.core.Hello implemented on the image",
                "org.graalvm.android.bridges.core.Listener implemented on the image",
                "org.graalvm.android.bridges.core.Probe implemented on ART",
                "org.graalvm.android.bridges.core.Registry implemented on ART",
            ),
            model.filter { " implemented on " in it },
        )
    }

    @Test
    fun `the output directory is cleared before the bridges are written`() {
        val compiled = fixtures.compile("cleared", CORE)
        val stray = compiled.out.resolve("art/java/Stray.java").apply { parentFile.mkdirs() }
        stray.writeText("class Stray {\n}\n")

        compiled.generate()

        assertFalse(stray.exists())
        assertTrue(compiled.out.resolve("art/java/fix/core/generated/Bridge.java").isFile)
    }

    @Test
    fun `a failing compilation writes no bridge`() {
        val compiled = fixtures.compile(
            "failing",
            mapOf(
                "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                    "    void fill(Object[] values);\n}\n",
            ),
        )

        assertThat(compiled.process()).failed()
        assertFalse(compiled.out.exists())
    }

    @Test
    fun `a throws clause naming a checked throwable fails naming the method, collected with the other errors`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    void read() throws java.io.IOException;\n    void attach(\n        Peer peer);\n}\n",
            "fix/core/Peer.java" to "package fix.core;\n\npublic interface Peer {\n}\n",
        )

        fixtures.compile("throws", core).assertErrors(
            ErrorAt(
                "fix.core.Service.read() declares throws java.io.IOException, which is checked and cannot cross:" +
                    " wrap it in an unchecked exception. The cause arrives as a MarshalledException naming it",
                SERVICE,
                "void read(",
            ),
            ErrorAt(
                "fix.core.Service.attach(fix.core.Peer).peer crosses fix.core.Peer by reference, which is not" +
                    " bridged: annotate it @Art or @Svm",
                SERVICE,
                "Peer peer)",
            ),
        )
    }

    @Test
    fun `a throws Exception or Throwable clause fails`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    void any() throws Exception;\n    void all() throws Throwable;\n}\n",
        )

        assertErrors(
            fixtures.compile("throws-any", core).process(),
            "fix.core.Service.any() declares throws java.lang.Exception, which is checked and cannot cross: wrap it" +
                " in an unchecked exception. The cause arrives as a MarshalledException naming it",
            "fix.core.Service.all() declares throws java.lang.Throwable, which is checked and cannot cross: wrap it" +
                " in an unchecked exception. The cause arrives as a MarshalledException naming it",
        )
    }

    @Test
    fun `an unchecked throwable in a throws clause crosses`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    void ping() throws Odd, RuntimeException;\n}\n",
        )

        val compiled = fixtures.compile("unchecked-throws", core + ODD)

        assertTrue("fix.core.Service implemented on the image" in compiled.generate().model.lines())
    }

    @Test
    fun `a redeclared method of Object crosses, an inherited one does not`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    String toString();\n\n    int hashCode();\n}\n",
        )

        val compiled = fixtures.compile("object-methods", core)
        val generated = compiled.generate()

        assertEquals(
            listOf("  hashCode hashCode(): () -> primitive int", "  toString toString(): () -> String"),
            generated.model.lines().filter { it.startsWith("  ") },
        )
        fixtures.compileBridges(compiled, generated, "object-methods")
    }

    @Test
    fun `a generic method fails`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    <T extends Gift> void f(T t);\n}\n",
            "fix/core/Gift.java" to "package fix.core;\n\n@$PACKAGE.Svm\npublic interface Gift {\n}\n",
        )

        fixtures.compile("generic-method", core).assertErrors(
            ErrorAt("fix.core.Service.f(T).t cannot cross T$NO_KIND", SERVICE, "void f("),
        )
    }

    @Test
    fun `inherited methods with one signature cross once`() {
        val core = mapOf(
            "fix/core/Service.java" to
                "package fix.core;\n\n$ROOT\npublic interface Service extends Left, Right {\n}\n",
            "fix/core/Left.java" to "package fix.core;\n\npublic interface Left {\n    void h(Gift g);\n}\n",
            "fix/core/Right.java" to "package fix.core;\n\npublic interface Right {\n    void h(Gift g);\n}\n",
            "fix/core/Gift.java" to "package fix.core;\n\n@$PACKAGE.Svm\npublic interface Gift {\n}\n",
        )

        val lines = fixtures.compile("diamond", core).generate().model.lines()

        assertEquals(1, lines.count { it.startsWith("  h h(") }, lines.joinToString("\n"))
    }

    @Test
    fun `a covariant return crosses with the specific type`() {
        val core = mapOf(
            "fix/core/Service.java" to
                "package fix.core;\n\n$ROOT\npublic interface Service extends Left, Right {\n}\n",
            "fix/core/Left.java" to "package fix.core;\n\npublic interface Left {\n    Base get();\n}\n",
            "fix/core/Right.java" to "package fix.core;\n\npublic interface Right {\n    Peer get();\n}\n",
            "fix/core/Base.java" to "package fix.core;\n\n@$PACKAGE.Art\npublic interface Base {\n}\n",
            "fix/core/Peer.java" to "package fix.core;\n\n@$PACKAGE.Art\npublic interface Peer extends Base {\n}\n",
        )

        val compiled = fixtures.compile("covariant", core)
        val generated = compiled.generate()

        assertEquals(1, generated.model.lines().count { it.startsWith("  get get(") }, generated.model)
        val proxy = generated.art.getValue("fix/core/generated/\$ServiceSvm.java")
        assertTrue("public Peer get() {" in proxy, proxy)
        fixtures.compileBridges(compiled, generated, "covariant")
    }

    @Test
    fun `a generic crossing interface whose type variable reaches no position crosses`() {
        val core = mapOf(
            "fix/core/Service.java" to
                "package fix.core;\n\n$ROOT\npublic interface Service<T> {\n    void ping();\n}\n",
        )

        val compiled = fixtures.compile("generic", core)
        val generated = compiled.generate()

        assertEquals(listOf("  ping ping(): () -> void"), generated.model.lines().filter { it.startsWith("  ") })
        fixtures.compileBridges(compiled, generated, "generic", listOf("-Xlint:-rawtypes"))
    }

    @Test
    fun `a parameterized type in a position fails`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    void put(Peer<String> peer);\n}\n",
            "fix/core/Peer.java" to
                "package fix.core;\n\n@$PACKAGE.Art\npublic interface Peer<T> {\n    String name();\n}\n",
        )

        fixtures.compile("parameterized", core).assertErrors(
            ErrorAt(
                "fix.core.Service.put(fix.core.Peer<java.lang.String>).peer cannot cross" +
                    " fix.core.Peer<java.lang.String>$NO_KIND",
                SERVICE,
                "void put(",
            ),
        )
    }

    @Test
    fun `a crossing interface over a generic superinterface fails`() {
        val core = mapOf(
            "fix/core/Screen.java" to
                "package fix.core;\n\n$ROOT\npublic interface Screen extends Box<Monitor> {\n}\n",
            "fix/core/Box.java" to
                "package fix.core;\n\npublic interface Box<T extends Display> {\n    void set(T t);\n}\n",
            "fix/core/Display.java" to "package fix.core;\n\n@$PACKAGE.Art\npublic interface Display {\n}\n",
            "fix/core/Monitor.java" to
                "package fix.core;\n\n@$PACKAGE.Art\npublic interface Monitor extends Display {\n}\n",
        )

        fixtures.compile("generic-super", core).assertErrors(
            ErrorAt("fix.core.Box.set(T).t cannot cross T$NO_KIND", "fix/core/Box.java", "void set("),
        )
    }

    @Test
    fun `a generic record fails`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    void put(Pair<String> pair);\n    void raw(Pair pair);\n}\n",
            "fix/core/Pair.java" to "package fix.core;\n\npublic record Pair<A>(A a) {\n}\n",
        )

        fixtures.compile("generic-record", core).assertErrors(
            ErrorAt(
                "fix.core.Service.put(fix.core.Pair<java.lang.String>).pair cannot cross" +
                    " fix.core.Pair<java.lang.String>$NO_KIND",
                SERVICE,
                "void put(",
            ),
            ErrorAt(
                "fix.core.Pair.a is A, a record crossing by value holds primitives, String, enums and records only",
                "fix/core/Pair.java",
                "record Pair<",
            ),
        )
    }

    @Test
    fun `annotations that cannot hold fail, collected`() {
        val core = mapOf(
            "fix/core/Service.java" to
                "package fix.core;\n\n@$PACKAGE.Root\npublic interface Service {\n    void ping();\n}\n",
            "fix/core/Mixed.java" to
                "package fix.core;\n\n@$PACKAGE.Root\n@$PACKAGE.Art\npublic interface Mixed {\n    void ping();\n}\n",
            "fix/core/Stamp.java" to
                "package fix.core;\n\n@$PACKAGE.Root\n@$PACKAGE.Art\n@$PACKAGE.Svm\npublic abstract class Stamp {\n}\n",
        )

        assertErrors(
            fixtures.compile("misplaced", core).process(),
            "fix.core.Stamp is @Art, but only an interface crosses by reference",
            "fix.core.Stamp is @Svm, but only an interface crosses by reference",
            "fix.core.Service is @Root without @Svm: a root is implemented in the image, add @Svm",
            "fix.core.Mixed is @Root without @Svm: a root is implemented in the image, add @Svm",
        )
    }

    @Test
    fun `a root alone fails as a root without @Svm, then the summary`() {
        val core = mapOf(
            "fix/core/Only.java" to "package fix.core;\n\n@$PACKAGE.Root\npublic interface Only {\n}\n",
        )

        assertErrors(
            fixtures.compile("root-alone", core).process(),
            "fix.core.Only is @Root without @Svm: a root is implemented in the image, add @Svm",
            "nothing of the contract is @Art or @Svm",
        )
    }

    @Test
    fun `a root may be implemented on both sides`() {
        val generated = fixtures.compile(
            "both",
            mapOf(
                "fix/core/Service.java" to
                    "package fix.core;\n\n$ROOT\n@$PACKAGE.Art\npublic interface Service {\n    void ping();\n}\n",
            ),
        ).generate()

        assertEquals(
            listOf(
                "root fix.core.Service",
                "fix.core.Service implemented on ART",
                "fix.core.Service implemented on the image",
            ),
            generated.model.lines().filter { it.startsWith("root") || " implemented on " in it },
        )
        listOf(generated.art, generated.svm).forEach {
            assertTrue("$GENERATED/\$ServiceArt.java" in it && "$GENERATED/\$ServiceSvm.java" in it, "${it.keys}")
        }
    }

    @Test
    fun `an annotated type in a jar on the contract classpath is no registration`() {
        val library = jar(fixtures.compileSets("library", CORE).contract, tmp.resolve("library.jar"))
        val contract = fixtures.compile("plain", mapOf("fix/Plain.java" to "package fix;\n\nclass Plain {\n}\n"))

        val compilation = contract.process(classpath = listOf(library))

        assertErrors(compilation, "nothing of the contract is @Art or @Svm")
        assertEquals(listOf(Diagnostic.NOPOS), compilation.errors().map { it.lineNumber })
    }

    @Test
    fun `annotations without a root fail`() {
        val compiled = fixtures.compile(
            "rootless",
            mapOf(
                "fix/core/Service.java" to
                    "package fix.core;\n\n@$PACKAGE.Art\npublic interface Service {\n    void ping();\n}\n",
            ),
        )

        val compilation = compiled.process()

        assertErrors(compilation, "no interface of the contract is @Root")
        assertEquals(listOf(Diagnostic.NOPOS), compilation.errors().map { it.lineNumber })
    }

    @Test
    fun `a method that cannot cross and no root fail in one build, the summary first`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n@$PACKAGE.Art\npublic interface Service {\n" +
                "    void fill(Object[] values);\n}\n",
        )

        assertErrors(
            fixtures.compile("rootless-fill", core).process(),
            "no interface of the contract is @Root",
            "fix.core.Service.fill(java.lang.Object[]).values cannot cross java.lang.Object[]$NO_KIND",
        )
    }

    @Test
    fun `each type is implemented on the sides it is annotated with, enums cross as ordinals`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    Session open(Mode mode);\n    void set(Callback c);\n}\n",
            "fix/core/Session.java" to
                "package fix.core;\n\n@$PACKAGE.Svm\npublic interface Session {\n    void close();\n}\n",
            "fix/core/Callback.java" to "package fix.core;\n\n@$PACKAGE.Art\npublic interface Callback {\n" +
                "    Token token();\n    void give(Gift gift);\n}\n",
            "fix/core/Token.java" to
                "package fix.core;\n\n@$PACKAGE.Art\npublic interface Token {\n    String value();\n}\n",
            "fix/core/Gift.java" to
                "package fix.core;\n\n@$PACKAGE.Svm\npublic interface Gift {\n    void open();\n}\n",
            "fix/core/Mode.java" to "package fix.core;\n\npublic enum Mode { READ, WRITE }\n",
        )
        val model = fixtures.compile("directions", core).generate().model

        assertEquals(
            listOf(
                "fix.core.Callback implemented on ART",
                "fix.core.Gift implemented on the image",
                "fix.core.Service implemented on the image",
                "fix.core.Session implemented on the image",
                "fix.core.Token implemented on ART",
                "enum fix.core.Mode as ordinal of READ, WRITE",
            ),
            model.lines().filter { it.startsWith("fix") || it.startsWith("enum") },
        )
    }

    @Test
    fun `both bridges carry the model's fingerprint, which an enum constant or a result type changes`() {
        fun contract(constants: String, result: String = "int") = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    $result set(Mode mode);\n}\n",
            "fix/core/Mode.java" to "package fix.core;\n\npublic enum Mode { $constants }\n",
        )
        fun fingerprint(source: String) =
            Regex("long FINGERPRINT = (0x\\p{XDigit}{16})L;").find(source)!!.groupValues[1]
        val generated = fixtures.compile("shape", contract("READ, WRITE")).generate()
        val sha = MessageDigest.getInstance("SHA-256").digest(generated.model.toByteArray())
        val expected = "0x" + sha.take(8).joinToString("") { "%02x".format(it) }

        assertEquals(expected, fingerprint(generated.art.getValue("fix/core/generated/Bridge.java")))
        assertEquals(expected, fingerprint(generated.svm.getValue("fix/core/generated/Bridge.java")))
        val renamed = fixtures.compile("renamed", contract("READ, APPEND")).generate()
        assertNotEquals(expected, fingerprint(renamed.art.getValue("fix/core/generated/Bridge.java")))
        val widened = fixtures.compile("widened", contract("READ, WRITE", "long")).generate()
        assertNotEquals(expected, fingerprint(widened.art.getValue("fix/core/generated/Bridge.java")))
    }

    @Test
    fun `two types of one simple name fail naming both`() {
        val core = mapOf(
            "fix/one/Service.java" to "package fix.one;\n\n$ROOT\npublic interface Service {\n" +
                "    void add(Listener a);\n    void add(fix.two.Listener b);\n}\n",
            "fix/one/Listener.java" to
                "package fix.one;\n\n@$PACKAGE.Art\npublic interface Listener {\n    void on();\n}\n",
            "fix/two/Listener.java" to
                "package fix.two;\n\n@$PACKAGE.Art\npublic interface Listener {\n    void on();\n}\n",
        )

        assertErrors(
            fixtures.compile("listeners", core).process(),
            "the simple name Listener is shared by fix.one.Listener, fix.two.Listener",
        )
    }

    @Test
    fun `two enums of one simple name fail naming both`() {
        val core = mapOf(
            "fix/one/Service.java" to "package fix.one;\n\n$ROOT\npublic interface Service {\n" +
                "    Mode a(Mode m);\n    fix.two.Mode b(fix.two.Mode m);\n}\n",
            "fix/one/Mode.java" to "package fix.one;\n\npublic enum Mode { ON }\n",
            "fix/two/Mode.java" to "package fix.two;\n\npublic enum Mode { OFF }\n",
        )

        assertErrors(
            fixtures.compile("modes", core).process(),
            "the simple name Mode is shared by fix.one.Mode, fix.two.Mode",
        )
    }

    @Test
    fun `an annotated type no root reaches is generated`() {
        val core =
            CORE + ("fix/core/Unused.java" to "package fix.core;\n\n@$PACKAGE.Art\npublic interface Unused {\n}\n")

        val model = fixtures.compile("unreached", core).generate().model

        assertTrue("fix.core.Unused implemented on ART" in model.lines(), model)
    }

    @Test
    fun `a class crossing by reference fails`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    void ping();\n    void stamp(Stamp stamp);\n}\n",
            "fix/core/Stamp.java" to "package fix.core;\n\npublic abstract class Stamp {\n" +
                "    public abstract long time();\n}\n",
        )

        assertErrors(
            fixtures.compile("class-reference", core).process(),
            "fix.core.Service.stamp(fix.core.Stamp).stamp cannot cross fix.core.Stamp$NO_KIND",
        )
    }

    @Test
    fun `a method that cannot cross fails naming why`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n    void ping();\n" +
                "    void fill(Object[] values);\n}\n",
        )

        assertErrors(
            fixtures.compile("object-array", core).process(),
            "fix.core.Service.fill(java.lang.Object[]).values cannot cross java.lang.Object[]$NO_KIND",
        )
    }

    @Test
    fun `a method named as a member of the generated proxy fails`() {
        val peer = mapOf(
            "fix/core/Peer.java" to """
                package fix.core;

                @$PACKAGE.Art
                public interface Peer extends Base {
                    long foreignId();

                    long foreignId(int value);
                }
            """.trimIndent(),
            "fix/core/Base.java" to
                "package fix.core;\n\npublic interface Base {\n    void foreignCheck();\n\n    void markReleased();\n}\n",
        )

        assertErrors(
            fixtures.compile("reserved-method", CORE + peer).process(),
            "fix.core.Base.foreignCheck() uses a name reserved by the generated proxy, rename it",
            "fix.core.Base.markReleased() uses a name reserved by the generated proxy, rename it",
            "fix.core.Peer.foreignId() uses a name reserved by the generated proxy, rename it",
            "fix.core.Peer.foreignId(int) uses a name reserved by the generated proxy, rename it",
        )
    }

    @Test
    fun `the reserved names are Foreign's members`() {
        assertEquals(
            Foreign::class.java.declaredMethods.filterNot { Modifier.isPrivate(it.modifiers) }.map { it.name }.toSet(),
            Interfaces.RESERVED_METHODS,
        )
        assertEquals(
            Foreign::class.java.declaredFields.filterNot { Modifier.isPrivate(it.modifiers) }.map { it.name }.toSet(),
            Interfaces.RESERVED_FIELDS,
        )
    }

    @Test
    fun `a constant named as a field of the generated proxy fails`() {
        val peer = mapOf(
            "fix/core/Peer.java" to "package fix.core;\n\n@$PACKAGE.Art\npublic interface Peer {\n" +
                "    int id = 1;\n    int ID = 2;\n\n    int ping(int value);\n}\n",
        )

        assertErrors(
            fixtures.compile("reserved-field", CORE + peer).process(),
            "fix.core.Peer.id uses a name reserved by the generated proxy, rename it",
        )
    }

    @Test
    fun `roots in two packages generate into the marked package's generated package`() {
        val compiled = fixtures.compile(
            "several",
            mapOf(
                "fix/app/one/Game.java" to
                    "package fix.app.one;\n\n$ROOT\npublic interface Game {\n    void ping();\n}\n",
                "fix/app/two/Audio.java" to
                    "package fix.app.two;\n\n$ROOT\npublic interface Audio {\n    void play();\n}\n",
            ),
            marked = "fix.app",
        )
        val generated = compiled.generate()

        assertEquals(
            listOf(
                "contract class fix.app.generated.Bridge",
                "root fix.app.one.Game",
                "root fix.app.two.Audio",
                "fix.app.one.Game implemented on the image",
                "fix.app.two.Audio implemented on the image",
            ),
            generated.model.lines().filter {
                it.startsWith("contract") || it.startsWith("root") || it.startsWith("fix")
            },
        )
        val names =
            setOf(
                "fix/app/generated/Bridge.java",
                "fix/app/generated/\$AudioSvm.java",
                "fix/app/generated/\$GameSvm.java",
            )
        assertEquals(names, generated.art.keys)
        assertEquals(names, generated.svm.keys)
        val art = generated.art.getValue("fix/app/generated/Bridge.java")
        assertTrue("return new Class<?>[] {Game.class, Audio.class};" in art, art)
        assertTrue("if (root == Game.class) {" in art && "if (root == Audio.class) {" in art, art)
        assertFalse("public static" in art, art)
        fixtures.compileBridges(compiled, generated, "${compiled.contract.name}-generated")
    }

    @Test
    fun `a contract class a library of the contract holds fails`() {
        val library = library("taken", "Bridge")
        val compiled = fixtures.compile("taken", CORE)

        assertErrors(
            compiled.process(classpath = listOf(library)),
            "the generated class fix.core.generated.Bridge already exists on the contract classpath",
        )
    }

    @Test
    fun `a class named like a generated class fails`() {
        val library = library("generated-name", "\$ServiceSvm")
        val compiled = fixtures.compile("generated-name", CORE)

        assertErrors(
            compiled.process(classpath = listOf(library)),
            "the generated class fix.core.generated.\$ServiceSvm already exists on the contract classpath",
        )
    }

    @Test
    fun `contract types that are not public fail, collected`() {
        val core = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n" +
                "    void attach(Peer peer);\n\n    void mode(Mode mode);\n}\n",
            "fix/core/Peer.java" to "package fix.core;\n\n@$PACKAGE.Svm\ninterface Peer {\n}\n",
            "fix/core/Mode.java" to "package fix.core;\n\nenum Mode { READ }\n",
        )

        assertErrors(
            fixtures.compile("not-public", core).process(),
            "fix.core.Peer crosses, but it is not public. Make it public",
            "fix.core.Mode crosses, but it is not public. Make it public",
        )
    }

    private fun library(name: String, simpleName: String): File {
        val source = "package fix.core.generated;\n\npublic final class $simpleName {\n}\n"
        val classes = fixtures.compileSets("$name-library", mapOf("fix/core/generated/$simpleName.java" to source))
        return jar(classes.contract, tmp.resolve("$name.jar"))
    }

    private fun jar(classes: File, jar: File): File {
        JarOutputStream(jar.outputStream()).use { out ->
            classes.walkTopDown().filter { it.isFile }.forEach {
                out.putNextEntry(JarEntry(it.relativeTo(classes).invariantSeparatorsPath))
                out.write(it.readBytes())
                out.closeEntry()
            }
        }
        return jar
    }

    private companion object {
        const val ROOT = "@$PACKAGE.Root\n@$PACKAGE.Svm"

        val CORE = mapOf(
            "fix/core/Service.java" to "package fix.core;\n\n$ROOT\npublic interface Service {\n    void ping();\n}\n",
        )

        val ODD = mapOf(
            "fix/core/Odd.java" to "package fix.core;\n\npublic class Odd extends IllegalStateException {\n" +
                "    public Odd(String message) {\n        super(message);\n    }\n}\n",
        )
    }
}
