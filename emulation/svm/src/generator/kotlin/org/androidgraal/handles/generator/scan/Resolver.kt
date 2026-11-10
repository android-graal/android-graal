package org.androidgraal.handles.generator.scan

import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.ArrayTypeTree
import com.sun.source.tree.AssignmentTree
import com.sun.source.tree.BinaryTree
import com.sun.source.tree.ClassTree
import com.sun.source.tree.CompilationUnitTree
import com.sun.source.tree.ExpressionTree
import com.sun.source.tree.IdentifierTree
import com.sun.source.tree.ImportTree
import com.sun.source.tree.LiteralTree
import com.sun.source.tree.MemberSelectTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.NewArrayTree
import com.sun.source.tree.ParameterizedTypeTree
import com.sun.source.tree.PrimitiveTypeTree
import com.sun.source.tree.Tree
import com.sun.source.tree.VariableTree
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.fail

internal class Scope(val type: ClassTree, val internalName: String, val outer: Scope?) {
    val simpleName: String = type.simpleName.toString()

    val canonicalName: String = internalName.replace('/', '.').replace('$', '.')
}

internal class Resolver(
    unit: CompilationUnitTree,
    private val classpath: Classpath,
    val top: Scope,
    private val declarations: Declarations,
    private val removed: Set<Tree>,
) {
    private val scopes: Map<ClassTree, Scope> = scopesOf(top).associateBy { it.type }
    private val kept: Set<String> = scopes.values.map { it.internalName }.toSet()
    private val packagePath = top.internalName.substringBeforeLast('/')

    val presentImports: List<ImportTree> = unit.imports.filter(::isPresentImport)

    val absentImports: Set<ImportTree> = unit.imports.filter { it !in presentImports }.toSet()

    fun scopeOf(type: ClassTree): Scope? = scopes[type]

    fun isPresentHead(method: MethodTree): Boolean {
        val types = listOf(method.returnType) + method.parameters.map { it.type } + method.throws
        return types.all(::isPresentType)
    }

    fun isPresentAnnotation(annotation: AnnotationTree, context: Scope): Boolean {
        if (typeOf(segmentsOf(annotation.annotationType), context) == null) return false
        return annotation.arguments.all { argument ->
            isPresentConstant(if (argument is AssignmentTree) argument.expression else argument, context)
        }
    }

    fun isPresentConstant(tree: ExpressionTree, context: Scope): Boolean = when (tree) {
        is LiteralTree -> true
        is NewArrayTree -> tree.initializers.all { isPresentConstant(it, context) }
        is BinaryTree -> isPresentConstant(tree.leftOperand, context) && isPresentConstant(tree.rightOperand, context)
        is IdentifierTree -> isPresentValue(tree.name.toString(), context)
        is MemberSelectTree -> isPresentMember(tree, context)
        else -> fail("${top.internalName} has the constant expression $tree, a kind this generator does not read")
    }

    fun isResolvedBody(method: MethodTree, gone: Set<Tree>): Boolean {
        val references = referencesOf(method)
        return !constructsTop(references) &&
            references.used.filter { it !in references.declared }.all { isResolvedName(it, gone) } &&
            references.selected.filter { (qualifier, _) -> qualifier !in references.declared }
                .all { (qualifier, name) -> isResolvedMember(qualifier, name) }
    }

    fun constructsTop(references: References): Boolean =
        references.constructed.any { it == top.simpleName || it == top.canonicalName }

    private fun scopesOf(scope: Scope): List<Scope> {
        val nested = scope.type.members.filterIsInstance<ClassTree>().filter { it !in removed }
        return listOf(scope) + nested.flatMap { scopesOf(Scope(it, "${scope.internalName}$${it.simpleName}", scope)) }
    }

    private fun isPresentImport(import: ImportTree): Boolean {
        val segments = segmentsOf(import.qualifiedIdentifier)
        if (!import.isStatic) return typeOfQualified(segments) != null
        val owner = typeOfQualified(segments.dropLast(1)) ?: return false
        return classpath.hasMember(owner, segments.last())
    }

    private fun isPresentType(type: Tree): Boolean = when (type) {
        is PrimitiveTypeTree -> true
        is ArrayTypeTree -> isPresentType(type.type)
        is ParameterizedTypeTree -> isPresentType(type.type) && type.typeArguments.all(::isPresentType)
        else -> typeOf(segmentsOf(type), top) != null
    }

    private fun isPresentValue(name: String, context: Scope): Boolean {
        for (scope in generateSequence(context) { it.outer }) {
            val field = scope.type.members.filterIsInstance<VariableTree>().firstOrNull { it.name.contentEquals(name) }
            if (field != null) return field !in removed
        }
        if (presentImports.any { isStaticImportOf(it, name) }) return true
        if (absentImports.any { isStaticImportOf(it, name) }) return false
        fail("${top.internalName} names the constant $name, which no class of its source declares and no import names")
    }

    private fun isPresentMember(select: MemberSelectTree, context: Scope): Boolean {
        if (select.identifier.contentEquals("class")) {
            fail("${top.internalName} has the class literal $select, a kind this generator does not read")
        }
        val owner = typeOf(segmentsOf(select.expression), context) ?: return false
        return classpath.hasMember(owner, select.identifier.toString())
    }

    private fun isResolvedName(name: String, gone: Set<Tree>): Boolean {
        if (name == top.simpleName) return true
        val named = declarations.named(name)
        if (named.isNotEmpty()) return named.none { it in removed || it in gone }
        return presentImports.any { isStaticImportOf(it, name) } || typeOf(listOf(name), top) != null
    }

    private fun isResolvedMember(qualifier: String, name: String): Boolean {
        val owner = typeOf(listOf(qualifier), top) ?: return true
        return classpath.hasMember(owner, name)
    }

    private fun isStaticImportOf(import: ImportTree, name: String): Boolean =
        import.isStatic && segmentsOf(import.qualifiedIdentifier).last() == name

    private fun typeOf(segments: List<String>, context: Scope): String? {
        val outermost = outermostOf(segments.first(), context) ?: return typeOfQualified(segments)
        val type = (listOf(outermost) + segments.drop(1)).joinToString("$")
        if (segments.size > 1 && type !in kept && !classpath.has(type)) {
            fail(
                "${top.internalName} names ${segments.joinToString(".")}, a nested class the handle does not keep " +
                    "and the emulation lacks, $UNWRITTEN",
            )
        }
        return type
    }

    private fun outermostOf(name: String, context: Scope): String? {
        return localOf(name, context)
            ?: importedOf(name)
            ?: "$packagePath/$name".takeIf(classpath::has)
            ?: "java/lang/$name".takeIf(classpath::has)
    }

    private fun localOf(name: String, context: Scope): String? {
        return generateSequence(context) { it.outer }.firstNotNullOfOrNull { scope ->
            scope.internalName.takeIf { scope.simpleName == name }
                ?: "${scope.internalName}$$name".takeIf { it in kept }
        }
    }

    private fun importedOf(name: String): String? {
        val import = presentImports.firstOrNull { !it.isStatic && segmentsOf(it.qualifiedIdentifier).last() == name }
            ?: return null
        return typeOfQualified(segmentsOf(import.qualifiedIdentifier))
    }

    private fun typeOfQualified(segments: List<String>): String? {
        for (split in segments.size - 1 downTo 1) {
            val name = segments.take(split).joinToString("/") + "/" + segments.drop(split).joinToString("$")
            if (classpath.has(name)) return name
        }
        return null
    }

    private fun segmentsOf(name: Tree): List<String> = when (name) {
        is IdentifierTree -> listOf(name.name.toString())
        is MemberSelectTree -> segmentsOf(name.expression) + name.identifier.toString()
        else -> fail("${top.internalName} names $name where the generator resolves a name")
    }
}
