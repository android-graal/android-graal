plugins {
    id("androidgraal.java")
}

group = "${nativeHost.settings.group}.graal"

description = "The @AlwaysInline annotation of SubstrateVM's com.oracle.svm.shared module from the fork the " +
    "android-graal toolchain is built from, for compile classpaths only (GPL 2 with the Classpath exception, Oracle)."

sourceSets.main {
    java.srcDir(nativeHost.vendor.graal.resolve("substratevm/src/com.oracle.svm.shared/src"))
    java.include("module-info.java", "com/oracle/svm/shared/AlwaysInline.java")
}

dependencies {
    api(project(":java:graal:nativeimage"))
}

tasks.compileJava {
    options.release = 17
}

tasks.processResources {
    from(nativeHost.vendor.graal.resolve("substratevm/LICENSE")) {
        into("META-INF")
    }
}

publishing {
    publications.named<MavenPublication>("java") {
        pom {
            name = "org.graalvm.nativeimage.shared (AlwaysInline)"
            description = provider { project.description }
            licenses {
                license {
                    name = "GNU General Public License, version 2, with the Classpath Exception"
                    url = "https://openjdk.org/legal/gplv2+ce.html"
                }
            }
        }
    }
}
