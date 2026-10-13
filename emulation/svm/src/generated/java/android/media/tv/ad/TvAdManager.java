package android.media.tv.ad;

/**
 * Central system API to the overall client-side TV AD architecture, which arbitrates interaction
 * between applications and AD services.
 */
public final class TvAdManager {
    /**
     * Broadcast intent action to send app command to TV app.
     *
     * @see #sendAppLinkCommand(String, Bundle)
     */
    public static final String ACTION_APP_LINK_COMMAND = "android.media.tv.ad.action.APP_LINK_COMMAND";

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
     * Intent key for TV AD service ID. It's used to send app command to TV app.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     * @see TvAdServiceInfo#getId()
     */
    public static final String INTENT_KEY_AD_SERVICE_ID = "ad_service_id";

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
     * Intent key for TV input ID. It's used to send app command to TV app.
     * <p>Type: String
     *
     * @see #sendAppLinkCommand(String, Bundle)
     * @see #ACTION_APP_LINK_COMMAND
     */
    public static final String INTENT_KEY_TV_INPUT_ID = "tv_input_id";

    /**
     * An object of {@link AdBuffer}.
     *
     * <p> Type: android.media.tv.AdBuffer
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_AD_BUFFER = "ad_buffer";

    /**
     * An object of {@link android.media.tv.AdRequest}.
     *
     * <p> Type: android.media.tv.AdRequest
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_AD_REQUEST = "ad_request";

    /**
     * An object of {@link android.media.tv.BroadcastInfoRequest}.
     *
     * <p> Type: android.media.tv.BroadcastInfoRequest
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_BROADCAST_INFO_REQUEST = "broadcast_info_request";

    /**
     * The ID of {@link android.media.tv.BroadcastInfoRequest}.
     *
     * <p> Type: Integer
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_REQUEST_ID = "request_id";

    /**
     * Notifies the advertisement buffer is ready.
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_AD_BUFFER
     */
    public static final String SESSION_DATA_TYPE_AD_BUFFER_READY = "ad_buffer_ready";

    /**
     * Sends an advertisement request to be processed by the related TV input.
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_AD_REQUEST
     */
    public static final String SESSION_DATA_TYPE_AD_REQUEST = "ad_request";

    /**
     * Sends request for broadcast info.
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_BROADCAST_INFO_RESQUEST
     */
    public static final String SESSION_DATA_TYPE_BROADCAST_INFO_REQUEST = "broadcast_info_request";

    /**
     * Removes request for broadcast info.
     *
     * @see TvAdService.Session#sendTvAdSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_BROADCAST_INFO_REQUEST_ID
     */
    public static final String SESSION_DATA_TYPE_REMOVE_BROADCAST_INFO_REQUEST = "remove_broadcast_info_request";

    /**
     * Error state of AD service session.
     */
    public static final int SESSION_STATE_ERROR = 3;

    /**
     * Running state of AD service session.
     */
    public static final int SESSION_STATE_RUNNING = 2;

    /**
     * Stopped (or not started) state of AD service session.
     */
    public static final int SESSION_STATE_STOPPED = 1;

    private TvAdManager() {
    }
}
