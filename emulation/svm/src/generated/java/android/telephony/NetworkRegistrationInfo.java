package android.telephony;

import android.os.Parcelable;

/**
 * Description of a mobile network registration info
 */
public final class NetworkRegistrationInfo implements Parcelable {
    /**
     * Circuit switched domain
     */
    public static final int DOMAIN_CS = 1;

    /**
     * Applicable to both CS and PS Domain
     */
    public static final int DOMAIN_CS_PS = 3;

    /**
     * Packet switched domain
     */
    public static final int DOMAIN_PS = 2;

    /**
     * Unknown / Unspecified domain
     */
    public static final int DOMAIN_UNKNOWN = 0;

    /**
     * The device is camped on an LTE cell that supports E-UTRA-NR Dual Connectivity(EN-DC) and
     * also connected to at least one 5G cell as a secondary serving cell.
     */
    public static final int NR_STATE_CONNECTED = 3;

    /**
     * The device isn't camped on an LTE cell or the LTE cell doesn't support E-UTRA-NR
     * Dual Connectivity(EN-DC).
     */
    public static final int NR_STATE_NONE = 0;

    /**
     * The device is camped on an LTE cell that supports E-UTRA-NR Dual Connectivity(EN-DC) and both
     * the use of dual connectivity with NR(DCNR) is not restricted and NR is supported by the
     * selected PLMN.
     */
    public static final int NR_STATE_NOT_RESTRICTED = 2;

    /**
     * The device is camped on an LTE cell that supports E-UTRA-NR Dual Connectivity(EN-DC) but
     * either the use of dual connectivity with NR(DCNR) is restricted or NR is not supported by
     * the selected PLMN.
     */
    public static final int NR_STATE_RESTRICTED = 1;

    /**
     * Data service
     */
    public static final int SERVICE_TYPE_DATA = 2;

    /**
     * Emergency service
     */
    public static final int SERVICE_TYPE_EMERGENCY = 5;

    /**
     * MMS service
     */
    public static final int SERVICE_TYPE_MMS = 6;

    /**
     * SMS service
     */
    public static final int SERVICE_TYPE_SMS = 3;

    /**
     * Unknown service
     */
    public static final int SERVICE_TYPE_UNKNOWN = 0;

    /**
     * Video service
     */
    public static final int SERVICE_TYPE_VIDEO = 4;

    /**
     * Voice service
     */
    public static final int SERVICE_TYPE_VOICE = 1;

    private NetworkRegistrationInfo() {
    }
}
