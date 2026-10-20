package android.service.autofill;

import android.os.Parcelable;

/**
 * This class represents a request to an autofill service
 * to interpret the screen and provide information to the system which views are
 * interesting for saving and what are the possible ways to fill the inputs on
 * the screen if applicable.
 *
 * @see AutofillService#onFillRequest(FillRequest, android.os.CancellationSignal, FillCallback)
 */
public final class FillRequest implements Parcelable {
    /**
     * Indicates this request was made using
     * <a href="AutofillService.html#CompatibilityMode">compatibility mode</a>.
     */
    public static final int FLAG_COMPATIBILITY_MODE_REQUEST = 2;

    /**
     * Indicates autofill was explicitly requested by the user.
     *
     * <p>Users typically make an explicit request to autofill a screen in two situations:
     * <ul>
     *   <li>The app disabled autofill (using {@link View#setImportantForAutofill(int)}.
     *   <li>The service could not figure out how to autofill a screen (but the user knows the
     *       service has data for that app).
     * </ul>
     *
     * <p>This flag is particularly useful for the second case. For example, the service could offer
     * a complex UI where the user can map which screen views belong to each user data, or it could
     * offer a simpler UI where the user picks the data for just the view used to trigger the
     * request (that would be the view whose
     * {@link android.app.assist.AssistStructure.ViewNode#isFocused()} method returns {@code true}).
     *
     * <p>An explicit autofill request is triggered when the
     * {@link android.view.autofill.AutofillManager#requestAutofill(View)} or
     * {@link android.view.autofill.AutofillManager#requestAutofill(View, int, android.graphics.Rect)}
     * is called. For example, standard {@link android.widget.TextView} views show an
     * {@code AUTOFILL} option in the overflow menu that triggers such request.
     */
    public static final int FLAG_MANUAL_REQUEST = 1;

    /**
     * Indicates the request supports fill dialog presentation for the fields, the
     * system will send the request when the activity just started.
     *
     * @deprecated All requests would support fill dialog by default.
     * Presence of this flag isn't needed.
     */
    @Deprecated
    public static final int FLAG_SUPPORTS_FILL_DIALOG = 64;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private FillRequest() {
    }
}
