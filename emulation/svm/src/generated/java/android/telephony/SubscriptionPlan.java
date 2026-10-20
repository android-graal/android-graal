package android.telephony;

import android.os.Parcelable;

/**
 * Description of a billing relationship plan between a carrier and a specific
 * subscriber. This information is used to present more useful UI to users, such
 * as explaining how much mobile data they have remaining, and what will happen
 * when they run out.
 *
 * If specifying network types, the developer must supply at least one plan
 * that applies to all network types (default), and all additional plans
 * may not include a particular network type more than once.
 * This is enforced by {@link SubscriptionManager} when setting the plans.
 *
 * Plan selection will prefer plans that have specific network types defined
 * over plans that apply to all network types.
 *
 * @see SubscriptionManager#setSubscriptionPlans(int, java.util.List)
 * @see SubscriptionManager#getSubscriptionPlans(int)
 */
public final class SubscriptionPlan implements Parcelable {
    /**
     * Value indicating a number of bytes is unknown.
     */
    public static final long BYTES_UNKNOWN = -1L;

    /**
     * Value indicating a number of bytes is unlimited.
     */
    public static final long BYTES_UNLIMITED = 9223372036854775807L;

    /**
     * When a resource limit is hit, the user is billed automatically.
     */
    public static final int LIMIT_BEHAVIOR_BILLED = 1;

    /**
     * When a resource limit is hit, access is disabled.
     */
    public static final int LIMIT_BEHAVIOR_DISABLED = 0;

    /**
     * When a resource limit is hit, access is throttled to a slower rate.
     */
    public static final int LIMIT_BEHAVIOR_THROTTLED = 2;

    /**
     * When a resource limit is hit, the behavior is unknown.
     */
    public static final int LIMIT_BEHAVIOR_UNKNOWN = -1;

    /**
     * Subscription is active.
     */
    public static final int SUBSCRIPTION_STATUS_ACTIVE = 1;

    /**
     * Subscription is inactive.
     */
    public static final int SUBSCRIPTION_STATUS_INACTIVE = 2;

    /**
     * Subscription is suspended.
     */
    public static final int SUBSCRIPTION_STATUS_SUSPENDED = 4;

    /**
     * Subscription is in a trial period.
     */
    public static final int SUBSCRIPTION_STATUS_TRIAL = 3;

    /**
     * Subscription status is unknown.
     */
    public static final int SUBSCRIPTION_STATUS_UNKNOWN = 0;

    /**
     * Value indicating a timestamp is unknown.
     */
    public static final long TIME_UNKNOWN = -1L;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SubscriptionPlan() {
    }
}
