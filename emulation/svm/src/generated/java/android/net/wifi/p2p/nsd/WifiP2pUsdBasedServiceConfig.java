package android.net.wifi.p2p.nsd;

import android.os.Parcelable;

/**
 * A class for creating a USD based service discovery configuration for use with
 * {@link WifiP2pServiceInfo}.<br> or {@link WifiP2pServiceRequest}.<br>
 * For the details of the configuration, refer Wi-Fi Alliance Wi-Fi Direct R2 specification
 * - Appendix H - Unsynchronized Service Discovery (as defined in Wi-Fi Aware) and section
 * 4.2.13 USD frame format.
 */
public final class WifiP2pUsdBasedServiceConfig implements Parcelable {
    /**
     * Bonjour service protocol type
     */
    public static final int SERVICE_PROTOCOL_TYPE_BONJOUR = 1;

    /**
     * Generic service protocol type
     */
    public static final int SERVICE_PROTOCOL_TYPE_GENERIC = 2;

    private WifiP2pUsdBasedServiceConfig() {
    }
}
