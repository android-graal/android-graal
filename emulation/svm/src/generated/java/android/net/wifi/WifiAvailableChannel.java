package android.net.wifi;

import android.os.Parcelable;

/**
 * Contains information about a Wifi channel and bitmask of Wifi operational modes allowed on that
 * channel. Use {@link WifiManager#getAllowedChannels(int, int)} to retrieve the list of channels
 * filtered by regulatory constraints. Use {@link WifiManager#getUsableChannels(int, int)} to
 * retrieve the list of channels filtered by regulatory and dynamic constraints like concurrency and
 * interference due to other radios.
 */
public final class WifiAvailableChannel implements Parcelable {
    /**
     * Wifi SoftAp (Mobile Hotspot) operational mode.
     */
    public static final int OP_MODE_SAP = 2;

    /**
     * Wifi Infrastructure client (STA) operational mode.
     */
    public static final int OP_MODE_STA = 1;

    /**
     * Wifi Tunneled Direct Link Setup (TDLS) operational mode.
     */
    public static final int OP_MODE_TDLS = 32;

    /**
     * Wifi Aware (NAN) operational mode.
     */
    public static final int OP_MODE_WIFI_AWARE = 16;

    /**
     * Wifi Direct client (CLI) operational mode.
     */
    public static final int OP_MODE_WIFI_DIRECT_CLI = 4;

    /**
     * Wifi Direct Group Owner (GO) operational mode.
     */
    public static final int OP_MODE_WIFI_DIRECT_GO = 8;

    private WifiAvailableChannel() {
    }
}
