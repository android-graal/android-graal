package android.net.wifi;

import android.os.Parcelable;

/**
 * A class representing Wi-Fi Protected Setup
 *
 * {@see android.net.wifi.p2p.WifiP2pConfig}
 */
public final class WpsInfo implements Parcelable {
    /**
     * Display pin method configuration - pin is generated and displayed on device
     */
    public static final int DISPLAY = 1;

    /**
     * Invalid configuration
     */
    public static final int INVALID = 4;

    /**
     * Keypad pin method configuration - pin is entered on device
     */
    public static final int KEYPAD = 2;

    /**
     * Label pin method configuration - pin is labelled on device
     */
    public static final int LABEL = 3;

    /**
     * Push button configuration
     */
    public static final int PBC = 0;

    private WpsInfo() {
    }
}
