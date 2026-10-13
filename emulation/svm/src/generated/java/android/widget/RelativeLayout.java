package android.widget;

/**
 * A Layout where the positions of the children can be described in relation to each other or to the
 * parent.
 *
 * <p>
 * Note that you cannot have a circular dependency between the size of the RelativeLayout and the
 * position of its children. For example, you cannot have a RelativeLayout whose height is set to
 * {@link android.view.ViewGroup.LayoutParams#WRAP_CONTENT WRAP_CONTENT} and a child set to
 * {@link #ALIGN_PARENT_BOTTOM}.
 * </p>
 *
 * <p><strong>Note:</strong> In platform version 17 and lower, RelativeLayout was affected by
 * a measurement bug that could cause child views to be measured with incorrect
 * {@link android.view.View.MeasureSpec MeasureSpec} values. (See
 * {@link android.view.View.MeasureSpec#makeMeasureSpec(int, int) MeasureSpec.makeMeasureSpec}
 * for more details.) This was triggered when a RelativeLayout container was placed in
 * a scrolling container, such as a ScrollView or HorizontalScrollView. If a custom view
 * not equipped to properly measure with the MeasureSpec mode
 * {@link android.view.View.MeasureSpec#UNSPECIFIED UNSPECIFIED} was placed in a RelativeLayout,
 * this would silently work anyway as RelativeLayout would pass a very large
 * {@link android.view.View.MeasureSpec#AT_MOST AT_MOST} MeasureSpec instead.</p>
 *
 * <p>This behavior has been preserved for apps that set <code>android:targetSdkVersion="17"</code>
 * or older in their manifest's <code>uses-sdk</code> tag for compatibility. Apps targeting SDK
 * version 18 or newer will receive the correct behavior.</p>
 *
 * <p>See the <a href="{@docRoot}guide/topics/ui/layout/relative.html">Relative
 * Layout</a> guide.</p>
 *
 * <p>
 * Also see {@link android.widget.RelativeLayout.LayoutParams RelativeLayout.LayoutParams} for
 * layout attributes
 * </p>
 *
 * @attr ref android.R.styleable#RelativeLayout_gravity
 * @attr ref android.R.styleable#RelativeLayout_ignoreGravity
 */
public final class RelativeLayout {
    /**
     * Rule that aligns a child's bottom edge with another child's top edge.
     */
    public static final int ABOVE = 2;

    /**
     * Rule that aligns a child's baseline with another child's baseline.
     */
    public static final int ALIGN_BASELINE = 4;

    /**
     * Rule that aligns a child's bottom edge with another child's bottom edge.
     */
    public static final int ALIGN_BOTTOM = 8;

    /**
     * Rule that aligns a child's end edge with another child's end edge.
     */
    public static final int ALIGN_END = 19;

    /**
     * Rule that aligns a child's left edge with another child's left edge.
     */
    public static final int ALIGN_LEFT = 5;

    /**
     * Rule that aligns the child's bottom edge with its RelativeLayout
     * parent's bottom edge.
     */
    public static final int ALIGN_PARENT_BOTTOM = 12;

    /**
     * Rule that aligns the child's end edge with its RelativeLayout
     * parent's end edge.
     */
    public static final int ALIGN_PARENT_END = 21;

    /**
     * Rule that aligns the child's left edge with its RelativeLayout
     * parent's left edge.
     */
    public static final int ALIGN_PARENT_LEFT = 9;

    /**
     * Rule that aligns the child's right edge with its RelativeLayout
     * parent's right edge.
     */
    public static final int ALIGN_PARENT_RIGHT = 11;

    /**
     * Rule that aligns the child's start edge with its RelativeLayout
     * parent's start edge.
     */
    public static final int ALIGN_PARENT_START = 20;

    /**
     * Rule that aligns the child's top edge with its RelativeLayout
     * parent's top edge.
     */
    public static final int ALIGN_PARENT_TOP = 10;

    /**
     * Rule that aligns a child's right edge with another child's right edge.
     */
    public static final int ALIGN_RIGHT = 7;

    /**
     * Rule that aligns a child's start edge with another child's start edge.
     */
    public static final int ALIGN_START = 18;

    /**
     * Rule that aligns a child's top edge with another child's top edge.
     */
    public static final int ALIGN_TOP = 6;

    /**
     * Rule that aligns a child's top edge with another child's bottom edge.
     */
    public static final int BELOW = 3;

    /**
     * Rule that centers the child horizontally with respect to the
     * bounds of its RelativeLayout parent.
     */
    public static final int CENTER_HORIZONTAL = 14;

    /**
     * Rule that centers the child with respect to the bounds of its
     * RelativeLayout parent.
     */
    public static final int CENTER_IN_PARENT = 13;

    /**
     * Rule that centers the child vertically with respect to the
     * bounds of its RelativeLayout parent.
     */
    public static final int CENTER_VERTICAL = 15;

    /**
     * Rule that aligns a child's start edge with another child's end edge.
     */
    public static final int END_OF = 17;

    /**
     * Rule that aligns a child's right edge with another child's left edge.
     */
    public static final int LEFT_OF = 0;

    /**
     * Rule that aligns a child's left edge with another child's right edge.
     */
    public static final int RIGHT_OF = 1;

    /**
     * Rule that aligns a child's end edge with another child's start edge.
     */
    public static final int START_OF = 16;

    public static final int TRUE = -1;

    private RelativeLayout() {
    }
}
