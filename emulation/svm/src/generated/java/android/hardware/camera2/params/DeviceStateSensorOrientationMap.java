package android.hardware.camera2.params;

/**
 * Immutable class that maps the device fold state to sensor orientation.
 *
 * <p>Some {@link CameraCharacteristics#REQUEST_AVAILABLE_CAPABILITIES_LOGICAL_MULTI_CAMERA logical}
 * cameras on foldables can include physical sensors with different sensor orientation
 * values. As a result, the values of the logical camera device can potentially change depending
 * on the device fold state.</p>
 *
 * <p>The device fold state to sensor orientation map will contain information about the
 * respective logical camera sensor orientation given a device state. Clients
 * can query the mapping for all possible supported folded states.
 *
 * @see CameraCharacteristics#SENSOR_ORIENTATION
 */
public final class DeviceStateSensorOrientationMap {
    /**
     * The device is folded.  If not set, the device is unfolded or does not
     * support folding.
     *
     * The exact point when this status change happens during the folding
     * operation is device-specific.
     */
    public static final long FOLDED = 4L;

    /**
     * The device is in its normal physical configuration. This is the default if the
     * device does not support multiple different states.
     */
    public static final long NORMAL = 0L;

    private DeviceStateSensorOrientationMap() {
    }
}
