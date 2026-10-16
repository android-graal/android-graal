package org.androidgraal.buildlogic

import org.androidgraal.common.Target
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.testfixtures.ProjectBuilder
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NativeArtifactsTest {

    private val tmp: File = createTempDirectory("native-artifacts-test").toFile()

    @AfterTest
    fun cleanUp() {
        tmp.deleteRecursively()
    }

    @Test
    fun `outside the runtime build nativeInput requests the module in the native group`() {
        val project = ProjectBuilder.builder().withProjectDir(File(tmp, "toolchain")).build()
        project.group = "org.androidgraal"

        project.nativeInput(Native.Svm.staticLibs.getValue(Target.AARCH64))

        val dependency = project.configurations.getByName("svmStaticLibsAarch64").dependencies.single()
        dependency as ExternalModuleDependency
        assertEquals("org.androidgraal.native:svm", "${dependency.group}:${dependency.name}")
    }

    @Test
    fun `inside the runtime build nativeInput depends on the project`() {
        val root = ProjectBuilder.builder().withProjectDir(File(tmp, "runtime")).build()
        val native = child(root, "native")
        child(native, "svm")
        val consumer = child(native, "capcache")

        consumer.nativeInput(Native.Svm.staticLibs.getValue(Target.AARCH64))

        val dependency = consumer.configurations.getByName("svmStaticLibsAarch64").dependencies.single()
        dependency as ProjectDependency
        assertEquals(":native:svm", dependency.path)
    }

    @Test
    fun `every target has its own artifact, published and requested under its own name`() {
        val root = ProjectBuilder.builder().withProjectDir(File(tmp, "runtime")).build()
        val native = child(root, "native")
        val producer = child(native, "jdk")
        val consumer = child(native, "svm")

        for (target in Target.entries) {
            val artifact = Native.Jdk.staticLibs.getValue(target)
            producer.nativeOutput(artifact, producer.file("lib-${target.triple}"))
            consumer.nativeInput(artifact)

            assertEquals("staticLibs${target.title}", artifact.name)
            assertEquals("android-graal-jdk-staticLibs${target.title}", artifact.usage)
            assertEquals(
                setOf(producer.file("lib-${target.triple}")),
                producer.configurations.getByName("staticLibs${target.title}Elements").outgoing.artifacts.files.files,
            )
            val dependency = consumer.configurations.getByName("jdkStaticLibs${target.title}").dependencies.single()
            dependency as ProjectDependency
            assertEquals(":native:jdk", dependency.path)
        }
    }

    private fun child(parent: Project, name: String): Project =
        ProjectBuilder.builder().withParent(parent).withName(name).withProjectDir(File(parent.projectDir, name)).build()
}
