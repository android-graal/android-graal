package android.view.animation;

/**
 * A layout animation controller is used to animated a grid layout's children.
 *
 * While {@link LayoutAnimationController} relies only on the index of the child
 * in the view group to compute the animation delay, this class uses both the
 * X and Y coordinates of the child within a grid.
 *
 * In addition, the animation direction can be controlled. The default direction
 * is <code>DIRECTION_LEFT_TO_RIGHT | DIRECTION_TOP_TO_BOTTOM</code>. You can
 * also set the animation priority to columns or rows. The default priority is
 * none.
 *
 * Information used to compute the animation delay of each child are stored
 * in an instance of
 * {@link android.view.animation.GridLayoutAnimationController.AnimationParameters},
 * itself stored in the {@link android.view.ViewGroup.LayoutParams} of the view.
 *
 * @see LayoutAnimationController
 * @see android.widget.GridView
 *
 * @attr ref android.R.styleable#GridLayoutAnimation_columnDelay
 * @attr ref android.R.styleable#GridLayoutAnimation_rowDelay
 * @attr ref android.R.styleable#GridLayoutAnimation_direction
 * @attr ref android.R.styleable#GridLayoutAnimation_directionPriority
 */
public final class GridLayoutAnimationController {
    /**
     * Animates the children starting from the bottom of the grid to the top.
     */
    public static final int DIRECTION_BOTTOM_TO_TOP = 2;

    /**
     * Bitmask used to retrieve the horizontal component of the direction.
     */
    public static final int DIRECTION_HORIZONTAL_MASK = 1;

    /**
     * Animates the children starting from the left of the grid to the right.
     */
    public static final int DIRECTION_LEFT_TO_RIGHT = 0;

    /**
     * Animates the children starting from the right of the grid to the left.
     */
    public static final int DIRECTION_RIGHT_TO_LEFT = 1;

    /**
     * Animates the children starting from the top of the grid to the bottom.
     */
    public static final int DIRECTION_TOP_TO_BOTTOM = 0;

    /**
     * Bitmask used to retrieve the vertical component of the direction.
     */
    public static final int DIRECTION_VERTICAL_MASK = 2;

    /**
     * Columns are animated first.
     */
    public static final int PRIORITY_COLUMN = 1;

    /**
     * Rows and columns are animated at the same time.
     */
    public static final int PRIORITY_NONE = 0;

    /**
     * Rows are animated first.
     */
    public static final int PRIORITY_ROW = 2;

    private GridLayoutAnimationController() {
    }
}
