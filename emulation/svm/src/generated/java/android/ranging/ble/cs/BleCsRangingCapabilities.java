package android.ranging.ble.cs;

import android.os.Parcelable;

/**
 * Represents the capabilities of the Bluetooth-based Channel Sounding (CS) ranging.
 */
public final class BleCsRangingCapabilities implements Parcelable {
    /**
     * Security Level 4:
     * 10 ns CS RTT accuracy and CS tones with the addition of CS RTT sounding sequence or random
     * sequence payloads, and support of the Normalized Attack Detector Metric requirements.
     */
    public static final int CS_SECURITY_LEVEL_FOUR = 4;

    /**
     * Security Level 1:
     * Either CS tone or CS RTT..
     */
    public static final int CS_SECURITY_LEVEL_ONE = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private BleCsRangingCapabilities() {
    }
}
