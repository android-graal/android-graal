package android.service.settings.preferences;

import android.os.Parcelable;

/**
 * Result object given a corresponding {@link MetadataRequest}.
 * <ul>
 *   <li>If the request was successful, {@link #getResultCode} will be {@link #RESULT_OK} and
 *   {@link #getMetadataList} will be populated with metadata for all available preferences within
 *   this application.
 *   <li>If the request is unsuccessful, {@link #getResultCode} be a value other than
 *   {@link #RESULT_OK} - see documentation for those possibilities to understand the cause
 *   of the failure.
 * </ul>
 */
public final class MetadataResult implements Parcelable {
    /**
     * API call failed due to an issue with the service binding.
     * <p>Retry may succeed.
     */
    public static final int RESULT_INTERNAL_ERROR = 2;

    /**
     * Request is successful.
     */
    public static final int RESULT_OK = 0;

    /**
     * No preferences in this application support this API.
     * <p>Retry not advised.
     */
    public static final int RESULT_UNSUPPORTED = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private MetadataResult() {
    }
}
