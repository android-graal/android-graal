package org.androidgraal.buildlogic

import org.gradle.api.artifacts.DependencyConstraint
import org.gradle.api.artifacts.ExcludeRule
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.api.artifacts.ModuleVersionIdentifier
import org.gradle.api.artifacts.PublishArtifact
import org.gradle.api.attributes.AttributeContainer
import org.gradle.api.capabilities.Capability
import org.gradle.api.component.ComponentWithCoordinates
import org.gradle.api.component.ComponentWithVariants
import org.gradle.api.component.SoftwareComponent
import org.gradle.api.internal.component.SoftwareComponentInternal
import org.gradle.api.internal.component.UsageContext

class MultiHostComponent(private val name: String, private val hosts: Set<SoftwareComponent>) :
    SoftwareComponentInternal,
    ComponentWithVariants {

    override fun getName(): String = name

    override fun getUsages(): Set<UsageContext> = emptySet()

    override fun getVariants(): Set<SoftwareComponent> = hosts
}

class HostModule(group: String, module: String, version: String, attributes: AttributeContainer) :
    SoftwareComponentInternal,
    ComponentWithCoordinates {

    private val coordinates = Coordinates(Module(group, module), version)

    private val usage = HostUsage(module, attributes)

    override fun getName(): String = coordinates.name

    override fun getUsages(): Set<UsageContext> = setOf(usage)

    override fun getCoordinates(): ModuleVersionIdentifier = coordinates
}

private class HostUsage(private val name: String, private val attributes: AttributeContainer) : UsageContext {

    override fun getName(): String = name

    override fun getAttributes(): AttributeContainer = attributes

    override fun getArtifacts(): Set<PublishArtifact> = emptySet()

    override fun getDependencies(): Set<ModuleDependency> = emptySet()

    override fun getDependencyConstraints(): Set<DependencyConstraint> = emptySet()

    override fun getCapabilities(): Set<Capability> = emptySet()

    override fun getGlobalExcludes(): Set<ExcludeRule> = emptySet()
}

private data class Module(private val group: String, private val name: String) : ModuleIdentifier {

    override fun getGroup(): String = group

    override fun getName(): String = name
}

private data class Coordinates(private val module: Module, private val version: String) : ModuleVersionIdentifier {

    override fun getGroup(): String = module.group

    override fun getName(): String = module.name

    override fun getVersion(): String = version

    override fun getModule(): ModuleIdentifier = module
}
