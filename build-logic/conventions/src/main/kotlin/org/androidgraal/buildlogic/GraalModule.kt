package org.androidgraal.buildlogic

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

abstract class GraalModule {

    /** The fork's `sdk/src/<project>/src` trees. */
    abstract val sdkProjects: ListProperty<String>

    /** The `org.graalvm.sdk` artifact this jar replaces. */
    abstract val sdkArtifact: Property<String>
}
