package org.androidgraal.generator.model

import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.type.ArrayType
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

internal fun String.capitalized(): String = replaceFirstChar { it.uppercaseChar() }

internal fun TypeMirror.element(): TypeElement = (this as DeclaredType).asElement() as TypeElement

internal fun TypeMirror.isString(): Boolean =
    kind == TypeKind.DECLARED && element().qualifiedName.contentEquals(JdkNames.STRING)

internal fun TypeMirror.name(): String = when {
    kind == TypeKind.DECLARED ->
        if ((this as DeclaredType).typeArguments.isEmpty()) "${element().qualifiedName}" else "$this"

    kind == TypeKind.ARRAY -> (this as ArrayType).componentType.name() + "[]"

    kind.isPrimitive || kind == TypeKind.VOID -> kind.name.lowercase()

    else -> "$this"
}

internal fun ExecutableElement.signature(): String =
    parameters.joinToString(", ", "$simpleName(", ")") { it.asType().name() }

internal fun TypeElement.isPublic(): Boolean =
    generateSequence(this) { it.enclosingElement as? TypeElement }.all { Modifier.PUBLIC in it.modifiers }

internal fun Element.qualifiedName(): String = when (this) {
    is TypeElement -> "$qualifiedName"
    is ExecutableElement -> "${enclosingElement.qualifiedName()}.${signature()}"
    else -> "${enclosingElement.qualifiedName()}.$simpleName"
}
