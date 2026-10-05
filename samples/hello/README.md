# hello

The plainest app: no bridge. The image exports two JNI-named `@CEntryPoint`s that `MainActivity` declares
`native` and calls on the UI thread; the image's `System.out` goes to logcat.

Read in this order:

1. `android/svm/src/main/java/org/graalvm/android/hello/svm/HelloWorld.java`: the two entry points.
2. `android/art/src/main/java/org/graalvm/android/hello/MainActivity.java`: loads `libhello.so` by `NativeImage.NAME`, the image's name the plugin generates into the app,
   creates the isolate, calls `hello`.
3. `android/art/build.gradle.kts`: `androidGraal { imageName }` and `nativeImage(project(":android:svm"))`.

Install from the repository root: `./gradlew :samples:hello:android:art:installDebug`
