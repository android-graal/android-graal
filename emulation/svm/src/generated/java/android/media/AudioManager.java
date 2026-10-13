package android.media;

/**
 * AudioManager provides access to volume and ringer mode control.
 */
public final class AudioManager {
    /**
     * Broadcast intent, a hint for applications that audio is about to become
     * 'noisy' due to a change in audio outputs. For example, this intent may
     * be sent when a wired headset is unplugged, or when an A2DP audio
     * sink is disconnected, and the audio system is about to automatically
     * switch audio route to the speaker. Applications that are controlling
     * audio streams may consider pausing, reducing volume or some other action
     * on receipt of this intent so as not to surprise the user with audio
     * from the speaker.
     */
    public static final String ACTION_AUDIO_BECOMING_NOISY = "android.media.AUDIO_BECOMING_NOISY";

    /**
     * Broadcast Action: A sticky broadcast indicating an HDMI cable was plugged or unplugged.
     *
     * The intent will have the following extra values: {@link #EXTRA_AUDIO_PLUG_STATE},
     * {@link #EXTRA_MAX_CHANNEL_COUNT}, {@link #EXTRA_ENCODINGS}.
     * <p>It can only be received by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)}.
     */
    public static final String ACTION_HDMI_AUDIO_PLUG = "android.media.action.HDMI_AUDIO_PLUG";

    /**
     * Broadcast Action: Wired Headset plugged in or unplugged.
     *
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p>The intent will have the following extra values:
     * <ul>
     *   <li><em>state</em> - 0 for unplugged, 1 for plugged. </li>
     *   <li><em>name</em> - Headset type, human readable string </li>
     *   <li><em>microphone</em> - 1 if headset has a microphone, 0 otherwise </li>
     * </ul>
     * </ul>
     */
    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";

    /**
     * Broadcast Action: microphone muting state changed.
     *
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p>The intent has no extra values, use {@link #isMicrophoneMute} to check whether the
     * microphone is muted.
     */
    public static final String ACTION_MICROPHONE_MUTE_CHANGED = "android.media.action.MICROPHONE_MUTE_CHANGED";

    /**
     * Sticky broadcast intent action indicating that the Bluetooth SCO audio
     * connection state has changed. The intent contains on extra {@link #EXTRA_SCO_AUDIO_STATE}
     * indicating the new state which is either {@link #SCO_AUDIO_STATE_DISCONNECTED}
     * or {@link #SCO_AUDIO_STATE_CONNECTED}
     *
     * @see #startBluetoothSco()
     * @deprecated Use  {@link #ACTION_SCO_AUDIO_STATE_UPDATED} instead
     */
    @Deprecated
    public static final String ACTION_SCO_AUDIO_STATE_CHANGED = "android.media.SCO_AUDIO_STATE_CHANGED";

    /**
     * Sticky broadcast intent action indicating that the Bluetooth SCO audio
     * connection state has been updated.
     * <p>This intent has two extras:
     * <ul>
     *   <li> {@link #EXTRA_SCO_AUDIO_STATE} - The new SCO audio state. </li>
     *   <li> {@link #EXTRA_SCO_AUDIO_PREVIOUS_STATE}- The previous SCO audio state. </li>
     * </ul>
     * <p> EXTRA_SCO_AUDIO_STATE or EXTRA_SCO_AUDIO_PREVIOUS_STATE can be any of:
     * <ul>
     *   <li> {@link #SCO_AUDIO_STATE_DISCONNECTED}, </li>
     *   <li> {@link #SCO_AUDIO_STATE_CONNECTING} or </li>
     *   <li> {@link #SCO_AUDIO_STATE_CONNECTED}, </li>
     * </ul>
     * @see #startBluetoothSco()
     */
    public static final String ACTION_SCO_AUDIO_STATE_UPDATED = "android.media.ACTION_SCO_AUDIO_STATE_UPDATED";

    /**
     * Broadcast Action: speakerphone state changed.
     *
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p>The intent has no extra values, use {@link #isSpeakerphoneOn} to check whether the
     * speakerphone functionality is enabled or not.
     */
    public static final String ACTION_SPEAKERPHONE_STATE_CHANGED = "android.media.action.SPEAKERPHONE_STATE_CHANGED";

    /**
     * Decrease the ringer volume.
     *
     * @see #adjustVolume(int, int)
     * @see #adjustStreamVolume(int, int, int)
     */
    public static final int ADJUST_LOWER = -1;

    /**
     * Mute the volume. Has no effect if the stream is already muted.
     *
     * @see #adjustVolume(int, int)
     * @see #adjustStreamVolume(int, int, int)
     */
    public static final int ADJUST_MUTE = -100;

    /**
     * Increase the ringer volume.
     *
     * @see #adjustVolume(int, int)
     * @see #adjustStreamVolume(int, int, int)
     */
    public static final int ADJUST_RAISE = 1;

    /**
     * Maintain the previous ringer volume. This may be useful when needing to
     * show the volume toast without actually modifying the volume.
     *
     * @see #adjustVolume(int, int)
     * @see #adjustStreamVolume(int, int, int)
     */
    public static final int ADJUST_SAME = 0;

    /**
     * Toggle the mute state. If muted the stream will be unmuted. If not muted
     * the stream will be muted.
     *
     * @see #adjustVolume(int, int)
     * @see #adjustStreamVolume(int, int, int)
     */
    public static final int ADJUST_TOGGLE_MUTE = 101;

    /**
     * Unmute the volume. Has no effect if the stream is not muted.
     *
     * @see #adjustVolume(int, int)
     * @see #adjustStreamVolume(int, int, int)
     */
    public static final int ADJUST_UNMUTE = 100;

    /**
     * Used to indicate a gain of audio focus, or a request of audio focus, of unknown duration.
     * @see OnAudioFocusChangeListener#onAudioFocusChange(int)
     * @see #requestAudioFocus(OnAudioFocusChangeListener, int, int)
     */
    public static final int AUDIOFOCUS_GAIN = 1;

    /**
     * Used to indicate a temporary gain or request of audio focus, anticipated to last a short
     * amount of time. Examples of temporary changes are the playback of driving directions, or an
     * event notification.
     * @see OnAudioFocusChangeListener#onAudioFocusChange(int)
     * @see #requestAudioFocus(OnAudioFocusChangeListener, int, int)
     */
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;

    /**
     * Used to indicate a temporary request of audio focus, anticipated to last a short
     * amount of time, during which no other applications, or system components, should play
     * anything. Examples of exclusive and transient audio focus requests are voice
     * memo recording and speech recognition, during which the system shouldn't play any
     * notifications, and media playback should have paused.
     * @see #requestAudioFocus(OnAudioFocusChangeListener, int, int)
     */
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;

    /**
     * Used to indicate a temporary request of audio focus, anticipated to last a short
     * amount of time, and where it is acceptable for other audio applications to keep playing
     * after having lowered their output level (also referred to as "ducking").
     * Examples of temporary changes are the playback of driving directions where playback of music
     * in the background is acceptable.
     * @see OnAudioFocusChangeListener#onAudioFocusChange(int)
     * @see #requestAudioFocus(OnAudioFocusChangeListener, int, int)
     */
    public static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;

    /**
     * Used to indicate a loss of audio focus of unknown duration.
     * @see OnAudioFocusChangeListener#onAudioFocusChange(int)
     */
    public static final int AUDIOFOCUS_LOSS = -1;

    /**
     * Used to indicate a transient loss of audio focus.
     * @see OnAudioFocusChangeListener#onAudioFocusChange(int)
     */
    public static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;

    /**
     * Used to indicate a transient loss of audio focus where the loser of the audio focus can
     * lower its output volume if it wants to continue playing (also referred to as "ducking"), as
     * the new focus owner doesn't require others to be silent.
     * @see OnAudioFocusChangeListener#onAudioFocusChange(int)
     */
    public static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;

    /**
     * Used to indicate no audio focus has been gained or lost, or requested.
     */
    public static final int AUDIOFOCUS_NONE = 0;

    /**
     * A focus change request whose granting is delayed: the request was successful, but the
     * requester will only be granted audio focus once the condition that prevented immediate
     * granting has ended.
     * See {@link #requestAudioFocus(AudioFocusRequest)} and
     * {@link AudioFocusRequest.Builder#setAcceptsDelayedFocusGain(boolean)}
     */
    public static final int AUDIOFOCUS_REQUEST_DELAYED = 2;

    /**
     * A failed focus change request.
     */
    public static final int AUDIOFOCUS_REQUEST_FAILED = 0;

    /**
     * A successful focus change request.
     */
    public static final int AUDIOFOCUS_REQUEST_GRANTED = 1;

    /**
     * A special audio session ID to indicate that the audio session ID isn't known and the
     * framework should generate a new value. This can be used when building a new
     * {@link AudioTrack} instance with
     * {@link AudioTrack#AudioTrack(AudioAttributes, AudioFormat, int, int, int)}.
     */
    public static final int AUDIO_SESSION_ID_GENERATE = 0;

    /**
     * Return value for {@link #getDirectPlaybackSupport(AudioFormat, AudioAttributes)}:
     *         direct playback supported. This value covers direct playback that is bitstream pass-through
     *         such as compressed pass-through.
     */
    public static final int DIRECT_PLAYBACK_BITSTREAM_SUPPORTED = 4;

    /**
     * Return value for {@link #getDirectPlaybackSupport(AudioFormat, AudioAttributes)}:
     *         direct playback not supported.
     */
    public static final int DIRECT_PLAYBACK_NOT_SUPPORTED = 0;

    /**
     * Return value for {@link #getDirectPlaybackSupport(AudioFormat, AudioAttributes)}:
     *         direct offload playback supported with gapless transitions. Compressed offload is a variant
     *         of direct playback. It is the feature that allows audio processing tasks to be done on the
     *         Android device but not on the application processor, instead, it is handled by dedicated
     *         hardware such as audio DSPs. That will allow the application processor to be idle as much as
     *         possible, which is good for power saving. Compressed offload playback supports
     *         {@link AudioTrack.StreamEventCallback} for event notifications. Gapless transitions
     *         indicates the ability to play consecutive audio tracks without an audio silence in
     *         between.
     */
    public static final int DIRECT_PLAYBACK_OFFLOAD_GAPLESS_SUPPORTED = 3;

    /**
     * Return value for {@link #getDirectPlaybackSupport(AudioFormat, AudioAttributes)}:
     *         direct offload playback supported. Compressed offload is a variant of direct playback.
     *         It is the feature that allows audio processing tasks to be done on the Android device but
     *         not on the application processor, instead, it is handled by dedicated hardware such as audio
     *         DSPs. That will allow the application processor to be idle as much as possible, which is
     *         good for power saving. Compressed offload playback supports
     *         {@link AudioTrack.StreamEventCallback} for event notifications.
     */
    public static final int DIRECT_PLAYBACK_OFFLOAD_SUPPORTED = 1;

    /**
     * The surround sound formats are ALWAYS available, even if they are not detected by the
     * hardware. Those formats will be reported as part of the HDMI output capability.
     * Applications are then free to use either PCM or encoded output.
     */
    public static final int ENCODED_SURROUND_OUTPUT_ALWAYS = 2;

    /**
     * The surround sound formats are available for use if they are detected. This is the default
     * mode.
     */
    public static final int ENCODED_SURROUND_OUTPUT_AUTO = 0;

    /**
     * Surround sound formats are available according to the choice of user, even if they are not
     * detected by the hardware. Those formats will be reported as part of the HDMI output
     * capability. Applications are then free to use either PCM or encoded output.
     */
    public static final int ENCODED_SURROUND_OUTPUT_MANUAL = 3;

    /**
     * The surround sound formats are NEVER available, even if they are detected by the hardware.
     * Those formats will not be reported.
     */
    public static final int ENCODED_SURROUND_OUTPUT_NEVER = 1;

    /**
     * The mode for surround sound formats is unknown.
     */
    public static final int ENCODED_SURROUND_OUTPUT_UNKNOWN = -1;

    /**
     * A default error code.
     */
    public static final int ERROR = -1;

    /**
     * An error code indicating that the object reporting it is no longer valid and needs to
     * be recreated.
     */
    public static final int ERROR_DEAD_OBJECT = -6;

    /**
     * Extra used in {@link #ACTION_HDMI_AUDIO_PLUG} to communicate whether HDMI is plugged in
     * or unplugged.
     * An integer value of 1 indicates a plugged-in state, 0 is unplugged.
     */
    public static final String EXTRA_AUDIO_PLUG_STATE = "android.media.extra.AUDIO_PLUG_STATE";

    /**
     * Extra used in {@link #ACTION_HDMI_AUDIO_PLUG} to define the audio encodings supported by
     * the connected HDMI device.
     * The corresponding array of encoding values is only available when the device is plugged in
     * (as expressed by {@link #EXTRA_AUDIO_PLUG_STATE}). Encoding values are defined in
     * {@link AudioFormat} (for instance see {@link AudioFormat#ENCODING_PCM_16BIT}). Use
     * {@link android.content.Intent#getIntArrayExtra(String)} to retrieve the encoding values.
     */
    public static final String EXTRA_ENCODINGS = "android.media.extra.ENCODINGS";

    /**
     * Extra used in {@link #ACTION_HDMI_AUDIO_PLUG} to define the maximum number of channels
     * supported by the HDMI device.
     * The corresponding integer value is only available when the device is plugged in (as expressed
     * by {@link #EXTRA_AUDIO_PLUG_STATE}).
     */
    public static final String EXTRA_MAX_CHANNEL_COUNT = "android.media.extra.MAX_CHANNEL_COUNT";

    /**
     * The new ringer mode.
     *
     * @see #RINGER_MODE_CHANGED_ACTION
     * @see #RINGER_MODE_NORMAL
     * @see #RINGER_MODE_SILENT
     * @see #RINGER_MODE_VIBRATE
     */
    public static final String EXTRA_RINGER_MODE = "android.media.EXTRA_RINGER_MODE";

    /**
     * Extra for intent {@link #ACTION_SCO_AUDIO_STATE_UPDATED} containing the previous
     * bluetooth SCO connection state.
     */
    public static final String EXTRA_SCO_AUDIO_PREVIOUS_STATE = "android.media.extra.SCO_AUDIO_PREVIOUS_STATE";

    /**
     * Extra for intent {@link #ACTION_SCO_AUDIO_STATE_CHANGED} or
     * {@link #ACTION_SCO_AUDIO_STATE_UPDATED} containing the new bluetooth SCO connection state.
     */
    public static final String EXTRA_SCO_AUDIO_STATE = "android.media.extra.SCO_AUDIO_STATE";

    /**
     * The new vibrate setting for a particular type.
     *
     * @see #VIBRATE_SETTING_CHANGED_ACTION
     * @see #EXTRA_VIBRATE_TYPE
     * @see #VIBRATE_SETTING_ON
     * @see #VIBRATE_SETTING_OFF
     * @see #VIBRATE_SETTING_ONLY_SILENT
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode and listen to {@link #RINGER_MODE_CHANGED_ACTION} instead.
     */
    @Deprecated
    public static final String EXTRA_VIBRATE_SETTING = "android.media.EXTRA_VIBRATE_SETTING";

    /**
     * The vibrate type whose setting has changed.
     *
     * @see #VIBRATE_SETTING_CHANGED_ACTION
     * @see #VIBRATE_TYPE_NOTIFICATION
     * @see #VIBRATE_TYPE_RINGER
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode and listen to {@link #RINGER_MODE_CHANGED_ACTION} instead.
     */
    @Deprecated
    public static final String EXTRA_VIBRATE_TYPE = "android.media.EXTRA_VIBRATE_TYPE";

    /**
     * Whether to include ringer modes as possible options when changing volume.
     * For example, if true and volume level is 0 and the volume is adjusted
     * with {@link #ADJUST_LOWER}, then the ringer mode may switch the silent or
     * vibrate mode.
     * <p>
     * By default this is on for the ring stream. If this flag is included,
     * this behavior will be present regardless of the stream type being
     * affected by the ringer mode.
     *
     * @see #adjustVolume(int, int)
     * @see #adjustStreamVolume(int, int, int)
     */
    public static final int FLAG_ALLOW_RINGER_MODES = 2;

    /**
     * Whether to play a sound when changing the volume.
     * <p>
     * If this is given to {@link #adjustVolume(int, int)} or
     * {@link #adjustSuggestedStreamVolume(int, int, int)}, it may be ignored
     * in some cases (for example, the decided stream type is not
     * {@link AudioManager#STREAM_RING}, or the volume is being adjusted
     * downward).
     *
     * @see #adjustStreamVolume(int, int, int)
     * @see #adjustVolume(int, int)
     * @see #setStreamVolume(int, int, int)
     */
    public static final int FLAG_PLAY_SOUND = 4;

    /**
     * Removes any sounds/vibrate that may be in the queue, or are playing (related to
     * changing volume).
     */
    public static final int FLAG_REMOVE_SOUND_AND_VIBRATE = 8;

    /**
     * Show a toast containing the current volume.
     *
     * @see #adjustStreamVolume(int, int, int)
     * @see #adjustVolume(int, int)
     * @see #setStreamVolume(int, int, int)
     * @see #setRingerMode(int)
     */
    public static final int FLAG_SHOW_UI = 1;

    /**
     * Whether to vibrate if going into the vibrate ringer mode.
     */
    public static final int FLAG_VIBRATE = 16;

    /**
     * Back sound
     * @see #playSoundEffect(int)
     */
    public static final int FX_BACK = 10;

    /**
     * Focus has moved down
     * @see #playSoundEffect(int)
     */
    public static final int FX_FOCUS_NAVIGATION_DOWN = 2;

    /**
     * Focus has moved left
     * @see #playSoundEffect(int)
     */
    public static final int FX_FOCUS_NAVIGATION_LEFT = 3;

    /**
     * Focus has moved right
     * @see #playSoundEffect(int)
     */
    public static final int FX_FOCUS_NAVIGATION_RIGHT = 4;

    /**
     * Focus has moved up
     * @see #playSoundEffect(int)
     */
    public static final int FX_FOCUS_NAVIGATION_UP = 1;

    /**
     * IME delete keypress sound
     * @see #playSoundEffect(int)
     */
    public static final int FX_KEYPRESS_DELETE = 7;

    /**
     * Invalid keypress sound
     * @see #playSoundEffect(int)
     */
    public static final int FX_KEYPRESS_INVALID = 9;

    /**
     * IME return_keypress sound
     * @see #playSoundEffect(int)
     */
    public static final int FX_KEYPRESS_RETURN = 8;

    /**
     * IME spacebar keypress sound
     * @see #playSoundEffect(int)
     */
    public static final int FX_KEYPRESS_SPACEBAR = 6;

    /**
     * IME standard keypress sound
     * @see #playSoundEffect(int)
     */
    public static final int FX_KEYPRESS_STANDARD = 5;

    /**
     * Keyboard and direction pad click sound
     * @see #playSoundEffect(int)
     */
    public static final int FX_KEY_CLICK = 0;

    /**
     * Specifies to the {@link AudioManager#getDevices(int)} method to include both
     * source and sink devices.
     */
    public static final int GET_DEVICES_ALL = 3;

    /**
     * Specifies to the {@link AudioManager#getDevices(int)} method to include
     * source (i.e. input) audio devices.
     */
    public static final int GET_DEVICES_INPUTS = 1;

    /**
     * Specifies to the {@link AudioManager#getDevices(int)} method to include
     * sink (i.e. output) audio devices.
     */
    public static final int GET_DEVICES_OUTPUTS = 2;

    /**
     * A telephony call is established and its audio is being redirected to another device.
     */
    public static final int MODE_CALL_REDIRECT = 5;

    /**
     * Call screening in progress. Call is connected and audio is accessible to call
     * screening applications but other audio use cases are still possible.
     */
    public static final int MODE_CALL_SCREENING = 4;

    /**
     * An audio/video chat or VoIP call is established and its audio is being redirected to another
     * device.
     */
    public static final int MODE_COMMUNICATION_REDIRECT = 6;

    /**
     * Current audio mode. Used to apply audio routing to current mode.
     */
    public static final int MODE_CURRENT = -1;

    /**
     * Invalid audio mode.
     */
    public static final int MODE_INVALID = -2;

    /**
     * In call audio mode. A telephony call is established.
     */
    public static final int MODE_IN_CALL = 2;

    /**
     * In communication audio mode. An audio/video chat or VoIP call is established.
     */
    public static final int MODE_IN_COMMUNICATION = 3;

    /**
     * Normal audio mode: not ringing and no call established.
     */
    public static final int MODE_NORMAL = 0;

    /**
     * Ringing audio mode. An incoming is being signaled.
     */
    public static final int MODE_RINGTONE = 1;

    /**
     * @deprecated Do not iterate on volume stream type values.
     */
    @Deprecated
    public static final int NUM_STREAMS = 5;

    /**
     * Return value for {@link #getPlaybackOffloadSupport(AudioFormat, AudioAttributes)}:
     *         offload playback supported with gapless transitions
     */
    public static final int PLAYBACK_OFFLOAD_GAPLESS_SUPPORTED = 2;

    /**
     * Return value for {@link #getPlaybackOffloadSupport(AudioFormat, AudioAttributes)}:
     *         offload playback not supported
     */
    public static final int PLAYBACK_OFFLOAD_NOT_SUPPORTED = 0;

    /**
     * Return value for {@link #getPlaybackOffloadSupport(AudioFormat, AudioAttributes)}:
     *         offload playback supported
     */
    public static final int PLAYBACK_OFFLOAD_SUPPORTED = 1;

    /**
     * Used as a key for {@link #getProperty} to request the native or optimal output buffer size
     * for this device's low latency output stream, in decimal PCM frames.  Latency-sensitive apps
     * should use this value as a minimum, and offer the user the option to override it.
     * The low latency output stream is typically either the device's primary output stream,
     * or another output stream with smaller buffers.
     */
    public static final String PROPERTY_OUTPUT_FRAMES_PER_BUFFER = "android.media.property.OUTPUT_FRAMES_PER_BUFFER";

    /**
     * Used as a key for {@link #getProperty} to request the native or optimal output sample rate
     * for this device's low latency output stream, in decimal Hz.  Latency-sensitive apps
     * should use this value as a default, and offer the user the option to override it.
     * The low latency output stream is typically either the device's primary output stream,
     * or another output stream with smaller buffers.
     */
    public static final String PROPERTY_OUTPUT_SAMPLE_RATE = "android.media.property.OUTPUT_SAMPLE_RATE";

    /**
     * Used as a key for {@link #getProperty} to determine if the unprocessed audio source is
     * available and supported with the expected frequency range and level response.
     */
    public static final String PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED = "android.media.property.SUPPORT_AUDIO_SOURCE_UNPROCESSED";

    /**
     * Used as a key for {@link #getProperty} to determine if the default microphone audio source
     * supports near-ultrasound frequencies (range of 18 - 21 kHz).
     */
    public static final String PROPERTY_SUPPORT_MIC_NEAR_ULTRASOUND = "android.media.property.SUPPORT_MIC_NEAR_ULTRASOUND";

    /**
     * Used as a key for {@link #getProperty} to determine if the default speaker audio path
     * supports near-ultrasound frequencies (range of 18 - 21 kHz).
     */
    public static final String PROPERTY_SUPPORT_SPEAKER_NEAR_ULTRASOUND = "android.media.property.SUPPORT_SPEAKER_NEAR_ULTRASOUND";

    /**
     * Sticky broadcast intent action indicating that the ringer mode has
     * changed. Includes the new ringer mode.
     *
     * @see #EXTRA_RINGER_MODE
     */
    public static final String RINGER_MODE_CHANGED_ACTION = "android.media.RINGER_MODE_CHANGED";

    /**
     * Ringer mode that may be audible and may vibrate. It will be audible if
     * the volume before changing out of this mode was audible. It will vibrate
     * if the vibrate setting is on.
     *
     * @see #setRingerMode(int)
     * @see #getRingerMode()
     */
    public static final int RINGER_MODE_NORMAL = 2;

    /**
     * Ringer mode that will be silent and will not vibrate. (This overrides the
     * vibrate setting.)
     *
     * @see #setRingerMode(int)
     * @see #getRingerMode()
     */
    public static final int RINGER_MODE_SILENT = 0;

    /**
     * Ringer mode that will be silent and will vibrate. (This will cause the
     * phone ringer to always vibrate, but the notification vibrate to only
     * vibrate if set.)
     *
     * @see #setRingerMode(int)
     * @see #getRingerMode()
     */
    public static final int RINGER_MODE_VIBRATE = 1;

    /**
     * Used for mask parameter of {@link #setRouting(int,int,int)}.
     * @deprecated   Do not set audio routing directly, use setSpeakerphoneOn(),
     * setBluetoothScoOn() methods instead.
     */
    @Deprecated
    public static final int ROUTE_ALL = -1;

    /**
     * @deprecated use {@link #ROUTE_BLUETOOTH_SCO}
     * @deprecated   Do not set audio routing directly, use setSpeakerphoneOn(),
     * setBluetoothScoOn() methods instead.
     */
    @Deprecated
    public static final int ROUTE_BLUETOOTH = 4;

    /**
     * Routing audio output to bluetooth A2DP
     * @deprecated   Do not set audio routing directly, use setSpeakerphoneOn(),
     * setBluetoothScoOn() methods instead.
     */
    @Deprecated
    public static final int ROUTE_BLUETOOTH_A2DP = 16;

    /**
     * Routing audio output to bluetooth SCO
     * @deprecated   Do not set audio routing directly, use setSpeakerphoneOn(),
     * setBluetoothScoOn() methods instead.
     */
    @Deprecated
    public static final int ROUTE_BLUETOOTH_SCO = 4;

    /**
     * Routing audio output to earpiece
     * @deprecated   Do not set audio routing directly, use setSpeakerphoneOn(),
     * setBluetoothScoOn() methods instead.
     */
    @Deprecated
    public static final int ROUTE_EARPIECE = 1;

    /**
     * Routing audio output to headset
     * @deprecated   Do not set audio routing directly, use setSpeakerphoneOn(),
     * setBluetoothScoOn() methods instead.
     */
    @Deprecated
    public static final int ROUTE_HEADSET = 8;

    /**
     * Routing audio output to speaker
     * @deprecated   Do not set audio routing directly, use setSpeakerphoneOn(),
     * setBluetoothScoOn() methods instead.
     */
    @Deprecated
    public static final int ROUTE_SPEAKER = 2;

    /**
     * Value for extra {@link #EXTRA_SCO_AUDIO_STATE} or {@link #EXTRA_SCO_AUDIO_PREVIOUS_STATE}
     * indicating that the SCO audio channel is established
     */
    public static final int SCO_AUDIO_STATE_CONNECTED = 1;

    /**
     * Value for extra EXTRA_SCO_AUDIO_STATE or EXTRA_SCO_AUDIO_PREVIOUS_STATE
     * indicating that the SCO audio channel is being established
     */
    public static final int SCO_AUDIO_STATE_CONNECTING = 2;

    /**
     * Value for extra EXTRA_SCO_AUDIO_STATE or EXTRA_SCO_AUDIO_PREVIOUS_STATE
     * indicating that the SCO audio channel is not established
     */
    public static final int SCO_AUDIO_STATE_DISCONNECTED = 0;

    /**
     * Value for extra EXTRA_SCO_AUDIO_STATE indicating that
     * there was an error trying to obtain the state
     */
    public static final int SCO_AUDIO_STATE_ERROR = -1;

    /**
     * Used to identify the volume of audio streams for accessibility prompts
     */
    public static final int STREAM_ACCESSIBILITY = 10;

    /**
     * Used to identify the volume of audio streams for alarms
     */
    public static final int STREAM_ALARM = 4;

    /**
     * Used to identify the volume of audio streams for DTMF Tones
     */
    public static final int STREAM_DTMF = 8;

    /**
     * Used to identify the volume of audio streams for music playback
     */
    public static final int STREAM_MUSIC = 3;

    /**
     * Used to identify the volume of audio streams for notifications
     */
    public static final int STREAM_NOTIFICATION = 5;

    /**
     * Used to identify the volume of audio streams for the phone ring
     */
    public static final int STREAM_RING = 2;

    /**
     * Used to identify the volume of audio streams for system sounds
     */
    public static final int STREAM_SYSTEM = 1;

    /**
     * Used to identify the volume of audio streams for phone calls
     */
    public static final int STREAM_VOICE_CALL = 0;

    /**
     * Suggests using the default stream type. This may not be used in all
     * places a stream type is needed.
     */
    public static final int USE_DEFAULT_STREAM_TYPE = -2147483648;

    /**
     * Broadcast intent action indicating that the vibrate setting has
     * changed. Includes the vibrate type and its new setting.
     *
     * @see #EXTRA_VIBRATE_TYPE
     * @see #EXTRA_VIBRATE_SETTING
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode and listen to {@link #RINGER_MODE_CHANGED_ACTION} instead.
     */
    @Deprecated
    public static final String VIBRATE_SETTING_CHANGED_ACTION = "android.media.VIBRATE_SETTING_CHANGED";

    /**
     * Vibrate setting that suggests to never vibrate.
     *
     * @see #setVibrateSetting(int, int)
     * @see #getVibrateSetting(int)
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode that can be queried via {@link #getRingerMode()}.
     */
    @Deprecated
    public static final int VIBRATE_SETTING_OFF = 0;

    /**
     * Vibrate setting that suggests to vibrate when possible.
     *
     * @see #setVibrateSetting(int, int)
     * @see #getVibrateSetting(int)
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode that can be queried via {@link #getRingerMode()}.
     */
    @Deprecated
    public static final int VIBRATE_SETTING_ON = 1;

    /**
     * Vibrate setting that suggests to only vibrate when in the vibrate ringer
     * mode.
     *
     * @see #setVibrateSetting(int, int)
     * @see #getVibrateSetting(int)
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode that can be queried via {@link #getRingerMode()}.
     */
    @Deprecated
    public static final int VIBRATE_SETTING_ONLY_SILENT = 2;

    /**
     * Vibrate type that corresponds to notifications.
     *
     * @see #setVibrateSetting(int, int)
     * @see #getVibrateSetting(int)
     * @see #shouldVibrate(int)
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode that can be queried via {@link #getRingerMode()}.
     */
    @Deprecated
    public static final int VIBRATE_TYPE_NOTIFICATION = 1;

    /**
     * Vibrate type that corresponds to the ringer.
     *
     * @see #setVibrateSetting(int, int)
     * @see #getVibrateSetting(int)
     * @see #shouldVibrate(int)
     * @deprecated Applications should maintain their own vibrate policy based on
     * current ringer mode that can be queried via {@link #getRingerMode()}.
     */
    @Deprecated
    public static final int VIBRATE_TYPE_RINGER = 0;

    private AudioManager() {
    }
}
