package org.androidgraal.handles.generator.scan

import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.ClassTree
import com.sun.source.tree.IdentifierTree
import com.sun.source.tree.MemberSelectTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.NewClassTree
import com.sun.source.tree.Tree
import com.sun.source.tree.VariableTree
import com.sun.source.util.TreeScanner

internal class References : TreeScanner<Void?, Void?>() {
    val used = LinkedHashSet<String>()
    val declared = HashSet<String>()
    val selected = LinkedHashSet<Pair<String, String>>()
    val constructed = LinkedHashSet<String>()

    override fun visitVariable(tree: VariableTree, unused: Void?): Void? {
        declared += tree.name.toString()
        return super.visitVariable(tree, unused)
    }

    override fun visitMethod(tree: MethodTree, unused: Void?): Void? {
        declared += tree.name.toString()
        return super.visitMethod(tree, unused)
    }

    override fun visitClass(tree: ClassTree, unused: Void?): Void? {
        declared += tree.simpleName.toString()
        return super.visitClass(tree, unused)
    }

    override fun visitIdentifier(tree: IdentifierTree, unused: Void?): Void? {
        used += tree.name.toString()
        return null
    }

    override fun visitMemberSelect(tree: MemberSelectTree, unused: Void?): Void? {
        val qualifier = tree.expression
        if (qualifier is IdentifierTree) selected += qualifier.name.toString() to tree.identifier.toString()
        return super.visitMemberSelect(tree, unused)
    }

    override fun visitNewClass(tree: NewClassTree, unused: Void?): Void? {
        constructed += tree.identifier.toString()
        return super.visitNewClass(tree, unused)
    }

    override fun visitAnnotation(tree: AnnotationTree, unused: Void?): Void? = null
}

internal fun referencesOf(tree: Tree): References {
    val references = References()
    if (tree is MethodTree) {
        references.scan(tree.parameters, null)
        references.scan(tree.body, null)
    } else {
        references.scan(tree, null)
    }
    return references
}
