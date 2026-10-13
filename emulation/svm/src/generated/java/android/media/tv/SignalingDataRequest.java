package android.media.tv;

import android.os.Parcelable;

/**
 * Request to retrieve the Low-level Signalling Tables (LLS) and Service-layer Signalling (SLS)
 * metadata.
 *
 * <p> For more details on each type of metadata that can be requested, refer to the ATSC standard
 * A/344:2023-5 9.2.10 - Query Signaling Data API.
 *
 * @see SignalingDataResponse
 */
public final class SignalingDataRequest implements Parcelable {
    /**
     * Denotes that theres no group associated with this request.
     */
    public static final int SIGNALING_DATA_NO_GROUP_ID = -1;

    /**
     * Advance Emergency Information Table for the requested service(s), LLS_table_id = 4.
     */
    public static final String SIGNALING_METADATA_AEAT = "AEAT";

    /**
     * SLS Metadata: MMT Application Event Information for the requested service(s)
     */
    public static final String SIGNALING_METADATA_AEI = "AEI";

    /**
     * SLS Metadata: APD for the requested service(s)
     */
    public static final String SIGNALING_METADATA_APD = "APD";

    /**
     * SLS Metadata: ATSC Staggercast Descriptor
     */
    public static final String SIGNALING_METADATA_ASD = "ASD";

    /**
     * SLS Metadata: Audio Stream Properties Descriptor
     */
    public static final String SIGNALING_METADATA_ASPD = "ASPD";

    /**
     * SLS Metadata: Caption Asset Descriptor
     */
    public static final String SIGNALING_METADATA_CAD = "CAD";

    /**
     * CertificateData Tablefor the requested service(s), LLS_table_id = 6.
     */
    public static final String SIGNALING_METADATA_CDT = "CDT";

    /**
     * SLS Metadata: MMT Clock Relation Information for the requested service(s)
     */
    public static final String SIGNALING_METADATA_CRIT = "CRIT";

    /**
     * SLS Metadata: MMT Device Capabilities Information Table for the requested service(s)
     */
    public static final String SIGNALING_METADATA_DCIT = "DCIT";

    /**
     * SLS Metadata: Distribution Window Desciription for the requested service(s)
     */
    public static final String SIGNALING_METADATA_DWD = "DWD";

    /**
     * SLS Metadata: ROUTE/DASH Application Dynamic Event for the requested service(s)
     */
    public static final String SIGNALING_METADATA_EMSG = "EMSG";

    /**
     * SLS Metadata: MMT Application Dynamic Event for the requested service(s)
     */
    public static final String SIGNALING_METADATA_EVTI = "EVTI";

    /**
     * SLS Metadata: HTML Entry Pages Location Description for the requested service(s)
     */
    public static final String SIGNALING_METADATA_HELD = "HELD";

    /**
     * SLS Metadata: Inband Event Descriptor
     */
    public static final String SIGNALING_METADATA_IED = "IED";

    /**
     * SLS Metadata: DASH MPD for the requested service(s)
     */
    public static final String SIGNALING_METADATA_MPD = "MPD";

    /**
     * SLS Metadata: MMT Media Presentation Information Table for the requested service(s)
     */
    public static final String SIGNALING_METADATA_MPIT = "MPIT";

    /**
     * SLS Metadata: MMT Package Table for the requested service(s)
     */
    public static final String SIGNALING_METADATA_MPT = "MPT";

    /**
     * Onscreen Message Notifications for the requested service(s), LLS_table_id = 5.
     */
    public static final String SIGNALING_METADATA_OSN = "OSN";

    /**
     * SLS Metadata: MMT Package Access Table for the requested service(s)
     */
    public static final String SIGNALING_METADATA_PAT = "PAT";

    /**
     * SLS Metadata: Recovery Data Table for the requested service(s)
     */
    public static final String SIGNALING_METADATA_RDT = "RDT";

    /**
     * Region Rating Table for the requested service(s), LLS_table_id = 2.
     */
    public static final String SIGNALING_METADATA_RRT = "RRT";

    /**
     * SLS Metadata: Regional Service Availability Table for the requested service(s)
     */
    public static final String SIGNALING_METADATA_RSAT = "RSAT";

    /**
     * Service List Table for the requested service(s), LLS_table_id = 1.
     */
    public static final String SIGNALING_METADATA_SLT = "SLT";

    /**
     * Signed Multitable for the requested service(s), LLS_table_id = 0xFE (254).
     */
    public static final String SIGNALING_METADATA_SMT = "SMT";

    /**
     * SLS Metadata: Security Properties Descriptor
     */
    public static final String SIGNALING_METADATA_SSD = "SSD";

    /**
     * SLS Metadata: S-TSID for the requested service(s)
     */
    public static final String SIGNALING_METADATA_STSID = "STSID";

    /**
     * System Time Table for the requested service(s), LLS_table_id = 3.
     */
    public static final String SIGNALING_METADATA_STT = "STT";

    /**
     * SLS Metadata: USBD for the requested service(s)
     */
    public static final String SIGNALING_METADATA_USBD = "USBD";

    /**
     * SLS Metadata: User Service Description for MMTP
     */
    public static final String SIGNALING_METADATA_USD = "USD";

    /**
     * SLS Metadata: Video Stream Properties Descriptor
     */
    public static final String SIGNALING_METADATA_VSPD = "VSPD";

    private SignalingDataRequest() {
    }
}
