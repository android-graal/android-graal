package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.TypeName
import org.androidgraal.common.Side
import org.androidgraal.generator.emit.JavaNames

internal class Transport(
    private val side: Side,
    implementation: Side,
    parameters: List<ParameterCodec>,
    result: ResultCodec,
) {
    private val blocks: List<TransportBlock> = parameters.flatMap { it.blocks }

    private val offsets: List<String> = listOf("0") + blocks.drop(1).map { it.offset }

    private val needsThread: Boolean = result.needsThread

    val slots: List<WireSlot> = slotsOf(implementation)

    fun send(body: SenderBody): List<CodeBlock> {
        if (blocks.isEmpty()) return emptyList()
        blocks.forEachIndexed { index, block ->
            body.prepare.addStatement("int ${block.length} = \$L", block.blockLength)
            if (index > 0) body.prepare.addStatement("int ${block.offset} = \$L", endOf(index - 1))
        }
        body.prepare.addStatement("int bytes = \$L", endOf(blocks.lastIndex))
        val none = blocks.joinToString(" && ") { "${it.place.argument} == null" }
        val values = if (side == Side.ART) {
            body.prepare.addStatement(
                "\$T transport = $none ? null : \$T.alloc(thread, bytes)",
                JavaNames.BYTE_BUFFER,
                JavaNames.ART_TRANSPORT,
            )
            body.inside.beginControlFlow("if (transport != null)")
            listOf(CodeBlock.of("transport"))
        } else {
            body.prepare.addStatement("long transport = $none ? 0 : \$T.alloc(bytes)", JavaNames.TRANSPORT)
            body.inside.beginControlFlow("if (transport != 0)")
            listOf(CodeBlock.of("transport"), CodeBlock.of("bytes"))
        }
        blocks.forEachIndexed { index, block -> block.sendBlock(body, offsets[index]) }
        body.inside.endControlFlow()
        return values
    }

    fun receive(body: ReceiverBody) {
        if (side == Side.ART && (blocks.isNotEmpty() || needsThread)) {
            body.statements.addStatement("\$T thread = \$T.thread()", JavaNames.IMAGE_THREAD_DATA, JavaNames.IMAGE)
        }
        if (blocks.isEmpty()) return
        if (side == Side.ART) {
            body.statements.addStatement(
                "\$T view = transport == 0 ? null : \$T.view(thread, transport, bytes)",
                JavaNames.BYTE_BUFFER,
                JavaNames.ART_TRANSPORT,
            )
        } else {
            body.statements.addStatement("long address = \$T.address(env, transport)", JavaNames.ART)
        }
        blocks.forEachIndexed { index, block ->
            if (index > 0) body.statements.addStatement("int ${block.offset} = \$L", endOf(index - 1))
            body.statements.addStatement("int ${block.length} = \$L", receivedLengthOf(offsets[index]))
            block.receiveBlock(body, offsets[index])
        }
    }

    fun bracketOf(body: SenderBody): CodeBlock {
        val inside = body.inside.build()
        if (blocks.isEmpty()) return inside
        val (absent, free) = if (side == Side.ART) {
            "null" to CodeBlock.of("\$T.free(thread, transport)", JavaNames.ART_TRANSPORT)
        } else {
            "0" to CodeBlock.of("\$T.free(transport)", JavaNames.TRANSPORT)
        }
        return CodeBlock.builder()
            .beginControlFlow("try")
            .add(inside)
            .nextControlFlow("finally")
            .beginControlFlow("if (transport != \$L)", absent)
            .addStatement("\$L", free)
            .endControlFlow()
            .endControlFlow()
            .build()
    }

    private fun slotsOf(implementation: Side): List<WireSlot> {
        if (blocks.isEmpty()) return emptyList()
        if (implementation == Side.SVM) return listOf(WireSlot("transport", JavaNames.BYTE_BUFFER))
        return listOf(WireSlot("transport", TypeName.LONG), WireSlot("bytes", TypeName.INT))
    }

    private fun endOf(index: Int): CodeBlock {
        val block = blocks[index]
        return plus(offsets[index], sizeOf(block.length, block.elements.bytes))
    }

    private fun receivedLengthOf(at: String): CodeBlock {
        if (side == Side.ART) {
            return CodeBlock.of(
                "view == null ? \$L : \$T.getBlockLength(view, \$L)",
                JavaNames.BLOCK_NULL,
                JavaNames.ART_TRANSPORT,
                at,
            )
        }
        return CodeBlock.of(
            "address == 0 ? \$L : \$T.getBlockLength(\$L)",
            JavaNames.BLOCK_NULL,
            JavaNames.TRANSPORT,
            plus("address", at),
        )
    }
}
