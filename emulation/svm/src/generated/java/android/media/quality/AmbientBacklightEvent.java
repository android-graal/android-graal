package android.media.quality;

import android.os.Parcelable;

/**
 * Ambient backlight event
 */
public final class AmbientBacklightEvent implements Parcelable {
    /**
     * Event type for ambient backlight events. The ambient backlight is disabled.
     */
    public static final int AMBIENT_BACKLIGHT_EVENT_DISABLED = 2;

    /**
     * Event type for ambient backlight events. The ambient backlight is enabled.
     */
    public static final int AMBIENT_BACKLIGHT_EVENT_ENABLED = 1;

    /**
     * Event type for ambient backlight events. The ambient backlight event is preempted by another
     * application.
     */
    public static final int AMBIENT_BACKLIGHT_EVENT_INTERRUPTED = 4;

    /**
     * Event type for ambient backlight events. The ambient backlight metadata is
     * available.
     */
    public static final int AMBIENT_BACKLIGHT_EVENT_METADATA_AVAILABLE = 3;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private AmbientBacklightEvent() {
    }
}
