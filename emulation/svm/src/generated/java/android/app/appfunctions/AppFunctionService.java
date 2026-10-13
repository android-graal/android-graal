package android.app.appfunctions;

/**
 * Abstract base class to provide app functions to the system.
 *
 * <p>Include the following in the manifest:
 *
 * <pre>
 * {@literal
 * <service android:name=".YourService"
 *       android:permission="android.permission.BIND_APP_FUNCTION_SERVICE">
 *    <intent-filter>
 *      <action android:name="android.app.appfunctions.AppFunctionService" />
 *    </intent-filter>
 * </service>
 * }
 * </pre>
 *
 * @see AppFunctionManager
 */
public final class AppFunctionService {
    /**
     * The {@link Intent} that must be declared as handled by the service. To be supported, the
     * service must also require the {@link BIND_APP_FUNCTION_SERVICE} permission so that other
     * applications can not abuse it.
     */
    public static final String SERVICE_INTERFACE = "android.app.appfunctions.AppFunctionService";

    private AppFunctionService() {
    }
}
