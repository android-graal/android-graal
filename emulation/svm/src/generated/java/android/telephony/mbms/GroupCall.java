package android.telephony.mbms;

/**
 * Class used to represent a single MBMS group call. After a call has been started with
 * {@link MbmsGroupCallSession#startGroupCall},
 * this class is used to hold information about the call and control it.
 */
public final class GroupCall {
    /**
     * State changed due to a call to {@link #close()} or
     * {@link MbmsGroupCallSession#startGroupCall}
     */
    public static final int REASON_BY_USER_REQUEST = 1;

    /**
     * State changed due to a frequency conflict with another requested call.
     */
    public static final int REASON_FREQUENCY_CONFLICT = 3;

    /**
     * State changed due to the device leaving the area where this call is being broadcast.
     */
    public static final int REASON_LEFT_MBMS_BROADCAST_AREA = 6;

    /**
     * Indicates that the middleware does not have a reason to provide for the state change.
     */
    public static final int REASON_NONE = 0;

    /**
     * State changed due to the device leaving the home carrier's LTE network.
     */
    public static final int REASON_NOT_CONNECTED_TO_HOMECARRIER_LTE = 5;

    /**
     * State changed due to the middleware running out of memory
     */
    public static final int REASON_OUT_OF_MEMORY = 4;

    /**
     * Indicates that the group call is stalled.
     *
     * This may be due to a network issue or the device being temporarily out of range.
     */
    public static final int STATE_STALLED = 3;

    /**
     * Indicates that the group call is started.
     *
     * Data can be transmitted and received in this state.
     */
    public static final int STATE_STARTED = 2;

    /**
     * Indicates that the group call is in a stopped state
     *
     * This can be reported after network action or after calling {@link #close}.
     */
    public static final int STATE_STOPPED = 1;

    private GroupCall() {
    }
}
