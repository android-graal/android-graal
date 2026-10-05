package org.androidgraal.generator.model

import javax.lang.model.element.ElementKind
import javax.lang.model.type.ArrayType
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror

internal enum class Kind(val label: String, val inRecord: Boolean) {
    VOID("void", false),
    PRIMITIVE("primitive", true),
    STRING("String", true),
    ENUM("ordinal", true),
    REFERENCE("reference", false),
    ARRAY("array", false),
    RECORD("record", true),
    ;

    companion object {
        fun of(type: TypeMirror): Kind? {
            if (type.kind == TypeKind.VOID) return VOID
            if (type.kind.isPrimitive) return PRIMITIVE
            if (type.isString()) return STRING
            if (type.kind == TypeKind.ARRAY) {
                return if ((type as ArrayType).componentType.kind.isPrimitive) ARRAY else null
            }
            if (type.kind != TypeKind.DECLARED || (type as DeclaredType).typeArguments.isNotEmpty()) return null
            return when (type.element().kind) {
                ElementKind.ENUM -> ENUM
                ElementKind.RECORD -> RECORD
                ElementKind.INTERFACE -> REFERENCE
                else -> null
            }
        }
    }
}
