package android.telephony.mbms;

/**
 * A callback class for use when the application is actively streaming content. The middleware
 * will provide updates on the status of the stream via this callback.
 */
public final class StreamingServiceCallback {
    /**
     * Indicates broadcast signal strength is not available for this service.
     *
     * This may be due to the service no longer being available due to geography
     * or timing (end of service) or because lack of demand has caused the service
     * to be delivered via unicast.
     */
    public static final int SIGNAL_STRENGTH_UNAVAILABLE = -1;

    private StreamingServiceCallback() {
    }
}
