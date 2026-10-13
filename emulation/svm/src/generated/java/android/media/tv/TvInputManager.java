package android.media.tv;

/**
 * Central system API to the overall TV input framework (TIF) architecture, which arbitrates
 * interaction between applications and the selected TV inputs.
 *
 * <p>There are three primary parties involved in the TV input framework (TIF) architecture:
 *
 * <ul>
 * <li>The <strong>TV input manager</strong> as expressed by this class is the central point of the
 * system that manages interaction between all other parts. It is expressed as the client-side API
 * here which exists in each application context and communicates with a global system service that
 * manages the interaction across all processes.
 * <li>A <strong>TV input</strong> implemented by {@link TvInputService} represents an input source
 * of TV, which can be a pass-through input such as HDMI, or a tuner input which provides broadcast
 * TV programs. The system binds to the TV input per application’s request.
 * on implementing TV inputs.
 * <li><strong>Applications</strong> talk to the TV input manager to list TV inputs and check their
 * status. Once an application find the input to use, it uses {@link TvView} or
 * {@link TvRecordingClient} for further interaction such as watching and recording broadcast TV
 * programs.
 * </ul>
 */
public final class TvInputManager {
    /**
     * Broadcast intent action when the user blocked content ratings change. For use with the
     * {@link #isRatingBlocked}.
     */
    public static final String ACTION_BLOCKED_RATINGS_CHANGED = "android.media.tv.action.BLOCKED_RATINGS_CHANGED";

    /**
     * Broadcast intent action when the parental controls enabled state changes. For use with the
     * {@link #isParentalControlsEnabled}.
     */
    public static final String ACTION_PARENTAL_CONTROLS_ENABLED_CHANGED = "android.media.tv.action.PARENTAL_CONTROLS_ENABLED_CHANGED";

    /**
     * Broadcast intent action used to query available content rating systems.
     *
     * <p>The TV input manager service locates available content rating systems by querying
     * broadcast receivers that are registered for this action. An application can offer additional
     * content rating systems to the user by declaring a suitable broadcast receiver in its
     * manifest.
     *
     * <p>Here is an example broadcast receiver declaration that an application might include in its
     * AndroidManifest.xml to advertise custom content rating systems. The meta-data specifies a
     * resource that contains a description of each content rating system that is provided by the
     * application.
     *
     * <p><pre class="prettyprint">
     * {@literal
     * <receiver android:name=".TvInputReceiver">
     *     <intent-filter>
     *         <action android:name=
     *                 "android.media.tv.action.QUERY_CONTENT_RATING_SYSTEMS" />
     *     </intent-filter>
     *     <meta-data
     *             android:name="android.media.tv.metadata.CONTENT_RATING_SYSTEMS"
     *             android:resource="@xml/tv_content_rating_systems" />
     * </receiver>}</pre>
     *
     * <p>In the above example, the <code>@xml/tv_content_rating_systems</code> resource refers to an
     * XML resource whose root element is <code>&lt;rating-system-definitions&gt;</code> that
     * contains zero or more <code>&lt;rating-system-definition&gt;</code> elements. Each <code>
     * &lt;rating-system-definition&gt;</code> element specifies the ratings, sub-ratings and rating
     * orders of a particular content rating system.
     *
     * @see TvContentRating
     */
    public static final String ACTION_QUERY_CONTENT_RATING_SYSTEMS = "android.media.tv.action.QUERY_CONTENT_RATING_SYSTEMS";

    /**
     * Activity action to set up channel sources i.e.&nbsp;TV inputs of type
     * {@link TvInputInfo#TYPE_TUNER}. When invoked, the system will display an appropriate UI for
     * the user to initiate the individual setup flow provided by
     * {@link android.R.attr#setupActivity} of each TV input service.
     */
    public static final String ACTION_SETUP_INPUTS = "android.media.tv.action.SETUP_INPUTS";

    /**
     * Activity action to display the recording schedules. When invoked, the system will display an
     * appropriate UI to browse the schedules.
     */
    public static final String ACTION_VIEW_RECORDING_SCHEDULES = "android.media.tv.action.VIEW_RECORDING_SCHEDULES";

    public static final int BROADCAST_INFO_STREAM_EVENT = 5;

    public static final int BROADCAST_INFO_TYPE_COMMAND = 7;

    public static final int BROADCAST_INFO_TYPE_DSMCC = 6;

    public static final int BROADCAST_INFO_TYPE_PES = 4;

    public static final int BROADCAST_INFO_TYPE_SECTION = 3;

    public static final int BROADCAST_INFO_TYPE_TABLE = 2;

    public static final int BROADCAST_INFO_TYPE_TIMELINE = 8;

    public static final int BROADCAST_INFO_TYPE_TS = 1;

    /**
     * State for {@link #getInputState(String)} and
     * {@link TvInputCallback#onInputStateChanged(String, int)}: The input source is connected.
     *
     * <p>This state indicates that a source device is connected to the input port and is in the
     * normal operation mode. It is mostly relevant to hardware inputs such as HDMI input.
     * Non-hardware inputs are considered connected all the time.
     */
    public static final int INPUT_STATE_CONNECTED = 0;

    /**
     * State for {@link #getInputState(String)} and
     * {@link TvInputCallback#onInputStateChanged(String, int)}: The input source is connected but
     * in standby mode.
     *
     * <p>This state indicates that a source device is connected to the input port but is in standby
     * or low power mode. It is mostly relevant to hardware inputs such as HDMI input and Component
     * inputs.
     */
    public static final int INPUT_STATE_CONNECTED_STANDBY = 1;

    /**
     * State for {@link #getInputState(String)} and
     * {@link TvInputCallback#onInputStateChanged(String, int)}: The input source is disconnected.
     *
     * <p>This state indicates that a source device is disconnected from the input port. It is
     * mostly relevant to hardware inputs such as HDMI input.
     */
    public static final int INPUT_STATE_DISCONNECTED = 2;

    /**
     * Content rating systems metadata associated with {@link #ACTION_QUERY_CONTENT_RATING_SYSTEMS}.
     *
     * <p>Specifies the resource ID of an XML resource that describes the content rating systems
     * that are provided by the application.
     */
    public static final String META_DATA_CONTENT_RATING_SYSTEMS = "android.media.tv.metadata.CONTENT_RATING_SYSTEMS";

    /**
     * Error for {@link TvInputService.RecordingSession#notifyError(int)} and
     * {@link TvRecordingClient.RecordingCallback#onError(int)}: Recording cannot proceed due to
     * insufficient storage space.
     */
    public static final int RECORDING_ERROR_INSUFFICIENT_SPACE = 1;

    /**
     * Error for {@link TvInputService.RecordingSession#notifyError(int)} and
     * {@link TvRecordingClient.RecordingCallback#onError(int)}: Recording cannot proceed because
     * a required recording resource was not able to be allocated.
     */
    public static final int RECORDING_ERROR_RESOURCE_BUSY = 2;

    /**
     * Error for {@link TvInputService.RecordingSession#notifyError(int)} and
     * {@link TvRecordingClient.RecordingCallback#onError(int)}: The requested operation cannot be
     * completed due to a problem that does not fit under any other error codes, or the error code
     * for the problem is defined on the higher version than application's
     * <code>android:targetSdkVersion</code>.
     */
    public static final int RECORDING_ERROR_UNKNOWN = 0;

    /**
     * An object of {@link AdBuffer}.
     *
     * <p> Type: android.media.tv.AdBuffer
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_AD_BUFFER = "ad_buffer";

    /**
     * An object of {@link AdResponse}.
     *
     * <p> Type: android.media.tv.AdResponse
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_AD_RESPONSE = "ad_response";

    /**
     * An object of {@link BroadcastInfoResponse}.
     *
     * <p> Type: android.media.tv.BroadcastInfoResponse
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_BROADCAST_INFO_RESPONSE = "broadcast_info_response";

    /**
     * The URI of a channel.
     *
     * <p> Type: android.net.Uri
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_CHANNEL_URI = "channel_uri";

    /**
     * A list which includes track information.
     *
     * <p> Type: {@code java.util.List<android.media.tv.TvTrackInfo> }
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_TRACKS = "tracks";

    /**
     * The ID of the track.
     *
     * <p> Type: String
     *
     * @see TvTrackInfo#getId()
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_TRACK_ID = "track_id";

    /**
     * The type of the track.
     *
     * <p>One of {@link TvTrackInfo#TYPE_AUDIO}, {@link TvTrackInfo#TYPE_VIDEO},
     * {@link TvTrackInfo#TYPE_SUBTITLE}.
     *
     * <p> Type: Integer
     *
     * @see TvTrackInfo#getType()
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_TRACK_TYPE = "track_type";

    /**
     * The type of TV message.
     * <p>It can be one of {@link TV_MESSAGE_TYPE_WATERMARK},
     * {@link TV_MESSAGE_TYPE_CLOSED_CAPTION}, {@link TV_MESSAGE_TYPE_OTHER}
     *
     * <p> Type: Integer
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_TV_MESSAGE_TYPE = "tv_message_type";

    /**
     * The reason why the video became unavailable.
     * <p>The value can be {@link VIDEO_UNAVAILABLE_REASON_BUFFERING},
     * {@link VIDEO_UNAVAILABLE_REASON_AUDIO_ONLY}, etc.
     *
     * <p> Type: Integer
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_KEY_VIDEO_UNAVAILABLE_REASON = "video_unavailable_reason";

    /**
     * Notifies the advertisement buffer is consumed.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_AD_BUFFER
     */
    public static final String SESSION_DATA_TYPE_AD_BUFFER_CONSUMED = "ad_buffer_consumed";

    /**
     * Notifies response for advertisement.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_AD_RESPONSE
     */
    public static final String SESSION_DATA_TYPE_AD_RESPONSE = "ad_response";

    /**
     * Notifies response for broadcast info.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_BROADCAST_INFO_RESPONSE
     */
    public static final String SESSION_DATA_TYPE_BROADCAST_INFO_RESPONSE = "broadcast_info_response";

    /**
     * Sends the list of all audio/video/subtitle tracks.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_TRACKS
     */
    public static final String SESSION_DATA_TYPE_TRACKS_CHANGED = "tracks_changed";

    /**
     * Sends the type and ID of a selected track. This is used to inform the application that a
     * specific track is selected.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_TRACK_TYPE
     * @see SESSION_DATA_KEY_TRACK_ID
     */
    public static final String SESSION_DATA_TYPE_TRACK_SELECTED = "track_selected";

    /**
     * Informs the application that the session has been tuned to the given channel.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_CHANNEL_URI
     */
    public static final String SESSION_DATA_TYPE_TUNED = "tuned";

    /**
     * Sends the TV message.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see TvInputService.Session#notifyTvMessage(int, Bundle)
     * @see SESSION_DATA_KEY_TV_MESSAGE_TYPE
     */
    public static final String SESSION_DATA_TYPE_TV_MESSAGE = "tv_message";

    /**
     * Informs the application that the video is now available for watching.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     */
    public static final String SESSION_DATA_TYPE_VIDEO_AVAILABLE = "video_available";

    /**
     * Informs the application that the video became unavailable for some reason.
     *
     * @see TvInputService.Session#sendTvInputSessionData(String, Bundle)
     * @see SESSION_DATA_KEY_VIDEO_UNAVAILABLE_REASON
     */
    public static final String SESSION_DATA_TYPE_VIDEO_UNAVAILABLE = "video_unavailable";

    /**
     * Signal lost.
     */
    public static final int SIGNAL_STRENGTH_LOST = 1;

    /**
     * Strong signal.
     */
    public static final int SIGNAL_STRENGTH_STRONG = 3;

    /**
     * Weak signal.
     */
    public static final int SIGNAL_STRENGTH_WEAK = 2;

    /**
     * Value returned by {@link TvInputService.Session#onTimeShiftGetCurrentPosition()} and
     * {@link TvInputService.Session#onTimeShiftGetStartPosition()} when time shifting has not
     * yet started.
     */
    public static final long TIME_SHIFT_INVALID_TIME = -9223372036854775808L;

    /**
     * Time shift mode: auto.
     * <p>Time shift mode is handled automatically.
     */
    public static final int TIME_SHIFT_MODE_AUTO = 4;

    /**
     * Time shift mode: local.
     * <p>Time shift is handle locally, using on-device data. E.g. playing a local file.
     */
    public static final int TIME_SHIFT_MODE_LOCAL = 2;

    /**
     * Time shift mode: network.
     * <p>Time shift is handle remotely via network. E.g. online streaming.
     */
    public static final int TIME_SHIFT_MODE_NETWORK = 3;

    /**
     * Time shift mode: off.
     * <p>Time shift is disabled.
     */
    public static final int TIME_SHIFT_MODE_OFF = 1;

    /**
     * Status for {@link TvInputService.Session#notifyTimeShiftStatusChanged(int)} and
     * {@link TvView.TvInputCallback#onTimeShiftStatusChanged(String, int)}: Time shifting is
     * currently available. In this status, the application assumes it can pause/resume playback,
     * seek to a specified time position and set playback rate and audio mode.
     */
    public static final int TIME_SHIFT_STATUS_AVAILABLE = 3;

    /**
     * Status for {@link TvInputService.Session#notifyTimeShiftStatusChanged(int)} and
     * {@link TvView.TvInputCallback#onTimeShiftStatusChanged(String, int)}: Time shifting is
     * currently unavailable but might work again later.
     */
    public static final int TIME_SHIFT_STATUS_UNAVAILABLE = 2;

    /**
     * Status for {@link TvInputService.Session#notifyTimeShiftStatusChanged(int)} and
     * {@link TvView.TvInputCallback#onTimeShiftStatusChanged(String, int)}: Unknown status. Also
     * the status prior to calling {@code notifyTimeShiftStatusChanged}.
     */
    public static final int TIME_SHIFT_STATUS_UNKNOWN = 0;

    /**
     * Status for {@link TvInputService.Session#notifyTimeShiftStatusChanged(int)} and
     * {@link TvView.TvInputCallback#onTimeShiftStatusChanged(String, int)}: The current TV input
     * does not support time shifting.
     */
    public static final int TIME_SHIFT_STATUS_UNSUPPORTED = 1;

    /**
     * This value for {@link #TV_MESSAGE_KEY_GROUP_ID} denotes that the message doesn't
     * belong to any group.
     */
    public static final long TV_MESSAGE_GROUP_ID_NONE = -1L;

    /**
     * This constant is used as a {@link Bundle} key for TV messages. This is used to
     * optionally identify messages that belong together, such as headers and bodies
     * of the same event. For messages that do not have a group, this value
     * should be {@link #TV_MESSAGE_GROUP_ID_NONE}.
     *
     * <p> As -1 is a reserved value, -1 should not be used as a valid groupId.
     *
     * <p> Type: long
     */
    public static final String TV_MESSAGE_KEY_GROUP_ID = "android.media.tv.TvInputManager.group_id";

    /**
     * This constant is used as a {@link Bundle} key for TV messages. The value of the key
     * stores the raw data contained in this TV message. The format of this data is determined
     * by the format defined by the subtype, found using the key at
     * {@link #TV_MESSAGE_KEY_SUBTYPE}. See {@link #TV_MESSAGE_KEY_SUBTYPE} for more
     * information on how to parse this data.
     *
     * <p> Type: byte[]
     */
    public static final String TV_MESSAGE_KEY_RAW_DATA = "android.media.tv.TvInputManager.raw_data";

    /**
     * This constant is used as a {@link Bundle} key for TV messages. The value of the key
     * identifies the stream on the TV input source for which the watermark event is relevant to.
     *
     * <p> Type: String
     */
    public static final String TV_MESSAGE_KEY_STREAM_ID = "android.media.tv.TvInputManager.stream_id";

    /**
     * This constant is used as a {@link Bundle} key for TV messages. The value of the key
     * identifies the subtype of the data, such as the format of the CC data. The format
     * found at this key can then be used to identify how to parse the data at
     * {@link #TV_MESSAGE_KEY_RAW_DATA}.
     *
     * <p> To parse the raw data based on the subtype, please refer to the official
     * documentation of the concerning subtype. For example, for the subtype
     * {@link #TV_MESSAGE_SUBTYPE_WATERMARKING_A335}, the document for A/335 from the ATSC
     * standard details how this data is formatted. Similarly, the subtype
     * {@link #TV_MESSAGE_SUBTYPE_CC_608E} is documented in the ANSI/CTA standard for
     * 608-E. These subtypes are examples of common formats for their respective uses
     * and other subtypes may exist.
     *
     * <p> Type: String
     */
    public static final String TV_MESSAGE_KEY_SUBTYPE = "android.media.tv.TvInputManager.subtype";

    /**
     * This is a subtype for TV messages that can be potentially found as a value
     * at {@link #TV_MESSAGE_KEY_SUBTYPE}. It identifies the subtype of the message
     * as the CC format CTA 608-E.
     */
    public static final String TV_MESSAGE_SUBTYPE_CC_608E = "CTA 608-E";

    /**
     * This is a subtype for TV messages that can be potentially found as a value
     * at {@link #TV_MESSAGE_KEY_SUBTYPE}. It identifies the subtype of the message
     * as the watermarking format ATSC A/335.
     */
    public static final String TV_MESSAGE_SUBTYPE_WATERMARKING_A335 = "ATSC A/335";

    /**
     * Indicates that this TV message contains Closed Captioning data
     */
    public static final int TV_MESSAGE_TYPE_CLOSED_CAPTION = 2;

    /**
     * Indicates that this TV message contains other data
     */
    public static final int TV_MESSAGE_TYPE_OTHER = 1000;

    /**
     * Indicates that this TV message contains watermarking data
     */
    public static final int TV_MESSAGE_TYPE_WATERMARK = 1;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the current TV program is audio-only.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_AUDIO_ONLY = 4;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the corresponding TV input has stopped playback temporarily to buffer more data.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_BUFFERING = 3;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * of a geographical blackout.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_BLACKOUT = 16;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * smart card is invalid.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_CARD_INVALID = 15;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * smart card is muted.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_CARD_MUTE = 14;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the output protection level enabled on the device is not sufficient to meet the requirements
     * in the license policy.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_INSUFFICIENT_OUTPUT_PROTECTION = 7;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * Using a license in whhich the keys have expired.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_LICENSE_EXPIRED = 10;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the device need be activated.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_NEED_ACTIVATION = 11;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the device need be paired.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_NEED_PAIRING = 12;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * smart card is missed.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_NO_CARD = 13;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the PVR record is not allowed by the license policy.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_PVR_RECORDING_NOT_ALLOWED = 8;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * CAS system is rebooting.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_REBOOTING = 17;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * of unknown CAS error.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_CAS_UNKNOWN = 18;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the resource is not enough to meet requirement.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_INSUFFICIENT_RESOURCE = 6;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the source is not physically connected, for example the HDMI cable is not connected.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_NOT_CONNECTED = 5;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * it has been stopped by {@link TvView#stopPlayback(int)}.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_STOPPED = 19;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable because
     * the corresponding TV input is in the middle of tuning to a new channel.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_TUNING = 1;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable due to
     * an unspecified error.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_UNKNOWN = 0;

    /**
     * Reason for {@link TvInputService.Session#notifyVideoUnavailable(int)} and
     * {@link TvView.TvInputCallback#onVideoUnavailable(String, int)}: Video is unavailable due to
     * weak TV signal.
     */
    public static final int VIDEO_UNAVAILABLE_REASON_WEAK_SIGNAL = 2;

    private TvInputManager() {
    }
}
