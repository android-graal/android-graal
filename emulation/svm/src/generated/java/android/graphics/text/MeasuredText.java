package android.graphics.text;

/**
 * Result of text shaping of the single paragraph string.
 *
 * <p>
 * <pre>
 * <code>
 * Paint paint = new Paint();
 * Paint bigPaint = new Paint();
 * bigPaint.setTextSize(paint.getTextSize() * 2.0);
 * String text = "Hello, Android.";
 * MeasuredText mt = new MeasuredText.Builder(text.toCharArray())
 *      .appendStyleRun(paint, 7, false)  // Use paint for "Hello, "
 *      .appendStyleRun(bigPaint, 8, false)  // Use bigPaint for "Android."
 *      .build();
 * </code>
 * </pre>
 * </p>
 */
public final class MeasuredText {
    private MeasuredText() {
    }

    /**
     * Helper class for creating a {@link MeasuredText}.
     * <p>
     * <pre>
     * <code>
     * Paint paint = new Paint();
     * String text = "Hello, Android.";
     * MeasuredText mt = new MeasuredText.Builder(text.toCharArray())
     *      .appendStyleRun(paint, text.length, false)
     *      .build();
     * </code>
     * </pre>
     * </p>
     *
     * Note: The appendStyle and appendReplacementRun should be called to cover the text length.
     */
    public static final class Builder {
        /**
         * 　A value for hyphenation calculation mode.
         *
         *  This value indicates that hyphenation points are calculated with faster algorithm. This
         *  algorithm measures text width with ignoring the context of hyphen character shaping, e.g.
         *  kerning.
         */
        public static final int HYPHENATION_MODE_FAST = 2;

        /**
         * 　A value for hyphenation calculation mode.
         *
         *  This value indicates that no hyphenation points are calculated.
         */
        public static final int HYPHENATION_MODE_NONE = 0;

        /**
         * 　A value for hyphenation calculation mode.
         *
         *  This value indicates that hyphenation points are calculated.
         */
        public static final int HYPHENATION_MODE_NORMAL = 1;

        private Builder() {
        }
    }
}
