package android.net.wifi.p2p;

import android.os.Parcelable;

/**
 * A class representing Wifi Display information for a device.
 *
 * See Wifi Display technical specification v1.0.0, section 5.1.2.
 * See Wifi Display technical specification v2.0.0, section 5.1.12 for Wifi Display R2.
 */
public final class WifiP2pWfdInfo implements Parcelable {
    /**
     * Bit field for {@link #getDeviceInfo()}, indicate audo is only supported at source.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_AUDIO_ONLY_SUPPORT_AT_SOURCE = 2048;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicate audio is not supported at primary sink.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_AUDIO_UNSUPPORTED_AT_PRIMARY_SINK = 1024;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicate the support of Content Protection
     * using the HDCP system 2.0/2.1.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_CONTENT_PROTECTION_SUPPORT = 256;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicates that coupled sink is supporeted at sink.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_COUPLED_SINK_SUPPORT_AT_SINK = 8;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicates that coupled sink is supported at source.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_COUPLED_SINK_SUPPORT_AT_SOURCE = 4;

    /**
     * {@link #getDeviceInfo()} & {@link #DEVICE_INFO_DEVICE_TYPE_MASK} is one of
     * {@link #DEVICE_TYPE_WFD_SOURCE}, {@link #DEVICE_TYPE_PRIMARY_SINK},
     * {@link #DEVICE_TYPE_SECONDARY_SINK} or {@link #DEVICE_TYPE_SOURCE_OR_PRIMARY_SINK}.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement and
     * 5.1.12 WFD R2 Device Information Subelement in Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_DEVICE_TYPE_MASK = 3;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicate the preferred connectifity for Wifi Display.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     * The value is one of {@link #PREFERRED_CONNECTIVITY_P2P} or
     * {@link #PREFERRED_CONNECTIVITY_TDLS}.
     */
    public static final int DEVICE_INFO_PREFERRED_CONNECTIVITY_MASK = 128;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicates that Wifi Display session is available.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_SESSION_AVAILABLE_MASK = 48;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicate that TDLS persistent group is intended.
     *
     *  The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     *  Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_TDLS_PERSISTENT_GROUP = 4096;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicate that the request is for
     *  re-invocation of TDLS persistent group.
     *
     *  The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     *  Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_TDLS_PERSISTENT_GROUP_REINVOKE = 8192;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicate time synchronization
     * using 802.1AS is supported.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_TIME_SYNCHRONIZATION_SUPPORT = 512;

    /**
     * Bit field for {@link #getDeviceInfo()}, indicates that Wifi Display discovery is supported.
     *
     * The bit definition is listed in 5.1.2 WFD Device Information Subelement in
     * Wifi Display Technical Specification.
     */
    public static final int DEVICE_INFO_WFD_SERVICE_DISCOVERY_SUPPORT = 64;

    /**
     * The device is a primary sink.
     */
    public static final int DEVICE_TYPE_PRIMARY_SINK = 1;

    /**
     * The device is a secondary sink. This type is only supported by R1.
     */
    public static final int DEVICE_TYPE_SECONDARY_SINK = 2;

    /**
     * The device is dual-role capable i.e. either a WFD source or a primary sink.
     */
    public static final int DEVICE_TYPE_SOURCE_OR_PRIMARY_SINK = 3;

    /**
     * The device is a Wifi Display Source.
     */
    public static final int DEVICE_TYPE_WFD_SOURCE = 0;

    /**
     * Wifi Display (WFD) preferred connectivity is Wifi Direct (P2P).
     */
    public static final int PREFERRED_CONNECTIVITY_P2P = 0;

    /**
     * Wifi Display (WFD) preferred connectivity is TDLS.
     */
    public static final int PREFERRED_CONNECTIVITY_TDLS = 1;

    private WifiP2pWfdInfo() {
    }
}
