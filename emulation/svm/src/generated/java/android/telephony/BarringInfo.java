package android.telephony;

import android.os.Parcelable;

/**
 * Provides the barring configuration for a particular service type.
 *
 * Provides indication about the barring of a particular service for use. Certain barring types
 * are only valid for certain technology families. Any service that does not have a barring
 * configuration is unbarred by default.
 */
public final class BarringInfo implements Parcelable {
    /**
     * Barring indicator for circuit-switched fallback for voice; applicable to EUTRAN and NGRAN
     */
    public static final int BARRING_SERVICE_TYPE_CS_FALLBACK = 5;

    /**
     * Barring indicator for circuit-switched service; applicable to UTRAN
     */
    public static final int BARRING_SERVICE_TYPE_CS_SERVICE = 0;

    /**
     * Barring indicator for circuit-switched voice service; applicable to UTRAN
     */
    public static final int BARRING_SERVICE_TYPE_CS_VOICE = 2;

    /**
     * Barring indicator for emergency services; applicable to UTRAN, EUTRAN, and NGRAN
     */
    public static final int BARRING_SERVICE_TYPE_EMERGENCY = 8;

    /**
     * Barring indicator for MMTEL (IMS) video; applicable to EUTRAN and NGRAN
     */
    public static final int BARRING_SERVICE_TYPE_MMTEL_VIDEO = 7;

    /**
     * Barring indicator for MMTEL (IMS) voice; applicable to EUTRAN and NGRAN
     */
    public static final int BARRING_SERVICE_TYPE_MMTEL_VOICE = 6;

    /**
     * Barring indicator for mobile-originated data traffic; applicable to EUTRAN and NGRAN
     */
    public static final int BARRING_SERVICE_TYPE_MO_DATA = 4;

    /**
     * Barring indicator for mobile-originated signalling; applicable to EUTRAN and NGRAN
     */
    public static final int BARRING_SERVICE_TYPE_MO_SIGNALLING = 3;

    /**
     * Barring indicator for packet-switched service; applicable to UTRAN
     */
    public static final int BARRING_SERVICE_TYPE_PS_SERVICE = 1;

    /**
     * Barring indicator for SMS sending; applicable to UTRAN, EUTRAN, and NGRAN
     */
    public static final int BARRING_SERVICE_TYPE_SMS = 9;

    private BarringInfo() {
    }

    /**
     * Describe the current barring configuration of a cell
     */
    public static final class BarringServiceInfo implements Parcelable {
        /**
         * The service may be barred based on additional factors
         */
        public static final int BARRING_TYPE_CONDITIONAL = 1;

        /**
         * Barring is inactive
         */
        public static final int BARRING_TYPE_NONE = 0;

        /**
         * The service is barred
         */
        public static final int BARRING_TYPE_UNCONDITIONAL = 2;

        /**
         * If a modem does not report barring info, then the barring type will be UNKNOWN
         */
        public static final int BARRING_TYPE_UNKNOWN = -1;

        private BarringServiceInfo() {
        }
    }
}
