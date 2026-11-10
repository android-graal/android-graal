package org.androidgraal.handles.generator.emit

import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.JavaFile
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.ParameterSpec
import com.palantir.javapoet.ParameterizedTypeName
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import com.palantir.javapoet.WildcardTypeName
import org.androidgraal.handles.generator.model.ArrayKind
import org.androidgraal.handles.generator.model.Forwarded
import org.androidgraal.handles.generator.model.HandleModel
import org.androidgraal.handles.generator.model.Kind
import org.androidgraal.handles.generator.model.PrimitiveKind
import org.androidgraal.handles.generator.model.Root
import org.androidgraal.handles.generator.model.TextKind
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

private const val ID = "id"

internal class ImageSources(model: HandleModel) : Sources(model) {
    override fun render(): List<JavaFile> = model.roots.map(::bindingOf) + table()

    private fun bindingOf(root: Root): JavaFile {
        val binding = JavaNames.bindingOf(root.name)
        val type = TypeSpec.classBuilder(binding).addModifiers(PUBLIC, FINAL)
            .addFields(
                root.levels.map { FieldSpec.builder(JavaNames.J_CLASS, classFieldOf(it), PRIVATE, STATIC).build() },
            )
            .addFields(
                root.forwarded.map {
                    FieldSpec.builder(JavaNames.J_METHOD_ID, methodFieldOf(it), PRIVATE, STATIC).build()
                },
            )
            .addMethod(privateConstructor)
            .addMethod(initializeOf(root))
            .addMethods(root.forwarded.map(::bindingMethodOf))
        return javaFileOf(binding, type.build())
    }

    private fun initializeOf(root: Root): MethodSpec {
        val initialize = MethodSpec.methodBuilder("initialize")
            .addModifiers(PUBLIC, STATIC)
            .addParameter(JavaNames.JNI_ENV, "env")
        for (level in root.levels) {
            val endPoints = JavaNames.endPointsOf(root.name, level).reflectionName().replace('.', '/')
            if (level != null) initialize.beginControlFlow("if (\$T.SDK_INT >= \$L)", JavaNames.BUILD_VERSION, level)
            initialize.addStatement("\$N = \$T.findClass(env, \$S)", classFieldOf(level), JavaNames.ART, endPoints)
            if (level != null) initialize.endControlFlow()
        }
        return initialize.build()
    }

    private fun bindingMethodOf(forwarded: Forwarded): MethodSpec {
        val wire = forwarded.wire
        val endPoints = classFieldOf(forwarded.level)
        val spec = MethodSpec.methodBuilder(wire.endPoint)
            .addModifiers(PUBLIC, STATIC)
            .returns(typeOf(wire.result))
            .addParameter(TypeName.LONG, ID)
            .addParameters(
                wire.parameters.mapIndexed { index, kind ->
                    ParameterSpec.builder(kind.type, "a$index").build()
                },
            )
            .addStatement("\$T thread = \$T.thread()", JavaNames.ART_THREAD, JavaNames.ART)
            .addStatement("\$T method = \$N", JavaNames.J_METHOD_ID, methodFieldOf(forwarded))
            .beginControlFlow("if (method.isNull())")
            .addStatement(
                "method = \$T.staticMethod(thread.env, \$N, \$S, \$S)",
                JavaNames.ART,
                endPoints,
                wire.endPoint,
                wire.jniDescriptor,
            )
            .addStatement("\$N = method", methodFieldOf(forwarded))
            .endControlFlow()
            .addStatement(
                "\$T args = \$T.get(\$L, \$T.class)",
                JavaNames.J_VALUE,
                JavaNames.STACK_VALUE,
                1 + wire.parameters.size,
                JavaNames.J_VALUE,
            )
            .addStatement("args.addressOf(0).setLong(\$N)", ID)
        wire.parameters.forEachIndexed { index, kind ->
            spec.addStatement("args.addressOf(${index + 1}).set\$L(a$index)", capitalized(kind.type))
        }
        returnOf(spec, wire.result, endPoints)
        return spec.build()
    }

    private fun returnOf(spec: MethodSpec.Builder, kind: Kind, endPoints: String) {
        when (kind) {
            is PrimitiveKind -> spec.addStatement(
                "return \$T.call\$L(thread, \$N, method, args)",
                JavaNames.ART,
                capitalized(kind.type),
                endPoints,
            )

            TextKind -> blockOf(spec, endPoints)
                .addStatement("String result = \$T.readStringBlock(r)", JavaNames.IMAGE_TRANSPORT)
                .addStatement("\$T.free(r)", JavaNames.IMAGE_TRANSPORT)
                .addStatement("return result")

            is ArrayKind -> blockOf(spec, endPoints)
                .addStatement(
                    "\$T result = r == 0 ? null : new \$T[\$T.getBlockLength(r)]",
                    ArrayTypeName.of(kind.component),
                    kind.component,
                    JavaNames.IMAGE_TRANSPORT,
                )
                .addStatement("\$T.read(r + \$T.HEADER, result)", JavaNames.IMAGE_TRANSPORT, JavaNames.BLOCKS)
                .addStatement("\$T.free(r)", JavaNames.IMAGE_TRANSPORT)
                .addStatement("return result")
        }
    }

    private fun blockOf(spec: MethodSpec.Builder, endPoints: String): MethodSpec.Builder {
        return spec.addStatement(
            "\$T view = \$T.callObject(thread, \$N, method, args)",
            JavaNames.J_OBJECT,
            JavaNames.ART,
            endPoints,
        )
            .addStatement("long r = \$T.address(thread.env, view)", JavaNames.ART)
            .addStatement("\$T.deleteLocal(thread.env, view)", JavaNames.ART)
    }

    private fun table(): JavaFile {
        val factory = ParameterizedTypeName.get(JavaNames.FACTORY, WildcardTypeName.subtypeOf(Any::class.java))
        val factories = CodeBlock.join(model.roots.map { CodeBlock.of("\$T::new", it.name) }, ", ")
        val initialize = MethodSpec.methodBuilder("initialize")
            .addModifiers(PUBLIC, STATIC)
            .addParameter(JavaNames.JNI_ENV, "env")
        model.roots.forEach { initialize.addStatement("\$T.initialize(env)", JavaNames.bindingOf(it.name)) }
        val type = TypeSpec.classBuilder(JavaNames.IMAGE_TABLE).addModifiers(PUBLIC, FINAL)
            .addField(
                FieldSpec.builder(JavaNames.HANDLES, "HANDLES", PRIVATE, STATIC, FINAL)
                    .initializer("\$T.handles()", JavaNames.ART)
                    .build(),
            )
            .addField(
                FieldSpec.builder(ArrayTypeName.of(factory), "FACTORIES", PRIVATE, STATIC, FINAL)
                    .initializer("{\$L}", factories)
                    .build(),
            )
            .addField(fingerprintField)
            .addMethod(privateConstructor)
            .addMethod(initialize.build())
            .addMethod(
                MethodSpec.methodBuilder("receive")
                    .addModifiers(PUBLIC, STATIC)
                    .returns(Any::class.java)
                    .addParameter(TypeName.LONG, "tag")
                    .addStatement(
                        "return HANDLES.receive(\$T.idOf(tag), FACTORIES[\$T.ordinalOf(tag)])",
                        JavaNames.HANDLES,
                        JavaNames.HANDLES,
                    )
                    .build(),
            )
            .addMethod(
                MethodSpec.methodBuilder("send")
                    .addModifiers(PUBLIC, STATIC)
                    .returns(TypeName.LONG)
                    .addParameter(Any::class.java, "object")
                    .addStatement("return HANDLES.send(object)")
                    .build(),
            )
        return javaFileOf(JavaNames.IMAGE_TABLE, type.build())
    }
}

private fun typeOf(kind: Kind): TypeName = when (kind) {
    is PrimitiveKind -> kind.type
    TextKind -> TypeName.get(String::class.java)
    is ArrayKind -> ArrayTypeName.of(kind.component)
}

private fun classFieldOf(level: Int?): String = "endPoints" + (level ?: "")

private fun methodFieldOf(forwarded: Forwarded): String = forwarded.wire.endPoint + "Method"
