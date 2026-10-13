package android.app.admin;

/**
 * Definitions for working with security logs.
 *
 * <p>Device owner apps can control the logging with
 * {@link DevicePolicyManager#setSecurityLoggingEnabled}. When security logs are enabled, device
 * owner apps receive periodic callbacks from {@link DeviceAdminReceiver#onSecurityLogsAvailable},
 * at which time new batch of logs can be collected via
 * {@link DevicePolicyManager#retrieveSecurityLogs}. {@link SecurityEvent} describes the type and
 * format of security logs being collected.
 */
public final class SecurityLog {
    /**
     * Event severity level indicating that the event requires urgent admin action.
     */
    public static final int LEVEL_ERROR = 3;

    /**
     * Event severity level indicating that the event corresponds to normal workflow.
     */
    public static final int LEVEL_INFO = 1;

    /**
     * Event severity level indicating that the event may require admin attention.
     */
    public static final int LEVEL_WARNING = 2;

    /**
     * Indicates that a shell command was issued over ADB via {@code adb shell <command>}
     * The log entry contains a {@code String} payload containing the shell command, accessible
     * via {@link SecurityEvent#getData()}. If security logging is enabled on organization-owned
     * managed profile devices, the shell command will be redacted to an empty string.
     */
    public static final int TAG_ADB_SHELL_CMD = 210002;

    /**
     * Indicates that an ADB interactive shell was opened via "adb shell".
     * There is no extra payload in the log event.
     */
    public static final int TAG_ADB_SHELL_INTERACTIVE = 210001;

    /**
     * Indicates that an app process was started. The log entry contains the following
     * information about the process encapsulated in an {@link Object} array, accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] process name ({@code String})
     * <li> [1] exact start time in milliseconds according to {@code System.currentTimeMillis()}
     *      ({@code Long})
     * <li> [2] app uid ({@code Integer})
     * <li> [3] app pid ({@code Integer})
     * <li> [4] seinfo tag ({@code String})
     * <li> [5] SHA-256 hash of the base APK in hexadecimal ({@code String})
     * If security logging is enabled on organization-owned managed profile devices, only events
     * happening inside the managed profile will be visible.
     */
    public static final int TAG_APP_PROCESS_START = 210005;

    /**
     * Indicates that an admin has enabled or disabled backup service. The log entry contains the
     * following information about the event encapsulated in an {@link Object} array, accessible
     * via {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] backup service state ({@code Integer}, 1 for enabled, 0 for disabled)
     * @see DevicePolicyManager#setBackupServiceEnabled(ComponentName, boolean)
     */
    public static final int TAG_BACKUP_SERVICE_TOGGLED = 210044;

    /**
     * Indicates that the device attempts to connect to a Bluetooth device.
     * The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] The MAC address of the Bluetooth device ({@code String})
     * <li> [1] Whether the connection is successful ({@code Integer}, 1 if successful, 0 otherwise)
     * <li> [2] Optional human-readable failure reason, empty string if none ({@code String})
     */
    public static final int TAG_BLUETOOTH_CONNECTION = 210039;

    /**
     * Indicates that the device disconnects from a connected Bluetooth device.
     * The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] The MAC address of the connected Bluetooth device ({@code String})
     * <li> [1] Optional human-readable disconnection reason, empty string if none ({@code String})
     */
    public static final int TAG_BLUETOOTH_DISCONNECTION = 210040;

    /**
     * Indicates that the admin has set policy to disable camera.
     * The log entry contains the following information about the event, encapsulated in an
     * {@link Object} array and accessible via {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] whether the camera is disabled or not ({@code Integer}, 1 if it's disabled,
     *      0 if enabled)
     */
    public static final int TAG_CAMERA_POLICY_SET = 210034;

    /**
     * Indicates that a new root certificate has been installed into system's trusted credential
     * storage. The log entry contains the following information about the event, encapsulated in an
     * {@link Object} array and accessible via {@link SecurityEvent#getData()}:
     * <li> [0] result ({@code Integer}, 0 if operation failed, 1 if succeeded)
     * <li> [1] subject of the certificate ({@code String}).
     * <li> [2] which user the certificate is installed for ({@code Integer}), only available from
     *   version {@link android.os.Build.VERSION_CODES#R}.
     *
     * If security logging is enabled on organization-owned managed profile devices, only events
     * happening inside the managed profile will be visible.
     */
    public static final int TAG_CERT_AUTHORITY_INSTALLED = 210029;

    /**
     * Indicates that a new root certificate has been removed from system's trusted credential
     * storage. The log entry contains the following information about the event, encapsulated in an
     * {@link Object} array and accessible via {@link SecurityEvent#getData()}:
     * <li> [0] result ({@code Integer}, 0 if operation failed, 1 if succeeded)
     * <li> [1] subject of the certificate ({@code String}).
     * <li> [2] which user the certificate is removed from ({@code Integer}), only available from
     *   version {@link android.os.Build.VERSION_CODES#R}.
     *
     * If security logging is enabled on organization-owned managed profile devices, only events
     * happening inside the managed profile will be visible.
     */
    public static final int TAG_CERT_AUTHORITY_REMOVED = 210030;

    /**
     * Indicates a failure to validate X.509v3 certificate. The log entry contains a {@code String}
     * payload indicating the failure reason, accessible via {@link SecurityEvent#getData()}.
     */
    public static final int TAG_CERT_VALIDATION_FAILURE = 210033;

    /**
     * Indicates that cryptographic functionality self test has completed. The log entry contains an
     * {@code Integer} payload, indicating the result of the test (0 if the test failed, 1 if
     * succeeded) and accessible via {@link SecurityEvent#getData()}.
     */
    public static final int TAG_CRYPTO_SELF_TEST_COMPLETED = 210031;

    /**
     * Indicates that an admin has set disabled keyguard features. The log entry contains the
     * following information about the event encapsulated in an {@link Object} array, accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] disabled keyguard feature mask ({@code Integer}).
     * @see DevicePolicyManager#setKeyguardDisabledFeatures(ComponentName, int)
     */
    public static final int TAG_KEYGUARD_DISABLED_FEATURES_SET = 210021;

    /**
     * Indicates that keyguard has been dismissed. This event is only logged if the device
     * has a secure keyguard. It is logged regardless of how keyguard is dismissed, including
     * via PIN/pattern/password, biometrics or via a trust agent.
     * There is no extra payload in the log event.
     * @see #TAG_KEYGUARD_DISMISS_AUTH_ATTEMPT
     */
    public static final int TAG_KEYGUARD_DISMISSED = 210006;

    /**
     * Indicates that there has been an authentication attempt to dismiss the keyguard. The log
     * entry contains the following information about the attempt encapsulated in an {@link Object}
     * array, accessible via {@link SecurityEvent#getData()}:
     * <li> [0] attempt result ({@code Integer}, 1 for successful, 0 for unsuccessful)
     * <li> [1] strength of authentication method ({@code Integer}, 1 if strong authentication
     *      method was used, 0 otherwise)
     */
    public static final int TAG_KEYGUARD_DISMISS_AUTH_ATTEMPT = 210007;

    /**
     * Indicates that the device has been locked, either by the user or by a timeout. There is no
     * extra payload in the log event.
     */
    public static final int TAG_KEYGUARD_SECURED = 210008;

    /**
     * Indicates that a cryptographic key was destroyed. The log entry contains the following
     * information about the event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] result ({@code Integer}, 0 if operation failed, 1 if succeeded)
     * <li> [1] alias of the key ({@code String})
     * <li> [2] requesting process uid ({@code Integer}).
     *
     * If security logging is enabled on organization-owned managed profile devices, only events
     * happening inside the managed profile will be visible.
     */
    public static final int TAG_KEY_DESTRUCTION = 210026;

    /**
     * Indicates that a cryptographic key was generated. The log entry contains the following
     * information about the event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] result ({@code Integer}, 0 if operation failed, 1 if succeeded)
     * <li> [1] alias of the key ({@code String})
     * <li> [2] requesting process uid ({@code Integer}).
     *
     * If security logging is enabled on organization-owned managed profile devices, only events
     * happening inside the managed profile will be visible.
     */
    public static final int TAG_KEY_GENERATED = 210024;

    /**
     * Indicates that a cryptographic key was imported. The log entry contains the following
     * information about the event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] result ({@code Integer}, 0 if operation failed, 1 if succeeded)
     * <li> [1] alias of the key ({@code String})
     * <li> [2] requesting process uid ({@code Integer}).
     *
     * If security logging is enabled on organization-owned managed profile devices, only events
     * happening inside the managed profile will be visible.
     */
    public static final int TAG_KEY_IMPORT = 210025;

    /**
     * Indicates a failed cryptographic key integrity check. The log entry contains the following
     * information about the event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] alias of the key ({@code String})
     * <li> [1] owner application uid ({@code Integer}).
     *
     * If security logging is enabled on organization-owned managed profile devices, only events
     * happening inside the managed profile will be visible.
     */
    public static final int TAG_KEY_INTEGRITY_VIOLATION = 210032;

    /**
     * Indicates start-up of audit logging. There is no extra payload in the log event.
     */
    public static final int TAG_LOGGING_STARTED = 210011;

    /**
     * Indicates shutdown of audit logging. There is no extra payload in the log event.
     */
    public static final int TAG_LOGGING_STOPPED = 210012;

    /**
     * Indicates that the audit log buffer has reached 90% of its capacity. There is no extra
     * payload in the log event.
     */
    public static final int TAG_LOG_BUFFER_SIZE_CRITICAL = 210015;

    /**
     * Indicates that an admin has set a maximum number of failed password attempts before wiping
     * data. The log entry contains the following information about the event encapsulated in an
     * {@link Object} array, accessible via {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] new maximum number of failed password attempts ({@code Integer})
     * @see DevicePolicyManager#setMaximumFailedPasswordsForWipe(ComponentName, int)
     */
    public static final int TAG_MAX_PASSWORD_ATTEMPTS_SET = 210020;

    /**
     * Indicates that an admin has set a maximum screen lock timeout. The log entry contains the
     * following information about the event encapsulated in an {@link Object} array, accessible
     * via {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] new screen lock timeout in milliseconds ({@code Long})
     * @see DevicePolicyManager#setMaximumTimeToLock(ComponentName, long)
     */
    public static final int TAG_MAX_SCREEN_LOCK_TIMEOUT_SET = 210019;

    /**
     * Indicates that removable media has been mounted on the device. The log entry contains the
     * following information about the event, encapsulated in an {@link Object} array and
     * accessible via {@link SecurityEvent#getData()}:
     * <li> [0] mount point ({@code String})
     * <li> [1] volume label ({@code String}). Redacted to empty string on organization-owned
     *     managed profile devices.
     */
    public static final int TAG_MEDIA_MOUNT = 210013;

    /**
     * Indicates that removable media was unmounted from the device. The log entry contains the
     * following information about the event, encapsulated in an {@link Object} array and
     * accessible via {@link SecurityEvent#getData()}:
     * <li> [0] mount point ({@code String})
     * <li> [1] volume label ({@code String}). Redacted to empty string on organization-owned
     *     managed profile devices.
     */
    public static final int TAG_MEDIA_UNMOUNT = 210014;

    /**
     * Indicates that NFC service is disabled. There is no extra payload in the log event.
     */
    public static final int TAG_NFC_DISABLED = 210046;

    /**
     * Indicates that NFC service is enabled. There is no extra payload in the log event.
     */
    public static final int TAG_NFC_ENABLED = 210045;

    /**
     * Indicates that the Android OS has shutdown. There is no extra payload in the log event.
     */
    public static final int TAG_OS_SHUTDOWN = 210010;

    /**
     * Indicates that the Android OS has started. The log entry contains the following information
     * about the startup time software integrity check encapsulated in an {@link Object} array,
     * accessible via {@link SecurityEvent#getData()}:
     * <li> [0] Verified Boot state ({@code String})
     * <li> [1] dm-verity mode ({@code String}).
     * <p>Verified Boot state can be one of the following:
     * <li> {@code green} indicates that there is a full chain of trust extending from the
     * bootloader to verified partitions including the bootloader, boot partition, and all verified
     * partitions.
     * <li> {@code yellow} indicates that the boot partition has been verified using the embedded
     * certificate and the signature is valid.
     * <li> {@code orange} indicates that the device may be freely modified. Device integrity is
     * left to the user to verify out-of-band.
     * <p>dm-verity mode can be one of the following:
     * <li> {@code enforcing} indicates that the device will be restarted when corruption is
     * detected.
     * <li> {@code eio} indicates that an I/O error will be returned for an attempt to read
     * corrupted data blocks.
     * <li> {@code disabled} indicates that integrity check is disabled.
     * For details see Verified Boot documentation.
     */
    public static final int TAG_OS_STARTUP = 210009;

    /**
     * Indicates that a package is installed.
     * The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] Name of the package being installed ({@code String})
     * <li> [1] Package version code ({@code Long})
     * <li> [2] UserId of the user that installed this package ({@code Integer})
     */
    public static final int TAG_PACKAGE_INSTALLED = 210041;

    /**
     * Indicates that a package is uninstalled.
     * The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] Name of the package being uninstalled ({@code String})
     * <li> [1] Package version code ({@code Long})
     * <li> [2] UserId of the user that uninstalled this package ({@code Integer})
     */
    public static final int TAG_PACKAGE_UNINSTALLED = 210043;

    /**
     * Indicates that a package is updated.
     * The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] Name of the package being updated ({@code String})
     * <li> [1] Package version code ({@code Long})
     * <li> [2] UserId of the user that updated this package ({@code Integer})
     */
    public static final int TAG_PACKAGE_UPDATED = 210042;

    /**
     * Indicates that a user has just changed their lockscreen password.
     * The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] complexity for the new password ({@code Integer})
     * <li> [1] target user ID ({@code Integer})
     *
     * <p>Password complexity levels are defined as in
     * {@link DevicePolicyManager#getPasswordComplexity()}
     */
    public static final int TAG_PASSWORD_CHANGED = 210036;

    /**
     * Indicates that an admin has set a password complexity requirement, using the platform's
     * pre-defined complexity levels. The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] Password complexity ({@code Integer})
     *
     * @see DevicePolicyManager#setRequiredPasswordComplexity(int)
     */
    public static final int TAG_PASSWORD_COMPLEXITY_REQUIRED = 210035;

    /**
     * Indicates that an admin has set a requirement for password complexity. The log entry contains
     * the following information about the event, encapsulated in an {@link Object} array and
     * accessible via {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] minimum password length ({@code Integer})
     * <li> [4] password quality constraint ({@code Integer})
     * <li> [5] minimum number of letters ({@code Integer})
     * <li> [6] minimum number of non-letters ({@code Integer})
     * <li> [7] minimum number of digits ({@code Integer})
     * <li> [8] minimum number of uppercase letters ({@code Integer})
     * <li> [9] minimum number of lowercase letters ({@code Integer})
     * <li> [10] minimum number of symbols ({@code Integer})
     *
     * @see DevicePolicyManager#setPasswordMinimumLength(ComponentName, int)
     * @see DevicePolicyManager#setPasswordQuality(ComponentName, int)
     * @see DevicePolicyManager#setPasswordMinimumLetters(ComponentName, int)
     * @see DevicePolicyManager#setPasswordMinimumNonLetter(ComponentName, int)
     * @see DevicePolicyManager#setPasswordMinimumLowerCase(ComponentName, int)
     * @see DevicePolicyManager#setPasswordMinimumUpperCase(ComponentName, int)
     * @see DevicePolicyManager#setPasswordMinimumNumeric(ComponentName, int)
     * @see DevicePolicyManager#setPasswordMinimumSymbols(ComponentName, int)
     */
    public static final int TAG_PASSWORD_COMPLEXITY_SET = 210017;

    /**
     * Indicates that an admin has set a password expiration timeout. The log entry contains the
     * following information about the event, encapsulated in an {@link Object} array and accessible
     * via {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] new password expiration timeout in milliseconds ({@code Long}).
     * @see DevicePolicyManager#setPasswordExpirationTimeout(ComponentName, long)
     */
    public static final int TAG_PASSWORD_EXPIRATION_SET = 210016;

    /**
     * Indicates that an admin has set a password history length. The log entry contains the
     * following information about the event encapsulated in an {@link Object} array, accessible
     * via {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] target user ID ({@code Integer})
     * <li> [3] new password history length value ({@code Integer})
     * @see DevicePolicyManager#setPasswordHistoryLength(ComponentName, int)
     */
    public static final int TAG_PASSWORD_HISTORY_LENGTH_SET = 210018;

    /**
     * Indicates that an admin remotely locked the device or profile. The log entry contains the
     * following information about the event encapsulated in an {@link Object} array, accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String}),
     * <li> [1] admin user ID ({@code Integer}).
     * <li> [2] target user ID ({@code Integer})
     */
    public static final int TAG_REMOTE_LOCK = 210022;

    /**
     * Indicates that a file was pulled from the device via the adb daemon, for example via
     * {@code adb pull}. The log entry contains a {@code String} payload containing the path of the
     * pulled file on the device, accessible via {@link SecurityEvent#getData()}.
     */
    public static final int TAG_SYNC_RECV_FILE = 210003;

    /**
     * Indicates that a file was pushed to the device via the adb daemon, for example via
     * {@code adb push}. The log entry contains a {@code String} payload containing the destination
     * path of the pushed file, accessible via {@link SecurityEvent#getData()}.
     */
    public static final int TAG_SYNC_SEND_FILE = 210004;

    /**
     * Indicates that an admin has set a user restriction. The log entry contains the following
     * information about the event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] user restriction ({@code String})
     * @see DevicePolicyManager#addUserRestriction(ComponentName, String)
     */
    public static final int TAG_USER_RESTRICTION_ADDED = 210027;

    /**
     * Indicates that an admin has removed a user restriction. The log entry contains the following
     * information about the event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] admin package name ({@code String})
     * <li> [1] admin user ID ({@code Integer})
     * <li> [2] user restriction ({@code String})
     * @see DevicePolicyManager#clearUserRestriction(ComponentName, String)
     */
    public static final int TAG_USER_RESTRICTION_REMOVED = 210028;

    /**
     * Indicates that an event occurred as the device attempted to connect to
     * a managed WiFi network. The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] Last 2 octets of the network BSSID ({@code String}, in the form "xx:xx:xx:xx:AA:BB")
     * <li> [1] Type of event that occurred ({@code String}). Event types are CONNECTED,
     *      DISCONNECTED, ASSOCIATING, ASSOCIATED, EAP_METHOD_SELECTED, EAP_FAILURE,
     *      SSID_TEMP_DISABLED, and OPEN_SSL_FAILURE.
     * <li> [2] Optional human-readable failure reason, empty string if none ({@code String})
     */
    public static final int TAG_WIFI_CONNECTION = 210037;

    /**
     * Indicates that the device disconnects from a managed WiFi network.
     * The log entry contains the following information about the
     * event, encapsulated in an {@link Object} array and accessible via
     * {@link SecurityEvent#getData()}:
     * <li> [0] Last 2 octets of the network BSSID ({@code String}, in the form "xx:xx:xx:xx:AA:BB")
     * <li> [1] Optional human-readable disconnection reason, empty string if none ({@code String})
     */
    public static final int TAG_WIFI_DISCONNECTION = 210038;

    /**
     * Indicates a failure to wipe device or user data. There is no extra payload in the log event.
     */
    public static final int TAG_WIPE_FAILURE = 210023;

    private SecurityLog() {
    }
}
