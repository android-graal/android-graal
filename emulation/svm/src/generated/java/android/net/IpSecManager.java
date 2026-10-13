package android.net;

/**
 * This class contains methods for managing IPsec sessions. Once configured, the kernel will apply
 * confidentiality (encryption) and integrity (authentication) to IP traffic.
 *
 * <p>Note that not all aspects of IPsec are permitted by this API. Applications may create
 * transport mode security associations and apply them to individual sockets. Applications looking
 * to create an IPsec VPN should use {@link VpnManager} and {@link Ikev2VpnProfile}.
 *
 * @see <a href="https://tools.ietf.org/html/rfc4301">RFC 4301, Security Architecture for the
 *     Internet Protocol</a>
 */
public final class IpSecManager {
    /**
     * Used when applying a transform to direct traffic through an {@link IpSecTransform}
     * towards the host.
     *
     * <p>See {@link #applyTransportModeTransform(Socket, int, IpSecTransform)}.
     */
    public static final int DIRECTION_IN = 0;

    /**
     * Used when applying a transform to direct traffic through an {@link IpSecTransform}
     * away from the host.
     *
     * <p>See {@link #applyTransportModeTransform(Socket, int, IpSecTransform)}.
     */
    public static final int DIRECTION_OUT = 1;

    private IpSecManager() {
    }
}
