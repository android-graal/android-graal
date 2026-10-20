package android.media.quality;

import android.os.Parcelable;

/**
 * Metadata of ambient backlight.
 *
 * <p>A metadata instance is sent from ambient backlight hardware in a {@link AmbientBacklightEvent}
 * with {@link AmbientBacklightEvent#AMBIENT_BACKLIGHT_EVENT_METADATA_AVAILABLE}.
 */
public final class AmbientBacklightMetadata implements Parcelable {
    /**
     * The compress algorithm is disabled.
     */
    public static final int ALGORITHM_NONE = 0;

    /**
     * The compress algorithm is run length encoding (RLE).
     */
    public static final int ALGORITHM_RLE = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AmbientBacklightMetadata() {
    }
}
