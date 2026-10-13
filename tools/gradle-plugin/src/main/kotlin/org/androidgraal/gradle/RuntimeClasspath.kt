package org.androidgraal.gradle

import org.androidgraal.common.Side
import org.gradle.api.artifacts.Configuration
import org.gradle.api.attributes.Attribute
import org.gradle.api.attributes.Bundling
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.attributes.Usage
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.model.ObjectFactory

internal val SIDE_ATTRIBUTE: Attribute<String> = Attribute.of("org.androidgraal.side", String::class.java)

internal fun Configuration.requestSide(side: Side) {
    attributes.attribute(SIDE_ATTRIBUTE, side.feature)
}

internal fun Configuration.configureAsRuntimeClasspath(objects: ObjectFactory) {
    attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
    attributes.attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category::class.java, Category.LIBRARY))
    attributes.attribute(
        LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE,
        objects.named(LibraryElements::class.java, LibraryElements.JAR),
    )
    attributes.attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling::class.java, Bundling.EXTERNAL))
    attributes.attribute(
        TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
        objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM),
    )
}
