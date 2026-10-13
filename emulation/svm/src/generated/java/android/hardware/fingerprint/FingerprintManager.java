package android.hardware.fingerprint;

/**
 * A class that coordinates access to the fingerprint hardware.
 * @deprecated See {@link BiometricPrompt} which shows a system-provided dialog upon starting
 * authentication. In a world where devices may have different types of biometric authentication,
 * it's much more realistic to have a system-provided authentication dialog since the method may
 * vary by vendor/device.
 */
@Deprecated
public final class FingerprintManager {
    @Deprecated
    public static final int FINGERPRINT_ACQUIRED_GOOD = 0;

    @Deprecated
    public static final int FINGERPRINT_ACQUIRED_IMAGER_DIRTY = 3;

    @Deprecated
    public static final int FINGERPRINT_ACQUIRED_INSUFFICIENT = 2;

    @Deprecated
    public static final int FINGERPRINT_ACQUIRED_PARTIAL = 1;

    @Deprecated
    public static final int FINGERPRINT_ACQUIRED_TOO_FAST = 5;

    @Deprecated
    public static final int FINGERPRINT_ACQUIRED_TOO_SLOW = 4;

    @Deprecated
    public static final int FINGERPRINT_ERROR_CANCELED = 5;

    @Deprecated
    public static final int FINGERPRINT_ERROR_HW_NOT_PRESENT = 12;

    @Deprecated
    public static final int FINGERPRINT_ERROR_HW_UNAVAILABLE = 1;

    @Deprecated
    public static final int FINGERPRINT_ERROR_LOCKOUT = 7;

    @Deprecated
    public static final int FINGERPRINT_ERROR_LOCKOUT_PERMANENT = 9;

    @Deprecated
    public static final int FINGERPRINT_ERROR_NO_FINGERPRINTS = 11;

    @Deprecated
    public static final int FINGERPRINT_ERROR_NO_SPACE = 4;

    @Deprecated
    public static final int FINGERPRINT_ERROR_TIMEOUT = 3;

    @Deprecated
    public static final int FINGERPRINT_ERROR_UNABLE_TO_PROCESS = 2;

    @Deprecated
    public static final int FINGERPRINT_ERROR_USER_CANCELED = 10;

    @Deprecated
    public static final int FINGERPRINT_ERROR_VENDOR = 8;

    private FingerprintManager() {
    }
}
