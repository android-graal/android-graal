plugins {
    id("org.androidgraal.svm")
    id("androidgraal.base")
}

dependencies {
    contract(project(":android:contract"))
    implementation("org.androidgraal:gles")
}
