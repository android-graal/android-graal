package android.telecom;

import android.os.Parcelable;

/**
 * This class defines exceptions that can be thrown when using Telecom APIs with
 * {@link android.os.OutcomeReceiver}s.  Most of these exceptions are thrown when changing a call
 * state with {@link CallControl}s or {@link CallControlCallback}s.
 */
public final class CallException implements Parcelable {
    /**
     * The operation has failed because Telecom cannot set the requested call as the current active
     * call.  The client should end the current active call and re-try the operation.
     */
    public static final int CODE_CALL_CANNOT_BE_SET_TO_ACTIVE = 4;

    /**
     * The operation has failed because Telecom has already removed the call from the server side
     * and destroyed all the objects associated with it.  The client should re-add the call.
     */
    public static final int CODE_CALL_IS_NOT_BEING_TRACKED = 3;

    /**
     * The operation has failed because there is either no PhoneAccount registered with Telecom
     * for the given operation, or the limit of calls has been reached. The client should end the
     * current active call and re-try the failed operation.
     */
    public static final int CODE_CALL_NOT_PERMITTED_AT_PRESENT_TIME = 5;

    /**
     * The operation has failed due to Telecom failing to hold the current active call for the
     * call attempting to become the new active call.  The client should end the current active call
     * and re-try the failed operation.
     */
    public static final int CODE_CANNOT_HOLD_CURRENT_ACTIVE_CALL = 2;

    /**
     * The operation has failed due to an unknown or unspecified error.
     */
    public static final int CODE_ERROR_UNKNOWN = 1;

    /**
     * The operation has failed because the operation failed to complete before the timeout
     */
    public static final int CODE_OPERATION_TIMED_OUT = 6;

    private CallException() {
    }
}
