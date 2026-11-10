package org.androidgraal.handles.generator.emit

import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.Tree
import org.androidgraal.handles.generator.model.Declared
import org.androidgraal.handles.generator.model.Forwarded
import org.androidgraal.handles.generator.model.MethodFate
import org.androidgraal.handles.generator.model.Removed
import org.androidgraal.handles.generator.model.SourcePlan
import org.androidgraal.handles.generator.model.Stubbed
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.Unsupported
import org.androidgraal.handles.generator.model.Verbatim
import org.androidgraal.handles.generator.model.fail

private const val COLUMNS = 100

private const val STUB = "throw new UnsupportedOperationException(\"hidden on ART\");"

private class Edit(val start: Int, val end: Int, val text: String)

internal class HandleSource(private val plan: SourcePlan) {
    private val source = plan.source
    private val type = plan.type
    private val name = plan.root.internalName
    private val bodyOpen = source.bodyOpenOf(type)

    fun text(): String {
        val annotations = plan.removedAnnotations.map(::removalOf).groupBy { source.lineOf(it.start) }
        val emptied = annotations.filter { (line, removals) -> remainderOf(line, removals).isBlank() }.keys
        val removed = plan.removedImports.map { source.lineOf(source.start(it)) } +
            emptied +
            plan.fates.removed.flatMap(::linesOf)
        val deleted = withBlankLines(removed.toSet())
        val edits = listOf(Edit(0, 0, "// $COMMENT\n")) +
            annotations.filterKeys { it !in emptied }.values.flatten() +
            plan.fates.methods.mapNotNull { (method, fate) -> bodyOf(method, fate) } +
            proxy(deleted) +
            emulated() +
            imports() +
            deleted.map { Edit(source.lineStart(it), source.lineStart(it + 1), "") }
        return applied(edits)
    }

    private fun removalOf(annotation: AnnotationTree): Edit {
        var end = source.end(annotation)
        while (source.text[end] == ' ') end++
        return Edit(source.start(annotation), end, "")
    }

    private fun remainderOf(line: Int, removals: List<Edit>): String {
        val offset = source.lineStart(line)
        return removals.sortedByDescending { it.start }.fold(source.lineText(line)) { rest, removal ->
            rest.removeRange(removal.start - offset, removal.end - offset)
        }
    }

    private fun linesOf(member: Tree): IntRange {
        val index = type.members.indexOf(member)
        val previousEnd = if (index == 0) bodyOpen else source.end(type.members[index - 1]) - 1
        val start = source.lineOf(source.start(member))
        var line = source.lineOf(previousEnd) + 1
        while (line < start && source.lineText(line).isBlank()) line++
        return line..source.lineOf(source.end(member) - 1)
    }

    private fun withBlankLines(removed: Set<Int>): Set<Int> {
        val deleted = HashSet(removed)
        for (line in 0 until source.lineCount) {
            if (line in deleted || !source.lineText(line).isBlank()) continue
            if (line - 1 !in removed && line + 1 !in removed) continue
            val previous = (line - 1 downTo 0).firstOrNull { it !in deleted }?.let(source::lineText).orEmpty()
            val next = (line + 1 until source.lineCount).firstOrNull { it !in deleted }?.let(source::lineText).orEmpty()
            if (previous.isBlank() ||
                previous.trimEnd().endsWith("{") ||
                next.isBlank() ||
                next.trimStart().startsWith("}")
            ) {
                deleted += line
            }
        }
        return deleted
    }

    private fun bodyOf(method: MethodTree, fate: MethodFate): Edit? {
        val indent = source.indentOf(source.lineOf(source.start(method)))
        val statements = when (fate) {
            is Declared -> forwardOf(method, fate, "$indent    ")
            Stubbed -> listOf("$indent    $STUB")
            Verbatim, is Removed -> return null
        }
        val text = "{\n" + statements.joinToString("\n") + "\n$indent}"
        return Edit(source.start(method.body), source.end(method.body), text)
    }

    private fun forwardOf(method: MethodTree, fate: Declared, inner: String): List<String> {
        val key = "\"${fate.method.key}\""
        val guard = fate.level?.let {
            listOf(
                "${inner}if (Build.VERSION.SDK_INT < $it) {",
                callOf(indent = "$inner    ", call = "throw new NoSuchMethodError", arguments = key),
                "$inner}",
            )
        }.orEmpty()
        return guard + when (fate) {
            is Unsupported -> callOf(indent = inner, call = "throw new UnsupportedOperationException", arguments = key)

            is Forwarded -> {
                val parameters = method.parameters.map { it.name.toString() }
                val receiver = if ("id" in parameters) "this.id" else "id"
                val binding = JavaNames.bindingOf(plan.root.name).simpleName()
                val arguments = (listOf(receiver) + parameters).joinToString(", ")
                callOf(indent = inner, call = "return $binding.${fate.wire.endPoint}", arguments = arguments)
            }
        }
    }

    private fun callOf(indent: String, call: String, arguments: String): String {
        val line = "$indent$call($arguments);"
        return if (line.length <= COLUMNS) line else "$indent$call(\n$indent        $arguments);"
    }

    private fun proxy(deleted: Set<Int>): List<Edit> {
        var at = bodyOpen
        while (source.text[at - 1].isWhitespace()) at--
        val close = source.lineOf(source.end(type) - 1)
        val last = (close - 1 downTo 0).firstOrNull { it !in deleted }?.let(source::lineText).orEmpty()
        if (last.isBlank()) fail("$name keeps a blank line before its closing brace, $UNWRITTEN")
        val members = """
            |
            |    @Added(reason = "the id of the ART object this handle forwards to")
            |    private final long id;
            |
            |    @Added(reason = "the handle of the ART object received under its id")
            |    public ${type.simpleName}(long id) {
            |        this.id = id;
            |    }
            |
            |    @Added(reason = "the id this handle crosses to ART as")
            |    @Override
            |    public long foreignId() {
            |        return id;
            |    }
            |
        """.trimMargin()
        return listOf(
            Edit(at, at, " implements Proxy"),
            Edit(source.lineStart(close), source.lineStart(close), members),
        )
    }

    private fun emulated(): Edit {
        val at = source.lineStart(source.lineOf(source.end(type.modifiers) - 1))
        return Edit(at, at, "@Emulated\n")
    }

    private fun imports(): Edit {
        val names = listOf(JavaNames.ADDED, JavaNames.EMULATED, JavaNames.PROXY, JavaNames.bindingOf(plan.root.name))
            .map { it.canonicalName() }
            .sorted()
        val at = source.lineStart(source.lineOf(source.start(plan.javaImport)))
        return Edit(at, at, names.joinToString("") { "import $it;\n" } + "\n")
    }

    private fun applied(edits: List<Edit>): String {
        val text = StringBuilder(source.text)
        var limit = Int.MAX_VALUE
        for (edit in edits.sortedWith(compareByDescending<Edit> { it.start }.thenByDescending { it.end })) {
            if (edit.end > limit) fail("$name: two edits overlap at offset ${edit.start}")
            text.replace(edit.start, edit.end, edit.text)
            limit = edit.start
        }
        return text.toString()
    }
}
