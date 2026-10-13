package android.os;

/**
 * The BatteryManager class contains strings and constants used for values
 * in the {@link android.content.Intent#ACTION_BATTERY_CHANGED} Intent, and
 * provides a method for querying battery and charging properties.
 */
public final class BatteryManager {
    /**
     * Sent when the device's battery has started charging (or has reached full charge
     * and the device is on power).  This is a good time to do work that you would like to
     * avoid doing while on battery (that is to avoid draining the user's battery due to
     * things they don't care enough about).
     *
     * This is paired with {@link #ACTION_DISCHARGING}.  The current state can always
     * be retrieved with {@link #isCharging()}.
     */
    public static final String ACTION_CHARGING = "android.os.action.CHARGING";

    /**
     * Sent when the device's battery may be discharging, so apps should avoid doing
     * extraneous work that would cause it to discharge faster.
     *
     * This is paired with {@link #ACTION_CHARGING}.  The current state can always
     * be retrieved with {@link #isCharging()}.
     */
    public static final String ACTION_DISCHARGING = "android.os.action.DISCHARGING";

    /**
     * Battery capacity level is critical. The Android framework has been notified to schedule
     * a shutdown by this value.
     *
     * @see #EXTRA_CAPACITY_LEVEL
     */
    public static final int BATTERY_CAPACITY_LEVEL_CRITICAL = 1;

    /**
     * Battery capacity level is full. The battery is full, the battery temperature is within the
     * normal range, and adapter power is enough to sustain running background loads. The Android
     * framework can run background tasks without affecting the battery level or battery
     * performance.
     *
     * @see #EXTRA_CAPACITY_LEVEL
     */
    public static final int BATTERY_CAPACITY_LEVEL_FULL = 5;

    /**
     * Battery capacity level is high. Battery level is high, battery temperature is within the
     * normal range, and adapter power is enough to charge the battery at an acceptable rate
     * while running background loads. The Android framework can run background tasks without
     * affecting charging or battery performance.
     *
     * @see #EXTRA_CAPACITY_LEVEL
     */
    public static final int BATTERY_CAPACITY_LEVEL_HIGH = 4;

    /**
     * Battery capacity level is low. The Android framework must limit background jobs to avoid
     * impacting charging speed.
     *
     * @see #EXTRA_CAPACITY_LEVEL
     */
    public static final int BATTERY_CAPACITY_LEVEL_LOW = 2;

    /**
     * Battery capacity level is normal. Battery level and charging rates are normal, battery
     * temperature is within the normal range, and adapter power is enough to charge the battery
     * at an acceptable rate. The Android framework can run light background tasks without
     * affecting charging performance severely.
     *
     * @see #EXTRA_CAPACITY_LEVEL
     */
    public static final int BATTERY_CAPACITY_LEVEL_NORMAL = 3;

    /**
     * Battery capacity level is unknown.
     *
     * @see #EXTRA_CAPACITY_LEVEL
     */
    public static final int BATTERY_CAPACITY_LEVEL_UNKNOWN = 0;

    /**
     * Battery capacity level is unsupported.
     *
     * @see #EXTRA_CAPACITY_LEVEL
     */
    public static final int BATTERY_CAPACITY_LEVEL_UNSUPPORTED = -1;

    public static final int BATTERY_HEALTH_COLD = 7;

    public static final int BATTERY_HEALTH_DEAD = 4;

    public static final int BATTERY_HEALTH_GOOD = 2;

    public static final int BATTERY_HEALTH_OVERHEAT = 3;

    public static final int BATTERY_HEALTH_OVER_VOLTAGE = 5;

    public static final int BATTERY_HEALTH_UNKNOWN = 1;

    public static final int BATTERY_HEALTH_UNSPECIFIED_FAILURE = 6;

    /**
     * Power source is an AC charger.
     */
    public static final int BATTERY_PLUGGED_AC = 1;

    /**
     * Power source is dock.
     */
    public static final int BATTERY_PLUGGED_DOCK = 8;

    /**
     * Power source is a USB port.
     */
    public static final int BATTERY_PLUGGED_USB = 2;

    /**
     * Power source is wireless.
     */
    public static final int BATTERY_PLUGGED_WIRELESS = 4;

    /**
     * Remaining battery capacity as an integer percentage of total capacity
     * (with no fractional part).
     */
    public static final int BATTERY_PROPERTY_CAPACITY = 4;

    /**
     * Battery capacity in microampere-hours, as an integer.
     */
    public static final int BATTERY_PROPERTY_CHARGE_COUNTER = 1;

    /**
     * Average battery current in microamperes, as an integer.  Positive
     * values indicate net current entering the battery from a charge source,
     * negative values indicate net current discharging from the battery.
     * The time period over which the average is computed may depend on the
     * fuel gauge hardware and its configuration.
     */
    public static final int BATTERY_PROPERTY_CURRENT_AVERAGE = 3;

    /**
     * Instantaneous battery current in microamperes, as an integer.  Positive
     * values indicate net current entering the battery from a charge source,
     * negative values indicate net current discharging from the battery.
     */
    public static final int BATTERY_PROPERTY_CURRENT_NOW = 2;

    /**
     * Battery remaining energy in nanowatt-hours, as a long integer.
     */
    public static final int BATTERY_PROPERTY_ENERGY_COUNTER = 5;

    /**
     * Battery charge status, from a BATTERY_STATUS_* value.
     */
    public static final int BATTERY_PROPERTY_STATUS = 6;

    public static final int BATTERY_STATUS_CHARGING = 2;

    public static final int BATTERY_STATUS_DISCHARGING = 3;

    public static final int BATTERY_STATUS_FULL = 5;

    public static final int BATTERY_STATUS_NOT_CHARGING = 4;

    public static final int BATTERY_STATUS_UNKNOWN = 1;

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * Boolean field indicating whether the battery is currently considered to be
     * low, that is whether a {@link Intent#ACTION_BATTERY_LOW} broadcast
     * has been sent.
     */
    public static final String EXTRA_BATTERY_LOW = "battery_low";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * Int value representing the battery's capacity level. These constants are key indicators of
     * battery status and system capabilities, guiding power management decisions for both the
     * system and apps.
     *
     * @see #BATTERY_CAPACITY_LEVEL_UNSUPPORTED
     * @see #BATTERY_CAPACITY_LEVEL_UNKNOWN
     * @see #BATTERY_CAPACITY_LEVEL_CRITICAL
     * @see #BATTERY_CAPACITY_LEVEL_LOW
     * @see #BATTERY_CAPACITY_LEVEL_NORMAL
     * @see #BATTERY_CAPACITY_LEVEL_HIGH
     * @see #BATTERY_CAPACITY_LEVEL_FULL
     */
    public static final String EXTRA_CAPACITY_LEVEL = "android.os.extra.CAPACITY_LEVEL";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * Int value representing the battery charging status.
     */
    public static final String EXTRA_CHARGING_STATUS = "android.os.extra.CHARGING_STATUS";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * Int value representing the battery charging cycle count.
     */
    public static final String EXTRA_CYCLE_COUNT = "android.os.extra.CYCLE_COUNT";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer containing the current health constant.
     */
    public static final String EXTRA_HEALTH = "health";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer containing the resource ID of a small status bar icon
     * indicating the current battery state.
     */
    public static final String EXTRA_ICON_SMALL = "icon-small";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer field containing the current battery level, from 0 to
     * {@link #EXTRA_SCALE}.
     */
    public static final String EXTRA_LEVEL = "level";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer indicating whether the device is plugged in to a power
     * source; 0 means it is on battery, other constants are different
     * types of power sources.
     */
    public static final String EXTRA_PLUGGED = "plugged";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * boolean indicating whether a battery is present.
     */
    public static final String EXTRA_PRESENT = "present";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer containing the maximum battery level.
     */
    public static final String EXTRA_SCALE = "scale";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer containing the current status constant.
     */
    public static final String EXTRA_STATUS = "status";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * String describing the technology of the current battery.
     */
    public static final String EXTRA_TECHNOLOGY = "technology";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer containing the current battery temperature.
     */
    public static final String EXTRA_TEMPERATURE = "temperature";

    /**
     * Extra for {@link android.content.Intent#ACTION_BATTERY_CHANGED}:
     * integer containing the current battery voltage level.
     */
    public static final String EXTRA_VOLTAGE = "voltage";

    private BatteryManager() {
    }
}
