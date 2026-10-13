package android.health.connect.datatypes;

/**
 * Captures the skin temperature of a user. Each record can represent a series of measurements of
 * temperature differences.
 */
public final class SkinTemperatureRecord {
    /**
     * Skin temperature measurement location finger.
     */
    public static final int MEASUREMENT_LOCATION_FINGER = 1;

    /**
     * Skin temperature measurement location toe.
     */
    public static final int MEASUREMENT_LOCATION_TOE = 2;

    /**
     * Skin temperature measurement location unknown.
     */
    public static final int MEASUREMENT_LOCATION_UNKNOWN = 0;

    /**
     * Skin temperature measurement location wrist.
     */
    public static final int MEASUREMENT_LOCATION_WRIST = 3;

    private SkinTemperatureRecord() {
    }
}
