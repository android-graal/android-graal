package org.androidgraal.handles.generator.scan

import org.androidgraal.handles.generator.model.Method
import org.androidgraal.handles.generator.model.fail
import java.lang.classfile.Attributes
import java.lang.classfile.ClassFile
import java.lang.classfile.ClassModel
import java.lang.classfile.FieldModel
import java.lang.classfile.MethodModel
import java.lang.classfile.MethodSignature
import java.lang.classfile.Signature
import java.lang.reflect.AccessFlag
import java.util.zip.ZipFile

internal fun classOf(androidJar: ZipFile, internalName: String): ClassModel {
    val entry = androidJar.getEntry("$internalName.class") ?: fail("$internalName is not in android.jar")
    return ClassFile.of().parse(androidJar.getInputStream(entry).use { it.readAllBytes() })
}

internal fun visibleFieldsOf(model: ClassModel): List<FieldModel> =
    model.fields().filter { isVisible(it.flags().flags()) }

internal fun visibleMethodsOf(model: ClassModel): List<MethodModel> =
    model.methods().filter { isVisible(it.flags().flags()) }

internal fun visibleNestedOf(model: ClassModel): Map<String, String> {
    val name = model.thisClass().asInternalName()
    return model.findAttribute(Attributes.innerClasses()).map { attribute ->
        attribute.classes()
            .filter { info -> info.outerClass().map { it.asInternalName() == name }.orElse(false) }
            .filter { isVisible(it.flags()) }
            .associate { it.innerClass().asInternalName() to it.innerName().get().stringValue() }
    }.orElse(emptyMap())
}

internal fun signatureOf(method: MethodModel): MethodSignature {
    return method.findAttribute(Attributes.signature())
        .map { it.asMethodSignature() }
        .orElseGet { MethodSignature.of(method.methodTypeSymbol()) }
}

internal fun methodOf(owner: String, method: MethodModel): Method {
    val name = method.methodName().stringValue()
    val signature = signatureOf(method)
    return Method(
        name = name,
        type = method.methodTypeSymbol(),
        sourceKey = sourceKeyOf(owner, name, signature),
        types = (signature.arguments() + signature.result()).flatMap(::typesOf),
    )
}

private fun isVisible(flags: Set<AccessFlag>): Boolean = AccessFlag.PUBLIC in flags || AccessFlag.PROTECTED in flags

private fun typesOf(signature: Signature): List<String> = when (signature) {
    is Signature.ArrayTypeSig -> typesOf(signature.componentSignature())

    is Signature.ClassTypeSig -> listOf(signature.classDesc().descriptorString().drop(1).dropLast(1)) +
        signature.typeArgs().filterIsInstance<Signature.TypeArg.Bounded>().flatMap { typesOf(it.boundType()) }

    is Signature.BaseTypeSig, is Signature.TypeVarSig -> emptyList()
}
