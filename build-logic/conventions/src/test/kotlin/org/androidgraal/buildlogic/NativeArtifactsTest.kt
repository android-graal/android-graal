package org.androidgraal.buildlogic

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

        project.nativeInput(Native.Svm.staticLibs)

        val dependency = project.configurations.getByName("svmStaticLibs").dependencies.single()
        dependency as ExternalModuleDependency
        assertEquals("org.androidgraal.native:svm", "${dependency.group}:${dependency.name}")
    }

    @Test
    fun `inside the runtime build nativeInput depends on the project`() {
        val root = ProjectBuilder.builder().withProjectDir(File(tmp, "runtime")).build()
        val native = child(root, "native")
        child(native, "svm")
        val consumer = child(native, "capcache")

        consumer.nativeInput(Native.Svm.staticLibs)

        val dependency = consumer.configurations.getByName("svmStaticLibs").dependencies.single()
        dependency as ProjectDependency
        assertEquals(":native:svm", dependency.path)
    }

    private fun child(parent: Project, name: String): Project =
        ProjectBuilder.builder().withParent(parent).withName(name).withProjectDir(File(parent.projectDir, name)).build()
}
