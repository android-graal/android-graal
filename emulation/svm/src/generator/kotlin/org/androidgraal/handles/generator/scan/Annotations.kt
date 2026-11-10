package org.androidgraal.handles.generator.scan

import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.ClassTree
import com.sun.source.util.TreeScanner
import org.androidgraal.handles.generator.model.Declared
import org.androidgraal.handles.generator.model.Fates
import org.androidgraal.handles.generator.model.Removed
import org.androidgraal.handles.generator.model.Stubbed
import org.androidgraal.handles.generator.model.Verbatim

internal fun absentAnnotationsOf(type: ClassTree, fates: Fates, resolver: Resolver): List<AnnotationTree> {
    val scanner = AbsentAnnotations(resolver)
    scanner.scan(type.modifiers, null)
    scanner.scan(fates.constants, null)
    scanner.scan(fates.nested, null)
    for ((method, fate) in fates.methods) {
        when (fate) {
            is Declared, Stubbed -> {
                scanner.scan(method.modifiers, null)
                scanner.scan(method.returnType, null)
                scanner.scan(method.parameters, null)
                scanner.scan(method.throws, null)
            }

            Verbatim -> scanner.scan(method, null)

            is Removed -> {}
        }
    }
    return scanner.absent
}

private class AbsentAnnotations(private val resolver: Resolver) : TreeScanner<Void?, Void?>() {
    val absent = ArrayList<AnnotationTree>()
    private val context = ArrayList(listOf(resolver.top))

    override fun visitClass(tree: ClassTree, unused: Void?): Void? {
        val scope = resolver.scopeOf(tree)
        if (scope != null) context += scope
        super.visitClass(tree, unused)
        if (scope != null) context.removeLast()
        return null
    }

    override fun visitAnnotation(tree: AnnotationTree, unused: Void?): Void? {
        if (!resolver.isPresentAnnotation(tree, context.last())) absent += tree
        return null
    }
}
