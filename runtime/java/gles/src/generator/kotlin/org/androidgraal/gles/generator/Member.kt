package org.androidgraal.gles.generator

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.ParameterSpec
import com.palantir.javapoet.TypeName

internal sealed interface Member {
    val name: String

    val returns: TypeName

    val parameters: List<ParameterSpec>

    val resources: List<CodeBlock>

    val invocation: CodeBlock

    val result: Result
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
    override val parameters: List<ParameterSpec> = args.flatMap { it.parameters }

    override val resources: List<CodeBlock> = args.mapNotNull { it.resource }

    override val invocation: CodeBlock = callOf(function.library, function.name, args.map { it.argument })
}

internal class ByHand(
    override val name: String,
    override val returns: TypeName,
    override val parameters: List<ParameterSpec>,
) : Member {
    override val result: Result = if (returns == TypeName.VOID) Result.VOID else Result.VALUE

    override val resources: List<CodeBlock> = emptyList()

    override val invocation: CodeBlock =
        callOf(JavaNames.HANDWRITTEN, name, parameters.map { CodeBlock.of("\$N", it) })
}

private fun callOf(owner: ClassName, name: String, arguments: List<CodeBlock>): CodeBlock =
    CodeBlock.of("\$T.\$N(\$L)", owner, name, CodeBlock.join(arguments, ",\$W"))
