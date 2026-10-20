package android.telecom;

import android.os.Parcelable;

/**
 * Describes the cause of a disconnected call. This always includes a code describing the generic
 * cause of the disconnect. Optionally, it may include a label and/or description to display to the
 * user. It is the responsibility of the {@link ConnectionService} to provide localized versions of
 * the label and description. It also may contain a reason for the disconnect, which is intended for
 * logging and not for display to the user.
 */
public final class DisconnectCause implements Parcelable {
    /**
     * Disconnected because the user did not locally answer the incoming call, but it was answered
     * on another device where the call was ringing.
     */
    public static final int ANSWERED_ELSEWHERE = 11;

    /**
     * Disconnected because the other party was busy.
     */
    public static final int BUSY = 7;

    /**
     * Disconnected because the call was pulled from the current device to another device.
     */
    public static final int CALL_PULLED = 12;

    /**
     * Disconnected because it has been canceled.
     */
    public static final int CANCELED = 4;

    /**
     * Disconnected because the connection manager did not support the call. The call will be tried
     * again without a connection manager. See {@link PhoneAccount#CAPABILITY_CONNECTION_MANAGER}.
     */
    public static final int CONNECTION_MANAGER_NOT_SUPPORTED = 10;

    /**
     * Disconnected because there was an error, such as a problem with the network.
     */
    public static final int ERROR = 1;

    /**
     * Disconnected because of a local user-initiated action, such as hanging up.
     */
    public static final int LOCAL = 2;

    /**
     * Disconnected because there was no response to an incoming call.
     */
    public static final int MISSED = 5;

    /**
     * Disconnected for reason not described by other disconnect codes.
     */
    public static final int OTHER = 9;

    /**
     * This reason is set when a call is ended in order to place an emergency call when a
     * {@link PhoneAccount} doesn't support holding an ongoing call to place an emergency call. This
     * reason string should only be associated with the {@link #LOCAL} disconnect code returned from
     * {@link #getCode()}.
     */
    public static final String REASON_EMERGENCY_CALL_PLACED = "REASON_EMERGENCY_CALL_PLACED";

    /**
     * Reason code (returned via {@link #getReason()}), which indicates that the connection service
     * is setting the call's state to {@link Call#STATE_DISCONNECTED} because it is internally
     * changing the representation of an IMS conference call to simulate a single-party call.
     *
     * This reason code is only used for communication between a {@link ConnectionService} and
     * Telecom and should not be surfaced to the user.
     */
    public static final String REASON_EMULATING_SINGLE_CALL = "EMULATING_SINGLE_CALL";

    /**
     * Reason code (returned via {@link #getReason()}), which indicates that the call was
     * disconnected because IMS access is blocked.
     */
    public static final String REASON_IMS_ACCESS_BLOCKED = "REASON_IMS_ACCESS_BLOCKED";

    /**
     * Reason code (returned via {@link #getReason()}) which indicates that a call could not be
     * completed because the cellular radio is off or out of service, the device is connected to
     * a wifi network, but the user has not enabled wifi calling.
     */
    public static final String REASON_WIFI_ON_BUT_WFC_OFF = "REASON_WIFI_ON_BUT_WFC_OFF";

    /**
     * Disconnected because the user rejected an incoming call.
     */
    public static final int REJECTED = 6;

    /**
     * Disconnected because the remote party hung up an ongoing call, or because an outgoing call
     * was not answered by the remote party.
     */
    public static final int REMOTE = 3;

    /**
     * Disconnected because of a restriction on placing the call, such as dialing in airplane
     * mode.
     */
    public static final int RESTRICTED = 8;

    /**
     * Disconnected because of an unknown or unspecified reason.
     */
    public static final int UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private DisconnectCause() {
    }
}
