package android.telecom;

import android.os.Parcelable;

/**
 * This class represents a set of exceptions that can occur when requesting a
 * {@link Connection#queryLocationForEmergency(long, String, Executor, OutcomeReceiver)}
 */
public final class QueryLocationException implements Parcelable {
    /**
     * The operation has failed due to a location query being requested for a non-emergency
     * connection.
     */
    public static final int ERROR_NOT_ALLOWED_FOR_NON_EMERGENCY_CONNECTIONS = 4;

    /**
     * The operation has failed because it is not permitted.
     */
    public static final int ERROR_NOT_PERMITTED = 3;

    /**
     * The operation was rejected due to an existing request.
     */
    public static final int ERROR_PREVIOUS_REQUEST_EXISTS = 2;

    /**
     * The operation was not completed on time.
     */
    public static final int ERROR_REQUEST_TIME_OUT = 1;

    /**
     * The operation has failed due to the service is not available.
     */
    public static final int ERROR_SERVICE_UNAVAILABLE = 5;

    /**
     * The operation has failed due to an unknown or unspecified error.
     */
    public static final int ERROR_UNSPECIFIED = 6;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private QueryLocationException() {
    }
}
