package android.net;

/**
 * Allows applications to request that the system periodically send specific packets on their
 * behalf, using hardware offload to save battery power.
 *
 * To request that the system send keepalives, call one of the methods that return a
 * {@link SocketKeepalive} object, such as {@link ConnectivityManager#createSocketKeepalive},
 * passing in a non-null callback. If the {@link SocketKeepalive} is successfully
 * started, the callback's {@code onStarted} method will be called. If an error occurs,
 * {@code onError} will be called, specifying one of the {@code ERROR_*} constants in this
 * class.
 *
 * To stop an existing keepalive, call {@link SocketKeepalive#stop}. The system will call
 * {@link SocketKeepalive.Callback#onStopped} if the operation was successful or
 * {@link SocketKeepalive.Callback#onError} if an error occurred.
 *
 * For cellular, the device MUST support at least 1 keepalive slot.
 *
 * For WiFi, the device SHOULD support keepalive offload. If it does not, it MUST reply with
 * {@link SocketKeepalive.Callback#onError} with {@code ERROR_UNSUPPORTED} to any keepalive offload
 * request. If it does, it MUST support at least 3 concurrent keepalive slots.
 */
public final class SocketKeepalive {
    /**
     * There was a hardware error.
     */
    public static final int ERROR_HARDWARE_ERROR = -31;

    /**
     * Resources are insufficient (e.g. all hardware slots are in use).
     */
    public static final int ERROR_INSUFFICIENT_RESOURCES = -32;

    /**
     * The interval is invalid (e.g. too short).
     */
    public static final int ERROR_INVALID_INTERVAL = -24;

    /**
     * The invalid IP addresses. Indicates the specified IP addresses are invalid.
     * For example, the specified source IP address is not configured on the
     * specified {@code Network}.
     */
    public static final int ERROR_INVALID_IP_ADDRESS = -21;

    /**
     * The length is invalid (e.g. too long).
     */
    public static final int ERROR_INVALID_LENGTH = -23;

    /**
     * The invalid network. It indicates the specified {@code Network} is not connected.
     */
    public static final int ERROR_INVALID_NETWORK = -20;

    /**
     * The port is invalid.
     */
    public static final int ERROR_INVALID_PORT = -22;

    /**
     * The socket is invalid.
     */
    public static final int ERROR_INVALID_SOCKET = -25;

    /**
     * The socket is not idle.
     */
    public static final int ERROR_SOCKET_NOT_IDLE = -26;

    /**
     * The request is unsupported.
     */
    public static final int ERROR_UNSUPPORTED = -30;

    private SocketKeepalive() {
    }
}
