package android.companion;

/**
 * A service that receives calls from the system with device events.
 *
 * <p>
 * Companion applications must create a service that {@code extends}
 * {@link CompanionDeviceService}, and declare it in their AndroidManifest.xml with the
 * "android.permission.BIND_COMPANION_DEVICE_SERVICE" permission
 * (see {@link android.Manifest.permission#BIND_COMPANION_DEVICE_SERVICE}),
 * as well as add an intent filter for the "android.companion.CompanionDeviceService" action
 * (see {@link #SERVICE_INTERFACE}).
 *
 * <p>
 * Following is an example of such declaration:
 * <pre>{@code
 * <service
 *        android:name=".CompanionService"
 *        android:label="@string/service_name"
 *        android:exported="true"
 *        android:permission="android.permission.BIND_COMPANION_DEVICE_SERVICE">
 *    <intent-filter>
 *        <action android:name="android.companion.CompanionDeviceService" />
 *    </intent-filter>
 * </service>
 * }</pre>
 *
 * <p>
 * If the companion application has requested observing device presence (see
 * {@link CompanionDeviceManager#stopObservingDevicePresence(ObservingDevicePresenceRequest)})
 * the system will <a href="https://developer.android.com/guide/components/bound-services">
 * bind the service</a> when one of the {@link DevicePresenceEvent#EVENT_BLE_APPEARED},
 * {@link DevicePresenceEvent#EVENT_BT_CONNECTED},
 * {@link DevicePresenceEvent#EVENT_SELF_MANAGED_APPEARED} event is notified.
 *
 * <p>
 * The system binding {@link CompanionDeviceService} elevates the priority of the process that
 * the service is running in, and thus may prevent
 * <a href="https://developer.android.com/topic/performance/memory-management#low-memory_killer">
 * the Low-memory killer</a> from killing the process at expense of other processes with lower
 * priority.
 *
 * <p>
 * It is possible for an application to declare multiple {@link CompanionDeviceService}-s.
 * In such case, the system will bind all declared services, but will deliver
 * {@link #onDeviceAppeared(AssociationInfo)} and {@link #onDeviceDisappeared(AssociationInfo)}
 * only to one "primary" services.
 * Applications that declare multiple {@link CompanionDeviceService}-s should indicate the "primary"
 * service using "android.companion.PROPERTY_PRIMARY_COMPANION_DEVICE_SERVICE" service level
 * property.
 * <pre>{@code
 * <property
 *       android:name="android.companion.PROPERTY_PRIMARY_COMPANION_DEVICE_SERVICE"
 *       android:value="true" />
 * }</pre>
 *
 * <p>
 * If the application declares multiple {@link CompanionDeviceService}-s, but does not indicate
 * the "primary" one, the system will pick one of the declared services to use as "primary".
 *
 * <p>
 * If the application declares multiple "primary" {@link CompanionDeviceService}-s, the system
 * will pick single one of them to use as "primary".
 */
public final class CompanionDeviceService {
    /**
     * An intent action for a service to be bound whenever this app's companion device(s)
     * are nearby or self-managed device(s) report app appeared.
     *
     * <p>The app will be kept bound by the system when one of the
     * {@link DevicePresenceEvent#EVENT_BLE_APPEARED},
     * {@link DevicePresenceEvent#EVENT_BT_CONNECTED},
     * {@link DevicePresenceEvent#EVENT_SELF_MANAGED_APPEARED} event is notified.
     *
     * If the app is not running when one of the
     * {@link DevicePresenceEvent#EVENT_BLE_APPEARED},
     * {@link DevicePresenceEvent#EVENT_BT_CONNECTED},
     * {@link DevicePresenceEvent#EVENT_SELF_MANAGED_APPEARED} event is notified, the app will be
     * kept bound by the system.</p>
     *
     * <p>Shortly, the service will be unbound if both
     * {@link DevicePresenceEvent#EVENT_BLE_DISAPPEARED} and
     * {@link DevicePresenceEvent#EVENT_BT_DISCONNECTED} are notified, or
     * {@link DevicePresenceEvent#EVENT_SELF_MANAGED_DISAPPEARED} event is notified.
     * The app will be eligible for cleanup, unless any other user-visible components are
     * running.</p>
     *
     * If running in background is not essential for the devices that this app can manage,
     * app should avoid declaring this service.</p>
     *
     * <p>The service must also require permission
     * {@link android.Manifest.permission#BIND_COMPANION_DEVICE_SERVICE}</p>
     */
    public static final String SERVICE_INTERFACE = "android.companion.CompanionDeviceService";

    private CompanionDeviceService() {
    }
}
