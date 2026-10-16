package org.graalvm.android.gameloop;

import org.androidgraal.hosted.annotate.In;
import org.androidgraal.hosted.annotate.Out;
import org.androidgraal.hosted.annotate.Root;
import org.androidgraal.hosted.annotate.StaticBuffer;
import org.androidgraal.hosted.annotate.Svm;

@Root
@Svm
public interface Game {
    /** The pointer actions in {@link #render}'s {@code touches}. */
    int DOWN = 0;

    int MOVE = 1;

    int UP = 2;

    int TOUCH_INTS = 3 * 16;

    int BALL_FLOATS = 3 * 64;

    /** The view starts drawing: the next frame advances nothing. */
    void start();

    void resize(int width, int height);

    /**
     * Applies the pointer events in the first {@code touchInts} ints of {@code touches}, three per event: the action
     * ({@link #DOWN}, {@link #MOVE} or {@link #UP}), then x and y as {@code Float.floatToRawIntBits}. Then advances the
     * game to {@code frameNanos}, writes x, y and radius of each ball into {@code balls} and returns how many balls it
     * wrote.
     */
    int render(long frameNanos, @In @StaticBuffer(capacity = TOUCH_INTS) int[] touches, int touchInts,
            @Out @StaticBuffer(capacity = BALL_FLOATS) float[] balls);
}
