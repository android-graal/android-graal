package android.service.settings.preferences;

import android.os.Parcelable;

/**
 * Result object given a corresponding {@link SetValueRequest}.
 * <ul>
 *   <li>If the request was successful, {@link #getResultCode} will be {@link #RESULT_OK}.
 *   <li>If the request is unsuccessful, {@link #getResultCode} be a value other than
 *   {@link #RESULT_OK} - see documentation for those possibilities to understand the cause
 *   of the failure.
 * </ul>
 */
public final class SetValueResult implements Parcelable {
    /**
     * Requested preference is disabled, thus unable to be set in this state.
     * <p>Retry may succeed if underlying conditions change.
     */
    public static final int RESULT_DISABLED = 2;

    /**
     * Requested preference is not allowed for access in this API under the current device policy.
     * <p>Retry may succeed if underlying conditions change.
     */
    public static final int RESULT_DISALLOW = 7;

    /**
     * API call failed due to an issue with the service binding.
     * <p>Retry may succeed.
     */
    public static final int RESULT_INTERNAL_ERROR = 9;

    /**
     * Request object is not valid.
     * <p>Retry not advised with current parameters.
     */
    public static final int RESULT_INVALID_REQUEST = 8;

    /**
     * Request is successful and the value was set.
     */
    public static final int RESULT_OK = 0;

    /**
     * Requested preference requires permissions not held by the calling application.
     * <p>Retry may succeed if necessary permissions are obtained.
     */
    public static final int RESULT_REQUIRE_APP_PERMISSION = 5;

    /**
     * User consent was not approved for this operation.
     * <p>Retry may succeed if user provides consent.
     */
    public static final int RESULT_REQUIRE_USER_CONSENT = 6;

    /**
     * Requested preference is restricted, thus unable to be set under this policy.
     * <p>Retry may succeed if underlying conditions change.
     */
    public static final int RESULT_RESTRICTED = 3;

    /**
     * Preference is currently not available, likely due to device state or the state of
     * a dependency.
     * <p>Retry may succeed if underlying conditions change.
     */
    public static final int RESULT_UNAVAILABLE = 4;

    /**
     * Requested preference is not supported by this API.
     * <p>Retry not advised.
     */
    public static final int RESULT_UNSUPPORTED = 1;

    private SetValueResult() {
    }
}
