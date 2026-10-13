package android.app.appsearch;

/**
 * Information about the success or failure of an AppSearch call.
 *
 * @param <ValueType> The type of result object for successful calls.
 */
public final class AppSearchResult {
    /**
     * The operation is invalid because the resource already exists and can't be replaced.
     */
    public static final int RESULT_ALREADY_EXISTS = 12;

    /**
     * The requested operation is denied for the caller. This error is logged and returned for
     * denylist rejections.
     */
    public static final int RESULT_DENIED = 9;

    /**
     * An internal error occurred within AppSearch, which the caller cannot address.
     *
     * <p>This error may be considered similar to {@link IllegalStateException}
     */
    public static final int RESULT_INTERNAL_ERROR = 2;

    /**
     * The caller supplied invalid arguments to the call.
     *
     * <p>This error may be considered similar to {@link IllegalArgumentException}.
     */
    public static final int RESULT_INVALID_ARGUMENT = 3;

    /**
     * The caller supplied a schema which is invalid or incompatible with the previous schema.
     */
    public static final int RESULT_INVALID_SCHEMA = 7;

    /**
     * An issue occurred reading or writing to storage. The call might succeed if repeated.
     *
     * <p>This error may be considered similar to {@link java.io.IOException}.
     */
    public static final int RESULT_IO_ERROR = 4;

    /**
     * An entity the caller requested to interact with does not exist in the system.
     */
    public static final int RESULT_NOT_FOUND = 6;

    /**
     * The call was successful.
     */
    public static final int RESULT_OK = 0;

    /**
     * Storage is out of space, and no more space could be reclaimed.
     */
    public static final int RESULT_OUT_OF_SPACE = 5;

    /**
     * The caller has hit AppSearch's rate limit and the requested operation has been rejected. The
     * caller is recommended to reschedule tasks with exponential backoff.
     */
    public static final int RESULT_RATE_LIMITED = 10;

    /**
     * The caller requested an operation it does not have privileges for.
     */
    public static final int RESULT_SECURITY_ERROR = 8;

    /**
     * An unknown error occurred while processing the call.
     */
    public static final int RESULT_UNKNOWN_ERROR = 1;

    private AppSearchResult() {
    }
}
