package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import org.androidgraal.generator.model.Operand

internal class Place(val suffix: String) {
    val argument: String = "a$suffix"

    val value: String = "v$suffix"
}

internal interface Codec {
    val fields: List<FieldSpec> get() = emptyList()

    val types: List<TypeSpec> get() = emptyList()
}

internal interface ParameterCodec : Codec {
    val operand: Operand

    val place: Place

    val slots: List<WireSlot>

    val blocks: List<TransportBlock> get() = emptyList()

    fun send(body: SenderBody): List<CodeBlock>

    fun receive(body: ReceiverBody): CodeBlock
}

internal interface TransportBlock {
    val place: Place

    val elements: BlockElements

    val length: String

    val offset: String

    val blockLength: CodeBlock

    fun sendBlock(body: SenderBody, at: String)

    fun receiveBlock(body: ReceiverBody, at: String)
}

internal interface ResultCodec : Codec {
    val resultSlots: List<WireSlot> get() = emptyList()

    val resultType: TypeName

    val needsThread: Boolean get() = false

    fun sendResultSlots(body: SenderBody): List<CodeBlock> = emptyList()

    fun receiveResult(body: SenderBody, call: CodeBlock)

    fun sendResult(body: ReceiverBody, call: CodeBlock)
}

internal class SenderBody(private val pending: CodeBlock) {
    val checks: CodeBlock.Builder = CodeBlock.builder()

    val prepare: CodeBlock.Builder = CodeBlock.builder()

    val inside: CodeBlock.Builder = CodeBlock.builder()

    val after: CodeBlock.Builder = CodeBlock.builder()

    fun called(call: CodeBlock.Builder.() -> Unit): CodeBlock.Builder {
        inside.call()
        return inside.add(pending).add(after.build())
    }
}

internal class ReceiverBody {
    val statements: CodeBlock.Builder = CodeBlock.builder()

    val after: CodeBlock.Builder = CodeBlock.builder()

    fun called(call: CodeBlock.Builder.() -> Unit): CodeBlock.Builder {
        statements.call()
        return statements.add(after.build())
    }
}
