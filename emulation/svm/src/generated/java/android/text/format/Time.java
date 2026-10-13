package android.text.format;

/**
 * An alternative to the {@link java.util.Calendar} and
 * {@link java.util.GregorianCalendar} classes. An instance of the Time class represents
 * a moment in time, specified with second precision. It is modelled after
 * struct tm. This class is not thread-safe and does not consider leap seconds.
 *
 * <p>This class has a number of issues and it is recommended that
 * {@link java.util.GregorianCalendar} is used instead.
 *
 * <p>Known issues:
 * <ul>
 *     <li>For historical reasons when performing time calculations all arithmetic currently takes
 *     place using 32-bit integers. This limits the reliable time range representable from 1902
 *     until 2037.See the wikipedia article on the
 *     <a href="http://en.wikipedia.org/wiki/Year_2038_problem">Year 2038 problem</a> for details.
 *     Do not rely on this behavior; it may change in the future.
 *     </li>
 *     <li>Calling {@link #switchTimezone(String)} on a date that cannot exist, such as a wall time
 *     that was skipped due to a DST transition, will result in a date in 1969 (i.e. -1, or 1 second
 *     before 1st Jan 1970 UTC).</li>
 *     <li>Much of the formatting / parsing assumes ASCII text and is therefore not suitable for
 *     use with non-ASCII scripts.</li>
 *     <li>No support for pseudo-zones like "GMT-07:00".</li>
 * </ul>
 *
 * @deprecated Use {@link java.util.GregorianCalendar} instead.
 */
@Deprecated
public final class Time {
    /**
     * The Julian day of the epoch, that is, January 1, 1970 on the Gregorian
     * calendar.
     */
    @Deprecated
    public static final int EPOCH_JULIAN_DAY = 2440588;

    @Deprecated
    public static final int FRIDAY = 5;

    @Deprecated
    public static final int HOUR = 3;

    @Deprecated
    public static final int MINUTE = 2;

    @Deprecated
    public static final int MONDAY = 1;

    /**
     * The Julian day of the Monday in the week of the epoch, December 29, 1969
     * on the Gregorian calendar.
     */
    @Deprecated
    public static final int MONDAY_BEFORE_JULIAN_EPOCH = 2440585;

    @Deprecated
    public static final int MONTH = 5;

    @Deprecated
    public static final int MONTH_DAY = 4;

    @Deprecated
    public static final int SATURDAY = 6;

    @Deprecated
    public static final int SECOND = 1;

    @Deprecated
    public static final int SUNDAY = 0;

    @Deprecated
    public static final int THURSDAY = 4;

    @Deprecated
    public static final String TIMEZONE_UTC = "UTC";

    @Deprecated
    public static final int TUESDAY = 2;

    @Deprecated
    public static final int WEDNESDAY = 3;

    @Deprecated
    public static final int WEEK_DAY = 7;

    @Deprecated
    public static final int WEEK_NUM = 9;

    @Deprecated
    public static final int YEAR = 6;

    @Deprecated
    public static final int YEAR_DAY = 8;

    private Time() {
    }
}
