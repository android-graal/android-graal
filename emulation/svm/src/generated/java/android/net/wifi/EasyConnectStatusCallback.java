package android.net.wifi;

/**
 * Easy Connect (DPP) Status Callback. Use this callback to get status updates (success, failure,
 * progress) from the Easy Connect operations.
 */
public final class EasyConnectStatusCallback {
    /**
     * Easy Connect Failure event: Bootstrapping/Authentication initialization process failure.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_AUTHENTICATION = -2;

    /**
     * Easy Connect Failure event: Easy Connect request while in another Easy Connect exchange.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_BUSY = -5;

    /**
     * Easy Connect R2 Failure event: Enrollee cannot find the network.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_CANNOT_FIND_NETWORK = -10;

    /**
     * Easy Connect Failure event: Configuration process has failed due to malformed message.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_CONFIGURATION = -4;

    /**
     * Easy Connect R2 Failure event: Enrollee failed to authenticate with the network.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_ENROLLEE_AUTHENTICATION = -11;

    /**
     * Easy Connect Failure event: Enrollee didn't scan the network's operating channel.
     * This error is generated when framework finds that Network's operating channel
     * is not included in the list of channels the Enrollee scanned in attempting to
     * discover the network prior to connection.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_ENROLLEE_FAILED_TO_SCAN_NETWORK_CHANNEL = -14;

    /**
     * Easy Connect R2 Failure event: Enrollee rejected the configuration.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_ENROLLEE_REJECTED_CONFIGURATION = -12;

    /**
     * Easy Connect Failure event: General protocol failure.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_GENERIC = -7;

    /**
     * Easy Connect Failure event: Invalid network provided to Easy Connect configurator.
     * Network must either be WPA3-Personal (SAE) or WPA2-Personal (PSK).
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_INVALID_NETWORK = -9;

    /**
     * Easy Connect Failure event: Scanned QR code is either not a Easy Connect URI, or the Easy
     * Connect URI has errors.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_INVALID_URI = -1;

    /**
     * Easy Connect Failure event: Both devices are implementing the same role and are incompatible.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_NOT_COMPATIBLE = -3;

    /**
     * Easy Connect Failure event: Feature or option is not supported.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_NOT_SUPPORTED = -8;

    /**
     * Easy Connect Failure event: No response from the peer.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_TIMEOUT = -6;

    /**
     * Easy Connect Failure event: System failed to generate DPP URI.
     */
    public static final int EASY_CONNECT_EVENT_FAILURE_URI_GENERATION = -13;

    private EasyConnectStatusCallback() {
    }
}
