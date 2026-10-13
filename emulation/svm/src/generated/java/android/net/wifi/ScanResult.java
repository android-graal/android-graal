package android.net.wifi;

import android.os.Parcelable;

/**
 * Describes information about a detected access point. In addition
 * to the attributes described here, the supplicant keeps track of
 * {@code quality}, {@code noise}, and {@code maxbitrate} attributes,
 * but does not currently report them to external clients.
 */
public final class ScanResult implements Parcelable {
    /**
     * AP Channel bandwidth is 160 MHZ
     */
    public static final int CHANNEL_WIDTH_160MHZ = 3;

    /**
     * AP Channel bandwidth is 20 MHZ
     */
    public static final int CHANNEL_WIDTH_20MHZ = 0;

    /**
     * AP Channel bandwidth is 320 MHZ
     */
    public static final int CHANNEL_WIDTH_320MHZ = 5;

    /**
     * AP Channel bandwidth is 40 MHZ
     */
    public static final int CHANNEL_WIDTH_40MHZ = 1;

    /**
     * AP Channel bandwidth is 80 MHZ
     */
    public static final int CHANNEL_WIDTH_80MHZ = 2;

    /**
     * AP Channel bandwidth is 160 MHZ, but 80MHZ + 80MHZ
     */
    public static final int CHANNEL_WIDTH_80MHZ_PLUS_MHZ = 4;

    /**
     * Preamble type: EHT.
     */
    public static final int PREAMBLE_EHT = 4;

    /**
     * Preamble type: HE.
     */
    public static final int PREAMBLE_HE = 3;

    /**
     * Preamble type: HT.
     */
    public static final int PREAMBLE_HT = 1;

    /**
     * Preamble type: Legacy.
     */
    public static final int PREAMBLE_LEGACY = 0;

    /**
     * Preamble type: VHT.
     */
    public static final int PREAMBLE_VHT = 2;

    /**
     * The unspecified value.
     */
    public static final int UNSPECIFIED = -1;

    /**
     * Wi-Fi 2.4 GHz band.
     */
    public static final int WIFI_BAND_24_GHZ = 1;

    /**
     * Wi-Fi 5 GHz band.
     */
    public static final int WIFI_BAND_5_GHZ = 2;

    /**
     * Wi-Fi 60 GHz band.
     */
    public static final int WIFI_BAND_60_GHZ = 16;

    /**
     * Wi-Fi 6 GHz band.
     */
    public static final int WIFI_BAND_6_GHZ = 8;

    /**
     * Wi-Fi 802.11ac
     */
    public static final int WIFI_STANDARD_11AC = 5;

    /**
     * Wi-Fi 802.11ad
     */
    public static final int WIFI_STANDARD_11AD = 7;

    /**
     * Wi-Fi 802.11ax
     */
    public static final int WIFI_STANDARD_11AX = 6;

    /**
     * Wi-Fi 802.11be
     */
    public static final int WIFI_STANDARD_11BE = 8;

    /**
     * Wi-Fi 802.11n
     */
    public static final int WIFI_STANDARD_11N = 4;

    /**
     * Wi-Fi 802.11a/b/g
     */
    public static final int WIFI_STANDARD_LEGACY = 1;

    /**
     * Wi-Fi unknown standard
     */
    public static final int WIFI_STANDARD_UNKNOWN = 0;

    private ScanResult() {
    }
}
