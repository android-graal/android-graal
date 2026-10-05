package org.androidgraal.generator.emit

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.CodeBlock
import com.palantir.javapoet.TypeName
import org.androidgraal.generator.model.fail

internal fun zeroOf(type: TypeName): String = when {
    type == TypeName.VOID -> fail("void has no zero")
    type == TypeName.BOOLEAN -> "false"
    type.isPrimitive -> "0"
    else -> "null"
}

internal object JavaNames {
    private const val ART_PACKAGE = "org.androidgraal.art"
    private const val SVM_PACKAGE = "org.androidgraal.svm"
    private const val SHARED_PACKAGE = "org.androidgraal.shared"

    val IMAGE: ClassName = ClassName.get(ART_PACKAGE, "Image")
    val IMAGE_THREAD_DATA: ClassName = IMAGE.nestedClass("ThreadData")
    val ART_TRANSPORT: ClassName = ClassName.get(ART_PACKAGE, "Transport")
    val CONTRACT: ClassName = ClassName.get(ART_PACKAGE, "Contract")
    val END_POINT: ClassName = ClassName.get(ART_PACKAGE, "EndPoint")
    val ART: ClassName = ClassName.get(SVM_PACKAGE, "Art")
    val ART_THREAD_DATA: ClassName = ART.nestedClass("ThreadData")
    val TRANSPORT: ClassName = ClassName.get(SVM_PACKAGE, "Transport")
    private val JNI: ClassName = ClassName.get("$SVM_PACKAGE.jni", "JNI")
    val JNI_ENV: ClassName = JNI.nestedClass("JNIEnv")
    val JCLASS: ClassName = JNI.nestedClass("JClass")
    val JOBJECT: ClassName = JNI.nestedClass("JObject")
    val JMETHOD_ID: ClassName = JNI.nestedClass("JMethodID")
    val JVALUE: ClassName = JNI.nestedClass("JValue")
    val BLOCKS: ClassName = ClassName.get(SHARED_PACKAGE, "Blocks")
    val HEADER: CodeBlock = CodeBlock.of("\$T.HEADER", BLOCKS)
    val BLOCK_NULL: CodeBlock = CodeBlock.of("\$T.NULL", BLOCKS)
    val FOREIGN: ClassName = ClassName.get(SHARED_PACKAGE, "Foreign")
    val HANDLES: ClassName = ClassName.get(SHARED_PACKAGE, "Handles")
    val FACTORY: ClassName = HANDLES.nestedClass("Factory")
    val SERVICES: ClassName = ClassName.get(SHARED_PACKAGE, "Services")
    val C_ENTRY_POINT: ClassName = ClassName.get("org.graalvm.nativeimage.c.function", "CEntryPoint")
    val ISOLATE_THREAD_CONTEXT: ClassName = C_ENTRY_POINT.nestedClass("IsolateThreadContext")
    val STACK_VALUE: ClassName = ClassName.get("org.graalvm.nativeimage", "StackValue")
    val THREAD_LOCAL: ClassName = ClassName.get("java.lang", "ThreadLocal")
    val ATOMIC_BOOLEAN: ClassName = ClassName.get("java.util.concurrent.atomic", "AtomicBoolean")
    val BYTE_BUFFER: ClassName = ClassName.get("java.nio", "ByteBuffer")
}
