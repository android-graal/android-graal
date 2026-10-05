package org.androidgraal.generator.scan

import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement

internal object Annotations {
    const val PACKAGE = "org.androidgraal.hosted.annotate"
    const val ART = "$PACKAGE.Art"
    const val SVM = "$PACKAGE.Svm"
    const val ROOT = "$PACKAGE.Root"
    const val IN = "$PACKAGE.In"
    const val OUT = "$PACKAGE.Out"
    const val STATIC_BUFFER = "$PACKAGE.StaticBuffer"
    const val CAPACITY = "capacity"
    const val BRIDGE = "$PACKAGE.Bridge"

    val ALL: Set<String> = setOf(ART, SVM, ROOT, IN, OUT, STATIC_BUFFER, BRIDGE)
}

internal class Annotated {
    val bridge = linkedSetOf<PackageElement>()
    val art = linkedSetOf<TypeElement>()
    val svm = linkedSetOf<TypeElement>()
    val root = linkedSetOf<TypeElement>()

    fun add(annotation: TypeElement, elements: Set<out Element>) {
        when ("${annotation.qualifiedName}") {
            Annotations.BRIDGE -> bridge += elements.filterIsInstance<PackageElement>()
            Annotations.ART -> art += elements.filterIsInstance<TypeElement>()
            Annotations.SVM -> svm += elements.filterIsInstance<TypeElement>()
            Annotations.ROOT -> root += elements.filterIsInstance<TypeElement>()
        }
    }
}

internal fun Element.annotation(name: String): AnnotationMirror? {
    return annotationMirrors
        .firstOrNull { (it.annotationType.asElement() as TypeElement).qualifiedName.contentEquals(name) }
}

internal fun Element.hasAnnotation(name: String): Boolean = annotation(name) != null

internal fun AnnotationMirror.intValue(name: String): Int =
    elementValues.entries.first { it.key.simpleName.contentEquals(name) }.value.value as Int
