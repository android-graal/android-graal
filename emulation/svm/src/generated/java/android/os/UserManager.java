package android.os;

/**
 * Manages users and user details on a multi-user system. There are two major categories of
 * users: fully customizable users with their own login, and profiles that share a workspace
 * with a related user.
 * <p>
 * Users are different from accounts, which are managed by
 * {@link AccountManager}. Each user can have their own set of accounts.
 * <p>
 * See {@link DevicePolicyManager#ACTION_PROVISION_MANAGED_PROFILE} for more on managed profiles.
 */
public final class UserManager {
    /**
     * Allows apps in the parent profile to handle web links from the managed profile.
     *
     * This user restriction has an effect only in a managed profile.
     * If set:
     * Intent filters of activities in the parent profile with action
     * {@link android.content.Intent#ACTION_VIEW},
     * category {@link android.content.Intent#CATEGORY_BROWSABLE}, scheme http or https, and which
     * define a host can handle intents from the managed profile.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_PROFILES}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String ALLOW_PARENT_PROFILE_APP_LINKING = "allow_parent_profile_app_linking";

    /**
     * Specifies if a user is disallowed from adding managed profiles.
     * <p>The default value for an unmanaged user is <code>false</code>.
     * For users with a device owner set, the default is <code>true</code>.
     * <p>This restriction has no effect on managed profiles.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     * @deprecated As the ability to have a managed profile on a fully-managed device has been
     * removed from the platform, this restriction will be silently ignored when applied by the
     * device owner.
     */
    @Deprecated
    public static final String DISALLOW_ADD_MANAGED_PROFILE = "no_add_managed_profile";

    /**
     * Specifies if a user is disallowed from creating a private profile.
     * <p>The default value for an unmanaged user is <code>false</code>.
     * For users with a device owner set, the default value is <code>true</code> and the
     * device owner currently cannot change it to <code>false</code>.
     * On organization-owned managed profile devices, the default value is <code>false</code> but
     * the profile owner can change it to <code>true</code> via the parent profile to block creating
     * of private profiles on the personal user.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_PROFILES}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#getParentProfileInstance(ComponentName)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_ADD_PRIVATE_PROFILE = "no_add_private_profile";

    /**
     * Specifies if a user is disallowed from adding new users. This can only be set by device
     * owners or profile owners on the main user. The default value is <code>false</code>.
     * <p> When the device is an organization-owned device, this restriction will be set as
     * a base restriction which cannot be removed by any admin.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MODIFY_USERS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_ADD_USER = "no_add_user";

    /**
     * Specifies if a user is disallowed from adding a new Wi-Fi configuration.
     *
     * <p>This restriction can only be set by a device owner,
     * a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by any of these owners, it prevents all users from adding
     * a new Wi-Fi configuration. This does not limit the owner and carrier's ability
     * to add a new configuration.
     *
     * <p>Holders of the permission {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WIFI}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_ADD_WIFI_CONFIG = "no_add_wifi_config";

    /**
     * Specifies if a user is disallowed from adjusting the global volume. If set, the global volume
     * will be muted. This can be set by device owners from API 21 and profile owners from API 24.
     * The default value is <code>false</code>.
     *
     * <p>When the restriction is set by profile owners, then it only applies to relevant
     * profiles.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_AUDIO_OUTPUT}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>This restriction has no effect on managed profiles.
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_ADJUST_VOLUME = "no_adjust_volume";

    /**
     * Specifies if airplane mode is disallowed on the device.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the primary
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by any of these owners, it applies globally - i.e., it disables airplane mode
     * on the entire device.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_AIRPLANE_MODE}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_AIRPLANE_MODE = "no_airplane_mode";

    /**
     * Specifies if ambient display is disallowed for the user.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_DISPLAY}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>This user restriction has no effect on managed profiles.
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_AMBIENT_DISPLAY = "no_ambient_display";

    /**
     * Specifies if a user is disallowed from modifying
     * applications in Settings or launchers. The following actions will not be allowed when this
     * restriction is enabled:
     * <li>uninstalling apps</li>
     * <li>disabling apps</li>
     * <li>clearing app caches</li>
     * <li>clearing app data</li>
     * <li>force stopping apps</li>
     * <li>clearing app defaults</li>
     * <p>
     * The default value is <code>false</code>.
     *
     * <p><strong>Note:</strong> The user will still be able to perform those actions via other
     * means (such as adb). Third party apps will also be able to uninstall apps via the
     * {@link android.content.pm.PackageInstaller}. {@link #DISALLOW_UNINSTALL_APPS} or
     * {@link DevicePolicyManager#setUninstallBlocked(ComponentName, String, boolean)} should be
     * used to prevent the user from uninstalling apps completely, and
     * {@link DevicePolicyManager#addPersistentPreferredActivity(ComponentName, IntentFilter, ComponentName)}
     * to add a default intent handler for a given intent filter.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_APPS_CONTROL}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_APPS_CONTROL = "no_control_apps";

    /**
     * This user restriction specifies if assist content is disallowed from being sent to
     * a privileged app such as the Assistant app. Assist content includes screenshots and
     * information about an app, such as package name.
     *
     * <p>This restriction can only be set by a device owner or a profile owner. When it is set
     * by a device owner, it disables the assist contextual data on the entire device. When it is
     * set by a profile owner, it disables assist content on the profile.
     *
     * <p>Default is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_ASSIST_CONTENT = "no_assist_content";

    /**
     * Specifies if a user is not allowed to use Autofill Services.
     *
     * <p>Device owner and profile owner can set this restriction. When it is set by device owner,
     * only the target user will be affected.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_AUTOFILL}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_AUTOFILL = "no_autofill";

    /**
     * Specifies if bluetooth is disallowed on the device. If bluetooth is disallowed on the device,
     * bluetooth cannot be turned on or configured via Settings.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the primary
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally - i.e., it disables bluetooth on
     * the entire device and all users will be affected. When it is set by a profile owner on the
     * primary user or by a profile owner of an organization-owned managed profile on the parent
     * profile, it disables the primary user from using bluetooth and configuring bluetooth
     * in Settings.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_BLUETOOTH}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_BLUETOOTH = "no_bluetooth";

    /**
     * Specifies if outgoing bluetooth sharing is disallowed.
     *
     * <p>A device owner and a profile owner can set this restriction. When it is set by a device
     * owner, it applies globally. When it is set by a profile owner on the primary user or by a
     * profile owner of an organization-owned managed profile on the parent profile, it disables
     * the primary user from any outgoing bluetooth sharing.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_BLUETOOTH}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Default is <code>true</code> for managed and private profiles, false otherwise.
     *
     * <p>When a device upgrades to {@link android.os.Build.VERSION_CODES#O}, the system sets it
     * for all existing managed profiles.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_BLUETOOTH_SHARING = "no_bluetooth_sharing";

    /**
     * Specifies whether the camera toggle is available to the user. If this restriction is set,
     * the user will not be able to block camera access via the system toggle. If camera
     * access is blocked when the restriction is added, it will be automatically re-enabled.
     *
     * This restriction can only be set by a device owner.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see android.hardware.SensorPrivacyManager
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CAMERA_TOGGLE = "disallow_camera_toggle";

    /**
     * Specifies if a user is not allowed to use 2g networks.
     *
     * <p> This is a security feature. 2g has no mutual authentication between a device and
     * cellular base station and downgrading a device's connection to 2g is a common tactic for
     * several types of privacy and security compromising attacks that could allow an adversary
     * to intercept, inject, or modify cellular communications.
     *
     * <p>This restriction can only be set by a device owner or a profile owner of an
     * organization-owned managed profile on the parent profile.
     * In all cases, the setting applies globally on the device.
     *
     * <p> Cellular connectivity loss (where a device would have otherwise successfully
     * connected to a 2g network) occurs if the device is in an area where only 2g networks are
     * available. Emergency calls are an exception and are never impacted. The device will still
     * scan for and connect to a 2g network for emergency calls.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MOBILE_NETWORK}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CELLULAR_2G = "no_cellular_2g";

    /**
     * This user restriction specifies if Near-field communication is disallowed to change
     * on the device. If Near-field communication is disallowed it cannot be changed via Settings.
     *
     * <p>This restriction can only be set by a device owner or a profile owner of an
     * organization-owned managed profile on the parent profile.
     * In both cases, the restriction applies globally on the device and will not allow Near-field
     * communication state being changed.
     *
     * <p>
     * Near-field communication (NFC) is a radio technology that allows two devices (like your phone
     * and a payments terminal) to communicate with each other when they're close together.
     *
     * <p>Default is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CHANGE_NEAR_FIELD_COMMUNICATION_RADIO = "no_change_near_field_communication_radio";

    /**
     * Specifies if a user is disallowed from enabling/disabling Wi-Fi.
     *
     * <p>This restriction can only be set by a device owner,
     * a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by any of these owners, it applies globally - i.e., it disables airplane mode
     * from changing Wi-Fi state.
     *
     * <p>Holders of the permission {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WIFI}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CHANGE_WIFI_STATE = "no_change_wifi_state";

    /**
     * Specifies if a user is disallowed from configuring bluetooth via Settings. This does
     * <em>not</em> restrict the user from turning bluetooth on or off.
     *
     * <p>This restriction doesn't prevent the user from using bluetooth. For disallowing usage of
     * bluetooth completely on the device, use {@link #DISALLOW_BLUETOOTH}.
     *
     * <p>A device owner and a profile owner can set this restriction, although the restriction has
     * no effect in a managed profile. When it is set by a device owner, a profile owner on the
     * primary user or by a profile owner of an organization-owned managed profile on the parent
     * profile, it disallows the primary user from configuring bluetooth.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_BLUETOOTH}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_BLUETOOTH = "no_config_bluetooth";

    /**
     * Specifies if a user is disallowed from configuring brightness. When device owner sets it,
     * it'll only be applied on the target(system) user.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_DISPLAY}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>This user restriction has no effect on managed profiles.
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_BRIGHTNESS = "no_config_brightness";

    /**
     * Specifies if a user is disallowed from configuring cell broadcasts.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the main
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the main user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the user from configuring cell broadcasts.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MOBILE_NETWORK}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>This restriction has no effect on non-Admin users since they cannot configure cell
     * broadcasts.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_CELL_BROADCASTS = "no_config_cell_broadcasts";

    /**
     * Specifies if a user is disallowed from configuring user
     * credentials. The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_LOCK_CREDENTIALS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_CREDENTIALS = "no_config_credentials";

    /**
     * Specifies configuring date, time and timezone is disallowed via Settings.
     *
     * <p>A device owner and a profile owner can set this restriction, although the restriction has
     * no effect in a managed profile. When it is set by a device owner or by a profile owner of an
     * organization-owned managed profile on the parent profile, it applies globally - i.e.,
     * it disables date, time and timezone setting on the entire device and all users are affected.
     * When it is set by a profile owner on the primary user, it disables the primary user
     * from configuring date, time and timezone and disables all configuring of date, time and
     * timezone in Settings.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_TIME}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_DATE_TIME = "no_config_date_time";

    /**
     * Specifies whether the user is allowed to modify default apps in settings.
     *
     * <p>A device owner and a profile owner can set this restriction. When it is set by a
     * device owner, it applies globally - i.e., modifying of default apps in Settings for all
     * users is disallowed. When it is set by a profile owner on the primary user or by a profile
     * owner of an organization-owned managed profile on the parent profile, modifying of
     * default apps in Settings for the primary user is disallowed.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_DEFAULT_APPS = "disallow_config_default_apps";

    /**
     * Specifies if a user is disallowed from changing the device
     * language. The default value is <code>false</code>.
     *
     * <p>Holders of the permission {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_LOCALE}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_LOCALE = "no_config_locale";

    /**
     * Specifies if a user is disallowed from enabling or disabling location providers. As a
     * result, user is disallowed from turning on or off location via Settings.
     *
     * <p>A device owner and a profile owner can set this restriction. When it is set by a device
     * owner, a profile owner on the primary user or by a profile owner of an organization-owned
     * managed profile on the parent profile, it disallows the primary user from turning location
     * on or off.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_LOCATION}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>This user restriction is different from {@link #DISALLOW_SHARE_LOCATION},
     * as a device owner or a profile owner can still enable or disable location mode via
     * {@link DevicePolicyManager#setLocationEnabled} when this restriction is on.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see LocationManager#isLocationEnabled()
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_LOCATION = "no_config_location";

    /**
     * Specifies if a user is disallowed from configuring mobile networks.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the main
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the main user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the user from configuring mobile networks.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MOBILE_NETWORK}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>This restriction has no effect on non-Admin users since they cannot configure mobile
     * networks.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_MOBILE_NETWORKS = "no_config_mobile_networks";

    /**
     * Specifies whether the user is allowed to modify private DNS settings.
     *
     * <p>This restriction can only be set by a device owner or a profile owner of an
     * organization-owned managed profile on the parent profile. When it is set by either of these
     * owners, it applies globally.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_RESTRICT_PRIVATE_DNS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_PRIVATE_DNS = "disallow_config_private_dns";

    /**
     * Specifies if a user is disallowed from changing screen off timeout.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_DISPLAY}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>This user restriction has no effect on managed profiles.
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_SCREEN_TIMEOUT = "no_config_screen_timeout";

    /**
     * Specifies if a user is disallowed from using and configuring Tethering and portable hotspots
     * via Settings.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the primary
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the primary user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the primary user from using Tethering and hotspots and
     * disables all configuring of Tethering and hotspots in Settings.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MOBILE_NETWORK}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>In Android 9.0 or higher, if tethering is enabled when this restriction is set,
     * tethering will be automatically turned off.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_TETHERING = "no_config_tethering";

    /**
     * Specifies if a user is disallowed from configuring a VPN. The default value is
     * <code>false</code>. This restriction has an effect when set by device owners and, in Android
     * 6.0 ({@linkplain android.os.Build.VERSION_CODES#M API level 23}) or higher, profile owners.
     * <p>This restriction also prevents VPNs from starting. However, in Android 7.0
     * ({@linkplain android.os.Build.VERSION_CODES#N API level 24}) or higher, the system does
     * start always-on VPNs created by the device or profile owner.
     * <p>From Android 12 ({@linkplain android.os.Build.VERSION_CODES#S API level 31}) enforcing
     * this restriction clears currently active VPN if it was configured by the user.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_VPN}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_VPN = "no_config_vpn";

    /**
     * Specifies if a user is disallowed from changing Wi-Fi access points via Settings. This
     * restriction does not affect Wi-Fi tethering settings.
     *
     * <p>A device owner and a profile owner can set this restriction, although the restriction has
     * no effect in a managed profile. When it is set by a device owner, a profile owner on the
     * primary user or by a profile owner of an organization-owned managed profile on the parent
     * profile, it disallows the primary user from changing Wi-Fi access points.
     *
     * <p>Holders of the permission {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WIFI}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONFIG_WIFI = "no_config_wifi";

    /**
     * Specifies if the contents of a user's screen is not allowed to be captured for artificial
     * intelligence purposes.
     *
     * <p>A device owner and a profile owner can set this restriction. When it is set by a device
     * owner, a profile owner on the primary user or by a profile owner of an organization-owned
     * managed profile on the parent profile, it disables the primary user's screen from being
     * captured for artificial intelligence purposes.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_SCREEN_CONTENT}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONTENT_CAPTURE = "no_content_capture";

    /**
     * Specifies if the current user is able to receive content suggestions for selections based on
     * the contents of their screen.
     *
     * <p>A device owner and a profile owner can set this restriction. When it is set by a device
     * owner, a profile owner on the primary user or by a profile owner of an organization-owned
     * managed profile on the parent profile, it disables the primary user from receiving content
     * suggestions for selections based on the contents of their screen.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_SCREEN_CONTENT}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CONTENT_SUGGESTIONS = "no_content_suggestions";

    /**
     * Specifies that windows besides app windows should not be
     * created. This will block the creation of the following types of windows.
     * <li>{@link LayoutParams#TYPE_TOAST}</li>
     * <li>{@link LayoutParams#TYPE_APPLICATION_OVERLAY}</li>
     *
     * <p>This can only be set by device owners and profile owners on the primary user.
     * The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WINDOWS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CREATE_WINDOWS = "no_create_windows";

    /**
     * Specifies if the clipboard contents can be exported by pasting the data into other users or
     * profiles. This restriction doesn't prevent import, such as someone pasting clipboard data
     * from other profiles or users. The default value is {@code false}.
     *
     * <p><strong>Note</strong>: Because it's possible to extract data from screenshots using
     * optical character recognition (OCR), we strongly recommend combining this user restriction
     * with {@link DevicePolicyManager#setScreenCaptureDisabled(ComponentName, boolean)}.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_PROFILE_INTERACTION}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_CROSS_PROFILE_COPY_PASTE = "no_cross_profile_copy_paste";

    /**
     * Specifies if a user is not allowed to use cellular data when roaming.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the primary
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the primary user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the primary user from using cellular data when roaming.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MOBILE_NETWORK}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_DATA_ROAMING = "no_data_roaming";

    /**
     * Specifies if a user is disallowed from enabling or accessing debugging features.
     *
     * <p>A device owner and a profile owner can set this restriction. When it is set by a device
     * owner, a profile owner on the primary user or by a profile owner of an organization-owned
     * managed profile on the parent profile, it disables debugging features altogether, including
     * USB debugging. When set on a managed profile or a secondary user, it blocks debugging for
     * that user only, including starting activities, making service calls, accessing content
     * providers, sending broadcasts, installing/uninstalling packages, clearing user data, etc.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_DEBUGGING_FEATURES}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_DEBUGGING_FEATURES = "no_debugging_features";

    /**
     * Specifies if a user is disallowed from factory resetting from Settings.
     * This can only be set by device owners and profile owners on an admin user.
     * The default value is <code>false</code>.
     * <p>This restriction has no effect on non-admin users since they cannot factory reset the
     * device.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_FACTORY_RESET}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_FACTORY_RESET = "no_factory_reset";

    /**
     * Specifies if the user is not allowed to have fun. In some cases, the
     * device owner may wish to prevent the user from experiencing amusement or
     * joy while using the device. The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_FUN}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_FUN = "no_fun";

    /**
     * Restricts a user's ability to possess or grant admin privileges.
     *
     * <p>When set to <code>true</code>, this prevents the user from:
     *     <ul>
     *         <li>Becoming an admin</li>
     *         <li>Giving other users admin privileges</li>
     *     </ul>
     *
     * <p>This restriction is only effective in environments where multiple admins are allowed.
     *
     * <p>Key for user restrictions. Type: Boolean. Default: <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_GRANT_ADMIN = "no_grant_admin";

    /**
     * Specifies if a user is disallowed from installing applications. This user restriction also
     * prevents device owners and profile owners installing apps. The default value is
     * {@code false}.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_APPS_CONTROL}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_INSTALL_APPS = "no_install_apps";

    /**
     * Specifies if a user is disallowed from enabling the
     * "Unknown Sources" setting, that allows installation of apps from unknown sources.
     * Unknown sources exclude adb and special apps such as trusted app stores.
     * The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_INSTALL_UNKNOWN_SOURCES}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES = "no_install_unknown_sources";

    /**
     * This restriction is a device-wide version of {@link #DISALLOW_INSTALL_UNKNOWN_SOURCES}.
     *
     * Specifies if all users on the device are disallowed from enabling the
     * "Unknown Sources" setting, that allows installation of apps from unknown sources.
     *
     * This restriction can be enabled by the profile owner, in which case all accounts and
     * profiles will be affected.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_INSTALL_UNKNOWN_SOURCES}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_INSTALL_UNKNOWN_SOURCES_GLOBALLY = "no_install_unknown_sources_globally";

    /**
     * Specifies whether the microphone toggle is available to the user. If this restriction is set,
     * the user will not be able to block microphone access via the system toggle. If microphone
     * access is blocked when the restriction is added, it will be automatically re-enabled.
     *
     * This restriction can only be set by a device owner.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see android.hardware.SensorPrivacyManager
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_MICROPHONE_TOGGLE = "disallow_microphone_toggle";

    /**
     * Specifies if a user is disallowed from adding and removing accounts, unless they are
     * {@link android.accounts.AccountManager#addAccountExplicitly programmatically} added by
     * Authenticator.
     * The default value is <code>false</code>.
     *
     * <p>From {@link android.os.Build.VERSION_CODES#N} a profile or device owner app can still
     * use {@link android.accounts.AccountManager} APIs to add or remove accounts when account
     * management is disallowed.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_ACCOUNT_MANAGEMENT}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_MODIFY_ACCOUNTS = "no_modify_accounts";

    /**
     * Specifies if a user is disallowed from mounting physical external media.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the primary
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the primary user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the primary user from mounting physical external media.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_PHYSICAL_MEDIA}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_MOUNT_PHYSICAL_MEDIA = "no_physical_media";

    /**
     * This user restriction specifies if Near-field communication is disallowed on the device. If
     * Near-field communication is disallowed it cannot be turned on via Settings.
     *
     * <p>This restriction can only be set by a device owner or a profile owner of an
     * organization-owned managed profile on the parent profile.
     * In both cases, the restriction applies globally on the device and will turn off the
     * Near-field communication radio if it's currently on and prevent the radio from being turned
     * on in the future.
     *
     * <p>
     * Near-field communication (NFC) is a radio technology that allows two devices (like your phone
     * and a payments terminal) to communicate with each other when they're close together.
     *
     * <p>Default is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_NEAR_FIELD_COMMUNICATION_RADIO = "no_near_field_communication_radio";

    /**
     * Specifies if a user is disallowed from resetting network settings
     * from Settings. This can only be set by device owners and profile owners on the main user.
     * The default value is <code>false</code>.
     * <p>This restriction has no effect on non-Admin users since they cannot reset the network
     * settings of the device.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MOBILE_NETWORK}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_NETWORK_RESET = "no_network_reset";

    /**
     * Specifies if the user is not allowed to use NFC to beam out data from apps.
     * The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_NEARBY_COMMUNICATION}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_OUTGOING_BEAM = "no_outgoing_beam";

    /**
     * Specifies that the user is not allowed to make outgoing phone calls. Emergency calls are
     * still permitted.
     *
     * <p>A device owner and a profile owner can set this restriction, although the restriction has
     * no effect in a managed profile. When it is set by a device owner, a profile owner on the
     * primary user or by a profile owner of an organization-owned managed profile on the parent
     * profile, it disallows the primary user from making outgoing phone calls.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_CALLS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_OUTGOING_CALLS = "no_outgoing_calls";

    /**
     * Specifies whether the user is allowed to print.
     *
     * This restriction can be set by device or profile owner.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_PRINTING}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * The default value is {@code false}.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_PRINTING = "no_printing";

    /**
     * Specifies if managed profiles of this user can be removed, other than by its profile owner.
     * The default value is <code>false</code>.
     * <p>
     * This restriction has no effect on managed profiles, and this restriction does not block the
     * removal of private profiles of this user.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     * @deprecated As the ability to have a managed profile on a fully-managed device has been
     * removed from the platform, this restriction will be silently ignored when applied by the
     * device owner.
     * When the device is provisioned with a managed profile on an organization-owned device,
     * the managed profile could not be removed anyway.
     */
    @Deprecated
    public static final String DISALLOW_REMOVE_MANAGED_PROFILE = "no_remove_managed_profile";

    /**
     * When set on the admin user this specifies if the user can remove secondary users. Managed
     * profiles and private profiles can still be removed even if this is set on the admin user.
     * When set on a non-admin secondary user, this specifies if the user can remove itself.
     * This restriction has no effect when set on managed profiles.
     * The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MODIFY_USERS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_REMOVE_USER = "no_remove_user";

    /**
     * Specifies if the user is not allowed to reboot the device into safe boot mode.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the primary
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the primary user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the primary user from rebooting the device into safe
     * boot mode.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_SAFE_BOOT}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SAFE_BOOT = "no_safe_boot";

    /**
     * Specifies if a user is not allowed to change their icon. Device owner and profile owner
     * can set this restriction. When it is set by device owner, only the target user will be
     * affected. The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MODIFY_USERS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SET_USER_ICON = "no_set_user_icon";

    /**
     * User restriction to disallow setting a wallpaper. Profile owner and device owner
     * are able to set wallpaper regardless of this restriction.
     * The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WALLPAPER}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SET_WALLPAPER = "no_set_wallpaper";

    /**
     * Specifies whether the user can share file / picture / data from the primary user into the
     * managed profile, either by sending them from the primary side, or by picking up data within
     * an app in the managed profile.
     * <p>
     * When a managed profile is created, the system allows the user to send data from the primary
     * side to the profile by setting up certain default cross profile intent filters. If
     * this is undesired, this restriction can be set to disallow it. Note that this restriction
     * will not block any sharing allowed by explicit
     * {@link DevicePolicyManager#addCrossProfileIntentFilter} calls by the profile owner.
     * <p>
     * This restriction is only meaningful when set by profile owner. When it is set by device
     * owner, it does not have any effect.
     * <p>
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_PROFILE_INTERACTION}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SHARE_INTO_MANAGED_PROFILE = "no_sharing_into_profile";

    /**
     * Specifies if a user is disallowed from turning on location sharing.
     *
     * <p>In a managed profile, location sharing by default reflects the primary user's setting, but
     * can be overridden and forced off by setting this restriction to true in the managed profile.
     *
     * <p>A device owner and a profile owner can set this restriction. When it is set by a device
     * owner, a profile owner on the primary user or by a profile owner of an organization-owned
     * managed profile on the parent profile, it prevents the primary user from turning on
     * location sharing.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_LOCATION}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SHARE_LOCATION = "no_share_location";

    /**
     * Specifies if users are disallowed from sharing Wi-Fi for admin configured networks.
     *
     * <p>Device owner and profile owner can set this restriction.
     * When it is set by any of these owners, it prevents all users from
     * sharing Wi-Fi for networks configured by these owners.
     * Other networks not configured by these owners are not affected.
     *
     * <p>Holders of the permission {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WIFI}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SHARING_ADMIN_CONFIGURED_WIFI = "no_sharing_admin_configured_wifi";

    /**
     * This user restriction specifies if the user is able to add embedded SIMs to the device.
     *
     * <p>
     * This restriction blocks the download of embedded SIMs.
     *
     * <p>
     * This restriction can only be set by a device owner or a profile owner of an
     * organization-owned managed profile.
     * In both cases, the restriction applies globally on the device.
     *
     * <p>
     * Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MOBILE_NETWORK}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Default is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SIM_GLOBALLY = "no_sim_globally";

    /**
     * Specifies that the user is not allowed to send or receive SMS messages.
     *
     * <p>This restriction can only be set by a device owner, a profile owner on the primary
     * user or a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the primary user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the primary user from sending or receiving SMS messages.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_SMS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SMS = "no_sms";

    /**
     * Specifies that system error dialogs for crashed or unresponsive apps should not be shown.
     * In this case, the system will force-stop the app as if the user chooses the "close app"
     * option on the UI. A feedback report isn't collected as there is no way for the user to
     * provide explicit consent. The default value is <code>false</code>.
     *
     * <p>When this user restriction is set by device owners, it's applied to all users. When set by
     * the profile owner of the primary user or a secondary user, the restriction affects only the
     * calling user. This user restriction has no effect on managed profiles.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_SYSTEM_DIALOGS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_SYSTEM_ERROR_DIALOGS = "no_system_error_dialogs";

    /**
     * This user restriction specifies if Thread network is disallowed on the device. If Thread
     * network is disallowed it cannot be turned on via Settings.
     *
     * <p>This restriction can only be set by a device owner or a profile owner of an
     * organization-owned managed profile on the parent profile.
     * In both cases, the restriction applies globally on the device and will turn off the
     * Thread network radio if it's currently on and prevent the radio from being turned
     * on in the future.
     *
     * <p> <a href="https://www.threadgroup.org">Thread</a> is a low-power and low-latency wireless
     * mesh networking protocol built on IPv6.
     *
     * <p>Default is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_THREAD_NETWORK = "no_thread_network";

    /**
     * This user restriction specifies if Ultra-wideband is disallowed on the device. If
     * Ultra-wideband is disallowed it cannot be turned on via Settings.
     *
     * <p>
     * Ultra-wideband (UWB) is a radio technology that can use a very low energy level
     * for short-range, high-bandwidth communications over a large portion of the radio spectrum.
     *
     * <p>This restriction can only be set by a device owner or a profile owner of an
     * organization-owned managed profile on the parent profile.
     * In both cases, the restriction applies globally on the device and will turn off the
     * ultra-wideband radio if it's currently on and prevent the radio from being turned on in
     * the future.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_NEARBY_COMMUNICATION}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Default is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_ULTRA_WIDEBAND_RADIO = "no_ultra_wideband_radio";

    /**
     * Specifies that the managed profile is not allowed to have unified lock screen challenge with
     * the primary user.
     *
     * <p>To ensure that there is a separate work profile password, IT admins
     * have to:
     * <ol>
     *   <li>Enforce {@link UserManager#DISALLOW_UNIFIED_PASSWORD}</li>
     *   <li>Verify that {@link DevicePolicyManager#isUsingUnifiedPassword(ComponentName)}
     *       returns true. This indicates that there is now a separate work
     *       profile password configured and the set up is completed.</li>
     *   <li>In case {@link DevicePolicyManager#isUsingUnifiedPassword(ComponentName)}
     *       returns false, invoke {@link DevicePolicyManager#ACTION_SET_NEW_PASSWORD}
     *       intent and then verify again
     *       {@link DevicePolicyManager#isUsingUnifiedPassword(ComponentName)}.</li>
     * </ol>
     * </p>
     *
     * <p>Can be set by profile owners. It only has effect on managed profiles when set by managed
     * profile owner. Has no effect on non-managed profiles or users.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_LOCK_CREDENTIALS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_UNIFIED_PASSWORD = "no_unified_password";

    /**
     * Specifies if a user is disallowed from uninstalling applications.
     * The default value is <code>false</code>.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_APPS_CONTROL}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_UNINSTALL_APPS = "no_uninstall_apps";

    /**
     * Specifies if a user is disallowed from adjusting microphone volume. If set, the microphone
     * will be muted.
     *
     * <p>A device owner and a profile owner can set this restriction, although the restriction has
     * no effect in a managed profile. When it is set by a device owner, it applies globally. When
     * it is set by a profile owner on the primary user or by a profile owner of an
     * organization-owned managed profile on the parent profile, it will disallow the primary user
     * from adjusting the microphone volume.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MICROPHONE}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_UNMUTE_MICROPHONE = "no_unmute_microphone";

    /**
     * Specifies if a user is disallowed from transferring files over USB.
     *
     * <p>This restriction can only be set by a <a href="https://developers.google.com/android/work/terminology#device_owner_do">
     * device owner</a> or a <a href="https://developers.google.com/android/work/terminology#profile_owner_po">
     * profile owner</a> on the primary user's profile or a profile owner of an organization-owned
     * <a href="https://developers.google.com/android/work/terminology#managed_profile">
     * managed profile</a> on the parent profile.
     * When it is set by a device owner, it applies globally. When it is set by a profile owner
     * on the primary user or by a profile owner of an organization-owned managed profile on
     * the parent profile, it disables the primary user from transferring files over USB. No other
     * user on the device is able to use file transfer over USB because the UI for file transfer
     * is always associated with the primary user.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_USB_FILE_TRANSFER}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_USB_FILE_TRANSFER = "no_usb_file_transfer";

    /**
     * Specifies if user switching is blocked on the current user.
     *
     * <p> This restriction can only be set by the device owner, it will be applied to all users.
     * Device owner can still switch user via
     * {@link DevicePolicyManager#switchUser(ComponentName, UserHandle)} when this restriction is
     * set.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_MODIFY_USERS}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_USER_SWITCH = "no_user_switch";

    /**
     * Specifies if a user is disallowed from using Wi-Fi Direct.
     *
     * <p>This restriction can only be set by a device owner,
     * a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by any of these owners, it prevents all users from using
     * Wi-Fi Direct.
     *
     * <p>Holders of the permission {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WIFI}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_WIFI_DIRECT = "no_wifi_direct";

    /**
     * Specifies if a user is disallowed from using Wi-Fi tethering.
     *
     * <p>This restriction does not limit the user's ability to modify or connect to regular
     * Wi-Fi networks, which is separately controlled by {@link #DISALLOW_CONFIG_WIFI}.
     *
     * <p>This restriction can only be set by a device owner,
     * a profile owner of an organization-owned managed profile on the parent profile.
     * When it is set by any of these owners, it prevents all users from using
     * Wi-Fi tethering. Other forms of tethering are not affected.
     *
     * <p>Holders of the permission {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_WIFI}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * This user restriction disables only Wi-Fi tethering.
     * Use {@link #DISALLOW_CONFIG_TETHERING} to limit all forms of tethering.
     * When {@link #DISALLOW_CONFIG_TETHERING} is set, this user restriction becomes obsolete.
     *
     * <p>The default value is <code>false</code>.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String DISALLOW_WIFI_TETHERING = "no_wifi_tethering";

    /**
     * Specifies if a user is disallowed from disabling application verification. The default
     * value is <code>false</code>.
     *
     * <p>In Android 8.0 ({@linkplain android.os.Build.VERSION_CODES#O API level 26}) and higher,
     * this is a global user restriction. If a device owner or profile owner sets this restriction,
     * the system enforces app verification across all users on the device. Running in earlier
     * Android versions, this restriction affects only the profile that sets it.
     *
     * <p>Holders of the permission
     * {@link android.Manifest.permission#MANAGE_DEVICE_POLICY_INSTALL_UNKNOWN_SOURCES}
     * can set this restriction using the DevicePolicyManager APIs mentioned below.
     *
     * <p>Key for user restrictions.
     * <p>Type: Boolean
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     * @see #getUserRestrictions()
     */
    public static final String ENSURE_VERIFY_APPS = "ensure_verify_apps";

    /**
     * Application restriction key that is used to indicate the pending arrival
     * of real restrictions for the app.
     *
     * <p>
     * Applications that support restrictions should check for the presence of this key.
     * A <code>true</code> value indicates that restrictions may be applied in the near
     * future but are not available yet. It is the responsibility of any
     * management application that sets this flag to update it when the final
     * restrictions are enforced.
     *
     * <p>Key for application restrictions.
     * <p>Type: Boolean
     * @see android.app.admin.DevicePolicyManager#setApplicationRestrictions(
     *      android.content.ComponentName, String, Bundle)
     * @see android.app.admin.DevicePolicyManager#getApplicationRestrictions(
     *      android.content.ComponentName, String)
     */
    public static final String KEY_RESTRICTIONS_PENDING = "restrictions_pending";

    /**
     * Flag passed to {@link #requestQuietModeEnabled} to request disabling quiet mode only if
     * there is no need to confirm the user credentials. If credentials are required to disable
     * quiet mode, {@link #requestQuietModeEnabled} will do nothing and return {@code false}.
     */
    public static final int QUIET_MODE_DISABLE_ONLY_IF_CREDENTIAL_NOT_REQUIRED = 1;

    /**
     * Error result indicating that this user is not allowed to add other users on this device.
     * This is a result code returned from the activity created by the intent
     * {@link #createUserCreationIntent(String, String, String, PersistableBundle)}.
     */
    public static final int USER_CREATION_FAILED_NOT_PERMITTED = 1;

    /**
     * Error result indicating that no more users can be created on this device.
     * This is a result code returned from the activity created by the intent
     * {@link #createUserCreationIntent(String, String, String, PersistableBundle)}.
     */
    public static final int USER_CREATION_FAILED_NO_MORE_USERS = 2;

    /**
     * Indicates user operation failed because the target user is in the foreground.
     */
    public static final int USER_OPERATION_ERROR_CURRENT_USER = 4;

    /**
     * Indicates user operation failed because device has low data storage.
     */
    public static final int USER_OPERATION_ERROR_LOW_STORAGE = 5;

    /**
     * Indicates user operation failed because target user is a managed profile.
     */
    public static final int USER_OPERATION_ERROR_MANAGED_PROFILE = 2;

    /**
     * Indicates user operation failed because maximum running user limit has been reached.
     */
    public static final int USER_OPERATION_ERROR_MAX_RUNNING_USERS = 3;

    /**
     * Indicates user operation failed because maximum user limit has been reached.
     */
    public static final int USER_OPERATION_ERROR_MAX_USERS = 6;

    /**
     * Indicates user operation failed for unknown reason.
     */
    public static final int USER_OPERATION_ERROR_UNKNOWN = 1;

    /**
     * Indicates user operation is successful.
     */
    public static final int USER_OPERATION_SUCCESS = 0;

    /**
     * User type representing a clone profile. Clone profile is a user profile type used to run
     * second instance of an otherwise single user App (eg, messengers). Currently only the
     * {@link android.content.pm.UserInfo#isMain()} user can have a clone profile.
     */
    public static final String USER_TYPE_PROFILE_CLONE = "android.os.usertype.profile.CLONE";

    /**
     * User type representing a managed profile, which is a profile that is to be managed by a
     * device policy controller (DPC).
     * The intended purpose is for work profiles, which are managed by a corporate entity.
     */
    public static final String USER_TYPE_PROFILE_MANAGED = "android.os.usertype.profile.MANAGED";

    /**
     * User type representing a private profile. Private profile is a user profile that can be used
     * as an alternative user-space to install and use sensitive apps.
     * UI surfaces can adopt an alternative strategy to show apps belonging to this profile, in line
     * with their sensitive nature.
     */
    public static final String USER_TYPE_PROFILE_PRIVATE = "android.os.usertype.profile.PRIVATE";

    private UserManager() {
    }
}
