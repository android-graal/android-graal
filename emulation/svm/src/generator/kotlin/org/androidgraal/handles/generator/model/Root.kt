package org.androidgraal.handles.generator.model

import com.palantir.javapoet.ClassName
import javax.lang.model.element.Modifier

internal class Root(
    val name: ClassName,
    val internalName: String,
    val modifiers: Set<Modifier>,
    val superclass: Root?,
    val interfaces: List<String>,
    val level: Int?,
    val declared: List<Declared>,
    val lacking: List<Method>,
    val constructors: List<Method>,
    val constants: Set<String>,
    val creator: Boolean,
    val values: Set<String>,
    val cut: List<String>,
) {
    val isAbstract: Boolean = Modifier.ABSTRACT in modifiers

    val forwarded: List<Forwarded> = declared.filterIsInstance<Forwarded>()

    val inherited: List<Inherited> = declared.filterIsInstance<Inherited>()

    val levels: List<Int?> = forwarded.map { it.level }.distinct().sortedBy { it ?: 0 }
}
