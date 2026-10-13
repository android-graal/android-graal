package android.app.admin;

/**
 * Manages device policy and restrictions applied to the user of the device or
 * apps running on the device.
 *
 * <p>This class contains three types of methods:
 * <ol><li>Those aimed at <a href="#managingapps">managing apps</a>
 * <li>Those aimed at the <a href="#roleholder">Device Policy Management Role Holder</a>
 * <li>Those aimed at <a href="#querying">apps which wish to respect device policy</a>
 * </ol>
 *
 * <p>The intended caller for each API is indicated in its Javadoc.
 *
 * <p id="managingapps"><b>Managing Apps</b>
 * <p>Apps can be made capable of setting device policy ("Managing Apps") either by
 * being set as a <a href="#deviceadmin">Device Administrator</a>, being set as a
 * <a href="#devicepolicycontroller">Device Policy Controller</a>, or by holding the
 * appropriate <a href="#permissions">Permissions</a>.
 *
 * <p id="deviceadmin">A <b>Device Administrator</b> is an app which is able to enforce device
 * policies that it has declared in its device admin XML file. An app can prompt the user to give it
 * device administator privileges using the {@link #ACTION_ADD_DEVICE_ADMIN} action.
 *
 * <p>For more information about Device Administration, read the
 * <a href="{@docRoot}guide/topics/admin/device-admin.html">Device Administration</a>
 * developer guide.
 *
 * <p id="devicepolicycontroller">Device Administrator apps can also be recognised as <b>
 * Device Policy Controllers</b>. Device Policy Controllers can be one of
 * two types:
 * <ul>
 * <li>A <i id="deviceowner">Device Owner</i>, which only ever exists on the
 * {@link UserManager#isSystemUser System User} or Main User, is
 * the most powerful type of Device Policy Controller and can affect policy across the device.
 * <li>A <i id="profileowner">Profile Owner</i>, which can exist on any user, can
 * affect policy on the user it is on, and when it is running on
 * {@link UserManager#isProfile a profile} has
 * <a href="#profile-on-parent">limited</a> ability to affect policy on its parent.
 * </ul>
 *
 * <p>Additional capabilities can be provided to Device Policy Controllers in
 * the following circumstances:
 * <ul>
 * <li>A Profile Owner on an <a href="#organization-owned">organization owned</a> device has access
 * to additional abilities, both <a href="#profile-on-parent-organization-owned">affecting policy on the profile's</a>
 * parent and also the profile itself.
 * <li>A Profile Owner running on the {@link UserManager#isSystemUser System User} has access to
 * additional capabilities which affect the {@link UserManager#isSystemUser System User} and
 * also the whole device.
 * <li>A Profile Owner running on an <a href="#affiliated">affiliated</a> user has
 * capabilities similar to that of a <a href="#deviceowner">Device Owner</a>
 * </ul>
 *
 * <p>For more information, see <a href="{@docRoot}work/dpc/build-dpc">Building a Device Policy
 * Controller</a>.
 *
 * <p><a href="#permissions">Permissions</a> are generally only given to apps
 * fulfilling particular key roles on the device (such as managing
 * {@link android.devicelock.DeviceLockManager device locks}).
 *
 * <p id="roleholder"><b>Device Policy Management Role Holder</b>
 * <p>One app on the device fulfills the Device Policy Management Role and is trusted with managing
 * the overall state of Device Policy. This has access to much more powerful methods than
 * <a href="#managingapps">managing apps</a>.
 *
 * <p id="querying"><b>Querying Device Policy</b>
 * <p>In most cases, regular apps do not need to concern themselves with device
 * policy, and restrictions will be enforced automatically. There are some cases
 * where an app may wish to query device policy to provide a better user
 * experience. Only a small number of policies allow apps to query them directly.
 * These APIs will typically have no special required permissions.
 *
 * <p id="managedprovisioning"><b>Managed Provisioning</b>
 * <p>Managed Provisioning is the process of recognising an app as a
 * <a href="#deviceowner">Device Owner</a> or <a href="#profileowner">Profile Owner</a>. It
 * involves presenting education and consent screens to the user to ensure they
 * are aware of the capabilities this grants the <a href="#devicepolicycontroller">Device Policy
 * Controller</a>
 *
 * <p>For more information on provisioning, see <a href="{@docRoot}work/dpc/build-dpc">Building a
 * Device Policy Controller</a>.
 *
 * <p id="managed_profile">A <b>Managed Profile</b> enables data separation. For example to use
 * a device both for personal and corporate usage. The managed profile and its
 * parent share a launcher.
 *
 * <p id="affiliated"><b>Affiliation</b>
 * <p>Using the {@link #setAffiliationIds} method, a
 * <a href="#deviceowner">Device Owner</a> can set a list of affiliation ids for the
 * {@link UserManager#isSystemUser System User}. Any <a href="#profileowner">Profile Owner</a> on
 * the same device can also call {@link #setAffiliationIds} to set affiliation ids
 * for the {@link UserManager user} it is on. When there is the same ID
 * present in both lists, the user is said to be "affiliated" and we can refer to
 * the <a href="#profileowner">Profile Owner</a> as a "profile owner on an affiliated
 * user" or an "affiliated profile owner".
 *
 * Becoming affiliated grants the <a href="#profileowner">Profile Owner</a> capabilities similar to
 * that of the <a href="#deviceowner">Device Owner</a>. It also allows use of the
 * {@link #bindDeviceAdminServiceAsUser} APIs for direct communication between the
 * <a href="#deviceowner">Device Owner</a> and
 * affiliated <a href="#profileowner">Profile Owners</a>.
 *
 * <p id="organization-owned"><b>Organization Owned</b></p>
 * An organization owned device is one which is not owned by the person making use of the device and
 * is instead owned by an organization such as their employer or education provider. These devices
 * are recognised as being organization owned either by the presence of a
 * <a href="#deviceowner">device owner</a> or of a
 * {@link #isOrganizationOwnedDeviceWithManagedProfile profile which has a profile owner is marked
 * as organization owned}.
 *
 * <p id="profile-on-parent-organization-owned">Profile owners running on an
 * <a href="organization-owned">organization owned</a> device can exercise additional capabilities
 * using the {@link #getParentProfileInstance(ComponentName)} API which apply to the parent user.
 * Each API will indicate if it is usable in this way.
 *
 * <p id="automotive"><b>Android Automotive</b>
 * <p>On {@link android.content.pm.PackageManager#FEATURE_AUTOMOTIVE
 * "Android Automotive builds"}, some methods can throw
 * {@link UnsafeStateException "an exception"} if an action is unsafe (for example, if the vehicle
 * is moving). Callers running on
 * {@link android.content.pm.PackageManager#FEATURE_AUTOMOTIVE
 * "Android Automotive builds"} should always check for this exception.
 */
public final class DevicePolicyManager {
    /**
     * Activity action: ask the user to add a new device administrator to the system.
     * The desired policy is the ComponentName of the policy in the
     * {@link #EXTRA_DEVICE_ADMIN} extra field.  This will invoke a UI to
     * bring the user through adding the device administrator to the system (or
     * allowing them to reject it).
     *
     * <p>You can optionally include the {@link #EXTRA_ADD_EXPLANATION}
     * field to provide the user with additional explanation (in addition
     * to your component's description) about what is being added.
     *
     * <p>If your administrator is already active, this will ordinarily return immediately (without
     * user intervention).  However, if your administrator has been updated and is requesting
     * additional uses-policy flags, the user will be presented with the new list.  New policies
     * will not be available to the updated administrator until the user has accepted the new list.
     */
    public static final String ACTION_ADD_DEVICE_ADMIN = "android.app.action.ADD_DEVICE_ADMIN";

    /**
     * Activity action: Starts the administrator to show policy compliance for the provisioning.
     * This action is used any time that the administrator has an opportunity to show policy
     * compliance before the end of setup wizard. This could happen as part of the admin-integrated
     * provisioning flow (in which case this gets sent after {@link #ACTION_GET_PROVISIONING_MODE}),
     * or it could happen during provisioning finalization if the administrator supports
     * finalization during setup wizard.
     *
     * <p>Intents with this action may also be supplied with the {@link
     * #EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE} extra.
     *
     * @see #ACTION_GET_PROVISIONING_MODE
     */
    public static final String ACTION_ADMIN_POLICY_COMPLIANCE = "android.app.action.ADMIN_POLICY_COMPLIANCE";

    /**
     * Broadcast Action: Sent after application delegation scopes are changed. The new delegation
     * scopes will be sent in an {@code ArrayList<String>} extra identified by the
     * {@link #EXTRA_DELEGATION_SCOPES} key.
     *
     * <p class="note"><b>Note:</b> This is a protected intent that can only be sent by the
     * system.</p>
     */
    public static final String ACTION_APPLICATION_DELEGATION_SCOPES_CHANGED = "android.app.action.APPLICATION_DELEGATION_SCOPES_CHANGED";

    /**
     * Activity action: launch the DPC to check policy compliance. This intent is launched when
     * the user taps on the notification about personal apps suspension. When handling this intent
     * the DPC must check if personal apps should still be suspended and either unsuspend them or
     * instruct the user on how to resolve the noncompliance causing the suspension.
     *
     * @see #setPersonalAppsSuspended
     */
    public static final String ACTION_CHECK_POLICY_COMPLIANCE = "android.app.action.CHECK_POLICY_COMPLIANCE";

    /**
     * Service action: Action for a service that device owner and profile owner can optionally
     * own.  If a device owner or a profile owner has such a service, the system tries to keep
     * a bound connection to it, in order to keep their process always running.
     * The service must be protected with the {@link android.Manifest.permission#BIND_DEVICE_ADMIN}
     * permission.
     */
    public static final String ACTION_DEVICE_ADMIN_SERVICE = "android.app.action.DEVICE_ADMIN_SERVICE";

    /**
     * Broadcast Action: Broadcast sent to indicate that the device financing state has changed.
     *
     * <p>This occurs when, for example, a financing kiosk app has been added or removed.
     *
     * <p>To query the current device financing state see {@link #isDeviceFinanced}.
     *
     * <p>This will be delivered to the following apps if they include a receiver for this action
     * in their manifest:
     * <ul>
     *     <li>Device owner admins.
     *     <li>Organization-owned profile owner admins
     *     <li>The supervision app
     *     <li>The device management role holder
     * </ul>
     */
    public static final String ACTION_DEVICE_FINANCING_STATE_CHANGED = "android.app.admin.action.DEVICE_FINANCING_STATE_CHANGED";

    /**
     * Broadcast action: sent when the device owner is set, changed or cleared.
     *
     * This broadcast is sent only to the primary user.
     * @see #ACTION_PROVISION_MANAGED_DEVICE
     * @see DevicePolicyManager#transferOwnership(ComponentName, ComponentName, PersistableBundle)
     */
    public static final String ACTION_DEVICE_OWNER_CHANGED = "android.app.action.DEVICE_OWNER_CHANGED";

    /**
     * Broadcast action: notify system apps (e.g. settings, SysUI, etc) that the device management
     * resources with IDs {@link #EXTRA_RESOURCE_IDS} has been updated, the updated resources can be
     * retrieved using {@link DevicePolicyResourcesManager#getDrawable} and
     * {@link DevicePolicyResourcesManager#getString}.
     *
     * <p>This broadcast is sent to registered receivers only.
     *
     * <p> {@link #EXTRA_RESOURCE_TYPE} will be included to identify the type of resource being
     * updated.
     */
    public static final String ACTION_DEVICE_POLICY_RESOURCE_UPDATED = "android.app.action.DEVICE_POLICY_RESOURCE_UPDATED";

    /**
     * Activity action: Starts the administrator to get the mode for the provisioning.
     * This intent may contain the following extras:
     * <ul>
     *     <li>{@link #EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE}</li>
     *     <li>{@link #EXTRA_PROVISIONING_IMEI}</li>
     *     <li>{@link #EXTRA_PROVISIONING_SERIAL_NUMBER}</li>
     *     <li>{@link #EXTRA_PROVISIONING_ALLOWED_PROVISIONING_MODES}</li>
     *     <li>{@link #EXTRA_PROVISIONING_SENSORS_PERMISSION_GRANT_OPT_OUT}</li>
     * </ul>
     *
     * <p>The target activity should return one of the following values in
     * {@link #EXTRA_PROVISIONING_MODE} as result:
     * <ul>
     *     <li>{@link #PROVISIONING_MODE_FULLY_MANAGED_DEVICE}</li>
     *     <li>{@link #PROVISIONING_MODE_MANAGED_PROFILE}</li>
     * </ul>
     *
     * <p>If performing fully-managed device provisioning and the admin app desires to show its
     * own education screens, the target activity can additionally return
     * {@link #EXTRA_PROVISIONING_SKIP_EDUCATION_SCREENS} set to <code>true</code>.
     *
     * <p>The target activity may also return the account that needs to be migrated from primary
     * user to managed profile in case of a profile owner provisioning in
     * {@link #EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE} as result.
     *
     * <p>The target activity may also include the {@link #EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE}
     * extra in the intent result. The values of this {@link android.os.PersistableBundle} will be
     * sent as an intent extra of the same name to the {@link #ACTION_ADMIN_POLICY_COMPLIANCE}
     * activity, along with the values of the {@link #EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE} extra
     * that are already supplied to this activity.
     *
     * <p>Other extras the target activity may include in the intent result:
     * <ul>
     *     <li>{@link #EXTRA_PROVISIONING_DISCLAIMERS}</li>
     *     <li>{@link #EXTRA_PROVISIONING_SKIP_ENCRYPTION}</li>
     *     <li>{@link #EXTRA_PROVISIONING_KEEP_SCREEN_ON}</li>
     *     <li>{@link #EXTRA_PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION} for work profile
     *     provisioning</li>
     *     <li>{@link #EXTRA_PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED} for work profile
     *     provisioning</li>
     *     <li>{@link #EXTRA_PROVISIONING_SENSORS_PERMISSION_GRANT_OPT_OUT} for fully-managed
     *     device provisioning</li>
     *     <li>{@link #EXTRA_PROVISIONING_LOCALE} for fully-managed device provisioning</li>
     *     <li>{@link #EXTRA_PROVISIONING_LOCAL_TIME} for fully-managed device provisioning</li>
     *     <li>{@link #EXTRA_PROVISIONING_TIME_ZONE} for fully-managed device provisioning</li>
     * </ul>
     *
     * @see #ACTION_ADMIN_POLICY_COMPLIANCE
     */
    public static final String ACTION_GET_PROVISIONING_MODE = "android.app.action.GET_PROVISIONING_MODE";

    /**
     * Broadcast Action: This broadcast is sent to indicate that provisioning of a managed profile
     * has completed successfully.
     *
     * <p>The broadcast is limited to the primary profile, to the app specified in the provisioning
     * intent with action {@link #ACTION_PROVISION_MANAGED_PROFILE}.
     *
     * <p>This intent will contain the following extras
     * <ul>
     * <li>{@link Intent#EXTRA_USER}, corresponds to the {@link UserHandle} of the managed
     * profile.</li>
     * <li>{@link #EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE}, corresponds to the account requested to
     * be migrated at provisioning time, if any.</li>
     * </ul>
     */
    public static final String ACTION_MANAGED_PROFILE_PROVISIONED = "android.app.action.MANAGED_PROFILE_PROVISIONED";

    /**
     * Broadcast action: sent when the profile owner is set, changed or cleared.
     *
     * This broadcast is sent only to the user managed by the new profile owner.
     * @see DevicePolicyManager#transferOwnership(ComponentName, ComponentName, PersistableBundle)
     */
    public static final String ACTION_PROFILE_OWNER_CHANGED = "android.app.action.PROFILE_OWNER_CHANGED";

    /**
     * Activity action: This activity action is sent to indicate that provisioning of a managed
     * profile or managed device has completed successfully. It'll be sent at the same time as
     * {@link DeviceAdminReceiver#ACTION_PROFILE_PROVISIONING_COMPLETE} broadcast but this will be
     * delivered faster as it's an activity intent.
     *
     * <p>The intent is only sent to the new device or profile owner.
     *
     * @see #ACTION_PROVISION_MANAGED_PROFILE
     * @see #ACTION_PROVISION_MANAGED_DEVICE
     */
    public static final String ACTION_PROVISIONING_SUCCESSFUL = "android.app.action.PROVISIONING_SUCCESSFUL";

    /**
     * Activity action: Starts the provisioning flow which sets up a managed device.
     * Must be started with {@link android.app.Activity#startActivityForResult(Intent, int)}.
     *
     * <p> During device owner provisioning a device admin app is set as the owner of the device.
     * A device owner has full control over the device. The device owner can not be modified by the
     * user.
     *
     * <p> A typical use case would be a device that is owned by a company, but used by either an
     * employee or client.
     *
     * <p> An intent with this action can be sent only on an unprovisioned device.
     * It is possible to check if provisioning is allowed or not by querying the method
     * {@link #isProvisioningAllowed(String)}.
     *
     * <p>The intent contains the following extras:
     * <ul>
     * <li>{@link #EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME}</li>
     * <li>{@link #EXTRA_PROVISIONING_SKIP_ENCRYPTION}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_LOGO_URI}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_DISCLAIMERS}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_SKIP_EDUCATION_SCREENS}, optional</li>
     * </ul>
     *
     * <p>When device owner provisioning has completed, an intent of the type
     * {@link DeviceAdminReceiver#ACTION_PROFILE_PROVISIONING_COMPLETE} is broadcast to the
     * device owner.
     *
     * <p>From version {@link android.os.Build.VERSION_CODES#O}, when device owner provisioning has
     * completed, along with the above broadcast, activity intent
     * {@link #ACTION_PROVISIONING_SUCCESSFUL} will also be sent to the device owner.
     *
     * <p>If provisioning fails, the device is factory reset.
     *
     * <p>A result code of {@link android.app.Activity#RESULT_OK} implies that the synchronous part
     * of the provisioning flow was successful, although this doesn't guarantee the full flow will
     * succeed. Conversely a result code of {@link android.app.Activity#RESULT_CANCELED} implies
     * that the user backed-out of provisioning, or some precondition for provisioning wasn't met.
     *
     * @deprecated to support {@link android.os.Build.VERSION_CODES#S} and later, admin apps must
     * implement activities with intent filters for the {@link #ACTION_GET_PROVISIONING_MODE} and
     * {@link #ACTION_ADMIN_POLICY_COMPLIANCE} intent actions; using {@link
     * #ACTION_PROVISION_MANAGED_DEVICE} to start provisioning will cause the provisioning to fail;
     * to additionally support pre-{@link android.os.Build.VERSION_CODES#S}, admin apps must also
     * continue to use this constant.
     */
    @Deprecated
    public static final String ACTION_PROVISION_MANAGED_DEVICE = "android.app.action.PROVISION_MANAGED_DEVICE";

    /**
     * Activity action: Starts the provisioning flow which sets up a
     * <a href="#managed-profile">managed profile</a>.
     *
     * <p>It is possible to check if provisioning is allowed or not by querying the method
     * {@link #isProvisioningAllowed(String)}.
     *
     * <p>The intent may contain the following extras:
     *
     * <table>
     *  <thead>
     *      <tr>
     *          <th>Extra</th>
     *          <th></th>
     *          <th>Supported Versions</th>
     *      </tr>
     *  </thead>
     *  <tbody>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE}</td>
     *          <td colspan="2"></td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_SKIP_ENCRYPTION}</td>
     *          <td></td>
     *          <td>{@link android.os.Build.VERSION_CODES#N}+</td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE}</td>
     *          <td colspan="2"></td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_LOGO_URI}</td>
     *          <td colspan="2"></td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_SKIP_USER_CONSENT}</td>
     *          <td colspan="2"><b>Can only be used by an existing device owner trying to create a
     *          managed profile</b></td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION}</td>
     *          <td colspan="2"></td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_DISCLAIMERS}</td>
     *          <td colspan="2"></td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME}</td>
     *          <td>
     *              <b>Required if {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME} is not
     *              specified. Must match the package name of the calling application.</b>
     *          </td>
     *          <td>{@link android.os.Build.VERSION_CODES#LOLLIPOP}+</td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME}</td>
     *          <td>
     *              <b>Required if {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME} is not
     *              specified. Package name must match the package name of the calling
     *              application.</b>
     *          </td>
     *          <td>{@link android.os.Build.VERSION_CODES#M}+</td>
     *      </tr>
     *      <tr>
     *          <td>{@link #EXTRA_PROVISIONING_ALLOW_OFFLINE}</td>
     *          <td colspan="2">On {@link android.os.Build.VERSION_CODES#TIRAMISU}+, when set to
     *          true this will <b>force</b> offline provisioning instead of allowing it</td>
     *      </tr>
     *  </tbody>
     * </table>
     *
     * <p>When <a href="#managedprovisioning">managed provisioning</a> has completed, broadcasts
     * are sent to the application specified in the provisioning intent. The
     * {@link DeviceAdminReceiver#ACTION_PROFILE_PROVISIONING_COMPLETE} broadcast is sent in the
     * managed profile and the {@link #ACTION_MANAGED_PROFILE_PROVISIONED} broadcast is sent in
     * the primary profile.
     *
     * <p>From version {@link android.os.Build.VERSION_CODES#O}, when managed provisioning has
     * completed, along with the above broadcast, activity intent
     * {@link #ACTION_PROVISIONING_SUCCESSFUL} will also be sent to the profile owner.
     *
     * <p>If provisioning fails, the managed profile is removed so the device returns to its
     * previous state.
     *
     * <p>If launched with {@link android.app.Activity#startActivityForResult(Intent, int)} a
     * result code of {@link android.app.Activity#RESULT_OK} indicates that the synchronous part of
     * the provisioning flow was successful, although this doesn't guarantee the full flow will
     * succeed. Conversely a result code of {@link android.app.Activity#RESULT_CANCELED} indicates
     * that the user backed-out of provisioning or some precondition for provisioning wasn't met.
     *
     * <p>If a <a href="#roleholder">device policy management role holder</a> updater is present on
     * the device, an internet connection attempt must be made prior to launching this intent.
     */
    public static final String ACTION_PROVISION_MANAGED_PROFILE = "android.app.action.PROVISION_MANAGED_PROFILE";

    /**
     * Activity action: have the user enter a new password for the parent profile.
     * If the intent is launched from within a managed profile, this will trigger
     * entering a new password for the parent of the profile. The caller can optionally
     * set {@link #EXTRA_DEVICE_PASSWORD_REQUIREMENT_ONLY} to only enforce device-wide
     * password requirement. In all other cases the behaviour is identical to
     * {@link #ACTION_SET_NEW_PASSWORD}.
     */
    public static final String ACTION_SET_NEW_PARENT_PROFILE_PASSWORD = "android.app.action.SET_NEW_PARENT_PROFILE_PASSWORD";

    /**
     * Activity action: have the user enter a new password.
     *
     * <p>For admin apps, this activity should be launched after using {@link
     * #setPasswordQuality(ComponentName, int)}, or {@link
     * #setPasswordMinimumLength(ComponentName, int)} to have the user enter a new password that
     * meets the current requirements. You can use {@link #isActivePasswordSufficient()} to
     * determine whether you need to have the user select a new password in order to meet the
     * current constraints. Upon being resumed from this activity, you can check the new
     * password characteristics to see if they are sufficient.
     *
     * <p>Non-admin apps can use {@link #getPasswordComplexity()} to check the current screen lock
     * complexity, and use this activity with extra {@link #EXTRA_PASSWORD_COMPLEXITY} to suggest
     * to users how complex the app wants the new screen lock to be. Note that both {@link
     * #getPasswordComplexity()} and the extra {@link #EXTRA_PASSWORD_COMPLEXITY} require the
     * calling app to have the permission {@link permission#REQUEST_PASSWORD_COMPLEXITY}.
     *
     * <p>If the intent is launched from within a managed profile with a profile
     * owner built against {@link android.os.Build.VERSION_CODES#M} or before,
     * this will trigger entering a new password for the parent of the profile.
     * For all other cases it will trigger entering a new password for the user
     * or profile it is launched from.
     *
     * @see #ACTION_SET_NEW_PARENT_PROFILE_PASSWORD
     */
    public static final String ACTION_SET_NEW_PASSWORD = "android.app.action.SET_NEW_PASSWORD";

    /**
     * Activity action: begin the process of encrypting data on the device.  This activity should
     * be launched after using {@link #setStorageEncryption} to request encryption be activated.
     * After resuming from this activity, use {@link #getStorageEncryption}
     * to check encryption status.  However, on some devices this activity may never return, as
     * it may trigger a reboot and in some cases a complete data wipe of the device.
     */
    public static final String ACTION_START_ENCRYPTION = "android.app.action.START_ENCRYPTION";

    /**
     * Broadcast action: notify that a new local system update policy has been set by the device
     * owner. The new policy can be retrieved by {@link #getSystemUpdatePolicy()}.
     */
    public static final String ACTION_SYSTEM_UPDATE_POLICY_CHANGED = "android.app.action.SYSTEM_UPDATE_POLICY_CHANGED";

    /**
     * Indicates that {@link android.app.appfunctions.AppFunctionManager} is controlled and
     *  disabled by policy, i.e. no apps in the current user are allowed to expose app functions.
     */
    public static final int APP_FUNCTIONS_DISABLED = 1;

    /**
     * Indicates that {@link android.app.appfunctions.AppFunctionManager} is controlled and
     * disabled by a policy for cross profile interactions only, i.e. app functions exposed by apps
     * in the current user can only be invoked within the same user.
     *
     * <p>This is different from {@link #APP_FUNCTIONS_DISABLED} in that it only disables cross
     * profile interactions (even if the caller has permissions required to interact across users).
     * appfunctions can still be used within the a user profile boundary.
     */
    public static final int APP_FUNCTIONS_DISABLED_CROSS_PROFILE = 2;

    /**
     * Indicates that {@link android.app.appfunctions.AppFunctionManager} is not controlled by
     * policy.
     *
     * <p>If no admin set this policy, it means appfunctions are enabled.
     */
    public static final int APP_FUNCTIONS_NOT_CONTROLLED_BY_POLICY = 0;

    /**
     * Specifies the "disabled" auto time state.
     *
     * @see #setAutoTimePolicy(ComponentName, int)
     */
    public static final int AUTO_TIME_DISABLED = 1;

    /**
     * Specifies the "enabled" auto time state.
     *
     * @see #setAutoTimePolicy(ComponentName, int)
     */
    public static final int AUTO_TIME_ENABLED = 2;

    /**
     * Specifies that the auto time state is not controlled by device policy.
     *
     * @see #setAutoTimePolicy(ComponentName, int)
     */
    public static final int AUTO_TIME_NOT_CONTROLLED_BY_POLICY = 0;

    /**
     * Specifies the "disabled" auto time zone state.
     *
     * @see #setAutoTimeZonePolicy(int)
     */
    public static final int AUTO_TIME_ZONE_DISABLED = 1;

    /**
     * Specifies the "enabled" auto time zone state.
     *
     * @see #setAutoTimeZonePolicy(int)
     */
    public static final int AUTO_TIME_ZONE_ENABLED = 2;

    /**
     * Specifies that the auto time zone state is not controlled by device policy.
     *
     * @see #setAutoTimeZonePolicy(int)
     */
    public static final int AUTO_TIME_ZONE_NOT_CONTROLLED_BY_POLICY = 0;

    /**
     * Indicates that content protection is controlled and disabled by a policy (default).
     */
    public static final int CONTENT_PROTECTION_DISABLED = 1;

    /**
     * Indicates that content protection is controlled and enabled by a policy.
     */
    public static final int CONTENT_PROTECTION_ENABLED = 2;

    /**
     * Indicates that content protection is not controlled by policy, allowing user to choose.
     */
    public static final int CONTENT_PROTECTION_NOT_CONTROLLED_BY_POLICY = 0;

    /**
     * Delegation of application restrictions management. This scope grants access to the
     * {@link #setApplicationRestrictions} and {@link #getApplicationRestrictions} APIs.
     */
    public static final String DELEGATION_APP_RESTRICTIONS = "delegation-app-restrictions";

    /**
     * Delegation of application uninstall block. This scope grants access to the
     * {@link #setUninstallBlocked} API.
     */
    public static final String DELEGATION_BLOCK_UNINSTALL = "delegation-block-uninstall";

    /**
     * Delegation of certificate installation and management. This scope grants access to the
     * {@link #getInstalledCaCerts}, {@link #hasCaCertInstalled}, {@link #installCaCert},
     * {@link #uninstallCaCert}, {@link #uninstallAllUserCaCerts} and {@link #installKeyPair} APIs.
     * This scope also grants the ability to read identifiers that the delegating device owner or
     * profile owner can obtain. See {@link #getEnrollmentSpecificId()}.
     */
    public static final String DELEGATION_CERT_INSTALL = "delegation-cert-install";

    /**
     * Grants access to selection of KeyChain certificates on behalf of requesting apps.
     * Once granted the app will start receiving
     * {@link DelegatedAdminReceiver#onChoosePrivateKeyAlias}. The caller (PO/DO) will
     * no longer receive {@link DeviceAdminReceiver#onChoosePrivateKeyAlias}.
     * There can be at most one app that has this delegation.
     * If another app already had delegated certificate selection access,
     * it will lose the delegation when a new app is delegated.
     * <p> The delegated app can also call {@link #grantKeyPairToApp} and
     * {@link #revokeKeyPairFromApp} to directly grant KeyChain keys to other apps.
     * <p> Can be granted by Device Owner or Profile Owner.
     */
    public static final String DELEGATION_CERT_SELECTION = "delegation-cert-selection";

    /**
     * Delegation for enabling system apps. This scope grants access to the {@link #enableSystemApp}
     * API.
     */
    public static final String DELEGATION_ENABLE_SYSTEM_APP = "delegation-enable-system-app";

    /**
     * Delegation for installing existing packages. This scope grants access to the
     * {@link #installExistingPackage} API.
     */
    public static final String DELEGATION_INSTALL_EXISTING_PACKAGE = "delegation-install-existing-package";

    /**
     * Delegation of management of uninstalled packages. This scope grants access to the
     * {@link #setKeepUninstalledPackages} and {@link #getKeepUninstalledPackages} APIs.
     */
    public static final String DELEGATION_KEEP_UNINSTALLED_PACKAGES = "delegation-keep-uninstalled-packages";

    /**
     * Grants access to {@link #setNetworkLoggingEnabled}, {@link #isNetworkLoggingEnabled} and
     * {@link #retrieveNetworkLogs}. Once granted the delegated app will start receiving
     * DelegatedAdminReceiver.onNetworkLogsAvailable() callback, and Device owner or Profile Owner
     * will no longer receive the DeviceAdminReceiver.onNetworkLogsAvailable() callback.
     * There can be at most one app that has this delegation.
     * If another app already had delegated network logging access,
     * it will lose the delegation when a new app is delegated.
     *
     * <p> Device Owner can grant this access since Android 10. Profile Owner of a managed profile
     * can grant this access since Android 12.
     */
    public static final String DELEGATION_NETWORK_LOGGING = "delegation-network-logging";

    /**
     * Delegation of package access state. This scope grants access to the
     * {@link #isApplicationHidden}, {@link #setApplicationHidden}, {@link #isPackageSuspended}, and
     * {@link #setPackagesSuspended} APIs.
     */
    public static final String DELEGATION_PACKAGE_ACCESS = "delegation-package-access";

    /**
     * Delegation of permission policy and permission grant state. This scope grants access to the
     * {@link #setPermissionPolicy}, {@link #getPermissionGrantState},
     * and {@link #setPermissionGrantState} APIs.
     */
    public static final String DELEGATION_PERMISSION_GRANT = "delegation-permission-grant";

    /**
     * Grants access to {@link #setSecurityLoggingEnabled}, {@link #isSecurityLoggingEnabled},
     * {@link #retrieveSecurityLogs}, and {@link #retrievePreRebootSecurityLogs}. Once granted the
     * delegated app will start receiving {@link DelegatedAdminReceiver#onSecurityLogsAvailable}
     * callback, and Device owner or Profile Owner will no longer receive the
     * {@link DeviceAdminReceiver#onSecurityLogsAvailable} callback. There can be at most one app
     * that has this delegation. If another app already had delegated security logging access, it
     * will lose the delegation when a new app is delegated.
     *
     * <p> Can only be granted by Device Owner or Profile Owner of an organization-owned
     * managed profile.
     */
    public static final String DELEGATION_SECURITY_LOGGING = "delegation-security-logging";

    /**
     * Result code for {@link #getStorageEncryptionStatus}: indicating that encryption is not
     * currently active, but is currently being activated.
     * <p>
     * @deprecated This result code has never actually been used, so there is no reason for apps to
     * check for it.
     */
    @Deprecated
    public static final int ENCRYPTION_STATUS_ACTIVATING = 2;

    /**
     * Result code for {@link #setStorageEncryption} and {@link #getStorageEncryptionStatus}:
     * indicating that encryption is active.
     * <p>
     * {@link #getStorageEncryptionStatus} can only return this value for apps targeting API level
     * 23 or lower, or on devices that use Full Disk Encryption.  Support for Full Disk Encryption
     * was entirely removed in API level 33, having been replaced by File Based Encryption.  The
     * result code {@link #ENCRYPTION_STATUS_ACTIVE_PER_USER} is used on devices that use File Based
     * Encryption, except when the app targets API level 23 or lower.
     * <p>
     * {@link #setStorageEncryption} can still return this value for an unrelated reason, but {@link
     * #setStorageEncryption} is deprecated since it doesn't do anything useful.
     */
    public static final int ENCRYPTION_STATUS_ACTIVE = 3;

    /**
     * Result code for {@link #getStorageEncryptionStatus}: indicating that encryption is active,
     * but the encryption key is not cryptographically protected by the user's credentials.
     * <p>
     * This value can only be returned on devices that use Full Disk Encryption.  Support for Full
     * Disk Encryption was entirely removed in API level 33, having been replaced by File Based
     * Encryption.  With File Based Encryption, each user's credential-encrypted storage is always
     * cryptographically protected by the user's credentials.
     */
    public static final int ENCRYPTION_STATUS_ACTIVE_DEFAULT_KEY = 4;

    /**
     * Result code for {@link #getStorageEncryptionStatus}:
     * indicating that encryption is active and the encryption key is tied to the user or profile.
     * <p>
     * This value is only returned to apps targeting API level 24 and above. For apps targeting
     * earlier API levels, {@link #ENCRYPTION_STATUS_ACTIVE} is returned, even if the
     * encryption key is specific to the user or profile.
     */
    public static final int ENCRYPTION_STATUS_ACTIVE_PER_USER = 5;

    /**
     * Result code for {@link #setStorageEncryption} and {@link #getStorageEncryptionStatus}:
     * indicating that encryption is supported, but is not currently active.
     * <p>
     * {@link #getStorageEncryptionStatus} can only return this value on devices that use Full Disk
     * Encryption.  Support for Full Disk Encryption was entirely removed in API level 33, having
     * been replaced by File Based Encryption.  Devices that use File Based Encryption always
     * automatically activate their encryption on first boot.
     * <p>
     * {@link #setStorageEncryption} can still return this value for an unrelated reason, but {@link
     * #setStorageEncryption} is deprecated since it doesn't do anything useful.
     */
    public static final int ENCRYPTION_STATUS_INACTIVE = 1;

    /**
     * Result code for {@link #setStorageEncryption} and {@link #getStorageEncryptionStatus}:
     * indicating that encryption is not supported.
     */
    public static final int ENCRYPTION_STATUS_UNSUPPORTED = 0;

    /**
     * An optional CharSequence providing additional explanation for why the
     * admin is being added.
     *
     * @see #ACTION_ADD_DEVICE_ADMIN
     */
    public static final String EXTRA_ADD_EXPLANATION = "android.app.extra.ADD_EXPLANATION";

    /**
     * An {@code ArrayList<String>} corresponding to the delegation scopes given to an app in the
     * {@link #ACTION_APPLICATION_DELEGATION_SCOPES_CHANGED} broadcast.
     */
    public static final String EXTRA_DELEGATION_SCOPES = "android.app.extra.DELEGATION_SCOPES";

    /**
     * The ComponentName of the administrator component.
     *
     * @see #ACTION_ADD_DEVICE_ADMIN
     */
    public static final String EXTRA_DEVICE_ADMIN = "android.app.extra.DEVICE_ADMIN";

    /**
     * A boolean extra for {@link #ACTION_SET_NEW_PARENT_PROFILE_PASSWORD} requesting that only
     * device password requirement is enforced during the parent profile password enrolment flow.
     * <p> Normally when enrolling password for the parent profile, both the device-wide password
     * requirement (requirement set via {@link #getParentProfileInstance(ComponentName)} instance)
     * and the profile password requirement are enforced, if the profile currently does not have a
     * separate work challenge. By setting this to {@code true}, profile password requirement is
     * explicitly disregarded.
     *
     * @see #isActivePasswordSufficientForDeviceRequirement()
     */
    public static final String EXTRA_DEVICE_PASSWORD_REQUIREMENT_ONLY = "android.app.extra.DEVICE_PASSWORD_REQUIREMENT_ONLY";

    /**
     * An integer indicating the complexity level of the new password an app would like the user to
     * set when launching the action {@link #ACTION_SET_NEW_PASSWORD}.
     *
     * <p>Must be one of
     * <ul>
     *     <li>{@link #PASSWORD_COMPLEXITY_HIGH}
     *     <li>{@link #PASSWORD_COMPLEXITY_MEDIUM}
     *     <li>{@link #PASSWORD_COMPLEXITY_LOW}
     *     <li>{@link #PASSWORD_COMPLEXITY_NONE}
     * </ul>
     *
     * <p>If an invalid value is used, it will be treated as {@link #PASSWORD_COMPLEXITY_NONE}.
     */
    public static final String EXTRA_PASSWORD_COMPLEXITY = "android.app.extra.PASSWORD_COMPLEXITY";

    /**
     * An {@link android.accounts.Account} extra holding the account to migrate during managed
     * profile provisioning.
     *
     * <p>If the account supplied is present in the user, it will be copied, along with its
     * credentials to the managed profile and removed from the user.
     */
    public static final String EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE = "android.app.extra.PROVISIONING_ACCOUNT_TO_MIGRATE";

    /**
     * A {@link android.os.Parcelable} extra of type {@link android.os.PersistableBundle} that is
     * passed directly to the <a href="#devicepolicycontroller">Device Policy Controller</a>
     * after <a href="#managed-provisioning">provisioning</a>.
     *
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#M}, if used with
     * {@link #MIME_TYPE_PROVISIONING_NFC} as part of NFC managed device provisioning, the NFC
     * message should contain a stringified {@link java.util.Properties} instance, whose string
     * properties will be converted into a {@link android.os.PersistableBundle} and passed to the
     * management application after provisioning.
     */
    public static final String EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE = "android.app.extra.PROVISIONING_ADMIN_EXTRAS_BUNDLE";

    /**
     * An {@link ArrayList} of {@link Integer} extra specifying the allowed provisioning modes.
     * <p>This extra will be passed to the admin app's {@link #ACTION_GET_PROVISIONING_MODE}
     * activity, whose result intent must contain {@link #EXTRA_PROVISIONING_MODE} set to one of
     * the values in this array.
     * <p>If the value set to {@link #EXTRA_PROVISIONING_MODE} is not in the array,
     * provisioning will fail.
     * @see #PROVISIONING_MODE_MANAGED_PROFILE
     * @see #PROVISIONING_MODE_FULLY_MANAGED_DEVICE
     */
    public static final String EXTRA_PROVISIONING_ALLOWED_PROVISIONING_MODES = "android.app.extra.PROVISIONING_ALLOWED_PROVISIONING_MODES";

    /**
     * A boolean extra indicating whether offline provisioning should be used.
     *
     * <p>The default value is {@code false}.
     */
    public static final String EXTRA_PROVISIONING_ALLOW_OFFLINE = "android.app.extra.PROVISIONING_ALLOW_OFFLINE";

    /**
     * A ComponentName extra indicating the {@link DeviceAdminReceiver device admin receiver} of
     * the application that will be set as the <a href="#devicepolicycontroller">
     *     Device Policy Controller</a>.
     *
     * <p>If an application starts provisioning directly via an intent with action
     * {@link #ACTION_PROVISION_MANAGED_DEVICE} the package name of this
     * component has to match the package name of the application that started provisioning.
     *
     * <p>This component is set as device owner and active admin when device owner provisioning is
     * started by an intent with action {@link #ACTION_PROVISION_MANAGED_DEVICE} or by an NFC
     * message containing an NFC record with MIME type
     * {@link #MIME_TYPE_PROVISIONING_NFC}. For the NFC record, the component name must be
     * flattened to a string, via {@link ComponentName#flattenToShortString()}.
     */
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME";

    /**
     * An int extra holding a minimum required version code for the device admin package. If the
     * device admin is already installed on the device, it will only be re-downloaded from
     * {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION} if the version of the
     * installed package is less than this version code.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE = "android.app.extra.PROVISIONING_DEVICE_ADMIN_MINIMUM_VERSION_CODE";

    /**
     * A String extra holding the URL-safe base64 encoded SHA-256 hash of the file at download
     * location specified in {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION}.
     *
     * <p>Either this extra or {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM} must be
     * present. The provided checksum must match the checksum of the file at the download
     * location. If the checksum doesn't match an error will be shown to the user and the user will
     * be asked to factory reset the device.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     *
     * <p><strong>Note:</strong> for devices running {@link android.os.Build.VERSION_CODES#LOLLIPOP}
     * and {@link android.os.Build.VERSION_CODES#LOLLIPOP_MR1} only SHA-1 hash is supported.
     * Starting from {@link android.os.Build.VERSION_CODES#M}, this parameter accepts SHA-256 in
     * addition to SHA-1. From {@link android.os.Build.VERSION_CODES#Q}, only SHA-256 hash is
     * supported.
     */
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM";

    /**
     * A String extra holding a http cookie header which should be used in the http request to the
     * url specified in {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER";

    /**
     * A String extra holding a url that specifies the download location of the device admin
     * package. When not provided it is assumed that the device admin package is already installed.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION";

    /**
     * A String extra holding the package name of the application that
     * will be set as <a href="#devicepolicycontroller">Device Policy Controller</a>.
     *
     * <p>When this extra is set, the application must have exactly one
     * {@link DeviceAdminReceiver device admin receiver}. This receiver will be set as the
     * <a href="#devicepolicycontroller">Device Policy Controller</a>.
     *
     * @deprecated Use {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME}.
     */
    @Deprecated
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME = "android.app.extra.PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME";

    /**
     * A String extra holding the URL-safe base64 encoded SHA-256 checksum of any signature of the
     * android package archive at the download location specified in {@link
     * #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION}.
     *
     * <p>The signatures of an android package archive can be obtained using
     * {@link android.content.pm.PackageManager#getPackageArchiveInfo} with flag
     * {@link android.content.pm.PackageManager#GET_SIGNATURES}.
     *
     * <p>Either this extra or {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM} must be
     * present. The provided checksum must match the checksum of any signature of the file at
     * the download location. If the checksum does not match an error will be shown to the user and
     * the user will be asked to factory reset the device.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM = "android.app.extra.PROVISIONING_DEVICE_ADMIN_SIGNATURE_CHECKSUM";

    /**
     * A {@link Bundle}[] extra consisting of list of disclaimer headers and disclaimer contents.
     *
     * <p>Each {@link Bundle} must have both {@link #EXTRA_PROVISIONING_DISCLAIMER_HEADER}
     * as disclaimer header, and {@link #EXTRA_PROVISIONING_DISCLAIMER_CONTENT} as disclaimer
     * content.
     *
     * <p> The extra typically contains one disclaimer from the company of mobile device
     * management application (MDM), and one disclaimer from the organization.
     *
     * <p> Call {@link Bundle#putParcelableArray(String, Parcelable[])} to put the {@link Bundle}[]
     *
     * <p> Maximum 3 key-value pairs can be specified. The rest will be ignored.
     *
     * <p> Can be used in an intent with action {@link #ACTION_PROVISION_MANAGED_PROFILE}. This
     * extra can also be returned by the admin app when performing the admin-integrated
     * provisioning flow as a result of the {@link #ACTION_GET_PROVISIONING_MODE} activity.
     */
    public static final String EXTRA_PROVISIONING_DISCLAIMERS = "android.app.extra.PROVISIONING_DISCLAIMERS";

    /**
     * A {@link Uri} extra pointing to disclaimer content.
     *
     * <h5>The following URI schemes are accepted:</h5>
     * <ul>
     * <li>content ({@link android.content.ContentResolver#SCHEME_CONTENT})</li>
     * <li>android.resource ({@link android.content.ContentResolver#SCHEME_ANDROID_RESOURCE})</li>
     * </ul>
     *
     * <p>Styled text is supported. This is parsed by {@link android.text.Html#fromHtml(String)}
     * and displayed in a {@link android.widget.TextView}.
     *
     * <p>If a <code>content:</code> URI is passed, the intent should also have the
     * flag {@link Intent#FLAG_GRANT_READ_URI_PERMISSION} and the uri should be added to the
     * {@link android.content.ClipData} of the intent.
     *
     * <p>{@link ApplicationInfo#FLAG_SYSTEM System apps} can also insert a
     * disclaimer by declaring an application-level meta-data in {@code AndroidManifest.xml}.
     *
     * <p>For example:
     *
     * <pre>
     *  &lt;meta-data
     *      android:name="android.app.extra.PROVISIONING_DISCLAIMER_CONTENT"
     *      android:resource="@string/disclaimer_content"
     * /&gt;</pre>
     *
     * <p>This must be accompanied with another extra using the key
     * {@link #EXTRA_PROVISIONING_DISCLAIMER_HEADER}.
     */
    public static final String EXTRA_PROVISIONING_DISCLAIMER_CONTENT = "android.app.extra.PROVISIONING_DISCLAIMER_CONTENT";

    /**
     * A String extra of localized disclaimer header.
     *
     * <p>The extra is typically the company name of mobile device management application (MDM)
     * or the organization name.
     *
     * <p>{@link ApplicationInfo#FLAG_SYSTEM System apps} can also insert a disclaimer by declaring
     * an application-level meta-data in {@code AndroidManifest.xml}.
     *
     * <p>For example:
     * <pre>
     *  &lt;meta-data
     *      android:name="android.app.extra.PROVISIONING_DISCLAIMER_HEADER"
     *      android:resource="@string/disclaimer_header"
     * /&gt;</pre>
     *
     * <p>This must be accompanied with another extra using the key
     * {@link #EXTRA_PROVISIONING_DISCLAIMER_CONTENT}.
     */
    public static final String EXTRA_PROVISIONING_DISCLAIMER_HEADER = "android.app.extra.PROVISIONING_DISCLAIMER_HEADER";

    /**
     * @deprecated From {@link android.os.Build.VERSION_CODES#O}, never used while provisioning the
     * device.
     */
    @Deprecated
    public static final String EXTRA_PROVISIONING_EMAIL_ADDRESS = "android.app.extra.PROVISIONING_EMAIL_ADDRESS";

    /**
     * A string extra holding the IMEI (International Mobile Equipment Identity) of the device.
     */
    public static final String EXTRA_PROVISIONING_IMEI = "android.app.extra.PROVISIONING_IMEI";

    /**
     * Boolean extra to indicate that the
     * {@link #EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE migrated account} should be kept.
     *
     * <p>If it's set to {@code true}, the account will not be removed from the user after it is
     * migrated to the newly created user or profile.
     *
     * <p>Defaults to {@code false}
     *
     * @see #EXTRA_PROVISIONING_ACCOUNT_TO_MIGRATE
     */
    public static final String EXTRA_PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION = "android.app.extra.PROVISIONING_KEEP_ACCOUNT_ON_MIGRATION";

    /**
     * A {@code boolean} flag that indicates whether the screen should be on throughout the
     * provisioning flow.
     *
     * <p>This extra can either be passed as an extra to the {@link
     * #ACTION_PROVISION_MANAGED_PROFILE} intent, or it can be returned by the
     * admin app when performing the admin-integrated provisioning flow as a result of the
     * {@link #ACTION_GET_PROVISIONING_MODE} activity.
     *
     * @deprecated from {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, the flag wouldn't
     * be functional. The screen is kept on throughout the provisioning flow.
     */
    @Deprecated
    public static final String EXTRA_PROVISIONING_KEEP_SCREEN_ON = "android.app.extra.PROVISIONING_KEEP_SCREEN_ON";

    /**
     * A Boolean extra that can be used by the mobile device management application to skip the
     * disabling of system apps during provisioning when set to {@code true}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC}, an intent with action
     * {@link #ACTION_PROVISION_MANAGED_PROFILE} that starts profile owner provisioning or
     * set as an extra to the intent result of the {@link #ACTION_GET_PROVISIONING_MODE} activity.
     */
    public static final String EXTRA_PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED = "android.app.extra.PROVISIONING_LEAVE_ALL_SYSTEM_APPS_ENABLED";

    /**
     * A String extra holding the {@link java.util.Locale} that the device will be set to.
     * Format: xx_yy, where xx is the language code, and yy the country code.
     *
     * <p>Use only for device owner provisioning. This extra can be returned by the admin app when
     * performing the admin-integrated provisioning flow as a result of the {@link
     * #ACTION_GET_PROVISIONING_MODE} activity.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_LOCALE = "android.app.extra.PROVISIONING_LOCALE";

    /**
     * A Long extra holding the wall clock time (in milliseconds) to be set on the device's
     * {@link android.app.AlarmManager}.
     *
     * <p>Use only for device owner provisioning. This extra can be returned by the admin app when
     * performing the admin-integrated provisioning flow as a result of the {@link
     * #ACTION_GET_PROVISIONING_MODE} activity.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_LOCAL_TIME = "android.app.extra.PROVISIONING_LOCAL_TIME";

    /**
     * A {@link Uri} extra pointing to a logo image. This image will be shown during the
     * provisioning. If this extra is not passed, a default image will be shown.
     *
     * <p><b>The following URI schemes are accepted:</b>
     * <ul>
     * <li>content ({@link android.content.ContentResolver#SCHEME_CONTENT})</li>
     * <li>android.resource ({@link android.content.ContentResolver#SCHEME_ANDROID_RESOURCE})</li>
     * </ul>
     *
     * <p>It is the responsibility of the caller to provide an image with a reasonable
     * pixel density for the device.
     *
     * <p>If a content: URI is passed, the intent should also have the flag
     * {@link Intent#FLAG_GRANT_READ_URI_PERMISSION} and the uri should be added to the
     * {@link android.content.ClipData} of the intent.
     *
     * @deprecated Logo customization is no longer supported in the
     *             <a href="#managedprovisioning">provisioning flow</a>.
     */
    @Deprecated
    public static final String EXTRA_PROVISIONING_LOGO_URI = "android.app.extra.PROVISIONING_LOGO_URI";

    /**
     * A integer extra indicating the predominant color to show during the provisioning.
     * Refer to {@link android.graphics.Color} for how the color is represented.
     *
     * <p>Use with {@link #ACTION_PROVISION_MANAGED_PROFILE} or
     * {@link #ACTION_PROVISION_MANAGED_DEVICE}.
     *
     * @deprecated Color customization is no longer supported in the provisioning flow.
     */
    @Deprecated
    public static final String EXTRA_PROVISIONING_MAIN_COLOR = "android.app.extra.PROVISIONING_MAIN_COLOR";

    /**
     * An intent extra holding the provisioning mode returned by the administrator.
     * The value of this extra must be one of the values provided in {@link
     * #EXTRA_PROVISIONING_ALLOWED_PROVISIONING_MODES}, which is provided as an intent extra to
     * the admin app's {@link #ACTION_GET_PROVISIONING_MODE} activity.
     *
     * @see #PROVISIONING_MODE_FULLY_MANAGED_DEVICE
     * @see #PROVISIONING_MODE_MANAGED_PROFILE
     */
    public static final String EXTRA_PROVISIONING_MODE = "android.app.extra.PROVISIONING_MODE";

    /**
     * A boolean extra indicating the admin of a fully-managed device opts out of controlling
     * permission grants for sensor-related permissions,
     * see {@link #setPermissionGrantState(ComponentName, String, String, int)}.
     *
     * The default for this extra is {@code false} - by default, the admin of a fully-managed
     * device has the ability to grant sensors-related permissions.
     *
     * <p>Use only for device owner provisioning. This extra can be returned by the
     * admin app when performing the admin-integrated provisioning flow as a result of the
     * {@link #ACTION_GET_PROVISIONING_MODE} activity.
     *
     * <p>This extra may also be provided to the admin app via an intent extra for {@link
     * #ACTION_GET_PROVISIONING_MODE}.
     *
     * @see #ACTION_GET_PROVISIONING_MODE
     */
    public static final String EXTRA_PROVISIONING_SENSORS_PERMISSION_GRANT_OPT_OUT = "android.app.extra.PROVISIONING_SENSORS_PERMISSION_GRANT_OPT_OUT";

    /**
     * A string extra holding the serial number of the device.
     */
    public static final String EXTRA_PROVISIONING_SERIAL_NUMBER = "android.app.extra.PROVISIONING_SERIAL_NUMBER";

    /**
     * A boolean extra that determines whether the provisioning flow should launch the resulting
     * launch intent, if one is supplied by the device policy management role holder via {@link
     * #EXTRA_RESULT_LAUNCH_INTENT}. Default value is {@code false}.
     *
     * <p>If {@code true}, the resulting intent will be launched by the provisioning flow, if one
     * is supplied by the device policy management role holder.
     *
     * <p>If {@code false}, the resulting intent will be returned as {@link
     * #EXTRA_RESULT_LAUNCH_INTENT} to the provisioning initiator, if one is supplied by the device
     * manager role holder. It will be the responsibility of the provisioning initiator to launch
     * this {@link Intent} after provisioning completes.
     *
     * <p>This extra is respected when provided via the provisioning intent actions such as {@link
     * #ACTION_PROVISION_MANAGED_PROFILE}.
     */
    public static final String EXTRA_PROVISIONING_SHOULD_LAUNCH_RESULT_INTENT = "android.app.extra.PROVISIONING_SHOULD_LAUNCH_RESULT_INTENT";

    /**
     * A boolean extra indicating if the education screens from the provisioning flow should be
     * skipped. If unspecified, defaults to {@code false}.
     *
     * <p>This extra can be set in the following ways:
     * <ul>
     * <li>By the admin app when performing the admin-integrated
     * provisioning flow as a result of the {@link #ACTION_GET_PROVISIONING_MODE} activity</li>
     * <li>For managed account enrollment</li>
     * </ul>
     *
     * <p>If the education screens are skipped, it is the admin application's responsibility
     * to display its own user education screens.
     */
    public static final String EXTRA_PROVISIONING_SKIP_EDUCATION_SCREENS = "android.app.extra.PROVISIONING_SKIP_EDUCATION_SCREENS";

    /**
     * A boolean extra indicating whether device encryption can be skipped as part of
     * <a href="#managed-provisioning">provisioning</a>.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} or an intent with action
     * {@link #ACTION_PROVISION_MANAGED_DEVICE} that starts device owner provisioning.
     *
     * <p>From {@link android.os.Build.VERSION_CODES#N} onwards, this is also supported for an
     * intent with action {@link #ACTION_PROVISION_MANAGED_PROFILE}.
     */
    public static final String EXTRA_PROVISIONING_SKIP_ENCRYPTION = "android.app.extra.PROVISIONING_SKIP_ENCRYPTION";

    /**
     * A boolean extra indicating if the user consent steps from the
     * <a href="#managed-provisioning">provisioning flow</a> should be skipped.
     *
     * <p>If unspecified, defaults to {@code false}.
     *
     * @deprecated this extra is no longer relevant as device owners cannot create managed profiles
     */
    @Deprecated
    public static final String EXTRA_PROVISIONING_SKIP_USER_CONSENT = "android.app.extra.PROVISIONING_SKIP_USER_CONSENT";

    /**
     * A String extra holding the time zone {@link android.app.AlarmManager} that the device
     * will be set to.
     *
     * <p>Use only for device owner provisioning. This extra can be returned by the admin app when
     * performing the admin-integrated provisioning flow as a result of the {@link
     * #ACTION_GET_PROVISIONING_MODE} activity.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_TIME_ZONE = "android.app.extra.PROVISIONING_TIME_ZONE";

    /**
     * A boolean extra indicating if mobile data should be used during the provisioning flow
     * for downloading the admin app. If {@link #EXTRA_PROVISIONING_WIFI_SSID} is also specified,
     * wifi network will be used instead.
     *
     * <p>Default value is {@code false}.
     *
     * <p>If this extra is set to {@code true} and {@link #EXTRA_PROVISIONING_WIFI_SSID} is not
     * specified, this extra has different behaviour depending on the way provisioning is triggered:
     * <ul>
     * <li>
     *     For provisioning started via a QR code or an NFC tag, mobile data is always used for
     *     downloading the admin app.
     * </li>
     * <li>
     *     For all other provisioning methods, a mobile data connection check is made at the start
     *     of provisioning. If mobile data is connected at that point, the admin app download will
     *     happen using mobile data. If mobile data is not connected at that point, the end-user
     *     will be asked to pick a wifi network and the admin app download will proceed over wifi.
     * </li>
     * </ul>
     */
    public static final String EXTRA_PROVISIONING_USE_MOBILE_DATA = "android.app.extra.PROVISIONING_USE_MOBILE_DATA";

    /**
     * The anonymous identity of the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}. This is
     * only used if the {@link #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE} is {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_ANONYMOUS_IDENTITY = "android.app.extra.PROVISIONING_WIFI_ANONYMOUS_IDENTITY";

    /**
     * The CA certificate of the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}. This should
     * be an X.509 certificate Base64 encoded DER format, ie. PEM representation of a certificate
     * without header, footer and line breaks. <a href=
     * "https://tools.ietf.org/html/rfc7468"> More information</a> This is only
     * used if the {@link
     * #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE} is {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_CA_CERTIFICATE = "android.app.extra.PROVISIONING_WIFI_CA_CERTIFICATE";

    /**
     * The domain of the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}. This is only used if
     * the {@link #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE} is {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_DOMAIN = "android.app.extra.PROVISIONING_WIFI_DOMAIN";

    /**
     * The EAP method of the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}
     * and could be one of {@code PEAP}, {@code TLS}, {@code TTLS}, {@code PWD}, {@code SIM},
     * {@code AKA} or {@code AKA_PRIME}. This is only used if the
     * {@link #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE} is {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_EAP_METHOD = "android.app.extra.PROVISIONING_WIFI_EAP_METHOD";

    /**
     * A boolean extra indicating whether the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}
     * is hidden or not.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_HIDDEN = "android.app.extra.PROVISIONING_WIFI_HIDDEN";

    /**
     * The identity of the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}. This is only used
     * if the {@link #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE} is {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_IDENTITY = "android.app.extra.PROVISIONING_WIFI_IDENTITY";

    /**
     * A String extra holding the proxy auto-config (PAC) URL for the wifi network in
     * {@link #EXTRA_PROVISIONING_WIFI_SSID}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_PAC_URL = "android.app.extra.PROVISIONING_WIFI_PAC_URL";

    /**
     * A String extra holding the password of the wifi network in
     * {@link #EXTRA_PROVISIONING_WIFI_SSID}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_PASSWORD = "android.app.extra.PROVISIONING_WIFI_PASSWORD";

    /**
     * The phase 2 authentication of the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}
     * and could be one of {@code NONE}, {@code PAP}, {@code MSCHAP}, {@code MSCHAPV2}, {@code GTC},
     * {@code SIM}, {@code AKA} or {@code AKA_PRIME}. This is only used if the
     * {@link #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE} is {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_PHASE2_AUTH = "android.app.extra.PROVISIONING_WIFI_PHASE2_AUTH";

    /**
     * A String extra holding the proxy bypass for the wifi network in
     * {@link #EXTRA_PROVISIONING_WIFI_SSID}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_PROXY_BYPASS = "android.app.extra.PROVISIONING_WIFI_PROXY_BYPASS";

    /**
     * A String extra holding the proxy host for the wifi network in
     * {@link #EXTRA_PROVISIONING_WIFI_SSID}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_PROXY_HOST = "android.app.extra.PROVISIONING_WIFI_PROXY_HOST";

    /**
     * An int extra holding the proxy port for the wifi network in
     * {@link #EXTRA_PROVISIONING_WIFI_SSID}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_PROXY_PORT = "android.app.extra.PROVISIONING_WIFI_PROXY_PORT";

    /**
     * A String extra indicating the security type of the wifi network in
     * {@link #EXTRA_PROVISIONING_WIFI_SSID} and could be one of {@code NONE}, {@code WPA},
     * {@code WEP} or {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_SECURITY_TYPE = "android.app.extra.PROVISIONING_WIFI_SECURITY_TYPE";

    /**
     * A String extra holding the ssid of the wifi network that should be used during nfc device
     * owner provisioning for downloading the mobile device management application.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_SSID = "android.app.extra.PROVISIONING_WIFI_SSID";

    /**
     * The user certificate of the wifi network in {@link #EXTRA_PROVISIONING_WIFI_SSID}. This
     * should be an X.509 certificate and private key Base64 encoded DER format, ie. PEM
     * representation of a certificate and key without header, footer and line breaks. <a href=
     * "https://tools.ietf.org/html/rfc7468"> More information</a> This is only
     * used if the {@link #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE} is {@code EAP}.
     *
     * <p>Use in an NFC record with {@link #MIME_TYPE_PROVISIONING_NFC} that starts device owner
     * provisioning via an NFC bump. It can also be used for QR code provisioning.
     */
    public static final String EXTRA_PROVISIONING_WIFI_USER_CERTIFICATE = "android.app.extra.PROVISIONING_WIFI_USER_CERTIFICATE";

    /**
     * An integer array extra for {@link #ACTION_DEVICE_POLICY_RESOURCE_UPDATED} to indicate which
     * resource IDs (i.e. strings and drawables) have been updated.
     */
    public static final String EXTRA_RESOURCE_IDS = "android.app.extra.RESOURCE_IDS";

    /**
     * An {@code int} extra for {@link #ACTION_DEVICE_POLICY_RESOURCE_UPDATED} to indicate the type
     * of the resource being updated, the type can be {@link #EXTRA_RESOURCE_TYPE_DRAWABLE} or
     * {@link #EXTRA_RESOURCE_TYPE_STRING}
     */
    public static final String EXTRA_RESOURCE_TYPE = "android.app.extra.RESOURCE_TYPE";

    /**
     * A {@code int} value for {@link #EXTRA_RESOURCE_TYPE} to indicate that a resource of type
     * {@link Drawable} is being updated.
     */
    public static final int EXTRA_RESOURCE_TYPE_DRAWABLE = 1;

    /**
     * A {@code int} value for {@link #EXTRA_RESOURCE_TYPE} to indicate that a resource of type
     * {@link String} is being updated.
     */
    public static final int EXTRA_RESOURCE_TYPE_STRING = 2;

    /**
     * An {@link Intent} result extra specifying the {@link Intent} to be launched after
     * provisioning is finalized.
     *
     * <p>If {@link #EXTRA_PROVISIONING_SHOULD_LAUNCH_RESULT_INTENT} is set to {@code false},
     * this result will be supplied as part of the result {@link Intent} for provisioning actions
     * such as {@link #ACTION_PROVISION_MANAGED_PROFILE}. This result will also be supplied as
     * part of the result {@link Intent} for the device policy management role holder provisioning
     * actions.
     */
    public static final String EXTRA_RESULT_LAUNCH_INTENT = "android.app.extra.RESULT_LAUNCH_INTENT";

    /**
     * Flag for {@link #lockNow(int)}: also evict the user's credential encryption key from the
     * keyring. The user's credential will need to be entered again in order to derive the
     * credential encryption key that will be stored back in the keyring for future use.
     * <p>
     * This flag can only be used by a profile owner when locking a managed profile when
     * {@link #getStorageEncryptionStatus} returns {@link #ENCRYPTION_STATUS_ACTIVE_PER_USER}.
     * <p>
     * In order to secure user data, the user will be stopped and restarted so apps should wait
     * until they are next run to perform further actions.
     */
    public static final int FLAG_EVICT_CREDENTIAL_ENCRYPTION_KEY = 1;

    /**
     * Flag used by {@link #addCrossProfileIntentFilter} to allow activities in
     * the managed profile to access intents sent from the parent profile.
     * That is, when an app in the parent profile calls
     * {@link Activity#startActivity(Intent)}, the intent can be resolved by a
     * matching activity in the managed profile.
     */
    public static final int FLAG_MANAGED_CAN_ACCESS_PARENT = 2;

    /**
     * Flag used by {@link #addCrossProfileIntentFilter} to allow activities in
     * the parent profile to access intents sent from the managed profile.
     * That is, when an app in the managed profile calls
     * {@link Activity#startActivity(Intent)}, the intent can be resolved by a
     * matching activity in the parent profile.
     */
    public static final int FLAG_PARENT_CAN_ACCESS_MANAGED = 1;

    /**
     * Specifies that the device should attest its manufacturer details. For use with
     * {@link #generateKeyPair}.
     *
     * @see #generateKeyPair
     */
    public static final int ID_TYPE_BASE_INFO = 1;

    /**
     * Specifies that the device should attest its IMEI. For use with {@link #generateKeyPair}.
     *
     * @see #generateKeyPair
     */
    public static final int ID_TYPE_IMEI = 4;

    /**
     * Specifies that the device should attest using an individual attestation certificate.
     * For use with {@link #generateKeyPair}.
     *
     * @see #generateKeyPair
     */
    public static final int ID_TYPE_INDIVIDUAL_ATTESTATION = 16;

    /**
     * Specifies that the device should attest its MEID. For use with {@link #generateKeyPair}.
     *
     * @see #generateKeyPair
     */
    public static final int ID_TYPE_MEID = 8;

    /**
     * Specifies that the device should attest its serial number. For use with
     * {@link #generateKeyPair}.
     *
     * @see #generateKeyPair
     */
    public static final int ID_TYPE_SERIAL = 2;

    /**
     * Specifies that the calling app should be granted access to the installed credentials
     * immediately. Otherwise, access to the credentials will be gated by user approval.
     * For use with {@link #installKeyPair(ComponentName, PrivateKey, Certificate[], String, int)}
     *
     * @see #installKeyPair(ComponentName, PrivateKey, Certificate[], String, int)
     */
    public static final int INSTALLKEY_REQUEST_CREDENTIALS_ACCESS = 1;

    /**
     * Specifies that a user can select the key via the Certificate Selection prompt.
     * If this flag is not set when calling {@link #installKeyPair}, the key can only be granted
     * access by implementing {@link android.app.admin.DeviceAdminReceiver#onChoosePrivateKeyAlias}.
     * For use with {@link #installKeyPair(ComponentName, PrivateKey, Certificate[], String, int)}
     *
     * @see #installKeyPair(ComponentName, PrivateKey, Certificate[], String, int)
     */
    public static final int INSTALLKEY_SET_USER_SELECTABLE = 2;

    /**
     * Disable all biometric authentication on keyguard secure screens (e.g. PIN/Pattern/Password).
     */
    public static final int KEYGUARD_DISABLE_BIOMETRICS = 416;

    /**
     * Disable face authentication on keyguard secure screens (e.g. PIN/Pattern/Password).
     */
    public static final int KEYGUARD_DISABLE_FACE = 128;

    /**
     * Disable all current and future keyguard customizations.
     */
    public static final int KEYGUARD_DISABLE_FEATURES_ALL = 2147483647;

    /**
     * Widgets are enabled in keyguard
     */
    public static final int KEYGUARD_DISABLE_FEATURES_NONE = 0;

    /**
     * Disable fingerprint authentication on keyguard secure screens (e.g. PIN/Pattern/Password).
     */
    public static final int KEYGUARD_DISABLE_FINGERPRINT = 32;

    /**
     * Disable iris authentication on keyguard secure screens (e.g. PIN/Pattern/Password).
     */
    public static final int KEYGUARD_DISABLE_IRIS = 256;

    /**
     * Disable text entry into notifications on secure keyguard screens (e.g. PIN/Pattern/Password).
     * @deprecated This flag was added in version {@link android.os.Build.VERSION_CODES#N}, but it
     * never had any effect.
     */
    @Deprecated
    public static final int KEYGUARD_DISABLE_REMOTE_INPUT = 64;

    /**
     * Disable the camera on secure keyguard screens (e.g. PIN/Pattern/Password)
     */
    public static final int KEYGUARD_DISABLE_SECURE_CAMERA = 2;

    /**
     * Disable showing all notifications on secure keyguard screens (e.g. PIN/Pattern/Password)
     */
    public static final int KEYGUARD_DISABLE_SECURE_NOTIFICATIONS = 4;

    /**
     * Disable all keyguard shortcuts.
     */
    public static final int KEYGUARD_DISABLE_SHORTCUTS_ALL = 512;

    /**
     * Disable trust agents on secure keyguard screens (e.g. PIN/Pattern/Password).
     * By setting this flag alone, all trust agents are disabled. If the admin then wants to
     * allowlist specific features of some trust agent, {@link #setTrustAgentConfiguration} can be
     * used in conjuction to set trust-agent-specific configurations.
     */
    public static final int KEYGUARD_DISABLE_TRUST_AGENTS = 16;

    /**
     * Only allow redacted notifications on secure keyguard screens (e.g. PIN/Pattern/Password)
     */
    public static final int KEYGUARD_DISABLE_UNREDACTED_NOTIFICATIONS = 8;

    /**
     * Disable all keyguard widgets. Has no effect between {@link
     * android.os.Build.VERSION_CODES#LOLLIPOP} and {@link
     * android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE} (both inclusive), since keyguard widget is
     * only supported on Android versions lower than 5.0 and versions higher than 14.
     */
    public static final int KEYGUARD_DISABLE_WIDGETS_ALL = 1;

    /**
     * Flag used by {@link #createAndManageUser} to specify that the newly created user should skip
     * the disabling of system apps during provisioning.
     */
    public static final int LEAVE_ALL_SYSTEM_APPS_ENABLED = 16;

    /**
     * Enable blocking of non-allowlisted activities from being started into a locked task.
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_BLOCK_ACTIVITY_START_IN_TASK = 64;

    /**
     * Enable the global actions dialog during LockTask mode. This is the dialog that shows up when
     * the user long-presses the power button, for example. Note that the user may not be able to
     * power off the device if this flag is not set.
     *
     * <p>This flag is enabled by default until {@link #setLockTaskFeatures(ComponentName, int)} is
     * called for the first time.
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_GLOBAL_ACTIONS = 16;

    /**
     * Enable the Home button during LockTask mode. Note that if a custom launcher is used, it has
     * to be registered as the default launcher with
     * {@link #addPersistentPreferredActivity(ComponentName, IntentFilter, ComponentName)}, and its
     * package needs to be allowlisted for LockTask with
     * {@link #setLockTaskPackages(ComponentName, String[])}.
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_HOME = 4;

    /**
     * Enable the keyguard during LockTask mode. Note that if the keyguard is already disabled with
     * {@link #setKeyguardDisabled(ComponentName, boolean)}, setting this flag will have no effect.
     * If this flag is not set, the keyguard will not be shown even if the user has a lock screen
     * credential.
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_KEYGUARD = 32;

    /**
     * Disable all configurable SystemUI features during LockTask mode. This includes,
     * <ul>
     *     <li>system info area in the status bar (connectivity icons, clock, etc.)
     *     <li>notifications (including alerts, icons, and the notification shade)
     *     <li>Home button
     *     <li>Recents button and UI
     *     <li>global actions menu (i.e. power button menu)
     *     <li>keyguard
     * </ul>
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_NONE = 0;

    /**
     * Enable notifications during LockTask mode. This includes notification icons on the status
     * bar, heads-up notifications, and the expandable notification shade. Note that the Quick
     * Settings panel remains disabled. This feature flag can only be used in combination with
     * {@link #LOCK_TASK_FEATURE_HOME}. {@link #setLockTaskFeatures(ComponentName, int)}
     * throws an {@link IllegalArgumentException} if this feature flag is defined without
     * {@link #LOCK_TASK_FEATURE_HOME}.
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_NOTIFICATIONS = 2;

    /**
     * Enable the Overview button and the Overview screen during LockTask mode. This feature flag
     * can only be used in combination with {@link #LOCK_TASK_FEATURE_HOME}, and
     * {@link #setLockTaskFeatures(ComponentName, int)} will throw an
     * {@link IllegalArgumentException} if this feature flag is defined without
     * {@link #LOCK_TASK_FEATURE_HOME}.
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_OVERVIEW = 8;

    /**
     * Enable the system info area in the status bar during LockTask mode. The system info area
     * usually occupies the right side of the status bar (although this can differ across OEMs). It
     * includes all system information indicators, such as date and time, connectivity, battery,
     * vibration mode, etc.
     *
     * @see #setLockTaskFeatures(ComponentName, int)
     */
    public static final int LOCK_TASK_FEATURE_SYSTEM_INFO = 1;

    /**
     * Flag used by {@link #createAndManageUser} to specify that the user should be created
     * ephemeral. Ephemeral users will be removed after switching to another user or rebooting the
     * device.
     */
    public static final int MAKE_USER_EPHEMERAL = 2;

    /**
     * This MIME type is used for starting the device owner provisioning.
     *
     * <p>During device owner provisioning a device admin app is set as the owner of the device.
     * A device owner has full control over the device. The device owner can not be modified by the
     * user and the only way of resetting the device is if the device owner app calls a factory
     * reset.
     *
     * <p> A typical use case would be a device that is owned by a company, but used by either an
     * employee or client.
     *
     * <p> The NFC message must be sent to an unprovisioned device.
     *
     * <p>The NFC record must contain a serialized {@link java.util.Properties} object which
     * contains the following properties:
     * <ul>
     * <li>{@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME}</li>
     * <li>{@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_LOCATION}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_DOWNLOAD_COOKIE_HEADER}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_CHECKSUM}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_LOCAL_TIME} (convert to String), optional</li>
     * <li>{@link #EXTRA_PROVISIONING_TIME_ZONE}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_LOCALE}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_SSID}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_HIDDEN} (convert to String), optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_SECURITY_TYPE}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_PASSWORD}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_PROXY_HOST}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_PROXY_PORT} (convert to String), optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_PROXY_BYPASS}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_PAC_URL}, optional</li>
     * <li>{@link #EXTRA_PROVISIONING_ADMIN_EXTRAS_BUNDLE}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#M} </li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_EAP_METHOD}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#Q}</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_PHASE2_AUTH}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#Q}</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_CA_CERTIFICATE}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#Q}</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_USER_CERTIFICATE}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#Q}</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_IDENTITY}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#Q}</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_ANONYMOUS_IDENTITY}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#Q}</li>
     * <li>{@link #EXTRA_PROVISIONING_WIFI_DOMAIN}, optional, supported from {@link
     * android.os.Build.VERSION_CODES#Q}</li></ul>
     *
     * <p>
     * As of {@link android.os.Build.VERSION_CODES#M}, the properties should contain
     * {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME} instead of
     * {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME}, (although specifying only
     * {@link #EXTRA_PROVISIONING_DEVICE_ADMIN_PACKAGE_NAME} is still supported).
     */
    public static final String MIME_TYPE_PROVISIONING_NFC = "application/com.android.managedprovisioning";

    /**
     * Require that MTE be disabled on the device. Can be set by a device owner.
     */
    public static final int MTE_DISABLED = 2;

    /**
     * Require that MTE be enabled on the device, if supported. Can be set by a device owner or a
     * profile owner of an organization-owned managed profile.
     */
    public static final int MTE_ENABLED = 1;

    /**
     * Allow the user to choose whether to enable MTE on the device.
     */
    public static final int MTE_NOT_CONTROLLED_BY_POLICY = 0;

    /**
     * Indicates that nearby streaming is disabled.
     */
    public static final int NEARBY_STREAMING_DISABLED = 1;

    /**
     * Indicates that nearby streaming is enabled.
     */
    public static final int NEARBY_STREAMING_ENABLED = 2;

    /**
     * Indicates that nearby streaming is not controlled by policy, which means nearby streaming is
     * allowed.
     */
    public static final int NEARBY_STREAMING_NOT_CONTROLLED_BY_POLICY = 0;

    /**
     * Indicates that nearby streaming is enabled only to devices offering a comparable level of
     * security, with the same authenticated managed account.
     */
    public static final int NEARBY_STREAMING_SAME_MANAGED_ACCOUNT_ONLY = 3;

    /**
     * Indicates that a {@link UnsafeStateException} was thrown because the operation would distract
     * the driver of the vehicle.
     */
    public static final int OPERATION_SAFETY_REASON_DRIVING_DISTRACTION = 1;

    /**
     * Constant for {@link #getPasswordComplexity()} and
     * {@link #setRequiredPasswordComplexity(int)}.
     * Define the high password complexity band as:
     * <ul>
     * <li>PIN with <b>no</b> repeating (4444) or ordered (1234, 4321, 2468) sequences, length at
     * least 8
     * <li>alphabetic, length at least 6
     * <li>alphanumeric, length at least 6
     * </ul>
     *
     * <p> When returned from {@link #getPasswordComplexity()}, the constant represents
     * the exact complexity band the password is in.
     * When passed to {@link #setRequiredPasswordComplexity(int), it sets the minimum complexity
     * band which the password must meet.
     *
     * @see #PASSWORD_QUALITY_NUMERIC_COMPLEX
     * @see #PASSWORD_QUALITY_ALPHABETIC
     * @see #PASSWORD_QUALITY_ALPHANUMERIC
     */
    public static final int PASSWORD_COMPLEXITY_HIGH = 327680;

    /**
     * Constant for {@link #getPasswordComplexity()} and
     * {@link #setRequiredPasswordComplexity(int)}.
     * Define the low password complexity band as:
     * <ul>
     * <li>pattern
     * <li>PIN with repeating (4444) or ordered (1234, 4321, 2468) sequences
     * </ul>
     *
     * <p> When returned from {@link #getPasswordComplexity()}, the constant represents
     * the exact complexity band the password is in.
     * When passed to {@link #setRequiredPasswordComplexity(int), it sets the minimum complexity
     * band which the password must meet.
     *
     * @see #PASSWORD_QUALITY_SOMETHING
     * @see #PASSWORD_QUALITY_NUMERIC
     */
    public static final int PASSWORD_COMPLEXITY_LOW = 65536;

    /**
     * Constant for {@link #getPasswordComplexity()} and
     * {@link #setRequiredPasswordComplexity(int)}.
     * Define the medium password complexity band as:
     * <ul>
     * <li>PIN with <b>no</b> repeating (4444) or ordered (1234, 4321, 2468) sequences, length at
     * least 4
     * <li>alphabetic, length at least 4
     * <li>alphanumeric, length at least 4
     * </ul>
     *
     * <p> When returned from {@link #getPasswordComplexity()}, the constant represents
     * the exact complexity band the password is in.
     * When passed to {@link #setRequiredPasswordComplexity(int), it sets the minimum complexity
     * band which the password must meet.
     *
     * @see #PASSWORD_QUALITY_NUMERIC_COMPLEX
     * @see #PASSWORD_QUALITY_ALPHABETIC
     * @see #PASSWORD_QUALITY_ALPHANUMERIC
     */
    public static final int PASSWORD_COMPLEXITY_MEDIUM = 196608;

    /**
     * Constant for {@link #getPasswordComplexity()} and
     * {@link #setRequiredPasswordComplexity(int)}: no password.
     *
     * <p> When returned from {@link #getPasswordComplexity()}, the constant represents
     * the exact complexity band the password is in.
     * When passed to {@link #setRequiredPasswordComplexity(int), it sets the minimum complexity
     * band which the password must meet.
     */
    public static final int PASSWORD_COMPLEXITY_NONE = 0;

    /**
     * Constant for {@link #setPasswordQuality}: the user must have entered a
     * password containing at least alphabetic (or other symbol) characters.
     * Note that quality constants are ordered so that higher values are more
     * restrictive.
     */
    public static final int PASSWORD_QUALITY_ALPHABETIC = 262144;

    /**
     * Constant for {@link #setPasswordQuality}: the user must have entered a
     * password containing at least <em>both></em> numeric <em>and</em>
     * alphabetic (or other symbol) characters.  Note that quality constants are
     * ordered so that higher values are more restrictive.
     */
    public static final int PASSWORD_QUALITY_ALPHANUMERIC = 327680;

    /**
     * Constant for {@link #setPasswordQuality}: the policy allows for low-security biometric
     * recognition technology.  This implies technologies that can recognize the identity of
     * an individual to about a 3 digit PIN (false detection is less than 1 in 1,000).
     * Note that quality constants are ordered so that higher values are more restrictive.
     */
    public static final int PASSWORD_QUALITY_BIOMETRIC_WEAK = 32768;

    /**
     * Constant for {@link #setPasswordQuality}: allows the admin to set precisely how many
     * characters of various types the password should contain to satisfy the policy. The admin
     * should set these requirements via {@link #setPasswordMinimumLetters},
     * {@link #setPasswordMinimumNumeric}, {@link #setPasswordMinimumSymbols},
     * {@link #setPasswordMinimumUpperCase}, {@link #setPasswordMinimumLowerCase},
     * {@link #setPasswordMinimumNonLetter}, and {@link #setPasswordMinimumLength}.
     * Note that quality constants are ordered so that higher values are more restrictive.
     */
    public static final int PASSWORD_QUALITY_COMPLEX = 393216;

    /**
     * Constant for {@link #setPasswordQuality}: the user must have entered a
     * password containing at least numeric characters.  Note that quality
     * constants are ordered so that higher values are more restrictive.
     */
    public static final int PASSWORD_QUALITY_NUMERIC = 131072;

    /**
     * Constant for {@link #setPasswordQuality}: the user must have entered a
     * password containing at least numeric characters with no repeating (4444)
     * or ordered (1234, 4321, 2468) sequences.  Note that quality
     * constants are ordered so that higher values are more restrictive.
     */
    public static final int PASSWORD_QUALITY_NUMERIC_COMPLEX = 196608;

    /**
     * Constant for {@link #setPasswordQuality}: the policy requires some kind
     * of password or pattern, but doesn't care what it is. Note that quality constants
     * are ordered so that higher values are more restrictive.
     */
    public static final int PASSWORD_QUALITY_SOMETHING = 65536;

    /**
     * Constant for {@link #setPasswordQuality}: the policy has no requirements
     * for the password.  Note that quality constants are ordered so that higher
     * values are more restrictive.
     */
    public static final int PASSWORD_QUALITY_UNSPECIFIED = 0;

    /**
     * Runtime permission state: The user can manage the permission
     * through the UI.
     */
    public static final int PERMISSION_GRANT_STATE_DEFAULT = 0;

    /**
     * Runtime permission state: The permission is denied to the app
     * and the user cannot manage the permission through the UI.
     */
    public static final int PERMISSION_GRANT_STATE_DENIED = 2;

    /**
     * Runtime permission state: The permission is granted to the app
     * and the user cannot manage the permission through the UI.
     */
    public static final int PERMISSION_GRANT_STATE_GRANTED = 1;

    /**
     * Permission policy to always deny new permission requests for runtime permissions.
     * Already granted or denied permissions are not affected by this.
     */
    public static final int PERMISSION_POLICY_AUTO_DENY = 2;

    /**
     * Permission policy to always grant new permission requests for runtime permissions.
     * Already granted or denied permissions are not affected by this.
     */
    public static final int PERMISSION_POLICY_AUTO_GRANT = 1;

    /**
     * Permission policy to prompt user for new permission requests for runtime permissions.
     * Already granted or denied permissions are not affected by this.
     */
    public static final int PERMISSION_POLICY_PROMPT = 0;

    /**
     * Return value for {@link #getPersonalAppsSuspendedReasons} when personal apps are not
     * suspended.
     */
    public static final int PERSONAL_APPS_NOT_SUSPENDED = 0;

    /**
     * Flag for {@link #getPersonalAppsSuspendedReasons} return value. Set when personal
     * apps are suspended by an admin explicitly via {@link #setPersonalAppsSuspended}.
     */
    public static final int PERSONAL_APPS_SUSPENDED_EXPLICITLY = 1;

    /**
     * Flag for {@link #getPersonalAppsSuspendedReasons} return value. Set when personal apps are
     * suspended by framework because managed profile was off for longer than allowed by policy.
     * @see #setManagedProfileMaximumTimeOff
     */
    public static final int PERSONAL_APPS_SUSPENDED_PROFILE_TIMEOUT = 2;

    /**
     * Constant to indicate the feature of disabling the camera. Used as argument to
     * {@link #createAdminSupportIntent(String)}.
     * @see #setCameraDisabled(ComponentName, boolean)
     */
    public static final String POLICY_DISABLE_CAMERA = "policy_disable_camera";

    /**
     * Constant to indicate the feature of disabling screen captures. Used as argument to
     * {@link #createAdminSupportIntent(String)}.
     * @see #setScreenCaptureDisabled(ComponentName, boolean)
     */
    public static final String POLICY_DISABLE_SCREEN_CAPTURE = "policy_disable_screen_capture";

    /**
     * Specifies that Private DNS was turned off completely.
     */
    public static final int PRIVATE_DNS_MODE_OFF = 1;

    /**
     * Specifies that the device owner requested opportunistic DNS over TLS
     */
    public static final int PRIVATE_DNS_MODE_OPPORTUNISTIC = 2;

    /**
     * Specifies that the device owner configured a specific host to use for Private DNS.
     */
    public static final int PRIVATE_DNS_MODE_PROVIDER_HOSTNAME = 3;

    /**
     * Specifies that the Private DNS setting is in an unknown state.
     */
    public static final int PRIVATE_DNS_MODE_UNKNOWN = 0;

    /**
     * General failure to set the Private DNS mode, not due to one of the reasons listed above.
     */
    public static final int PRIVATE_DNS_SET_ERROR_FAILURE_SETTING = 2;

    /**
     * If the {@code privateDnsHost} provided was of a valid hostname but that host was found
     * to not support DNS-over-TLS.
     */
    public static final int PRIVATE_DNS_SET_ERROR_HOST_NOT_SERVING = 1;

    /**
     * The selected mode has been set successfully. If the mode is
     * {@code PRIVATE_DNS_MODE_PROVIDER_HOSTNAME} then it implies the supplied host is valid
     * and reachable.
     */
    public static final int PRIVATE_DNS_SET_NO_ERROR = 0;

    /**
     * The provisioning mode for fully managed device.
     */
    public static final int PROVISIONING_MODE_FULLY_MANAGED_DEVICE = 1;

    /**
     * The provisioning mode for managed profile.
     */
    public static final int PROVISIONING_MODE_MANAGED_PROFILE = 2;

    /**
     * The provisioning mode for a managed profile on a personal device.
     * <p>This mode is only available when the provisioning initiator has explicitly instructed the
     * provisioning flow to support managed profile on a personal device provisioning. In that case,
     * {@link #PROVISIONING_MODE_MANAGED_PROFILE} corresponds to an organization-owned managed
     * profile, whereas this constant corresponds to a personally-owned managed profile.
     *
     * @see #EXTRA_PROVISIONING_MODE
     */
    public static final int PROVISIONING_MODE_MANAGED_PROFILE_ON_PERSONAL_DEVICE = 3;

    /**
     * Flag for {@link #resetPasswordWithToken} and {@link #resetPassword}: don't ask for user
     * credentials on device boot.
     * If the flag is set, the device can be booted without asking for user password.
     * The absence of this flag does not change the current boot requirements. This flag
     * can be set by the device owner only. If the app is not the device owner, the flag
     * is ignored. Once the flag is set, it cannot be reverted back without resetting the
     * device to factory defaults.
     */
    public static final int RESET_PASSWORD_DO_NOT_ASK_CREDENTIALS_ON_BOOT = 2;

    /**
     * Flag for {@link #resetPasswordWithToken} and {@link #resetPassword}: don't allow other admins
     * to change the password again until the user has entered it.
     */
    public static final int RESET_PASSWORD_REQUIRE_ENTRY = 1;

    /**
     * Flag used by {@link #createAndManageUser} to skip setup wizard after creating a new user.
     */
    public static final int SKIP_SETUP_WIZARD = 1;

    /**
     * Constant for {@link #getMinimumRequiredWifiSecurityLevel()} and
     * {@link #setMinimumRequiredWifiSecurityLevel(int)}: enterprise 192 bit network.
     *
     * <p> When returned from {@link #getMinimumRequiredWifiSecurityLevel()}, the constant
     * represents the current minimum security level required.
     * When passed to {@link #setMinimumRequiredWifiSecurityLevel(int)}, it sets the
     * minimum security level a Wi-Fi network must meet.
     *
     * @see #WIFI_SECURITY_OPEN
     * @see #WIFI_SECURITY_PERSONAL
     * @see #WIFI_SECURITY_ENTERPRISE_EAP
     */
    public static final int WIFI_SECURITY_ENTERPRISE_192 = 3;

    /**
     * Constant for {@link #getMinimumRequiredWifiSecurityLevel()} and
     * {@link #setMinimumRequiredWifiSecurityLevel(int)}: enterprise EAP network.
     *
     * <p> When returned from {@link #getMinimumRequiredWifiSecurityLevel()}, the constant
     * represents the current minimum security level required.
     * When passed to {@link #setMinimumRequiredWifiSecurityLevel(int)}, it sets the
     * minimum security level a Wi-Fi network must meet.
     *
     * @see #WIFI_SECURITY_OPEN
     * @see #WIFI_SECURITY_PERSONAL
     * @see #WIFI_SECURITY_ENTERPRISE_192
     */
    public static final int WIFI_SECURITY_ENTERPRISE_EAP = 2;

    /**
     * Constant for {@link #getMinimumRequiredWifiSecurityLevel()} and
     * {@link #setMinimumRequiredWifiSecurityLevel(int)}: no minimum security level.
     *
     * <p> When returned from {@link #getMinimumRequiredWifiSecurityLevel()}, the constant
     * represents the current minimum security level required.
     * When passed to {@link #setMinimumRequiredWifiSecurityLevel(int)}, it sets the
     * minimum security level a Wi-Fi network must meet.
     *
     * @see #WIFI_SECURITY_PERSONAL
     * @see #WIFI_SECURITY_ENTERPRISE_EAP
     * @see #WIFI_SECURITY_ENTERPRISE_192
     */
    public static final int WIFI_SECURITY_OPEN = 0;

    /**
     * Constant for {@link #getMinimumRequiredWifiSecurityLevel()} and
     * {@link #setMinimumRequiredWifiSecurityLevel(int)}: personal network such as WEP, WPA2-PSK.
     *
     * <p> When returned from {@link #getMinimumRequiredWifiSecurityLevel()}, the constant
     * represents the current minimum security level required.
     * When passed to {@link #setMinimumRequiredWifiSecurityLevel(int)}, it sets the
     * minimum security level a Wi-Fi network must meet.
     *
     * @see #WIFI_SECURITY_OPEN
     * @see #WIFI_SECURITY_ENTERPRISE_EAP
     * @see #WIFI_SECURITY_ENTERPRISE_192
     */
    public static final int WIFI_SECURITY_PERSONAL = 1;

    /**
     * Flag for {@link #wipeData(int)}: also erase the device's eUICC data.
     */
    public static final int WIPE_EUICC = 4;

    /**
     * Flag for {@link #wipeData(int)}: also erase the device's adopted external storage (such as
     * adopted SD cards).
     * @see <a href="{@docRoot}about/versions/marshmallow/android-6.0.html#adoptable-storage">
     *     Adoptable Storage Devices</a>
     */
    public static final int WIPE_EXTERNAL_STORAGE = 1;

    /**
     * Flag for {@link #wipeData(int)}: also erase the factory reset protection
     * data.
     *
     * <p>This flag may only be set by device owner admins; if it is set by
     * other admins a {@link SecurityException} will be thrown.
     */
    public static final int WIPE_RESET_PROTECTION_DATA = 2;

    /**
     * Flag for {@link #wipeData(int)}: won't show reason for wiping to the user.
     */
    public static final int WIPE_SILENTLY = 8;

    private DevicePolicyManager() {
    }

    /**
     * Callback used in {@link #installSystemUpdate} to indicate that there was an error while
     * trying to install an update.
     */
    public static final class InstallSystemUpdateCallback {
        /**
         * Represents the battery being too low to apply an update.
         */
        public static final int UPDATE_ERROR_BATTERY_LOW = 5;

        /**
         * Represents that the file could not be found.
         */
        public static final int UPDATE_ERROR_FILE_NOT_FOUND = 4;

        /**
         * Represents the update file being intended for different OS version.
         */
        public static final int UPDATE_ERROR_INCORRECT_OS_VERSION = 2;

        /**
         * Represents an unknown error while trying to install an update.
         */
        public static final int UPDATE_ERROR_UNKNOWN = 1;

        /**
         * Represents the update file being wrong; e.g. payloads are mismatched, or the wrong
         * compression method is used.
         */
        public static final int UPDATE_ERROR_UPDATE_FILE_INVALID = 3;

        private InstallSystemUpdateCallback() {
        }
    }
}
