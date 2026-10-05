package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.TypeName
import org.androidgraal.generator.model.Operand

internal class PrimitiveParameter(override val operand: Operand, override val place: Place) : ParameterCodec {
    override val slots: List<WireSlot> = listOf(WireSlot(place.argument, TypeName.get(operand.type)))

    override fun send(body: SenderBody): List<CodeBlock> = listOf(CodeBlock.of(place.argument))

    override fun receive(body: ReceiverBody): CodeBlock = CodeBlock.of(place.argument)
}

internal class PrimitiveResult(operand: Operand) : ResultCodec {
    override val resultType: TypeName = TypeName.get(operand.type)

    override fun receiveResult(body: SenderBody, call: CodeBlock) {
        body.called { addStatement("\$T r = \$L", resultType, call) }.addStatement("return r")
    }

    override fun sendResult(body: ReceiverBody, call: CodeBlock) {
        if (body.after.isEmpty()) {
            body.statements.addStatement("return \$L", call)
        } else {
            body.called { addStatement("\$T result = \$L", resultType, call) }.addStatement("return result")
        }
    }
}
