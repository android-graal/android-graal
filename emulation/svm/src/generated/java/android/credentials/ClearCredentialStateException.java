package android.credentials;

/**
 * Represents an error encountered during the
 * {@link CredentialManager#clearCredentialState(ClearCredentialStateRequest,
 * CancellationSignal, Executor, OutcomeReceiver)} operation.
 */
public final class ClearCredentialStateException {
    /**
     * The error type value for when the given operation failed due to an unknown reason.
     */
    public static final String TYPE_UNKNOWN = "android.credentials.ClearCredentialStateException.TYPE_UNKNOWN";

    private ClearCredentialStateException() {
    }
}
