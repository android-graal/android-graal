package android.view;

/**
 * Helper class for receiving notifications from the SensorManager when
 * the orientation of the device has changed.
 *  @deprecated use {@link android.view.OrientationEventListener} instead.
 *  This class internally uses the OrientationEventListener.
 */
@Deprecated
public final class OrientationListener {
    /**
     * Returned from onOrientationChanged when the device orientation cannot be determined
     * (typically when the device is in a close to flat position).
     *
     *  @see #onOrientationChanged
     */
    @Deprecated
    public static final int ORIENTATION_UNKNOWN = -1;

    private OrientationListener() {
    }
}
