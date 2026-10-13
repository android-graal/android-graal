package android.ranging.uwb;

import android.os.Parcelable;

/**
 * A Class representing the complex channel for UWB which comprises channel and preamble index
 * negotiated between peer devices out of band before ranging.
 *
 * <p> PRF (Pulse Repetition Frequency) supported:
 * <ul>
 *     <li> BPRF - Base Pulse Repetition Frequency.</li>
 *     <li> HPRF - Higher Pulse Repetition Frequency.</li>
 * </ul>
 *
 * See <a href="https://groups.firaconsortium.org/wg/members/document/1949> FiRa UCI Spec.</a>
 */
public final class UwbComplexChannel implements Parcelable {
    /**
     * UWB channel 10
     */
    public static final int UWB_CHANNEL_10 = 10;

    /**
     * UWB channel 12
     */
    public static final int UWB_CHANNEL_12 = 12;

    /**
     * UWB channel 13
     */
    public static final int UWB_CHANNEL_13 = 13;

    /**
     * UWB channel 14
     */
    public static final int UWB_CHANNEL_14 = 14;

    /**
     * UWB channel 5
     */
    public static final int UWB_CHANNEL_5 = 5;

    /**
     * UWB channel 6
     */
    public static final int UWB_CHANNEL_6 = 6;

    /**
     * UWB channel 8
     */
    public static final int UWB_CHANNEL_8 = 8;

    /**
     * UWB channel 9
     */
    public static final int UWB_CHANNEL_9 = 9;

    /**
     * UWB preamble code index 10 (PRF mode - BPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_10 = 10;

    /**
     * UWB preamble code index 11 (PRF mode - BPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_11 = 11;

    /**
     * UWB preamble code index 12 (PRF mode - BPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_12 = 12;

    /**
     * UWB preamble code index 25 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_25 = 25;

    /**
     * UWB preamble code index 26 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_26 = 26;

    /**
     * UWB preamble code index 27 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_27 = 27;

    /**
     * UWB preamble code index 28 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_28 = 28;

    /**
     * UWB preamble code index 29 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_29 = 29;

    /**
     * UWB preamble code index 30 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_30 = 30;

    /**
     * UWB preamble code index 31 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_31 = 31;

    /**
     * UWB preamble code index 32 (PRF mode - HPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_32 = 32;

    /**
     * UWB preamble code index 9 (PRF mode - BPRF).
     */
    public static final int UWB_PREAMBLE_CODE_INDEX_9 = 9;

    private UwbComplexChannel() {
    }
}
