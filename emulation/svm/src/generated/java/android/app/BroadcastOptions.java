package android.app;

/**
 * Helper class for building an options Bundle that can be used with
 * {@link android.content.Context#sendBroadcast(android.content.Intent)
 * Context.sendBroadcast(Intent)} and related methods.
 */
public final class BroadcastOptions {
    /**
     * Deferral policy that indicates no desire has been expressed, and that the
     * system should use a reasonable default behavior.
     */
    public static final int DEFERRAL_POLICY_DEFAULT = 0;

    /**
     * Deferral policy that indicates a strong desire that no receiver of this
     * broadcast should be deferred.
     */
    public static final int DEFERRAL_POLICY_NONE = 1;

    /**
     * Deferral policy that indicates a strong desire that each receiver of this
     * broadcast should be deferred until that receiver's process is in an
     * active (non-cached) state. Whether an app's process state is considered
     * active is independent of its standby bucket.
     * <p>
     * This policy only applies to runtime registered receivers of a broadcast,
     * and does not apply to ordered broadcasts, alarm broadcasts, interactive
     * broadcasts, or manifest broadcasts.
     * <p>
     * This policy means that a runtime registered receiver will not typically
     * execute until that receiver's process is brought to an active state by
     * some other action, such as a job, alarm, or service binding. As a result,
     * the receiver may be delayed indefinitely.
     * <p>
     * When this policy is set on an unordered broadcast with a completion
     * callback, the completion callback will run once all eligible processes
     * have finished receiving the broadcast. Processes in inactive process
     * state are not considered eligible and may not receive the broadcast prior
     * to the completion callback.
     */
    public static final int DEFERRAL_POLICY_UNTIL_ACTIVE = 2;

    /**
     * Delivery group policy that indicates that all the broadcasts in the delivery group
     * need to be delivered as is.
     */
    public static final int DELIVERY_GROUP_POLICY_ALL = 0;

    /**
     * Delivery group policy that indicates that only the most recent broadcast in the delivery
     * group need to be delivered and the rest can be dropped.
     */
    public static final int DELIVERY_GROUP_POLICY_MOST_RECENT = 1;

    private BroadcastOptions() {
    }
}
