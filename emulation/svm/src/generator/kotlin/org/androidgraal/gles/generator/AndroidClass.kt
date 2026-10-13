package org.androidgraal.gles.generator

import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.TypeName
import java.lang.classfile.Attributes
import java.lang.classfile.ClassFile
import java.lang.classfile.FieldModel
import java.lang.constant.ClassDesc
import java.lang.constant.MethodTypeDesc
import java.lang.invoke.MethodHandles
import java.lang.reflect.AccessFlag
import java.util.zip.ZipFile

internal class AndroidClass(val constants: List<Constant>, val methods: List<JavaMethod>)

internal class Constant(val name: String, val type: TypeName, val value: Number, val isDeprecated: Boolean)

internal class JavaMethod(val name: String, val type: MethodTypeDesc) {
    val key: String = name + type.descriptorString()
}

internal fun androidClassOf(jar: ZipFile, name: ClassName): AndroidClass {
    val entry = jar.getEntry(name.reflectionName().replace('.', '/') + ".class")
    val model = ClassFile.of().parse(jar.getInputStream(entry).use { it.readAllBytes() })
    val constants = model.fields().map(::constantOf)
    val methods = model.methods()
        .filter { it.flags().has(AccessFlag.STATIC) }
        .map { JavaMethod(it.methodName().stringValue(), it.methodTypeSymbol()) }
        .sortedWith(compareBy({ it.name }, { it.type.descriptorString() }))
    return AndroidClass(constants, methods)
}

private fun constantOf(field: FieldModel): Constant {
    val value = field.findAttribute(Attributes.constantValue()).get().constant().constantValue()
    val deprecated = field.findAttribute(Attributes.deprecated()).isPresent
    return Constant(field.fieldName().stringValue(), typeNameOf(field.fieldTypeSymbol()), value as Number, deprecated)
}

internal fun typeNameOf(type: ClassDesc): TypeName {
    if (type.isArray) return ArrayTypeName.of(typeNameOf(type.componentType()))
    if (type.isPrimitive) return TypeName.get(type.resolveConstantDesc(MethodHandles.lookup()) as Class<*>)
    val names = type.displayName().split('$')
    return ClassName.get(type.packageName(), names.first(), *names.drop(1).toTypedArray())
}
