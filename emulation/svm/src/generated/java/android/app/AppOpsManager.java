package android.app;

/**
 * App-ops are used for two purposes: Access control and tracking.
 *
 * <p>App-ops cover a wide variety of functionality from helping with runtime permissions access
 * control and tracking to battery consumption tracking.
 *
 * <h2>Access control</h2>
 *
 * <p>App-ops can either be controlled for each uid or for each package. Which one is used depends
 * on the API provider maintaining this app-op. For any security or privacy related app-op the
 * provider needs to control the app-op for per uid as all security and privacy is based on uid in
 * Android.
 *
 * <p>To control access the app-op can be set to a mode to:
 * <dl>
 *     <dt>{@link #MODE_DEFAULT}
 *     <dd>Default behavior, might differ from app-op or app-op
 *     <dt>{@link #MODE_ALLOWED}
 *     <dd>Allow the access
 *     <dt>{@link #MODE_IGNORED}
 *     <dd>Don't allow the access, i.e. don't perform the requested action or return no or
 *     placeholder data
 *     <dt>{@link #MODE_ERRORED}
 *     <dd>Throw a {@link SecurityException} on access. This can be suppressed by using a
 *     {@code ...noThrow} method to check the mode
 * </dl>
 *
 * <p>API providers need to check the mode returned by {@link #noteOp} if they are are allowing
 * access to operations gated by the app-op. {@link #unsafeCheckOp} should be used to check the
 * mode if no access is granted. E.g. this can be used for displaying app-op state in the UI or
 * when checking the state before later calling {@link #noteOp} anyway.
 *
 * <p>If an operation refers to a time span (e.g. a audio-recording session) the API provider
 * should use {@link #startOp} and {@link #finishOp} instead of {@link #noteOp}.
 *
 * <h3>Runtime permissions and app-ops</h3>
 *
 * <p>Each platform defined runtime permission (beside background modifiers) has an associated app
 * op which is used for tracking but also to allow for silent failures. I.e. if the runtime
 * permission is denied the caller gets a {@link SecurityException}, but if the permission is
 * granted and the app-op is {@link #MODE_IGNORED} then the callers gets placeholder behavior, e.g.
 * location callbacks would not happen.
 *
 * <h3>App-op permissions</h3>
 *
 * <p>App-ops permissions are platform defined permissions that can be overridden. The security
 * check for app-op permissions should by {@link #MODE_DEFAULT default} check the permission grant
 * state. If the app-op state is set to {@link #MODE_ALLOWED} or {@link #MODE_IGNORED} the app-op
 * state should be checked instead of the permission grant state.
 *
 * <p>This functionality allows to grant access by default to apps fulfilling the requirements for
 * a certain permission level. Still the behavior can be overridden when needed.
 *
 * <h2>Tracking</h2>
 *
 * <p>App-ops track many important events, including all accesses to runtime permission protected
 * APIs. This is done by tracking when an app-op was {@link #noteOp noted} or
 * {@link #startOp started}. The tracked data can only be read by system components.
 *
 * <p><b>Only {@link #noteOp}/{@link #startOp} are tracked; {@link #unsafeCheckOp} is not tracked.
 * Hence it is important to eventually call {@link #noteOp} or {@link #startOp} when providing
 * access to protected operations or data.</b>
 *
 * <p>Some apps are forwarding access to other apps. E.g. an app might get the location from the
 * system's location provider and then send the location further to a 3rd app. In this case the
 * app passing on the data needs to call {@link #noteProxyOp} to signal the access proxying. This
 * might also make sense inside of a single app if the access is forwarded between two parts of
 * the tagged with different attribution tags.
 *
 * <p>An app can register an {@link OnOpNotedCallback} to get informed about what accesses the
 * system is tracking for it. As each runtime permission has an associated app-op this API is
 * particularly useful for an app that want to find unexpected private data accesses.
 */
public final class AppOpsManager {
    /**
     * Result from {@link #checkOp}, {@link #noteOp}, {@link #startOp}: the given caller is
     * allowed to perform the given operation.
     */
    public static final int MODE_ALLOWED = 0;

    /**
     * Result from {@link #checkOp}, {@link #noteOp}, {@link #startOp}: the given caller should
     * use its default security check.  This mode is not normally used; it should only be used
     * with appop permissions, and callers must explicitly check for it and deal with it.
     */
    public static final int MODE_DEFAULT = 3;

    /**
     * Result from {@link #checkOpNoThrow}, {@link #noteOpNoThrow}, {@link #startOpNoThrow}: the
     * given caller is not allowed to perform the given operation, and this attempt should
     * cause it to have a fatal error, typically a {@link SecurityException}.
     */
    public static final int MODE_ERRORED = 2;

    /**
     * Special mode that means "allow only when app is in foreground."  This is <b>not</b>
     * returned from {@link #unsafeCheckOp}, {@link #noteOp}, {@link #startOp}.  Rather,
     * {@link #unsafeCheckOp} will always return {@link #MODE_ALLOWED} (because it is always
     * possible for it to be ultimately allowed, depending on the app's background state),
     * and {@link #noteOp} and {@link #startOp} will return {@link #MODE_ALLOWED} when the app
     * being checked is currently in the foreground, otherwise {@link #MODE_IGNORED}.
     *
     * <p>The only place you will this normally see this value is through
     * {@link #unsafeCheckOpRaw}, which returns the actual raw mode of the op.  Note that because
     * you can't know the current state of the app being checked (and it can change at any
     * point), you can only treat the result here as an indication that it will vary between
     * {@link #MODE_ALLOWED} and {@link #MODE_IGNORED} depending on changes in the background
     * state of the app.  You thus must always use {@link #noteOp} or {@link #startOp} to do
     * the actual check for access to the op.</p>
     */
    public static final int MODE_FOREGROUND = 4;

    /**
     * Result from {@link #checkOp}, {@link #noteOp}, {@link #startOp}: the given caller is
     * not allowed to perform the given operation, and this attempt should
     * <em>silently fail</em> (it should not cause the app to crash).
     */
    public static final int MODE_IGNORED = 1;

    /**
     * Required to access phone state related information.
     */
    public static final String OPSTR_ADD_VOICEMAIL = "android:add_voicemail";

    /**
     * Answer incoming phone calls
     */
    public static final String OPSTR_ANSWER_PHONE_CALLS = "android:answer_phone_calls";

    /**
     * Access to body sensors such as heart rate, etc.
     */
    public static final String OPSTR_BODY_SENSORS = "android:body_sensors";

    /**
     * Allows an application to initiate a phone call.
     */
    public static final String OPSTR_CALL_PHONE = "android:call_phone";

    /**
     * Required to be able to access the camera device.
     */
    public static final String OPSTR_CAMERA = "android:camera";

    /**
     * Access to coarse location information.
     */
    public static final String OPSTR_COARSE_LOCATION = "android:coarse_location";

    /**
     * Access to fine location information.
     */
    public static final String OPSTR_FINE_LOCATION = "android:fine_location";

    /**
     * Access to {@link android.app.usage.UsageStatsManager}.
     */
    public static final String OPSTR_GET_USAGE_STATS = "android:get_usage_stats";

    /**
     * Inject mock location into the system.
     */
    public static final String OPSTR_MOCK_LOCATION = "android:mock_location";

    /**
     * Continually monitoring location data with a relatively high power request.
     */
    public static final String OPSTR_MONITOR_HIGH_POWER_LOCATION = "android:monitor_location_high_power";

    /**
     * Continually monitoring location data.
     */
    public static final String OPSTR_MONITOR_LOCATION = "android:monitor_location";

    /**
     * Access to picture-in-picture.
     */
    public static final String OPSTR_PICTURE_IN_PICTURE = "android:picture_in_picture";

    /**
     * Access APIs for diverting outgoing calls
     */
    public static final String OPSTR_PROCESS_OUTGOING_CALLS = "android:process_outgoing_calls";

    /**
     * Allows an application to read the user's calendar data.
     */
    public static final String OPSTR_READ_CALENDAR = "android:read_calendar";

    /**
     * Allows an application to read the user's call log.
     */
    public static final String OPSTR_READ_CALL_LOG = "android:read_call_log";

    /**
     * Read previously received cell broadcast messages.
     */
    public static final String OPSTR_READ_CELL_BROADCASTS = "android:read_cell_broadcasts";

    /**
     * Allows an application to read the user's contacts data.
     */
    public static final String OPSTR_READ_CONTACTS = "android:read_contacts";

    /**
     * Read external storage.
     */
    public static final String OPSTR_READ_EXTERNAL_STORAGE = "android:read_external_storage";

    public static final String OPSTR_READ_PHONE_NUMBERS = "android:read_phone_numbers";

    /**
     * Required to access phone state related information.
     */
    public static final String OPSTR_READ_PHONE_STATE = "android:read_phone_state";

    /**
     * Allows an application to read SMS messages.
     */
    public static final String OPSTR_READ_SMS = "android:read_sms";

    /**
     * Allows an application to receive MMS messages.
     */
    public static final String OPSTR_RECEIVE_MMS = "android:receive_mms";

    /**
     * Allows an application to receive SMS messages.
     */
    public static final String OPSTR_RECEIVE_SMS = "android:receive_sms";

    /**
     * Allows an application to receive WAP push messages.
     */
    public static final String OPSTR_RECEIVE_WAP_PUSH = "android:receive_wap_push";

    /**
     * Required to be able to access the microphone device.
     */
    public static final String OPSTR_RECORD_AUDIO = "android:record_audio";

    /**
     * Allows an application to send SMS messages.
     */
    public static final String OPSTR_SEND_SMS = "android:send_sms";

    /**
     * Required to draw on top of other apps.
     */
    public static final String OPSTR_SYSTEM_ALERT_WINDOW = "android:system_alert_window";

    /**
     * Use the fingerprint API.
     */
    public static final String OPSTR_USE_FINGERPRINT = "android:use_fingerprint";

    /**
     * Access APIs for SIP calling over VOIP or WiFi
     */
    public static final String OPSTR_USE_SIP = "android:use_sip";

    /**
     * Allows an application to write to the user's calendar data.
     */
    public static final String OPSTR_WRITE_CALENDAR = "android:write_calendar";

    /**
     * Allows an application to write to the user's call log.
     */
    public static final String OPSTR_WRITE_CALL_LOG = "android:write_call_log";

    /**
     * Allows an application to write to the user's contacts data.
     */
    public static final String OPSTR_WRITE_CONTACTS = "android:write_contacts";

    /**
     * Write external storage.
     */
    public static final String OPSTR_WRITE_EXTERNAL_STORAGE = "android:write_external_storage";

    /**
     * Required to write/modify/update system settings.
     */
    public static final String OPSTR_WRITE_SETTINGS = "android:write_settings";

    /**
     * Ignores async op noted events.
     *
     * @see #setOnOpNotedCallback
     */
    public static final int OP_NOTED_CALLBACK_FLAG_IGNORE_ASYNC = 1;

    /**
     * Flag for {@link #startWatchingMode(String, String, int, OnOpChangedListener)}:
     * Also get reports if the foreground state of an op's uid changes.  This only works
     * when watching a particular op, not when watching a package.
     */
    public static final int WATCH_FOREGROUND_CHANGES = 1;

    private AppOpsManager() {
    }
}
