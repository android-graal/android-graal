package android.net;

/**
 * Class that answers queries about the state of network connectivity. It also
 * notifies applications when network connectivity changes.
 * <p>
 * The primary responsibilities of this class are to:
 * <ol>
 * <li>Monitor network connections (Wi-Fi, GPRS, UMTS, etc.)</li>
 * <li>Send broadcast intents when network connectivity changes</li>
 * <li>Attempt to "fail over" to another network when connectivity to a network
 * is lost</li>
 * <li>Provide an API that allows applications to query the coarse-grained or fine-grained
 * state of the available networks</li>
 * <li>Provide an API that allows applications to request and select networks for their data
 * traffic</li>
 * </ol>
 */
public final class ConnectivityManager {
    /**
     * Broadcast Action: The setting for background data usage has changed
     * values. Use {@link #getBackgroundDataSetting()} to get the current value.
     * <p>
     * If an application uses the network in the background, it should listen
     * for this broadcast and stop using the background data if the value is
     * {@code false}.
     * <p>
     *
     * @deprecated As of {@link VERSION_CODES#ICE_CREAM_SANDWICH}, availability
     *             of background data depends on several combined factors, and
     *             this broadcast is no longer sent. Instead, when background
     *             data is unavailable, {@link #getActiveNetworkInfo()} will now
     *             appear disconnected. During first boot after a platform
     *             upgrade, this broadcast will be sent once if
     *             {@link #getBackgroundDataSetting()} was {@code false} before
     *             the upgrade.
     */
    @Deprecated
    public static final String ACTION_BACKGROUND_DATA_SETTING_CHANGED = "android.net.conn.BACKGROUND_DATA_SETTING_CHANGED";

    /**
     * The device has connected to a network that has presented a captive
     * portal, which is blocking Internet connectivity. The user was presented
     * with a notification that network sign in is required,
     * and the user invoked the notification's action indicating they
     * desire to sign in to the network. Apps handling this activity should
     * facilitate signing in to the network. This action includes a
     * {@link Network} typed extra called {@link #EXTRA_NETWORK} that represents
     * the network presenting the captive portal; all communication with the
     * captive portal must be done using this {@code Network} object.
     * <p/>
     * This activity includes a {@link CaptivePortal} extra named
     * {@link #EXTRA_CAPTIVE_PORTAL} that can be used to indicate different
     * outcomes of the captive portal sign in to the system:
     * <ul>
     * <li> When the app handling this action believes the user has signed in to
     * the network and the captive portal has been dismissed, the app should
     * call {@link CaptivePortal#reportCaptivePortalDismissed} so the system can
     * reevaluate the network. If reevaluation finds the network no longer
     * subject to a captive portal, the network may become the default active
     * data network.</li>
     * <li> When the app handling this action believes the user explicitly wants
     * to ignore the captive portal and the network, the app should call
     * {@link CaptivePortal#ignoreNetwork}. </li>
     * </ul>
     */
    public static final String ACTION_CAPTIVE_PORTAL_SIGN_IN = "android.net.conn.CAPTIVE_PORTAL";

    /**
     * A change in the background metered network activity restriction has occurred.
     * <p>
     * Applications should call {@link #getRestrictBackgroundStatus()} to check if the restriction
     * applies to them.
     * <p>
     * This is only sent to registered receivers, not manifest receivers.
     */
    public static final String ACTION_RESTRICT_BACKGROUND_CHANGED = "android.net.conn.RESTRICT_BACKGROUND_CHANGED";

    /**
     * A change in network connectivity has occurred. A default connection has either
     * been established or lost. The NetworkInfo for the affected network is
     * sent as an extra; it should be consulted to see what kind of
     * connectivity event occurred.
     * <p/>
     * Apps targeting Android 7.0 (API level 24) and higher do not receive this
     * broadcast if they declare the broadcast receiver in their manifest. Apps
     * will still receive broadcasts if they register their
     * {@link android.content.BroadcastReceiver} with
     * {@link android.content.Context#registerReceiver Context.registerReceiver()}
     * and that context is still valid.
     * <p/>
     * If this is a connection that was the result of failing over from a
     * disconnected network, then the FAILOVER_CONNECTION boolean extra is
     * set to true.
     * <p/>
     * For a loss of connectivity, if the connectivity manager is attempting
     * to connect (or has already connected) to another network, the
     * NetworkInfo for the new network is also passed as an extra. This lets
     * any receivers of the broadcast know that they should not necessarily
     * tell the user that no data traffic will be possible. Instead, the
     * receiver should expect another broadcast soon, indicating either that
     * the failover attempt succeeded (and so there is still overall data
     * connectivity), or that the failover attempt failed, meaning that all
     * connectivity has been lost.
     * <p/>
     * For a disconnect event, the boolean extra EXTRA_NO_CONNECTIVITY
     * is set to {@code true} if there are no connected networks at all.
     * <p />
     * Note that this broadcast is deprecated and generally tries to implement backwards
     * compatibility with older versions of Android. As such, it may not reflect new
     * capabilities of the system, like multiple networks being connected at the same
     * time, the details of newer technology, or changes in tethering state.
     *
     * @deprecated apps should use the more versatile {@link #requestNetwork},
     *             {@link #registerNetworkCallback} or {@link #registerDefaultNetworkCallback}
     *             functions instead for faster and more detailed updates about the network
     *             changes they care about.
     */
    @Deprecated
    public static final String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    /**
     * If you want to set the default network preference,you can directly
     * change the networkAttributes array in framework's config.xml.
     *
     * @deprecated Since we support so many more networks now, the single
     *             network default network preference can't really express
     *             the hierarchy.  Instead, the default is defined by the
     *             networkAttributes in config.xml.  You can determine
     *             the current value by calling {@link #getNetworkPreference()}
     *             from an App.
     */
    @Deprecated
    public static final int DEFAULT_NETWORK_PREFERENCE = 1;

    /**
     * The lookup key for a {@link CaptivePortal} object included with the
     * {@link #ACTION_CAPTIVE_PORTAL_SIGN_IN} intent.  The {@code CaptivePortal}
     * object can be used to either indicate to the system that the captive
     * portal has been dismissed or that the user does not want to pursue
     * signing in to captive portal.  Retrieve it with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_CAPTIVE_PORTAL = "android.net.extra.CAPTIVE_PORTAL";

    /**
     * Key for passing a URL to the captive portal login activity.
     */
    public static final String EXTRA_CAPTIVE_PORTAL_URL = "android.net.extra.CAPTIVE_PORTAL_URL";

    /**
     * The lookup key for a string that provides optionally supplied
     * extra information about the network state. The information
     * may be passed up from the lower networking layers, and its
     * meaning may be specific to a particular network type. Retrieve
     * it with {@link android.content.Intent#getStringExtra(String)}.
     *
     * @deprecated See {@link NetworkInfo#getExtraInfo()}.
     */
    @Deprecated
    public static final String EXTRA_EXTRA_INFO = "extraInfo";

    /**
     * The lookup key for a boolean that indicates whether a connect event
     * is for a network to which the connectivity manager was failing over
     * following a disconnect on another network.
     * Retrieve it with {@link android.content.Intent#getBooleanExtra(String,boolean)}.
     *
     * @deprecated See {@link NetworkInfo}.
     */
    @Deprecated
    public static final String EXTRA_IS_FAILOVER = "isFailover";

    /**
     * The lookup key for a {@link Network} object included with the intent after
     * successfully finding a network for the applications request.  Retrieve it with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     * <p>
     * Note that if you intend to invoke {@link Network#openConnection(java.net.URL)}
     * then you must get a ConnectivityManager instance before doing so.
     */
    public static final String EXTRA_NETWORK = "android.net.extra.NETWORK";

    /**
     * The lookup key for a {@link NetworkInfo} object. Retrieve with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     *
     * @deprecated The {@link NetworkInfo} object is deprecated, as many of its properties
     *             can't accurately represent modern network characteristics.
     *             Please obtain information about networks from the {@link NetworkCapabilities}
     *             or {@link LinkProperties} objects instead.
     */
    @Deprecated
    public static final String EXTRA_NETWORK_INFO = "networkInfo";

    /**
     * The lookup key for a {@link NetworkRequest} object included with the intent after
     * successfully finding a network for the applications request.  Retrieve it with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_NETWORK_REQUEST = "android.net.extra.NETWORK_REQUEST";

    /**
     * Network type which triggered a {@link #CONNECTIVITY_ACTION} broadcast.
     *
     * @see android.content.Intent#getIntExtra(String, int)
     * @deprecated The network type is not rich enough to represent the characteristics
     *             of modern networks. Please use {@link NetworkCapabilities} instead,
     *             in particular the transports.
     */
    @Deprecated
    public static final String EXTRA_NETWORK_TYPE = "networkType";

    /**
     * The lookup key for a boolean that indicates whether there is a
     * complete lack of connectivity, i.e., no network is available.
     * Retrieve it with {@link android.content.Intent#getBooleanExtra(String,boolean)}.
     */
    public static final String EXTRA_NO_CONNECTIVITY = "noConnectivity";

    /**
     * The lookup key for a {@link NetworkInfo} object. This is supplied when
     * there is another network that it may be possible to connect to. Retrieve with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     *
     * @deprecated See {@link NetworkInfo}.
     */
    @Deprecated
    public static final String EXTRA_OTHER_NETWORK_INFO = "otherNetwork";

    /**
     * The lookup key for a string that indicates why an attempt to connect
     * to a network failed. The string has no particular structure. It is
     * intended to be used in notifications presented to users. Retrieve
     * it with {@link android.content.Intent#getStringExtra(String)}.
     */
    public static final String EXTRA_REASON = "reason";

    /**
     * It is acceptable to briefly use multipath data to provide seamless connectivity for
     * time-sensitive user-facing operations when the system default network is temporarily
     * unresponsive. The amount of data should be limited (less than one megabyte for every call to
     * this method), and the operation should be infrequent to ensure that data usage is limited.
     *
     * An example of such an operation might be a time-sensitive foreground activity, such as a
     * voice command, that the user is performing while walking out of range of a Wi-Fi network.
     */
    public static final int MULTIPATH_PREFERENCE_HANDOVER = 1;

    /**
     * It is acceptable to use metered data to improve network latency and performance.
     */
    public static final int MULTIPATH_PREFERENCE_PERFORMANCE = 4;

    /**
     * It is acceptable to use small amounts of multipath data on an ongoing basis to provide
     * a backup channel for traffic that is primarily going over another network.
     *
     * An example might be maintaining backup connections to peers or servers for the purpose of
     * fast fallback if the default network is temporarily unresponsive or disconnects. The traffic
     * on backup paths should be negligible compared to the traffic on the main path.
     */
    public static final int MULTIPATH_PREFERENCE_RELIABILITY = 2;

    /**
     * Device is not restricting metered network activity while application is running on
     * background.
     */
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;

    /**
     * Device is restricting metered network activity while application is running on background.
     * <p>
     * In this state, application should not try to use the network while running on background,
     * because it would be denied.
     */
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;

    /**
     * Device is restricting metered network activity while application is running on background,
     * but application is allowed to bypass it.
     * <p>
     * In this state, application should take action to mitigate metered network access.
     * For example, a music streaming application should switch to a low-bandwidth bitrate.
     */
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    /**
     * A Bluetooth data connection.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasTransport} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request an
     *         appropriate network. See {@link NetworkCapabilities} for supported transports.
     */
    @Deprecated
    public static final int TYPE_BLUETOOTH = 7;

    /**
     * Fake data connection.  This should not be used on shipping devices.
     * @deprecated This is not used any more.
     */
    @Deprecated
    public static final int TYPE_DUMMY = 8;

    /**
     * An Ethernet data connection.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasTransport} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request an
     *         appropriate network. See {@link NetworkCapabilities} for supported transports.
     */
    @Deprecated
    public static final int TYPE_ETHERNET = 9;

    /**
     * A Mobile data connection. Devices may support more than one.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasTransport} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request an
     *         appropriate network. See {@link NetworkCapabilities} for supported transports.
     */
    @Deprecated
    public static final int TYPE_MOBILE = 0;

    /**
     * A DUN-specific Mobile data connection.  This network type may use the
     * same network interface as {@link #TYPE_MOBILE} or it may use a different
     * one.  This is sometimes by the system when setting up an upstream connection
     * for tethering so that the carrier is aware of DUN traffic.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasCapability} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request a network that
     *         provides the {@link NetworkCapabilities#NET_CAPABILITY_DUN} capability.
     */
    @Deprecated
    public static final int TYPE_MOBILE_DUN = 4;

    /**
     * A High Priority Mobile data connection.  This network type uses the
     * same network interface as {@link #TYPE_MOBILE} but the routing setup
     * is different.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasTransport} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request an
     *         appropriate network. See {@link NetworkCapabilities} for supported transports.
     */
    @Deprecated
    public static final int TYPE_MOBILE_HIPRI = 5;

    /**
     * An MMS-specific Mobile data connection.  This network type may use the
     * same network interface as {@link #TYPE_MOBILE} or it may use a different
     * one.  This is used by applications needing to talk to the carrier's
     * Multimedia Messaging Service servers.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasCapability} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request a network that
     *         provides the {@link NetworkCapabilities#NET_CAPABILITY_MMS} capability.
     */
    @Deprecated
    public static final int TYPE_MOBILE_MMS = 2;

    /**
     * A SUPL-specific Mobile data connection.  This network type may use the
     * same network interface as {@link #TYPE_MOBILE} or it may use a different
     * one.  This is used by applications needing to talk to the carrier's
     * Secure User Plane Location servers for help locating the device.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasCapability} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request a network that
     *         provides the {@link NetworkCapabilities#NET_CAPABILITY_SUPL} capability.
     */
    @Deprecated
    public static final int TYPE_MOBILE_SUPL = 3;

    /**
     * A virtual network using one or more native bearers.
     * It may or may not be providing security services.
     * @deprecated Applications should use {@link NetworkCapabilities#TRANSPORT_VPN} instead.
     */
    @Deprecated
    public static final int TYPE_VPN = 17;

    /**
     * A WIFI data connection. Devices may support more than one.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasTransport} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request an
     *         appropriate network. See {@link NetworkCapabilities} for supported transports.
     */
    @Deprecated
    public static final int TYPE_WIFI = 1;

    /**
     * A WiMAX data connection.
     *
     * @deprecated Applications should instead use {@link NetworkCapabilities#hasTransport} or
     *         {@link #requestNetwork(NetworkRequest, NetworkCallback)} to request an
     *         appropriate network. See {@link NetworkCapabilities} for supported transports.
     */
    @Deprecated
    public static final int TYPE_WIMAX = 6;

    private ConnectivityManager() {
    }

    /**
     * Base class for {@code NetworkRequest} callbacks. Used for notifications about network
     * changes. Should be extended by applications wanting notifications.
     *
     * A {@code NetworkCallback} is registered by calling
     * {@link #requestNetwork(NetworkRequest, NetworkCallback)},
     * {@link #registerNetworkCallback(NetworkRequest, NetworkCallback)},
     * or {@link #registerDefaultNetworkCallback(NetworkCallback)}. A {@code NetworkCallback} is
     * unregistered by calling {@link #unregisterNetworkCallback(NetworkCallback)}.
     * A {@code NetworkCallback} should be registered at most once at any time.
     * A {@code NetworkCallback} that has been unregistered can be registered again.
     */
    public static final class NetworkCallback {
        /**
         * Inclusion of this flag means location-sensitive redaction requests keeping location info.
         *
         * Some objects like {@link NetworkCapabilities} may contain location-sensitive information.
         * Prior to Android 12, this information is always returned to apps holding the appropriate
         * permission, possibly noting that the app has used location.
         * <p>In Android 12 and above, by default the sent objects do not contain any location
         * information, even if the app holds the necessary permissions, and the system does not
         * take note of location usage by the app. Apps can request that location information is
         * included, in which case the system will check location permission and the location
         * toggle state, and take note of location usage by the app if any such information is
         * returned.
         *
         * Use this flag to include any location sensitive data in {@link NetworkCapabilities} sent
         * via {@link #onCapabilitiesChanged(Network, NetworkCapabilities)}.
         * <p>
         * These include:
         * <li> Some transport info instances (retrieved via
         * {@link NetworkCapabilities#getTransportInfo()}) like {@link android.net.wifi.WifiInfo}
         * contain location sensitive information.
         * <li> OwnerUid (retrieved via {@link NetworkCapabilities#getOwnerUid()} is location
         * sensitive for wifi suggestor apps (i.e using
         * {@link android.net.wifi.WifiNetworkSuggestion WifiNetworkSuggestion}).</li>
         * </p>
         * <p>
         * Note:
         * <li> Retrieving this location sensitive information (subject to app's location
         * permissions) will be noted by system. </li>
         * <li> Without this flag any {@link NetworkCapabilities} provided via the callback does
         * not include location sensitive information.
         */
        public static final int FLAG_INCLUDE_LOCATION_INFO = 1;

        private NetworkCallback() {
        }
    }
}
