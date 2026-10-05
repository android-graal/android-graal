package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.TypeName
import org.androidgraal.generator.model.Crossing
import org.androidgraal.generator.model.Proxy
import org.androidgraal.generator.model.fail

internal class WireSlot(val name: String, val type: TypeName)

internal class Wire(val proxy: Proxy, val crossing: Crossing, val name: String, factory: Codecs) {
    val parameters: List<ParameterCodec> =
        crossing.parameters.mapIndexed { index, operand -> factory.parameterOf(this, operand, Place("$index")) }

    val result: ResultCodec = factory.resultOf(this, crossing.result)

    val transport: Transport = factory.transportOf(proxy.implementation, parameters, result)

    val codecs: List<Codec> = parameters + result

    val slots: List<WireSlot> = listOf(WireSlot("handle", TypeName.LONG)) +
        transport.slots +
        result.resultSlots +
        parameters.flatMap { it.slots }

    fun descriptor(): String =
        slots.joinToString("", "(", ")") { descriptorOf(it.type) } + descriptorOf(result.resultType)
}

private fun descriptorOf(type: TypeName): String = when (type) {
    TypeName.BOOLEAN -> "Z"
    TypeName.BYTE -> "B"
    TypeName.CHAR -> "C"
    TypeName.SHORT -> "S"
    TypeName.INT -> "I"
    TypeName.LONG -> "J"
    TypeName.FLOAT -> "F"
    TypeName.DOUBLE -> "D"
    TypeName.VOID -> "V"
    is ClassName -> "L" + type.reflectionName().replace('.', '/') + ";"
    else -> fail("$type has no descriptor")
}
