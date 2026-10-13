package android.view.translation;

import android.os.Parcelable;

/**
 * A translated response value from translation service.
 */
public final class TranslationResponseValue implements Parcelable {
    /**
     * Name in the result of {@link #getExtras()} to pass dictionary definitions of the text
     * categorized by parts of speech.
     *
     * <p>The dictionary definitions consists of groups of terms keyed by their corresponding parts
     * of speech. This map-like structure is stored in a {@link Bundle}. The individual parts of
     * speech can be traversed by {@link Bundle#keySet()} and used to get the corresponding list
     * of terms as {@link CharSequence}s.
     *
     * <ul>
     *     <li>"noun" -> ["def1", "def2", ...]</li>
     *     <li>"verb" -> ["def3", "def4", ...]</li>
     *     <li>...</li>
     * </ul>
     *
     * The set of parts of speech can then be used by
     * {@link Bundle#getCharSequenceArrayList(String)} to get the list of terms.
     *
     * <b>Example</b>:
     *
     * {@code for (String partOfSpeech : extras.getBundle(EXTRA_DEFINITIONS).keySet()) {
     *    ArrayList<CharSequence> terms =
     *            extras.getBundle(EXTRA_DEFINITIONS).getCharSequenceArrayList(partOfSpeech);
     *    ...
     * }}</p>
     */
    public static final String EXTRA_DEFINITIONS = "android.view.translation.extra.DEFINITIONS";

    /**
     * This value was not successfully translated. No value can be obtained with {@link #getText()}.
     */
    public static final int STATUS_ERROR = 1;

    /**
     * This value was successfully translated.
     */
    public static final int STATUS_SUCCESS = 0;

    private TranslationResponseValue() {
    }
}
