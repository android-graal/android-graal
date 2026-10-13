package android.security.keystore;

/**
 * Properties of <a href="{@docRoot}training/articles/keystore.html">Android Keystore</a> keys.
 */
public final class KeyProperties {
    /**
     * Any biometric (e.g. fingerprint, iris, or face) on the device that meets or exceeds the
     * requirements for <strong>Strong</strong>, as defined by the Android CDD.
     */
    public static final int AUTH_BIOMETRIC_STRONG = 2;

    /**
     * The non-biometric credential used to secure the device (i.e., PIN, pattern, or password)
     */
    public static final int AUTH_DEVICE_CREDENTIAL = 1;

    /**
     * Cipher Block Chaining (CBC) block mode.
     */
    public static final String BLOCK_MODE_CBC = "CBC";

    /**
     * Counter (CTR) block mode.
     */
    public static final String BLOCK_MODE_CTR = "CTR";

    /**
     * Electronic Codebook (ECB) block mode.
     */
    public static final String BLOCK_MODE_ECB = "ECB";

    /**
     * Galois/Counter Mode (GCM) block mode.
     */
    public static final String BLOCK_MODE_GCM = "GCM";

    /**
     * MD5 digest.
     */
    public static final String DIGEST_MD5 = "MD5";

    /**
     * No digest: sign/authenticate the raw message.
     */
    public static final String DIGEST_NONE = "NONE";

    /**
     * SHA-1 digest.
     */
    public static final String DIGEST_SHA1 = "SHA-1";

    /**
     * SHA-2 224 (aka SHA-224) digest.
     */
    public static final String DIGEST_SHA224 = "SHA-224";

    /**
     * SHA-2 256 (aka SHA-256) digest.
     */
    public static final String DIGEST_SHA256 = "SHA-256";

    /**
     * SHA-2 384 (aka SHA-384) digest.
     */
    public static final String DIGEST_SHA384 = "SHA-384";

    /**
     * SHA-2 512 (aka SHA-512) digest.
     */
    public static final String DIGEST_SHA512 = "SHA-512";

    /**
     * No encryption padding.
     */
    public static final String ENCRYPTION_PADDING_NONE = "NoPadding";

    /**
     * PKCS#7 encryption padding scheme.
     */
    public static final String ENCRYPTION_PADDING_PKCS7 = "PKCS7Padding";

    /**
     * RSA Optimal Asymmetric Encryption Padding (OAEP) scheme.
     */
    public static final String ENCRYPTION_PADDING_RSA_OAEP = "OAEPPadding";

    /**
     * RSA PKCS#1 v1.5 padding scheme for encryption.
     */
    public static final String ENCRYPTION_PADDING_RSA_PKCS1 = "PKCS1Padding";

    /**
     * Triple Data Encryption Algorithm (3DES) key.
     *
     * @deprecated Included for interoperability with legacy systems. Prefer {@link
     * KeyProperties#KEY_ALGORITHM_AES} for new development.
     */
    @Deprecated
    public static final String KEY_ALGORITHM_3DES = "DESede";

    /**
     * Advanced Encryption Standard (AES) key.
     */
    public static final String KEY_ALGORITHM_AES = "AES";

    /**
     * Elliptic Curve (EC) Cryptography key.
     */
    public static final String KEY_ALGORITHM_EC = "EC";

    /**
     * Keyed-Hash Message Authentication Code (HMAC) key using SHA-1 as the hash.
     */
    public static final String KEY_ALGORITHM_HMAC_SHA1 = "HmacSHA1";

    /**
     * Keyed-Hash Message Authentication Code (HMAC) key using SHA-224 as the hash.
     */
    public static final String KEY_ALGORITHM_HMAC_SHA224 = "HmacSHA224";

    /**
     * Keyed-Hash Message Authentication Code (HMAC) key using SHA-256 as the hash.
     */
    public static final String KEY_ALGORITHM_HMAC_SHA256 = "HmacSHA256";

    /**
     * Keyed-Hash Message Authentication Code (HMAC) key using SHA-384 as the hash.
     */
    public static final String KEY_ALGORITHM_HMAC_SHA384 = "HmacSHA384";

    /**
     * Keyed-Hash Message Authentication Code (HMAC) key using SHA-512 as the hash.
     */
    public static final String KEY_ALGORITHM_HMAC_SHA512 = "HmacSHA512";

    /**
     * Rivest Shamir Adleman (RSA) key.
     */
    public static final String KEY_ALGORITHM_RSA = "RSA";

    /**
     * Key was generated inside AndroidKeyStore.
     */
    public static final int ORIGIN_GENERATED = 1;

    /**
     * Key was imported into AndroidKeyStore.
     */
    public static final int ORIGIN_IMPORTED = 2;

    /**
     * Key was imported into the AndroidKeyStore in an encrypted wrapper. Unlike imported keys,
     * securely imported keys can be imported without appearing as plaintext in the device's host
     * memory.
     */
    public static final int ORIGIN_SECURELY_IMPORTED = 8;

    /**
     * Origin of the key is unknown. This can occur only for keys backed by an old TEE-backed
     * implementation which does not record origin information.
     */
    public static final int ORIGIN_UNKNOWN = 4;

    /**
     * Purpose of key: creating a shared ECDH secret through key agreement.
     *
     * <p>A key having this purpose can be combined with the elliptic curve public key of another
     * party to establish a shared secret over an insecure channel. It should be used  as a
     * parameter to {@link javax.crypto.KeyAgreement#init(java.security.Key)} (a complete example is
     * available <a
     * href="{@docRoot}reference/android/security/keystore/KeyGenParameterSpec#example:ecdh"
     * >here</a>).
     * See <a href="https://en.wikipedia.org/wiki/Elliptic-curve_Diffie%E2%80%93Hellman">this
     * article</a> for a more detailed explanation.
     */
    public static final int PURPOSE_AGREE_KEY = 64;

    /**
     * Purpose of key: Signing attestations. This purpose is incompatible with all others, meaning
     * that when generating a key with PURPOSE_ATTEST_KEY, no other purposes may be specified. In
     * addition, PURPOSE_ATTEST_KEY may not be specified for imported keys.
     */
    public static final int PURPOSE_ATTEST_KEY = 128;

    /**
     * Purpose of key: decryption.
     */
    public static final int PURPOSE_DECRYPT = 2;

    /**
     * Purpose of key: encryption.
     */
    public static final int PURPOSE_ENCRYPT = 1;

    /**
     * Purpose of key: signing or generating a Message Authentication Code (MAC).
     */
    public static final int PURPOSE_SIGN = 4;

    /**
     * Purpose of key: signature or Message Authentication Code (MAC) verification.
     */
    public static final int PURPOSE_VERIFY = 8;

    /**
     * Purpose of key: wrapping and unwrapping wrapped keys for secure import.
     */
    public static final int PURPOSE_WRAP_KEY = 32;

    /**
     * Indicates enforcement by system software.
     */
    public static final int SECURITY_LEVEL_SOFTWARE = 0;

    /**
     * Indicates enforcement by environment meeting the Strongbox security profile,
     * such as a secure element.
     */
    public static final int SECURITY_LEVEL_STRONGBOX = 2;

    /**
     * Indicates enforcement by a trusted execution environment.
     */
    public static final int SECURITY_LEVEL_TRUSTED_ENVIRONMENT = 1;

    /**
     * This security level indicates that no assumptions can be made about the security level of the
     * respective key.
     */
    public static final int SECURITY_LEVEL_UNKNOWN = -2;

    /**
     * This security level indicates that due to the target API level of the caller no exact
     * statement can be made about the security level of the key, however, the security level
     * can be considered is at least equivalent to {@link #SECURITY_LEVEL_TRUSTED_ENVIRONMENT}.
     */
    public static final int SECURITY_LEVEL_UNKNOWN_SECURE = -1;

    /**
     * RSA PKCS#1 v1.5 padding for signatures.
     */
    public static final String SIGNATURE_PADDING_RSA_PKCS1 = "PKCS1";

    /**
     * RSA PKCS#1 v2.1 Probabilistic Signature Scheme (PSS) padding.
     */
    public static final String SIGNATURE_PADDING_RSA_PSS = "PSS";

    /**
     * This value indicates that there is no restriction on the number of times the key can be used.
     */
    public static final int UNRESTRICTED_USAGE_COUNT = -1;

    private KeyProperties() {
    }
}
