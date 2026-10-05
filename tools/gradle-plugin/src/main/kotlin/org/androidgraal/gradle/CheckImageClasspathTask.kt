package org.androidgraal.gradle

import org.gradle.api.GradleException
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.OutputFile
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import java.io.File
import java.util.zip.ZipFile
import kotlin.sequences.forEach

@CacheableTask
abstract class CheckImageClasspathTask : BaseTask("checkImageClasspath") {

    @get:Classpath
    abstract val imageClasspath: ConfigurableFileCollection

    @get:OutputFile
    abstract val report: RegularFileProperty

    fun setup(variantName: String, imageClasspath: FileCollection) {
        group = "verification"
        description = "Checks that every Android class on the image classpath is an emulated class ($variantName)."
        this.variantName.convention(variantName)
        this.report.convention(taskDir.file("report.txt"))
        this.imageClasspath.convention(imageClasspath)
    }

    override fun execute() {
        val image = imageClasspath.files.toList()
        val errors = problems(image)
        if (errors.isNotEmpty()) {
            val text = errors.joinToString("\n") { "android-graal: $it" }
            log.write("$text\n".toByteArray())
            throw GradleException(text)
        }
        val out = StringBuilder("image classpath:\n")
        image.forEach { file -> out.append("  ${file.name}\n") }
        report.get().asFile.writeText(out.toString())
        log.write(out.toString().toByteArray())
    }

    private fun problems(image: List<File>): List<String> {
        val android = mutableSetOf<String>()
        val emulated = mutableSetOf<String>()
        scan(image) { bytes ->
            val visitor = object : ClassVisitor(Opcodes.ASM9) {
                private lateinit var name: String

                override fun visit(
                    version: Int,
                    access: Int,
                    name: String,
                    signature: String?,
                    superName: String?,
                    interfaces: Array<String>?,
                ) {
                    this.name = name
                    if (name.startsWith("android/")) android += name.substringBefore('$')
                }

                override fun visitAnnotation(descriptor: String, visible: Boolean): AnnotationVisitor? {
                    if (descriptor == EMULATED) emulated += name
                    return null
                }
            }
            ClassReader(bytes).accept(
                visitor,
                ClassReader.SKIP_CODE or ClassReader.SKIP_DEBUG or ClassReader.SKIP_FRAMES,
            )
        }
        return (android - emulated).sorted().map {
            "$it is an Android class on the image classpath without @Emulated -" +
                " Android classes do not work in the image."
        }
    }

    companion object {
        private const val EMULATED = "Lorg/androidgraal/hosted/annotate/Emulated;"
    }
}

fun scan(files: List<File>, onClass: (ByteArray) -> Unit) {
    files.forEach { file ->
        if (file.isFile) {
            ZipFile(file).use { zipFile ->
                zipFile.entries().asSequence()
                    .filter { it.name.endsWith(".class") && !it.name.startsWith("META-INF/") }
                    .forEach { entry ->
                        zipFile.getInputStream(entry).use {
                            onClass(it.readBytes())
                        }
                    }
            }
        } else {
            file.walkTopDown().filter { it.isFile }.filter { it.path.endsWith(".class") }.forEach { classFile ->
                onClass(classFile.readBytes())
            }
        }
    }
}
