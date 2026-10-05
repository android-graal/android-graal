plugins {
    id("androidgraal.java")
    id("org.gradle.kotlin.embedded-kotlin")
}

description = "android.opengl.GLES20 and GLES30 for the image, over libGLESv2 and libGLESv3."

val sdkVersion = 36
val generator: SourceSet = sourceSets.create("generator")

dependencies {
    compileOnly(project(":java:hosted"))
    implementation(project(":java:graal:nativeimage"))
    "generatorImplementation"(libs.javapoet)
    "generatorImplementation"(embeddedKotlin("stdlib"))
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
            layout.projectDirectory.dir("src/main/java").asFile,
        ).map { it.absolutePath },
    )

    finalizedBy("spotlessApply")
}

tasks.matching { it.name.startsWith("spotlessJava") }.configureEach {
    mustRunAfter("generateGlesBindings")
}

abstract class DeclarationsInputs : CommandLineArgumentProvider {
    @get:Classpath
    abstract val androidJar: ConfigurableFileCollection

    override fun asArguments(): List<String> = listOf("-Dgles.androidJar=${androidJar.singleFile.absolutePath}")
}

tasks.test {
    jvmArgumentProviders +=
        objects.newInstance<DeclarationsInputs>().apply { androidJar.from(nativeHost.sdk.androidJar(sdkVersion)) }
}

tasks.check {
    dependsOn(generator.classesTaskName)
}
