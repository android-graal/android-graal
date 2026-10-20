package android.telecom;

import android.os.Parcelable;

/**
 * This class represents a set of exceptions that can occur when requesting a
 * {@link CallEndpoint} change.
 */
public final class CallEndpointException implements Parcelable {
    /**
     * The operation was canceled by another request.
     */
    public static final int ERROR_ANOTHER_REQUEST = 3;

    /**
     * The operation has failed because requested CallEndpoint does not exist.
     */
    public static final int ERROR_ENDPOINT_DOES_NOT_EXIST = 1;

    /**
     * The operation was not completed on time.
     */
    public static final int ERROR_REQUEST_TIME_OUT = 2;

    /**
     * The operation has failed due to an unknown or unspecified error.
     */
    public static final int ERROR_UNSPECIFIED = 4;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private CallEndpointException() {
    }
}
