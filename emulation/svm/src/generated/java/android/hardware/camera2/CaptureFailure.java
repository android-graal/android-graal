package android.hardware.camera2;

/**
 * A report of failed capture for a single image capture from the image sensor.
 *
 * <p>CaptureFailures are produced by a {@link CameraDevice} if processing a
 * {@link CaptureRequest} fails, either partially or fully. Use {@link #getReason}
 * to determine the specific nature of the failed capture.</p>
 *
 * <p>Receiving a CaptureFailure means that the metadata associated with that frame number
 * has been dropped -- no {@link CaptureResult} with the same frame number will be
 * produced.</p>
 */
public final class CaptureFailure {
    /**
     * The {@link CaptureResult} has been dropped this frame only due to an error
     * in the framework.
     *
     * @see #getReason()
     */
    public static final int REASON_ERROR = 0;

    /**
     * The capture has failed due to a {@link CameraCaptureSession#abortCaptures} call from the
     * application.
     *
     * @see #getReason()
     */
    public static final int REASON_FLUSHED = 1;

    private CaptureFailure() {
    }
}
