package android.view.translation;

import android.os.Parcelable;

/**
 * Translation request sent to the translation service by the
 * {@link android.view.translation.Translator} which contains the text to be translated.
 */
public final class TranslationRequest implements Parcelable {
    /**
     * Indicates this request wants to receive the dictionary result.
     *
     * <p>See {@link TranslationResponseValue#EXTRA_DEFINITIONS} for more detail on the structure
     * of the returned data.
     */
    public static final int FLAG_DICTIONARY_RESULT = 2;

    /**
     * Indicates this request is willing to accept partial responses.
     *
     * <p>The partial responses can be accessed by
     * {@link TranslationResponse#getTranslationResponseValues()} or
     * {@link TranslationResponse#getViewTranslationResponses()}. These responses will each contain
     * only a subset of the corresponding translated values.
     *
     * <p>The are no guarantees to the number of translated values or the order in which these
     * values are returned in the {@link TranslationResponse}.
     *
     * <p>This flag denotes the client can expect multiple partial responses, but there may not
     * necessarily be multiple responses.</p>
     */
    public static final int FLAG_PARTIAL_RESPONSES = 8;

    /**
     * Indicates this request wants to receive the standard translation result.
     */
    public static final int FLAG_TRANSLATION_RESULT = 1;

    /**
     * Indicates this request wants to receive the transliteration result.
     *
     * <p>This returns a CharSequence representation of the transliteration of the translated text.
     *  See {@link TranslationResponseValue#getTransliteration()}.
     */
    public static final int FLAG_TRANSLITERATION_RESULT = 4;

    private TranslationRequest() {
    }
}
