package android.content.pm;

import android.os.Parcelable;

/**
 * Offers the ability to install, upgrade, and remove applications on the
 * device. This includes support for apps packaged either as a single
 * "monolithic" APK, or apps packaged as multiple "split" APKs.
 * <p>
 * An app is delivered for installation through a
 * {@link PackageInstaller.Session}, which any app can create. Once the session
 * is created, the installer can stream one or more APKs into place until it
 * decides to either commit or destroy the session. Committing may require user
 * intervention to complete the installation, unless the caller falls into one of the
 * following categories, in which case the installation will complete automatically.
 * <ul>
 * <li>the device owner
 * <li>the affiliated profile owner
 * </ul>
 * <p>
 * Sessions can install brand new apps, upgrade existing apps, or add new splits
 * into an existing app.
 * <p>
 * Apps packaged as multiple split APKs always consist of a single "base" APK
 * (with a {@code null} split name) and zero or more "split" APKs (with unique
 * split names). Any subset of these APKs can be installed together, as long as
 * the following constraints are met:
 * <ul>
 * <li>All APKs must have the exact same package name, version code, and signing
 * certificates.
 * <li>All APKs must have unique split names.
 * <li>All installations must contain a single base APK.
 * </ul>
 * <p>
 * The ApiDemos project contains examples of using this API:
 * <code>ApiDemos/src/com/example/android/apis/content/InstallApk*.java</code>.
 */
public final class PackageInstaller {
    /**
     * Broadcast Action: Explicit broadcast sent to the last known default launcher when a session
     * for a new install is committed. For managed profile, this is sent to the default launcher
     * of the primary profile.
     * For user-profiles that have items restricted on home screen, this broadcast is sent to
     * the default launcher of the primary profile, only if it has either
     * {@link Manifest.permission.ACCESS_HIDDEN_PROFILES_FULL} or
     * {@link Manifest.permission.ACCESS_HIDDEN_PROFILES} permission.
     * <p>
     * The associated session is defined in {@link #EXTRA_SESSION} and the user for which this
     * session was created in {@link Intent#EXTRA_USER}.
     */
    public static final String ACTION_SESSION_COMMITTED = "android.content.pm.action.SESSION_COMMITTED";

    /**
     * Activity Action: Show details about a particular install session. This
     * may surface actions such as pause, resume, or cancel.
     * <p>
     * This should always be scoped to the installer package that owns the
     * session. Clients should use {@link SessionInfo#createDetailsIntent()} to
     * build this intent correctly.
     * <p>
     * In some cases, a matching Activity may not exist, so ensure you safeguard
     * against this.
     * <p>
     * The session to show details for is defined in {@link #EXTRA_SESSION_ID}.
     */
    public static final String ACTION_SESSION_DETAILS = "android.content.pm.action.SESSION_DETAILS";

    /**
     * Broadcast Action: Send information about a staged install session when its state is updated.
     * <p>
     * The associated session information is defined in {@link #EXTRA_SESSION}.
     */
    public static final String ACTION_SESSION_UPDATED = "android.content.pm.action.SESSION_UPDATED";

    /**
     * The {@link InstallConstraints} object.
     *
     * @see Intent#getParcelableExtra(String, Class)
     * @see #waitForInstallConstraints(List, InstallConstraints, IntentSender, long)
     */
    public static final String EXTRA_INSTALL_CONSTRAINTS = "android.content.pm.extra.INSTALL_CONSTRAINTS";

    /**
     * The {@link InstallConstraintsResult} object.
     *
     * @see Intent#getParcelableExtra(String, Class)
     * @see #waitForInstallConstraints(List, InstallConstraints, IntentSender, long)
     */
    public static final String EXTRA_INSTALL_CONSTRAINTS_RESULT = "android.content.pm.extra.INSTALL_CONSTRAINTS_RESULT";

    /**
     * Another package name relevant to a status. This is typically the package
     * responsible for causing an operation failure.
     *
     * @see Intent#getStringExtra(String)
     */
    public static final String EXTRA_OTHER_PACKAGE_NAME = "android.content.pm.extra.OTHER_PACKAGE_NAME";

    /**
     * Package name that an operation is working with.
     *
     * @see Intent#getStringExtra(String)
     */
    public static final String EXTRA_PACKAGE_NAME = "android.content.pm.extra.PACKAGE_NAME";

    /**
     * Indicate if the status is for a pre-approval request.
     *
     * If callers use the same {@link IntentSender} for both
     * {@link Session#requestUserPreapproval(PreapprovalDetails, IntentSender)} and
     * {@link Session#commit(IntentSender)}, they can use this to differentiate between them.
     *
     * @see Intent#getBooleanExtra(String, boolean)
     */
    public static final String EXTRA_PRE_APPROVAL = "android.content.pm.extra.PRE_APPROVAL";

    /**
     * {@link SessionInfo} that an operation is working with.
     *
     * @see Intent#getParcelableExtra(String)
     */
    public static final String EXTRA_SESSION = "android.content.pm.extra.SESSION";

    /**
     * An integer session ID that an operation is working with.
     *
     * @see Intent#getIntExtra(String, int)
     */
    public static final String EXTRA_SESSION_ID = "android.content.pm.extra.SESSION_ID";

    /**
     * Current status of an operation. Will be one of
     * {@link #STATUS_PENDING_USER_ACTION}, {@link #STATUS_SUCCESS},
     * {@link #STATUS_FAILURE}, {@link #STATUS_FAILURE_ABORTED},
     * {@link #STATUS_FAILURE_BLOCKED}, {@link #STATUS_FAILURE_CONFLICT},
     * {@link #STATUS_FAILURE_INCOMPATIBLE}, {@link #STATUS_FAILURE_INVALID},
     * {@link #STATUS_FAILURE_STORAGE}, or {@link #STATUS_FAILURE_TIMEOUT}.
     * <p>
     * More information about a status may be available through additional
     * extras; see the individual status documentation for details.
     *
     * @see Intent#getIntExtra(String, int)
     */
    public static final String EXTRA_STATUS = "android.content.pm.extra.STATUS";

    /**
     * Detailed string representation of the status, including raw details that
     * are useful for debugging.
     *
     * @see Intent#getStringExtra(String)
     */
    public static final String EXTRA_STATUS_MESSAGE = "android.content.pm.extra.STATUS_MESSAGE";

    /**
     * Storage path relevant to a status.
     *
     * @see Intent#getStringExtra(String)
     */
    public static final String EXTRA_STORAGE_PATH = "android.content.pm.extra.STORAGE_PATH";

    /**
     * If true, the requestor of the unarchival has specified that the app should be unarchived
     * for all users. Sent as part of the {@link android.content.Intent#ACTION_UNARCHIVE_PACKAGE}
     * intent.
     */
    public static final String EXTRA_UNARCHIVE_ALL_USERS = "android.content.pm.extra.UNARCHIVE_ALL_USERS";

    /**
     * Extra field for the unarchive ID. Sent as
     * part of the {@link android.content.Intent#ACTION_UNARCHIVE_PACKAGE} intent.
     *
     * @see SessionParams#setUnarchiveId
     */
    public static final String EXTRA_UNARCHIVE_ID = "android.content.pm.extra.UNARCHIVE_ID";

    /**
     * Extra field for the package name of a package that is requested to be unarchived. Sent as
     * part of the {@link android.content.Intent#ACTION_UNARCHIVE_PACKAGE} intent.
     */
    public static final String EXTRA_UNARCHIVE_PACKAGE_NAME = "android.content.pm.extra.UNARCHIVE_PACKAGE_NAME";

    /**
     * Current status of an unarchive operation. Will be one of
     * {@link #UNARCHIVAL_OK}, {@link #UNARCHIVAL_ERROR_USER_ACTION_NEEDED},
     * {@link #UNARCHIVAL_ERROR_INSUFFICIENT_STORAGE}, {@link #UNARCHIVAL_ERROR_NO_CONNECTIVITY},
     * {@link #UNARCHIVAL_GENERIC_ERROR}, {@link #UNARCHIVAL_ERROR_INSTALLER_DISABLED} or
     * {@link #UNARCHIVAL_ERROR_INSTALLER_UNINSTALLED}.
     *
     * <p> If the status is not {@link #UNARCHIVAL_OK}, then {@link Intent#EXTRA_INTENT} will be set
     * with an intent for a corresponding follow-up action (e.g. storage clearing dialog) or a
     * failure dialog.
     *
     * <p> Used as part of {@link #requestUnarchive} to return the status of the unarchival through
     * the {@link IntentSender}.
     *
     * @see #requestUnarchive
     */
    public static final String EXTRA_UNARCHIVE_STATUS = "android.content.pm.extra.UNARCHIVE_STATUS";

    /**
     * Code indicating that the package being installed comes from a file that was downloaded to
     * the device by the user. For use in place of {@link #PACKAGE_SOURCE_LOCAL_FILE} when the
     * installer knows the package was downloaded.
     */
    public static final int PACKAGE_SOURCE_DOWNLOADED_FILE = 4;

    /**
     * Code indicating that the package being installed comes from a local file on the device. A
     * file manager that is facilitating the installation of an APK file would use this.
     */
    public static final int PACKAGE_SOURCE_LOCAL_FILE = 3;

    /**
     * Code indicating that the package being installed is from a source not reflected by any
     * other package source constant.
     */
    public static final int PACKAGE_SOURCE_OTHER = 1;

    /**
     * Code indicating that the package being installed is from a store. An app store that
     * installs an app for the user would use this.
     */
    public static final int PACKAGE_SOURCE_STORE = 2;

    /**
     * The installer did not call {@link PackageInstaller.SessionParams#setPackageSource(int)} to
     * specify the package source.
     */
    public static final int PACKAGE_SOURCE_UNSPECIFIED = 0;

    /**
     * The operation failed in a generic way. The system will always try to
     * provide a more specific failure reason, but in some rare cases this may
     * be delivered.
     *
     * @see #EXTRA_STATUS_MESSAGE
     */
    public static final int STATUS_FAILURE = 1;

    /**
     * The operation failed because it was actively aborted. For example, the
     * user actively declined requested permissions, or the session was
     * abandoned.
     *
     * @see #EXTRA_STATUS_MESSAGE
     */
    public static final int STATUS_FAILURE_ABORTED = 3;

    /**
     * The operation failed because it was blocked. For example, a device policy
     * may be blocking the operation, a package verifier may have blocked the
     * operation, or the app may be required for core system operation.
     * <p>
     * The result may also contain {@link #EXTRA_OTHER_PACKAGE_NAME} with the
     * specific package blocking the install.
     *
     * @see #EXTRA_STATUS_MESSAGE
     * @see #EXTRA_OTHER_PACKAGE_NAME
     */
    public static final int STATUS_FAILURE_BLOCKED = 2;

    /**
     * The operation failed because it conflicts (or is inconsistent with) with
     * another package already installed on the device. For example, an existing
     * permission, incompatible certificates, etc. The user may be able to
     * uninstall another app to fix the issue.
     * <p>
     * The result may also contain {@link #EXTRA_OTHER_PACKAGE_NAME} with the
     * specific package identified as the cause of the conflict.
     *
     * @see #EXTRA_STATUS_MESSAGE
     * @see #EXTRA_OTHER_PACKAGE_NAME
     */
    public static final int STATUS_FAILURE_CONFLICT = 5;

    /**
     * The operation failed because it is fundamentally incompatible with this
     * device. For example, the app may require a hardware feature that doesn't
     * exist, it may be missing native code for the ABIs supported by the
     * device, or it requires a newer SDK version, etc.
     *
     * Starting in {@link Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, an app with only 32-bit native
     * code can still be installed on a device that supports both 64-bit and 32-bit ABIs.
     * However, a warning dialog will be displayed when the app is launched.
     *
     * @see #EXTRA_STATUS_MESSAGE
     */
    public static final int STATUS_FAILURE_INCOMPATIBLE = 7;

    /**
     * The operation failed because one or more of the APKs was invalid. For
     * example, they might be malformed, corrupt, incorrectly signed,
     * mismatched, etc.
     *
     * @see #EXTRA_STATUS_MESSAGE
     */
    public static final int STATUS_FAILURE_INVALID = 4;

    /**
     * The operation failed because of storage issues. For example, the device
     * may be running low on space, or external media may be unavailable. The
     * user may be able to help free space or insert different external media.
     * <p>
     * The result may also contain {@link #EXTRA_STORAGE_PATH} with the path to
     * the storage device that caused the failure.
     *
     * @see #EXTRA_STATUS_MESSAGE
     * @see #EXTRA_STORAGE_PATH
     */
    public static final int STATUS_FAILURE_STORAGE = 6;

    /**
     * The operation failed because it didn't complete within the specified timeout.
     *
     * @see #EXTRA_STATUS_MESSAGE
     */
    public static final int STATUS_FAILURE_TIMEOUT = 8;

    /**
     * User action is currently required to proceed. You can launch the intent
     * activity described by {@link Intent#EXTRA_INTENT} to involve the user and
     * continue.
     * <p>
     * You may choose to immediately launch the intent if the user is actively
     * using your app. Otherwise, you should use a notification to guide the
     * user back into your app before launching.
     *
     * @see Intent#getParcelableExtra(String)
     */
    public static final int STATUS_PENDING_USER_ACTION = -1;

    /**
     * The operation succeeded.
     */
    public static final int STATUS_SUCCESS = 0;

    /**
     * The installer responsible for the unarchival is disabled.
     *
     * <p> The system will return this status if appropriate. Installers do not need to verify for
     * this error.
     */
    public static final int UNARCHIVAL_ERROR_INSTALLER_DISABLED = 4;

    /**
     * The installer responsible for the unarchival has been uninstalled
     *
     * <p> The system will return this status if appropriate. Installers do not need to verify for
     * this error.
     */
    public static final int UNARCHIVAL_ERROR_INSTALLER_UNINSTALLED = 5;

    /**
     * Not enough storage to unarchive the application.
     *
     * <p> The installer can optionally provide a {@code userActionIntent} for a space-clearing
     * dialog. If no action is provided, then a generic intent
     * {@link android.os.storage.StorageManager#ACTION_MANAGE_STORAGE} is started instead.
     */
    public static final int UNARCHIVAL_ERROR_INSUFFICIENT_STORAGE = 2;

    /**
     * The device is not connected to the internet
     */
    public static final int UNARCHIVAL_ERROR_NO_CONNECTIVITY = 3;

    /**
     * The user needs to interact with the installer to enable the installation.
     *
     * <p> An example use case for this could be that the user needs to login to allow the
     * download for a paid app.
     */
    public static final int UNARCHIVAL_ERROR_USER_ACTION_NEEDED = 1;

    /**
     * Generic error: The app cannot be unarchived.
     */
    public static final int UNARCHIVAL_GENERIC_ERROR = 100;

    /**
     * The unarchival is possible and will commence.
     *
     * <p> Note that this does not mean that the unarchival has completed. This status should be
     * sent before any longer asynchronous action (e.g. app download) is started.
     */
    public static final int UNARCHIVAL_OK = 0;

    private PackageInstaller() {
    }

    /**
     * Details for an active install session.
     */
    public static final class SessionInfo implements Parcelable {
        /**
         * A session ID that does not exist or is invalid.
         */
        public static final int INVALID_ID = -1;

        /**
         * Constant indicating that an error occurred during the activation phase of
         * this session.
         */
        public static final int SESSION_ACTIVATION_FAILED = 2;

        /**
         * Constant indicating that the session was in conflict with another session and had
         * to be sacrificed for resolution.
         */
        public static final int SESSION_CONFLICT = 4;

        /**
         * Constant indicating that no error occurred during the preparation or the activation of
         * this session.
         */
        public static final int SESSION_NO_ERROR = 0;

        /**
         * Constant indicating that an unknown error occurred while processing this session.
         */
        public static final int SESSION_UNKNOWN_ERROR = 3;

        /**
         * Constant indicating that an error occurred during the verification phase of
         * this session.
         */
        public static final int SESSION_VERIFICATION_FAILED = 1;

        /**
         * @deprecated use {@link #SESSION_ACTIVATION_FAILED}.
         */
        @Deprecated
        public static final int STAGED_SESSION_ACTIVATION_FAILED = 2;

        /**
         * @deprecated use {@link #SESSION_CONFLICT}.
         */
        @Deprecated
        public static final int STAGED_SESSION_CONFLICT = 4;

        /**
         * @deprecated use {@link #SESSION_NO_ERROR}.
         */
        @Deprecated
        public static final int STAGED_SESSION_NO_ERROR = 0;

        /**
         * @deprecated use {@link #SESSION_UNKNOWN_ERROR}.
         */
        @Deprecated
        public static final int STAGED_SESSION_UNKNOWN = 3;

        /**
         * @deprecated use {@link #SESSION_VERIFICATION_FAILED}.
         */
        @Deprecated
        public static final int STAGED_SESSION_VERIFICATION_FAILED = 1;

        private SessionInfo() {
        }
    }

    /**
     * Parameters for creating a new {@link PackageInstaller.Session}.
     */
    public static final class SessionParams implements Parcelable {
        /**
         * Mode for an install session whose staged APKs should fully replace any
         * existing APKs for the target app.
         */
        public static final int MODE_FULL_INSTALL = 1;

        /**
         * Mode for an install session that should inherit any existing APKs for the
         * target app, unless they have been explicitly overridden (based on split
         * name) by the session. For example, this can be used to add one or more
         * split APKs to an existing installation.
         * <p>
         * If there are no existing APKs for the target app, this behaves like
         * {@link #MODE_FULL_INSTALL}.
         */
        public static final int MODE_INHERIT_EXISTING = 2;

        /**
         * Value is passed by the installer to {@link #setPermissionState(String, int)} to set
         * the state of a permission. This indicates no preference by the installer, relying on
         * the device's default policy to set the grant state of the permission.
         */
        public static final int PERMISSION_STATE_DEFAULT = 0;

        /**
         * Value is passed by the installer to {@link #setPermissionState(String, int)} to set
         * the state of a permission. This indicates the installers wants to deny the permission
         * by default to the package being installed. The user and other actors in the system may
         * still be able to grant the permission after installation.
         */
        public static final int PERMISSION_STATE_DENIED = 2;

        /**
         * Value is passed by the installer to {@link #setPermissionState(String, int)} to set
         * the state of a permission. This indicates the installers wants to automatically grant
         * the permission to the package being installed. The user and other actors in the system
         * may still be able to deny the permission after installation.
         */
        public static final int PERMISSION_STATE_GRANTED = 1;

        /**
         * This value is passed by the installer to {@link SessionParams#setRequireUserAction(int)}
         * to indicate that user action is not required for this install.
         */
        public static final int USER_ACTION_NOT_REQUIRED = 2;

        /**
         * This value is passed by the installer to {@link SessionParams#setRequireUserAction(int)}
         * to indicate that user action is required for this install.
         */
        public static final int USER_ACTION_REQUIRED = 1;

        /**
         * This value is passed by the installer to {@link SessionParams#setRequireUserAction(int)}
         * to indicate that user action is unspecified for this install.
         * {@code requireUserAction} also defaults to this value unless modified by
         * {@link SessionParams#setRequireUserAction(int)}
         */
        public static final int USER_ACTION_UNSPECIFIED = 0;

        private SessionParams() {
        }
    }
}
