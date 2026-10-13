@file:JvmName("ConstantsGenerator")

package org.androidgraal.android.generator

import java.io.File
import java.util.zip.ZipFile

fun main(args: Array<String>) {
    val (jar, sources, handwrittenDir, out) = args.map(::File)
    val handwritten = handwrittenDir.walk()
        .filter { it.isFile && it.extension == "java" }
        .map { it.relativeTo(handwrittenDir).invariantSeparatorsPath.removeSuffix(".java") }
        .toSet()
    val all = ZipFile(jar).use { androidTypesOf(it) }
    val wanted = HashSet<String>()
    for (type in all.values.filter { it.constants.isNotEmpty() }) {
        var name: String? = type.internalName
        while (name != null && wanted.add(name)) name = all[name]?.outer
    }
    val placed = wanted.filter { isPlaced(it, all, wanted) }
    val (skipped, generated) = placed.map { all.getValue(it) }.partition { it.outermost in handwritten }
    val types = generated.associateBy { it.internalName }
    val nested = generated.filter { it.outer != null }.sortedBy { it.simpleName }.groupBy { it.outer!! }
    val outermost = generated.filter { it.outer == null }.sortedBy { it.internalName }
    val docs = typeDocsOf(sources, outermost.map { it.internalName })
    val supertypes = supertypesOf(types, all, handwritten)
    val skeletons = Skeletons(nested, supertypes, docs)

    out.resolve("android").deleteRecursively()
    outermost.forEach { skeletons.javaFileOf(it).writeTo(out) }
    println(
        "generateAndroidConstants: ${generated.size} classes, ${generated.sumOf { it.constants.size }} constants, " +
            "${skeletons.documented} documented fields, ${supertypes.values.count { !it.isEmpty }} with supertypes, " +
            "${skipped.size} skipped hand-written classes",
    )
}

private fun isPlaced(name: String, all: Map<String, AndroidType>, wanted: Set<String>): Boolean {
    val outer = all[name]?.outer ?: return all.containsKey(name)
    return outer in wanted && isPlaced(outer, all, wanted)
}
