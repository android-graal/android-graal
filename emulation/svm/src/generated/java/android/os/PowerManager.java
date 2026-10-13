package android.os;

/**
 * This class lets you query and request control of aspects of the device's power state.
 */
public final class PowerManager {
    /**
     * Wake lock flag: Turn the screen on when the wake lock is acquired.
     * <p>
     * This flag will require {@link android.Manifest.permission#TURN_SCREEN_ON} in future releases.
     * </p><p>
     * Normally wake locks don't actually wake the device, they just cause the screen to remain on
     * once it's already on. This flag will cause the device to wake up when the wake lock is
     * acquired.
     * </p><p>
     * Android TV playback devices attempt to turn on the HDMI-connected TV via HDMI-CEC on any
     * wake-up, including wake-ups triggered by wake locks.
     * </p><p>
     * Cannot be used with {@link #PARTIAL_WAKE_LOCK}.
     * </p>
     *
     * @deprecated Most applications should use {@link android.R.attr#turnScreenOn} or
     * {@link android.app.Activity#setTurnScreenOn(boolean)} instead, as this prevents the previous
     * foreground app from being resumed first when the screen turns on.
     */
    @Deprecated
    public static final int ACQUIRE_CAUSES_WAKEUP = 268435456;

    /**
     * Intent that is broadcast when the state of {@link #isDeviceIdleMode()} changes.
     * This broadcast is only sent to registered receivers.
     */
    public static final String ACTION_DEVICE_IDLE_MODE_CHANGED = "android.os.action.DEVICE_IDLE_MODE_CHANGED";

    /**
     * Intent that is broadcast when the state of {@link #isDeviceLightIdleMode()} changes.
     * This broadcast is only sent to registered receivers.
     */
    public static final String ACTION_DEVICE_LIGHT_IDLE_MODE_CHANGED = "android.os.action.LIGHT_DEVICE_IDLE_MODE_CHANGED";

    /**
     * Intent that is broadcast when Low Power Standby is enabled or disabled.
     * This broadcast is only sent to registered receivers and receivers holding
     * {@code android.permission.MANAGE_LOW_POWER_STANDBY}.
     *
     * @see #isLowPowerStandbyEnabled()
     */
    public static final String ACTION_LOW_POWER_STANDBY_ENABLED_CHANGED = "android.os.action.LOW_POWER_STANDBY_ENABLED_CHANGED";

    /**
     * Intent that is broadcast when Low Power Standby policy is changed.
     * This broadcast is only sent to registered receivers and receivers holding
     * {@code android.permission.MANAGE_LOW_POWER_STANDBY}.
     *
     * @see #isExemptFromLowPowerStandby()
     * @see #isAllowedInLowPowerStandby(int)
     * @see #isAllowedInLowPowerStandby(String)
     */
    public static final String ACTION_LOW_POWER_STANDBY_POLICY_CHANGED = "android.os.action.LOW_POWER_STANDBY_POLICY_CHANGED";

    /**
     * Intent that is broadcast when the state of {@link #isPowerSaveMode()} changes.
     * This broadcast is only sent to registered receivers.
     */
    public static final String ACTION_POWER_SAVE_MODE_CHANGED = "android.os.action.POWER_SAVE_MODE_CHANGED";

    /**
     * Signals that wake-on-lan/wake-on-wlan is allowed in Low Power Standby.
     *
     * <p>If Low Power Standby is enabled ({@link #isLowPowerStandbyEnabled()}),
     * wake-on-lan/wake-on-wlan may not be available while in standby.
     * Use {@link #isAllowedInLowPowerStandby(String)} to determine whether the device allows this
     * feature to be used during Low Power Standby with the currently active Low Power Standby
     * policy.
     *
     * @see #isAllowedInLowPowerStandby(String)
     */
    public static final String FEATURE_WAKE_ON_LAN_IN_LOW_POWER_STANDBY = "com.android.lowpowerstandby.WAKE_ON_LAN";

    /**
     * Wake lock level: Ensures that the screen and keyboard backlight are on at
     * full brightness.
     * <p>
     * If the user presses the power button, then the {@link #FULL_WAKE_LOCK} will be
     * implicitly released by the system, causing both the screen and the CPU to be turned off.
     * Contrast with {@link #PARTIAL_WAKE_LOCK}.
     * </p>
     *
     * @deprecated Most applications should use
     * {@link android.view.WindowManager.LayoutParams#FLAG_KEEP_SCREEN_ON} instead
     * of this type of wake lock, as it will be correctly managed by the platform
     * as the user moves between applications and doesn't require a special permission.
     */
    @Deprecated
    public static final int FULL_WAKE_LOCK = 26;

    /**
     * All location providers should be disabled when battery saver is on and
     * the device is non-interactive.
     */
    public static final int LOCATION_MODE_ALL_DISABLED_WHEN_SCREEN_OFF = 2;

    /**
     * In this mode, all the location providers will be kept available, but location fixes
     * should only be provided to foreground apps.
     */
    public static final int LOCATION_MODE_FOREGROUND_ONLY = 3;

    /**
     * In this mode, the GPS based location provider should be disabled when battery saver is on and
     * the device is non-interactive.
     */
    public static final int LOCATION_MODE_GPS_DISABLED_WHEN_SCREEN_OFF = 1;

    /**
     * Either the location providers shouldn't be affected by battery saver,
     * or battery saver is off.
     */
    public static final int LOCATION_MODE_NO_CHANGE = 0;

    /**
     * In this mode, location will not be turned off, but LocationManager will throttle all
     * requests to providers when the device is non-interactive.
     */
    public static final int LOCATION_MODE_THROTTLE_REQUESTS_WHEN_SCREEN_OFF = 4;

    /**
     * Exempts apps with ongoing calls.
     *
     * <p>This includes apps with foreground services of type "phoneCall".
     *
     * @see #isAllowedInLowPowerStandby(int)
     */
    public static final int LOW_POWER_STANDBY_ALLOWED_REASON_ONGOING_CALL = 4;

    /**
     * Exempts apps on the temporary powersave allowlist.
     *
     * @see #isAllowedInLowPowerStandby(int)
     */
    public static final int LOW_POWER_STANDBY_ALLOWED_REASON_TEMP_POWER_SAVE_ALLOWLIST = 2;

    /**
     * Exempts active Voice Interaction Sessions in Low Power Standby.
     *
     * @see #isAllowedInLowPowerStandby(int)
     */
    public static final int LOW_POWER_STANDBY_ALLOWED_REASON_VOICE_INTERACTION = 1;

    /**
     * Wake lock flag: When this wake lock is released, poke the user activity timer
     * so the screen stays on for a little longer.
     * <p>
     * This will not turn the screen on if it is not already on.
     * </p><p>
     * Cannot be used with {@link #PARTIAL_WAKE_LOCK}.
     * </p>
     */
    public static final int ON_AFTER_RELEASE = 536870912;

    /**
     * Wake lock level: Ensures that the CPU is running; the screen and keyboard
     * backlight will be allowed to go off.
     * <p>
     * If the user presses the power button, then the screen will be turned off
     * but the CPU will be kept on until all partial wake locks have been released.
     * </p>
     */
    public static final int PARTIAL_WAKE_LOCK = 1;

    /**
     * Wake lock level: Turns the screen off when the proximity sensor activates.
     * <p>
     * If the proximity sensor detects that an object is nearby, the screen turns off
     * immediately.  Shortly after the object moves away, the screen turns on again.
     * </p><p>
     * A proximity wake lock does not prevent the device from falling asleep
     * unlike {@link #FULL_WAKE_LOCK}, {@link #SCREEN_BRIGHT_WAKE_LOCK} and
     * {@link #SCREEN_DIM_WAKE_LOCK}.  If there is no user activity and no other
     * wake locks are held, then the device will fall asleep (and lock) as usual.
     * However, the device will not fall asleep while the screen has been turned off
     * by the proximity sensor because it effectively counts as ongoing user activity.
     * </p><p>
     * Since not all devices have proximity sensors, use {@link #isWakeLockLevelSupported}
     * to determine whether this wake lock level is supported.
     * </p><p>
     * Cannot be used with {@link #ACQUIRE_CAUSES_WAKEUP}.
     * </p>
     */
    public static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;

    /**
     * Flag for {@link WakeLock#release WakeLock.release(int)}: Defer releasing a
     * {@link #PROXIMITY_SCREEN_OFF_WAKE_LOCK} wake lock until the proximity sensor
     * indicates that an object is not in close proximity.
     */
    public static final int RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY = 1;

    /**
     * Wake lock level: Ensures that the screen is on at full brightness;
     * the keyboard backlight will be allowed to go off.
     * <p>
     * If the user presses the power button, then the {@link #SCREEN_BRIGHT_WAKE_LOCK} will be
     * implicitly released by the system, causing both the screen and the CPU to be turned off.
     * Contrast with {@link #PARTIAL_WAKE_LOCK}.
     * </p>
     *
     * @deprecated Most applications should use
     * {@link android.view.WindowManager.LayoutParams#FLAG_KEEP_SCREEN_ON} instead
     * of this type of wake lock, as it will be correctly managed by the platform
     * as the user moves between applications and doesn't require a special permission.
     */
    @Deprecated
    public static final int SCREEN_BRIGHT_WAKE_LOCK = 10;

    /**
     * Wake lock level: Ensures that the screen is on (but may be dimmed);
     * the keyboard backlight will be allowed to go off.
     * <p>
     * If the user presses the power button, then the {@link #SCREEN_DIM_WAKE_LOCK} will be
     * implicitly released by the system, causing both the screen and the CPU to be turned off.
     * Contrast with {@link #PARTIAL_WAKE_LOCK}.
     * </p>
     *
     * @deprecated Most applications should use
     * {@link android.view.WindowManager.LayoutParams#FLAG_KEEP_SCREEN_ON} instead
     * of this type of wake lock, as it will be correctly managed by the platform
     * as the user moves between applications and doesn't require a special permission.
     */
    @Deprecated
    public static final int SCREEN_DIM_WAKE_LOCK = 6;

    /**
     * Thermal status code: Platform has done everything to reduce power.
     */
    public static final int THERMAL_STATUS_CRITICAL = 4;

    /**
     * Thermal status code: Key components in platform are shutting down due to thermal condition.
     * Device functionalities will be limited.
     */
    public static final int THERMAL_STATUS_EMERGENCY = 5;

    /**
     * Thermal status code: Light throttling where UX is not impacted.
     */
    public static final int THERMAL_STATUS_LIGHT = 1;

    /**
     * Thermal status code: Moderate throttling where UX is not largely impacted.
     */
    public static final int THERMAL_STATUS_MODERATE = 2;

    /**
     * Thermal status code: Not under throttling.
     */
    public static final int THERMAL_STATUS_NONE = 0;

    /**
     * Thermal status code: Severe throttling where UX is largely impacted.
     */
    public static final int THERMAL_STATUS_SEVERE = 3;

    /**
     * Thermal status code: Need shutdown immediately.
     */
    public static final int THERMAL_STATUS_SHUTDOWN = 6;

    private PowerManager() {
    }
}
