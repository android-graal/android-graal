package android.media.metrics;

import android.os.Parcelable;

/**
 * Playback track change event.
 */
public final class TrackChangeEvent implements Parcelable {
    /**
     * Track change reason for adaptive changes.
     */
    public static final int TRACK_CHANGE_REASON_ADAPTIVE = 4;

    /**
     * Track change reason for initial state.
     */
    public static final int TRACK_CHANGE_REASON_INITIAL = 2;

    /**
     * Track change reason for manual changes.
     */
    public static final int TRACK_CHANGE_REASON_MANUAL = 3;

    /**
     * Other track change reason.
     */
    public static final int TRACK_CHANGE_REASON_OTHER = 1;

    /**
     * Unknown track change reason.
     */
    public static final int TRACK_CHANGE_REASON_UNKNOWN = 0;

    /**
     * The track is off.
     */
    public static final int TRACK_STATE_OFF = 0;

    /**
     * The track is on.
     */
    public static final int TRACK_STATE_ON = 1;

    /**
     * Audio track.
     */
    public static final int TRACK_TYPE_AUDIO = 0;

    /**
     * Text track.
     */
    public static final int TRACK_TYPE_TEXT = 2;

    /**
     * Video track.
     */
    public static final int TRACK_TYPE_VIDEO = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TrackChangeEvent() {
    }
}
