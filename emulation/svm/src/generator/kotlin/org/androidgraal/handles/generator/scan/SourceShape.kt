package org.androidgraal.handles.generator.scan

import com.sun.source.tree.AnnotationTree
import com.sun.source.tree.ClassTree
import com.sun.source.tree.ImportTree
import com.sun.source.tree.MethodTree
import com.sun.source.tree.NewClassTree
import com.sun.source.tree.VariableTree
import org.androidgraal.handles.generator.model.Root
import org.androidgraal.handles.generator.model.SourceFile
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.fail
import javax.lang.model.element.Modifier

private val CHECKED = setOf(Modifier.PUBLIC, Modifier.FINAL)

private const val BUILD = "android.os.Build"

internal const val CREATE_FROM_PARCEL = "createFromParcel"

private val JAVA = Regex("javax?\\..*")

internal class SourceShape(private val root: Root, private val source: SourceFile) {
    private val name = root.internalName

    fun type(): ClassTree {
        checkText()
        val types = source.unit.typeDecls.filterIsInstance<ClassTree>()
        val type = types.singleOrNull() ?: fail("$name's source declares ${types.size} top-level classes")
        checkHead(type)
        return type
    }

    fun declarationsOf(type: ClassTree): Declarations {
        val fields = type.members.filterIsInstance<VariableTree>()
        val methods = type.members.filterIsInstance<MethodTree>()
        val types = type.members.filterIsInstance<ClassTree>()
        if (fields.size + methods.size + types.size != type.members.size) {
            fail("$name declares a member that is no field, method or class, $UNWRITTEN")
        }
        checkLines(type)
        methods.forEach(::checkMethod)
        return Declarations(fields, methods, types)
    }

    fun createFromParcelOf(creator: VariableTree): MethodTree {
        val body = (creator.initializer as? NewClassTree)?.classBody
            ?: fail("$name's $CREATOR is no anonymous class, $UNWRITTEN")
        return body.members.filterIsInstance<MethodTree>()
            .singleOrNull { it.name.contentEquals(CREATE_FROM_PARCEL) && it.parameters.size == 1 && it.body != null }
            ?: fail("$name's $CREATOR declares no $CREATE_FROM_PARCEL of one parameter, $UNWRITTEN")
    }

    fun addsBuildOf(type: ClassTree, imports: List<ImportTree>): Boolean {
        if (root.declared.none { it.level != null }) return false
        val named = imports.filter { !it.isStatic && it.qualifiedIdentifier.toString().endsWith(".Build") }
        if (named.any { it.qualifiedIdentifier.toString() == BUILD }) return false
        if (named.isNotEmpty() || type.members.any { it is ClassTree && it.simpleName.contentEquals("Build") }) {
            fail("$name's source names another Build where a level guard names $BUILD, $UNWRITTEN")
        }
        return true
    }

    fun javaImportOf(imports: List<ImportTree>): ImportTree? =
        imports.firstOrNull { !it.isStatic && it.qualifiedIdentifier.toString().matches(JAVA) }

    fun lastImport(): ImportTree =
        source.unit.imports.lastOrNull() ?: fail("$name's source imports nothing, $UNWRITTEN")

    fun checkRemoved(annotations: List<AnnotationTree>) {
        for (annotation in annotations) {
            val first = source.lineOf(source.start(annotation))
            val last = source.lineOf(source.end(annotation) - 1)
            if (first != last) {
                fail("$name has an annotation the emulation lacks across lines ${first + 1} to ${last + 1}, $UNWRITTEN")
            }
        }
    }

    private fun checkText() {
        if ('\t' in source.text) fail("$name's source holds a tab, $UNWRITTEN")
        val onDemand = source.unit.imports.firstOrNull { it.qualifiedIdentifier.toString().endsWith(".*") }
        if (onDemand != null) fail("$name's source imports ${onDemand.qualifiedIdentifier} on demand, $UNWRITTEN")
    }

    private fun checkHead(type: ClassTree) {
        val declared = type.modifiers.flags.filter { it in CHECKED }.toSet()
        if (declared != root.modifiers) {
            fail("$name's source declares $declared where android.jar declares ${root.modifiers}")
        }
        if (type.typeParameters.isNotEmpty() || type.extendsClause != null) {
            fail("$name's source declares a type parameter or a superclass android.jar lacks, $UNWRITTEN")
        }
        val implemented = type.implementsClause.map { it.toString().substringAfterLast('.') }
        if (implemented != root.interfaces.map { it.substringAfterLast('/') }) {
            fail("$name's source implements $implemented where android.jar declares ${root.interfaces}, $UNWRITTEN")
        }
        val line = source.lineOf(source.end(type.modifiers) - 1)
        val modifiers = source.text.substring(source.lineStart(line), source.end(type.modifiers))
        if (modifiers != type.modifiers.flags.joinToString(" ")) {
            fail("$name's class declaration does not start a line with all its modifiers, $UNWRITTEN")
        }
        if (source.lineText(source.lineOf(source.end(type) - 1)) != "}") {
            fail("$name does not end on a line of its own, $UNWRITTEN")
        }
    }

    private fun checkLines(type: ClassTree) {
        var previous = source.lineOf(source.bodyOpenOf(type))
        for (member in type.members) {
            val start = source.lineOf(source.start(member))
            if (start <= previous) fail("$name has two members on line ${start + 1}, $UNWRITTEN")
            previous = source.lineOf(source.end(member) - 1)
        }
    }

    private fun checkMethod(method: MethodTree) {
        val key = sourceKeyOf(name, method)
        if (method.typeParameters.isNotEmpty()) fail("$name $key is generic in the SDK source, $UNWRITTEN")
        if (method.body == null && Modifier.NATIVE !in method.modifiers.flags) {
            fail("$name $key has no body in the SDK source and is not native, $UNWRITTEN")
        }
    }
}
