package android.ranging.oob;

import android.os.Parcelable;

/**
 * Represents the configuration for an Out-of-Band (OOB) initiator in a ranging session.
 * This class includes configuration options such as device handles, security level,
 * ranging mode, and interval range for setting up an OOB initiator ranging session.
 */
public final class OobInitiatorRangingConfig implements Parcelable {
    /**
     * Automatic ranging mode. Allows the system to choose the best mode.
     */
    public static final int RANGING_MODE_AUTO = 0;

    /**
     * Starts ranging with all the ranging technologies both devices support.
     */
    public static final int RANGING_MODE_FUSED = 3;

    /**
     * High accuracy ranging mode. No fallback allowed.
     */
    public static final int RANGING_MODE_HIGH_ACCURACY = 1;

    /**
     * High accuracy ranging mode. Fallback to lower accuracy if high accuracy ranging is not
     * supported by all devices.
     */
    public static final int RANGING_MODE_HIGH_ACCURACY_PREFERRED = 2;

    /**
     * Basic security level for the ranging session.
     * <p>Example usage:
     * UWB: Static-STS
     * BLE-CS: Security level one
     */
    public static final int SECURITY_LEVEL_BASIC = 0;

    /**
     * Basic security level for the ranging session.
     * <p>Example usage:
     * UWB: Provisioned-STS
     * BLE-CS: Security level four
     */
    public static final int SECURITY_LEVEL_SECURE = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private OobInitiatorRangingConfig() {
    }
}
