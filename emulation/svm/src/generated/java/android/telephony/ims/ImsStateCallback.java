package android.telephony.ims;

/**
 * A callback class used for monitoring changes in IMS service connection states
 * for a specific subscription.
 * <p>
 * @see ImsMmTelManager#registerImsStateCallback(Executor, ImsStateCallback)
 * @see ImsRcsManager#registerImsStateCallback(Executor, ImsStateCallback)
 */
public final class ImsStateCallback {
    /**
     * The underlying IMS service has died, is reconfiguring, or has never
     * come up yet and as a result is currently unavailable.
     * {@link #onAvailable} will be called when the IMS service becomes
     * available. All callbacks should be unregistered now and registered again
     * if the IMS service moves back to available.
     */
    public static final int REASON_IMS_SERVICE_DISCONNECTED = 3;

    /**
     * The IMS service is connected, but in a NOT_READY state. Once the
     * service moves to ready, {@link #onAvailable} will be called.
     */
    public static final int REASON_IMS_SERVICE_NOT_READY = 6;

    /**
     * There is no IMS service configured for the subscription ID specified.
     * This is a permanent error and there will be no Manager available for
     * this subscription.
     */
    public static final int REASON_NO_IMS_SERVICE_CONFIGURED = 4;

    /**
     * The subscription associated with this Manager has moved to an inactive
     * state (e.g. SIM removed) and the IMS service has torn down the resources
     * related to this subscription. This has caused this callback
     * to be deregistered. The callback must be re-registered when this subscription
     * becomes active in order to continue listening to the IMS service state.
     */
    public static final int REASON_SUBSCRIPTION_INACTIVE = 5;

    /**
     * The underlying IMS service is permanently unavailable for the
     * associated subscription and there will be no Manager available for
     * this subscription.
     */
    public static final int REASON_UNKNOWN_PERMANENT_ERROR = 2;

    /**
     * The underlying IMS service is temporarily unavailable for the
     * associated subscription.
     * {@link #onAvailable} will be called when the IMS service becomes
     * available again.
     */
    public static final int REASON_UNKNOWN_TEMPORARY_ERROR = 1;

    private ImsStateCallback() {
    }
}
