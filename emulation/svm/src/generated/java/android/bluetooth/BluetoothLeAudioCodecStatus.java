package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents the codec status (configuration and capability) for a Bluetooth Le Audio source
 * device.
 *
 * @see BluetoothLeAudio
 */
public final class BluetoothLeAudioCodecStatus implements Parcelable {
    /**
     * Extra for the codec configuration intents of the individual profiles.
     *
     * <p>This extra represents the current codec status of the Le Audio profile.
     */
    public static final String EXTRA_LE_AUDIO_CODEC_STATUS = "android.bluetooth.extra.LE_AUDIO_CODEC_STATUS";

    private BluetoothLeAudioCodecStatus() {
    }
}
