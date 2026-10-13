package android.media;

/**
 * Used to record audio and video. The recording control is based on a
 * simple state machine (see below).
 *
 * <p><img src="{@docRoot}images/mediarecorder_state_diagram.gif" border="0" />
 * </p>
 *
 * <p>A common case of using MediaRecorder to record audio works as follows:
 *
 * <pre>
 * MediaRecorder recorder = new MediaRecorder(context);
 * recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
 * recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
 * recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
 * recorder.setOutputFile(PATH_NAME);
 * recorder.prepare();
 * recorder.start();   // Recording is now started
 * ...
 * recorder.stop();
 * recorder.reset();   // You can reuse the object by going back to setAudioSource() step
 * recorder.release(); // Now the object cannot be reused
 * </pre>
 *
 * <p>Applications may want to register for informational and error
 * events in order to be informed of some internal update and possible
 * runtime errors during recording. Registration for such events is
 * done by setting the appropriate listeners (via calls
 * (to {@link #setOnInfoListener(OnInfoListener)}setOnInfoListener and/or
 * {@link #setOnErrorListener(OnErrorListener)}setOnErrorListener).
 * In order to receive the respective callback associated with these listeners,
 * applications are required to create MediaRecorder objects on threads with a
 * Looper running (the main UI thread by default already has a Looper running).
 *
 * <p><strong>Note:</strong> Currently, MediaRecorder does not work on the emulator.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about how to use MediaRecorder for recording video, read the
 * <a href="{@docRoot}guide/topics/media/camera.html#capture-video">Camera</a> developer guide.
 * For more information about how to use MediaRecorder for recording sound, read the
 * <a href="{@docRoot}guide/topics/media/audio-capture.html">Audio Capture</a> developer guide.</p>
 * </div>
 */
public final class MediaRecorder implements MicrophoneDirection {
    /**
     * Media server died. In this case, the application must release the
     *  MediaRecorder object and instantiate a new one.
     *  @see android.media.MediaRecorder.OnErrorListener
     */
    public static final int MEDIA_ERROR_SERVER_DIED = 100;

    /**
     * Unspecified media recorder error.
     *  @see android.media.MediaRecorder.OnErrorListener
     */
    public static final int MEDIA_RECORDER_ERROR_UNKNOWN = 1;

    /**
     * A maximum duration had been setup and has now been reached.
     *  @see android.media.MediaRecorder.OnInfoListener
     */
    public static final int MEDIA_RECORDER_INFO_MAX_DURATION_REACHED = 800;

    /**
     * A maximum filesize had been setup and current recorded file size
     *  has reached 90% of the limit. This is sent once per file upon
     *  reaching/passing the 90% limit. To continue the recording, applicaiton
     *  should use {@link #setNextOutputFile} to set the next output file.
     *  Otherwise, recording will stop when reaching maximum file size.
     *  @see android.media.MediaRecorder.OnInfoListener
     */
    public static final int MEDIA_RECORDER_INFO_MAX_FILESIZE_APPROACHING = 802;

    /**
     * A maximum filesize had been setup and has now been reached.
     *  Note: This event will not be sent if application already set
     *  next output file through {@link #setNextOutputFile}.
     *  @see android.media.MediaRecorder.OnInfoListener
     */
    public static final int MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED = 801;

    /**
     * A maximum filesize had been reached and MediaRecorder has switched
     *  output to a new file set by application {@link #setNextOutputFile}.
     *  For best practice, application should use this event to keep track
     *  of whether the file previously set has been used or not.
     *  @see android.media.MediaRecorder.OnInfoListener
     */
    public static final int MEDIA_RECORDER_INFO_NEXT_OUTPUT_FILE_STARTED = 803;

    /**
     * Unspecified media recorder info.
     *  @see android.media.MediaRecorder.OnInfoListener
     */
    public static final int MEDIA_RECORDER_INFO_UNKNOWN = 1;

    private MediaRecorder() {
    }

    /**
     * Defines the audio encoding. These constants are used with
     * {@link MediaRecorder#setAudioEncoder(int)}.
     */
    public static final class AudioEncoder {
        /**
         * AAC Low Complexity (AAC-LC) audio codec
         */
        public static final int AAC = 3;

        /**
         * Enhanced Low Delay AAC (AAC-ELD) audio codec
         */
        public static final int AAC_ELD = 5;

        /**
         * AMR (Narrowband) audio codec
         */
        public static final int AMR_NB = 1;

        /**
         * AMR (Wideband) audio codec
         */
        public static final int AMR_WB = 2;

        public static final int DEFAULT = 0;

        /**
         * High Efficiency AAC (HE-AAC) audio codec
         */
        public static final int HE_AAC = 4;

        /**
         * Opus audio codec
         */
        public static final int OPUS = 7;

        /**
         * Ogg Vorbis audio codec (Support is optional)
         */
        public static final int VORBIS = 6;

        private AudioEncoder() {
        }
    }

    /**
     * Defines the audio source.
     * An audio source defines both a default physical source of audio signal, and a recording
     * configuration. These constants are for instance used
     * in {@link MediaRecorder#setAudioSource(int)} or
     * {@link AudioRecord.Builder#setAudioSource(int)}.
     */
    public static final class AudioSource {
        /**
         * Microphone audio source tuned for video recording, with the same orientation
         *   as the camera if available.
         */
        public static final int CAMCORDER = 5;

        /**
         * Default audio source
         */
        public static final int DEFAULT = 0;

        /**
         * Microphone audio source
         */
        public static final int MIC = 1;

        /**
         * Audio source for a submix of audio streams to be presented remotely.
         * <p>
         * An application can use this audio source to capture a mix of audio streams
         * that should be transmitted to a remote receiver such as a Wifi display.
         * While recording is active, these audio streams are redirected to the remote
         * submix instead of being played on the device speaker or headset.
         * </p><p>
         * Certain streams are excluded from the remote submix, including
         * {@link AudioManager#STREAM_RING}, {@link AudioManager#STREAM_ALARM},
         * and {@link AudioManager#STREAM_NOTIFICATION}.  These streams will continue
         * to be presented locally as usual.
         * </p><p>
         * Capturing the remote submix audio requires the
         * {@link android.Manifest.permission#CAPTURE_AUDIO_OUTPUT} permission.
         * This permission is reserved for use by system components and is not available to
         * third-party applications.
         * </p>
         */
        public static final int REMOTE_SUBMIX = 8;

        /**
         * Microphone audio source tuned for unprocessed (raw) sound if available, behaves like
         *   {@link #DEFAULT} otherwise.
         */
        public static final int UNPROCESSED = 9;

        /**
         * Voice call uplink + downlink audio source
         *  <p>
         *  Capturing from <code>VOICE_CALL</code> source requires the
         *  {@link android.Manifest.permission#CAPTURE_AUDIO_OUTPUT} permission.
         *  This permission is reserved for use by system components and is not available to
         *  third-party applications.
         *  </p>
         */
        public static final int VOICE_CALL = 4;

        /**
         * Microphone audio source tuned for voice communications such as VoIP. It
         *   will for instance take advantage of echo cancellation or automatic gain control
         *   if available.
         */
        public static final int VOICE_COMMUNICATION = 7;

        /**
         * Voice call downlink (Rx) audio source.
         *  <p>
         *  Capturing from <code>VOICE_DOWNLINK</code> source requires the
         *  {@link android.Manifest.permission#CAPTURE_AUDIO_OUTPUT} permission.
         *  This permission is reserved for use by system components and is not available to
         *  third-party applications.
         *  </p>
         */
        public static final int VOICE_DOWNLINK = 3;

        /**
         * Source for capturing audio meant to be processed in real time and played back for live
         * performance (e.g karaoke).
         * <p>
         * The capture path will minimize latency and coupling with
         * playback path.
         * </p>
         */
        public static final int VOICE_PERFORMANCE = 10;

        /**
         * Microphone audio source tuned for voice recognition.
         */
        public static final int VOICE_RECOGNITION = 6;

        /**
         * Voice call uplink (Tx) audio source.
         *  <p>
         *  Capturing from <code>VOICE_UPLINK</code> source requires the
         *  {@link android.Manifest.permission#CAPTURE_AUDIO_OUTPUT} permission.
         *  This permission is reserved for use by system components and is not available to
         *  third-party applications.
         *  </p>
         */
        public static final int VOICE_UPLINK = 2;

        private AudioSource() {
        }
    }

    public static final class MetricsConstants {
        /**
         * Key to extract the audio bitrate
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String AUDIO_BITRATE = "android.media.mediarecorder.audio-bitrate";

        /**
         * Key to extract the number of audio channels
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String AUDIO_CHANNELS = "android.media.mediarecorder.audio-channels";

        /**
         * Key to extract the audio samplerate
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String AUDIO_SAMPLERATE = "android.media.mediarecorder.audio-samplerate";

        /**
         * Key to extract the audio timescale
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String AUDIO_TIMESCALE = "android.media.mediarecorder.audio-timescale";

        /**
         * Key to extract the video capture frame rate
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is a double.
         */
        public static final String CAPTURE_FPS = "android.media.mediarecorder.capture-fps";

        /**
         * Key to extract the video capture framerate enable value
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String CAPTURE_FPS_ENABLE = "android.media.mediarecorder.capture-fpsenable";

        /**
         * Key to extract the intended playback frame rate
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String FRAMERATE = "android.media.mediarecorder.frame-rate";

        /**
         * Key to extract the height (in pixels) of the captured video
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String HEIGHT = "android.media.mediarecorder.height";

        /**
         * Key to extract the recorded movies time units
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         * A value of 1000 indicates that the movie's timing is in milliseconds.
         */
        public static final String MOVIE_TIMESCALE = "android.media.mediarecorder.movie-timescale";

        /**
         * Key to extract the rotation (in degrees) to properly orient the video
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String ROTATION = "android.media.mediarecorder.rotation";

        /**
         * Key to extract the video bitrate from being used
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String VIDEO_BITRATE = "android.media.mediarecorder.video-bitrate";

        /**
         * Key to extract the value for how often video iframes are generated
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String VIDEO_IFRAME_INTERVAL = "android.media.mediarecorder.video-iframe-interval";

        /**
         * Key to extract the video encoding level
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String VIDEO_LEVEL = "android.media.mediarecorder.video-encoder-level";

        /**
         * Key to extract the video encoding profile
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String VIDEO_PROFILE = "android.media.mediarecorder.video-encoder-profile";

        /**
         * Key to extract the recorded video time units
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         * A value of 1000 indicates that the video's timing is in milliseconds.
         */
        public static final String VIDEO_TIMESCALE = "android.media.mediarecorder.video-timescale";

        /**
         * Key to extract the width (in pixels) of the captured video
         * from the {@link MediaRecorder#getMetrics} return.
         * The value is an integer.
         */
        public static final String WIDTH = "android.media.mediarecorder.width";

        private MetricsConstants() {
        }
    }

    /**
     * Defines the output format. These constants are used with
     * {@link MediaRecorder#setOutputFormat(int)}.
     */
    public static final class OutputFormat {
        /**
         * AAC ADTS file format
         */
        public static final int AAC_ADTS = 6;

        /**
         * AMR NB file format
         */
        public static final int AMR_NB = 3;

        /**
         * AMR WB file format
         */
        public static final int AMR_WB = 4;

        public static final int DEFAULT = 0;

        /**
         * H.264/AAC data encapsulated in MPEG2/TS
         */
        public static final int MPEG_2_TS = 8;

        /**
         * MPEG4 media file format
         */
        public static final int MPEG_4 = 2;

        /**
         * Opus data in a Ogg container
         */
        public static final int OGG = 11;

        /**
         * AMR NB file format
         * @deprecated  Deprecated in favor of MediaRecorder.OutputFormat.AMR_NB
         */
        @Deprecated
        public static final int RAW_AMR = 3;

        /**
         * 3GPP media file format
         */
        public static final int THREE_GPP = 1;

        /**
         * VP8/VORBIS data in a WEBM container
         */
        public static final int WEBM = 9;

        private OutputFormat() {
        }
    }

    /**
     * Defines the video encoding. These constants are used with
     * {@link MediaRecorder#setVideoEncoder(int)}.
     */
    public static final class VideoEncoder {
        public static final int AV1 = 8;

        public static final int DEFAULT = 0;

        public static final int DOLBY_VISION = 7;

        public static final int H263 = 1;

        public static final int H264 = 2;

        public static final int HEVC = 5;

        public static final int MPEG_4_SP = 3;

        public static final int VP8 = 4;

        public static final int VP9 = 6;

        private VideoEncoder() {
        }
    }

    /**
     * Defines the video source. These constants are used with
     * {@link MediaRecorder#setVideoSource(int)}.
     */
    public static final class VideoSource {
        /**
         * Camera video source
         *  <p>
         *  Using the {@link android.hardware.Camera} API as video source.
         *  </p>
         */
        public static final int CAMERA = 1;

        public static final int DEFAULT = 0;

        /**
         * Surface video source
         *  <p>
         *  Using a Surface as video source.
         *  </p><p>
         *  This flag must be used when recording from an
         *  {@link android.hardware.camera2} API source.
         *  </p><p>
         *  When using this video source type, use {@link MediaRecorder#getSurface()}
         *  to retrieve the surface created by MediaRecorder.
         */
        public static final int SURFACE = 2;

        private VideoSource() {
        }
    }
}
