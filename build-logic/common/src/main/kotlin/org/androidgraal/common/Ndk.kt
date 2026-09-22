package org.androidgraal.common

import org.semver4j.Semver
import java.io.File

class Ndk(val root: File) {

    /** `Pkg.Revision` of `source.properties`. */
    fun version(): Semver = SourceProperties.revision(root.resolve(SourceProperties.FILE_NAME))

    val toolchain: File =
        root.resolve("toolchains").resolve("llvm").resolve("prebuilt").resolve(Host.current().ndkTag)

    val clang: File = toolchain.resolve("bin").resolve("clang")

    fun validate(api: Int) {
        check(toolchain.isDirectory) { "no NDK host toolchain at $toolchain" }
        for (target in Target.entries) {
            val level = toolchain.resolve("sysroot/usr/lib").resolve(target.triple).resolve(api.toString())
            check(level.isDirectory) { "the NDK cannot link for API level $api: no $level" }
        }
    }
}
