package org.androidgraal.handles.generator.model

import java.lang.constant.MethodTypeDesc

internal class Method(
    val name: String,
    val type: MethodTypeDesc,
    val sourceKey: String,
    val types: List<String>,
    val isGeneric: Boolean,
    val isProtected: Boolean,
) {
    val key: String = name + type.descriptorString()
}
