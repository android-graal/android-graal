package android.view.inputmethod;

import android.os.Parcelable;

/**
 * Positional information about the text insertion point and characters in the composition string.
 *
 * <p>This class encapsulates locations of the text insertion point and the composition string in
 * the screen coordinates so that IMEs can render their UI components near where the text is
 * actually inserted.</p>
 */
public final class CursorAnchorInfo implements Parcelable {
    /**
     * Flag for {@link #getInsertionMarkerFlags()} and {@link #getCharacterBoundsFlags(int)}: the
     * insertion marker or character bounds have at least one invisible (clipped) region.
     */
    public static final int FLAG_HAS_INVISIBLE_REGION = 2;

    /**
     * Flag for {@link #getInsertionMarkerFlags()} and {@link #getCharacterBoundsFlags(int)}: the
     * insertion marker or character bounds have at least one visible region.
     */
    public static final int FLAG_HAS_VISIBLE_REGION = 1;

    /**
     * Flag for {@link #getInsertionMarkerFlags()} and {@link #getCharacterBoundsFlags(int)}: the
     * insertion marker or character bounds is placed at right-to-left (RTL) character.
     */
    public static final int FLAG_IS_RTL = 4;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private CursorAnchorInfo() {
    }
}
