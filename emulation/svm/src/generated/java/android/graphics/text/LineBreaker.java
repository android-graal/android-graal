package android.graphics.text;

/**
 * Provides automatic line breaking for a <em>single</em> paragraph.
 *
 * <p>
 * <pre>
 * <code>
 * Paint paint = new Paint();
 * Paint bigPaint = new Paint();
 * bigPaint.setTextSize(paint.getTextSize() * 2.0);
 * String text = "Hello, Android.";
 *
 * // Prepare the measured text
 * MeasuredText mt = new MeasuredText.Builder(text.toCharArray())
 *     .appendStyleRun(paint, 7, false)  // Use paint for "Hello, "
 *     .appednStyleRun(bigPaint, 8, false)  // Use bigPaint for "Hello, "
 *     .build();
 *
 * LineBreaker lb = new LineBreaker.Builder()
 *     // Use simple line breaker
 *     .setBreakStrategy(LineBreaker.BREAK_STRATEGY_SIMPLE)
 *     // Do not add hyphenation.
 *     .setHyphenationFrequency(LineBreaker.HYPHENATION_FREQUENCY_NONE)
 *     // Build the LineBreaker
 *     .build();
 *
 * ParagraphConstraints c = new ParagraphConstraints();
 * c.setWidth(240);  // Set the line wieth as 1024px
 *
 * // Do the line breaking
 * Result r = lb.computeLineBreaks(mt, c, 0);
 *
 * // Compute the total height of the text.
 * float totalHeight = 0;
 * for (int i = 0; i < r.getLineCount(); ++i) {  // iterate over the lines
 *    totalHeight += r.getLineDescent(i) - r.getLineAscent(i);
 * }
 *
 * // Draw text to the canvas
 * Bitmap bmp = Bitmap.createBitmap(240, totalHeight, Bitmap.Config.ARGB_8888);
 * Canvas c = new Canvas(bmp);
 * float yOffset = 0f;
 * int prevOffset = 0;
 * for (int i = 0; i < r.getLineCount(); ++i) {  // iterate over the lines
 *     int nextOffset = r.getLineBreakOffset(i);
 *     c.drawText(text, prevOffset, nextOffset, 0f, yOffset, paint);
 *
 *     prevOffset = nextOffset;
 *     yOffset += r.getLineDescent(i) - r.getLineAscent(i);
 * }
 * </code>
 * </pre>
 * </p>
 */
public final class LineBreaker {
    /**
     * Value for break strategy indicating balanced line breaking.
     *
     * The line breaker does whole-paragraph optimization for making all lines similar length, and
     * also applies automatic hyphenation when required. This break strategy is good for small
     * screen devices such as watch screens.
     */
    public static final int BREAK_STRATEGY_BALANCED = 2;

    /**
     * Value for break strategy indicating high quality line breaking.
     *
     * With this option line breaker does whole-paragraph optimization for more readable text, and
     * also applies automatic hyphenation when required.
     */
    public static final int BREAK_STRATEGY_HIGH_QUALITY = 1;

    /**
     * Value for break strategy indicating simple line breaking.
     *
     * The line breaker puts words to the line as much as possible and breaks line if no more words
     * can fit into the same line. Automatic hyphens are only added when a line has a single word
     * and that word is longer than line width. This is the fastest break strategy and ideal for
     * editor.
     */
    public static final int BREAK_STRATEGY_SIMPLE = 0;

    /**
     * Value for hyphenation frequency indicating the full amount of automatic hyphenation.
     *
     * This hyphenation frequency is useful for running text and where it's important to put the
     * maximum amount of text in a screen with limited space.
     */
    public static final int HYPHENATION_FREQUENCY_FULL = 2;

    /**
     * Value for hyphenation frequency indicating no automatic hyphenation.
     *
     * Using this option disables auto hyphenation which results in better text layout performance.
     * A word may be broken without hyphens when a line has a single word and that word is longer
     * than line width. Soft hyphens are ignored and will not be used as suggestions for potential
     * line breaks.
     */
    public static final int HYPHENATION_FREQUENCY_NONE = 0;

    /**
     * Value for hyphenation frequency indicating a light amount of automatic hyphenation.
     *
     * This hyphenation frequency is useful for informal cases, such as short sentences or chat
     * messages.
     */
    public static final int HYPHENATION_FREQUENCY_NORMAL = 1;

    /**
     * Value for justification mode indicating the text is justified by stretching letter spacing.
     */
    public static final int JUSTIFICATION_MODE_INTER_CHARACTER = 2;

    /**
     * Value for justification mode indicating the text is justified by stretching word spacing.
     */
    public static final int JUSTIFICATION_MODE_INTER_WORD = 1;

    /**
     * Value for justification mode indicating no justification.
     */
    public static final int JUSTIFICATION_MODE_NONE = 0;

    private LineBreaker() {
    }
}
