package android.view.autofill;

/**
 * <p>The {@link AutofillManager} class provides ways for apps and custom views to
 * integrate with the Autofill Framework lifecycle.
 *
 * <p>To learn about using Autofill in your app, read
 * the <a href="/guide/topics/text/autofill">Autofill Framework</a> guides.
 *
 * <h3 id="autofill-lifecycle">Autofill lifecycle</h3>
 *
 * <p>The autofill lifecycle starts with the creation of an autofill context associated with an
 * activity context. The autofill context is created when one of the following methods is called for
 * the first time in an activity context, and the current user has an enabled autofill service:
 *
 * <ul>
 *   <li>{@link #notifyViewEntered(View)}
 *   <li>{@link #notifyViewEntered(View, int, Rect)}
 *   <li>{@link #requestAutofill(View)}
 * </ul>
 *
 * <p>Typically, the context is automatically created when the first view of the activity is
 * focused because {@code View.onFocusChanged()} indirectly calls
 * {@link #notifyViewEntered(View)}. App developers can call {@link #requestAutofill(View)} to
 * explicitly create it (for example, a custom view developer could offer a contextual menu action
 * in a text-field view to let users manually request autofill).
 *
 * <p>After the context is created, the Android System creates a {@link android.view.ViewStructure}
 * that represents the view hierarchy by calling
 * {@link View#dispatchProvideAutofillStructure(android.view.ViewStructure, int)} in the root views
 * of all application windows. By default, {@code dispatchProvideAutofillStructure()} results in
 * subsequent calls to {@link View#onProvideAutofillStructure(android.view.ViewStructure, int)} and
 * {@link View#onProvideAutofillVirtualStructure(android.view.ViewStructure, int)} for each view in
 * the hierarchy.
 *
 * <p>The resulting {@link android.view.ViewStructure} is then passed to the autofill service, which
 * parses it looking for views that can be autofilled. If the service finds such views, it returns
 * a data structure to the Android System containing the following optional info:
 *
 * <ul>
 *   <li>Datasets used to autofill subsets of views in the activity.
 *   <li>Id of views that the service can save their values for future autofilling.
 * </ul>
 *
 * <p>When the service returns datasets, the Android System displays an autofill dataset picker
 * UI associated with the view, when the view is focused on and is part of a dataset.
 * The application can be notified when the UI is shown by registering an
 * {@link AutofillCallback} through {@link #registerCallback(AutofillCallback)}. When the user
 * selects a dataset from the UI, all views present in the dataset are autofilled, through
 * calls to {@link View#autofill(AutofillValue)} or {@link View#autofill(SparseArray)}.
 *
 * <p>When the service returns ids of savable views, the Android System keeps track of changes
 * made to these views, so they can be used to determine if the autofill save UI is shown later.
 *
 * <p>The context is then finished when one of the following occurs:
 *
 * <ul>
 *   <li>{@link #commit()} is called or all savable views are gone.
 *   <li>{@link #cancel()} is called.
 * </ul>
 *
 * <p>Finally, after the autofill context is commited (i.e., not cancelled), the Android System
 * shows an autofill save UI if the value of savable views have changed. If the user selects the
 * option to Save, the current value of the views is then sent to the autofill service.
 *
 * <h3 id="additional-notes">Additional notes</h3>
 *
 * <p>It is safe to call <code>AutofillManager</code> methods from any thread.
 */
public final class AutofillManager {
    /**
     * Intent extra: The assist structure which captures the filled screen.
     *
     * <p>
     * Type: {@link android.app.assist.AssistStructure}
     */
    public static final String EXTRA_ASSIST_STRUCTURE = "android.view.autofill.extra.ASSIST_STRUCTURE";

    /**
     * Intent extra: The result of an authentication operation. It is
     * either a fully populated {@link android.service.autofill.FillResponse}
     * or a fully populated {@link android.service.autofill.Dataset} if
     * a response or a dataset is being authenticated respectively.
     *
     * <p>
     * Type: {@link android.service.autofill.FillResponse} or a
     * {@link android.service.autofill.Dataset}
     */
    public static final String EXTRA_AUTHENTICATION_RESULT = "android.view.autofill.extra.AUTHENTICATION_RESULT";

    /**
     * Intent extra: The optional boolean extra field provided by the
     * {@link android.service.autofill.AutofillService} accompanying the {@link
     * android.service.autofill.Dataset} result of an authentication operation.
     *
     * <p> Before {@link android.os.Build.VERSION_CODES#R}, if the authentication result is a
     * {@link android.service.autofill.Dataset}, it'll be used to autofill the fields, and also
     * replace the existing dataset in the cached {@link android.service.autofill.FillResponse}.
     * That means if the user clears the field values, the autofill suggestion will show up again
     * with the new authenticated Dataset.
     *
     * <p> In {@link android.os.Build.VERSION_CODES#R}, we added an exception to this behavior
     * that if the Dataset being authenticated is a pinned dataset (see
     * {@link android.service.autofill.InlinePresentation#isPinned()}), the old Dataset will not be
     * replaced.
     *
     * <p> In {@link android.os.Build.VERSION_CODES#S}, we added this boolean extra field to
     * allow the {@link android.service.autofill.AutofillService} to explicitly specify whether
     * the returned authenticated Dataset is ephemeral. An ephemeral Dataset will be used to
     * autofill once and then thrown away. Therefore, when the boolean extra is set to true, the
     * returned Dataset will not replace the old dataset from the existing
     * {@link android.service.autofill.FillResponse}. When it's set to false, it will. When it's not
     * set, the old dataset will be replaced, unless it is a pinned inline suggestion, which is
     * consistent with the behavior in {@link android.os.Build.VERSION_CODES#R}.
     */
    public static final String EXTRA_AUTHENTICATION_RESULT_EPHEMERAL_DATASET = "android.view.autofill.extra.AUTHENTICATION_RESULT_EPHEMERAL_DATASET";

    /**
     * Intent extra: The optional extras provided by the
     * {@link android.service.autofill.AutofillService}.
     *
     * <p>For example, when the service responds to a {@link
     * android.service.autofill.FillCallback#onSuccess(android.service.autofill.FillResponse)} with
     * a {@code FillResponse} that requires authentication, the Intent that launches the
     * service authentication will contain the Bundle set by
     * {@link android.service.autofill.FillResponse.Builder#setClientState(Bundle)} on this extra.
     *
     * <p>On Android {@link android.os.Build.VERSION_CODES#P} and higher, the autofill service
     * can also add this bundle to the {@link Intent} set as the
     * {@link android.app.Activity#setResult(int, Intent) result} for an authentication request,
     * so the bundle can be recovered later on
     * {@link android.service.autofill.SaveRequest#getClientState()}.
     *
     * <p>
     * Type: {@link android.os.Bundle}
     */
    public static final String EXTRA_CLIENT_STATE = "android.view.autofill.extra.CLIENT_STATE";

    /**
     * Intent extra: the {@link android.view.inputmethod.InlineSuggestionsRequest} in the
     * autofill request.
     *
     * <p>This is filled in the authentication intent so the
     * {@link android.service.autofill.AutofillService} can use it to create the inline
     * suggestion {@link android.service.autofill.Dataset} in the response, if the original autofill
     * request contains the {@link android.view.inputmethod.InlineSuggestionsRequest}.
     */
    public static final String EXTRA_INLINE_SUGGESTIONS_REQUEST = "android.view.autofill.extra.INLINE_SUGGESTIONS_REQUEST";

    private AutofillManager() {
    }

    /**
     * Callback for autofill related events.
     *
     * <p>Typically used for applications that display their own "auto-complete" views, so they can
     * enable / disable such views when the autofill UI is shown / hidden.
     */
    public static final class AutofillCallback {
        /**
         * The autofill input UI associated with the view was hidden.
         *
         * <p>If the view provides its own auto-complete UI that was hidden upon a
         * {@link #EVENT_INPUT_SHOWN} event, it could be shown again now.
         */
        public static final int EVENT_INPUT_HIDDEN = 2;

        /**
         * The autofill input UI associated with the view was shown.
         *
         * <p>If the view provides its own auto-complete UI and its currently shown, it
         * should be hidden upon receiving this event.
         */
        public static final int EVENT_INPUT_SHOWN = 1;

        /**
         * The autofill input UI associated with the view isn't shown because
         * autofill is not available.
         *
         * <p>If the view provides its own auto-complete UI but was not displaying it
         * to avoid flickering, it could shown it upon receiving this event.
         */
        public static final int EVENT_INPUT_UNAVAILABLE = 3;

        private AutofillCallback() {
        }
    }
}
