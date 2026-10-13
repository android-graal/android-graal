package android.icu.util;

/**
 * There are quite a few different conventions for binary datetime, depending on different
 * platforms and protocols. Some of these have severe drawbacks. For example, people using
 * Unix time (seconds since Jan 1, 1970, usually in a 32-bit integer)
 * think that they are safe until near the year 2038.
 * But cases can and do arise where arithmetic manipulations causes serious problems. Consider
 * the computation of the average of two datetimes, for example: if one calculates them with
 * <code>averageTime = (time1 + time2)/2</code>, there will be overflow even with dates
 * beginning in 2004. Moreover, even if these problems don't occur, there is the issue of
 * conversion back and forth between different systems.
 *
 * <p>Binary datetimes differ in a number of ways: the datatype, the unit,
 * and the epoch (origin). We refer to these as time scales.
 *
 * <p>ICU implements a universal time scale that is similar to the
 * .NET framework's System.DateTime. The universal time scale is a
 * 64-bit integer that holds ticks since midnight, January 1st, 0001.
 * (One tick is 100 nanoseconds.)
 * Negative values are supported. This has enough range to guarantee that
 * calculations involving dates around the present are safe.
 *
 * <p>The universal time scale always measures time according to the
 * proleptic Gregorian calendar. That is, the Gregorian calendar's
 * leap year rules are used for all times, even before 1582 when it was
 * introduced. (This is different from the default ICU calendar which
 * switches from the Julian to the Gregorian calendar in 1582.
 * See GregorianCalendar.setGregorianChange() and ucal_setGregorianChange().)
 *
 * ICU provides conversion functions to and from all other major time
 * scales, allowing datetimes in any time scale to be converted to the
 * universal time scale, safely manipulated, and converted back to any other
 * datetime time scale.
 *
 * <p>For more details and background, see the
 * <a href="https://unicode-org.github.io/icu/userguide/datetime/universaltimescale">Universal Time Scale</a>
 * chapter in the ICU User Guide.
 */
public final class UniversalTimeScale {
    /**
     * Used in DB2. Data is a <code>?unknown?</code>. Value
     * is days since December 31, 1899.
     */
    public static final int DB2_TIME = 8;

    /**
     * Used in the .NET framework's <code>System.DateTime</code> structure.
     * Data is a <code>long</code>. Value is ticks (1 tick == 100 nanoseconds) since January 1, 0001.
     */
    public static final int DOTNET_DATE_TIME = 4;

    /**
     * The constant used to select the epoch plus one value
     * for a time scale.
     *
     * NOTE: This is an internal value. DO NOT USE IT. May not
     * actually be equal to the epoch offset value plus one.
     *
     * @see #getTimeScaleValue
     */
    public static final int EPOCH_OFFSET_PLUS_1_VALUE = 6;

    /**
     * The constant used to select the epoch offset value
     * for a time scale.
     *
     * @see #getTimeScaleValue
     */
    public static final int EPOCH_OFFSET_VALUE = 1;

    /**
     * Used in Excel. Data is a <code>?unknown?</code>. Value
     * is days since December 31, 1899.
     */
    public static final int EXCEL_TIME = 7;

    /**
     * The constant used to select the maximum from value
     * for a time scale.
     *
     * @see #getTimeScaleValue
     */
    public static final int FROM_MAX_VALUE = 3;

    /**
     * The constant used to select the minimum from value
     * for a time scale.
     *
     * @see #getTimeScaleValue
     */
    public static final int FROM_MIN_VALUE = 2;

    /**
     * Used in the ICU4C. Data is a <code>double</code>. Value
     * is milliseconds since January 1, 1970.
     */
    public static final int ICU4C_TIME = 2;

    /**
     * Used in the JDK. Data is a <code>long</code>. Value
     * is milliseconds since January 1, 1970.
     */
    public static final int JAVA_TIME = 0;

    /**
     * Used in older Macintosh systems. Data is an <code>int</code>. Value
     * is seconds since January 1, 1904.
     */
    public static final int MAC_OLD_TIME = 5;

    /**
     * Used in the JDK. Data is a <code>double</code>. Value
     * is milliseconds since January 1, 2001.
     */
    public static final int MAC_TIME = 6;

    /**
     * This is the first unused time scale value.
     *
     * @deprecated ICU 59
     */
    @Deprecated
    public static final int MAX_SCALE = 10;

    /**
     * The constant used to select the maximum to value
     * for a time scale.
     *
     * @see #getTimeScaleValue
     */
    public static final int TO_MAX_VALUE = 5;

    /**
     * The constant used to select the minimum to value
     * for a time scale.
     *
     * @see #getTimeScaleValue
     */
    public static final int TO_MIN_VALUE = 4;

    /**
     * The constant used to select the units value
     * for a time scale.
     */
    public static final int UNITS_VALUE = 0;

    /**
     * Data is a <code>long</code>. Value is microseconds since January 1, 1970.
     * Similar to Unix time (linear value from 1970) and struct timeval
     * (microseconds resolution).
     */
    public static final int UNIX_MICROSECONDS_TIME = 9;

    /**
     * Used in Unix systems. Data is an <code>int</code> or a <code>long</code>. Value
     * is seconds since January 1, 1970.
     */
    public static final int UNIX_TIME = 1;

    /**
     * Used in Windows for file times. Data is a <code>long</code>. Value
     * is ticks (1 tick == 100 nanoseconds) since January 1, 1601.
     */
    public static final int WINDOWS_FILE_TIME = 3;

    private UniversalTimeScale() {
    }
}
