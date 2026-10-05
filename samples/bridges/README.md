# bridges

Calls both ways between ART and the image through generated bridges. ART asks the image for a `Hello` and calls it;
the image calls back into ART's `Display`, once on the calling thread and once from a thread it starts itself, and hands
it a `Greeting` the image implements, which ART calls back; an exception thrown in the image arrives on ART as a
`MarshalledException` naming the sample's own `HelloException`.

## Files

`android/contract` holds what both sides share, `android/svm` is the image module, `android/art` the Android app.

- `android/contract/.../bridges/Hello.java`, `Display.java`, `Greeting.java`, `HelloException.java`: the types both
  sides use, annotated with how they cross (see below). The contract's jar is on both sides; the build generates its
  two bridges, one per side.
- `android/svm/.../bridges/svm/HelloImage.java`: the image's `Hello`, named in
  `android/svm/src/main/resources/META-INF/services/org.graalvm.android.bridges.Hello`.
- `android/svm/.../bridges/svm/GreetingImage.java`: the image's `Greeting`.
- `android/art/.../bridges/MainActivity.java`: starts the image, gets a `Hello` and calls it.
- `android/art/.../bridges/TextViewDisplay.java`: ART's `Display`, which appends each line to the screen.

## Build and run

From the repository root: `./gradlew :samples:bridges:android:art:installDebug`, then open "GraalVM Bridges". The
screen shows:

```
hello from the image
ART called back: a greeting the image implements
hello from a thread the image started
echo: äöü€😀
caught org.graalvm.android.bridges.HelloException: thrown in the image
```

Logcat shows `Hello from the image`, the image's `System.out`.

## The contract

`android/contract` applies `org.androidgraal.contract` and marks its package in a `package-info.java`,
`@Bridge package org.graalvm.android.bridges;`; the generated classes live in its `generated` subpackage. Both
modules depend on it through the
`contract` scope, `contract(project(":android:contract"))`, which brings the contract's jar to compile against and,
at run time only, the bridge of their side: the image module gets the image bridge, the app the ART bridge. The
`generated` subpackage holds nothing of the contract; every contract type is public, and
user code names no generated class.

## Annotations

Every interface that crosses by reference is annotated with the side whose objects cross, from
`org.androidgraal.hosted.annotate`: `@Svm` for one the image implements, `@Art` for one ART implements, both when both
sides do. `Hello` is `@Svm` and `@Root`, which requires `@Svm` and lets ART create one with
`NativeImage.createRoot(Hello.class)`; the image module names its implementation in
`META-INF/services/org.graalvm.android.bridges.Hello`, exactly one class. `Display` is `@Art`, `Greeting` is `@Svm`
and no root: ART only gets a `Greeting` from the image. An interface that crosses
by reference and carries neither fails the build, naming the method. The annotations are read from the contract's
class files at build time; nothing reads them at run time.

## Starting the image

`NativeImage.start()`, a class the plugin generates into the app's namespace, loads `libbridges.so`, named by
`androidGraal { imageName }` in the app, starts the image and initialises every contract on the app's classpath. A
process starts the image once: a second `start()` throws an `IllegalStateException`, so a launcher that may run again
asks `NativeImage.isStarted()` first, and `Application.onCreate` is the place free of races. `start()` throws an
`ImageStartException` when the image cannot start.
`NativeImage.createRoot(Hello.class)` (the generated `NativeImage`) creates a new `HelloImage` in the image on every call and
returns ART's proxy of it; a class no loaded contract declares as a root throws an `IllegalArgumentException`. The
contract class `org.graalvm.android.bridges.generated.Bridge` declares the roots and makes the crossing for `Image.createRoot`. The other generated classes are
the proxies, `$<Base>Svm`
for a type the image implements (`$HelloSvm`, `$GreetingSvm` on ART) and `$<Base>Art` for one ART implements
(`$DisplayArt` in the image); user code does not call them.

## Releasing

An object crossing by reference arrives as a proxy, the same proxy every time it crosses, and the other side keeps the
object until the proxy is released: `Art.handles().release(proxy)` in the image (`org.androidgraal.svm.Art`),
`NativeImage.releaseProxy(proxy)` on ART (the generated `NativeImage`). `HelloImage.run` releases the `Display`, which
holds the Activity's `TextView`, and `TextViewDisplay.greet` the `Greeting`. A proxy that is never released keeps its
object until the process ends. Release a proxy only when no other thread can still be calling through it: a call racing the release
throws an `IllegalStateException` on either side.

## Threads

Any thread may cross, in either direction, with nothing around the call. An ART thread is attached to the image on its
first call into it and detached when it exits; a thread the image starts is attached to ART under its own name on its
first call into ART and detached when it exits. `HelloImage.run` starts one that calls `Display.show`.

## How an exception crosses

Every throwable crosses as an `org.androidgraal.shared.MarshalledException`, an unchecked exception carrying the name
of the thrown class (`getForeignExceptionClassName()`), its message and the thrower's stack trace; each of its causes
crosses the same way, as the cause of the one before. Nothing is rebuilt by type, so `MainActivity` catches the
carrier and reads the name: `HelloException` is a contract class only so that the image can throw it and
`Hello.fail` can document it in its `throws` clause. A `throws` clause naming a checked throwable fails the build,
naming the method: a checked exception is wrapped in an unchecked one, and crosses as its cause.

An exception the image does not catch on a thread it started goes to that thread's uncaught exception handler on ART,
as one thrown on ART would: by default the app crashes with a `FATAL EXCEPTION` whose trace starts with the image's
frames.
