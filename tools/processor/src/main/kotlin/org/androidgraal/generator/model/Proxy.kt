package org.androidgraal.generator.model

import org.androidgraal.common.Side
import javax.lang.model.element.TypeElement

internal class Proxy private constructor(
    val type: TypeElement,
    val implementation: Side,
    val crossing: List<Crossing>,
) {
    fun crossingName(crossing: Crossing): String {
        val name = "${crossing.method.simpleName}"
        val overloads = this.crossing.filter { "${it.method.simpleName}" == name }
        return if (overloads.size == 1) name else "$name$" + overloads.indexOf(crossing)
    }

    companion object {
        fun of(type: TypeElement, implementation: Side, crossing: Collection<Crossing>): Proxy =
            Proxy(type, implementation, crossing.sortedBy { it.method.signature() })
    }
}
