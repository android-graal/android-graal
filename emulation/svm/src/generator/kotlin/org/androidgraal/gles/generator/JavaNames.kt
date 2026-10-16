package org.androidgraal.gles.generator

import com.palantir.javapoet.ClassName

internal object JavaNames {
    private const val PACKAGE = "org.androidgraal.gles"
    private const val HELPER_PACKAGE = "org.androidgraal.android"
    private const val C_TYPE_PACKAGE = "org.graalvm.nativeimage.c.type"
    private const val C_FUNCTION_PACKAGE = "org.graalvm.nativeimage.c.function"

    val GLES20: ClassName = ClassName.get("android.opengl", "GLES20")
    val GLES30: ClassName = ClassName.get("android.opengl", "GLES30")
    val GLES31: ClassName = ClassName.get("android.opengl", "GLES31")
    val GLES32: ClassName = ClassName.get("android.opengl", "GLES32")
    val GLES_V2: ClassName = ClassName.get(PACKAGE, "GLESv2")
    val GLES_V3: ClassName = ClassName.get(PACKAGE, "GLESv3")
    val GLES_V32: ClassName = ClassName.get(PACKAGE, "GLESv32")
    val POINTER: ClassName = ClassName.get(HELPER_PACKAGE, "Pointer")
    val WORDS: ClassName = ClassName.get(HELPER_PACKAGE, "Words")
    val COUNTS: ClassName = ClassName.get(PACKAGE, "Counts")
    val C_STRING: ClassName = ClassName.get(HELPER_PACKAGE, "CString")
    val HANDWRITTEN: ClassName = ClassName.get(PACKAGE, "Handwritten")
    val EMULATED: ClassName = ClassName.get("org.androidgraal.hosted.annotate", "Emulated")
    val DEPRECATED: ClassName = ClassName.get("java.lang", "Deprecated")
    val PINNED_OBJECT: ClassName = ClassName.get("org.graalvm.nativeimage", "PinnedObject")
    val C_FUNCTION: ClassName = ClassName.get(C_FUNCTION_PACKAGE, "CFunction")
    val C_LIBRARY: ClassName = ClassName.get(C_FUNCTION_PACKAGE, "CLibrary")
    val C_FUNCTION_POINTER: ClassName = ClassName.get(C_FUNCTION_PACKAGE, "CFunctionPointer")
    val INVOKE_C_FUNCTION_POINTER: ClassName = ClassName.get(C_FUNCTION_PACKAGE, "InvokeCFunctionPointer")
    val C_CHAR_POINTER: ClassName = ClassName.get(C_TYPE_PACKAGE, "CCharPointer")
    val C_CHAR_POINTER_POINTER: ClassName = ClassName.get(C_TYPE_PACKAGE, "CCharPointerPointer")
    val C_TYPE_CONVERSION: ClassName = ClassName.get(C_TYPE_PACKAGE, "CTypeConversion")
    val C_CHAR_POINTER_HOLDER: ClassName = C_TYPE_CONVERSION.nestedClass("CCharPointerHolder")
    val POINTER_BASE: ClassName = ClassName.get("org.graalvm.word", "PointerBase")
    val SIGNED_WORD: ClassName = ClassName.get("org.graalvm.word", "SignedWord")
    val WORD_FACTORY: ClassName = ClassName.get("org.graalvm.word", "WordFactory")
}
