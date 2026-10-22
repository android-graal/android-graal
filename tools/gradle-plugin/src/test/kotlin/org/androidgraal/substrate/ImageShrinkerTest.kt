package org.androidgraal.substrate

import com.android.tools.r8.CompilationMode
import com.android.tools.r8.DiagnosticsHandler
import com.android.tools.r8.JdkClassFileProvider
import org.apache.commons.io.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.io.ByteArrayOutputStream
import java.io.Closeable
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarFile
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ImageShrinkerTest {

    private val tmp: File = createTempDirectory("image-shrinker-test").toFile()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `the command is full or compatibility mode as the switch says, debug mode for a debuggable variant`() {
        val library = JdkClassFileProvider.fromSystemJdk()
        (library as Closeable).use {
            listOf(true, false).forEach { fullMode ->
                listOf(true, false).forEach { debuggable ->
                    val command = ImageShrinker(config(fullMode = fullMode, debuggable = debuggable))
                        .command(object : DiagnosticsHandler {}, library)

                    assertEquals(!fullMode, command.proguardCompatibility)
                    assertEquals(if (debuggable) CompilationMode.DEBUG else CompilationMode.RELEASE, command.mode)
                    assertTrue(command.enableTreeShaking)
                    assertEquals(!debuggable, command.enableMinification)
                }
            }
        }
    }

    @Test
    fun `a keep rule without members keeps the default constructor in legacy full mode only`() {
        listOf(true to false, false to true).forEach { (strict, defaultConstructor) ->
            val config = config(strictFullModeForKeepRules = strict)

            ImageShrinker(config).shrink(ByteArrayOutputStream())

            assertEquals(defaultConstructor, "<init>()V" in methods(config.outputJar, "p/Kept"), "strict = $strict")
        }
    }

    @Test
    fun `the rules files and a program jar's embedded rules apply, data resources pass, a service is renamed`() {
        val config = config()

        ImageShrinker(config).shrink(ByteArrayOutputStream())

        val entries = entries(config.outputJar)
        val classes = classNames(config.imageMapping)
        assertTrue("p/Kept.class" in entries, "$entries")
        assertTrue("run()Ljava/util/ServiceLoader;" in methods(config.outputJar, "p/Kept"))
        assertTrue("p.Dropped" !in classes, "$classes")
        assertTrue(NATIVE_IMAGE_PROPERTIES in entries, "$entries")
        val services = entries.filter { it.startsWith("META-INF/services/") }
        val provider = text(config.outputJar, services.single()).trim()
        assertTrue(services.single() != "META-INF/services/p.Service", "$services")
        assertEquals("p.Provider", classes.entries.single { it.value == provider }.key)
        assertTrue("${provider.replace('.', '/')}.class" in entries, "$entries")
        assertTrue("-applymapping" !in config.configuration.readText())
    }

    @Test
    fun `neither a manifest nor a signature entry reaches the image jar, the other META-INF entries do`() {
        val config = config(entries = SIGNING_ENTRIES.associateWith { "x\n".toByteArray() })

        ImageShrinker(config).shrink(ByteArrayOutputStream())

        val entries = entries(config.outputJar)
        assertTrue(entries.none { it in SIGNING_ENTRIES }, "$entries")
        assertTrue(NATIVE_IMAGE_PROPERTIES in entries, "$entries")
        assertTrue(entries.any { it.startsWith("META-INF/services/") }, "$entries")
    }

    @Test
    fun `the image jar of a signed program jar behind a plain one loads`() {
        val signed = tmp.resolve("signed.jar")
        jar(
            signed,
            mapOf(
                "q/Signed.class" to classBytes("q/Signed"),
                "META-INF/com.android.tools/r8/signed.pro" to "-keep class q.Signed\n".toByteArray(),
            ),
        )
        sign(signed)
        val manifest = "Manifest-Version: 1.0\r\nCreated-By: test\r\n\r\n".toByteArray()
        val config = config(entries = mapOf("META-INF/MANIFEST.MF" to manifest), more = listOf(signed))

        ImageShrinker(config).shrink(ByteArrayOutputStream())

        URLClassLoader(arrayOf(config.outputJar.toURI().toURL()), null).use { loader ->
            assertEquals("q.Signed", loader.loadClass("q.Signed").name)
        }
        assertNull(JarFile(config.outputJar).use { it.manifest })
    }

    private fun config(
        fullMode: Boolean = true,
        strictFullModeForKeepRules: Boolean = true,
        debuggable: Boolean = false,
        entries: Map<String, ByteArray> = emptyMap(),
        more: List<File> = emptyList(),
    ): ImageShrinker.Config {
        val program = tmp.resolve("program.jar")
        val rules = tmp.resolve("rules.pro")
        if (!program.exists()) {
            jar(
                program,
                mapOf(
                    "p/Kept.class" to kept(),
                    "p/Dropped.class" to classBytes("p/Dropped"),
                    "p/Service.class" to serviceInterface(),
                    "p/Provider.class" to classBytes("p/Provider", "p/Service"),
                    "META-INF/services/p.Service" to "p.Provider\n".toByteArray(),
                    NATIVE_IMAGE_PROPERTIES to "Args = --verbose\n".toByteArray(),
                    "META-INF/com.android.tools/r8/kept.pro" to "-keep class p.Kept\n".toByteArray(),
                ) + entries,
            )
            rules.writeText("-keepclassmembers class p.Kept { *** run(); }\n")
        }
        val out = tmp.resolve("out-$fullMode-$strictFullModeForKeepRules-$debuggable")
        return ImageShrinker.Config(
            program = listOf(program) + more,
            jdkHome = File(System.getProperty("java.home")),
            rules = listOf(rules),
            outputJar = out.resolve("image.jar"),
            imageMapping = out.resolve("mapping.txt"),
            configuration = out.resolve("configuration.txt"),
            fullMode = fullMode,
            strictFullModeForKeepRules = strictFullModeForKeepRules,
            debuggable = debuggable,
        )
    }

    /** `p.Kept.run()` returns the loader of `p.Service`'s providers. */
    private fun kept(): ByteArray {
        return classBytes("p/Kept") {
            visitMethod(Opcodes.ACC_PUBLIC, "run", "()Ljava/util/ServiceLoader;", null, null).apply {
                visitCode()
                visitLdcInsn(Type.getObjectType("p/Service"))
                visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "java/util/ServiceLoader",
                    "load",
                    "(Ljava/lang/Class;)Ljava/util/ServiceLoader;",
                    false,
                )
                visitInsn(Opcodes.ARETURN)
                visitMaxs(0, 0)
                visitEnd()
            }
        }
    }

    private fun serviceInterface(): ByteArray {
        val writer = ClassWriter(ClassWriter.COMPUTE_MAXS)
        writer.visit(
            Opcodes.V17,
            Opcodes.ACC_PUBLIC or Opcodes.ACC_INTERFACE or Opcodes.ACC_ABSTRACT,
            "p/Service",
            null,
            "java/lang/Object",
            null,
        )
        writer.visitEnd()
        return writer.toByteArray()
    }

    private fun classBytes(name: String, vararg interfaces: String, more: ClassWriter.() -> Unit = {}): ByteArray {
        val writer = ClassWriter(ClassWriter.COMPUTE_MAXS)
        writer.visit(Opcodes.V17, Opcodes.ACC_PUBLIC or Opcodes.ACC_SUPER, name, null, "java/lang/Object", interfaces)
        writer.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null).apply {
            visitCode()
            visitVarInsn(Opcodes.ALOAD, 0)
            visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
            visitInsn(Opcodes.RETURN)
            visitMaxs(0, 0)
            visitEnd()
        }
        writer.more()
        writer.visitEnd()
        return writer.toByteArray()
    }

    private fun jar(file: File, entries: Map<String, ByteArray>) {
        ZipOutputStream(file.outputStream()).use { zip ->
            entries.forEach { (name, bytes) ->
                zip.putNextEntry(ZipEntry(name))
                zip.write(bytes)
                zip.closeEntry()
            }
        }
    }

    private fun sign(jar: File) {
        val bin = File(System.getProperty("java.home"), "bin")
        val keystore = tmp.resolve("keystore")
        val password = "changeit"
        run(
            bin.resolve("keytool").path,
            "-genkeypair",
            "-keyalg",
            "RSA",
            "-keysize",
            "2048",
            "-dname",
            "CN=test",
            "-alias",
            "t",
            "-keystore",
            keystore.path,
            "-storepass",
            password,
            "-keypass",
            password,
        )
        run(bin.resolve("jarsigner").path, "-keystore", keystore.path, "-storepass", password, jar.path, "t")
    }

    private fun run(vararg command: String) {
        val process = ProcessBuilder(*command).redirectErrorStream(true).start()
        val output = process.inputStream.readBytes().decodeToString()
        assertEquals(0, process.waitFor(), output)
    }

    private fun entries(file: File): List<String> = ZipFile(file).use { zip -> zip.entries().toList().map { it.name } }

    private fun text(file: File, entry: String): String =
        ZipFile(file).use { zip -> zip.getInputStream(zip.getEntry(entry)).readBytes().decodeToString() }

    /** Original to obfuscated class name, from R8's mapping. */
    private fun classNames(mapping: File): Map<String, String> {
        return mapping.readLines()
            .filter { !it.startsWith(" ") && !it.startsWith("#") && it.endsWith(":") }
            .associate { line -> line.removeSuffix(":").split(" -> ").let { it[0] to it[1] } }
    }

    private fun methods(file: File, className: String): Set<String> {
        val bytes = ZipFile(file).use { zip -> zip.getInputStream(zip.getEntry("$className.class")).readBytes() }
        val methods = mutableSetOf<String>()
        ClassReader(bytes).accept(
            object : ClassVisitor(Opcodes.ASM9) {
                override fun visitMethod(
                    access: Int,
                    name: String,
                    descriptor: String,
                    signature: String?,
                    exceptions: Array<String>?,
                ): MethodVisitor? {
                    methods += name + descriptor
                    return null
                }
            },
            ClassReader.SKIP_CODE,
        )
        return methods
    }

    private companion object {
        const val NATIVE_IMAGE_PROPERTIES = "META-INF/native-image/p/native-image.properties"

        val SIGNING_ENTRIES = listOf(
            "META-INF/MANIFEST.MF",
            "META-INF/X.SF",
            "META-INF/X.RSA",
            "META-INF/x.dsa",
            "META-INF/X.EC",
            "META-INF/SIG-X",
        )
    }
}
