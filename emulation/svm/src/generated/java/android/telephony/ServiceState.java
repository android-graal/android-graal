package android.telephony;

import android.os.Parcelable;

/**
 * Contains phone state and service related information.
 *
 * The following phone information is included in returned ServiceState:
 *
 * <ul>
 *   <li>Service state: IN_SERVICE, OUT_OF_SERVICE, EMERGENCY_ONLY, POWER_OFF
 *   <li>Duplex mode: UNKNOWN, FDD, TDD
 *   <li>Roaming indicator
 *   <li>Operator name, short name and numeric id
 *   <li>Network selection mode
 * </ul>
 *
 * For historical reasons this class is not declared as final; however,
 * it should be treated as though it were final.
 */
public final class ServiceState implements Parcelable {
    /**
     * Duplex mode for the phone is frequency-division duplexing.
     */
    public static final int DUPLEX_MODE_FDD = 1;

    /**
     * Duplex mode for the phone is time-division duplexing.
     */
    public static final int DUPLEX_MODE_TDD = 2;

    /**
     * Duplex mode for the phone is unknown.
     */
    public static final int DUPLEX_MODE_UNKNOWN = 0;

    /**
     * The phone is registered and locked.  Only emergency numbers are allowed. {@more}
     */
    public static final int STATE_EMERGENCY_ONLY = 2;

    /**
     * Normal operation condition, the phone is registered
     * with an operator either in home network or in roaming.
     */
    public static final int STATE_IN_SERVICE = 0;

    /**
     * Phone is not registered with any operator, the phone
     * can be currently searching a new operator to register to, or not
     * searching to registration at all, or registration is denied, or radio
     * signal is not available.
     */
    public static final int STATE_OUT_OF_SERVICE = 1;

    /**
     * Radio of telephony is explicitly powered off.
     */
    public static final int STATE_POWER_OFF = 3;

    /**
     * Unknown ID. Could be returned by {@link #getCdmaNetworkId()} or {@link #getCdmaSystemId()}
     */
    public static final int UNKNOWN_ID = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private ServiceState() {
    }
}
