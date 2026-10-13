package android.security;

/**
 * Exception containing information about the failure at the Keystore / KeyMint layer while
 * generating or using a key.
 *
 * The public error codes indicate the cause of the error and the methods indicate whether
 * it's a system/key issue and whether re-trying the operation (with the same key or a new key)
 * is likely to succeed.
 */
public final class KeyStoreException {
    /**
     * The attestation challenge specified is too large.
     */
    public static final int ERROR_ATTESTATION_CHALLENGE_TOO_LARGE = 9;

    /**
     * There are no keys available for attestation.
     * This error is returned only on devices that rely solely on remotely-provisioned keys (see
     * <a href=
     * "https://android-developers.googleblog.com/2022/03/upgrading-android-attestation-remote.html"
     * >Remote Key Provisioning</a>).
     *
     * <p>On such a device, if the caller requests key generation and includes an attestation
     * challenge (indicating key attestation is required), the error will be returned in one of
     * the following cases:
     * <ul>
     *     <li>The pool of remotely-provisioned keys has been exhausted.</li>
     *     <li>The device is not registered with the key provisioning server.</li>
     * </ul>
     * </p>
     *
     * <p>This error is a transient one if the pool of remotely-provisioned keys has been
     * exhausted. However, if the device is not registered with the server, or the key
     * provisioning server refuses key issuance, this is a permanent error.</p>
     */
    public static final int ERROR_ATTESTATION_KEYS_UNAVAILABLE = 16;

    /**
     * The error related to inclusion of device identifiers in the attestation record.
     */
    public static final int ERROR_ID_ATTESTATION_FAILURE = 8;

    /**
     * The feature the caller is trying to use is not compatible with the parameters used to
     * generate the key. For example, trying to use a key generated for a different signature
     * algorithm, or a digest not specified during key creation.
     * Another case is the attempt to generate a symmetric AES key and requesting key attestation.
     */
    public static final int ERROR_INCORRECT_USAGE = 13;

    /**
     * An internal system error - refer to {@link #isTransientFailure()} to determine whether
     * re-trying the operation is likely to yield different results.
     */
    public static final int ERROR_INTERNAL_SYSTEM_ERROR = 4;

    /**
     * General error in the KeyMint layer.
     */
    public static final int ERROR_KEYMINT_FAILURE = 10;

    /**
     * Failure in the Keystore layer.
     */
    public static final int ERROR_KEYSTORE_FAILURE = 11;

    /**
     * Indicating that {@code load()} has not been called on the Keystore instance, or an attempt
     * has been made to generate an authorization bound key while the user has not set a lock
     * screen knowledge factor (LSKF). Instruct the user to set an LSKF and retry.
     */
    public static final int ERROR_KEYSTORE_UNINITIALIZED = 3;

    /**
     * The key is corrupted and could not be recovered.
     */
    public static final int ERROR_KEY_CORRUPTED = 7;

    /**
     * The key the operation refers to doesn't exist.
     */
    public static final int ERROR_KEY_DOES_NOT_EXIST = 6;

    /**
     * The key is not currently valid: Either at has expired or it will be valid for use in the
     * future.
     */
    public static final int ERROR_KEY_NOT_TEMPORALLY_VALID = 14;

    /**
     * The crypto object the caller has been using held a reference to a KeyMint operation that
     * has been evacuated (likely due to other concurrent operations taking place).
     * The caller should re-create the crypto object and try again.
     */
    public static final int ERROR_KEY_OPERATION_EXPIRED = 15;

    /**
     * This error code is for mapping errors that the caller will not know about. If the caller is
     * targeting an API level earlier than the one the error was introduced in, then the error will
     * be mapped to this one.
     * In API level 33 no errors map to this error.
     */
    public static final int ERROR_OTHER = 1;

    /**
     * The caller has requested key parameters or operation which are only available to system
     * or privileged apps.
     */
    public static final int ERROR_PERMISSION_DENIED = 5;

    /**
     * The feature the caller is trying to use is not implemented by the underlying
     * KeyMint implementation.
     * This could happen when an unsupported algorithm is requested, or when trying to import
     * a key in a format other than raw or PKCS#8.
     */
    public static final int ERROR_UNIMPLEMENTED = 12;

    /**
     * Indicating the key could not be used because the user needs to authenticate first.
     * See
     * {@link android.security.keystore.KeyGenParameterSpec.Builder#setUserAuthenticationRequired(boolean)}.
     */
    public static final int ERROR_USER_AUTHENTICATION_REQUIRED = 2;

    /**
     * Re-try the operation that led to this error when the device has a software update
     * downloaded and on the next reboot. The Remote provisioning server recognizes
     * the device, but refuses issuance of attestation keys because it contains a software
     * version that could potentially be vulnerable and needs an update. Re-trying after the
     * device has upgraded and rebooted may alleviate the problem.
     *
     * <p>This value is returned when {@link #isTransientFailure()} is {@code true}.
     */
    public static final int RETRY_AFTER_NEXT_REBOOT = 4;

    /**
     * Never re-try the operation that led to this error, since it's a permanent error.
     *
     * This value is always returned when {@link #isTransientFailure()} is {@code false}.
     */
    public static final int RETRY_NEVER = 1;

    /**
     * Re-try the operation that led to this error when the device regains connectivity.
     * Remote provisioning of keys requires reaching the remote server, and the device is
     * currently unable to due that due to lack of network connectivity.
     *
     * This value is returned when {@link #isTransientFailure()} is {@code true}.
     */
    public static final int RETRY_WHEN_CONNECTIVITY_AVAILABLE = 3;

    /**
     * Re-try the operation that led to this error with an exponential back-off delay.
     * The first delay should be between 5 to 30 seconds, and each subsequent re-try should double
     * the delay time.
     *
     * This value is returned when {@link #isTransientFailure()} is {@code true}.
     */
    public static final int RETRY_WITH_EXPONENTIAL_BACKOFF = 2;

    private KeyStoreException() {
    }
}
