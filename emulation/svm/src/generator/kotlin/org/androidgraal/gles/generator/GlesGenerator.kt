@file:JvmName("GlesGenerator")

package org.androidgraal.gles.generator

import com.palantir.javapoet.ClassName
import java.io.File
import java.util.zip.ZipFile
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC

fun main(args: Array<String>) {
    val (jar, gl2, gl3, gl31, gl32) = args.take(5).map(::File)
    val out = File(args[5])
    val es2 = functionsOf(gl2, Libraries.GLES_V2)
    val es3 = newIn(functionsOf(gl3, Libraries.GLES_V3), es2)
    val es31 = newIn(functionsOf(gl31, Libraries.GLES_V3), es2 + es3)
    val es32 = newIn(functionsOf(gl32, Libraries.GLES_V32), es2 + es3 + es31)
    val functions = (es2 + es3 + es31 + es32).associateBy { it.name }
    val android = ZipFile(jar).use { archive ->
        listOf(JavaNames.GLES20, JavaNames.GLES30, JavaNames.GLES31, JavaNames.GLES32)
            .map { androidClassOf(archive, it) }
    }
    val (gles20, gles30, gles31, gles32) = android
    val images = listOf(
        Sources.imageOf(JavaNames.GLES20, ClassName.OBJECT, listOf(PUBLIC), gles20, membersOf(gles20, functions)),
        Sources.imageOf(JavaNames.GLES30, JavaNames.GLES20, listOf(PUBLIC), gles30, membersOf(gles30, functions)),
        Sources.imageOf(JavaNames.GLES31, JavaNames.GLES30, emptyList(), gles31, membersOf(gles31, functions)),
        Sources.imageOf(JavaNames.GLES32, JavaNames.GLES31, listOf(PRIVATE), gles32, membersOf(gles32, functions)),
    )
    val bindings = listOf(
        Sources.bindingsOf(Libraries.GLES_V2, es2),
        Sources.bindingsOf(Libraries.GLES_V3, es3 + es31),
        Sources.pointersOf(Libraries.GLES_V32, es32),
    )
    (bindings + images).forEach { it.writeTo(out) }
}

private fun newIn(functions: List<CFunction>, earlier: List<CFunction>): List<CFunction> {
    val names = earlier.mapTo(HashSet()) { it.name }
    return functions.filterNot { it.name in names }
}

private fun membersOf(android: AndroidClass, functions: Map<String, CFunction>): List<Member> =
    android.methods.map { memberOf(it, functions) }
