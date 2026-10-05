package org.androidgraal.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.GradleException
import org.gradle.testfixtures.ProjectBuilder
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.io.File
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CheckImageClasspathTaskTest {

    private val tmp: File = createTempDirectory("check-image-classpath").toFile()

    private val project = ProjectBuilder.builder().withProjectDir(tmp.resolve("project")).build()

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(tmp)

    @Test
    fun `a marked Android class passes`() {
        val classes = fixture("classes", "android/x/A" to true)

        assertEquals(listOf("image classpath:", "  classes"), check(classes).readLines())
    }

    @Test
    fun `an unmarked Android class is one error naming it and its file`() {
        val widgets = fixture("widgets.jar", "android/x/A" to false)

        assertEquals(listOf(error("android/x/A", "widgets.jar")), errors(widgets))
    }

    @Test
    fun `two unmarked classes in one jar are two errors, sorted by name`() {
        val widgets = fixture("widgets.jar", "android/x/B" to false, "android/x/A" to false)

        assertEquals(listOf(error("android/x/A", "widgets.jar"), error("android/x/B", "widgets.jar")), errors(widgets))
    }

    @Test
    fun `a nested class is covered by its outermost class`() {
        val marked = fixture("marked.jar", "android/x/A" to true, "android/x/A$1" to false)
        val unmarked = fixture("unmarked.jar", "android/x/A" to false, "android/x/A$1" to false)

        assertEquals(listOf("image classpath:", "  marked.jar"), check(marked).readLines())
        assertEquals(listOf(error("android/x/A", "unmarked.jar")), errors(unmarked))
    }

    @Test
    fun `a nested class without its outermost class counts as unmarked`() {
        val nested = fixture("nested.jar", "android/x/A$1" to true)

        assertEquals(listOf(error("android/x/A", "nested.jar")), errors(nested))
    }

    @Test
    fun `classes outside android are never reported`() {
        val other = fixture("other.jar", "com/example/A" to false, "androidx/x/A" to false)

        assertEquals(listOf("image classpath:", "  other.jar"), check(other).readLines())
    }

    private fun check(vararg image: File): File {
        val task = project.tasks.register("check${project.tasks.size}", CheckImageClasspathTask::class.java) {
            it.setup("debug", project.files(*image))
        }.get()
        task.run()
        return task.report.get().asFile
    }

    private fun errors(vararg image: File): List<String> {
        val failure = assertFailsWith<GradleException> { check(*image) }
        return failure.cause?.message.orEmpty().lines()
    }

    private fun error(name: String, file: String): String =
        "android-graal: $name is an Android class on the image classpath without @Emulated - Android classes do not work in the image."

    /** A directory, or a jar when [name] ends in `.jar`, holding [classes] by internal name, each marked or not. */
    private fun fixture(name: String, vararg classes: Pair<String, Boolean>): File {
        val file = tmp.resolve(name)
        if (name.endsWith(".jar")) {
            JarOutputStream(file.outputStream()).use { out ->
                classes.forEach { (internal, emulated) ->
                    out.putNextEntry(JarEntry("$internal.class"))
                    out.write(classFile(internal, emulated))
                    out.closeEntry()
                }
            }
        } else {
            classes.forEach { (internal, emulated) ->
                file.resolve("$internal.class").apply { parentFile.mkdirs() }.writeBytes(classFile(internal, emulated))
            }
        }
        return file
    }

    private fun classFile(internal: String, emulated: Boolean): ByteArray {
        val writer = ClassWriter(0)
        writer.visit(Opcodes.V17, Opcodes.ACC_PUBLIC or Opcodes.ACC_SUPER, internal, null, "java/lang/Object", null)
        if (emulated) writer.visitAnnotation("Lorg/androidgraal/hosted/annotate/Emulated;", false).visitEnd()
        writer.visitEnd()
        return writer.toByteArray()
    }
}
