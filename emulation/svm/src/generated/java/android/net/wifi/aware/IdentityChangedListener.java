package android.net.wifi.aware;

/**
 * Base class for Aware identity/cluster changes callbacks. Should be extended by applications and
 * set when calling {@link WifiAwareManager#attach(AttachCallback, IdentityChangedListener,
 * android.os.Handler)}. These are callbacks applying to the Aware connection as a whole - not to
 * specific publish or subscribe sessions - for that see {@link DiscoverySessionCallback}.
 */
public final class IdentityChangedListener {
    /**
     * Wi-Fi Aware cluster change event type when joining a cluster.
     */
    public static final int CLUSTER_CHANGE_EVENT_JOINED = 1;

    /**
     * Wi-Fi Aware cluster change event type when starting a cluster.
     */
    public static final int CLUSTER_CHANGE_EVENT_STARTED = 0;

    private IdentityChangedListener() {
    }
}
