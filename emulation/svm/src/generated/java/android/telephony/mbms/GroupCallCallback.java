package android.telephony.mbms;

/**
 * A callback class for use when the application is in a group call. The middleware
 * will provide updates on the status of the call via this callback.
 */
public interface GroupCallCallback {
    /**
     * Indicates broadcast signal strength is not available for this call.
     *
     * This may be due to the call no longer being available due to geography
     * or timing (end of service)
     */
    int SIGNAL_STRENGTH_UNAVAILABLE = -1;
}
