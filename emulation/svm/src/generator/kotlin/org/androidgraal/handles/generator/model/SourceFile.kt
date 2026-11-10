package org.androidgraal.handles.generator.model

import com.sun.source.tree.ClassTree
import com.sun.source.tree.CompilationUnitTree
import com.sun.source.tree.Tree
import com.sun.source.util.SourcePositions

internal class SourceFile(val text: String, val unit: CompilationUnitTree, private val positions: SourcePositions) {
    private val starts: IntArray = (listOf(0) + text.indices.filter { text[it] == '\n' }.map { it + 1 }).toIntArray()

    val lineCount: Int = starts.size - 1

    fun start(tree: Tree): Int = positions.getStartPosition(unit, tree).toInt()

    fun end(tree: Tree): Int = positions.getEndPosition(unit, tree).toInt()

    fun bodyOpenOf(type: ClassTree): Int = text.indexOf('{', end(type.modifiers))

    fun lineOf(offset: Int): Int {
        val index = starts.binarySearch(offset)
        return if (index >= 0) index else -index - 2
    }

    fun lineStart(line: Int): Int = starts[line]

    fun lineText(line: Int): String = text.substring(starts[line], starts[line + 1] - 1)

    fun indentOf(line: Int): String = lineText(line).takeWhile { it == ' ' }
}
