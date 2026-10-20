package android.net.wifi;

import android.os.Parcelable;

/**
 * Data structure class representing a Wi-Fi Multi-Link Operation (MLO) link
 * This is only used by 802.11be capable devices
 */
public final class MloLink implements Parcelable {
    /**
     * Invalid link id. Used in {link #getLinkId()}
     */
    public static final int INVALID_MLO_LINK_ID = -1;

    /**
     * MLO link state: Link is associated to the access point and mapped to at least one traffic
     * stream. {link #getState()}
     * Note that an MLO link could be in that state but in power save mode.
     */
    public static final int MLO_LINK_STATE_ACTIVE = 3;

    /**
     * MLO link state: Link is associated to the access point but not mapped to any traffic stream.
     * Used in {link #getState()}
     */
    public static final int MLO_LINK_STATE_IDLE = 2;

    /**
     * MLO link state: Invalid link state. Used in {link #getState()}
     */
    public static final int MLO_LINK_STATE_INVALID = 0;

    /**
     * MLO link state: Link is not associated with the access point. Used in {link #getState()}
     */
    public static final int MLO_LINK_STATE_UNASSOCIATED = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private MloLink() {
    }
}
