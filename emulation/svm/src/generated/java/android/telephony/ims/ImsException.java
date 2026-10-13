package android.telephony.ims;

/**
 * This class defines an IMS-related exception that has been thrown while interacting with a
 * device or carrier provided ImsService implementation.
 */
public final class ImsException {
    /**
     * The subscription ID associated with this operation is invalid or not active.
     * <p>
     * This is a configuration error and there should be no retry. The subscription used for this
     * operation is either invalid or has become inactive. The active subscriptions can be queried
     * with {@link SubscriptionManager#getActiveSubscriptionInfoList()}.
     */
    public static final int CODE_ERROR_INVALID_SUBSCRIPTION = 3;

    /**
     * The operation has failed because there is no remote process available to service it. This
     * may be due to a process crash or other illegal state.
     * <p>
     * This is a temporary error and the operation may be retried until the connection to the
     * remote process is restored.
     */
    public static final int CODE_ERROR_SERVICE_UNAVAILABLE = 1;

    /**
     * The operation has failed due to an unknown or unspecified error.
     */
    public static final int CODE_ERROR_UNSPECIFIED = 0;

    /**
     * This device or carrier configuration does not support this feature for this subscription.
     * <p>
     * This is a permanent configuration error and there should be no retry until the subscription
     * changes if this operation is denied due to a carrier configuration. If this is due to a
     * device configuration, the feature {@link PackageManager#FEATURE_TELEPHONY_IMS} is not
     * available or the device has no ImsService implementation to service this request.
     */
    public static final int CODE_ERROR_UNSUPPORTED_OPERATION = 2;

    private ImsException() {
    }
}
