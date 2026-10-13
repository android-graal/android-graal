package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents the codec configuration for a Bluetooth LE Audio source device.
 *
 * <p>Contains the source codec type.
 *
 * <p>The source codec type values are the same as those supported by the device hardware.
 *
 * @see BluetoothLeAudioCodecConfig
 */
public final class BluetoothLeAudioCodecConfig implements Parcelable {
    /**
     * Codec bits per sample 16.
     */
    public static final int BITS_PER_SAMPLE_16 = 1;

    /**
     * Codec bits per sample 24.
     */
    public static final int BITS_PER_SAMPLE_24 = 2;

    /**
     * Codec bits per sample 32.
     */
    public static final int BITS_PER_SAMPLE_32 = 8;

    /**
     * Codec bits per sample 0. Default value of the codec bits per sample.
     */
    public static final int BITS_PER_SAMPLE_NONE = 0;

    /**
     * Codec channel mode MONO.
     */
    public static final int CHANNEL_COUNT_1 = 1;

    /**
     * Codec channel mode STEREO.
     */
    public static final int CHANNEL_COUNT_2 = 2;

    /**
     * Codec channel mode NONE. Default value of the codec channel mode.
     */
    public static final int CHANNEL_COUNT_NONE = 0;

    /**
     * Codec priority default. Default value used for codec priority.
     */
    public static final int CODEC_PRIORITY_DEFAULT = 0;

    /**
     * Codec priority disabled. Used to indicate that this codec is disabled and should not be used.
     */
    public static final int CODEC_PRIORITY_DISABLED = -1;

    /**
     * Codec priority highest. Used to indicate the highest priority a codec can have.
     */
    public static final int CODEC_PRIORITY_HIGHEST = 1000000;

    /**
     * Frame duration 10000 us.
     */
    public static final int FRAME_DURATION_10000 = 2;

    /**
     * Frame duration 7500 us.
     */
    public static final int FRAME_DURATION_7500 = 1;

    /**
     * Frame duration 0. Default value of the frame duration.
     */
    public static final int FRAME_DURATION_NONE = 0;

    /**
     * Codec sample rate 11025 Hz.
     */
    public static final int SAMPLE_RATE_11025 = 2;

    /**
     * Codec sample rate 16000 Hz.
     */
    public static final int SAMPLE_RATE_16000 = 4;

    /**
     * Codec sample rate 176400 Hz.
     */
    public static final int SAMPLE_RATE_176400 = 1024;

    /**
     * Codec sample rate 192000 Hz.
     */
    public static final int SAMPLE_RATE_192000 = 2048;

    /**
     * Codec sample rate 22050 Hz.
     */
    public static final int SAMPLE_RATE_22050 = 8;

    /**
     * Codec sample rate 24000 Hz.
     */
    public static final int SAMPLE_RATE_24000 = 16;

    /**
     * Codec sample rate 32000 Hz.
     */
    public static final int SAMPLE_RATE_32000 = 32;

    /**
     * Codec sample rate 384000 Hz.
     */
    public static final int SAMPLE_RATE_384000 = 4096;

    /**
     * Codec sample rate 44100 Hz.
     */
    public static final int SAMPLE_RATE_44100 = 64;

    /**
     * Codec sample rate 48000 Hz.
     */
    public static final int SAMPLE_RATE_48000 = 128;

    /**
     * Codec sample rate 8000 Hz.
     */
    public static final int SAMPLE_RATE_8000 = 1;

    /**
     * Codec sample rate 88200 Hz.
     */
    public static final int SAMPLE_RATE_88200 = 256;

    /**
     * Codec sample rate 96000 Hz.
     */
    public static final int SAMPLE_RATE_96000 = 512;

    /**
     * Codec sample rate 0 Hz. Default value used for codec sample rate. Values are the bit mask as
     * defined in the Bluetooth Assigned Numbers, Generic Audio, Supported_Sampling_Frequencies
     * table.
     */
    public static final int SAMPLE_RATE_NONE = 0;

    public static final int SOURCE_CODEC_TYPE_INVALID = 1000000;

    public static final int SOURCE_CODEC_TYPE_LC3 = 0;

    public static final int SOURCE_CODEC_TYPE_OPUS = 1;

    private BluetoothLeAudioCodecConfig() {
    }
}
