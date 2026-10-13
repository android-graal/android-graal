package android.animation;

/**
 * This class provides a simple timing engine for running animations
 * which calculate animated values and set them on target objects.
 *
 * <p>There is a single timing pulse that all animations use. It runs in a
 * custom handler to ensure that property changes happen on the UI thread.</p>
 *
 * <p>By default, ValueAnimator uses non-linear time interpolation, via the
 * {@link AccelerateDecelerateInterpolator} class, which accelerates into and decelerates
 * out of an animation. This behavior can be changed by calling
 * {@link ValueAnimator#setInterpolator(TimeInterpolator)}.</p>
 *
 * <p>Animators can be created from either code or resource files. Here is an example
 * of a ValueAnimator resource file:</p>
 *
 * {@sample development/samples/ApiDemos/res/anim/animator.xml ValueAnimatorResources}
 *
 * <p>Starting from API 23, it is also possible to use a combination of {@link PropertyValuesHolder}
 * and {@link Keyframe} resource tags to create a multi-step animation.
 * Note that you can specify explicit fractional values (from 0 to 1) for
 * each keyframe to determine when, in the overall duration, the animation should arrive at that
 * value. Alternatively, you can leave the fractions off and the keyframes will be equally
 * distributed within the total duration:</p>
 *
 * {@sample development/samples/ApiDemos/res/anim/value_animator_pvh_kf.xml
 * ValueAnimatorKeyframeResources}
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about animating with {@code ValueAnimator}, read the
 * <a href="{@docRoot}guide/topics/graphics/prop-animation.html#value-animator">Property
 * Animation</a> developer guide.</p>
 * </div>
 */
public final class ValueAnimator {
    /**
     * This value used used with the {@link #setRepeatCount(int)} property to repeat
     * the animation indefinitely.
     */
    public static final int INFINITE = -1;

    /**
     * When the animation reaches the end and <code>repeatCount</code> is INFINITE
     * or a positive value, the animation restarts from the beginning.
     */
    public static final int RESTART = 1;

    /**
     * When the animation reaches the end and <code>repeatCount</code> is INFINITE
     * or a positive value, the animation reverses direction on every iteration.
     */
    public static final int REVERSE = 2;

    private ValueAnimator() {
    }
}
