package android.ranging.uwb;

import android.os.Parcelable;

/**
 * UwbRangingParams encapsulates the parameters required for a UWB ranging session.
 */
public final class UwbRangingParams implements Parcelable {
    /**
     * FiRa-defined multicast {@code STATIC STS DS-TWR} ranging, deferred mode, ranging interval
     * Fast (120ms), Normal (200ms), Infrequent (600ms)
     */
    public static final int CONFIG_MULTICAST_DS_TWR = 2;

    /**
     * Same as {@code CONFIG_UNICAST_DS_TWR}, except P-STS individual controlee key mode is
     * enabled.
     */
    public static final int CONFIG_PROVISIONED_INDIVIDUAL_MULTICAST_DS_TWR = 5;

    /**
     * Same as {@code CONFIG_MULTICAST_DS_TWR}, except P-STS security mode is enabled.
     */
    public static final int CONFIG_PROVISIONED_MULTICAST_DS_TWR = 4;

    /**
     * Same as {@code CONFIG_UNICAST_DS_TWR}, except P-STS security mode is enabled.
     */
    public static final int CONFIG_PROVISIONED_UNICAST_DS_TWR = 3;

    /**
     * Same as {@code CONFIG_ID_3}, except fast ranging interval is 96 milliseconds.
     */
    public static final int CONFIG_PROVISIONED_UNICAST_DS_TWR_VERY_FAST = 6;

    /**
     * FiRa-defined unicast {@code STATIC STS DS-TWR} ranging, deferred mode, ranging interval
     * Fast (120ms), Normal (240ms), Infrequent (600ms)
     */
    public static final int CONFIG_UNICAST_DS_TWR = 1;

    /**
     * 1 millisecond slot duration
     */
    public static final int DURATION_1_MS = 1;

    /**
     * 2 millisecond slot duration
     */
    public static final int DURATION_2_MS = 2;

    /**
     * Sub session id not applicable.
     */
    public static final int SUB_SESSION_UNDEFINED = -1;

    private UwbRangingParams() {
    }
}
