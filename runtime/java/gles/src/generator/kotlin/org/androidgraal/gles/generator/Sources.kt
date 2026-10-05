package org.androidgraal.gles.generator

import com.palantir.javapoet.AnnotationSpec
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.JavaFile
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.ParameterSpec
import com.palantir.javapoet.TypeSpec
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.NATIVE
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

internal object Sources {
    fun bindingsOf(library: ClassName, functions: List<CFunction>): JavaFile {
        val methods = functions.map { function ->
            MethodSpec.methodBuilder(function.name)
                .addAnnotation(annotationOf(JavaNames.C_FUNCTION, function.name))
                .addModifiers(PUBLIC, STATIC, NATIVE)
                .returns(function.result.bindingType)
                .addParameters(function.params.map { ParameterSpec.builder(it.type.bindingType, it.name).build() })
                .build()
        }
        val type = TypeSpec.classBuilder(library)
            .addAnnotation(annotationOf(JavaNames.C_LIBRARY, library.simpleName()))
            .addModifiers(PUBLIC, FINAL)
            .addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build())
            .addMethods(methods)
            .build()
        return javaFileOf(library, type)
    }

    fun imageOf(name: ClassName, superclass: ClassName, android: AndroidClass, members: List<Member>): JavaFile {
        val fields = android.constants.map {
            FieldSpec.builder(it.type, it.name, PUBLIC, STATIC, FINAL).initializer("\$L", literalOf(it.value)).build()
        }
        val type = TypeSpec.classBuilder(name)
            .addAnnotation(JavaNames.EMULATED)
            .addModifiers(PUBLIC)
            .superclass(superclass)
            .addFields(fields)
            .addMethod(MethodSpec.constructorBuilder().addModifiers(PUBLIC).build())
            .addMethods(members.map(::methodOf))
            .build()
        return javaFileOf(name, type)
    }

    private fun methodOf(member: Member): MethodSpec {
        val method = MethodSpec.methodBuilder(member.name)
            .addModifiers(PUBLIC, STATIC)
            .returns(member.returns)
            .addParameters(member.parameters)
        val statement = member.result.statementOf(member.invocation)
        if (member.resources.isEmpty()) return method.addStatement(statement).build()
        return method.beginControlFlow("try (\$>\$>\$L\$<\$<)", CodeBlock.join(member.resources, ";\n"))
            .addStatement(statement)
            .endControlFlow()
            .build()
    }

    private fun literalOf(value: Number): String {
        if (value is Long) return "${value}L"
        return if (value.toInt() in 0..9) "$value" else "0x%04X".format(value.toInt())
    }

    private fun annotationOf(annotation: ClassName, value: String): AnnotationSpec =
        AnnotationSpec.builder(annotation).addMember("value", "\$S", value).build()

    private fun javaFileOf(name: ClassName, type: TypeSpec): JavaFile {
        return JavaFile.builder(name.packageName(), type)
            .addFileComment("Generated android.jar and the NDK's GLES headers.")
            .skipJavaLangImports(true)
            .indent("    ")
            .build()
    }
}
