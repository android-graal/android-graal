package android.ranging;

import android.os.Parcelable;

/**
 * Represents the capabilities and availability of various ranging technologies.
 *
 * <p>The {@code RangingCapabilities} class encapsulates the status of different ranging
 * technologies. It also allows querying the availability of other ranging technologies through a
 * mapping of technology identifiers to availability statuses.</p>
 */
public final class RangingCapabilities implements Parcelable {
    /**
     * Indicates that the ranging technology is disabled due to regulatory restrictions.
     */
    public static final int DISABLED_REGULATORY = 2;

    /**
     * Indicates that the ranging technology is disabled by the user.
     */
    public static final int DISABLED_USER = 1;

    /**
     * Indicates that the ranging technology is disabled due to device usage restrictions.
     */
    public static final int DISABLED_USER_RESTRICTIONS = 4;

    /**
     * Indicates that the ranging technology is enabled and available for use.
     */
    public static final int ENABLED = 3;

    /**
     * Indicates that the ranging technology is not supported on the current device.
     */
    public static final int NOT_SUPPORTED = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private RangingCapabilities() {
    }
}
