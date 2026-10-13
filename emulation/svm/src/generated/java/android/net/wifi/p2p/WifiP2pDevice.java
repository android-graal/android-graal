package android.net.wifi.p2p;

import android.os.Parcelable;

/**
 * A class representing a Wi-Fi p2p device
 *
 * Note that the operations are not thread safe
 * {@see WifiP2pManager}
 */
public final class WifiP2pDevice implements Parcelable {
    public static final int AVAILABLE = 3;

    public static final int CONNECTED = 0;

    public static final int FAILED = 2;

    public static final int INVITED = 1;

    public static final int UNAVAILABLE = 4;

    private WifiP2pDevice() {
    }
}
