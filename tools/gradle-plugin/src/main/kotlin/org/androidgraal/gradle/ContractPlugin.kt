package org.androidgraal.gradle

import org.androidgraal.common.Side
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.attributes.java.TargetJvmVersion
import org.gradle.api.component.AdhocComponentWithVariants
import org.gradle.api.file.Directory
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.process.CommandLineArgumentProvider

class ContractPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.dependencies.attributesSchema.attribute(SIDE_ATTRIBUTE)
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
        project.tasks.named(BasePlugin.ASSEMBLE_TASK_NAME) { it.dependsOn(art, svm) }
    }

    private fun registerBridge(
        project: Project,
        java: JavaPluginExtension,
        side: Side,
        bridges: Provider<Directory>,
    ): TaskProvider<Jar> {
        val sourceSet = java.sourceSets.create(side.feature)
        sourceSet.java.setSrcDirs(listOf(bridges.map { it.dir("${side.feature}/java") }))
        sourceSet.resources.setSrcDirs(listOf(bridges.map { it.dir("${side.feature}/resources") }))
        project.tasks.named(sourceSet.compileJavaTaskName, JavaCompile::class.java) {
            it.options.release.convention(side.release)
        }
        project.dependencies.add(
            sourceSet.compileOnlyConfigurationName,
            project.dependencyFactory.createProjectDependency(),
        )
        project.dependencies.add(sourceSet.compileOnlyConfigurationName, Modules.HOSTED)
        project.dependencies.add(sourceSet.implementationConfigurationName, Modules.runtime(side))

        val jar = project.tasks.register(sourceSet.jarTaskName, Jar::class.java) {
            it.archiveClassifier.set(side.feature)
            it.from(sourceSet.output)
        }

        val runtimeElements = project.configurations.getByName(JavaPlugin.RUNTIME_ELEMENTS_CONFIGURATION_NAME)
        val elements = project.configurations.consumable("${side.feature}RuntimeElements") {
            it.extendsFrom(*runtimeElements.extendsFrom.toTypedArray())
            it.extendsFrom(
                project.configurations.getByName(sourceSet.implementationConfigurationName),
                project.configurations.getByName(sourceSet.runtimeOnlyConfigurationName),
            )
            it.attributes.addAllLater(runtimeElements.attributes)
            it.attributes.attribute(SIDE_ATTRIBUTE, side.feature)
            it.attributes.attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, side.release)
            it.outgoing.artifact(project.tasks.named(JavaPlugin.JAR_TASK_NAME))
            it.outgoing.artifact(jar)
        }
        (project.components.getByName(JAVA_COMPONENT_NAME) as AdhocComponentWithVariants)
            .addVariantsFromConfiguration(elements.get()) {
                it.mapToMavenScope(RUNTIME_SCOPE)
                it.mapToOptional()
            }
        return jar
    }

    private companion object {
        const val JAVA_COMPONENT_NAME = "java"
        const val RUNTIME_SCOPE = "runtime"
    }
}

internal class BridgesOutput(@get:OutputDirectory val dir: Provider<Directory>) : CommandLineArgumentProvider {
    override fun asArguments(): Iterable<String> = listOf("-Aandroidgraal.out=${dir.get().asFile.path}")
}
