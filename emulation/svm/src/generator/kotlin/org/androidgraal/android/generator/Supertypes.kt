package org.androidgraal.android.generator

internal class Supertypes(val superclass: String?, val interfaces: List<String>) {
    val isEmpty: Boolean = superclass == null && interfaces.isEmpty()
}

internal fun supertypesOf(
    generated: Map<String, AndroidType>,
    all: Map<String, AndroidType>,
    handwritten: Set<String>,
): Map<String, Supertypes> {
    return generated.values.associate { type ->
        val superclass = type.superclass?.takeIf { name ->
            name.startsWith("android/") && (
                (name in generated && all.getValue(name).outermost == type.outermost) ||
                    (name.substringBefore('$') in handwritten && all[name]?.hasVisibleNoArgConstructor == true)
                )
        }
        val interfaces = type.interfaces.filter { name ->
            name.startsWith("android/") &&
                (name in generated || (name.substringBefore('$') in handwritten && name in all))
        }
        type.internalName to Supertypes(superclass, interfaces)
    }
}
