package android.net.wifi.aware;

/**
 *  This class provides the primary API for managing Wi-Fi Aware operations:
 *  discovery and peer-to-peer data connections.
 *  <p>
 *  The class provides access to:
 *  <ul>
 *  <li>Initialize a Aware cluster (peer-to-peer synchronization). Refer to
 *  {@link #attach(AttachCallback, Handler)}.
 *  <li>Create discovery sessions (publish or subscribe sessions). Refer to
 *  {@link WifiAwareSession#publish(PublishConfig, DiscoverySessionCallback, Handler)} and
 *  {@link WifiAwareSession#subscribe(SubscribeConfig, DiscoverySessionCallback, Handler)}.
 *  <li>Create a Aware network specifier to be used with
 *  {@link ConnectivityManager#requestNetwork(NetworkRequest, ConnectivityManager.NetworkCallback)}
 *  to set-up a Aware connection with a peer. Refer to {@link WifiAwareNetworkSpecifier.Builder}.
 *  </ul>
 *  <p>
 *      Aware may not be usable when Wi-Fi is disabled (and other conditions). To validate that
 *      the functionality is available use the {@link #isAvailable()} function. To track
 *      changes in Aware usability register for the {@link #ACTION_WIFI_AWARE_STATE_CHANGED}
 *      broadcast. Note that this broadcast is not sticky - you should register for it and then
 *      check the above API to avoid a race condition.
 *  <p>
 *      An application must use {@link #attach(AttachCallback, Handler)} to initialize a
 *      Aware cluster - before making any other Aware operation. Aware cluster membership is a
 *      device-wide operation - the API guarantees that the device is in a cluster or joins a
 *      Aware cluster (or starts one if none can be found). Information about attach success (or
 *      failure) are returned in callbacks of {@link AttachCallback}. Proceed with Aware
 *      discovery or connection setup only after receiving confirmation that Aware attach
 *      succeeded - {@link AttachCallback#onAttached(WifiAwareSession)}. When an
 *      application is finished using Aware it <b>must</b> use the
 *      {@link WifiAwareSession#close()} API to indicate to the Aware service that the device
 *      may detach from the Aware cluster. The device will actually disable Aware once the last
 *      application detaches.
 *  <p>
 *      Once a Aware attach is confirmed use the
 *      {@link WifiAwareSession#publish(PublishConfig, DiscoverySessionCallback, Handler)}
 *      or
 *      {@link WifiAwareSession#subscribe(SubscribeConfig, DiscoverySessionCallback,
 *      Handler)} to create publish or subscribe Aware discovery sessions. Events are called on the
 *      provided callback object {@link DiscoverySessionCallback}. Specifically, the
 *      {@link DiscoverySessionCallback#onPublishStarted(PublishDiscoverySession)}
 *      and
 *      {@link DiscoverySessionCallback#onSubscribeStarted(
 * SubscribeDiscoverySession)}
 *      return {@link PublishDiscoverySession} and
 *      {@link SubscribeDiscoverySession}
 *      objects respectively on which additional session operations can be performed, e.g. updating
 *      the session {@link PublishDiscoverySession#updatePublish(PublishConfig)} and
 *      {@link SubscribeDiscoverySession#updateSubscribe(SubscribeConfig)}. Sessions can
 *      also be used to send messages using the
 *      {@link DiscoverySession#sendMessage(PeerHandle, int, byte[])} APIs. When an
 *      application is finished with a discovery session it <b>must</b> terminate it using the
 *      {@link DiscoverySession#close()} API.
 *  <p>
 *     Creating connections between Aware devices is managed by the standard
 *     {@link ConnectivityManager#requestNetwork(NetworkRequest,
 *     ConnectivityManager.NetworkCallback)}.
 *     The {@link NetworkRequest} object should be constructed with:
 *     <ul>
 *         <li>{@link NetworkRequest.Builder#addTransportType(int)} of
 *         {@link android.net.NetworkCapabilities#TRANSPORT_WIFI_AWARE}.
 *         <li>{@link NetworkRequest.Builder#setNetworkSpecifier(String)} using
 *         {@link WifiAwareNetworkSpecifier.Builder}.
 *     </ul>
 */
public final class WifiAwareManager {
    /**
     * Intent broadcast sent whenever Wi-Fi Aware resource availability has changed. The resources
     * are attached with the {@link #EXTRA_AWARE_RESOURCES} extra. The resources can also be
     * obtained using the {@link #getAvailableAwareResources()} method. To receive this broadcast,
     * apps must hold {@link android.Manifest.permission#ACCESS_WIFI_STATE}.
     * <p>Note: The broadcast is only delivered to registered receivers - no manifest registered
     * components will be launched.
     */
    public static final String ACTION_WIFI_AWARE_RESOURCE_CHANGED = "android.net.wifi.aware.action.WIFI_AWARE_RESOURCE_CHANGED";

    /**
     * Broadcast intent action to indicate that the state of Wi-Fi Aware availability has changed
     * and all active Aware sessions are no longer usable. Use the {@link #isAvailable()} to query
     * the current status.
     * This broadcast is <b>not</b> sticky, use the {@link #isAvailable()} API after registering
     * the broadcast to check the current state of Wi-Fi Aware.
     * <p>Note: The broadcast is only delivered to registered receivers - no manifest registered
     * components will be launched.
     */
    public static final String ACTION_WIFI_AWARE_STATE_CHANGED = "android.net.wifi.aware.action.WIFI_AWARE_STATE_CHANGED";

    /**
     * Sent as a part of {@link #ACTION_WIFI_AWARE_RESOURCE_CHANGED} that contains an instance of
     * {@link AwareResources} representing the current Wi-Fi Aware resources.
     */
    public static final String EXTRA_AWARE_RESOURCES = "android.net.wifi.aware.extra.AWARE_RESOURCES";

    /**
     * Connection creation role is that of INITIATOR. Used to create a network specifier string
     * when requesting a Aware network.
     *
     * @see WifiAwareSession#createNetworkSpecifierOpen(int, byte[])
     * @see WifiAwareSession#createNetworkSpecifierPassphrase(int, byte[], String)
     */
    public static final int WIFI_AWARE_DATA_PATH_ROLE_INITIATOR = 0;

    /**
     * Connection creation role is that of RESPONDER. Used to create a network specifier string
     * when requesting a Aware network.
     *
     * @see WifiAwareSession#createNetworkSpecifierOpen(int, byte[])
     * @see WifiAwareSession#createNetworkSpecifierPassphrase(int, byte[], String)
     */
    public static final int WIFI_AWARE_DATA_PATH_ROLE_RESPONDER = 1;

    /**
     * Reason code provided in {@link DiscoverySessionCallback#onServiceLost(PeerHandle, int)}
     * indicating that the service advertised by the peer is no longer visible. This may be because
     * the peer is out of range or because the peer stopped advertising this service.
     */
    public static final int WIFI_AWARE_DISCOVERY_LOST_REASON_PEER_NOT_VISIBLE = 1;

    /**
     * Reason code provided in {@link DiscoverySessionCallback#onServiceLost(PeerHandle, int)}
     * indicating that the service was lost for unknown reason.
     */
    public static final int WIFI_AWARE_DISCOVERY_LOST_REASON_UNKNOWN = 0;

    private WifiAwareManager() {
    }
}
