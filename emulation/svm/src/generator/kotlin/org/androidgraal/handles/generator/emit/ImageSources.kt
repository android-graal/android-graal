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
import org.androidgraal.handles.generator.model.HandleKind
import org.androidgraal.handles.generator.model.HandleModel
import org.androidgraal.handles.generator.model.Kind
import org.androidgraal.handles.generator.model.ParameterKind
import org.androidgraal.handles.generator.model.PrimitiveKind
import org.androidgraal.handles.generator.model.Root
import org.androidgraal.handles.generator.model.TextKind
import org.androidgraal.handles.generator.model.VoidKind
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

private const val HANDLE = "handle"

internal class ImageSources(model: HandleModel) : Sources(model) {
    private val bound = model.roots.filter { it.forwarded.isNotEmpty() }

    override fun render(): List<JavaFile> =
        bound.map(::bindingOf) + model.roots.filter { it.isAbstract }.map(::receivedOf) + table()

    private fun receivedOf(root: Root): JavaFile {
        val received = JavaNames.receivedOf(root.name)
        val constructor = MethodSpec.constructorBuilder()
            .addModifiers(PUBLIC)
            .addParameter(TypeName.LONG, "id")
            .addStatement("super(id)")
            .build()
        val type = TypeSpec.classBuilder(received).addModifiers(PUBLIC, FINAL)
            .superclass(root.name)
            .addMethod(constructor)
        return javaFileOf(received, type.build())
    }

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
            .addMethods(root.forwarded.map { bindingMethodOf(root, it) })
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

    private fun bindingMethodOf(root: Root, forwarded: Forwarded): MethodSpec {
        val wire = forwarded.wire
        val endPoints = classFieldOf(forwarded.level)
        val spec = MethodSpec.methodBuilder(wire.endPoint)
            .addModifiers(PUBLIC, STATIC)
            .returns(typeOf(wire.result))
            .addParameter(root.name, HANDLE)
            .addParameters(
                wire.parameters.mapIndexed { index, kind ->
                    ParameterSpec.builder(parameterTypeOf(kind), "a$index").build()
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
        val texts = wire.parameters.indices.filter { wire.parameters[it] == TextKind }
        val names = BlockNames(texts)
        texts.forEach {
            spec.addStatement("int n$it = a$it == null ? \$T.NULL : a$it.length()", JavaNames.BLOCKS)
        }
        texts.forEach { spec.addStatement("int ${names.bytes(it)} = \$T.size(n$it, 2)", JavaNames.BLOCKS) }
        texts.forEach {
            spec.addStatement(
                "long ${names.transport(it)} = a$it == null ? 0 : \$T.alloc(${names.bytes(it)})",
                JavaNames.IMAGE_TRANSPORT,
            )
        }
        if (texts.isNotEmpty()) spec.beginControlFlow("try")
        texts.forEach {
            val transport = names.transport(it)
            spec.beginControlFlow("if ($transport != 0)")
                .addStatement("\$T.setBlockHeader($transport, n$it, 2)", JavaNames.IMAGE_TRANSPORT)
                .addStatement("\$T.write(a$it, $transport + \$T.HEADER)", JavaNames.IMAGE_TRANSPORT, JavaNames.BLOCKS)
                .endControlFlow()
        }
        val slots = wire.parameters.sumOf { if (it == TextKind) 2 else 1 }
        spec.addStatement(
            "\$T args = \$T.get(\$L, \$T.class)",
            JavaNames.J_VALUE,
            JavaNames.STACK_VALUE,
            1 + slots,
            JavaNames.J_VALUE,
        )
            .addStatement("args.addressOf(0).setLong(\$N.foreignId())", HANDLE)
        var slot = 1
        wire.parameters.forEachIndexed { index, kind ->
            when (kind) {
                is PrimitiveKind -> spec.addStatement(
                    "args.addressOf(${slot++}).set\$L(a$index)",
                    capitalized(kind.type),
                )

                TextKind -> spec.addStatement("args.addressOf(${slot++}).setLong(${names.transport(index)})")
                    .addStatement("args.addressOf(${slot++}).setInt(${names.bytes(index)})")

                is HandleKind -> spec.addStatement(
                    "args.addressOf(${slot++}).setLong(\$T.send(a$index))",
                    JavaNames.IMAGE_TABLE,
                )
            }
        }
        val handles = wire.parameters.indices.filter { wire.parameters[it] is HandleKind }
        val fenced = listOf(HANDLE) + handles.map { "a$it" }
        returnOf(spec, wire.result, endPoints, fenced)
        if (texts.isNotEmpty()) {
            spec.nextControlFlow("finally")
            texts.forEach {
                spec.beginControlFlow("if (${names.transport(it)} != 0)")
                    .addStatement("\$T.free(${names.transport(it)})", JavaNames.IMAGE_TRANSPORT)
                    .endControlFlow()
            }
            spec.endControlFlow()
        }
        return spec.build()
    }

    private fun returnOf(spec: MethodSpec.Builder, kind: Kind, endPoints: String, fenced: List<String>) {
        when (kind) {
            is PrimitiveKind -> {
                spec.addStatement(
                    "\$T r = \$T.call\$L(thread, \$N, method, args)",
                    kind.type,
                    JavaNames.ART,
                    capitalized(kind.type),
                    endPoints,
                )
                fenceOf(spec, fenced).addStatement("return r")
            }

            VoidKind -> {
                spec.addStatement("\$T.callVoid(thread, \$N, method, args)", JavaNames.ART, endPoints)
                fenceOf(spec, fenced)
            }

            is HandleKind -> {
                spec.addStatement("long r = \$T.callLong(thread, \$N, method, args)", JavaNames.ART, endPoints)
                fenceOf(spec, fenced).addStatement("return (\$T) \$T.receive(r)", kind.type, JavaNames.IMAGE_TABLE)
            }

            TextKind -> blockOf(spec, endPoints, fenced)
                .addStatement("String result = \$T.readStringBlock(r)", JavaNames.IMAGE_TRANSPORT)
                .addStatement("\$T.free(r)", JavaNames.IMAGE_TRANSPORT)
                .addStatement("return result")

            is ArrayKind -> blockOf(spec, endPoints, fenced)
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

    private fun blockOf(spec: MethodSpec.Builder, endPoints: String, fenced: List<String>): MethodSpec.Builder {
        spec.addStatement(
            "\$T view = \$T.callObject(thread, \$N, method, args)",
            JavaNames.J_OBJECT,
            JavaNames.ART,
            endPoints,
        )
            .addStatement("long r = \$T.address(thread.env, view)", JavaNames.ART)
            .addStatement("\$T.deleteLocal(thread.env, view)", JavaNames.ART)
        return fenceOf(spec, fenced)
    }

    private fun fenceOf(spec: MethodSpec.Builder, fenced: List<String>): MethodSpec.Builder {
        fenced.forEach { spec.addStatement("\$T.reachabilityFence(\$N)", JavaNames.REFERENCE, it) }
        return spec
    }

    private fun table(): JavaFile {
        val factory = ParameterizedTypeName.get(JavaNames.FACTORY, WildcardTypeName.subtypeOf(Any::class.java))
        val received = model.roots.map { if (it.isAbstract) JavaNames.receivedOf(it.name) else it.name }
        val factories = CodeBlock.join(received.map { CodeBlock.of("\$T::new", it) }, ", ")
        val initialize = MethodSpec.methodBuilder("initialize")
            .addModifiers(PUBLIC, STATIC)
            .addParameter(JavaNames.JNI_ENV, "env")
        bound.forEach { initialize.addStatement("\$T.initialize(env)", JavaNames.bindingOf(it.name)) }
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
    VoidKind -> TypeName.VOID
    is HandleKind -> kind.type
    TextKind -> TypeName.get(String::class.java)
    is ArrayKind -> ArrayTypeName.of(kind.component)
}

private fun parameterTypeOf(kind: ParameterKind): TypeName = when (kind) {
    is PrimitiveKind -> kind.type
    TextKind -> TypeName.get(String::class.java)
    is HandleKind -> kind.type
}

private fun classFieldOf(level: Int?): String = "endPoints" + (level ?: "")

private fun methodFieldOf(forwarded: Forwarded): String = forwarded.wire.endPoint + "Method"
