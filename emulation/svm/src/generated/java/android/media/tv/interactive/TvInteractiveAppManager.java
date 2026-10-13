package android.media.tv.interactive;

/**
 * Central system API to the overall TV interactive application framework (TIAF) architecture, which
 * arbitrates interaction between Android applications and TV interactive apps.
 */
public final class TvInteractiveAppManager {
    /**
     * Broadcast intent action to send app command to TV app.
     *
     * @see #sendAppLinkCommand(String, Bundle)
     */
    public static final String ACTION_APP_LINK_COMMAND = "android.media.tv.interactive.action.APP_LINK_COMMAND";

    /**
     * Key for back URI in app link command.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     */
    public static final String APP_LINK_KEY_BACK_URI = "back_uri";

    /**
     * Key for class name in app link.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     */
    public static final String APP_LINK_KEY_CLASS_NAME = "class_name";

    /**
     * Key for command type in app link command.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     */
    public static final String APP_LINK_KEY_COMMAND_TYPE = "command_type";

    /**
     * Key for package name in app link.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     */
    public static final String APP_LINK_KEY_PACKAGE_NAME = "package_name";

    /**
     * Key for service ID in app link command.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     */
    public static final String APP_LINK_KEY_SERVICE_ID = "service_id";

    /**
     * Error code for blocked contents.
     */
    public static final int ERROR_BLOCKED = 5;

    /**
     * Error code when the key or module is missing for the encrypted channel.
     */
    public static final int ERROR_ENCRYPTED = 6;

    /**
     * No error.
     */
    public static final int ERROR_NONE = 0;

    /**
     * Error code for an unsupported channel.
     */
    public static final int ERROR_NOT_SUPPORTED = 2;

    /**
     * Error code when resource (e.g. tuner) is unavailable.
     */
    public static final int ERROR_RESOURCE_UNAVAILABLE = 4;

    /**
     * Unknown error code.
     */
    public static final int ERROR_UNKNOWN = 1;

    /**
     * Error code when the current channel is an unknown channel.
     */
    public static final int ERROR_UNKNOWN_CHANNEL = 7;

    /**
     * Error code for weak signal.
     */
    public static final int ERROR_WEAK_SIGNAL = 3;

    /**
     * Intent key for broadcast-independent(BI) interactive app type. It's used to send app command
     * to TV app.
     * <p>Type: int
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     * @see android.media.tv.interactive.TvInteractiveAppServiceInfo#getSupportedTypes()
     * @see android.media.tv.interactive.TvInteractiveAppView#createBiInteractiveApp(Uri, Bundle)
     */
    public static final String INTENT_KEY_BI_INTERACTIVE_APP_TYPE = "bi_interactive_app_type";

    /**
     * Intent key for broadcast-independent(BI) interactive app URI. It's used to send app command
     * to TV app.
     * <p>Type: android.net.Uri
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     * @see android.media.tv.interactive.TvInteractiveAppView#createBiInteractiveApp(Uri, Bundle)
     */
    public static final String INTENT_KEY_BI_INTERACTIVE_APP_URI = "bi_interactive_app_uri";

    /**
     * Intent key for TV channel URI. It's used to send app command to TV app.
     * <p>Type: android.net.Uri
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     */
    public static final String INTENT_KEY_CHANNEL_URI = "channel_uri";

    /**
     * Intent key for command type. It's used to send app command to TV app. The value of this key
     * could vary according to TV apps.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     */
    public static final String INTENT_KEY_COMMAND_TYPE = "command_type";

    /**
     * Intent key for TV interactive app ID. It's used to send app command to TV app.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     * @see TvInteractiveAppServiceInfo#getId()
     */
    public static final String INTENT_KEY_INTERACTIVE_APP_SERVICE_ID = "interactive_app_id";

    /**
     * Intent key for TV input ID. It's used to send app command to TV app.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     */
    public static final String INTENT_KEY_TV_INPUT_ID = "tv_input_id";

    /**
     * Error state of interactive application.
     */
    public static final int INTERACTIVE_APP_STATE_ERROR = 3;

    /**
     * Running state of interactive application.
     */
    public static final int INTERACTIVE_APP_STATE_RUNNING = 2;

    /**
     * Stopped (or not started) state of interactive application.
     */
    public static final int INTERACTIVE_APP_STATE_STOPPED = 1;

    /**
     * Error state of interactive app service.
     */
    public static final int SERVICE_STATE_ERROR = 4;

    /**
     * Preparing state of interactive app service.
     */
    public static final int SERVICE_STATE_PREPARING = 2;

    /**
     * Ready state of interactive app service.
     *
     * <p>In this state, the interactive app service is ready, and interactive apps can be started.
     *
     * @see TvInteractiveAppView#startInteractiveApp()
     */
    public static final int SERVICE_STATE_READY = 3;

    /**
     * Unrealized state of interactive app service.
     */
    public static final int SERVICE_STATE_UNREALIZED = 1;

    /**
     * State of Teletext app: error
     */
    public static final int TELETEXT_APP_STATE_ERROR = 3;

    /**
     * State of Teletext app: hide
     */
    public static final int TELETEXT_APP_STATE_HIDE = 2;

    /**
     * State of Teletext app: show
     */
    public static final int TELETEXT_APP_STATE_SHOW = 1;

    private TvInteractiveAppManager() {
    }
}
