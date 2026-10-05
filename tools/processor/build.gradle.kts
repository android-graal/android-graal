import org.androidgraal.common.Side

plugins {
    id("androidgraal.kotlin")
    alias(libs.plugins.shadow)
}

description = "The javac annotation processor that checks a contract and generates its ART and image bridges."

val shade = configurations.dependencyScope("shade")
val shadeClasspath = configurations.resolvable("shadeClasspath") {
    extendsFrom(shade.get())
}

configurations {
    compileOnly { extendsFrom(shade.get()) }
    testImplementation { extendsFrom(shade.get()) }
}

val contractFixtures = configurations.dependencyScope("contractFixtures")
val svmFixtures = configurations.dependencyScope("svmFixtures")
val artFixtures = configurations.dependencyScope("artFixtures")
val (contractClasspath, svmClasspath, artClasspath) = listOf(contractFixtures, svmFixtures, artFixtures).map { scope ->
    configurations.resolvable("${scope.name}Classpath") {
        extendsFrom(scope.get())
        attributes {
            attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_API))
            attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.JAR))
        }
    }
}

class FixtureClasspaths(
    @get:Classpath val contract: FileCollection,
    @get:Classpath val svm: FileCollection,
    @get:Classpath val art: FileCollection,
) : CommandLineArgumentProvider {
    override fun asArguments(): Iterable<String> = listOf(
        "-Dandroidgraal.test.contractClasspath=${contract.asPath}",
        "-Dandroidgraal.test.svmClasspath=${svm.asPath}",
        "-Dandroidgraal.test.artClasspath=${art.asPath}",
    )
}

dependencies {
    shade("$group:common")
    shade(libs.javapoet)
    shade(embeddedKotlin("stdlib"))
    testImplementation(libs.asm)
    testImplementation(libs.commons.io)
    testImplementation(libs.compile.testing)
    testImplementation("$group:shared")
    contractFixtures("$group:hosted")
    svmFixtures("$group:svm")
    artFixtures("$group:art")
}

tasks.test {
    // The test JVM hosts javac for the release-25 fixtures.
    javaLauncher = javaToolchains.launcherFor { languageVersion = JavaLanguageVersion.of(Side.SVM.release) }
    jvmArgumentProviders.add(FixtureClasspaths(contractClasspath.get(), svmClasspath.get(), artClasspath.get()))
    inputs.dir("src/test/golden").withPathSensitivity(PathSensitivity.RELATIVE)
    inputs.dir("src/test/fixtures").withPathSensitivity(PathSensitivity.RELATIVE)
}

shadow {
    addShadowVariantIntoJavaComponent = false
}

tasks.shadowJar {
    configurations = listOf(shadeClasspath.get())
    archiveClassifier = ""
    mergeServiceFiles()
    enableAutoRelocation = true
    relocationPrefix = "org.androidgraal.processor.shadow"
    // Keeps own packages out of the auto-relocation.
    relocate("org.androidgraal", "org.androidgraal")
    exclude("META-INF/versions/**")
    exclude("module-info.class")
    addMultiReleaseAttribute = false
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    failOnDuplicateEntries = true
}

tasks.jar {
    archiveClassifier = "plain"
}

listOf(configurations.apiElements, configurations.runtimeElements).forEach { elements ->
    elements.configure {
        outgoing.artifacts.clear()
        outgoing.variants.clear()
        outgoing.artifact(tasks.shadowJar)
    }
}

publishing {
    publications {
        register<MavenPublication>("processor") {
            from(components["java"])
        }
    }
}
