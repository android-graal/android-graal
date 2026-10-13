package android.view.displayhash;

/**
 * Use when calling {@link View#generateDisplayHash(String, Rect, Executor,
 * DisplayHashResultCallback)}.
 *
 * The callback will only invoke either {@link #onDisplayHashResult} when the system successfully
 * generated the {@link DisplayHash} or {@link #onDisplayHashError(int)} when it failed.
 */
public interface DisplayHashResultCallback {
    /**
     * The bounds used when requesting the hash hash were invalid or empty.
     */
    int DISPLAY_HASH_ERROR_INVALID_BOUNDS = -2;

    /**
     * The hash algorithm sent to generate the hash was invalid. This means the value is not one
     * of the supported values in {@link DisplayHashManager#getSupportedHashAlgorithms()}
     */
    int DISPLAY_HASH_ERROR_INVALID_HASH_ALGORITHM = -5;

    /**
     * The window for the view that requested the hash is no longer around. This can happen if the
     * window is getting torn down.
     */
    int DISPLAY_HASH_ERROR_MISSING_WINDOW = -3;

    /**
     * The view that requested the hash is not visible on screen. This could either mean
     * that the view bounds are offscreen, window bounds are offscreen, view is not visible, or
     * window is not visible.
     */
    int DISPLAY_HASH_ERROR_NOT_VISIBLE_ON_SCREEN = -4;

    /**
     * The caller requested to generate the hash too frequently. The caller should try again at a
     * after some time has passed to ensure the system isn't overloaded.
     */
    int DISPLAY_HASH_ERROR_TOO_MANY_REQUESTS = -6;

    /**
     * An unknown error occurred.
     */
    int DISPLAY_HASH_ERROR_UNKNOWN = -1;
}
