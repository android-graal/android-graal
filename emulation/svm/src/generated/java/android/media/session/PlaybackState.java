package android.media.session;

import android.os.Parcelable;

/**
 * Playback state for a {@link MediaSession}. This includes a state like
 * {@link PlaybackState#STATE_PLAYING}, the current playback position,
 * and the current control capabilities.
 */
public final class PlaybackState implements Parcelable {
    /**
     * Indicates this session supports the fast forward command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_FAST_FORWARD = 64L;

    /**
     * Indicates this session supports the pause command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PAUSE = 2L;

    /**
     * Indicates this session supports the play command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PLAY = 4L;

    /**
     * Indicates this session supports the play from media id command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;

    /**
     * Indicates this session supports the play from search command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PLAY_FROM_SEARCH = 2048L;

    /**
     * Indicates this session supports the play from URI command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PLAY_FROM_URI = 8192L;

    /**
     * Indicates this session supports the play/pause toggle command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PLAY_PAUSE = 512L;

    /**
     * Indicates this session supports the prepare command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PREPARE = 16384L;

    /**
     * Indicates this session supports the prepare from media id command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768L;

    /**
     * Indicates this session supports the prepare from search command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PREPARE_FROM_SEARCH = 65536L;

    /**
     * Indicates this session supports the prepare from URI command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_PREPARE_FROM_URI = 131072L;

    /**
     * Indicates this session supports the rewind command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_REWIND = 8L;

    /**
     * Indicates this session supports the seek to command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_SEEK_TO = 256L;

    /**
     * Indicates this session supports the set playback speed command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_SET_PLAYBACK_SPEED = 4194304L;

    /**
     * Indicates this session supports the set rating command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_SET_RATING = 128L;

    /**
     * Indicates this session supports the next command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_SKIP_TO_NEXT = 32L;

    /**
     * Indicates this session supports the previous command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_SKIP_TO_PREVIOUS = 16L;

    /**
     * Indicates this session supports the skip to queue item command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;

    /**
     * Indicates this session supports the stop command.
     *
     * @see Builder#setActions(long)
     */
    public static final long ACTION_STOP = 1L;

    /**
     * Use this value for the position to indicate the position is not known.
     */
    public static final long PLAYBACK_POSITION_UNKNOWN = -1L;

    /**
     * State indicating this item is currently buffering and will begin playing
     * when enough data has buffered.
     *
     * @see Builder#setState
     */
    public static final int STATE_BUFFERING = 6;

    /**
     * State indicating the class doing playback is currently connecting to a
     * new destination.  Depending on the implementation you may return to the previous
     * state when the connection finishes or enter {@link #STATE_NONE}.
     * If the connection failed {@link #STATE_ERROR} should be used.
     *
     * @see Builder#setState
     */
    public static final int STATE_CONNECTING = 8;

    /**
     * State indicating this item is currently in an error state. The error
     * message should also be set when entering this state.
     *
     * @see Builder#setState
     */
    public static final int STATE_ERROR = 7;

    /**
     * State indicating this item is currently fast forwarding.
     *
     * @see Builder#setState
     */
    public static final int STATE_FAST_FORWARDING = 4;

    /**
     * This is the default playback state and indicates that no media has been
     * added yet, or the performer has been reset and has no content to play.
     *
     * @see Builder#setState(int, long, float)
     * @see Builder#setState(int, long, float, long)
     */
    public static final int STATE_NONE = 0;

    /**
     * State indicating this item is currently paused.
     *
     * @see Builder#setState
     */
    public static final int STATE_PAUSED = 2;

    /**
     * State indicating this item is currently playing.
     *
     * @see Builder#setState
     */
    public static final int STATE_PLAYING = 3;

    /**
     * State indicating this item is currently rewinding.
     *
     * @see Builder#setState
     */
    public static final int STATE_REWINDING = 5;

    /**
     * State indicating the player is currently skipping to the next item.
     *
     * @see Builder#setState
     */
    public static final int STATE_SKIPPING_TO_NEXT = 10;

    /**
     * State indicating the player is currently skipping to the previous item.
     *
     * @see Builder#setState
     */
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;

    /**
     * State indicating the player is currently skipping to a specific item in
     * the queue.
     *
     * @see Builder#setState
     */
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;

    /**
     * State indicating this item is currently stopped.
     *
     * @see Builder#setState
     */
    public static final int STATE_STOPPED = 1;

    private PlaybackState() {
    }
}
