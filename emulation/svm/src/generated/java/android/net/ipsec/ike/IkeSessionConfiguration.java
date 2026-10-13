package android.net.ipsec.ike;

/**
 * IkeSessionConfiguration represents the negotiated configuration for a {@link IkeSession}.
 *
 * <p>Configurations include remote application version and enabled IKE extensions.
 */
public final class IkeSessionConfiguration {
    /**
     * IKE Message Fragmentation
     */
    public static final int EXTENSION_TYPE_FRAGMENTATION = 1;

    /**
     * IKEv2 Mobility and Multihoming Protocol
     */
    public static final int EXTENSION_TYPE_MOBIKE = 2;

    private IkeSessionConfiguration() {
    }
}
