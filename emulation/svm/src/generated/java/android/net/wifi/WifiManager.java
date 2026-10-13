package android.net.wifi;

import android.os.Parcelable;

/**
 * This class provides the primary API for managing all aspects of Wi-Fi
 * connectivity.
 * <p>
 * On releases before {@link android.os.Build.VERSION_CODES#N}, this object
 * should only be obtained from an {@linkplain Context#getApplicationContext()
 * application context}, and not from any other derived context to avoid memory
 * leaks within the calling process.
 * <p>
 * It deals with several categories of items:
 * </p>
 * <ul>
 * <li>The list of configured networks. The list can be viewed and updated, and
 * attributes of individual entries can be modified.</li>
 * <li>The currently active Wi-Fi network, if any. Connectivity can be
 * established or torn down, and dynamic information about the state of the
 * network can be queried.</li>
 * <li>Results of access point scans, containing enough information to make
 * decisions about what access point to connect to.</li>
 * <li>It defines the names of various Intent actions that are broadcast upon
 * any sort of change in Wi-Fi state.
 * </ul>
 * <p>
 * This is the API to use when performing Wi-Fi specific operations. To perform
 * operations that pertain to network connectivity at an abstract level, use
 * {@link android.net.ConnectivityManager}.
 * </p>
 */
public final class WifiManager {
    /**
     * Activity Action: Pick a Wi-Fi network to connect to.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    public static final String ACTION_PICK_WIFI_NETWORK = "android.net.wifi.PICK_WIFI_NETWORK";

    /**
     * If one of the removed suggestions is currently connected, trigger an immediate disconnect
     * after suggestions removal
     */
    public static final int ACTION_REMOVE_SUGGESTION_DISCONNECT = 2;

    /**
     * If one of the removed suggestions is currently connected, that network will be disconnected
     * after a short delay as opposed to immediately (which will be done by
     * {@link #ACTION_REMOVE_SUGGESTION_DISCONNECT}). The {@link ConnectivityManager} may call the
     * {@link NetworkCallback#onLosing(Network, int)} on such networks.
     */
    public static final int ACTION_REMOVE_SUGGESTION_LINGER = 1;

    /**
     * Activity Action: Show a system activity that allows the user to enable
     * scans to be available even with Wi-Fi turned off.
     *
     * <p>Notification of the result of this activity is posted using the
     * {@link android.app.Activity#onActivityResult} callback. The
     * <code>resultCode</code>
     * will be {@link android.app.Activity#RESULT_OK} if scan always mode has
     * been turned on or {@link android.app.Activity#RESULT_CANCELED} if the user
     * has rejected the request or an error has occurred.
     */
    public static final String ACTION_REQUEST_SCAN_ALWAYS_AVAILABLE = "android.net.wifi.action.REQUEST_SCAN_ALWAYS_AVAILABLE";

    /**
     * Directed broadcast intent action indicating that the device has connected to one of the
     * network suggestions provided by the app. This will be sent post connection to a network
     * which was created with {@link WifiNetworkSuggestion.Builder#setIsAppInteractionRequired(
     * boolean)}
     * flag set.
     * <p>
     * Note: The broadcast is sent to the app only if it holds
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION ACCESS_FINE_LOCATION} permission.
     *
     * @see #EXTRA_NETWORK_SUGGESTION
     */
    public static final String ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION = "android.net.wifi.action.WIFI_NETWORK_SUGGESTION_POST_CONNECTION";

    /**
     * Broadcast intent action indicating whether Wi-Fi scanning is currently available.
     * Available extras:
     * - {@link #EXTRA_SCAN_AVAILABLE}
     */
    public static final String ACTION_WIFI_SCAN_AVAILABILITY_CHANGED = "android.net.wifi.action.WIFI_SCAN_AVAILABILITY_CHANGED";

    /**
     * The bundle key string for the channel frequency in MHz.
     * See {@link #getChannelData(Executor, Consumer)}
     */
    public static final String CHANNEL_DATA_KEY_FREQUENCY_MHZ = "CHANNEL_DATA_KEY_FREQUENCY_MHZ";

    /**
     * The bundle key for the number of APs found on the corresponding channel specified by
     * {@link WifiManager#CHANNEL_DATA_KEY_FREQUENCY_MHZ}.
     * See {@link #getChannelData(Executor, Consumer)}
     */
    public static final String CHANNEL_DATA_KEY_NUM_AP = "CHANNEL_DATA_KEY_NUM_AP";

    /**
     * The error code if there was a problem authenticating.
     * @deprecated This is no longer supported.
     */
    @Deprecated
    public static final int ERROR_AUTHENTICATING = 1;

    /**
     * The lookup key for a String giving the BSSID of the access point to which
     * we are connected. No longer used.
     */
    @Deprecated
    public static final String EXTRA_BSSID = "bssid";

    /**
     * The lookup key for a {@link android.net.NetworkInfo} object associated with the
     * Wi-Fi network. Retrieve with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_NETWORK_INFO = "networkInfo";

    /**
     * Sent as as a part of {@link #ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION} that holds
     * an instance of {@link WifiNetworkSuggestion} corresponding to the connected network.
     */
    public static final String EXTRA_NETWORK_SUGGESTION = "android.net.wifi.extra.NETWORK_SUGGESTION";

    /**
     * The lookup key for an {@code int} giving the new RSSI in dBm.
     */
    public static final String EXTRA_NEW_RSSI = "newRssi";

    /**
     * The lookup key for a {@link SupplicantState} describing the new state
     * Retrieve with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     * @deprecated This is no longer supported.
     */
    @Deprecated
    public static final String EXTRA_NEW_STATE = "newState";

    /**
     * The previous Wi-Fi state.
     *
     * @see #EXTRA_WIFI_STATE
     */
    public static final String EXTRA_PREVIOUS_WIFI_STATE = "previous_wifi_state";

    /**
     * Lookup key for a {@code boolean} extra in intent {@link #SCAN_RESULTS_AVAILABLE_ACTION}
     * representing if the scan was successful or not.
     * Scans may fail for multiple reasons, these may include:
     * <ol>
     * <li>An app requested too many scans in a certain period of time.
     * This may lead to additional scan request rejections via "scan throttling" for both
     * foreground and background apps.
     * Note: Apps holding android.Manifest.permission.NETWORK_SETTINGS permission are
     * exempted from scan throttling.
     * </li>
     * <li>The device is idle and scanning is disabled.</li>
     * <li>Wifi hardware reported a scan failure.</li>
     * </ol>
     * @return true scan was successful, results are updated
     * @return false scan was not successful, results haven't been updated since previous scan
     */
    public static final String EXTRA_RESULTS_UPDATED = "resultsUpdated";

    /**
     * A boolean extra indicating whether scanning is currently available.
     * Sent in the broadcast {@link #ACTION_WIFI_SCAN_AVAILABILITY_CHANGED}.
     * Its value is true if scanning is currently available, false otherwise.
     */
    public static final String EXTRA_SCAN_AVAILABLE = "android.net.wifi.extra.SCAN_AVAILABLE";

    /**
     * The lookup key for a boolean that indicates whether a connection to
     * the supplicant daemon has been gained or lost. {@code true} means
     * a connection now exists.
     * Retrieve it with {@link android.content.Intent#getBooleanExtra(String,boolean)}.
     * @deprecated This is no longer supported.
     */
    @Deprecated
    public static final String EXTRA_SUPPLICANT_CONNECTED = "connected";

    /**
     * The lookup key for a {@link SupplicantState} describing the supplicant
     * error code if any
     * Retrieve with
     * {@link android.content.Intent#getIntExtra(String, int)}.
     * @see #ERROR_AUTHENTICATING
     * @deprecated This is no longer supported.
     */
    @Deprecated
    public static final String EXTRA_SUPPLICANT_ERROR = "supplicantError";

    /**
     * The lookup key for a {@link android.net.wifi.WifiInfo} object giving the
     * information about the access point to which we are connected.
     * No longer used.
     */
    @Deprecated
    public static final String EXTRA_WIFI_INFO = "wifiInfo";

    /**
     * The lookup key for an int that indicates whether Wi-Fi is enabled,
     * disabled, enabling, disabling, or unknown.  Retrieve it with
     * {@link android.content.Intent#getIntExtra(String,int)}.
     *
     * @see #WIFI_STATE_DISABLED
     * @see #WIFI_STATE_DISABLING
     * @see #WIFI_STATE_ENABLED
     * @see #WIFI_STATE_ENABLING
     * @see #WIFI_STATE_UNKNOWN
     */
    public static final String EXTRA_WIFI_STATE = "wifi_state";

    /**
     * Do not send the DHCP hostname to open networks.
     */
    public static final int FLAG_SEND_DHCP_HOSTNAME_RESTRICTION_OPEN = 1;

    /**
     * Do not send the DHCP hostname to secure network.
     */
    public static final int FLAG_SEND_DHCP_HOSTNAME_RESTRICTION_SECURE = 2;

    /**
     * The network IDs of the configured networks could have changed.
     */
    public static final String NETWORK_IDS_CHANGED_ACTION = "android.net.wifi.NETWORK_IDS_CHANGED";

    /**
     * Broadcast intent action indicating that the state of Wi-Fi connectivity
     * has changed. An extra provides the new state
     * in the form of a {@link android.net.NetworkInfo} object.  No network-related
     * permissions are required to subscribe to this broadcast.
     *
     * <p class="note">This broadcast is not delivered to manifest receivers in
     * applications that target API version 26 or later.
     * @see #EXTRA_NETWORK_INFO
     */
    public static final String NETWORK_STATE_CHANGED_ACTION = "android.net.wifi.STATE_CHANGE";

    /**
     * Allows the device to roam more quickly than the normal roaming mode.
     * Used in cases such as where APs are installed in a high density.
     */
    public static final int ROAMING_MODE_AGGRESSIVE = 2;

    /**
     * Roaming is disabled.
     */
    public static final int ROAMING_MODE_NONE = 0;

    /**
     * Chipset has roaming trigger capability based on the score calculated
     * using multiple parameters. If device is configured to this mode then it
     * will be using chipset's normal (default) roaming.
     */
    public static final int ROAMING_MODE_NORMAL = 1;

    /**
     * The RSSI (signal strength) has changed.
     *
     * Receiver Required Permission: android.Manifest.permission.ACCESS_WIFI_STATE
     * @see #EXTRA_NEW_RSSI
     */
    public static final String RSSI_CHANGED_ACTION = "android.net.wifi.RSSI_CHANGED";

    /**
     * An access point scan has completed, and results are available.
     * Call {@link #getScanResults()} to obtain the results.
     * The broadcast intent may contain an extra field with the key {@link #EXTRA_RESULTS_UPDATED}
     * and a {@code boolean} value indicating if the scan was successful.
     */
    public static final String SCAN_RESULTS_AVAILABLE_ACTION = "android.net.wifi.SCAN_RESULTS";

    /**
     * Reason code if local-only network connection attempt failed with association failure.
     */
    public static final int STATUS_LOCAL_ONLY_CONNECTION_FAILURE_ASSOCIATION = 1;

    /**
     * Reason code if local-only network connection attempt failed with an authentication failure.
     */
    public static final int STATUS_LOCAL_ONLY_CONNECTION_FAILURE_AUTHENTICATION = 2;

    /**
     * Reason code if local-only network connection attempt failed with an IP provisioning failure.
     */
    public static final int STATUS_LOCAL_ONLY_CONNECTION_FAILURE_IP_PROVISIONING = 3;

    /**
     * Reason code if local-only network connection attempt failed with AP not in range.
     */
    public static final int STATUS_LOCAL_ONLY_CONNECTION_FAILURE_NOT_FOUND = 4;

    /**
     * Reason code if local-only network connection attempt failed with AP not responding.
     */
    public static final int STATUS_LOCAL_ONLY_CONNECTION_FAILURE_NO_RESPONSE = 5;

    /**
     * Reason code if local-only network connection attempt failed with an unknown failure.
     */
    public static final int STATUS_LOCAL_ONLY_CONNECTION_FAILURE_UNKNOWN = 0;

    /**
     * Reason code if local-only network request rejected by the user.
     */
    public static final int STATUS_LOCAL_ONLY_CONNECTION_FAILURE_USER_REJECT = 6;

    /**
     * Reason code if one or more of the network suggestions added already exists in platform's
     * database.
     * Note: this code will not be returned with Android 11 as in-place modification is allowed,
     * please check {@link #addNetworkSuggestions(List)}.
     * @see WifiNetworkSuggestion#equals(Object)
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE = 3;

    /**
     * Reason code if the number of network suggestions provided by the app crosses the max
     * threshold set per app.
     * The framework will reject all suggestions provided by {@link #addNetworkSuggestions(List)} if
     * the total size exceeds the limit.
     * @see #getMaxNumberOfNetworkSuggestionsPerApp()
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_EXCEEDS_MAX_PER_APP = 4;

    /**
     * Reason code if one or more of the network suggestions added is invalid. Framework will reject
     * all the suggestions in the list.
     * The framework will reject all suggestions provided by {@link #addNetworkSuggestions(List)}
     * if one or more of them is invalid.
     * Please use {@link WifiNetworkSuggestion.Builder} to create network suggestions.
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_INVALID = 7;

    /**
     * Reason code if one or more of the network suggestions added is not allowed.
     * The framework will reject all suggestions provided by {@link #addNetworkSuggestions(List)}
     * if one or more of them is not allowed.
     * This error may be caused by suggestion is using SIM-based encryption method, but calling app
     * is not carrier privileged.
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_NOT_ALLOWED = 6;

    /**
     * Reason code if the user has disallowed "android:change_wifi_state" app-ops from the app.
     * @see android.app.AppOpsManager#unsafeCheckOp(String, int, String).
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_APP_DISALLOWED = 2;

    /**
     * Reason code if there was an internal error in the platform while processing the addition or
     * removal of suggestions.
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_INTERNAL = 1;

    /**
     * Reason code if one or more of the network suggestions removed does not exist in platform's
     * database.
     * The framework won't remove any suggestions if one or more of suggestions provided
     * by {@link #removeNetworkSuggestions(List)} does not exist in database.
     * @see WifiNetworkSuggestion#equals(Object)
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_REMOVE_INVALID = 5;

    /**
     * Reason code if {@link android.os.UserManager#DISALLOW_ADD_WIFI_CONFIG} user restriction
     * is set and calling app is restricted by device admin.
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_ERROR_RESTRICTED_BY_ADMIN = 8;

    /**
     * Reason code if all of the network suggestions were successfully added or removed.
     */
    public static final int STATUS_NETWORK_SUGGESTIONS_SUCCESS = 0;

    /**
     * Status code if the calling app was approved by virtue of being a carrier privileged app.
     *
     * @see android.telephony.TelephonyManager#hasCarrierPrivileges()
     */
    public static final int STATUS_SUGGESTION_APPROVAL_APPROVED_BY_CARRIER_PRIVILEGE = 4;

    /**
     * Status code if the calling app got the user approval for suggestions.
     */
    public static final int STATUS_SUGGESTION_APPROVAL_APPROVED_BY_USER = 2;

    /**
     * Status code if the calling app is still pending user approval for suggestions.
     */
    public static final int STATUS_SUGGESTION_APPROVAL_PENDING = 1;

    /**
     * Status code if the calling app suggestions were rejected by the user.
     */
    public static final int STATUS_SUGGESTION_APPROVAL_REJECTED_BY_USER = 3;

    /**
     * Status code if suggestion approval status is unknown, an App which hasn't made any
     * suggestions will get this code.
     */
    public static final int STATUS_SUGGESTION_APPROVAL_UNKNOWN = 0;

    /**
     * Reason code if suggested network connection attempt failed with association failure.
     */
    public static final int STATUS_SUGGESTION_CONNECTION_FAILURE_ASSOCIATION = 1;

    /**
     * Reason code if suggested network connection attempt failed with an authentication failure.
     */
    public static final int STATUS_SUGGESTION_CONNECTION_FAILURE_AUTHENTICATION = 2;

    /**
     * Reason code if suggested network connection attempt failed with an IP provision failure.
     */
    public static final int STATUS_SUGGESTION_CONNECTION_FAILURE_IP_PROVISIONING = 3;

    /**
     * Reason code if suggested network connection attempt failed with an unknown failure.
     */
    public static final int STATUS_SUGGESTION_CONNECTION_FAILURE_UNKNOWN = 0;

    /**
     * Broadcast intent action indicating that a connection to the supplicant has
     * been established (and it is now possible
     * to perform Wi-Fi operations) or the connection to the supplicant has been
     * lost. One extra provides the connection state as a boolean, where {@code true}
     * means CONNECTED.
     * @deprecated This is no longer supported.
     * @see #EXTRA_SUPPLICANT_CONNECTED
     */
    @Deprecated
    public static final String SUPPLICANT_CONNECTION_CHANGE_ACTION = "android.net.wifi.supplicant.CONNECTION_CHANGE";

    /**
     * Broadcast intent action indicating that the state of establishing a connection to
     * an access point has changed.One extra provides the new
     * {@link SupplicantState}. Note that the supplicant state is Wi-Fi specific, and
     * is not generally the most useful thing to look at if you are just interested in
     * the overall state of connectivity.
     * @see #EXTRA_NEW_STATE
     * @see #EXTRA_SUPPLICANT_ERROR
     * @deprecated This is no longer supported.
     */
    @Deprecated
    public static final String SUPPLICANT_STATE_CHANGED_ACTION = "android.net.wifi.supplicant.STATE_CHANGE";

    /**
     * Indicates an invalid SSID.
     */
    public static final String UNKNOWN_SSID = "<unknown ssid>";

    /**
     * Wi-Fi interface of type AP (access point Wi-Fi infrastructure device).
     */
    public static final int WIFI_INTERFACE_TYPE_AP = 1;

    /**
     * Wi-Fi interface of type Wi-Fi Aware (aka NAN).
     */
    public static final int WIFI_INTERFACE_TYPE_AWARE = 2;

    /**
     * Wi-Fi interface of type Wi-Fi Direct (aka P2P).
     */
    public static final int WIFI_INTERFACE_TYPE_DIRECT = 3;

    /**
     * Wi-Fi interface of type STA (station/client Wi-Fi infrastructure device).
     */
    public static final int WIFI_INTERFACE_TYPE_STA = 0;

    /**
     * In this Wi-Fi lock mode, Wi-Fi will be kept active,
     * and will behave normally, i.e., it will attempt to automatically
     * establish a connection to a remembered access point that is
     * within range, and will do periodic scans if there are remembered
     * access points but none are in range.
     *
     * @deprecated This API is non-functional and will have no impact.
     */
    @Deprecated
    public static final int WIFI_MODE_FULL = 1;

    /**
     * In this Wi-Fi lock mode, Wi-Fi will not go to power save.
     * This results in operating with low packet latency.
     * The lock is only active when the device is connected to an access point.
     * The lock is active even when the device screen is off or the acquiring application is
     * running in the background.
     * This mode will consume more power and hence should be used only
     * when there is a need for this tradeoff.
     * <p>
     * An example use case is when a voice connection needs to be
     * kept active even after the device screen goes off.
     * Holding a {@link #WIFI_MODE_FULL_HIGH_PERF} lock for the
     * duration of the voice call may improve the call quality.
     * <p>
     * When there is no support from the hardware, the {@link #WIFI_MODE_FULL_HIGH_PERF}
     * lock will have no impact.
     *
     * @deprecated The {@code WIFI_MODE_FULL_HIGH_PERF} is deprecated and is automatically replaced
     * with {@link #WIFI_MODE_FULL_LOW_LATENCY} with all the restrictions documented on that lock.
     * I.e. any request to the {@code WIFI_MODE_FULL_HIGH_PERF} will now obtain a
     * {@link #WIFI_MODE_FULL_LOW_LATENCY} lock instead.
     * Deprecation is due to the impact of {@code WIFI_MODE_FULL_HIGH_PERF} on power dissipation.
     * The {@link #WIFI_MODE_FULL_LOW_LATENCY} provides much of the same desired functionality with
     * less impact on power dissipation.
     */
    @Deprecated
    public static final int WIFI_MODE_FULL_HIGH_PERF = 3;

    /**
     * In this Wi-Fi lock mode, Wi-Fi will operate with a priority to achieve low latency.
     * {@link #WIFI_MODE_FULL_LOW_LATENCY} lock has the following limitations:
     * <ol>
     * <li>The lock is only active when the device is connected to an access point.</li>
     * <li>The lock is only active when the screen is on.</li>
     * <li>The lock is only active when the acquiring app is running in the foreground.</li>
     * </ol>
     * Low latency mode optimizes for reduced packet latency,
     * and as a result other performance measures may suffer when there are trade-offs to make:
     * <ol>
     * <li>Battery life may be reduced.</li>
     * <li>Throughput may be reduced.</li>
     * <li>Frequency of Wi-Fi scanning may be reduced. This may result in: </li>
     * <ul>
     * <li>The device may not roam or switch to the AP with highest signal quality.</li>
     * <li>Location accuracy may be reduced.</li>
     * </ul>
     * </ol>
     * <p>
     * Example use cases are real time gaming or virtual reality applications where
     * low latency is a key factor for user experience.
     * <p>
     * Note: For an app which acquires both {@link #WIFI_MODE_FULL_LOW_LATENCY} and
     * {@link #WIFI_MODE_FULL_HIGH_PERF} locks, {@link #WIFI_MODE_FULL_LOW_LATENCY}
     * lock will be effective when app is running in foreground and screen is on,
     * while the {@link #WIFI_MODE_FULL_HIGH_PERF} lock will take effect otherwise.
     */
    public static final int WIFI_MODE_FULL_LOW_LATENCY = 4;

    /**
     * In this Wi-Fi lock mode, Wi-Fi will be kept active,
     * but the only operation that will be supported is initiation of
     * scans, and the subsequent reporting of scan results. No attempts
     * will be made to automatically connect to remembered access points,
     * nor will periodic scans be automatically performed looking for
     * remembered access points. Scans must be explicitly requested by
     * an application in this mode.
     *
     * @deprecated This API is non-functional and will have no impact.
     */
    @Deprecated
    public static final int WIFI_MODE_SCAN_ONLY = 2;

    /**
     * Wi-Fi simultaneous connection to multiple internet-providing Wi-FI networks (APs) is enabled
     * and restricted to a single network on different bands (e.g. a DBS AP).
     *
     * @see #getStaConcurrencyForMultiInternetMode()
     */
    public static final int WIFI_MULTI_INTERNET_MODE_DBS_AP = 1;

    /**
     * Wi-Fi simultaneous connection to multiple internet-providing Wi-Fi networks (APs) is
     * disabled.
     *
     * @see #getStaConcurrencyForMultiInternetMode()
     */
    public static final int WIFI_MULTI_INTERNET_MODE_DISABLED = 0;

    /**
     * Wi-Fi simultaneous connection to multiple internet-providing Wi-Fi networks (APs) is enabled.
     * The device can connect to any networks/APs - it is just restricted to using different bands
     * for individual connections.
     *
     * @see #getStaConcurrencyForMultiInternetMode()
     */
    public static final int WIFI_MULTI_INTERNET_MODE_MULTI_AP = 2;

    /**
     * Broadcast intent action indicating that Wi-Fi has been enabled, disabled,
     * enabling, disabling, or unknown. One extra provides this state as an int.
     * Another extra provides the previous state, if available.  No network-related
     * permissions are required to subscribe to this broadcast.
     *
     * <p class="note">This broadcast is not delivered to manifest receivers in
     * applications that target API version 26 or later.
     *
     * @see #EXTRA_WIFI_STATE
     * @see #EXTRA_PREVIOUS_WIFI_STATE
     */
    public static final String WIFI_STATE_CHANGED_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";

    /**
     * Wi-Fi is disabled.
     *
     * @see #WIFI_STATE_CHANGED_ACTION
     * @see #getWifiState()
     */
    public static final int WIFI_STATE_DISABLED = 1;

    /**
     * Wi-Fi is currently being disabled. The state will change to {@link #WIFI_STATE_DISABLED} if
     * it finishes successfully.
     *
     * @see #WIFI_STATE_CHANGED_ACTION
     * @see #getWifiState()
     */
    public static final int WIFI_STATE_DISABLING = 0;

    /**
     * Wi-Fi is enabled.
     *
     * @see #WIFI_STATE_CHANGED_ACTION
     * @see #getWifiState()
     */
    public static final int WIFI_STATE_ENABLED = 3;

    /**
     * Wi-Fi is currently being enabled. The state will change to {@link #WIFI_STATE_ENABLED} if
     * it finishes successfully.
     *
     * @see #WIFI_STATE_CHANGED_ACTION
     * @see #getWifiState()
     */
    public static final int WIFI_STATE_ENABLING = 2;

    /**
     * Wi-Fi is in an unknown state. This state will occur when an error happens while enabling
     * or disabling.
     *
     * @see #WIFI_STATE_CHANGED_ACTION
     * @see #getWifiState()
     */
    public static final int WIFI_STATE_UNKNOWN = 4;

    /**
     * Authentication failure on WPS
     *  @deprecated This is deprecated
     */
    @Deprecated
    public static final int WPS_AUTH_FAILURE = 6;

    /**
     * WPS overlap detected
     *  @deprecated This is deprecated
     */
    @Deprecated
    public static final int WPS_OVERLAP_ERROR = 3;

    /**
     * WPS timed out
     *  @deprecated This is deprecated
     */
    @Deprecated
    public static final int WPS_TIMED_OUT = 7;

    /**
     * TKIP only prohibited
     *  @deprecated This is deprecated
     */
    @Deprecated
    public static final int WPS_TKIP_ONLY_PROHIBITED = 5;

    /**
     * WEP on WPS is prohibited
     *  @deprecated This is deprecated
     */
    @Deprecated
    public static final int WPS_WEP_PROHIBITED = 4;

    private WifiManager() {
    }

    /**
     * Provides the results of a call to {@link #addNetworkPrivileged(WifiConfiguration)}
     */
    public static final class AddNetworkResult implements Parcelable {
        /**
         * Generic failure code for adding a passpoint network.
         */
        public static final int STATUS_ADD_PASSPOINT_FAILURE = 3;

        /**
         * Generic failure code for adding a non-passpoint network.
         */
        public static final int STATUS_ADD_WIFI_CONFIG_FAILURE = 4;

        /**
         * The operation has failed due to an unknown reason.
         */
        public static final int STATUS_FAILURE_UNKNOWN = 1;

        /**
         * Internal failure in updating network keys..
         */
        public static final int STATUS_FAILURE_UPDATE_NETWORK_KEYS = 9;

        /**
         * The network configuration is invalid.
         */
        public static final int STATUS_INVALID_CONFIGURATION = 5;

        /**
         * The enterprise network is missing either the root CA or domain name.
         */
        public static final int STATUS_INVALID_CONFIGURATION_ENTERPRISE = 10;

        /**
         * The calling app does not have permission to call this API.
         */
        public static final int STATUS_NO_PERMISSION = 2;

        /**
         * The calling app has no permission to modify the configuration.
         */
        public static final int STATUS_NO_PERMISSION_MODIFY_CONFIG = 6;

        /**
         * The calling app has no permission to modify the MAC randomization setting.
         */
        public static final int STATUS_NO_PERMISSION_MODIFY_MAC_RANDOMIZATION = 8;

        /**
         * The calling app has no permission to modify the proxy setting.
         */
        public static final int STATUS_NO_PERMISSION_MODIFY_PROXY_SETTING = 7;

        /**
         * The operation has completed successfully.
         */
        public static final int STATUS_SUCCESS = 0;

        private AddNetworkResult() {
        }
    }

    /**
     * Callback class for applications to receive updates about the LocalOnlyHotspot status.
     */
    public static final class LocalOnlyHotspotCallback {
        public static final int ERROR_GENERIC = 2;

        public static final int ERROR_INCOMPATIBLE_MODE = 3;

        public static final int ERROR_NO_CHANNEL = 1;

        public static final int ERROR_TETHERING_DISALLOWED = 4;

        private LocalOnlyHotspotCallback() {
        }
    }
}
