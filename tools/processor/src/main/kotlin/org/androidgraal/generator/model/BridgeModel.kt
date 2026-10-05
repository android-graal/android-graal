package org.androidgraal.generator.model

import com.palantir.javapoet.ClassName
import org.androidgraal.common.Side
import java.nio.ByteBuffer
import java.security.MessageDigest
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

internal class BridgeModel private constructor(
    val roots: List<TypeElement>,
    val packageName: String,
    val proxies: List<Proxy>,
    val enums: List<TypeElement>,
    val records: List<TypeElement>,
) {
    val contractClass: ClassName = ClassName.get(packageName, "Bridge")

    fun generatedClass(proxy: Proxy): ClassName =
        ClassName.get(packageName, "\$${proxy.type.simpleName}${proxy.implementation.title}")

    fun proxyClass(type: TypeElement, receiver: Side): ClassName? =
        proxies.find { it.type == type && it.implementation == receiver.other() }?.let(::generatedClass)

    fun generatedClasses(): List<ClassName> = listOf(contractClass) + proxies.map(::generatedClass)

    fun instantiateMethod(type: TypeElement): String = "instantiate${type.simpleName}"

    fun enumArray(type: TypeElement): String = "${type.simpleName}".uppercase() + "_VALUES"

    fun report(): String {
        val out = StringBuilder()
        out.append("contract class ${contractClass.canonicalName()}\n")
        roots.forEach { out.append("root ${it.qualifiedName}\n") }
        proxies.forEach { proxy ->
            out.append("${proxy.type.qualifiedName} implemented on ${proxy.implementation.label}\n")
            proxy.crossing.forEach { crossing ->
                out.append("  ${proxy.crossingName(crossing)} ${crossing.method.signature()}: ")
                out.append(crossing.parameters.joinToString(", ", "(", ")") { it.label })
                out.append(" -> ${crossing.result.resultLabel}\n")
            }
        }
        enums.forEach { type ->
            val constants = type.enclosedElements.filter { it.kind == ElementKind.ENUM_CONSTANT }
            out.append("enum ${type.qualifiedName} as ordinal of ${constants.joinToString(", ") { it.simpleName }}\n")
        }
        records.forEach { record ->
            val components = record.recordComponents.joinToString(", ") { "${it.simpleName} ${it.asType().name()}" }
            out.append("record ${record.qualifiedName}: $components\n")
        }
        return out.toString()
    }

    fun fingerprint(): Long = ByteBuffer.wrap(MessageDigest.getInstance("SHA-256").digest(report().toByteArray())).long

    companion object {
        fun of(
            roots: Collection<TypeElement>,
            packageName: String,
            proxies: Collection<Proxy>,
            enums: Collection<TypeElement>,
            records: Collection<TypeElement>,
        ): BridgeModel = BridgeModel(
            roots.sortedBy { "${it.qualifiedName}" },
            packageName,
            proxies.sortedWith(compareBy({ "${it.type.qualifiedName}" }, { it.implementation })),
            enums.sortedBy { "${it.qualifiedName}" },
            records.sortedBy { "${it.qualifiedName}" },
        )
    }
}
