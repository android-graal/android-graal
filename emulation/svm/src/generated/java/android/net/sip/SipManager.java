package android.net.sip;

/**
 * Provides APIs for SIP tasks, such as initiating SIP connections, and provides access to related
 * SIP services. This class is the starting point for any SIP actions. You can acquire an instance
 * of it with {@link #newInstance newInstance()}.</p>
 * <p>The APIs in this class allows you to:</p>
 * <ul>
 * <li>Create a {@link SipSession} to get ready for making calls or listen for incoming calls. See
 * {@link #createSipSession createSipSession()} and {@link #getSessionFor getSessionFor()}.</li>
 * <li>Initiate and receive generic SIP calls or audio-only SIP calls. Generic SIP calls may
 * be video, audio, or other, and are initiated with {@link #open open()}. Audio-only SIP calls
 * should be handled with a {@link SipAudioCall}, which you can acquire with {@link
 * #makeAudioCall makeAudioCall()} and {@link #takeAudioCall takeAudioCall()}.</li>
 * <li>Register and unregister with a SIP service provider, with
 *      {@link #register register()} and {@link #unregister unregister()}.</li>
 * <li>Verify session connectivity, with {@link #isOpened isOpened()} and
 *      {@link #isRegistered isRegistered()}.</li>
 * </ul>
 * <p class="note"><strong>Note:</strong> Not all Android-powered devices support VOIP calls using
 * SIP. You should always call {@link android.net.sip.SipManager#isVoipSupported
 * isVoipSupported()} to verify that the device supports VOIP calling and {@link
 * android.net.sip.SipManager#isApiSupported isApiSupported()} to verify that the device supports
 * the SIP APIs. Your application must also request the {@link
 * android.Manifest.permission#INTERNET} and {@link android.Manifest.permission#USE_SIP}
 * permissions.</p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using SIP, read the
 * <a href="{@docRoot}guide/topics/network/sip.html">Session Initiation Protocol</a>
 * developer guide.</p>
 * </div>
 * @deprecated {@link android.net.sip.SipManager} and associated classes are no longer supported and
 * should not be used as the basis of future VOIP apps.
 */
@Deprecated
public final class SipManager {
    /**
     * Key to retrieve the call ID from an incoming call intent.
     * @see #open(SipProfile, PendingIntent, SipRegistrationListener)
     */
    @Deprecated
    public static final String EXTRA_CALL_ID = "android:sipCallID";

    /**
     * Key to retrieve the offered session description from an incoming call
     * intent.
     * @see #open(SipProfile, PendingIntent, SipRegistrationListener)
     */
    @Deprecated
    public static final String EXTRA_OFFER_SD = "android:sipOfferSD";

    /**
     * The result code to be sent back with the incoming call
     * {@link PendingIntent}.
     * @see #open(SipProfile, PendingIntent, SipRegistrationListener)
     */
    @Deprecated
    public static final int INCOMING_CALL_RESULT_CODE = 101;

    private SipManager() {
    }
}
