package android.accessibilityservice;

/**
 * An {@link AccessibilityService} can capture gestures performed on a device's fingerprint
 * sensor, as long as the device has a sensor capable of detecting gestures.
 * <p>
 * This capability must be declared by the service as
 * {@link AccessibilityServiceInfo#CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES}. It also requires
 * the permission {@link android.Manifest.permission#USE_FINGERPRINT}.
 * <p>
 * Because capturing fingerprint gestures may have side effects, services with the capability only
 * capture gestures when {@link AccessibilityServiceInfo#FLAG_REQUEST_FINGERPRINT_GESTURES} is set.
 * <p>
 * <strong>Note: </strong>The fingerprint sensor is used for authentication in critical use cases,
 * so services must carefully design their user's experience when performing gestures on the sensor.
 * When the sensor is in use by an app, for example, when authenticating or enrolling a user,
 * the sensor will not detect gestures. Services need to ensure that users understand when the
 * sensor is in-use for authentication to prevent users from authenticating unintentionally when
 * trying to interact with the service. They can use
 * {@link FingerprintGestureCallback#onGestureDetectionAvailabilityChanged(boolean)} to learn when
 * gesture detection becomes unavailable.
 * <p>
 * Multiple accessibility services may listen for fingerprint gestures simultaneously, so services
 * should provide a way for the user to disable the use of this feature so multiple services don't
 * conflict with each other.
 * <p>
 * {@see android.hardware.fingerprint.FingerprintManager#isHardwareDetected}
 */
public final class FingerprintGestureController {
    /**
     * Identifier for a swipe down on the fingerprint sensor
     */
    public static final int FINGERPRINT_GESTURE_SWIPE_DOWN = 8;

    /**
     * Identifier for a swipe left on the fingerprint sensor
     */
    public static final int FINGERPRINT_GESTURE_SWIPE_LEFT = 2;

    /**
     * Identifier for a swipe right on the fingerprint sensor
     */
    public static final int FINGERPRINT_GESTURE_SWIPE_RIGHT = 1;

    /**
     * Identifier for a swipe up on the fingerprint sensor
     */
    public static final int FINGERPRINT_GESTURE_SWIPE_UP = 4;

    private FingerprintGestureController() {
    }
}
