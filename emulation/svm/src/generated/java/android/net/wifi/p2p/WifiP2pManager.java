package android.net.wifi.p2p;

/**
 * This class provides the API for managing Wi-Fi peer-to-peer connectivity. This lets an
 * application discover available peers, setup connection to peers and query for the list of peers.
 * When a p2p connection is formed over wifi, the device continues to maintain the uplink
 * connection over mobile or any other available network for internet connectivity on the device.
 *
 * <p> The API is asynchronous and responses to requests from an application are on listener
 * callbacks provided by the application. The application needs to do an initialization with
 * {@link #initialize} before doing any p2p operation.
 *
 * <p> Most application calls need a {@link ActionListener} instance for receiving callbacks
 * {@link ActionListener#onSuccess} or {@link ActionListener#onFailure}. Action callbacks
 * indicate whether the initiation of the action was a success or a failure.
 * Upon failure, the reason of failure can be one of {@link #ERROR}, {@link #P2P_UNSUPPORTED}
 * or {@link #BUSY}.
 *
 * <p> An application can initiate discovery of peers with {@link #discoverPeers}. An initiated
 * discovery request from an application stays active until the device starts connecting to a peer
 * ,forms a p2p group or there is an explicit {@link #stopPeerDiscovery}.
 * Applications can listen to {@link #WIFI_P2P_DISCOVERY_CHANGED_ACTION} to know if a peer-to-peer
 * discovery is running or stopped. Additionally, {@link #WIFI_P2P_PEERS_CHANGED_ACTION} indicates
 * if the peer list has changed.
 *
 * <p> When an application needs to fetch the current list of peers, it can request the list
 * of peers with {@link #requestPeers}. When the peer list is available
 * {@link PeerListListener#onPeersAvailable} is called with the device list.
 *
 * <p> An application can initiate a connection request to a peer through {@link #connect}. See
 * {@link WifiP2pConfig} for details on setting up the configuration. For communication with legacy
 * Wi-Fi devices that do not support p2p, an app can create a group using {@link #createGroup}
 * which creates an access point whose details can be fetched with {@link #requestGroupInfo}.
 *
 * <p> After a successful group formation through {@link #createGroup} or through {@link #connect},
 * use {@link #requestConnectionInfo} to fetch the connection details. The connection info
 * {@link WifiP2pInfo} contains the address of the group owner
 * {@link WifiP2pInfo#groupOwnerAddress} and a flag {@link WifiP2pInfo#isGroupOwner} to indicate
 * if the current device is a p2p group owner. A p2p client can thus communicate with
 * the p2p group owner through a socket connection. If the current device is the p2p group owner,
 * {@link WifiP2pInfo#groupOwnerAddress} is anonymized unless the caller holds the
 * {@code android.Manifest.permission#LOCAL_MAC_ADDRESS} permission.
 *
 * <p> With peer discovery using {@link  #discoverPeers}, an application discovers the neighboring
 * peers, but has no good way to figure out which peer to establish a connection with. For example,
 * if a game application is interested in finding all the neighboring peers that are also running
 * the same game, it has no way to find out until after the connection is setup. Pre-association
 * service discovery is meant to address this issue of filtering the peers based on the running
 * services.
 *
 * <p>With pre-association service discovery, an application can advertise a service for a
 * application on a peer device prior to a connection setup between the devices.
 * Currently, DNS based service discovery (Bonjour) and Upnp are the higher layer protocols
 * supported. Get Bonjour resources at dns-sd.org and Upnp resources at upnp.org
 * As an example, a video application can discover a Upnp capable media renderer
 * prior to setting up a Wi-fi p2p connection with the device.
 *
 * <p> An application can advertise a Upnp or a Bonjour service with a call to
 * {@link #addLocalService}. After a local service is added,
 * the framework automatically responds to a peer application discovering the service prior
 * to establishing a p2p connection. A call to {@link #removeLocalService} removes a local
 * service and {@link #clearLocalServices} can be used to clear all local services.
 *
 * <p> An application that is looking for peer devices that support certain services
 * can do so with a call to  {@link #discoverServices}. Prior to initiating the discovery,
 * application can add service discovery request with a call to {@link #addServiceRequest},
 * remove a service discovery request with a call to {@link #removeServiceRequest} or clear
 * all requests with a call to {@link #clearServiceRequests}. When no service requests remain,
 * a previously running service discovery will stop.
 *
 * The application is notified of a result of service discovery request through listener callbacks
 * set through {@link #setDnsSdResponseListeners} for Bonjour or
 * {@link #setUpnpServiceResponseListener} for Upnp.
 *
 * <p class="note"><strong>Note:</strong>
 * Registering an application handler with {@link #initialize} requires the permissions
 * {@link android.Manifest.permission#ACCESS_WIFI_STATE} and
 * {@link android.Manifest.permission#CHANGE_WIFI_STATE} to perform any further peer-to-peer
 * operations.
 *
 * {@see WifiP2pConfig}
 * {@see WifiP2pInfo}
 * {@see WifiP2pGroup}
 * {@see WifiP2pDevice}
 * {@see WifiP2pDeviceList}
 * {@see android.net.wifi.WpsInfo}
 */
public final class WifiP2pManager {
    /**
     * Broadcast intent action indicating that peer listen has either started or stopped.
     * One extra {@link #EXTRA_LISTEN_STATE} indicates whether listen has started or stopped.
     */
    public static final String ACTION_WIFI_P2P_LISTEN_STATE_CHANGED = "android.net.wifi.p2p.action.WIFI_P2P_LISTEN_STATE_CHANGED";

    /**
     * Broadcast intent action indicating whether or not current connecting
     * request is accepted.
     *
     * The connecting request is initiated by
     * {@link #connect(Channel, WifiP2pConfig, ActionListener)}.
     * <p>The {@link #EXTRA_REQUEST_RESPONSE} extra indicates whether or not current
     * request is accepted or rejected.
     * <p>The {@link #EXTRA_REQUEST_CONFIG} extra indicates the responsed configuration.
     */
    public static final String ACTION_WIFI_P2P_REQUEST_RESPONSE_CHANGED = "android.net.wifi.p2p.action.WIFI_P2P_REQUEST_RESPONSE_CHANGED";

    /**
     * Passed with {@link ActionListener#onFailure}.
     * Indicates that the operation failed because the framework is busy and
     * unable to service the request
     */
    public static final int BUSY = 2;

    /**
     * Accept the incoming request.
     *
     * Used in {@link #setConnectionRequestResult(Channel, MacAddress, int, ActionListener)}.
     */
    public static final int CONNECTION_REQUEST_ACCEPT = 0;

    /**
     * Defer the PIN display to the Wi-Fi service (which will display a dialog to the user).
     *
     * Used in {@link #setConnectionRequestResult(Channel, MacAddress, int, ActionListener)}.
     */
    public static final int CONNECTION_REQUEST_DEFER_SHOW_PIN_TO_SERVICE = 3;

    /**
     * Defer the decision back to the Wi-Fi service (which will display a dialog to the user).
     *
     * Used in {@link #setConnectionRequestResult(Channel, MacAddress, int, ActionListener)}.
     */
    public static final int CONNECTION_REQUEST_DEFER_TO_SERVICE = 2;

    /**
     * Reject the incoming request.
     *
     * Used in {@link #setConnectionRequestResult(Channel, MacAddress, int, ActionListener)}.
     */
    public static final int CONNECTION_REQUEST_REJECT = 1;

    /**
     * Passed with {@link ActionListener#onFailure}.
     * Indicates that the operation failed due to an internal error.
     */
    public static final int ERROR = 0;

    /**
     * The lookup key for an int that indicates whether p2p discovery has started or stopped.
     * Retrieve it with {@link android.content.Intent#getIntExtra(String,int)}.
     *
     * @see #WIFI_P2P_DISCOVERY_STARTED
     * @see #WIFI_P2P_DISCOVERY_STOPPED
     */
    public static final String EXTRA_DISCOVERY_STATE = "discoveryState";

    /**
     * The lookup key for an int that indicates whether p2p listen has started or stopped.
     * Retrieve it with {@link android.content.Intent#getIntExtra(String,int)}.
     *
     * @see #WIFI_P2P_LISTEN_STARTED
     * @see #WIFI_P2P_LISTEN_STOPPED
     */
    public static final String EXTRA_LISTEN_STATE = "android.net.wifi.p2p.extra.LISTEN_STATE";

    /**
     * The lookup key for a {@link android.net.NetworkInfo} object associated with the
     * p2p network. Retrieve with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_NETWORK_INFO = "networkInfo";

    /**
     * The lookup key for a {@link android.net.wifi.p2p.WifiP2pDeviceList} object representing
     * the new peer list when {@link #WIFI_P2P_PEERS_CHANGED_ACTION} broadcast is sent.
     *
     * <p>Retrieve with {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_P2P_DEVICE_LIST = "wifiP2pDeviceList";

    /**
     * The lookup key for the {@link WifiP2pConfig} object of a request.
     */
    public static final String EXTRA_REQUEST_CONFIG = "android.net.wifi.p2p.extra.REQUEST_CONFIG";

    /**
     * The lookup key for the result of a request, true if accepted, false otherwise.
     */
    public static final String EXTRA_REQUEST_RESPONSE = "android.net.wifi.p2p.extra.REQUEST_RESPONSE";

    /**
     * The lookup key for a {@link android.net.wifi.p2p.WifiP2pDevice} object
     * Retrieve with {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_WIFI_P2P_DEVICE = "wifiP2pDevice";

    /**
     * The lookup key for a {@link android.net.wifi.p2p.WifiP2pGroup} object
     * associated with the p2p network. Retrieve with
     * {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_WIFI_P2P_GROUP = "p2pGroupInfo";

    /**
     * The lookup key for a {@link android.net.wifi.p2p.WifiP2pInfo} object
     * Retrieve with {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_WIFI_P2P_INFO = "wifiP2pInfo";

    /**
     * The lookup key for an int that indicates whether Wi-Fi p2p is enabled or disabled.
     * Retrieve it with {@link android.content.Intent#getIntExtra(String,int)}.
     *
     * @see #WIFI_P2P_STATE_DISABLED
     * @see #WIFI_P2P_STATE_ENABLED
     */
    public static final String EXTRA_WIFI_STATE = "wifi_p2p_state";

    /**
     * P2p group creation failed because the connection has been cancelled.
     * Used in {@link WifiP2pListener#onGroupCreationFailed(int reason)}.
     */
    public static final int GROUP_CREATION_FAILURE_REASON_CONNECTION_CANCELLED = 0;

    /**
     * P2p group creation failed because the group has been removed.
     * Used in {@link WifiP2pListener#onGroupCreationFailed(int reason)}.
     */
    public static final int GROUP_CREATION_FAILURE_REASON_GROUP_REMOVED = 4;

    /**
     * P2p group creation failed because invitation has failed.
     * Used in {@link WifiP2pListener#onGroupCreationFailed(int reason)}.
     */
    public static final int GROUP_CREATION_FAILURE_REASON_INVITATION_FAILED = 5;

    /**
     * P2p group creation failed because provision discovery has failed.
     * Used in {@link WifiP2pListener#onGroupCreationFailed(int reason)}.
     */
    public static final int GROUP_CREATION_FAILURE_REASON_PROVISION_DISCOVERY_FAILED = 3;

    /**
     * P2p group creation failed because it has timed out.
     * Used in {@link WifiP2pListener#onGroupCreationFailed(int reason)}.
     */
    public static final int GROUP_CREATION_FAILURE_REASON_TIMED_OUT = 1;

    /**
     * P2p group creation failed because user has rejected.
     * Used in {@link WifiP2pListener#onGroupCreationFailed(int reason)}.
     */
    public static final int GROUP_CREATION_FAILURE_REASON_USER_REJECTED = 2;

    /**
     * Passed with {@link ActionListener#onFailure}.
     * Indicates that the operation failed due to calling app doesn't have permission to call the
     * API.
     */
    public static final int NO_PERMISSION = 4;

    /**
     * Passed with {@link ActionListener#onFailure}.
     * Indicates that the {@link #discoverServices} failed because no service
     * requests are added. Use {@link #addServiceRequest} to add a service
     * request.
     */
    public static final int NO_SERVICE_REQUESTS = 3;

    /**
     * Passed with {@link ActionListener#onFailure}.
     * Indicates that the operation failed because p2p is unsupported on the device.
     */
    public static final int P2P_UNSUPPORTED = 1;

    /**
     * Broadcast intent action indicating that the state of Wi-Fi p2p connectivity
     * has changed. One extra {@link #EXTRA_WIFI_P2P_INFO} provides the p2p connection info in
     * the form of a {@link WifiP2pInfo} object. Another extra {@link #EXTRA_NETWORK_INFO} provides
     * the network info in the form of a {@link android.net.NetworkInfo}. A third extra provides
     * the details of the group and may contain a {@code null}.
     *
     * All of these permissions are required to receive this broadcast:
     * {@link android.Manifest.permission#ACCESS_WIFI_STATE} and either
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION} or
     * {@link android.Manifest.permission#NEARBY_WIFI_DEVICES}
     *
     * @see #EXTRA_WIFI_P2P_INFO
     * @see #EXTRA_NETWORK_INFO
     * @see #EXTRA_WIFI_P2P_GROUP
     */
    public static final String WIFI_P2P_CONNECTION_CHANGED_ACTION = "android.net.wifi.p2p.CONNECTION_STATE_CHANGE";

    /**
     * Broadcast intent action indicating that peer discovery has either started or stopped.
     * One extra {@link #EXTRA_DISCOVERY_STATE} indicates whether discovery has started
     * or stopped.
     *
     * <p>Note that discovery will be stopped during a connection setup. If the application tries
     * to re-initiate discovery during this time, it can fail.
     */
    public static final String WIFI_P2P_DISCOVERY_CHANGED_ACTION = "android.net.wifi.p2p.DISCOVERY_STATE_CHANGE";

    /**
     * p2p discovery has started
     *
     * @see #WIFI_P2P_DISCOVERY_CHANGED_ACTION
     */
    public static final int WIFI_P2P_DISCOVERY_STARTED = 2;

    /**
     * p2p discovery has stopped
     *
     * @see #WIFI_P2P_DISCOVERY_CHANGED_ACTION
     */
    public static final int WIFI_P2P_DISCOVERY_STOPPED = 1;

    /**
     * p2p listen has started
     *
     * @see #ACTION_WIFI_P2P_LISTEN_STATE_CHANGED
     */
    public static final int WIFI_P2P_LISTEN_STARTED = 2;

    /**
     * p2p listen has stopped
     *
     * @see #ACTION_WIFI_P2P_LISTEN_STATE_CHANGED
     */
    public static final int WIFI_P2P_LISTEN_STOPPED = 1;

    /**
     * Broadcast intent action indicating that the available peer list has changed. This
     * can be sent as a result of peers being found, lost or updated.
     *
     * All of these permissions are required to receive this broadcast:
     * {@link android.Manifest.permission#ACCESS_WIFI_STATE} and either
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION} or
     * {@link android.Manifest.permission#NEARBY_WIFI_DEVICES}
     *
     * <p> An extra {@link #EXTRA_P2P_DEVICE_LIST} provides the full list of
     * current peers. The full list of peers can also be obtained any time with
     * {@link #requestPeers}.
     *
     * @see #EXTRA_P2P_DEVICE_LIST
     */
    public static final String WIFI_P2P_PEERS_CHANGED_ACTION = "android.net.wifi.p2p.PEERS_CHANGED";

    /**
     * Run P2P scan on all channels.
     */
    public static final int WIFI_P2P_SCAN_FULL = 0;

    /**
     * Run P2P scan only on a specific channel.
     */
    public static final int WIFI_P2P_SCAN_SINGLE_FREQ = 2;

    /**
     * Run P2P scan only on social channels.
     */
    public static final int WIFI_P2P_SCAN_SOCIAL = 1;

    /**
     * Broadcast intent action to indicate whether Wi-Fi p2p is enabled or disabled. An
     * extra {@link #EXTRA_WIFI_STATE} provides the state information as int.
     *
     * @see #EXTRA_WIFI_STATE
     */
    public static final String WIFI_P2P_STATE_CHANGED_ACTION = "android.net.wifi.p2p.STATE_CHANGED";

    /**
     * Wi-Fi p2p is disabled.
     *
     * @see #WIFI_P2P_STATE_CHANGED_ACTION
     */
    public static final int WIFI_P2P_STATE_DISABLED = 1;

    /**
     * Wi-Fi p2p is enabled.
     *
     * @see #WIFI_P2P_STATE_CHANGED_ACTION
     */
    public static final int WIFI_P2P_STATE_ENABLED = 2;

    /**
     * Broadcast intent action indicating that this device details have changed.
     *
     * <p> An extra {@link #EXTRA_WIFI_P2P_DEVICE} provides this device details.
     * The valid device details can also be obtained with
     * {@link #requestDeviceInfo(Channel, DeviceInfoListener)} when p2p is enabled.
     * To get information notifications on P2P getting enabled refers
     * {@link #WIFI_P2P_STATE_ENABLED}.
     *
     * <p> The {@link #EXTRA_WIFI_P2P_DEVICE} extra contains an anonymized version of the device's
     * MAC address. Callers holding the {@code android.Manifest.permission#LOCAL_MAC_ADDRESS}
     * permission can use {@link #requestDeviceInfo} to obtain the actual MAC address of this
     * device.
     *
     * All of these permissions are required to receive this broadcast:
     * {@link android.Manifest.permission#ACCESS_WIFI_STATE} and either
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION} or
     * {@link android.Manifest.permission#NEARBY_WIFI_DEVICES}
     *
     * @see #EXTRA_WIFI_P2P_DEVICE
     */
    public static final String WIFI_P2P_THIS_DEVICE_CHANGED_ACTION = "android.net.wifi.p2p.THIS_DEVICE_CHANGED";

    private WifiP2pManager() {
    }

    /**
     * Interface for callback invocation when an incoming request is received.
     *
     * This callback is registered by
     * {@link #addExternalApprover(Channel, MacAddress, ExternalApproverRequestListener)}.
     */
    public interface ExternalApproverRequestListener {
        /**
         * Detached since the {@link WifiP2pManager} channel was closed, e.g.
         * by using {@link Channel#close()} method.
         *
         * Used in {@link #onDetached(MacAddress, int)}.
         */
        int APPROVER_DETACH_REASON_CLOSE = 3;

        /**
         * Detached due to a framework failure.
         *
         * Used in {@link #onDetached(MacAddress, int)}.
         */
        int APPROVER_DETACH_REASON_FAILURE = 1;

        /**
         * Detached by a call to
         * {@link #removeExternalApprover(Channel, MacAddress, ActionListener)}.
         *
         * Used in {@link #onDetached(MacAddress, int)}.
         */
        int APPROVER_DETACH_REASON_REMOVE = 0;

        /**
         * Detached when a new approver replaces an old one.
         *
         * Used in {@link #onDetached(MacAddress, int)}.
         */
        int APPROVER_DETACH_REASON_REPLACE = 2;

        /**
         * This device received an invitation request from GO to join the group.
         *
         * Used in {@link #onConnectionRequested(int, WifiP2pConfig, WifiP2pDevice)}.
         */
        int REQUEST_TYPE_INVITATION = 1;

        /**
         * This GO device received a request from a peer to join the group.
         *
         * Used in {@link #onConnectionRequested(int, WifiP2pConfig, WifiP2pDevice)}.
         */
        int REQUEST_TYPE_JOIN = 2;

        /**
         * This device received a negotiation request from another peer.
         *
         * Used in {@link #onConnectionRequested(int, WifiP2pConfig, WifiP2pDevice)}.
         */
        int REQUEST_TYPE_NEGOTIATION = 0;
    }
}
