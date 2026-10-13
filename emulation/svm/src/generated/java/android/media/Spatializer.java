package android.media;

/**
 * Spatializer provides access to querying capabilities and behavior of sound spatialization
 * on the device.
 * Sound spatialization simulates sounds originating around the listener as if they were coming
 * from virtual speakers placed around the listener.<br>
 * Support for spatialization is optional, use {@link AudioManager#getSpatializer()} to obtain an
 * instance of this class if the feature is supported.
 */
public final class Spatializer {
    /**
     * Constant indicating the {@code Spatializer} on this device supports multichannel
     * spatialization.
     * @see #getImmersiveAudioLevel()
     */
    public static final int SPATIALIZER_IMMERSIVE_LEVEL_MULTICHANNEL = 1;

    /**
     * Constant indicating there are no spatialization capabilities supported on this device.
     * @see #getImmersiveAudioLevel()
     */
    public static final int SPATIALIZER_IMMERSIVE_LEVEL_NONE = 0;

    /**
     * Constant indicating the {@code Spatializer} on this device supports a spatialization
     * mode that differs from the ones available at this SDK level.
     * @see #getImmersiveAudioLevel()
     */
    public static final int SPATIALIZER_IMMERSIVE_LEVEL_OTHER = -1;

    private Spatializer() {
    }
}
