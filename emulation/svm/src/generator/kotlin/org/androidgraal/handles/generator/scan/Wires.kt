package org.androidgraal.handles.generator.scan

import com.palantir.javapoet.TypeName
import org.androidgraal.handles.generator.model.ArrayKind
import org.androidgraal.handles.generator.model.Kind
import org.androidgraal.handles.generator.model.Method
import org.androidgraal.handles.generator.model.PrimitiveKind
import org.androidgraal.handles.generator.model.TextKind
import org.androidgraal.handles.generator.model.Wire
import java.lang.constant.ClassDesc
import java.lang.constant.ConstantDescs
import java.lang.invoke.MethodHandles

private val ELEMENT_BYTES = mapOf(
    ConstantDescs.CD_char to 2,
    ConstantDescs.CD_short to 2,
    ConstantDescs.CD_int to 4,
    ConstantDescs.CD_float to 4,
    ConstantDescs.CD_long to 8,
    ConstantDescs.CD_double to 8,
)

internal fun wireOf(method: Method): Wire? {
    val parameters = method.type.parameterList()
    if (!parameters.all { it.isPrimitive }) return null
    val result = resultOf(method.type.returnType()) ?: return null
    return Wire(method.name, result, parameters.map(::primitiveOf))
}

private fun resultOf(type: ClassDesc): Kind? = when {
    type == ConstantDescs.CD_void -> null
    type.isPrimitive -> primitiveOf(type)
    type == ConstantDescs.CD_String -> TextKind
    type.isArray -> ELEMENT_BYTES[type.componentType()]?.let { ArrayKind(typeNameOf(type.componentType()), it) }
    else -> null
}

private fun primitiveOf(type: ClassDesc): PrimitiveKind = PrimitiveKind(typeNameOf(type), type.descriptorString())

private fun typeNameOf(primitive: ClassDesc): TypeName =
    TypeName.get(primitive.resolveConstantDesc(MethodHandles.lookup()) as Class<*>)
