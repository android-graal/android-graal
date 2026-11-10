package org.androidgraal.handles.generator

import com.github.ajalt.clikt.core.CoreCliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.clikt.parameters.types.int
import org.androidgraal.handles.generator.emit.ArtSources
import org.androidgraal.handles.generator.emit.HandleSource
import org.androidgraal.handles.generator.emit.ImageSources
import org.androidgraal.handles.generator.emit.JavaNames
import org.androidgraal.handles.generator.model.HandleModel
import org.androidgraal.handles.generator.model.fail
import org.androidgraal.handles.generator.scan.Classpath
import org.androidgraal.handles.generator.scan.Members
import org.androidgraal.handles.generator.scan.Roots
import org.androidgraal.handles.generator.scan.levelsOf
import org.androidgraal.handles.generator.scan.proxyBoundaryOf
import java.util.zip.ZipFile

class HandleGenerator : CoreCliktCommand() {
    private val jar by option("--android-jar").file(mustExist = true, canBeDir = false).required()
    private val apiVersions by option("--api-versions").file(mustExist = true, canBeDir = false).required()
    private val sources by option("--android-sources").file(mustExist = true, canBeFile = false).required()
    private val boundaryFile by option("--proxy-boundary").file(mustExist = true, canBeDir = false).required()
    private val emulation by option("--emulation-classes").file(mustExist = true, canBeFile = false).required()
    private val image by option("--image-out").file(mustExist = true, canBeFile = false).required()
    private val art by option("--art-out").file(mustExist = true, canBeFile = false).required()
    private val minSdk by option("--min-sdk").int().required()

    override fun run() {
        ZipFile(jar).use { archive ->
            val boundary = proxyBoundaryOf(boundaryFile)
            val classpath = Classpath(emulation, boundary)
            val roots = Roots(archive, levelsOf(apiVersions), classpath, boundary, minSdk)
            val model = HandleModel(boundary, boundary.handles.map(roots::of))
            model.roots.groupBy { JavaNames.bindingOf(it.name) }.values.firstOrNull { it.size > 1 }?.let { sharing ->
                fail("${sharing.joinToString(" and ") { it.internalName }} share a binding name")
            }
            val members = Members(sources, classpath)
            val plans = model.roots.map(members::planOf)
            val handles = plans.map { image.resolve("${it.root.internalName}.java") to HandleSource(it).text() }
            val imageFiles = ImageSources(model).render()
            val artFiles = ArtSources(model).render()
            image.resolve(JavaNames.IMAGE_TABLE.packageName().replace('.', '/')).deleteRecursively()
            art.resolve(JavaNames.ART_TABLE.packageName().replace('.', '/')).deleteRecursively()
            handles.forEach { (file, text) -> file.writeText(text) }
            imageFiles.forEach { it.writeTo(image) }
            artFiles.forEach { it.writeTo(art) }
            plans.flatMap { it.notes }.forEach(::println)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = HandleGenerator().context { exitProcess = System::exit }.main(args)
    }
}
