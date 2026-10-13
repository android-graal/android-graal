package android.net.nsd;

/**
 * The Network Service Discovery Manager class provides the API to discover services
 * on a network. As an example, if device A and device B are connected over a Wi-Fi
 * network, a game registered on device A can be discovered by a game on device
 * B. Another example use case is an application discovering printers on the network.
 *
 * <p> The API currently supports DNS based service discovery and discovery is currently
 * limited to a local network over Multicast DNS. DNS service discovery is described at
 * http://files.dns-sd.org/draft-cheshire-dnsext-dns-sd.txt
 *
 * <p> The API is asynchronous, and responses to requests from an application are on listener
 * callbacks on a separate internal thread.
 *
 * <p> There are three main operations the API supports - registration, discovery and resolution.
 * <pre>
 *                          Application start
 *                                 |
 *                                 |
 *                                 |                  onServiceRegistered()
 *                     Register any local services  /
 *                      to be advertised with       \
 *                       registerService()            onRegistrationFailed()
 *                                 |
 *                                 |
 *                          discoverServices()
 *                                 |
 *                      Maintain a list to track
 *                        discovered services
 *                                 |
 *                                 |--------->
 *                                 |          |
 *                                 |      onServiceFound()
 *                                 |          |
 *                                 |     add service to list
 *                                 |          |
 *                                 |<----------
 *                                 |
 *                                 |--------->
 *                                 |          |
 *                                 |      onServiceLost()
 *                                 |          |
 *                                 |   remove service from list
 *                                 |          |
 *                                 |<----------
 *                                 |
 *                                 |
 *                                 | Connect to a service
 *                                 | from list ?
 *                                 |
 *                          resolveService()
 *                                 |
 *                         onServiceResolved()
 *                                 |
 *                     Establish connection to service
 *                     with the host and port information
 *
 * </pre>
 * An application that needs to advertise itself over a network for other applications to
 * discover it can do so with a call to {@link #registerService}. If Example is a http based
 * application that can provide HTML data to peer services, it can register a name "Example"
 * with service type "_http._tcp". A successful registration is notified with a callback to
 * {@link RegistrationListener#onServiceRegistered} and a failure to register is notified
 * over {@link RegistrationListener#onRegistrationFailed}
 *
 * <p> A peer application looking for http services can initiate a discovery for "_http._tcp"
 * with a call to {@link #discoverServices}. A service found is notified with a callback
 * to {@link DiscoveryListener#onServiceFound} and a service lost is notified on
 * {@link DiscoveryListener#onServiceLost}.
 *
 * <p> Once the peer application discovers the "Example" http service, and either needs to read the
 * attributes of the service or wants to receive data from the "Example" application, it can
 * initiate a resolve with {@link #resolveService} to resolve the attributes, host, and port
 * details. A successful resolve is notified on {@link ResolveListener#onServiceResolved} and a
 * failure is notified on {@link ResolveListener#onResolveFailed}.
 *
 * Applications can reserve for a service type at
 * http://www.iana.org/form/ports-service. Existing services can be found at
 * http://www.iana.org/assignments/service-names-port-numbers/service-names-port-numbers.xml
 *
 * @see NsdServiceInfo
 */
public final class NsdManager {
    /**
     * Broadcast intent action to indicate whether network service discovery is
     * enabled or disabled. An extra {@link #EXTRA_NSD_STATE} provides the state
     * information as int.
     *
     * @see #EXTRA_NSD_STATE
     */
    public static final String ACTION_NSD_STATE_CHANGED = "android.net.nsd.STATE_CHANGED";

    /**
     * The lookup key for an int that indicates whether network service discovery is enabled
     * or disabled. Retrieve it with {@link android.content.Intent#getIntExtra(String,int)}.
     *
     * @see #NSD_STATE_DISABLED
     * @see #NSD_STATE_ENABLED
     */
    public static final String EXTRA_NSD_STATE = "nsd_state";

    /**
     * Indicates that the operation failed because it is already active.
     */
    public static final int FAILURE_ALREADY_ACTIVE = 3;

    /**
     * Indicates that the service has failed to resolve because of bad parameters.
     *
     * This failure is passed with
     * {@link ServiceInfoCallback#onServiceInfoCallbackRegistrationFailed}.
     */
    public static final int FAILURE_BAD_PARAMETERS = 6;

    /**
     * Failures are passed with {@link RegistrationListener#onRegistrationFailed},
     * {@link RegistrationListener#onUnregistrationFailed},
     * {@link DiscoveryListener#onStartDiscoveryFailed},
     * {@link DiscoveryListener#onStopDiscoveryFailed} or {@link ResolveListener#onResolveFailed}.
     *
     * Indicates that the operation failed due to an internal error.
     */
    public static final int FAILURE_INTERNAL_ERROR = 0;

    /**
     * Indicates that the operation failed because the maximum outstanding
     * requests from the applications have reached.
     */
    public static final int FAILURE_MAX_LIMIT = 4;

    /**
     * Indicates that the stop operation failed because it is not running.
     * This failure is passed with {@link ResolveListener#onStopResolutionFailed}.
     */
    public static final int FAILURE_OPERATION_NOT_RUNNING = 5;

    /**
     * Network service discovery is disabled
     *
     * @see #ACTION_NSD_STATE_CHANGED
     */
    public static final int NSD_STATE_DISABLED = 1;

    /**
     * Network service discovery is enabled
     *
     * @see #ACTION_NSD_STATE_CHANGED
     */
    public static final int NSD_STATE_ENABLED = 2;

    /**
     * Dns based service discovery protocol
     */
    public static final int PROTOCOL_DNS_SD = 1;

    private NsdManager() {
    }
}
