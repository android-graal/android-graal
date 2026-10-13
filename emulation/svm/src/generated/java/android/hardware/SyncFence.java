package android.hardware;

import android.os.Parcelable;

/**
 * A SyncFence represents a synchronization primitive which signals when hardware units have
 * completed work on a particular resource. They initially start in an unsignaled state and make
 * a one-time transition to either a signaled or error state. SyncFences are created by various
 * device APIs in response to submitting tasks to the device. They cannot be created nor signaled
 * by userspace. As a result, this means that a SyncFence will make always make forward progress.
 *
 * <p>SyncFence's generally come in one of two varieties. "Presentation fences" refer to
 *  a SyncFence when the writing to a buffer has finished. "Release fences" then refer
 *  to when the reading from a buffer has finished.</p>
 *
 * <p>For example, a GPU rendering to a framebuffer may generate a synchronization fence,
 * e.g., an EGLSync or VkFence, which signals when rendering has completed. Once the fence signals,
 * then the backing storage for the framebuffer may be safely read from, such as for display or
 * for media encoding. This would be referred to as a "presentation fence."</p>
 *
 * <p>Similarly when using an {@link android.media.ImageWriter} it is possible that an
 * {@link android.media.Image} returned by {@link ImageWriter#dequeueInputImage()} may already
 * have a {@link Image#getFence() fence} set on it. This would be what is referred to as either
 * a "release fence" or an "acqurie fence" and indicates the fence that the writer must wait
 * on before writing to the underlying buffer. In the case of ImageWriter this is done
 * automatically when eg {@link Image#getPlanes()} is called, however when using
 * {@link Image#getHardwareBuffer()} it is the caller's responsibility to ensure the
 * release fence has signaled before writing to the buffer.</p>
 *
 * @see android.opengl.EGLExt#eglDupNativeFenceFDANDROID(EGLDisplay, EGLSync)
 * @see android.media.Image#getFence()
 */
public final class SyncFence implements Parcelable {
    /**
     * An invalid signal time. Represents either the signal time for a SyncFence that isn't valid
     * (that is, {@link #isValid()} is false), or if an error occurred while attempting to retrieve
     * the signal time.
     */
    public static final long SIGNAL_TIME_INVALID = -1L;

    /**
     * A pending signal time. This is equivalent to the max value of a long, representing an
     * infinitely far point in the future.
     */
    public static final long SIGNAL_TIME_PENDING = 9223372036854775807L;

    private SyncFence() {
    }
}
