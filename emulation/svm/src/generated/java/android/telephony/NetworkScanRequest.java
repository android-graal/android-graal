package android.telephony;

import android.os.Parcelable;

/**
 * Defines a request to perform a network scan.
 *
 * This class defines whether the network scan will be performed only once or periodically until
 * cancelled, when the scan is performed periodically, the time interval is not controlled by the
 * user but defined by the modem vendor.
 */
public final class NetworkScanRequest implements Parcelable {
    /**
     * Performs the scan only once
     */
    public static final int SCAN_TYPE_ONE_SHOT = 0;

    /**
     * Performs the scan periodically until cancelled
     *
     * The modem will start new scans periodically, and the interval between two scans is usually
     * multiple minutes.
     */
    public static final int SCAN_TYPE_PERIODIC = 1;

    private NetworkScanRequest() {
    }
}
