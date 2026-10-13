package android.media.tv.interactive;

/**
 * A TV interactive application service is a service that provides runtime environment and runs TV
 * interactive applications.
 */
public final class TvInteractiveAppService {
    /**
     * Command to quiet channel change. No channel banner or channel info is shown.
     * <p>Refer to HbbTV Spec 2.0.4 chapter A.2.4.3.
     */
    public static final String COMMAND_PARAMETER_KEY_CHANGE_CHANNEL_QUIETLY = "command_change_channel_quietly";

    /**
     * Playback command parameter: channel URI.
     * <p>Type: android.net.Uri
     *
     * @see #PLAYBACK_COMMAND_TYPE_TUNE
     */
    public static final String COMMAND_PARAMETER_KEY_CHANNEL_URI = "command_channel_uri";

    /**
     * Playback command parameter: TV input ID.
     * <p>Type: String
     *
     * @see TvInputInfo#getId()
     */
    public static final String COMMAND_PARAMETER_KEY_INPUT_ID = "command_input_id";

    /**
     * Time shift command parameter: playback params.
     * <p>Type: android.media.PlaybackParams
     *
     * @see #TIME_SHIFT_COMMAND_TYPE_SET_PLAYBACK_PARAMS
     */
    public static final String COMMAND_PARAMETER_KEY_PLAYBACK_PARAMS = "command_playback_params";

    /**
     * Time shift command parameter: program URI.
     * <p>Type: android.net.Uri
     *
     * @see #TIME_SHIFT_COMMAND_TYPE_PLAY
     */
    public static final String COMMAND_PARAMETER_KEY_PROGRAM_URI = "command_program_uri";

    /**
     * Playback command parameter: stop mode.
     * <p>Type: int
     *
     * @see #PLAYBACK_COMMAND_TYPE_STOP
     */
    public static final String COMMAND_PARAMETER_KEY_STOP_MODE = "command_stop_mode";

    /**
     * Time shift command parameter: time position for time shifting, in milliseconds.
     * <p>Type: long
     *
     * @see #TIME_SHIFT_COMMAND_TYPE_SEEK_TO
     */
    public static final String COMMAND_PARAMETER_KEY_TIME_POSITION = "command_time_position";

    /**
     * Time shift command parameter: playback params.
     * <p>Type: Integer. One of {@link TvInputManager#TIME_SHIFT_MODE_OFF},
     * {@link TvInputManager#TIME_SHIFT_MODE_LOCAL},
     * {@link TvInputManager#TIME_SHIFT_MODE_NETWORK},
     * {@link TvInputManager#TIME_SHIFT_MODE_AUTO}.
     *
     * @see #TIME_SHIFT_COMMAND_TYPE_SET_MODE
     */
    public static final String COMMAND_PARAMETER_KEY_TIME_SHIFT_MODE = "command_time_shift_mode";

    /**
     * Playback command parameter: track ID.
     * <p>Type: String
     *
     * @see #PLAYBACK_COMMAND_TYPE_SELECT_TRACK
     * @see TvTrackInfo#getId()
     */
    public static final String COMMAND_PARAMETER_KEY_TRACK_ID = "command_track_id";

    /**
     * Playback command parameter: track type.
     * <p>Type: int
     *
     * @see #PLAYBACK_COMMAND_TYPE_SELECT_TRACK
     * @see TvTrackInfo#getType()
     */
    public static final String COMMAND_PARAMETER_KEY_TRACK_TYPE = "command_track_type";

    /**
     * Playback command parameter: stream volume.
     * <p>Type: float
     *
     * @see #PLAYBACK_COMMAND_TYPE_SET_STREAM_VOLUME
     */
    public static final String COMMAND_PARAMETER_KEY_VOLUME = "command_volume";

    /**
     * Playback command stop mode: show a blank screen.
     * @see #PLAYBACK_COMMAND_TYPE_STOP
     */
    public static final int COMMAND_PARAMETER_VALUE_STOP_MODE_BLANK = 1;

    /**
     * Playback command stop mode: freeze the video.
     * @see #PLAYBACK_COMMAND_TYPE_STOP
     */
    public static final int COMMAND_PARAMETER_VALUE_STOP_MODE_FREEZE = 2;

    /**
     * Playback command type: select the given track.
     */
    public static final String PLAYBACK_COMMAND_TYPE_SELECT_TRACK = "select_track";

    /**
     * Playback command type: set the volume.
     */
    public static final String PLAYBACK_COMMAND_TYPE_SET_STREAM_VOLUME = "set_stream_volume";

    /**
     * Playback command type: stop the playback.
     */
    public static final String PLAYBACK_COMMAND_TYPE_STOP = "stop";

    /**
     * Playback command type: tune to the given channel.
     * @see #COMMAND_PARAMETER_KEY_CHANNEL_URI
     */
    public static final String PLAYBACK_COMMAND_TYPE_TUNE = "tune";

    /**
     * Playback command type: tune to the next channel.
     */
    public static final String PLAYBACK_COMMAND_TYPE_TUNE_NEXT = "tune_next";

    /**
     * Playback command type: tune to the previous channel.
     */
    public static final String PLAYBACK_COMMAND_TYPE_TUNE_PREV = "tune_previous";

    /**
     * This is the interface name that a service implementing a TV Interactive App service should
     * say that it supports -- that is, this is the action it uses for its intent filter. To be
     * supported, the service must also require the
     * {@link android.Manifest.permission#BIND_TV_INTERACTIVE_APP} permission so that other
     * applications cannot abuse it.
     */
    public static final String SERVICE_INTERFACE = "android.media.tv.interactive.TvInteractiveAppService";

    /**
     * Name under which a TvInteractiveAppService component publishes information about itself. This
     * meta-data must reference an XML resource containing an
     * <code>&lt;{@link android.R.styleable#TvInteractiveAppService tv-interactive-app}&gt;</code>
     * tag.
     */
    public static final String SERVICE_META_DATA = "android.media.tv.interactive.app";

    /**
     * Time shift command type: pause.
     *
     * @see TvView#timeShiftPause()
     */
    public static final String TIME_SHIFT_COMMAND_TYPE_PAUSE = "pause";

    /**
     * Time shift command type: play.
     *
     * @see TvView#timeShiftPlay(String, Uri)
     */
    public static final String TIME_SHIFT_COMMAND_TYPE_PLAY = "play";

    /**
     * Time shift command type: resume.
     *
     * @see TvView#timeShiftResume()
     */
    public static final String TIME_SHIFT_COMMAND_TYPE_RESUME = "resume";

    /**
     * Time shift command type: seek to.
     *
     * @see TvView#timeShiftSeekTo(long)
     */
    public static final String TIME_SHIFT_COMMAND_TYPE_SEEK_TO = "seek_to";

    /**
     * Time shift command type: set time shift mode.
     */
    public static final String TIME_SHIFT_COMMAND_TYPE_SET_MODE = "set_mode";

    /**
     * Time shift command type: set playback params.
     *
     * @see TvView#timeShiftSetPlaybackParams(PlaybackParams)
     */
    public static final String TIME_SHIFT_COMMAND_TYPE_SET_PLAYBACK_PARAMS = "set_playback_params";

    private TvInteractiveAppService() {
    }
}
