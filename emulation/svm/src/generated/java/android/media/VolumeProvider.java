package android.media;

/**
 * Handles requests to adjust or set the volume on a session. This is also used
 * to push volume updates back to the session. The provider must call
 * {@link #setCurrentVolume(int)} each time the volume being provided changes.
 * <p>
 * You can set a volume provider on a session by calling
 * {@link MediaSession#setPlaybackToRemote}.
 */
public final class VolumeProvider {
    /**
     * The volume control uses an absolute value. It may be adjusted using
     * {@link #onAdjustVolume(int)} or set directly using
     * {@link #onSetVolumeTo(int)}.
     */
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;

    /**
     * The volume is fixed and can not be modified. Requests to change volume
     * should be ignored.
     */
    public static final int VOLUME_CONTROL_FIXED = 0;

    /**
     * The volume control uses relative adjustment via
     * {@link #onAdjustVolume(int)}. Attempts to set the volume to a specific
     * value should be ignored.
     */
    public static final int VOLUME_CONTROL_RELATIVE = 1;

    private VolumeProvider() {
    }
}
