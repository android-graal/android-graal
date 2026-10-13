package android.service.settings.preferences;

import android.os.Parcelable;

/**
 * Result object given a corresponding {@link GetValueRequest}.
 * <ul>
 *   <li>If the request was successful, {@link #getResultCode} will be {@link #RESULT_OK},
 *   {@link #getValue} will be populated with the settings preference value and
 *   {@link #getMetadata} will be populated with its metadata.
 *   <li>If the request is unsuccessful, {@link #getResultCode} be a value other than
 *   {@link #RESULT_OK} - see documentation for those possibilities to understand the cause
 *   of the failure.
 * </ul>
 */
public final class GetValueResult implements Parcelable {
    /**
     * Requested preference is not allowed for access in this API under the current device policy.
     * <p>Retry may succeed if underlying conditions change.
     */
    public static final int RESULT_DISALLOW = 4;

    /**
     * API call failed due to an issue with the service binding.
     * <p>Retry may succeed.
     */
    public static final int RESULT_INTERNAL_ERROR = 6;

    /**
     * Request object is not valid.
     * <p>Retry not advised with current parameters.
     */
    public static final int RESULT_INVALID_REQUEST = 5;

    /**
     * Request is successful.
     */
    public static final int RESULT_OK = 0;

    /**
     * Requested preference requires permissions not held by the calling application.
     * <p>Retry may succeed if necessary permissions are obtained.
     */
    public static final int RESULT_REQUIRE_APP_PERMISSION = 3;

    /**
     * Preference is currently not available, likely due to device state or the state of
     * a dependency.
     * <p>Retry may succeed if underlying conditions change.
     */
    public static final int RESULT_UNAVAILABLE = 2;

    /**
     * Requested preference is not supported by this API.
     * <p>Retry not advised.
     */
    public static final int RESULT_UNSUPPORTED = 1;

    private GetValueResult() {
    }
}
