package android.view;

/**
 * Helper class for receiving notifications from the SensorManager when
 * the orientation of the device has changed.
 */
public final class OrientationEventListener {
    /**
     * Returned from onOrientationChanged when the device orientation cannot be determined
     * (typically when the device is in a close to flat position).
     *
     *  @see #onOrientationChanged
     */
    public static final int ORIENTATION_UNKNOWN = -1;

    private OrientationEventListener() {
    }
}
