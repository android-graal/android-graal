package org.androidgraal.gles.generator

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.TypeName
import java.io.File

internal enum class GlType(private val spelling: String, val binding: TypeName, val size: Int) {
    VOID("void", TypeName.VOID, 1),
    BOOLEAN("GLboolean", TypeName.BOOLEAN, 1),
    CHAR("GLchar", TypeName.BYTE, 1),
    UBYTE("GLubyte", TypeName.BYTE, 1),
    ENUM("GLenum", TypeName.INT, 4),
    BITFIELD("GLbitfield", TypeName.INT, 4),
    INT("GLint", TypeName.INT, 4),
    UINT("GLuint", TypeName.INT, 4),
    SIZEI("GLsizei", TypeName.INT, 4),
    FLOAT("GLfloat", TypeName.FLOAT, 4),
    INTPTR("GLintptr", TypeName.LONG, 8),
    SIZEIPTR("GLsizeiptr", TypeName.LONG, 8),
    INT64("GLint64", TypeName.LONG, 8),
    UINT64("GLuint64", TypeName.LONG, 8),
    SYNC("GLsync", TypeName.LONG, 8),
    DEBUGPROC("GLDEBUGPROC", JavaNames.POINTER_BASE, 8),
    ;

    fun isCharacter(): Boolean = this == CHAR || this == UBYTE

    companion object {
        fun of(spelling: String): GlType = entries.first { it.spelling == spelling }
    }
}

internal class CType(val spelling: String, val base: GlType, val pointers: Int, val isConst: Boolean) {
    val isPointer: Boolean = pointers > 0

    val isString: Boolean = pointers == 1 && base == GlType.CHAR && isConst

    val bindingType: TypeName = when (pointers) {
        0 -> base.binding
        1 -> if (isConst && base.isCharacter()) JavaNames.C_CHAR_POINTER else JavaNames.POINTER_BASE
        else -> if (base == GlType.CHAR) JavaNames.C_CHAR_POINTER_POINTER else JavaNames.POINTER_BASE
    }
}

internal class CParam(val type: CType, val name: String)

internal class Library(val binding: ClassName, val name: String, val isResolvedAtRunTime: Boolean)

internal object Libraries {
    val GLES_V2: Library = Library(JavaNames.GLES_V2, "GLESv2", false)
    val GLES_V3: Library = Library(JavaNames.GLES_V3, "GLESv3", false)
    val GLES_V32: Library = Library(JavaNames.GLES_V32, "EGL", true)
}

internal class CFunction(val name: String, val result: CType, val params: List<CParam>, val library: Library)

private val PROTOTYPE = Regex("""GL_APICALL (.+?)\s*GL_APIENTRY (\w+) \((.*)\);""")

private val PARAMETER = Regex("""(.*?)(\w+)""")

internal fun functionsOf(header: File, library: Library): List<CFunction> =
    header.readLines().filter { it.startsWith("GL_APICALL ") }.map { functionOf(it, library) }

private fun functionOf(prototype: String, library: Library): CFunction {
    val (result, name, list) = PROTOTYPE.matchEntire(prototype)!!.destructured
    val params = if (list == "void") emptyList() else list.split(", ").map(::paramOf)
    return CFunction(name, typeOf(result), params, library)
}

private fun paramOf(text: String): CParam {
    val (type, name) = PARAMETER.matchEntire(text)!!.destructured
    return CParam(typeOf(type), name)
}

private fun typeOf(text: String): CType {
    val base = text.replace("const", "").replace("*", "").trim()
    return CType(text.trim(), GlType.of(base), text.count { it == '*' }, text.startsWith("const "))
}
