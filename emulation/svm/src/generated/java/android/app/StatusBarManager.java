package android.app;

/**
 * Allows an app to control the status bar.
 */
public final class StatusBarManager {
    /**
     * Indicates that the requesting application is not in the foreground.
     */
    public static final int TILE_ADD_REQUEST_ERROR_APP_NOT_IN_FOREGROUND = 1004;

    /**
     * Indicates that the component does not match an enabled exported
     * {@link android.service.quicksettings.TileService} for the current user.
     */
    public static final int TILE_ADD_REQUEST_ERROR_BAD_COMPONENT = 1002;

    /**
     * Indicates that this package does not match that of the
     * {@link android.service.quicksettings.TileService}.
     */
    public static final int TILE_ADD_REQUEST_ERROR_MISMATCHED_PACKAGE = 1000;

    /**
     * Indicates that the user is not the current user.
     */
    public static final int TILE_ADD_REQUEST_ERROR_NOT_CURRENT_USER = 1003;

    /**
     * The request could not be processed because no fulfilling service was found. This could be
     * a temporary issue (for example, SystemUI has crashed).
     */
    public static final int TILE_ADD_REQUEST_ERROR_NO_STATUS_BAR_SERVICE = 1005;

    /**
     * Indicates that there's a request in progress for this package.
     */
    public static final int TILE_ADD_REQUEST_ERROR_REQUEST_IN_PROGRESS = 1001;

    /**
     * Response indicating that the tile was added.
     */
    public static final int TILE_ADD_REQUEST_RESULT_TILE_ADDED = 2;

    /**
     * Response indicating that the tile was already added and the user was not prompted.
     */
    public static final int TILE_ADD_REQUEST_RESULT_TILE_ALREADY_ADDED = 1;

    /**
     * Response indicating that the tile was not added.
     */
    public static final int TILE_ADD_REQUEST_RESULT_TILE_NOT_ADDED = 0;

    private StatusBarManager() {
    }
}
