package org.androidgraal.gradle

import org.androidgraal.common.Side
import org.gradle.api.GradleException
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.DependencyScopeConfiguration
import org.gradle.api.artifacts.FileCollectionDependency
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.file.FileCollection
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.provider.Provider

internal const val CONTRACT_CONFIGURATION_NAME = "contract"
internal const val CONTRACT_JARS_CONFIGURATION_NAME = "androidGraalContractJars"

internal fun registerContractScope(
    project: Project,
    side: Side,
): NamedDomainObjectProvider<DependencyScopeConfiguration> {
    val contract = project.configurations.dependencyScope(CONTRACT_CONFIGURATION_NAME) {
        it.description = "Contracts this module uses: each one's jar and its bridge for ${side.label}."
    }
    project.configurations.getByName(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME).extendsFrom(contract.get())
    project.configurations.getByName(JavaPlugin.RUNTIME_ONLY_CONFIGURATION_NAME).dependencies.addAllLater(
        bridgeDependencies(project, contract, side),
    )
    return contract
}

internal fun registerContractJars(
    project: Project,
    contract: NamedDomainObjectProvider<DependencyScopeConfiguration>,
    side: Side,
): FileCollection {
    val jars = project.configurations.resolvable(CONTRACT_JARS_CONFIGURATION_NAME) {
        it.extendsFrom(contract.get())
        it.dependencies.addAllLater(bridgeDependencies(project, contract, side))
        it.configureAsRuntimeClasspath(project.objects)
        it.isTransitive = false
    }
    return project.files(jars)
}

private fun bridgeDependencies(
    project: Project,
    contract: NamedDomainObjectProvider<DependencyScopeConfiguration>,
    side: Side,
): Provider<List<ModuleDependency>> = project.provider {
    contract.get().dependencies.map { bridgeDependency(it, side) }
}

private fun bridgeDependency(dependency: Dependency, side: Side): ModuleDependency {
    if (dependency !is ModuleDependency) {
        val what = if (dependency is FileCollectionDependency) "the files ${dependency.files.files}" else "$dependency"
        throw GradleException("$what on contract: a contract is a project or a module")
    }
    return dependency.copy().also { copy -> copy.capabilities { it.requireFeature(side.feature) } }
}
