package org.androidgraal.buildlogic

import org.androidgraal.common.Ndk
import org.gradle.api.GradleException
import java.io.File

class NativeHost(val root: File, val settings: BuildSettings, val sdk: AndroidSdk, val path: List<File>) {

    val vendor: Vendor = Vendor(root.resolve("vendor"))

    val androidApi: Int = settings.androidApi

    val ndk: Ndk = resolveNdk()

    fun cmake(): CMake = CMake.find(settings, sdk, path)

    private fun resolveNdk(): Ndk {
        val dir = settings.ndkDir
        val resolved = if (dir == null) sdk.ndk(settings.ndkVersion) else ndkAt(dir)
        resolved.validate(androidApi)
        return resolved
    }

    private fun ndkAt(dir: File): Ndk {
        val explicit = Ndk(dir)
        val wanted = settings.ndkVersion ?: return explicit
        val found = explicit.version()
        if (found != wanted) {
            throw GradleException("ndk.dir $dir is NDK $found, androidgraal.ndk.version says $wanted")
        }
        return explicit
    }
}

class Vendor(val dir: File) {

    val graal: File = dir.resolve("graal")

    val llvm: File = dir.resolve("llvm-project")

    val mx: File = dir.resolve("mx")

    val labsOpenjdk: File = dir.resolve("labs-openjdk")
}
