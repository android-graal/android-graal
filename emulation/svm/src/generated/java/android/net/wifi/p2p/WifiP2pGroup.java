package android.net.wifi.p2p;

import android.os.Parcelable;

/**
 * A class representing a Wi-Fi P2p group. A p2p group consists of a single group
 * owner and one or more clients. In the case of a group with only two devices, one
 * will be the group owner and the other will be a group client.
 *
 * {@see WifiP2pManager}
 */
public final class WifiP2pGroup implements Parcelable {
    /**
     * The persistent network id.
     * If a matching persistent profile is found, use it.
     * Otherwise, create a new persistent profile.
     * @see #getNetworkId()
     */
    public static final int NETWORK_ID_PERSISTENT = -2;

    /**
     * The temporary network id.
     * @see #getNetworkId()
     */
    public static final int NETWORK_ID_TEMPORARY = -1;

    /**
     * The definition of security type unknown. It is set when framework fails to derive the
     * security type from the authentication key management provided by wpa_supplicant.
     */
    public static final int SECURITY_TYPE_UNKNOWN = -1;

    /**
     * The definition of security type WPA2-PSK.
     */
    public static final int SECURITY_TYPE_WPA2_PSK = 0;

    /**
     * The definition of security type WPA3-Compatibility Mode.
     */
    public static final int SECURITY_TYPE_WPA3_COMPATIBILITY = 1;

    /**
     * The definition of security type WPA3-SAE.
     */
    public static final int SECURITY_TYPE_WPA3_SAE = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private WifiP2pGroup() {
    }
}
