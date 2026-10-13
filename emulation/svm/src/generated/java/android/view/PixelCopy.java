package android.view;

/**
 * Provides a mechanisms to issue pixel copy requests to allow for copy
 * operations from {@link Surface} to {@link Bitmap}
 */
public final class PixelCopy {
    /**
     * The destination isn't a valid copy target. If the destination is a bitmap
     * this can occur if the bitmap is too large for the hardware to copy to.
     * It can also occur if the destination has been destroyed.
     */
    public static final int ERROR_DESTINATION_INVALID = 5;

    /**
     * It is not possible to copy from the source. This can happen if the source
     * is hardware-protected or destroyed.
     */
    public static final int ERROR_SOURCE_INVALID = 4;

    /**
     * The source has nothing to copy from. When the source is a {@link Surface}
     * this means that no buffers have been queued yet. Wait for the source
     * to produce a frame and try again.
     */
    public static final int ERROR_SOURCE_NO_DATA = 3;

    /**
     * A timeout occurred while trying to acquire a buffer from the source to
     * copy from.
     */
    public static final int ERROR_TIMEOUT = 2;

    /**
     * The pixel copy request failed with an unknown error.
     */
    public static final int ERROR_UNKNOWN = 1;

    /**
     * The pixel copy request succeeded
     */
    public static final int SUCCESS = 0;

    private PixelCopy() {
    }
}
