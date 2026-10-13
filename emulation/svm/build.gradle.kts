plugins {
    id("androidgraal.java")
    id("org.gradle.kotlin.embedded-kotlin")
}

group = "${nativeHost.settings.group}.emulation"

description = "Every android.* class the image provides, emulated or as a constants skeleton."

val sdkVersion = 36
val generator: SourceSet = sourceSets.create("generator")
val declarations: SourceSet = sourceSets.create("declarations") {
    java.setSrcDirs(listOf("src/generated/java"))
    resources.setSrcDirs(emptyList<File>())
}

dependencies {
    api(project(":contract"))
    implementation("${nativeHost.settings.group}:svm:$version")
    implementation("${nativeHost.settings.group}:bionic:$version")
    compileOnly("${nativeHost.settings.group}:hosted:$version")
    compileOnly("${nativeHost.settings.group}.graal:svm-shared:$version")
    compileOnly(libs.androidx.annotation)
    "declarationsCompileOnly"(project(path))
    // KGP adds kotlin-stdlib to a source set's api unless the source set declares one.
    "declarationsCompileOnly"(embeddedKotlin("stdlib"))
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
    args(
        listOf(
            nativeHost.sdk.androidJar(sdkVersion),
            include.resolve("GLES2/gl2.h"),
            include.resolve("GLES3/gl3.h"),
            include.resolve("GLES3/gl31.h"),
            include.resolve("GLES3/gl32.h"),
            layout.projectDirectory.dir("src/main/java").asFile,
        ).map { it.absolutePath },
    )
}

spotless {
    java {
        targetExclude(
            "src/generated/java/**",
            fileTree("src/main/java") {
                exclude("org/androidgraal/**", "android/opengl/GLES*.java")
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

tasks.test {
    jvmArgumentProviders +=
        objects.newInstance<DeclarationsInputs>().apply {
            androidJar.from(nativeHost.sdk.androidJar(sdkVersion))
            emulationClasses.from(sourceSets.main.flatMap { it.java.classesDirectory })
            declarationClasses.from(declarations.java.classesDirectory)
        }
}

tasks.check {
    dependsOn(generator.classesTaskName)
}
