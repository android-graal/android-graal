package android.content.pm;

/**
 * Class for retrieving various kinds of information related to the application
 * packages that are currently installed on the device.
 *
 * You can find this class through {@link Context#getPackageManager}.
 *
 * <p class="note"><strong>Note: </strong>If your app targets Android 11 (API level 30) or
 * higher, the methods in this class each return a filtered list of apps. Learn more about how to
 * <a href="/training/basics/intents/package-visibility">manage package visibility</a>.
 * </p>
 */
public final class PackageManager {
    /**
     * Certificate input bytes: the input bytes represent an encoded X.509 Certificate which could
     * be generated using an {@code CertificateFactory}
     */
    public static final int CERT_INPUT_RAW_X509 = 0;

    /**
     * Certificate input bytes: the input bytes represent the SHA256 output of an encoded X.509
     * Certificate.
     */
    public static final int CERT_INPUT_SHA256 = 1;

    /**
     * Flag for {@link #setApplicationEnabledSetting(String, int, int)} and
     * {@link #setComponentEnabledSetting(ComponentName, int, int)}: This
     * component or application is in its default enabled state (as specified in
     * its manifest).
     * <p>
     * Explicitly setting the component state to this value restores it's
     * enabled state to whatever is set in the manifest.
     */
    public static final int COMPONENT_ENABLED_STATE_DEFAULT = 0;

    /**
     * Flag for {@link #setApplicationEnabledSetting(String, int, int)}
     * and {@link #setComponentEnabledSetting(ComponentName, int, int)}: This
     * component or application has been explicitly disabled, regardless of
     * what it has specified in its manifest.
     */
    public static final int COMPONENT_ENABLED_STATE_DISABLED = 2;

    /**
     * Flag for {@link #setApplicationEnabledSetting(String, int, int)} only: This
     * application should be considered, until the point where the user actually
     * wants to use it.  This means that it will not normally show up to the user
     * (such as in the launcher), but various parts of the user interface can
     * use {@link #GET_DISABLED_UNTIL_USED_COMPONENTS} to still see it and allow
     * the user to select it (as for example an IME, device admin, etc).  Such code,
     * once the user has selected the app, should at that point also make it enabled.
     * This option currently <strong>can not</strong> be used with
     * {@link #setComponentEnabledSetting(ComponentName, int, int)}.
     */
    public static final int COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED = 4;

    /**
     * Flag for {@link #setApplicationEnabledSetting(String, int, int)} only: The
     * user has explicitly disabled the application, regardless of what it has
     * specified in its manifest.  Because this is due to the user's request,
     * they may re-enable it if desired through the appropriate system UI.  This
     * option currently <strong>cannot</strong> be used with
     * {@link #setComponentEnabledSetting(ComponentName, int, int)}.
     */
    public static final int COMPONENT_ENABLED_STATE_DISABLED_USER = 3;

    /**
     * Flag for {@link #setApplicationEnabledSetting(String, int, int)}
     * and {@link #setComponentEnabledSetting(ComponentName, int, int)}: This
     * component or application has been explictily enabled, regardless of
     * what it has specified in its manifest.
     */
    public static final int COMPONENT_ENABLED_STATE_ENABLED = 1;

    /**
     * Flag parameter for {@link PackageInstaller#uninstall(VersionedPackage, int, IntentSender)} to
     * indicate that the deletion is an archival. This
     * flag is only for internal usage as part of
     * {@link PackageInstaller#requestArchive}.
     */
    public static final int DELETE_ARCHIVE = 16;

    /**
     * Flag parameter for
     * {@link #setComponentEnabledSetting(android.content.ComponentName, int, int)} to indicate
     * that you don't want to kill the app containing the component.  Be careful when you set this
     * since changing component states can make the containing application's behavior unpredictable.
     */
    public static final int DONT_KILL_APP = 1;

    /**
     * Extra field name for the ID of a package pending verification. Passed to
     * a package verifier and is used to call back to
     * {@link PackageManager#verifyPendingInstall(int, int)}
     */
    public static final String EXTRA_VERIFICATION_ID = "android.content.pm.extra.VERIFICATION_ID";

    /**
     * Extra field name for the result of a verification, either
     * {@link #VERIFICATION_ALLOW}, or {@link #VERIFICATION_REJECT}.
     * Passed to package verifiers after a package is verified.
     */
    public static final String EXTRA_VERIFICATION_RESULT = "android.content.pm.extra.VERIFICATION_RESULT";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports running activities on secondary displays. Displays here
     * refers to both physical and virtual displays. Disabling this feature can impact
     * support for application projection use-cases and support for virtual devices
     * on the device.
     */
    public static final String FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS = "android.software.activities_on_secondary_displays";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports app widgets.
     */
    public static final String FEATURE_APP_WIDGETS = "android.software.app_widgets";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device's
     * audio pipeline is low-latency, more suitable for audio applications sensitive to delays or
     * lag in sound input or output.
     */
    public static final String FEATURE_AUDIO_LOW_LATENCY = "android.hardware.audio.low_latency";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes at least one form of audio
     * output, as defined in the Android Compatibility Definition Document (CDD)
     * <a href="https://source.android.com/compatibility/android-cdd#7_8_audio">section 7.8 Audio</a>.
     */
    public static final String FEATURE_AUDIO_OUTPUT = "android.hardware.audio.output";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device has professional audio level of functionality and performance.
     */
    public static final String FEATURE_AUDIO_PRO = "android.hardware.audio.pro";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}
     * which indicates whether head tracking for spatial audio operates with low-latency,
     * as defined by the CDD criteria for the feature.
     */
    public static final String FEATURE_AUDIO_SPATIAL_HEADTRACKING_LOW_LATENCY = "android.hardware.audio.spatial.headtracking.low_latency";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports autofill of user credentials, addresses, credit cards, etc
     * via integration with {@link android.service.autofill.AutofillService autofill
     * providers}.
     */
    public static final String FEATURE_AUTOFILL = "android.software.autofill";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: This is a device dedicated to showing UI
     * on a vehicle headunit. A headunit here is defined to be inside a
     * vehicle that may or may not be moving. A headunit uses either a
     * primary display in the center console and/or additional displays in
     * the instrument cluster or elsewhere in the vehicle. Headunit display(s)
     * have limited size and resolution. The user will likely be focused on
     * driving so limiting driver distraction is a primary concern. User input
     * can be a variety of hard buttons, touch, rotary controllers and even mouse-
     * like interfaces.
     */
    public static final String FEATURE_AUTOMOTIVE = "android.hardware.type.automotive";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device can perform backup and restore operations on installed applications.
     */
    public static final String FEATURE_BACKUP = "android.software.backup";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device is capable of communicating with
     * other devices via Bluetooth.
     */
    public static final String FEATURE_BLUETOOTH = "android.hardware.bluetooth";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device is capable of communicating with
     * other devices via Bluetooth Low Energy radio.
     */
    public static final String FEATURE_BLUETOOTH_LE = "android.hardware.bluetooth_le";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device is capable of ranging with
     * other devices using channel sounding via Bluetooth Low Energy radio.
     */
    public static final String FEATURE_BLUETOOTH_LE_CHANNEL_SOUNDING = "android.hardware.bluetooth_le.channel_sounding";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has a camera facing away
     * from the screen.
     */
    public static final String FEATURE_CAMERA = "android.hardware.camera";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has at least one camera pointing in
     * some direction, or can support an external camera being connected to it.
     */
    public static final String FEATURE_CAMERA_ANY = "android.hardware.camera.any";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: At least one
     * of the cameras on the device supports the
     * {@link android.hardware.camera2.CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_MOTION_TRACKING
     * MOTION_TRACKING} capability level.
     */
    public static final String FEATURE_CAMERA_AR = "android.hardware.camera.ar";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's camera supports auto-focus.
     */
    public static final String FEATURE_CAMERA_AUTOFOCUS = "android.hardware.camera.autofocus";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: At least one
     * of the cameras on the device supports the
     * {@link android.hardware.camera2.CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING manual post-processing}
     * capability level.
     */
    public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_POST_PROCESSING = "android.hardware.camera.capability.manual_post_processing";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: At least one
     * of the cameras on the device supports the
     * {@link android.hardware.camera2.CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR manual sensor}
     * capability level.
     */
    public static final String FEATURE_CAMERA_CAPABILITY_MANUAL_SENSOR = "android.hardware.camera.capability.manual_sensor";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: At least one
     * of the cameras on the device supports the
     * {@link android.hardware.camera2.CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_RAW RAW}
     * capability level.
     */
    public static final String FEATURE_CAMERA_CAPABILITY_RAW = "android.hardware.camera.capability.raw";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's main front and back cameras can stream
     * concurrently as described in {@link
     * android.hardware.camera2.CameraManager#getConcurrentCameraIds()}.
     * </p>
     * <p>While {@link android.hardware.camera2.CameraManager#getConcurrentCameraIds()} and
     * associated APIs are only available on API level 30 or newer, this feature flag may be
     * advertised by devices on API levels below 30. If present on such a device, the same
     * guarantees hold: The main front and main back camera can be used at the same time, with
     * guaranteed stream configurations as defined in the table for concurrent streaming at
     * {@link android.hardware.camera2.CameraDevice#createCaptureSession(android.hardware.camera2.params.SessionConfiguration)}.
     * </p>
     */
    public static final String FEATURE_CAMERA_CONCURRENT = "android.hardware.camera.concurrent";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device can support having an external camera connected to it.
     * The external camera may not always be connected or available to applications to use.
     */
    public static final String FEATURE_CAMERA_EXTERNAL = "android.hardware.camera.external";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's camera supports flash.
     */
    public static final String FEATURE_CAMERA_FLASH = "android.hardware.camera.flash";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has a front facing camera.
     */
    public static final String FEATURE_CAMERA_FRONT = "android.hardware.camera.front";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: At least one
     * of the cameras on the device supports the
     * {@link android.hardware.camera2.CameraCharacteristics#INFO_SUPPORTED_HARDWARE_LEVEL full hardware}
     * capability level.
     */
    public static final String FEATURE_CAMERA_LEVEL_FULL = "android.hardware.camera.level.full";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports the
     * {@link android.R.attr#cantSaveState} API.
     */
    public static final String FEATURE_CANT_SAVE_STATE = "android.software.cant_save_state";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports {@link android.companion.CompanionDeviceManager#associate associating}
     * with devices via {@link android.companion.CompanionDeviceManager}.
     */
    public static final String FEATURE_COMPANION_DEVICE_SETUP = "android.software.companion_device_setup";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The Connection Service API is enabled on the device.
     * @deprecated use {@link #FEATURE_TELECOM} instead.
     */
    @Deprecated
    public static final String FEATURE_CONNECTION_SERVICE = "android.software.connectionservice";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device is capable of communicating with
     * consumer IR devices.
     */
    public static final String FEATURE_CONSUMER_IR = "android.hardware.consumerir";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports a system interface for the user to select
     * and bind device control services provided by applications.
     *
     * @see android.service.controls.ControlsProviderService
     */
    public static final String FEATURE_CONTROLS = "android.software.controls";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports retrieval of user credentials, via integration with credential providers.
     */
    public static final String FEATURE_CREDENTIALS = "android.software.credentials";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports device policy enforcement via device admins.
     */
    public static final String FEATURE_DEVICE_ADMIN = "android.software.device_admin";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports locking (for example, by a financing provider in case of a missed payment).
     */
    public static final String FEATURE_DEVICE_LOCK = "android.software.device_lock";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: This is a device for IoT and may not have an UI. An embedded
     * device is defined as a full stack Android device with or without a display and no
     * user-installable apps.
     */
    public static final String FEATURE_EMBEDDED = "android.hardware.type.embedded";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: This device supports ethernet.
     */
    public static final String FEATURE_ETHERNET = "android.hardware.ethernet";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports expanded picture-in-picture multi-window mode.
     *
     * @see android.app.PictureInPictureParams.Builder#setExpandedAspectRatio
     */
    public static final String FEATURE_EXPANDED_PICTURE_IN_PICTURE = "android.software.expanded_picture_in_picture";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has biometric hardware to perform face authentication.
     */
    public static final String FEATURE_FACE = "android.hardware.biometrics.face";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device does not have a touch screen, but
     * does support touch emulation for basic events. For instance, the
     * device might use a mouse or remote control to drive a cursor, and
     * emulate basic touch pointer events like down, up, drag, etc. All
     * devices that support android.hardware.touchscreen or a sub-feature are
     * presumed to also support faketouch.
     */
    public static final String FEATURE_FAKETOUCH = "android.hardware.faketouch";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device does not have a touch screen, but
     * does support touch emulation for basic events that supports distinct
     * tracking of two or more fingers.  This is an extension of
     * {@link #FEATURE_FAKETOUCH} for input devices with this capability.  Note
     * that unlike a distinct multitouch screen as defined by
     * {@link #FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT}, these kinds of input
     * devices will not actually provide full two-finger gestures since the
     * input is being transformed to cursor movement on the screen.  That is,
     * single finger gestures will move a cursor; two-finger swipes will
     * result in single-finger touch events; other two-finger gestures will
     * result in the corresponding two-finger touch event.
     */
    public static final String FEATURE_FAKETOUCH_MULTITOUCH_DISTINCT = "android.hardware.faketouch.multitouch.distinct";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device does not have a touch screen, but
     * does support touch emulation for basic events that supports tracking
     * a hand of fingers (5 or more fingers) fully independently.
     * This is an extension of
     * {@link #FEATURE_FAKETOUCH} for input devices with this capability.  Note
     * that unlike a multitouch screen as defined by
     * {@link #FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND}, not all two finger
     * gestures can be detected due to the limitations described for
     * {@link #FEATURE_FAKETOUCH_MULTITOUCH_DISTINCT}.
     */
    public static final String FEATURE_FAKETOUCH_MULTITOUCH_JAZZHAND = "android.hardware.faketouch.multitouch.jazzhand";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has biometric hardware to detect a fingerprint.
     */
    public static final String FEATURE_FINGERPRINT = "android.hardware.fingerprint";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports freeform window management.
     * Windows have title bars and can be moved and resized.
     */
    public static final String FEATURE_FREEFORM_WINDOW_MANAGEMENT = "android.software.freeform_window_management";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device has all of the inputs necessary to be considered a compatible game controller, or
     * includes a compatible game controller in the box.
     */
    public static final String FEATURE_GAMEPAD = "android.hardware.gamepad";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the device implements
     * the Android Keystore backed by an isolated execution environment. The version indicates
     * which features are implemented in the isolated execution environment:
     * <ul>
     * <li>400: Inclusion of module information (via tag MODULE_HASH) in the attestation record.
     * <li>300: Ability to include a second IMEI in the ID attestation record, see
     * {@link android.app.admin.DevicePolicyManager#ID_TYPE_IMEI}.
     * <li>200: Hardware support for Curve 25519 (including both Ed25519 signature generation and
     * X25519 key agreement).
     * <li>100: Hardware support for ECDH (see {@link javax.crypto.KeyAgreement}) and support
     * for app-generated attestation keys (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setAttestKeyAlias(String)}).
     * <li>41: Hardware enforcement of device-unlocked keys (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setUnlockedDeviceRequired(boolean)}).
     * <li>40: Support for wrapped key import (see {@link
     * android.security.keystore.WrappedKeyEntry}), optional support for ID attestation (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setDevicePropertiesAttestationIncluded(boolean)}),
     * attestation (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setAttestationChallenge(byte[])}),
     * AES, HMAC, ECDSA and RSA support where the secret or private key never leaves secure
     * hardware, and support for requiring user authentication before a key can be used.
     * </ul>
     * This feature version is guaranteed to be set for all devices launching with Android 12 and
     * may be set on devices launching with an earlier version. If the feature version is set, it
     * will at least have the value 40. If it's not set the device may have a version of
     * hardware-backed keystore but it may not support all features listed above.
     */
    public static final String FEATURE_HARDWARE_KEYSTORE = "android.hardware.hardware_keystore";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports high fidelity sensor processing
     * capabilities.
     */
    public static final String FEATURE_HIFI_SENSORS = "android.hardware.sensor.hifi_sensors";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports a home screen that is replaceable
     * by third party applications.
     */
    public static final String FEATURE_HOME_SCREEN = "android.software.home_screen";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the device supports
     * {@link android.security.identity.IdentityCredentialStore} implemented in secure hardware
     * at the given feature version.
     *
     * <p>Known feature versions include:
     * <ul>
     * <li><code>202009</code>: corresponds to the features included in the Identity Credential
     * API shipped in Android 11.
     * <li><code>202101</code>: corresponds to the features included in the Identity Credential
     * API shipped in Android 12.
     * <li><code>202201</code>: corresponds to the features included in the Identity Credential
     * API shipped in Android 13.
     * </ul>
     */
    public static final String FEATURE_IDENTITY_CREDENTIAL_HARDWARE = "android.hardware.identity_credential";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the device supports
     * {@link android.security.identity.IdentityCredentialStore} implemented in secure hardware
     * with direct access at the given feature version.
     * See {@link #FEATURE_IDENTITY_CREDENTIAL_HARDWARE} for known feature versions.
     */
    public static final String FEATURE_IDENTITY_CREDENTIAL_HARDWARE_DIRECT_ACCESS = "android.hardware.identity_credential_direct_access";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports adding new input methods implemented
     * with the {@link android.inputmethodservice.InputMethodService} API.
     */
    public static final String FEATURE_INPUT_METHODS = "android.software.input_methods";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device has
     * the requisite kernel support for multinetworking-capable IPsec tunnels.
     *
     * <p>This feature implies that the device supports XFRM Interfaces (CONFIG_XFRM_INTERFACE), or
     * VTIs with kernel patches allowing updates of output/set mark via UPDSA.
     */
    public static final String FEATURE_IPSEC_TUNNELS = "android.software.ipsec_tunnels";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device has
     * the requisite kernel support for migrating IPsec tunnels to new source/destination addresses.
     *
     * <p>This feature implies that the device supports XFRM Migration (CONFIG_XFRM_MIGRATE) and has
     * the kernel fixes to support cross-address-family IPsec tunnel migration
     */
    public static final String FEATURE_IPSEC_TUNNEL_MIGRATION = "android.software.ipsec_tunnel_migration";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has biometric hardware to perform iris authentication.
     */
    public static final String FEATURE_IRIS = "android.hardware.biometrics.iris";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device has
     * a Keystore implementation that can create application-specific attestation keys.
     * See {@link android.security.keystore.KeyGenParameterSpec.Builder#setAttestKeyAlias}.
     */
    public static final String FEATURE_KEYSTORE_APP_ATTEST_KEY = "android.hardware.keystore.app_attest_key";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device has
     * a Keystore implementation that can enforce limited use key in hardware with any max usage
     * count (including count equals to 1).
     */
    public static final String FEATURE_KEYSTORE_LIMITED_USE_KEY = "android.hardware.keystore.limited_use_key";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device has
     * a Keystore implementation that can only enforce limited use key in hardware with max usage
     * count equals to 1.
     */
    public static final String FEATURE_KEYSTORE_SINGLE_USE_KEY = "android.hardware.keystore.single_use_key";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports leanback UI. This is
     * typically used in a living room television experience, but is a software
     * feature unlike {@link #FEATURE_TELEVISION}. Devices running with this
     * feature will use resources associated with the "television" UI mode.
     */
    public static final String FEATURE_LEANBACK = "android.software.leanback";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports only leanback UI. Only
     * applications designed for this experience should be run, though this is
     * not enforced by the system.
     */
    public static final String FEATURE_LEANBACK_ONLY = "android.software.leanback_only";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports live TV and can display
     * contents from TV inputs implemented with the
     * {@link android.media.tv.TvInputService} API.
     */
    public static final String FEATURE_LIVE_TV = "android.software.live_tv";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports live wallpapers.
     */
    public static final String FEATURE_LIVE_WALLPAPER = "android.software.live_wallpaper";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports one or more methods of
     * reporting current location.
     */
    public static final String FEATURE_LOCATION = "android.hardware.location";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has a Global Positioning System
     * receiver and can report precise location.
     */
    public static final String FEATURE_LOCATION_GPS = "android.hardware.location.gps";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device can report location with coarse
     * accuracy using a network-based geolocation system.
     */
    public static final String FEATURE_LOCATION_NETWORK = "android.hardware.location.network";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports creating secondary users and managed profiles via
     * {@link DevicePolicyManager}.
     */
    public static final String FEATURE_MANAGED_USERS = "android.software.managed_users";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device can record audio via a
     * microphone.
     */
    public static final String FEATURE_MICROPHONE = "android.hardware.microphone";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device has a full implementation of the android.media.midi.* APIs.
     */
    public static final String FEATURE_MIDI = "android.software.midi";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device can communicate using Near-Field
     * Communications (NFC).
     */
    public static final String FEATURE_NFC = "android.hardware.nfc";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The Beam API is enabled on the device.
     */
    public static final String FEATURE_NFC_BEAM = "android.sofware.nfc.beam";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports host-
     * based NFC card emulation.
     */
    public static final String FEATURE_NFC_HOST_CARD_EMULATION = "android.hardware.nfc.hce";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports host-
     * based NFC-F card emulation.
     */
    public static final String FEATURE_NFC_HOST_CARD_EMULATION_NFCF = "android.hardware.nfc.hcef";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports eSE-
     * based NFC card emulation.
     */
    public static final String FEATURE_NFC_OFF_HOST_CARD_EMULATION_ESE = "android.hardware.nfc.ese";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports uicc-
     * based NFC card emulation.
     */
    public static final String FEATURE_NFC_OFF_HOST_CARD_EMULATION_UICC = "android.hardware.nfc.uicc";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the feature version
     * specifies a date such that the device is known to pass the OpenGLES dEQP test suite
     * associated with that date.  The date is encoded as follows:
     * <ul>
     * <li>Year in bits 31-16</li>
     * <li>Month in bits 15-8</li>
     * <li>Day in bits 7-0</li>
     * </ul>
     * <p>
     * Example: 2021-03-01 is encoded as 0x07E50301, and would indicate that the device passes the
     * OpenGL ES dEQP test suite version that was current on 2021-03-01.
     */
    public static final String FEATURE_OPENGLES_DEQP_LEVEL = "android.software.opengles.deqp.level";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports the OpenGL ES
     * <a href="http://www.khronos.org/registry/gles/extensions/ANDROID/ANDROID_extension_pack_es31a.txt">
     * Android Extension Pack</a>.
     */
    public static final String FEATURE_OPENGLES_EXTENSION_PACK = "android.hardware.opengles.aep";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: This is a device dedicated to be primarily used
     * with keyboard, mouse or touchpad. This includes traditional desktop
     * computers, laptops and variants such as convertibles or detachables.
     * Due to the larger screen, the device will most likely use the
     * {@link #FEATURE_FREEFORM_WINDOW_MANAGEMENT} feature as well.
     */
    public static final String FEATURE_PC = "android.hardware.type.pc";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports picture-in-picture multi-window mode.
     */
    public static final String FEATURE_PICTURE_IN_PICTURE = "android.software.picture_in_picture";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports printing.
     */
    public static final String FEATURE_PRINTING = "android.software.print";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's
     * {@link ActivityManager#isLowRamDevice() ActivityManager.isLowRamDevice()} method returns
     * true.
     */
    public static final String FEATURE_RAM_LOW = "android.hardware.ram.low";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's
     * {@link ActivityManager#isLowRamDevice() ActivityManager.isLowRamDevice()} method returns
     * false.
     */
    public static final String FEATURE_RAM_NORMAL = "android.hardware.ram.normal";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports landscape orientation
     * screens.  For backwards compatibility, you can assume that if neither
     * this nor {@link #FEATURE_SCREEN_PORTRAIT} is set then the device supports
     * both portrait and landscape.
     */
    public static final String FEATURE_SCREEN_LANDSCAPE = "android.hardware.screen.landscape";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports portrait orientation
     * screens.  For backwards compatibility, you can assume that if neither
     * this nor {@link #FEATURE_SCREEN_LANDSCAPE} is set then the device supports
     * both portrait and landscape.
     */
    public static final String FEATURE_SCREEN_PORTRAIT = "android.hardware.screen.portrait";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports secure removal of users. When a user is deleted the data associated
     * with that user is securely deleted and no longer available.
     */
    public static final String FEATURE_SECURELY_REMOVES_USERS = "android.software.securely_removes_users";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has a secure implementation of keyguard, meaning the
     * device supports PIN, pattern and password as defined in Android CDD
     */
    public static final String FEATURE_SECURE_LOCK_SCREEN = "android.software.secure_lock_screen";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device is
     * compatible with Android's security model.
     *
     * <p>See sections 2 and 9 in the
     * <a href="https://source.android.com/compatibility/android-cdd">Android CDD</a> for more
     * details.
     */
    public static final String FEATURE_SECURITY_MODEL_COMPATIBLE = "android.hardware.security.model.compatible";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes an accelerometer.
     */
    public static final String FEATURE_SENSOR_ACCELEROMETER = "android.hardware.sensor.accelerometer";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a limited axes accelerometer.
     */
    public static final String FEATURE_SENSOR_ACCELEROMETER_LIMITED_AXES = "android.hardware.sensor.accelerometer_limited_axes";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes an uncalibrated limited axes accelerometer.
     */
    public static final String FEATURE_SENSOR_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED = "android.hardware.sensor.accelerometer_limited_axes_uncalibrated";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes an ambient temperature sensor.
     */
    public static final String FEATURE_SENSOR_AMBIENT_TEMPERATURE = "android.hardware.sensor.ambient_temperature";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a barometer (air
     * pressure sensor.)
     */
    public static final String FEATURE_SENSOR_BAROMETER = "android.hardware.sensor.barometer";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a magnetometer (compass).
     */
    public static final String FEATURE_SENSOR_COMPASS = "android.hardware.sensor.compass";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports exposing head tracker sensors from peripheral
     * devices via the dynamic sensors API.
     */
    public static final String FEATURE_SENSOR_DYNAMIC_HEAD_TRACKER = "android.hardware.sensor.dynamic.head_tracker";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a gyroscope.
     */
    public static final String FEATURE_SENSOR_GYROSCOPE = "android.hardware.sensor.gyroscope";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a limited axes gyroscope.
     */
    public static final String FEATURE_SENSOR_GYROSCOPE_LIMITED_AXES = "android.hardware.sensor.gyroscope_limited_axes";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes an uncalibrated limited axes gyroscope.
     */
    public static final String FEATURE_SENSOR_GYROSCOPE_LIMITED_AXES_UNCALIBRATED = "android.hardware.sensor.gyroscope_limited_axes_uncalibrated";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a heading sensor.
     */
    public static final String FEATURE_SENSOR_HEADING = "android.hardware.sensor.heading";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a heart rate monitor.
     */
    public static final String FEATURE_SENSOR_HEART_RATE = "android.hardware.sensor.heartrate";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The heart rate sensor on this device is an Electrocardiogram.
     */
    public static final String FEATURE_SENSOR_HEART_RATE_ECG = "android.hardware.sensor.heartrate.ecg";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a hinge angle sensor.
     */
    public static final String FEATURE_SENSOR_HINGE_ANGLE = "android.hardware.sensor.hinge_angle";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a light sensor.
     */
    public static final String FEATURE_SENSOR_LIGHT = "android.hardware.sensor.light";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a proximity sensor.
     */
    public static final String FEATURE_SENSOR_PROXIMITY = "android.hardware.sensor.proximity";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a relative humidity sensor.
     */
    public static final String FEATURE_SENSOR_RELATIVE_HUMIDITY = "android.hardware.sensor.relative_humidity";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a hardware step counter.
     */
    public static final String FEATURE_SENSOR_STEP_COUNTER = "android.hardware.sensor.stepcounter";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device includes a hardware step detector.
     */
    public static final String FEATURE_SENSOR_STEP_DETECTOR = "android.hardware.sensor.stepdetector";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports Open Mobile API capable eSE-based secure
     * elements.
     */
    public static final String FEATURE_SE_OMAPI_ESE = "android.hardware.se.omapi.ese";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports Open Mobile API capable SD-based secure
     * elements.
     */
    public static final String FEATURE_SE_OMAPI_SD = "android.hardware.se.omapi.sd";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports Open Mobile API capable UICC-based secure
     * elements.
     */
    public static final String FEATURE_SE_OMAPI_UICC = "android.hardware.se.omapi.uicc";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The SIP API is enabled on the device.
     */
    public static final String FEATURE_SIP = "android.software.sip";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports SIP-based VOIP.
     */
    public static final String FEATURE_SIP_VOIP = "android.software.sip.voip";

    /**
     * Feature for {@link #getSystemAvailableFeatures}, {@link #hasSystemFeature(String)}, and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the device implements
     * the Android Keystore backed by a dedicated secure processor referred to as
     * <a href="https://source.android.com/security/best-practices/hardware#strongbox-keymaster">
     * StrongBox</a>. If this feature has a version, the version number indicates which features are
     * implemented in StrongBox:
     * <ul>
     * <li>400: Inclusion of module information (via tag MODULE_HASH) in the attestation record.
     * <li>300: Ability to include a second IMEI in the ID attestation record, see
     * {@link android.app.admin.DevicePolicyManager#ID_TYPE_IMEI}.
     * <li>200: No new features for StrongBox (the Android Keystore environment backed by an
     * isolated execution environment has gained support for Curve 25519 in this version, but
     * the implementation backed by a dedicated secure processor is not expected to implement it).
     * <li>100: Hardware support for ECDH (see {@link javax.crypto.KeyAgreement}) and support
     * for app-generated attestation keys (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setAttestKeyAlias(String)}).
     * <li>41: Hardware enforcement of device-unlocked keys (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setUnlockedDeviceRequired(boolean)}).
     * <li>40: Support for wrapped key import (see {@link
     * android.security.keystore.WrappedKeyEntry}), optional support for ID attestation (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setDevicePropertiesAttestationIncluded(boolean)}),
     * attestation (see {@link
     * android.security.keystore.KeyGenParameterSpec.Builder#setAttestationChallenge(byte[])}),
     * AES, HMAC, ECDSA and RSA support where the secret or private key never leaves secure
     * hardware, and support for requiring user authentication before a key can be used.
     * </ul>
     * If a device has StrongBox, this feature version number is guaranteed to be set for all
     * devices launching with Android 12 and may be set on devices launching with an earlier
     * version. If the feature version is set, it will at least have the value 40. If it's not
     * set the device may have StrongBox but it may not support all features listed above.
     */
    public static final String FEATURE_STRONGBOX_KEYSTORE = "android.hardware.strongbox_keystore";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports Telecom Service APIs.
     */
    public static final String FEATURE_TELECOM = "android.software.telecom";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has a telephony radio with data
     * communication support.
     */
    public static final String FEATURE_TELEPHONY = "android.hardware.telephony";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports Telephony APIs for calling service.
     *
     * <p>This feature should only be defined if {@link #FEATURE_TELEPHONY_RADIO_ACCESS},
     * {@link #FEATURE_TELEPHONY_SUBSCRIPTION}, and {@link #FEATURE_TELECOM} have been defined.
     */
    public static final String FEATURE_TELEPHONY_CALLING = "android.hardware.telephony.calling";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has a CDMA telephony stack.
     *
     * <p>This feature should only be defined if {@link #FEATURE_TELEPHONY} has been defined.
     */
    public static final String FEATURE_TELEPHONY_CDMA = "android.hardware.telephony.cdma";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports Telephony APIs for data service.
     *
     * <p>This feature should only be defined if both {@link #FEATURE_TELEPHONY_SUBSCRIPTION}
     * and {@link #FEATURE_TELEPHONY_RADIO_ACCESS} have been defined.
     */
    public static final String FEATURE_TELEPHONY_DATA = "android.hardware.telephony.data";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports embedded subscriptions on eUICCs.
     *
     * This feature should only be defined if {@link #FEATURE_TELEPHONY_SUBSCRIPTION}
     * has been defined.
     */
    public static final String FEATURE_TELEPHONY_EUICC = "android.hardware.telephony.euicc";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports multiple enabled profiles on eUICCs.
     *
     * <p>Devices declaring this feature must have an implementation of the
     *  {@link UiccCardInfo#getPorts},
     *  {@link UiccCardInfo#isMultipleEnabledProfilesSupported} and
     *  {@link android.telephony.euicc.EuiccManager#switchToSubscription (with portIndex)}.
     *
     * This feature should only be defined if {@link #FEATURE_TELEPHONY_EUICC} have been defined.
     */
    public static final String FEATURE_TELEPHONY_EUICC_MEP = "android.hardware.telephony.euicc.mep";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device has a GSM telephony stack.
     *
     * <p>This feature should only be defined if {@link #FEATURE_TELEPHONY} has been defined.
     */
    public static final String FEATURE_TELEPHONY_GSM = "android.hardware.telephony.gsm";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports attaching to IMS implementations using the ImsService API in telephony.
     *
     * <p>This feature should only be defined if {@link #FEATURE_TELEPHONY_DATA} has been defined.
     */
    public static final String FEATURE_TELEPHONY_IMS = "android.hardware.telephony.ims";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports cell-broadcast reception using the MBMS APIs.
     *
     * <p>This feature should only be defined if both {@link #FEATURE_TELEPHONY_SUBSCRIPTION}
     * and {@link #FEATURE_TELEPHONY_RADIO_ACCESS} have been defined.
     */
    public static final String FEATURE_TELEPHONY_MBMS = "android.hardware.telephony.mbms";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports Telephony APIs for SMS and MMS.
     *
     * <p>This feature should only be defined if both {@link #FEATURE_TELEPHONY_SUBSCRIPTION}
     * and {@link #FEATURE_TELEPHONY_RADIO_ACCESS} have been defined.
     */
    public static final String FEATURE_TELEPHONY_MESSAGING = "android.hardware.telephony.messaging";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports Telephony APIs for the radio access.
     *
     * <p>This feature should only be defined if {@link #FEATURE_TELEPHONY} has been defined.
     */
    public static final String FEATURE_TELEPHONY_RADIO_ACCESS = "android.hardware.telephony.radio.access";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports Telephony APIs for the subscription.
     *
     * <p>This feature should only be defined if {@link #FEATURE_TELEPHONY} has been defined.
     */
    public static final String FEATURE_TELEPHONY_SUBSCRIPTION = "android.hardware.telephony.subscription";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: This is a device dedicated to showing UI
     * on a television.  Television here is defined to be a typical living
     * room television experience: displayed on a big screen, where the user
     * is sitting far away from it, and the dominant form of input will be
     * something like a DPAD, not through touch or mouse.
     * @deprecated use {@link #FEATURE_LEANBACK} instead.
     */
    @Deprecated
    public static final String FEATURE_TELEVISION = "android.hardware.type.television";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device is capable of communicating with other devices via
     * <a href="https://www.threadgroup.org">Thread</a> networking protocol.
     */
    public static final String FEATURE_THREAD_NETWORK = "android.hardware.thread_network";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's display has a touch screen.
     */
    public static final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's touch screen supports
     * multitouch sufficient for basic two-finger gesture detection.
     */
    public static final String FEATURE_TOUCHSCREEN_MULTITOUCH = "android.hardware.touchscreen.multitouch";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's touch screen is capable of
     * tracking two or more fingers fully independently.
     */
    public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT = "android.hardware.touchscreen.multitouch.distinct";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device's touch screen is capable of
     * tracking a full hand of fingers fully independently -- that is, 5 or
     * more simultaneous independent pointers.
     */
    public static final String FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND = "android.hardware.touchscreen.multitouch.jazzhand";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports connecting to USB accessories.
     */
    public static final String FEATURE_USB_ACCESSORY = "android.hardware.usb.accessory";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports connecting to USB devices
     * as the USB host.
     */
    public static final String FEATURE_USB_HOST = "android.hardware.usb.host";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device is capable of communicating with
     * other devices via ultra wideband.
     */
    public static final String FEATURE_UWB = "android.hardware.uwb";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device supports verified boot.
     */
    public static final String FEATURE_VERIFIED_BOOT = "android.software.verified_boot";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device implements headtracking suitable for a VR device.
     */
    public static final String FEATURE_VR_HEADTRACKING = "android.hardware.vr.headtracking";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device implements an optimized mode for virtual reality (VR) applications that handles
     * stereoscopic rendering of notifications, and disables most monocular system UI components
     * while a VR application has user focus.
     * Devices declaring this feature must include an application implementing a
     * {@link android.service.vr.VrListenerService} that can be targeted by VR applications via
     * {@link android.app.Activity#setVrModeEnabled}.
     * @deprecated use {@link #FEATURE_VR_MODE_HIGH_PERFORMANCE} instead.
     */
    @Deprecated
    public static final String FEATURE_VR_MODE = "android.software.vr.mode";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device implements an optimized mode for virtual reality (VR) applications that handles
     * stereoscopic rendering of notifications, disables most monocular system UI components
     * while a VR application has user focus and meets extra CDD requirements to provide a
     * high-quality VR experience.
     * Devices declaring this feature must include an application implementing a
     * {@link android.service.vr.VrListenerService} that can be targeted by VR applications via
     * {@link android.app.Activity#setVrModeEnabled}.
     * and must meet CDD requirements to provide a high-quality VR experience.
     */
    public static final String FEATURE_VR_MODE_HIGH_PERFORMANCE = "android.hardware.vr.high_performance";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the feature version
     * specifies a date such that the device is known to pass the Vulkan dEQP test suite associated
     * with that date.  The date is encoded as follows:
     * <ul>
     * <li>Year in bits 31-16</li>
     * <li>Month in bits 15-8</li>
     * <li>Day in bits 7-0</li>
     * </ul>
     * <p>
     * Example: 2019-03-01 is encoded as 0x07E30301, and would indicate that the device passes the
     * Vulkan dEQP test suite version that was current on 2019-03-01.
     */
    public static final String FEATURE_VULKAN_DEQP_LEVEL = "android.software.vulkan.deqp.level";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the Vulkan
     * implementation on this device is hardware accelerated, and the Vulkan native API will
     * enumerate at least one {@code VkPhysicalDevice}, and the feature version will indicate what
     * level of optional compute features that device supports beyond the Vulkan 1.0 requirements.
     * <p>
     * Compute level 0 indicates:
     * <ul>
     * <li>The {@code VK_KHR_variable_pointers} extension and
     *     {@code VkPhysicalDeviceVariablePointerFeaturesKHR::variablePointers} feature are
     *           supported.</li>
     * <li>{@code VkPhysicalDeviceLimits::maxPerStageDescriptorStorageBuffers} is at least 16.</li>
     * </ul>
     */
    public static final String FEATURE_VULKAN_HARDWARE_COMPUTE = "android.hardware.vulkan.compute";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the Vulkan
     * implementation on this device is hardware accelerated, and the Vulkan native API will
     * enumerate at least one {@code VkPhysicalDevice}, and the feature version will indicate what
     * level of optional hardware features limits it supports.
     * <p>
     * Level 0 includes the base Vulkan requirements as well as:
     * <ul><li>{@code VkPhysicalDeviceFeatures::textureCompressionETC2}</li></ul>
     * <p>
     * Level 1 additionally includes:
     * <ul>
     * <li>{@code VkPhysicalDeviceFeatures::fullDrawIndexUint32}</li>
     * <li>{@code VkPhysicalDeviceFeatures::imageCubeArray}</li>
     * <li>{@code VkPhysicalDeviceFeatures::independentBlend}</li>
     * <li>{@code VkPhysicalDeviceFeatures::geometryShader}</li>
     * <li>{@code VkPhysicalDeviceFeatures::tessellationShader}</li>
     * <li>{@code VkPhysicalDeviceFeatures::sampleRateShading}</li>
     * <li>{@code VkPhysicalDeviceFeatures::textureCompressionASTC_LDR}</li>
     * <li>{@code VkPhysicalDeviceFeatures::fragmentStoresAndAtomics}</li>
     * <li>{@code VkPhysicalDeviceFeatures::shaderImageGatherExtended}</li>
     * <li>{@code VkPhysicalDeviceFeatures::shaderUniformBufferArrayDynamicIndexing}</li>
     * <li>{@code VkPhysicalDeviceFeatures::shaderSampledImageArrayDynamicIndexing}</li>
     * </ul>
     */
    public static final String FEATURE_VULKAN_HARDWARE_LEVEL = "android.hardware.vulkan.level";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature(String, int)}: If this feature is supported, the Vulkan
     * implementation on this device is hardware accelerated, and the feature version will indicate
     * the highest {@code VkPhysicalDeviceProperties::apiVersion} supported by the physical devices
     * that support the hardware level indicated by {@link #FEATURE_VULKAN_HARDWARE_LEVEL}. The
     * feature version uses the same encoding as Vulkan version numbers:
     * <ul>
     * <li>Major version number in bits 31-22</li>
     * <li>Minor version number in bits 21-12</li>
     * <li>Patch version number in bits 11-0</li>
     * </ul>
     * A version of 1.1.0 or higher also indicates:
     * <ul>
     * <li>The {@code VK_ANDROID_external_memory_android_hardware_buffer} extension is
     *     supported.</li>
     * <li>{@code SYNC_FD} external semaphore and fence handles are supported.</li>
     * <li>{@code VkPhysicalDeviceSamplerYcbcrConversionFeatures::samplerYcbcrConversion} is
     *     supported.</li>
     * </ul>
     * A subset of devices that support Vulkan 1.1 do so via software emulation. For more
     * information, see
     * <a href="{@docRoot}ndk/guides/graphics/design-notes">Vulkan Design Guidelines</a>.
     */
    public static final String FEATURE_VULKAN_HARDWARE_VERSION = "android.hardware.vulkan.version";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports showing location-based suggestions for wallet cards provided by the default payment
     * app.
     */
    public static final String FEATURE_WALLET_LOCATION_BASED_SUGGESTIONS = "android.software.wallet_location_based_suggestions";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: This is a device dedicated to showing UI
     * on a watch. A watch here is defined to be a device worn on the body, perhaps on
     * the wrist. The user is very close when interacting with the device.
     */
    public static final String FEATURE_WATCH = "android.hardware.type.watch";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}:
     * The device has a full implementation of the android.webkit.* APIs. Devices
     * lacking this feature will not have a functioning WebView implementation.
     */
    public static final String FEATURE_WEBVIEW = "android.software.webview";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports WiFi (802.11) networking.
     */
    public static final String FEATURE_WIFI = "android.hardware.wifi";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports Wi-Fi Aware.
     */
    public static final String FEATURE_WIFI_AWARE = "android.hardware.wifi.aware";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports Wi-Fi Direct networking.
     */
    public static final String FEATURE_WIFI_DIRECT = "android.hardware.wifi.direct";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports Wi-Fi Passpoint and all
     * Passpoint related APIs in {@link WifiManager} are supported. Refer to
     * {@link WifiManager#addOrUpdatePasspointConfiguration} for more info.
     */
    public static final String FEATURE_WIFI_PASSPOINT = "android.hardware.wifi.passpoint";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and
     * {@link #hasSystemFeature}: The device supports Wi-Fi RTT (IEEE 802.11mc).
     */
    public static final String FEATURE_WIFI_RTT = "android.hardware.wifi.rtt";

    /**
     * Feature for {@link #getSystemAvailableFeatures} and {@link #hasSystemFeature}: The device
     * supports window magnification.
     *
     * @see android.accessibilityservice.MagnificationConfig#MAGNIFICATION_MODE_WINDOW
     */
    public static final String FEATURE_WINDOW_MAGNIFICATION = "android.software.window_magnification";

    /**
     * Permission whitelist flag: permissions whitelisted by the installer.
     * Permissions can also be whitelisted by the system, on upgrade, or on role
     * grant.
     *
     * <p>
     * <strong>Note: </strong>In retrospect it would have been preferred to use
     * more inclusive terminology when naming this API. Similar APIs added will
     * refrain from using the term "whitelist".
     * </p>
     */
    public static final int FLAG_PERMISSION_WHITELIST_INSTALLER = 2;

    /**
     * Permission whitelist flag: permissions whitelisted by the system.
     * Permissions can also be whitelisted by the installer, on upgrade, or on
     * role grant.
     *
     * <p>
     * <strong>Note: </strong>In retrospect it would have been preferred to use
     * more inclusive terminology when naming this API. Similar APIs added will
     * refrain from using the term "whitelist".
     * </p>
     */
    public static final int FLAG_PERMISSION_WHITELIST_SYSTEM = 1;

    /**
     * Permission whitelist flag: permissions whitelisted by the system
     * when upgrading from an OS version where the permission was not
     * restricted to an OS version where the permission is restricted.
     * Permissions can also be whitelisted by the installer, the system, or on
     * role grant.
     *
     * <p>
     * <strong>Note: </strong>In retrospect it would have been preferred to use
     * more inclusive terminology when naming this API. Similar APIs added will
     * refrain from using the term "whitelist".
     * </p>
     */
    public static final int FLAG_PERMISSION_WHITELIST_UPGRADE = 4;

    /**
     * {@link PackageInfo} flag: return information about
     * activities in the package in {@link PackageInfo#activities}.
     */
    public static final int GET_ACTIVITIES = 1;

    /**
     * @deprecated Use {@link #GET_ATTRIBUTIONS_LONG} to avoid unintended sign extension.
     */
    @Deprecated
    public static final int GET_ATTRIBUTIONS = -2147483648;

    /**
     * {@link PackageInfo} flag: return all attributions declared in the package manifest
     */
    public static final long GET_ATTRIBUTIONS_LONG = 2147483648L;

    /**
     * {@link PackageInfo} flag: return information about
     * hardware preferences in
     * {@link PackageInfo#configPreferences PackageInfo.configPreferences},
     * and requested features in {@link PackageInfo#reqFeatures} and
     * {@link PackageInfo#featureGroups}.
     */
    public static final int GET_CONFIGURATIONS = 16384;

    /**
     * @deprecated replaced with {@link #MATCH_DISABLED_COMPONENTS}
     */
    @Deprecated
    public static final int GET_DISABLED_COMPONENTS = 512;

    /**
     * @deprecated replaced with {@link #MATCH_DISABLED_UNTIL_USED_COMPONENTS}.
     */
    @Deprecated
    public static final int GET_DISABLED_UNTIL_USED_COMPONENTS = 32768;

    /**
     * {@link PackageInfo} flag: return the
     * {@link PackageInfo#gids group ids} that are associated with an
     * application.
     * This applies for any API returning a PackageInfo class, either
     * directly or nested inside of another.
     */
    public static final int GET_GIDS = 256;

    /**
     * {@link PackageInfo} flag: return information about
     * instrumentation in the package in
     * {@link PackageInfo#instrumentation}.
     */
    public static final int GET_INSTRUMENTATION = 16;

    /**
     * {@link PackageInfo} flag: return information about the
     * intent filters supported by the activity.
     *
     * @deprecated The platform does not support getting {@link IntentFilter}s for the package.
     */
    @Deprecated
    public static final int GET_INTENT_FILTERS = 32;

    /**
     * {@link ComponentInfo} flag: return the {@link ComponentInfo#metaData}
     * data {@link android.os.Bundle}s that are associated with a component.
     * This applies for any API returning a ComponentInfo subclass.
     */
    public static final int GET_META_DATA = 128;

    /**
     * {@link PackageInfo} flag: return information about
     * permissions in the package in
     * {@link PackageInfo#permissions}.
     */
    public static final int GET_PERMISSIONS = 4096;

    /**
     * {@link PackageInfo} flag: return information about
     * content providers in the package in
     * {@link PackageInfo#providers}.
     */
    public static final int GET_PROVIDERS = 8;

    /**
     * {@link PackageInfo} flag: return information about
     * intent receivers in the package in
     * {@link PackageInfo#receivers}.
     */
    public static final int GET_RECEIVERS = 2;

    /**
     * {@link ResolveInfo} flag: return the IntentFilter that
     * was matched for a particular ResolveInfo in
     * {@link ResolveInfo#filter}.
     */
    public static final int GET_RESOLVED_FILTER = 64;

    /**
     * {@link PackageInfo} flag: return information about
     * services in the package in {@link PackageInfo#services}.
     */
    public static final int GET_SERVICES = 4;

    /**
     * {@link ApplicationInfo} flag: return the
     * {@link ApplicationInfo#sharedLibraryFiles paths to the shared libraries}
     * that are associated with an application.
     * This applies for any API returning an ApplicationInfo class, either
     * directly or nested inside of another.
     */
    public static final int GET_SHARED_LIBRARY_FILES = 1024;

    /**
     * {@link PackageInfo} flag: return information about the
     * signatures included in the package.
     *
     * @deprecated use {@code GET_SIGNING_CERTIFICATES} instead
     */
    @Deprecated
    public static final int GET_SIGNATURES = 64;

    /**
     * {@link PackageInfo} flag: return the signing certificates associated with
     * this package.  Each entry is a signing certificate that the package
     * has proven it is authorized to use, usually a past signing certificate from
     * which it has rotated.
     */
    public static final int GET_SIGNING_CERTIFICATES = 134217728;

    /**
     * @deprecated replaced with {@link #MATCH_UNINSTALLED_PACKAGES}
     */
    @Deprecated
    public static final int GET_UNINSTALLED_PACKAGES = 8192;

    /**
     * {@link ProviderInfo} flag: return the
     * {@link ProviderInfo#uriPermissionPatterns URI permission patterns}
     * that are associated with a content provider.
     * This applies for any API returning a ProviderInfo class, either
     * directly or nested inside of another.
     */
    public static final int GET_URI_PERMISSION_PATTERNS = 2048;

    /**
     * Code indicating that this package was installed as part of restoring from another device.
     */
    public static final int INSTALL_REASON_DEVICE_RESTORE = 2;

    /**
     * Code indicating that this package was installed as part of device setup.
     */
    public static final int INSTALL_REASON_DEVICE_SETUP = 3;

    /**
     * Code indicating that this package was installed due to enterprise policy.
     */
    public static final int INSTALL_REASON_POLICY = 1;

    /**
     * Code indicating that the reason for installing this package is unknown.
     */
    public static final int INSTALL_REASON_UNKNOWN = 0;

    /**
     * Code indicating that the package installation was initiated by the user.
     */
    public static final int INSTALL_REASON_USER = 4;

    /**
     * Installation scenario indicating a bulk operation with the desired result of a fully
     * optimized application.  If the system is busy or resources are scarce the system will
     * perform less work to avoid impacting system health.
     *
     * Examples of bulk installation scenarios might include device restore, background updates of
     * multiple applications, or user-triggered updates for all applications.
     *
     * The decision to use BULK or BULK_SECONDARY should be based on the desired user experience.
     * BULK_SECONDARY operations may take less time to complete but, when they do, will produce
     * less optimized applications.  The device state (e.g. memory usage or battery status) should
     * not be considered when making this decision as those factors are taken into account by the
     * Package Manager when acting on the installation scenario.
     */
    public static final int INSTALL_SCENARIO_BULK = 2;

    /**
     * Installation scenario indicating a bulk operation that prioritizes minimal system health
     * impact over application optimization.  The application may undergo additional optimization
     * if the system is idle and system resources are abundant.  The more elements of a bulk
     * operation that are marked BULK_SECONDARY, the faster the entire bulk operation will be.
     *
     * See the comments for INSTALL_SCENARIO_BULK for more information.
     */
    public static final int INSTALL_SCENARIO_BULK_SECONDARY = 3;

    /**
     * A value to indicate the lack of CUJ information, disabling all installation scenario logic.
     */
    public static final int INSTALL_SCENARIO_DEFAULT = 0;

    /**
     * Installation scenario providing the fastest "install button to launch" experience possible.
     */
    public static final int INSTALL_SCENARIO_FAST = 1;

    /**
     * Querying flag: if set and if the platform is doing any filtering of the
     * results, then the filtering will not happen. This is a synonym for saying
     * that all results should be returned.
     * <p>
     * <em>This flag should be used with extreme care.</em>
     */
    public static final int MATCH_ALL = 131072;

    /**
     * {@link PackageInfo} flag: include APEX packages that are currently
     * installed. In APEX terminology, this corresponds to packages that are
     * currently active, i.e. mounted and available to other processes of the OS.
     * In particular, this flag alone will not match APEX files that are staged
     * for activation at next reboot.
     */
    public static final int MATCH_APEX = 1073741824;

    /**
     * Flag parameter to also retrieve some information about archived packages.
     * Packages can be archived through {@link PackageInstaller#requestArchive} and do not have any
     * APKs stored on the device, but do keep the data directory.
     * <p> Note: Archived apps are a subset of apps returned by {@link #MATCH_UNINSTALLED_PACKAGES}.
     * <p> Note: this flag may cause less information about currently installed
     * applications to be returned.
     * <p> Note: use of this flag requires the android.permission.QUERY_ALL_PACKAGES
     * permission to see uninstalled packages.
     */
    public static final long MATCH_ARCHIVED_PACKAGES = 4294967296L;

    /**
     * Resolution and querying flag: if set, only filters that support the
     * {@link android.content.Intent#CATEGORY_DEFAULT} will be considered for
     * matching.  This is a synonym for including the CATEGORY_DEFAULT in your
     * supplied Intent.
     */
    public static final int MATCH_DEFAULT_ONLY = 65536;

    /**
     * Querying flag: automatically match components based on their Direct Boot
     * awareness and the current user state.
     * <p>
     * Since the default behavior is to automatically apply the current user
     * state, this is effectively a sentinel value that doesn't change the
     * output of any queries based on its presence or absence.
     * <p>
     * Instead, this value can be useful in conjunction with
     * {@link android.os.StrictMode.VmPolicy.Builder#detectImplicitDirectBoot()}
     * to detect when a caller is relying on implicit automatic matching,
     * instead of confirming the explicit behavior they want, using a
     * combination of these flags:
     * <ul>
     * <li>{@link #MATCH_DIRECT_BOOT_AWARE}
     * <li>{@link #MATCH_DIRECT_BOOT_UNAWARE}
     * <li>{@link #MATCH_DIRECT_BOOT_AUTO}
     * </ul>
     */
    public static final int MATCH_DIRECT_BOOT_AUTO = 268435456;

    /**
     * Querying flag: match components which are direct boot <em>aware</em> in
     * the returned info, regardless of the current user state.
     * <p>
     * When neither {@link #MATCH_DIRECT_BOOT_AWARE} nor
     * {@link #MATCH_DIRECT_BOOT_UNAWARE} are specified, the default behavior is
     * to match only runnable components based on the user state. For example,
     * when a user is started but credentials have not been presented yet, the
     * user is running "locked" and only {@link #MATCH_DIRECT_BOOT_AWARE}
     * components are returned. Once the user credentials have been presented,
     * the user is running "unlocked" and both {@link #MATCH_DIRECT_BOOT_AWARE}
     * and {@link #MATCH_DIRECT_BOOT_UNAWARE} components are returned.
     *
     * @see UserManager#isUserUnlocked()
     */
    public static final int MATCH_DIRECT_BOOT_AWARE = 524288;

    /**
     * Querying flag: match components which are direct boot <em>unaware</em> in
     * the returned info, regardless of the current user state.
     * <p>
     * When neither {@link #MATCH_DIRECT_BOOT_AWARE} nor
     * {@link #MATCH_DIRECT_BOOT_UNAWARE} are specified, the default behavior is
     * to match only runnable components based on the user state. For example,
     * when a user is started but credentials have not been presented yet, the
     * user is running "locked" and only {@link #MATCH_DIRECT_BOOT_AWARE}
     * components are returned. Once the user credentials have been presented,
     * the user is running "unlocked" and both {@link #MATCH_DIRECT_BOOT_AWARE}
     * and {@link #MATCH_DIRECT_BOOT_UNAWARE} components are returned.
     *
     * @see UserManager#isUserUnlocked()
     */
    public static final int MATCH_DIRECT_BOOT_UNAWARE = 262144;

    /**
     * {@link PackageInfo} flag: include disabled components in the returned info.
     */
    public static final int MATCH_DISABLED_COMPONENTS = 512;

    /**
     * {@link PackageInfo} flag: include disabled components which are in
     * that state only because of {@link #COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED}
     * in the returned info.  Note that if you set this flag, applications
     * that are in this disabled state will be reported as enabled.
     */
    public static final int MATCH_DISABLED_UNTIL_USED_COMPONENTS = 32768;

    /**
     * Querying flag: include only components from applications that are marked
     * with {@link ApplicationInfo#FLAG_SYSTEM}.
     */
    public static final int MATCH_SYSTEM_ONLY = 1048576;

    /**
     * Flag parameter to retrieve some information about all applications (even
     * uninstalled ones) which have data directories. This state could have
     * resulted if applications have been deleted with flag
     * {@code DELETE_KEEP_DATA} with a possibility of being replaced or
     * reinstalled in future.
     * <p>
     * Note: this flag may cause less information about currently installed
     * applications to be returned.
     * <p>
     * Note: use of this flag requires the android.permission.QUERY_ALL_PACKAGES
     * permission to see uninstalled packages.
     */
    public static final int MATCH_UNINSTALLED_PACKAGES = 8192;

    /**
     * Can be used as the {@code millisecondsToDelay} argument for
     * {@link PackageManager#extendVerificationTimeout}. This is the
     * maximum time {@code PackageManager} waits for the verification
     * agent to return (in milliseconds).
     */
    public static final long MAXIMUM_VERIFICATION_TIMEOUT = 3600000L;

    /**
     * Permission check result: this is returned by {@link #checkPermission}
     * if the permission has not been granted to the given package.
     */
    public static final int PERMISSION_DENIED = -1;

    /**
     * Permission check result: this is returned by {@link #checkPermission}
     * if the permission has been granted to the given package.
     */
    public static final int PERMISSION_GRANTED = 0;

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager
     * .Property} for an app to inform the system that the app can be opted-in or opted-out
     * from the compatibility treatment that rotates camera output by 90 degrees on landscape
     * sensors on devices known to have compatibility issues.
     *
     * <p>The treatment is disabled by default but device manufacturers can enable the treatment
     * using their discretion to improve camera compatibility. With this property set to
     * {@code false}, the rotation will not be applied. A value of {@code true}
     * will ensure that rotation is applied, provided it is enabled for the device. In most cases,
     * if rotation is the desired behavior this property need not be set. However, if your app
     * experiences stretching or incorrect rotation on these devices, explicitly setting this to
     * {@code true} may resolve that behavior. Apps should set this to {@code false} if there
     * is confidence that the app handles
     * {@link android.hardware.camera2.CameraCharacteristics#SENSOR_ORIENTATION} correctly.
     * See <a href="https://developer.android.com/training/camera2/camera-preview"> the
     * documentation for best practice.</a>
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.camera.PROPERTY_COMPAT_OVERRIDE_LANDSCAPE_TO_PORTRAIT"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    public static final String PROPERTY_COMPAT_OVERRIDE_LANDSCAPE_TO_PORTRAIT = "android.camera.PROPERTY_COMPAT_OVERRIDE_LANDSCAPE_TO_PORTRAIT";

    /**
     * &lt;application&gt; level {@link android.content.pm.PackageManager.Property} tag specifying
     * the XML resource ID containing an application's media capabilities XML file
     *
     * For example:
     * &lt;application&gt;
     *   &lt;property android:name="android.media.PROPERTY_MEDIA_CAPABILITIES"
     *     android:resource="@xml/media_capabilities"&gt;
     * &lt;application&gt;
     */
    public static final String PROPERTY_MEDIA_CAPABILITIES = "android.media.PROPERTY_MEDIA_CAPABILITIES";

    /**
     * &lt;application&gt; level {@link android.content.pm.PackageManager.Property} tag
     * specifying the XML resource ID containing the declaration of the self-certified network
     * capabilities used by the application.
     *
     * <p> Starting from Android 14, usage of some network capabilities in
     * {@link android.net.ConnectivityManager#requestNetwork} require the application to
     * declare its usage of that particular capability in this resource. Only some capabilities
     * require a declaration. Please look up the specific capability you want to use in
     * {@link android.net.NetworkCapabilities} to see if it needs declaration in this property.
     *
     * For example:
     * &lt;application&gt;
     *   &lt;property android:name="android.net.PROPERTY_SELF_CERTIFIED_NETWORK_CAPABILITIES"
     *     android:resource="@xml/self_certified_network_capabilities"&gt;
     * &lt;application&gt;
     *
     * <p> The detail format of self_certified_network_capabilities.xml is described in
     * {@link android.net.NetworkRequest}
     */
    public static final String PROPERTY_SELF_CERTIFIED_NETWORK_CAPABILITIES = "android.net.PROPERTY_SELF_CERTIFIED_NETWORK_CAPABILITIES";

    /**
     * &lt;service&gt; level {@link android.content.pm.PackageManager.Property} tag specifying
     * the actual use case of the service if it's foreground service with the type
     * {@link ServiceInfo#FOREGROUND_SERVICE_TYPE_SPECIAL_USE}.
     *
     * <p>
     * For example:
     * &lt;service&gt;
     *   &lt;property android:name="android.app.PROPERTY_SPECIAL_USE_FGS_SUBTYPE"
     *     android:value="foo"/&gt;
     * &lt;/service&gt;
     */
    public static final String PROPERTY_SPECIAL_USE_FGS_SUBTYPE = "android.app.PROPERTY_SPECIAL_USE_FGS_SUBTYPE";

    /**
     * &lt;application&gt; level {@link android.content.pm.PackageManager.Property} tag
     * specifying whether the app should be put into the "restricted" backup mode when it's started
     * for backup and restore operations.
     *
     * <p> See <a
     * href="https://developer.android.com/identity/data/autobackup#ImplementingBackupAgent"> for
     * information about restricted mode</a>.
     *
     * <p> Starting with Android 16 apps may not be started in restricted mode based on this
     * property.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.app.backup.PROPERTY_USE_RESTRICTED_BACKUP_MODE"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     *
     * <p>If this property is set, the operating system will respect it for now (see Note below).
     * If it's not set, the behavior depends on the SDK level that the app is targeting. For apps
     * targeting SDK level {@link android.os.Build.VERSION_CODES#VANILLA_ICE_CREAM} or lower, the
     * property defaults to {@code true}. For apps targeting SDK level
     * {@link android.os.Build.VERSION_CODES#BAKLAVA} or higher, the operating system will make a
     * decision dynamically.
     *
     * <p>Note: It's not recommended to set this property to {@code true} unless absolutely
     * necessary. In a future Android version, this property may be deprecated in favor of removing
     * restricted mode completely.
     */
    public static final String PROPERTY_USE_RESTRICTED_BACKUP_MODE = "android.app.backup.PROPERTY_USE_RESTRICTED_BACKUP_MODE";

    /**
     * Signature check result: this is returned by {@link #checkSignatures}
     * if the first package is not signed but the second is.
     */
    public static final int SIGNATURE_FIRST_NOT_SIGNED = -1;

    /**
     * Signature check result: this is returned by {@link #checkSignatures}
     * if all signatures on the two packages match.
     */
    public static final int SIGNATURE_MATCH = 0;

    /**
     * Signature check result: this is returned by {@link #checkSignatures}
     * if neither of the two packages is signed.
     */
    public static final int SIGNATURE_NEITHER_SIGNED = 1;

    /**
     * Signature check result: this is returned by {@link #checkSignatures}
     * if not all signatures on both packages match.
     */
    public static final int SIGNATURE_NO_MATCH = -3;

    /**
     * Signature check result: this is returned by {@link #checkSignatures}
     * if the second package is not signed but the first is.
     */
    public static final int SIGNATURE_SECOND_NOT_SIGNED = -2;

    /**
     * Signature check result: this is returned by {@link #checkSignatures}
     * if either of the packages are not valid.
     */
    public static final int SIGNATURE_UNKNOWN_PACKAGE = -4;

    /**
     * Flag parameter for
     * {@link #setComponentEnabledSetting(android.content.ComponentName, int, int)} to indicate
     * that the given user's package restrictions state will be serialised to disk after the
     * component state has been updated. Note that this is synchronous disk access, so calls using
     * this flag should be run on a background thread.
     */
    public static final int SYNCHRONOUS = 2;

    /**
     * Used as the {@code verificationCode} argument for
     * {@link PackageManager#verifyPendingInstall} to indicate that the calling
     * package verifier allows the installation to proceed.
     */
    public static final int VERIFICATION_ALLOW = 1;

    /**
     * Used as the {@code verificationCode} argument for
     * {@link PackageManager#verifyPendingInstall} to indicate the calling
     * package verifier does not vote to allow the installation to proceed.
     */
    public static final int VERIFICATION_REJECT = -1;

    /**
     * Constant for specifying the highest installed package version code.
     */
    public static final int VERSION_CODE_HIGHEST = -1;

    private PackageManager() {
    }
}
