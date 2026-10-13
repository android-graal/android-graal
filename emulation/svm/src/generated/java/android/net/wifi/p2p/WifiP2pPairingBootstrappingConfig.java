package android.net.wifi.p2p;

import android.os.Parcelable;

/**
 * A class representing Wi-Fi Direct pairing bootstrapping configuration.
 *
 * @see android.net.wifi.p2p.WifiP2pConfig
 */
public final class WifiP2pPairingBootstrappingConfig implements Parcelable {
    /**
     * Pairing bootstrapping method display passphrase - The passphrase is displayed on the
     * connection initiating device. The user enters the displayed passphrase on the other device.
     */
    public static final int PAIRING_BOOTSTRAPPING_METHOD_DISPLAY_PASSPHRASE = 4;

    /**
     * Pairing bootstrapping method display pin-code - The pin-code is displayed on the connection
     * initiating device. The user enters the displayed pin-code on the other device.
     */
    public static final int PAIRING_BOOTSTRAPPING_METHOD_DISPLAY_PINCODE = 2;

    /**
     * Pairing bootstrapping method keypad passphrase - The passphrase is displayed on the other
     * device. The user enters the displayed passphrase on the connection initiating device.
     */
    public static final int PAIRING_BOOTSTRAPPING_METHOD_KEYPAD_PASSPHRASE = 16;

    /**
     * Pairing bootstrapping method keypad pin-code - The pin-code is displayed on the other
     * device. The user enters the displayed pin-code on the connection initiating device.
     */
    public static final int PAIRING_BOOTSTRAPPING_METHOD_KEYPAD_PINCODE = 8;

    /**
     * Pairing bootstrapping method opportunistic
     */
    public static final int PAIRING_BOOTSTRAPPING_METHOD_OPPORTUNISTIC = 1;

    /**
     * Pairing bootstrapping done out of band (For example: Over Bluetooth LE.
     * Refer Wi-Fi Alliance Wi-Fi Direct R2 specification Section 3.9 for the details).
     */
    public static final int PAIRING_BOOTSTRAPPING_METHOD_OUT_OF_BAND = 32;

    private WifiP2pPairingBootstrappingConfig() {
    }
}
