package android.media;

/**
 * The AudioTrack class manages and plays a single audio resource for Java applications.
 * It allows streaming of PCM audio buffers to the audio sink for playback. This is
 * achieved by "pushing" the data to the AudioTrack object using one of the
 *  {@link #write(byte[], int, int)}, {@link #write(short[], int, int)},
 *  and {@link #write(float[], int, int, int)} methods.
 *
 * <p>An AudioTrack instance can operate under two modes: static or streaming.<br>
 * In Streaming mode, the application writes a continuous stream of data to the AudioTrack, using
 * one of the {@code write()} methods. These are blocking and return when the data has been
 * transferred from the Java layer to the native layer and queued for playback. The streaming
 * mode is most useful when playing blocks of audio data that for instance are:
 *
 * <ul>
 *   <li>too big to fit in memory because of the duration of the sound to play,</li>
 *   <li>too big to fit in memory because of the characteristics of the audio data
 *         (high sampling rate, bits per sample ...)</li>
 *   <li>received or generated while previously queued audio is playing.</li>
 * </ul>
 *
 * The static mode should be chosen when dealing with short sounds that fit in memory and
 * that need to be played with the smallest latency possible. The static mode will
 * therefore be preferred for UI and game sounds that are played often, and with the
 * smallest overhead possible.
 *
 * <p>Upon creation, an AudioTrack object initializes its associated audio buffer.
 * The size of this buffer, specified during the construction, determines how long an AudioTrack
 * can play before running out of data.<br>
 * For an AudioTrack using the static mode, this size is the maximum size of the sound that can
 * be played from it.<br>
 * For the streaming mode, data will be written to the audio sink in chunks of
 * sizes less than or equal to the total buffer size.
 *
 * AudioTrack is not final and thus permits subclasses, but such use is not recommended.
 */
public final class AudioTrack {
    /**
     * This mode indicates that a stereo stream should be presented
     * with the left audio channel replicated into the right audio channel.
     *
     * Behavior for non-stereo streams is implementation defined.
     * A suggested guideline is that all channels with left-right
     * stereo symmetry will have the left channel position replicated
     * into the right channel position.
     * The center channels (with no left/right symmetry) or unbalanced
     * channels are left alone.
     *
     * The Dual Mono effect occurs before volume scaling.
     */
    public static final int DUAL_MONO_MODE_LL = 2;

    /**
     * This mode indicates that a stereo stream should be presented
     * with the left and right audio channels blended together
     * and delivered to both channels.
     *
     * Behavior for non-stereo streams is implementation defined.
     * A suggested guideline is that the left-right stereo symmetric
     * channels are pairwise blended;
     * the other channels such as center are left alone.
     *
     * The Dual Mono effect occurs before volume scaling.
     */
    public static final int DUAL_MONO_MODE_LR = 1;

    /**
     * This mode disables any Dual Mono presentation effect.
     */
    public static final int DUAL_MONO_MODE_OFF = 0;

    /**
     * This mode indicates that a stereo stream should be presented
     * with the right audio channel replicated into the left audio channel.
     *
     * Behavior for non-stereo streams is implementation defined.
     * A suggested guideline is that all channels with left-right
     * stereo symmetry will have the right channel position replicated
     * into the left channel position.
     * The center channels (with no left/right symmetry) or unbalanced
     * channels are left alone.
     *
     * The Dual Mono effect occurs before volume scaling.
     */
    public static final int DUAL_MONO_MODE_RR = 3;

    /**
     * Encapsulation metadata type for DVB AD descriptor.
     *
     * This metadata is formatted per ETSI TS 101 154 Table E.1: AD_descriptor.
     */
    public static final int ENCAPSULATION_METADATA_TYPE_DVB_AD_DESCRIPTOR = 2;

    /**
     * Encapsulation metadata type for framework tuner information.
     *
     * Refer to the Android Media TV Tuner API for details.
     */
    public static final int ENCAPSULATION_METADATA_TYPE_FRAMEWORK_TUNER = 1;

    /**
     * Encapsulation metadata type for placement of supplementary audio.
     *
     * A 32 bit integer constant, one of {@link #SUPPLEMENTARY_AUDIO_PLACEMENT_NORMAL}, {@link
     * #SUPPLEMENTARY_AUDIO_PLACEMENT_LEFT}, {@link #SUPPLEMENTARY_AUDIO_PLACEMENT_RIGHT}.
     */
    public static final int ENCAPSULATION_METADATA_TYPE_SUPPLEMENTARY_AUDIO_PLACEMENT = 3;

    /**
     * This mode indicates metadata encapsulation with an elementary stream payload.
     * Both compressed and PCM format is allowed.
     */
    public static final int ENCAPSULATION_MODE_ELEMENTARY_STREAM = 1;

    /**
     * This mode indicates no metadata encapsulation,
     * which is the default mode for sending audio data
     * through {@code AudioTrack}.
     */
    public static final int ENCAPSULATION_MODE_NONE = 0;

    /**
     * Denotes a generic operation failure.
     */
    public static final int ERROR = -1;

    /**
     * Denotes a failure due to the use of an invalid value.
     */
    public static final int ERROR_BAD_VALUE = -2;

    /**
     * An error code indicating that the object reporting it is no longer valid and needs to
     * be recreated.
     */
    public static final int ERROR_DEAD_OBJECT = -6;

    /**
     * Denotes a failure due to the improper use of a method.
     */
    public static final int ERROR_INVALID_OPERATION = -3;

    /**
     * Creation mode where audio data is transferred from Java to the native layer
     * only once before the audio starts playing.
     */
    public static final int MODE_STATIC = 0;

    /**
     * Creation mode where audio data is streamed from Java to the native layer
     * as the audio is playing.
     */
    public static final int MODE_STREAM = 1;

    /**
     * Low latency performance mode for an {@link AudioTrack}.
     * If the device supports it, this mode
     * enables a lower latency path through to the audio output sink.
     * Effects may no longer work with such an {@code AudioTrack} and
     * the sample rate must match that of the output sink.
     * <p>
     * Applications should be aware that low latency requires careful
     * buffer management, with smaller chunks of audio data written by each
     * {@code write()} call.
     * <p>
     * If this flag is used without specifying a {@code bufferSizeInBytes} then the
     * {@code AudioTrack}'s actual buffer size may be too small.
     * It is recommended that a fairly
     * large buffer should be specified when the {@code AudioTrack} is created.
     * Then the actual size can be reduced by calling
     * {@link #setBufferSizeInFrames(int)}. The buffer size can be optimized
     * by lowering it after each {@code write()} call until the audio glitches,
     * which is detected by calling
     * {@link #getUnderrunCount()}. Then the buffer size can be increased
     * until there are no glitches.
     * This tuning step should be done while playing silence.
     * This technique provides a compromise between latency and glitch rate.
     */
    public static final int PERFORMANCE_MODE_LOW_LATENCY = 1;

    /**
     * Default performance mode for an {@link AudioTrack}.
     */
    public static final int PERFORMANCE_MODE_NONE = 0;

    /**
     * Power saving performance mode for an {@link AudioTrack}.
     * If the device supports it, this
     * mode will enable a lower power path to the audio output sink.
     * In addition, this lower power path typically will have
     * deeper internal buffers and better underrun resistance,
     * with a tradeoff of higher latency.
     * <p>
     * In this mode, applications should attempt to use a larger buffer size
     * and deliver larger chunks of audio data per {@code write()} call.
     * Use {@link #getBufferSizeInFrames()} to determine
     * the actual buffer size of the {@code AudioTrack} as it may have increased
     * to accommodate a deeper buffer.
     */
    public static final int PERFORMANCE_MODE_POWER_SAVING = 2;

    /**
     * indicates AudioTrack state is paused
     */
    public static final int PLAYSTATE_PAUSED = 2;

    /**
     * indicates AudioTrack state is playing
     */
    public static final int PLAYSTATE_PLAYING = 3;

    /**
     * indicates AudioTrack state is stopped
     */
    public static final int PLAYSTATE_STOPPED = 1;

    /**
     * State of an AudioTrack that is ready to be used.
     */
    public static final int STATE_INITIALIZED = 1;

    /**
     * State of a successfully initialized AudioTrack that uses static data,
     * but that hasn't received that data yet.
     */
    public static final int STATE_NO_STATIC_DATA = 2;

    /**
     * State of an AudioTrack that was not successfully initialized upon creation.
     */
    public static final int STATE_UNINITIALIZED = 0;

    /**
     * Denotes a successful operation.
     */
    public static final int SUCCESS = 0;

    /**
     * Supplementary audio placement left.
     */
    public static final int SUPPLEMENTARY_AUDIO_PLACEMENT_LEFT = 1;

    /**
     * Supplementary audio placement normal.
     */
    public static final int SUPPLEMENTARY_AUDIO_PLACEMENT_NORMAL = 0;

    /**
     * Supplementary audio placement right.
     */
    public static final int SUPPLEMENTARY_AUDIO_PLACEMENT_RIGHT = 2;

    /**
     * The write mode indicating the write operation will block until all data has been written,
     * to be used as the actual value of the writeMode parameter in
     * {@link #write(byte[], int, int, int)}, {@link #write(short[], int, int, int)},
     * {@link #write(float[], int, int, int)}, {@link #write(ByteBuffer, int, int)}, and
     * {@link #write(ByteBuffer, int, int, long)}.
     */
    public static final int WRITE_BLOCKING = 0;

    /**
     * The write mode indicating the write operation will return immediately after
     * queuing as much audio data for playback as possible without blocking,
     * to be used as the actual value of the writeMode parameter in
     * {@link #write(ByteBuffer, int, int)}, {@link #write(short[], int, int, int)},
     * {@link #write(float[], int, int, int)}, {@link #write(ByteBuffer, int, int)}, and
     * {@link #write(ByteBuffer, int, int, long)}.
     */
    public static final int WRITE_NON_BLOCKING = 1;

    private AudioTrack() {
    }

    public static final class MetricsConstants {
        /**
         * Key to extract the native channel mask information for this track
         * from the {@link AudioTrack#getMetrics} return value.
         *
         * The value is a {@code long}.
         * @deprecated This does not work. Use {@link AudioTrack#getFormat()} and read from
         * the returned format instead.
         */
        @Deprecated
        public static final String CHANNELMASK = "android.media.audiorecord.channelmask";

        /**
         * Key to extract the attribute content type for this track
         * from the {@link AudioTrack#getMetrics} return value.
         * The value is a {@code String}.
         */
        public static final String CONTENTTYPE = "android.media.audiotrack.type";

        /**
         * Key to extract the sample rate for this track in Hz
         * from the {@link AudioTrack#getMetrics} return value.
         * The value is an {@code int}.
         * @deprecated This does not work. Use {@link AudioTrack#getSampleRate()} instead.
         */
        @Deprecated
        public static final String SAMPLERATE = "android.media.audiorecord.samplerate";

        /**
         * Key to extract the stream type for this track
         * from the {@link AudioTrack#getMetrics} return value.
         * This value may not exist in API level {@link android.os.Build.VERSION_CODES#P}.
         * The value is a {@code String}.
         */
        public static final String STREAMTYPE = "android.media.audiotrack.streamtype";

        /**
         * Key to extract the attribute usage for this track
         * from the {@link AudioTrack#getMetrics} return value.
         * The value is a {@code String}.
         */
        public static final String USAGE = "android.media.audiotrack.usage";

        private MetricsConstants() {
        }
    }
}
