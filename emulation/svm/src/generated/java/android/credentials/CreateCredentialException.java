package android.credentials;

/**
 * Represents an error encountered during the
 * {@link CredentialManager#createCredential(Context, CreateCredentialRequest,
 * CancellationSignal, Executor, OutcomeReceiver)} operation.
 */
public final class CreateCredentialException {
    /**
     * The error type value for when the given operation failed due to internal interruption.
     * Retrying the same operation should fix the error.
     */
    public static final String TYPE_INTERRUPTED = "android.credentials.CreateCredentialException.TYPE_INTERRUPTED";

    /**
     * The error type value for when no create options are available from any provider(s),
     * for the given {@link CredentialManager#createCredential(Context, CreateCredentialRequest,
     * CancellationSignal, Executor, OutcomeReceiver)} request.
     */
    public static final String TYPE_NO_CREATE_OPTIONS = "android.credentials.CreateCredentialException.TYPE_NO_CREATE_OPTIONS";

    /**
     * The error type value for when the given operation failed due to an unknown reason.
     */
    public static final String TYPE_UNKNOWN = "android.credentials.CreateCredentialException.TYPE_UNKNOWN";

    /**
     * The error type value for when the user intentionally cancelled the request.
     *
     * <p>This is a strong indicator that your app should refrain from making the same api call for
     * a certain amount of time to provide a better user experience.
     */
    public static final String TYPE_USER_CANCELED = "android.credentials.CreateCredentialException.TYPE_USER_CANCELED";

    private CreateCredentialException() {
    }
}
