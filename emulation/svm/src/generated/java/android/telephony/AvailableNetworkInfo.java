package android.telephony;

import android.os.Parcelable;

/**
 * Defines available network information which includes corresponding subscription id,
 * network plmns and corresponding priority to be used for network selection by Opportunistic
 * Network Service when passed through {@link TelephonyManager#updateAvailableNetworks}
 */
public final class AvailableNetworkInfo implements Parcelable {
    public static final int PRIORITY_HIGH = 1;

    public static final int PRIORITY_LOW = 3;

    public static final int PRIORITY_MED = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AvailableNetworkInfo() {
    }
}
