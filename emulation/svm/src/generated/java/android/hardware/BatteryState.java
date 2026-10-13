package android.hardware;

/**
 * The BatteryState class is a representation of a single battery on a device.
 */
public final class BatteryState {
    /**
     * Battery is charging.
     */
    public static final int STATUS_CHARGING = 2;

    /**
     * Battery is discharging.
     */
    public static final int STATUS_DISCHARGING = 3;

    /**
     * Battery is full.
     */
    public static final int STATUS_FULL = 5;

    /**
     * Battery is connected to power but not charging.
     */
    public static final int STATUS_NOT_CHARGING = 4;

    /**
     * Battery status is unknown.
     */
    public static final int STATUS_UNKNOWN = 1;

    private BatteryState() {
    }
}
