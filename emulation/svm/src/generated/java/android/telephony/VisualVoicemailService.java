package android.telephony;

/**
 * This service is implemented by dialer apps that wishes to handle OMTP or similar visual
 * voicemails. Telephony binds to this service when the cell service is first connected, a visual
 * voicemail SMS has been received, or when a SIM has been removed. Telephony will only bind to the
 * default dialer for such events (See {@link TelecomManager#getDefaultDialerPackage()}). The
 * {@link android.service.carrier.CarrierMessagingService} precedes the VisualVoicemailService in
 * the SMS filtering chain and may intercept the visual voicemail SMS before it reaches this
 * service.
 * <p>
 * To extend this class, The service must be declared in the manifest file with
 * the {@link android.Manifest.permission#BIND_VISUAL_VOICEMAIL_SERVICE} permission and include an
 * intent filter with the {@link #SERVICE_INTERFACE} action.
 * <p>
 * Below is an example manifest registration for a {@code VisualVoicemailService}.
 * <pre>
 * {@code
 * <service android:name="your.package.YourVisualVoicemailServiceImplementation"
 *          android:permission="android.permission.BIND_VISUAL_VOICEMAIL_SERVICE">
 *      <intent-filter>
 *          <action android:name="android.telephony.VisualVoicemailService"/>
 *      </intent-filter>
 * </service>
 * }
 * </pre>
 */
public final class VisualVoicemailService {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.telephony.VisualVoicemailService";

    private VisualVoicemailService() {
    }
}
