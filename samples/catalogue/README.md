# catalogue

One contract position per pattern the bridge generator supports, so the generated code can be read next to the
contract line that produced it. The app calls every method once and prints one line per call. For the annotations,
starting the image, releasing proxies and threads, see [`../bridges/README.md`](../bridges/README.md).

## Files

- `android/contract/.../catalogue/Patterns.java`: the root the image implements, one method per pattern of ART
  calling the image, and the static `tag`, which does not cross.
- `android/contract/.../catalogue/Factory.java`: a second root, whose `newPatterns()` hands out the image's
  `Patterns`.
- `android/contract/.../catalogue/Sink.java`: the interface ART implements, one method per pattern of the image
  calling ART.
- `android/contract/.../catalogue/Peer.java`, `Mode.java`: the interface both sides implement, the enum.
- `android/contract/.../catalogue/Placement.java`, `Spot.java`: the records, `Spot` nested in `Placement`.
- `android/contract/.../catalogue/CatalogueException.java`, `ImageException.java`, `ArtException.java`: the
  exceptions the two sides throw.
- `android/svm/.../catalogue/svm/PatternsImage.java`, `FactoryImage.java`, `PeerImage.java`: the image's
  implementations, each root's named in `android/svm/src/main/resources/META-INF/services/` under the root's name.
- `android/art/.../catalogue/MainActivity.java`: starts the image, obtains `Patterns` through `Factory`, calls every
  `Patterns` method in contract order.
- `android/art/.../catalogue/TextSink.java`, `ArtPeer.java`: ART's `Sink`, which appends to the screen, and ART's
  `Peer`.

## Roots

ART obtains an image object of a `@Root` interface with `NativeImage.createRoot(<root>.class)` (the generated
`NativeImage`), a new one per call. `Patterns` is a root, so `NativeImage.createRoot(Patterns.class)` would do;
`MainActivity` instead obtains the root `Factory` once and calls `newPatterns()`, an ordinary crossing whose result
arrives as ART's proxy of the image's `Patterns`. A factory root is the typed way when the image hands out several types or needs arguments to make one.

## Build and run

From the repository root: `./gradlew :samples:catalogue:android:art:installDebug`, then open "GraalVM Catalogue". The
screen shows:

```
not: false
negate: -5
upper: A
twice: 42
square: 49
cube: 27
half: 1.5
root: 1.4142135623730951
next: OFF
next: null
echo: äöü€😀
place: Placement[label=title, mode=ON, spot=Spot[x=3, y=4]]
place: null
sum: 6
fill: [0.0, 0.5, 1.0]
first: 7
bytes: [1, 2, 3]
fill16: [0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0]
sum16Tls: 10
range: [0, 1, 2, 3]
length16: 15
label16: label 42
greet: hello catalogue
tag: #7
line from the image
level: AUTO
samples: [0.25, 0.5]
collected [10, 20, 30]
accept: image
accepted 5
samples16: [0.0, 0.25, 0.5, 0.75, 1.0, 1.25, 1.5, 1.75, 2.0, 2.25, 2.5, 2.75, 3.0, 3.25, 3.5, 3.75]
collected16 [0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225]
squares [0, 1, 4, 9]
width16: from the image
width16 14
corners4 [0.0, 1.0, 1.0, 0.0]
place: from the image, OFF, 5/6
caught org.graalvm.android.catalogue.ArtException: thrown on ART
primitives: true 1 x 2 3 4 0.5 0.25
primitives returned 10
line from a thread the image started
swap: image
describe: image peer
self: true
caught org.graalvm.android.catalogue.ImageException: thrown in the image
caught org.graalvm.android.catalogue.CatalogueException: reading missing.txt failed, cause java.io.IOException: cannot read missing.txt
```

Logcat shows `swap received art` and `describe peer art`, the image's `System.out`.

## Reading the generated code

From the repository root, `./gradlew :samples:catalogue:android:contract:build` compiles the contract and generates
the two bridges with the annotation processor into
`android/contract/build/generated/androidgraal/art/java` (compiled into the app) and
`android/contract/build/generated/androidgraal/svm/java` (compiled into the image). Both bridges carry the hash of the
contract's model as `FINGERPRINT`. The art bridge carries the service file
`META-INF/services/org.androidgraal.art.Contract` naming `Bridge` for `NativeImage.start()`. Each bridge holds the
same class names, in `org.graalvm.android.catalogue.generated`:

| Contract type | Generated | ART bridge | Image bridge |
|---|---|---|---|
| `Patterns` (`@Root @Svm`) | `$PatternsSvm` | the proxy ART calls, one native per method, the default `greet` included; the per-thread blocks of `fill16`, `sum16Tls`, `length16` and `label16`'s result | the `@CEntryPoint` per method, calling `PatternsImage`, `greet` included; the per-thread arrays of `fill16`, `sum16Tls` and `length16` |
| `Sink` (`@Art`) | `$SinkArt` | the `@EndPoint` statics the image calls, calling `TextSink`; the per-thread arrays of `samples16`, `collect16Tls` and `width16` | the proxy the image calls, each call's arrays and strings in one transport it allocates and frees; the per-thread blocks of `samples16`, `collect16Tls`, `width16` and `corners4`'s result |
| `Factory` (`@Root @Svm`) | `$FactorySvm` | the proxy ART calls, `newPatterns` receiving the image's `Patterns` as a `$PatternsSvm` | the entry point calling `FactoryImage` |
| `Peer` (`@Art @Svm`) | `$PeerArt`, `$PeerSvm` | `$PeerArt`'s end points, `$PeerSvm`'s proxy, `name` and `describe` in each | `$PeerArt`'s proxy, `$PeerSvm`'s entry points, `name` and `describe` in each |
| the contract | `Bridge` | `roots()` and `create(Class)`, which `Image.createRoot` calls, and `initialize()`, which `NativeImage.start()` calls and which calls the image's `initialize` | `initialize`, `instantiatePatterns`, `instantiateFactory` |

`Mode`, the records and the exceptions generate no class of their own: `Mode` crosses as its ordinal (the `MODES`
arrays), a record as its components, an exception as an `org.androidgraal.shared.MarshalledException` carrying the
thrown class's name, its message, its stack trace and its cause, which crosses the same way.

Which pattern each `Patterns` method shows:

- `not`, `negate`, `upper`, `twice`, `square`, `cube`, `half`, `root`: the eight primitives, passed as they are.
- `next`: an enum in and out, as its ordinal, `-1` for null.
- `echo`: a `String` both ways, its UTF-16 chars copied as they are. In: the call's transport (below) holds it as a
  block, its header then its chars. Out: the image allocates a block for the result, returns its address, and ART
  reads it through `Transport.viewBlock` and frees it.
- `place`: a record with a `String`, an enum and the nested record `Spot`, by value: `$PatternsSvm`'s
  proxy reads the components into a presence flag and one native parameter per component (`p0`, `r0_mode`,
  `p0_spot`, `r0_spot_x`, `r0_spot_y`), the `String` `r0_label` riding in the call's transport, and the entry point
  builds a new `Placement` with its canonical constructor; null crosses as a false presence flag. The image prints
  the record it received.
- `sum`: an `@In int[]` with a prefix length, an ordinary `int` the callee reads; `first`: an `@In long[]`.
- `fill`: an `@Out float[]`, arriving zero-filled and copied back whole; `bytes`: an `@Out byte[]`. Each call of a
  directed array without `@StaticBuffer` receives a fresh array. The arrays and strings of one call cross in one
  transport, a `ByteBuffer` over native memory of the image that ART's proxy takes from `Transport.alloc` and hands back
  to `Transport.free` after the call; each value is a block of its own at an 8-byte aligned offset, a header of two ints
  (its length, -1 for null, and its size in bytes, the layout `Blocks` names) then its elements; the `ByteBuffer`
  crosses and the image reads its address, and no transport crosses when every value is null.
- `fill16`: an `@Out float[]` with `@StaticBuffer(capacity = 16)`: ART passes exactly 16 elements. Each calling thread
  has its own block of 16 floats for the position, taken once from `Transport.threadAlloc` and freed when the thread
  exits, under a typed view (the nested `Fill16Buffer0` of the ART bridge), and the image reuses one `float[16]` per
  thread (a `ThreadLocal` field of the image bridge); the image's array is copied into the block, ART reads it back.
  Nothing zero-fills either.
- `sum16Tls`: an `@In int[]` with `@StaticBuffer(capacity = 16)` and a count: the same per-thread block and array in
  the other direction, ART's 16 ints copied into the block, read by the image into its `int[16]`.
- `range`: an `int[]` result: the image allocates a block of the array, returns its address, ART copies it into a
  fresh array and frees the block.
- `length16`: a `String` with `@StaticBuffer(capacity = 16)`: at most 16 chars (more throw on ART before the call),
  written into the calling thread's block of the position after its length, read by the image through a `char[16]`
  it keeps per thread.
- `label16`: a `String` result with `@StaticBuffer(capacity = 16)` on the method: ART passes its thread's block for the
  result, the image writes the length and chars into it (more than 16 throw in the image) and returns nothing; ART
  reads them through a `char[16]` it keeps per thread.
- `greet`: a default method crosses like an abstract one: ART's proxy overrides it with a native, and the body runs
  on the owner's side, where `PatternsImage` inherits it.
- `tag`: a static method is the contract jar's own code and runs where it is called, like a record's or an enum's
  methods: ART calls it without a crossing, and it has no generated member.
- `show`: an `@Art` reference crossing into the image; the image calls every `Sink` method through `$SinkArt`,
  `line` once more from a thread it starts, then releases the `Sink`.
- `swap`: a type both sides implement: ART's `Peer` arrives in the image as a `$PeerArt`, the image's goes back to
  ART as a `$PeerSvm`. `Peer`'s default `describe` crosses on both proxies and runs on the owner's side, where an
  implementation's override wins and the default body runs when there is none: `PeerImage` overrides it, so ART
  prints `describe: image peer`, and `ArtPeer` does not, so the image logs `describe peer art`.
- `self`: the image returns `this`; ART gets the same proxy it called.
- `fail`: the image throws `ImageException`; ART catches a `MarshalledException` naming it.
- `read`: a `throws` clause cannot name a checked exception, so the image wraps its `IOException` in
  `CatalogueException`; ART catches a `MarshalledException` naming `CatalogueException`, whose cause is one naming
  `java.io.IOException`.

Which pattern each `Sink` method shows, in the order `show` calls them:

- `line`: a `String`; `level`: an enum.
- `samples`: an `@In float[]` with a prefix length; `collect`: an `@Out int[]`: the image's proxy allocates a
  transport for the call, passes ART its address and size and frees it after the call; ART makes its view with
  `Transport.view` and reads into a fresh array. The image's generated code holds no JNI reference.
- `accept`: an image `Peer` handed to ART as a `$PeerSvm`, an `int` result.
- `samples16`: an `@In float[]` with `@StaticBuffer(capacity = 16)`: the image copies its 16 elements into its
  calling thread's block for the position (`Transport.threadAlloc`, a `ThreadLocal` field of the image bridge) and
  passes the address; ART reads them into a `float[16]` it keeps for that thread (the nested `Samples16Buffer0` of the
  ART bridge).
- `collect16Tls`: an `@Out int[]` with `@StaticBuffer(capacity = 16)`: the same in the other direction, `TextSink`
  fills ART's `int[16]` for the thread, which is copied into the block and read back into the image's array.
- `squares`: an `int[]` result from ART: `$SinkArt`'s end point takes a block from `Transport.alloc`, writes the array
  and returns the `ByteBuffer`, whose address the image reads before it drops the reference and frees the block.
- `width16`: a `String` with `@StaticBuffer(capacity = 16)`: the image writes it into its thread's block, ART reads it
  through a `char[16]` kept per thread.
- `corners4`: a `float[]` result with `@StaticBuffer(capacity = 4)`: ART writes the length and floats into the image
  thread's block for the result, the image reads them into the `float[4]` it keeps per thread and returns that array
  (an array of another length would come back fresh).
- `place`: the record from the image to ART, flattened by `$SinkArt`'s proxy into the same parameters as
  `Patterns.place`, rebuilt by the end point. `TextSink` prints the components, since on ART the record's `toString`
  shows the class name, which R8 minifies in a release build.
- `fail`: `TextSink` throws `ArtException` on ART; the end point sets the image thread's pending exception, the
  proxy's `Art.callVoid` finds it after the call and throws it in the image as a `MarshalledException` naming
  `ArtException`.
- `primitives`: the eight primitives in one call and a `long` result, one JNI call of the result's kind.
