package android.media.quality;

import android.os.Parcelable;

/**
 * Settings to configure ambient backlight hardware.
 */
public final class AmbientBacklightSettings implements Parcelable {
    /**
     * The detection is enabled for audio.
     */
    public static final int SOURCE_AUDIO = 1;

    /**
     * The detection is enabled for audio and video.
     */
    public static final int SOURCE_AUDIO_VIDEO = 3;

    /**
     * The detection is disabled.
     */
    public static final int SOURCE_NONE = 0;

    /**
     * The detection is enabled for video.
     */
    public static final int SOURCE_VIDEO = 2;

    private AmbientBacklightSettings() {
    }
}
