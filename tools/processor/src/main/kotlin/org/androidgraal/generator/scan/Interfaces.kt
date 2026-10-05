package org.androidgraal.generator.scan

import org.androidgraal.common.Side
import org.androidgraal.generator.model.Crossing
import org.androidgraal.generator.model.Direction
import org.androidgraal.generator.model.JdkNames
import org.androidgraal.generator.model.Kind
import org.androidgraal.generator.model.Operand
import org.androidgraal.generator.model.Proxy
import org.androidgraal.generator.model.element
import org.androidgraal.generator.model.fail
import org.androidgraal.generator.model.isPublic
import org.androidgraal.generator.model.name
import org.androidgraal.generator.model.qualifiedName
import org.androidgraal.generator.model.signature
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.ElementFilter
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

internal class Interfaces(
    private val registrations: Registrations,
    private val elements: Elements,
    private val types: Types,
    private val errors: Errors,
) {

    val proxies = mutableListOf<Proxy>()
    val enums = linkedSetOf<TypeElement>()
    val records = linkedSetOf<TypeElement>()

    fun analyze() {
        registrations.sides.forEach { (type, registered) ->
            registered.forEach { proxies += proxyOf(type, it) }
        }
        (registrations.sides.keys + enums + records).filterNot { it.isPublic() }.forEach {
            errors.add("${it.qualifiedName()} crosses, but it is not public. Make it public", it)
        }
    }

    private fun proxyOf(type: TypeElement, implementation: Side): Proxy {
        val methods = methodsOf(type)
        reportReserved(type, methods)
        return Proxy.of(type, implementation, methods.map(::crossingOf))
    }

    private fun crossingOf(method: ExecutableElement): Crossing {
        reportChecked(method)
        val parameters = method.parameters.map(::operandOf)
        val result = resultOf(method)
        return Crossing(method, parameters, result)
    }

    private fun operandOf(parameter: VariableElement): Operand {
        val type = parameter.asType()
        val kind = Kind.of(type)
        if (kind == null) {
            reportNoKind(type, parameter)
            return Operand(Kind.VOID, type)
        }
        collectType(kind, type, parameter)
        val direction = directionOf(parameter, kind)
        if (kind == Kind.ARRAY && direction == null) {
            errors.add(
                "${parameter.qualifiedName()} crosses ${type.name()} without a direction, annotate it @In or @Out",
                parameter,
            )
        }
        return Operand(kind, type, direction, capacityOf(parameter, kind))
    }

    private fun resultOf(method: ExecutableElement): Operand {
        val type = method.returnType
        val kind = Kind.of(type)
        if (kind == null) {
            reportNoKind(type, method)
            return Operand(Kind.VOID, type)
        }
        collectType(kind, type, method)
        if (kind == Kind.RECORD) {
            errors.add("${method.qualifiedName()} returns ${type.name()}, a record crosses as a parameter only", method)
        }
        return Operand(kind, type, capacity = capacityOf(method, kind))
    }

    private fun methodsOf(type: TypeElement): List<ExecutableElement> {
        return ElementFilter.methodsIn(elements.getAllMembers(type))
            .filter { Modifier.PUBLIC in it.modifiers && Modifier.STATIC !in it.modifiers }
            .filter { it.enclosingElement != typeElement(JdkNames.OBJECT) }
            .groupBy { it.signature() }
            .values.map { declared ->
                declared.reduce { a, b -> if (types.isSubtype(b.returnType, a.returnType)) b else a }
            }
    }

    private fun directionOf(parameter: VariableElement, kind: Kind): Direction? {
        val input = parameter.hasAnnotation(Annotations.IN)
        val output = parameter.hasAnnotation(Annotations.OUT)
        if (!input && !output) return null
        if (input && output) {
            errors.add("${parameter.qualifiedName()} is both @In and @Out, a directed array crosses one way", parameter)
        } else if (kind != Kind.ARRAY) {
            errors.add("${parameter.qualifiedName()} has a direction, only a primitive array has one", parameter)
        }
        return if (input) Direction.IN else Direction.OUT
    }

    private fun capacityOf(at: Element, kind: Kind): Int? {
        val buffer = at.annotation(Annotations.STATIC_BUFFER) ?: return null
        val capacity = buffer.intValue(Annotations.CAPACITY)
        if (kind != Kind.ARRAY && kind != Kind.STRING) {
            errors.add(
                "${at.qualifiedName()} has @StaticBuffer, only a directed array or a String has a static buffer",
                at,
            )
        } else if (capacity <= 0) {
            errors.add(
                "${at.qualifiedName()} has @StaticBuffer(capacity = $capacity), a static buffer holds at least one" +
                    " element",
                at,
            )
        }
        return capacity
    }

    private fun collectType(kind: Kind, type: TypeMirror, at: Element) {
        when (kind) {
            Kind.ENUM -> {
                enums += type.element()
            }

            Kind.RECORD -> {
                collectRecord(type.element())
            }

            Kind.REFERENCE -> {
                if (type.element() !in registrations.sides) {
                    errors.add(
                        "${at.qualifiedName()} crosses ${type.name()} by reference, which is not bridged: annotate it" +
                            " @Art or @Svm",
                        at,
                    )
                }
            }

            else -> {}
        }
    }

    private fun collectRecord(record: TypeElement, path: Set<TypeElement> = setOf(record)) {
        if (!records.add(record)) return
        record.recordComponents.forEach { component ->
            val type = component.asType()
            val kind = Kind.of(type)
            if (kind == null || !kind.inRecord) {
                errors.add(
                    "${component.qualifiedName()} is ${type.name()}, a record crossing by value holds primitives," +
                        " String, enums and records only",
                    component,
                )
            } else if (kind == Kind.RECORD) {
                if (type.element() in path) {
                    errors.add(
                        "${component.qualifiedName()} is ${type.name()}, a record cannot contain itself",
                        component,
                    )
                } else {
                    collectRecord(type.element(), path + type.element())
                }
            } else {
                collectType(kind, type, component)
            }
        }
    }

    private fun reportReserved(type: TypeElement, methods: List<ExecutableElement>) {
        methods
            .filter { it.simpleName.toString() in RESERVED_METHODS }
            .forEach { errors.add("${it.qualifiedName()} uses a name reserved by the generated proxy, rename it", it) }
        ElementFilter.fieldsIn(elements.getAllMembers(type))
            .filter { it.simpleName.toString() in RESERVED_FIELDS }
            .forEach { errors.add("${it.qualifiedName()} uses a name reserved by the generated proxy, rename it", it) }
    }

    private fun reportNoKind(type: TypeMirror, at: Element) {
        errors.add(
            "${at.qualifiedName()} cannot cross ${type.name()}, only primitives, String, enums, records, directed" +
                " primitive arrays and registered interfaces cross",
            at,
        )
    }

    private fun reportChecked(method: ExecutableElement) {
        method.thrownTypes.filter {
            !types.isSubtype(it, typeElement(JdkNames.RUNTIME_EXCEPTION).asType()) &&
                !types.isSubtype(it, typeElement(JdkNames.ERROR).asType())
        }.forEach {
            errors.add(
                "${method.qualifiedName()} declares throws ${it.name()}, which is checked and cannot cross: wrap it" +
                    " in an unchecked exception. The cause arrives as a MarshalledException naming it",
                method,
            )
        }
    }

    private fun typeElement(name: String): TypeElement =
        elements.getTypeElement(name) ?: fail("$name is on neither the contract classpath nor the JDK")

    companion object {
        val RESERVED_METHODS = setOf("foreignId", "foreignCheck", "markReleased", "isReleased")
        val RESERVED_FIELDS = setOf("id")
    }
}
