package android.location;

/**
 * This class represents the current state of the GPS engine and is used in conjunction with {@link
 * GpsStatus.Listener}.
 *
 * @deprecated Use {@link GnssStatus} instead.
 */
@Deprecated
public final class GpsStatus {
    /**
     * Event sent when the GPS system has received its first fix since starting.
     * Call {@link #getTimeToFirstFix()} to find the time from start to first fix.
     */
    @Deprecated
    public static final int GPS_EVENT_FIRST_FIX = 3;

    /**
     * Event sent periodically to report GPS satellite status.
     * Call {@link #getSatellites()} to retrieve the status for each satellite.
     */
    @Deprecated
    public static final int GPS_EVENT_SATELLITE_STATUS = 4;

    /**
     * Event sent when the GPS system has started.
     */
    @Deprecated
    public static final int GPS_EVENT_STARTED = 1;

    /**
     * Event sent when the GPS system has stopped.
     */
    @Deprecated
    public static final int GPS_EVENT_STOPPED = 2;

    private GpsStatus() {
    }
}
