package org.androidgraal.handles.generator.emit

import com.palantir.javapoet.AnnotationSpec
import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.JavaFile
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.ParameterizedTypeName
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import com.palantir.javapoet.WildcardTypeName
import org.androidgraal.handles.generator.model.ArrayKind
import org.androidgraal.handles.generator.model.Forwarded
import org.androidgraal.handles.generator.model.HandleKind
import org.androidgraal.handles.generator.model.HandleModel
import org.androidgraal.handles.generator.model.Kind
import org.androidgraal.handles.generator.model.PrimitiveKind
import org.androidgraal.handles.generator.model.Root
import org.androidgraal.handles.generator.model.TextKind
import org.androidgraal.handles.generator.model.VoidKind
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

internal class ArtSources(model: HandleModel) : Sources(model) {
    private val handlesField: FieldSpec = FieldSpec.builder(JavaNames.HANDLES, "HANDLES", PRIVATE, STATIC, FINAL)
        .initializer("\$T.handles()", JavaNames.IMAGE)
        .build()

    override fun render(): List<JavaFile> = model.roots.flatMap(::endPointsOf) + table()

    private fun endPointsOf(root: Root): List<JavaFile> {
        return root.levels.map { level ->
            val name = JavaNames.endPointsOf(root.name, level)
            val type = TypeSpec.classBuilder(name).addModifiers(FINAL)
                .addField(handlesField)
                .addMethod(privateConstructor)
                .addMethods(root.forwarded.filter { it.level == level }.map { endPointOf(root, it) })
            if (level != null) {
                type.addAnnotation(
                    AnnotationSpec.builder(JavaNames.REQUIRES_API).addMember("value", "\$L", level).build(),
                )
            }
            javaFileOf(name, type.build())
        }
    }

    private fun endPointOf(root: Root, forwarded: Forwarded): MethodSpec {
        val wire = forwarded.wire
        val texts = wire.parameters.indices.filter { wire.parameters[it] == TextKind }
        val names = BlockNames(texts)
        val arguments = wire.parameters.withIndex()
            .joinToString(", ") { (index, kind) -> if (kind is PrimitiveKind) "a$index" else "v$index" }
        val call = CodeBlock.of("receiver.\$N($arguments)", forwarded.method.name)
        val spec = MethodSpec.methodBuilder(wire.endPoint)
            .addAnnotation(JavaNames.END_POINT)
            .addModifiers(PRIVATE, STATIC)
            .returns(wireTypeOf(wire.result))
            .addParameter(TypeName.LONG, "handle")
        wire.parameters.forEachIndexed { index, kind ->
            when (kind) {
                is PrimitiveKind -> spec.addParameter(kind.type, "a$index")

                TextKind -> spec.addParameter(TypeName.LONG, names.transport(index))
                    .addParameter(TypeName.INT, names.bytes(index))

                is HandleKind -> spec.addParameter(TypeName.LONG, "h$index")
            }
        }
        spec.beginControlFlow("try")
        if (texts.isNotEmpty()) threadOf(spec)
        for (text in texts) {
            val view = names.view(text)
            val transport = names.transport(text)
            spec.addStatement(
                "\$T $view = $transport == 0 ? null : \$T.view(thread, $transport, ${names.bytes(text)})",
                JavaNames.BYTE_BUFFER,
                JavaNames.ART_TRANSPORT,
            )
                .addStatement(
                    "String v$text = $view == null ? null : \$T.readStringBlock($view, 0)",
                    JavaNames.ART_TRANSPORT,
                )
        }
        wire.parameters.forEachIndexed { index, kind ->
            if (kind is HandleKind) spec.addStatement("\$T v$index = HANDLES.receive(h$index, null)", kind.type)
        }
        spec.addStatement("\$T receiver = HANDLES.object(handle)", root.name)
        val result = wire.result
        if (texts.isEmpty() && (result == TextKind || result is ArrayKind)) threadOf(spec)
        when (result) {
            is PrimitiveKind -> spec.addStatement("return \$L", call)

            VoidKind -> spec.addStatement("\$L", call)

            is HandleKind -> spec.addStatement("return \$T.tagOf(\$L)", JavaNames.ART_TABLE, call)

            TextKind -> blockOf(spec, TypeName.get(String::class.java), call)
                .addStatement(
                    "\$T r = \$T.allocBlock(thread, result.length(), 2)",
                    JavaNames.BYTE_BUFFER,
                    JavaNames.ART_TRANSPORT,
                )
                .addStatement("\$T.write(result, r, \$T.HEADER)", JavaNames.ART_TRANSPORT, JavaNames.BLOCKS)
                .addStatement("return r")

            is ArrayKind -> blockOf(spec, ArrayTypeName.of(result.component), call)
                .addStatement(
                    "\$T r = \$T.allocBlock(thread, result.length, \$L)",
                    JavaNames.BYTE_BUFFER,
                    JavaNames.ART_TRANSPORT,
                    result.elementBytes,
                )
                .addStatement("r.position(\$T.HEADER)", JavaNames.BLOCKS)
                .addStatement("r.as\$LBuffer().put(result)", capitalized(result.component))
                .addStatement("return r")
        }
        spec.nextControlFlow("catch (\$T t)", Throwable::class.java)
            .addStatement("\$T.raiseImageException(t)", JavaNames.IMAGE)
        zeroOf(wire.result)?.let { spec.addStatement("return \$L", it) }
        return spec.endControlFlow().build()
    }

    private fun threadOf(spec: MethodSpec.Builder) {
        spec.addStatement("\$T thread = \$T.thread()", JavaNames.IMAGE_THREAD, JavaNames.IMAGE)
    }

    private fun blockOf(spec: MethodSpec.Builder, type: TypeName, call: CodeBlock): MethodSpec.Builder {
        return spec.addStatement("\$T result = \$L", type, call)
            .beginControlFlow("if (result == null)")
            .addStatement("return null")
            .endControlFlow()
    }

    private fun table(): JavaFile {
        val ordinals = CodeBlock.builder()
        val byLevel = model.roots.withIndex().groupBy { it.value.level }.entries.sortedBy { it.key ?: 0 }
        for ((level, roots) in byLevel) {
            if (level != null) ordinals.beginControlFlow("if (\$T.SDK_INT >= \$L)", JavaNames.BUILD_VERSION, level)
            for ((ordinal, root) in roots) {
                ordinals.addStatement("ORDINALS.put(\$T.class, \$L)", root.name, ordinal)
            }
            if (level != null) ordinals.endControlFlow()
        }
        val classes = ParameterizedTypeName.get(
            ClassName.get(Class::class.java),
            WildcardTypeName.subtypeOf(Any::class.java),
        )
        val table = ParameterizedTypeName.get(
            JavaNames.IDENTITY_HASH_MAP,
            classes,
            ClassName.get(Int::class.javaObjectType),
        )
        val type = TypeSpec.classBuilder(JavaNames.ART_TABLE).addModifiers(PUBLIC, FINAL)
            .addField(handlesField)
            .addField(
                FieldSpec.builder(table, "ORDINALS", PRIVATE, STATIC, FINAL)
                    .initializer("new \$T<>()", JavaNames.IDENTITY_HASH_MAP)
                    .build(),
            )
            .addField(fingerprintField)
            .addStaticBlock(ordinals.build())
            .addMethod(privateConstructor)
            .addMethod(
                MethodSpec.methodBuilder("tagOf")
                    .addModifiers(PUBLIC, STATIC)
                    .returns(TypeName.LONG)
                    .addParameter(Any::class.java, "object")
                    .beginControlFlow("if (object == null)")
                    .addStatement("return 0")
                    .endControlFlow()
                    .addStatement("int ordinal = ordinalOf(object.getClass())")
                    .addStatement("return \$T.tagOf(HANDLES.send(object), ordinal)", JavaNames.HANDLES)
                    .build(),
            )
            .addMethod(
                MethodSpec.methodBuilder("ordinalOf")
                    .addModifiers(PRIVATE, STATIC)
                    .returns(TypeName.INT)
                    .addParameter(classes, "type")
                    .beginControlFlow("for (\$T c = type; c != null; c = c.getSuperclass())", classes)
                    .addStatement("Integer known = ORDINALS.get(c)")
                    .beginControlFlow("if (known != null)")
                    .addStatement("return known")
                    .endControlFlow()
                    .endControlFlow()
                    .addStatement(
                        "throw new \$T(type.getName() + \$S)",
                        IllegalArgumentException::class.java,
                        " extends no class of the handle table",
                    )
                    .build(),
            )
        return javaFileOf(JavaNames.ART_TABLE, type.build())
    }
}

private fun wireTypeOf(kind: Kind): TypeName = when (kind) {
    is PrimitiveKind -> kind.type
    VoidKind -> TypeName.VOID
    is HandleKind -> TypeName.LONG
    TextKind, is ArrayKind -> JavaNames.BYTE_BUFFER
}

private fun zeroOf(kind: Kind): String? = when (kind) {
    is PrimitiveKind -> if (kind.type == TypeName.BOOLEAN) "false" else "0"
    VoidKind -> null
    is HandleKind -> "0"
    TextKind, is ArrayKind -> "null"
}
