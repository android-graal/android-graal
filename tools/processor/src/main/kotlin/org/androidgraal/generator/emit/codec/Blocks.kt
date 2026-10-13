package org.androidgraal.generator.emit.codec

import com.palantir.javapoet.ArrayTypeName
import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.TypeName
import org.androidgraal.generator.emit.JavaNames
import org.androidgraal.generator.model.Kind
import org.androidgraal.generator.model.Operand
import org.androidgraal.generator.model.capitalized
import org.androidgraal.generator.model.fail

internal class BlockElements(operand: Operand) {
    val isString: Boolean = operand.kind == Kind.STRING

    val isStringArray: Boolean = operand.kind == Kind.STRING_ARRAY

    val reader: String? = when {
        isString -> "readStringBlock"
        isStringArray -> "readStringArrayBlock"
        else -> null
    }

    val type: TypeName = when {
        isString -> TypeName.CHAR
        isStringArray -> TypeName.BYTE
        else -> (TypeName.get(operand.type) as ArrayTypeName).componentType()
    }

    val bytes: Int = type.elementBytes()

    fun lengthOf(value: String): CodeBlock = when {
        isString -> CodeBlock.of("\$L.length()", value)
        isStringArray -> CodeBlock.of("\$T.stringArrayLength(\$L)", JavaNames.BLOCKS, value)
        else -> CodeBlock.of("\$L.length", value)
    }
}

internal fun TypeName.elementBytes(): Int = when (this) {
    TypeName.BOOLEAN, TypeName.BYTE -> 1
    TypeName.CHAR, TypeName.SHORT -> 2
    TypeName.INT, TypeName.FLOAT -> 4
    TypeName.LONG, TypeName.DOUBLE -> 8
    else -> fail("$this is no element type")
}

internal fun blockLengthOf(value: String, length: CodeBlock): CodeBlock =
    CodeBlock.of("\$L == null ? \$L : \$L", value, JavaNames.BLOCK_NULL, length)

internal fun sizeOf(length: String, elementBytes: Int): CodeBlock =
    CodeBlock.of("\$T.size(\$L, \$L)", JavaNames.BLOCKS, length, elementBytes)

internal fun viewOf(component: TypeName, sliced: Boolean): String = when {
    component.elementBytes() > 1 -> ".as${viewTypeOf(component).simpleName()}()"
    sliced -> ".slice()"
    else -> ""
}

internal fun viewTypeOf(component: TypeName): ClassName {
    if (component.elementBytes() == 1) return JavaNames.BYTE_BUFFER
    return ClassName.get("java.nio", "${"$component".capitalized()}Buffer")
}

internal fun booleanCopyOf(buffer: String, start: CodeBlock, array: String, intoBuffer: Boolean): CodeBlock {
    val copy = CodeBlock.builder().beginControlFlow("for (int k = 0; k < \$L.length; k++)", array)
    if (intoBuffer) {
        copy.addStatement("\$L.put(\$Lk, (byte) (\$L[k] ? 1 : 0))", buffer, start, array)
    } else {
        copy.addStatement("\$L[k] = \$L.get(\$Lk) != 0", array, buffer, start)
    }
    return copy.endControlFlow().build()
}

internal fun copyInOf(elements: BlockElements, buffer: String, at: CodeBlock, value: String): CodeBlock {
    if (!elements.isString && !elements.isStringArray) {
        return bufferCopyOf(elements.type, buffer, at, value, intoBuffer = true)
    }
    return CodeBlock.builder()
        .addStatement("\$T.write(\$L, \$L, \$L)", JavaNames.ART_TRANSPORT, value, buffer, at)
        .build()
}

internal fun bufferCopyOf(
    component: TypeName,
    buffer: String,
    at: CodeBlock,
    array: String,
    intoBuffer: Boolean,
): CodeBlock {
    if (component == TypeName.BOOLEAN) return booleanCopyOf(buffer, CodeBlock.of("\$L + ", at), array, intoBuffer)
    return CodeBlock.builder()
        .addStatement("\$L.position(\$L)", buffer, at)
        .addStatement("\$L\$L.\$L(\$L)", buffer, viewOf(component, false), if (intoBuffer) "put" else "get", array)
        .build()
}

internal fun plus(left: String, right: String): String = when {
    left == "0" -> right
    right == "0" -> left
    else -> "$left + $right"
}

internal fun plus(left: String, right: CodeBlock): CodeBlock =
    if (left == "0") right else CodeBlock.of("\$L + \$L", left, right)
