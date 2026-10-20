package android.view;

import android.os.Parcelable;

/**
 * Handle onto a raw buffer that is being managed by the screen compositor.
 *
 * <p>A Surface is generally created by or from a consumer of image buffers (such as a
 * {@link android.graphics.SurfaceTexture}, {@link android.media.MediaRecorder}, or
 * {@link android.renderscript.Allocation}), and is handed to some kind of producer (such as
 * {@link android.opengl.EGL14#eglCreateWindowSurface(android.opengl.EGLDisplay,android.opengl.EGLConfig,java.lang.Object,int[],int) OpenGL},
 * {@link android.media.MediaPlayer#setSurface MediaPlayer}, or
 * {@link android.hardware.camera2.CameraDevice#createCaptureSession CameraDevice}) to draw
 * into.</p>
 *
 * <p><strong>Note:</strong> A Surface acts like a
 * {@link java.lang.ref.WeakReference weak reference} to the consumer it is associated with. By
 * itself it will not keep its parent consumer from being reclaimed.</p>
 */
public final class Surface implements Parcelable {
    /**
     * Change the frame rate even if the transition is going to be non-seamless, i.e. with visual
     * interruptions for the user. Non-seamless switches might be used when the benefit of matching
     * the content's frame rate outweighs the cost of the transition, for example when
     * displaying long-running video content.
     */
    public static final int CHANGE_FRAME_RATE_ALWAYS = 1;

    /**
     * Change the frame rate only if the transition is going to be seamless.
     */
    public static final int CHANGE_FRAME_RATE_ONLY_IF_SEAMLESS = 0;

    /**
     * The surface requests a frame rate that is at least the specified frame rate.
     * This value should be used for UIs, animations, scrolling and fling, and anything that is not
     * a game or video.
     *
     * For video, use {@link FRAME_RATE_COMPATIBILITY_FIXED_SOURCE} instead. For game content, use
     * {@link FRAME_RATE_COMPATIBILITY_DEFAULT}.
     */
    public static final int FRAME_RATE_COMPATIBILITY_AT_LEAST = 2;

    /**
     * There are no inherent restrictions on the frame rate of this surface. When the
     * system selects a frame rate other than what the app requested, the app will be able
     * to run at the system frame rate without requiring pull down. This value should be
     * used when displaying game content, UIs, and anything that isn't video.
     *
     * In Android version {@link Build.VERSION_CODES#BAKLAVA} and above, use
     * {@link FRAME_RATE_COMPATIBILITY_DEFAULT} for game content.
     * For other cases, see {@link FRAME_RATE_COMPATIBILITY_FIXED_SOURCE} and
     * {@link FRAME_RATE_COMPATIBILITY_AT_LEAST}.
     */
    public static final int FRAME_RATE_COMPATIBILITY_DEFAULT = 0;

    /**
     * This surface is being used to display content with an inherently fixed frame rate,
     * e.g. a video that has a specific frame rate. When the system selects a frame rate
     * other than what the app requested, the app will need to do pull down or use some
     * other technique to adapt to the system's frame rate. The user experience is likely
     * to be worse (e.g. more frame stuttering) than it would be if the system had chosen
     * the app's requested frame rate. This value should be used for video content.
     */
    public static final int FRAME_RATE_COMPATIBILITY_FIXED_SOURCE = 1;

    /**
     * Rotation constant: 0 degree rotation (natural orientation)
     */
    public static final int ROTATION_0 = 0;

    /**
     * Rotation constant: 180 degree rotation.
     */
    public static final int ROTATION_180 = 2;

    /**
     * Rotation constant: 270 degree rotation.
     */
    public static final int ROTATION_270 = 3;

    /**
     * Rotation constant: 90 degree rotation.
     */
    public static final int ROTATION_90 = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Surface() {
    }
}
