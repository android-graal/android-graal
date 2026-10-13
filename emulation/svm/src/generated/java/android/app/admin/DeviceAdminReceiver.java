package android.app.admin;

/**
 * Base class for implementing a device administration component.  This
 * class provides a convenience for interpreting the raw intent actions
 * that are sent by the system.
 *
 * <p>The callback methods, like the base
 * {@link BroadcastReceiver#onReceive(Context, Intent) BroadcastReceiver.onReceive()}
 * method, happen on the main thread of the process.  Thus long running
 * operations must be done on another thread.  Note that because a receiver
 * is done once returning from its receive function, such long-running operations
 * should probably be done in a {@link Service}.
 *
 * <p>When publishing your DeviceAdmin subclass as a receiver, it must
 * handle {@link #ACTION_DEVICE_ADMIN_ENABLED} and require the
 * {@link android.Manifest.permission#BIND_DEVICE_ADMIN} permission.  A typical
 * manifest entry would look like:</p>
 *
 * {@sample development/samples/ApiDemos/AndroidManifest.xml device_admin_declaration}
 *
 * <p>The meta-data referenced here provides addition information specific
 * to the device administrator, as parsed by the {@link DeviceAdminInfo} class.
 * A typical file would be:</p>
 *
 * {@sample development/samples/ApiDemos/res/xml/device_admin_sample.xml meta_data}
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about device administration, read the
 * <a href="{@docRoot}guide/topics/admin/device-admin.html">Device Administration</a>
 * developer guide.</p>
 * </div>
 */
public final class DeviceAdminReceiver {
    /**
     * Broadcast action: notify that some app is attempting to choose a KeyChain key.
     * @see DeviceAdminReceiver#onChoosePrivateKeyAlias
     * @see DelegatedAdminReceiver#onChoosePrivateKeyAlias
     */
    public static final String ACTION_CHOOSE_PRIVATE_KEY_ALIAS = "android.app.action.CHOOSE_PRIVATE_KEY_ALIAS";

    /**
     * Action sent to a device administrator when the user has disabled
     * it.  Upon return, the application no longer has access to the
     * protected device policy manager APIs.  You will generally
     * handle this in {@link DeviceAdminReceiver#onDisabled(Context, Intent)}.  Note
     * that this action will be
     * sent the receiver regardless of whether it is explicitly listed in
     * its intent filter.
     */
    public static final String ACTION_DEVICE_ADMIN_DISABLED = "android.app.action.DEVICE_ADMIN_DISABLED";

    /**
     * Action sent to a device administrator when the user has requested to
     * disable it, but before this has actually been done.  This gives you
     * a chance to supply a message to the user about the impact of
     * disabling your admin, by setting the extra field
     * {@link #EXTRA_DISABLE_WARNING} in the result Intent.  If not set,
     * no warning will be displayed.  If set, the given text will be shown
     * to the user before they disable your admin.
     */
    public static final String ACTION_DEVICE_ADMIN_DISABLE_REQUESTED = "android.app.action.DEVICE_ADMIN_DISABLE_REQUESTED";

    /**
     * This is the primary action that a device administrator must implement to be
     * allowed to manage a device.  This will be set to the receiver
     * when the user enables it for administration.  You will generally
     * handle this in {@link DeviceAdminReceiver#onEnabled(Context, Intent)}.  To be
     * supported, the receiver must also require the
     * {@link android.Manifest.permission#BIND_DEVICE_ADMIN} permission so
     * that other applications can not abuse it.
     */
    public static final String ACTION_DEVICE_ADMIN_ENABLED = "android.app.action.DEVICE_ADMIN_ENABLED";

    /**
     * Action sent to a device administrator to notify that the device is entering
     * lock task mode.  The extra {@link #EXTRA_LOCK_TASK_PACKAGE}
     * will describe the package using lock task mode.
     *
     * <p>The calling device admin must be the device owner or profile
     * owner to receive this broadcast.
     *
     * @see DevicePolicyManager#isLockTaskPermitted(String)
     */
    public static final String ACTION_LOCK_TASK_ENTERING = "android.app.action.LOCK_TASK_ENTERING";

    /**
     * Action sent to a device administrator to notify that the device is exiting
     * lock task mode.
     *
     * <p>The calling device admin must be the device owner or profile
     * owner to receive this broadcast.
     *
     * @see DevicePolicyManager#isLockTaskPermitted(String)
     */
    public static final String ACTION_LOCK_TASK_EXITING = "android.app.action.LOCK_TASK_EXITING";

    /**
     * Broadcast action: notify that a new batch of network logs is ready to be collected.
     * @see DeviceAdminReceiver#onNetworkLogsAvailable
     * @see DelegatedAdminReceiver#onNetworkLogsAvailable
     */
    public static final String ACTION_NETWORK_LOGS_AVAILABLE = "android.app.action.NETWORK_LOGS_AVAILABLE";

    /**
     * Action sent to a device administrator when the user has changed the password of their device
     * or profile challenge.  You can at this point check the characteristics
     * of the new password with {@link DevicePolicyManager#isActivePasswordSufficient()
     * DevicePolicyManager.isActivePasswordSufficient()}.
     * You will generally
     * handle this in {@link DeviceAdminReceiver#onPasswordChanged(Context, Intent, UserHandle)}.
     *
     * <p>The calling device admin must have requested
     * {@link DeviceAdminInfo#USES_POLICY_LIMIT_PASSWORD} to receive
     * this broadcast.
     */
    public static final String ACTION_PASSWORD_CHANGED = "android.app.action.ACTION_PASSWORD_CHANGED";

    /**
     * Action periodically sent to a device administrator when the device or profile challenge
     * password is expiring.  You will generally
     * handle this in {@link DeviceAdminReceiver#onPasswordExpiring(Context, Intent, UserHandle)}.
     *
     * <p>The calling device admin must have requested
     * {@link DeviceAdminInfo#USES_POLICY_EXPIRE_PASSWORD} to receive
     * this broadcast.
     */
    public static final String ACTION_PASSWORD_EXPIRING = "android.app.action.ACTION_PASSWORD_EXPIRING";

    /**
     * Action sent to a device administrator when the user has entered an incorrect device
     * or profile challenge password.  You can at this point check the
     * number of failed password attempts there have been with
     * {@link DevicePolicyManager#getCurrentFailedPasswordAttempts
     * DevicePolicyManager.getCurrentFailedPasswordAttempts()}.  You will generally
     * handle this in {@link DeviceAdminReceiver#onPasswordFailed(Context, Intent, UserHandle)}.
     *
     * <p>The calling device admin must have requested
     * {@link DeviceAdminInfo#USES_POLICY_WATCH_LOGIN} to receive
     * this broadcast.
     */
    public static final String ACTION_PASSWORD_FAILED = "android.app.action.ACTION_PASSWORD_FAILED";

    /**
     * Action sent to a device administrator when the user has successfully entered their device
     * or profile challenge password, after failing one or more times.  You will generally
     * handle this in {@link DeviceAdminReceiver#onPasswordSucceeded(Context, Intent, UserHandle)}.
     *
     * <p>The calling device admin must have requested
     * {@link DeviceAdminInfo#USES_POLICY_WATCH_LOGIN} to receive
     * this broadcast.
     */
    public static final String ACTION_PASSWORD_SUCCEEDED = "android.app.action.ACTION_PASSWORD_SUCCEEDED";

    /**
     * Broadcast Action: This broadcast is sent to indicate that provisioning of a managed profile
     * or managed device has completed successfully.
     *
     * <p>The broadcast is limited to the profile that will be managed by the application that
     * requested provisioning. In the device owner case the profile is the primary user.
     * The broadcast will also be limited to the {@link DeviceAdminReceiver} component
     * specified in the original intent or NFC bump that started the provisioning process
     * (see {@link DevicePolicyManager#ACTION_PROVISION_MANAGED_PROFILE
     * DevicePolicyManager.ACTION_PROVISION_MANAGED_PROFILE}).
     *
     * <p>A device admin application which listens to this intent can find out if the device was
     * provisioned for the device owner or profile owner case by calling respectively
     * {@link android.app.admin.DevicePolicyManager#isDeviceOwnerApp} and
     * {@link android.app.admin.DevicePolicyManager#isProfileOwnerApp}. You will generally handle
     * this in {@link DeviceAdminReceiver#onProfileProvisioningComplete}.
     *
     * <p>The intent for this action may include the following extras:
     * <ul>
     *     <li>{@link DevicePolicyManager#EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE}
     *
     * @see DevicePolicyManager#ACTION_PROVISIONING_SUCCESSFUL
     */
    public static final String ACTION_PROFILE_PROVISIONING_COMPLETE = "android.app.action.PROFILE_PROVISIONING_COMPLETE";

    /**
     * Broadcast action: notify that a new batch of security logs is ready to be collected.
     */
    public static final String ACTION_SECURITY_LOGS_AVAILABLE = "android.app.action.SECURITY_LOGS_AVAILABLE";

    /**
     * Bugreport completion process failed.
     *
     * <p>If this error code is received, the requesting of bugreport can be retried.
     * @see DevicePolicyManager#requestBugreport
     */
    public static final int BUGREPORT_FAILURE_FAILED_COMPLETING = 0;

    /**
     * Bugreport has been created, but is no longer available for collection.
     *
     * <p>This error likely occurs because the user of the device hasn't consented to share
     * the bugreport for a long period after its creation.
     *
     * <p>If this error code is received, the requesting of bugreport can be retried.
     * @see DevicePolicyManager#requestBugreport
     */
    public static final int BUGREPORT_FAILURE_FILE_NO_LONGER_AVAILABLE = 1;

    /**
     * Name under which a DevicePolicy component publishes information
     * about itself.  This meta-data must reference an XML resource containing
     * a device-admin tag.
     */
    public static final String DEVICE_ADMIN_META_DATA = "android.app.device_admin";

    /**
     * A CharSequence that can be shown to the user informing them of the
     * impact of disabling your admin.
     *
     * @see #ACTION_DEVICE_ADMIN_DISABLE_REQUESTED
     */
    public static final String EXTRA_DISABLE_WARNING = "android.app.extra.DISABLE_WARNING";

    /**
     * A string containing the name of the package entering lock task mode.
     *
     * @see #ACTION_LOCK_TASK_ENTERING
     */
    public static final String EXTRA_LOCK_TASK_PACKAGE = "android.app.extra.LOCK_TASK_PACKAGE";

    /**
     * A {@link android.os.Parcelable} extra of type {@link android.os.PersistableBundle} that
     * allows a mobile device management application to pass data to the management application
     * instance after owner transfer.
     *
     * <p>If the transfer is successful, the new owner receives the data in
     * {@link DeviceAdminReceiver#onTransferOwnershipComplete(Context, PersistableBundle)}.
     * The bundle is not changed during the ownership transfer.
     *
     * @see DevicePolicyManager#transferOwnership(ComponentName, ComponentName, PersistableBundle)
     */
    public static final String EXTRA_TRANSFER_OWNERSHIP_ADMIN_EXTRAS_BUNDLE = "android.app.extra.TRANSFER_OWNERSHIP_ADMIN_EXTRAS_BUNDLE";

    private DeviceAdminReceiver() {
    }
}
