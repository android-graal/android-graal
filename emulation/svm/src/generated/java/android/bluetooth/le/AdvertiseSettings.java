package android.bluetooth.le;

import android.os.Parcelable;

/**
 * The {@link AdvertiseSettings} provide a way to adjust advertising preferences for each Bluetooth
 * LE advertisement instance. Use {@link AdvertiseSettings.Builder} to create an instance of this
 * class.
 */
public final class AdvertiseSettings implements Parcelable {
    /**
     * Perform Bluetooth LE advertising in balanced power mode. This is balanced between advertising
     * frequency and power consumption.
     */
    public static final int ADVERTISE_MODE_BALANCED = 1;

    /**
     * Perform Bluetooth LE advertising in low latency, high power mode. This has the highest power
     * consumption and should not be used for continuous background advertising.
     */
    public static final int ADVERTISE_MODE_LOW_LATENCY = 2;

    /**
     * Perform Bluetooth LE advertising in low power mode. This is the default and preferred
     * advertising mode as it consumes the least power.
     */
    public static final int ADVERTISE_MODE_LOW_POWER = 0;

    /**
     * Advertise using high TX power level. This corresponds to largest visibility range of the
     * advertising packet.
     */
    public static final int ADVERTISE_TX_POWER_HIGH = 3;

    /**
     * Advertise using low TX power level.
     */
    public static final int ADVERTISE_TX_POWER_LOW = 1;

    /**
     * Advertise using medium TX power level.
     */
    public static final int ADVERTISE_TX_POWER_MEDIUM = 2;

    /**
     * Advertise using the lowest transmission (TX) power level. Low transmission power can be used
     * to restrict the visibility range of advertising packets.
     */
    public static final int ADVERTISE_TX_POWER_ULTRA_LOW = 0;

    private AdvertiseSettings() {
    }
}
