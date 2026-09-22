package org.androidgraal.substrate

import java.io.File

class CompiledImage(
    val objects: List<File>,
    val exportedSymbols: File,
    /** In link order, as native-image resolved them. */
    val staticLibraries: List<String>,
    val libraries: List<String>,
) {
    companion object {
        const val LLVM_OBJECT = "llvm.o"

        const val EXPORTED_SYMBOLS = "exported_symbols.list"

        const val STATIC_LIBRARIES = "static_libraries.list"

        const val LIBRARIES = "libraries.list"

        fun imageObject(dir: File, imageName: String): File = dir.resolve("$imageName.o")

        fun load(dir: File, imageName: String, useLLVM: Boolean): CompiledImage {
            val objects = buildList {
                add(imageObject(dir, imageName))
                if (useLLVM) add(dir.resolve(LLVM_OBJECT))
            }
            return CompiledImage(
                objects = objects,
                exportedSymbols = dir.resolve(EXPORTED_SYMBOLS),
                staticLibraries = names(dir.resolve(STATIC_LIBRARIES)),
                libraries = names(dir.resolve(LIBRARIES)),
            )
        }

        private fun names(file: File): List<String> = file.readLines().map(String::trim).filter(String::isNotEmpty)
    }
}
