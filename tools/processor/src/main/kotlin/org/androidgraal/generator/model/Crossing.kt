package org.androidgraal.generator.model

import javax.lang.model.element.ExecutableElement

internal class Crossing(val method: ExecutableElement, val parameters: List<Operand>, val result: Operand)
