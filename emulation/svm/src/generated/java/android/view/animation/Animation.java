package android.view.animation;

/**
 * Abstraction for an Animation that can be applied to Views, Surfaces, or
 * other objects. See the {@link android.view.animation animation package
 * description file}.
 */
public final class Animation {
    /**
     * The specified dimension is an absolute number of pixels.
     */
    public static final int ABSOLUTE = 0;

    /**
     * Repeat the animation indefinitely.
     */
    public static final int INFINITE = -1;

    /**
     * The specified dimension holds a float and should be multiplied by the
     * height or width of the parent of the object being animated.
     */
    public static final int RELATIVE_TO_PARENT = 2;

    /**
     * The specified dimension holds a float and should be multiplied by the
     * height or width of the object being animated.
     */
    public static final int RELATIVE_TO_SELF = 1;

    /**
     * When the animation reaches the end and the repeat count is INFINTE_REPEAT
     * or a positive value, the animation restarts from the beginning.
     */
    public static final int RESTART = 1;

    /**
     * When the animation reaches the end and the repeat count is INFINTE_REPEAT
     * or a positive value, the animation plays backward (and then forward again).
     */
    public static final int REVERSE = 2;

    /**
     * Can be used as the start time to indicate the start time should be the current
     * time when {@link #getTransformation(long, Transformation)} is invoked for the
     * first animation frame. This can is useful for short animations.
     */
    public static final int START_ON_FIRST_FRAME = -1;

    /**
     * Requests that the content being animated be forced under all other
     * content for the duration of the animation.
     */
    public static final int ZORDER_BOTTOM = -1;

    /**
     * Requests that the content being animated be kept in its current Z
     * order.
     */
    public static final int ZORDER_NORMAL = 0;

    /**
     * Requests that the content being animated be forced on top of all other
     * content for the duration of the animation.
     */
    public static final int ZORDER_TOP = 1;

    private Animation() {
    }
}
