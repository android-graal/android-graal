import org.androidgraal.common.Side

plugins {
    id("androidgraal.host")
    java
}

// The image builder's JDK, and the newest class file version it reads.
java {
    toolchain.languageVersion = JavaLanguageVersion.of(Side.SVM.release)
}

val junitVersion = the<VersionCatalogsExtension>().named("libs").findVersion("junit")
    .orElseThrow { GradleException("no junit version in the libs catalog") }

dependencies {
    "testImplementation"(platform("org.junit:junit-bom:${junitVersion.requiredVersion}"))
    "testImplementation"("org.junit.jupiter:junit-jupiter")
    "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
}

// --release keeps javac on ct.sym, so a GraalVM JDK's own org.graalvm.* modules never shadow the jars on the classpath.
tasks.withType<JavaCompile>().configureEach {
    options.release.convention(Side.SVM.release)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<Jar>().configureEach {
    manifest.attributes["Implementation-Version"] = version
}
