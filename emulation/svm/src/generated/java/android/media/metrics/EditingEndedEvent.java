package android.media.metrics;

import android.os.Parcelable;

/**
 * Event for an editing operation having ended.
 */
public final class EditingEndedEvent implements Parcelable {
    /**
     * Caused by an audio processing failure.
     */
    public static final int ERROR_CODE_AUDIO_PROCESSING_FAILED = 18;

    /**
     * Caused by a decoder initialization failure.
     */
    public static final int ERROR_CODE_DECODER_INIT_FAILED = 11;

    /**
     * Caused by a failure while trying to decode media samples.
     */
    public static final int ERROR_CODE_DECODING_FAILED = 12;

    /**
     * Caused by trying to decode content whose format is not supported.
     */
    public static final int ERROR_CODE_DECODING_FORMAT_UNSUPPORTED = 13;

    /**
     * Caused by an encoder initialization failure.
     */
    public static final int ERROR_CODE_ENCODER_INIT_FAILED = 14;

    /**
     * Caused by a failure while trying to encode media samples.
     */
    public static final int ERROR_CODE_ENCODING_FAILED = 15;

    /**
     * Caused by trying to encode content whose format is not supported.
     */
    public static final int ERROR_CODE_ENCODING_FORMAT_UNSUPPORTED = 16;

    /**
     * Error code for unexpected runtime errors.
     */
    public static final int ERROR_CODE_FAILED_RUNTIME_CHECK = 2;

    /**
     * Caused by an HTTP server returning an unexpected HTTP response status code.
     */
    public static final int ERROR_CODE_IO_BAD_HTTP_STATUS = 6;

    /**
     * Caused by failing to load data via cleartext HTTP, when the app's network security
     * configuration does not permit it.
     */
    public static final int ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED = 9;

    /**
     * Caused by a non-existent file.
     */
    public static final int ERROR_CODE_IO_FILE_NOT_FOUND = 7;

    /**
     * Error code for network connection failures.
     */
    public static final int ERROR_CODE_IO_NETWORK_CONNECTION_FAILED = 4;

    /**
     * Error code for network timeouts.
     */
    public static final int ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT = 5;

    /**
     * Caused by lack of permission to perform an IO operation. For example, lack of permission to
     * access internet or external storage.
     */
    public static final int ERROR_CODE_IO_NO_PERMISSION = 8;

    /**
     * Caused by reading data out of the data bounds.
     */
    public static final int ERROR_CODE_IO_READ_POSITION_OUT_OF_RANGE = 10;

    /**
     * Error code for non-specific errors during input/output.
     */
    public static final int ERROR_CODE_IO_UNSPECIFIED = 3;

    /**
     * Caused by a failure while muxing media samples.
     */
    public static final int ERROR_CODE_MUXING_FAILED = 19;

    /**
     * Special value representing that no error occurred.
     */
    public static final int ERROR_CODE_NONE = 1;

    /**
     * Caused by a video frame processing failure.
     */
    public static final int ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED = 17;

    /**
     * The editing operation was canceled.
     */
    public static final int FINAL_STATE_CANCELED = 2;

    /**
     * The editing operation failed due to an error.
     */
    public static final int FINAL_STATE_ERROR = 3;

    /**
     * The editing operation was successful.
     */
    public static final int FINAL_STATE_SUCCEEDED = 1;

    /**
     * Input audio was edited.
     */
    public static final long OPERATION_TYPE_AUDIO_EDIT = 8L;

    /**
     * Input audio was decoded and re-encoded.
     */
    public static final long OPERATION_TYPE_AUDIO_TRANSCODE = 2L;

    /**
     * Input audio samples were written (muxed) directly to the output file without transcoding.
     */
    public static final long OPERATION_TYPE_AUDIO_TRANSMUX = 32L;

    /**
     * The editing operation was paused before it completed.
     */
    public static final long OPERATION_TYPE_PAUSED = 64L;

    /**
     * The editing operation resumed a previous (paused) operation.
     */
    public static final long OPERATION_TYPE_RESUMED = 128L;

    /**
     * Input video was edited.
     */
    public static final long OPERATION_TYPE_VIDEO_EDIT = 4L;

    /**
     * Input video was decoded and re-encoded.
     */
    public static final long OPERATION_TYPE_VIDEO_TRANSCODE = 1L;

    /**
     * Input video samples were written (muxed) directly to the output file without transcoding.
     */
    public static final long OPERATION_TYPE_VIDEO_TRANSMUX = 16L;

    /**
     * Special value for unknown {@linkplain #getFinalProgressPercent() final progress}.
     */
    public static final int PROGRESS_PERCENT_UNKNOWN = -1;

    /**
     * Special value for unknown {@linkplain #getTimeSinceCreatedMillis() time since creation}.
     */
    public static final int TIME_SINCE_CREATED_UNKNOWN = -1;

    private EditingEndedEvent() {
    }
}
