package android.hardware.camera2;

/**
 * <p>The CameraDevice class is a representation of a single camera connected to an
 * Android device, allowing for fine-grain control of image capture and
 * post-processing at high frame rates.</p>
 *
 * <p>Your application must declare the
 * {@link android.Manifest.permission#CAMERA Camera} permission in its manifest
 * in order to access camera devices.</p>
 *
 * <p>A given camera device may provide support at one of several levels defined
 * in {@link CameraCharacteristics#INFO_SUPPORTED_HARDWARE_LEVEL}.
 * If a device supports {@link CameraMetadata#INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY LEGACY} level,
 * the camera device is running in backward compatibility mode and has minimum camera2 API support.
 * If a device supports the {@link CameraMetadata#INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED LIMITED}
 * level, then Camera2 exposes a feature set that is roughly equivalent to the older
 * {@link android.hardware.Camera Camera} API, although with a cleaner and more
 * efficient interface.
 * If a device supports the {@link CameraMetadata#INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL EXTERNAL}
 * level, then the device is a removable camera that provides similar but slightly less features
 * as the {@link CameraMetadata#INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED LIMITED} level.
 * Devices that implement the {@link CameraMetadata#INFO_SUPPORTED_HARDWARE_LEVEL_FULL FULL} or
 * {@link CameraMetadata#INFO_SUPPORTED_HARDWARE_LEVEL_3 LEVEL3} level of support
 * provide substantially improved capabilities over the older camera
 * API. If your application requires a full-level device for
 * proper operation, declare the "android.hardware.camera.level.full" feature in your
 * manifest.</p>
 *
 * @see CameraManager#openCamera
 * @see android.Manifest.permission#CAMERA
 * @see CameraCharacteristics#INFO_SUPPORTED_HARDWARE_LEVEL
 */
public final class CameraDevice {
    /**
     * No vibration or sound muting for this camera device. This is the default
     * mode for all camera devices.
     *
     * @see #setCameraAudioRestriction
     */
    public static final int AUDIO_RESTRICTION_NONE = 0;

    /**
     * Mute vibration from ringtones, alarms or notifications while this camera device is in use.
     *
     * @see #setCameraAudioRestriction
     */
    public static final int AUDIO_RESTRICTION_VIBRATION = 1;

    /**
     * Mute vibration and sound from ringtones, alarms or notifications while this camera device is
     * in use.
     *
     * @see #setCameraAudioRestriction
     */
    public static final int AUDIO_RESTRICTION_VIBRATION_SOUND = 3;

    /**
     * A basic template for direct application control of capture
     * parameters. All automatic control is disabled (auto-exposure, auto-white
     * balance, auto-focus), and post-processing parameters are set to preview
     * quality. The manual capture parameters (exposure, sensitivity, and so on)
     * are set to reasonable defaults, but should be overridden by the
     * application depending on the intended use case.
     * This template is guaranteed to be supported on camera devices that support the
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR MANUAL_SENSOR}
     * capability.
     *
     * @see #createCaptureRequest
     */
    public static final int TEMPLATE_MANUAL = 6;

    /**
     * Create a request suitable for a camera preview window. Specifically, this
     * means that high frame rate is given priority over the highest-quality
     * post-processing. These requests would normally be used with the
     * {@link CameraCaptureSession#setRepeatingRequest} method.
     * This template is guaranteed to be supported on all camera devices.
     *
     * @see #createCaptureRequest
     */
    public static final int TEMPLATE_PREVIEW = 1;

    /**
     * Create a request suitable for video recording. Specifically, this means
     * that a stable frame rate is used, and post-processing is set for
     * recording quality. These requests would commonly be used with the
     * {@link CameraCaptureSession#setRepeatingRequest} method.
     * This template is guaranteed to be supported on all camera devices except
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT DEPTH_OUTPUT} devices
     * that are not {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE
     * BACKWARD_COMPATIBLE}.
     *
     * @see #createCaptureRequest
     */
    public static final int TEMPLATE_RECORD = 3;

    /**
     * Create a request suitable for still image capture. Specifically, this
     * means prioritizing image quality over frame rate. These requests would
     * commonly be used with the {@link CameraCaptureSession#capture} method.
     * This template is guaranteed to be supported on all camera devices except
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT DEPTH_OUTPUT} devices
     * that are not {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE
     * BACKWARD_COMPATIBLE}.
     * @see #createCaptureRequest
     */
    public static final int TEMPLATE_STILL_CAPTURE = 2;

    /**
     * Create a request suitable for still image capture while recording
     * video. Specifically, this means maximizing image quality without
     * disrupting the ongoing recording. These requests would commonly be used
     * with the {@link CameraCaptureSession#capture} method while a request based on
     * {@link #TEMPLATE_RECORD} is is in use with {@link CameraCaptureSession#setRepeatingRequest}.
     * This template is guaranteed to be supported on all camera devices except
     * legacy devices ({@link CameraCharacteristics#INFO_SUPPORTED_HARDWARE_LEVEL}
     * {@code == }{@link CameraMetadata#INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY LEGACY}) and
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT DEPTH_OUTPUT} devices
     * that are not {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE
     * BACKWARD_COMPATIBLE}.
     *
     * @see #createCaptureRequest
     */
    public static final int TEMPLATE_VIDEO_SNAPSHOT = 4;

    /**
     * Create a request suitable for zero shutter lag still capture. This means
     * means maximizing image quality without compromising preview frame rate.
     * AE/AWB/AF should be on auto mode. This is intended for application-operated ZSL. For
     * device-operated ZSL, use {@link CaptureRequest#CONTROL_ENABLE_ZSL} if available.
     * This template is guaranteed to be supported on camera devices that support the
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING PRIVATE_REPROCESSING}
     * capability or the
     * {@link CameraMetadata#REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING YUV_REPROCESSING}
     * capability.
     *
     * @see #createCaptureRequest
     * @see CaptureRequest#CONTROL_ENABLE_ZSL
     */
    public static final int TEMPLATE_ZERO_SHUTTER_LAG = 5;

    private CameraDevice() {
    }

    /**
     * A callback objects for receiving updates about the state of a camera device.
     *
     * <p>A callback instance must be provided to the {@link CameraManager#openCamera} method to
     * open a camera device.</p>
     *
     * <p>These state updates include notifications about the device completing startup (
     * allowing for {@link #createCaptureSession} to be called), about device
     * disconnection or closure, and about unexpected device errors.</p>
     *
     * <p>Events about the progress of specific {@link CaptureRequest CaptureRequests} are provided
     * through a {@link CameraCaptureSession.CaptureCallback} given to the
     * {@link CameraCaptureSession#capture}, {@link CameraCaptureSession#captureBurst},
     * {@link CameraCaptureSession#setRepeatingRequest}, or
     * {@link CameraCaptureSession#setRepeatingBurst} methods.
     *
     * @see CameraManager#openCamera
     */
    public static final class StateCallback {
        /**
         * An error code that can be reported by {@link #onError}
         * indicating that the camera device has encountered a fatal error.
         *
         * <p>The camera device needs to be re-opened to be used again.</p>
         *
         * @see #onError
         */
        public static final int ERROR_CAMERA_DEVICE = 4;

        /**
         * An error code that can be reported by {@link #onError}
         * indicating that the camera device could not be opened due to a device
         * policy.
         *
         * @see android.app.admin.DevicePolicyManager#setCameraDisabled(android.content.ComponentName, boolean)
         * @see #onError
         */
        public static final int ERROR_CAMERA_DISABLED = 3;

        /**
         * An error code that can be reported by {@link #onError}
         * indicating that the camera device is in use already.
         *
         * <p>
         * This error can be produced when opening the camera fails due to the camera
         *  being used by a higher-priority camera API client.
         * </p>
         *
         * @see #onError
         */
        public static final int ERROR_CAMERA_IN_USE = 1;

        /**
         * An error code that can be reported by {@link #onError}
         * indicating that the camera service has encountered a fatal error.
         *
         * <p>The Android device may need to be shut down and restarted to restore
         * camera function, or there may be a persistent hardware problem.</p>
         *
         * <p>An attempt at recovery <i>may</i> be possible by closing the
         * CameraDevice and the CameraManager, and trying to acquire all resources
         * again from scratch.</p>
         *
         * @see #onError
         */
        public static final int ERROR_CAMERA_SERVICE = 5;

        /**
         * An error code that can be reported by {@link #onError}
         * indicating that the camera device could not be opened
         * because there are too many other open camera devices.
         *
         * <p>
         * The system-wide limit for number of open cameras has been reached,
         * and more camera devices cannot be opened until previous instances are
         * closed.
         * </p>
         *
         * <p>
         * This error can be produced when opening the camera fails.
         * </p>
         *
         * @see #onError
         */
        public static final int ERROR_MAX_CAMERAS_IN_USE = 2;

        private StateCallback() {
        }
    }
}
