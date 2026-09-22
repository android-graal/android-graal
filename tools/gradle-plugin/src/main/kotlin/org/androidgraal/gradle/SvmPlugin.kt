package org.androidgraal.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion

class SvmPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.extensions.getByType(JavaPluginExtension::class.java).toolchain.languageVersion
            .convention(JavaLanguageVersion.of(JAVA_VERSION))
        project.dependencies.add(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, NATIVE_IMAGE_API)
    }

    companion object {
        /** The JDK the image builder runs on, and the newest class file version it reads. */
        private const val JAVA_VERSION = 25

        /** Must match the GraalVM the toolchain is assembled from (`vendor/graal`). */
        private const val NATIVE_IMAGE_API = "org.graalvm.sdk:nativeimage:25.2.4"
    }
}
