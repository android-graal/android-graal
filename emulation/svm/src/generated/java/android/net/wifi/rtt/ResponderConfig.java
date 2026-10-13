package android.net.wifi.rtt;

import android.os.Parcelable;

/**
 * Defines the configuration of an IEEE 802.11mc Responder. The Responder may be an Access Point
 * (AP), a Wi-Fi Aware device, or a manually configured Responder.
 * <p>
 * A Responder configuration may be constructed from a {@link ScanResult} or manually (with the
 * data obtained out-of-band from a peer).
 */
public final class ResponderConfig implements Parcelable {
    /**
     * Responder is an access point(AP).
     */
    public static final int RESPONDER_AP = 0;

    /**
     * Responder is a client device(STA).
     */
    public static final int RESPONDER_STA = 1;

    private ResponderConfig() {
    }
}
