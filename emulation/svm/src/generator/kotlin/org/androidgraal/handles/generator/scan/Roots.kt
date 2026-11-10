package org.androidgraal.handles.generator.scan

import com.palantir.javapoet.ClassName
import org.androidgraal.handles.generator.model.Declared
import org.androidgraal.handles.generator.model.Forwarded
import org.androidgraal.handles.generator.model.Method
import org.androidgraal.handles.generator.model.ProxyBoundary
import org.androidgraal.handles.generator.model.Root
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.Unsupported
import org.androidgraal.handles.generator.model.fail
import java.lang.classfile.Attributes
import java.lang.classfile.ClassModel
import java.lang.classfile.FieldModel
import java.lang.classfile.MethodModel
import java.lang.constant.ClassDesc
import java.lang.constant.ConstantDescs
import java.lang.reflect.AccessFlag
import java.util.zip.ZipFile
import javax.lang.model.element.Modifier

private val CLASS_MODIFIERS = mapOf(AccessFlag.PUBLIC to Modifier.PUBLIC, AccessFlag.FINAL to Modifier.FINAL)

internal class Roots(
    private val androidJar: ZipFile,
    private val levels: Levels,
    private val classpath: Classpath,
    private val boundary: ProxyBoundary,
    private val minSdk: Int,
) {
    fun of(internalName: String): Root {
        val type = ClassDesc.ofInternalName(internalName)
        val name = ClassName.get(type.packageName(), type.displayName())
        val model = classOf(androidJar, internalName)
        checkClass(name, model)
        val fields = visibleFieldsOf(model).onEach { checkField(name, it) }
        val methods = visibleMethodsOf(model).onEach { checkMethod(name, it) }.map { methodOf(internalName, it) }
        val (present, lacking) = methods.sortedBy { it.name }.partition { method -> method.types.all(classpath::has) }
        val root = Root(
            name = name,
            internalName = internalName,
            modifiers = CLASS_MODIFIERS.filterKeys { it in model.flags().flags() }.values.toSet(),
            level = levels.sinceOf(internalName).takeIf { it > minSdk },
            declared = present.map { declaredOf(internalName, it) },
            lacking = lacking,
            constants = fields.map { it.fieldName().stringValue() }.toSet(),
            values = valuesOf(name, model),
        )
        checkCrossing(root)
        return root
    }

    private fun checkClass(name: ClassName, model: ClassModel) {
        if (model.flags().has(AccessFlag.ABSTRACT)) fail("$name is abstract, $UNWRITTEN")
        if (!model.superclass().map { it.asSymbol() == ConstantDescs.CD_Object }.orElse(false)) {
            fail("$name extends a class other than Object, $UNWRITTEN")
        }
        if (model.interfaces().isNotEmpty()) fail("$name implements an interface, $UNWRITTEN")
        if (model.findAttribute(Attributes.signature()).isPresent) fail("$name is generic, $UNWRITTEN")
    }

    private fun checkField(name: ClassName, field: FieldModel) {
        if (!field.findAttribute(Attributes.constantValue()).isPresent) {
            fail("$name declares the field ${field.fieldName().stringValue()} that is no constant, $UNWRITTEN")
        }
    }

    private fun checkMethod(name: ClassName, method: MethodModel) {
        val flags = method.flags()
        val shape = when {
            method.methodName().equalsString(ConstantDescs.INIT_NAME) -> "is a constructor"
            flags.has(AccessFlag.STATIC) -> "is static"
            flags.has(AccessFlag.PROTECTED) -> "is protected"
            flags.has(AccessFlag.SYNTHETIC) || flags.has(AccessFlag.BRIDGE) -> "is synthetic"
            method.findAttribute(Attributes.exceptions()).isPresent -> "declares an exception"
            signatureOf(method).typeParameters().isNotEmpty() -> "is generic"
            else -> null
        }
        if (shape != null) {
            fail("$name ${method.methodName().stringValue()}${method.methodType().stringValue()} $shape, $UNWRITTEN")
        }
    }

    private fun checkCrossing(root: Root) {
        if (root.forwarded.isEmpty()) fail("${root.name} has no method that crosses, $UNWRITTEN")
        val overloads = root.forwarded.groupBy { it.wire.endPoint }.values.firstOrNull { it.size > 1 }.orEmpty()
        val keys = overloads.joinToString(" and ") { it.method.key }
        if (overloads.isNotEmpty()) fail("${root.name} has the crossing overloads $keys, $UNWRITTEN")
    }

    private fun valuesOf(name: ClassName, model: ClassModel): Set<String> {
        val nested = visibleNestedOf(model)
        val values = boundary.values
            .filter { it.substringBeforeLast('$') == model.thisClass().asInternalName() }
            .toSet()
        if (nested.keys != values) {
            fail(
                "android.jar nests ${nested.keys} in $name where the proxy boundary lists the values " +
                    "$values, $UNWRITTEN",
            )
        }
        return nested.values.toSet()
    }

    private fun declaredOf(owner: String, method: Method): Declared {
        val level = levels.sinceOf(owner, method.key).takeIf { it > minSdk }
        val wire = wireOf(method) ?: return Unsupported(method, level)
        return Forwarded(method, level, wire)
    }
}
