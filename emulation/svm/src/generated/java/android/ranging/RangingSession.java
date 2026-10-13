package android.ranging;

/**
 * Represents a session for performing ranging operations. A {@link RangingSession} manages
 * the lifecycle of a ranging operation, including start, stop, and event callbacks.
 *
 * <p>All methods are asynchronous and rely on the provided {@link Executor} to invoke
 * callbacks on appropriate threads.
 *
 * <p>This class implements {@link AutoCloseable}, ensuring that resources can be
 * automatically released when the session is closed.
 */
public final class RangingSession {
    private RangingSession() {
    }

    /**
     * Callback interface for receiving ranging session events.
     */
    public interface Callback {
        /**
         * Indicates that the session was closed because {@link AutoCloseable#close()} or
         * {@link RangingSession#stop()} was called.
         */
        int REASON_LOCAL_REQUEST = 1;

        /**
         * Indicates that the session was closed because none of the specified peers were found.
         */
        int REASON_NO_PEERS_FOUND = 5;

        /**
         * Indicates that the session was closed at the request of a remote peer.
         */
        int REASON_REMOTE_REQUEST = 2;

        /**
         * Indicates that the local system policy forced the session to close, such
         * as power management policy, airplane mode etc.
         */
        int REASON_SYSTEM_POLICY = 4;

        /**
         * Indicates that the session was closed due to an unknown reason.
         */
        int REASON_UNKNOWN = 0;

        /**
         * Indicates that the session closed because the provided session parameters were not
         * supported.
         */
        int REASON_UNSUPPORTED = 3;
    }
}
