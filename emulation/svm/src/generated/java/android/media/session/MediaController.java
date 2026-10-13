package android.media.session;

import android.os.Parcelable;

/**
 * Allows an app to interact with an ongoing media session. Media buttons and
 * other commands can be sent to the session. A callback may be registered to
 * receive updates from the session, such as metadata and play state changes.
 * <p>
 * A MediaController can be created through {@link MediaSessionManager} if you
 * hold the "android.permission.MEDIA_CONTENT_CONTROL" permission or are an
 * enabled notification listener or by getting a {@link MediaSession.Token}
 * directly from the session owner.
 * <p>
 * MediaController objects are thread-safe.
 */
public final class MediaController {
    private MediaController() {
    }

    /**
     * Holds information about the current playback and how audio is handled for
     * this session.
     */
    public static final class PlaybackInfo implements Parcelable {
        /**
         * The session uses local playback.
         */
        public static final int PLAYBACK_TYPE_LOCAL = 1;

        /**
         * The session uses remote playback.
         */
        public static final int PLAYBACK_TYPE_REMOTE = 2;

        private PlaybackInfo() {
        }
    }
}
