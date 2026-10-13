package android.widget;

/**
 * A layout that arranges other views either horizontally in a single column
 * or vertically in a single row.
 *
 * <p>The following snippet shows how to include a linear layout in your layout XML file:</p>
 *
 * <pre>&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 *   android:layout_width="match_parent"
 *   android:layout_height="match_parent"
 *   android:paddingLeft="16dp"
 *   android:paddingRight="16dp"
 *   android:orientation="horizontal"
 *   android:gravity="center"&gt;
 *
 *   &lt;!-- Include other widget or layout tags here. These are considered
 *           "child views" or "children" of the linear layout --&gt;
 *
 * &lt;/LinearLayout&gt;</pre>
 *
 * <p>Set {@link android.R.styleable#LinearLayout_orientation android:orientation} to specify
 * whether child views are displayed in a row or column.</p>
 *
 * <p>To control how linear layout aligns all the views it contains, set a value for
 * {@link android.R.styleable#LinearLayout_gravity android:gravity}.  For example, the
 * snippet above sets android:gravity to "center".  The value you set affects
 * both horizontal and vertical alignment of all child views within the single row or column.</p>
 *
 * <p>You can set
 * {@link android.R.styleable#LinearLayout_Layout_layout_weight android:layout_weight}
 * on individual child views to specify how linear layout divides remaining space amongst
 * the views it contains. See the
 * <a href="https://developer.android.com/guide/topics/ui/layout/linear.html">Linear Layout</a>
 * guide for an example.</p>
 *
 * <p>See
 * {@link android.widget.LinearLayout.LayoutParams LinearLayout.LayoutParams}
 * to learn about other attributes you can set on a child view to affect its
 * position and size in the containing linear layout.</p>
 *
 * @attr ref android.R.styleable#LinearLayout_baselineAligned
 * @attr ref android.R.styleable#LinearLayout_baselineAlignedChildIndex
 * @attr ref android.R.styleable#LinearLayout_gravity
 * @attr ref android.R.styleable#LinearLayout_measureWithLargestChild
 * @attr ref android.R.styleable#LinearLayout_orientation
 * @attr ref android.R.styleable#LinearLayout_weightSum
 */
public final class LinearLayout {
    public static final int HORIZONTAL = 0;

    /**
     * Show a divider at the beginning of the group.
     */
    public static final int SHOW_DIVIDER_BEGINNING = 1;

    /**
     * Show a divider at the end of the group.
     */
    public static final int SHOW_DIVIDER_END = 4;

    /**
     * Show dividers between each item in the group.
     */
    public static final int SHOW_DIVIDER_MIDDLE = 2;

    /**
     * Don't show any dividers.
     */
    public static final int SHOW_DIVIDER_NONE = 0;

    public static final int VERTICAL = 1;

    private LinearLayout() {
    }
}
