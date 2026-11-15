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

internal const val CREATOR = "CREATOR"

private val CREATOR_FLAGS = setOf(AccessFlag.PUBLIC, AccessFlag.STATIC, AccessFlag.FINAL)

private val PARCELABLE = ClassDesc.of("android.os.Parcelable")

private val CREATOR_TYPE = PARCELABLE.nested("Creator")

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
        val interfaces = interfacesOf(name, model)
        val (creators, fields) = visibleFieldsOf(model).partition { isCreator(it, interfaces) }
        fields.forEach { checkField(name, it) }
        val methods = visibleMethodsOf(model).onEach { checkMethod(name, it) }.map { methodOf(internalName, it) }
        val (present, lacking) = methods.sortedBy { it.name }.partition { method -> method.types.all(classpath::has) }
        val root = Root(
            name = name,
            internalName = internalName,
            modifiers = CLASS_MODIFIERS.filterKeys { it in model.flags().flags() }.values.toSet(),
            interfaces = interfaces.map { it.thisClass().asInternalName() },
            level = levels.sinceOf(internalName).takeIf { it > minSdk },
            declared = endPointsNamed(present.map { declaredOf(internalName, it, interfaces) }),
            lacking = lacking,
            constants = fields.map { it.fieldName().stringValue() }.toSet(),
            creator = creators.isNotEmpty(),
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
        if (model.findAttribute(Attributes.signature()).isPresent) fail("$name is generic, $UNWRITTEN")
    }

    private fun interfacesOf(name: ClassName, model: ClassModel): List<ClassModel> {
        return model.interfaces().map { it.asInternalName() }.map { type ->
            if (!classpath.has(type)) fail("$name implements $type, which the emulation lacks, $UNWRITTEN")
            classOf(androidJar, type)
        }
    }

    private fun isCreator(field: FieldModel, interfaces: List<ClassModel>): Boolean {
        return field.fieldName().equalsString(CREATOR) &&
            field.flags().flags().containsAll(CREATOR_FLAGS) &&
            field.fieldTypeSymbol() == CREATOR_TYPE &&
            interfaces.any { it.thisClass().asSymbol() == PARCELABLE }
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
    }

    private fun endPointsNamed(declared: List<Declared>): List<Declared> {
        val crossing = declared.filterIsInstance<Forwarded>().map { it.method }
        return declared.map { fate ->
            val overloads = crossing.filter { it.name == fate.method.name }.sortedBy { it.type.descriptorString() }
            if (fate !is Forwarded || overloads.size == 1) return@map fate
            Forwarded(fate.method, fate.level, fate.wire.named("${fate.method.name}$${overloads.indexOf(fate.method)}"))
        }
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

    private fun declaredOf(owner: String, method: Method, interfaces: List<ClassModel>): Declared {
        val declaring = interfaces.filter { declares(it, method) }
        if (declaring.size > 1) fail("$owner ${method.key} is declared by more than one interface, $UNWRITTEN")
        if (declaring.isEmpty() && !levels.lists(owner, method.key)) {
            val inheriting = interfaces.firstOrNull { type -> superInterfacesOf(type).any { declares(it, method) } }
            if (inheriting != null) {
                fail(
                    "$owner ${method.key} is declared by a super-interface of " +
                        "${inheriting.thisClass().asInternalName()}, $UNWRITTEN",
                )
            }
        }
        val since = levels.sinceOf(owner, method.key, declaring.singleOrNull()?.thisClass()?.asInternalName())
        val level = since.takeIf { it > minSdk }
        val wire = wireOf(owner, method, boundary.handles) ?: return Unsupported(method, level)
        return Forwarded(method, level, wire)
    }

    private fun declares(type: ClassModel, method: Method): Boolean =
        type.methods().any { it.methodName().equalsString(method.name) && it.methodTypeSymbol() == method.type }

    private fun superInterfacesOf(type: ClassModel): List<ClassModel> {
        return type.interfaces().map { classOf(androidJar, it.asInternalName()) }
            .flatMap { listOf(it) + superInterfacesOf(it) }
    }
}
