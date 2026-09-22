package org.androidgraal.common

/** Names and values of the contract between the toolchain build and the plugin. */
object Toolchain {

    const val VERSION = "VERSION"

    /** The `Usage` attribute of the toolchain variant. */
    const val USAGE = "android-graal-toolchain"

    /** The artifact type of the unpacked toolchain directory, not the zip. */
    const val ARTIFACT_TYPE = "android-graal-toolchain"

    /** The LLVM tools substratevm execs; `llvm-config` must report major >= 20. */
    val LLVM_TOOLS: List<String> =
        listOf("llc", "opt", "llvm-link", "clang", "ld.lld", "llvm-objcopy", "llvm-config")

    val SVM_LIBS: List<String> = listOf("liblibchelper.a", "libjvm.a", "libsvm_container.a")

    /** The static libraries `make static-libs` produces. */
    val JDK_LIBS: List<String> = listOf(
        "libattach.a", "libextnet.a", "libj2gss.a", "libj2pcsc.a", "libj2pkcs11.a", "libjaas.a", "libjava.a",
        "libjimage.a", "libjli.a", "libjsig.a", "libmanagement.a", "libmanagement_agent.a", "libmanagement_ext.a",
        "libnet.a", "libnio.a", "libprefs.a", "librmi.a", "libsyslookup.a", "libverify.a", "libzip.a",
    )

    /** The files native-image reads from `-H:CAPCacheDir`. */
    val CAP_CACHE: List<String> = listOf(
        "AArch64LibCHelperDirectives.cap", "AMD64LibCHelperDirectives.cap", "ARM32LibCHelperDirectives.cap",
        "BuiltinDirectives.cap", "ContainerLibraryDirectives.cap", "JNIHeaderDirectives.cap", "LLVMDirectives.cap",
        "LocaleDirectives.cap", "PosixDirectives.cap", "RISCV64LibCHelperDirectives.cap",
    )
}
