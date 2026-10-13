package android.view;

/**
 * Class representing an animation of a set of windows that cause insets.
 */
public final class WindowInsetsAnimation {
    private WindowInsetsAnimation() {
    }

    /**
     * Interface that allows the application to listen to animation events for windows that cause
     * insets.
     */
    public static final class Callback {
        /**
         * Return value for {@link #getDispatchMode()}: Dispatching of animation events should
         * continue in the view hierarchy.
         */
        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;

        /**
         * Return value for {@link #getDispatchMode()}: Dispatching of animation events should
         * stop at this level in the view hierarchy, and no animation events should be dispatch to
         * the subtree of the view hierarchy.
         */
        public static final int DISPATCH_MODE_STOP = 0;

        private Callback() {
        }
    }
}
