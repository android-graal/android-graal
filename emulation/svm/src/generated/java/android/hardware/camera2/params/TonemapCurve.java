package android.hardware.camera2.params;

/**
 * Immutable class for describing a {@code 2 x M x 3} tonemap curve of floats.
 *
 * <p>This defines red, green, and blue curves that the {@link CameraDevice} will
 * use as the tonemapping/contrast/gamma curve when {@link CaptureRequest#TONEMAP_MODE} is
 * set to {@link CameraMetadata#TONEMAP_MODE_CONTRAST_CURVE}.</p>
 *
 * <p>For a camera device with
 * {@link android.hardware.camera2.CameraCharacteristics#REQUEST_AVAILABLE_CAPABILITIES_MONOCHROME
 * MONOCHROME} capability, all 3 channels will contain the same set of control points.
 *
 * <p>The total number of points {@code (Pin, Pout)} for each color channel can be no more than
 * {@link CameraCharacteristics#TONEMAP_MAX_CURVE_POINTS}.</p>
 *
 * <p>The coordinate system for each point is within the inclusive range
 * [{@value #LEVEL_BLACK}, {@value #LEVEL_WHITE}].</p>
 *
 * @see CameraMetadata#TONEMAP_MODE_CONTRAST_CURVE
 * @see CameraCharacteristics#TONEMAP_MAX_CURVE_POINTS
 */
public final class TonemapCurve {
    /**
     * Index of the blue color channel curve.
     */
    public static final int CHANNEL_BLUE = 2;

    /**
     * Index of the green color channel curve.
     */
    public static final int CHANNEL_GREEN = 1;

    /**
     * Index of the red color channel curve.
     */
    public static final int CHANNEL_RED = 0;

    /**
     * Lower bound tonemap value corresponding to pure black for a single color channel.
     */
    public static final float LEVEL_BLACK = 0.0f;

    /**
     * Upper bound tonemap value corresponding to a pure white for a single color channel.
     */
    public static final float LEVEL_WHITE = 1.0f;

    /**
     * Number of elements in a {@code (Pin, Pout)} point;
     */
    public static final int POINT_SIZE = 2;

    private TonemapCurve() {
    }
}
