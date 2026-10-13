package android.app;

/**
 * The GameManager allows system apps to modify and query the game mode of apps.
 *
 * <p><b>Note:</b> After {@link android.os.Build.VERSION_CODES#VANILLA_ICE_CREAM}, some devices
 * that do not support the GameManager features <i>may</i> not publish a GameManager instance.
 * These device types include:
 * <ul>
 *      <li> Wear devices ({@link PackageManager#FEATURE_WATCH})
 * </ul>
 *
 * <p>Therefore, you should always do a {@code null} check on the return value of
 * {@link Context#getSystemService(Class)} and {@link Context#getSystemService(String)} when trying
 * to obtain an instance of GameManager on the aforementioned device types.
 */
public final class GameManager {
    /**
     * Battery game mode will save battery and give longer game play time.
     */
    public static final int GAME_MODE_BATTERY = 3;

    /**
     * Custom game mode that has user-provided configuration overrides.
     * <p>
     * Custom game mode is expected to be handled only by the platform using users'
     * preferred config. It is currently not allowed to opt in custom mode in game mode XML file nor
     * expected to perform app-based optimizations when activated.
     */
    public static final int GAME_MODE_CUSTOM = 4;

    /**
     * Performance game mode maximizes the game's performance.
     * <p>
     * This game mode is highly likely to increase battery consumption.
     */
    public static final int GAME_MODE_PERFORMANCE = 2;

    /**
     * Standard game mode means the platform will use the game's default
     * performance characteristics.
     */
    public static final int GAME_MODE_STANDARD = 1;

    /**
     * Game mode is not supported for this application.
     */
    public static final int GAME_MODE_UNSUPPORTED = 0;

    private GameManager() {
    }
}
