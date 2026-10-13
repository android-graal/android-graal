package org.androidgraal.android.generator

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.JavaFile
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PROTECTED
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

internal class Skeletons(
    private val nested: Map<String, List<AndroidType>>,
    private val supertypes: Map<String, Supertypes>,
    private val docs: Map<String, TypeDoc>,
) {
    private val extended: Set<String> = supertypes.values.mapNotNull { it.superclass }.toSet()

    var documented: Int = 0
        private set

    fun javaFileOf(type: AndroidType): JavaFile {
        return JavaFile.builder(type.internalName.substringBeforeLast('/').replace('/', '.'), typeOf(type, true))
            .skipJavaLangImports(true)
            .indent("    ")
            .build()
    }

    private fun typeOf(type: AndroidType, outermost: Boolean): TypeSpec {
        val builder =
            if (type.isInterface) TypeSpec.interfaceBuilder(type.simpleName) else TypeSpec.classBuilder(type.simpleName)
        builder.addModifiers(if (type.isProtected) PROTECTED else PUBLIC)
        if (!outermost) builder.addModifiers(STATIC)
        if (!type.isInterface && type.internalName !in extended) builder.addModifiers(FINAL)
        if (type.isDeprecated) builder.addAnnotation(DEPRECATED)
        supertypes[type.internalName]?.let { supertypes ->
            supertypes.superclass?.let { builder.superclass(classNameOf(it)) }
            supertypes.interfaces.forEach { builder.addSuperinterface(classNameOf(it)) }
        }
        val doc = docs[type.internalName]
        doc?.comment?.let { builder.addJavadoc("\$L", javadocOf(it)) }
        if (!type.isInterface) builder.addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build())
        for (constant in type.constants) {
            val field = FieldSpec.builder(typeNameOf(constant.descriptor), constant.name)
                .addModifiers(if (constant.isProtected) PROTECTED else PUBLIC, STATIC, FINAL)
                .initializer("\$L", literalOf(constant))
            if (constant.isDeprecated) field.addAnnotation(DEPRECATED)
            doc?.fields?.get(constant.name)?.let {
                field.addJavadoc("\$L", javadocOf(it))
                documented++
            }
            builder.addField(field.build())
        }
        nested[type.internalName].orEmpty().forEach { builder.addType(typeOf(it, false)) }
        return builder.build()
    }

    private fun javadocOf(comment: String): String {
        val lines = comment.trimEnd().lines()
        val indented = lines.filter { it.isNotBlank() }.all { it.startsWith(" ") }
        return lines.joinToString("\n", postfix = "\n") { if (indented) it.removePrefix(" ") else it }
    }

    private companion object {
        val DEPRECATED: ClassName = ClassName.get("java.lang", "Deprecated")

        fun classNameOf(internalName: String): ClassName {
            val names = internalName.substringAfterLast('/').split('$')
            val packageName = internalName.substringBeforeLast('/').replace('/', '.')
            return ClassName.get(packageName, names.first(), *names.drop(1).toTypedArray())
        }

        fun typeNameOf(descriptor: String): TypeName = when (descriptor) {
            "Z" -> TypeName.BOOLEAN
            "B" -> TypeName.BYTE
            "C" -> TypeName.CHAR
            "S" -> TypeName.SHORT
            "I" -> TypeName.INT
            "J" -> TypeName.LONG
            "F" -> TypeName.FLOAT
            "D" -> TypeName.DOUBLE
            "Ljava/lang/String;" -> ClassName.get(String::class.java)
            else -> throw IllegalArgumentException("no constant of type $descriptor")
        }
    }
}
