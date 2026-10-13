package android.media;

/**
 * Allows you to enumerate available codecs, each specified as a {@link MediaCodecInfo} object,
 * find a codec supporting a given format and query the capabilities
 * of a given codec.
 * <p>See {@link MediaCodecInfo} for sample usage.
 */
public final class MediaCodecList {
    /**
     * Use in {@link #MediaCodecList} to enumerate all codecs, even ones that are
     * not suitable for regular (buffer-to-buffer) decoding or encoding.  These
     * include codecs, for example, that only work with special input or output
     * surfaces, such as secure-only or tunneled-only codecs.
     *
     * @see MediaCodecInfo.CodecCapabilities#isFormatSupported
     * @see MediaCodecInfo.CodecCapabilities#FEATURE_SecurePlayback
     * @see MediaCodecInfo.CodecCapabilities#FEATURE_TunneledPlayback
     */
    public static final int ALL_CODECS = 1;

    /**
     * Use in {@link #MediaCodecList} to enumerate only codecs that are suitable
     * for regular (buffer-to-buffer) decoding or encoding.
     *
     * <em>NOTE:</em> These are the codecs that are returned prior to API 21,
     * using the now deprecated static methods.
     */
    public static final int REGULAR_CODECS = 0;

    private MediaCodecList() {
    }
}
