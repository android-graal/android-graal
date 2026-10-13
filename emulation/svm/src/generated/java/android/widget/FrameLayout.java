package android.widget;

/**
 * FrameLayout is designed to block out an area on the screen to display
 * a single item. Generally, FrameLayout should be used to hold a single child view, because it can
 * be difficult to organize child views in a way that's scalable to different screen sizes without
 * the children overlapping each other. You can, however, add multiple children to a FrameLayout
 * and control their position within the FrameLayout by assigning gravity to each child, using the
 * <a href="FrameLayout.LayoutParams.html#attr_android:layout_gravity">{@code
 * android:layout_gravity}</a> attribute.
 * <p>Child views are drawn in a stack, with the most recently added child on top.
 * The size of the FrameLayout is the size of its largest child (plus padding), visible
 * or not (if the FrameLayout's parent permits). Views that are {@link android.view.View#GONE} are
 * used for sizing
 * only if {@link #setMeasureAllChildren(boolean) setConsiderGoneChildrenWhenMeasuring()}
 * is set to true.
 *
 * @attr ref android.R.styleable#FrameLayout_measureAllChildren
 */
public final class FrameLayout {
    private FrameLayout() {
    }

    /**
     * Per-child layout information for layouts that support margins.
     * See {@link android.R.styleable#FrameLayout_Layout FrameLayout Layout Attributes}
     * for a list of all child view attributes that this class supports.
     *
     * @attr ref android.R.styleable#FrameLayout_Layout_layout_gravity
     */
    public static final class LayoutParams {
        /**
         * Value for {@link #gravity} indicating that a gravity has not been
         * explicitly specified.
         */
        public static final int UNSPECIFIED_GRAVITY = -1;

        private LayoutParams() {
        }
    }
}
