package android.view.translation;

import android.os.Parcelable;

/**
 * Info class holding information for {@link Translator}s and used by
 * {@link TranslationManager#createOnDeviceTranslator(TranslationContext, Executor, Consumer)}.
 */
public final class TranslationContext implements Parcelable {
    /**
     * This context will enable the {@link Translator} to return dictionary definitions.
     */
    public static final int FLAG_DEFINITIONS = 4;

    /**
     * This context will perform translations in low latency mode.
     */
    public static final int FLAG_LOW_LATENCY = 1;

    /**
     * This context will enable the {@link Translator} to return transliteration results.
     */
    public static final int FLAG_TRANSLITERATION = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TranslationContext() {
    }
}
