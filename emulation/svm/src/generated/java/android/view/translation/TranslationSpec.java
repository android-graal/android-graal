package android.view.translation;

import android.os.Parcelable;

/**
 * Specs and additional info for the translation data.
 *
 * <p>This spec help specify information such as the language/locale for the translation, as well
 * as the data format for the translation (text, audio, etc.)</p>
 */
public final class TranslationSpec implements Parcelable {
    /**
     * Data format for translation is text.
     */
    public static final int DATA_FORMAT_TEXT = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TranslationSpec() {
    }
}
