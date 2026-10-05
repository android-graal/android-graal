package org.androidgraal.processor

import org.androidgraal.generator.emit.render.ArtSources
import org.androidgraal.generator.emit.render.ImageSources
import org.androidgraal.generator.model.BridgeException
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.scan.Annotated
import org.androidgraal.generator.scan.Annotations
import org.androidgraal.generator.scan.Discovery
import org.androidgraal.generator.scan.Errors
import org.androidgraal.generator.scan.Interfaces
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

class BridgeProcessor : AbstractProcessor() {

    private val annotated = Annotated()

    override fun getSupportedAnnotationTypes(): Set<String> = Annotations.ALL

    override fun getSupportedOptions(): Set<String> = setOf(OUT)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(annotations: Set<TypeElement>, round: RoundEnvironment): Boolean {
        for (a in annotations) annotated.add(a, round.getElementsAnnotatedWith(a))
        if (!round.processingOver()) return false
        val out = processingEnv.options[OUT]
        if (out == null) {
            error("android-graal: the option -A$OUT is missing")
            return false
        }
        val errors = Errors()
        val registrations = Discovery.registrations(annotated, errors)
        val interfaces = Interfaces(registrations, processingEnv.elementUtils, processingEnv.typeUtils, errors)
        try {
            interfaces.analyze()
        } catch (e: BridgeException) {
            report(errors)
            error(e.message.orEmpty())
            return false
        }

        if (errors.isNotEmpty()) {
            report(errors)
            return false
        }

        val model = BridgeModel.of(
            registrations.roots,
            registrations.packageName,
            interfaces.proxies,
            interfaces.enums,
            interfaces.records,
        )

        reportCollisions(model, errors)
        if (errors.isNotEmpty()) {
            report(errors)
            return false
        }

        val dir = File(out).apply { deleteRecursively() }
        val art = ArtSources(model)
        val svm = ImageSources(model)
        write(dir.resolve("art/java"), art.render())
        write(dir.resolve("art/resources"), art.resources())
        write(dir.resolve("svm/java"), svm.render())
        return false
    }

    private fun reportCollisions(model: BridgeModel, errors: Errors) {
        val types = model.proxies.map { it.type }.distinct() + model.enums
        types.groupBy { "${it.simpleName}" }.filterValues { it.size > 1 }.forEach { (name, shared) ->
            errors.add("the simple name $name is shared by ${shared.joinToString(", ") { "${it.qualifiedName}" }}")
        }

        model.generatedClasses().map { it.canonicalName() }
            .filter { processingEnv.elementUtils.getTypeElement(it) != null }
            .forEach { errors.add("the generated class $it already exists on the contract classpath") }
    }

    private fun write(dir: File, files: Map<String, String>) {
        files.forEach { (path, text) -> dir.resolve(path).apply { parentFile.mkdirs() }.writeText(text) }
    }

    private fun report(errors: Errors) {
        errors.all.forEach { (message, element) -> error("android-graal: $message", element) }
    }

    private fun error(message: String, element: Element? = null) {
        if (element == null) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message)
        } else {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message, element)
        }
    }

    companion object {
        const val OUT = "androidgraal.out"
    }
}
