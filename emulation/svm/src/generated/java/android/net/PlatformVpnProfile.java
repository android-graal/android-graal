package android.net;

/**
 * PlatformVpnProfile represents a configuration for a platform-based VPN implementation.
 *
 * <p>Platform-based VPNs allow VPN applications to provide configuration and authentication options
 * to leverage the Android OS' implementations of well-defined control plane (authentication, key
 * negotiation) and data plane (per-packet encryption) protocols to simplify the creation of VPN
 * tunnels. In contrast, {@link VpnService} based VPNs must implement both the control and data
 * planes on a per-app basis.
 *
 * @see Ikev2VpnProfile
 */
public final class PlatformVpnProfile {
    public static final int TYPE_IKEV2_IPSEC_PSK = 7;

    public static final int TYPE_IKEV2_IPSEC_RSA = 8;

    public static final int TYPE_IKEV2_IPSEC_USER_PASS = 6;

    private PlatformVpnProfile() {
    }
}
