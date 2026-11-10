package org.androidgraal.handles.generator.emit

import com.palantir.javapoet.ClassName

internal object JavaNames {
    private const val IMAGE_PACKAGE = "org.androidgraal.android.handles"
    private const val ART_PACKAGE = "org.androidgraal.android.art.handles"
    private const val SHARED_PACKAGE = "org.androidgraal.shared"
    private const val ANNOTATE_PACKAGE = "org.androidgraal.hosted.annotate"
    private val JNI: ClassName = ClassName.get("org.androidgraal.svm.jni", "JNI")

    val IMAGE_TABLE: ClassName = ClassName.get(IMAGE_PACKAGE, "HandleClasses")
    val ART_TABLE: ClassName = ClassName.get(ART_PACKAGE, "HandleClasses")
    val EMULATED: ClassName = ClassName.get(ANNOTATE_PACKAGE, "Emulated")
    val ADDED: ClassName = ClassName.get(ANNOTATE_PACKAGE, "Added")
    val PROXY: ClassName = ClassName.get(SHARED_PACKAGE, "Proxy")
    val HANDLES: ClassName = ClassName.get(SHARED_PACKAGE, "Handles")
    val FACTORY: ClassName = HANDLES.nestedClass("Factory")
    val BLOCKS: ClassName = ClassName.get(SHARED_PACKAGE, "Blocks")
    val ART: ClassName = ClassName.get("org.androidgraal.svm", "Art")
    val ART_THREAD: ClassName = ART.nestedClass("ThreadData")
    val IMAGE_TRANSPORT: ClassName = ClassName.get("org.androidgraal.svm", "Transport")
    val JNI_ENV: ClassName = JNI.nestedClass("JNIEnv")
    val J_CLASS: ClassName = JNI.nestedClass("JClass")
    val J_METHOD_ID: ClassName = JNI.nestedClass("JMethodID")
    val J_VALUE: ClassName = JNI.nestedClass("JValue")
    val J_OBJECT: ClassName = JNI.nestedClass("JObject")
    val STACK_VALUE: ClassName = ClassName.get("org.graalvm.nativeimage", "StackValue")
    val BUILD_VERSION: ClassName = ClassName.get("android.os", "Build", "VERSION")
    val IMAGE: ClassName = ClassName.get("org.androidgraal.art", "Image")
    val IMAGE_THREAD: ClassName = IMAGE.nestedClass("ThreadData")
    val END_POINT: ClassName = ClassName.get("org.androidgraal.art", "EndPoint")
    val ART_TRANSPORT: ClassName = ClassName.get("org.androidgraal.art", "Transport")
    val BYTE_BUFFER: ClassName = ClassName.get("java.nio", "ByteBuffer")
    val IDENTITY_HASH_MAP: ClassName = ClassName.get("java.util", "IdentityHashMap")
    val REQUIRES_API: ClassName = ClassName.get("androidx.annotation", "RequiresApi")

    fun bindingOf(root: ClassName): ClassName = ClassName.get(IMAGE_PACKAGE, root.simpleName() + "Binding")

    fun endPointsOf(root: ClassName, level: Int?): ClassName =
        ClassName.get(ART_PACKAGE, root.simpleName() + "EndPoints" + (level ?: ""))
}
