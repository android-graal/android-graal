package android.widget;

/**
 * A layout that places its children in a rectangular <em>grid</em>.
 * <p>
 * The grid is composed of a set of infinitely thin lines that separate the
 * viewing area into <em>cells</em>. Throughout the API, grid lines are referenced
 * by grid <em>indices</em>. A grid with {@code N} columns
 * has {@code N + 1} grid indices that run from {@code 0}
 * through {@code N} inclusive. Regardless of how GridLayout is
 * configured, grid index {@code 0} is fixed to the leading edge of the
 * container and grid index {@code N} is fixed to its trailing edge
 * (after padding is taken into account).
 *
 * <h4>Row and Column Specs</h4>
 *
 * Children occupy one or more contiguous cells, as defined
 * by their {@link GridLayout.LayoutParams#rowSpec rowSpec} and
 * {@link GridLayout.LayoutParams#columnSpec columnSpec} layout parameters.
 * Each spec defines the set of rows or columns that are to be
 * occupied; and how children should be aligned within the resulting group of cells.
 * Although cells do not normally overlap in a GridLayout, GridLayout does
 * not prevent children being defined to occupy the same cell or group of cells.
 * In this case however, there is no guarantee that children will not themselves
 * overlap after the layout operation completes.
 *
 * <h4>Default Cell Assignment</h4>
 *
 * If a child does not specify the row and column indices of the cell it
 * wishes to occupy, GridLayout assigns cell locations automatically using its:
 * {@link GridLayout#setOrientation(int) orientation},
 * {@link GridLayout#setRowCount(int) rowCount} and
 * {@link GridLayout#setColumnCount(int) columnCount} properties.
 *
 * <h4>Space</h4>
 *
 * Space between children may be specified either by using instances of the
 * dedicated {@link Space} view or by setting the
 *
 * {@link ViewGroup.MarginLayoutParams#leftMargin leftMargin},
 * {@link ViewGroup.MarginLayoutParams#topMargin topMargin},
 * {@link ViewGroup.MarginLayoutParams#rightMargin rightMargin} and
 * {@link ViewGroup.MarginLayoutParams#bottomMargin bottomMargin}
 *
 * layout parameters. When the
 * {@link GridLayout#setUseDefaultMargins(boolean) useDefaultMargins}
 * property is set, default margins around children are automatically
 * allocated based on the prevailing UI style guide for the platform.
 * Each of the margins so defined may be independently overridden by an assignment
 * to the appropriate layout parameter.
 * Default values will generally produce a reasonable spacing between components
 * but values may change between different releases of the platform.
 *
 * <h4>Excess Space Distribution</h4>
 *
 * As of API 21, GridLayout's distribution of excess space accomodates the principle of weight.
 * In the event that no weights are specified, the previous conventions are respected and
 * columns and rows are taken as flexible if their views specify some form of alignment
 * within their groups.
 * <p>
 * The flexibility of a view is therefore influenced by its alignment which is,
 * in turn, typically defined by setting the
 * {@link LayoutParams#setGravity(int) gravity} property of the child's layout parameters.
 * If either a weight or alignment were defined along a given axis then the component
 * is taken as <em>flexible</em> in that direction. If no weight or alignment was set,
 * the component is instead assumed to be <em>inflexible</em>.
 * <p>
 * Multiple components in the same row or column group are
 * considered to act in <em>parallel</em>. Such a
 * group is flexible only if <em>all</em> of the components
 * within it are flexible. Row and column groups that sit either side of a common boundary
 * are instead considered to act in <em>series</em>. The composite group made of these two
 * elements is flexible if <em>one</em> of its elements is flexible.
 * <p>
 * To make a column stretch, make sure all of the components inside it define a
 * weight or a gravity. To prevent a column from stretching, ensure that one of the components
 * in the column does not define a weight or a gravity.
 * <p>
 * When the principle of flexibility does not provide complete disambiguation,
 * GridLayout's algorithms favour rows and columns that are closer to its <em>right</em>
 * and <em>bottom</em> edges. To be more precise, GridLayout treats each of its layout
 * parameters as a constraint in the a set of variables that define the grid-lines along a
 * given axis. During layout, GridLayout solves the constraints so as to return the unique
 * solution to those constraints for which all variables are less-than-or-equal-to
 * the corresponding value in any other valid solution.
 *
 * <h4>Interpretation of GONE</h4>
 *
 * For layout purposes, GridLayout treats views whose visibility status is
 * {@link View#GONE GONE}, as having zero width and height. This is subtly different from
 * the policy of ignoring views that are marked as GONE outright. If, for example, a gone-marked
 * view was alone in a column, that column would itself collapse to zero width if and only if
 * no gravity was defined on the view. If gravity was defined, then the gone-marked
 * view has no effect on the layout and the container should be laid out as if the view
 * had never been added to it. GONE views are taken to have zero weight during excess space
 * distribution.
 * <p>
 * These statements apply equally to rows as well as columns, and to groups of rows or columns.
 *
 * <p>
 * See {@link GridLayout.LayoutParams} for a full description of the
 * layout parameters used by GridLayout.
 *
 * @attr ref android.R.styleable#GridLayout_orientation
 * @attr ref android.R.styleable#GridLayout_rowCount
 * @attr ref android.R.styleable#GridLayout_columnCount
 * @attr ref android.R.styleable#GridLayout_useDefaultMargins
 * @attr ref android.R.styleable#GridLayout_rowOrderPreserved
 * @attr ref android.R.styleable#GridLayout_columnOrderPreserved
 */
public final class GridLayout {
    /**
     * This constant is an {@link #setAlignmentMode(int) alignmentMode}.
     * When the {@code alignmentMode} is set to {@link #ALIGN_BOUNDS}, alignment
     * is made between the edges of each component's raw
     * view boundary: i.e. the area delimited by the component's:
     * {@link android.view.View#getTop() top},
     * {@link android.view.View#getLeft() left},
     * {@link android.view.View#getBottom() bottom} and
     * {@link android.view.View#getRight() right} properties.
     * <p>
     * For example, when {@code GridLayout} is in {@link #ALIGN_BOUNDS} mode,
     * children that belong to a row group that uses {@link #TOP} alignment will
     * all return the same value when their {@link android.view.View#getTop()}
     * method is called.
     *
     * @see #setAlignmentMode(int)
     */
    public static final int ALIGN_BOUNDS = 0;

    /**
     * This constant is an {@link #setAlignmentMode(int) alignmentMode}.
     * When the {@code alignmentMode} is set to {@link #ALIGN_MARGINS},
     * the bounds of each view are extended outwards, according
     * to their margins, before the edges of the resulting rectangle are aligned.
     * <p>
     * For example, when {@code GridLayout} is in {@link #ALIGN_MARGINS} mode,
     * the quantity {@code top - layoutParams.topMargin} is the same for all children that
     * belong to a row group that uses {@link #TOP} alignment.
     *
     * @see #setAlignmentMode(int)
     */
    public static final int ALIGN_MARGINS = 1;

    /**
     * The horizontal orientation.
     */
    public static final int HORIZONTAL = 0;

    /**
     * The constant used to indicate that a value is undefined.
     * Fields can use this value to indicate that their values
     * have not yet been set. Similarly, methods can return this value
     * to indicate that there is no suitable value that the implementation
     * can return.
     * The value used for the constant (currently {@link Integer#MIN_VALUE}) is
     * intended to avoid confusion between valid values whose sign may not be known.
     */
    public static final int UNDEFINED = -2147483648;

    /**
     * The vertical orientation.
     */
    public static final int VERTICAL = 1;

    private GridLayout() {
    }
}
