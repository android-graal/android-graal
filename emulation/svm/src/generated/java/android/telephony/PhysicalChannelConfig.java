package android.telephony;

import android.os.Parcelable;

public final class PhysicalChannelConfig implements Parcelable {
    /**
     * The band is unknown.
     */
    public static final int BAND_UNKNOWN = 0;

    /**
     * Cell bandwidth is unknown.
     */
    public static final int CELL_BANDWIDTH_UNKNOWN = 0;

    /**
     * Channel number is unknown.
     */
    public static final int CHANNEL_NUMBER_UNKNOWN = 2147483647;

    /**
     * UE has connection to cell for signalling and possibly data (3GPP 36.331, 25.331).
     *
     * @deprecated Use {@link  CellInfo#CONNECTION_PRIMARY_SERVING} instead.
     */
    @Deprecated
    public static final int CONNECTION_PRIMARY_SERVING = 1;

    /**
     * UE has connection to cell for data (3GPP 36.331, 25.331).
     *
     * @deprecated Use {@link  CellInfo#CONNECTION_SECONDARY_SERVING} instead.
     */
    @Deprecated
    public static final int CONNECTION_SECONDARY_SERVING = 2;

    /**
     * Connection status is unknown.
     *
     * @deprecated Use {@link  CellInfo#CONNECTION_UNKNOWN} instead.
     */
    @Deprecated
    public static final int CONNECTION_UNKNOWN = -1;

    /**
     * The frequency is unknown.
     */
    public static final int FREQUENCY_UNKNOWN = -1;

    /**
     * Physical Cell Id's maximum value is 1007.
     */
    public static final int PHYSICAL_CELL_ID_MAXIMUM_VALUE = 1007;

    /**
     * Physical Cell Id is unknown.
     */
    public static final int PHYSICAL_CELL_ID_UNKNOWN = -1;

    private PhysicalChannelConfig() {
    }
}
