package android.telephony;

import android.os.Parcelable;

/**
 * Contains precise data connection state.
 *
 * The following data connection information is included in returned PreciseDataConnectionState:
 *
 * <ul>
 *   <li>Data connection state.
 *   <li>Network type of the connection.
 *   <li>APN types.
 *   <li>APN.
 *   <li>The properties of the network link.
 *   <li>Data connection fail cause.
 * </ul>
 */
public final class PreciseDataConnectionState implements Parcelable {
    /**
     * Failure. The Failure status is used when network validation has been completed for the data
     * network and the result is failure.
     */
    public static final int NETWORK_VALIDATION_FAILURE = 4;

    /**
     * In progress. The in progress state is used when the network validation process for the data
     * network is in progress. This state is followed by either success or failure.
     */
    public static final int NETWORK_VALIDATION_IN_PROGRESS = 2;

    /**
     * Not Requested. The not requested status is used when the data network supports the network
     * validation function, but no network validation is being performed yet.
     */
    public static final int NETWORK_VALIDATION_NOT_REQUESTED = 1;

    /**
     * Success. The Success status is used when network validation has been completed for the data
     * network and the result is successful.
     */
    public static final int NETWORK_VALIDATION_SUCCESS = 3;

    /**
     * Unsupported. The unsupported state is used when the data network cannot support the network
     * validation function for the current data connection state.
     */
    public static final int NETWORK_VALIDATION_UNSUPPORTED = 0;

    private PreciseDataConnectionState() {
    }
}
