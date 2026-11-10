package org.androidgraal.handles.generator.model

import com.palantir.javapoet.ClassName
import javax.lang.model.element.Modifier

internal class Root(
    val name: ClassName,
    val internalName: String,
    val modifiers: Set<Modifier>,
    val level: Int?,
    val declared: List<Declared>,
    val lacking: List<Method>,
    val constants: Set<String>,
    val values: Set<String>,
) {
    val forwarded: List<Forwarded> = declared.filterIsInstance<Forwarded>()

    val levels: List<Int?> = forwarded.map { it.level }.distinct().sortedBy { it ?: 0 }
}
