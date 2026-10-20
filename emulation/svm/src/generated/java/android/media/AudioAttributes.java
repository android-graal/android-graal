package android.media;

import android.os.Parcelable;

/**
 * A class to encapsulate a collection of attributes describing information about an audio
 * stream.
 * <p><code>AudioAttributes</code> supersede the notion of stream types (see for instance
 * {@link AudioManager#STREAM_MUSIC} or {@link AudioManager#STREAM_ALARM}) for defining the
 * behavior of audio playback. Attributes allow an application to specify more information than is
 * conveyed in a stream type by allowing the application to define:
 * <ul>
 * <li>usage: "why" you are playing a sound, what is this sound used for. This is achieved with
 *     the "usage" information. Examples of usage are {@link #USAGE_MEDIA} and {@link #USAGE_ALARM}.
 *     These two examples are the closest to stream types, but more detailed use cases are
 *     available. Usage information is more expressive than a stream type, and allows certain
 *     platforms or routing policies to use this information for more refined volume or routing
 *     decisions. Usage is the most important information to supply in <code>AudioAttributes</code>
 *     and it is recommended to build any instance with this information supplied, see
 *     {@link AudioAttributes.Builder} for exceptions.</li>
 * <li>content type: "what" you are playing. The content type expresses the general category of
 *     the content. This information is optional. But in case it is known (for instance
 *     {@link #CONTENT_TYPE_MOVIE} for a movie streaming service or {@link #CONTENT_TYPE_MUSIC} for
 *     a music playback application) this information might be used by the audio framework to
 *     selectively configure some audio post-processing blocks.</li>
 * <li>flags: "how" is playback to be affected, see the flag definitions for the specific playback
 *     behaviors they control. </li>
 * </ul>
 * <p><code>AudioAttributes</code> are used for example in one of the {@link AudioTrack}
 * constructors (see {@link AudioTrack#AudioTrack(AudioAttributes, AudioFormat, int, int, int)}),
 * to configure a {@link MediaPlayer}
 * (see {@link MediaPlayer#setAudioAttributes(AudioAttributes)} or a
 * {@link android.app.Notification} (see {@link android.app.Notification#audioAttributes}). An
 * <code>AudioAttributes</code> instance is built through its builder,
 * {@link AudioAttributes.Builder}.
 */
public final class AudioAttributes implements Parcelable {
    /**
     * Indicates that the audio may be captured by any app.
     *
     * For privacy, the following usages cannot be recorded: VOICE_COMMUNICATION*,
     * USAGE_NOTIFICATION*, USAGE_ASSISTANCE* and USAGE_ASSISTANT.
     *
     * On {@link android.os.Build.VERSION_CODES#Q}, this means only {@link #USAGE_UNKNOWN},
     * {@link #USAGE_MEDIA} and {@link #USAGE_GAME} may be captured.
     *
     * See {@link android.media.projection.MediaProjection} and
     * {@link Builder#setAllowedCapturePolicy}.
     */
    public static final int ALLOW_CAPTURE_BY_ALL = 1;

    /**
     * Indicates that the audio is not to be recorded by any app, even if it is a system app.
     *
     * It is encouraged to use {@link #ALLOW_CAPTURE_BY_SYSTEM} instead of this value as system apps
     * provide significant and useful features for the user (such as live captioning
     * and accessibility).
     *
     * See {@link Builder#setAllowedCapturePolicy}.
     */
    public static final int ALLOW_CAPTURE_BY_NONE = 3;

    /**
     * Indicates that the audio may only be captured by system apps.
     *
     * System apps can capture for many purposes like accessibility, live captions, user guidance...
     * but abide to the following restrictions:
     *  - the audio cannot leave the device
     *  - the audio cannot be passed to a third party app
     *  - the audio cannot be recorded at a higher quality than 16kHz 16bit mono
     *
     * See {@link Builder#setAllowedCapturePolicy}.
     */
    public static final int ALLOW_CAPTURE_BY_SYSTEM = 2;

    /**
     * Content type value to use when the content type is a soundtrack, typically accompanying
     * a movie or TV program.
     */
    public static final int CONTENT_TYPE_MOVIE = 3;

    /**
     * Content type value to use when the content type is music.
     */
    public static final int CONTENT_TYPE_MUSIC = 2;

    /**
     * Content type value to use when the content type is a sound used to accompany a user
     * action, such as a beep or sound effect expressing a key click, or event, such as the
     * type of a sound for a bonus being received in a game. These sounds are mostly synthesized
     * or short Foley sounds.
     */
    public static final int CONTENT_TYPE_SONIFICATION = 4;

    /**
     * Content type value to use when the content type is speech.
     */
    public static final int CONTENT_TYPE_SPEECH = 1;

    /**
     * Content type value to use when the content type is unknown, or other than the ones defined.
     */
    public static final int CONTENT_TYPE_UNKNOWN = 0;

    /**
     * Flag defining a behavior where the audibility of the sound will be ensured by the system.
     * To ensure sound audibility, the system only uses built-in speakers or wired headphones
     * and specifically excludes wireless audio devices.
     * <p>Note this flag should only be used for sounds subject to regulatory behaviors in some
     * countries, such as for camera shutter sound, and not for routing behaviors.
     */
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;

    /**
     * Flag requesting the use of an output stream supporting hardware A/V synchronization.
     */
    public static final int FLAG_HW_AV_SYNC = 16;

    /**
     * Flag requesting a low latency path when creating an AudioTrack.
     * When using this flag, the sample rate must match the native sample rate
     * of the device. Effects processing is also unavailable.
     *
     * Note that if this flag is used without specifying a bufferSizeInBytes then the
     * AudioTrack's actual buffer size may be too small. It is recommended that a fairly
     * large buffer should be specified when the AudioTrack is created.
     * Then the actual size can be reduced by calling
     * {@link AudioTrack#setBufferSizeInFrames(int)}. The buffer size can be optimized
     * by lowering it after each write() call until the audio glitches, which is detected by calling
     * {@link AudioTrack#getUnderrunCount()}. Then the buffer size can be increased
     * until there are no glitches.
     * This tuning step should be done while playing silence.
     * This technique provides a compromise between latency and glitch rate.
     *
     * @deprecated Use {@link AudioTrack.Builder#setPerformanceMode(int)} with
     * {@link AudioTrack#PERFORMANCE_MODE_LOW_LATENCY} to control performance.
     */
    @Deprecated
    public static final int FLAG_LOW_LATENCY = 256;

    /**
     * Constant indicating the audio content associated with these attributes will follow the
     * default platform behavior with regards to which content will be spatialized or not.
     * @see #getSpatializationBehavior()
     * @see Spatializer
     */
    public static final int SPATIALIZATION_BEHAVIOR_AUTO = 0;

    /**
     * Constant indicating the audio content associated with these attributes should never
     * be virtualized.
     * @see #getSpatializationBehavior()
     * @see Spatializer
     */
    public static final int SPATIALIZATION_BEHAVIOR_NEVER = 1;

    /**
     * Usage value to use when the usage is an alarm (e.g. wake-up alarm).
     */
    public static final int USAGE_ALARM = 4;

    /**
     * Usage value to use when the usage is for accessibility, such as with
     * a screen reader.
     */
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;

    /**
     * Usage value to use when the usage is driving or navigation directions.
     */
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;

    /**
     * Usage value to use when the usage is sonification, such as  with user
     * interface sounds.
     */
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;

    /**
     * Usage value to use for audio responses to user queries, audio instructions or help
     * utterances.
     */
    public static final int USAGE_ASSISTANT = 16;

    /**
     * Usage value to use when the usage is for game audio.
     */
    public static final int USAGE_GAME = 14;

    /**
     * Usage value to use when the usage is media, such as music, or movie
     * soundtracks.
     */
    public static final int USAGE_MEDIA = 1;

    /**
     * Usage value to use when the usage is notification. See other
     * notification usages for more specialized uses.
     */
    public static final int USAGE_NOTIFICATION = 5;

    /**
     * Usage value to use when the usage is notification for a
     * non-immediate type of communication such as e-mail.
     * @deprecated Use {@link #USAGE_NOTIFICATION} which is handled
     *             the same way as this usage by the audio framework
     */
    @Deprecated
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;

    /**
     * Usage value to use when the usage is notification for an "instant"
     * communication such as a chat, or SMS.
     * @deprecated Use {@link #USAGE_NOTIFICATION} which is handled
     *             the same way as this usage by the audio framework
     */
    @Deprecated
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;

    /**
     * Usage value to use when the usage is a request to enter/end a
     * communication, such as a VoIP communication or video-conference.
     * @deprecated Use {@link #USAGE_NOTIFICATION} which is handled
     *             the same way as this usage by the audio framework
     */
    @Deprecated
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;

    /**
     * Usage value to use when the usage is to attract the user's attention,
     * such as a reminder or low battery warning.
     */
    public static final int USAGE_NOTIFICATION_EVENT = 10;

    /**
     * Usage value to use when the usage is telephony ringtone.
     */
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;

    /**
     * Usage value to use when the usage is unknown.
     */
    public static final int USAGE_UNKNOWN = 0;

    /**
     * Usage value to use when the usage is voice communications, such as telephony
     * or VoIP.
     */
    public static final int USAGE_VOICE_COMMUNICATION = 2;

    /**
     * Usage value to use when the usage is in-call signalling, such as with
     * a "busy" beep, or DTMF tones.
     */
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AudioAttributes() {
    }
}
