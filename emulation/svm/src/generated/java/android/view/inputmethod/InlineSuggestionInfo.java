package android.view.inputmethod;

import android.os.Parcelable;

/**
 * This class represents the description of an inline suggestion. It contains information to help
 * the IME to decide where and when to show the suggestions. See {@link InlineSuggestion} for more
 * information.
 */
public final class InlineSuggestionInfo implements Parcelable {
    /**
     * Suggestion source: the suggestion is made by the user selected autofill service.
     */
    public static final String SOURCE_AUTOFILL = "android:autofill";

    /**
     * Suggestion source: the suggestion is made by the platform.
     */
    public static final String SOURCE_PLATFORM = "android:platform";

    /**
     * UI type: the UI contains an widget that will launch an intent when tapped.
     */
    public static final String TYPE_ACTION = "android:autofill:action";

    /**
     * UI type: the UI contains an Autofill suggestion that will autofill the fields when tapped.
     */
    public static final String TYPE_SUGGESTION = "android:autofill:suggestion";

    private InlineSuggestionInfo() {
    }
}
