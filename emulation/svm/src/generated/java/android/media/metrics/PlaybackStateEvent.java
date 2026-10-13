package android.media.metrics;

import android.os.Parcelable;

/**
 * Playback state event.
 */
public final class PlaybackStateEvent implements Parcelable {
    /**
     * Playback is abandoned before reaching the end of the media.
     */
    public static final int STATE_ABANDONED = 15;

    /**
     * Playback is buffering to resume active playback.
     */
    public static final int STATE_BUFFERING = 6;

    /**
     * Playback has reached the end of the media.
     */
    public static final int STATE_ENDED = 11;

    /**
     * Playback is stopped due a fatal error and can be retried.
     */
    public static final int STATE_FAILED = 13;

    /**
     * Playback is interrupted by an ad.
     */
    public static final int STATE_INTERRUPTED_BY_AD = 14;

    /**
     * Playback is buffering in the background for initial playback start
     */
    public static final int STATE_JOINING_BACKGROUND = 1;

    /**
     * Playback is buffering in the foreground for initial playback start
     */
    public static final int STATE_JOINING_FOREGROUND = 2;

    /**
     * Playback has not started (initial state)
     */
    public static final int STATE_NOT_STARTED = 0;

    /**
     * Playback is paused but ready to play
     */
    public static final int STATE_PAUSED = 4;

    /**
     * Playback is buffering while paused.
     */
    public static final int STATE_PAUSED_BUFFERING = 7;

    /**
     * Playback is actively playing
     */
    public static final int STATE_PLAYING = 3;

    /**
     * Playback is handling a seek.
     */
    public static final int STATE_SEEKING = 5;

    /**
     * Playback is stopped and can be restarted.
     */
    public static final int STATE_STOPPED = 12;

    /**
     * Playback is suppressed (e.g. due to audio focus loss).
     */
    public static final int STATE_SUPPRESSED = 9;

    /**
     * Playback is suppressed (e.g. due to audio focus loss) while buffering to resume a playback.
     */
    public static final int STATE_SUPPRESSED_BUFFERING = 10;

    private PlaybackStateEvent() {
    }
}
