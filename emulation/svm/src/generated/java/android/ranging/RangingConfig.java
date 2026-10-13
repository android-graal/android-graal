package android.ranging;

import android.os.Parcelable;

/**
 * Abstract class to represent type of ranging configuration.
 *
 * <p>Subclasses include:</p>
 * <ul>
 *     <li>{@link RawResponderRangingConfig}</li>
 *     <li>{@link RawInitiatorRangingConfig}</li>
 *     <li>{@link OobResponderRangingConfig}</li>
 *     <li>{@link OobInitiatorRangingConfig}</li>
 * </ul>
 */
public final class RangingConfig implements Parcelable {
    /**
     * Ranging session with the out-of-band negotiations performed by the ranging API.
     */
    public static final int RANGING_SESSION_OOB = 1;

    /**
     * Ranging session with the out-of-band negotiations performed by the app.
     */
    public static final int RANGING_SESSION_RAW = 0;

    private RangingConfig() {
    }
}
