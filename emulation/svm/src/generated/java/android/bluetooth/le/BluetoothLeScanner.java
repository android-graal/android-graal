package android.bluetooth.le;

/**
 * This class provides methods to perform scan related operations for Bluetooth LE devices. An
 * application can scan for a particular type of Bluetooth LE devices using {@link ScanFilter}. It
 * can also request different types of callbacks for delivering the result.
 *
 * <p>Use {@link BluetoothAdapter#getBluetoothLeScanner()} to get an instance of {@link
 * BluetoothLeScanner}.
 *
 * @see ScanFilter
 */
public final class BluetoothLeScanner {
    /**
     * Optional extra indicating the callback type, which will be one of CALLBACK_TYPE_* constants
     * in {@link ScanSettings}.
     *
     * @see ScanCallback#onScanResult(int, ScanResult)
     */
    public static final String EXTRA_CALLBACK_TYPE = "android.bluetooth.le.extra.CALLBACK_TYPE";

    /**
     * Optional extra indicating the error code, if any. The error code will be one of the
     * SCAN_FAILED_* codes in {@link ScanCallback}.
     */
    public static final String EXTRA_ERROR_CODE = "android.bluetooth.le.extra.ERROR_CODE";

    /**
     * Extra containing a list of ScanResults. It can have one or more results if there was no
     * error. In case of error, {@link #EXTRA_ERROR_CODE} will contain the error code and this extra
     * will not be available.
     */
    public static final String EXTRA_LIST_SCAN_RESULT = "android.bluetooth.le.extra.LIST_SCAN_RESULT";

    private BluetoothLeScanner() {
    }
}
