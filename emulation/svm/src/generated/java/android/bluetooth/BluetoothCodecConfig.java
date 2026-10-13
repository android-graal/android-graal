package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents the codec configuration for a Bluetooth A2DP source device.
 *
 * <p>Contains the source codec type, the codec priority, the codec sample rate, the codec bits per
 * sample, and the codec channel mode.
 *
 * <p>The source codec type values are the same as those supported by the device hardware.
 *
 * @see BluetoothA2dp
 */
public final class BluetoothCodecConfig implements Parcelable {
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
    public static final int BITS_PER_SAMPLE_32 = 4;

    /**
     * Codec bits per sample 0. Default value of the codec bits per sample.
     */
    public static final int BITS_PER_SAMPLE_NONE = 0;

    /**
     * Codec channel mode MONO.
     */
    public static final int CHANNEL_MODE_MONO = 1;

    /**
     * Codec channel mode NONE. Default value of the codec channel mode.
     */
    public static final int CHANNEL_MODE_NONE = 0;

    /**
     * Codec channel mode STEREO.
     */
    public static final int CHANNEL_MODE_STEREO = 2;

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
     * Codec sample rate 176400 Hz.
     */
    public static final int SAMPLE_RATE_176400 = 16;

    /**
     * Codec sample rate 192000 Hz.
     */
    public static final int SAMPLE_RATE_192000 = 32;

    /**
     * Codec sample rate 44100 Hz.
     */
    public static final int SAMPLE_RATE_44100 = 1;

    /**
     * Codec sample rate 48000 Hz.
     */
    public static final int SAMPLE_RATE_48000 = 2;

    /**
     * Codec sample rate 88200 Hz.
     */
    public static final int SAMPLE_RATE_88200 = 4;

    /**
     * Codec sample rate 96000 Hz.
     */
    public static final int SAMPLE_RATE_96000 = 8;

    /**
     * Codec sample rate 0 Hz. Default value used for codec sample rate.
     */
    public static final int SAMPLE_RATE_NONE = 0;

    /**
     * Source codec type AAC.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_AAC = 1;

    /**
     * Source codec type APTX.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_APTX = 2;

    /**
     * Source codec type APTX HD.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_APTX_HD = 3;

    /**
     * Source codec type invalid. This is the default value used for codec type.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_INVALID = 1000000;

    /**
     * Source codec type LC3.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_LC3 = 5;

    /**
     * Source codec type LDAC.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_LDAC = 4;

    /**
     * Source codec type Opus.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_OPUS = 6;

    /**
     * Source codec type SBC. This is the mandatory source codec type.
     *
     * @deprecated Use the {@link BluetoothCodecType} values returned by {@link
     *     BluetoothA2dp#getSupportedCodecTypes} instead.
     */
    @Deprecated
    public static final int SOURCE_CODEC_TYPE_SBC = 0;

    private BluetoothCodecConfig() {
    }
}
