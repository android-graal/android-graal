package android.media.tv;

import android.os.Parcelable;

/**
 * An advertisement request which can be sent to TV input to request AD operations.
 */
public final class AdRequest implements Parcelable {
    /**
     * Request to start an advertisement.
     */
    public static final int REQUEST_TYPE_START = 1;

    /**
     * Request to stop an advertisement.
     */
    public static final int REQUEST_TYPE_STOP = 2;

    private AdRequest() {
    }
}
