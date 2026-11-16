package org.androidgraal.handles.generator.scan

import com.palantir.javapoet.ClassName
import org.androidgraal.handles.generator.model.Declared
import org.androidgraal.handles.generator.model.Forwarded
import org.androidgraal.handles.generator.model.Inherited
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

private val CLASS_MODIFIERS = mapOf(
    AccessFlag.PUBLIC to Modifier.PUBLIC,
    AccessFlag.ABSTRACT to Modifier.ABSTRACT,
    AccessFlag.FINAL to Modifier.FINAL,
)

internal const val CREATOR = "CREATOR"

internal const val OBJECT = "java/lang/Object"

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
    private val roots = HashMap<String, Root>()

    fun of(internalName: String): Root {
        val known = roots[internalName]
        if (known != null) return known
        val root = rootOf(internalName)
        roots[internalName] = root
        return root
    }

    private fun rootOf(internalName: String): Root {
        val type = ClassDesc.ofInternalName(internalName)
        val name = ClassName.get(type.packageName(), type.displayName())
        val model = classOf(androidJar, internalName)
        checkClass(name, model)
        val superclass = superclassOf(name, model)
        val interfaces = interfacesOf(name, model)
        val (creators, fields) = visibleFieldsOf(model).partition { isCreator(it, interfaces) }
        fields.forEach { checkField(name, it) }
        val (constructors, methods) = visibleMethodsOf(model)
            .partition { it.methodName().equalsString(ConstantDescs.INIT_NAME) }
        methods.forEach { checkMethod(name, it) }
        val (present, lacking) = methods.map { methodOf(internalName, it) }.sortedBy { it.name }
            .partition { method -> method.types.all(classpath::has) }
        val (presentConstructors, lackingConstructors) = constructors
            .partition { constructor -> methodOf(internalName, constructor).types.all(classpath::has) }
        presentConstructors.forEach { checkConstructor(internalName, it) }
        val upper = generateSequence(superclass) { it.superclass }.toList().asReversed()
        val (inherited, own) = present.partition { method -> upper.any { listsOf(it, method) } }
        val tops = inherited.associateWith { method -> upper.first { listsOf(it, method) } }
        val (inheritedPresent, inheritedLacking) = inherited.partition { tops.getValue(it).declares(it) }
        val nested = visibleNestedOf(model)
        val root = Root(
            name = name,
            internalName = internalName,
            modifiers = CLASS_MODIFIERS.filterKeys { it in model.flags().flags() }.values.toSet(),
            superclass = superclass,
            interfaces = interfaces.map { it.thisClass().asInternalName() },
            level = levels.sinceOf(internalName).takeIf { it > minSdk },
            declared = endPointsNamed(own.map { declaredOf(internalName, it, interfaces) }) +
                inheritedPresent.map { inheritedOf(it, tops.getValue(it)) },
            lacking = lackingConstructors.map { methodOf(internalName, it) } + lacking + inheritedLacking,
            constructors = presentConstructors.map { methodOf(internalName, it) },
            constants = fields.map { it.fieldName().stringValue() }.toSet(),
            creator = creators.isNotEmpty(),
            values = valuesOf(name, model, nested),
            cut = nested.keys.filter { it !in boundary.values },
        )
        checkCrossing(root)
        return root
    }

    private fun checkClass(name: ClassName, model: ClassModel) {
        if (model.findAttribute(Attributes.signature()).isPresent) fail("$name is generic, $UNWRITTEN")
    }

    private fun superclassOf(name: ClassName, model: ClassModel): Root? {
        val superclass = model.superclass().map { it.asInternalName() }
            .orElseGet { fail("$name has no superclass in android.jar") }
        if (superclass == OBJECT) return null
        if (superclass !in boundary.handles) {
            fail("$name extends $superclass, which is neither Object nor a handle of the proxy boundary, $UNWRITTEN")
        }
        return of(superclass)
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

    private fun checkConstructor(owner: String, constructor: MethodModel) {
        val shape = when {
            !constructor.flags().has(AccessFlag.PUBLIC) -> "is no public constructor"
            levels.sinceOf(owner, constructor.key(), null) > minSdk -> "is a constructor above the floor"
            else -> null
        }
        if (shape != null) fail("$owner ${constructor.key()} $shape, $UNWRITTEN")
    }

    private fun checkMethod(name: ClassName, method: MethodModel) {
        val flags = method.flags()
        val shape = when {
            flags.has(AccessFlag.STATIC) -> "is static"
            flags.has(AccessFlag.SYNTHETIC) || flags.has(AccessFlag.BRIDGE) -> "is synthetic"
            else -> null
        }
        if (shape != null) fail("$name ${method.key()} $shape, $UNWRITTEN")
    }

    private fun checkCrossing(root: Root) {
        if (root.superclass == null && root.forwarded.isEmpty()) {
            fail("${root.name} has no method that crosses, $UNWRITTEN")
        }
    }

    private fun listsOf(root: Root, method: Method): Boolean =
        root.declares(method) || root.lacking.any { it.key == method.key }

    private fun Root.declares(method: Method): Boolean = declared.any { it.method.key == method.key }

    private fun inheritedOf(method: Method, top: Root): Declared {
        val upper = top.declared.first { it.method.key == method.key }
        return when (upper) {
            is Forwarded -> Inherited(method, upper.level, upper.wire, top.name)
            is Unsupported -> Unsupported(method, upper.level)
            is Inherited -> fail("${top.internalName} ${method.key} is inherited where it is declared first")
        }
    }

    private fun endPointsNamed(declared: List<Declared>): List<Declared> {
        val crossing = declared.filterIsInstance<Forwarded>().map { it.method }
        return declared.map { fate ->
            val overloads = crossing.filter { it.name == fate.method.name }.sortedBy { it.type.descriptorString() }
            if (fate !is Forwarded || overloads.size == 1) return@map fate
            Forwarded(fate.method, fate.level, fate.wire.named("${fate.method.name}$${overloads.indexOf(fate.method)}"))
        }
    }

    private fun valuesOf(name: ClassName, model: ClassModel, nested: Map<String, String>): Set<String> {
        val values = boundary.values
            .filter { it.substringBeforeLast('$') == model.thisClass().asInternalName() }
            .toSet()
        val absent = values.filter { it !in nested }
        if (absent.isNotEmpty()) {
            fail("the proxy boundary lists the values $absent, which android.jar does not nest in $name, $UNWRITTEN")
        }
        return values.map { nested.getValue(it) }.toSet()
    }

    private fun declaredOf(owner: String, method: Method, interfaces: List<ClassModel>): Declared {
        val level = levelOf(owner, method, interfaces)
        if (method.isGeneric || method.isProtected) return Unsupported(method, level)
        val wire = wireOf(method, boundary.handles) ?: return Unsupported(method, level)
        return Forwarded(method, level, wire)
    }

    private fun levelOf(owner: String, method: Method, interfaces: List<ClassModel>): Int? {
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
        return since.takeIf { it > minSdk }
    }

    private fun MethodModel.key(): String = methodName().stringValue() + methodType().stringValue()

    private fun declares(type: ClassModel, method: Method): Boolean =
        type.methods().any { it.methodName().equalsString(method.name) && it.methodTypeSymbol() == method.type }

    private fun superInterfacesOf(type: ClassModel): List<ClassModel> {
        return type.interfaces().map { classOf(androidJar, it.asInternalName()) }
            .flatMap { listOf(it) + superInterfacesOf(it) }
    }
}
