package org.androidgraal.gles.generator

import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.ParameterSpec
import com.palantir.javapoet.TypeName

internal sealed interface Arg {
    val parameters: List<ParameterSpec>

    val resource: CodeBlock? get() = null

    val argument: CodeBlock
}

internal class Scalar(param: CParam, typeName: TypeName) : Arg {
    override val parameters: List<ParameterSpec> = listOf(ParameterSpec.builder(typeName, param.name).build())

    override val argument: CodeBlock = when (param.type.base) {
        GlType.INTPTR, GlType.SIZEIPTR ->
            if (typeName == TypeName.LONG) {
                CodeBlock.of("\$T.checkedSigned(\$N)", JavaNames.WORDS, param.name)
            } else {
                CodeBlock.of("\$T.signed(\$N)", JavaNames.WORD_FACTORY, param.name)
            }

        GlType.SYNC -> CodeBlock.of("\$T.pointer(\$N)", JavaNames.WORD_FACTORY, param.name)

        else -> CodeBlock.of("\$N", param.name)
    }
}

internal class Offset(param: CParam, typeName: TypeName) : Arg {
    private val offset = "${param.name}Offset"

    override val parameters: List<ParameterSpec> = listOf(ParameterSpec.builder(typeName, offset).build())

    override val argument: CodeBlock = if (typeName == TypeName.LONG) {
        CodeBlock.of("\$T.checkedPointer(\$N)", JavaNames.WORDS, offset)
    } else {
        CodeBlock.of("\$T.pointer(\$N)", JavaNames.WORD_FACTORY, offset)
    }
}

internal class Text(param: CParam, typeName: TypeName, passing: Passing.Counted?) : Arg {
    private val cString = "${param.name}CString"

    override val parameters: List<ParameterSpec> = listOf(ParameterSpec.builder(typeName, param.name).build())

    override val resource: CodeBlock = CodeBlock.of(
        "\$T \$N = \$T.\$N(\$L)",
        JavaNames.C_CHAR_POINTER_HOLDER,
        cString,
        JavaNames.C_STRING,
        if (passing?.isNullable == true) "ofNullable" else "of",
        CodeBlock.join(listOfNotNull(param.name, passing?.length).map { CodeBlock.of("\$N", it) }, ",\$W"),
    )

    override val argument: CodeBlock = CodeBlock.of("\$N.get()", cString)
}

internal class DirectBuffer(param: CParam, typeName: TypeName) : Arg {
    override val parameters: List<ParameterSpec> = listOf(ParameterSpec.builder(typeName, param.name).build())

    override val argument: CodeBlock = CodeBlock.of("\$T.direct(\$N)", JavaNames.POINTER, param.name)
}

internal class PinnedBuffer(param: CParam, typeName: TypeName, passing: Passing.Pinned) : Arg {
    private val pin = "${param.name}Pin"

    override val parameters: List<ParameterSpec> = listOf(ParameterSpec.builder(typeName, param.name).build())

    override val resource: CodeBlock = pinOf(pin, param, passing, param.name)

    override val argument: CodeBlock = CodeBlock.of("\$T.address(\$N,\$W\$N)", JavaNames.POINTER, param.name, pin)
}

internal class PinnedArray(param: CParam, typeName: TypeName, passing: Passing.Pinned) : Arg {
    private val offset = "${param.name}Offset"
    private val pin = "${param.name}Pin"

    override val parameters: List<ParameterSpec> = listOf(
        ParameterSpec.builder(typeName, param.name).build(),
        ParameterSpec.builder(TypeName.INT, offset).build(),
    )

    override val resource: CodeBlock = pinOf(pin, param, passing, param.name, offset)

    override val argument: CodeBlock = CodeBlock.of("\$T.address(\$N,\$W\$N)", JavaNames.POINTER, pin, offset)
}

private fun pinOf(pin: String, param: CParam, passing: Passing.Pinned, vararg names: String): CodeBlock = CodeBlock.of(
    "\$T \$N = \$T.\$N(\$L)",
    JavaNames.PINNED_OBJECT,
    pin,
    JavaNames.POINTER,
    if (passing.isNullable) "pinNullable" else "pin",
    CodeBlock.join(
        listOfNotNull(
            *names.map { CodeBlock.of("\$N", it) }.toTypedArray(),
            bytesOf(passing.count, param.type.base.size!!),
        ),
        ",\$W",
    ),
)

private fun bytesOf(count: Count?, size: Int): CodeBlock? = when (count) {
    null -> null

    is Count.Literal -> CodeBlock.of("\$L", count.times * size)

    is Count.Param -> timesOf(CodeBlock.of("\$N", count.name), count.times * size)

    is Count.Query -> timesOf(
        CodeBlock.of(
            "\$T.\$N(\$L)",
            JavaNames.COUNTS,
            count.query.method,
            CodeBlock.join(count.params.map { CodeBlock.of("\$N", it) }, ", "),
        ),
        size,
    )
}

private fun timesOf(term: CodeBlock, bytes: Int): CodeBlock =
    if (bytes == 1) term else CodeBlock.of("\$L * \$LL", term, bytes)
