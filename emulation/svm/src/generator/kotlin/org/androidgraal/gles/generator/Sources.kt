package org.androidgraal.gles.generator

import com.palantir.javapoet.AnnotationSpec
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.JavaFile
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.ParameterSpec
import com.palantir.javapoet.TypeSpec
import com.palantir.javapoet.TypeVariableName
import javax.lang.model.element.Modifier
import javax.lang.model.element.Modifier.ABSTRACT
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.NATIVE
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

private const val EGL_GET_PROC_ADDRESS = "eglGetProcAddress"
private const val UNAVAILABLE = " is not available on this device"

private val DECODES_STANDARD_UTF8: AnnotationSpec = AnnotationSpec.builder(JavaNames.ADAPTED)
    .addMember("reason", "\$S", "decodes the driver's bytes as standard UTF-8")
    .addMember("premise", "\$T.STANDARD_UTF8", JavaNames.PREMISE)
    .build()

internal object Sources {
    fun bindingsOf(library: Library, functions: List<CFunction>): JavaFile {
        val methods = functions.map { function ->
            MethodSpec.methodBuilder(function.name)
                .addAnnotation(annotationOf(JavaNames.C_FUNCTION, function.name))
                .addModifiers(PUBLIC, STATIC, NATIVE)
                .returns(function.result.bindingType)
                .addParameters(function.params.map { ParameterSpec.builder(it.type.bindingType, it.name).build() })
                .build()
        }
        val type = TypeSpec.classBuilder(library.binding)
            .addAnnotation(annotationOf(JavaNames.C_LIBRARY, library.name))
            .addModifiers(PUBLIC, FINAL)
            .addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build())
            .addMethods(methods)
            .build()
        return javaFileOf(library.binding, type)
    }

    fun pointersOf(library: Library, functions: List<CFunction>): JavaFile {
        val lookup = MethodSpec.methodBuilder(EGL_GET_PROC_ADDRESS)
            .addAnnotation(annotationOf(JavaNames.C_FUNCTION, EGL_GET_PROC_ADDRESS))
            .addModifiers(PRIVATE, STATIC, NATIVE)
            .returns(JavaNames.POINTER_BASE)
            .addParameter(JavaNames.C_CHAR_POINTER, "procname")
            .build()
        val type = TypeSpec.classBuilder(library.binding)
            .addAnnotation(annotationOf(JavaNames.C_LIBRARY, library.name))
            .addModifiers(PUBLIC, FINAL)
            .addMethod(MethodSpec.constructorBuilder().addModifiers(PRIVATE).build())
            .addMethod(lookup)
            .addMethod(resolveMethod())
        for (function in functions) {
            val pointer = library.binding.nestedClass(function.name.replaceFirstChar(Char::uppercase))
            val call = MethodSpec.methodBuilder("call")
                .addAnnotation(JavaNames.INVOKE_C_FUNCTION_POINTER)
                .addModifiers(PUBLIC, ABSTRACT)
                .returns(function.result.bindingType)
                .addParameters(function.params.map { ParameterSpec.builder(it.type.bindingType, it.name).build() })
                .build()
            type.addType(
                TypeSpec.interfaceBuilder(pointer)
                    .addModifiers(PUBLIC)
                    .addSuperinterface(JavaNames.C_FUNCTION_POINTER)
                    .addMethod(call)
                    .build(),
            )
            type.addField(pointer, function.name, PRIVATE, STATIC)
            type.addMethod(
                MethodSpec.methodBuilder(function.name)
                    .addModifiers(PUBLIC, STATIC)
                    .returns(pointer)
                    .addStatement("\$T pointer = \$N", pointer, function.name)
                    .beginControlFlow("if (pointer.isNull())")
                    .addStatement("pointer = resolve(\$S)", function.name)
                    .addStatement("\$N = pointer", function.name)
                    .endControlFlow()
                    .addStatement("return pointer")
                    .build(),
            )
        }
        return javaFileOf(library.binding, type.build())
    }

    private fun resolveMethod(): MethodSpec {
        val type = TypeVariableName.get("T", JavaNames.POINTER_BASE)
        return MethodSpec.methodBuilder("resolve")
            .addModifiers(PRIVATE, STATIC)
            .addTypeVariable(type)
            .returns(type)
            .addParameter(String::class.java, "name")
            .addStatement("\$T function", JavaNames.POINTER_BASE)
            .beginControlFlow(
                "try (\$T procname = \$T.toCString(name))",
                JavaNames.C_CHAR_POINTER_HOLDER,
                JavaNames.C_TYPE_CONVERSION,
            )
            .addStatement("function = \$N(procname.get())", EGL_GET_PROC_ADDRESS)
            .endControlFlow()
            .beginControlFlow("if (function.isNull())")
            .addStatement("throw new \$T(name + \$S)", UnsatisfiedLinkError::class.java, UNAVAILABLE)
            .endControlFlow()
            .addStatement("return \$T.pointer(function.rawValue())", JavaNames.WORD_FACTORY)
            .build()
    }

    fun imageOf(
        name: ClassName,
        superclass: ClassName,
        constructor: List<Modifier>,
        android: AndroidClass,
        members: List<Member>,
    ): JavaFile {
        val fields = android.constants.map {
            val field = FieldSpec.builder(
                it.type,
                it.name,
                PUBLIC,
                STATIC,
                FINAL,
            ).initializer("\$L", literalOf(it.value))
            if (it.isDeprecated) field.addAnnotation(JavaNames.DEPRECATED)
            field.build()
        }
        val type = TypeSpec.classBuilder(name)
            .addAnnotation(JavaNames.EMULATED)
            .addModifiers(PUBLIC)
            .superclass(superclass)
            .addFields(fields)
            .addMethod(MethodSpec.constructorBuilder().addModifiers(constructor).build())
            .addMethods(members.map(::methodOf))
            .addTypes(CALLBACKS[name].orEmpty().map(::callbackOf))
            .build()
        return javaFileOf(name, type)
    }

    private fun callbackOf(callback: Callback): TypeSpec {
        val method = MethodSpec.methodBuilder(callback.method)
            .addModifiers(PUBLIC, ABSTRACT)
            .addParameters(callback.parameters.map { (type, name) -> ParameterSpec.builder(type, name).build() })
            .build()
        return TypeSpec.interfaceBuilder(callback.name).addModifiers(PUBLIC).addMethod(method).build()
    }

    private fun methodOf(member: Member): MethodSpec {
        val method = MethodSpec.methodBuilder(member.name)
            .addModifiers(PUBLIC, STATIC)
            .returns(member.returns)
            .addParameters(member.parameters)
        if (member.result == Result.TEXT) method.addAnnotation(DECODES_STANDARD_UTF8)
        if (member.resources.isEmpty()) return method.addStatement(member.statement).build()
        return method.beginControlFlow("try (\$>\$>\$L\$<\$<)", CodeBlock.join(member.resources, ";\n"))
            .addStatement(member.statement)
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
            .addFileComment("Generated from android.jar and the NDK's GLES headers.")
            .skipJavaLangImports(true)
            .indent("    ")
            .build()
    }
}
