package android.view.textclassifier;

/**
 * Interface for providing text classification related features.
 * <p>
 * The TextClassifier may be used to understand the meaning of text, as well as generating predicted
 * next actions based on the text.
 *
 * <p><strong>NOTE: </strong>Unless otherwise stated, methods of this interface are blocking
 * operations. Call on a worker thread.
 */
public interface TextClassifier {
    /**
     * Extra that is included on activity intents coming from a TextClassifier when
     * it suggests actions to its caller.
     * <p>
     * All {@link TextClassifier} implementations should make sure this extra exists in their
     * generated intents.
     */
    String EXTRA_FROM_TEXT_CLASSIFIER = "android.view.textclassifier.extra.FROM_TEXT_CLASSIFIER";

    /**
     * Extra specifying the package name of the app from which the text to be classified originated.
     *
     * For example, a notification assistant might use TextClassifier, but the notification
     * content could originate from a different app. This key allows you to provide
     * the package name of that source app.
     */
    String EXTRA_TEXT_ORIGIN_PACKAGE = "android.view.textclassifier.extra.TEXT_ORIGIN_PACKAGE";

    /**
     * Designates that the text in question is editable.
     */
    String HINT_TEXT_IS_EDITABLE = "android.text_is_editable";

    /**
     * Designates that the text in question is not editable.
     */
    String HINT_TEXT_IS_NOT_EDITABLE = "android.text_is_not_editable";

    /**
     * Physical address.
     */
    String TYPE_ADDRESS = "address";

    /**
     * Time reference that is no more specific than a date. May be absolute such as "01/01/2000" or
     *  relative like "tomorrow".
     */
    String TYPE_DATE = "date";

    /**
     * Time reference that includes a specific time. May be absolute such as "01/01/2000 5:30pm" or
     *  relative like "tomorrow at 5:30pm".
     */
    String TYPE_DATE_TIME = "datetime";

    /**
     * E-mail address (e.g. "noreply@android.com").
     */
    String TYPE_EMAIL = "email";

    /**
     * Flight number in IATA format.
     */
    String TYPE_FLIGHT_NUMBER = "flight";

    /**
     * The classifier ran, but didn't recognize a known entity.
     */
    String TYPE_OTHER = "other";

    /**
     * Onetime password.
     */
    String TYPE_OTP = "otp";

    /**
     * Phone number (e.g. "555-123 456").
     */
    String TYPE_PHONE = "phone";

    /**
     * The TextClassifier failed to run.
     */
    String TYPE_UNKNOWN = "";

    /**
     * Web URL.
     */
    String TYPE_URL = "url";

    /**
     * The text classification context is for use with the system clipboard.
     */
    String WIDGET_TYPE_CLIPBOARD = "clipboard";

    /**
     * The widget involved in the text classification context is a custom editable text widget.
     */
    String WIDGET_TYPE_CUSTOM_EDITTEXT = "customedit";

    /**
     * The widget involved in the text classification context is a custom text widget.
     */
    String WIDGET_TYPE_CUSTOM_TEXTVIEW = "customview";

    /**
     * The widget involved in the text classification context is a custom non-selectable text
     *  widget.
     */
    String WIDGET_TYPE_CUSTOM_UNSELECTABLE_TEXTVIEW = "nosel-customview";

    /**
     * The widget involved in the text classification context is a standard
     *  {@link android.widget.EditText}.
     */
    String WIDGET_TYPE_EDITTEXT = "edittext";

    /**
     * The widget involved in the text classification context is a standard editable
     *  {@link android.webkit.WebView}.
     */
    String WIDGET_TYPE_EDIT_WEBVIEW = "edit-webview";

    /**
     * The widget involved in the text classification context is a notification
     */
    String WIDGET_TYPE_NOTIFICATION = "notification";

    /**
     * The widget involved in the text classification context is a standard
     *  {@link android.widget.TextView}.
     */
    String WIDGET_TYPE_TEXTVIEW = "textview";

    /**
     * The widget involved in the text classification context is of an unknown/unspecified type.
     */
    String WIDGET_TYPE_UNKNOWN = "unknown";

    /**
     * The widget involved in the text classification context is a standard non-selectable
     *  {@link android.widget.TextView}.
     */
    String WIDGET_TYPE_UNSELECTABLE_TEXTVIEW = "nosel-textview";

    /**
     * The widget involved in the text classification context is a standard
     *  {@link android.webkit.WebView}.
     */
    String WIDGET_TYPE_WEBVIEW = "webview";
}
