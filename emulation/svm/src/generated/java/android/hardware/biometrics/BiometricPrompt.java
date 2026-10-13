package android.hardware.biometrics;

/**
 * A class that manages a system-provided biometric dialog.
 */
public final class BiometricPrompt {
    /**
     * Authentication type reported by {@link AuthenticationResult} when the user authenticated by
     * presenting some form of biometric (e.g. fingerprint or face).
     */
    public static final int AUTHENTICATION_RESULT_TYPE_BIOMETRIC = 2;

    /**
     * Authentication type reported by {@link AuthenticationResult} when the user authenticated by
     * entering their device PIN, pattern, or password.
     */
    public static final int AUTHENTICATION_RESULT_TYPE_DEVICE_CREDENTIAL = 1;

    public static final int BIOMETRIC_ACQUIRED_GOOD = 0;

    public static final int BIOMETRIC_ACQUIRED_IMAGER_DIRTY = 3;

    public static final int BIOMETRIC_ACQUIRED_INSUFFICIENT = 2;

    public static final int BIOMETRIC_ACQUIRED_PARTIAL = 1;

    public static final int BIOMETRIC_ACQUIRED_TOO_FAST = 5;

    public static final int BIOMETRIC_ACQUIRED_TOO_SLOW = 4;

    public static final int BIOMETRIC_ERROR_CANCELED = 5;

    public static final int BIOMETRIC_ERROR_HW_NOT_PRESENT = 12;

    public static final int BIOMETRIC_ERROR_HW_UNAVAILABLE = 1;

    public static final int BIOMETRIC_ERROR_IDENTITY_CHECK_NOT_ACTIVE = 20;

    public static final int BIOMETRIC_ERROR_LOCKOUT = 7;

    public static final int BIOMETRIC_ERROR_LOCKOUT_PERMANENT = 9;

    public static final int BIOMETRIC_ERROR_NOT_ENABLED_FOR_APPS = 21;

    public static final int BIOMETRIC_ERROR_NO_BIOMETRICS = 11;

    public static final int BIOMETRIC_ERROR_NO_DEVICE_CREDENTIAL = 14;

    public static final int BIOMETRIC_ERROR_NO_SPACE = 4;

    public static final int BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED = 15;

    public static final int BIOMETRIC_ERROR_TIMEOUT = 3;

    public static final int BIOMETRIC_ERROR_UNABLE_TO_PROCESS = 2;

    public static final int BIOMETRIC_ERROR_USER_CANCELED = 10;

    public static final int BIOMETRIC_ERROR_VENDOR = 8;

    public static final long BIOMETRIC_NO_AUTHENTICATION = -1L;

    private BiometricPrompt() {
    }
}
