package android.app.admin;

import android.os.Parcelable;

/**
 * This class is used to specify meta information of a device administrator
 * component.
 */
public final class DeviceAdminInfo implements Parcelable {
    /**
     * Value for {@link #getHeadlessDeviceOwnerMode} which indicates that this DPC should be
     * provisioned into "affiliated" mode when on a Headless System User Mode device.
     *
     * <p>This mode adds a Profile Owner to all users other than the user the Device Owner is on.
     *
     * <p>Starting from Android version {@link android.os.Build.VERSION_CODES#VANILLA_ICE_CREAM},
     * DPCs should set the value of attribute "headless-device-owner-mode" inside the
     * "headless-system-user" tag as "affiliated".
     */
    public static final int HEADLESS_DEVICE_OWNER_MODE_AFFILIATED = 1;

    /**
     * Value for {@link #getHeadlessDeviceOwnerMode} which indicates that this DPC should be
     * provisioned into the first secondary user when on a Headless System User Mode device.
     *
     * <p>This mode only allows a single secondary user on the device blocking the creation of
     * additional secondary users.
     *
     * <p>Starting from Android version {@link android.os.Build.VERSION_CODES#VANILLA_ICE_CREAM},
     * DPCs should set the value of attribute "headless-device-owner-mode" inside the
     * "headless-system-user" tag as "single_user".
     */
    public static final int HEADLESS_DEVICE_OWNER_MODE_SINGLE_USER = 2;

    /**
     * Value for {@link #getHeadlessDeviceOwnerMode} which indicates that this DPC should not
     * be provisioned into Device Owner mode on a Headless System User Mode device.
     */
    public static final int HEADLESS_DEVICE_OWNER_MODE_UNSUPPORTED = 0;

    /**
     * A type of policy that this device admin can use: require encryption of stored data.
     *
     * <p>To control this policy, the device admin must have a "encrypted-storage"
     * tag in the "uses-policies" section of its meta-data.
     */
    public static final int USES_ENCRYPTED_STORAGE = 7;

    /**
     * A type of policy that this device admin can use: disables use of all device cameras.
     *
     * <p>To control this policy, the device admin must be a device owner or profile owner,
     * and must have a "disable-camera" tag in the "uses-policies" section of its meta-data.
     * If used by a device owner, the policy affects all users on the device.
     */
    public static final int USES_POLICY_DISABLE_CAMERA = 8;

    /**
     * A type of policy that this device admin can use: disables use of keyguard features.
     *
     * <p>To control this policy, the device admin must be a device owner or profile owner,
     * and must have a "disable-keyguard-features" tag in the "uses-policies" section of its
     * meta-data.  If used by a device owner, the policy only affects the primary user and
     * its profiles, but not any secondary users on the device.
     */
    public static final int USES_POLICY_DISABLE_KEYGUARD_FEATURES = 9;

    /**
     * A type of policy that this device admin can use: force the user to
     * change their password after an administrator-defined time limit.
     *
     * <p>To control this policy, the device admin must be a device owner or profile owner,
     * and must have an "expire-password" tag in the "uses-policies" section of its meta-data.
     * If used by a device owner, the policy only affects the primary user and its profiles,
     * but not any secondary users on the device.
     */
    public static final int USES_POLICY_EXPIRE_PASSWORD = 6;

    /**
     * A type of policy that this device admin can use: able to force the device
     * to lock via{@link DevicePolicyManager#lockNow} or limit the
     * maximum lock timeout for the device via
     * {@link DevicePolicyManager#setMaximumTimeToLock}.
     *
     * <p>To control this policy, the device admin must have a "force-lock"
     * tag in the "uses-policies" section of its meta-data.
     */
    public static final int USES_POLICY_FORCE_LOCK = 3;

    /**
     * A type of policy that this device admin can use: limit the passwords
     * that the user can select, via {@link DevicePolicyManager#setPasswordQuality}
     * and {@link DevicePolicyManager#setPasswordMinimumLength}.
     *
     * <p>To control this policy, the device admin must be a device owner or profile owner,
     * and must have a "limit-password" tag in the "uses-policies" section of its meta-data.
     * If used by a device owner, the policy only affects the primary user and its profiles,
     * but not any secondary users on the device.
     */
    public static final int USES_POLICY_LIMIT_PASSWORD = 0;

    /**
     * A type of policy that this device admin can use: able to reset the
     * user's password via
     * {@link DevicePolicyManager#resetPassword}.
     *
     * <p>To control this policy, the device admin must have a "reset-password"
     * tag in the "uses-policies" section of its meta-data.
     */
    public static final int USES_POLICY_RESET_PASSWORD = 2;

    /**
     * A type of policy that this device admin can use: able to watch login
     * attempts from the user, via {@link DeviceAdminReceiver#ACTION_PASSWORD_FAILED},
     * {@link DeviceAdminReceiver#ACTION_PASSWORD_SUCCEEDED}, and
     * {@link DevicePolicyManager#getCurrentFailedPasswordAttempts}.
     *
     * <p>To control this policy, the device admin must have a "watch-login"
     * tag in the "uses-policies" section of its meta-data.
     */
    public static final int USES_POLICY_WATCH_LOGIN = 1;

    /**
     * A type of policy that this device admin can use: able to factory
     * reset the device, erasing all of the user's data, via
     * {@link DevicePolicyManager#wipeData}.
     *
     * <p>To control this policy, the device admin must have a "wipe-data"
     * tag in the "uses-policies" section of its meta-data.
     */
    public static final int USES_POLICY_WIPE_DATA = 4;

    private DeviceAdminInfo() {
    }
}
