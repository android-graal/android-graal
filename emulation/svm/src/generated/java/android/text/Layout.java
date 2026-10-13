package android.text;

/**
 * A base class that manages text layout in visual elements on
 * the screen.
 * <p>For text that will be edited, use a {@link DynamicLayout},
 * which will be updated as the text changes.
 * For text that will not change, use a {@link StaticLayout}.
 */
public final class Layout {
    /**
     * Value for break strategy indicating balanced line breaking. The breaks are chosen to
     * make all lines as close to the same length as possible, including automatic hyphenation.
     */
    public static final int BREAK_STRATEGY_BALANCED = 2;

    /**
     * Value for break strategy indicating high quality line breaking, including automatic
     * hyphenation and doing whole-paragraph optimization of line breaks.
     */
    public static final int BREAK_STRATEGY_HIGH_QUALITY = 1;

    /**
     * Value for break strategy indicating simple line breaking. Automatic hyphens are not added
     * (though soft hyphens are respected), and modifying text generally doesn't affect the layout
     * before it (which yields a more consistent user experience when editing), but layout may not
     * be the highest quality.
     */
    public static final int BREAK_STRATEGY_SIMPLE = 0;

    public static final float DEFAULT_LINESPACING_ADDITION = 0.0f;

    public static final float DEFAULT_LINESPACING_MULTIPLIER = 1.0f;

    public static final int DIR_LEFT_TO_RIGHT = 1;

    public static final int DIR_RIGHT_TO_LEFT = -1;

    /**
     * Value for hyphenation frequency indicating the full amount of automatic hyphenation, typical
     * in typography. Useful for running text and where it's important to put the maximum amount of
     * text in a screen with limited space.
     */
    public static final int HYPHENATION_FREQUENCY_FULL = 2;

    /**
     * Value for hyphenation frequency indicating the full amount of automatic hyphenation with
     * using faster algorithm.
     *
     * This option is useful for running text and where it's important to put the maximum amount of
     * text in a screen with limited space. To make text rendering faster with hyphenation, this
     * algorithm ignores some hyphen character related typographic features, e.g. kerning.
     */
    public static final int HYPHENATION_FREQUENCY_FULL_FAST = 4;

    /**
     * Value for hyphenation frequency indicating no automatic hyphenation. Useful
     * for backward compatibility, and for cases where the automatic hyphenation algorithm results
     * in incorrect hyphenation. Mid-word breaks may still happen when a word is wider than the
     * layout and there is otherwise no valid break. Soft hyphens are ignored and will not be used
     * as suggestions for potential line breaks.
     */
    public static final int HYPHENATION_FREQUENCY_NONE = 0;

    /**
     * Value for hyphenation frequency indicating a light amount of automatic hyphenation, which
     * is a conservative default. Useful for informal cases, such as short sentences or chat
     * messages.
     */
    public static final int HYPHENATION_FREQUENCY_NORMAL = 1;

    /**
     * Value for hyphenation frequency indicating a light amount of automatic hyphenation with
     * using faster algorithm.
     *
     * This option is useful for informal cases, such as short sentences or chat messages. To make
     * text rendering faster with hyphenation, this algorithm ignores some hyphen character related
     * typographic features, e.g. kerning.
     */
    public static final int HYPHENATION_FREQUENCY_NORMAL_FAST = 3;

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

    private Layout() {
    }
}
