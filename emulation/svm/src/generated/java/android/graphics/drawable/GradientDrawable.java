package android.graphics.drawable;

/**
 * A Drawable with a color gradient for buttons, backgrounds, etc.
 *
 * <p>It can be defined in an XML file with the <code>&lt;shape></code> element. For more
 * information, see the guide to <a
 * href="{@docRoot}guide/topics/resources/drawable-resource.html">Drawable Resources</a>.</p>
 *
 * @attr ref android.R.styleable#GradientDrawable_visible
 * @attr ref android.R.styleable#GradientDrawable_shape
 * @attr ref android.R.styleable#GradientDrawable_innerRadiusRatio
 * @attr ref android.R.styleable#GradientDrawable_innerRadius
 * @attr ref android.R.styleable#GradientDrawable_thicknessRatio
 * @attr ref android.R.styleable#GradientDrawable_thickness
 * @attr ref android.R.styleable#GradientDrawable_useLevel
 * @attr ref android.R.styleable#GradientDrawableSize_width
 * @attr ref android.R.styleable#GradientDrawableSize_height
 * @attr ref android.R.styleable#GradientDrawableGradient_startColor
 * @attr ref android.R.styleable#GradientDrawableGradient_centerColor
 * @attr ref android.R.styleable#GradientDrawableGradient_endColor
 * @attr ref android.R.styleable#GradientDrawableGradient_useLevel
 * @attr ref android.R.styleable#GradientDrawableGradient_angle
 * @attr ref android.R.styleable#GradientDrawableGradient_type
 * @attr ref android.R.styleable#GradientDrawableGradient_centerX
 * @attr ref android.R.styleable#GradientDrawableGradient_centerY
 * @attr ref android.R.styleable#GradientDrawableGradient_gradientRadius
 * @attr ref android.R.styleable#GradientDrawableSolid_color
 * @attr ref android.R.styleable#GradientDrawableStroke_width
 * @attr ref android.R.styleable#GradientDrawableStroke_color
 * @attr ref android.R.styleable#GradientDrawableStroke_dashWidth
 * @attr ref android.R.styleable#GradientDrawableStroke_dashGap
 * @attr ref android.R.styleable#GradientDrawablePadding_left
 * @attr ref android.R.styleable#GradientDrawablePadding_top
 * @attr ref android.R.styleable#GradientDrawablePadding_right
 * @attr ref android.R.styleable#GradientDrawablePadding_bottom
 */
public final class GradientDrawable {
    /**
     * Shape is a line
     */
    public static final int LINE = 2;

    /**
     * Gradient is linear (default.)
     */
    public static final int LINEAR_GRADIENT = 0;

    /**
     * Shape is an ellipse
     */
    public static final int OVAL = 1;

    /**
     * Gradient is circular.
     */
    public static final int RADIAL_GRADIENT = 1;

    /**
     * Shape is a rectangle, possibly with rounded corners
     */
    public static final int RECTANGLE = 0;

    /**
     * Shape is a ring.
     */
    public static final int RING = 3;

    /**
     * Gradient is a sweep.
     */
    public static final int SWEEP_GRADIENT = 2;

    private GradientDrawable() {
    }
}
