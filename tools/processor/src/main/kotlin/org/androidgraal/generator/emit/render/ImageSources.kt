package org.androidgraal.generator.emit.render

import com.palantir.javapoet.AnnotationSpec
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.ParameterSpec
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import org.androidgraal.common.Side
import org.androidgraal.generator.emit.JavaNames
import org.androidgraal.generator.emit.codec.SenderBody
import org.androidgraal.generator.emit.codec.Wire
import org.androidgraal.generator.emit.jniName
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.model.Proxy
import org.androidgraal.generator.model.capitalized
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC
import javax.lang.model.element.TypeElement

internal class ImageSources(model: BridgeModel) : Sources(model, Side.SVM) {
    override val threadData: ClassName = JavaNames.ART_THREAD_DATA

    override val pending: CodeBlock = CodeBlock.of("")

    override val raise: CodeBlock = CodeBlock.of("\$T.raiseException(env, t)", JavaNames.ART)

    override fun contractClass(): TypeSpec {
        val contract = model.contractClass
        val type = TypeSpec.classBuilder(contract)
            .addModifiers(PUBLIC, FINAL)
            .addField(fingerprintField)
        if (model.roots.isNotEmpty()) type.addField(handlesField)
        type.addMethod(privateConstructor).addMethod(initializeOf(contract))
        model.roots.forEach { type.addMethod(instantiateOf(contract, it)) }
        return type.build()
    }

    override fun endPointOf(wire: Wire): MethodSpec.Builder {
        val entryPoint = cEntryPointOf(model.generatedClass(wire.proxy), wire.name, wire.result.resultType)
        wire.slots.forEach { entryPoint.addParameter(jniTypeOf(it.type), it.name) }
        return entryPoint
    }

    override fun proxyFieldsOf(wires: List<Wire>): List<FieldSpec> {
        val methodIds = wires.map { FieldSpec.builder(JavaNames.JMETHOD_ID, methodIdOf(it), PRIVATE, STATIC).build() }
        return listOf(FieldSpec.builder(JavaNames.JCLASS, "endPoints", PRIVATE, STATIC).build()) + methodIds
    }

    override fun proxyMethodsOf(proxy: Proxy, wires: List<Wire>): List<MethodSpec> =
        listOf(resolveOf(proxy, wires)) + overridesOf(wires)

    override fun sendCall(body: SenderBody, wire: Wire, values: List<CodeBlock>): CodeBlock {
        val arguments = listOf(CodeBlock.of("id")) + values
        body.inside.addStatement(
            "\$T args = \$T.get(\$L, \$T.class)",
            JavaNames.JVALUE,
            JavaNames.STACK_VALUE,
            arguments.size,
            JavaNames.JVALUE,
        )
        wire.slots.zip(arguments).forEachIndexed { index, (slot, argument) ->
            body.inside.addStatement("args.addressOf(\$L).set\$L(\$L)", index, "${slot.type}".capitalized(), argument)
        }
        return CodeBlock.of(
            "\$T.call\$L(thread, endPoints, \$N, args)",
            JavaNames.ART,
            jniCallOf(wire.result.resultType),
            methodIdOf(wire),
        )
    }

    private fun cEntryPointOf(owner: ClassName, name: String, returns: TypeName): MethodSpec.Builder {
        val entryPoint = AnnotationSpec.builder(JavaNames.C_ENTRY_POINT)
            .addMember("name", "\$S", jniName(owner.reflectionName(), name))
            .build()
        return MethodSpec.methodBuilder(name)
            .addAnnotation(entryPoint)
            .addModifiers(PRIVATE, STATIC)
            .returns(returns)
            .addParameter(JavaNames.JNI_ENV, "env")
            .addParameter(JavaNames.JCLASS, "clazz")
            .addParameter(
                ParameterSpec.builder(TypeName.LONG, "thread").addAnnotation(JavaNames.ISOLATE_THREAD_CONTEXT).build(),
            )
    }

    private fun initializeOf(owner: ClassName): MethodSpec {
        val initialize = cEntryPointOf(owner, "initialize", TypeName.INT)
            .addParameter(TypeName.LONG, "fingerprint")
            .beginControlFlow("try")
            .beginControlFlow("if (fingerprint != FINGERPRINT)")
            .addStatement("return -1")
            .endControlFlow()
        model.proxies.filter { it.implementation == Side.ART }.forEach {
            initialize.addStatement("\$T.resolve(env)", model.generatedClass(it))
        }
        return initialize.addStatement("return 1")
            .nextControlFlow("catch (\$T t)", Throwable::class.java)
            .addStatement("\$L", raise)
            .addStatement("return 0")
            .endControlFlow()
            .build()
    }

    private fun instantiateOf(owner: ClassName, type: TypeElement): MethodSpec {
        return cEntryPointOf(owner, model.instantiateMethod(type), TypeName.LONG)
            .beginControlFlow("try")
            .addStatement("return HANDLES.send(\$T.one(\$T.class))", JavaNames.SERVICES, ClassName.get(type))
            .nextControlFlow("catch (\$T t)", Throwable::class.java)
            .addStatement("\$L", raise)
            .addStatement("return 0")
            .endControlFlow()
            .build()
    }

    private fun resolveOf(proxy: Proxy, wires: List<Wire>): MethodSpec {
        val resolve = MethodSpec.methodBuilder("resolve")
            .addModifiers(PUBLIC, STATIC)
            .addParameter(JavaNames.JNI_ENV, "env")
            .addStatement(
                "endPoints = \$T.findClass(env, \$S)",
                JavaNames.ART,
                model.generatedClass(proxy).reflectionName().replace('.', '/'),
            )
        wires.forEach { wire ->
            resolve.addStatement(
                "\$N = \$T.staticMethod(env, endPoints, \$S, \$S)",
                methodIdOf(wire),
                JavaNames.ART,
                wire.name,
                wire.descriptor(),
            )
        }
        return resolve.build()
    }

    private fun methodIdOf(wire: Wire): String = "${wire.name}Method"

    private fun jniTypeOf(type: TypeName): TypeName = if (type == JavaNames.BYTE_BUFFER) JavaNames.JOBJECT else type

    private fun jniCallOf(type: TypeName): String = when (type) {
        JavaNames.BYTE_BUFFER -> "Object"
        else -> "$type".capitalized()
    }
}
