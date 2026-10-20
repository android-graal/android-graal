package android.ranging.ble.cs;

import android.os.Parcelable;

/**
 * BleCsRangingParams encapsulates the parameters required for a bluetooth channel sounding ranging
 * session.
 */
public final class BleCsRangingParams implements Parcelable {
    /**
     * Location of the device is indoor.
     */
    public static final int LOCATION_TYPE_INDOOR = 1;

    /**
     * Location of the device is outdoor.
     */
    public static final int LOCATION_TYPE_OUTDOOR = 2;

    /**
     * Location of the device is unknown.
     */
    public static final int LOCATION_TYPE_UNKNOWN = 0;

    /**
     * Remote device is in line of sight.
     */
    public static final int SIGHT_TYPE_LINE_OF_SIGHT = 1;

    /**
     * Remote device is not in line of sight.
     */
    public static final int SIGHT_TYPE_NON_LINE_OF_SIGHT = 2;

    /**
     * Sight type is unknown.
     */
    public static final int SIGHT_TYPE_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private BleCsRangingParams() {
    }
}
