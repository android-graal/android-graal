package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.TypeName
import org.androidgraal.common.Side
import org.androidgraal.generator.emit.JavaNames
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.model.Operand
import org.androidgraal.generator.model.element
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.STATIC
import javax.lang.model.element.TypeElement

internal class ReferenceParameter(
    private val model: BridgeModel,
    private val side: Side,
    override val operand: Operand,
    override val place: Place,
) : ParameterCodec {
    private val type = operand.type.element()

    private val handle = "h${place.suffix}"

    override val slots: List<WireSlot> = listOf(WireSlot(handle, TypeName.LONG))

    override val fields: List<FieldSpec> = listOf(handlesFieldOf(side))

    override fun send(body: SenderBody): List<CodeBlock> = listOf(CodeBlock.of("HANDLES.send(${place.argument})"))

    override fun receive(body: ReceiverBody): CodeBlock = objectOf(model, side, type, handle)
}

internal class ReferenceResult(private val model: BridgeModel, private val side: Side, operand: Operand) :
    ResultCodec {
    private val type = operand.type.element()

    override val resultType: TypeName = TypeName.LONG

    override val fields: List<FieldSpec> = listOf(handlesFieldOf(side))

    override fun receiveResult(body: SenderBody, call: CodeBlock) {
        body.called { addStatement("long r = \$L", call) }
            .addStatement("return \$L", objectOf(model, side, type, "r"))
    }

    override fun sendResult(body: ReceiverBody, call: CodeBlock) {
        if (body.after.isEmpty()) {
            body.statements.addStatement("return HANDLES.send(\$L)", call)
        } else {
            body.called { addStatement("\$T result = \$L", ClassName.get(type), call) }
                .addStatement("return HANDLES.send(result)")
        }
    }
}

internal fun handlesFieldOf(side: Side): FieldSpec {
    val owner = if (side == Side.ART) JavaNames.IMAGE else JavaNames.ART
    return FieldSpec.builder(JavaNames.HANDLES, "HANDLES", PRIVATE, STATIC, FINAL)
        .initializer("\$T.handles()", owner)
        .build()
}

internal fun objectOf(model: BridgeModel, receiver: Side, type: TypeElement, handle: String): CodeBlock {
    val proxy = model.proxyClass(type, receiver)
        ?: return CodeBlock.of("HANDLES.<\$T>receive(\$L, null)", ClassName.get(type), handle)
    return CodeBlock.of("HANDLES.receive(\$L, \$T.PROXIES)", handle, proxy)
}
