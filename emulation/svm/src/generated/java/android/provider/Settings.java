package android.provider;

/**
 * The Settings provider contains global system-level device preferences.
 */
public final class Settings {
    /**
     * Activity Action: Show settings for accessibility modules.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_ACCESSIBILITY_SETTINGS = "android.settings.ACCESSIBILITY_SETTINGS";

    /**
     * Activity Action: Show add account screen for creating a new account.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * The account types available to add may be restricted by adding an {@link #EXTRA_AUTHORITIES}
     * extra to the Intent with one or more syncable content provider's authorities.  Only account
     * types which can sync with that content provider will be offered to the user.
     * <p>
     * Account types can also be filtered by adding an {@link #EXTRA_ACCOUNT_TYPES} extra to the
     * Intent with one or more account types.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_ADD_ACCOUNT = "android.settings.ADD_ACCOUNT_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of Advanced memory protection.
     * <p>
     * Memory Tagging Extension (MTE) is a CPU extension that allows to protect against certain
     * classes of security problems at a small runtime performance cost overhead.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_ADVANCED_MEMORY_PROTECTION_SETTINGS = "android.settings.ADVANCED_MEMORY_PROTECTION_SETTINGS";

    /**
     * Activity Action: Show settings to allow entering/exiting airplane mode.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_AIRPLANE_MODE_SETTINGS = "android.settings.AIRPLANE_MODE_SETTINGS";

    /**
     * Activity Action: Show app listing settings, filtered by those that send notifications.
     */
    public static final String ACTION_ALL_APPS_NOTIFICATION_SETTINGS = "android.settings.ALL_APPS_NOTIFICATION_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of APNs.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     *
     * <p class="note">
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     */
    public static final String ACTION_APN_SETTINGS = "android.settings.APN_SETTINGS";

    /**
     * Activity Action: Show screen of details about a particular application.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: The Intent's data URI specifies the application package name
     * to be shown, with the "package" scheme.  That is "package:com.my.app".
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APPLICATION_DETAILS_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of application
     * development-related settings.  As of
     * {@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR1} this action is
     * a required part of the platform.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APPLICATION_DEVELOPMENT_SETTINGS = "android.settings.APPLICATION_DEVELOPMENT_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of application-related settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APPLICATION_SETTINGS = "android.settings.APPLICATION_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of per application locale.
     * <p>
     * Input: The Intent's data URI can specify the application package name to directly invoke the
     * app locale details GUI specific to the package name.
     * For example "package:com.my.app".
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APP_LOCALE_SETTINGS = "android.settings.APP_LOCALE_SETTINGS";

    /**
     * Activity Action: Show notification bubble settings for a single app.
     * See {@link NotificationManager#getBubblePreference()}.
     * <p>
     *     Input: {@link #EXTRA_APP_PACKAGE}, the package to display.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APP_NOTIFICATION_BUBBLE_SETTINGS = "android.settings.APP_NOTIFICATION_BUBBLE_SETTINGS";

    /**
     * Activity Action: Show the permission screen for allowing apps to post promoted notifications.
     * Properly formatted priority notifications are elevated in appearance. For example they may be
     * able to use colors, have richer progress bars, show as chips in the status bar, and/or
     * permanently appear on always-on-displays. This functionality is intended to be reserved for
     * user initiated ongoing activities like navigation, phone calls, and ride sharing.
     *
     * <p>
     *     Input: {@link #EXTRA_APP_PACKAGE}, the package to display.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APP_NOTIFICATION_PROMOTION_SETTINGS = "android.settings.APP_NOTIFICATION_PROMOTION_SETTINGS";

    /**
     * Activity Action: Show notification settings for a single app.
     * <p>
     *     Input: {@link #EXTRA_APP_PACKAGE}, the package to display.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APP_NOTIFICATION_SETTINGS = "android.settings.APP_NOTIFICATION_SETTINGS";

    /**
     * Activity Action: Show the "Open by Default" page in a particular application's details page.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * Input: The Intent's data URI specifies the application package name
     * to be shown, with the "package" scheme. That is "package:com.my.app".
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APP_OPEN_BY_DEFAULT_SETTINGS = "android.settings.APP_OPEN_BY_DEFAULT_SETTINGS";

    /**
     * Activity action: Show Settings app search UI when this action is available for device.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_APP_SEARCH_SETTINGS = "android.settings.APP_SEARCH_SETTINGS";

    /**
     * Activity Action: Show screen for controlling app usage properties for an app.
     * Input: Intent's extra {@link android.content.Intent#EXTRA_PACKAGE_NAME} must specify the
     * application package name.
     * Output: Nothing.
     */
    public static final String ACTION_APP_USAGE_SETTINGS = "android.settings.action.APP_USAGE_SETTINGS";

    /**
     * Activity Action: Shows the settings page for an {@link AutomaticZenRule} mode.
     * <p>
     * Users can change the behavior of the mode when it's activated and access the owning app's
     * additional configuration screen, where triggering criteria can be modified (see
     * {@link AutomaticZenRule#setConfigurationActivity(ComponentName)}).
     * <p>
     * A matching Activity will only be found if
     * {@link NotificationManager#areAutomaticZenRulesUserManaged()} is true.
     * <p>
     * Input: The id of the rule, provided in the {@link #EXTRA_AUTOMATIC_ZEN_RULE_ID} extra.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_AUTOMATIC_ZEN_RULE_SETTINGS = "android.settings.AUTOMATIC_ZEN_RULE_SETTINGS";

    /**
     * Activity Action: Show Auto Rotate configuration settings.
     */
    public static final String ACTION_AUTO_ROTATE_SETTINGS = "android.settings.AUTO_ROTATE_SETTINGS";

    /**
     * Activity Action: Show battery saver settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard
     * against this.
     */
    public static final String ACTION_BATTERY_SAVER_SETTINGS = "android.settings.BATTERY_SAVER_SETTINGS";

    /**
     * Activity Action: Show settings to enroll biometrics, and setup PIN/Pattern/Pass if
     * necessary. By default, this prompts the user to enroll biometrics with strength
     * Weak or above, as defined by the CDD. Only biometrics that meet or exceed Strong, as defined
     * in the CDD are allowed to participate in Keystore operations.
     * <p>
     * Input: extras {@link #EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED} as an integer, with
     * constants defined in {@link android.hardware.biometrics.BiometricManager.Authenticators},
     * e.g. {@link android.hardware.biometrics.BiometricManager.Authenticators#BIOMETRIC_STRONG}.
     * If not specified, the default behavior is
     * {@link android.hardware.biometrics.BiometricManager.Authenticators#BIOMETRIC_WEAK}.
     * <p>
     * Output: Nothing. Note that callers should still check
     * {@link android.hardware.biometrics.BiometricManager#canAuthenticate(int)}
     * afterwards to ensure that the user actually completed enrollment.
     */
    public static final String ACTION_BIOMETRIC_ENROLL = "android.settings.BIOMETRIC_ENROLL";

    /**
     * Activity Action: Show settings to allow configuration of Bluetooth.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_BLUETOOTH_SETTINGS = "android.settings.BLUETOOTH_SETTINGS";

    /**
     * Activity Action: Show settings for video captioning.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard
     * against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_CAPTIONING_SETTINGS = "android.settings.CAPTIONING_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of cast endpoints.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_CAST_SETTINGS = "android.settings.CAST_SETTINGS";

    /**
     * Activity Action: Show notification settings for a single {@link NotificationChannel}.
     * <p>
     *     Input: {@link #EXTRA_APP_PACKAGE}, the package containing the channel to display.
     *     Input: {@link #EXTRA_CHANNEL_ID}, the id of the channel to display.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_CHANNEL_NOTIFICATION_SETTINGS = "android.settings.CHANNEL_NOTIFICATION_SETTINGS";

    /**
     * Activity Action: Show the automatic do not disturb rule listing page
     * <p>
     *     Users can add, enable, disable, and remove automatic do not disturb rules from this
     *     screen. See {@link NotificationManager#addAutomaticZenRule(AutomaticZenRule)} for more
     *     details.
     * </p>
     * <p>
     *     Input: Nothing
     *     Output: Nothing
     * </p>
     */
    public static final String ACTION_CONDITION_PROVIDER_SETTINGS = "android.settings.ACTION_CONDITION_PROVIDER_SETTINGS";

    /**
     * Activity Action: Show screen that let user enable a Credential Manager provider.
     * <p>
     * Input: Intent's data URI set with an application package name, using the
     * "package" schema (like "package:com.my.app").
     *
     * <p>
     * Output: {@link android.app.Activity#RESULT_OK} if user selected a provider belonging
     * to the caller package.
     * <p>
     * <b>NOTE: </b> Applications should call
     * {@link android.credentials.CredentialManager#isEnabledCredentialProviderService(
     * ComponentName)} and only use this action to start an activity if they return {@code false}.
     */
    public static final String ACTION_CREDENTIAL_PROVIDER = "android.settings.CREDENTIAL_PROVIDER";

    /**
     * Activity Action: Show settings for selection of 2G/3G.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_DATA_ROAMING_SETTINGS = "android.settings.DATA_ROAMING_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of data and view data usage.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_DATA_USAGE_SETTINGS = "android.settings.DATA_USAGE_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of date and time.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_DATE_SETTINGS = "android.settings.DATE_SETTINGS";

    /**
     * Activity Action: Show general device information settings (serial
     * number, software version, phone number, etc.).
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing
     */
    public static final String ACTION_DEVICE_INFO_SETTINGS = "android.settings.DEVICE_INFO_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of display.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_DISPLAY_SETTINGS = "android.settings.DISPLAY_SETTINGS";

    /**
     * Activity Action: Show Daydream settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     * @see android.service.dreams.DreamService
     */
    public static final String ACTION_DREAM_SETTINGS = "android.settings.DREAM_SETTINGS";

    /**
     * Activity Action: Show settings to enroll fingerprints, and setup PIN/Pattern/Pass if
     * necessary.
     * @deprecated See {@link #ACTION_BIOMETRIC_ENROLL}.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    @Deprecated
    public static final String ACTION_FINGERPRINT_ENROLL = "android.settings.FINGERPRINT_ENROLL";

    /**
     * Activity Action: Show first day of week configuration settings.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_FIRST_DAY_OF_WEEK_SETTINGS = "android.settings.FIRST_DAY_OF_WEEK_SETTINGS";

    /**
     * Activity Action: Show settings to configure the hardware keyboard.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_HARD_KEYBOARD_SETTINGS = "android.settings.HARD_KEYBOARD_SETTINGS";

    /**
     * Activity Action: Show Home selection settings. If there are multiple activities
     * that can satisfy the {@link Intent#CATEGORY_HOME} intent, this screen allows you
     * to pick your preferred activity.
     */
    public static final String ACTION_HOME_SETTINGS = "android.settings.HOME_SETTINGS";

    /**
     * Activity Action: Show screen for controlling background data
     * restrictions for a particular application.
     * <p>
     * Input: Intent's data URI set with an application name, using the
     * "package" schema (like "package:com.my.app").
     *
     * <p>
     * Output: Nothing.
     * <p>
     * Applications can also use {@link android.net.ConnectivityManager#getRestrictBackgroundStatus
     * ConnectivityManager#getRestrictBackgroundStatus()} to determine the
     * status of the background data restrictions for them.
     *
     * <p class="note">
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     */
    public static final String ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS = "android.settings.IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS";

    /**
     * Activity Action: Show screen for controlling which apps can ignore battery optimizations.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     * <p>
     * You can use {@link android.os.PowerManager#isIgnoringBatteryOptimizations
     * PowerManager.isIgnoringBatteryOptimizations()} to determine if an application is
     * already ignoring optimizations.  You can use
     * {@link #ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS} to ask the user to put you
     * on this list.
     */
    public static final String ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS = "android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS";

    /**
     * Activity Action: Show settings to configure input methods, in particular
     * allowing the user to enable input methods.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_INPUT_METHOD_SETTINGS = "android.settings.INPUT_METHOD_SETTINGS";

    /**
     * Activity Action: Show settings to enable/disable input method subtypes.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * To tell which input method's subtypes are displayed in the settings, add
     * {@link #EXTRA_INPUT_METHOD_ID} extra to this Intent with the input method id.
     * If there is no extra in this Intent, subtypes from all installed input methods
     * will be displayed in the settings.
     *
     * @see android.view.inputmethod.InputMethodInfo#getId
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_INPUT_METHOD_SUBTYPE_SETTINGS = "android.settings.INPUT_METHOD_SUBTYPE_SETTINGS";

    /**
     * Activity Action: Show settings for internal storage.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_INTERNAL_STORAGE_SETTINGS = "android.settings.INTERNAL_STORAGE_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of locale.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: The optional {@code #EXTRA_EXPLICIT_LOCALES} with language tags that contains locales
     * to limit available locales. This is only supported when device is under demo mode.
     * If intent does not contain this extra, it will show system supported locale list.
     * <br/>
     * If {@code #EXTRA_EXPLICIT_LOCALES} contain a unsupported locale, it will still show this
     * locale on list, but may not be supported by the devcie.
     *
     * Output: Nothing.
     */
    public static final String ACTION_LOCALE_SETTINGS = "android.settings.LOCALE_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of current location
     * sources.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_LOCATION_SOURCE_SETTINGS = "android.settings.LOCATION_SOURCE_SETTINGS";

    /**
     * Activity Action: Show settings to manage all applications.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS = "android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS";

    /**
     * Activity Action: Show screen for controlling which apps have access to manage external
     * storage.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * If you want to control a specific app's access to manage external storage, use
     * {@link #ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION} instead.
     * <p>
     * Output: Nothing.
     * @see #ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
     */
    public static final String ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION = "android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION";

    /**
     * Activity Action: Show settings to manage all SIM profiles.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_ALL_SIM_PROFILES_SETTINGS = "android.settings.MANAGE_ALL_SIM_PROFILES_SETTINGS";

    /**
     * Activity Action: Show settings to manage installed applications.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_APPLICATIONS_SETTINGS = "android.settings.MANAGE_APPLICATIONS_SETTINGS";

    /**
     * Activity Action: Show screen for controlling if the app specified in the data URI of the
     * intent can manage external storage.
     * <p>
     * Launching the corresponding activity requires the permission
     * {@link Manifest.permission#MANAGE_EXTERNAL_STORAGE}.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * Input: The Intent's data URI MUST specify the application package name whose ability of
     * managing external storage you want to control.
     * For example "package:com.my.app".
     * <p>
     * Output: Nothing.
     * @see #ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
     */
    public static final String ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION = "android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION";

    /**
     * Activity Action: Show screen for controlling whether an app can send full screen intents.
     * <p>
     *     Input: the intent's data URI must specify the application package name for which you want
     *     to manage full screen intents.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_APP_USE_FULL_SCREEN_INTENT = "android.settings.MANAGE_APP_USE_FULL_SCREEN_INTENT";

    /**
     * Activity Action: Show Default apps settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_DEFAULT_APPS_SETTINGS = "android.settings.MANAGE_DEFAULT_APPS_SETTINGS";

    /**
     * Activity Action: Show screen for controlling which apps can draw on top of other apps.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * Input: Optionally, in versions of Android prior to {@link android.os.Build.VERSION_CODES#R},
     * the Intent's data URI can specify the application package name to directly invoke the
     * management GUI specific to the package name.
     * For example "package:com.my.app".
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_OVERLAY_PERMISSION = "android.settings.action.MANAGE_OVERLAY_PERMISSION";

    /**
     * Activity action: Launch UI to manage a setting restricted by supervisors.
     * <p>
     * Input: {@link #EXTRA_SUPERVISOR_RESTRICTED_SETTING_KEY} specifies what setting to open.
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     */
    public static final String ACTION_MANAGE_SUPERVISOR_RESTRICTED_SETTING = "android.settings.MANAGE_SUPERVISOR_RESTRICTED_SETTING";

    /**
     * Activity Action: Show settings to allow configuration of trusted external sources
     *
     * Input: Optionally, the Intent's data URI can specify the application package name to
     * directly invoke the management GUI specific to the package name. For example
     * "package:com.my.app".
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_UNKNOWN_APP_SOURCES = "android.settings.MANAGE_UNKNOWN_APP_SOURCES";

    /**
     * Activity Action: Show screen for controlling which apps are allowed to write/modify
     * system settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Optionally, the Intent's data URI can specify the application package name to
     * directly invoke the management GUI specific to the package name. For example
     * "package:com.my.app".
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MANAGE_WRITE_SETTINGS = "android.settings.action.MANAGE_WRITE_SETTINGS";

    /**
     * Activity Action: Show measurement system configuration settings.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MEASUREMENT_SYSTEM_SETTINGS = "android.settings.MEASUREMENT_SYSTEM_SETTINGS";

    /**
     * Activity Action: Show settings for memory card storage.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_MEMORY_CARD_SETTINGS = "android.settings.MEMORY_CARD_SETTINGS";

    /**
     * Activity Action: Show settings for selecting the network operator.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * The subscription ID of the subscription for which available network operators should be
     * displayed may be optionally specified with {@link #EXTRA_SUB_ID}.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_NETWORK_OPERATOR_SETTINGS = "android.settings.NETWORK_OPERATOR_SETTINGS";

    /**
     * Activity Action: Show NFC Sharing settings.
     * <p>
     * This shows UI that allows NDEF Push (Android Beam) to be turned on or
     * off.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing
     */
    public static final String ACTION_NFCSHARING_SETTINGS = "android.settings.NFCSHARING_SETTINGS";

    /**
     * Activity Action: Show NFC Tap & Pay settings
     * <p>
     * This shows UI that allows the user to configure Tap&Pay
     * settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing
     */
    public static final String ACTION_NFC_PAYMENT_SETTINGS = "android.settings.NFC_PAYMENT_SETTINGS";

    /**
     * Activity Action: Show NFC settings.
     * <p>
     * This shows UI that allows NFC to be turned on or off.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing
     * @see android.nfc.NfcAdapter#isEnabled()
     */
    public static final String ACTION_NFC_SETTINGS = "android.settings.NFC_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of Night display.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_NIGHT_DISPLAY_SETTINGS = "android.settings.NIGHT_DISPLAY_SETTINGS";

    /**
     * Activity Action: Show Notification assistant settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_NOTIFICATION_ASSISTANT_SETTINGS = "android.settings.NOTIFICATION_ASSISTANT_SETTINGS";

    /**
     * Activity Action: Show notification listener permission settings page for app.
     * <p>
     * Users can grant and deny access to notifications for a {@link ComponentName} from here.
     * See
     * {@link android.app.NotificationManager#isNotificationListenerAccessGranted(ComponentName)}
     * for more details.
     * <p>
     * Input: The extra {@link #EXTRA_NOTIFICATION_LISTENER_COMPONENT_NAME} containing the name
     * of the component to grant or revoke notification listener access to.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_NOTIFICATION_LISTENER_DETAIL_SETTINGS = "android.settings.NOTIFICATION_LISTENER_DETAIL_SETTINGS";

    /**
     * Activity Action: Show Notification listener settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     * @see android.service.notification.NotificationListenerService
     */
    public static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    /**
     * Activity Action: Show Notification Policy access settings.
     * <p>
     * Users can grant and deny access to Notification Policy (DND / Modes) configuration
     * from here. Managed profiles cannot grant Notification Policy access.
     * See {@link android.app.NotificationManager#isNotificationPolicyAccessGranted()} for more
     * details.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     *
     * <p class="note">
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     */
    public static final String ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS = "android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS";

    /**
     * Activity Action: Show the top level print settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_PRINT_SETTINGS = "android.settings.ACTION_PRINT_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of privacy options, i.e. permission
     * manager, privacy dashboard, privacy controls and more.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_PRIVACY_SETTINGS = "android.settings.PRIVACY_SETTINGS";

    /**
     * Activity Action: Show setting page to process a Wi-Fi Easy Connect (aka DPP) URI and start
     * configuration. This intent should be used when you want to use this device to take on the
     * configurator role for an IoT/other device. When provided with a valid DPP URI
     * string, Settings will open a Wi-Fi selection screen for the user to indicate which network
     * they would like to configure the device specified in the DPP URI string and
     * carry them through the rest of the flow for provisioning the device.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure to safeguard against this by
     * checking {@link WifiManager#isEasyConnectSupported()}.
     * <p>
     * Input: The Intent's data URI specifies bootstrapping information for authenticating and
     * provisioning the peer, and uses a "DPP" scheme. The URI should be attached to the intent
     * using {@link Intent#setData(Uri)}. The calling app can obtain a DPP URI in any
     * way, e.g. by scanning a QR code or other out-of-band methods. The calling app may also
     * attach the {@link #EXTRA_EASY_CONNECT_BAND_LIST} extra to provide information
     * about the bands supported by the enrollee device.
     * <p>
     * Output: After calling {@link android.app.Activity#startActivityForResult}, the callback
     * {@code onActivityResult} will have resultCode {@link android.app.Activity#RESULT_OK} if
     * the Wi-Fi Easy Connect configuration succeeded and the user tapped the 'Done' button, or
     * {@link android.app.Activity#RESULT_CANCELED} if the operation failed and user tapped the
     * 'Cancel' button. In case the operation has failed, a status code from
     * {@link android.net.wifi.EasyConnectStatusCallback} {@code EASY_CONNECT_EVENT_FAILURE_*} will
     * be returned as an Extra {@link #EXTRA_EASY_CONNECT_ERROR_CODE}. Easy Connect R2
     * Enrollees report additional details about the error they encountered, which will be
     * provided in the {@link #EXTRA_EASY_CONNECT_ATTEMPTED_SSID},
     * {@link #EXTRA_EASY_CONNECT_CHANNEL_LIST}, and {@link #EXTRA_EASY_CONNECT_BAND_LIST}.
     */
    public static final String ACTION_PROCESS_WIFI_EASY_CONNECT_URI = "android.settings.PROCESS_WIFI_EASY_CONNECT_URI";

    /**
     * Activity Action: Show screen for controlling the Quick Access Wallet.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_QUICK_ACCESS_WALLET_SETTINGS = "android.settings.QUICK_ACCESS_WALLET_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of quick launch shortcuts.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_QUICK_LAUNCH_SETTINGS = "android.settings.QUICK_LAUNCH_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of regional preferences
     * <p>
     * Input: Nothing
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_REGIONAL_PREFERENCES_SETTINGS = "android.settings.REGIONAL_PREFERENCES_SETTINGS";

    /**
     * Activity Action: Show screen for allowing the region configuration.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_REGION_SETTINGS = "android.settings.REGION_SETTINGS";

    /**
     * Activity Action: Ask the user to allow an app to ignore battery optimizations (that is,
     * put them on the allowlist of apps shown by
     * {@link #ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS}).  For an app to use this, it also
     * must hold the {@link android.Manifest.permission#REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}
     * permission.
     * <p><b>Note:</b> most applications should <em>not</em> use this; there are many facilities
     * provided by the platform for applications to operate correctly in the various power
     * saving modes.  This is only for unusual applications that need to deeply control their own
     * execution, at the potential expense of the user's battery life.  Note that these applications
     * greatly run the risk of showing to the user as high power consumers on their device.</p>
     * <p>
     * Input: The Intent's data URI must specify the application package name
     * to be shown, with the "package" scheme.  That is "package:com.my.app".
     * <p>
     * Output: Nothing.
     * <p>
     * You can use {@link android.os.PowerManager#isIgnoringBatteryOptimizations
     * PowerManager.isIgnoringBatteryOptimizations()} to determine if an application is
     * already ignoring optimizations.
     */
    public static final String ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = "android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";

    /**
     * Activity Action: Show settings to allow configuration of
     * {@link Manifest.permission#MANAGE_MEDIA} permission
     *
     * Input: Optionally, the Intent's data URI can specify the application package name to
     * directly invoke the management GUI specific to the package name. For example
     * "package:com.my.app".
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_REQUEST_MANAGE_MEDIA = "android.settings.REQUEST_MANAGE_MEDIA";

    /**
     * Activity Action: Show settings to allow configuration of
     * {@link Manifest.permission#MEDIA_ROUTING_CONTROL} permission.
     *
     * Input: Optionally, the Intent's data URI can specify the application package name to
     * directly invoke the management GUI specific to the package name. For example
     * "package:com.my.app". However, modifying this permission setting for any package is allowed
     * only when that package holds an appropriate companion device profile such as
     * {@link android.companion.AssociationRequest#DEVICE_PROFILE_WATCH}.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_REQUEST_MEDIA_ROUTING_CONTROL = "android.settings.REQUEST_MEDIA_ROUTING_CONTROL";

    /**
     * Activity Action: Show settings to allow configuration of
     * {@link Manifest.permission#SCHEDULE_EXACT_ALARM} permission
     *
     * Input: Optionally, the Intent's data URI can specify the application package name to
     * directly invoke the management GUI specific to the package name. For example
     * "package:com.my.app".
     * <p>
     * Output: When a package data uri is passed as input, the activity result is set to
     * {@link android.app.Activity#RESULT_OK} if the permission was granted to the app. Otherwise,
     * the result is set to {@link android.app.Activity#RESULT_CANCELED}.
     */
    public static final String ACTION_REQUEST_SCHEDULE_EXACT_ALARM = "android.settings.REQUEST_SCHEDULE_EXACT_ALARM";

    /**
     * Activity Action: Show screen that let user select its Autofill Service.
     * <p>
     * Input: Intent's data URI set with an application package name, using the
     * "package" schema (like "package:com.my.app").
     *
     * <p>
     * Output: {@link android.app.Activity#RESULT_OK} if user selected an Autofill Service belonging
     * to the caller package.
     *
     * <p>
     * <b>NOTE: </b> Applications should call
     * {@link android.view.autofill.AutofillManager#hasEnabledAutofillServices()} and
     * {@link android.view.autofill.AutofillManager#isAutofillSupported()}, and only use this action
     * to start an activity if they return {@code false} and {@code true} respectively.
     */
    public static final String ACTION_REQUEST_SET_AUTOFILL_SERVICE = "android.settings.REQUEST_SET_AUTOFILL_SERVICE";

    /**
     * Activity Action: Show settings to provide guide about carrier satellite messaging.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SATELLITE_SETTING = "android.settings.SATELLITE_SETTING";

    /**
     * Activity Action: Show settings for global search.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing
     */
    public static final String ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of security and
     * location privacy.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SECURITY_SETTINGS = "android.settings.SECURITY_SETTINGS";

    /**
     * Activity Action: Show system settings.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SETTINGS = "android.settings.SETTINGS";

    /**
     * Activity Action: For system or preinstalled apps to show their {@link Activity} embedded
     * in Settings app on large screen devices.
     *
     * Developers should resolve the Intent action before using it.
     *
     * <p>
     *     Input: {@link #EXTRA_SETTINGS_EMBEDDED_DEEP_LINK_INTENT_URI} must be included to
     * specify the intent for the activity which will be embedded in Settings app.
     * It's an intent URI string from {@code intent.toUri(Intent.URI_INTENT_SCHEME)}.
     *
     *     Input: {@link #EXTRA_SETTINGS_EMBEDDED_DEEP_LINK_HIGHLIGHT_MENU_KEY} must be included to
     * specify a key that indicates the menu item which will be highlighted on settings home menu.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SETTINGS_EMBED_DEEP_LINK_ACTIVITY = "android.settings.SETTINGS_EMBED_DEEP_LINK_ACTIVITY";

    /**
     * Activity Action: Show the regulatory information screen for the device.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard
     * against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SHOW_REGULATORY_INFO = "android.settings.SHOW_REGULATORY_INFO";

    /**
     * Activity Action: Show Work Policy info.
     * DPC apps can implement an activity that handles this intent in order to show device policies
     * associated with the work profile or managed device.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SHOW_WORK_POLICY_INFO = "android.settings.SHOW_WORK_POLICY_INFO";

    /**
     * Activity Action: Show settings to allow configuration of sound and volume.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SOUND_SETTINGS = "android.settings.SOUND_SETTINGS";

    /**
     * Activity Action: Show screen for controlling which apps have access on volume directories.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     * <p>
     * Applications typically use this action to ask the user to revert the "Do not ask again"
     * status of directory access requests made by
     * {@link android.os.storage.StorageVolume#createAccessIntent(String)}.
     * @deprecated use {@link #ACTION_APPLICATION_DETAILS_SETTINGS} to manage storage permissions
     *             for a specific application
     */
    @Deprecated
    public static final String ACTION_STORAGE_VOLUME_ACCESS_SETTINGS = "android.settings.STORAGE_VOLUME_ACCESS_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of sync settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * The account types available to add via the add account button may be restricted by adding an
     * {@link #EXTRA_AUTHORITIES} extra to this Intent with one or more syncable content provider's
     * authorities. Only account types which can sync with that content provider will be offered to
     * the user.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_SYNC_SETTINGS = "android.settings.SYNC_SETTINGS";

    /**
     * Activity Action: Show temperature unit configuration settings.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_TEMPERATURE_UNIT_SETTINGS = "android.settings.TEMPERATURE_UNIT_SETTINGS";

    /**
     * Activity Action: Show settings to control access to usage information.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_USAGE_ACCESS_SETTINGS = "android.settings.USAGE_ACCESS_SETTINGS";

    /**
     * Activity Action: Show settings to manage the user input dictionary.
     * <p>
     * Starting with {@link android.os.Build.VERSION_CODES#KITKAT},
     * it is guaranteed there will always be an appropriate implementation for this Intent action.
     * In prior releases of the platform this was optional, so ensure you safeguard against it.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_USER_DICTIONARY_SETTINGS = "android.settings.USER_DICTIONARY_SETTINGS";

    /**
     * Activity Action: Modify Airplane mode settings using a voice command.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * This intent MUST be started using
     * {@link android.service.voice.VoiceInteractionSession#startVoiceActivity
     * startVoiceActivity}.
     * <p>
     * Note: The activity implementing this intent MUST verify that
     * {@link android.app.Activity#isVoiceInteraction isVoiceInteraction} returns true before
     * modifying the setting.
     * <p>
     * Input: To tell which state airplane mode should be set to, add the
     * {@link #EXTRA_AIRPLANE_MODE_ENABLED} extra to this Intent with the state specified.
     * If the extra is not included, no changes will be made.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_VOICE_CONTROL_AIRPLANE_MODE = "android.settings.VOICE_CONTROL_AIRPLANE_MODE";

    /**
     * Activity Action: Modify Battery Saver mode setting using a voice command.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * This intent MUST be started using
     * {@link android.service.voice.VoiceInteractionSession#startVoiceActivity
     * startVoiceActivity}.
     * <p>
     * Note: The activity implementing this intent MUST verify that
     * {@link android.app.Activity#isVoiceInteraction isVoiceInteraction} returns true before
     * modifying the setting.
     * <p>
     * Input: To tell which state batter saver mode should be set to, add the
     * {@link #EXTRA_BATTERY_SAVER_MODE_ENABLED} extra to this Intent with the state specified.
     * If the extra is not included, no changes will be made.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_VOICE_CONTROL_BATTERY_SAVER_MODE = "android.settings.VOICE_CONTROL_BATTERY_SAVER_MODE";

    /**
     * Activity Action: Modify do not disturb mode settings.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard against this.
     * <p>
     * This intent MUST be started using
     * {@link android.service.voice.VoiceInteractionSession#startVoiceActivity
     * startVoiceActivity}.
     * <p>
     * Note: The Activity implementing this intent MUST verify that
     * {@link android.app.Activity#isVoiceInteraction isVoiceInteraction}.
     * returns true before modifying the setting.
     * <p>
     * Input: The optional {@link #EXTRA_DO_NOT_DISTURB_MODE_MINUTES} extra can be used to indicate
     * how long the user wishes to avoid interruptions for. The optional
     * {@link #EXTRA_DO_NOT_DISTURB_MODE_ENABLED} extra can be to indicate if the user is
     * enabling or disabling do not disturb mode. If either extra is not included, the
     * user maybe asked to provide the value.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE = "android.settings.VOICE_CONTROL_DO_NOT_DISTURB_MODE";

    /**
     * Activity Action: Show settings to configure input methods, in particular
     * allowing the user to enable input methods.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_VOICE_INPUT_SETTINGS = "android.settings.VOICE_INPUT_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of VPN.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_VPN_SETTINGS = "android.settings.VPN_SETTINGS";

    /**
     * Activity Action: Show VR listener settings.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     *
     * @see android.service.vr.VrListenerService
     */
    public static final String ACTION_VR_LISTENER_SETTINGS = "android.settings.VR_LISTENER_SETTINGS";

    /**
     * Activity Action: Allows user to select current webview implementation.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     * <p class="note">
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     */
    public static final String ACTION_WEBVIEW_SETTINGS = "android.settings.WEBVIEW_SETTINGS";

    /**
     * Activity Action: Show setting page to process the addition of Wi-Fi networks to the user's
     * saved network list. The app should send a new intent with an extra that holds a maximum
     * of five {@link android.net.wifi.WifiNetworkSuggestion} that specify credentials for the
     * networks to be added to the user's database. The Intent should be sent via the
     * {@link android.app.Activity#startActivityForResult(Intent, int)} API.
     * <p>
     * Note: The app sending the Intent to add the credentials doesn't get any ownership over the
     * newly added network(s). For the Wi-Fi stack, these networks will look like the user
     * manually added them from the Settings UI.
     * <p>
     * Input: The app should put parcelable array list of
     * {@link android.net.wifi.WifiNetworkSuggestion} into the {@link #EXTRA_WIFI_NETWORK_LIST}
     * extra.
     * <p>
     * Output: After {@link android.app.Activity#startActivityForResult(Intent, int)}, the
     * callback {@link android.app.Activity#onActivityResult(int, int, Intent)} will have a
     * result code {@link android.app.Activity#RESULT_OK} to indicate user pressed the save
     * button to save the networks or {@link android.app.Activity#RESULT_CANCELED} to indicate
     * that the user rejected the request. Additionally, an integer array list, stored in
     * {@link #EXTRA_WIFI_NETWORK_RESULT_LIST}, will indicate the process result of each network.
     */
    public static final String ACTION_WIFI_ADD_NETWORKS = "android.settings.WIFI_ADD_NETWORKS";

    /**
     * Activity Action: Show settings to allow configuration of a static IP
     * address for Wi-Fi.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard
     * against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_WIFI_IP_SETTINGS = "android.settings.WIFI_IP_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of Wi-Fi.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_WIFI_SETTINGS = "android.settings.WIFI_SETTINGS";

    /**
     * Activity Action: Show settings to allow configuration of wireless controls
     * such as Wi-Fi, Bluetooth and Mobile networks.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String ACTION_WIRELESS_SETTINGS = "android.settings.WIRELESS_SETTINGS";

    /**
     * Activity Action: Show Zen Mode (aka Do Not Disturb) priority configuration settings.
     */
    public static final String ACTION_ZEN_MODE_PRIORITY_SETTINGS = "android.settings.ZEN_MODE_PRIORITY_SETTINGS";

    /**
     * A result of {@link #ACTION_WIFI_ADD_NETWORKS} intent action that saving the corresponding
     * Wi-Fi network failed.
     */
    public static final int ADD_WIFI_RESULT_ADD_OR_UPDATE_FAILED = 1;

    /**
     * A result of {@link #ACTION_WIFI_ADD_NETWORKS} intent action that indicates the Wi-Fi network
     * already exists.
     */
    public static final int ADD_WIFI_RESULT_ALREADY_EXISTS = 2;

    /**
     * A result of {@link #ACTION_WIFI_ADD_NETWORKS} intent action that saving or updating the
     * corresponding Wi-Fi network was successful.
     */
    public static final int ADD_WIFI_RESULT_SUCCESS = 0;

    public static final String AUTHORITY = "settings";

    /**
     * Activity Extra: Limit available options in launched activity based on the given account
     * types.
     * <p>
     * This can be passed as an extra field in an Activity Intent with one or more account types
     * as a String[]. This field is used by some intents to alter the behavior of the called
     * activity.
     * <p>
     * Example: The {@link #ACTION_ADD_ACCOUNT} intent restricts the account types to the specified
     * list.
     */
    public static final String EXTRA_ACCOUNT_TYPES = "account_types";

    /**
     * Activity Extra: Enable or disable Airplane Mode.
     * <p>
     * This can be passed as an extra field to the {@link #ACTION_VOICE_CONTROL_AIRPLANE_MODE}
     * intent as a boolean to indicate if it should be enabled.
     */
    public static final String EXTRA_AIRPLANE_MODE_ENABLED = "airplane_mode_enabled";

    /**
     * Activity Extra: The package owner of the notification channel settings to display.
     * <p>
     * This must be passed as an extra field to the {@link #ACTION_CHANNEL_NOTIFICATION_SETTINGS}.
     */
    public static final String EXTRA_APP_PACKAGE = "android.provider.extra.APP_PACKAGE";

    /**
     * Activity Extra: Limit available options in launched activity based on the given authority.
     * <p>
     * This can be passed as an extra field in an Activity Intent with one or more syncable content
     * provider's authorities as a String[]. This field is used by some intents to alter the
     * behavior of the called activity.
     * <p>
     * Example: The {@link #ACTION_ADD_ACCOUNT} intent restricts the account types available based
     * on the authority given.
     */
    public static final String EXTRA_AUTHORITIES = "authorities";

    /**
     * Activity Extra: The String id of the {@link AutomaticZenRule mode} settings to display.
     * <p>
     * This must be passed as an extra field to the {@link #ACTION_AUTOMATIC_ZEN_RULE_SETTINGS}.
     */
    public static final String EXTRA_AUTOMATIC_ZEN_RULE_ID = "android.provider.extra.AUTOMATIC_ZEN_RULE_ID";

    /**
     * Activity Extra: Enable or disable Battery saver mode.
     * <p>
     * This can be passed as an extra field to the {@link #ACTION_VOICE_CONTROL_BATTERY_SAVER_MODE}
     * intent as a boolean to indicate if it should be enabled.
     */
    public static final String EXTRA_BATTERY_SAVER_MODE_ENABLED = "android.settings.extra.battery_saver_mode_enabled";

    /**
     * Activity Extra: The minimum strength to request enrollment for.
     * <p>
     * This can be passed as an extra field to the {@link #ACTION_BIOMETRIC_ENROLL} intent to
     * indicate that only enrollment for sensors that meet these requirements should be shown. The
     * value should be a combination of the constants defined in
     * {@link android.hardware.biometrics.BiometricManager.Authenticators}.
     */
    public static final String EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED = "android.provider.extra.BIOMETRIC_AUTHENTICATORS_ALLOWED";

    /**
     * Activity Extra: An {@code Arraylist<String>} of {@link NotificationChannel} field names to
     * show on the Settings UI.
     *
     * <p>
     * This is an optional extra field to the {@link #ACTION_CHANNEL_NOTIFICATION_SETTINGS}. If
     * included the system will filter out any Settings that doesn't appear in this list that
     * otherwise would display.
     */
    public static final String EXTRA_CHANNEL_FILTER_LIST = "android.provider.extra.CHANNEL_FILTER_LIST";

    /**
     * Activity Extra: The {@link NotificationChannel#getId()} of the notification channel settings
     * to display.
     * <p>
     * This must be passed as an extra field to the {@link #ACTION_CHANNEL_NOTIFICATION_SETTINGS}.
     */
    public static final String EXTRA_CHANNEL_ID = "android.provider.extra.CHANNEL_ID";

    /**
     * Activity Extra: The {@link NotificationChannel#getConversationId()} of the notification
     * conversation settings to display.
     * <p>
     * This is an optional extra field to the {@link #ACTION_CHANNEL_NOTIFICATION_SETTINGS}. If
     * included the system will first look up notification settings by channel and conversation id,
     * and will fall back to channel id if a specialized channel for this conversation doesn't
     * exist, similar to {@link NotificationManager#getNotificationChannel(String, String)}.
     */
    public static final String EXTRA_CONVERSATION_ID = "android.provider.extra.CONVERSATION_ID";

    /**
     * Activity Extra: Enable or disable Do Not Disturb mode.
     * <p>
     * This can be passed as an extra field to the {@link #ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE}
     * intent as a boolean to indicate if it should be enabled.
     */
    public static final String EXTRA_DO_NOT_DISTURB_MODE_ENABLED = "android.settings.extra.do_not_disturb_mode_enabled";

    /**
     * Activity Extra: How many minutes to enable do not disturb mode for.
     * <p>
     * This can be passed as an extra field to the {@link #ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE}
     * intent to indicate how long do not disturb mode should be enabled for.
     */
    public static final String EXTRA_DO_NOT_DISTURB_MODE_MINUTES = "android.settings.extra.do_not_disturb_mode_minutes";

    /**
     * Activity Extra: The SSID that the Enrollee tried to connect to.
     * <p>
     * An extra returned on the result intent received when using the {@link
     * #ACTION_PROCESS_WIFI_EASY_CONNECT_URI} intent to launch the Easy Connect Operation. This
     * extra contains the SSID of the Access Point that the remote Enrollee tried to connect to.
     * This value is populated only by remote R2 devices, and only for the following error codes:
     * {@link android.net.wifi.EasyConnectStatusCallback#EASY_CONNECT_EVENT_FAILURE_CANNOT_FIND_NETWORK}
     * {@link android.net.wifi.EasyConnectStatusCallback#EASY_CONNECT_EVENT_FAILURE_ENROLLEE_AUTHENTICATION}.
     * Therefore, always check if this extra is available using {@link Intent#hasExtra(String)}. If
     * there is no error, i.e. if the operation returns {@link android.app.Activity#RESULT_OK}, then
     * this extra is not attached to the result intent.
     * <p>
     * Use the {@link Intent#getStringExtra(String)} to obtain the SSID.
     */
    public static final String EXTRA_EASY_CONNECT_ATTEMPTED_SSID = "android.provider.extra.EASY_CONNECT_ATTEMPTED_SSID";

    /**
     * Activity Extra: The Band List that the Enrollee supports.
     * <p>
     * This extra contains the bands the Enrollee supports, expressed as the Global Operating
     * Class, see Table E-4 in IEEE Std 802.11-2016 Global operating classes. It is used both as
     * input, to configure the Easy Connect operation and as output of the operation.
     * <p>
     * As input: an optional extra to be attached to the
     * {@link #ACTION_PROCESS_WIFI_EASY_CONNECT_URI}. If attached, it indicates the bands which
     * the remote device (enrollee, device-to-be-configured) supports. The Settings operation
     * may take this into account when presenting the user with list of networks configurations
     * to be used. The calling app may obtain this information in any out-of-band method. The
     * information should be attached as an array of raw integers - using the
     * {@link Intent#putExtra(String, int[])}.
     * <p>
     * As output: an extra returned on the result intent received when using the
     * {@link #ACTION_PROCESS_WIFI_EASY_CONNECT_URI} intent to launch the Easy Connect Operation
     * . This value is populated only by remote R2 devices, and only for the following error
     * codes:
     * {@link android.net.wifi.EasyConnectStatusCallback#EASY_CONNECT_EVENT_FAILURE_CANNOT_FIND_NETWORK},
     * {@link android.net.wifi.EasyConnectStatusCallback#EASY_CONNECT_EVENT_FAILURE_ENROLLEE_AUTHENTICATION},
     * or
     * {@link android.net.wifi.EasyConnectStatusCallback#EASY_CONNECT_EVENT_FAILURE_ENROLLEE_REJECTED_CONFIGURATION}.
     * Therefore, always check if this extra is available using {@link Intent#hasExtra(String)}.
     * If there is no error, i.e. if the operation returns {@link android.app.Activity#RESULT_OK}
     * , then this extra is not attached to the result intent.
     * <p>
     * Use the {@link Intent#getIntArrayExtra(String)} to obtain the list.
     */
    public static final String EXTRA_EASY_CONNECT_BAND_LIST = "android.provider.extra.EASY_CONNECT_BAND_LIST";

    /**
     * Activity Extra: The Channel List that the Enrollee used to scan a network.
     * <p>
     * An extra returned on the result intent received when using the {@link
     * #ACTION_PROCESS_WIFI_EASY_CONNECT_URI} intent to launch the Easy Connect Operation. This
     * extra contains the channel list that the Enrollee scanned for a network. This value is
     * populated only by remote R2 devices, and only for the following error code: {@link
     * android.net.wifi.EasyConnectStatusCallback#EASY_CONNECT_EVENT_FAILURE_CANNOT_FIND_NETWORK}.
     * Therefore, always check if this extra is available using {@link Intent#hasExtra(String)}. If
     * there is no error, i.e. if the operation returns {@link android.app.Activity#RESULT_OK}, then
     * this extra is not attached to the result intent. The list is JSON formatted, as an array
     * (Wi-Fi global operating classes) of arrays (Wi-Fi channels).
     * <p>
     * Use the {@link Intent#getStringExtra(String)} to obtain the list.
     */
    public static final String EXTRA_EASY_CONNECT_CHANNEL_LIST = "android.provider.extra.EASY_CONNECT_CHANNEL_LIST";

    /**
     * Activity Extra: The Easy Connect operation error code
     * <p>
     * An extra returned on the result intent received when using the
     * {@link #ACTION_PROCESS_WIFI_EASY_CONNECT_URI} intent to launch the Easy Connect Operation.
     * This extra contains the integer error code of the operation - one of
     * {@link android.net.wifi.EasyConnectStatusCallback} {@code EASY_CONNECT_EVENT_FAILURE_*}. If
     * there is no error, i.e. if the operation returns {@link android.app.Activity#RESULT_OK},
     * then this extra is not attached to the result intent.
     * <p>
     * Use the {@link Intent#hasExtra(String)} to determine whether the extra is attached and
     * {@link Intent#getIntExtra(String, int)} to obtain the error code data.
     */
    public static final String EXTRA_EASY_CONNECT_ERROR_CODE = "android.provider.extra.EASY_CONNECT_ERROR_CODE";

    public static final String EXTRA_INPUT_METHOD_ID = "input_method_id";

    /**
     * Activity Extra: What component name to show the notification listener permission
     * page for.
     * <p>
     * A string extra containing a {@link ComponentName}. This must be passed as an extra field to
     * {@link #ACTION_NOTIFICATION_LISTENER_DETAIL_SETTINGS}.
     */
    public static final String EXTRA_NOTIFICATION_LISTENER_COMPONENT_NAME = "android.provider.extra.NOTIFICATION_LISTENER_COMPONENT_NAME";

    /**
     * Activity Extra: Specify a key that indicates the menu item which should be highlighted on
     * settings home menu.
     * <p>
     * This must be passed as an extra field to
     * {@link #ACTION_SETTINGS_EMBED_DEEP_LINK_ACTIVITY}.
     */
    public static final String EXTRA_SETTINGS_EMBEDDED_DEEP_LINK_HIGHLIGHT_MENU_KEY = "android.provider.extra.SETTINGS_EMBEDDED_DEEP_LINK_HIGHLIGHT_MENU_KEY";

    /**
     * Activity Extra: Specify the intent for the {@link Activity} which will be embedded in
     * Settings app. It's an intent URI string from
     * {@code intent.toUri(Intent.URI_INTENT_SCHEME)}.
     * <p>
     * This must be passed as an extra field to
     * {@link #ACTION_SETTINGS_EMBED_DEEP_LINK_ACTIVITY}.
     */
    public static final String EXTRA_SETTINGS_EMBEDDED_DEEP_LINK_INTENT_URI = "android.provider.extra.SETTINGS_EMBEDDED_DEEP_LINK_INTENT_URI";

    /**
     * An int extra specifying a subscription ID.
     *
     * @see android.telephony.SubscriptionInfo#getSubscriptionId
     */
    public static final String EXTRA_SUB_ID = "android.provider.extra.SUB_ID";

    /**
     * Intent extra: The id of a setting restricted by supervisors.
     * <p>
     * Type: Integer with a value from the one of the SUPERVISOR_VERIFICATION_* constants below.
     * <ul>
     * <li>{@see #SUPERVISOR_VERIFICATION_SETTING_UNKNOWN}
     * <li>{@see #SUPERVISOR_VERIFICATION_SETTING_BIOMETRICS}
     * </ul>
     * </p>
     */
    public static final String EXTRA_SUPERVISOR_RESTRICTED_SETTING_KEY = "android.provider.extra.SUPERVISOR_RESTRICTED_SETTING_KEY";

    /**
     * A bundle extra of {@link #ACTION_WIFI_ADD_NETWORKS} intent action that indicates the list
     * of the {@link android.net.wifi.WifiNetworkSuggestion} elements. The maximum count of the
     * {@link android.net.wifi.WifiNetworkSuggestion} elements in the list will be five.
     * <p>
     * For example:
     * To provide credentials for one open and one WPA2 networks:
     *
     * <pre>{@code
     * final WifiNetworkSuggestion suggestion1 =
     *       new WifiNetworkSuggestion.Builder()
     *       .setSsid("test111111")
     *       .build();
     * final WifiNetworkSuggestion suggestion2 =
     *       new WifiNetworkSuggestion.Builder()
     *       .setSsid("test222222")
     *       .setWpa2Passphrase("test123456")
     *       .build();
     * final List<WifiNetworkSuggestion> suggestionsList = new ArrayList<>;
     * suggestionsList.add(suggestion1);
     * suggestionsList.add(suggestion2);
     * Bundle bundle = new Bundle();
     * bundle.putParcelableArrayList(Settings.EXTRA_WIFI_NETWORK_LIST,(ArrayList<? extends
     * Parcelable>) suggestionsList);
     * final Intent intent = new Intent(Settings.ACTION_WIFI_ADD_NETWORKS);
     * intent.putExtras(bundle);
     * startActivityForResult(intent, 0);
     * }</pre>
     */
    public static final String EXTRA_WIFI_NETWORK_LIST = "android.provider.extra.WIFI_NETWORK_LIST";

    /**
     * A bundle extra of the result of {@link #ACTION_WIFI_ADD_NETWORKS} intent action that
     * indicates the action result of the saved {@link android.net.wifi.WifiNetworkSuggestion}.
     * Its value is a list of integers, and all the elements will be 1:1 mapping to the elements
     * in {@link #EXTRA_WIFI_NETWORK_LIST}, if user press cancel to cancel the add networks
     * request, then its value will be null.
     * <p>
     * Note: The integer value will be one of the {@link #ADD_WIFI_RESULT_SUCCESS},
     * {@link #ADD_WIFI_RESULT_ADD_OR_UPDATE_FAILED}, or {@link #ADD_WIFI_RESULT_ALREADY_EXISTS}}.
     */
    public static final String EXTRA_WIFI_NETWORK_RESULT_LIST = "android.provider.extra.WIFI_NETWORK_RESULT_LIST";

    /**
     * Activity Category: Show application settings related to usage access.
     * <p>
     * An activity that provides a user interface for adjusting usage access related
     * preferences for its containing application. Optional but recommended for apps that
     * use {@link android.Manifest.permission#PACKAGE_USAGE_STATS}.
     * <p>
     * The activity may define meta-data to describe what usage access is
     * used for within their app with {@link #METADATA_USAGE_ACCESS_REASON}, which
     * will be displayed in Settings.
     * <p>
     * Input: Nothing.
     * <p>
     * Output: Nothing.
     */
    public static final String INTENT_CATEGORY_USAGE_ACCESS_CONFIG = "android.intent.category.USAGE_ACCESS_CONFIG";

    /**
     * Metadata key: Reason for needing usage access.
     * <p>
     * A key for metadata attached to an activity that receives action
     * {@link #INTENT_CATEGORY_USAGE_ACCESS_CONFIG}, shown to the
     * user as description of how the app uses usage access.
     * <p>
     */
    public static final String METADATA_USAGE_ACCESS_REASON = "android.settings.metadata.USAGE_ACCESS_REASON";

    /**
     * Settings for supervisors to control what kinds of biometric sensors, such a face and
     * fingerprint scanners, can be used on the device.
     */
    public static final int SUPERVISOR_VERIFICATION_SETTING_BIOMETRICS = 1;

    /**
     * The unknown setting can usually be ignored and is used for compatibility with future
     * supervisor settings.
     */
    public static final int SUPERVISOR_VERIFICATION_SETTING_UNKNOWN = 0;

    private Settings() {
    }

    /**
     * Global system settings, containing preferences that always apply identically
     * to all defined users.  Applications can read these but are not allowed to write;
     * like the "Secure" settings, these are for preferences that the user must
     * explicitly modify through the system UI or specialized APIs for those values.
     */
    public static final class Global extends NameValueTable {
        /**
         * Whether ADB over USB is enabled.
         */
        public static final String ADB_ENABLED = "adb_enabled";

        /**
         * Whether Airplane Mode is on.
         */
        public static final String AIRPLANE_MODE_ON = "airplane_mode_on";

        /**
         * A comma separated list of radios that need to be disabled when airplane mode
         * is on. This overrides WIFI_ON and BLUETOOTH_ON, if Wi-Fi and bluetooth are
         * included in the comma separated list.
         */
        public static final String AIRPLANE_MODE_RADIOS = "airplane_mode_radios";

        /**
         * If not 0, the activity manager will aggressively finish activities and
         * processes as soon as they are no longer needed.  If 0, the normal
         * extended lifetime is used.
         */
        public static final String ALWAYS_FINISH_ACTIVITIES = "always_finish_activities";

        /**
         * Scaling factor for Animator-based animations. This affects both the
         * start delay and duration of all such animations.
         *
         * The value is a float. Setting to 0.0f will cause animations to end immediately.
         * The default value is 1.0f.
         */
        public static final String ANIMATOR_DURATION_SCALE = "animator_duration_scale";

        /**
         * Whether applying ramping ringer on incoming phone call ringtone.
         * <p>1 = apply ramping ringer
         * <p>0 = do not apply ramping ringer
         * @deprecated Use {@link AudioManager#isRampingRingerEnabled()} instead
         */
        @Deprecated
        public static final String APPLY_RAMPING_RINGER = "apply_ramping_ringer";

        /**
         * Value to specify if the device's UTC system clock should be set automatically, e.g. using
         * telephony signals like NITZ, or other sources like GNSS or NTP.
         *
         * <p>Prefer {@code android.app.time.TimeManager} API calls to determine the state of
         * automatic time detection instead of directly observing this setting as it may be ignored
         * by the time_detector service under various conditions.
         *
         * <p>1=yes, 0=no (manual)
         */
        public static final String AUTO_TIME = "auto_time";

        /**
         * Value to specify if the device's time zone system property should be set automatically,
         * e.g. using telephony signals like MCC and NITZ, or other mechanisms like the location.
         *
         * <p>Prefer {@code android.app.time.TimeManager} API calls to determine the state of
         * automatic time zone detection instead of directly observing this setting as it may be
         * ignored by the time_zone_detector service under various conditions.
         *
         * <p>1=yes, 0=no (manual).
         */
        public static final String AUTO_TIME_ZONE = "auto_time_zone";

        /**
         * Whether bluetooth is enabled/disabled
         * 0=disabled. 1=enabled.
         */
        public static final String BLUETOOTH_ON = "bluetooth_on";

        /**
         * Boot count since the device starts running API level 24.
         * <p>
         * Type: int
         */
        public static final String BOOT_COUNT = "boot_count";

        /**
         * Whether to enable contacts metadata syncing or not
         * The value 1 - enable, 0 - disable
         */
        public static final String CONTACT_METADATA_SYNC_ENABLED = "contact_metadata_sync_enabled";

        /**
         * Whether or not data roaming is enabled. (0 = false, 1 = true)
         * Use {@link TelephonyManager#isDataRoamingEnabled} instead of calling via settings.
         */
        public static final String DATA_ROAMING = "data_roaming";

        /**
         * Name of an application package to be debugged.
         */
        public static final String DEBUG_APP = "debug_app";

        /**
         * Whether user has enabled development settings.
         */
        public static final String DEVELOPMENT_SETTINGS_ENABLED = "development_settings_enabled";

        /**
         * The name of the device
         */
        public static final String DEVICE_NAME = "device_name";

        /**
         * Whether the device has been provisioned (0 = false, 1 = true).
         * <p>On a multiuser device with a separate system user, the screen may be locked
         * as soon as this is set to true and further activities cannot be launched on the
         * system user unless they are marked to show over keyguard.
         */
        public static final String DEVICE_PROVISIONED = "device_provisioned";

        /**
         * Host name and port for global http proxy. Uses ':' seperator for
         * between host and port.
         */
        public static final String HTTP_PROXY = "http_proxy";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#INSTALL_NON_MARKET_APPS} instead
         */
        @Deprecated
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";

        /**
         * Ringer mode. This is used internally, changing this value will not
         * change the ringer mode. See AudioManager.
         */
        public static final String MODE_RINGER = "mode_ringer";

        /**
         * User preference for which network(s) should be used. Only the
         * connectivity service should touch this.
         */
        public static final String NETWORK_PREFERENCE = "network_preference";

        /**
         * Constant for use in AIRPLANE_MODE_RADIOS or SATELLITE_MODE_RADIOS to specify Bluetooth
         * radio.
         */
        public static final String RADIO_BLUETOOTH = "bluetooth";

        /**
         * Constant for use in AIRPLANE_MODE_RADIOS to specify Cellular radio.
         */
        public static final String RADIO_CELL = "cell";

        /**
         * Constant for use in AIRPLANE_MODE_RADIOS or SATELLITE_MODE_RADIOS to specify NFC radio.
         */
        public static final String RADIO_NFC = "nfc";

        /**
         * Constant for use in AIRPLANE_MODE_RADIOS or SATELLITE_MODE_RADIOS to specify Wi-Fi radio.
         */
        public static final String RADIO_WIFI = "wifi";

        /**
         * Indicates whether the device is under restricted secure FRP mode.
         * Secure FRP mode is enabled when the device is under FRP. On solving of FRP challenge,
         * device is removed from this mode.
         * <p>
         * Type: int (0 for false, 1 for true)
         */
        public static final String SECURE_FRP_MODE = "secure_frp_mode";

        /**
         * Control whether the process CPU usage meter should be shown.
         *
         * @deprecated This functionality is no longer available as of
         * {@link android.os.Build.VERSION_CODES#N_MR1}.
         */
        @Deprecated
        public static final String SHOW_PROCESSES = "show_processes";

        /**
         * Whether we keep the device on while the device is plugged in.
         * Supported values are:
         * <ul>
         * <li>{@code 0} to never stay on while plugged in</li>
         * <li>{@link BatteryManager#BATTERY_PLUGGED_AC} to stay on for AC charger</li>
         * <li>{@link BatteryManager#BATTERY_PLUGGED_USB} to stay on for USB charger</li>
         * <li>{@link BatteryManager#BATTERY_PLUGGED_WIRELESS} to stay on for wireless charger</li>
         * <li>{@link BatteryManager#BATTERY_PLUGGED_DOCK} to stay on for dock charger</li>
         * </ul>
         * These values can be OR-ed together.
         */
        public static final String STAY_ON_WHILE_PLUGGED_IN = "stay_on_while_plugged_in";

        /**
         * Scaling factor for activity transition animations.
         *
         * The value is a float. Setting to 0.0f will disable window animations.
         */
        public static final String TRANSITION_ANIMATION_SCALE = "transition_animation_scale";

        /**
         * USB Mass Storage Enabled
         */
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";

        /**
         * If this setting is set (to anything), then all references
         * to Gmail on the device must change to Google Mail.
         */
        public static final String USE_GOOGLE_MAIL = "use_google_mail";

        /**
         * If 1, when launching DEBUG_APP it will wait for the debugger before
         * starting user code.  If 0, it will run normally.
         */
        public static final String WAIT_FOR_DEBUGGER = "wait_for_debugger";

        /**
         * This setting controls whether WiFi configurations created by a Device Owner app
         * should be locked down (that is, be editable or removable only by the Device Owner App,
         * not even by Settings app).
         * This setting takes integer values. Non-zero values mean DO created configurations
         * are locked down. Value of zero means they are not. Default value in the absence of
         * actual value to this setting is 0.
         */
        public static final String WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN = "wifi_device_owner_configs_lockdown";

        /**
         * The maximum number of times we will retry a connection to an access
         * point for which we have failed in acquiring an IP address from DHCP.
         * A value of N means that we will make N+1 connection attempts in all.
         */
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";

        /**
         * Maximum amount of time in milliseconds to hold a wakelock while waiting for mobile
         * data connectivity to be established after a disconnect from Wi-Fi.
         */
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";

        /**
         * Whether to notify the user of open networks.
         * <p>
         * If not connected and the scan results have an open network, we will
         * put this notification up. If we attempt to connect to a network or
         * the open network(s) disappear, we remove the notification. When we
         * show the notification, we will not show it again for
         * {@link android.provider.Settings.Secure#WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY} time.
         *
         * @deprecated This feature is no longer controlled by this setting in
         * {@link android.os.Build.VERSION_CODES#O}.
         */
        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";

        /**
         * Delay (in seconds) before repeating the Wi-Fi networks available notification.
         * Connecting to a network will reset the timer.
         * @deprecated This is no longer used or set by the platform.
         */
        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";

        /**
         * When the number of open networks exceeds this number, the
         * least-recently-used excess networks will be removed.
         * @deprecated This is no longer used or set by the platform.
         */
        @Deprecated
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";

        /**
         * Whether the Wi-Fi should be on.  Only the Wi-Fi service should touch this.
         */
        public static final String WIFI_ON = "wifi_on";

        /**
         * The policy for deciding when Wi-Fi should go to sleep (which will in
         * turn switch to using the mobile data as an Internet connection).
         * <p>
         * Set to one of {@link #WIFI_SLEEP_POLICY_DEFAULT},
         * {@link #WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED}, or
         * {@link #WIFI_SLEEP_POLICY_NEVER}.
         * @deprecated This is no longer used or set by the platform.
         */
        @Deprecated
        public static final String WIFI_SLEEP_POLICY = "wifi_sleep_policy";

        /**
         * Value for {@link #WIFI_SLEEP_POLICY} to use the default Wi-Fi sleep
         * policy, which is to sleep shortly after the turning off
         * according to the {@link #STAY_ON_WHILE_PLUGGED_IN} setting.
         * @deprecated This is no longer used by the platform.
         */
        @Deprecated
        public static final int WIFI_SLEEP_POLICY_DEFAULT = 0;

        /**
         * Value for {@link #WIFI_SLEEP_POLICY} to never go to sleep.
         * @deprecated This is no longer used by the platform.
         */
        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER = 2;

        /**
         * Value for {@link #WIFI_SLEEP_POLICY} to use the default policy when
         * the device is on battery, and never go to sleep when the device is
         * plugged in.
         * @deprecated This is no longer used by the platform.
         */
        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED = 1;

        /**
         * Whether the Wi-Fi watchdog is enabled.
         */
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";

        /**
         * Scaling factor for normal window animations.
         *
         * The value is a float. Setting to 0.0f will disable window animations.
         */
        public static final String WINDOW_ANIMATION_SCALE = "window_animation_scale";

        private Global() {
        }
    }

    /**
     * Common base for tables of name/value settings.
     */
    public static class NameValueTable implements BaseColumns {
        public static final String NAME = "name";

        public static final String VALUE = "value";

        private NameValueTable() {
        }
    }

    /**
     * <p>
     *     A Settings panel is floating UI that contains a fixed subset of settings to address a
     *     particular user problem. For example, the
     *     {@link #ACTION_INTERNET_CONNECTIVITY Internet Panel} surfaces settings related to
     *     connecting to the internet.
     * <p>
     *     Settings panels appear above the calling app to address the problem without
     *     the user needing to open Settings and thus leave their current screen.
     */
    public static final class Panel {
        /**
         * Activity Action: Show a settings dialog containing settings to enable internet
         * connection.
         * <p>
         * Input: Nothing.
         * <p>
         * Output: Nothing.
         */
        public static final String ACTION_INTERNET_CONNECTIVITY = "android.settings.panel.action.INTERNET_CONNECTIVITY";

        /**
         * Activity Action: Show a settings dialog containing NFC-related settings.
         * <p>
         * Input: Nothing.
         * <p>
         * Output: Nothing.
         */
        public static final String ACTION_NFC = "android.settings.panel.action.NFC";

        /**
         * Activity Action: Show a settings dialog containing all volume streams.
         * <p>
         * Input: Nothing.
         * <p>
         * Output: Nothing.
         */
        public static final String ACTION_VOLUME = "android.settings.panel.action.VOLUME";

        /**
         * Activity Action: Show a settings dialog containing controls for Wifi.
         * <p>
         * Input: Nothing.
         * <p>
         * Output: Nothing.
         */
        public static final String ACTION_WIFI = "android.settings.panel.action.WIFI";

        private Panel() {
        }
    }

    /**
     * Secure system settings, containing system preferences that applications
     * can read but are not allowed to write.  These are for preferences that
     * the user must explicitly modify through the UI of a system app. Normal
     * applications cannot modify the secure settings database, either directly
     * or by calling the "put" methods that this class contains.
     */
    public static final class Secure extends NameValueTable {
        /**
         * Setting that specifies whether display color inversion is enabled.
         */
        public static final String ACCESSIBILITY_DISPLAY_INVERSION_ENABLED = "accessibility_display_inversion_enabled";

        /**
         * If accessibility is enabled.
         */
        public static final String ACCESSIBILITY_ENABLED = "accessibility_enabled";

        /**
         * Whether to speak passwords while in accessibility mode.
         *
         * @deprecated The speaking of passwords is controlled by individual accessibility services.
         * Apps should ignore this setting and provide complete information to accessibility
         * at all times, which was the behavior when this value was {@code true}.
         */
        @Deprecated
        public static final String ACCESSIBILITY_SPEAK_PASSWORD = "speak_password";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#ADB_ENABLED} instead
         */
        @Deprecated
        public static final String ADB_ENABLED = "adb_enabled";

        /**
         * Origins for which browsers should allow geolocation by default.
         * The value is a space-separated list of origins.
         */
        public static final String ALLOWED_GEOLOCATION_ORIGINS = "allowed_geolocation_origins";

        /**
         * Setting to allow mock locations and location provider status to be injected into the
         * LocationManager service for testing purposes during application development.  These
         * locations and status values  override actual location and status information generated
         * by network, gps, or other location providers.
         *
         * @deprecated This settings is not used anymore.
         */
        @Deprecated
        public static final String ALLOW_MOCK_LOCATION = "mock_location";

        /**
         * On Android 8.0 (API level 26) and higher versions of the platform,
         * a 64-bit number (expressed as a hexadecimal string), unique to
         * each combination of app-signing key, user, and device.
         * Values of {@code ANDROID_ID} are scoped by signing key and user.
         * The value may change if a factory reset is performed on the
         * device or if an APK signing key changes.
         *
         * For more information about how the platform handles {@code ANDROID_ID}
         * in Android 8.0 (API level 26) and higher, see <a
         * href="{@docRoot}about/versions/oreo/android-8.0-changes.html#privacy-all">
         * Android 8.0 Behavior Changes</a>.
         *
         * <p class="note"><strong>Note:</strong> For apps that were installed
         * prior to updating the device to a version of Android 8.0
         * (API level 26) or higher, the value of {@code ANDROID_ID} changes
         * if the app is uninstalled and then reinstalled after the OTA.
         * To preserve values across uninstalls after an OTA to Android 8.0
         * or higher, developers can use
         * <a href="{@docRoot}guide/topics/data/keyvaluebackup.html">
         * Key/Value Backup</a>.</p>
         *
         * <p>In versions of the platform lower than Android 8.0 (API level 26),
         * a 64-bit number (expressed as a hexadecimal string) that is randomly
         * generated when the user first sets up the device and should remain
         * constant for the lifetime of the user's device.
         *
         * On devices that have
         * <a href="{@docRoot}about/versions/android-4.2.html#MultipleUsers">
         * multiple users</a>, each user appears as a
         * completely separate device, so the {@code ANDROID_ID} value is
         * unique to each user.</p>
         *
         * <p class="note"><strong>Note:</strong> If the caller is an Instant App the ID is scoped
         * to the Instant App, it is generated when the Instant App is first installed and reset if
         * the user clears the Instant App.
         */
        public static final String ANDROID_ID = "android_id";

        /**
         * Whether background data usage is allowed.
         *
         * @deprecated As of {@link VERSION_CODES#ICE_CREAM_SANDWICH},
         *             availability of background data depends on several
         *             combined factors. When background data is unavailable,
         *             {@link ConnectivityManager#getActiveNetworkInfo()} will
         *             now appear disconnected.
         */
        @Deprecated
        public static final String BACKGROUND_DATA = "background_data";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#BLUETOOTH_ON} instead
         */
        @Deprecated
        public static final String BLUETOOTH_ON = "bluetooth_on";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#DATA_ROAMING} instead
         */
        @Deprecated
        public static final String DATA_ROAMING = "data_roaming";

        /**
         * Stores {@link android.view.inputmethod.InputMethodInfo#getId()} of the input method
         * service that is currently selected.
         *
         * <p>Although the name {@link #DEFAULT_INPUT_METHOD} implies that there is a concept of
         * <i>default</i> input method, in reality this setting is no more or less than the
         * <strong>currently selected</strong> input method. This setting can be updated at any
         * time as a result of user-initiated and system-initiated input method switching.</p>
         *
         * <p>Use {@link ComponentName#unflattenFromString(String)} to parse the stored value.</p>
         */
        public static final String DEFAULT_INPUT_METHOD = "default_input_method";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#DEVELOPMENT_SETTINGS_ENABLED}
         * instead
         */
        @Deprecated
        public static final String DEVELOPMENT_SETTINGS_ENABLED = "development_settings_enabled";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#DEVICE_PROVISIONED} instead
         */
        @Deprecated
        public static final String DEVICE_PROVISIONED = "device_provisioned";

        /**
         * List of the enabled accessibility providers.
         */
        public static final String ENABLED_ACCESSIBILITY_SERVICES = "enabled_accessibility_services";

        /**
         * List of input methods that are currently enabled.  This is a string
         * containing the IDs of all enabled input methods, each ID separated
         * by ':'.
         *
         * Format like "ime0;subtype0;subtype1;subtype2:ime1:ime2;subtype0"
         * where imeId is ComponentName and subtype is int32.
         *
         * <p>Note: This setting is not readable to the app targeting API level 34 or higher. use
         * {@link android.view.inputmethod.InputMethodManager#getEnabledInputMethodList()} instead.
         */
        public static final String ENABLED_INPUT_METHODS = "enabled_input_methods";

        /**
         * Host name and port for global http proxy. Uses ':' seperator for
         * between host and port.
         *
         * @deprecated Use {@link Global#HTTP_PROXY}
         */
        @Deprecated
        public static final String HTTP_PROXY = "http_proxy";

        /**
         * Setting to record the visibility of input method selector
         */
        public static final String INPUT_METHOD_SELECTOR_VISIBILITY = "input_method_selector_visibility";

        /**
         * Whether applications can be installed for this user via the system's
         * {@link Intent#ACTION_INSTALL_PACKAGE} mechanism.
         *
         * <p>1 = permit app installation via the system package installer intent
         * <p>0 = do not allow use of the package installer
         * @deprecated Starting from {@link android.os.Build.VERSION_CODES#O}, apps should use
         * {@link PackageManager#canRequestPackageInstalls()}
         * @see PackageManager#canRequestPackageInstalls()
         */
        @Deprecated
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";

        /**
         * The current location mode of the device. Do not rely on this value being present or on
         * ContentObserver notifications on the corresponding Uri.
         *
         * @deprecated The preferred methods for checking location mode and listening for changes
         * are via {@link LocationManager#isLocationEnabled()} and
         * {@link LocationManager#MODE_CHANGED_ACTION}.
         */
        @Deprecated
        public static final String LOCATION_MODE = "location_mode";

        /**
         * This mode no longer has any distinct meaning, but is interpreted as the location mode is
         * on.
         *
         * @deprecated See {@link #LOCATION_MODE}.
         */
        @Deprecated
        public static final int LOCATION_MODE_BATTERY_SAVING = 2;

        /**
         * This mode no longer has any distinct meaning, but is interpreted as the location mode is
         * on.
         *
         * @deprecated See {@link #LOCATION_MODE}.
         */
        @Deprecated
        public static final int LOCATION_MODE_HIGH_ACCURACY = 3;

        /**
         * Location mode is off.
         */
        public static final int LOCATION_MODE_OFF = 0;

        /**
         * This mode no longer has any distinct meaning, but is interpreted as the location mode is
         * on.
         *
         * @deprecated See {@link #LOCATION_MODE}.
         */
        @Deprecated
        public static final int LOCATION_MODE_SENSORS_ONLY = 1;

        /**
         * Comma-separated list of location providers that are enabled. Do not rely on this value
         * being present or correct, or on ContentObserver notifications on the corresponding Uri.
         *
         * @deprecated This setting no longer exists from Android S onwards as it no longer is
         * capable of realistically reflecting location settings. Use {@link
         * LocationManager#isProviderEnabled(String)} or {@link LocationManager#isLocationEnabled()}
         * instead.
         */
        @Deprecated
        public static final String LOCATION_PROVIDERS_ALLOWED = "location_providers_allowed";

        /**
         * Whether autolock is enabled (0 = false, 1 = true)
         *
         * @deprecated Use {@link android.app.KeyguardManager} to determine the state and security
         *             level of the keyguard. Accessing this setting from an app that is targeting
         *             {@link VERSION_CODES#M} or later throws a {@code SecurityException}.
         */
        @Deprecated
        public static final String LOCK_PATTERN_ENABLED = "lock_pattern_autolock";

        /**
         * Whether lock pattern will vibrate as user enters (0 = false, 1 =
         * true)
         *
         * @deprecated Starting in {@link VERSION_CODES#JELLY_BEAN_MR1} the
         *             lockscreen uses
         *             {@link Settings.System#HAPTIC_FEEDBACK_ENABLED}.
         *             Accessing this setting from an app that is targeting
         *             {@link VERSION_CODES#M} or later throws a {@code SecurityException}.
         */
        @Deprecated
        public static final String LOCK_PATTERN_TACTILE_FEEDBACK_ENABLED = "lock_pattern_tactile_feedback_enabled";

        /**
         * Whether lock pattern is visible as user enters (0 = false, 1 = true)
         *
         * @deprecated Accessing this setting from an app that is targeting
         *             {@link VERSION_CODES#M} or later throws a {@code SecurityException}.
         */
        @Deprecated
        public static final String LOCK_PATTERN_VISIBLE = "lock_pattern_visible_pattern";

        /**
         * The Logging ID (a unique 64-bit value) as a hex string.
         * Used as a pseudonymous identifier for logging.
         * @deprecated This identifier is poorly initialized and has
         * many collisions.  It should not be used.
         */
        @Deprecated
        public static final String LOGGING_ID = "logging_id";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#NETWORK_PREFERENCE} instead
         */
        @Deprecated
        public static final String NETWORK_PREFERENCE = "network_preference";

        /**
         * No longer supported.
         */
        public static final String PARENTAL_CONTROL_ENABLED = "parental_control_enabled";

        /**
         * No longer supported.
         */
        public static final String PARENTAL_CONTROL_LAST_UPDATE = "parental_control_last_update";

        /**
         * No longer supported.
         */
        public static final String PARENTAL_CONTROL_REDIRECT_URL = "parental_control_redirect_url";

        /**
         * User-selected RTT mode. When on, outgoing and incoming calls will be answered as RTT
         * calls when supported by the device and carrier. Boolean value.
         * 0 = OFF
         * 1 = ON
         */
        public static final String RTT_CALLING_MODE = "rtt_calling_mode";

        /**
         * Indicates whether the device is under restricted secure FRP mode.
         * Secure FRP mode is enabled when the device is under FRP. On solving of FRP challenge,
         * device is removed from this mode.
         * <p>
         * Type: int (0 for false, 1 for true)
         *
         * @deprecated Use Global.SECURE_FRP_MODE
         */
        @Deprecated
        public static final String SECURE_FRP_MODE = "secure_frp_mode";

        /**
         * Setting to record the input method subtype used by default, holding the ID
         * of the desired method.
         */
        public static final String SELECTED_INPUT_METHOD_SUBTYPE = "selected_input_method_subtype";

        /**
         * Settings classname to launch when Settings is clicked from All
         * Applications.  Needed because of user testing between the old
         * and new Settings apps.
         */
        public static final String SETTINGS_CLASSNAME = "settings_classname";

        /**
         * If enabled, apps should try to skip any introductory hints on first launch. This might
         * apply to users that are already familiar with the environment or temporary users.
         * <p>
         * Type : int (0 to show hints, 1 to skip showing hints)
         */
        public static final String SKIP_FIRST_USE_HINTS = "skip_first_use_hints";

        /**
         * If touch exploration is enabled.
         */
        public static final String TOUCH_EXPLORATION_ENABLED = "touch_exploration_enabled";

        /**
         * Default text-to-speech country.
         *
         * @deprecated this setting is no longer in use, as of the Ice Cream
         * Sandwich release. Apps should never need to read this setting directly,
         * instead can query the TextToSpeech framework classes for the default
         * locale. {@link TextToSpeech#getLanguage()}.
         */
        @Deprecated
        public static final String TTS_DEFAULT_COUNTRY = "tts_default_country";

        /**
         * Default text-to-speech language.
         *
         * @deprecated this setting is no longer in use, as of the Ice Cream
         * Sandwich release. Apps should never need to read this setting directly,
         * instead can query the TextToSpeech framework classes for the default
         * locale. {@link TextToSpeech#getLanguage()}.
         */
        @Deprecated
        public static final String TTS_DEFAULT_LANG = "tts_default_lang";

        /**
         * Default text-to-speech engine pitch. 100 = 1x
         */
        public static final String TTS_DEFAULT_PITCH = "tts_default_pitch";

        /**
         * Default text-to-speech engine speech rate. 100 = 1x
         */
        public static final String TTS_DEFAULT_RATE = "tts_default_rate";

        /**
         * Default text-to-speech engine.
         */
        public static final String TTS_DEFAULT_SYNTH = "tts_default_synth";

        /**
         * Default text-to-speech locale variant.
         *
         * @deprecated this setting is no longer in use, as of the Ice Cream
         * Sandwich release. Apps should never need to read this setting directly,
         * instead can query the TextToSpeech framework classes for the
         * locale that is in use {@link TextToSpeech#getLanguage()}.
         */
        @Deprecated
        public static final String TTS_DEFAULT_VARIANT = "tts_default_variant";

        /**
         * Space delimited list of plugin packages that are enabled.
         */
        public static final String TTS_ENABLED_PLUGINS = "tts_enabled_plugins";

        /**
         * Setting to always use the default text-to-speech settings regardless
         * of the application settings.
         * 1 = override application settings,
         * 0 = use application settings (if specified).
         *
         * @deprecated  The value of this setting is no longer respected by
         * the framework text to speech APIs as of the Ice Cream Sandwich release.
         */
        @Deprecated
        public static final String TTS_USE_DEFAULTS = "tts_use_defaults";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#USB_MASS_STORAGE_ENABLED} instead
         */
        @Deprecated
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#USE_GOOGLE_MAIL} instead
         */
        @Deprecated
        public static final String USE_GOOGLE_MAIL = "use_google_mail";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Global#WIFI_MAX_DHCP_RETRY_COUNT} instead
         */
        @Deprecated
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Global#WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS} instead
         */
        @Deprecated
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON}
         * instead.
         */
        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY}
         * instead.
         */
        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_NUM_OPEN_NETWORKS_KEPT}
         * instead.
         */
        @Deprecated
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_ON}
         * instead.
         */
        @Deprecated
        public static final String WIFI_ON = "wifi_on";

        /**
         * The acceptable packet loss percentage (range 0 - 100) before trying
         * another AP on the same network.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE = "wifi_watchdog_acceptable_packet_loss_percentage";

        /**
         * The number of access points required for a network in order for the
         * watchdog to monitor it.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_AP_COUNT = "wifi_watchdog_ap_count";

        /**
         * The delay between background checks.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS = "wifi_watchdog_background_check_delay_ms";

        /**
         * Whether the Wi-Fi watchdog is enabled for background checking even
         * after it thinks the user has connected to a good access point.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED = "wifi_watchdog_background_check_enabled";

        /**
         * The timeout for a background ping
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS = "wifi_watchdog_background_check_timeout_ms";

        /**
         * The number of initial pings to perform that *may* be ignored if they
         * fail. Again, if these fail, they will *not* be used in packet loss
         * calculation. For example, one network always seemed to time out for
         * the first couple pings, so this is set to 3 by default.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT = "wifi_watchdog_initial_ignored_ping_count";

        /**
         * The maximum number of access points (per network) to attempt to test.
         * If this number is reached, the watchdog will no longer monitor the
         * initial connection state for the network. This is a safeguard for
         * networks containing multiple APs whose DNS does not respond to pings.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_MAX_AP_CHECKS = "wifi_watchdog_max_ap_checks";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_WATCHDOG_ON} instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";

        /**
         * The number of pings to test if an access point is a good connection.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_PING_COUNT = "wifi_watchdog_ping_count";

        /**
         * The delay between pings.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_PING_DELAY_MS = "wifi_watchdog_ping_delay_ms";

        /**
         * The timeout per ping.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_PING_TIMEOUT_MS = "wifi_watchdog_ping_timeout_ms";

        /**
         * A comma-separated list of SSIDs for which the Wi-Fi watchdog should be enabled.
         * @deprecated This setting is not used.
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_WATCH_LIST = "wifi_watchdog_watch_list";

        private Secure() {
        }
    }

    /**
     * System settings, containing miscellaneous system preferences.  This
     * table holds simple name/value pairs.  There are convenience
     * functions for accessing individual settings entries.
     */
    public static final class System extends NameValueTable {
        /**
         * Control whether the accelerometer will be used to change screen
         * orientation.  If 0, it will not be used unless explicitly requested
         * by the application; if 1, it will be used by default unless explicitly
         * disabled by the application.
         */
        public static final String ACCELEROMETER_ROTATION = "accelerometer_rotation";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#ADB_ENABLED}
         * instead
         */
        @Deprecated
        public static final String ADB_ENABLED = "adb_enabled";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#AIRPLANE_MODE_ON} instead
         */
        @Deprecated
        public static final String AIRPLANE_MODE_ON = "airplane_mode_on";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#AIRPLANE_MODE_RADIOS} instead
         */
        @Deprecated
        public static final String AIRPLANE_MODE_RADIOS = "airplane_mode_radios";

        /**
         * Persistent store for the system-wide default alarm alert.
         *
         * @see #RINGTONE
         * @see #DEFAULT_ALARM_ALERT_URI
         */
        public static final String ALARM_ALERT = "alarm_alert";

        /**
         * If 1, the activity manager will aggressively finish activities and
         * processes as soon as they are no longer needed.  If 0, the normal
         * extended lifetime is used.
         *
         * @deprecated Use {@link Global#ALWAYS_FINISH_ACTIVITIES} instead
         */
        @Deprecated
        public static final String ALWAYS_FINISH_ACTIVITIES = "always_finish_activities";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#ANDROID_ID} instead
         */
        @Deprecated
        public static final String ANDROID_ID = "android_id";

        /**
         * Scaling factor for Animator-based animations. This affects both the start delay and
         * duration of all such animations. Setting to 0 will cause animations to end immediately.
         * The default value is 1.
         *
         * @deprecated Use {@link Global#ANIMATOR_DURATION_SCALE} instead
         */
        @Deprecated
        public static final String ANIMATOR_DURATION_SCALE = "animator_duration_scale";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#AUTO_TIME}
         * instead
         */
        @Deprecated
        public static final String AUTO_TIME = "auto_time";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#AUTO_TIME_ZONE}
         * instead
         */
        @Deprecated
        public static final String AUTO_TIME_ZONE = "auto_time_zone";

        /**
         * Determines whether remote devices may discover and/or connect to
         * this device.
         * <P>Type: INT</P>
         * 2 -- discoverable and connectable
         * 1 -- connectable but not discoverable
         * 0 -- neither connectable nor discoverable
         */
        public static final String BLUETOOTH_DISCOVERABILITY = "bluetooth_discoverability";

        /**
         * Bluetooth discoverability timeout.  If this value is nonzero, then
         * Bluetooth becomes discoverable for a certain number of seconds,
         * after which is becomes simply connectable.  The value is in seconds.
         */
        public static final String BLUETOOTH_DISCOVERABILITY_TIMEOUT = "bluetooth_discoverability_timeout";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#BLUETOOTH_ON} instead
         */
        @Deprecated
        public static final String BLUETOOTH_ON = "bluetooth_on";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#DATA_ROAMING} instead
         */
        @Deprecated
        public static final String DATA_ROAMING = "data_roaming";

        /**
         * @deprecated No longer used. Use {@link #TIME_12_24} instead.
         */
        @Deprecated
        public static final String DATE_FORMAT = "date_format";

        /**
         * Name of an application package to be debugged.
         *
         * @deprecated Use {@link Global#DEBUG_APP} instead
         */
        @Deprecated
        public static final String DEBUG_APP = "debug_app";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#DEVICE_PROVISIONED} instead
         */
        @Deprecated
        public static final String DEVICE_PROVISIONED = "device_provisioned";

        /**
         * Whether or not to dim the screen. 0=no  1=yes
         * @deprecated This setting is no longer used.
         */
        @Deprecated
        public static final String DIM_SCREEN = "dim_screen";

        /**
         * CDMA only settings
         * DTMF tone type played by the dialer when dialing.
         *                 0 = Normal
         *                 1 = Long
         */
        public static final String DTMF_TONE_TYPE_WHEN_DIALING = "dtmf_tone_type";

        /**
         * Whether the audible DTMF tones are played by the dialer when dialing. The value is
         * boolean (1 or 0).
         */
        public static final String DTMF_TONE_WHEN_DIALING = "dtmf_tone";

        /**
         * What happens when the user presses the end call button if they're not
         * on a call.<br/>
         * <b>Values:</b><br/>
         * 0 - The end button does nothing.<br/>
         * 1 - The end button goes to the home screen.<br/>
         * 2 - The end button puts the device to sleep and locks the keyguard.<br/>
         * 3 - The end button goes to the home screen.  If the user is already on the
         * home screen, it puts the device to sleep.
         */
        public static final String END_BUTTON_BEHAVIOR = "end_button_behavior";

        /**
         * Scaling factor for fonts, float.
         */
        public static final String FONT_SCALE = "font_scale";

        /**
         * Whether haptic feedback (Vibrate on tap) is enabled. The value is
         * boolean (1 or 0).
         *
         * @deprecated Replaced by using {@link android.os.VibrationAttributes#USAGE_TOUCH} on
         * vibrations. User settings are applied automatically by the service and should not be
         * applied by individual apps.
         */
        @Deprecated
        public static final String HAPTIC_FEEDBACK_ENABLED = "haptic_feedback_enabled";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#HTTP_PROXY} instead
         */
        @Deprecated
        public static final String HTTP_PROXY = "http_proxy";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#INSTALL_NON_MARKET_APPS} instead
         */
        @Deprecated
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#LOCATION_PROVIDERS_ALLOWED}
         * instead
         */
        @Deprecated
        public static final String LOCATION_PROVIDERS_ALLOWED = "location_providers_allowed";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#LOCK_PATTERN_ENABLED}
         * instead
         */
        @Deprecated
        public static final String LOCK_PATTERN_ENABLED = "lock_pattern_autolock";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Secure#LOCK_PATTERN_TACTILE_FEEDBACK_ENABLED}
         * instead
         */
        @Deprecated
        public static final String LOCK_PATTERN_TACTILE_FEEDBACK_ENABLED = "lock_pattern_tactile_feedback_enabled";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#LOCK_PATTERN_VISIBLE}
         * instead
         */
        @Deprecated
        public static final String LOCK_PATTERN_VISIBLE = "lock_pattern_visible_pattern";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#LOGGING_ID} instead
         */
        @Deprecated
        public static final String LOGGING_ID = "logging_id";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#MODE_RINGER} instead
         */
        @Deprecated
        public static final String MODE_RINGER = "mode_ringer";

        /**
         * Determines which streams are affected by ringer and zen mode changes. The
         * stream type's bit should be set to 1 if it should be muted when going
         * into an inaudible ringer mode.
         */
        public static final String MODE_RINGER_STREAMS_AFFECTED = "mode_ringer_streams_affected";

        /**
         * Determines which streams are affected by mute. The
         * stream type's bit should be set to 1 if it should be muted when a mute request
         * is received.
         */
        public static final String MUTE_STREAMS_AFFECTED = "mute_streams_affected";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#NETWORK_PREFERENCE} instead
         */
        @Deprecated
        public static final String NETWORK_PREFERENCE = "network_preference";

        /**
         * A formatted string of the next alarm that is set, or the empty string
         * if there is no alarm set.
         *
         * @deprecated Use {@link android.app.AlarmManager#getNextAlarmClock()}.
         */
        @Deprecated
        public static final String NEXT_ALARM_FORMATTED = "next_alarm_formatted";

        /**
         * Persistent store for the system-wide default notification sound.
         *
         * @see #RINGTONE
         * @see #DEFAULT_NOTIFICATION_URI
         */
        public static final String NOTIFICATION_SOUND = "notification_sound";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#PARENTAL_CONTROL_ENABLED}
         * instead
         */
        @Deprecated
        public static final String PARENTAL_CONTROL_ENABLED = "parental_control_enabled";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#PARENTAL_CONTROL_LAST_UPDATE}
         * instead
         */
        @Deprecated
        public static final String PARENTAL_CONTROL_LAST_UPDATE = "parental_control_last_update";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#PARENTAL_CONTROL_REDIRECT_URL}
         * instead
         */
        @Deprecated
        public static final String PARENTAL_CONTROL_REDIRECT_URL = "parental_control_redirect_url";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#RADIO_BLUETOOTH} instead
         */
        @Deprecated
        public static final String RADIO_BLUETOOTH = "bluetooth";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#RADIO_CELL} instead
         */
        @Deprecated
        public static final String RADIO_CELL = "cell";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#RADIO_NFC} instead
         */
        @Deprecated
        public static final String RADIO_NFC = "nfc";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#RADIO_WIFI} instead
         */
        @Deprecated
        public static final String RADIO_WIFI = "wifi";

        /**
         * Persistent store for the system-wide default ringtone URI.
         * <p>
         * If you need to play the default ringtone at any given time, it is recommended
         * you give {@link #DEFAULT_RINGTONE_URI} to the media player.  It will resolve
         * to the set default ringtone at the time of playing.
         *
         * @see #DEFAULT_RINGTONE_URI
         */
        public static final String RINGTONE = "ringtone";

        /**
         * The screen backlight brightness between 1 (minimum) and 255 (maximum).
         *
         * Use {@link android.view.WindowManager.LayoutParams#screenBrightness} to set the screen
         * brightness instead.
         */
        public static final String SCREEN_BRIGHTNESS = "screen_brightness";

        /**
         * Controls whether to enable automatic brightness mode. Value can be set to
         * {@link #SCREEN_BRIGHTNESS_MODE_MANUAL} or {@link #SCREEN_BRIGHTNESS_MODE_AUTOMATIC}.
         * If {@link #SCREEN_BRIGHTNESS_MODE_AUTOMATIC} is set, the system may change
         * {@link #SCREEN_BRIGHTNESS} automatically.
         */
        public static final String SCREEN_BRIGHTNESS_MODE = "screen_brightness_mode";

        /**
         * SCREEN_BRIGHTNESS_MODE value for automatic mode.
         */
        public static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = 1;

        /**
         * SCREEN_BRIGHTNESS_MODE value for manual mode.
         */
        public static final int SCREEN_BRIGHTNESS_MODE_MANUAL = 0;

        /**
         * The amount of time in milliseconds before the device goes to sleep or begins
         * to dream after a period of inactivity.  This value is also known as the
         * user activity timeout period since the screen isn't necessarily turned off
         * when it expires.
         *
         * <p>
         * This value is bounded by maximum timeout set by
         * {@link android.app.admin.DevicePolicyManager#setMaximumTimeToLock(ComponentName, long)}.
         */
        public static final String SCREEN_OFF_TIMEOUT = "screen_off_timeout";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#SETTINGS_CLASSNAME} instead
         */
        @Deprecated
        public static final String SETTINGS_CLASSNAME = "settings_classname";

        /**
         * Whether the setup wizard has been run before (on first boot), or if
         * it still needs to be run.
         *
         * nonzero = it has been run in the past
         * 0 = it has not been run in the past
         */
        public static final String SETUP_WIZARD_HAS_RUN = "setup_wizard_has_run";

        public static final String SHOW_GTALK_SERVICE_STATUS = "SHOW_GTALK_SERVICE_STATUS";

        /**
         * Control whether the process CPU usage meter should be shown.
         *
         * @deprecated This functionality is no longer available as of
         * {@link android.os.Build.VERSION_CODES#N_MR1}.
         */
        @Deprecated
        public static final String SHOW_PROCESSES = "show_processes";

        /**
         * @deprecated Each application that shows web suggestions should have its own
         * setting for this.
         */
        @Deprecated
        public static final String SHOW_WEB_SUGGESTIONS = "show_web_suggestions";

        /**
         * Whether the sounds effects (key clicks, lid open ...) are enabled. The value is
         * boolean (1 or 0).
         */
        public static final String SOUND_EFFECTS_ENABLED = "sound_effects_enabled";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#STAY_ON_WHILE_PLUGGED_IN} instead
         */
        @Deprecated
        public static final String STAY_ON_WHILE_PLUGGED_IN = "stay_on_while_plugged_in";

        /**
         * Setting to enable Auto Caps in text editors. 1 = On, 0 = Off
         */
        public static final String TEXT_AUTO_CAPS = "auto_caps";

        /**
         * Setting to enable Auto Punctuate in text editors. 1 = On, 0 = Off. This
         * feature converts two spaces to a "." and space.
         */
        public static final String TEXT_AUTO_PUNCTUATE = "auto_punctuate";

        /**
         * Setting to enable Auto Replace (AutoText) in text editors. 1 = On, 0 = Off
         */
        public static final String TEXT_AUTO_REPLACE = "auto_replace";

        /**
         * Setting to showing password characters in text editors. 1 = On, 0 = Off
         */
        public static final String TEXT_SHOW_PASSWORD = "show_password";

        /**
         * Display the user's times, e.g. in the status bar, as 12 or 24 hours.
         * <ul>
         *    <li>24 = 24 hour</li>
         *    <li>12 = 12 hour</li>
         *    <li>[unset] = use the device locale's default</li>
         * </ul>
         */
        public static final String TIME_12_24 = "time_12_24";

        /**
         * Scaling factor for activity transition animations. Setting to 0 will disable window
         * animations.
         *
         * @deprecated Use {@link Global#TRANSITION_ANIMATION_SCALE} instead
         */
        @Deprecated
        public static final String TRANSITION_ANIMATION_SCALE = "transition_animation_scale";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#USB_MASS_STORAGE_ENABLED} instead
         */
        @Deprecated
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";

        /**
         * Default screen rotation when no other policy applies.
         * When {@link #ACCELEROMETER_ROTATION} is zero and no on-screen Activity expresses a
         * preference, this rotation value will be used. Must be one of the
         * {@link android.view.Surface#ROTATION_0 Surface rotation constants}.
         *
         * @see Display#getRotation
         */
        public static final String USER_ROTATION = "user_rotation";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#USE_GOOGLE_MAIL} instead
         */
        @Deprecated
        public static final String USE_GOOGLE_MAIL = "use_google_mail";

        /**
         * Whether vibrate is on for different events. This is used internally,
         * changing this value will not change the vibrate. See AudioManager.
         */
        public static final String VIBRATE_ON = "vibrate_on";

        /**
         * Whether the phone vibrates when it is ringing due to an incoming call. This will
         * be used by Phone and Setting apps; it shouldn't affect other apps.
         * The value is boolean (1 or 0).
         *
         * Note: this is not same as "vibrate on ring", which had been available until ICS.
         * It was about AudioManager's setting and thus affected all the applications which
         * relied on the setting, while this is purely about the vibration setting for incoming
         * calls.
         *
         * @deprecated Replaced by using {@link android.os.VibrationAttributes#USAGE_RINGTONE} on
         * vibrations for incoming calls. User settings are applied automatically by the service and
         * should not be applied by individual apps.
         */
        @Deprecated
        public static final String VIBRATE_WHEN_RINGING = "vibrate_when_ringing";

        /**
         * If 1, when launching DEBUG_APP it will wait for the debugger before
         * starting user code.  If 0, it will run normally.
         *
         * @deprecated Use {@link Global#WAIT_FOR_DEBUGGER} instead
         */
        @Deprecated
        public static final String WAIT_FOR_DEBUGGER = "wait_for_debugger";

        /**
         * Name of activity to use for wallpaper on the home screen.
         *
         * @deprecated Use {@link WallpaperManager} instead.
         */
        @Deprecated
        public static final String WALLPAPER_ACTIVITY = "wallpaper_activity";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Global#WIFI_MAX_DHCP_RETRY_COUNT} instead
         */
        @Deprecated
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Global#WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS} instead
         */
        @Deprecated
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Global#WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON} instead
         */
        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Global#WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY} instead
         */
        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_NUM_OPEN_NETWORKS_KEPT}
         * instead
         */
        @Deprecated
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_ON} instead
         */
        @Deprecated
        public static final String WIFI_ON = "wifi_on";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_SLEEP_POLICY} instead
         */
        @Deprecated
        public static final String WIFI_SLEEP_POLICY = "wifi_sleep_policy";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_SLEEP_POLICY_DEFAULT} instead
         */
        @Deprecated
        public static final int WIFI_SLEEP_POLICY_DEFAULT = 0;

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_SLEEP_POLICY_NEVER} instead
         */
        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER = 2;

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED} instead
         */
        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED = 1;

        /**
         * If using static IP, the primary DNS's IP address.
         * <p>
         * Example: "192.168.1.1"
         *
         * @deprecated Use {@link WifiManager} instead
         */
        @Deprecated
        public static final String WIFI_STATIC_DNS1 = "wifi_static_dns1";

        /**
         * If using static IP, the secondary DNS's IP address.
         * <p>
         * Example: "192.168.1.2"
         *
         * @deprecated Use {@link WifiManager} instead
         */
        @Deprecated
        public static final String WIFI_STATIC_DNS2 = "wifi_static_dns2";

        /**
         * If using static IP, the gateway's IP address.
         * <p>
         * Example: "192.168.1.1"
         *
         * @deprecated Use {@link WifiManager} instead
         */
        @Deprecated
        public static final String WIFI_STATIC_GATEWAY = "wifi_static_gateway";

        /**
         * The static IP address.
         * <p>
         * Example: "192.168.1.51"
         *
         * @deprecated Use {@link WifiManager} instead
         */
        @Deprecated
        public static final String WIFI_STATIC_IP = "wifi_static_ip";

        /**
         * If using static IP, the net mask.
         * <p>
         * Example: "255.255.255.0"
         *
         * @deprecated Use {@link WifiManager} instead
         */
        @Deprecated
        public static final String WIFI_STATIC_NETMASK = "wifi_static_netmask";

        /**
         * Whether to use static IP and other static network attributes.
         * <p>
         * Set to 1 for true and 0 for false.
         *
         * @deprecated Use {@link WifiManager} instead
         */
        @Deprecated
        public static final String WIFI_USE_STATIC_IP = "wifi_use_static_ip";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Secure#WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE}
         * instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE = "wifi_watchdog_acceptable_packet_loss_percentage";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#WIFI_WATCHDOG_AP_COUNT} instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_AP_COUNT = "wifi_watchdog_ap_count";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Secure#WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS} instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS = "wifi_watchdog_background_check_delay_ms";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Secure#WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED} instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED = "wifi_watchdog_background_check_enabled";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Secure#WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS}
         * instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS = "wifi_watchdog_background_check_timeout_ms";

        /**
         * @deprecated Use
         * {@link android.provider.Settings.Secure#WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT} instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT = "wifi_watchdog_initial_ignored_ping_count";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#WIFI_WATCHDOG_MAX_AP_CHECKS}
         * instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_MAX_AP_CHECKS = "wifi_watchdog_max_ap_checks";

        /**
         * @deprecated Use {@link android.provider.Settings.Global#WIFI_WATCHDOG_ON} instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#WIFI_WATCHDOG_PING_COUNT} instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_PING_COUNT = "wifi_watchdog_ping_count";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#WIFI_WATCHDOG_PING_DELAY_MS}
         * instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_PING_DELAY_MS = "wifi_watchdog_ping_delay_ms";

        /**
         * @deprecated Use {@link android.provider.Settings.Secure#WIFI_WATCHDOG_PING_TIMEOUT_MS}
         * instead
         */
        @Deprecated
        public static final String WIFI_WATCHDOG_PING_TIMEOUT_MS = "wifi_watchdog_ping_timeout_ms";

        /**
         * Scaling factor for normal window animations. Setting to 0 will disable window
         * animations.
         *
         * @deprecated Use {@link Global#WINDOW_ANIMATION_SCALE} instead
         */
        @Deprecated
        public static final String WINDOW_ANIMATION_SCALE = "window_animation_scale";

        private System() {
        }
    }
}
