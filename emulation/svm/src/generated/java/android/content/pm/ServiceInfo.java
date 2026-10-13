package android.content.pm;

import android.os.Parcelable;

/**
 * Information you can retrieve about a particular application
 * service. This corresponds to information collected from the
 * AndroidManifest.xml's &lt;service&gt; tags.
 */
public final class ServiceInfo implements Parcelable {
    /**
     * Bit in {@link #flags}: If set, and this is an {@link android.R.attr#isolatedProcess}
     * service, the service is allowed to be bound in a shared isolated process with other
     * isolated services. Note that these other isolated services can also belong to other
     * apps from different vendors.
     *
     * Shared isolated processes are created when using the
     * {@link android.content.Context#BIND_SHARED_ISOLATED_PROCESS) during service binding.
     *
     * Note that when this flag is used, the {@link android.R.attr#process} attribute is
     * ignored when the process is bound into a shared isolated process by a client.
     */
    public static final int FLAG_ALLOW_SHARED_ISOLATED_PROCESS = 16;

    /**
     * Bit in {@link #flags}: If set, the service can be bound and run in the
     * calling application's package, rather than the package in which it is
     * declared.  Set from {@link android.R.attr#externalService} attribute.
     */
    public static final int FLAG_EXTERNAL_SERVICE = 4;

    /**
     * Bit in {@link #flags}: If set, the service will run in its own
     * isolated process.  Set from the
     * {@link android.R.attr#isolatedProcess} attribute.
     */
    public static final int FLAG_ISOLATED_PROCESS = 2;

    /**
     * Bit in {@link #flags}: If set, a single instance of the service will
     * run for all users on the device.  Set from the
     * {@link android.R.attr#singleUser} attribute.
     */
    public static final int FLAG_SINGLE_USER = 1073741824;

    /**
     * Bit in {@link #flags}: If set, the service will automatically be
     * stopped by the system if the user removes a task that is rooted
     * in one of the application's activities.  Set from the
     * {@link android.R.attr#stopWithTask} attribute.
     */
    public static final int FLAG_STOP_WITH_TASK = 1;

    /**
     * Bit in {@link #flags}: If set, the service (which must be isolated)
     * will be spawned from an Application Zygote, instead of the regular Zygote.
     * The Application Zygote will pre-initialize the application's class loader,
     * and call a static callback into the application to allow it to perform
     * application-specific preloads (such as loading a shared library). Therefore,
     * spawning from the Application Zygote will typically reduce the service
     * launch time and reduce its memory usage. The downside of using this flag
     * is that you will have an additional process (the app zygote itself) that
     * is taking up memory. Whether actual memory usage is improved therefore
     * strongly depends on the number of isolated services that an application
     * starts, and how much memory those services save by preloading. Therefore,
     * it is recommended to measure memory usage under typical workloads to
     * determine whether it makes sense to use this flag.
     */
    public static final int FLAG_USE_APP_ZYGOTE = 8;

    /**
     * Constant corresponding to {@code camera} in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Use the camera device or record video.
     * For apps with <code>targetSdkVersion</code> {@link android.os.Build.VERSION_CODES#R} and
     * above, a foreground service will not be able to access the camera if this type is not
     * specified in the manifest and in
     * {@link android.app.Service#startForeground(int, android.app.Notification, int)}.
     *
     * <p>Starting foreground service with this type from apps targeting API level
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and later, will require permission
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_CAMERA} and
     * {@link android.Manifest.permission#CAMERA}.
     */
    public static final int FOREGROUND_SERVICE_TYPE_CAMERA = 64;

    /**
     * Constant corresponding to <code>connectedDevice</code> in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Auto, bluetooth, TV or other devices connection, monitoring and interaction.
     *
     * <p>Starting foreground service with this type from apps targeting API level
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and later, will require permission
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_CONNECTED_DEVICE} and one of the
     * following permissions:
     * {@link android.Manifest.permission#BLUETOOTH_ADVERTISE},
     * {@link android.Manifest.permission#BLUETOOTH_CONNECT},
     * {@link android.Manifest.permission#BLUETOOTH_SCAN},
     * {@link android.Manifest.permission#CHANGE_NETWORK_STATE},
     * {@link android.Manifest.permission#CHANGE_WIFI_STATE},
     * {@link android.Manifest.permission#CHANGE_WIFI_MULTICAST_STATE},
     * {@link android.Manifest.permission#NFC},
     * {@link android.Manifest.permission#TRANSMIT_IR},
     * {@link android.Manifest.permission#UWB_RANGING},
     * {@link android.Manifest.permission#RANGING},
     * or has been granted the access to one of the attached USB devices/accessories.
     */
    public static final int FOREGROUND_SERVICE_TYPE_CONNECTED_DEVICE = 16;

    /**
     * Constant corresponding to <code>dataSync</code> in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Data(photo, file, account) upload/download, backup/restore, import/export, fetch,
     * transfer over network between device and cloud.
     *
     * <p>This type has time limit of 6 hours starting from Android version
     * {@link android.os.Build.VERSION_CODES#VANILLA_ICE_CREAM}.
     * A foreground service of this type must be stopped within the timeout by
     * {@link android.app.Service#stopSelf()},
     * {@link android.content.Context#stopService(android.content.Intent)} or their overloads).
     * {@link android.app.Service#stopForeground(int)} will also work, which will demote the
     * service to a "background" service, which will soon be stopped by the system.
     *
     * <p>If the service isn't stopped within the timeout,
     * {@link android.app.Service#onTimeout(int, int)} will be called.
     *
     * <p>Also note, even though
     * {@link android.content.pm.ServiceInfo#FOREGROUND_SERVICE_TYPE_DATA_SYNC} can be used on
     * Android versions prior to {@link android.os.Build.VERSION_CODES#VANILLA_ICE_CREAM}, since
     * {@link android.app.Service#onTimeout(int, int)} did not exist on such versions, it will
     * never be called.
     *
     * Because of this, developers must make sure to stop the foreground service even if
     * {@link android.app.Service#onTimeout(int, int)} is not called on such versions.
     *
     * @see android.app.Service#onTimeout(int, int)
     */
    public static final int FOREGROUND_SERVICE_TYPE_DATA_SYNC = 1;

    /**
     * Constant corresponding to {@code health} in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Health, wellness and fitness.
     *
     * <p>The caller app is required to have the permissions
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_HEALTH} and one of the following
     * permissions:
     * {@link android.Manifest.permission#ACTIVITY_RECOGNITION},
     * {@link android.Manifest.permission#HIGH_SAMPLING_RATE_SENSORS}.
     * {@link android.health.connect.HealthPermissions#READ_HEART_RATE},
     * {@link android.health.connect.HealthPermissions#READ_SKIN_TEMPERATURE},
     * {@link android.health.connect.HealthPermissions#READ_OXYGEN_SATURATION},
     */
    public static final int FOREGROUND_SERVICE_TYPE_HEALTH = 256;

    /**
     * Constant corresponding to <code>location</code> in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * GPS, map, navigation location update.
     *
     * <p>Starting foreground service with this type from apps targeting API level
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and later, will require permission
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_LOCATION} and one of the
     * following permissions:
     * {@link android.Manifest.permission#ACCESS_COARSE_LOCATION},
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION}.
     */
    public static final int FOREGROUND_SERVICE_TYPE_LOCATION = 8;

    /**
     * A special value indicates to use all types set in manifest file.
     */
    public static final int FOREGROUND_SERVICE_TYPE_MANIFEST = -1;

    /**
     * Constant corresponding to <code>mediaPlayback</code> in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Music, video, news or other media playback.
     *
     * <p>Starting foreground service with this type from apps targeting API level
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and later, will require permission
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_MEDIA_PLAYBACK}.
     */
    public static final int FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK = 2;

    /**
     * Constant corresponding to {@code mediaProcessing} in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Media processing use cases such as video or photo editing and processing.
     *
     * This type has time limit of 6 hours.
     * A foreground service of this type must be stopped within the timeout by
     * {@link android.app.Service#stopSelf()},
     * {@link android.content.Context#stopService(android.content.Intent)} or their overloads).
     * {@link android.app.Service#stopForeground(int)} will also work, which will demote the
     * service to a "background" service, which will soon be stopped by the system.
     *
     * <p>If the service isn't stopped within the timeout,
     * {@link android.app.Service#onTimeout(int, int)} will be called.
     *
     * <p>Also note, even though
     * {@link android.content.pm.ServiceInfo#FOREGROUND_SERVICE_TYPE_MEDIA_PROCESSING} was added in
     * Android version {@link android.os.Build.VERSION_CODES#VANILLA_ICE_CREAM}, it can be also used
     * on prior android versions (just like other new foreground service types can be used).
     * However, because {@link android.app.Service#onTimeout(int, int)} did not exist on prior
     * versions, it will never be called on such versions.
     * Because of this, developers must make sure to stop the foreground service even if
     * {@link android.app.Service#onTimeout(int, int)} is not called on such versions.
     *
     * @see android.app.Service#onTimeout(int, int)
     */
    public static final int FOREGROUND_SERVICE_TYPE_MEDIA_PROCESSING = 8192;

    /**
     * Constant corresponding to {@code mediaProjection} in
     * the {@link android.R.attr#foregroundServiceType foregroundServiceType} attribute.
     *
     * <p>
     * To capture through {@link android.media.projection.MediaProjection}, an app must start a
     * foreground service with the type corresponding to this constant. This type should only be
     * used for {@link android.media.projection.MediaProjection}. Capturing screen contents via
     * {@link android.media.projection.MediaProjection#createVirtualDisplay(String, int, int, int,
     * int, android.view.Surface, android.hardware.display.VirtualDisplay.Callback,
     * android.os.Handler) createVirtualDisplay} conveniently allows recording, presenting screen
     * contents into a meeting, taking screenshots, or several other scenarios.
     * </p>
     *
     * <p>Starting foreground service with this type from apps targeting API level
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and later, will require permission
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_MEDIA_PROJECTION}, and the user must
     * have allowed the screen capture request from this app.
     */
    public static final int FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION = 32;

    /**
     * Constant corresponding to {@code microphone} in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Use the microphone device or record audio.
     * For apps with <code>targetSdkVersion</code> {@link android.os.Build.VERSION_CODES#R} and
     * above, a foreground service will not be able to access the microphone if this type is not
     * specified in the manifest and in
     * {@link android.app.Service#startForeground(int, android.app.Notification, int)}.
     *
     * <p>Starting foreground service with this type from apps targeting API level
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and later, will require permission
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_MICROPHONE} and one of the following
     * permissions:
     * {@link android.Manifest.permission#CAPTURE_AUDIO_OUTPUT},
     * {@link android.Manifest.permission#RECORD_AUDIO}.
     */
    public static final int FOREGROUND_SERVICE_TYPE_MICROPHONE = 128;

    /**
     * The default foreground service type if not been set in manifest file.
     *
     * <p>Apps targeting API level {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and
     * later should NOT use this type,
     * calling {@link android.app.Service#startForeground(int, android.app.Notification, int)} with
     * this type will get a {@link android.app.InvalidForegroundServiceTypeException}.</p>
     *
     * @deprecated Do not use.
     */
    @Deprecated
    public static final int FOREGROUND_SERVICE_TYPE_NONE = 0;

    /**
     * Constant corresponding to <code>phoneCall</code> in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Ongoing operations related to phone calls, video conferencing,
     * or similar interactive communication.
     *
     * <p>Starting foreground service with this type from apps targeting API level
     * {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} and later, will require permission
     * {@link android.Manifest.permission#FOREGROUND_SERVICE_PHONE_CALL} and
     * {@link android.Manifest.permission#MANAGE_OWN_CALLS} or holding the default
     * {@link android.app.role.RoleManager#ROLE_DIALER dialer role}.
     */
    public static final int FOREGROUND_SERVICE_TYPE_PHONE_CALL = 4;

    /**
     * Constant corresponding to {@code remoteMessaging} in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Messaging use cases which host local server to relay messages across devices.
     */
    public static final int FOREGROUND_SERVICE_TYPE_REMOTE_MESSAGING = 512;

    /**
     * A foreground service type for "short-lived" services, which corresponds to
     * {@code shortService} in the {@link android.R.attr#foregroundServiceType} attribute in the
     * manifest.
     *
     * <p>Unlike other foreground service types, this type is not associated with a specific use
     * case, and it will not require any special permissions
     * (besides {@link android.Manifest.permission#FOREGROUND_SERVICE}).
     *
     * However, this type has the following restrictions.
     *
     * <ul>
     *     <li>
     *         The type has a 3 minute timeout.
     *         A foreground service of this type must be stopped within the timeout by
     *         {@link android.app.Service#stopSelf()},
     *         {@link android.content.Context#stopService(android.content.Intent)}
     *         or their overloads).
     *         {@link android.app.Service#stopForeground(int)} will also work,
     *         which will demote the
     *         service to a "background" service, which will soon be stopped by the system.
     *
     *         <p>If the service isn't stopped within the timeout,
     *         {@link android.app.Service#onTimeout(int)} will be called. Note, even when the
     *         system calls this callback, it will not stop the service automatically.
     *         You still need to stop the service using one of the aforementioned
     *         ways even when you get this callback.
     *
     *         <p>If the service is still not stopped after the callback,
     *         the app will be declared an ANR, after a short grace period of several seconds.
     *     <li>
     *         A foreground service of this type cannot be made "sticky"
     *         (see {@link android.app.Service#START_STICKY}). That is, if an app is killed
     *         due to a crash or out-of memory while it's running a short foregorund-service,
     *         the system will not restart the service.
     *     <li>
     *         Other foreground services cannot be started from short foreground services.
     *         Unlike other foreground service types, when an app is running in the background
     *         while only having a "short" foreground service, it's not allowed to start
     *         other foreground services, due to the restriction describe here:
     *         <a href="/guide/components/foreground-services#background-start-restrictions>
     *             Restrictions on background starts
     *         </a>
     *     <li>
     *         You can combine multiple foreground services types with {@code |}s, and you can
     *         combine
     *         {@link android.content.pm.ServiceInfo#FOREGROUND_SERVICE_TYPE_SHORT_SERVICE}.
     *         with other types as well.
     *         However,
     *         {@link android.content.pm.ServiceInfo#FOREGROUND_SERVICE_TYPE_SHORT_SERVICE}
     *         is for situations
     *         where you have no other valid foreground services to use and the timeout is long
     *         enough for the task, and if you can use other types, there's no point using
     *         this type.
     *         For this reason, if
     *         {@link android.content.pm.ServiceInfo#FOREGROUND_SERVICE_TYPE_SHORT_SERVICE}
     *         is combined with other foreground service types, the system will simply ignore
     *         it, and as a result,
     *         none of the above restrictions will apply (e.g. there'll be no timeout).
     * </ul>
     *
     * <p>Also note, even though
     * {@link android.content.pm.ServiceInfo#FOREGROUND_SERVICE_TYPE_SHORT_SERVICE}
     * was added
     * on Android version {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE},
     * it can be also used on
     * on prior android versions (just like other new foreground service types can be used).
     * However, because {@link android.app.Service#onTimeout(int)} did not exist on prior versions,
     * it will never called on such versions.
     * Because of this, developers must make sure to stop the foreground service even if
     * {@link android.app.Service#onTimeout(int)} is not called on such versions.
     *
     * @see android.app.Service#onTimeout(int)
     */
    public static final int FOREGROUND_SERVICE_TYPE_SHORT_SERVICE = 2048;

    /**
     * Constant corresponding to {@code specialUse} in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * Use cases that can't be categorized into any other foreground service types, but also
     * can't use {@link android.app.job.JobInfo.Builder} APIs.
     *
     * <p>The use of this foreground service type may be restricted. Additionally, apps must declare
     * a service-level {@link PackageManager#PROPERTY_SPECIAL_USE_FGS_SUBTYPE &lt;property&gt;} in
     * {@code AndroidManifest.xml} as a hint of what the exact use case here is.
     * Here is an example:
     * <pre>
     *  &lt;uses-permission
     *      android:name="android.permission.FOREGROUND_SERVICE_SPECIAL_USE"
     *  /&gt;
     *  &lt;service
     *      android:name=".MySpecialForegroundService"
     *      android:foregroundServiceType="specialUse"&gt;
     *      &lt;property
     *          android:name="android.app.PROPERTY_SPECIAL_USE_FGS_SUBTYPE"
     *          android:value="foo"
     *      /&gt;
     * &lt;/service&gt;
     * </pre>
     *
     * In a future release of Android, if the above foreground service type {@code foo} is supported
     * by the platform, to offer the backward compatibility, the app could specify
     * the {@code android:maxSdkVersion} attribute in the &lt;uses-permission&gt; section,
     * and also add the foreground service type {@code foo} into
     * the {@code android:foregroundServiceType}, therefore the same app could be installed
     * in both platforms.
     * <pre>
     *  &lt;uses-permission
     *      android:name="android.permission.FOREGROUND_SERVICE_SPECIAL_USE"
     *      android:maxSdkVersion="last_sdk_version_without_type_foo"
     *  /&gt;
     *  &lt;service
     *      android:name=".MySpecialForegroundService"
     *      android:foregroundServiceType="specialUse|foo"&gt;
     *      &lt;property
     *          android:name="android.app.PROPERTY_SPECIAL_USE_FGS_SUBTYPE"
     *          android:value="foo"
     *      /&gt;
     * &lt;/service&gt;
     * </pre>
     */
    public static final int FOREGROUND_SERVICE_TYPE_SPECIAL_USE = 1073741824;

    /**
     * Constant corresponding to {@code systemExempted} in
     * the {@link android.R.attr#foregroundServiceType} attribute.
     * The system exempted foreground service use cases.
     *
     * <p class="note">Note, apps are allowed to use this type only in the following cases:
     * <ul>
     *   <li>App has a UID &lt; {@link android.os.Process#FIRST_APPLICATION_UID}</li>
     *   <li>App is on Doze allowlist</li>
     *   <li>Device is running in <a href="https://android.googlesource.com/platform/frameworks/base/+/master/packages/SystemUI/docs/demo_mode.md">Demo Mode</a></li>
     *   <li><a href="https://source.android.com/devices/tech/admin/provision">Device owner app</a></li>
     *   <li><a href="https://source.android.com/devices/tech/admin/managed-profiles">Profile owner apps</a></li>
     *   <li>Persistent apps</li>
     *   <li><a href="https://source.android.com/docs/core/connect/carrier">Carrier privileged apps</a></li>
     *   <li>Apps that have the {@code android.app.role.RoleManager#ROLE_EMERGENCY} role</li>
     *   <li>Headless system apps</li>
     *   <li><a href="{@docRoot}guide/topics/admin/device-admin">Device admin apps</a></li>
     *   <li>Active VPN apps</li>
     *   <li>Apps holding {@link android.Manifest.permission#SCHEDULE_EXACT_ALARM} or
     *       {@link android.Manifest.permission#USE_EXACT_ALARM} permission.</li>
     * </ul>
     * </p>
     */
    public static final int FOREGROUND_SERVICE_TYPE_SYSTEM_EXEMPTED = 1024;

    private ServiceInfo() {
    }
}
