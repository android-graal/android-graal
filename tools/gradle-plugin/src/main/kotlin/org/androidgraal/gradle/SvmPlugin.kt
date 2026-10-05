package org.androidgraal.gradle

import org.androidgraal.common.Side
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion

class SvmPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.extensions.getByType(JavaPluginExtension::class.java).toolchain.languageVersion
            .convention(JavaLanguageVersion.of(Side.SVM.release))
        project.tasks.withType(JavaCompile::class.java).configureEach {
            it.options.release.convention(Side.SVM.release)
        }
        project.dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, Modules.runtime(Side.SVM))
        project.dependencies.add(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, Modules.HOSTED)
        registerContractScope(project, Side.SVM)
    }
}
