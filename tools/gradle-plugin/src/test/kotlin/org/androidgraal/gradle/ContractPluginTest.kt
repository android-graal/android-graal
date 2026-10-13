package org.androidgraal.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.attributes.java.TargetJvmVersion
import org.gradle.api.internal.component.SoftwareComponentInternal
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.internal.component.MavenPublishingAwareVariant
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ContractPluginTest {

    private val dir: File = createTempDirectory("contract-plugin").toFile()

    private val root: Project = ProjectBuilder.builder().withProjectDir(dir).withName("game").build()

    private val project: Project = ProjectBuilder.builder().withParent(root).withName("contract").build().also {
        it.group = "org.example"
        it.pluginManager.apply("org.androidgraal.contract")
    }

    @AfterTest
    fun cleanUp() = FileUtils.deleteDirectory(dir)

    @Test
    fun `the bridge source sets read what main's compilation writes`() {
        val sourceSets = project.extensions.getByType(JavaPluginExtension::class.java).sourceSets
        val bridges = project.layout.buildDirectory.get().asFile.resolve("generated/androidgraal")
        val compileJava = project.tasks.getByName("compileJava")

        assertEquals(listOf("art", "main", "svm", "test"), sourceSets.names.sorted())
        listOf("art", "svm").forEach {
            val sourceSet = sourceSets.getByName(it)
            assertEquals(setOf(bridges.resolve("$it/java")), sourceSet.java.srcDirs)
            assertEquals(setOf(compileJava), sourceSet.java.buildDependencies.getDependencies(null))
            assertEquals(setOf(bridges.resolve("$it/resources")), sourceSet.resources.srcDirs)
            assertEquals(setOf(compileJava), sourceSet.resources.buildDependencies.getDependencies(null))
        }
        val assemble = project.tasks.getByName("assemble")
        val built = assemble.taskDependencies.getDependencies(assemble).map { it.name }
        assertTrue(built.containsAll(listOf("artJar", "svmJar")), "$built")
    }

    @Test
    fun `main and art compile at 17, svm at 25`() {
        val releases = listOf("compileJava", "compileArtJava", "compileSvmJava").associateWith {
            (project.tasks.getByName(it) as JavaCompile).options.release.get()
        }

        assertEquals(
            mapOf("compileJava" to 17, "compileArtJava" to 17, "compileSvmJava" to 25),
            releases,
        )
    }

    @Test
    fun `each bridge is a runtime variant of the contract carrying its side, both jars and its side's runtime`() {
        project.dependencies.add("implementation", "org.example:dep:1.0")
        project.dependencies.add("runtimeOnly", "org.example:rt:1.0")
        val runtimeElements = project.configurations.getByName("runtimeElements")
        val own = runtimeElements.attributes.keySet()
        val jvmVersion = TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE

        mapOf("art" to 17, "svm" to 25).forEach { (side, release) ->
            val elements = project.configurations.getByName("${side}RuntimeElements")
            assertTrue(elements.isCanBeConsumed)
            assertFalse(elements.isCanBeResolved)
            assertEquals(own + SIDE_ATTRIBUTE, elements.attributes.keySet())
            (own - jvmVersion).forEach {
                assertEquals(runtimeElements.attributes.getAttribute(it), elements.attributes.getAttribute(it))
            }
            assertEquals(side, elements.attributes.getAttribute(SIDE_ATTRIBUTE))
            assertEquals(release, elements.attributes.getAttribute(jvmVersion))
            assertEquals(
                listOf("contract.jar", "contract-$side.jar"),
                elements.outgoing.artifacts.files.map { it.name },
            )
            assertEquals(
                listOf("org.androidgraal:$side", "org.example:dep", "org.example:rt"),
                elements.allDependencies.map { "${it.group}:${it.name}" }.sorted(),
            )
            assertEquals(emptyList(), elements.outgoing.capabilities.toList())
        }
        assertEquals(null, runtimeElements.attributes.getAttribute(SIDE_ATTRIBUTE))
        assertTrue(project.dependencies.attributesSchema.hasAttribute(SIDE_ATTRIBUTE))
    }

    @Test
    fun `the java component carries the two side variants as optional runtime dependencies, no other bridge variant`() {
        val component = project.components.getByName("java") as SoftwareComponentInternal

        assertEquals(
            mapOf(
                "apiElements" to "compile",
                "artRuntimeElements" to "runtime_optional",
                "runtimeElements" to "runtime",
                "svmRuntimeElements" to "runtime_optional",
            ),
            component.usages.associate {
                it.name to MavenPublishingAwareVariant.scopeForVariant(it).name
            }.toSortedMap(),
        )
        assertTrue(project.configurations.names.none { it == "artApiElements" || it == "svmApiElements" })
    }

    @Test
    fun `each bridge compiles against the contract, its side's runtime and hosted`() {
        fun declared(configuration: String): List<String> =
            project.configurations.getByName(configuration).allDependencies.map {
                if (it is ProjectDependency) it.path else "${it.group}:${it.name}"
            }.sorted()

        assertEquals(
            listOf(":contract", "org.androidgraal:art", "org.androidgraal:hosted"),
            declared("artCompileClasspath"),
        )
        assertEquals(
            listOf(":contract", "org.androidgraal:hosted", "org.androidgraal:svm"),
            declared("svmCompileClasspath"),
        )
        assertEquals(listOf("org.androidgraal:art"), declared("artRuntimeClasspath"))
        assertEquals(listOf("org.androidgraal:svm"), declared("svmRuntimeClasspath"))
        assertEquals(listOf("org.androidgraal:hosted"), declared("compileClasspath"))
        assertEquals(emptyList(), declared("runtimeClasspath"))
    }

    @Test
    fun `the processor runs in main's compilation only, writing below its output directory`() {
        val options = (project.tasks.getByName("compileJava") as JavaCompile).options
        val bridges = project.layout.buildDirectory.get().asFile.resolve("generated/androidgraal")

        assertEquals(
            listOf("-Aandroidgraal.out=${bridges.path}"),
            options.compilerArgumentProviders.flatMap { it.asArguments() },
        )
        assertTrue(bridges in project.tasks.getByName("compileJava").outputs.files.files)
        listOf("compileArtJava", "compileSvmJava").forEach {
            assertEquals(emptyList(), (project.tasks.getByName(it) as JavaCompile).options.compilerArgumentProviders)
        }
        val processors = listOf("annotationProcessor", "artAnnotationProcessor", "svmAnnotationProcessor")
            .associateWith { name ->
                project.configurations.getByName(name).dependencies.map { "${it.group}:${it.name}" }
            }
        assertEquals(
            mapOf(
                "annotationProcessor" to listOf("org.androidgraal:processor"),
                "artAnnotationProcessor" to emptyList(),
                "svmAnnotationProcessor" to emptyList(),
            ),
            processors,
        )
    }
}
