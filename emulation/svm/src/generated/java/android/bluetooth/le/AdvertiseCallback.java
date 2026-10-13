package android.bluetooth.le;

/**
 * Bluetooth LE advertising callbacks, used to deliver advertising operation status.
 */
public final class AdvertiseCallback {
    /**
     * Failed to start advertising as the advertising is already started.
     */
    public static final int ADVERTISE_FAILED_ALREADY_STARTED = 3;

    /**
     * Failed to start advertising as the advertise data to be broadcasted is larger than 31 bytes.
     */
    public static final int ADVERTISE_FAILED_DATA_TOO_LARGE = 1;

    /**
     * This feature is not supported on this platform.
     */
    public static final int ADVERTISE_FAILED_FEATURE_UNSUPPORTED = 5;

    /**
     * Operation failed due to an internal error.
     */
    public static final int ADVERTISE_FAILED_INTERNAL_ERROR = 4;

    /**
     * Failed to start advertising because no advertising instance is available.
     */
    public static final int ADVERTISE_FAILED_TOO_MANY_ADVERTISERS = 2;

    private AdvertiseCallback() {
    }
}
