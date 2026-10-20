package android.telephony;

import android.os.Parcelable;

/**
 * Immutable cell information from a point in time.
 */
public abstract class CellInfo implements Parcelable {
    /**
     * Cell is not a serving cell.
     *
     * <p>The cell has been measured but is neither a camped nor serving cell (3GPP 36.304).
     */
    public static final int CONNECTION_NONE = 0;

    /**
     * UE is connected to cell for signalling and possibly data (3GPP 36.331, 25.331).
     */
    public static final int CONNECTION_PRIMARY_SERVING = 1;

    /**
     * UE is connected to cell for data (3GPP 36.331, 25.331).
     */
    public static final int CONNECTION_SECONDARY_SERVING = 2;

    /**
     * Connection status is unknown.
     */
    public static final int CONNECTION_UNKNOWN = 2147483647;

    /**
     * This value indicates that the integer field is unreported.
     */
    public static final int UNAVAILABLE = 2147483647;

    /**
     * This value indicates that the long field is unreported.
     */
    public static final long UNAVAILABLE_LONG = 9223372036854775807L;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    private CellInfo() {
    }
}
