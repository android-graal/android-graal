package android.media.tv;

import android.os.Parcelable;

/**
 * Encapsulates the format of tracks played in {@link TvInputService}.
 */
public final class TvTrackInfo implements Parcelable {
    /**
     * The type value for audio tracks.
     */
    public static final int TYPE_AUDIO = 0;

    /**
     * The type value for subtitle tracks.
     */
    public static final int TYPE_SUBTITLE = 2;

    /**
     * The type value for video tracks.
     */
    public static final int TYPE_VIDEO = 1;

    private TvTrackInfo() {
    }
}
