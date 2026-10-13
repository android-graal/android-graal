package android.net.wifi.p2p;

import android.os.Parcelable;

/**
 * A class representing a Wi-Fi P2p configuration for setting up a connection
 *
 * {@see WifiP2pManager}
 */
public final class WifiP2pConfig implements Parcelable {
    /**
     * IP provisioning via IPv4 DHCP, when joining a group as a group client.
     */
    public static final int GROUP_CLIENT_IP_PROVISIONING_MODE_IPV4_DHCP = 0;

    /**
     * IP provisioning via IPv6 link-local, when joining a group as a group client.
     */
    public static final int GROUP_CLIENT_IP_PROVISIONING_MODE_IPV6_LINK_LOCAL = 1;

    /**
     * Allow the system to pick the operating frequency from the 2.4 GHz band.
     */
    public static final int GROUP_OWNER_BAND_2GHZ = 1;

    /**
     * Allow the system to pick the operating frequency from the 5 GHz band.
     */
    public static final int GROUP_OWNER_BAND_5GHZ = 2;

    /**
     * Allow the system to pick the operating frequency from the 6 GHz band.
     */
    public static final int GROUP_OWNER_BAND_6GHZ = 3;

    /**
     * Allow the system to pick the operating frequency from all supported bands.
     */
    public static final int GROUP_OWNER_BAND_AUTO = 0;

    /**
     * The system can choose an appropriate owner intent value, to be filled in the field
     * {@link #groupOwnerIntent}.
     */
    public static final int GROUP_OWNER_INTENT_AUTO = -1;

    /**
     * The most inclination to be a group owner, to be filled in the field
     * {@link #groupOwnerIntent}.
     */
    public static final int GROUP_OWNER_INTENT_MAX = 15;

    /**
     * The least inclination to be a group owner, to be filled in the field
     * {@link #groupOwnerIntent}.
     */
    public static final int GROUP_OWNER_INTENT_MIN = 0;

    /**
     * P2P Protocol version 1
     */
    public static final int P2P_VERSION_1 = 0;

    /**
     * P2P Protocol version 2
     */
    public static final int P2P_VERSION_2 = 1;

    /**
     * Legacy connection type.
     * <p>Group Owner: Configured to support WPA2-Personal connections.
     * <p>Group Client: Configured to connect to Group Owner using WPA2-Personal.
     */
    public static final int PCC_MODE_CONNECTION_TYPE_LEGACY_ONLY = 0;

    /**
     * Wi-Fi Direct R1/R2 compatible mode connection type.
     * <p>Group Owner: Configured in WPA3-Personal Compatibility Mode to support WPA3-Personal and
     *              WPA2-Personal connections simultaneously.
     * <p>Group Client: Configured to connect to Group Owner using WPA3-Personal or WPA2-Personal.
     *               The system will choose WPA3-Personal if Group Owner support WPA3-Personal.
     */
    public static final int PCC_MODE_CONNECTION_TYPE_LEGACY_OR_R2 = 1;

    /**
     * This configuration allows only Wi-Fi Direct R2 supported devices to establish connection.
     * <p>Group Owner: Configured to support WPA3-Personal connections.
     * <p>Group Client: Configured to connect to Group Owner using WPA3-Personal.
     */
    public static final int PCC_MODE_CONNECTION_TYPE_R2_ONLY = 2;

    private WifiP2pConfig() {
    }
}
