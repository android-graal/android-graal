package org.androidgraal.handles.generator.model

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.TypeName

internal sealed interface Kind

internal sealed interface ParameterKind

internal class PrimitiveKind(val type: TypeName, val descriptor: String) :
    Kind,
    ParameterKind

internal object TextKind : Kind, ParameterKind

internal class ArrayKind(val component: TypeName, val elementBytes: Int) : Kind

internal object VoidKind : Kind

internal class HandleKind(val type: ClassName) :
    Kind,
    ParameterKind

internal class Wire(val endPoint: String, val result: Kind, val parameters: List<ParameterKind>) {
    val jniDescriptor: String = "(J" + parameters.joinToString("") { descriptorOf(it) } + ")" + when (result) {
        is PrimitiveKind -> result.descriptor
        VoidKind -> "V"
        is HandleKind -> "J"
        TextKind, is ArrayKind -> "Ljava/nio/ByteBuffer;"
    }

    fun named(endPoint: String): Wire = Wire(endPoint, result, parameters)
}

private fun descriptorOf(parameter: ParameterKind): String = when (parameter) {
    is PrimitiveKind -> parameter.descriptor
    TextKind -> "JI"
    is HandleKind -> "J"
}
