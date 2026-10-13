package android.content.pm;

import android.os.Parcelable;

/**
 * Represents a shortcut that can be published via {@link ShortcutManager}.
 *
 * @see ShortcutManager
 */
public final class ShortcutInfo implements Parcelable {
    /**
     * Shortcut has been disabled due to changes to the publisher app. (e.g. a manifest shortcut
     * no longer exists.)
     */
    public static final int DISABLED_REASON_APP_CHANGED = 2;

    /**
     * Shortcut has not been restored because the publisher app does not support backup and restore.
     */
    public static final int DISABLED_REASON_BACKUP_NOT_SUPPORTED = 101;

    /**
     * Shortcut has been disabled by the publisher app with the
     * {@link ShortcutManager#disableShortcuts(List)} API.
     */
    public static final int DISABLED_REASON_BY_APP = 1;

    /**
     * Shortcut is not disabled.
     */
    public static final int DISABLED_REASON_NOT_DISABLED = 0;

    /**
     * Shortcut has not been restored for unknown reason.
     */
    public static final int DISABLED_REASON_OTHER_RESTORE_ISSUE = 103;

    /**
     * Shortcut has not been restored because the publisher app's signature has changed.
     */
    public static final int DISABLED_REASON_SIGNATURE_MISMATCH = 102;

    /**
     * Shortcut is disabled for an unknown reason.
     */
    public static final int DISABLED_REASON_UNKNOWN = 3;

    /**
     * Shortcut has been restored from the previous device, but the publisher app on the current
     * device is of a lower version. The shortcut will not be usable until the app is upgraded to
     * the same version or higher.
     */
    public static final int DISABLED_REASON_VERSION_LOWER = 100;

    /**
     * Shortcut category for messaging related actions, such as chat.
     */
    public static final String SHORTCUT_CATEGORY_CONVERSATION = "android.shortcut.conversation";

    /**
     * Indicates system surfaces managed by a launcher app. e.g. Long-Press Menu.
     */
    public static final int SURFACE_LAUNCHER = 1;

    private ShortcutInfo() {
    }
}
