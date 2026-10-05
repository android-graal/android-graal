# gameloop

A frame loop whose per-frame crossing allocates nothing on ART. The image moves bouncing balls and ART draws them on a
`Canvas`; a tap spawns a ball, and holding the finger down pulls the balls towards it.

Everything a frame needs crosses in one call, `Game.render`: ART queues the pointer events since the last frame into an
`int[]` it reuses and hands them over with the frame, so input costs no crossing of its own however many events
arrive. The libGDX backend's queued input works the same way: one crossing per frame instead of one per event.

- `@In @StaticBuffer(capacity = TOUCH_INTS)` on `render`'s `touches`: three ints per pointer event (the action,
  `Game.DOWN`, `MOVE` or `UP`, then x and y as `Float.floatToRawIntBits`) in its first `touchInts` ints; the array of
  exactly `Game.TOUCH_INTS` ints is copied whole into the buffer the image keeps for it on the calling thread, and
  the image reads only those. A move replaces a move queued before it, and a full queue drops every event but an up, which takes the last
  slot, so the image always learns that the finger was lifted.
- `@Out @StaticBuffer(capacity = BALL_FLOATS)` on `render`'s `balls`: the image writes the positions into its
  buffer of `Game.BALL_FLOATS` floats for the calling thread, copied back whole into the array ART reuses every frame.

`@StaticBuffer` makes each side keep one array per position and calling thread instead of a fresh one per call, and
the two copy through a native block per position and thread instead of a transport per call; here that is the UI
thread, where `Choreographer` calls `render`.

## Files

`android/contract` holds what both sides share, `android/svm` is the image module, `android/art` the Android app.

- `android/contract/.../gameloop/Game.java`: the interface with its annotations, on both sides; `@Root` lets ART
  create one with `NativeImage.createRoot(Game.class)`.
- `android/svm/.../gameloop/svm/Balls.java`: the game, named in
  `android/svm/src/main/resources/META-INF/services/org.graalvm.android.gameloop.Game`.
- `android/art/.../gameloop/MainActivity.java`: starts the image and creates the `Game`.
- `android/art/.../gameloop/BallsView.java`: queues pointer events into `touches`; `Choreographer` calls `render`
  every frame.

## Build and run

From the repository root: `./gradlew :samples:gameloop:android:art:installDebug`, then open "GraalVM Game Loop". Eight
balls bounce on a dark background; every tap adds one, up to 64, after which the oldest is reused. While a finger is
down the balls swing towards it and gather there.

## Starting the image

`android/contract` marks its package in a `package-info.java`, `@Bridge package org.graalvm.android.gameloop;`, and
the generated classes live in its `generated` subpackage; both modules depend on it with
`contract(project(":android:contract"))`. `NativeImage.start()`, generated into the app's namespace, loads
`libgameloop.so` and starts the image once per process; `NativeImage.createRoot(Game.class)` creates a new `Balls` in the image
on every call. `MainActivity` keeps its `Game` in a static field, so the balls survive a recreated Activity. The other
generated class, `$GameSvm`, is ART's proxy of the image's `Balls` and is not called by user code.
