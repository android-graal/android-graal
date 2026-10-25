package org.androidgraal.common

import com.badlogic.gdx.jnigen.commons.AndroidABI

enum class Target(
    val abi: AndroidABI,
    /** The target triple, the NDK's directory name under `sysroot/usr/lib`. */
    val triple: String,
    /** What the NDK clang is driven with: `--target=<clangTarget><api>`. */
    val clangTarget: String,
    /** `-Dsvm.platform`: a `Platform` leaf class. */
    val platform: String,
    /** `-Dsvm.targetArch`. */
    val arch: String,
    val march: String,
    /** The `jdk.internal.foreign.CABI` constant SVM's FFM downcalls follow. */
    val cabi: String,
    val lirBackend: Boolean,
) {
    AARCH64(
        AndroidABI.ABI_ARM64_V8A,
        "aarch64-linux-android",
        "aarch64-linux-android",
        "org.graalvm.nativeimage.Platform\$ANDROID_AARCH64",
        "aarch64",
        "armv8-a",
        "LINUX_AARCH_64",
        true,
    ),
    ARM32(
        AndroidABI.ABI_ARMEABI_V7A,
        "arm-linux-androideabi",
        "armv7a-linux-androideabi",
        "org.graalvm.nativeimage.Platform\$ANDROID_ARM32",
        "arm",
        "armv7-a",
        "UNSUPPORTED",
        false,
    ),
    ;

    val title: String = name.lowercase().replaceFirstChar(Char::uppercase)

    val dslName: String = name.lowercase()
}
