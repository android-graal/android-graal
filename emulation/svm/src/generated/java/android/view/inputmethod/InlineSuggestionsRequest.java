package android.view.inputmethod;

import android.os.Parcelable;

/**
 * This class represents an inline suggestion request made by one app to get suggestions from the
 * other source. See {@link InlineSuggestion} for more information.
 */
public final class InlineSuggestionsRequest implements Parcelable {
    /**
     * Constant used to indicate not putting a cap on the number of suggestions to return.
     */
    public static final int SUGGESTION_COUNT_UNLIMITED = 2147483647;

    private InlineSuggestionsRequest() {
    }
}
