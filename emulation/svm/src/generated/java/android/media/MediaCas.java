package android.media;

/**
 * MediaCas can be used to obtain keys for descrambling protected media streams, in
 * conjunction with {@link android.media.MediaDescrambler}. The MediaCas APIs are
 * designed to support conditional access such as those in the ISO/IEC13818-1.
 * The CA system is identified by a 16-bit integer CA_system_id. The scrambling
 * algorithms are usually proprietary and implemented by vendor-specific CA plugins
 * installed on the device.
 * <p>
 * The app is responsible for constructing a MediaCas object for the CA system it
 * intends to use. The app can query if a certain CA system is supported using static
 * method {@link #isSystemIdSupported}. It can also obtain the entire list of supported
 * CA systems using static method {@link #enumeratePlugins}.
 * <p>
 * Once the MediaCas object is constructed, the app should properly provision it by
 * using method {@link #provision} and/or {@link #processEmm}. The EMMs (Entitlement
 * management messages) can be distributed out-of-band, or in-band with the stream.
 * <p>
 * To descramble elementary streams, the app first calls {@link #openSession} to
 * generate a {@link Session} object that will uniquely identify a session. A session
 * provides a context for subsequent key updates and descrambling activities. The ECMs
 * (Entitlement control messages) are sent to the session via method
 * {@link Session#processEcm}.
 * <p>
 * The app next constructs a MediaDescrambler object, and initializes it with the
 * session using {@link MediaDescrambler#setMediaCasSession}. This ties the
 * descrambler to the session, and the descrambler can then be used to descramble
 * content secured with the session's key, either during extraction, or during decoding
 * with {@link android.media.MediaCodec}.
 * <p>
 * If the app handles sample extraction using its own extractor, it can use
 * MediaDescrambler to descramble samples into clear buffers (if the session's license
 * doesn't require secure decoders), or descramble a small amount of data to retrieve
 * information necessary for the downstream pipeline to process the sample (if the
 * session's license requires secure decoders).
 * <p>
 * If the session requires a secure decoder, a MediaDescrambler needs to be provided to
 * MediaCodec to descramble samples queued by {@link MediaCodec#queueSecureInputBuffer}
 * into protected buffers. The app should use {@link MediaCodec#configure(MediaFormat,
 * android.view.Surface, int, MediaDescrambler)} instead of the normal {@link
 * MediaCodec#configure(MediaFormat, android.view.Surface, MediaCrypto, int)} method
 * to configure MediaCodec.
 * <p>
 * <h3>Using Android's MediaExtractor</h3>
 * <p>
 * If the app uses {@link MediaExtractor}, it can delegate the CAS session
 * management to MediaExtractor by calling {@link MediaExtractor#setMediaCas}.
 * MediaExtractor will take over and call {@link #openSession}, {@link #processEmm}
 * and/or {@link Session#processEcm}, etc.. if necessary.
 * <p>
 * When using {@link MediaExtractor}, the app would still need a MediaDescrambler
 * to use with {@link MediaCodec} if the licensing requires a secure decoder. The
 * session associated with the descrambler of a track can be retrieved by calling
 * {@link MediaExtractor#getCasInfo}, and used to initialize a MediaDescrambler
 * object for MediaCodec.
 * <p>
 * <h3>Listeners</h3>
 * <p>The app may register a listener to receive events from the CA system using
 * method {@link #setEventListener}. The exact format of the event is scheme-specific
 * and is not specified by this API.
 */
public final class MediaCas {
    /**
     * The event to indicate that the status of CAS system is changed by the removal or insertion of
     * physical CAS modules.
     */
    public static final int PLUGIN_STATUS_PHYSICAL_MODULE_CHANGED = 0;

    /**
     * The event to indicate that the number of CAS system's session is changed.
     */
    public static final int PLUGIN_STATUS_SESSION_NUMBER_CHANGED = 1;

    /**
     * Advanced Encryption System (AES) 128-bit Encryption mode.
     */
    public static final int SCRAMBLING_MODE_AES128 = 9;

    /**
     * Advanced Encryption System (AES) Cipher Block Chaining (CBC) mode.
     */
    public static final int SCRAMBLING_MODE_AES_CBC = 14;

    /**
     * Advanced Encryption System (AES) Electronic Code Book (ECB) mode.
     */
    public static final int SCRAMBLING_MODE_AES_ECB = 10;

    /**
     * Advanced Encryption System (AES) Society of Cable Telecommunications Engineers (SCTE) 52
     * mode.
     */
    public static final int SCRAMBLING_MODE_AES_SCTE52 = 11;

    /**
     * DVB Common IPTV Software-oriented Scrambling Algorithm (CISSA) Version 1.
     */
    public static final int SCRAMBLING_MODE_DVB_CISSA_V1 = 6;

    /**
     * DVB (Digital Video Broadcasting) Common Scrambling Algorithm (CSA) 1.
     */
    public static final int SCRAMBLING_MODE_DVB_CSA1 = 1;

    /**
     * DVB CSA 2.
     */
    public static final int SCRAMBLING_MODE_DVB_CSA2 = 2;

    /**
     * DVB CSA 3 in fully enhanced mode.
     */
    public static final int SCRAMBLING_MODE_DVB_CSA3_ENHANCE = 5;

    /**
     * DVB CSA 3 in minimally enhanced mode.
     */
    public static final int SCRAMBLING_MODE_DVB_CSA3_MINIMAL = 4;

    /**
     * DVB CSA 3 in standard mode.
     */
    public static final int SCRAMBLING_MODE_DVB_CSA3_STANDARD = 3;

    /**
     * ATIS-0800006 IIF Default Scrambling Algorithm (IDSA).
     */
    public static final int SCRAMBLING_MODE_DVB_IDSA = 7;

    /**
     * A symmetric key algorithm.
     */
    public static final int SCRAMBLING_MODE_MULTI2 = 8;

    /**
     * DVB (Digital Video Broadcasting) reserved mode.
     */
    public static final int SCRAMBLING_MODE_RESERVED = 0;

    /**
     * Triple Data Encryption Algorithm (TDES) Electronic Code Book (ECB) mode.
     */
    public static final int SCRAMBLING_MODE_TDES_ECB = 12;

    /**
     * Triple Data Encryption Algorithm (TDES) Society of Cable Telecommunications Engineers (SCTE)
     * 52 mode.
     */
    public static final int SCRAMBLING_MODE_TDES_SCTE52 = 13;

    /**
     * Cas session is used to descramble live streams.
     */
    public static final int SESSION_USAGE_LIVE = 0;

    /**
     * Cas session is used to descramble recoreded streams.
     */
    public static final int SESSION_USAGE_PLAYBACK = 1;

    /**
     * Cas session is used to descramble live streams and encrypt local recorded content
     */
    public static final int SESSION_USAGE_RECORD = 2;

    /**
     * Cas session is used to descramble live streams , encrypt local recorded content and playback
     * local encrypted content.
     */
    public static final int SESSION_USAGE_TIMESHIFT = 3;

    private MediaCas() {
    }
}
