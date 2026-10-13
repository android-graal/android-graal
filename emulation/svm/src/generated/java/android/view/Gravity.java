package android.view;

/**
 * Standard constants and tools for placing an object within a potentially
 * larger container.
 */
public final class Gravity {
    /**
     * Raw bit controlling whether the right/bottom edge is clipped to its
     *  container, based on the gravity direction being applied.
     */
    public static final int AXIS_CLIP = 8;

    /**
     * Raw bit controlling how the right/bottom edge is placed.
     */
    public static final int AXIS_PULL_AFTER = 4;

    /**
     * Raw bit controlling how the left/top edge is placed.
     */
    public static final int AXIS_PULL_BEFORE = 2;

    /**
     * Raw bit indicating the gravity for an axis has been specified.
     */
    public static final int AXIS_SPECIFIED = 1;

    /**
     * Bits defining the horizontal axis.
     */
    public static final int AXIS_X_SHIFT = 0;

    /**
     * Bits defining the vertical axis.
     */
    public static final int AXIS_Y_SHIFT = 4;

    /**
     * Push object to the bottom of its container, not changing its size.
     */
    public static final int BOTTOM = 80;

    /**
     * Place the object in the center of its container in both the vertical
     *   and horizontal axis, not changing its size.
     */
    public static final int CENTER = 17;

    /**
     * Place object in the horizontal center of its container, not changing its
     *   size.
     */
    public static final int CENTER_HORIZONTAL = 1;

    /**
     * Place object in the vertical center of its container, not changing its
     *   size.
     */
    public static final int CENTER_VERTICAL = 16;

    /**
     * Flag to clip the edges of the object to its container along the
     *   horizontal axis.
     */
    public static final int CLIP_HORIZONTAL = 8;

    /**
     * Flag to clip the edges of the object to its container along the
     *   vertical axis.
     */
    public static final int CLIP_VERTICAL = 128;

    /**
     * Special constant to enable clipping to an overall display along the
     *   horizontal dimension.  This is not applied by default by
     *   {@link #apply(int, int, int, Rect, int, int, Rect)}; you must do so
     *   yourself by calling {@link #applyDisplay}.
     */
    public static final int DISPLAY_CLIP_HORIZONTAL = 16777216;

    /**
     * Special constant to enable clipping to an overall display along the
     *   vertical dimension.  This is not applied by default by
     *   {@link #apply(int, int, int, Rect, int, int, Rect)}; you must do so
     *   yourself by calling {@link #applyDisplay}.
     */
    public static final int DISPLAY_CLIP_VERTICAL = 268435456;

    /**
     * Push object to x-axis position at the end of its container, not changing its size.
     */
    public static final int END = 8388613;

    /**
     * Grow the horizontal and vertical size of the object if needed so it
     *   completely fills its container.
     */
    public static final int FILL = 119;

    /**
     * Grow the horizontal size of the object if needed so it completely fills
     *   its container.
     */
    public static final int FILL_HORIZONTAL = 7;

    /**
     * Grow the vertical size of the object if needed so it completely fills
     *   its container.
     */
    public static final int FILL_VERTICAL = 112;

    /**
     * Binary mask to get the absolute horizontal gravity of a gravity.
     */
    public static final int HORIZONTAL_GRAVITY_MASK = 7;

    /**
     * Push object to the left of its container, not changing its size.
     */
    public static final int LEFT = 3;

    /**
     * Constant indicating that no gravity has been set
     */
    public static final int NO_GRAVITY = 0;

    /**
     * Binary mask for the horizontal gravity and script specific direction bit.
     */
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;

    /**
     * Raw bit controlling whether the layout direction is relative or not (START/END instead of
     *  absolute LEFT/RIGHT).
     */
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;

    /**
     * Push object to the right of its container, not changing its size.
     */
    public static final int RIGHT = 5;

    /**
     * Push object to x-axis position at the start of its container, not changing its size.
     */
    public static final int START = 8388611;

    /**
     * Push object to the top of its container, not changing its size.
     */
    public static final int TOP = 48;

    /**
     * Binary mask to get the vertical gravity of a gravity.
     */
    public static final int VERTICAL_GRAVITY_MASK = 112;

    private Gravity() {
    }
}
