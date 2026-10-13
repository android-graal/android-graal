package android.media;

import android.os.Parcelable;

/**
 * The MediaSyncEvent class defines events that can be used to synchronize playback or capture
 * actions between different players and recorders.
 * <p>For instance, {@link AudioRecord#startRecording(MediaSyncEvent)} is used to start capture
 * only when the playback on a particular audio session is complete.
 * The audio session ID is retrieved from a player (e.g {@link MediaPlayer}, {@link AudioTrack} or
 * {@link ToneGenerator}) by use of the getAudioSessionId() method.
 */
public final class MediaSyncEvent implements Parcelable {
    /**
     * No sync event specified. When used with a synchronized playback or capture method, the
     * behavior is equivalent to calling the corresponding non synchronized method.
     */
    public static final int SYNC_EVENT_NONE = 0;

    /**
     * The corresponding action is triggered only when the presentation is completed
     * (meaning the media has been presented to the user) on the specified session.
     * A synchronization of this type requires a source audio session ID to be set via
     * {@link #setAudioSessionId(int)} method.
     */
    public static final int SYNC_EVENT_PRESENTATION_COMPLETE = 1;

    private MediaSyncEvent() {
    }
}
