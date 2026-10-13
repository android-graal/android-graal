package android.content.pm;

import android.os.Parcelable;

/**
 * A typed checksum.
 *
 * @see ApkChecksum
 * @see PackageManager#requestChecksums
 */
public final class Checksum implements Parcelable {
    /**
     * Root SHA256 hash of a 1M Merkle tree computed over protected content.
     * Excludes signing block.
     * <a href="https://source.android.com/security/apksigning/v2">See APK Signature Scheme V2</a>.
     *
     * @see PackageManager#requestChecksums
     */
    public static final int TYPE_PARTIAL_MERKLE_ROOT_1M_SHA256 = 32;

    /**
     * Root SHA512 hash of a 1M Merkle tree computed over protected content.
     * Excludes signing block.
     * <a href="https://source.android.com/security/apksigning/v2">See APK Signature Scheme V2</a>.
     *
     * @see PackageManager#requestChecksums
     */
    public static final int TYPE_PARTIAL_MERKLE_ROOT_1M_SHA512 = 64;

    /**
     * MD5 hash computed over all file bytes.
     *
     * @see PackageManager#requestChecksums
     * @deprecated Not platform enforced. Cryptographically broken and unsuitable for further use.
     *             Use platform enforced digests e.g. {@link #TYPE_WHOLE_MERKLE_ROOT_4K_SHA256}.
     *             Provided for completeness' sake and to support legacy usecases.
     */
    @Deprecated
    public static final int TYPE_WHOLE_MD5 = 2;

    /**
     * Root SHA256 hash of a 4K Merkle tree computed over all file bytes.
     * <a href="https://source.android.com/security/apksigning/v4">See APK Signature Scheme V4</a>.
     * <a href="https://www.kernel.org/doc/html/latest/filesystems/fsverity.html">See fs-verity</a>.
     *
     * Recommended for all new applications.
     * Can be used by kernel to enforce authenticity and integrity of the APK.
     * <a href="https://www.kernel.org/doc/html/latest/filesystems/fsverity.html">See fs-verity for details</a>
     *
     * @see PackageManager#requestChecksums
     */
    public static final int TYPE_WHOLE_MERKLE_ROOT_4K_SHA256 = 1;

    /**
     * SHA1 hash computed over all file bytes.
     *
     * @see PackageManager#requestChecksums
     * @deprecated Not platform enforced. Broken and should not be used.
     *             Use platform enforced digests e.g. {@link #TYPE_WHOLE_MERKLE_ROOT_4K_SHA256}.
     *             Provided for completeness' sake and to support legacy usecases.
     */
    @Deprecated
    public static final int TYPE_WHOLE_SHA1 = 4;

    /**
     * SHA256 hash computed over all file bytes.
     * @deprecated Not platform enforced.
     *             Use platform enforced digests e.g. {@link #TYPE_WHOLE_MERKLE_ROOT_4K_SHA256}.
     *             Provided for completeness' sake and to support legacy usecases.
     *
     * @see PackageManager#requestChecksums
     */
    @Deprecated
    public static final int TYPE_WHOLE_SHA256 = 8;

    /**
     * SHA512 hash computed over all file bytes.
     * @deprecated Not platform enforced.
     *             Use platform enforced digests e.g. {@link #TYPE_WHOLE_MERKLE_ROOT_4K_SHA256}.
     *             Provided for completeness' sake and to support legacy usecases.
     *
     * @see PackageManager#requestChecksums
     */
    @Deprecated
    public static final int TYPE_WHOLE_SHA512 = 16;

    private Checksum() {
    }
}
