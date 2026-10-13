package android.media;

import android.os.Parcelable;

/**
 * This API is not generally intended for third party application developers.
 * Use the <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
 * <a href="{@docRoot}media/media3/session/control-playback">Media3 session
 * Library</a> for consistent behavior across all devices.
 * <p>
 * Represents an ongoing {@link MediaSession2} or a {@link MediaSession2Service}.
 * If it's representing a session service, it may not be ongoing.
 * <p>
 * This may be passed to apps by the session owner to allow them to create a
 * {@link MediaController2} to communicate with the session.
 * <p>
 * It can be also obtained by {@link android.media.session.MediaSessionManager}.
 */
public final class Session2Token implements Parcelable {
    /**
     * Type for {@link MediaSession2}.
     */
    public static final int TYPE_SESSION = 0;

    /**
     * Type for {@link MediaSession2Service}.
     */
    public static final int TYPE_SESSION_SERVICE = 1;

    private Session2Token() {
    }
}
