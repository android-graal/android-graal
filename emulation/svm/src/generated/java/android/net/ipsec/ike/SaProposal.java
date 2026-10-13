package android.net.ipsec.ike;

/**
 * SaProposal represents a proposed configuration to negotiate an IKE or Child SA.
 *
 * <p>SaProposal will contain cryptograhic algorithms and key generation materials for the
 * negotiation of an IKE or Child SA.
 *
 * <p>User must provide at least one valid SaProposal when they are creating a new IKE or Child SA.
 *
 * @see <a href="https://tools.ietf.org/html/rfc7296#section-3.3">RFC 7296, Internet Key Exchange
 *     Protocol Version 2 (IKEv2)</a>
 */
public final class SaProposal {
    /**
     * 1024-bit MODP Diffie-Hellman Group.
     */
    public static final int DH_GROUP_1024_BIT_MODP = 2;

    /**
     * 1536-bit MODP Diffie-Hellman Group.
     */
    public static final int DH_GROUP_1536_BIT_MODP = 5;

    /**
     * 2048-bit MODP Diffie-Hellman Group.
     */
    public static final int DH_GROUP_2048_BIT_MODP = 14;

    /**
     * 3072-bit MODP Diffie-Hellman Group.
     */
    public static final int DH_GROUP_3072_BIT_MODP = 15;

    /**
     * 4096-bit MODP Diffie-Hellman Group.
     */
    public static final int DH_GROUP_4096_BIT_MODP = 16;

    /**
     * Elliptic Curve Diffie-Hellman 25519.
     */
    public static final int DH_GROUP_CURVE_25519 = 31;

    /**
     * None Diffie-Hellman Group.
     */
    public static final int DH_GROUP_NONE = 0;

    /**
     * 3DES Encryption/Ciphering Algorithm.
     */
    public static final int ENCRYPTION_ALGORITHM_3DES = 3;

    /**
     * AES-CBC Encryption/Ciphering Algorithm.
     */
    public static final int ENCRYPTION_ALGORITHM_AES_CBC = 12;

    /**
     * AES-CTR Encryption/Ciphering Algorithm.
     */
    public static final int ENCRYPTION_ALGORITHM_AES_CTR = 13;

    /**
     * AES-GCM Authentication/Integrity + Encryption/Ciphering Algorithm with 12-octet ICV
     * (truncation).
     */
    public static final int ENCRYPTION_ALGORITHM_AES_GCM_12 = 19;

    /**
     * AES-GCM Authentication/Integrity + Encryption/Ciphering Algorithm with 16-octet ICV
     * (truncation).
     */
    public static final int ENCRYPTION_ALGORITHM_AES_GCM_16 = 20;

    /**
     * AES-GCM Authentication/Integrity + Encryption/Ciphering Algorithm with 8-octet ICV
     * (truncation).
     */
    public static final int ENCRYPTION_ALGORITHM_AES_GCM_8 = 18;

    /**
     * ChaCha20-Poly1305 Authentication/Integrity + Encryption/Ciphering Algorithm with 16-octet ICV
     * (truncation).
     */
    public static final int ENCRYPTION_ALGORITHM_CHACHA20_POLY1305 = 28;

    /**
     * AES-CMAC-96 Authentication/Integrity Algorithm.
     */
    public static final int INTEGRITY_ALGORITHM_AES_CMAC_96 = 8;

    /**
     * AES-XCBC-96 Authentication/Integrity Algorithm.
     */
    public static final int INTEGRITY_ALGORITHM_AES_XCBC_96 = 5;

    /**
     * HMAC-SHA1 Authentication/Integrity Algorithm.
     */
    public static final int INTEGRITY_ALGORITHM_HMAC_SHA1_96 = 2;

    /**
     * HMAC-SHA256 Authentication/Integrity Algorithm with 128-bit truncation.
     */
    public static final int INTEGRITY_ALGORITHM_HMAC_SHA2_256_128 = 12;

    /**
     * HMAC-SHA384 Authentication/Integrity Algorithm with 192-bit truncation.
     */
    public static final int INTEGRITY_ALGORITHM_HMAC_SHA2_384_192 = 13;

    /**
     * HMAC-SHA512 Authentication/Integrity Algorithm with 256-bit truncation.
     */
    public static final int INTEGRITY_ALGORITHM_HMAC_SHA2_512_256 = 14;

    /**
     * None Authentication/Integrity Algorithm.
     */
    public static final int INTEGRITY_ALGORITHM_NONE = 0;

    /**
     * AES Encryption/Ciphering Algorithm key length 128 bits.
     */
    public static final int KEY_LEN_AES_128 = 128;

    /**
     * AES Encryption/Ciphering Algorithm key length 192 bits.
     */
    public static final int KEY_LEN_AES_192 = 192;

    /**
     * AES Encryption/Ciphering Algorithm key length 256 bits.
     */
    public static final int KEY_LEN_AES_256 = 256;

    /**
     * Key length unused.
     *
     * <p>This value should only be used with the Encryption/Ciphering Algorithm that accepts a
     * fixed key size such as {@link #ENCRYPTION_ALGORITHM_3DES}.
     */
    public static final int KEY_LEN_UNUSED = 0;

    /**
     * AES128-CMAC Pseudorandom Function.
     */
    public static final int PSEUDORANDOM_FUNCTION_AES128_CMAC = 8;

    /**
     * AES128-XCBC Pseudorandom Function.
     */
    public static final int PSEUDORANDOM_FUNCTION_AES128_XCBC = 4;

    /**
     * HMAC-SHA1 Pseudorandom Function.
     */
    public static final int PSEUDORANDOM_FUNCTION_HMAC_SHA1 = 2;

    /**
     * HMAC-SHA2-256 Pseudorandom Function.
     */
    public static final int PSEUDORANDOM_FUNCTION_SHA2_256 = 5;

    /**
     * HMAC-SHA2-384 Pseudorandom Function.
     */
    public static final int PSEUDORANDOM_FUNCTION_SHA2_384 = 6;

    /**
     * HMAC-SHA2-384 Pseudorandom Function.
     */
    public static final int PSEUDORANDOM_FUNCTION_SHA2_512 = 7;

    private SaProposal() {
    }
}
