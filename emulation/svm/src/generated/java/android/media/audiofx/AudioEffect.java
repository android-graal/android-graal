package android.media.audiofx;

/**
 * AudioEffect is the base class for controlling audio effects provided by the android audio
 * framework.
 * <p>Applications should not use the AudioEffect class directly but one of its derived classes to
 * control specific effects:
 * <ul>
 *   <li> {@link android.media.audiofx.Equalizer}</li>
 *   <li> {@link android.media.audiofx.Virtualizer}</li>
 *   <li> {@link android.media.audiofx.BassBoost}</li>
 *   <li> {@link android.media.audiofx.PresetReverb}</li>
 *   <li> {@link android.media.audiofx.EnvironmentalReverb}</li>
 *   <li> {@link android.media.audiofx.DynamicsProcessing}</li>
 *   <li> {@link android.media.audiofx.HapticGenerator}</li>
 * </ul>
 * <p>To apply the audio effect to a specific AudioTrack or MediaPlayer instance,
 * the application must specify the audio session ID of that instance when creating the AudioEffect.
 * (see {@link android.media.MediaPlayer#getAudioSessionId()} for details on audio sessions).
 * <p>NOTE: attaching insert effects (equalizer, bass boost, virtualizer) to the global audio output
 * mix by use of session 0 is deprecated.
 * <p>Creating an AudioEffect object will create the corresponding effect engine in the audio
 * framework if no instance of the same effect type exists in the specified audio session.
 * If one exists, this instance will be used.
 * <p>The application creating the AudioEffect object (or a derived class) will either receive
 * control of the effect engine or not depending on the priority parameter. If priority is higher
 * than the priority used by the current effect engine owner, the control will be transfered to the
 * new object. Otherwise control will remain with the previous object. In this case, the new
 * application will be notified of changes in effect engine state or control ownership by the
 * appropriate listener.
 */
public final class AudioEffect {
    /**
     *  Intent to signal to the effect control application or service that an audio session
     *  is closed and that effects should not be applied anymore.
     *  <p>The effect control application receiving this intent will delete all effects on
     *  this session and store current settings in package specific storage.
     *  <p>The calling package name is indicated by the {@link #EXTRA_PACKAGE_NAME} extra and the
     *  audio session ID by the {@link #EXTRA_AUDIO_SESSION} extra. Both extras are mandatory.
     *  <p>It is good practice for applications to broadcast this intent when music playback stops
     *  and/or when exiting to free system resources consumed by audio effect engines.
     */
    public static final String ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION = "android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION";

    /**
     *  Intent to launch an audio effect control panel UI.
     *  <p>The goal of this intent is to enable separate implementations of music/media player
     *  applications and audio effect control application or services.
     *  This will allow platform vendors to offer more advanced control options for standard effects
     *  or control for platform specific effects.
     *  <p>The intent carries a number of extras used by the player application to communicate
     *  necessary pieces of information to the control panel application.
     *  <p>The calling application must use the
     *  {@link android.app.Activity#startActivityForResult(Intent, int)} method to launch the
     *  control panel so that its package name is indicated and used by the control panel
     *  application to keep track of changes for this particular application.
     *  <p>The {@link #EXTRA_AUDIO_SESSION} extra will indicate an audio session to which the
     *  audio effects should be applied. If no audio session is specified, either one of the
     *  follownig will happen:
     *  <p>- If an audio session was previously opened by the calling application with
     *  {@link #ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION} intent, the effect changes will
     *  be applied to that session.
     *  <p>- If no audio session is opened, the changes will be stored in the package specific
     *  storage area and applied whenever a new audio session is opened by this application.
     *  <p>The {@link #EXTRA_CONTENT_TYPE} extra will help the control panel application
     *  customize both the UI layout and the default audio effect settings if none are already
     *  stored for the calling application.
     */
    public static final String ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL = "android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL";

    /**
     *  Intent to signal to the effect control application or service that a new audio session
     *  is opened and requires audio effects to be applied.
     *  <p>This is different from {@link #ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL} in that no
     *  UI should be displayed in this case. Music player applications can broadcast this intent
     *  before starting playback to make sure that any audio effect settings previously selected
     *  by the user are applied.
     *  <p>The effect control application receiving this intent will look for previously stored
     *  settings for the calling application, create all required audio effects and apply the
     *  effect settings to the specified audio session.
     *  <p>The calling package name is indicated by the {@link #EXTRA_PACKAGE_NAME} extra and the
     *  audio session ID by the {@link #EXTRA_AUDIO_SESSION} extra. Both extras are mandatory.
     *  <p>If no stored settings are found for the calling application, default settings for the
     *  content type indicated by {@link #EXTRA_CONTENT_TYPE} will be applied. The default settings
     *  for a given content type are platform specific.
     */
    public static final String ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION = "android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION";

    /**
     * Internal operation status. Not returned by any method.
     */
    public static final int ALREADY_EXISTS = -2;

    /**
     * Value for {@link #EXTRA_CONTENT_TYPE} when the type of content played is game audio
     */
    public static final int CONTENT_TYPE_GAME = 2;

    /**
     * Value for {@link #EXTRA_CONTENT_TYPE} when the type of content played is video or movie
     */
    public static final int CONTENT_TYPE_MOVIE = 1;

    /**
     * Value for {@link #EXTRA_CONTENT_TYPE} when the type of content played is music
     */
    public static final int CONTENT_TYPE_MUSIC = 0;

    /**
     * Value for {@link #EXTRA_CONTENT_TYPE} when the type of content played is voice audio
     */
    public static final int CONTENT_TYPE_VOICE = 3;

    /**
     * Effect connection mode is auxiliary.
     * <p>Auxiliary effects must be created on session 0 (global output mix). In order for a
     * MediaPlayer or AudioTrack to be fed into this effect, they must be explicitely attached to
     * this effect and a send level must be specified.
     * <p>Use the effect ID returned by {@link #getId()} to designate this particular effect when
     * attaching it to the MediaPlayer or AudioTrack.
     */
    public static final String EFFECT_AUXILIARY = "Auxiliary";

    /**
     * Effect connection mode is insert. Specifying an audio session ID when creating the effect
     * will insert this effect after all players in the same audio session.
     */
    public static final String EFFECT_INSERT = "Insert";

    /**
     * Effect connection mode is post processing.
     * The audio post processing effects are attached to an audio output stream or device
     */
    public static final String EFFECT_POST_PROCESSING = "Post Processing";

    /**
     * Effect connection mode is pre processing.
     * The audio pre processing effects are attached to an audio input stream or device
     */
    public static final String EFFECT_PRE_PROCESSING = "Pre Processing";

    /**
     * Unspecified error.
     */
    public static final int ERROR = -1;

    /**
     * Operation failed due to bad parameter value.
     */
    public static final int ERROR_BAD_VALUE = -4;

    /**
     * Operation failed due to dead remote object.
     */
    public static final int ERROR_DEAD_OBJECT = -7;

    /**
     * Operation failed because it was requested in wrong state.
     */
    public static final int ERROR_INVALID_OPERATION = -5;

    /**
     * Operation failed due to bad object initialization.
     */
    public static final int ERROR_NO_INIT = -3;

    /**
     * Operation failed due to lack of memory.
     */
    public static final int ERROR_NO_MEMORY = -6;

    /**
     * Contains the ID of the audio session the effects should be applied to.
     * <p>This extra is for use with {@link #ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL},
     * {@link #ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION} and
     * {@link #ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION} intents.
     * <p>The extra value is of type int and is the audio session ID.
     *  @see android.media.MediaPlayer#getAudioSessionId() for details on audio sessions.
     */
    public static final String EXTRA_AUDIO_SESSION = "android.media.extra.AUDIO_SESSION";

    /**
     * Indicates which type of content is played by the application.
     * <p>This extra is for use with {@link #ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL} and
     * {@link #ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION} intents.
     * <p>This information is used by the effect control application to customize UI and select
     * appropriate default effect settings. The content type is one of the following:
     * <ul>
     *   <li>{@link #CONTENT_TYPE_MUSIC}</li>
     *   <li>{@link #CONTENT_TYPE_MOVIE}</li>
     *   <li>{@link #CONTENT_TYPE_GAME}</li>
     *   <li>{@link #CONTENT_TYPE_VOICE}</li>
     * </ul>
     * If omitted, the content type defaults to {@link #CONTENT_TYPE_MUSIC}.
     */
    public static final String EXTRA_CONTENT_TYPE = "android.media.extra.CONTENT_TYPE";

    /**
     * Contains the package name of the calling application.
     * <p>This extra is for use with {@link #ACTION_OPEN_AUDIO_EFFECT_CONTROL_SESSION} and
     * {@link #ACTION_CLOSE_AUDIO_EFFECT_CONTROL_SESSION} intents.
     * <p>The extra value is a string containing the full package name.
     */
    public static final String EXTRA_PACKAGE_NAME = "android.media.extra.PACKAGE_NAME";

    /**
     * Successful operation.
     */
    public static final int SUCCESS = 0;

    private AudioEffect() {
    }
}
