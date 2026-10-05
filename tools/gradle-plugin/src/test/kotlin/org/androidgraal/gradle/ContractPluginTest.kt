package org.androidgraal.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
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
        assertTrue(project.tasks.getByName("assemble").dependsOn.containsAll(listOf("artJar", "svmJar")))
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
    fun `the bridges are feature variants under the contract's coordinates`() {
        val capabilities = listOf("artApiElements", "artRuntimeElements", "svmApiElements", "svmRuntimeElements")
            .associateWith { name ->
                project.configurations.getByName(name).outgoing.capabilities.map { "${it.group}:${it.name}" }
            }

        assertEquals(
            mapOf(
                "artApiElements" to listOf("org.example:contract-art"),
                "artRuntimeElements" to listOf("org.example:contract-art"),
                "svmApiElements" to listOf("org.example:contract-svm"),
                "svmRuntimeElements" to listOf("org.example:contract-svm"),
            ),
            capabilities,
        )
    }

    @Test
    fun `each bridge ships only the contract and its side's runtime, compiling against hosted too`() {
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
        assertEquals(listOf(":contract", "org.androidgraal:art"), declared("artRuntimeClasspath"))
        assertEquals(listOf(":contract", "org.androidgraal:svm"), declared("svmRuntimeClasspath"))
        assertEquals(listOf(":contract", "org.androidgraal:art"), declared("artRuntimeElements"))
        assertEquals(listOf(":contract", "org.androidgraal:svm"), declared("svmRuntimeElements"))
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
