package android.graphics;

/**
 * {@usesMathJax}
 *
 * <p>A {@link ColorSpace} is used to identify a specific organization of colors.
 * Each color space is characterized by a {@link Model color model} that defines
 * how a color value is represented (for instance the {@link Model#RGB RGB} color
 * model defines a color value as a triplet of numbers).</p>
 *
 * <p>Each component of a color must fall within a valid range, specific to each
 * color space, defined by {@link #getMinValue(int)} and {@link #getMaxValue(int)}
 * This range is commonly \([0..1]\). While it is recommended to use values in the
 * valid range, a color space always clamps input and output values when performing
 * operations such as converting to a different color space.</p>
 *
 * <h3>Using color spaces</h3>
 *
 * <p>This implementation provides a pre-defined set of common color spaces
 * described in the {@link Named} enum. To obtain an instance of one of the
 * pre-defined color spaces, simply invoke {@link #get(Named)}:</p>
 *
 * <pre class="prettyprint">
 * ColorSpace sRgb = ColorSpace.get(ColorSpace.Named.SRGB);
 * </pre>
 *
 * <p>The {@link #get(Named)} method always returns the same instance for a given
 * name. Color spaces with an {@link Model#RGB RGB} color model can be safely
 * cast to {@link Rgb}. Doing so gives you access to more APIs to query various
 * properties of RGB color models: color gamut primaries, transfer functions,
 * conversions to and from linear space, etc. Please refer to {@link Rgb} for
 * more information.</p>
 *
 * <p>The documentation of {@link Named} provides a detailed description of the
 * various characteristics of each available color space.</p>
 *
 * <h3>Color space conversions</h3>
 *
 * <p>To allow conversion between color spaces, this implementation uses the CIE
 * XYZ profile connection space (PCS). Color values can be converted to and from
 * this PCS using {@link #toXyz(float[])} and {@link #fromXyz(float[])}.</p>
 *
 * <p>For color space with a non-RGB color model, the white point of the PCS
 * <em>must be</em> the CIE standard illuminant D50. RGB color spaces use their
 * native white point (D65 for {@link Named#SRGB sRGB} for instance and must
 * undergo {@link Adaptation chromatic adaptation} as necessary.</p>
 *
 * <p>Since the white point of the PCS is not defined for RGB color space, it is
 * highly recommended to use the variants of the {@link #connect(ColorSpace, ColorSpace)}
 * method to perform conversions between color spaces. A color space can be
 * manually adapted to a specific white point using {@link #adapt(ColorSpace, float[])}.
 * Please refer to the documentation of {@link Rgb RGB color spaces} for more
 * information. Several common CIE standard illuminants are provided in this
 * class as reference (see {@link #ILLUMINANT_D65} or {@link #ILLUMINANT_D50}
 * for instance).</p>
 *
 * <p>Here is an example of how to convert from a color space to another:</p>
 *
 * <pre class="prettyprint">
 * // Convert from DCI-P3 to Rec.2020
 * ColorSpace.Connector connector = ColorSpace.connect(
 *         ColorSpace.get(ColorSpace.Named.DCI_P3),
 *         ColorSpace.get(ColorSpace.Named.BT2020));
 *
 * float[] bt2020 = connector.transform(p3r, p3g, p3b);
 * </pre>
 *
 * <p>You can easily convert to {@link Named#SRGB sRGB} by omitting the second
 * parameter:</p>
 *
 * <pre class="prettyprint">
 * // Convert from DCI-P3 to sRGB
 * ColorSpace.Connector connector = ColorSpace.connect(ColorSpace.get(ColorSpace.Named.DCI_P3));
 *
 * float[] sRGB = connector.transform(p3r, p3g, p3b);
 * </pre>
 *
 * <p>Conversions also work between color spaces with different color models:</p>
 *
 * <pre class="prettyprint">
 * // Convert from CIE L*a*b* (color model Lab) to Rec.709 (color model RGB)
 * ColorSpace.Connector connector = ColorSpace.connect(
 *         ColorSpace.get(ColorSpace.Named.CIE_LAB),
 *         ColorSpace.get(ColorSpace.Named.BT709));
 * </pre>
 *
 * <h3>Color spaces and multi-threading</h3>
 *
 * <p>Color spaces and other related classes ({@link Connector} for instance)
 * are immutable and stateless. They can be safely used from multiple concurrent
 * threads.</p>
 *
 * <p>Public static methods provided by this class, such as {@link #get(Named)}
 * and {@link #connect(ColorSpace, ColorSpace)}, are also guaranteed to be
 * thread-safe.</p>
 *
 * @see #get(Named)
 * @see Named
 * @see Model
 * @see Connector
 * @see Adaptation
 */
public final class ColorSpace {
    /**
     * The maximum ID value a color space can have.
     *
     * @see #getId()
     */
    public static final int MAX_ID = 63;

    /**
     * The minimum ID value a color space can have.
     *
     * @see #getId()
     */
    public static final int MIN_ID = -1;

    private ColorSpace() {
    }
}
