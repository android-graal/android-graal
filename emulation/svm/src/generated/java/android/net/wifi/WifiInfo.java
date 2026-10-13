package android.net.wifi;

import android.os.Parcelable;

/**
 * Describes the state of any Wi-Fi connection that is active or
 * is in the process of being set up.
 *
 * In the connected state, access to location sensitive fields requires
 * the same permissions as {@link WifiManager#getScanResults}. If such access is not allowed,
 * {@link #getSSID} will return {@link WifiManager#UNKNOWN_SSID} and
 * {@link #getBSSID} will return {@code "02:00:00:00:00:00"}.
 * {@link #getApMldMacAddress()} will return null.
 * {@link #getNetworkId()} will return {@code -1}.
 * {@link #getPasspointFqdn()} will return null.
 * {@link #getPasspointProviderFriendlyName()} will return null.
 * {@link #getInformationElements()} will return null.
 * {@link #getMacAddress()} will return {@code "02:00:00:00:00:00"}.
 */
public final class WifiInfo implements Parcelable {
    /**
     * Frequency in MHz
     */
    public static final String FREQUENCY_UNITS = "MHz";

    /**
     * The unit in which links speeds are expressed.
     */
    public static final String LINK_SPEED_UNITS = "Mbps";

    /**
     * Constant for unknown link speed.
     */
    public static final int LINK_SPEED_UNKNOWN = -1;

    /**
     * Security type for Easy Connect (DPP) network
     */
    public static final int SECURITY_TYPE_DPP = 13;

    /**
     * Security type for an EAP network.
     */
    public static final int SECURITY_TYPE_EAP = 3;

    /**
     * Security type for a WPA3-Enterprise network.
     */
    public static final int SECURITY_TYPE_EAP_WPA3_ENTERPRISE = 9;

    /**
     * Security type for a WPA3-Enterprise in 192-bit security network.
     */
    public static final int SECURITY_TYPE_EAP_WPA3_ENTERPRISE_192_BIT = 5;

    /**
     * Security type for an open network.
     */
    public static final int SECURITY_TYPE_OPEN = 0;

    /**
     * Security type for an OSEN network.
     */
    public static final int SECURITY_TYPE_OSEN = 10;

    /**
     * Security type for an OWE network.
     */
    public static final int SECURITY_TYPE_OWE = 6;

    /**
     * Security type for a Passpoint R1/R2 network, where TKIP and WEP are not allowed.
     */
    public static final int SECURITY_TYPE_PASSPOINT_R1_R2 = 11;

    /**
     * Security type for a Passpoint R3 network, where TKIP and WEP are not allowed,
     * and PMF must be set to Required.
     */
    public static final int SECURITY_TYPE_PASSPOINT_R3 = 12;

    /**
     * Security type for a PSK network.
     */
    public static final int SECURITY_TYPE_PSK = 2;

    /**
     * Security type for an SAE network.
     */
    public static final int SECURITY_TYPE_SAE = 4;

    /**
     * Unknown security type.
     */
    public static final int SECURITY_TYPE_UNKNOWN = -1;

    /**
     * Security type for a WAPI Certificate network.
     */
    public static final int SECURITY_TYPE_WAPI_CERT = 8;

    /**
     * Security type for a WAPI PSK network.
     */
    public static final int SECURITY_TYPE_WAPI_PSK = 7;

    /**
     * Security type for a WEP network.
     */
    public static final int SECURITY_TYPE_WEP = 1;

    private WifiInfo() {
    }
}
