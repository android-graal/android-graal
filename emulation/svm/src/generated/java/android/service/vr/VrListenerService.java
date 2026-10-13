package android.service.vr;

/**
 * A service that is bound from the system while running in virtual reality (VR) mode.
 *
 * <p>To extend this class, you must declare the service in your manifest file with
 * the {@link android.Manifest.permission#BIND_VR_LISTENER_SERVICE} permission
 * and include an intent filter with the {@link #SERVICE_INTERFACE} action. For example:</p>
 * <pre>
 * &lt;service android:name=".VrListener"
 *          android:label="&#64;string/service_name"
 *          android:permission="android.permission.BIND_VR_LISTENER_SERVICE">
 *     &lt;intent-filter>
 *         &lt;action android:name="android.service.vr.VrListenerService" />
 *     &lt;/intent-filter>
 * &lt;/service>
 * </pre>
 *
 * <p>This service is bound when the system enters VR mode and is unbound when the system leaves VR
 * mode.</p>
 * <p>The system will enter VR mode when an application that has previously called
 * {@link android.app.Activity#setVrModeEnabled} gains user focus.  The system will only start this
 * service if the VR application has specifically targeted this service by specifying
 * its {@link ComponentName} in the call to {@link android.app.Activity#setVrModeEnabled} and if
 * this service is installed and enabled in the current user's settings.</p>
 *
 * @see android.provider.Settings#ACTION_VR_LISTENER_SETTINGS
 * @see android.app.Activity#setVrModeEnabled
 * @see android.R.attr#enableVrMode
 */
public final class VrListenerService {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.service.vr.VrListenerService";

    private VrListenerService() {
    }
}
