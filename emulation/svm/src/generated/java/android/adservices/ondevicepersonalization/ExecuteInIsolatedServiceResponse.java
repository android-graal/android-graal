package android.adservices.ondevicepersonalization;

/**
 * The response of {@link OnDevicePersonalizationManager#executeInIsolatedService}.
 */
public final class ExecuteInIsolatedServiceResponse {
    /**
     * The default value of {@link ExecuteInIsolatedServiceResponse#getBestValue} if {@link
     * IsolatedService} didn't return any content.
     */
    public static final int DEFAULT_BEST_VALUE = -1;

    private ExecuteInIsolatedServiceResponse() {
    }
}
