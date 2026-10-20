package android.location;

import android.os.Parcelable;

/**
 * GNSS chipset capabilities.
 */
public final class GnssCapabilities implements Parcelable {
    /**
     * The capability is supported.
     */
    public static final int CAPABILITY_SUPPORTED = 1;

    /**
     * The capability is unknown to be supported or not.
     */
    public static final int CAPABILITY_UNKNOWN = 0;

    /**
     * The capability is not supported.
     */
    public static final int CAPABILITY_UNSUPPORTED = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private GnssCapabilities() {
    }
}
