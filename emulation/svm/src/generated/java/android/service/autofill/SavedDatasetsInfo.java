package android.service.autofill;

/**
 * A result returned from
 * {@link AutofillService#onSavedDatasetsInfoRequest(SavedDatasetsInfoCallback)}.
 */
public final class SavedDatasetsInfo {
    /**
     * Any other type of datasets.
     */
    public static final String TYPE_OTHER = "other";

    /**
     * Datasets such as login credentials.
     */
    public static final String TYPE_PASSWORDS = "passwords";

    private SavedDatasetsInfo() {
    }
}
