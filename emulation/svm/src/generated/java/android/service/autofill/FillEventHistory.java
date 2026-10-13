package android.service.autofill;

import android.os.Parcelable;

/**
 * Describes what happened after the last
 * {@link AutofillService#onFillRequest(FillRequest, android.os.CancellationSignal, FillCallback)}
 * call.
 *
 * <p>This history is typically used to keep track of previous user actions to optimize further
 * requests. For example, the service might return email addresses in alphabetical order by
 * default, but change that order based on the address the user picked on previous requests.
 *
 * <p>The history is not persisted over reboots, and it's cleared every time the service
 * replies to a
 * {@link AutofillService#onFillRequest(FillRequest, android.os.CancellationSignal, FillCallback)}
 * by calling {@link FillCallback#onSuccess(FillResponse)} or
 * {@link FillCallback#onFailure(CharSequence)} (if the service doesn't call any of these methods,
 * the history will clear out after some pre-defined time).
 */
public final class FillEventHistory implements Parcelable {
    private FillEventHistory() {
    }

    /**
     * Description of an event that occurred after the latest call to
     * {@link FillCallback#onSuccess(FillResponse)}.
     */
    public static final class Event {
        /**
         * All fields matched contents of datasets.
         */
        public static final int NO_SAVE_UI_REASON_DATASET_MATCH = 6;

        /**
         * Fields failed validation.
         */
        public static final int NO_SAVE_UI_REASON_FIELD_VALIDATION_FAILED = 5;

        /**
         * There was empty value for required ids.
         */
        public static final int NO_SAVE_UI_REASON_HAS_EMPTY_REQUIRED = 3;

        /**
         * No reason for save dialog.
         */
        public static final int NO_SAVE_UI_REASON_NONE = 0;

        /**
         * The SaveInfo associated with the FillResponse is null.
         */
        public static final int NO_SAVE_UI_REASON_NO_SAVE_INFO = 1;

        /**
         * No value has been changed.
         */
        public static final int NO_SAVE_UI_REASON_NO_VALUE_CHANGED = 4;

        /**
         * Credential Manager is invoked instead of Autofill. When that happens, Save Dialog cannot
         * be shown, and this will be populated in
         */
        public static final int NO_SAVE_UI_REASON_USING_CREDMAN = 7;

        /**
         * The service asked to delay save.
         */
        public static final int NO_SAVE_UI_REASON_WITH_DELAY_SAVE_FLAG = 2;

        /**
         * A {@link FillResponse.Builder#setAuthentication(android.view.autofill.AutofillId[],
         * IntentSender, android.widget.RemoteViews) fill response authentication} was selected.
         */
        public static final int TYPE_AUTHENTICATION_SELECTED = 2;

        /**
         * A committed autofill context for which the autofill service provided datasets.
         *
         * <p>This event is useful to track:
         * <ul>
         *   <li>Which datasets (if any) were selected by the user
         *       ({@link #getSelectedDatasetIds()}).
         *   <li>Which datasets (if any) were NOT selected by the user
         *       ({@link #getIgnoredDatasetIds()}).
         *   <li>Which fields in the selected datasets were changed by the user after the dataset
         *       was selected ({@link #getChangedFields()}.
         *   <li>Which fields match the {@link UserData} set by the service.
         * </ul>
         *
         * <p><b>Note: </b>This event is only generated when:
         * <ul>
         *   <li>The autofill context is committed.
         *   <li>The service provides at least one dataset in the
         *       {@link FillResponse fill responses} associated with the context.
         *   <li>The last {@link FillResponse fill responses} associated with the context has the
         *       {@link FillResponse#FLAG_TRACK_CONTEXT_COMMITED} flag.
         * </ul>
         *
         * <p>See {@link android.view.autofill.AutofillManager} for more information about autofill
         * contexts.
         */
        public static final int TYPE_CONTEXT_COMMITTED = 4;

        /**
         * A dataset selector was shown.
         *
         * <p>This event is fired whenever the autofill UI was presented to the user.</p>
         */
        public static final int TYPE_DATASETS_SHOWN = 5;

        /**
         * A {@link Dataset.Builder#setAuthentication(IntentSender) dataset authentication} was
         * selected. The dataset authenticated can be read from {@link #getDatasetId()}.
         */
        public static final int TYPE_DATASET_AUTHENTICATION_SELECTED = 1;

        /**
         * A dataset was selected. The dataset selected can be read from {@link #getDatasetId()}.
         *
         * <p><b>Note: </b>on Android {@link android.os.Build.VERSION_CODES#O}, this event was also
         * incorrectly reported after a
         * {@link Dataset.Builder#setAuthentication(IntentSender) dataset authentication} was
         * selected and the service returned a dataset in the
         * {@link AutofillManager#EXTRA_AUTHENTICATION_RESULT} of the activity launched from that
         * {@link IntentSender}. This behavior was fixed on Android
         * {@link android.os.Build.VERSION_CODES#O_MR1}.
         */
        public static final int TYPE_DATASET_SELECTED = 0;

        /**
         * A save UI was shown.
         */
        public static final int TYPE_SAVE_SHOWN = 3;

        /**
         * The app/user requested for a field to be Autofilled.
         *
         * This event is fired when the view has been entered (by user or app) in order
         * to differentiate from FillRequests that have been pretriggered for FillDialogs.
         *
         * For example, the user might navigate away from a screen without tapping any
         * fields. In this case, a FillRequest/FillResponse has been generated, but was
         * not used for Autofilling. The user did not intend to see an Autofill result,
         * but a FillRequest was still generated. This is different from when the user
         * did tap on a field after the pretriggered FillRequest, this event will appear
         * in the FillEventHistory, signaling that the user did intend to Autofill
         * something.
         */
        public static final int TYPE_VIEW_REQUESTED_AUTOFILL = 6;

        /**
         * Credential Manager suggestions are shown instead of Autofill suggestion
         */
        public static final int UI_TYPE_CREDENTIAL_MANAGER = 4;

        /**
         * The autofill suggestion is shown as a dialog presentation.
         */
        public static final int UI_TYPE_DIALOG = 3;

        /**
         * The autofill suggestion is shown as a keyboard inline presentation.
         */
        public static final int UI_TYPE_INLINE = 2;

        /**
         * The autofill suggestion is shown as a menu popup presentation.
         */
        public static final int UI_TYPE_MENU = 1;

        /**
         * The autofill suggestion presentation is unknown, this will be set for the event
         *  that is unrelated to fill Ui presentation
         */
        public static final int UI_TYPE_UNKNOWN = 0;

        private Event() {
        }
    }
}
