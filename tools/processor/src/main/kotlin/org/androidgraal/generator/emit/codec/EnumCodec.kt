package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.TypeName
import org.androidgraal.common.Side
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.model.Operand
import org.androidgraal.generator.model.element
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.STATIC
import javax.lang.model.element.TypeElement

internal class EnumParameter(
    model: BridgeModel,
    side: Side,
    implementation: Side,
    override val operand: Operand,
    override val place: Place,
) : ParameterCodec {
    private val type = operand.type.element()

    private val array = model.enumArray(type)

    override val slots: List<WireSlot> = listOf(WireSlot(place.argument, TypeName.INT))

    override val fields: List<FieldSpec> = if (implementation == side) listOf(valuesOf(type, array)) else emptyList()

    override fun send(body: SenderBody): List<CodeBlock> = listOf(ordinalOf(place.argument))

    override fun receive(body: ReceiverBody): CodeBlock = constantOf(array, place.argument)
}

internal class EnumResult(model: BridgeModel, side: Side, implementation: Side, operand: Operand) : ResultCodec {
    private val type = operand.type.element()

    private val array = model.enumArray(type)

    override val resultType: TypeName = TypeName.INT

    override val fields: List<FieldSpec> = if (implementation != side) listOf(valuesOf(type, array)) else emptyList()

    override fun receiveResult(body: SenderBody, call: CodeBlock) {
        body.called { addStatement("int r = \$L", call) }.addStatement("return \$L", constantOf(array, "r"))
    }

    override fun sendResult(body: ReceiverBody, call: CodeBlock) {
        body.called { addStatement("\$T result = \$L", ClassName.get(type), call) }
            .addStatement("return \$L", ordinalOf("result"))
    }
}

internal fun ordinalOf(value: String): CodeBlock = CodeBlock.of("\$N == null ? -1 : \$N.ordinal()", value, value)

internal fun constantOf(array: String, value: String): CodeBlock =
    CodeBlock.of("\$N < 0 ? null : \$N[\$N]", value, array, value)

internal fun valuesOf(type: TypeElement, array: String): FieldSpec {
    val enum = ClassName.get(type)
    return FieldSpec.builder(ArrayTypeName.of(enum), array, PRIVATE, STATIC, FINAL)
        .initializer("\$T.values()", enum)
        .build()
}
