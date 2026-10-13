package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents a supported source codec type for a Bluetooth A2DP device. See {@link
 * BluetoothA2dp#getSupportedCodecTypes}. The codec type is uniquely identified by its name and
 * codec identifier.
 */
public final class BluetoothCodecType implements Parcelable {
    /**
     * AAC codec identifier. See {@link BluetoothCodecType#getCodecId}.
     */
    public static final long CODEC_ID_AAC = 2L;

    /**
     * AptX codec identifier. See {@link BluetoothCodecType#getCodecId}.
     */
    public static final long CODEC_ID_APTX = 16797695L;

    /**
     * Aptx HD codec identifier. See {@link BluetoothCodecType#getCodecId}.
     */
    public static final long CODEC_ID_APTX_HD = 604035071L;

    /**
     * LDAC codec identifier. See {@link BluetoothCodecType#getCodecId}.
     */
    public static final long CODEC_ID_LDAC = -1442763265L;

    /**
     * Opus codec identifier. See {@link BluetoothCodecType#getCodecId}.
     */
    public static final long CODEC_ID_OPUS = 16834815L;

    /**
     * SBC codec identifier. See {@link BluetoothCodecType#getCodecId}.
     */
    public static final long CODEC_ID_SBC = 0L;

    private BluetoothCodecType() {
    }
}
