package android.view.animation;

/**
 * A layout animation controller is used to animate the children of a layout or a view
 * group. Each child uses the same animation but for every one of
 * them, the animation starts at a different time. A layout animation controller
 * is used by {@link android.view.ViewGroup} to compute the delay by which each
 * child's animation start must be offset. The delay is computed by using
 * characteristics of each child, like its index in the view group.
 *
 * This standard implementation computes the delay by multiplying a fixed
 * amount of miliseconds by the index of the child in its parent view group.
 * Subclasses are supposed to override
 * {@link #getDelayForView(android.view.View)} to implement a different way
 * of computing the delay. For instance, a
 * {@link android.view.animation.GridLayoutAnimationController} will compute the
 * delay based on the column and row indices of the child in its parent view
 * group.
 *
 * Information used to compute the animation delay of each child are stored
 * in an instance of
 * {@link android.view.animation.LayoutAnimationController.AnimationParameters},
 * itself stored in the {@link android.view.ViewGroup.LayoutParams} of the view.
 *
 * @attr ref android.R.styleable#LayoutAnimation_delay
 * @attr ref android.R.styleable#LayoutAnimation_animationOrder
 * @attr ref android.R.styleable#LayoutAnimation_interpolator
 * @attr ref android.R.styleable#LayoutAnimation_animation
 */
public final class LayoutAnimationController {
    /**
     * Distributes the animation delays in the order in which view were added
     * to their view group.
     */
    public static final int ORDER_NORMAL = 0;

    /**
     * Randomly distributes the animation delays.
     */
    public static final int ORDER_RANDOM = 2;

    /**
     * Distributes the animation delays in the reverse order in which view were
     * added to their view group.
     */
    public static final int ORDER_REVERSE = 1;

    private LayoutAnimationController() {
    }
}
