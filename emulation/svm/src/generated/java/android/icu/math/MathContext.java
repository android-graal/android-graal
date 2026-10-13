package android.icu.math;

/**
 * The <code>MathContext</code> immutable class encapsulates the
 * settings understood by the operator methods of the {@link BigDecimal}
 * class (and potentially other classes).  Operator methods are those
 * that effect an operation on a number or a pair of numbers.
 * <p>
 * The settings, which are not base-dependent, comprise:
 * <ol>
 * <li><code>digits</code>:
 * the number of digits (precision) to be used for an operation
 * <li><code>form</code>:
 * the form of any exponent that results from the operation
 * <li><code>lostDigits</code>:
 * whether checking for lost digits is enabled
 * <li><code>roundingMode</code>:
 * the algorithm to be used for rounding.
 * </ol>
 * <p>
 * When provided, a <code>MathContext</code> object supplies the
 * settings for an operation directly.
 * <p>
 * When <code>MathContext.DEFAULT</code> is provided for a
 * <code>MathContext</code> parameter then the default settings are used
 * (<code>9, SCIENTIFIC, false, ROUND_HALF_UP</code>).
 * <p>
 * In the <code>BigDecimal</code> class, all methods which accept a
 * <code>MathContext</code> object defaults) also have a version of the
 * method which does not accept a MathContext parameter.  These versions
 * carry out unlimited precision fixed point arithmetic (as though the
 * settings were (<code>0, PLAIN, false, ROUND_HALF_UP</code>).
 * <p>
 * The instance variables are shared with default access (so they are
 * directly accessible to the <code>BigDecimal</code> class), but must
 * never be changed.
 * <p>
 * The rounding mode constants have the same names and values as the
 * constants of the same name in <code>java.math.BigDecimal</code>, to
 * maintain compatibility with earlier versions of
 * <code>BigDecimal</code>.
 *
 * @see     BigDecimal
 * @author  Mike Cowlishaw
 */
public final class MathContext {
    /**
     * Standard floating point notation (with engineering exponential
     * format, where the power of ten is a multiple of 3).
     * Used as a setting to control the form of the result of a
     * <code>BigDecimal</code> operation.
     * A zero result in plain form may have a decimal part of one or
     * more zeros.
     *
     * @see #PLAIN
     * @see #SCIENTIFIC
     */
    public static final int ENGINEERING = 2;

    /**
     * Plain (fixed point) notation, without any exponent.
     * Used as a setting to control the form of the result of a
     * <code>BigDecimal</code> operation.
     * A zero result in plain form may have a decimal part of one or
     * more zeros.
     *
     * @see #ENGINEERING
     * @see #SCIENTIFIC
     */
    public static final int PLAIN = 0;

    /**
     * Rounding mode to round to a more positive number.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * If any of the discarded digits are non-zero then the result
     * should be rounded towards the next more positive digit.
     */
    public static final int ROUND_CEILING = 2;

    /**
     * Rounding mode to round towards zero.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * All discarded digits are ignored (truncated).  The result is
     * neither incremented nor decremented.
     */
    public static final int ROUND_DOWN = 1;

    /**
     * Rounding mode to round to a more negative number.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * If any of the discarded digits are non-zero then the result
     * should be rounded towards the next more negative digit.
     */
    public static final int ROUND_FLOOR = 3;

    /**
     * Rounding mode to round to nearest neighbor, where an equidistant
     * value is rounded down.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * If the discarded digits represent greater than half (0.5 times)
     * the value of a one in the next position then the result should be
     * rounded up (away from zero).  Otherwise the discarded digits are
     * ignored.
     */
    public static final int ROUND_HALF_DOWN = 5;

    /**
     * Rounding mode to round to nearest neighbor, where an equidistant
     * value is rounded to the nearest even neighbor.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * If the discarded digits represent greater than half (0.5 times)
     * the value of a one in the next position then the result should be
     * rounded up (away from zero).  If they represent less than half,
     * then the result should be rounded down.
     * <p>
     * Otherwise (they represent exactly half) the result is rounded
     * down if its rightmost digit is even, or rounded up if its
     * rightmost digit is odd (to make an even digit).
     */
    public static final int ROUND_HALF_EVEN = 6;

    /**
     * Rounding mode to round to nearest neighbor, where an equidistant
     * value is rounded up.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * If the discarded digits represent greater than or equal to half
     * (0.5 times) the value of a one in the next position then the result
     * should be rounded up (away from zero).  Otherwise the discarded
     * digits are ignored.
     */
    public static final int ROUND_HALF_UP = 4;

    /**
     * Rounding mode to assert that no rounding is necessary.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * Rounding (potential loss of information) is not permitted.
     * If any of the discarded digits are non-zero then an
     * <code>ArithmeticException</code> should be thrown.
     */
    public static final int ROUND_UNNECESSARY = 7;

    /**
     * Rounding mode to round away from zero.
     * Used as a setting to control the rounding mode used during a
     * <code>BigDecimal</code> operation.
     * <p>
     * If any of the discarded digits are non-zero then the result will
     * be rounded up (away from zero).
     */
    public static final int ROUND_UP = 0;

    /**
     * Standard floating point notation (with scientific exponential
     * format, where there is one digit before any decimal point).
     * Used as a setting to control the form of the result of a
     * <code>BigDecimal</code> operation.
     * A zero result in plain form may have a decimal part of one or
     * more zeros.
     *
     * @see #ENGINEERING
     * @see #PLAIN
     */
    public static final int SCIENTIFIC = 1;

    private MathContext() {
    }
}
