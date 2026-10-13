package android.location;

import android.os.Parcelable;

/**
 * A class containing a GNSS satellite Navigation Message.
 */
public final class GnssNavigationMessage implements Parcelable {
    /**
     * The Navigation Message was received without any parity error in its navigation words.
     */
    public static final int STATUS_PARITY_PASSED = 1;

    /**
     * The Navigation Message was received with words that failed parity check, but the receiver was
     * able to correct those words.
     */
    public static final int STATUS_PARITY_REBUILT = 2;

    /**
     * The Navigation Message Status is 'unknown'.
     */
    public static final int STATUS_UNKNOWN = 0;

    /**
     * Beidou CNAV1 message contained in the structure.
     */
    public static final int TYPE_BDS_CNAV1 = 1283;

    /**
     * Beidou CNAV2 message contained in the structure.
     */
    public static final int TYPE_BDS_CNAV2 = 1284;

    /**
     * Beidou D1 message contained in the structure.
     */
    public static final int TYPE_BDS_D1 = 1281;

    /**
     * Beidou D2 message contained in the structure.
     */
    public static final int TYPE_BDS_D2 = 1282;

    /**
     * Galileo F/NAV message contained in the structure.
     */
    public static final int TYPE_GAL_F = 1538;

    /**
     * Galileo I/NAV message contained in the structure.
     */
    public static final int TYPE_GAL_I = 1537;

    /**
     * Glonass L1 CA message contained in the structure.
     */
    public static final int TYPE_GLO_L1CA = 769;

    /**
     * GPS CNAV-2 message contained in the structure.
     */
    public static final int TYPE_GPS_CNAV2 = 260;

    /**
     * GPS L1 C/A message contained in the structure.
     */
    public static final int TYPE_GPS_L1CA = 257;

    /**
     * GPS L2-CNAV message contained in the structure.
     */
    public static final int TYPE_GPS_L2CNAV = 258;

    /**
     * GPS L5-CNAV message contained in the structure.
     */
    public static final int TYPE_GPS_L5CNAV = 259;

    /**
     * NavIC L1 message contained in the structure.
     */
    public static final int TYPE_IRN_L1 = 1795;

    /**
     * NavIC L5 message contained in the structure.
     */
    public static final int TYPE_IRN_L5 = 1794;

    /**
     * NavIC L5 C/A message contained in the structure.
     */
    public static final int TYPE_IRN_L5CA = 1793;

    /**
     * QZSS L1 C/A message contained in the structure.
     */
    public static final int TYPE_QZS_L1CA = 1025;

    /**
     * SBAS message contained in the structure.
     */
    public static final int TYPE_SBS = 513;

    /**
     * Message type unknown
     */
    public static final int TYPE_UNKNOWN = 0;

    private GnssNavigationMessage() {
    }

    /**
     * Used for receiving GNSS satellite Navigation Messages from the GNSS engine.
     *
     * <p>You can implement this interface and call
     * {@link LocationManager#registerGnssNavigationMessageCallback}.
     */
    public static final class Callback {
        /**
         * GNSS provider or Location is disabled, updated will not be received until they are
         * enabled.
         *
         * @deprecated Do not use.
         */
        @Deprecated
        public static final int STATUS_LOCATION_DISABLED = 2;

        /**
         * The system does not support tracking of GNSS Navigation Messages.
         *
         * This status will not change in the future.
         *
         * @deprecated Do not use.
         */
        @Deprecated
        public static final int STATUS_NOT_SUPPORTED = 0;

        /**
         * GNSS Navigation Messages are successfully being tracked, it will receive updates once
         * they are available.
         *
         * @deprecated Do not use.
         */
        @Deprecated
        public static final int STATUS_READY = 1;

        private Callback() {
        }
    }
}
