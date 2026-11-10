package org.androidgraal.handles.generator.scan

import com.sun.source.tree.ClassTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.PrimitiveTypeTree
import com.sun.source.tree.Tree
import com.sun.source.tree.VariableTree
import com.sun.source.util.JavacTask
import com.sun.source.util.Trees
import org.androidgraal.handles.generator.model.Fates
import org.androidgraal.handles.generator.model.FieldFate
import org.androidgraal.handles.generator.model.Kept
import org.androidgraal.handles.generator.model.MethodFate
import org.androidgraal.handles.generator.model.Removed
import org.androidgraal.handles.generator.model.Root
import org.androidgraal.handles.generator.model.SourceFile
import org.androidgraal.handles.generator.model.SourcePlan
import org.androidgraal.handles.generator.model.Stubbed
import org.androidgraal.handles.generator.model.TypeFate
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.Verbatim
import org.androidgraal.handles.generator.model.fail
import java.io.File
import javax.lang.model.element.Modifier
import javax.tools.ToolProvider

private val CONSTANT = setOf(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)

internal class Members(private val sources: File, private val classpath: Classpath) {
    fun planOf(root: Root): SourcePlan {
        val source = sourceFileOf(sources.resolve("${root.internalName}.java"))
        val shape = SourceShape(root, source)
        val type = shape.type()
        val declarations = shape.declarationsOf(type)
        checkDeclared(root, declarations)
        val fields = declarations.fields.associateWith { fateOf(it, root) }
        val types = declarations.types.associateWith { fateOf(it, root) }
        val settled = settledOf(declarations.methods, root)
        val resolver = Resolver(
            unit = source.unit,
            classpath = classpath,
            top = Scope(type, root.internalName, null),
            declarations = declarations,
            removed = Fates(fields, settled, types).removed,
        )
        val hidden = hiddenOf(declarations.methods.filter { it !in settled }, resolver)
        val fates = Fates(fields, settled + hidden, types)
        checkKeptTexts(root, fates, declarations, resolver)
        checkConstants(root, fates, resolver)
        val removedAnnotations = absentAnnotationsOf(type, fates, resolver)
        shape.checkRemoved(removedAnnotations)
        shape.checkLevelGuards(resolver.presentImports)
        return SourcePlan(
            root = root,
            source = source,
            type = type,
            fates = fates,
            removedImports = resolver.absentImports,
            javaImport = shape.javaImportOf(resolver.presentImports),
            removedAnnotations = removedAnnotations,
            notes = notesOf(root, fates),
        )
    }

    private fun checkDeclared(root: Root, declarations: Declarations) {
        val fields = declarations.fields.map { it.name.toString() }
        val types = declarations.types.map { it.simpleName.toString() }
        val absent = (root.constants.filter { it !in fields } + root.values.filter { it !in types }).firstOrNull()
        if (absent != null) fail("${root.internalName} $absent has no declaration in the SDK source")
    }

    private fun fateOf(field: VariableTree, root: Root): FieldFate {
        val hidden = field.modifiers.flags.containsAll(CONSTANT) &&
            (field.type is PrimitiveTypeTree || field.type.toString() == "String")
        return if (field.name.toString() in root.constants || hidden) Kept else Removed.NOT_KEPT
    }

    private fun fateOf(type: ClassTree, root: Root): TypeFate {
        val kept = type.kind == Tree.Kind.ANNOTATION_TYPE || type.simpleName.toString() in root.values
        return if (kept) Kept else Removed.NOT_KEPT
    }

    private fun settledOf(methods: List<MethodTree>, root: Root): Map<MethodTree, MethodFate> {
        val name = root.internalName
        val shown = root.declared.map { it.method } + root.lacking
        val keys = methods.map { sourceKeyOf(name, it) }
        checkUnique(name, shown.map { it.sourceKey }, "android.jar")
        checkUnique(name, keys, "the SDK source")
        val absent = shown.firstOrNull { it.sourceKey !in keys }
        if (absent != null) fail("$name ${absent.key} has no declaration in the SDK source")
        val fates: Map<String, MethodFate> = root.declared.associateBy { it.method.sourceKey } +
            root.lacking.associate { it.sourceKey to Removed.LACKING_TYPE }
        return methods.zip(keys).mapNotNull { (method, key) ->
            val fate = fates[key] ?: if (method.name.contentEquals("<init>")) Removed.NOT_KEPT else null
            fate?.let { method to it }
        }.toMap()
    }

    private fun checkUnique(name: String, keys: List<String>, input: String) {
        val shared = keys.groupBy { it }.filterValues { it.size > 1 }.keys.firstOrNull()
        if (shared != null) fail("$name has two methods with the source key $shared in $input, $UNWRITTEN")
    }

    private fun hiddenOf(methods: List<MethodTree>, resolver: Resolver): Map<MethodTree, MethodFate> {
        val (headed, lacking) = methods.partition(resolver::isPresentHead)
        val stubbed = LinkedHashSet<MethodTree>()
        do {
            val gone = lacking.toSet() + stubbed
            val unresolved = headed.filter { it !in stubbed && !resolver.isResolvedBody(it, gone) }
            stubbed += unresolved
        } while (unresolved.isNotEmpty())
        return lacking.associateWith { Removed.HIDDEN_LACKING_TYPE } +
            headed.associateWith { if (it in stubbed) Stubbed else Verbatim }
    }

    private fun notesOf(root: Root, fates: Fates): List<String> {
        val lacking = root.lacking.associate { it.sourceKey to it.key }
        return fates.methods.mapNotNull { (method, fate) ->
            val key = sourceKeyOf(root.internalName, method)
            fate.note?.let { "${root.internalName} ${lacking[key] ?: key} $it" }
        }
    }

    private fun checkKeptTexts(root: Root, fates: Fates, declarations: Declarations, resolver: Resolver) {
        val top = root.name.simpleName()
        for (text in fates.nested + fates.constants + fates.verbatim) {
            val references = referencesOf(text)
            if (resolver.constructsTop(references)) {
                fail("${root.internalName} is constructed by text the handle keeps and its constructors are cut")
            }
            val names = references.used.filter { it !in references.declared } +
                references.selected.filter { (qualifier, _) -> qualifier == top }.map { (_, name) -> name }
            for (name in names) {
                val named = declarations.named(name).map(fates.all::get)
                if (named.any { it is Removed }) {
                    fail("${root.internalName} $name is named by text the handle keeps and is cut")
                }
                if (Stubbed in named) {
                    fail("${root.internalName} $name is named by text the handle keeps and is stubbed")
                }
            }
        }
    }

    private fun checkConstants(root: Root, fates: Fates, resolver: Resolver) {
        val name = root.internalName
        for (field in fates.constants) {
            val initializer = field.initializer ?: fail("$name ${field.name} has no initializer")
            if (!resolver.isPresentConstant(initializer, resolver.top)) {
                fail("$name ${field.name} is initialized from what the handle cuts or the emulation lacks")
            }
        }
    }
}

private fun sourceFileOf(file: File): SourceFile {
    if (!file.isFile) fail("$file is not in the SDK sources")
    val compiler = ToolProvider.getSystemJavaCompiler()
    compiler.getStandardFileManager(null, null, Charsets.UTF_8).use { files ->
        val units = files.getJavaFileObjects(file)
        val task = compiler.getTask(null, files, null, listOf("-proc:none"), null, units) as JavacTask
        val unit = task.parse().single()
        return SourceFile(file.readText(), unit, Trees.instance(task).sourcePositions)
    }
}
