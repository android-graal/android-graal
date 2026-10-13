package android.graphics;

/**
 * The Typeface class specifies the typeface and intrinsic style of a font.
 * This is used in the paint, along with optionally Paint settings like
 * textSize, textSkewX, textScaleX to specify
 * how text appears when drawn (and measured).
 */
public final class Typeface {
    public static final int BOLD = 1;

    public static final int BOLD_ITALIC = 3;

    public static final int ITALIC = 2;

    public static final int NORMAL = 0;

    private Typeface() {
    }
}
