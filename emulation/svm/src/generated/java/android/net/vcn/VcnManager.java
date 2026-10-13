package android.net.vcn;

/**
 * VcnManager publishes APIs for applications to configure and manage Virtual Carrier Networks.
 *
 * <p>A VCN creates a virtualization layer to allow carriers to aggregate heterogeneous physical
 * networks, unifying them as a single carrier network. This enables infrastructure flexibility on
 * the part of carriers without impacting user connectivity, abstracting the physical network
 * technologies as an implementation detail of their public network.
 *
 * <p>Each VCN virtualizes a carrier's network by building tunnels to a carrier's core network over
 * carrier-managed physical links and supports a IP mobility layer to ensure seamless transitions
 * between the underlying networks. Each VCN is configured based on a Subscription Group (see {@link
 * android.telephony.SubscriptionManager}) and aggregates all networks that are brought up based on
 * a profile or suggestion in the specified Subscription Group.
 *
 * <p>The VCN can be configured to expose one or more {@link android.net.Network}(s), each with
 * different capabilities, allowing for APN virtualization.
 *
 * <p>If a tunnel fails to connect, or otherwise encounters a fatal error, the VCN will attempt to
 * reestablish the connection. If the tunnel still has not reconnected after a system-determined
 * timeout, the VCN Safe Mode (see below) will be entered.
 *
 * <p>The VCN Safe Mode ensures that users (and carriers) have a fallback to restore system
 * connectivity to update profiles, diagnose issues, contact support, or perform other remediation
 * tasks. In Safe Mode, the system will allow underlying cellular networks to be used as default.
 * Additionally, during Safe Mode, the VCN will continue to retry the connections, and will
 * automatically exit Safe Mode if all active tunnels connect successfully.
 *
 * <p>Apps targeting Android 15 or newer should check the existence of {@link
 * PackageManager#FEATURE_TELEPHONY_SUBSCRIPTION} before querying the service. If the feature is
 * absent, {@link Context#getSystemService} may return null.
 */
public final class VcnManager {
    /**
     * Value indicating that an error with this Gateway Connection's configuration occurred.
     *
     * <p>For example, this error code will be returned after authentication failures.
     */
    public static final int VCN_ERROR_CODE_CONFIG_ERROR = 1;

    /**
     * Value indicating that an internal failure occurred in this Gateway Connection.
     */
    public static final int VCN_ERROR_CODE_INTERNAL_ERROR = 0;

    /**
     * Value indicating that a Network error occurred with this Gateway Connection.
     *
     * <p>For example, this error code will be returned if an underlying {@link android.net.Network}
     * for this Gateway Connection is lost, or if an error occurs while resolving the connection
     * endpoint address.
     */
    public static final int VCN_ERROR_CODE_NETWORK_ERROR = 2;

    /**
     * Value indicating that the VCN for the subscription group is active.
     *
     * <p>A VCN is active if a {@link VcnConfig} is present for the subscription, the provisioning
     * package is privileged, and the VCN is not in Safe Mode. In other words, a VCN is considered
     * active while it is connecting, fully connected, and disconnecting.
     */
    public static final int VCN_STATUS_CODE_ACTIVE = 2;

    /**
     * Value indicating that the VCN for the subscription group is inactive.
     *
     * <p>A VCN is inactive if a {@link VcnConfig} is present for the subscription group, but the
     * provisioning package is not privileged.
     */
    public static final int VCN_STATUS_CODE_INACTIVE = 1;

    /**
     * Value indicating that the VCN for the subscription group is not configured, or that the
     * callback is not privileged for the subscription group.
     */
    public static final int VCN_STATUS_CODE_NOT_CONFIGURED = 0;

    /**
     * Value indicating that the VCN for the subscription group is in Safe Mode.
     *
     * <p>A VCN will be put into Safe Mode if any of the gateway connections were unable to
     * establish a connection within a system-determined timeout (while underlying networks were
     * available).
     */
    public static final int VCN_STATUS_CODE_SAFE_MODE = 3;

    private VcnManager() {
    }
}
