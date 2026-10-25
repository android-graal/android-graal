package org.androidgraal.gradle

import org.gradle.api.Named
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class TargetOptions @Inject constructor(private val name: String) : Named {

    override fun getName(): String = name

    abstract val buildArgs: ListProperty<String>

    /** For the image builder's JVM. */
    abstract val jvmArgs: ListProperty<String>

    /** Seen by the image builder, not by the running image. */
    abstract val systemProperties: MapProperty<String, String>

    abstract val configurationFileDirectories: ConfigurableFileCollection

    abstract val verbose: Property<Boolean>

    /** `-Ob`: a faster build for a slower image. */
    abstract val quickBuild: Property<Boolean>

    /** Default `true`, `false` selects graal's own LIR backend. */
    abstract val useLLVM: Property<Boolean>

    /** `-H:IncludeLocales`. Empty, the default, is `-H:+IncludeAllLocales`. */
    abstract val includeLocales: ListProperty<String>

    /** `-H:+AddAllCharsets`, default `true`. */
    abstract val addAllCharsets: Property<Boolean>
}
