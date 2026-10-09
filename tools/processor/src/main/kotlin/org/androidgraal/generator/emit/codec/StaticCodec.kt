package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.ParameterizedTypeName
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import org.androidgraal.common.Side
import org.androidgraal.generator.emit.JavaNames
import org.androidgraal.generator.model.BridgeModel
import org.androidgraal.generator.model.Direction
import org.androidgraal.generator.model.Operand
import org.androidgraal.generator.model.capitalized
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

internal class StaticBlock(
    model: BridgeModel,
    private val side: Side,
    wire: Wire,
    operand: Operand,
    private val argument: String,
    private val local: String,
    private val field: String,
    isResult: Boolean,
) {
    val elements = BlockElements(operand)

    private val capacity = operand.capacity

    private val isOwned = wire.proxy.implementation != side

    private val isHeaded = isResult || elements.isString

    private val reusesArray = isOwned == isResult

    val reusedField = if (isResult) "${field}Array" else field

    val holderClass: ClassName = model.generatedClass(wire.proxy).nestedClass(field.capitalized())

    private val blockLength = blockLengthOf(argument, elements.lengthOf(argument))

    private val bytes: CodeBlock = if (isHeaded) {
        sizeOf("$capacity", elements.bytes)
    } else {
        CodeBlock.of("\$L * \$L", capacity, elements.bytes)
    }

    private val method = "${wire.proxy.type.simpleName}.${wire.crossing.method.simpleName}"

    val fields: List<FieldSpec> = if (side == Side.SVM) threadLocals() else emptyList()

    val types: List<TypeSpec> = if (side == Side.ART) listOf(holderSpec()) else emptyList()

    private fun holderSpec(): TypeSpec {
        val spec = TypeSpec.classBuilder(holderClass.simpleName())
            .addModifiers(PRIVATE, STATIC, FINAL)
            .addField(
                FieldSpec.builder(
                    ParameterizedTypeName.get(JavaNames.THREAD_LOCAL, holderClass),
                    "CURRENT",
                    PRIVATE,
                    STATIC,
                    FINAL,
                )
                    .initializer("new \$T<>()", JavaNames.THREAD_LOCAL)
                    .build(),
            )
        if (elements.isString || reusesArray) {
            spec.addField(
                FieldSpec.builder(ArrayTypeName.of(elements.type), "array", PUBLIC, FINAL)
                    .initializer("new \$T[\$L]", elements.type, capacity)
                    .build(),
            )
        }
        val modifiers = if (isOwned) arrayOf(PUBLIC, FINAL) else arrayOf(PUBLIC)
        spec.addField(FieldSpec.builder(TypeName.LONG, "address", *modifiers).build())
        if (isHeaded) spec.addField(FieldSpec.builder(JavaNames.BYTE_BUFFER, "block", *modifiers).build())
        spec.addField(FieldSpec.builder(viewTypeOf(elements.type), "view", *modifiers).build())
        val target = if (isOwned) "this." else "buffer."
        val bind = CodeBlock.builder()
        if (isHeaded) {
            bind.addStatement("${target}block = block").addStatement("block.position(\$L)", JavaNames.HEADER)
        }
        bind.addStatement("${target}view = block\$L", viewOf(elements.type, isHeaded))
        if (isOwned) {
            spec.addMethod(
                MethodSpec.constructorBuilder()
                    .addModifiers(PRIVATE)
                    .addParameter(JavaNames.IMAGE_THREAD_DATA, "thread")
                    .addStatement("address = \$T.threadAlloc(thread, \$L)", JavaNames.ART_TRANSPORT, bytes)
                    .addStatement(
                        "\$T block = \$T.view(thread, address, \$L)",
                        JavaNames.BYTE_BUFFER,
                        JavaNames.ART_TRANSPORT,
                        bytes,
                    )
                    .addCode(bind.build())
                    .build(),
            )
            return spec.addMethod(
                MethodSpec.methodBuilder("current")
                    .addModifiers(PUBLIC, STATIC)
                    .returns(holderClass)
                    .addParameter(JavaNames.IMAGE_THREAD_DATA, "thread")
                    .addStatement("\$T buffer = CURRENT.get()", holderClass)
                    .beginControlFlow("if (buffer == null)")
                    .addStatement("buffer = new \$T(thread)", holderClass)
                    .addStatement("CURRENT.set(buffer)")
                    .endControlFlow()
                    .addStatement("return buffer")
                    .build(),
            ).build()
        }
        return spec.addMethod(
            MethodSpec.methodBuilder("at")
                .addModifiers(PUBLIC, STATIC)
                .returns(holderClass)
                .addParameter(TypeName.LONG, "address")
                .addStatement("\$T buffer = CURRENT.get()", holderClass)
                .beginControlFlow("if (buffer == null)")
                .addStatement("buffer = new \$T()", holderClass)
                .addStatement("CURRENT.set(buffer)")
                .endControlFlow()
                .beginControlFlow("if (buffer.address != address)")
                .addStatement(
                    "\$T block = \$T.view(\$T.thread(), address, \$L)",
                    JavaNames.BYTE_BUFFER,
                    JavaNames.ART_TRANSPORT,
                    JavaNames.IMAGE,
                    bytes,
                )
                .addCode(bind.build())
                .addStatement("buffer.address = address")
                .endControlFlow()
                .addStatement("return buffer")
                .build(),
        ).build()
    }

    private fun threadLocals(): List<FieldSpec> {
        val fields = mutableListOf<FieldSpec>()
        if (isOwned) {
            val block = ParameterizedTypeName.get(JavaNames.THREAD_LOCAL, ClassName.get(Long::class.javaObjectType))
            fields += FieldSpec.builder(block, field, PRIVATE, STATIC, FINAL)
                .initializer(
                    "\$T.withInitial(() -> \$T.threadAlloc(\$L))",
                    JavaNames.THREAD_LOCAL,
                    JavaNames.TRANSPORT,
                    bytes,
                )
                .build()
        }
        if (reusesArray) {
            val array = ParameterizedTypeName.get(JavaNames.THREAD_LOCAL, ArrayTypeName.of(elements.type))
            fields += FieldSpec.builder(array, reusedField, PRIVATE, STATIC, FINAL)
                .initializer(
                    "\$T.withInitial(() -> new \$T[\$L])",
                    JavaNames.THREAD_LOCAL,
                    elements.type,
                    capacity,
                )
                .build()
        }
        return fields
    }

    fun checkOf(subject: String): CodeBlock {
        val length = elements.lengthOf(argument)
        return CodeBlock.builder()
            .beginControlFlow(
                "if ($argument != null && $length ${if (elements.isString) ">" else "!="} \$L)",
                capacity,
            )
            .addStatement(
                "throw new \$T(\$S + $length + \$S)",
                IllegalArgumentException::class.java,
                "$method $subject ",
                " ${if (elements.isString) "chars" else "elements"}, its static buffer $capacity",
            )
            .endControlFlow()
            .build()
    }

    fun sendBlock(body: SenderBody): List<CodeBlock> {
        if (side == Side.ART) {
            body.prepare.addStatement("\$T $local = \$T.current(thread)", holderClass, holderClass)
            return listOf(CodeBlock.of("$local.address"))
        }
        body.prepare.addStatement("long $local = \$N.get()", field)
        return listOf(CodeBlock.of(local))
    }

    fun artWrite(): CodeBlock {
        return CodeBlock.builder()
            .addStatement(
                "\$T.setBlockHeader($local.block, 0, \$L, \$L)",
                JavaNames.ART_TRANSPORT,
                blockLength,
                elements.bytes,
            )
            .beginControlFlow("if ($argument != null)")
            .add(viewCopyIn())
            .endControlFlow()
            .build()
    }

    fun imageWriteOf(address: String): CodeBlock {
        return CodeBlock.builder()
            .addStatement("\$T.setBlockHeader($address, \$L, \$L)", JavaNames.TRANSPORT, blockLength, elements.bytes)
            .addStatement("\$T.write($argument, $address + \$L)", JavaNames.TRANSPORT, JavaNames.HEADER)
            .build()
    }

    private fun viewCopyIn(): CodeBlock {
        if (!elements.isString) return viewCopyOf(elements.type, "$local.view", argument, intoView = true)
        return CodeBlock.builder()
            .addStatement("$argument.getChars(0, $argument.length(), $local.array, 0)")
            .addStatement("$local.view.clear()")
            .addStatement("$local.view.put($local.array, 0, $argument.length())")
            .build()
    }
}

internal class StaticParameter(
    model: BridgeModel,
    private val side: Side,
    wire: Wire,
    override val operand: Operand,
    override val place: Place,
) : ParameterCodec {
    private val local = "b${place.suffix}"

    private val field = "${wire.name}Buffer${place.suffix}"

    private val block =
        StaticBlock(model, side, wire, operand, place.argument, local, field, isResult = false)

    private val type = TypeName.get(operand.type)

    private val isInput = operand.direction == Direction.IN

    override val slots: List<WireSlot> = listOf(WireSlot(place.argument, TypeName.LONG))

    override val fields: List<FieldSpec> = block.fields

    override val types: List<TypeSpec> = block.types

    override fun send(body: SenderBody): List<CodeBlock> {
        body.checks.add(block.checkOf("parameter ${place.suffix} has"))
        if (block.elements.isString) {
            body.inside.add(if (side == Side.ART) block.artWrite() else block.imageWriteOf(local))
            return block.sendBlock(body)
        }
        return if (side == Side.ART) sendArtArray(body) else sendImageArray(body)
    }

    override fun receive(body: ReceiverBody): CodeBlock = if (side == Side.ART) receiveArt(body) else receiveImage(body)

    private fun sendArtArray(body: SenderBody): List<CodeBlock> {
        body.prepare.addStatement(
            "\$T $local = ${place.argument} == null ? null : \$T.current(thread)",
            block.holderClass,
            block.holderClass,
        )
        (if (isInput) body.inside else body.after).beginControlFlow("if ($local != null)")
            .add(viewCopyOf(block.elements.type, "$local.view", place.argument, isInput))
            .endControlFlow()
        return listOf(CodeBlock.of("$local == null ? 0 : $local.address"))
    }

    private fun sendImageArray(body: SenderBody): List<CodeBlock> {
        body.prepare.addStatement("long $local = ${place.argument} == null ? 0 : \$N.get()", field)
        if (isInput) {
            body.inside.addStatement("\$T.write(${place.argument}, $local)", JavaNames.TRANSPORT)
        } else {
            body.after.addStatement("\$T.read($local, ${place.argument})", JavaNames.TRANSPORT)
        }
        return listOf(CodeBlock.of(local))
    }

    private fun receiveArt(body: ReceiverBody): CodeBlock {
        if (block.elements.isString) {
            body.statements.addStatement("\$T $local = \$T.at(${place.argument})", block.holderClass, block.holderClass)
                .addStatement(
                    "String ${place.value} = \$T.readStringBlock($local.block, $local.view, $local.array)",
                    JavaNames.ART_TRANSPORT,
                )
            return CodeBlock.of(place.value)
        }
        body.statements.addStatement(
            "\$T $local = ${place.argument} == 0 ? null : \$T.at(${place.argument})",
            block.holderClass,
            block.holderClass,
        ).addStatement("\$T ${place.value} = $local == null ? null : $local.array", type)
        (if (isInput) body.statements else body.after).beginControlFlow("if ($local != null)")
            .add(viewCopyOf(block.elements.type, "$local.view", place.value, !isInput))
            .endControlFlow()
        return CodeBlock.of(place.value)
    }

    private fun receiveImage(body: ReceiverBody): CodeBlock {
        if (block.elements.isString) {
            body.statements.addStatement(
                "String ${place.value} = \$T.readStringBlock(${place.argument}, \$N.get())",
                JavaNames.TRANSPORT,
                block.reusedField,
            )
            return CodeBlock.of(place.value)
        }
        body.statements.addStatement(
            "\$T ${place.value} = ${place.argument} == 0 ? null : \$N.get()",
            type,
            block.reusedField,
        )
        if (isInput) {
            body.statements.addStatement("\$T.read(${place.argument}, ${place.value})", JavaNames.TRANSPORT)
        } else {
            body.after.addStatement("\$T.write(${place.value}, ${place.argument})", JavaNames.TRANSPORT)
        }
        return CodeBlock.of(place.value)
    }
}

internal class StaticResult(model: BridgeModel, private val side: Side, wire: Wire, operand: Operand) : ResultCodec {
    private val argument = "result"

    private val local = "rb"

    private val length = "n"

    private val field = "${wire.name}Result"

    private val block = StaticBlock(model, side, wire, operand, argument, local, field, isResult = true)

    private val type = TypeName.get(operand.type)

    override val resultSlots: List<WireSlot> = listOf(WireSlot("block", TypeName.LONG))

    override val resultType: TypeName = TypeName.VOID

    override val fields: List<FieldSpec> = block.fields

    override val types: List<TypeSpec> = block.types

    override fun sendResultSlots(body: SenderBody): List<CodeBlock> = block.sendBlock(body)

    override fun receiveResult(body: SenderBody, call: CodeBlock) {
        val code = body.called { addStatement("\$L", call) }
        if (side == Side.ART) receiveArtResult(code) else receiveImageResult(code)
    }

    override fun sendResult(body: ReceiverBody, call: CodeBlock) {
        val code = body.called { addStatement("\$T $argument = \$L", type, call) }
            .add(block.checkOf("returns"))
        if (side == Side.ART) {
            code.addStatement("\$T $local = \$T.at(block)", block.holderClass, block.holderClass).add(block.artWrite())
        } else {
            code.add(block.imageWriteOf("block"))
        }
    }

    private fun receiveArtResult(code: CodeBlock.Builder) {
        if (block.elements.isString) {
            code.addStatement(
                "return \$T.readStringBlock($local.block, $local.view, $local.array)",
                JavaNames.ART_TRANSPORT,
            )
            return
        }
        code.addStatement("int $length = \$T.getBlockLength($local.block, 0)", JavaNames.ART_TRANSPORT)
            .beginControlFlow("if ($length == \$L)", JavaNames.BLOCK_NULL)
            .addStatement("return null")
            .endControlFlow()
            .add(viewCopyOf(block.elements.type, "$local.view", "$local.array", intoView = false))
            .addStatement("return $local.array")
    }

    private fun receiveImageResult(code: CodeBlock.Builder) {
        if (block.elements.isString) {
            code.addStatement("return \$T.readStringBlock($local, \$N.get())", JavaNames.TRANSPORT, block.reusedField)
            return
        }
        code.addStatement("int $length = \$T.getBlockLength($local)", JavaNames.TRANSPORT)
            .addStatement(
                "\$T $argument = $length == \$L ? null : \$N.get()",
                type,
                JavaNames.BLOCK_NULL,
                block.reusedField,
            )
            .addStatement("\$T.read($local + \$L, $argument)", JavaNames.TRANSPORT, JavaNames.HEADER)
            .addStatement("return $argument")
    }
}

private fun viewCopyOf(component: TypeName, view: String, array: String, intoView: Boolean): CodeBlock {
    if (component == TypeName.BOOLEAN) return booleanCopyOf(view, CodeBlock.of(""), array, intoView)
    return CodeBlock.builder()
        .addStatement("\$L.clear()", view)
        .addStatement("\$L.\$L(\$L)", view, if (intoView) "put" else "get", array)
        .build()
}
