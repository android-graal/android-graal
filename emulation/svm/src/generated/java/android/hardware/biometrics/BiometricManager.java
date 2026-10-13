package android.hardware.biometrics;

/**
 * A class that contains biometric utilities. For authentication, see {@link BiometricPrompt}.
 */
public final class BiometricManager {
    /**
     * The hardware is unavailable. Try again later.
     */
    public static final int BIOMETRIC_ERROR_HW_UNAVAILABLE = 1;

    /**
     * Identity Check is currently not active.
     *
     * This device either doesn't have this feature enabled, or it's not considered in a
     * high-risk environment that requires extra security measures for accessing sensitive data.
     */
    public static final int BIOMETRIC_ERROR_IDENTITY_CHECK_NOT_ACTIVE = 20;

    /**
     * The user does not have any biometrics enrolled.
     */
    public static final int BIOMETRIC_ERROR_NONE_ENROLLED = 11;

    /**
     * Biometrics is not allowed to verify the user in apps.
     */
    public static final int BIOMETRIC_ERROR_NOT_ENABLED_FOR_APPS = 21;

    /**
     * There is no biometric hardware.
     */
    public static final int BIOMETRIC_ERROR_NO_HARDWARE = 12;

    /**
     * A security vulnerability has been discovered and the sensor is unavailable until a
     * security update has addressed this issue. This error can be received if for example,
     * authentication was requested with {@link Authenticators#BIOMETRIC_STRONG}, but the
     * sensor's strength can currently only meet {@link Authenticators#BIOMETRIC_WEAK}.
     */
    public static final int BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED = 15;

    /**
     * Returned from {@link BiometricManager#getLastAuthenticationTime(int)} when no matching
     * successful authentication has been performed since boot.
     */
    public static final long BIOMETRIC_NO_AUTHENTICATION = -1L;

    /**
     * No error detected.
     */
    public static final int BIOMETRIC_SUCCESS = 0;

    private BiometricManager() {
    }

    /**
     * Types of authenticators, defined at a level of granularity supported by
     * {@link BiometricManager} and {@link BiometricPrompt}.
     *
     * <p>Types may combined via bitwise OR into a single integer representing multiple
     * authenticators (e.g. <code>DEVICE_CREDENTIAL | BIOMETRIC_WEAK</code>).
     *
     * @see #canAuthenticate(int)
     * @see BiometricPrompt.Builder#setAllowedAuthenticators(int)
     */
    public interface Authenticators {
        /**
         * Any biometric (e.g. fingerprint, iris, or face) on the device that meets or exceeds the
         * requirements for <strong>Class 3</strong> (formerly <strong>Strong</strong>), as defined
         * by the Android CDD.
         *
         * <p>This corresponds to {@link KeyProperties#AUTH_BIOMETRIC_STRONG} during key generation.
         *
         * @see android.security.keystore.KeyGenParameterSpec.Builder
         */
        int BIOMETRIC_STRONG = 15;

        /**
         * Any biometric (e.g. fingerprint, iris, or face) on the device that meets or exceeds the
         * requirements for <strong>Class 2</strong> (formerly <strong>Weak</strong>), as defined by
         * the Android CDD.
         *
         * <p>Note that this is a superset of {@link #BIOMETRIC_STRONG} and is defined such that
         * {@code BIOMETRIC_STRONG | BIOMETRIC_WEAK == BIOMETRIC_WEAK}.
         */
        int BIOMETRIC_WEAK = 255;

        /**
         * The non-biometric credential used to secure the device (i.e., PIN, pattern, or password).
         * This should typically only be used in combination with a biometric auth type, such as
         * {@link #BIOMETRIC_WEAK}.
         *
         * <p>This corresponds to {@link KeyProperties#AUTH_DEVICE_CREDENTIAL} during key
         * generation.
         *
         * @see android.security.keystore.KeyGenParameterSpec.Builder
         */
        int DEVICE_CREDENTIAL = 32768;

        /**
         * The bit is used to request for Identity Check.
         *
         * Identity Check is a feature which requires class 3 biometric authentication to access
         * sensitive surfaces when the device is outside trusted places.
         *
         * <p> The requirements to trigger Identity Check are as follows:
         * 1. User must have enabled the toggle for Identity Check in settings
         * 2. User must have enrollments for at least one {@link #BIOMETRIC_STRONG} sensor
         * 3. The device is determined to be in a high risk environment, for example if it is
         *    outside of the user's trusted locations or fails to meet similar conditions.
         * 4. The Identity Check requirements bit must be true
         * </p>
         *
         * <p> If all the above conditions are satisfied, only {@link #BIOMETRIC_STRONG} sensors
         * will be eligible for authentication, and device credential fallback will be dropped.
         */
        int IDENTITY_CHECK = 65536;
    }
}
