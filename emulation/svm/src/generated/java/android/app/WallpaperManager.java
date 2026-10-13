package android.app;

/**
 * Provides access to the system wallpaper. With WallpaperManager, you can
 * get the current wallpaper, get the desired dimensions for the wallpaper, set
 * the wallpaper, and more.
 *
 * <p> An app can check whether wallpapers are supported for the current user, by calling
 * {@link #isWallpaperSupported()}, and whether setting of wallpapers is allowed, by calling
 * {@link #isSetWallpaperAllowed()}.
 * Any public APIs added to WallpaperManager should have a corresponding stub in
 * {@link DisabledWallpaperManager}.
 */
public final class WallpaperManager {
    /**
     * Directly launch live wallpaper preview, allowing the user to immediately
     * confirm to switch to a specific live wallpaper.  You must specify
     * {@link #EXTRA_LIVE_WALLPAPER_COMPONENT} with the ComponentName of
     * a live wallpaper component that is to be shown.
     */
    public static final String ACTION_CHANGE_LIVE_WALLPAPER = "android.service.wallpaper.CHANGE_LIVE_WALLPAPER";

    /**
     * Activity Action: Show settings for choosing wallpaper. Do not use directly to construct
     * an intent; instead, use {@link #getCropAndSetWallpaperIntent}.
     * <p>Input:  {@link Intent#getData} is the URI of the image to crop and set as wallpaper.
     * <p>Output: RESULT_OK if user decided to crop/set the wallpaper, RESULT_CANCEL otherwise
     * Activities that support this intent should specify a MIME filter of "image/*"
     */
    public static final String ACTION_CROP_AND_SET_WALLPAPER = "android.service.wallpaper.CROP_AND_SET_WALLPAPER";

    /**
     * Launch an activity for the user to pick the current global live
     * wallpaper.
     */
    public static final String ACTION_LIVE_WALLPAPER_CHOOSER = "android.service.wallpaper.LIVE_WALLPAPER_CHOOSER";

    /**
     * Command for {@link #sendWallpaperCommand}: reported by the wallpaper
     * host when the user drops an object into an area of the host.  The x
     * and y arguments are the location of the drop.
     */
    public static final String COMMAND_DROP = "android.home.drop";

    /**
     * Command for {@link #sendWallpaperCommand}: reported by the wallpaper
     * host when the user releases a secondary pointer on an empty area
     * (not performing an action in the host).  The x and y arguments are
     * the location of the secondary tap in screen coordinates.
     */
    public static final String COMMAND_SECONDARY_TAP = "android.wallpaper.secondaryTap";

    /**
     * Command for {@link #sendWallpaperCommand}: reported by the wallpaper
     * host when the user taps on an empty area (not performing an action
     * in the host).  The x and y arguments are the location of the tap in
     * screen coordinates.
     */
    public static final String COMMAND_TAP = "android.wallpaper.tap";

    /**
     * Extra in {@link #ACTION_CHANGE_LIVE_WALLPAPER} that specifies the
     * ComponentName of a live wallpaper that should be shown as a preview,
     * for the user to confirm.
     */
    public static final String EXTRA_LIVE_WALLPAPER_COMPONENT = "android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT";

    /**
     * Flag: set or retrieve the lock-screen-specific wallpaper.
     */
    public static final int FLAG_LOCK = 2;

    /**
     * Flag: set or retrieve the general system wallpaper.
     */
    public static final int FLAG_SYSTEM = 1;

    /**
     * Manifest entry for activities that respond to {@link Intent#ACTION_SET_WALLPAPER}
     * which allows them to provide a custom large icon associated with this action.
     */
    public static final String WALLPAPER_PREVIEW_META_DATA = "android.wallpaper.preview";

    private WallpaperManager() {
    }
}
