package org.androidgraal.handles.generator.scan

import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.ClassTree
import com.sun.source.tree.Tree
import com.sun.source.util.TreeScanner
import org.androidgraal.handles.generator.model.Declared
import org.androidgraal.handles.generator.model.Fates
import org.androidgraal.handles.generator.model.Removed
import org.androidgraal.handles.generator.model.Stubbed
import org.androidgraal.handles.generator.model.Verbatim

internal fun absentAnnotationsOf(
    type: ClassTree,
    fates: Fates,
    resolver: Resolver,
    replaced: Tree?,
): List<AnnotationTree> {
    val scanner = AbsentAnnotations(resolver, replaced)
    scanner.scan(type.modifiers, null)
    scanner.scan(fates.constants, null)
    scanner.scan(fates.creator, null)
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

private class AbsentAnnotations(private val resolver: Resolver, private val replaced: Tree?) :
    TreeScanner<Void?, Void?>() {
    val absent = ArrayList<AnnotationTree>()
    private val context = ArrayList(listOf(resolver.top))

    override fun scan(tree: Tree?, unused: Void?): Void? =
        if (tree != null && tree === replaced) null else super.scan(tree, unused)

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
