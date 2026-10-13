package android.app;

/**
 * This class provides access to the system uimode services.  These services
 * allow applications to control UI modes of the device.
 * It provides functionality to disable the car mode and it gives access to the
 * night mode settings.
 *
 * <p>These facilities are built on top of the underlying
 * {@link android.content.Intent#ACTION_DOCK_EVENT} broadcasts that are sent when the user
 * physical places the device into and out of a dock.  When that happens,
 * the UiModeManager switches the system {@link android.content.res.Configuration}
 * to the appropriate UI mode, sends broadcasts about the mode switch, and
 * starts the corresponding mode activity if appropriate.  See the
 * broadcasts {@link #ACTION_ENTER_CAR_MODE} and
 * {@link #ACTION_ENTER_DESK_MODE} for more information.
 *
 * <p>In addition, the user may manually switch the system to car mode without
 * physically being in a dock.  While in car mode -- whether by manual action
 * from the user or being physically placed in a dock -- a notification is
 * displayed allowing the user to exit dock mode.  Thus the dock mode
 * represented here may be different than the current state of the underlying
 * dock event broadcast.
 */
public final class UiModeManager {
    /**
     * Flag for use with {@link #disableCarMode(int)}: go to the normal
     * home activity as part of the disable.  Disabling this way ensures
     * a clean transition between the current activity (in car mode) and
     * the original home activity (which was typically last running without
     * being in car mode).
     */
    public static final int DISABLE_CAR_MODE_GO_HOME = 1;

    /**
     * Flag for use with {@link #enableCarMode(int)}: allow sleep mode while in car mode.
     * By default, when this flag is not set, the system may hold a full wake lock to keep the
     * screen turned on and prevent the system from entering sleep mode while in car mode.
     * Setting this flag disables such behavior and the system may enter sleep mode
     * if there is no other user activity and no other wake lock held.
     * Setting this flag can be relevant for a car dock application that does not require the
     * screen kept on.
     */
    public static final int ENABLE_CAR_MODE_ALLOW_SLEEP = 2;

    /**
     * Flag for use with {@link #enableCarMode(int)}: go to the car
     * home activity as part of the enable.  Enabling this way ensures
     * a clean transition between the current activity (in non-car-mode) and
     * the car home activity that will serve as home while in car mode.  This
     * will switch to the car home activity even if we are already in car mode.
     */
    public static final int ENABLE_CAR_MODE_GO_CAR_HOME = 1;

    /**
     * Constant for {@link #setNightMode(int)} and {@link #getNightMode()}:
     * automatically switch night mode on and off based on the time.
     */
    public static final int MODE_NIGHT_AUTO = 0;

    /**
     * Constant for {@link #setNightMode(int)} and {@link #getNightMode()}:
     * automatically switch night mode on and off based on the time.
     */
    public static final int MODE_NIGHT_CUSTOM = 3;

    /**
     * Constant for {@link #setNightMode(int)} and {@link #getNightMode()}:
     * never run in night mode.
     */
    public static final int MODE_NIGHT_NO = 1;

    /**
     * Constant for {@link #setNightMode(int)} and {@link #getNightMode()}:
     * always run in night mode.
     */
    public static final int MODE_NIGHT_YES = 2;

    private UiModeManager() {
    }
}
