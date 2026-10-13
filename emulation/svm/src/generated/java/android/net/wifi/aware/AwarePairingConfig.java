package android.net.wifi.aware;

import android.os.Parcelable;

/**
 * The config for the Aware Pairing. Set to
 * {@link PublishConfig.Builder#setPairingConfig(AwarePairingConfig)} and
 * {@link SubscribeConfig.Builder#setPairingConfig(AwarePairingConfig)}.
 * Only valid when {@link Characteristics#isAwarePairingSupported()} is true.
 */
public final class AwarePairingConfig implements Parcelable {
    /**
     * Aware Pairing bootstrapping method NFC reader
     */
    public static final int PAIRING_BOOTSTRAPPING_NFC_READER = 256;

    /**
     * Aware Pairing bootstrapping method NFC tag
     */
    public static final int PAIRING_BOOTSTRAPPING_NFC_TAG = 16;

    /**
     * Aware Pairing bootstrapping method opportunistic
     */
    public static final int PAIRING_BOOTSTRAPPING_OPPORTUNISTIC = 1;

    /**
     * Aware Pairing bootstrapping method passphrase display
     */
    public static final int PAIRING_BOOTSTRAPPING_PASSPHRASE_DISPLAY = 4;

    /**
     * Aware Pairing bootstrapping method passphrase keypad
     */
    public static final int PAIRING_BOOTSTRAPPING_PASSPHRASE_KEYPAD = 64;

    /**
     * Aware Pairing bootstrapping method pin-code display
     */
    public static final int PAIRING_BOOTSTRAPPING_PIN_CODE_DISPLAY = 2;

    /**
     * Aware Pairing bootstrapping method pin-code keypad
     */
    public static final int PAIRING_BOOTSTRAPPING_PIN_CODE_KEYPAD = 32;

    /**
     * Aware Pairing bootstrapping method QR-code display
     */
    public static final int PAIRING_BOOTSTRAPPING_QR_DISPLAY = 8;

    /**
     * Aware Pairing bootstrapping method QR-code scan
     */
    public static final int PAIRING_BOOTSTRAPPING_QR_SCAN = 128;

    private AwarePairingConfig() {
    }
}
