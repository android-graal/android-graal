package android.net;

/**
 * Creates a (non-server) socket in the UNIX-domain namespace. The interface
 * here is not entirely unlike that of java.net.Socket. This class and the streams
 * returned from it may be used from multiple threads.
 */
public final class LocalSocket {
    /**
     * Datagram socket type
     */
    public static final int SOCKET_DGRAM = 1;

    /**
     * Sequential packet socket type
     */
    public static final int SOCKET_SEQPACKET = 3;

    /**
     * Stream socket type
     */
    public static final int SOCKET_STREAM = 2;

    private LocalSocket() {
    }
}
