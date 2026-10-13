package android.graphics;

/**
 * Shader used to draw a bitmap as a texture. The bitmap can be repeated or
 * mirrored by setting the tiling mode.
 */
public final class BitmapShader {
    /**
     * This FilterMode value will respect the value of the Paint#isFilterBitmap flag while the
     * shader is attached to the Paint.
     *
     * <p>The exception to this rule is when a Shader is attached as input to a RuntimeShader. In
     *    that case this mode will default to FILTER_MODE_NEAREST.</p>
     *
     * @see #setFilterMode(int)
     */
    public static final int FILTER_MODE_DEFAULT = 0;

    /**
     * This FilterMode value will cause the shader to interpolate the output of the shader from a
     * 2x2 grid of pixels nearest to the sample point (i.e. bilinear interpolation).
     *
     * <p>This value will override the effect of Paint#isFilterBitmap.</p>
     *
     * @see #setFilterMode(int)
     */
    public static final int FILTER_MODE_LINEAR = 2;

    /**
     * This FilterMode value will cause the shader to sample from the nearest pixel to the requested
     * sample point.
     *
     * <p>This value will override the effect of Paint#isFilterBitmap.</p>
     *
     * @see #setFilterMode(int)
     */
    public static final int FILTER_MODE_NEAREST = 1;

    private BitmapShader() {
    }
}
