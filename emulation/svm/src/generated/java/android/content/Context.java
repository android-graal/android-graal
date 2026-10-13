package android.content;

/**
 * Interface to global information about an application environment.  This is
 * an abstract class whose implementation is provided by
 * the Android system.  It
 * allows access to application-specific resources and classes, as well as
 * up-calls for application-level operations such as launching activities,
 * broadcasting and receiving intents, etc.
 */
public final class Context {
    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.accessibility.AccessibilityManager} for giving the user
     * feedback for UI events through the registered event listeners.
     *
     * @see #getSystemService(String)
     * @see android.view.accessibility.AccessibilityManager
     */
    public static final String ACCESSIBILITY_SERVICE = "accessibility";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.accounts.AccountManager} for receiving intents at a
     * time of your choosing.
     *
     * @see #getSystemService(String)
     * @see android.accounts.AccountManager
     */
    public static final String ACCOUNT_SERVICE = "account";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.ActivityManager} for interacting with the global
     * system state.
     *
     * @see #getSystemService(String)
     * @see android.app.ActivityManager
     */
    public static final String ACTIVITY_SERVICE = "activity";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.security.advancedprotection.AdvancedProtectionManager}
     * @see #getSystemService(String)
     * @see android.security.advancedprotection.AdvancedProtectionManager
     */
    public static final String ADVANCED_PROTECTION_SERVICE = "advanced_protection";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.AlarmManager} for receiving intents at a
     * time of your choosing.
     *
     * @see #getSystemService(String)
     * @see android.app.AlarmManager
     */
    public static final String ALARM_SERVICE = "alarm";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.appwidget.AppWidgetManager} for accessing AppWidgets.
     *
     * @see #getSystemService(String)
     */
    public static final String APPWIDGET_SERVICE = "appwidget";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link AppFunctionManager} for
     * executing app functions.
     *
     * @see #getSystemService(String)
     */
    public static final String APP_FUNCTION_SERVICE = "app_function";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.AppOpsManager} for tracking application operations
     * on the device.
     *
     * @see #getSystemService(String)
     * @see android.app.AppOpsManager
     */
    public static final String APP_OPS_SERVICE = "appops";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.app.appsearch.AppSearchManager} for
     * indexing and querying app data managed by the system.
     *
     * @see #getSystemService(String)
     */
    public static final String APP_SEARCH_SERVICE = "app_search";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.AudioManager} for handling management of volume,
     * ringer modes and audio routing.
     *
     * @see #getSystemService(String)
     * @see android.media.AudioManager
     */
    public static final String AUDIO_SERVICE = "audio";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.BatteryManager} for managing battery state.
     *
     * @see #getSystemService(String)
     */
    public static final String BATTERY_SERVICE = "batterymanager";

    /**
     * Flag for {@link #bindService}: indicates that the client application
     * binding to this service considers the service to be more important than
     * the app itself.  When set, the platform will try to have the out of
     * memory killer kill the app before it kills the service it is bound to, though
     * this is not guaranteed to be the case.
     */
    public static final int BIND_ABOVE_CLIENT = 8;

    /**
     * Flag for {@link #bindService}: If binding from an activity, allow the
     * target service's process importance to be raised based on whether the
     * activity is visible to the user, regardless whether another flag is
     * used to reduce the amount that the client process's overall importance
     * is used to impact it.
     */
    public static final int BIND_ADJUST_WITH_ACTIVITY = 128;

    /**
     * Flag for {@link #bindService}: If binding from an app that is visible, the bound service is
     * allowed to start an activity from background. This was the default behavior before SDK
     * version {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}. Since then, the default
     * behavior changed to disallow the bound service to start a background activity even if the app
     * bound to it is in foreground, unless this flag is specified when binding.
     */
    public static final int BIND_ALLOW_ACTIVITY_STARTS = 512;

    /**
     * Flag for {@link #bindService}: allow the process hosting the bound
     * service to go through its normal memory management.  It will be
     * treated more like a running service, allowing the system to
     * (temporarily) expunge the process if low on memory or for some other
     * whim it may have, and being more aggressive about making it a candidate
     * to be killed (and restarted) if running for a long time.
     */
    public static final int BIND_ALLOW_OOM_MANAGEMENT = 16;

    /**
     * Flag for {@link #bindService}: automatically create the service as long
     * as the binding exists.  Note that while this will create the service,
     * its {@link android.app.Service#onStartCommand}
     * method will still only be called due to an
     * explicit call to {@link #startService}.  Even without that, though,
     * this still provides you with access to the service object while the
     * service is created.
     *
     * <p>Note that prior to {@link android.os.Build.VERSION_CODES#ICE_CREAM_SANDWICH},
     * not supplying this flag would also impact how important the system
     * consider's the target service's process to be.  When set, the only way
     * for it to be raised was by binding from a service in which case it will
     * only be important when that activity is in the foreground.  Now to
     * achieve this behavior you must explicitly supply the new flag
     * {@link #BIND_ADJUST_WITH_ACTIVITY}.  For compatibility, old applications
     * that don't specify {@link #BIND_AUTO_CREATE} will automatically have
     * the flags {@link #BIND_WAIVE_PRIORITY} and
     * {@link #BIND_ADJUST_WITH_ACTIVITY} set for them in order to achieve
     * the same result.
     */
    public static final int BIND_AUTO_CREATE = 1;

    /**
     * Flag for {@link #bindService}: include debugging help for mismatched
     * calls to unbind.  When this flag is set, the callstack of the following
     * {@link #unbindService} call is retained, to be printed if a later
     * incorrect unbind call is made.  Note that doing this requires retaining
     * information about the binding that was made for the lifetime of the app,
     * resulting in a leak -- this should only be used for debugging.
     */
    public static final int BIND_DEBUG_UNBIND = 2;

    /**
     * Flag for {@link #bindService}: The service being bound is an
     * {@link android.R.attr#isolatedProcess isolated},
     * {@link android.R.attr#externalService external} service.  This binds the service into the
     * calling application's package, rather than the package in which the service is declared.
     * <p>
     * When using this flag, the code for the service being bound will execute under the calling
     * application's package name and user ID.  Because the service must be an isolated process,
     * it will not have direct access to the application's data, though.
     *
     * The purpose of this flag is to allow applications to provide services that are attributed
     * to the app using the service, rather than the application providing the service.
     * </p>
     *
     * <em>This flag is NOT compatible with {@link BindServiceFlags}. If you need to use
     * {@link BindServiceFlags}, you must use {@link #BIND_EXTERNAL_SERVICE_LONG} instead.</em>
     */
    public static final int BIND_EXTERNAL_SERVICE = -2147483648;

    /**
     * Works in the same way as {@link #BIND_EXTERNAL_SERVICE}, but it's defined as a {@code long}
     * value that is compatible to {@link BindServiceFlags}.
     */
    public static final long BIND_EXTERNAL_SERVICE_LONG = 4611686018427387904L;

    /**
     * Flag for {@link #bindService}: this service is very important to
     * the client, so should be brought to the foreground process level
     * when the client is.  Normally a process can only be raised to the
     * visibility level by a client, even if that client is in the foreground.
     */
    public static final int BIND_IMPORTANT = 64;

    /**
     * Flag for {@link #bindService}: If binding from an app that has specific capabilities
     * due to its foreground state such as an activity or foreground service, then this flag will
     * allow the bound app to get the same capabilities, as long as it has the required permissions
     * as well.
     *
     * If binding from a top app and its target SDK version is at or above
     * {@link android.os.Build.VERSION_CODES#R}, the app needs to
     * explicitly use BIND_INCLUDE_CAPABILITIES flag to pass all capabilities to the service so the
     * other app can have while-in-use access such as location, camera, microphone from background.
     * If binding from a top app and its target SDK version is below
     * {@link android.os.Build.VERSION_CODES#R}, BIND_INCLUDE_CAPABILITIES is implicit.
     */
    public static final int BIND_INCLUDE_CAPABILITIES = 4096;

    /**
     * Flag for {@link #bindService}: don't allow this binding to raise
     * the target service's process to the foreground scheduling priority.
     * It will still be raised to at least the same memory priority
     * as the client (so that its process will not be killable in any
     * situation where the client is not killable), but for CPU scheduling
     * purposes it may be left in the background.  This only has an impact
     * in the situation where the binding client is a foreground process
     * and the target service is in a background process.
     */
    public static final int BIND_NOT_FOREGROUND = 4;

    /**
     * Flag for {@link #bindService}: If binding from an app that is visible or user-perceptible,
     * lower the target service's importance to below the perceptible level. This allows
     * the system to (temporarily) expunge the bound process from memory to make room for more
     * important user-perceptible processes.
     */
    public static final int BIND_NOT_PERCEPTIBLE = 256;

    /**
     * Flag for {@link #bindIsolatedService}: Bind the service into a shared isolated process,
     * but only with other isolated services from the same package that declare the same process
     * name.
     *
     * <p>Specifying this flag allows multiple isolated services defined in the same package to be
     * running in a single shared isolated process. This shared isolated process must be specified
     * since this flag will not work with the default application process.
     *
     * <p>This flag is different from {@link #BIND_SHARED_ISOLATED_PROCESS} since it only
     * allows binding services from the same package in the same shared isolated process. This also
     * means the shared package isolated process is global, and not scoped to each potential
     * calling app.
     *
     * <p>The shared isolated process instance is identified by the "android:process" attribute
     * defined by the service. This flag cannot be used without this attribute set.
     */
    public static final int BIND_PACKAGE_ISOLATED_PROCESS = 16384;

    /**
     * Flag for {@link #bindIsolatedService}: Bind the service into a shared isolated process.
     * Specifying this flag allows multiple isolated services to be running in a single shared
     * isolated process.
     *
     * The shared isolated process instance is identified by the <var>instanceName</var>
     * parameter in {@link #bindIsolatedService(Intent, int, String, Executor, ServiceConnection)}.
     *
     * Subsequent calls to {@link #bindIsolatedService} with the same <var>instanceName</var>
     * will cause the isolated service to be co-located in the same shared isolated process.
     *
     * Note that the shared isolated process is scoped to the calling app; once created, only
     * the calling app can bind additional isolated services into the shared process. However,
     * the services themselves can come from different APKs and therefore different vendors.
     *
     * Only services that set the {@link android.R.attr#allowSharedIsolatedProcess} attribute
     * to {@code true} are allowed to be bound into a shared isolated process.
     */
    public static final int BIND_SHARED_ISOLATED_PROCESS = 8192;

    /**
     * Flag for {@link #bindService}: don't impact the scheduling or
     * memory management priority of the target service's hosting process.
     * Allows the service's process to be managed on the background LRU list
     * just like a regular application process in the background.
     */
    public static final int BIND_WAIVE_PRIORITY = 32;

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.biometrics.BiometricManager} for handling
     * biometric and PIN/pattern/password authentication.
     *
     * @see #getSystemService
     * @see android.hardware.biometrics.BiometricManager
     */
    public static final String BIOMETRIC_SERVICE = "biometric";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.blob.BlobStoreManager} for contributing and accessing data blobs
     * from the blob store maintained by the system.
     *
     * @see #getSystemService(String)
     * @see android.app.blob.BlobStoreManager
     */
    public static final String BLOB_STORE_SERVICE = "blob_store";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.bluetooth.BluetoothManager} for using Bluetooth.
     *
     * @see #getSystemService(String)
     */
    public static final String BLUETOOTH_SERVICE = "bluetooth";

    /**
     * Service to capture a bugreport.
     * @see #getSystemService(String)
     * @see android.os.BugreportManager
     */
    public static final String BUGREPORT_SERVICE = "bugreport";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.camera2.CameraManager} for interacting with
     * camera devices.
     *
     * @see #getSystemService(String)
     * @see android.hardware.camera2.CameraManager
     */
    public static final String CAMERA_SERVICE = "camera";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.accessibility.CaptioningManager} for obtaining
     * captioning properties and listening for changes in captioning
     * preferences.
     *
     * @see #getSystemService(String)
     * @see android.view.accessibility.CaptioningManager
     */
    public static final String CAPTIONING_SERVICE = "captioning";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.CarrierConfigManager} for reading carrier configuration values.
     *
     * @see #getSystemService(String)
     * @see android.telephony.CarrierConfigManager
     */
    public static final String CARRIER_CONFIG_SERVICE = "carrier_config";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.ClipboardManager} for accessing and modifying
     * the contents of the global clipboard.
     *
     * @see #getSystemService(String)
     * @see android.content.ClipboardManager
     */
    public static final String CLIPBOARD_SERVICE = "clipboard";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.companion.CompanionDeviceManager} for managing companion devices
     *
     * @see #getSystemService(String)
     * @see android.companion.CompanionDeviceManager
     */
    public static final String COMPANION_DEVICE_SERVICE = "companiondevice";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.ConnectivityDiagnosticsManager} for performing network connectivity diagnostics
     * as well as receiving network connectivity information from the system.
     *
     * @see #getSystemService(String)
     * @see android.net.ConnectivityDiagnosticsManager
     */
    public static final String CONNECTIVITY_DIAGNOSTICS_SERVICE = "connectivity_diagnostics";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.ConnectivityManager} for handling management of
     * network connections.
     *
     * @see #getSystemService(String)
     * @see android.net.ConnectivityManager
     */
    public static final String CONNECTIVITY_SERVICE = "connectivity";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.ConsumerIrManager} for transmitting infrared
     * signals from the device.
     *
     * @see #getSystemService(String)
     * @see android.hardware.ConsumerIrManager
     */
    public static final String CONSUMER_IR_SERVICE = "consumer_ir";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link E2eeContactKeysManager} to managing contact keys.
     *
     * @see #getSystemService(String)
     * @see E2eeContactKeysManager
     */
    public static final String CONTACT_KEYS_SERVICE = "contact_keys";

    /**
     * Flag for use with {@link #createPackageContext}: ignore any security
     * restrictions on the Context being requested, allowing it to always
     * be loaded.  For use with {@link #CONTEXT_INCLUDE_CODE} to allow code
     * to be loaded into a process even when it isn't safe to do so.  Use
     * with extreme care!
     */
    public static final int CONTEXT_IGNORE_SECURITY = 2;

    /**
     * Flag for use with {@link #createPackageContext}: include the application
     * code with the context.  This means loading code into the caller's
     * process, so that {@link #getClassLoader()} can be used to instantiate
     * the application's classes.  Setting this flags imposes security
     * restrictions on what application context you can access; if the
     * requested application can not be safely loaded into your process,
     * java.lang.SecurityException will be thrown.  If this flag is not set,
     * there will be no restrictions on the packages that can be loaded,
     * but {@link #getClassLoader} will always return the default system
     * class loader.
     */
    public static final int CONTEXT_INCLUDE_CODE = 1;

    /**
     * Flag for use with {@link #createPackageContext}: a restricted context may
     * disable specific features. For instance, a View associated with a restricted
     * context would ignore particular XML attributes.
     */
    public static final int CONTEXT_RESTRICTED = 4;

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.credentials.CredentialManager} to authenticate a user to your app.
     *
     * @see #getSystemService(String)
     * @see CredentialManager
     */
    public static final String CREDENTIAL_SERVICE = "credential";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.pm.CrossProfileApps} for cross profile operations.
     *
     * @see #getSystemService(String)
     */
    public static final String CROSS_PROFILE_APPS_SERVICE = "crossprofileapps";

    /**
     * The default device ID, which is the ID of the primary (non-virtual) device.
     */
    public static final int DEVICE_ID_DEFAULT = 0;

    /**
     * Invalid device ID.
     */
    public static final int DEVICE_ID_INVALID = -1;

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.devicelock.DeviceLockManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String DEVICE_LOCK_SERVICE = "device_lock";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.admin.DevicePolicyManager} for working with global
     * device policy management.
     *
     * @see #getSystemService(String)
     */
    public static final String DEVICE_POLICY_SERVICE = "device_policy";

    /**
     * Use with {@link #getSystemService(String)} to access
     * {@link android.view.displayhash.DisplayHashManager} to handle display hashes.
     *
     * @see #getSystemService(String)
     */
    public static final String DISPLAY_HASH_SERVICE = "display_hash";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.display.DisplayManager} for interacting with display devices.
     *
     * @see #getSystemService(String)
     * @see android.hardware.display.DisplayManager
     */
    public static final String DISPLAY_SERVICE = "display";

    /**
     * Use with {@link #getSystemService(String)} to access
     * {@link android.content.pm.verify.domain.DomainVerificationManager} to retrieve approval and
     * user state for declared web domains.
     *
     * @see #getSystemService(String)
     * @see android.content.pm.verify.domain.DomainVerificationManager
     */
    public static final String DOMAIN_VERIFICATION_SERVICE = "domain_verification";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.DownloadManager} for requesting HTTP downloads.
     *
     * @see #getSystemService(String)
     */
    public static final String DOWNLOAD_SERVICE = "download";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.DropBoxManager} instance for recording
     * diagnostic logs.
     * @see #getSystemService(String)
     */
    public static final String DROPBOX_SERVICE = "dropbox";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.euicc.EuiccManager} to manage the device eUICC (embedded SIM).
     *
     * @see #getSystemService(String)
     * @see android.telephony.euicc.EuiccManager
     */
    public static final String EUICC_SERVICE = "euicc";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.security.FileIntegrityManager}.
     * @see #getSystemService(String)
     * @see android.security.FileIntegrityManager
     */
    public static final String FILE_INTEGRITY_SERVICE = "file_integrity";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.fingerprint.FingerprintManager} for handling management
     * of fingerprints.
     *
     * @see #getSystemService(String)
     * @see android.hardware.fingerprint.FingerprintManager
     */
    public static final String FINGERPRINT_SERVICE = "fingerprint";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link GameManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String GAME_SERVICE = "game";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link GrammaticalInflectionManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String GRAMMATICAL_INFLECTION_SERVICE = "grammatical_inflection";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.HardwarePropertiesManager} for accessing the hardware properties service.
     *
     * @see #getSystemService(String)
     */
    public static final String HARDWARE_PROPERTIES_SERVICE = "hardware_properties";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.health.connect.HealthConnectManager}.
     *
     * @see #getSystemService(String)
     * @see android.health.connect.HealthConnectManager
     */
    public static final String HEALTHCONNECT_SERVICE = "healthconnect";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.inputmethod.InputMethodManager} for accessing input
     * methods.
     *
     * @see #getSystemService(String)
     */
    public static final String INPUT_METHOD_SERVICE = "input_method";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.hardware.input.InputManager} for interacting with input devices.
     *
     * @see #getSystemService(String)
     * @see android.hardware.input.InputManager
     */
    public static final String INPUT_SERVICE = "input";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.IpSecManager} for encrypting Sockets or Networks with
     * IPSec.
     *
     * @see #getSystemService(String)
     */
    public static final String IPSEC_SERVICE = "ipsec";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.job.JobScheduler} instance for managing occasional
     * background tasks.
     * @see #getSystemService(String)
     * @see android.app.job.JobScheduler
     */
    public static final String JOB_SCHEDULER_SERVICE = "jobscheduler";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.KeyguardManager} for controlling keyguard.
     *
     * @see #getSystemService(String)
     * @see android.app.KeyguardManager
     */
    public static final String KEYGUARD_SERVICE = "keyguard";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.security.keystore.KeyStoreManager} for accessing
     * <a href="/privacy-and-security/keystore">Android Keystore</a>
     * functions.
     *
     * @see #getSystemService(String)
     * @see android.security.keystore.KeyStoreManager
     */
    public static final String KEYSTORE_SERVICE = "keystore";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.pm.LauncherApps} for querying and monitoring launchable apps across
     * profiles of a user.
     *
     * @see #getSystemService(String)
     * @see android.content.pm.LauncherApps
     */
    public static final String LAUNCHER_APPS_SERVICE = "launcherapps";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.LayoutInflater} for inflating layout resources in this
     * context.
     *
     * @see #getSystemService(String)
     * @see android.view.LayoutInflater
     */
    public static final String LAYOUT_INFLATER_SERVICE = "layout_inflater";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.LocaleManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String LOCALE_SERVICE = "locale";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.location.LocationManager} for controlling location
     * updates.
     *
     * @see #getSystemService(String)
     * @see android.location.LocationManager
     */
    public static final String LOCATION_SERVICE = "location";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.MediaCommunicationManager}
     * for managing {@link android.media.MediaSession2}.
     *
     * @see #getSystemService(String)
     * @see android.media.MediaCommunicationManager
     */
    public static final String MEDIA_COMMUNICATION_SERVICE = "media_communication";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.metrics.MediaMetricsManager} for interacting with media metrics
     * on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.metrics.MediaMetricsManager
     */
    public static final String MEDIA_METRICS_SERVICE = "media_metrics";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.media.projection.MediaProjectionManager} instance for managing
     * media projection sessions.
     * @see #getSystemService(String)
     * @see android.media.projection.MediaProjectionManager
     */
    public static final String MEDIA_PROJECTION_SERVICE = "media_projection";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.quality.MediaQuality} for standardize picture and audio
     * API parameters.
     *
     * @see #getSystemService(String)
     * @see android.media.quality.MediaQuality
     */
    public static final String MEDIA_QUALITY_SERVICE = "media_quality";

    /**
     * Use with {@link #getSystemService} to retrieve a
     * {@link android.media.MediaRouter} for controlling and managing
     * routing of media.
     *
     * @see #getSystemService(String)
     * @see android.media.MediaRouter
     */
    public static final String MEDIA_ROUTER_SERVICE = "media_router";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.session.MediaSessionManager} for managing media Sessions.
     *
     * @see #getSystemService(String)
     * @see android.media.session.MediaSessionManager
     */
    public static final String MEDIA_SESSION_SERVICE = "media_session";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.midi.MidiManager} for accessing the MIDI service.
     *
     * @see #getSystemService(String)
     */
    public static final String MIDI_SERVICE = "midi";

    /**
     * File creation mode: for use with {@link #openFileOutput}, if the file
     * already exists then write data to the end of the existing file
     * instead of erasing it.
     * @see #openFileOutput
     */
    public static final int MODE_APPEND = 32768;

    /**
     * Database open flag: when set, the database is opened with write-ahead
     * logging enabled by default.
     *
     * @see #openOrCreateDatabase(String, int, CursorFactory)
     * @see #openOrCreateDatabase(String, int, CursorFactory, DatabaseErrorHandler)
     * @see SQLiteDatabase#enableWriteAheadLogging
     */
    public static final int MODE_ENABLE_WRITE_AHEAD_LOGGING = 8;

    /**
     * SharedPreference loading flag: when set, the file on disk will
     * be checked for modification even if the shared preferences
     * instance is already loaded in this process.  This behavior is
     * sometimes desired in cases where the application has multiple
     * processes, all writing to the same SharedPreferences file.
     * Generally there are better forms of communication between
     * processes, though.
     *
     * <p>This was the legacy (but undocumented) behavior in and
     * before Gingerbread (Android 2.3) and this flag is implied when
     * targeting such releases.  For applications targeting SDK
     * versions <em>greater than</em> Android 2.3, this flag must be
     * explicitly set if desired.
     *
     * @see #getSharedPreferences
     *
     * @deprecated MODE_MULTI_PROCESS does not work reliably in
     * some versions of Android, and furthermore does not provide any
     * mechanism for reconciling concurrent modifications across
     * processes.  Applications should not attempt to use it.  Instead,
     * they should use an explicit cross-process data management
     * approach such as {@link android.content.ContentProvider ContentProvider}.
     */
    @Deprecated
    public static final int MODE_MULTI_PROCESS = 4;

    /**
     * Database open flag: when set, the database is opened without support for
     * localized collators.
     *
     * @see #openOrCreateDatabase(String, int, CursorFactory)
     * @see #openOrCreateDatabase(String, int, CursorFactory, DatabaseErrorHandler)
     * @see SQLiteDatabase#NO_LOCALIZED_COLLATORS
     */
    public static final int MODE_NO_LOCALIZED_COLLATORS = 16;

    /**
     * File creation mode: the default mode, where the created file can only
     * be accessed by the calling application (or all applications sharing the
     * same user ID).
     */
    public static final int MODE_PRIVATE = 0;

    /**
     * File creation mode: allow all other applications to have read access to
     * the created file.
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#N}, attempting to use this
     * mode throws a {@link SecurityException}.
     *
     * @deprecated Creating world-readable files is very dangerous, and likely
     *             to cause security holes in applications. It is strongly
     *             discouraged; instead, applications should use more formal
     *             mechanism for interactions such as {@link ContentProvider},
     *             {@link BroadcastReceiver}, and {@link android.app.Service}.
     *             There are no guarantees that this access mode will remain on
     *             a file, such as when it goes through a backup and restore.
     * @see androidx.core.content.FileProvider
     * @see Intent#FLAG_GRANT_WRITE_URI_PERMISSION
     */
    @Deprecated
    public static final int MODE_WORLD_READABLE = 1;

    /**
     * File creation mode: allow all other applications to have write access to
     * the created file.
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#N}, attempting to use this
     * mode will throw a {@link SecurityException}.
     *
     * @deprecated Creating world-writable files is very dangerous, and likely
     *             to cause security holes in applications. It is strongly
     *             discouraged; instead, applications should use more formal
     *             mechanism for interactions such as {@link ContentProvider},
     *             {@link BroadcastReceiver}, and {@link android.app.Service}.
     *             There are no guarantees that this access mode will remain on
     *             a file, such as when it goes through a backup and restore.
     * @see androidx.core.content.FileProvider
     * @see Intent#FLAG_GRANT_WRITE_URI_PERMISSION
     */
    @Deprecated
    public static final int MODE_WORLD_WRITEABLE = 2;

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.usage.NetworkStatsManager} for querying network usage stats.
     *
     * @see #getSystemService(String)
     * @see android.app.usage.NetworkStatsManager
     */
    public static final String NETWORK_STATS_SERVICE = "netstats";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.nfc.NfcManager} for using NFC.
     *
     * @see #getSystemService(String)
     */
    public static final String NFC_SERVICE = "nfc";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.NotificationManager} for informing the user of
     * background events.
     *
     * @see #getSystemService(String)
     * @see android.app.NotificationManager
     */
    public static final String NOTIFICATION_SERVICE = "notification";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.nsd.NsdManager} for handling management of network service
     * discovery
     *
     * @see #getSystemService(String)
     * @see android.net.nsd.NsdManager
     */
    public static final String NSD_SERVICE = "servicediscovery";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.content.om.OverlayManager} for managing overlay packages.
     *
     * @see #getSystemService(String)
     * @see android.content.om.OverlayManager
     */
    public static final String OVERLAY_SERVICE = "overlay";

    /**
     * Use with {@link #getSystemService(String)} to access a {@link PeopleManager} to interact
     * with your published conversations.
     *
     * @see #getSystemService(String)
     */
    public static final String PEOPLE_SERVICE = "people";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.PerformanceHintManager} for accessing the performance hinting service.
     *
     * @see #getSystemService(String)
     */
    public static final String PERFORMANCE_HINT_SERVICE = "performance_hint";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.service.persistentdata.PersistentDataBlockManager} instance
     * for interacting with a storage device that lives across factory resets.
     *
     * @see #getSystemService(String)
     * @see android.service.persistentdata.PersistentDataBlockManager
     */
    public static final String PERSISTENT_DATA_BLOCK_SERVICE = "persistent_data_block";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.PowerManager} for controlling power management,
     * including "wake locks," which let you keep the device on while
     * you're running long tasks.
     */
    public static final String POWER_SERVICE = "power";

    /**
     * {@link android.print.PrintManager} for printing and managing
     * printers and print tasks.
     *
     * @see #getSystemService(String)
     * @see android.print.PrintManager
     */
    public static final String PRINT_SERVICE = "print";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.os.ProfilingManager}.
     *
     * @see #getSystemService(String)
     */
    public static final String PROFILING_SERVICE = "profiling";

    /**
     * Flag for {@link #registerReceiver}: The receiver can receive broadcasts from other Apps.
     * Has the same behavior as marking a statically registered receiver with "exported=true"
     */
    public static final int RECEIVER_EXPORTED = 2;

    /**
     * Flag for {@link #registerReceiver}: The receiver cannot receive broadcasts from other Apps.
     * Has the same behavior as marking a statically registered receiver with "exported=false"
     */
    public static final int RECEIVER_NOT_EXPORTED = 4;

    /**
     * Flag for {@link #registerReceiver}: The receiver can receive broadcasts from Instant Apps.
     */
    public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 1;

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.RestrictionsManager} for retrieving application restrictions
     * and requesting permissions for restricted operations.
     * @see #getSystemService(String)
     * @see android.content.RestrictionsManager
     */
    public static final String RESTRICTIONS_SERVICE = "restrictions";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.app.role.RoleManager}
     * for managing roles.
     *
     * @see #getSystemService(String)
     * @see android.app.role.RoleManager
     */
    public static final String ROLE_SERVICE = "role";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.satellite.SatelliteManager} for accessing satellite functionality.
     *
     * @see #getSystemService(String)
     * @see android.telephony.satellite.SatelliteManager
     */
    public static final String SATELLITE_SERVICE = "satellite";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.SearchManager} for handling searches.
     *
     * <p>
     * {@link Configuration#UI_MODE_TYPE_WATCH} does not support
     * {@link android.app.SearchManager}.
     *
     * @see #getSystemService
     * @see android.app.SearchManager
     */
    public static final String SEARCH_SERVICE = "search";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.SecurityStateManager} for accessing the security state manager service.
     *
     * @see #getSystemService(String)
     * @see android.os.SecurityStateManager
     */
    public static final String SECURITY_STATE_SERVICE = "security_state";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.hardware.SensorManager} for accessing sensors.
     *
     * @see #getSystemService(String)
     * @see android.hardware.SensorManager
     */
    public static final String SENSOR_SERVICE = "sensor";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.content.pm.ShortcutManager} for accessing the launcher shortcut service.
     *
     * @see #getSystemService(String)
     * @see android.content.pm.ShortcutManager
     */
    public static final String SHORTCUT_SERVICE = "shortcut";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.StatusBarManager} for interacting with the status bar and quick settings.
     *
     * @see #getSystemService(String)
     * @see android.app.StatusBarManager
     */
    public static final String STATUS_BAR_SERVICE = "statusbar";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.os.storage.StorageManager} for accessing system storage
     * functions.
     *
     * @see #getSystemService(String)
     * @see android.os.storage.StorageManager
     */
    public static final String STORAGE_SERVICE = "storage";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.usage.StorageStatsManager} for accessing system storage
     * statistics.
     *
     * @see #getSystemService(String)
     * @see android.app.usage.StorageStatsManager
     */
    public static final String STORAGE_STATS_SERVICE = "storagestats";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.health.SystemHealthManager} for accessing system health (battery, power,
     * memory, etc) metrics.
     *
     * @see #getSystemService(String)
     */
    public static final String SYSTEM_HEALTH_SERVICE = "systemhealth";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telecom.TelecomManager} to manage telecom-related features
     * of the device.
     *
     * @see #getSystemService(String)
     * @see android.telecom.TelecomManager
     */
    public static final String TELECOM_SERVICE = "telecom";

    /**
     * Use with {@link #getSystemService(String)} to retrieve an
     * {@link android.telephony.ims.ImsManager}.
     */
    public static final String TELEPHONY_IMS_SERVICE = "telephony_ims";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.TelephonyManager} for handling management the
     * telephony features of the device.
     *
     * @see #getSystemService(String)
     * @see android.telephony.TelephonyManager
     */
    public static final String TELEPHONY_SERVICE = "phone";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.telephony.SubscriptionManager} for handling management the
     * telephony subscriptions of the device.
     *
     * @see #getSystemService(String)
     * @see android.telephony.SubscriptionManager
     */
    public static final String TELEPHONY_SUBSCRIPTION_SERVICE = "telephony_subscription_service";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.net.TetheringManager}
     * for managing tethering functions.
     *
     * @see android.net.TetheringManager
     */
    public static final String TETHERING_SERVICE = "tethering";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link TextClassificationManager} for text classification services.
     *
     * @see #getSystemService(String)
     * @see TextClassificationManager
     */
    public static final String TEXT_CLASSIFICATION_SERVICE = "textclassification";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.textservice.TextServicesManager} for accessing
     * text services.
     *
     * @see #getSystemService(String)
     */
    public static final String TEXT_SERVICES_MANAGER_SERVICE = "textservices";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.tv.ad.TvAdManager} for interacting with TV client-side advertisement
     * services on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.tv.ad.TvAdManager
     */
    public static final String TV_AD_SERVICE = "tv_ad";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.tv.TvInputManager} for interacting with TV inputs
     * on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.tv.TvInputManager
     */
    public static final String TV_INPUT_SERVICE = "tv_input";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.media.tv.interactive.TvInteractiveAppManager} for interacting with TV
     * interactive applications on the device.
     *
     * @see #getSystemService(String)
     * @see android.media.tv.interactive.TvInteractiveAppManager
     */
    public static final String TV_INTERACTIVE_APP_SERVICE = "tv_interactive_app";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.app.UiModeManager} for controlling UI modes.
     *
     * @see #getSystemService(String)
     */
    public static final String UI_MODE_SERVICE = "uimode";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.app.usage.UsageStatsManager} for querying device usage stats.
     *
     * @see #getSystemService(String)
     * @see android.app.usage.UsageStatsManager
     */
    public static final String USAGE_STATS_SERVICE = "usagestats";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.hardware.usb.UsbManager} for access to USB devices (as a USB host)
     * and for controlling this device's behavior as a USB device.
     *
     * @see #getSystemService(String)
     * @see android.hardware.usb.UsbManager
     */
    public static final String USB_SERVICE = "usb";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.os.UserManager} for managing users on devices that support multiple users.
     *
     * @see #getSystemService(String)
     * @see android.os.UserManager
     */
    public static final String USER_SERVICE = "user";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.os.VibratorManager}
     * for accessing the device vibrators, interacting with individual ones and playing synchronized
     * effects on multiple vibrators.
     *
     * @see #getSystemService(String)
     * @see android.os.VibratorManager
     */
    public static final String VIBRATOR_MANAGER_SERVICE = "vibrator_manager";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.os.Vibrator} for
     * interacting with the vibration hardware.
     *
     * @deprecated Use {@link android.os.VibratorManager} to retrieve the default system vibrator.
     * @see #getSystemService(String)
     * @see android.os.Vibrator
     */
    @Deprecated
    public static final String VIBRATOR_SERVICE = "vibrator";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.companion.virtual.VirtualDeviceManager} for managing virtual devices.
     *
     * On devices without {@link PackageManager#FEATURE_COMPANION_DEVICE_SETUP}
     * system feature the {@link #getSystemService(String)} will return {@code null}.
     *
     * @see #getSystemService(String)
     * @see android.companion.virtual.VirtualDeviceManager
     */
    public static final String VIRTUAL_DEVICE_SERVICE = "virtualdevice";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link android.net.VpnManager} to
     * manage profiles for the platform built-in VPN.
     *
     * @see #getSystemService(String)
     */
    public static final String VPN_MANAGEMENT_SERVICE = "vpn_management";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * com.android.server.WallpaperService for accessing wallpapers.
     *
     * @see #getSystemService(String)
     */
    public static final String WALLPAPER_SERVICE = "wallpaper";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.net.wifi.aware.WifiAwareManager} for handling management of
     * Wi-Fi Aware.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.aware.WifiAwareManager
     */
    public static final String WIFI_AWARE_SERVICE = "wifiaware";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.p2p.WifiP2pManager} for handling management of
     * Wi-Fi peer-to-peer connections.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.p2p.WifiP2pManager
     */
    public static final String WIFI_P2P_SERVICE = "wifip2p";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.rtt.WifiRttManager} for ranging devices with wifi.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.rtt.WifiRttManager
     */
    public static final String WIFI_RTT_RANGING_SERVICE = "wifirtt";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a {@link
     * android.net.wifi.WifiManager} for handling management of
     * Wi-Fi access.
     *
     * @see #getSystemService(String)
     * @see android.net.wifi.WifiManager
     */
    public static final String WIFI_SERVICE = "wifi";

    /**
     * Use with {@link #getSystemService(String)} to retrieve a
     * {@link android.view.WindowManager} for accessing the system's window
     * manager.
     *
     * @see #getSystemService(String)
     * @see android.view.WindowManager
     */
    public static final String WINDOW_SERVICE = "window";

    private Context() {
    }
}
