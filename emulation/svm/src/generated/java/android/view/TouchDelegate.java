package android.view;

/**
 * Helper class to handle situations where you want a view to have a larger touch area than its
 * actual view bounds. The view whose touch area is changed is called the delegate view. This
 * class should be used by an ancestor of the delegate. To use a TouchDelegate, first create an
 * instance that specifies the bounds that should be mapped to the delegate and the delegate
 * view itself.
 * <p>
 * The ancestor should then forward all of its touch events received in its
 * {@link android.view.View#onTouchEvent(MotionEvent)} to {@link #onTouchEvent(MotionEvent)}.
 * </p>
 */
public final class TouchDelegate {
    /**
     * The touchable region of the View extends above its actual extent.
     */
    public static final int ABOVE = 1;

    /**
     * The touchable region of the View extends below its actual extent.
     */
    public static final int BELOW = 2;

    /**
     * The touchable region of the View extends to the left of its actual extent.
     */
    public static final int TO_LEFT = 4;

    /**
     * The touchable region of the View extends to the right of its actual extent.
     */
    public static final int TO_RIGHT = 8;

    private TouchDelegate() {
    }
}
