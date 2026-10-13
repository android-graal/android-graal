package android.bluetooth.le;

import android.os.Parcelable;

/**
 * Bluetooth LE scan settings are passed to {@link BluetoothLeScanner#startScan} to define the
 * parameters for the scan.
 */
public final class ScanSettings implements Parcelable {
    /**
     * Minimum report delay for {@link ScanSettings#CALLBACK_TYPE_ALL_MATCHES_AUTO_BATCH}.
     */
    public static final long AUTO_BATCH_MIN_REPORT_DELAY_MILLIS = 600000L;

    /**
     * Trigger a callback for every Bluetooth advertisement found that matches the filter criteria.
     * If no filter is active, all advertisement packets are reported.
     */
    public static final int CALLBACK_TYPE_ALL_MATCHES = 1;

    /**
     * A result callback for every Bluetooth advertisement found that matches the filter criteria is
     * only triggered when screen is turned on. While the screen is turned off, the advertisements
     * are batched and the batched result callbacks are triggered every report delay. When the batch
     * scan with this callback type is activated, the batched result callbacks are also triggered
     * while turning on screen or disabling the scan. This callback type must be used with a report
     * delay of {@link ScanSettings#AUTO_BATCH_MIN_REPORT_DELAY_MILLIS} or greater.
     */
    public static final int CALLBACK_TYPE_ALL_MATCHES_AUTO_BATCH = 8;

    /**
     * A result callback is only triggered for the first advertisement packet received that matches
     * the filter criteria.
     */
    public static final int CALLBACK_TYPE_FIRST_MATCH = 2;

    /**
     * Receive a callback when advertisements are no longer received from a device that has been
     * previously reported by a first match callback.
     */
    public static final int CALLBACK_TYPE_MATCH_LOST = 4;

    /**
     * In Aggressive mode, hw will determine a match sooner even with feeble signal strength and few
     * number of sightings/match in a duration.
     */
    public static final int MATCH_MODE_AGGRESSIVE = 1;

    /**
     * For sticky mode, higher threshold of signal strength and sightings is required before
     * reporting by hw.
     */
    public static final int MATCH_MODE_STICKY = 2;

    /**
     * Match few advertisement per filter, depends on current capability and availability of the
     * resources in hw.
     */
    public static final int MATCH_NUM_FEW_ADVERTISEMENT = 2;

    /**
     * Match as many advertisement per filter as hw could allow, depends on current capability and
     * availability of the resources in hw.
     */
    public static final int MATCH_NUM_MAX_ADVERTISEMENT = 3;

    /**
     * Determines how many advertisements to match per filter, as this is scarce hw resource. Match
     * one advertisement per filter.
     */
    public static final int MATCH_NUM_ONE_ADVERTISEMENT = 1;

    /**
     * Use all supported PHYs for scanning. This will check the controller capabilities, and start
     * the scan on 1Mbit and LE Coded PHYs if supported, or on the 1Mbit PHY only.
     */
    public static final int PHY_LE_ALL_SUPPORTED = 255;

    /**
     * Perform Bluetooth LE scan in balanced power mode. Scan results are returned at a rate that
     * provides a good trade-off between scan frequency and power consumption.
     */
    public static final int SCAN_MODE_BALANCED = 1;

    /**
     * Scan using highest duty cycle. It's recommended to only use this mode when the application is
     * running in the foreground.
     */
    public static final int SCAN_MODE_LOW_LATENCY = 2;

    /**
     * Perform Bluetooth LE scan in low power mode. This is the default scan mode as it consumes the
     * least power. This mode is enforced if the scanning application is not in foreground.
     */
    public static final int SCAN_MODE_LOW_POWER = 0;

    /**
     * A special Bluetooth LE scan mode. Applications using this scan mode will passively listen for
     * other scan results without starting BLE scans themselves.
     */
    public static final int SCAN_MODE_OPPORTUNISTIC = -1;

    private ScanSettings() {
    }
}
