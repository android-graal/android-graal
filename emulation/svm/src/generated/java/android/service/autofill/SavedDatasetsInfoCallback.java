package android.service.autofill;

/**
 * Handles the response to
 * {@link AutofillService#onSavedDatasetsInfoRequest(SavedDatasetsInfoCallback)}.
 * <p>
 * Use {@link #onSuccess(Set)} to return the computed info about the datasets the user saved to this
 * service. If there was an error querying the info, or if the service is unable to do so at this
 * time (for example, if the user isn't logged in), call {@link #onError(int)}.
 * <p>
 * This callback can be used only once.
 */
public interface SavedDatasetsInfoCallback {
    /**
     * The result cannot be computed until the user takes some action, such as setting up their
     * account.
     */
    int ERROR_NEEDS_USER_ACTION = 2;

    /**
     * The result could not be computed for any other reason.
     */
    int ERROR_OTHER = 0;

    /**
     * The service does not support this request.
     */
    int ERROR_UNSUPPORTED = 1;
}
