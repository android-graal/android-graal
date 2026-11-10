package org.androidgraal.handles.generator.scan

import org.androidgraal.handles.generator.model.ProxyBoundary
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.fail
import java.io.File
import java.lang.classfile.ClassFile
import java.lang.classfile.ClassModel
import java.lang.constant.ConstantDescs

internal class Classpath(private val emulation: File, private val boundary: ProxyBoundary) {
    fun has(internalName: String): Boolean {
        if (isOfBoundary(internalName)) return isKeptOfBoundary(internalName)
        return emulation.resolve("$internalName.class").isFile || jdkClassOf(internalName) != null
    }

    fun hasMember(internalName: String, member: String): Boolean {
        if (isOfBoundary(internalName)) return true
        val file = emulation.resolve("$internalName.class")
        if (file.isFile) return declares(ClassFile.of().parse(file.readBytes()), member)
        val type = jdkClassOf(internalName)
            ?: fail("$internalName is neither a class of the emulation nor of the JDK")
        return type.fields.any { it.name == member } || type.methods.any { it.name == member }
    }

    private fun isOfBoundary(internalName: String): Boolean = internalName.substringBefore('$') in boundary.handles

    private fun isKeptOfBoundary(internalName: String): Boolean {
        if (boundary.values.any { internalName.startsWith("$it$") }) {
            fail("$internalName is nested in a value of the proxy boundary, $UNWRITTEN")
        }
        return internalName in boundary.handles || internalName in boundary.values
    }

    private fun declares(model: ClassModel, member: String): Boolean {
        if (model.fields().any { it.fieldName().equalsString(member) }) return true
        if (model.methods().any { it.methodName().equalsString(member) }) return true
        val inherits = model.interfaces().isNotEmpty() ||
            model.superclass().map { it.asSymbol() != ConstantDescs.CD_Object }.orElse(false)
        if (inherits) {
            fail("${model.thisClass().asInternalName()} may inherit $member from a supertype, $UNWRITTEN")
        }
        return false
    }

    private fun jdkClassOf(internalName: String): Class<*>? {
        return try {
            Class.forName(internalName.replace('/', '.'), false, ClassLoader.getPlatformClassLoader())
        } catch (e: ClassNotFoundException) {
            null
        }
    }
}
