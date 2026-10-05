package org.androidgraal.generator.scan

import org.androidgraal.common.Side
import javax.lang.model.element.ElementKind
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement

internal object Discovery {

    fun registrations(annotated: Annotated, errors: Errors): Registrations {
        val state = Registrations()
        bridge(annotated.bridge, state, errors)
        for (type in annotated.art) side(type, Side.ART, state, errors)
        for (type in annotated.svm) side(type, Side.SVM, state, errors)
        for (type in annotated.root) root(type, annotated.svm, state, errors)
        if (annotated.art.isEmpty() && annotated.svm.isEmpty()) {
            errors.add("nothing of the contract is @Art or @Svm")
        } else if (annotated.root.isEmpty()) {
            errors.add("no interface of the contract is @Root")
        }
        return state
    }

    private fun bridge(packages: Set<PackageElement>, state: Registrations, errors: Errors) {
        when (packages.size) {
            0 -> errors.add("no package of the contract carries @Bridge")
            1 -> state.packageName = "${packages.single().qualifiedName}.generated"
            else -> packages.forEach { errors.add("${it.qualifiedName} carries @Bridge: a contract has one", it) }
        }
    }

    private fun side(type: TypeElement, side: Side, state: Registrations, errors: Errors) {
        if (type.kind != ElementKind.INTERFACE) {
            errors.add("${type.qualifiedName} is @${side.title}, but only an interface crosses by reference", type)
            return
        }
        state.sides[type] = state.sides[type].orEmpty() + side
    }

    private fun root(type: TypeElement, svm: Set<TypeElement>, state: Registrations, errors: Errors) {
        if (type !in svm) {
            val name = type.qualifiedName
            errors.add("$name is @Root without @Svm: a root is implemented in the image, add @Svm", type)
        } else {
            state.roots += type
        }
    }
}
