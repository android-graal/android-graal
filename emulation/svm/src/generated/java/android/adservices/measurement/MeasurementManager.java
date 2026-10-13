package android.adservices.measurement;

/**
 * MeasurementManager provides APIs to manage source and trigger registrations.
 */
public final class MeasurementManager {
    /**
     * This state indicates that Measurement APIs are unavailable. Invoking them will result in an
     * {@link UnsupportedOperationException}.
     */
    public static final int MEASUREMENT_API_STATE_DISABLED = 0;

    /**
     * This state indicates that Measurement APIs are enabled.
     */
    public static final int MEASUREMENT_API_STATE_ENABLED = 1;

    private MeasurementManager() {
    }
}
