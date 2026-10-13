package android.media;

/**
 * The AudioRecord class manages the audio resources for Java applications
 * to record audio from the audio input hardware of the platform. This is
 * achieved by "pulling" (reading) the data from the AudioRecord object. The
 * application is responsible for polling the AudioRecord object in time using one of
 * the following three methods:  {@link #read(byte[],int, int)}, {@link #read(short[], int, int)}
 * or {@link #read(ByteBuffer, int)}. The choice of which method to use will be based
 * on the audio data storage format that is the most convenient for the user of AudioRecord.
 * <p>Upon creation, an AudioRecord object initializes its associated audio buffer that it will
 * fill with the new audio data. The size of this buffer, specified during the construction,
 * determines how long an AudioRecord can record before "over-running" data that has not
 * been read yet. Data should be read from the audio hardware in chunks of sizes inferior to
 * the total recording buffer size.</p>
 * <p>
 * Applications creating an AudioRecord instance need
 * {@link android.Manifest.permission#RECORD_AUDIO} or the Builder will throw
 * {@link java.lang.UnsupportedOperationException} on
 * {@link android.media.AudioRecord.Builder#build build()},
 * and the constructor will return an instance in state
 * {@link #STATE_UNINITIALIZED}.</p>
 */
public final class AudioRecord implements MicrophoneDirection {
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
     * The read mode indicating the read operation will block until all data
     * requested has been read.
     */
    public static final int READ_BLOCKING = 0;

    /**
     * The read mode indicating the read operation will return immediately after
     * reading as much audio data as possible without blocking.
     */
    public static final int READ_NON_BLOCKING = 1;

    /**
     * indicates AudioRecord recording state is recording
     */
    public static final int RECORDSTATE_RECORDING = 3;

    /**
     * indicates AudioRecord recording state is not recording
     */
    public static final int RECORDSTATE_STOPPED = 1;

    /**
     *  indicates AudioRecord state is ready to be used
     */
    public static final int STATE_INITIALIZED = 1;

    /**
     *  indicates AudioRecord state is not successfully initialized.
     */
    public static final int STATE_UNINITIALIZED = 0;

    /**
     * Denotes a successful operation.
     */
    public static final int SUCCESS = 0;

    private AudioRecord() {
    }

    public static final class MetricsConstants {
        /**
         * Key to extract the number of channels being recorded in this record track
         * from the {@link AudioRecord#getMetrics} return value.
         * The value is an {@code int}.
         */
        public static final String CHANNELS = "android.media.audiorecord.channels";

        /**
         * Key to extract the audio data encoding for this track
         * from the {@link AudioRecord#getMetrics} return value.
         * The value is a {@code String}.
         */
        public static final String ENCODING = "android.media.audiorecord.encoding";

        /**
         * Key to extract the estimated latency through the recording pipeline
         * from the {@link AudioRecord#getMetrics} return value.
         * This is in units of milliseconds.
         * The value is an {@code int}.
         * @deprecated Not properly supported in the past.
         */
        @Deprecated
        public static final String LATENCY = "android.media.audiorecord.latency";

        /**
         * Key to extract the sink sample rate for this record track in Hz
         * from the {@link AudioRecord#getMetrics} return value.
         * The value is an {@code int}.
         */
        public static final String SAMPLERATE = "android.media.audiorecord.samplerate";

        /**
         * Key to extract the source type for this track
         * from the {@link AudioRecord#getMetrics} return value.
         * The value is a {@code String}.
         */
        public static final String SOURCE = "android.media.audiorecord.source";

        private MetricsConstants() {
        }
    }
}
