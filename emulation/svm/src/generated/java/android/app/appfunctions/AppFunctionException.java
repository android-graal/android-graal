package android.app.appfunctions;

import android.os.Parcelable;

/**
 * Represents an app function related error.
 *
 * <p>This exception may include an {@link AppFunctionException#getExtras() Bundle} containing
 * additional error-specific metadata.
 *
 * <p>The AppFunction SDK can expose structured APIs by packing and unpacking this Bundle.
 */
public final class AppFunctionException implements Parcelable {
    /**
     * An unknown error occurred while processing the call in the AppFunctionService.
     *
     * <p>This error is thrown when the service is connected in the remote application but an
     * unexpected error is thrown from the bound application.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_APP} category.
     */
    public static final int ERROR_APP_UNKNOWN_ERROR = 3000;

    /**
     * The operation was cancelled. Use this error code to report that a cancellation is done after
     * receiving a cancellation signal.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_SYSTEM} category.
     */
    public static final int ERROR_CANCELLED = 2001;

    /**
     * The error is caused by the app providing the function.
     *
     * <p>For example, the app crashed when the system is executing the request.
     *
     * <p>Errors in the category fall in the range 3000-3999 inclusive.
     */
    public static final int ERROR_CATEGORY_APP = 3;

    /**
     * The error is caused by the app requesting a function execution.
     *
     * <p>For example, the caller provided invalid parameters in the execution request e.g. an
     * invalid function ID.
     *
     * <p>Errors in the category fall in the range 1000-1999 inclusive.
     */
    public static final int ERROR_CATEGORY_REQUEST_ERROR = 1;

    /**
     * The error is caused by an issue in the system.
     *
     * <p>For example, the AppFunctionService implementation is not found by the system.
     *
     * <p>Errors in the category fall in the range 2000-2999 inclusive.
     */
    public static final int ERROR_CATEGORY_SYSTEM = 2;

    /**
     * The error category is unknown.
     *
     * <p>This is the default value for {@link #getErrorCategory}.
     */
    public static final int ERROR_CATEGORY_UNKNOWN = 0;

    /**
     * The caller does not have the permission to execute an app function.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_REQUEST_ERROR} category.
     */
    public static final int ERROR_DENIED = 1000;

    /**
     * The caller tried to execute a disabled app function.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_REQUEST_ERROR} category.
     */
    public static final int ERROR_DISABLED = 1002;

    /**
     * The operation was disallowed by enterprise policy.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_SYSTEM} category.
     */
    public static final int ERROR_ENTERPRISE_POLICY_DISALLOWED = 2002;

    /**
     * The caller tried to execute a function that does not exist.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_REQUEST_ERROR} category.
     */
    public static final int ERROR_FUNCTION_NOT_FOUND = 1003;

    /**
     * The caller supplied invalid arguments to the execution request.
     *
     * <p>This error may be considered similar to {@link IllegalArgumentException}.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_REQUEST_ERROR} category.
     */
    public static final int ERROR_INVALID_ARGUMENT = 1001;

    /**
     * An internal unexpected error coming from the system.
     *
     * <p>This error is in the {@link #ERROR_CATEGORY_SYSTEM} category.
     */
    public static final int ERROR_SYSTEM_ERROR = 2000;

    private AppFunctionException() {
    }
}
