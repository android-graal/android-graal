package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.TypeSpec
import org.androidgraal.common.Side
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.model.Kind
import org.androidgraal.generator.model.Operand
import org.androidgraal.generator.model.Proxy
import org.androidgraal.generator.model.fail

internal class Codecs(private val model: BridgeModel, private val side: Side) {
    fun wiresOf(proxy: Proxy): List<Wire> = proxy.crossing.map { Wire(proxy, it, proxy.crossingName(it), this) }

    fun parameterOf(wire: Wire, operand: Operand, place: Place): ParameterCodec = when (operand.kind) {
        Kind.PRIMITIVE -> PrimitiveParameter(operand, place)

        Kind.ENUM -> EnumParameter(model, side, wire.proxy.implementation, operand, place)

        Kind.REFERENCE -> ReferenceParameter(model, side, operand, place)

        Kind.RECORD -> RecordParameter(this, wire, operand, place)

        Kind.ARRAY, Kind.STRING ->
            if (operand.capacity == null) {
                FreshParameter(side, operand, place)
            } else {
                StaticParameter(model, side, wire, operand, place)
            }

        Kind.VOID -> fail("a parameter is never void")
    }

    fun resultOf(wire: Wire, operand: Operand): ResultCodec = when (operand.kind) {
        Kind.VOID -> VoidCodec

        Kind.PRIMITIVE -> PrimitiveResult(operand)

        Kind.ENUM -> EnumResult(model, side, wire.proxy.implementation, operand)

        Kind.REFERENCE -> ReferenceResult(model, side, operand)

        Kind.ARRAY, Kind.STRING ->
            if (operand.capacity == null) {
                FreshResult(side, wire.proxy.implementation, operand)
            } else {
                StaticResult(model, side, wire, operand)
            }

        Kind.RECORD -> fail("a record crosses as a parameter only")
    }

    fun transportOf(implementation: Side, parameters: List<ParameterCodec>, result: ResultCodec): Transport =
        Transport(side, implementation, parameters, result)

    fun fieldsOf(wires: List<Wire>): List<FieldSpec> = wires.flatMap { it.codecs }.flatMap { it.fields }.distinct()

    fun typesOf(wires: List<Wire>): List<TypeSpec> = wires.flatMap { it.codecs }.flatMap { it.types }.distinct()
}
