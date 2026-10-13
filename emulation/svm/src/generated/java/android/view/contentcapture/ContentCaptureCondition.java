package android.view.contentcapture;

import android.os.Parcelable;

/**
 * Defines a condition for when content capture should be allowed.
 *
 * <p>See {@link ContentCaptureManager#getContentCaptureConditions()} for more.
 */
public final class ContentCaptureCondition implements Parcelable {
    /**
     * When set, package should use the {@link LocusId#getId()} as a regular expression (using the
     * {@link java.util.regex.Pattern} format).
     */
    public static final int FLAG_IS_REGEX = 2;

    private ContentCaptureCondition() {
    }
}
