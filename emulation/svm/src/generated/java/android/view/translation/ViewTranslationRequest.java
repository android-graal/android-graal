package android.view.translation;

import android.os.Parcelable;

/**
 * Wrapper class representing a translation request associated with a {@link android.view.View} to
 * be used by translation service.
 */
public final class ViewTranslationRequest implements Parcelable {
    /**
     * Constant id for the default view text to be translated. This is used by
     * {@link Builder#setValue(String, TranslationRequestValue)}.
     */
    public static final String ID_TEXT = "android:text";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ViewTranslationRequest() {
    }
}
