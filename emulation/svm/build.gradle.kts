plugins {
    id("androidgraal.java")
    id("org.gradle.kotlin.embedded-kotlin")
}

group = "${nativeHost.settings.group}.emulation"

description = "Every android.* class the image provides, emulated or as a constants skeleton."

val generator: SourceSet = sourceSets.create("generator")
val declarations: SourceSet = sourceSets.create("declarations") {
    java.setSrcDirs(listOf("src/generated/java"))
    resources.setSrcDirs(emptyList<File>())
}

dependencies {
    api(project(":contract"))
    api("${nativeHost.settings.group}:svm:$version")
    implementation("${nativeHost.settings.group}:bionic:$version")
    compileOnly("${nativeHost.settings.group}:hosted:$version")
    compileOnly(libs.androidx.annotation)
    compileOnly(libs.errorprone.annotations)
    compileOnly(libs.jspecify)
    testImplementation(libs.java.diff.utils)
    testRuntimeOnly("${nativeHost.settings.group}.graal:word:$version")
    testRuntimeOnly("${nativeHost.settings.group}.graal:nativeimage:$version")
    "declarationsCompileOnly"(project(path))
    // KGP adds kotlin-stdlib to a source set's api unless the source set declares one.
    "declarationsCompileOnly"(embeddedKotlin("stdlib"))
    "generatorImplementation"(libs.clikt.core)
    "generatorImplementation"(libs.javapoet)
    "generatorImplementation"(embeddedKotlin("stdlib"))
}

java {
    withSourcesJar()
    withJavadocJar()
    registerFeature("declarations") {
        usingSourceSet(declarations)
        withSourcesJar()
        withJavadocJar()
    }
}

tasks.register<JavaExec>("generateGlesBindings") {
    group = "android-graal"
    description = "Regenerates the checked-in GLES bindings from android.jar and the NDK's GLES headers."
    classpath = generator.runtimeClasspath
    mainClass = "org.androidgraal.gles.generator.GlesGenerator"
    val include = nativeHost.ndk.toolchain.resolve("sysroot/usr/include")
    args("--android-jar", nativeHost.sdk.androidJar(nativeHost.compileSdk).absolutePath)
    args("--gl2-header", include.resolve("GLES2/gl2.h").absolutePath)
    args("--gl3-header", include.resolve("GLES3/gl3.h").absolutePath)
    args("--gl31-header", include.resolve("GLES3/gl31.h").absolutePath)
    args("--gl32-header", include.resolve("GLES3/gl32.h").absolutePath)
    args("--image-out", layout.projectDirectory.dir("src/main/java").asFile.absolutePath)
}

tasks.register<JavaExec>("generateHandles") {
    group = "android-graal"
    description =
        "Regenerates the handle classes and their ART end points from android.jar, its sources and the proxy boundary."
    classpath = generator.runtimeClasspath
    mainClass = "org.androidgraal.handles.generator.HandleGenerator"
    dependsOn(tasks.compileJava)
    val androidJar = nativeHost.sdk.androidJar(nativeHost.compileSdk)
    val boundary = layout.projectDirectory
        .file("src/generator/resources/org/androidgraal/handles/generator/proxy-boundary.txt")
        .asFile
    args("--android-jar", androidJar.absolutePath)
    args("--api-versions", androidJar.resolveSibling("data/api-versions.xml").absolutePath)
    args("--android-sources", nativeHost.sdk.androidSources(nativeHost.compileSdk).absolutePath)
    args("--proxy-boundary", boundary.absolutePath)
    args("--emulation-classes", sourceSets.main.get().java.destinationDirectory.get().asFile.absolutePath)
    args("--image-out", layout.projectDirectory.dir("src/main/java").asFile.absolutePath)
    args("--art-out", layout.settingsDirectory.dir("art/src/main/java").asFile.absolutePath)
    args("--min-sdk", nativeHost.minSdk)
}

spotless {
    java {
        targetExclude(
            "src/generated/java/**",
            fileTree("src/main/java") {
                exclude("org/androidgraal/**", "android/opengl/GLES*.java", "android/os/ProxyMessageQueue.java")
            },
        )
    }
}

tasks.assemble {
    dependsOn(declarations.jarTaskName)
}

tasks.withType<Jar>().configureEach {
    from(layout.projectDirectory.file("LICENSE-android")) { into("META-INF") }
}

tasks.withType<Javadoc>().configureEach {
    (options as StandardJavadocDocletOptions).apply {
        addBooleanOption("Xdoclint:none", true)
        tags("attr:X", "more:X")
    }
}

abstract class DeclarationsInputs : CommandLineArgumentProvider {
    @get:Classpath
    abstract val androidJar: ConfigurableFileCollection

    @get:Classpath
    abstract val emulationClasses: ConfigurableFileCollection

    @get:Classpath
    abstract val declarationClasses: ConfigurableFileCollection

    override fun asArguments(): List<String> = listOf(
        "-Dandroid.androidJar=${androidJar.singleFile.absolutePath}",
        "-Dandroid.emulations=${emulationClasses.singleFile.absolutePath}",
        "-Dandroid.declarations=${declarationClasses.singleFile.absolutePath}",
    )
}

abstract class StockDiffInputs : CommandLineArgumentProvider {
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val copies: DirectoryProperty

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val sdkSources: DirectoryProperty

    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val goldens: DirectoryProperty

    override fun asArguments(): List<String> = listOf(
        "-Dandroid.copies=${copies.get().asFile.absolutePath}",
        "-Dandroid.sdkSources=${sdkSources.get().asFile.absolutePath}",
        "-Dandroid.stockDiffs=${goldens.get().asFile.absolutePath}",
    )
}

abstract class HandleInputs : CommandLineArgumentProvider {
    @get:InputFile
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val proxyBoundary: RegularFileProperty

    @get:InputFile
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val apiVersions: RegularFileProperty

    @get:Input
    abstract val minApi: Property<Int>

    override fun asArguments(): List<String> = listOf(
        "-Dandroid.proxyBoundary=${proxyBoundary.get().asFile.absolutePath}",
        "-Dandroid.apiVersions=${apiVersions.get().asFile.absolutePath}",
        "-Dandroid.minApi=${minApi.get()}",
    )
}

tasks.test {
    jvmArgumentProviders +=
        objects.newInstance<DeclarationsInputs>().apply {
            androidJar.from(nativeHost.sdk.androidJar(nativeHost.compileSdk))
            emulationClasses.from(sourceSets.main.flatMap { it.java.classesDirectory })
            declarationClasses.from(declarations.java.classesDirectory)
        }
    jvmArgumentProviders +=
        objects.newInstance<StockDiffInputs>().apply {
            copies = layout.projectDirectory.dir("src/main/java")
            sdkSources = nativeHost.sdk.androidSources(nativeHost.compileSdk)
            goldens = layout.projectDirectory.dir("src/test/golden/stock-diff")
        }
    jvmArgumentProviders +=
        objects.newInstance<HandleInputs>().apply {
            proxyBoundary = layout.projectDirectory
                .file("src/generator/resources/org/androidgraal/handles/generator/proxy-boundary.txt")
            apiVersions = nativeHost.sdk.androidJar(nativeHost.compileSdk).resolveSibling("data/api-versions.xml")
            minApi = nativeHost.minSdk
        }
}

tasks.check {
    dependsOn(generator.classesTaskName)
}
