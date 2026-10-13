package android.ranging;

import android.os.Parcelable;

/**
 * Represents the configuration preferences for a ranging session.
 *
 * <p>The {@code RangingPreference} class allows users to specify various parameters
 * required for a ranging session, including ranging parameters, sensor fusion settings,
 * and data notification configurations. It provides a {@link Builder} to construct
 * an instance with custom configurations.</p>
 */
public final class RangingPreference implements Parcelable {
    /**
     * The device that initiates the session.
     */
    public static final int DEVICE_ROLE_INITIATOR = 1;

    /**
     * The device that responds to a session.
     */
    public static final int DEVICE_ROLE_RESPONDER = 0;

    private RangingPreference() {
    }
}
