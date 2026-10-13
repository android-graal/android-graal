package android.adservices.ondevicepersonalization;

/**
 * The request of {@link OnDevicePersonalizationManager#executeInIsolatedService}.
 */
public final class ExecuteInIsolatedServiceRequest {
    private ExecuteInIsolatedServiceRequest() {
    }

    /**
     * The set of spec to indicate output of {@link IsolatedService}. It's mainly used by platform.
     * If {@link OutputSpec} is set to {@link OutputSpec#DEFAULT}, OnDevicePersonalization will
     * ignore result returned by {@link IsolatedService}. If {@link OutputSpec} is built with {@link
     * OutputSpec#buildBestValueSpec}, OnDevicePersonalization will verify {@link
     * ExecuteOutput#getBestValue()} returned by {@link IsolatedService} within the max value range
     * set in {@link OutputSpec#getMaxIntValue} and add noise.
     */
    public static final class OutputSpec {
        /**
         * If set, {@link ExecuteInIsolatedServiceResponse#getBestValue()} will return an integer
         * that indicates the index of best values passed in {@link
         * ExecuteInIsolatedServiceRequest#getAppParams}.
         */
        public static final int OUTPUT_TYPE_BEST_VALUE = 1;

        /**
         * The default value of OutputType. If set, OnDevicePersonalization will ignore result
         * returned by {@link IsolatedService} and {@link ExecuteInIsolatedServiceResponse} doesn't
         * return any output data.
         */
        public static final int OUTPUT_TYPE_NULL = 0;

        private OutputSpec() {
        }
    }
}
