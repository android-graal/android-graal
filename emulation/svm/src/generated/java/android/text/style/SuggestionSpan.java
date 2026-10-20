package android.text.style;

import android.text.ParcelableSpan;

/**
 * Holds suggestion candidates for the text enclosed in this span.
 *
 * When such a span is edited in an EditText, double tapping on the text enclosed in this span will
 * display a popup dialog listing suggestion replacement for that text. The user can then replace
 * the original text by one of the suggestions.
 *
 * These spans should typically be created by the input method to provide correction and alternates
 * for the text.
 *
 * @see TextView#isSuggestionsEnabled()
 */
public abstract class SuggestionSpan implements ParcelableSpan {
    /**
     * This action is deprecated in {@link android.os.Build.VERSION_CODES#Q}.
     *
     * @deprecated For IMEs to receive this kind of user interaction signals, implement IMEs' own
     *             suggestion picker UI instead of relying on {@link SuggestionSpan}. To retrieve
     *             bounding boxes for each character of the composing text, use
     *             {@link android.view.inputmethod.CursorAnchorInfo}.
     */
    @Deprecated
    public static final String ACTION_SUGGESTION_PICKED = "android.text.style.SUGGESTION_PICKED";

    /**
     * Sets this flag if the auto correction is about to be applied to a word/text
     * that the user is typing/composing. This type of suggestion is rendered differently
     * to indicate the auto correction is happening.
     */
    public static final int FLAG_AUTO_CORRECTION = 4;

    /**
     * Sets this flag if the suggestions should be easily accessible with few interactions.
     * This flag should be set for every suggestions that the user is likely to use.
     */
    public static final int FLAG_EASY_CORRECT = 1;

    /**
     * Sets this flag if the suggestions apply to a grammar error. This type of suggestion is
     * rendered differently to highlight the error.
     */
    public static final int FLAG_GRAMMAR_ERROR = 8;

    /**
     * Sets this flag if the suggestions apply to a misspelled word/text. This type of suggestion is
     * rendered differently to highlight the error.
     */
    public static final int FLAG_MISSPELLED = 2;

    public static final int SUGGESTIONS_MAX_SIZE = 5;

    /**
     * This is deprecated in {@link android.os.Build.VERSION_CODES#Q}.
     *
     * @deprecated See {@link #ACTION_SUGGESTION_PICKED}.
     */
    @Deprecated
    public static final String SUGGESTION_SPAN_PICKED_AFTER = "after";

    /**
     * This is deprecated in {@link android.os.Build.VERSION_CODES#Q}.
     *
     * @deprecated See {@link #ACTION_SUGGESTION_PICKED}.
     */
    @Deprecated
    public static final String SUGGESTION_SPAN_PICKED_BEFORE = "before";

    /**
     * This is deprecated in {@link android.os.Build.VERSION_CODES#Q}.
     *
     * @deprecated See {@link #ACTION_SUGGESTION_PICKED}.
     */
    @Deprecated
    public static final String SUGGESTION_SPAN_PICKED_HASHCODE = "hashcode";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSpanTypeId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SuggestionSpan() {
    }
}
