package android.widget;

/**
 * Android magnifier widget. Can be used by any view which is attached to a window.
 */
public final class Magnifier {
    /**
     * A source bound that will extend as much as possible, while remaining within the surface
     * the content is copied from.
     */
    public static final int SOURCE_BOUND_MAX_IN_SURFACE = 0;

    /**
     * A source bound that will extend as much as possible, while remaining within the
     * visible region of the magnified view, as determined by
     * {@link View#getGlobalVisibleRect(Rect)}.
     */
    public static final int SOURCE_BOUND_MAX_VISIBLE = 1;

    private Magnifier() {
    }
}
