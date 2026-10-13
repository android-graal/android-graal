package android.net;

import android.os.Parcelable;

/**
 * This class represents a single algorithm that can be used by an {@link IpSecTransform}.
 *
 * @see <a href="https://tools.ietf.org/html/rfc4301">RFC 4301, Security Architecture for the
 * Internet Protocol</a>
 */
public final class IpSecAlgorithm implements Parcelable {
    /**
     * AES-CMAC Authentication/Integrity Algorithm.
     *
     * <p>Keys for this algorithm must be 128 bits in length.
     *
     * <p>The only valid truncation length is 96 bits.
     *
     * <p>This algorithm may be available on the device. Caller MUST check if it is supported before
     * using it by calling {@link #getSupportedAlgorithms()} and checking if this algorithm is
     * included in the returned algorithm set. The returned algorithm set will not change unless the
     * device is rebooted. {@link IllegalArgumentException} will be thrown if this algorithm is
     * requested on an unsupported device.
     *
     * <p>@see {@link #getSupportedAlgorithms()}
     */
    public static final String AUTH_AES_CMAC = "cmac(aes)";

    /**
     * AES-XCBC Authentication/Integrity Algorithm.
     *
     * <p>Keys for this algorithm must be 128 bits in length.
     *
     * <p>The only valid truncation length is 96 bits.
     *
     * <p>This algorithm may be available on the device. Caller MUST check if it is supported before
     * using it by calling {@link #getSupportedAlgorithms()} and checking if this algorithm is
     * included in the returned algorithm set. The returned algorithm set will not change unless the
     * device is rebooted. {@link IllegalArgumentException} will be thrown if this algorithm is
     * requested on an unsupported device.
     *
     * <p>@see {@link #getSupportedAlgorithms()}
     */
    public static final String AUTH_AES_XCBC = "xcbc(aes)";

    /**
     * AES-GCM Authentication/Integrity + Encryption/Ciphering Algorithm.
     *
     * <p>Valid lengths for keying material are {160, 224, 288}.
     *
     * <p>As per <a href="https://tools.ietf.org/html/rfc4106#section-8.1">RFC4106 (Section
     * 8.1)</a>, keying material consists of a 128, 192, or 256 bit AES key followed by a 32-bit
     * salt. RFC compliance requires that the salt must be unique per invocation with the same key.
     *
     * <p>Valid ICV (truncation) lengths are {64, 96, 128}.
     */
    public static final String AUTH_CRYPT_AES_GCM = "rfc4106(gcm(aes))";

    /**
     * ChaCha20-Poly1305 Authentication/Integrity + Encryption/Ciphering Algorithm.
     *
     * <p>Keys for this algorithm must be 288 bits in length.
     *
     * <p>As per <a href="https://tools.ietf.org/html/rfc7634#section-2">RFC7634 (Section 2)</a>,
     * keying material consists of a 256 bit key followed by a 32-bit salt. The salt is fixed per
     * security association.
     *
     * <p>The only valid ICV (truncation) length is 128 bits.
     *
     * <p>This algorithm may be available on the device. Caller MUST check if it is supported before
     * using it by calling {@link #getSupportedAlgorithms()} and checking if this algorithm is
     * included in the returned algorithm set. The returned algorithm set will not change unless the
     * device is rebooted. {@link IllegalArgumentException} will be thrown if this algorithm is
     * requested on an unsupported device.
     *
     * <p>@see {@link #getSupportedAlgorithms()}
     */
    public static final String AUTH_CRYPT_CHACHA20_POLY1305 = "rfc7539esp(chacha20,poly1305)";

    /**
     * MD5 HMAC Authentication/Integrity Algorithm. <b>This algorithm is not recommended for use in
     * new applications and is provided for legacy compatibility with 3gpp infrastructure.</b>
     *
     * <p>Keys for this algorithm must be 128 bits in length.
     *
     * <p>Valid truncation lengths are multiples of 8 bits from 96 to 128.
     */
    public static final String AUTH_HMAC_MD5 = "hmac(md5)";

    /**
     * SHA1 HMAC Authentication/Integrity Algorithm. <b>This algorithm is not recommended for use in
     * new applications and is provided for legacy compatibility with 3gpp infrastructure.</b>
     *
     * <p>Keys for this algorithm must be 160 bits in length.
     *
     * <p>Valid truncation lengths are multiples of 8 bits from 96 to 160.
     */
    public static final String AUTH_HMAC_SHA1 = "hmac(sha1)";

    /**
     * SHA256 HMAC Authentication/Integrity Algorithm.
     *
     * <p>Keys for this algorithm must be 256 bits in length.
     *
     * <p>Valid truncation lengths are multiples of 8 bits from 96 to 256.
     */
    public static final String AUTH_HMAC_SHA256 = "hmac(sha256)";

    /**
     * SHA384 HMAC Authentication/Integrity Algorithm.
     *
     * <p>Keys for this algorithm must be 384 bits in length.
     *
     * <p>Valid truncation lengths are multiples of 8 bits from 192 to 384.
     */
    public static final String AUTH_HMAC_SHA384 = "hmac(sha384)";

    /**
     * SHA512 HMAC Authentication/Integrity Algorithm.
     *
     * <p>Keys for this algorithm must be 512 bits in length.
     *
     * <p>Valid truncation lengths are multiples of 8 bits from 256 to 512.
     */
    public static final String AUTH_HMAC_SHA512 = "hmac(sha512)";

    /**
     * AES-CBC Encryption/Ciphering Algorithm.
     *
     * <p>Valid lengths for this key are {128, 192, 256}.
     */
    public static final String CRYPT_AES_CBC = "cbc(aes)";

    /**
     * AES-CTR Encryption/Ciphering Algorithm.
     *
     * <p>Valid lengths for keying material are {160, 224, 288}.
     *
     * <p>As per <a href="https://tools.ietf.org/html/rfc3686#section-5.1">RFC3686 (Section
     * 5.1)</a>, keying material consists of a 128, 192, or 256 bit AES key followed by a 32-bit
     * nonce. RFC compliance requires that the nonce must be unique per security association.
     *
     * <p>This algorithm may be available on the device. Caller MUST check if it is supported before
     * using it by calling {@link #getSupportedAlgorithms()} and checking if this algorithm is
     * included in the returned algorithm set. The returned algorithm set will not change unless the
     * device is rebooted. {@link IllegalArgumentException} will be thrown if this algorithm is
     * requested on an unsupported device.
     *
     * <p>@see {@link #getSupportedAlgorithms()}
     */
    public static final String CRYPT_AES_CTR = "rfc3686(ctr(aes))";

    private IpSecAlgorithm() {
    }
}
