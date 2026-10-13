package android.app.admin;

/**
 * Class containing identifiers for policy APIs in {@link DevicePolicyManager}, for example they
 * will be passed in {@link PolicyUpdateReceiver#onPolicySetResult} and
 * {@link PolicyUpdateReceiver#onPolicyChanged} to communicate updates of a certain policy back
 * to the admin.
 */
public final class DevicePolicyIdentifiers {
    /**
     * String identifier for {@link DevicePolicyManager#setAccountManagementDisabled}.
     */
    public static final String ACCOUNT_MANAGEMENT_DISABLED_POLICY = "accountManagementDisabled";

    /**
     * String identifier for {@link DevicePolicyManager#setApplicationHidden}.
     */
    public static final String APPLICATION_HIDDEN_POLICY = "applicationHidden";

    /**
     * String identifier for {@link DevicePolicyManager#setApplicationRestrictions}.
     */
    public static final String APPLICATION_RESTRICTIONS_POLICY = "applicationRestrictions";

    /**
     * String identifier for {@link DevicePolicyManager#setAppFunctionsPolicy(int)}.
     */
    public static final String APP_FUNCTIONS_POLICY = "appFunctions";

    /**
     * String identifier for {@link DevicePolicyManager#setAutoTimeZoneEnabled}.
     */
    public static final String AUTO_TIMEZONE_POLICY = "autoTimezone";

    /**
     * String identifier for {@link DevicePolicyManager#setAutoTimeEnabled}.
     */
    public static final String AUTO_TIME_POLICY = "autoTime";

    /**
     * String identifier for {@link DevicePolicyManager#setBackupServiceEnabled}.
     */
    public static final String BACKUP_SERVICE_POLICY = "backupService";

    /**
     * String identifier for {@link DevicePolicyManager#setCameraDisabled}.
     */
    public static final String CAMERA_DISABLED_POLICY = "cameraDisabled";

    /**
     * String identifier for {@link DevicePolicyManager#setContentProtectionPolicy}.
     */
    public static final String CONTENT_PROTECTION_POLICY = "contentProtection";

    /**
     * String identifier for {@link DevicePolicyManager#setKeyguardDisabledFeatures}.
     */
    public static final String KEYGUARD_DISABLED_FEATURES_POLICY = "keyguardDisabledFeatures";

    /**
     * String identifier for {@link DevicePolicyManager#setLockTaskPackages}.
     */
    public static final String LOCK_TASK_POLICY = "lockTask";

    /**
     * String identifier for {@link DevicePolicyManager#setPackagesSuspended}.
     */
    public static final String PACKAGES_SUSPENDED_POLICY = "packagesSuspended";

    /**
     * String identifier for {@link DevicePolicyManager#setUninstallBlocked}.
     */
    public static final String PACKAGE_UNINSTALL_BLOCKED_POLICY = "packageUninstallBlocked";

    /**
     * String identifier for {@link DevicePolicyManager#setRequiredPasswordComplexity}.
     */
    public static final String PASSWORD_COMPLEXITY_POLICY = "passwordComplexity";

    /**
     * String identifier for {@link DevicePolicyManager#setPermissionGrantState}.
     */
    public static final String PERMISSION_GRANT_POLICY = "permissionGrant";

    /**
     * String identifier for {@link DevicePolicyManager#addPersistentPreferredActivity}.
     */
    public static final String PERSISTENT_PREFERRED_ACTIVITY_POLICY = "persistentPreferredActivity";

    /**
     * String identifier for {@link DevicePolicyManager#setResetPasswordToken}.
     */
    public static final String RESET_PASSWORD_TOKEN_POLICY = "resetPasswordToken";

    /**
     * String identifier for {@link DevicePolicyManager#setSecurityLoggingEnabled}.
     */
    public static final String SECURITY_LOGGING_POLICY = "securityLogging";

    /**
     * String identifier for {@link DevicePolicyManager#setStatusBarDisabled}.
     */
    public static final String STATUS_BAR_DISABLED_POLICY = "statusBarDisabled";

    /**
     * String identifier for {@link DevicePolicyManager#setUsbDataSignalingEnabled}.
     */
    public static final String USB_DATA_SIGNALING_POLICY = "usbDataSignaling";

    /**
     * String identifier for {@link DevicePolicyManager#setUserControlDisabledPackages}.
     */
    public static final String USER_CONTROL_DISABLED_PACKAGES_POLICY = "userControlDisabledPackages";

    private DevicePolicyIdentifiers() {
    }
}
