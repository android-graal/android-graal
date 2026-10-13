package android.telecom;

/**
 * This service can be implemented by the default dialer (see
 * {@link TelecomManager#getDefaultDialerPackage()}) or a third party app to allow or disallow
 * incoming calls before they are shown to a user. A {@link CallScreeningService} can also see
 * outgoing calls for the purpose of providing caller ID services for those calls.
 * <p>
 * Below is an example manifest registration for a {@code CallScreeningService}.
 * <pre>
 * {@code
 * <service android:name="your.package.YourCallScreeningServiceImplementation"
 *          android:permission="android.permission.BIND_SCREENING_SERVICE">
 *      <intent-filter>
 *          <action android:name="android.telecom.CallScreeningService"/>
 *      </intent-filter>
 * </service>
 * }
 * </pre>
 * <p>
 * A CallScreeningService performs two functions:
 * <ol>
 *     <li>Call blocking/screening - the service can choose which calls will ring on the user's
 *     device, and which will be silently sent to voicemail.</li>
 *     <li>Call identification - services which provide call identification functionality can
 *     display a user-interface of their choosing which contains identifying information for a call.
 *     </li>
 * </ol>
 * <p>
 * <h2>Becoming the CallScreeningService</h2>
 * Telecom will bind to a single app chosen by the user which implements the
 * {@link CallScreeningService} API when there are new incoming and outgoing calls.
 * <p>
 * The code snippet below illustrates how your app can request that it fills the call screening
 * role.
 * <pre>
 * {@code
 * private static final int REQUEST_ID = 1;
 *
 * public void requestRole() {
 *     RoleManager roleManager = (RoleManager) getSystemService(ROLE_SERVICE);
 *     Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING);
 *     startActivityForResult(intent, REQUEST_ID);
 * }
 *
 * &#64;Override
 * public void onActivityResult(int requestCode, int resultCode, Intent data) {
 *     if (requestCode == REQUEST_ID) {
 *         if (resultCode == android.app.Activity.RESULT_OK) {
 *             // Your app is now the call screening app
 *         } else {
 *             // Your app is not the call screening app
 *         }
 *     }
 * }
 * }
 * </pre>
 *
 * <h2>CallScreeningService Lifecycle</h2>
 *
 * The framework binds to the {@link CallScreeningService} implemented by the user-chosen app
 * filling the {@link android.app.role.RoleManager#ROLE_CALL_SCREENING} role when incoming calls are
 * received (prior to ringing) and when outgoing calls are placed.  The platform calls the
 * {@link #onScreenCall(Call.Details)} method to provide your service with details about the call.
 * <p>
 * For incoming calls, the {@link CallScreeningService} must call
 * {@link #respondToCall(Call.Details, CallResponse)} within 5 seconds of being bound to indicate to
 * the platform whether the call should be blocked or not.  Your app must do this even if it is
 * primarily performing caller ID operations and not screening calls.  It is important to perform
 * screening operations in a timely matter as the user's device will not begin ringing until the
 * response is received (or the timeout is hit).  A {@link CallScreeningService} may choose to
 * perform local database lookups to help determine if a call should be screened or not; care should
 * be taken to ensure the timeout is not repeatedly hit, causing delays in the incoming call flow.
 * <p>
 * If your app provides a caller ID experience, it should launch an activity to show the caller ID
 * information from {@link #onScreenCall(Call.Details)}.
 */
public final class CallScreeningService {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.telecom.CallScreeningService";

    private CallScreeningService() {
    }

    /**
     * Information about how to respond to an incoming call. Call screening apps can construct an
     * instance of this class using {@link CallResponse.Builder}.
     */
    public static final class CallResponse {
        /**
         * Bit flag indicating whether to show the location attachment for call composer.
         *
         * Used with {@link Builder#setCallComposerAttachmentsToShow(int)}.
         */
        public static final int CALL_COMPOSER_ATTACHMENT_LOCATION = 2;

        /**
         * Bit flag indicating whether to show the picture attachment for call composer.
         *
         * Used with {@link Builder#setCallComposerAttachmentsToShow(int)}.
         */
        public static final int CALL_COMPOSER_ATTACHMENT_PICTURE = 1;

        /**
         * Bit flag indicating whether to show the priority attachment for call composer.
         *
         * Used with {@link Builder#setCallComposerAttachmentsToShow(int)}.
         */
        public static final int CALL_COMPOSER_ATTACHMENT_PRIORITY = 8;

        /**
         * Bit flag indicating whether to show the subject attachment for call composer.
         *
         * Used with {@link Builder#setCallComposerAttachmentsToShow(int)}.
         */
        public static final int CALL_COMPOSER_ATTACHMENT_SUBJECT = 4;

        private CallResponse() {
        }
    }
}
