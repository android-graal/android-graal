package android.media;

/**
 * MediaDescrambler class can be used in conjunction with {@link android.media.MediaCodec}
 * and {@link android.media.MediaExtractor} to decode media data scrambled by conditional
 * access (CA) systems such as those in the ISO/IEC13818-1.
 *
 * A MediaDescrambler object is initialized from a session opened by a MediaCas object,
 * and can be used to descramble media streams scrambled with that session's keys.
 *
 * Scrambling schemes are identified by 16-bit unsigned integer as in CA_system_id.
 */
public final class MediaDescrambler {
    /**
     * Scramble control value indicating that the even key is used.
     *
     * @see #descramble(ByteBuffer, ByteBuffer, android.media.MediaCodec.CryptoInfo)
     */
    public static final byte SCRAMBLE_CONTROL_EVEN_KEY = 2;

    /**
     * Scramble control value indicating that the odd key is used.
     *
     * @see #descramble(ByteBuffer, ByteBuffer, android.media.MediaCodec.CryptoInfo)
     */
    public static final byte SCRAMBLE_CONTROL_ODD_KEY = 3;

    /**
     * Scramble control value reserved and shouldn't be used currently.
     *
     * @see #descramble(ByteBuffer, ByteBuffer, android.media.MediaCodec.CryptoInfo)
     */
    public static final byte SCRAMBLE_CONTROL_RESERVED = 1;

    /**
     * Scramble control value indicating that the samples are not scrambled.
     *
     * @see #descramble(ByteBuffer, ByteBuffer, android.media.MediaCodec.CryptoInfo)
     */
    public static final byte SCRAMBLE_CONTROL_UNSCRAMBLED = 0;

    /**
     * Scramble flag for a hint indicating that the descrambling request is for
     * retrieving the PES header info only.
     *
     * @see #descramble(ByteBuffer, ByteBuffer, android.media.MediaCodec.CryptoInfo)
     */
    public static final byte SCRAMBLE_FLAG_PES_HEADER = 1;

    private MediaDescrambler() {
    }
}
