package android.hardware.camera2.params;

/**
 * Immutable class for describing a {@code 4 x N x M} lens shading map of floats.
 *
 * @see CaptureResult#STATISTICS_LENS_SHADING_CORRECTION_MAP
 */
public final class LensShadingMap {
    /**
     * The smallest gain factor in this map.
     *
     * <p>All values in this map will be at least this large.</p>
     */
    public static final float MINIMUM_GAIN_FACTOR = 1.0f;

    private LensShadingMap() {
    }
}
