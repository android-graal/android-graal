package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.TypeName
import org.androidgraal.generator.emit.zeroOf
import org.androidgraal.generator.model.Kind
import org.androidgraal.generator.model.Operand
import org.androidgraal.generator.model.element
import org.androidgraal.generator.model.fail
import javax.lang.model.element.RecordComponentElement

internal class RecordParameter(factory: Codecs, wire: Wire, override val operand: Operand, override val place: Place) :
    ParameterCodec {
    private val presence = "p${place.suffix}"

    private val components: List<RecordComponentElement> = operand.type.element().recordComponents

    private val codecs: List<ParameterCodec> = components.map {
        val type = it.asType()
        val kind = Kind.of(type) ?: fail("${it.simpleName} has no kind")
        factory.parameterOf(wire, Operand(kind, type), Place("${place.suffix}_${it.simpleName}"))
    }

    override val slots: List<WireSlot> = listOf(WireSlot(presence, TypeName.BOOLEAN)) + codecs.flatMap { it.slots }

    override val blocks: List<TransportBlock> = codecs.flatMap { it.blocks }

    override val fields: List<FieldSpec> = codecs.flatMap { it.fields }

    override fun send(body: SenderBody): List<CodeBlock> {
        body.prepare.addStatement("boolean \$N = \$N != null", presence, place.argument)
        components.zip(codecs).forEach { (component, codec) ->
            val type = TypeName.get(codec.operand.type)
            body.prepare.addStatement(
                "\$T \$N = \$N ? \$N.\$N() : \$L",
                type,
                codec.place.argument,
                presence,
                place.argument,
                component.simpleName,
                zeroOf(type),
            )
        }
        return listOf(CodeBlock.of(presence)) + codecs.flatMap { it.send(body) }
    }

    override fun receive(body: ReceiverBody): CodeBlock {
        val values = codecs.map { it.receive(body) }
        val type = TypeName.get(operand.type)
        body.statements.addStatement(
            "\$T \$N = \$N ? new \$T(\$L) : null",
            type,
            place.value,
            presence,
            type,
            CodeBlock.join(values, ",\$W"),
        )
        return CodeBlock.of(place.value)
    }
}
