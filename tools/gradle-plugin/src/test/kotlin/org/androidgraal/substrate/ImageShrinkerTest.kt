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
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
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

    private fun config(
        fullMode: Boolean = true,
        strictFullModeForKeepRules: Boolean = true,
        debuggable: Boolean = false,
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
                ),
            )
            rules.writeText("-keepclassmembers class p.Kept { *** run(); }\n")
        }
        val out = tmp.resolve("out-$fullMode-$strictFullModeForKeepRules-$debuggable")
        return ImageShrinker.Config(
            program = listOf(program),
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
    }
}
