package android.graphics.fonts;

/**
 * A font style object.
 *
 * This class represents a single font style which is a pair of weight value and slant value.
 * Here are common font styles examples:
 * <p>
 * <pre>
 * <code>
 * final FontStyle NORMAL = new FontStyle(FONT_WEIGHT_NORMAL, FONT_SLANT_UPRIGHT);
 * final FontStyle BOLD = new FontStyle(FONT_WEIGHT_BOLD, FONT_SLANT_UPRIGHT);
 * final FontStyle ITALIC = new FontStyle(FONT_WEIGHT_NORMAL, FONT_SLANT_ITALIC);
 * final FontStyle BOLD_ITALIC = new FontStyle(FONT_WEIGHT_BOLD, FONT_SLANT_ITALIC);
 * </code>
 * </pre>
 * </p>
 */
public final class FontStyle {
    /**
     * A font slant value for italic
     */
    public static final int FONT_SLANT_ITALIC = 1;

    /**
     * A font slant value for upright
     */
    public static final int FONT_SLANT_UPRIGHT = 0;

    /**
     * A font weight value for the black weight
     */
    public static final int FONT_WEIGHT_BLACK = 900;

    /**
     * A font weight value for the bold weight.
     */
    public static final int FONT_WEIGHT_BOLD = 700;

    /**
     * A font weight value for the extra-bold weight
     */
    public static final int FONT_WEIGHT_EXTRA_BOLD = 800;

    /**
     * A font weight value for the extra-light weight
     */
    public static final int FONT_WEIGHT_EXTRA_LIGHT = 200;

    /**
     * A font weight value for the light weight
     */
    public static final int FONT_WEIGHT_LIGHT = 300;

    /**
     * A maximum weight value for the font
     */
    public static final int FONT_WEIGHT_MAX = 1000;

    /**
     * A font weight value for the medium weight
     */
    public static final int FONT_WEIGHT_MEDIUM = 500;

    /**
     * A minimum weight value for the font
     */
    public static final int FONT_WEIGHT_MIN = 1;

    /**
     * A font weight value for the normal weight
     */
    public static final int FONT_WEIGHT_NORMAL = 400;

    /**
     * A font weight value for the semi-bold weight
     */
    public static final int FONT_WEIGHT_SEMI_BOLD = 600;

    /**
     * A font weight value for the thin weight
     */
    public static final int FONT_WEIGHT_THIN = 100;

    /**
     * A default value when font weight is unspecified
     */
    public static final int FONT_WEIGHT_UNSPECIFIED = -1;

    private FontStyle() {
    }
}
