package android.media;

/**
 * This API is not generally intended for third party application developers.
 * Use the <a href="{@docRoot}jetpack/androidx.html">AndroidX</a>
 * <a href="{@docRoot}media/media3/session/control-playback">Media3 session
 * Library</a> for consistent behavior across all devices.
 * <p>
 * Service containing {@link MediaSession2}.
 */
public final class MediaSession2Service {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.media.MediaSession2Service";

    private MediaSession2Service() {
    }
}
