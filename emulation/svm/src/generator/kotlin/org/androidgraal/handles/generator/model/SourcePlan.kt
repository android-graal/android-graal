package org.androidgraal.handles.generator.model

import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.ClassTree
import com.sun.source.tree.ImportTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.Tree
import com.sun.source.tree.VariableTree

internal class Fates(
    fields: Map<VariableTree, FieldFate>,
    val methods: Map<MethodTree, MethodFate>,
    types: Map<ClassTree, TypeFate>,
) {
    val all: Map<Tree, Fate> = fields + methods + types

    val removed: Set<Tree> = all.filterValues { it is Removed }.keys

    val constants: List<VariableTree> = fields.filterValues { it == Kept }.keys.toList()

    val nested: List<ClassTree> = types.filterValues { it == Kept }.keys.toList()

    val verbatim: List<MethodTree> = methods.filterValues { it == Verbatim }.keys.toList()
}

internal class SourcePlan(
    val root: Root,
    val source: SourceFile,
    val type: ClassTree,
    val fates: Fates,
    val removedImports: Set<ImportTree>,
    val javaImport: ImportTree,
    val removedAnnotations: List<AnnotationTree>,
    val notes: List<String>,
)
