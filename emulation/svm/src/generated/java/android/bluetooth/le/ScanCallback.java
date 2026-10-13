package android.bluetooth.le;

/**
 * Bluetooth LE scan callbacks. Scan results are reported using these callbacks.
 *
 * @see BluetoothLeScanner#startScan
 */
public final class ScanCallback {
    /**
     * Fails to start scan as BLE scan with the same settings is already started by the app.
     */
    public static final int SCAN_FAILED_ALREADY_STARTED = 1;

    /**
     * Fails to start scan as app cannot be registered.
     */
    public static final int SCAN_FAILED_APPLICATION_REGISTRATION_FAILED = 2;

    /**
     * Fails to start power optimized scan as this feature is not supported.
     */
    public static final int SCAN_FAILED_FEATURE_UNSUPPORTED = 4;

    /**
     * Fails to start scan due an internal error
     */
    public static final int SCAN_FAILED_INTERNAL_ERROR = 3;

    /**
     * Fails to start scan as it is out of hardware resources.
     */
    public static final int SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES = 5;

    /**
     * Fails to start scan as application tries to scan too frequently.
     */
    public static final int SCAN_FAILED_SCANNING_TOO_FREQUENTLY = 6;

    private ScanCallback() {
    }
}
