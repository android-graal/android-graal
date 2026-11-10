package org.androidgraal.handles.generator.emit

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.FieldSpec
import com.palantir.javapoet.JavaFile
import com.palantir.javapoet.MethodSpec
import com.palantir.javapoet.TypeName
import com.palantir.javapoet.TypeSpec
import org.androidgraal.handles.generator.model.HandleModel
import javax.lang.model.element.Modifier.FINAL
import javax.lang.model.element.Modifier.PRIVATE
import javax.lang.model.element.Modifier.PUBLIC
import javax.lang.model.element.Modifier.STATIC

internal const val COMMENT = "Generated from android.jar, its api-versions.xml, its sources and the proxy boundary."

internal abstract class Sources(internal val model: HandleModel) {
    internal val fingerprintField: FieldSpec = FieldSpec.builder(TypeName.LONG, "FINGERPRINT", PUBLIC, STATIC, FINAL)
        .initializer("0x\$LL", "%016x".format(model.fingerprint))
        .build()

    internal val privateConstructor: MethodSpec = MethodSpec.constructorBuilder().addModifiers(PRIVATE).build()

    internal abstract fun render(): List<JavaFile>

    internal fun javaFileOf(name: ClassName, type: TypeSpec): JavaFile {
        return JavaFile.builder(name.packageName(), type)
            .addFileComment(COMMENT)
            .skipJavaLangImports(true)
            .indent("    ")
            .build()
    }

    internal fun capitalized(type: TypeName): String = type.toString().replaceFirstChar(Char::uppercase)
}
