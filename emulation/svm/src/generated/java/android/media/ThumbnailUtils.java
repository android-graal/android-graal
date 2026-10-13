package android.media;

/**
 * Utilities for generating visual thumbnails from files.
 */
public final class ThumbnailUtils {
    /**
     * Constant used to indicate we should recycle the input in
     * {@link #extractThumbnail(Bitmap, int, int, int)} unless the output is the input.
     */
    public static final int OPTIONS_RECYCLE_INPUT = 2;

    private ThumbnailUtils() {
    }
}
