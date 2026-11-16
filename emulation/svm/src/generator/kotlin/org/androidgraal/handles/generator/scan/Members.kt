package org.androidgraal.handles.generator.scan

import com.sun.source.tree.ClassTree
import com.sun.source.tree.ExpressionStatementTree
import com.sun.source.tree.IdentifierTree
import com.sun.source.tree.MethodInvocationTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.PrimitiveTypeTree
import com.sun.source.tree.Tree
import com.sun.source.tree.VariableTree
import com.sun.source.util.JavacTask
import com.sun.source.util.Trees
import org.androidgraal.handles.generator.model.Fates
import org.androidgraal.handles.generator.model.FieldFate
import org.androidgraal.handles.generator.model.Kept
import org.androidgraal.handles.generator.model.KeptCreator
import org.androidgraal.handles.generator.model.Method
import org.androidgraal.handles.generator.model.MethodFate
import org.androidgraal.handles.generator.model.Removed
import org.androidgraal.handles.generator.model.Root
import org.androidgraal.handles.generator.model.SourceFile
import org.androidgraal.handles.generator.model.SourcePlan
import org.androidgraal.handles.generator.model.Stubbed
import org.androidgraal.handles.generator.model.TypeFate
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.Unsupported
import org.androidgraal.handles.generator.model.Verbatim
import org.androidgraal.handles.generator.model.fail
import java.io.File
import javax.lang.model.element.Modifier
import javax.tools.ToolProvider

private val CONSTANT = setOf(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)

private val NOT_OVERRIDABLE = setOf(Modifier.STATIC, Modifier.FINAL, Modifier.PRIVATE)

private const val INIT = "<init>"

private const val SUPER = "super"

private val EXPLICIT_CALLS = setOf(SUPER, "this")

internal class Members(private val sources: File, private val classpath: Classpath) {
    private val nested = HashMap<String, List<String>>()

    fun planOf(root: Root): SourcePlan {
        val source = sourceFileOf(sources.resolve("${root.internalName}.java"))
        val shape = SourceShape(root, source)
        val type = shape.type()
        val declarations = shape.declarationsOf(type)
        checkDeclared(root, declarations)
        val fields = declarations.fields.associateWith { fateOf(it, root) }
        val createFromParcel = fields.filterValues { it == KeptCreator }.keys.singleOrNull()
            ?.let(shape::createFromParcelOf)
        val types = declarations.types.associateWith { fateOf(it, root) }
        val settled = settledOf(declarations.methods, root)
        val resolver = Resolver(
            unit = source.unit,
            classpath = classpath,
            top = Scope(type, root.internalName, null),
            declarations = declarations,
            removed = Fates(fields, settled, types).removed,
            inherited = inheritedTypesOf(root),
        )
        val hidden = hiddenOf(root, declarations.methods.filter { it !in settled }, resolver)
        val fates = Fates(fields, settled + hidden, types)
        checkKeptTexts(root, fates, declarations, resolver, createFromParcel?.body)
        checkCreator(root, fates, resolver, createFromParcel?.body)
        checkConstants(root, fates, resolver)
        val constructorCalls = constructorCallsOf(fates)
        checkConstructorCalls(root, fates, resolver, constructorCalls)
        val addedConstructors = addedConstructorsOf(root, declarations)
        checkConstructorTargets(root, fates, constructorCalls, addedConstructors)
        val removedAnnotations = absentAnnotationsOf(type, fates, resolver, createFromParcel?.body)
        shape.checkRemoved(removedAnnotations)
        nested[root.internalName] = fates.nested.map { it.simpleName.toString() }
        return SourcePlan(
            root = root,
            source = source,
            type = type,
            fates = fates,
            createFromParcel = createFromParcel,
            addedConstructors = addedConstructors,
            constructorCalls = constructorCalls,
            removedImports = resolver.absentImports,
            addsBuild = shape.addsBuildOf(type, resolver.presentImports),
            javaImport = shape.javaImportOf(resolver.presentImports),
            lastImport = shape.lastImport(),
            removedAnnotations = removedAnnotations,
            notes = notesOf(root, fates),
        )
    }

    private fun inheritedTypesOf(root: Root): Map<String, String> {
        val types = LinkedHashMap<String, String>()
        for (upper in generateSequence(root.superclass) { it.superclass }) {
            val kept = nested[upper.internalName] ?: fail("${upper.internalName} is planned after ${root.internalName}")
            kept.forEach { types.putIfAbsent(it, "${upper.internalName}$$it") }
        }
        return types
    }

    private fun checkDeclared(root: Root, declarations: Declarations) {
        val fields = declarations.fields.map { it.name.toString() }
        val types = declarations.types.map { it.simpleName.toString() }
        val named = root.constants + listOfNotNull(CREATOR.takeIf { root.creator })
        val absent = named.firstOrNull { it !in fields } ?: root.values.firstOrNull { it !in types }
        if (absent != null) fail("${root.internalName} $absent has no declaration in the SDK source")
    }

    private fun fateOf(field: VariableTree, root: Root): FieldFate {
        val hidden = field.modifiers.flags.containsAll(CONSTANT) &&
            (field.type is PrimitiveTypeTree || field.type.toString() == "String")
        val name = field.name.toString()
        return when {
            root.creator && name == CREATOR -> KeptCreator
            name in root.constants || hidden -> Kept
            else -> Removed.NOT_KEPT
        }
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
        val constructors = root.constructors.associateBy { it.sourceKey }
        return methods.zip(keys).mapNotNull { (method, key) ->
            val native = Modifier.NATIVE in method.modifiers.flags
            if (native && key in fates) fail("$name $key is native and android.jar declares it, $UNWRITTEN")
            val fate = fates[key] ?: constructors[key]?.let { Unsupported(it, null) } ?: when {
                method.name.contentEquals(INIT) -> Removed.NOT_KEPT
                else -> hiddenFateOf(root, key, native)
            }
            fate?.let { method to it }
        }.toMap()
    }

    private fun hiddenFateOf(root: Root, key: String, native: Boolean): MethodFate? {
        val upper = generateSequence(root.superclass) { it.superclass }.firstOrNull { upper ->
            upper.declared.any { it.method.sourceKey == key } || upper.lacking.any { it.sourceKey == key }
        }
        if (upper != null) {
            fail("${root.internalName} $key is hidden and overrides what ${upper.internalName} declares, $UNWRITTEN")
        }
        return if (native) Removed.NATIVE else null
    }

    private fun addedConstructorsOf(root: Root, declarations: Declarations): List<Method> {
        val spelled = declarations.methods.filter { it.name.contentEquals(INIT) }
            .map { sourceKeyOf(root.internalName, it) }
        val added = root.constructors.filter { it.sourceKey !in spelled }
        val parameters = added.firstOrNull { it.type.parameterCount() > 0 }
        if (parameters != null) {
            fail(
                "${root.internalName} ${parameters.key} has parameters and the SDK source does not spell it, $UNWRITTEN",
            )
        }
        return added
    }

    private fun constructorCallsOf(fates: Fates): Map<MethodTree, MethodInvocationTree> {
        return fates.methods.filter { (method, fate) -> fate is Unsupported && method.name.contentEquals(INIT) }
            .keys.mapNotNull { method ->
                val first = method.body.statements.firstOrNull() as? ExpressionStatementTree
                val call = first?.expression as? MethodInvocationTree
                val callee = call?.methodSelect as? IdentifierTree
                if (callee != null && callee.name.toString() in EXPLICIT_CALLS) method to call else null
            }.toMap()
    }

    private fun checkConstructorCalls(
        root: Root,
        fates: Fates,
        resolver: Resolver,
        calls: Map<MethodTree, MethodInvocationTree>,
    ) {
        val gone = fates.removed + fates.methods.filterValues { it == Stubbed }.keys
        for ((method, call) in calls) {
            val references = References(null)
            references.scan(method.parameters, null)
            references.scan(call.arguments, null)
            val name = resolver.unresolvedOf(references, gone) ?: continue
            fail(
                "${root.internalName} ${sourceKeyOf(root.internalName, method)} calls a constructor with $name, " +
                    "which the handle cuts or the emulation lacks, $UNWRITTEN",
            )
        }
    }

    private fun checkConstructorTargets(
        root: Root,
        fates: Fates,
        calls: Map<MethodTree, MethodInvocationTree>,
        added: List<Method>,
    ) {
        val name = root.internalName
        val upper = root.superclass?.internalName ?: OBJECT
        val upperArities = root.superclass?.constructors?.map { it.type.parameterCount() }?.toSet() ?: setOf(0)
        val ownArities = root.constructors.map { it.type.parameterCount() }.toSet()
        val spelled = fates.methods.filter { (method, fate) -> fate is Unsupported && method.name.contentEquals(INIT) }
        for (method in spelled.keys) {
            val call = calls[method]
            val callee = (call?.methodSelect as? IdentifierTree)?.name?.toString() ?: SUPER
            val arity = call?.arguments?.size ?: 0
            val (target, arities) = if (callee == SUPER) upper to upperArities else name to ownArities
            if (arity in arities) continue
            val how = if (call == null) "calls $SUPER of arity 0 implicitly" else "calls $callee of arity $arity"
            fail("$name ${sourceKeyOf(name, method)} $how where $target keeps no constructor of that arity, $UNWRITTEN")
        }
        val implicit = added.firstOrNull() ?: return
        if (0 !in upperArities) {
            fail(
                "$name ${implicit.key} is added and calls $SUPER of arity 0 implicitly where $upper keeps no " +
                    "constructor of that arity, $UNWRITTEN",
            )
        }
    }

    private fun checkUnique(name: String, keys: List<String>, input: String) {
        val shared = keys.groupBy { it }.filterValues { it.size > 1 }.keys.firstOrNull()
        if (shared != null) fail("$name has two methods with the source key $shared in $input, $UNWRITTEN")
    }

    private fun hiddenOf(root: Root, methods: List<MethodTree>, resolver: Resolver): Map<MethodTree, MethodFate> {
        val (headed, lacking) = methods.partition(resolver::isPresentHead)
        val overridable = headed.filter {
            Modifier.FINAL !in root.modifiers && it.modifiers.flags.none(NOT_OVERRIDABLE::contains)
        }
        val stubbed = LinkedHashSet(overridable)
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
        return root.cut.map { "$it is nested in android.jar, the proxy boundary does not list it, it is cut" } +
            fates.methods.mapNotNull { (method, fate) ->
                val key = sourceKeyOf(root.internalName, method)
                fate.note?.let { "${root.internalName} ${lacking[key] ?: key} $it" }
            }
    }

    private fun checkKeptTexts(
        root: Root,
        fates: Fates,
        declarations: Declarations,
        resolver: Resolver,
        replaced: Tree?,
    ) {
        val top = root.name.simpleName()
        for (text in fates.nested + fates.constants + listOfNotNull(fates.creator) + fates.verbatim) {
            val references = referencesOf(text, replaced)
            if (resolver.constructsTop(references)) {
                fail("${root.internalName} is constructed by text the handle keeps, $UNWRITTEN")
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

    private fun checkCreator(root: Root, fates: Fates, resolver: Resolver, replaced: Tree?) {
        val creator = fates.creator ?: return
        val name = resolver.unresolvedOf(referencesOf(creator, replaced), fates.removed) ?: return
        fail("${root.internalName} $CREATOR names $name, which the emulation lacks, $UNWRITTEN")
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
