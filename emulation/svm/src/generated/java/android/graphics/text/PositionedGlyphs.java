package android.graphics.text;

/**
 * Text shaping result object for single style text.
 *
 * You can get text shaping result by
 * {@link TextRunShaper#shapeTextRun(char[], int, int, int, int, float, float, boolean, Paint)} and
 * {@link TextRunShaper#shapeTextRun(CharSequence, int, int, int, int, float, float, boolean,
 * Paint)}.
 *
 * @see TextRunShaper#shapeTextRun(char[], int, int, int, int, float, float, boolean, Paint)
 * @see TextRunShaper#shapeTextRun(CharSequence, int, int, int, int, float, float, boolean, Paint)
 */
public final class PositionedGlyphs {
    /**
     * A special value returned by {@link #getWeightOverride(int)} and
     * {@link #getItalicOverride(int)} that indicates no font variation setting is overridden.
     */
    public static final float NO_OVERRIDE = 1.4E-45f;

    private PositionedGlyphs() {
    }
}
