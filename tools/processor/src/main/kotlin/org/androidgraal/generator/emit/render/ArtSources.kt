package org.androidgraal.generator.emit.render

import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.ParameterizedTypeName
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import com.palantir.javapoet.TypeVariableName
import com.palantir.javapoet.WildcardTypeName
import org.androidgraal.common.Side
import org.androidgraal.generator.emit.JavaNames
import org.androidgraal.generator.emit.codec.SenderBody
import org.androidgraal.generator.emit.codec.Wire
import org.androidgraal.generator.emit.codec.objectOf
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.model.Proxy
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.NATIVE
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

internal class ArtSources(model: BridgeModel) : Sources(model, Side.ART) {
    override val threadData: ClassName = JavaNames.IMAGE_THREAD_DATA

    override val pending: CodeBlock = CodeBlock.builder().addStatement("thread.throwPendingException()").build()

    override val raise: CodeBlock = CodeBlock.of("\$T.raiseException(t)", JavaNames.IMAGE)

    fun resources(): Map<String, String> = mapOf(
        "META-INF/services/${JavaNames.CONTRACT.reflectionName()}" to
            "${model.contractClass.reflectionName()}\n",
    )

    override fun contractClass(): TypeSpec {
        val contract = model.contractClass.canonicalName()
        val type = TypeSpec.classBuilder(model.contractClass)
            .addModifiers(PUBLIC, FINAL)
            .addSuperinterface(JavaNames.CONTRACT)
            .addField(fingerprintField)
            .addField(
                FieldSpec.builder(JavaNames.ATOMIC_BOOLEAN, "INITIALIZED", PRIVATE, STATIC, FINAL)
                    .initializer("new \$T()", JavaNames.ATOMIC_BOOLEAN)
                    .build(),
            )
        type.addField(handlesField)
        type.addMethod(MethodSpec.constructorBuilder().addModifiers(PUBLIC).build())
        type.addMethod(
            overrideOf("initialize", TypeName.VOID)
                .beginControlFlow("if (!INITIALIZED.compareAndSet(false, true))")
                .addStatement(
                    "throw new \$T(\$S)",
                    IllegalStateException::class.java,
                    "$contract.initialize() ran already",
                )
                .endControlFlow()
                .addStatement("\$T thread = \$T.thread()", JavaNames.IMAGE_THREAD_DATA, JavaNames.IMAGE)
                .addStatement("int result = initialize(thread.isolateThread, FINGERPRINT)")
                .addCode(pending)
                .beginControlFlow("if (result == -1)")
                .addStatement(
                    "throw new \$T(\$S)",
                    IllegalStateException::class.java,
                    "the image's bridge of $contract differs from ART's",
                )
                .endControlFlow()
                .build(),
        )
        type.addMethod(roots()).addMethod(createOf(contract))
        type.addMethod(
            MethodSpec.methodBuilder("initialize")
                .addModifiers(PRIVATE, STATIC, NATIVE)
                .returns(TypeName.INT)
                .addParameter(TypeName.LONG, "thread")
                .addParameter(TypeName.LONG, "fingerprint")
                .build(),
        )
        model.roots.forEach {
            type.addMethod(
                MethodSpec.methodBuilder(model.instantiateMethod(it))
                    .addModifiers(PRIVATE, STATIC, NATIVE)
                    .returns(TypeName.LONG)
                    .addParameter(TypeName.LONG, "thread")
                    .build(),
            )
        }
        return type.build()
    }

    override fun endPointOf(wire: Wire): MethodSpec.Builder {
        val endPoint = MethodSpec.methodBuilder(wire.name)
            .addAnnotation(JavaNames.END_POINT)
            .addModifiers(PRIVATE, STATIC)
            .returns(wire.result.resultType)
        wire.slots.forEach { endPoint.addParameter(it.type, it.name) }
        return endPoint
    }

    override fun proxyFieldsOf(wires: List<Wire>): List<FieldSpec> = emptyList()

    override fun proxyMethodsOf(proxy: Proxy, wires: List<Wire>): List<MethodSpec> =
        overridesOf(wires) + wires.map { nativeOf(it) }

    override fun sendCall(body: SenderBody, wire: Wire, values: List<CodeBlock>): CodeBlock {
        val arguments = listOf(CodeBlock.of("thread.isolateThread"), CodeBlock.of("id")) + values
        return CodeBlock.of("\$N(\$L)", wire.name, CodeBlock.join(arguments, ", "))
    }

    private fun roots(): MethodSpec {
        val classes = model.roots.map { CodeBlock.of("\$T.class", ClassName.get(it)) }
        return overrideOf("roots", ArrayTypeName.of(CLASS_OF_ANY))
            .addStatement("return new \$T[] {\$L}", CLASS_OF_ANY, CodeBlock.join(classes, ", "))
            .build()
    }

    private fun createOf(contract: String): MethodSpec {
        val variable = TypeVariableName.get("T")
        val method = overrideOf("create", variable).addTypeVariable(variable)
            .addParameter(ParameterizedTypeName.get(ClassName.get(Class::class.java), variable), "root")
        model.roots.forEach { root ->
            method.beginControlFlow("if (root == \$T.class)", ClassName.get(root))
                .addStatement("\$T thread = \$T.thread()", JavaNames.IMAGE_THREAD_DATA, JavaNames.IMAGE)
                .addStatement("long r = \$N(thread.isolateThread)", model.instantiateMethod(root))
                .addCode(pending)
                .addStatement("return root.cast(\$L)", objectOf(model, Side.ART, root, "r"))
                .endControlFlow()
        }
        return method
            .addStatement(
                "throw new \$T(root.getName() + \$S)",
                IllegalArgumentException::class.java,
                " is no root of $contract",
            )
            .build()
    }

    private fun nativeOf(wire: Wire): MethodSpec {
        val method = MethodSpec.methodBuilder(wire.name)
            .addModifiers(PRIVATE, STATIC, NATIVE)
            .returns(wire.result.resultType)
            .addParameter(TypeName.LONG, "thread")
        wire.slots.forEach { method.addParameter(it.type, it.name) }
        return method.build()
    }
}

private val CLASS_OF_ANY: TypeName =
    ParameterizedTypeName.get(ClassName.get(Class::class.java), WildcardTypeName.subtypeOf(Any::class.java))
