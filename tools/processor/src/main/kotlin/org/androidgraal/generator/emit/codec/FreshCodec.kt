package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.TypeName
import org.androidgraal.common.Side
import org.androidgraal.generator.emit.JavaNames
import org.androidgraal.generator.model.Direction
import org.androidgraal.generator.model.Operand

internal class FreshParameter(private val side: Side, override val operand: Operand, override val place: Place) :
    ParameterCodec,
    TransportBlock {
    override val elements: BlockElements = BlockElements(operand)

    private val direction = operand.direction ?: Direction.IN

    override val length: String = "n${place.suffix}"

    override val offset: String = "o${place.suffix}"

    override val blockLength: CodeBlock = blockLengthOf(place.argument, elements.lengthOf(place.argument))

    override val slots: List<WireSlot> = emptyList()

    override val blocks: List<TransportBlock> = listOf(this)

    override fun send(body: SenderBody): List<CodeBlock> = emptyList()

    override fun receive(body: ReceiverBody): CodeBlock = CodeBlock.of(place.value)

    override fun sendBlock(body: SenderBody, at: String) {
        if (side == Side.ART) sendArt(body, at) else sendImage(body, at)
    }

    override fun receiveBlock(body: ReceiverBody, at: String) {
        if (side == Side.ART) receiveArt(body, at) else receiveImage(body, at)
    }

    private fun sendArt(body: SenderBody, at: String) {
        val start = plus(at, JavaNames.HEADER)
        body.inside.addStatement(
            "\$T.setBlockHeader(transport, \$L, $length, \$L)",
            JavaNames.ART_TRANSPORT,
            at,
            elements.bytes,
        )
        if (direction == Direction.IN) {
            body.inside.beginControlFlow("if (${place.argument} != null)")
                .add(copyInOf(elements, "transport", start, place.argument, "c${place.suffix}"))
                .endControlFlow()
        } else {
            body.after.beginControlFlow("if (${place.argument} != null)")
                .add(bufferCopyOf(elements.type, "transport", start, place.argument, intoBuffer = false))
                .endControlFlow()
        }
    }

    private fun sendImage(body: SenderBody, at: String) {
        val block = plus("transport", at)
        body.inside.addStatement("\$T.setBlockHeader($block, $length, \$L)", JavaNames.TRANSPORT, elements.bytes)
        val start = plus(block, JavaNames.HEADER)
        if (direction == Direction.IN) {
            body.inside.addStatement("\$T.write(${place.argument}, \$L)", JavaNames.TRANSPORT, start)
        } else {
            body.after.addStatement("\$T.read(\$L, ${place.argument})", JavaNames.TRANSPORT, start)
        }
    }

    private fun receiveArt(body: ReceiverBody, at: String) {
        if (elements.isString) {
            body.statements.addStatement(
                "String ${place.value} = view == null ? null : \$T.readStringBlock(view, \$L)",
                JavaNames.ART_TRANSPORT,
                at,
            )
            return
        }
        receiveArray(body)
        val output = direction == Direction.OUT
        (if (output) body.after else body.statements).beginControlFlow("if (${place.value} != null)")
            .add(bufferCopyOf(elements.type, "view", plus(at, JavaNames.HEADER), place.value, output))
            .endControlFlow()
    }

    private fun receiveImage(body: ReceiverBody, at: String) {
        if (elements.isString) {
            body.statements.addStatement(
                "String ${place.value} = address == 0 ? null : \$T.readStringBlock(\$L)",
                JavaNames.TRANSPORT,
                plus("address", at),
            )
            return
        }
        receiveArray(body)
        val start = plus("address", plus(at, JavaNames.HEADER))
        if (direction == Direction.IN) {
            body.statements.addStatement("\$T.read(\$L, ${place.value})", JavaNames.TRANSPORT, start)
        } else {
            body.after.addStatement("\$T.write(${place.value}, \$L)", JavaNames.TRANSPORT, start)
        }
    }

    private fun receiveArray(body: ReceiverBody) {
        body.statements.addStatement(
            "\$T ${place.value} = $length == \$L ? null : new \$T[$length]",
            ArrayTypeName.of(elements.type),
            JavaNames.BLOCK_NULL,
            elements.type,
        )
    }
}

internal class FreshResult(private val side: Side, implementation: Side, operand: Operand) : ResultCodec {
    private val elements = BlockElements(operand)

    private val type = TypeName.get(operand.type)

    override val resultType: TypeName = if (implementation == Side.SVM) TypeName.LONG else JavaNames.BYTE_BUFFER

    override val needsThread: Boolean = true

    override fun receiveResult(body: SenderBody, call: CodeBlock) {
        if (side == Side.ART) receiveArtResult(body, call) else receiveImageResult(body, call)
    }

    override fun sendResult(body: ReceiverBody, call: CodeBlock) {
        if (side == Side.ART) sendArtResult(body, call) else sendImageResult(body, call)
    }

    private fun receiveArtResult(body: SenderBody, call: CodeBlock) {
        val code = body.called { addStatement("long r = \$L", call) }
            .beginControlFlow("if (r == 0)")
            .addStatement("return null")
            .endControlFlow()
            .addStatement("\$T block = \$T.viewBlock(thread, r)", JavaNames.BYTE_BUFFER, JavaNames.ART_TRANSPORT)
        if (elements.isString) {
            code.addStatement("String result = \$T.readStringBlock(block, 0)", JavaNames.ART_TRANSPORT)
        } else {
            code.addStatement(
                "\$T result = new \$T[\$T.getBlockLength(block, 0)]",
                type,
                elements.type,
                JavaNames.ART_TRANSPORT,
            )
                .add(bufferCopyOf(elements.type, "block", JavaNames.HEADER, "result", intoBuffer = false))
        }
        code.addStatement("\$T.free(thread, block)", JavaNames.ART_TRANSPORT).addStatement("return result")
    }

    private fun receiveImageResult(body: SenderBody, call: CodeBlock) {
        val code = body.called {
            addStatement("\$T view = \$L", JavaNames.JOBJECT, call)
            addStatement("long r = \$T.address(thread.env, view)", JavaNames.ART)
            addStatement("\$T.deleteLocal(thread.env, view)", JavaNames.ART)
        }
        if (elements.isString) {
            code.addStatement("String result = \$T.readStringBlock(r)", JavaNames.TRANSPORT)
        } else {
            code.addStatement(
                "\$T result = r == 0 ? null : new \$T[\$T.getBlockLength(r)]",
                type,
                elements.type,
                JavaNames.TRANSPORT,
            )
                .addStatement("\$T.read(r + \$L, result)", JavaNames.TRANSPORT, JavaNames.HEADER)
        }
        code.addStatement("\$T.free(r)", JavaNames.TRANSPORT).addStatement("return result")
    }

    private fun sendArtResult(body: ReceiverBody, call: CodeBlock) {
        body.called { addStatement("\$T result = \$L", type, call) }
            .beginControlFlow("if (result == null)")
            .addStatement("return null")
            .endControlFlow()
            .addStatement(
                "\$T r = \$T.allocBlock(thread, \$L, \$L)",
                JavaNames.BYTE_BUFFER,
                JavaNames.ART_TRANSPORT,
                elements.lengthOf("result"),
                elements.bytes,
            )
            .add(copyInOf(elements, "r", JavaNames.HEADER, "result", "chars"))
            .addStatement("return r")
    }

    private fun sendImageResult(body: ReceiverBody, call: CodeBlock) {
        body.called { addStatement("\$T result = \$L", type, call) }
            .addStatement(
                "long r = result == null ? 0 : \$T.allocBlock(\$L, \$L)",
                JavaNames.TRANSPORT,
                elements.lengthOf("result"),
                elements.bytes,
            )
            .addStatement("\$T.write(result, r + \$L)", JavaNames.TRANSPORT, JavaNames.HEADER)
            .addStatement("return r")
    }
}
