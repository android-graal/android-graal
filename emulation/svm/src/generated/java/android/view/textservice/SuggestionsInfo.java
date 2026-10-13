package android.view.textservice;

import android.os.Parcelable;

/**
 * This class contains a metadata of suggestions from the text service
 */
public final class SuggestionsInfo implements Parcelable {
    /**
     * Flag of the attributes of the suggestions that can be obtained by
     * {@link #getSuggestionsAttributes}: this tells that the text service has an alternative way to
     * show UI for the list of correction suggestions to the user. When this flag is set, the
     * receiver of the result suggestions should mark the erroneous part of the text with a text
     * signifier (for example, underline), but should not show any UI for the list of correction
     * suggestions to the user (for example, in a popup window).
     */
    public static final int RESULT_ATTR_DONT_SHOW_UI_FOR_SUGGESTIONS = 16;

    /**
     * Flag of the attributes of the suggestions that can be obtained by
     * {@link #getSuggestionsAttributes}: this tells that the text service thinks
     * the result suggestions include highly recommended ones.
     */
    public static final int RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS = 4;

    /**
     * Flag of the attributes of the suggestions that can be obtained by
     * {@link #getSuggestionsAttributes}: this tells that the requested word was found
     * in the dictionary in the text service.
     */
    public static final int RESULT_ATTR_IN_THE_DICTIONARY = 1;

    /**
     * Flag of the attributes of the suggestions that can be obtained by
     * {@link #getSuggestionsAttributes}: this tells that the text service thinks the requested
     * sentence contains a grammar error.
     */
    public static final int RESULT_ATTR_LOOKS_LIKE_GRAMMAR_ERROR = 8;

    /**
     * Flag of the attributes of the suggestions that can be obtained by
     * {@link #getSuggestionsAttributes}: this tells that the text service thinks the requested
     * word looks like a typo.
     */
    public static final int RESULT_ATTR_LOOKS_LIKE_TYPO = 2;

    private SuggestionsInfo() {
    }
}
