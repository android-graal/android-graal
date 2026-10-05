package org.androidgraal.generator.model

import javax.lang.model.type.TypeMirror

internal enum class Direction(val label: String) {
    IN("in"),
    OUT("out"),
}

internal class Operand(
    val kind: Kind,
    val type: TypeMirror,
    val direction: Direction? = null,
    val capacity: Int? = null,
) {
    private val base: String = kind.label + (direction?.let { " ${it.label}" } ?: "")

    private val capacityLabel: String = capacity?.let { " static $it" } ?: ""

    val label: String = base + capacityLabel

    val resultLabel: String =
        base + (if (kind == Kind.VOID || kind == Kind.STRING) "" else " ${type.name()}") + capacityLabel
}
