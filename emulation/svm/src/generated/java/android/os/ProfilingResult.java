package android.os;

/**
 * Encapsulates results of a single profiling request operation.
 */
public final class ProfilingResult implements Parcelable {
    /**
     * The request was executed and failed for a reason not specified below.
     */
    public static final int ERROR_FAILED_EXECUTING = 4;

    /**
     * The request failed due to invalid ProfilingRequest.
     */
    public static final int ERROR_FAILED_INVALID_REQUEST = 7;

    /**
     * The request was executed and failed due to a lack of disk space.
     */
    public static final int ERROR_FAILED_NO_DISK_SPACE = 6;

    /**
     * The request was executed but post processing failed and the result was discarded.
     */
    public static final int ERROR_FAILED_POST_PROCESSING = 5;

    /**
     * The request was denied due to profiling already in progress.
     */
    public static final int ERROR_FAILED_PROFILING_IN_PROGRESS = 3;

    /**
     * The request was denied due to process level rate limiting.
     */
    public static final int ERROR_FAILED_RATE_LIMIT_PROCESS = 2;

    /**
     * The request was denied due to system level rate limiting.
     */
    public static final int ERROR_FAILED_RATE_LIMIT_SYSTEM = 1;

    /**
     * The request was executed and succeeded.
     */
    public static final int ERROR_NONE = 0;

    /**
     * The request was denied or failed for an unspecified reason.
     */
    public static final int ERROR_UNKNOWN = 8;

    private ProfilingResult() {
    }
}
