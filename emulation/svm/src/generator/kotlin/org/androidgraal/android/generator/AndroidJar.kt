package org.androidgraal.android.generator

import java.lang.classfile.AttributedElement
import java.lang.classfile.Attributes
import java.lang.classfile.ClassFile
import java.lang.classfile.ClassModel
import java.lang.classfile.FieldModel
import java.lang.reflect.AccessFlag
import java.util.zip.ZipFile

internal class AndroidType(
    val internalName: String,
    val outer: String?,
    val simpleName: String,
    val isProtected: Boolean,
    val isInterface: Boolean,
    val isDeprecated: Boolean,
    val superclass: String?,
    val interfaces: List<String>,
    val hasVisibleNoArgConstructor: Boolean,
    val constants: List<Constant>,
) {
    val outermost: String = internalName.substringBefore('$')
}

internal class Constant(
    val name: String,
    val descriptor: String,
    val value: Any,
    val isProtected: Boolean,
    val isDeprecated: Boolean,
)

internal fun androidTypesOf(jar: ZipFile): Map<String, AndroidType> {
    val types = sortedMapOf<String, AndroidType>()
    for (entry in jar.entries()) {
        if (!entry.name.startsWith("android/") || !entry.name.endsWith(".class")) continue
        val model = ClassFile.of().parse(jar.getInputStream(entry).use { it.readAllBytes() })
        androidTypeOf(model)?.let { types[it.internalName] = it }
    }
    return types
}

private fun androidTypeOf(model: ClassModel): AndroidType? {
    val internalName = model.thisClass().asInternalName()
    val nesting = model.findAttribute(Attributes.innerClasses()).orElse(null)
        ?.classes()?.firstOrNull { it.innerClass().asInternalName() == internalName }
    val flags = nesting?.flags() ?: model.flags().flags()
    if (AccessFlag.PUBLIC !in flags && AccessFlag.PROTECTED !in flags) return null
    if (nesting != null && (nesting.outerClass().isEmpty || nesting.innerName().isEmpty)) return null
    return AndroidType(
        internalName = internalName,
        outer = nesting?.outerClass()?.get()?.asInternalName(),
        simpleName = nesting?.innerName()?.get()?.stringValue() ?: internalName.substringAfterLast('/'),
        isProtected = AccessFlag.PROTECTED in flags,
        isInterface = AccessFlag.INTERFACE in model.flags().flags(),
        isDeprecated = isDeprecated(model),
        superclass = model.superclass().map { it.asInternalName() }.orElse(null),
        interfaces = model.interfaces().map { it.asInternalName() },
        hasVisibleNoArgConstructor = model.methods().any { method ->
            val methodFlags = method.flags().flags()
            method.methodName().stringValue() == "<init>" && method.methodType().stringValue() == "()V" &&
                (AccessFlag.PUBLIC in methodFlags || AccessFlag.PROTECTED in methodFlags)
        },
        constants = model.fields().mapNotNull(::constantOf),
    )
}

private fun constantOf(field: FieldModel): Constant? {
    val flags = field.flags().flags()
    val visible = AccessFlag.PUBLIC in flags || AccessFlag.PROTECTED in flags
    if (!visible || AccessFlag.STATIC !in flags || AccessFlag.FINAL !in flags) return null
    val value = field.findAttribute(Attributes.constantValue()).orElse(null) ?: return null
    return Constant(
        name = field.fieldName().stringValue(),
        descriptor = field.fieldType().stringValue(),
        value = value.constant().constantValue(),
        isProtected = AccessFlag.PROTECTED in flags,
        isDeprecated = isDeprecated(field),
    )
}

private fun isDeprecated(element: AttributedElement): Boolean {
    if (element.findAttribute(Attributes.deprecated()).isPresent) return true
    return element.findAttribute(Attributes.runtimeVisibleAnnotations()).map { attribute ->
        attribute.annotations().any { it.className().stringValue() == "Ljava/lang/Deprecated;" }
    }.orElse(false)
}
