package android.net.rtp;

/**
 * RtpStream represents the base class of streams which send and receive network
 * packets with media payloads over Real-time Transport Protocol (RTP).
 *
 * <p class="note">Using this class requires
 * {@link android.Manifest.permission#INTERNET} permission.</p>
 * @deprecated {@link android.net.sip.SipManager} and associated classes are no longer supported and
 * should not be used as the basis of future VOIP apps.
 */
@Deprecated
public final class RtpStream {
    /**
     * This mode indicates that the stream sends and receives packets at the
     * same time. This is the initial mode for new streams.
     */
    @Deprecated
    public static final int MODE_NORMAL = 0;

    /**
     * This mode indicates that the stream only receives packets.
     */
    @Deprecated
    public static final int MODE_RECEIVE_ONLY = 2;

    /**
     * This mode indicates that the stream only sends packets.
     */
    @Deprecated
    public static final int MODE_SEND_ONLY = 1;

    private RtpStream() {
    }
}
