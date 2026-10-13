package android.service.carrier;

/**
 * A service that exposes carrier-specific functionality to the system.
 * <p>
 * To extend this class, you must declare the service in your manifest file to require the
 * {@link android.Manifest.permission#BIND_CARRIER_SERVICES} permission and include an intent
 * filter with the {@link #CARRIER_SERVICE_INTERFACE}. If the service should have a long-lived
 * binding, set <code>android.service.carrier.LONG_LIVED_BINDING</code> to <code>true</code> in the
 * service's metadata. For example:
 * </p>
 *
 * <pre>{@code
 * <service android:name=".MyCarrierService"
 *       android:label="@string/service_name"
 *       android:permission="android.permission.BIND_CARRIER_SERVICES">
 *  <intent-filter>
 *      <action android:name="android.service.carrier.CarrierService" />
 *  </intent-filter>
 *  <meta-data android:name="android.service.carrier.LONG_LIVED_BINDING"
 *             android:value="true" />
 * </service>
 * }</pre>
 */
public final class CarrierService {
    public static final String CARRIER_SERVICE_INTERFACE = "android.service.carrier.CarrierService";

    private CarrierService() {
    }
}
