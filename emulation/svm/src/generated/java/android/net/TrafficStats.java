package android.net;

/**
 * Class that provides network traffic statistics. These statistics include
 * bytes transmitted and received and network packets transmitted and received,
 * over all interfaces, over the mobile interface, and on a per-UID basis.
 * <p>
 * These statistics may not be available on all platforms. If the statistics are
 * not supported by this device, {@link #UNSUPPORTED} will be returned.
 * <p>
 * Note that the statistics returned by this class reset and start from zero
 * after every reboot. To access more robust historical network statistics data,
 * use {@link NetworkStatsManager} instead.
 */
public final class TrafficStats {
    /**
     * The return value to indicate that the device does not support the statistic.
     */
    public static final int UNSUPPORTED = -1;

    private TrafficStats() {
    }
}
