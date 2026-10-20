package android.telephony;

import android.os.Parcelable;

/**
 * Represents the neighboring cell information, including
 * Received Signal Strength and Cell ID location.
 *
 * @deprecated This class should not be used by any app targeting
 *     {@link android.os.Build.VERSION_CODES#Q Android Q} or higher. Instead callers should use
 *     {@link android.telephony.CellInfo CellInfo}.
 */
@Deprecated
public final class NeighboringCellInfo implements Parcelable {
    /**
     * Cell location is not available
     */
    @Deprecated
    public static final int UNKNOWN_CID = -1;

    /**
     * Signal strength is not available
     */
    @Deprecated
    public static final int UNKNOWN_RSSI = 99;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private NeighboringCellInfo() {
    }
}
