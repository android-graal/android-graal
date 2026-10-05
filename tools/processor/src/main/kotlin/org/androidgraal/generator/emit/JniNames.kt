package org.androidgraal.generator.emit

internal fun jniName(className: String, method: String): String = "Java_${mangle(className)}_${mangle(method)}"

internal fun mangle(name: String): String {
    val mangled = StringBuilder()
    name.forEach { character ->
        when (character) {
            '.', '/' -> mangled.append('_')
            '_' -> mangled.append("_1")
            ';' -> mangled.append("_2")
            '[' -> mangled.append("_3")
            in 'a'..'z', in 'A'..'Z', in '0'..'9' -> mangled.append(character)
            else -> mangled.append("_0").append("%04x".format(character.code))
        }
    }
    return mangled.toString()
}
