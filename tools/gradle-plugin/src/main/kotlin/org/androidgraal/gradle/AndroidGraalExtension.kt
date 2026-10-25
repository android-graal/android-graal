package org.androidgraal.gradle

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Property

abstract class AndroidGraalExtension : ExtensionAware {

    /** Required. The plugin produces `lib<imageName>.so`. */
    abstract val imageName: Property<String>

    /**
     * `androidgraal.toolchain.dir` in the build's `local.properties` and `ANDROID_GRAAL_TOOLCHAIN` take
     * precedence. Unset, it is the `org.androidgraal:toolchain` dependency.
     */
    abstract val toolchainDir: DirectoryProperty

    /** R8's rules for the image of every minified variant, besides those the image classpath's jars embed. */
    abstract val proguardFiles: ConfigurableFileCollection
}
