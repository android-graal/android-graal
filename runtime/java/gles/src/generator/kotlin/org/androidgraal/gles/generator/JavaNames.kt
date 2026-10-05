package org.androidgraal.gles.generator

import com.palantir.javapoet.ClassName

internal object JavaNames {
    private const val PACKAGE = "org.androidgraal.gles"
    private const val C_TYPE_PACKAGE = "org.graalvm.nativeimage.c.type"
    private const val C_FUNCTION_PACKAGE = "org.graalvm.nativeimage.c.function"

    val GLES20: ClassName = ClassName.get("android.opengl", "GLES20")
    val GLES30: ClassName = ClassName.get("android.opengl", "GLES30")
    val GLES_V2: ClassName = ClassName.get(PACKAGE, "GLESv2")
    val GLES_V3: ClassName = ClassName.get(PACKAGE, "GLESv3")
    val POINTER: ClassName = ClassName.get(PACKAGE, "Pointer")
    val COUNTS: ClassName = ClassName.get(PACKAGE, "Counts")
    val C_STRING: ClassName = ClassName.get(PACKAGE, "CString")
    val HANDWRITTEN: ClassName = ClassName.get(PACKAGE, "Handwritten")
    val EMULATED: ClassName = ClassName.get("org.androidgraal.hosted.annotate", "Emulated")
    val PINNED_OBJECT: ClassName = ClassName.get("org.graalvm.nativeimage", "PinnedObject")
    val C_FUNCTION: ClassName = ClassName.get(C_FUNCTION_PACKAGE, "CFunction")
    val C_LIBRARY: ClassName = ClassName.get(C_FUNCTION_PACKAGE, "CLibrary")
    val C_CHAR_POINTER: ClassName = ClassName.get(C_TYPE_PACKAGE, "CCharPointer")
    val C_CHAR_POINTER_POINTER: ClassName = ClassName.get(C_TYPE_PACKAGE, "CCharPointerPointer")
    val C_TYPE_CONVERSION: ClassName = ClassName.get(C_TYPE_PACKAGE, "CTypeConversion")
    val C_CHAR_POINTER_HOLDER: ClassName = C_TYPE_CONVERSION.nestedClass("CCharPointerHolder")
    val POINTER_BASE: ClassName = ClassName.get("org.graalvm.word", "PointerBase")
}
