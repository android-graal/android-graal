package android.graphics.drawable;

/**
 * {@link Drawable} for drawing animated images (like GIF).
 *
 * <p>The framework handles decoding subsequent frames in another thread and
 * updating when necessary. The drawable will only animate while it is being
 * displayed.</p>
 *
 * <p>Created by {@link ImageDecoder#decodeDrawable}. A user needs to call
 * {@link #start} to start the animation.</p>
 *
 * <p>It can also be defined in XML using the <code>&lt;animated-image></code>
 * element.</p>
 *
 * @attr ref android.R.styleable#AnimatedImageDrawable_src
 * @attr ref android.R.styleable#AnimatedImageDrawable_autoStart
 * @attr ref android.R.styleable#AnimatedImageDrawable_repeatCount
 * @attr ref android.R.styleable#AnimatedImageDrawable_autoMirrored
 */
public final class AnimatedImageDrawable {
    /**
     *  Pass this to {@link #setRepeatCount} to repeat infinitely.
     *
     *  <p>{@link Animatable2.AnimationCallback#onAnimationEnd} will never be
     *  called unless there is an error.</p>
     */
    public static final int REPEAT_INFINITE = -1;

    private AnimatedImageDrawable() {
    }
}
