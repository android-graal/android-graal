package android.net.http;

/**
 * Controls an HTTP request (GET, PUT, POST etc).
 * Created by {@link UrlRequest.Builder}, which can be obtained by calling
 * {@link HttpEngine#newUrlRequestBuilder}.
 * Note: All methods must be called on the {@link Executor} passed to
 * {@link HttpEngine#newUrlRequestBuilder}.
 */
public final class UrlRequest {
    /**
     * Highest request priority. Passed to {@link Builder#setPriority}.
     */
    public static final int REQUEST_PRIORITY_HIGHEST = 4;

    /**
     * Lowest request priority. Passed to {@link Builder#setPriority}.
     */
    public static final int REQUEST_PRIORITY_IDLE = 0;

    /**
     * Low request priority. Passed to {@link Builder#setPriority}.
     */
    public static final int REQUEST_PRIORITY_LOW = 2;

    /**
     * Very low request priority. Passed to {@link Builder#setPriority}.
     */
    public static final int REQUEST_PRIORITY_LOWEST = 1;

    /**
     * Medium request priority. Passed to {@link Builder#setPriority}. This is the
     * default priority given to the request.
     */
    public static final int REQUEST_PRIORITY_MEDIUM = 3;

    private UrlRequest() {
    }

    /**
     * Request status values returned by {@link #getStatus}.
     */
    public static final class Status {
        /**
         * This state corresponds to a resource load that is blocked waiting for a TCP connection
         * (or other network connection) to be established. HTTP requests that reuse a keep-alive
         * connection skip this state.
         */
        public static final int CONNECTING = 10;

        /**
         * This state corresponds to a resource being blocked waiting for the PAC script to be
         * downloaded.
         */
        public static final int DOWNLOADING_PAC_FILE = 5;

        /**
         * This state indicates that we're in the process of establishing a tunnel through the proxy
         * server.
         */
        public static final int ESTABLISHING_PROXY_TUNNEL = 8;

        /**
         * This state corresponds to a resource load that has either not yet begun or is idle
         * waiting for the consumer to do something to move things along (e.g. when the consumer of
         * a {@link UrlRequest} has not called {@link UrlRequest#read read()} yet).
         */
        public static final int IDLE = 0;

        /**
         * This state indicates that the request is completed, canceled, or is not started.
         */
        public static final int INVALID = -1;

        /**
         * This state corresponds to a resource load that is blocked waiting for a read to complete.
         * In the case of a HTTP transaction, this corresponds to the period after the response
         * headers have been received and before all of the response body has been downloaded.
         * (NOTE: This state only applies for an {@link UrlRequest} while there is an outstanding
         * {@link UrlRequest#read read()} operation.)
         */
        public static final int READING_RESPONSE = 14;

        /**
         * This state corresponds to a resource load that is blocked waiting for a host name to be
         * resolved. This could either indicate resolution of the origin server corresponding to the
         * resource or to the host name of a proxy server used to fetch the resource.
         */
        public static final int RESOLVING_HOST = 9;

        /**
         * This state corresponds to a resource load that is blocked waiting for a proxy autoconfig
         * script to return a proxy server to use, but that proxy script is busy resolving the IP
         * address of a host.
         */
        public static final int RESOLVING_HOST_IN_PAC_FILE = 7;

        /**
         * This state corresponds to a resource load that is blocked waiting for a proxy autoconfig
         * script to return a proxy server to use.
         */
        public static final int RESOLVING_PROXY_FOR_URL = 6;

        /**
         * This state corresponds to a resource load that is blocked waiting to completely upload a
         * request to a server. In the case of a HTTP POST request, this state includes the period
         * of time during which the message body is being uploaded.
         */
        public static final int SENDING_REQUEST = 12;

        /**
         * This state corresponds to a resource load that is blocked waiting for the SSL handshake
         * to complete.
         */
        public static final int SSL_HANDSHAKE = 11;

        /**
         * When a socket pool group has reached the maximum number of sockets allowed per group,
         * this state is returned for all requests that don't have a socket, except those that
         * correspond to a pending new connection.
         */
        public static final int WAITING_FOR_AVAILABLE_SOCKET = 2;

        /**
         * This state corresponds to a resource load that is blocked waiting for access to a
         * resource in the cache. If multiple requests are made for the same resource, the first
         * request will be responsible for writing (or updating) the cache entry and the second
         * request will be deferred until the first completes. This may be done to optimize for
         * cache reuse.
         */
        public static final int WAITING_FOR_CACHE = 4;

        /**
         * This state indicates that the URLRequest delegate has chosen to block this request before
         * it was sent over the network.
         */
        public static final int WAITING_FOR_DELEGATE = 3;

        /**
         * This state corresponds to a resource load that is blocked waiting for the response to a
         * network request. In the case of a HTTP transaction, this corresponds to the period after
         * the request is sent and before all of the response headers have been received.
         */
        public static final int WAITING_FOR_RESPONSE = 13;

        /**
         * When a socket pool group is below the maximum number of sockets allowed per group, but a
         * new socket cannot be created due to the per-pool socket limit, this state is returned by
         * all requests for the group waiting on an idle connection, except those that may be
         * serviced by a pending new connection.
         */
        public static final int WAITING_FOR_STALLED_SOCKET_POOL = 1;

        private Status() {
        }
    }
}
