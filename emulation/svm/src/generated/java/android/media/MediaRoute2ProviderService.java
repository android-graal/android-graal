package android.media;

/**
 * Base class for media route provider services.
 * <p>
 * Media route provider services are used to publish {@link MediaRoute2Info media routes} such as
 * speakers, TVs, etc. The routes are published by calling {@link #notifyRoutes(Collection)}.
 * Media apps which use {@link MediaRouter2} can request to play their media on the routes.
 * </p><p>
 * When {@link MediaRouter2 media router} wants to play media on a route,
 * {@link #onCreateSession(long, String, String, Bundle)} will be called to handle the request.
 * A session can be considered as a group of currently selected routes for each connection.
 * Create and manage the sessions by yourself, and notify the {@link RoutingSessionInfo
 * session infos} when there are any changes.
 * </p><p>
 * The system media router service will bind to media route provider services when a
 * {@link RouteDiscoveryPreference discovery preference} is registered via
 * a {@link MediaRouter2 media router} by an application. See
 * {@link #onDiscoveryPreferenceChanged(RouteDiscoveryPreference)} for the details.
 * </p>
 * Use {@link #notifyRequestFailed(long, int)} to notify the failure with previously received
 * request ID.
 */
public final class MediaRoute2ProviderService {
    /**
     * The request has failed since the request is not valid. For example, selecting a route
     * which is not selectable.
     *
     * @see #notifyRequestFailed(long, int)
     */
    public static final int REASON_INVALID_COMMAND = 4;

    /**
     * The request has failed due to a network error.
     *
     * @see #notifyRequestFailed(long, int)
     */
    public static final int REASON_NETWORK_ERROR = 2;

    /**
     * The request has failed since this service rejected the request.
     *
     * @see #notifyRequestFailed(long, int)
     */
    public static final int REASON_REJECTED = 1;

    /**
     * The request has failed since the requested route is no longer available.
     *
     * @see #notifyRequestFailed(long, int)
     */
    public static final int REASON_ROUTE_NOT_AVAILABLE = 3;

    /**
     * The request has failed due to unknown reason.
     *
     * @see #notifyRequestFailed(long, int)
     */
    public static final int REASON_UNKNOWN_ERROR = 0;

    /**
     * The request ID to pass {@link #notifySessionCreated(long, RoutingSessionInfo)}
     * when {@link MediaRoute2ProviderService} created a session although there was no creation
     * request.
     *
     * @see #notifySessionCreated(long, RoutingSessionInfo)
     */
    public static final long REQUEST_ID_NONE = 0L;

    /**
     * The {@link Intent} action that must be declared as handled by the service.
     * Put this in your manifest to provide media routes.
     */
    public static final String SERVICE_INTERFACE = "android.media.MediaRoute2ProviderService";

    private MediaRoute2ProviderService() {
    }
}
