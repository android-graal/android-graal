package android.content.pm;

import android.os.Parcelable;

/**
 * The LauncherUserInfo object holds information about an Android user that is required to display
 * the Launcher related UI elements specific to the user (like badges).
 */
public final class LauncherUserInfo implements Parcelable {
    /**
     * A boolean extra indicating whether the private space entrypoint should be hidden when locked.
     *
     * @see #getUserConfig
     */
    public static final String PRIVATE_SPACE_ENTRYPOINT_HIDDEN = "private_space_entrypoint_hidden";

    private LauncherUserInfo() {
    }
}
