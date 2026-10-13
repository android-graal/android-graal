package android.telephony.mbms;

/**
 * Class used to represent a single MBMS stream. After a stream has been started with
 * {@link MbmsStreamingSession#startStreaming(StreamingServiceInfo, java.util.concurrent.Executor,
 * StreamingServiceCallback)},
 * this class is used to hold information about the stream and control it.
 */
public final class StreamingService {
    /**
     * The method of transmission currently used for a stream,
     * reported via {@link StreamingServiceCallback#onStreamMethodUpdated}
     */
    public static final int BROADCAST_METHOD = 1;

    /**
     * State changed due to a call to {@link #close()} or
     * {@link MbmsStreamingSession#startStreaming(StreamingServiceInfo,
     * java.util.concurrent.Executor, StreamingServiceCallback)}
     */
    public static final int REASON_BY_USER_REQUEST = 1;

    /**
     * State changed due to the streaming session ending at the carrier.
     */
    public static final int REASON_END_OF_SESSION = 2;

    /**
     * State changed due to a frequency conflict with another requested stream.
     */
    public static final int REASON_FREQUENCY_CONFLICT = 3;

    /**
     * State changed due to the device leaving the where this stream is being broadcast.
     */
    public static final int REASON_LEFT_MBMS_BROADCAST_AREA = 6;

    /**
     * Indicates that the middleware does not have a reason to provide for the state change.
     */
    public static final int REASON_NONE = 0;

    /**
     * State changed due to the device leaving the home carrier's LTE network.
     */
    public static final int REASON_NOT_CONNECTED_TO_HOMECARRIER_LTE = 5;

    /**
     * State changed due to the middleware running out of memory
     */
    public static final int REASON_OUT_OF_MEMORY = 4;

    public static final int STATE_STALLED = 3;

    public static final int STATE_STARTED = 2;

    public static final int STATE_STOPPED = 1;

    public static final int UNICAST_METHOD = 2;

    private StreamingService() {
    }
}
