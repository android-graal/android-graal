package android.net.wifi.rtt;

/**
 * This class provides the primary API for measuring distance (range) to other devices using the
 * IEEE 802.11mc Wi-Fi Round Trip Time (RTT) technology.
 * <p>
 * The devices which can be ranged include:
 * <li>Access Points (APs)
 * <li>Wi-Fi Aware peers
 * <p>
 * Ranging requests are triggered using
 * {@link #startRanging(RangingRequest, Executor, RangingResultCallback)}. Results (in case of
 * successful operation) are returned in the {@link RangingResultCallback#onRangingResults(List)}
 * callback.
 * <p>
 *     Wi-Fi RTT may not be usable at some points, e.g. when Wi-Fi is disabled. To validate that
 *     the functionality is available use the {@link #isAvailable()} function. To track
 *     changes in RTT usability register for the {@link #ACTION_WIFI_RTT_STATE_CHANGED}
 *     broadcast. Note that this broadcast is not sticky - you should register for it and then
 *     check the above API to avoid a race condition.
 */
public final class WifiRttManager {
    /**
     * Broadcast intent action to indicate that the state of Wi-Fi RTT availability has changed.
     * Use the {@link #isAvailable()} to query the current status.
     * This broadcast is <b>not</b> sticky, use the {@link #isAvailable()} API after registering
     * the broadcast to check the current state of Wi-Fi RTT.
     * <p>Note: The broadcast is only delivered to registered receivers - no manifest registered
     * components will be launched.
     */
    public static final String ACTION_WIFI_RTT_STATE_CHANGED = "android.net.wifi.rtt.action.WIFI_RTT_STATE_CHANGED";

    /**
     * Bundle key to access if getting the Location Configuration Information(LCI) from responder is
     * supported.
     * @see ResponderLocation
     */
    public static final String CHARACTERISTICS_KEY_BOOLEAN_LCI = "key_lci";

    /**
     * Bundle key to access if getting the Location Civic Report(LCR) from responder is supported.
     * @see ResponderLocation
     */
    public static final String CHARACTERISTICS_KEY_BOOLEAN_LCR = "key_lcr";

    /**
     * Bundle key to access if device supports to be a IEEE 802.11az non-trigger based initiator
     */
    public static final String CHARACTERISTICS_KEY_BOOLEAN_NTB_INITIATOR = "key_ntb_initiator";

    /**
     * Bundle key to access if one-sided Wi-Fi RTT is supported. When it is not supported, only
     * two-sided RTT can be performed, which requires responder supports IEEE 802.11mc and this can
     * be determined by the method {@link ScanResult#is80211mcResponder()}
     */
    public static final String CHARACTERISTICS_KEY_BOOLEAN_ONE_SIDED_RTT = "key_one_sided_rtt";

    /**
     * Bundle key to access if device supports ranging frame protection. IEEE 802.11az introduces
     * Protected Management Frames for FTM (Fine Timing Measurement), adding a layer of encryption
     * and integrity protection to these frames.
     */
    public static final String CHARACTERISTICS_KEY_BOOLEAN_RANGING_FRAME_PROTECTION_SUPPORTED = "key_rnm_mfp_supported";

    /**
     * Bundle key to access if device supports secure HE-LTF (High Efficiency Long Training Field).
     * Secure HE-LTF is a critical security enhancement in the IEEE 802.11az standard that aims to
     * protect ranging measurements from spoofing and manipulation.
     */
    public static final String CHARACTERISTICS_KEY_BOOLEAN_SECURE_HE_LTF_SUPPORTED = "key_secure_he_ltf_supported";

    /**
     * Bundle key to access if device supports to be a responder in station mode
     */
    public static final String CHARACTERISTICS_KEY_BOOLEAN_STA_RESPONDER = "key_sta_responder";

    /**
     * Bundle key to access the maximum supported secure HE-LTF protocol version.
     */
    public static final String CHARACTERISTICS_KEY_INT_MAX_SUPPORTED_SECURE_HE_LTF_PROTO_VERSION = "key_max_supported_secure_he_ltf_proto_ver";

    private WifiRttManager() {
    }
}
