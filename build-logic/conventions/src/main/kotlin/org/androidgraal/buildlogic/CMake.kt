package org.androidgraal.buildlogic

import com.badlogic.gdx.jnigen.commons.Os
import org.androidgraal.common.Host
import org.gradle.api.GradleException
import java.io.File

class CMake(val cmake: File, val ninja: File) {

    companion object {

        /** [BuildSettings.cmakeDir], else the SDK package, else [path]; ninja next to that cmake, else [path]. */
        fun find(settings: BuildSettings, sdk: AndroidSdk, path: List<File>): CMake {
            val cmake = cmake(settings, sdk, path)
            val ninja = executable(cmake.parentFile, "ninja")
                ?: onPath(path, "ninja")
                ?: throw GradleException("no ninja: none next to $cmake, none on PATH")
            return CMake(cmake, ninja)
        }

        private fun cmake(settings: BuildSettings, sdk: AndroidSdk, path: List<File>): File {
            settings.cmakeDir?.let { dir ->
                val bin = dir.resolve("bin")
                return executable(bin, "cmake") ?: throw GradleException("no cmake in $bin")
            }
            val packages = sdk.root.resolve("cmake")
            settings.cmakeVersion?.let { version ->
                val bin = sdk.cmake(version)
                    ?: throw GradleException(
                        "no cmake $version under $packages; installed: " +
                            sdk.cmakes().keys.joinToString().ifEmpty { "none" },
                    )
                return executable(bin, "cmake") ?: throw GradleException("no cmake in $bin")
            }
            return sdk.cmake(null)?.let { executable(it, "cmake") }
                ?: onPath(path, "cmake")
                ?: throw GradleException(
                    "no cmake: no cmake.dir or \$CMAKE_HOME, no package under $packages, none on PATH",
                )
        }

        private fun onPath(path: List<File>, name: String): File? = path.firstNotNullOfOrNull { executable(it, name) }

        private fun executable(dir: File, name: String): File? =
            dir.resolve(if (Host.current().os == Os.Windows) "$name.exe" else name).takeIf { it.isFile }
    }
}
