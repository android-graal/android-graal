package android.net;

/**
 * This class provides an interface for apps to manage platform VPN profiles
 *
 * <p>Apps can use this API to provide profiles with which the platform can set up a VPN without
 * further app intermediation. When a VPN profile is present and the app is selected as an always-on
 * VPN, the platform will directly trigger the negotiation of the VPN without starting or waking the
 * app (unlike VpnService).
 *
 * <p>VPN apps using supported protocols should preferentially use this API over the {@link
 * VpnService} API for ease-of-development and reduced maintenance burden. This also give the user
 * the guarantee that VPN network traffic is not subjected to on-device packet interception.
 *
 * @see Ikev2VpnProfile
 */
public final class VpnManager {
    /**
     * Action sent in {@link android.content.Intent}s to VpnManager clients when an event occurred.
     *
     * <p>If the provisioning application declares a service handling this intent action, but is not
     * already running, it will be started. Upon starting, the application is granted a short grace
     * period to run in the background even while the device is idle to handle any potential
     * failures. Applications requiring long-running actions triggered by one of these events should
     * declare a foreground service to prevent being killed once the grace period expires.
     *
     * This action will have a category of either {@link #CATEGORY_EVENT_IKE_ERROR},
     * {@link #CATEGORY_EVENT_NETWORK_ERROR}, or {@link #CATEGORY_EVENT_DEACTIVATED_BY_USER},
     * that the app can use to filter events it's interested in reacting to.
     *
     * It will also contain the following extras :
     * <ul>
     *   <li>{@link #EXTRA_SESSION_KEY}, a {@code String} for the session key, as returned by
     *       {@link #startProvisionedVpnProfileSession}.
     *   <li>{@link #EXTRA_TIMESTAMP_MILLIS}, a long for the timestamp at which the error occurred,
     *       in milliseconds since the epoch, as returned by
     *       {@link java.lang.System#currentTimeMillis}.
     *   <li>{@link #EXTRA_UNDERLYING_NETWORK}, a {@link Network} containing the underlying
     *       network at the time the error occurred, or null if none. Note that this network
     *       may have disconnected already.
     *   <li>{@link #EXTRA_UNDERLYING_NETWORK_CAPABILITIES}, a {@link NetworkCapabilities} for
     *       the underlying network at the time the error occurred.
     *   <li>{@link #EXTRA_UNDERLYING_LINK_PROPERTIES}, a {@link LinkProperties} for the underlying
     *       network at the time the error occurred.
     * </ul>
     * When this event is an error, either {@link #CATEGORY_EVENT_IKE_ERROR} or
     * {@link #CATEGORY_EVENT_NETWORK_ERROR}, the following extras will be populated :
     * <ul>
     *   <li>{@link #EXTRA_ERROR_CLASS}, an {@code int} for the class of error, either
     *       {@link #ERROR_CLASS_RECOVERABLE} or {@link #ERROR_CLASS_NOT_RECOVERABLE}.
     *   <li>{@link #EXTRA_ERROR_CODE}, an {@code int} error code specific to the error. See
     *       {@link #EXTRA_ERROR_CODE} for details.
     * </ul>
     */
    public static final String ACTION_VPN_MANAGER_EVENT = "android.net.action.VPN_MANAGER_EVENT";

    /**
     * The always-on state of this VPN was changed
     *
     * <p>This may be the result of a user changing VPN settings, or a Device Policy Manager app
     * having changed the VPN policy.
     */
    public static final String CATEGORY_EVENT_ALWAYS_ON_STATE_CHANGED = "android.net.category.EVENT_ALWAYS_ON_STATE_CHANGED";

    /**
     * The user deactivated the VPN.
     *
     * This can happen either when the user turns the VPN off explicitly, or when they select
     * a different VPN provider.
     */
    public static final String CATEGORY_EVENT_DEACTIVATED_BY_USER = "android.net.category.EVENT_DEACTIVATED_BY_USER";

    /**
     * An IKE protocol error occurred.
     *
     * Codes (in {@link #EXTRA_ERROR_CODE}) are the codes from
     * {@link android.net.ipsec.ike.exceptions.IkeProtocolException}, as defined by IANA in
     * "IKEv2 Notify Message Types - Error Types".
     */
    public static final String CATEGORY_EVENT_IKE_ERROR = "android.net.category.EVENT_IKE_ERROR";

    /**
     * A network error occurred.
     *
     * Error codes (in {@link #EXTRA_ERROR_CODE}) are ERROR_CODE_NETWORK_*.
     */
    public static final String CATEGORY_EVENT_NETWORK_ERROR = "android.net.category.EVENT_NETWORK_ERROR";

    /**
     * {@link #EXTRA_ERROR_CLASS} coding for a non-recoverable error.
     *
     * This error is fatal, e.g. configuration error. The stack will not retry connection.
     */
    public static final int ERROR_CLASS_NOT_RECOVERABLE = 1;

    /**
     * {@link #EXTRA_ERROR_CLASS} coding for a recoverable error.
     *
     * The stack experienced an error but will retry with exponential backoff, e.g. network timeout.
     */
    public static final int ERROR_CLASS_RECOVERABLE = 2;

    /**
     * An {@link #EXTRA_ERROR_CODE} for {@link #CATEGORY_EVENT_NETWORK_ERROR} indicating an
     * input/output error.
     *
     * This code happens when reading or writing to sockets on the underlying networks was
     * terminated by an I/O error. {@see IOException}.
     */
    public static final int ERROR_CODE_NETWORK_IO = 3;

    /**
     * An {@link #EXTRA_ERROR_CODE} for {@link #CATEGORY_EVENT_NETWORK_ERROR} indicating that
     * network connectivity was lost.
     *
     * The most common reason for this error is that the underlying network was disconnected,
     * {@see android.net.ipsec.ike.exceptions.IkeNetworkLostException}.
     */
    public static final int ERROR_CODE_NETWORK_LOST = 2;

    /**
     * An {@link #EXTRA_ERROR_CODE} for {@link #CATEGORY_EVENT_NETWORK_ERROR} indicating a timeout.
     *
     * For Ikev2 VPNs, this happens typically after a retransmission failure.
     * {@see android.net.ipsec.ike.exceptions.IkeTimeoutException}
     */
    public static final int ERROR_CODE_NETWORK_PROTOCOL_TIMEOUT = 1;

    /**
     * An {@link #EXTRA_ERROR_CODE} for {@link #CATEGORY_EVENT_NETWORK_ERROR} to indicate that the
     * network host isn't known.
     *
     * This happens when domain name resolution could not resolve an IP address for the
     * specified host. {@see java.net.UnknownHostException}
     */
    public static final int ERROR_CODE_NETWORK_UNKNOWN_HOST = 0;

    /**
     * Extra for the error class, as an {@code int}.
     *
     * This is always either {@link #ERROR_CLASS_NOT_RECOVERABLE} or
     * {@link #ERROR_CLASS_RECOVERABLE}. This extra is only populated for error categories.
     */
    public static final String EXTRA_ERROR_CLASS = "android.net.extra.ERROR_CLASS";

    /**
     * Extra for an error code, as an {@code int}.
     *
     * <ul>
     *   <li>For {@link #CATEGORY_EVENT_NETWORK_ERROR}, this is one of the
     *       {@code ERROR_CODE_NETWORK_*} constants.
     *   <li>For {@link #CATEGORY_EVENT_IKE_ERROR}, this is one of values defined in
     *       {@link android.net.ipsec.ike.exceptions.IkeProtocolException}.ERROR_TYPE_*.
     * </ul>
     * For non-error categories, this extra is not populated.
     */
    public static final String EXTRA_ERROR_CODE = "android.net.extra.ERROR_CODE";

    /**
     * The key of the session that experienced this event, as a {@code String}.
     *
     * This is the same key that was returned by {@link #startProvisionedVpnProfileSession}.
     */
    public static final String EXTRA_SESSION_KEY = "android.net.extra.SESSION_KEY";

    /**
     * A {@code long} timestamp containing the time at which the event occurred.
     *
     * This is a number of milliseconds since the epoch, suitable to be compared with
     * {@link java.lang.System#currentTimeMillis}.
     */
    public static final String EXTRA_TIMESTAMP_MILLIS = "android.net.extra.TIMESTAMP_MILLIS";

    /**
     * The {@link LinkProperties} of the underlying network when the event occurred.
     *
     * <p>This extra will be null if there was no underlying network at the time of the event, or
     *    the underlying network has no bearing on the event, as in the case of:
     * <ul>
     *   <li>CATEGORY_EVENT_DEACTIVATED_BY_USER
     *   <li>CATEGORY_EVENT_ALWAYS_ON_STATE_CHANGED
     * </ul>
     */
    public static final String EXTRA_UNDERLYING_LINK_PROPERTIES = "android.net.extra.UNDERLYING_LINK_PROPERTIES";

    /**
     * The network that was underlying the VPN when the event occurred, as a {@link Network}.
     *
     * <p>This extra will be null if there was no underlying network at the time of the event, or
     *    the underlying network has no bearing on the event, as in the case of:
     * <ul>
     *   <li>CATEGORY_EVENT_DEACTIVATED_BY_USER
     *   <li>CATEGORY_EVENT_ALWAYS_ON_STATE_CHANGED
     * </ul>
     */
    public static final String EXTRA_UNDERLYING_NETWORK = "android.net.extra.UNDERLYING_NETWORK";

    /**
     * The {@link NetworkCapabilities} of the underlying network when the event occurred.
     *
     * <p>This extra will be null if there was no underlying network at the time of the event, or
     *    the underlying network has no bearing on the event, as in the case of:
     * <ul>
     *   <li>CATEGORY_EVENT_DEACTIVATED_BY_USER
     *   <li>CATEGORY_EVENT_ALWAYS_ON_STATE_CHANGED
     * </ul>
     */
    public static final String EXTRA_UNDERLYING_NETWORK_CAPABILITIES = "android.net.extra.UNDERLYING_NETWORK_CAPABILITIES";

    /**
     * The VpnProfileState at the time that this event occurred.
     *
     * <p>This extra may be null if the VPN was revoked by the user, or the profile was deleted.
     */
    public static final String EXTRA_VPN_PROFILE_STATE = "android.net.extra.VPN_PROFILE_STATE";

    private VpnManager() {
    }
}
