package android.app.admin;

/**
 * Base class for implementing a policy update receiver. This class provides a convenience for
 * interpreting the raw intent actions ({@link #ACTION_DEVICE_POLICY_SET_RESULT} and
 * {@link #ACTION_DEVICE_POLICY_CHANGED}) that are sent by the system.
 *
 * <p>The callback methods happen on the main thread of the process. Thus, long-running
 * operations must be done on another thread.
 *
 * <p>When publishing your {@code PolicyUpdateReceiver} subclass as a receiver, it must
 * require the {@link android.Manifest.permission#BIND_DEVICE_ADMIN} permission.
 *
 * <p>Admins can implement {@link DeviceAdminService} to ensure they receive all policy updates
 * (for policies they have set) via {@link #onPolicyChanged} by constantly being bound to by the
 * system. For more information see {@link DeviceAdminService}.
 */
public final class PolicyUpdateReceiver {
    /**
     * Action for a broadcast sent to admins to communicate back a change in a policy they have
     * previously set.
     *
     * <p>Admins wishing to receive these updates should include this action in the intent filter
     * for their receiver in the manifest, the receiver must be protected by
     * {@link android.Manifest.permission#BIND_DEVICE_ADMIN} to ensure that only the system can
     * send updates.
     *
     * <p>Admins shouldn't implement {@link #onReceive} and should instead implement
     * {@link #onPolicyChanged}.
     */
    public static final String ACTION_DEVICE_POLICY_CHANGED = "android.app.admin.action.DEVICE_POLICY_CHANGED";

    /**
     * Action for a broadcast sent to admins to communicate back the result of setting a policy in
     * {@link DevicePolicyManager}.
     *
     * <p>Admins wishing to receive these updates (via {@link #onPolicySetResult}) should include
     * this action in the intent filter for their receiver in the manifest, the receiver
     * must be protected by {@link android.Manifest.permission#BIND_DEVICE_ADMIN} to ensure that
     * only the system can send updates.
     *
     * <p>Admins shouldn't implement {@link #onReceive} and should instead implement
     * {@link #onPolicySetResult}.
     */
    public static final String ACTION_DEVICE_POLICY_SET_RESULT = "android.app.admin.action.DEVICE_POLICY_SET_RESULT";

    /**
     * A string extra holding the account type this policy applies to, (see
     * {@link PolicyUpdateReceiver#onPolicyChanged} and
     * {@link PolicyUpdateReceiver#onPolicySetResult})
     */
    public static final String EXTRA_ACCOUNT_TYPE = "android.app.admin.extra.ACCOUNT_TYPE";

    /**
     * An {@link android.content.IntentFilter} extra holding the intent filter the policy relates
     * to, (see {@link PolicyUpdateReceiver#onPolicyChanged} and
     * {@link PolicyUpdateReceiver#onPolicySetResult})
     */
    public static final String EXTRA_INTENT_FILTER = "android.app.admin.extra.INTENT_FILTER";

    /**
     * A string extra holding the package name the policy applies to, (see
     * {@link PolicyUpdateReceiver#onPolicyChanged} and
     * {@link PolicyUpdateReceiver#onPolicySetResult})
     */
    public static final String EXTRA_PACKAGE_NAME = "android.app.admin.extra.PACKAGE_NAME";

    /**
     * A string extra holding the permission name the policy applies to, (see
     * {@link PolicyUpdateReceiver#onPolicyChanged} and
     * {@link PolicyUpdateReceiver#onPolicySetResult})
     */
    public static final String EXTRA_PERMISSION_NAME = "android.app.admin.extra.PERMISSION_NAME";

    private PolicyUpdateReceiver() {
    }
}
