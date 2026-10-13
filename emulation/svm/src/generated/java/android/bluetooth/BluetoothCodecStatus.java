package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents the codec status (configuration and capability) for a Bluetooth A2DP source device.
 *
 * @see BluetoothA2dp
 */
public final class BluetoothCodecStatus implements Parcelable {
    /**
     * Extra for the codec configuration intents of the individual profiles.
     *
     * <p>This extra represents the current codec status of the A2DP profile.
     */
    public static final String EXTRA_CODEC_STATUS = "android.bluetooth.extra.CODEC_STATUS";

    private BluetoothCodecStatus() {
    }
}
