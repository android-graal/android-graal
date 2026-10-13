package android.net.vcn;

/**
 * This class represents a configuration for a connection to a Virtual Carrier Network gateway.
 *
 * <p>Each VcnGatewayConnectionConfig represents a single logical connection to a carrier gateway,
 * and may provide one or more telephony services (as represented by network capabilities). Each
 * gateway is expected to provide mobility for a given session as the device roams across {@link
 * Network}s.
 *
 * <p>A VCN connection based on this configuration will be brought up dynamically based on device
 * settings, and filed NetworkRequests. Underlying Networks must provide INTERNET connectivity, and
 * must be part of the subscription group under which this configuration is registered (see {@link
 * VcnManager#setVcnConfig}).
 *
 * <p>As an abstraction of a cellular network, services that can be provided by a VCN network are
 * limited to services provided by cellular networks:
 *
 * <ul>
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_MMS}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_SUPL}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_DUN}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_FOTA}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_IMS}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_CBS}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_IA}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_RCS}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_XCAP}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_EIMS}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_INTERNET}
 *   <li>{@link NetworkCapabilities#NET_CAPABILITY_MCX}
 * </ul>
 */
public final class VcnGatewayConnectionConfig {
    /**
     * Minimum NAT timeout not set.
     *
     * <p>When the timeout is not set, the device will automatically choose a keepalive interval and
     * may reduce the keepalive frequency for power-optimization.
     */
    public static final int MIN_UDP_PORT_4500_NAT_TIMEOUT_UNSET = -1;

    /**
     * Perform mobility update to attempt recovery from suspected data stalls.
     *
     * <p>If set, the gateway connection will monitor the data stall detection of the VCN network.
     * When there is a suspected data stall, the gateway connection will attempt recovery by
     * performing a mobility update on the underlying IKE session.
     */
    public static final int VCN_GATEWAY_OPTION_ENABLE_DATA_STALL_RECOVERY_WITH_MOBILITY = 0;

    private VcnGatewayConnectionConfig() {
    }
}
