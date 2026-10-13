package org.androidgraal.android.generator

import com.sun.source.tree.ClassTree
import com.sun.source.tree.VariableTree
import com.sun.source.util.DocTrees
import com.sun.source.util.JavacTask
import com.sun.source.util.TreePath
import java.io.File
import javax.tools.DiagnosticListener
import javax.tools.JavaFileObject
import javax.tools.ToolProvider

internal class TypeDoc(val comment: String?, val fields: Map<String, String>)

internal fun typeDocsOf(sources: File, outermost: Collection<String>): Map<String, TypeDoc> {
    val files = outermost.map { sources.resolve("$it.java") }.filter { it.isFile }
    val docs = HashMap<String, TypeDoc>()
    val compiler = ToolProvider.getSystemJavaCompiler()
    compiler.getStandardFileManager(null, null, Charsets.UTF_8).use { fileManager ->
        val quiet = DiagnosticListener<JavaFileObject> { }
        val units = fileManager.getJavaFileObjectsFromFiles(files)
        val task = compiler.getTask(null, fileManager, quiet, listOf("-proc:none"), null, units) as JavacTask
        val trees = DocTrees.instance(task)
        for (unit in task.parse()) {
            val unitPath = TreePath(unit)
            val prefix = unit.packageName?.toString()?.replace('.', '/')?.plus("/") ?: ""
            for (type in unit.typeDecls.filterIsInstance<ClassTree>()) {
                collect(trees, TreePath(unitPath, type), type, prefix + type.simpleName, docs)
            }
        }
    }
    return docs
}

private fun collect(
    trees: DocTrees,
    path: TreePath,
    type: ClassTree,
    internalName: String,
    docs: MutableMap<String, TypeDoc>,
) {
    val fields = HashMap<String, String>()
    for (member in type.members) {
        val memberPath = TreePath(path, member)
        when (member) {
            is ClassTree -> {
                val nested = "$internalName\$${member.simpleName}"
                collect(trees, memberPath, member, nested, docs)
            }

            is VariableTree -> trees.getDocComment(memberPath)?.let { fields[member.name.toString()] = it }
        }
    }
    docs[internalName] = TypeDoc(trees.getDocComment(path), fields)
}
