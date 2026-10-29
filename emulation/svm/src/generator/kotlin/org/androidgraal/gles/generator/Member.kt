package org.androidgraal.gles.generator

import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.ParameterSpec
import com.palantir.javapoet.TypeName

internal sealed interface Member {
    val name: String

    val returns: TypeName

    val result: Result

    val parameters: List<ParameterSpec>

    val resources: List<CodeBlock>

    val statement: CodeBlock
}

internal enum class Result {
    VOID,
    VALUE,
    TEXT,
    ;

    fun statementOf(invocation: CodeBlock): CodeBlock = when (this) {
        VOID -> invocation
        VALUE -> CodeBlock.of("return \$L", invocation)
        TEXT -> CodeBlock.of("return \$T.toJava(\$L)", JavaNames.C_STRING, invocation)
    }
}

internal class Wrapper(
    override val name: String,
    override val returns: TypeName,
    function: CFunction,
    args: List<Arg>,
    override val result: Result,
) : Member {
    private val binding = function.library.binding

    private val target = if (function.library.isResolvedAtRunTime) {
        CodeBlock.of("\$T.\$N().call", binding, function.name)
    } else {
        CodeBlock.of("\$T.\$N", binding, function.name)
    }

    override val parameters: List<ParameterSpec> = args.flatMap { it.parameters }

    override val resources: List<CodeBlock> = args.mapNotNull { it.resource }

    override val statement: CodeBlock =
        result.statementOf(valueOf(function.result, callOf(target, args.map { it.argument })))
}

internal class ByHand(
    override val name: String,
    override val returns: TypeName,
    override val parameters: List<ParameterSpec>,
) : Member {
    override val result: Result = if (returns == TypeName.VOID) Result.VOID else Result.VALUE

    override val resources: List<CodeBlock> = emptyList()

    override val statement: CodeBlock = result.statementOf(
        callOf(CodeBlock.of("\$T.\$N", JavaNames.HANDWRITTEN, name), parameters.map { CodeBlock.of("\$N", it) }),
    )
}

private fun valueOf(type: CType, invocation: CodeBlock): CodeBlock =
    if (type.base == GlType.SYNC) CodeBlock.of("\$L.rawValue()", invocation) else invocation

private fun callOf(target: CodeBlock, arguments: List<CodeBlock>): CodeBlock =
    CodeBlock.of("\$L(\$L)", target, CodeBlock.join(arguments, ",\$W"))
