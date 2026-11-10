package org.androidgraal.handles.generator.scan

import com.sun.source.tree.ClassTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.Tree
import com.sun.source.tree.VariableTree

internal class Declarations(val fields: List<VariableTree>, val methods: List<MethodTree>, val types: List<ClassTree>) {
    fun named(name: String): List<Tree> {
        return fields.filter { it.name.contentEquals(name) } +
            methods.filter { it.name.contentEquals(name) } +
            types.filter { it.simpleName.contentEquals(name) }
    }
}
