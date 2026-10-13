package org.androidgraal.gradle

import org.androidgraal.common.Side
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion

class SvmPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.dependencies.attributesSchema.attribute(SIDE_ATTRIBUTE)
        val java = project.extensions.getByType(JavaPluginExtension::class.java)
        java.toolchain.languageVersion.convention(JavaLanguageVersion.of(Side.SVM.release))
        java.sourceSets.configureEach { sourceSet ->
            project.configurations.named(sourceSet.runtimeClasspathConfigurationName) { it.requestSide(Side.SVM) }
        }
        project.tasks.withType(JavaCompile::class.java).configureEach {
            it.options.release.convention(Side.SVM.release)
        }
        project.dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, Modules.runtime(Side.SVM))
        project.dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, Modules.EMULATION_SVM)
        val declarations = project.dependencies.create(Modules.EMULATION_SVM) as ModuleDependency
        declarations.capabilities { it.requireFeature("declarations") }
        project.dependencies.add(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, declarations)
        project.dependencies.add(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, Modules.HOSTED)
        project.dependencies.add(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, Modules.GRAAL_SVM_SHARED)
    }
}
