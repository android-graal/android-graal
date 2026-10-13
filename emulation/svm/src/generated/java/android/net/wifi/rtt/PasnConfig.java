package android.net.wifi.rtt;

import android.os.Parcelable;

/**
 * Pre-association security negotiation (PASN) configuration.
 * <p>
 * PASN configuration in IEEE 802.11az focuses on securing the ranging process before a device
 * fully associates with a Wi-Fi network. IEEE 802.11az supports various based AKMs as in
 * {@code AKM_*} for PASN and cipher as in {@code CIPHER_*}. Password is also another input to
 * some base AKMs.
 * <p>
 * Once PASN is initiated, the AP and the client device exchange messages to authenticate each
 * other and establish security keys. This process ensures that only authorized devices can
 * participate in ranging.
 * <p>
 * After successful PASN authentication, ranging operations are performed using the established
 * secure channel. This protects the ranging measurements from eavesdropping and tampering.
 * <p>
 * The keys derived during the PASN process are used to protect the LTFs exchanged during ranging.
 * This ensures that the LTFs are encrypted and authenticated, preventing unauthorized access
 * and manipulation.
 */
public final class PasnConfig implements Parcelable {
    /**
     * Fast Initial Link Setup (FILS) with Extensible Authentication Protocol (EAP) and SHA-256.
     */
    public static final int AKM_FILS_EAP_SHA256 = 64;

    /**
     * Fast Initial Link Setup (FILS) with Extensible Authentication Protocol (EAP) and SHA-384.
     */
    public static final int AKM_FILS_EAP_SHA384 = 128;

    /**
     * Fast BSS Transition (FT) with Extensible Authentication Protocol (EAP) and SHA-256.
     */
    public static final int AKM_FT_EAP_SHA256 = 4;

    /**
     * Fast BSS Transition (FT) with Extensible Authentication Protocol (EAP) and SHA-384.
     */
    public static final int AKM_FT_EAP_SHA384 = 16;

    /**
     * Fast BSS Transition (FT) with Pre-Shared Key (PSK) and SHA-256.
     */
    public static final int AKM_FT_PSK_SHA256 = 8;

    /**
     * Fast BSS Transition (FT) with Pre-Shared Key (PSK) and SHA-384.
     */
    public static final int AKM_FT_PSK_SHA384 = 32;

    /**
     *  No authentication and key management.
     */
    public static final int AKM_NONE = 0;

    /**
     * Pre-association security negotiation (PASN).
     */
    public static final int AKM_PASN = 1;

    /**
     * Simultaneous authentication of equals (SAE).
     */
    public static final int AKM_SAE = 2;

    /**
     * Counter Mode with Cipher Block Chaining Message Authentication Code Protocol (CCMP) with
     * 128-bit key.
     */
    public static final int CIPHER_CCMP_128 = 1;

    /**
     * Counter Mode with Cipher Block Chaining Message Authentication Code Protocol (CCMP) with
     * 256-bit key.
     */
    public static final int CIPHER_CCMP_256 = 2;

    /**
     * Galois/Counter Mode Protocol (GCMP) with 128-bit key.
     */
    public static final int CIPHER_GCMP_128 = 4;

    /**
     * Galois/Counter Mode Protocol (GCMP) with 256-bit key.
     */
    public static final int CIPHER_GCMP_256 = 8;

    /**
     * No encryption.
     */
    public static final int CIPHER_NONE = 0;

    private PasnConfig() {
    }
}
