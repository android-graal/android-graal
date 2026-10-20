package android.bluetooth.le;

import android.os.Parcelable;

/**
 * The {@link AdvertisingSetParameters} provide a way to adjust advertising preferences for each
 * Bluetooth LE advertising set. Use {@link AdvertisingSetParameters.Builder} to create an instance
 * of this class.
 */
public final class AdvertisingSetParameters implements Parcelable {
    /**
     * Advertise on low frequency, around every 1000ms. This is the default and preferred
     * advertising mode as it consumes the least power.
     */
    public static final int INTERVAL_HIGH = 1600;

    /**
     * Perform high frequency, low latency advertising, around every 100ms. This has the highest
     * power consumption and should not be used for continuous background advertising.
     */
    public static final int INTERVAL_LOW = 160;

    /**
     * Maximum value for advertising interval.
     */
    public static final int INTERVAL_MAX = 16777215;

    /**
     * Advertise on medium frequency, around every 250ms. This is balanced between advertising
     * frequency and power consumption.
     */
    public static final int INTERVAL_MEDIUM = 400;

    /**
     * Minimum value for advertising interval.
     */
    public static final int INTERVAL_MIN = 160;

    /**
     * Advertise using high TX power level. This corresponds to largest visibility range of the
     * advertising packet.
     */
    public static final int TX_POWER_HIGH = 1;

    /**
     * Advertise using low TX power level.
     */
    public static final int TX_POWER_LOW = -15;

    /**
     * Maximum value for TX power.
     */
    public static final int TX_POWER_MAX = 1;

    /**
     * Advertise using medium TX power level.
     */
    public static final int TX_POWER_MEDIUM = -7;

    /**
     * Minimum value for TX power.
     */
    public static final int TX_POWER_MIN = -127;

    /**
     * Advertise using the lowest transmission (TX) power level. Low transmission power can be used
     * to restrict the visibility range of advertising packets.
     */
    public static final int TX_POWER_ULTRA_LOW = -21;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AdvertisingSetParameters() {
    }
}
