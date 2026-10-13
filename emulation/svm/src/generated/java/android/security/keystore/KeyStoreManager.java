package android.security.keystore;

/**
 * This class provides methods for interacting with keys stored within the
 * <a href="/privacy-and-security/keystore">Android Keystore</a>.
 */
public final class KeyStoreManager {
    /**
     * When passed into getSupplementaryAttestationInfo, getSupplementaryAttestationInfo returns the
     * DER-encoded structure corresponding to the `Modules` schema described in the KeyMint HAL's
     * KeyCreationResult.aidl. The SHA-256 hash of this encoded structure is what's included with
     * the tag in attestations. To ensure the returned encoded structure is the one attested to,
     * clients should verify its SHA-256 hash matches the one in the attestation. Note that the
     * returned structure can vary between boots.
     */
    public static final int MODULE_HASH = -1879047468;

    private KeyStoreManager() {
    }
}
