package android.location;

import android.os.Parcelable;

/**
 * A class representing a GNSS satellite measurement, containing raw and computed information.
 */
public final class GnssMeasurement implements Parcelable {
    /**
     * The state of the {@link #getAccumulatedDeltaRangeMeters()} has a cycle slip detected.
     */
    public static final int ADR_STATE_CYCLE_SLIP = 4;

    /**
     * Reports whether the flag {@link #ADR_STATE_HALF_CYCLE_RESOLVED} has been reported by the
     * GNSS hardware.
     *
     * <p> When this bit is set, the value of {@link #getAccumulatedDeltaRangeUncertaintyMeters()}
     * can be low (centimeter level) whether or not the half cycle ambiguity is resolved.
     *
     * <p> When this bit is unset, the value of {@link #getAccumulatedDeltaRangeUncertaintyMeters()}
     * is larger, to cover the potential error due to half cycle ambiguity being unresolved.
     */
    public static final int ADR_STATE_HALF_CYCLE_REPORTED = 16;

    /**
     * Reports whether the value {@link #getAccumulatedDeltaRangeMeters()} has resolved the half
     * cycle ambiguity.
     *
     * <p> When this bit is set, the {@link #getAccumulatedDeltaRangeMeters()} corresponds to the
     * carrier phase measurement plus an accumulated integer number of carrier full cycles.
     *
     * <p> When this bit is unset, the {@link #getAccumulatedDeltaRangeMeters()} corresponds to the
     * carrier phase measurement plus an accumulated integer number of carrier half cycles.
     *
     * <p> For signals that have databits, the carrier phase tracking loops typically use a costas
     * loop discriminator.  This type of tracking loop introduces a half-cycle ambiguity that is
     * resolved by searching through the received data for known patterns of databits (e.g. GPS uses
     * the TLM word) which then determines the polarity of the incoming data and resolves the
     * half-cycle ambiguity.
     *
     * <p>Before the half-cycle ambiguity has been resolved it is possible that the ADR_STATE_VALID
     * flag is set:
     *
     * <ul>
     *   <li> In cases where ADR_STATE_HALF_CYCLE_REPORTED is not set, the
     *   ADR_STATE_HALF_CYCLE_RESOLVED flag will not be available. Here, a half wave length will be
     *   added to the returned accumulated delta range uncertainty to indicate the half cycle
     *   ambiguity.
     *   <li> In cases where ADR_STATE_HALF_CYCLE_REPORTED is set, half cycle ambiguity will be
     *   indicated via both the ADR_STATE_HALF_CYCLE_RESOLVED flag and as well a half wave length
     *   added to the returned accumulated delta range uncertainty.
     * </ul>
     */
    public static final int ADR_STATE_HALF_CYCLE_RESOLVED = 8;

    /**
     * The state of the {@link #getAccumulatedDeltaRangeMeters()} has detected a reset.
     */
    public static final int ADR_STATE_RESET = 2;

    /**
     * The state of the value {@link #getAccumulatedDeltaRangeMeters()} is invalid or unknown.
     */
    public static final int ADR_STATE_UNKNOWN = 0;

    /**
     * The state of the {@link #getAccumulatedDeltaRangeMeters()} is valid.
     */
    public static final int ADR_STATE_VALID = 1;

    /**
     * The measurement shows signs of multi-path.
     */
    public static final int MULTIPATH_INDICATOR_DETECTED = 1;

    /**
     * The measurement shows no signs of multi-path.
     */
    public static final int MULTIPATH_INDICATOR_NOT_DETECTED = 2;

    /**
     * The indicator is not available or the presence or absence of multipath is unknown.
     */
    public static final int MULTIPATH_INDICATOR_UNKNOWN = 0;

    /**
     * This GNSS measurement's tracking state has secondary code lock.
     */
    public static final int STATE_2ND_CODE_LOCK = 65536;

    /**
     * This Beidou measurement's tracking state has D2 bit sync.
     */
    public static final int STATE_BDS_D2_BIT_SYNC = 256;

    /**
     * This Beidou measurement's tracking state has D2 sub-frame sync.
     */
    public static final int STATE_BDS_D2_SUBFRAME_SYNC = 512;

    /**
     * This GNSS measurement's tracking state has bit sync.
     */
    public static final int STATE_BIT_SYNC = 2;

    /**
     * This GNSS measurement's tracking state has code lock.
     */
    public static final int STATE_CODE_LOCK = 1;

    /**
     * This Galileo measurement's tracking state has E1B/C code lock.
     */
    public static final int STATE_GAL_E1BC_CODE_LOCK = 1024;

    /**
     * This Galileo measurement's tracking state has E1B page sync.
     */
    public static final int STATE_GAL_E1B_PAGE_SYNC = 4096;

    /**
     * This Galileo measurement's tracking state has E1C secondary code lock.
     */
    public static final int STATE_GAL_E1C_2ND_CODE_LOCK = 2048;

    /**
     * This Glonass measurement's tracking state has string sync.
     */
    public static final int STATE_GLO_STRING_SYNC = 64;

    /**
     * This Glonass measurement's tracking state has time-of-day decoded.
     */
    public static final int STATE_GLO_TOD_DECODED = 128;

    /**
     * This Glonass measurement's tracking state has time-of-day known, possibly not decoded
     * over the air but has been determined from other sources. If TOD decoded is set then TOD Known
     * will also be set.
     */
    public static final int STATE_GLO_TOD_KNOWN = 32768;

    /**
     * This GNSS measurement's tracking state contains millisecond ambiguity.
     */
    public static final int STATE_MSEC_AMBIGUOUS = 16;

    /**
     * This SBAS measurement's tracking state has whole second level sync.
     */
    public static final int STATE_SBAS_SYNC = 8192;

    /**
     * This GNSS measurement's tracking state has sub-frame sync.
     */
    public static final int STATE_SUBFRAME_SYNC = 4;

    /**
     * This GNSS measurement's tracking state has symbol sync.
     */
    public static final int STATE_SYMBOL_SYNC = 32;

    /**
     * This GNSS measurement's tracking state has time-of-week decoded.
     */
    public static final int STATE_TOW_DECODED = 8;

    /**
     * This GNSS measurement's tracking state has time-of-week known, possibly not decoded
     * over the air but has been determined from other sources. If TOW decoded is set then TOW Known
     * will also be set.
     */
    public static final int STATE_TOW_KNOWN = 16384;

    /**
     * This GNSS measurement's tracking state is invalid or unknown.
     */
    public static final int STATE_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private GnssMeasurement() {
    }
}
