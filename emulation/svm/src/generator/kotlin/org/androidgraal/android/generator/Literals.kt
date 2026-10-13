package org.androidgraal.android.generator

internal fun literalOf(constant: Constant): String {
    val value = constant.value
    return when (constant.descriptor) {
        "Z" -> if (value as Int != 0) "true" else "false"
        "C" -> "'${escaped((value as Int).toChar(), '\'')}'"
        "J" -> "${value}L"
        "F" -> floatLiteralOf(value as Float)
        "D" -> doubleLiteralOf(value as Double)
        "Ljava/lang/String;" -> "\"" + (value as String).map { escaped(it, '"') }.joinToString("") + "\""
        else -> value.toString()
    }
}

private fun floatLiteralOf(value: Float): String = when {
    value.isNaN() -> "Float.NaN"
    value == Float.POSITIVE_INFINITY -> "Float.POSITIVE_INFINITY"
    value == Float.NEGATIVE_INFINITY -> "Float.NEGATIVE_INFINITY"
    else -> "${value}f"
}

private fun doubleLiteralOf(value: Double): String = when {
    value.isNaN() -> "Double.NaN"
    value == Double.POSITIVE_INFINITY -> "Double.POSITIVE_INFINITY"
    value == Double.NEGATIVE_INFINITY -> "Double.NEGATIVE_INFINITY"
    else -> "${value}d"
}

private fun escaped(c: Char, quote: Char): String = when {
    c == quote || c == '\\' -> "\\$c"
    c == '\n' -> "\\n"
    c == '\r' -> "\\r"
    c == '\t' -> "\\t"
    c < ' ' -> "\\%03o".format(c.code)
    c.code >= 0x7f -> "\\u%04x".format(c.code)
    else -> c.toString()
}
