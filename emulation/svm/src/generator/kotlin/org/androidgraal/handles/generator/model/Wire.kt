package org.androidgraal.handles.generator.model

import com.palantir.javapoet.TypeName

internal sealed interface Kind

internal class PrimitiveKind(val type: TypeName, val descriptor: String) : Kind

internal object TextKind : Kind

internal class ArrayKind(val component: TypeName, val elementBytes: Int) : Kind

internal class Wire(val endPoint: String, val result: Kind, val parameters: List<PrimitiveKind>) {
    val jniDescriptor: String = "(J" + parameters.joinToString("") { it.descriptor } + ")" + when (result) {
        is PrimitiveKind -> result.descriptor
        TextKind, is ArrayKind -> "Ljava/nio/ByteBuffer;"
    }
}
