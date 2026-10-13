package android.view;

/**
 * Describes a set of insets for window content.
 *
 * <p>WindowInsets are immutable and may be expanded to include more inset types in the future.
 * To adjust insets, use one of the supplied clone methods to obtain a new WindowInsets instance
 * with the adjusted properties.</p>
 *
 * <p>Note: Before {@link android.os.Build.VERSION_CODES#P P}, WindowInsets instances were only
 * immutable during a single layout pass (i.e. would return the same values between
 * {@link View#onApplyWindowInsets} and {@link View#onLayout}, but could return other values
 * otherwise). Starting with {@link android.os.Build.VERSION_CODES#P P}, WindowInsets are
 * always immutable and implement equality.
 *
 * @see View.OnApplyWindowInsetsListener
 * @see View#onApplyWindowInsets(WindowInsets)
 */
public final class WindowInsets {
    private WindowInsets() {
    }

    /**
     * Class that defines different sides for insets.
     */
    public static final class Side {
        public static final int BOTTOM = 8;

        public static final int LEFT = 1;

        public static final int RIGHT = 4;

        public static final int TOP = 2;

        private Side() {
        }
    }
}
