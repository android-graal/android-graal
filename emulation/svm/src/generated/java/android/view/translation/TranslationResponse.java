package android.view.translation;

import android.os.Parcelable;

/**
 * Response from the translation service, which contains the translated result.
 */
public final class TranslationResponse implements Parcelable {
    /**
     * The languages of the request is not available to be translated.
     */
    public static final int TRANSLATION_STATUS_CONTEXT_UNSUPPORTED = 2;

    /**
     * The translation service was successful in translating.
     */
    public static final int TRANSLATION_STATUS_SUCCESS = 0;

    /**
     * The translation service returned unknown translation result.
     */
    public static final int TRANSLATION_STATUS_UNKNOWN_ERROR = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TranslationResponse() {
    }
}
