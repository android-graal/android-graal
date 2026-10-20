package android.bluetooth.le;

import android.os.Parcelable;

/**
 * ScanResult for Bluetooth LE scan.
 */
public final class ScanResult implements Parcelable {
    /**
     * For chained advertisements, indicates that the data contained in this scan result is
     * complete.
     */
    public static final int DATA_COMPLETE = 0;

    /**
     * For chained advertisements, indicates that the controller was unable to receive all chained
     * packets and the scan result contains incomplete truncated data.
     */
    public static final int DATA_TRUNCATED = 2;

    /**
     * Periodic advertising interval is not present in the packet.
     */
    public static final int PERIODIC_INTERVAL_NOT_PRESENT = 0;

    /**
     * Indicates that the secondary physical layer was not used.
     */
    public static final int PHY_UNUSED = 0;

    /**
     * Advertising Set ID is not present in the packet.
     */
    public static final int SID_NOT_PRESENT = 255;

    /**
     * TX power is not present in the packet.
     */
    public static final int TX_POWER_NOT_PRESENT = 127;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ScanResult() {
    }
}
