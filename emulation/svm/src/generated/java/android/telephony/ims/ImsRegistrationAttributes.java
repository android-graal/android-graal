package android.telephony.ims;

import android.os.Parcelable;

/**
 * Contains the attributes associated with the current IMS registration.
 */
public final class ImsRegistrationAttributes implements Parcelable {
    /**
     * Attribute to specify if an EPDG tunnel is setup over the cellular internet APN.
     * <p>
     * If IMS is registered through an EPDG tunnel is setup over the cellular internet APN then this
     * bit will be set. If IMS is registered through the IMS APN, then this bit will not be set.
     */
    public static final int ATTR_EPDG_OVER_CELL_INTERNET = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ImsRegistrationAttributes() {
    }
}
