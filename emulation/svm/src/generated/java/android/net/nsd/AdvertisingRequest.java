package android.net.nsd;

import android.os.Parcelable;

/**
 * Encapsulates parameters for {@link NsdManager#registerService}.
 */
public final class AdvertisingRequest implements Parcelable {
    /**
     * Skip the probing step when advertising.
     *
     * <p>This must only be used when the service name ({@link NsdServiceInfo#getServiceName()} is
     * known to be unique and cannot possibly be used by any other device on the network.
     */
    public static final long FLAG_SKIP_PROBING = 2L;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AdvertisingRequest() {
    }
}
