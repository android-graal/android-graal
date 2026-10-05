package org.androidgraal.gradle

import org.androidgraal.common.Side
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.process.CommandLineArgumentProvider

class ContractPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        val java = project.extensions.getByType(JavaPluginExtension::class.java)
        java.toolchain.languageVersion.convention(JavaLanguageVersion.of(Side.SVM.release))

        val main = java.sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME)
        val genOutputDir = project.layout.buildDirectory.dir("generated/androidgraal")
        val compileJava = project.tasks.named(main.compileJavaTaskName, JavaCompile::class.java) {
            it.options.release.convention(Side.sharedRelease())
            it.options.compilerArgumentProviders.add(BridgesOutput(genOutputDir))
        }
        project.dependencies.add(main.compileOnlyConfigurationName, Modules.HOSTED)
        project.dependencies.add(main.annotationProcessorConfigurationName, Modules.PROCESSOR)

        val output = compileJava.map { genOutputDir.get() }
        val art = registerBridge(project, java, Side.ART, output)
        val svm = registerBridge(project, java, Side.SVM, output)
        project.tasks.named(BasePlugin.ASSEMBLE_TASK_NAME) { it.dependsOn(art.jarTaskName, svm.jarTaskName) }
    }

    private fun registerBridge(
        project: Project,
        java: JavaPluginExtension,
        side: Side,
        bridges: Provider<Directory>,
    ): SourceSet {
        val sourceSet = java.sourceSets.create(side.feature)
        sourceSet.java.setSrcDirs(listOf(bridges.map { it.dir("${side.feature}/java") }))
        sourceSet.resources.setSrcDirs(listOf(bridges.map { it.dir("${side.feature}/resources") }))
        project.tasks.named(sourceSet.compileJavaTaskName, JavaCompile::class.java) {
            it.options.release.convention(side.release)
        }
        java.registerFeature(side.feature) { it.usingSourceSet(sourceSet) }
        project.dependencies.add(sourceSet.apiConfigurationName, project.dependencyFactory.createProjectDependency())
        project.dependencies.add(sourceSet.implementationConfigurationName, Modules.runtime(side))
        project.dependencies.add(sourceSet.compileOnlyConfigurationName, Modules.HOSTED)
        return sourceSet
    }
}

internal class BridgesOutput(@get:OutputDirectory val dir: Provider<Directory>) : CommandLineArgumentProvider {
    override fun asArguments(): Iterable<String> = listOf("-Aandroidgraal.out=${dir.get().asFile.path}")
}
