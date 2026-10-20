package android.net.wifi.p2p.nsd;

import android.os.Parcelable;

/**
 * A class for storing service information that is advertised
 * over a Wi-Fi peer-to-peer setup
 *
 * @see WifiP2pUpnpServiceInfo
 * @see WifiP2pDnsSdServiceInfo
 */
public final class WifiP2pServiceInfo implements Parcelable {
    /**
     * All service protocol types.
     */
    public static final int SERVICE_TYPE_ALL = 0;

    /**
     * DNS based service discovery protocol.
     */
    public static final int SERVICE_TYPE_BONJOUR = 1;

    /**
     * UPnP protocol.
     */
    public static final int SERVICE_TYPE_UPNP = 2;

    /**
     * Vendor Specific protocol
     */
    public static final int SERVICE_TYPE_VENDOR_SPECIFIC = 255;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private WifiP2pServiceInfo() {
    }
}
