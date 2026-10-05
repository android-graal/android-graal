@file:JvmName("GlesGenerator")

package org.androidgraal.gles.generator

import com.palantir.javapoet.ClassName
import java.io.File
import java.util.zip.ZipFile

fun main(args: Array<String>) {
    val (jar, gl2, gl3, out) = args.map(::File)
    val es2 = functionsOf(gl2, JavaNames.GLES_V2)
    val es2Names = es2.mapTo(HashSet()) { it.name }
    val es3 = functionsOf(gl3, JavaNames.GLES_V3).filterNot { it.name in es2Names }
    val functions = (es2 + es3).associateBy { it.name }
    val (gles20, gles30) = ZipFile(jar).use { archive ->
        androidClassOf(archive, JavaNames.GLES20) to androidClassOf(archive, JavaNames.GLES30)
    }
    val images = listOf(
        Sources.imageOf(JavaNames.GLES20, ClassName.OBJECT, gles20, gles20.methods.map { memberOf(it, functions) }),
        Sources.imageOf(JavaNames.GLES30, JavaNames.GLES20, gles30, gles30.methods.map { memberOf(it, functions) }),
    )
    val bindings = listOf(Sources.bindingsOf(JavaNames.GLES_V2, es2), Sources.bindingsOf(JavaNames.GLES_V3, es3))
    (bindings + images).forEach { it.writeTo(out) }
}
