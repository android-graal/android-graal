package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.TypeName

internal object VoidCodec : ResultCodec {
    override val resultType: TypeName = TypeName.VOID

    override fun receiveResult(body: SenderBody, call: CodeBlock) {
        body.called { addStatement("\$L", call) }
    }

    override fun sendResult(body: ReceiverBody, call: CodeBlock) {
        body.called { addStatement("\$L", call) }
    }
}
