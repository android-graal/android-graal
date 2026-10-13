package android.telecom;

/**
 * This service can be implemented to interact between Telecom and its implementor
 * for making outgoing call with optional redirection/cancellation purposes.
 *
 * <p>
 * Below is an example manifest registration for a {@code CallRedirectionService}.
 * {@code
 * <service android:name="your.package.YourCallRedirectionServiceImplementation"
 *          android:permission="android.permission.BIND_CALL_REDIRECTION_SERVICE">
 *      <intent-filter>
 *          <action android:name="android.telecom.CallRedirectionService"/>
 *      </intent-filter>
 * </service>
 * }
 */
public final class CallRedirectionService {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.telecom.CallRedirectionService";

    private CallRedirectionService() {
    }
}
