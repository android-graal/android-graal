package android.ranging;

import android.os.Parcelable;

/**
 * Represents a ranging measurement.
 *
 * <p>This class provides a measurement result, such as a distance or angle.
 */
public final class RangingMeasurement implements Parcelable {
    /**
     * Ranging measurement with high confidence.
     */
    public static final int CONFIDENCE_HIGH = 2;

    /**
     * Ranging measurement with low confidence.
     */
    public static final int CONFIDENCE_LOW = 0;

    /**
     * Ranging measurement with medium confidence.
     */
    public static final int CONFIDENCE_MEDIUM = 1;

    private RangingMeasurement() {
    }
}
