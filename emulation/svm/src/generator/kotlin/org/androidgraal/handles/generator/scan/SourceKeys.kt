package org.androidgraal.handles.generator.scan

import com.sun.source.tree.ArrayTypeTree
import com.sun.source.tree.IdentifierTree
import com.sun.source.tree.MemberSelectTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.ParameterizedTypeTree
import com.sun.source.tree.PrimitiveTypeTree
import com.sun.source.tree.Tree
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.fail
import java.lang.classfile.MethodSignature
import java.lang.classfile.Signature
import java.lang.constant.ClassDesc

internal fun sourceKeyOf(owner: String, name: String, signature: MethodSignature): String =
    keyOf(name, signature.arguments().map { parameterOf(it, "$owner $name") })

internal fun sourceKeyOf(owner: String, method: MethodTree): String =
    keyOf(method.name.toString(), method.parameters.map { parameterOf(it.type, "$owner ${method.name}") })

private fun keyOf(name: String, parameters: List<String>): String = name + parameters.joinToString(",", "(", ")")

private fun parameterOf(signature: Signature, method: String): String = when (signature) {
    is Signature.BaseTypeSig -> ClassDesc.ofDescriptor(signature.signatureString()).displayName()

    is Signature.ArrayTypeSig -> parameterOf(signature.componentSignature(), method) + "[]"

    is Signature.ClassTypeSig -> signature.classDesc().displayName().substringAfterLast('$')

    is Signature.TypeVarSig -> fail(
        "$method has a parameter of the type variable ${signature.identifier()}, $UNWRITTEN",
    )
}

private fun parameterOf(type: Tree, method: String): String = when (type) {
    is PrimitiveTypeTree -> type.primitiveTypeKind.name.lowercase()
    is ArrayTypeTree -> parameterOf(type.type, method) + "[]"
    is ParameterizedTypeTree -> parameterOf(type.type, method)
    is MemberSelectTree -> type.identifier.toString()
    is IdentifierTree -> type.name.toString()
    else -> fail("$method has the parameter type $type in the SDK source, $UNWRITTEN")
}
