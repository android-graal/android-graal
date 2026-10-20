package android.telephony.ims;

import android.os.Parcelable;

/**
 * Contains the information for SIP.
 */
public final class SipDetails implements Parcelable {
    /**
     * Indicates information related to the SIP publication method.
     * See RFC 3903 for details.
     */
    public static final int METHOD_PUBLISH = 2;

    /**
     * Indicates information related to the SIP registration method.
     * See RFC 3261 for details.
     */
    public static final int METHOD_REGISTER = 1;

    /**
     * Indicates information related to the SIP subscription method.
     * See RFC 3856 for details.
     */
    public static final int METHOD_SUBSCRIBE = 3;

    public static final int METHOD_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SipDetails() {
    }
}
