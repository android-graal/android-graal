package android.text;

public final class TextUtils {
    /**
     * Capitalization mode for {@link #getCapsMode}: capitalize all
     * characters.  This value is explicitly defined to be the same as
     * {@link InputType#TYPE_TEXT_FLAG_CAP_CHARACTERS}.
     */
    public static final int CAP_MODE_CHARACTERS = 4096;

    /**
     * Capitalization mode for {@link #getCapsMode}: capitalize the first
     * character of each sentence.  This value is explicitly defined to be the same as
     * {@link InputType#TYPE_TEXT_FLAG_CAP_SENTENCES}.
     */
    public static final int CAP_MODE_SENTENCES = 16384;

    /**
     * Capitalization mode for {@link #getCapsMode}: capitalize the first
     * character of all words.  This value is explicitly defined to be the same as
     * {@link InputType#TYPE_TEXT_FLAG_CAP_WORDS}.
     */
    public static final int CAP_MODE_WORDS = 8192;

    /**
     * Return only first line of text (truncate at first newline). Cannot be set at the same time as
     * {@link #SAFE_STRING_FLAG_SINGLE_LINE}.
     *
     * @see #makeSafeForPresentation(String, int, float, int)
     */
    public static final int SAFE_STRING_FLAG_FIRST_LINE = 4;

    /**
     * Force entire string into single line of text (no newlines). Cannot be set at the same time as
     * {@link #SAFE_STRING_FLAG_FIRST_LINE}.
     *
     * @see #makeSafeForPresentation(String, int, float, int)
     */
    public static final int SAFE_STRING_FLAG_SINGLE_LINE = 2;

    /**
     * Remove {@link Character#isWhitespace(int) whitespace} and non-breaking spaces from the edges
     * of the label.
     *
     * @see #makeSafeForPresentation(String, int, float, int)
     */
    public static final int SAFE_STRING_FLAG_TRIM = 1;

    private TextUtils() {
    }
}
