# gl

Runs a class that calls Android's GL API inside the image: add `gles` to the image module and code written against
`android.opengl.GLES20` runs there unchanged. `org.androidgraal:gles` carries `android.opengl.GLES20` and `GLES30` for
the image, calling libGLESv2 and libGLESv3 directly. ART only hosts a `GLSurfaceView`; no GL call crosses to ART.

The image runs no Android code: the build fails on a class under `android.*` on the image classpath unless it carries
`@Emulated`, as the classes of `gles` do. It also fails on any reference the image reaches that no class on the image
classpath resolves (`--link-at-build-time`).

## Files

`android/contract` holds what both sides share, `android/svm` is the image module, `android/art` the Android app.

- `android/contract/.../gl/Scene.java`: what ART calls on the GL thread, on both sides; `@Root` lets ART create one
  with `NativeImage.createRoot(Scene.class)`.
- `android/svm/.../gl/svm/TriangleScene.java`: draws through `android.opengl.GLES20`, a clear colour and one
  triangle; named in `android/svm/src/main/resources/META-INF/services/org.graalvm.android.gl.Scene`.
- `android/svm/build.gradle.kts`: the image module depends on `org.androidgraal:gles`.
- `android/art/.../gl/MainActivity.java` and `SceneRenderer.java`: the `GLSurfaceView` and its renderer, which calls
  `created`, `resized` and `draw`; `onDestroy` releases the `Scene`.

## Build and run

From the repository root: `./gradlew :samples:gl:android:art:installDebug`, then open "GraalVM GL". A white triangle
turns over a background whose colour changes slowly.

## Starting the image

`android/contract` marks its package in a `package-info.java`, `@Bridge package org.graalvm.android.gl;`, and the
generated classes live in its `generated` subpackage; both modules depend on it with `contract(project(":android:contract"))`. `NativeImage.start()`, generated into the app's
namespace, loads `libgl.so` and starts the image, once per process: `onCreate` asks `NativeImage.isStarted()` first, a
second `start()` throws; `NativeImage.createRoot(Scene.class)` creates a new `TriangleScene` in the image on every call, so
every Activity, and with it every GL context, gets its own. The image keeps a `TriangleScene` until ART releases its proxy with
`NativeImage.releaseProxy(scene)`, which `onDestroy` does once `onPause` has paused the GL thread.
The other generated class, `$SceneSvm`, is ART's proxy of the image's `TriangleScene` and is not called by user code.
