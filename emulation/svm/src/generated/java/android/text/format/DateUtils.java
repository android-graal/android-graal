package android.text.format;

/**
 * This class contains various date-related utilities for creating text for things like
 * elapsed time and date ranges, strings for days of the week and months, and AM/PM text etc.
 */
public final class DateUtils {
    /**
     * This is not actually a useful month name in all locales.
     * @deprecated Use {@link java.text.SimpleDateFormat} instead.
     */
    @Deprecated
    public static final String ABBREV_MONTH_FORMAT = "%b";

    public static final String ABBREV_WEEKDAY_FORMAT = "%a";

    public static final long DAY_IN_MILLIS = 86400000L;

    @Deprecated
    public static final int FORMAT_12HOUR = 64;

    @Deprecated
    public static final int FORMAT_24HOUR = 128;

    public static final int FORMAT_ABBREV_ALL = 524288;

    public static final int FORMAT_ABBREV_MONTH = 65536;

    public static final int FORMAT_ABBREV_RELATIVE = 262144;

    public static final int FORMAT_ABBREV_TIME = 16384;

    public static final int FORMAT_ABBREV_WEEKDAY = 32768;

    @Deprecated
    public static final int FORMAT_CAP_AMPM = 256;

    @Deprecated
    public static final int FORMAT_CAP_MIDNIGHT = 4096;

    @Deprecated
    public static final int FORMAT_CAP_NOON = 1024;

    @Deprecated
    public static final int FORMAT_CAP_NOON_MIDNIGHT = 5120;

    public static final int FORMAT_NO_MIDNIGHT = 2048;

    public static final int FORMAT_NO_MONTH_DAY = 32;

    public static final int FORMAT_NO_NOON = 512;

    @Deprecated
    public static final int FORMAT_NO_NOON_MIDNIGHT = 2560;

    public static final int FORMAT_NO_YEAR = 8;

    public static final int FORMAT_NUMERIC_DATE = 131072;

    public static final int FORMAT_SHOW_DATE = 16;

    public static final int FORMAT_SHOW_TIME = 1;

    public static final int FORMAT_SHOW_WEEKDAY = 2;

    public static final int FORMAT_SHOW_YEAR = 4;

    /**
     * @deprecated Use
     * {@link #formatDateRange(Context, Formatter, long, long, int, String) formatDateRange}
     * and pass in {@link Time#TIMEZONE_UTC Time.TIMEZONE_UTC} for the timeZone instead.
     */
    @Deprecated
    public static final int FORMAT_UTC = 8192;

    public static final long HOUR_IN_MILLIS = 3600000L;

    /**
     * This is not actually the preferred 24-hour date format in all locales.
     * @deprecated Use {@link java.text.SimpleDateFormat} instead.
     */
    @Deprecated
    public static final String HOUR_MINUTE_24 = "%H:%M";

    /**
     * Request the full spelled-out name. For use with the 'abbrev' parameter of
     * {@link #getDayOfWeekString} and {@link #getMonthString}.
     *
     * @more <p>
     *       e.g. "Sunday" or "January"
     * @deprecated Use {@link java.text.SimpleDateFormat} instead.
     */
    @Deprecated
    public static final int LENGTH_LONG = 10;

    /**
     * Request an abbreviated version of the name. For use with the 'abbrev'
     * parameter of {@link #getDayOfWeekString} and {@link #getMonthString}.
     *
     * @more <p>
     *       e.g. "Sun" or "Jan"
     * @deprecated Use {@link java.text.SimpleDateFormat} instead.
     */
    @Deprecated
    public static final int LENGTH_MEDIUM = 20;

    /**
     * Request a shorter abbreviated version of the name.
     * For use with the 'abbrev' parameter of {@link #getDayOfWeekString} and {@link #getMonthString}.
     * @more
     * <p>e.g. "Su" or "Jan"
     * <p>In most languages, the results returned for LENGTH_SHORT will be the same as
     * the results returned for {@link #LENGTH_MEDIUM}.
     * @deprecated Use {@link java.text.SimpleDateFormat} instead.
     */
    @Deprecated
    public static final int LENGTH_SHORT = 30;

    /**
     * Request an even shorter abbreviated version of the name.
     * Do not use this.  Currently this will always return the same result
     * as {@link #LENGTH_SHORT}.
     * @deprecated Use {@link java.text.SimpleDateFormat} instead.
     */
    @Deprecated
    public static final int LENGTH_SHORTER = 40;

    /**
     * Request an even shorter abbreviated version of the name.
     * For use with the 'abbrev' parameter of {@link #getDayOfWeekString} and {@link #getMonthString}.
     * @more
     * <p>e.g. "S", "T", "T" or "J"
     * <p>In some languages, the results returned for LENGTH_SHORTEST will be the same as
     * the results returned for {@link #LENGTH_SHORT}.
     * @deprecated Use {@link java.text.SimpleDateFormat} instead.
     */
    @Deprecated
    public static final int LENGTH_SHORTEST = 50;

    public static final long MINUTE_IN_MILLIS = 60000L;

    public static final String MONTH_DAY_FORMAT = "%-d";

    public static final String MONTH_FORMAT = "%B";

    public static final String NUMERIC_MONTH_FORMAT = "%m";

    public static final long SECOND_IN_MILLIS = 1000L;

    public static final String WEEKDAY_FORMAT = "%A";

    public static final long WEEK_IN_MILLIS = 604800000L;

    public static final String YEAR_FORMAT = "%Y";

    public static final String YEAR_FORMAT_TWO_DIGITS = "%g";

    /**
     * @deprecated Not all years have the same number of days, and this constant is actually the
     * length of 364 days. Please use other date/time constructs such as
     * {@link java.util.concurrent.TimeUnit}, {@link java.util.Calendar} or
     * {@link java.time.Duration} instead.
     */
    @Deprecated
    public static final long YEAR_IN_MILLIS = 31449600000L;

    private DateUtils() {
    }
}
