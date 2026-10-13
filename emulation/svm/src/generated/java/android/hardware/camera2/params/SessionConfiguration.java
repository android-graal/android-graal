package android.hardware.camera2.params;

import android.os.Parcelable;

/**
 * A helper class that aggregates all supported arguments for capture session initialization.
 */
public final class SessionConfiguration implements Parcelable {
    /**
     * A high speed session type that can only contain instances of {@link OutputConfiguration}.
     * The outputs can run using high speed FPS ranges. Calls to {@link #setInputConfiguration}
     * are not supported.
     * <p>
     * When using this type, the CameraCaptureSession returned by
     * {@link android.hardware.camera2.CameraCaptureSession.StateCallback} can be cast to a
     * {@link android.hardware.camera2.CameraConstrainedHighSpeedCaptureSession} to access the extra
     * methods for constrained high speed recording.
     * </p>
     *
     * @see CameraDevice#createConstrainedHighSpeedCaptureSession
     */
    public static final int SESSION_HIGH_SPEED = 1;

    /**
     * A regular session type containing instances of {@link OutputConfiguration} running
     * at regular non high speed FPS ranges and optionally {@link InputConfiguration} for
     * reprocessable sessions.
     *
     * @see CameraDevice#createCaptureSession(SessionConfiguration)
     * @see CameraDevice#createReprocessableCaptureSession
     */
    public static final int SESSION_REGULAR = 0;

    private SessionConfiguration() {
    }
}
