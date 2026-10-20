package android.view.inputmethod;

import android.os.Parcelable;

/**
 * The text bounds information of a slice of text in the editor.
 *
 * <p> This class provides IME the layout information of the text within the range from
 * {@link #getStartIndex()} to {@link #getEndIndex()}. It's intended to be used by IME as a
 * supplementary API to support handwriting gestures.
 * </p>
 */
public final class TextBoundsInfo implements Parcelable {
    /**
     * The flag indicating that the character is a linefeed character.
     *
     * @see Builder#setCharacterFlags(int[])
     * @see #getCharacterFlags(int)
     */
    public static final int FLAG_CHARACTER_LINEFEED = 2;

    /**
     * The flag indicating that the character is a punctuation.
     *
     * @see Builder#setCharacterFlags(int[])
     * @see #getCharacterFlags(int)
     */
    public static final int FLAG_CHARACTER_PUNCTUATION = 4;

    /**
     * The flag indicating that the character is a whitespace.
     *
     * @see Builder#setCharacterFlags(int[])
     * @see #getCharacterFlags(int)
     */
    public static final int FLAG_CHARACTER_WHITESPACE = 1;

    /**
     * The flag indicating that the line this character belongs to has RTL line direction. It's
     * required that all characters in the same line must have the same direction.
     *
     * @see Builder#setCharacterFlags(int[])
     * @see #getCharacterFlags(int)
     */
    public static final int FLAG_LINE_IS_RTL = 8;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TextBoundsInfo() {
    }
}
