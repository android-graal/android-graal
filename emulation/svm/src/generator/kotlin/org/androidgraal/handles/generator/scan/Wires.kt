package org.androidgraal.handles.generator.scan

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.TypeName
import org.androidgraal.handles.generator.model.ArrayKind
import org.androidgraal.handles.generator.model.HandleKind
import org.androidgraal.handles.generator.model.Kind
import org.androidgraal.handles.generator.model.Method
import org.androidgraal.handles.generator.model.ParameterKind
import org.androidgraal.handles.generator.model.PrimitiveKind
import org.androidgraal.handles.generator.model.TextKind
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.VoidKind
import org.androidgraal.handles.generator.model.Wire
import org.androidgraal.handles.generator.model.fail
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

internal fun wireOf(owner: String, method: Method, handles: List<String>): Wire? {
    val parameters = method.type.parameterList().map { parameterOf(it) ?: return null }
    val result = resultOf(method.type.returnType(), handles) ?: return null
    val texts = parameters.count { it == TextKind }
    if (texts > 1) fail("$owner ${method.key} has more than one String parameter, $UNWRITTEN")
    if (texts == 1 && result !is PrimitiveKind) {
        fail("$owner ${method.key} has a String parameter and a result that is no primitive, $UNWRITTEN")
    }
    return Wire(method.name, result, parameters)
}

private fun parameterOf(type: ClassDesc): ParameterKind? = when {
    type.isPrimitive -> primitiveOf(type)
    type == ConstantDescs.CD_String -> TextKind
    else -> null
}

private fun resultOf(type: ClassDesc, handles: List<String>): Kind? = when {
    type == ConstantDescs.CD_void -> VoidKind
    type.isPrimitive -> primitiveOf(type)
    type == ConstantDescs.CD_String -> TextKind
    type.isArray -> ELEMENT_BYTES[type.componentType()]?.let { ArrayKind(typeNameOf(type.componentType()), it) }
    isHandle(type, handles) -> HandleKind(ClassName.get(type.packageName(), type.displayName()))
    else -> null
}

private fun isHandle(type: ClassDesc, handles: List<String>): Boolean =
    type.isClassOrInterface && type.descriptorString().drop(1).dropLast(1) in handles

private fun primitiveOf(type: ClassDesc): PrimitiveKind = PrimitiveKind(typeNameOf(type), type.descriptorString())

private fun typeNameOf(primitive: ClassDesc): TypeName =
    TypeName.get(primitive.resolveConstantDesc(MethodHandles.lookup()) as Class<*>)
