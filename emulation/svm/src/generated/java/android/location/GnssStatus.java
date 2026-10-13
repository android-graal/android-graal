package android.location;

import android.os.Parcelable;

/**
 * This class represents the current state of the GNSS engine and is used in conjunction with
 * {@link GnssStatus.Callback}.
 *
 * @see LocationManager#registerGnssStatusCallback
 * @see GnssStatus.Callback
 */
public final class GnssStatus implements Parcelable {
    /**
     * Constellation type constant for Beidou.
     */
    public static final int CONSTELLATION_BEIDOU = 5;

    /**
     * Constellation type constant for Galileo.
     */
    public static final int CONSTELLATION_GALILEO = 6;

    /**
     * Constellation type constant for Glonass.
     */
    public static final int CONSTELLATION_GLONASS = 3;

    /**
     * Constellation type constant for GPS.
     */
    public static final int CONSTELLATION_GPS = 1;

    /**
     * Constellation type constant for IRNSS.
     */
    public static final int CONSTELLATION_IRNSS = 7;

    /**
     * Constellation type constant for QZSS.
     */
    public static final int CONSTELLATION_QZSS = 4;

    /**
     * Constellation type constant for SBAS.
     */
    public static final int CONSTELLATION_SBAS = 2;

    /**
     * Unknown constellation type.
     */
    public static final int CONSTELLATION_UNKNOWN = 0;

    private GnssStatus() {
    }
}
