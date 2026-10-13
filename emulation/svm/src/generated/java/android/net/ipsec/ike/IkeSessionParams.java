package android.net.ipsec.ike;

/**
 * IkeSessionParams contains all user provided configurations for negotiating an {@link IkeSession}.
 *
 * <p>Note that all negotiated configurations will be reused during rekey including SA Proposal and
 * lifetime.
 */
public final class IkeSessionParams {
    /**
     * Setting timer to this value will disable the Dead Peer Detection(DPD).
     *
     * <p>@see {@link Builder#setDpdDelaySeconds}
     */
    public static final int IKE_DPD_DELAY_SEC_DISABLED = 2147483647;

    /**
     * If set, the IKE library will accept any remote (server) identity, even if it does not match
     * the configured remote identity
     *
     * <p>See {@link Builder#setRemoteIdentification(IkeIdentification)}
     */
    public static final int IKE_OPTION_ACCEPT_ANY_REMOTE_ID = 0;

    /**
     * If set, and EAP has been configured as the authentication method, the IKE library will
     * request that the remote (also) use an EAP-only authentication flow.
     *
     * <p>@see {@link Builder#setAuthEap(X509Certificate, EapSessionConfig)}
     */
    public static final int IKE_OPTION_EAP_ONLY_AUTH = 1;

    /**
     * Configures the IKE session to always send to port 4500.
     *
     * <p>If set, the IKE Session will be initiated and maintained exclusively using
     * destination port 4500, regardless of the presence of NAT. Otherwise, the IKE Session will
     * be initiated on destination port 500; then, if either a NAT is detected or both MOBIKE
     * and NAT-T are supported by the peer, it will proceed on port 4500.
     */
    public static final int IKE_OPTION_FORCE_PORT_4500 = 3;

    /**
     * If set, the IKE library will send INITIAL_CONTACT notification to the peers.
     *
     * <p>If this option is set, the INITIAL_CONTACT notification payload is sent in IKE_AUTH. The
     * client can use this option to assert to the peer that this IKE SA is the only IKE SA
     * currently active between the authenticated identities.
     *
     * <p>@see "https://tools.ietf.org/html/rfc7296#section-2.4" RFC 7296, Internet Key Exchange
     * Protocol Version 2 (IKEv2)
     *
     * <p>@see {@link Builder#addIkeOption(int)}
     */
    public static final int IKE_OPTION_INITIAL_CONTACT = 4;

    /**
     * If set, the IKE Session will attempt to handle IP address changes using RFC4555 MOBIKE.
     *
     * <p>Upon IP address changes (including Network changes), the IKE session will initiate an RFC
     * 4555 MOBIKE procedure, migrating both this IKE Session and associated IPsec Transforms to the
     * new local and remote address pair.
     *
     * <p>The IKE library will first attempt to enable MOBIKE to handle the changes of underlying
     * network and addresses. For callers targeting SDK {@link android.os.Build.VERSION_CODES#S_V2}
     * and earlier, this option will implicitly enable the support for rekey-based mobility, and
     * thus if the server does not support MOBIKE, the IKE Session will try migration by rekeying
     * all associated IPsec SAs. This rekey-based mobility feature is not best-practice and has
     * technical issues; accordingly, it will no longer be enabled for callers targeting SDK {@link
     * android.os.Build.VERSION_CODES#TIRAMISU} and above.
     *
     * <p>Checking whether or not MOBIKE is supported by both the IKE library and the server in an
     * IKE Session is done via {@link IkeSessionConfiguration#isIkeExtensionEnabled(int)}.
     *
     * <p>It is recommended that IKE_OPTION_MOBIKE be enabled unless precluded for compatibility
     * reasons.
     *
     * <p>If this option is set for an IKE Session, Transport-mode SAs will not be allowed in that
     * Session.
     *
     * <p>Callers that need to perform migration of IPsec transforms and tunnels MUST implement
     * migration specific methods in {@link IkeSessionCallback} and {@link ChildSessionCallback}.
     */
    public static final int IKE_OPTION_MOBIKE = 2;

    private IkeSessionParams() {
    }
}
