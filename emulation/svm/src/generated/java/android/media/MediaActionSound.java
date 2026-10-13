package android.media;

/**
 * <p>A class for producing sounds that match those produced by various actions
 * taken by the media and camera APIs. It is recommended to call methods in this class
 * in a background thread since it relies on binder calls.</p>
 *
 * <p>This class is recommended for use with the {@link android.hardware.camera2} API, since the
 * camera2 API does not play any sounds on its own for any capture or video recording actions.</p>
 *
 * <p>With the older {@link android.hardware.Camera} API, use this class to play an appropriate
 * camera operation sound when implementing a custom still or video recording mechanism (through the
 * Camera preview callbacks with
 * {@link android.hardware.Camera#setPreviewCallback Camera.setPreviewCallback}, or through GPU
 * processing with {@link android.hardware.Camera#setPreviewTexture Camera.setPreviewTexture}, for
 * example), or when implementing some other camera-like function in your application.</p>
 *
 * <p>There is no need to play sounds when using
 * {@link android.hardware.Camera#takePicture Camera.takePicture} or
 * {@link android.media.MediaRecorder} for still images or video, respectively,
 * as the Android framework will play the appropriate sounds when needed for
 * these calls.</p>
 */
public final class MediaActionSound {
    /**
     * A sound to indicate that focusing has completed. Because deciding
     * when this occurs is application-dependent, this sound is not used by
     * any methods in the media or camera APIs.
     * @see #play
     */
    public static final int FOCUS_COMPLETE = 1;

    /**
     * The sound used by
     * {@link android.hardware.Camera#takePicture Camera.takePicture} to
     * indicate still image capture.
     * @see #play
     */
    public static final int SHUTTER_CLICK = 0;

    /**
     * The sound used by
     * {@link android.media.MediaRecorder#start MediaRecorder.start()} to
     * indicate the start of video recording.
     * @see #play
     */
    public static final int START_VIDEO_RECORDING = 2;

    /**
     * The sound used by
     * {@link android.media.MediaRecorder#stop MediaRecorder.stop()} to
     * indicate the end of video recording.
     * @see #play
     */
    public static final int STOP_VIDEO_RECORDING = 3;

    private MediaActionSound() {
    }
}
