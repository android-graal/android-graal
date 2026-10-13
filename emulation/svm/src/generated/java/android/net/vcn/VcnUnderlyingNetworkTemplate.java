package android.net.vcn;

/**
 * This class represents a template containing set of underlying network requirements for doing
 * route selection.
 *
 * <p>Apps provisioning a VCN can configure the underlying network priority for each Gateway
 * Connection by setting a list (in priority order, most to least preferred) of the appropriate
 * subclasses in the VcnGatewayConnectionConfig. See {@link
 * VcnGatewayConnectionConfig.Builder#setVcnUnderlyingNetworkPriorities}
 */
public final class VcnUnderlyingNetworkTemplate {
    /**
     * Used to configure the matching criteria of a network characteristic. This may include network
     * capabilities, or cellular subscription information. Denotes that networks with or without the
     * characteristic are both acceptable to match this template.
     */
    public static final int MATCH_ANY = 0;

    /**
     * Used to configure the matching criteria of a network characteristic. This may include network
     * capabilities, or cellular subscription information. Denotes that a network MUST NOT have the
     * capability in order to match this template.
     */
    public static final int MATCH_FORBIDDEN = 2;

    /**
     * Used to configure the matching criteria of a network characteristic. This may include network
     * capabilities, or cellular subscription information. Denotes that a network MUST have the
     * capability in order to match this template.
     */
    public static final int MATCH_REQUIRED = 1;

    private VcnUnderlyingNetworkTemplate() {
    }
}
