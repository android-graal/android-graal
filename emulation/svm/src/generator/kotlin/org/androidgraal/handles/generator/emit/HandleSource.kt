package org.androidgraal.handles.generator.emit

import com.palantir.javapoet.ClassName
import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.Tree
import org.androidgraal.handles.generator.model.Declared
import org.androidgraal.handles.generator.model.Forwarded
import org.androidgraal.handles.generator.model.Inherited
import org.androidgraal.handles.generator.model.MethodFate
import org.androidgraal.handles.generator.model.Removed
import org.androidgraal.handles.generator.model.SourcePlan
import org.androidgraal.handles.generator.model.Stubbed
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.Unsupported
import org.androidgraal.handles.generator.model.Verbatim
import org.androidgraal.handles.generator.model.VoidKind
import org.androidgraal.handles.generator.model.Wire
import org.androidgraal.handles.generator.model.fail
import java.lang.constant.ClassDesc
import java.lang.constant.MethodTypeDesc

private const val COLUMNS = 100

private val PARCEL = ClassDesc.of("android.os.Parcel")

private const val STUB = "throw new UnsupportedOperationException(\"hidden on ART\");"

private const val ABSTRACT = "abstract"

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
            plan.fates.methods.flatMap { (method, fate) -> bodyOf(method, fate) } +
            listOfNotNull(plan.createFromParcel?.let(::createFromParcelOf)) +
            proxy(deleted) +
            emulated() +
            imports() +
            deleted.map { Edit(source.lineStart(it), source.lineStart(it + 1), "") }
        val text = applied(edits)
        return if (source.endsWithLineBreak) text else text.removeSuffix("\n")
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

    private fun bodyOf(method: MethodTree, fate: MethodFate): List<Edit> {
        val indent = source.indentOf(source.lineOf(source.start(method)))
        if (fate is Declared && method.name.contentEquals("<init>")) return constructorOf(method, fate, indent)
        val statements = when (fate) {
            is Declared -> forwardOf(method, fate, "$indent    ")
            Stubbed -> listOf("$indent    $STUB")
            Verbatim, is Removed -> return emptyList()
        }
        val text = "{\n" + statements.joinToString("\n") + "\n$indent}"
        val body = method.body ?: return abstractOf(method, text)
        return listOf(Edit(source.start(body), source.end(body), text))
    }

    private fun abstractOf(method: MethodTree, body: String): List<Edit> {
        val modifiers = method.modifiers
        val start = source.start(modifiers)
        val masked = StringBuilder(source.text.substring(start, source.end(modifiers)))
        for (annotation in modifiers.annotations) {
            for (at in source.start(annotation) until source.end(annotation)) masked.setCharAt(at - start, ' ')
        }
        val keyword = Regex("\\babstract\\b").find(masked)?.range?.first?.plus(start)
        if (keyword == null || source.text[keyword + ABSTRACT.length] != ' ') {
            fail("$name ${method.name} has no abstract keyword followed by a space, $UNWRITTEN")
        }
        val end = source.end(method) - 1
        if (source.text[end] != ';') fail("$name ${method.name} does not end with a semicolon, $UNWRITTEN")
        return listOf(Edit(keyword, keyword + ABSTRACT.length + 1, ""), Edit(end, end + 1, " $body"))
    }

    private fun constructorOf(method: MethodTree, fate: Declared, indent: String): List<Edit> {
        if (fate !is Unsupported || fate.level != null) fail("$name ${fate.method.key} is a constructor, $UNWRITTEN")
        val statement = callOf("$indent    ", "throw new UnsupportedOperationException", "\"${fate.method.key}\"")
        val body = method.body
        val call = plan.constructorCalls[method]
            ?: return listOf(Edit(source.start(body), source.end(body), "{\n$statement\n$indent}"))
        val end = source.text.indexOf(';', source.end(call)) + 1
        return listOf(Edit(end, source.end(body), "\n$statement\n$indent}"))
    }

    private fun createFromParcelOf(method: MethodTree): Edit {
        val indent = source.indentOf(source.lineOf(source.start(method)))
        val descriptor = MethodTypeDesc.of(ClassDesc.ofInternalName(name), PARCEL).descriptorString()
        val key = "\"${method.name}$descriptor\""
        val statement = callOf("$indent    ", "throw new UnsupportedOperationException", key)
        return Edit(source.start(method.body), source.end(method.body), "{\n$statement\n$indent}")
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
            is Forwarded -> forwardOf(method, fate.wire, plan.root.name, inner)
            is Inherited -> forwardOf(method, fate.wire, fate.owner, inner)
        }
    }

    private fun forwardOf(method: MethodTree, wire: Wire, owner: ClassName, inner: String): String {
        val parameters = method.parameters.map { it.name.toString() }
        val binding = JavaNames.bindingOf(owner).simpleName()
        val arguments = (listOf("this") + parameters).joinToString(", ")
        val call = "$binding.${wire.endPoint}"
        val statement = if (wire.result == VoidKind) call else "return $call"
        return callOf(indent = inner, call = statement, arguments = arguments)
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
        val constructors = plan.addedConstructors.joinToString("") {
            """
            |
            |    @Added(reason = "the public constructor android.jar declares")
            |    public ${type.simpleName}() {
            |        throw new UnsupportedOperationException("${it.key}");
            |    }
            |
            """.trimMargin()
        }
        if (plan.root.superclass != null) {
            val received = """
                |
                |    @Added(reason = "the handle of the ART object received under its id")
                |    public ${type.simpleName}(long id) {
                |        super(id);
                |    }
                |
            """.trimMargin()
            return listOf(Edit(source.lineStart(close), source.lineStart(close), constructors + received))
        }
        val id = """
            |
            |    @Added(reason = "the id of the ART object this handle forwards to")
            |    private final long id;
            |
        """.trimMargin()
        val rest = """
            |
            |    @Added(reason = "the handle of the ART object received under its id")
            |    public ${type.simpleName}(long id) {
            |        this.id = id;
            |    }
            |
            |    @Added(reason = "the id this handle crosses to ART as")
            |    @Override
            |    public final long foreignId() {
            |        return id;
            |    }
            |
        """.trimMargin()
        val members = id + constructors + rest
        return listOf(
            Edit(at, at, if (type.implementsClause.isEmpty()) " implements Proxy" else ", Proxy"),
            Edit(source.lineStart(close), source.lineStart(close), members),
        )
    }

    private fun emulated(): Edit {
        val at = source.lineStart(source.lineOf(source.end(type.modifiers) - 1))
        return Edit(at, at, "@Emulated\n")
    }

    private fun imports(): Edit {
        val root = plan.root
        val build = JavaNames.BUILD_VERSION.enclosingClassName().takeIf { plan.addsBuild }
        val owners = listOf(root.name).filter { root.forwarded.isNotEmpty() } + root.inherited.map { it.owner }
        val proxy = JavaNames.PROXY.takeIf { root.superclass == null }
        val added = listOfNotNull(JavaNames.ADDED, JavaNames.EMULATED, proxy, build)
        val names = (added + owners.distinct().map(JavaNames::bindingOf)).map { it.canonicalName() }.sorted()
        val block = names.joinToString("") { "import $it;\n" }
        val java = plan.javaImport
        if (java != null) {
            val at = source.lineStart(source.lineOf(source.start(java)))
            return Edit(at, at, "$block\n")
        }
        val at = source.lineStart(source.lineOf(source.end(plan.lastImport) - 1) + 1)
        return Edit(at, at, "\n$block")
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
