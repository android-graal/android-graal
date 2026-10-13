package android.health.connect.datatypes;

/**
 * Represents intensity of an activity.
 *
 * <p>Intensity can be either moderate or vigorous.
 *
 * <p>Each record requires the start time, the end time and the activity intensity type.
 */
public final class ActivityIntensityRecord {
    /**
     * Moderate activity intensity.
     */
    public static final int ACTIVITY_INTENSITY_TYPE_MODERATE = 0;

    /**
     * Vigorous activity intensity.
     */
    public static final int ACTIVITY_INTENSITY_TYPE_VIGOROUS = 1;

    private ActivityIntensityRecord() {
    }
}
