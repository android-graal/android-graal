package android.telephony;

import android.os.Parcelable;

/**
 * Contains phone signal strength related information.
 */
public final class SignalStrength implements Parcelable {
    /**
     * Indicates the invalid measures of signal strength.
     *
     * For example, this can be returned by {@link #getEvdoDbm()} or {@link #getCdmaDbm()}
     */
    public static final int INVALID = 2147483647;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SignalStrength() {
    }
}
