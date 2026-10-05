package org.androidgraal.generator.scan

import org.androidgraal.common.Side
import javax.lang.model.element.TypeElement

internal class Registrations {
    lateinit var packageName: String

    val roots = linkedSetOf<TypeElement>()

    val sides = linkedMapOf<TypeElement, Set<Side>>()
}
