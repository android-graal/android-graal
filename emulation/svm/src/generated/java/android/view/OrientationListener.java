package android.view;

import android.hardware.SensorListener;

/**
 * Helper class for receiving notifications from the SensorManager when
 * the orientation of the device has changed.
 *  @deprecated use {@link android.view.OrientationEventListener} instead.
 *  This class internally uses the OrientationEventListener.
 */
@Deprecated
public final class OrientationListener implements SensorListener {
    /**
     * Returned from onOrientationChanged when the device orientation cannot be determined
     * (typically when the device is in a close to flat position).
     *
     *  @see #onOrientationChanged
     */
    @Deprecated
    public static final int ORIENTATION_UNKNOWN = -1;

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        throw new UnsupportedOperationException();
    }

    private OrientationListener() {
    }
}
