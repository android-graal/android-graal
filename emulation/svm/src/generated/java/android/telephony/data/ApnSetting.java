package android.telephony.data;

import android.os.Parcelable;

/**
 * An Access Point Name (APN) configuration for a carrier data connection.
 *
 * <p>The APN provides configuration to connect a cellular network device to an IP data network. A
 * carrier uses the name, type and other configuration in an {@code APNSetting} to decide which IP
 * address to assign, any security methods to apply, and how the device might be connected to
 * private networks.
 *
 * <p>Use {@link ApnSetting.Builder} to create new instances.
 */
public final class ApnSetting implements Parcelable {
    /**
     * Authentication type for CHAP.
     */
    public static final int AUTH_TYPE_CHAP = 2;

    /**
     * Authentication is not required.
     */
    public static final int AUTH_TYPE_NONE = 0;

    /**
     * Authentication type for PAP.
     */
    public static final int AUTH_TYPE_PAP = 1;

    /**
     * Authentication type for PAP or CHAP.
     */
    public static final int AUTH_TYPE_PAP_OR_CHAP = 3;

    /**
     * MVNO type for group identifier level 1.
     */
    public static final int MVNO_TYPE_GID = 2;

    /**
     * MVNO type for ICCID.
     */
    public static final int MVNO_TYPE_ICCID = 3;

    /**
     * MVNO type for IMSI.
     */
    public static final int MVNO_TYPE_IMSI = 1;

    /**
     * MVNO type for service provider name.
     */
    public static final int MVNO_TYPE_SPN = 0;

    /**
     * Internet protocol.
     */
    public static final int PROTOCOL_IP = 0;

    /**
     * Virtual PDP type introduced to handle dual IP stack UE capability.
     */
    public static final int PROTOCOL_IPV4V6 = 2;

    /**
     * Internet protocol, version 6.
     */
    public static final int PROTOCOL_IPV6 = 1;

    /**
     * Transfer of Non-IP data to external packet data network.
     */
    public static final int PROTOCOL_NON_IP = 4;

    /**
     * Point to point protocol.
     */
    public static final int PROTOCOL_PPP = 3;

    /**
     * Transfer of Unstructured data to the Data Network via N6.
     */
    public static final int PROTOCOL_UNSTRUCTURED = 5;

    /**
     * APN type for BIP.
     */
    public static final int TYPE_BIP = 8192;

    /**
     * APN type for CBS.
     */
    public static final int TYPE_CBS = 128;

    /**
     * APN type for default data traffic.
     */
    public static final int TYPE_DEFAULT = 17;

    /**
     * APN type for DUN traffic.
     */
    public static final int TYPE_DUN = 8;

    /**
     * APN type for Emergency PDN. This is not an IA apn, but is used
     * for access to carrier services in an emergency call situation.
     */
    public static final int TYPE_EMERGENCY = 512;

    /**
     * APN type for ENTERPRISE.
     */
    public static final int TYPE_ENTERPRISE = 16384;

    /**
     * APN type for accessing the carrier's FOTA portal, used for over the air updates.
     */
    public static final int TYPE_FOTA = 32;

    /**
     * APN type for HiPri traffic.
     */
    public static final int TYPE_HIPRI = 16;

    /**
     * APN type for IA Initial Attach APN.
     */
    public static final int TYPE_IA = 256;

    /**
     * APN type for IMS.
     */
    public static final int TYPE_IMS = 64;

    /**
     * APN type for MCX (Mission Critical Service) where X can be PTT/Video/Data
     */
    public static final int TYPE_MCX = 1024;

    /**
     * APN type for MMS traffic.
     */
    public static final int TYPE_MMS = 2;

    /**
     * APN type for OEM_PAID networks (Automotive PANS)
     */
    public static final int TYPE_OEM_PAID = 65536;

    /**
     * APN type for OEM_PRIVATE networks (Automotive PANS)
     */
    public static final int TYPE_OEM_PRIVATE = 131072;

    /**
     * APN type for RCS (Rich Communication Services).
     */
    public static final int TYPE_RCS = 32768;

    /**
     * APN type for SUPL assisted GPS.
     */
    public static final int TYPE_SUPL = 4;

    /**
     * APN type for VSIM.
     */
    public static final int TYPE_VSIM = 4096;

    /**
     * APN type for XCAP.
     */
    public static final int TYPE_XCAP = 2048;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ApnSetting() {
    }
}
