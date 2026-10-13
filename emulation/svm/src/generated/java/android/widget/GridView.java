package android.widget;

/**
 * A view that shows items in two-dimensional scrolling grid. The items in the
 * grid come from the {@link ListAdapter} associated with this view.
 *
 * <p>See the <a href="{@docRoot}guide/topics/ui/layout/gridview.html">Grid
 * View</a> guide.</p>
 *
 * @attr ref android.R.styleable#GridView_horizontalSpacing
 * @attr ref android.R.styleable#GridView_verticalSpacing
 * @attr ref android.R.styleable#GridView_stretchMode
 * @attr ref android.R.styleable#GridView_columnWidth
 * @attr ref android.R.styleable#GridView_numColumns
 * @attr ref android.R.styleable#GridView_gravity
 */
public final class GridView {
    /**
     * Creates as many columns as can fit on screen.
     *
     * @see #setNumColumns(int)
     */
    public static final int AUTO_FIT = -1;

    /**
     * Disables stretching.
     *
     * @see #setStretchMode(int)
     */
    public static final int NO_STRETCH = 0;

    /**
     * Stretches columns.
     *
     * @see #setStretchMode(int)
     */
    public static final int STRETCH_COLUMN_WIDTH = 2;

    /**
     * Stretches the spacing between columns.
     *
     * @see #setStretchMode(int)
     */
    public static final int STRETCH_SPACING = 1;

    /**
     * Stretches the spacing between columns. The spacing is uniform.
     *
     * @see #setStretchMode(int)
     */
    public static final int STRETCH_SPACING_UNIFORM = 3;

    private GridView() {
    }
}
