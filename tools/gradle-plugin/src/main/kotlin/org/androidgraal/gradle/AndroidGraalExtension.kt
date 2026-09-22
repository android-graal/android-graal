package org.androidgraal.gradle

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

abstract class AndroidGraalExtension {

    /** The plugin produces `lib<imageName>.so`. */
    abstract val imageName: Property<String>

    abstract val mainClass: Property<String>

    /**
     * `androidgraal.toolchain.dir` in the build's `local.properties` and `ANDROID_GRAAL_TOOLCHAIN` take
     * precedence; unset, it is the `org.androidgraal:toolchain` dependency.
     */
    abstract val toolchainDir: DirectoryProperty

    abstract val buildArgs: ListProperty<String>

    /** For the image builder's JVM. */
    abstract val jvmArgs: ListProperty<String>

    /** Seen by the image builder, not by the running image. */
    abstract val systemProperties: MapProperty<String, String>

    abstract val configurationFileDirectories: ConfigurableFileCollection

    abstract val verbose: Property<Boolean>

    /** `-Ob`: a faster build for a slower image. */
    abstract val quickBuild: Property<Boolean>

    /** Default `true`; `false` selects graal's own LIR backend. */
    abstract val useLLVM: Property<Boolean>
}
