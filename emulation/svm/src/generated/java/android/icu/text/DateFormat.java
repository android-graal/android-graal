package android.icu.text;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.text.DateFormat}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p>
 * DateFormat is an abstract class for date/time formatting subclasses which formats and parses dates or time in a
 * language-independent manner. The date/time formatting subclass, such as SimpleDateFormat, allows for formatting
 * (i.e., date -&gt; text), parsing (text -&gt; date), and normalization. The date is represented as a <code>Date</code>
 * object or as the milliseconds since January 1, 1970, 00:00:00 GMT.
 *
 * <p>
 * DateFormat helps you to format and parse dates for any locale. Your code can be completely independent of the locale
 * conventions for months, days of the week, or even the calendar format: lunar vs. solar. It provides many class
 * methods for obtaining default date/time formatters based on the default for a given locale and a number of formatting
 * styles or arbitrary "skeletons".
 * <ol>
 * <li>The formatting styles include FULL, LONG, MEDIUM, and SHORT. More detail and examples of using these styles are
 * provided in the method descriptions.
 * <li>The formatting styles only cover a fraction of the necessary usage. You often need to have just certain
 * combinations of fields, like Month and Year, but have it to be formatted appropriate to a given locale. This is done
 * using the (misnamed) getPatternInstance() method, supplying a skeleton. There are a number of constants that have
 * common pre-defined skeletons, such as {@link #MINUTE_SECOND} for something like "13:45" or {@link #YEAR_ABBR_MONTH}
 * for something like "Sept 2012".
 * </ol>
 *
 * <p>
 * To format a date for the current Locale, use one of the static factory methods:
 *
 * <pre>
 * myString = DateFormat.getDateInstance().format(myDate);
 * myString = DateFormat.getPatternInstance(DateFormat.YEAR_ABBR_MONTH).format(myDate);
 * </pre>
 * <p>
 * If you are formatting multiple numbers, it is more efficient to get the format and use it multiple times so that the
 * system doesn't have to fetch the information about the local language and country conventions multiple times.
 *
 * <pre>
 * DateFormat df = DateFormat.getDateInstance();
 * for (int i = 0; i &lt; a.length; ++i) {
 *     output.println(df.format(myDate[i]) + &quot;; &quot;);
 * }
 * </pre>
 * <p>
 * To format a date for a different Locale, specify it in the call to getDateInstance().
 *
 * <pre>
 * DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
 * </pre>
 * <p>
 * You can use a DateFormat to parse also.
 *
 * <pre>
 * myDate = df.parse(myString);
 * </pre>
 * <p>
 * There are many static factory methods available. Use getDateInstance to get the normal date format for that country.
 * Use getTimeInstance to get the time format for that country. Use getDateTimeInstance to get a date and time format.
 * You can pass in different options to these factory methods to control the length of the result; from SHORT to MEDIUM
 * to LONG to FULL. The exact result depends on the locale, but generally:
 * <ul>
 * <li>SHORT is completely numeric, such as 12.13.52 or 3:30pm
 * <li>MEDIUM is longer, such as Jan 12, 1952
 * <li>LONG is longer, such as January 12, 1952 or 3:30:32pm
 * <li>FULL is pretty completely specified, such as Tuesday, April 12, 1952 AD or 3:30:42pm PST.
 * </ul>
 *
 * <p>
 * Use getPatternInstance to format with a skeleton. Typically this is with a predefined skeleton, like
 * {@link #YEAR_ABBR_MONTH} for something like "Sept 2012". If you don't want to use one of the predefined skeletons,
 * you can supply your own. The skeletons are like the patterns in SimpleDateFormat, except they:
 * <ol>
 * <li>only keep the field pattern letter and ignore all other parts in a pattern, such as space, punctuation, and
 * string literals.
 * <li>are independent of the order of fields.
 * <li>ignore certain differences in the field's pattern letter length:
 * <ol>
 * <li>For those non-digit calendar fields, the pattern letter length is important, such as MMM, MMMM, and MMMMM; E and
 * EEEE, and the field's pattern letter length is honored.
 * <li>For the digit calendar fields, such as M or MM, d or dd, yy or yyyy, the field pattern length is ignored and the
 * best match, which is defined in date time patterns, will be returned without honor the field pattern letter length in
 * skeleton.
 * </ol>
 * </ol>
 *
 * <p>
 * You can also set the time zone on the format if you wish. If you want even more control over the format or parsing,
 * (or want to give your users more control), you can try casting the DateFormat you get from the factory methods to a
 * SimpleDateFormat. This will work for the majority of countries; just remember to put it in a try block in case you
 * encounter an unusual one.
 *
 * <p>
 * You can also use forms of the parse and format methods with ParsePosition and FieldPosition to allow you to
 * <ul>
 * <li>progressively parse through pieces of a string.
 * <li>align any particular field, or find out where it is for selection on the screen.
 * </ul>
 *
 * <h3>Synchronization</h3>
 *
 * Date formats are not synchronized. It is recommended to create separate format instances for each thread. If multiple
 * threads access a format concurrently, it must be synchronized externally.
 *
 * @see UFormat
 * @see NumberFormat
 * @see SimpleDateFormat
 * @see android.icu.util.Calendar
 * @see android.icu.util.GregorianCalendar
 * @see android.icu.util.TimeZone
 * @author Mark Davis, Chen-Lieh Huang, Alan Liu
 */
public final class DateFormat {
    /**
     * <strong>[icu]</strong> Constant for <i>generic non-location format</i>, abbreviated if possible, such as PT;
     * used in combinations date + time + zone, or time + zone.
     * @see <a href="http://unicode.org/reports/tr35/#Date_Format_Patterns">LDML Date Format Patterns</a>
     * @see <a href="http://unicode.org/reports/tr35/#Time_Zone_Fallback">LDML Time Zone Fallback</a>
     */
    public static final String ABBR_GENERIC_TZ = "v";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with abbreviated month.
     */
    public static final String ABBR_MONTH = "MMM";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with abbreviated month and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String ABBR_MONTH_DAY = "MMMd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with abbreviated month, weekday, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String ABBR_MONTH_WEEKDAY_DAY = "MMMEd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with abbreviated quarter.
     */
    public static final String ABBR_QUARTER = "QQQ";

    /**
     * <strong>[icu]</strong> Constant for <i>specific non-location format</i>, abbreviated if possible, such as PDT;
     * used in combinations date + time + zone, or time + zone.
     * @see <a href="http://unicode.org/reports/tr35/#Date_Format_Patterns">LDML Date Format Patterns</a>
     * @see <a href="http://unicode.org/reports/tr35/#Time_Zone_Fallback">LDML Time Zone Fallback</a>
     */
    public static final String ABBR_SPECIFIC_TZ = "z";

    /**
     * <strong>[icu]</strong> Constant for <i>localized GMT/UTC format</i>, such as GMT+8:00 or HPG-8:00;
     * used in combinations date + time + zone, or time + zone.
     * @see <a href="http://unicode.org/reports/tr35/#Date_Format_Patterns">LDML Date Format Patterns</a>
     * @see <a href="http://unicode.org/reports/tr35/#Time_Zone_Fallback">LDML Time Zone Fallback</a>
     */
    public static final String ABBR_UTC_TZ = "ZZZZ";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with abbreviated weekday.
     */
    public static final String ABBR_WEEKDAY = "E";

    /**
     * FieldPosition selector for 'a' field alignment,
     * corresponding to the {@link Calendar#AM_PM} field.
     */
    public static final int AM_PM_FIELD = 14;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'b' field alignment.
     * No related Calendar field.
     * This displays the fixed day period (am/pm/midnight/noon).
     */
    public static final int AM_PM_MIDNIGHT_NOON_FIELD = 35;

    /**
     * FieldPosition selector for 'd' field alignment,
     * corresponding to the {@link Calendar#DATE} field.
     */
    public static final int DATE_FIELD = 3;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with day.
     */
    public static final String DAY = "d";

    /**
     * FieldPosition selector for 'E' field alignment,
     * corresponding to the {@link Calendar#DAY_OF_WEEK} field.
     */
    public static final int DAY_OF_WEEK_FIELD = 9;

    /**
     * FieldPosition selector for 'F' field alignment,
     * corresponding to the {@link Calendar#DAY_OF_WEEK_IN_MONTH} field.
     */
    public static final int DAY_OF_WEEK_IN_MONTH_FIELD = 11;

    /**
     * FieldPosition selector for 'D' field alignment,
     * corresponding to the {@link Calendar#DAY_OF_YEAR} field.
     */
    public static final int DAY_OF_YEAR_FIELD = 10;

    /**
     * Constant for default style pattern.  Its value is MEDIUM.
     */
    public static final int DEFAULT = 2;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'e' field alignment,
     * corresponding to the {@link Calendar#DOW_LOCAL} field.
     */
    public static final int DOW_LOCAL_FIELD = 19;

    /**
     * FieldPosition selector for 'G' field alignment,
     * corresponding to the {@link Calendar#ERA} field.
     */
    public static final int ERA_FIELD = 0;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'u' field alignment,
     * corresponding to the {@link Calendar#EXTENDED_YEAR} field.
     */
    public static final int EXTENDED_YEAR_FIELD = 20;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'B' field alignment.
     * No related Calendar field.
     * This displays the flexible day period.
     */
    public static final int FLEXIBLE_DAY_PERIOD_FIELD = 36;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'S' field alignment,
     * corresponding to the {@link Calendar#MILLISECOND} field.
     *
     * Note: Time formats that use 'S' can display a maximum of three
     * significant digits for fractional seconds, corresponding to millisecond
     * resolution and a fractional seconds sub-pattern of SSS. If the
     * sub-pattern is S or SS, the fractional seconds value will be truncated
     * (not rounded) to the number of display places specified. If the
     * fractional seconds sub-pattern is longer than SSS, the additional
     * display places will be filled with zeros.
     */
    public static final int FRACTIONAL_SECOND_FIELD = 8;

    /**
     * Constant for full style pattern.
     */
    public static final int FULL = 0;

    /**
     * <strong>[icu]</strong> Constant for <i>generic non-location format</i>, such as Pacific Time;
     * used in combinations date + time + zone, or time + zone.
     * @see <a href="http://unicode.org/reports/tr35/#Date_Format_Patterns">LDML Date Format Patterns</a>
     * @see <a href="http://unicode.org/reports/tr35/#Time_Zone_Fallback">LDML Time Zone Fallback</a>
     */
    public static final String GENERIC_TZ = "vvvv";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with hour, with the locale's preferred hour format (12 or 24).
     */
    public static final String HOUR = "j";

    /**
     * FieldPosition selector for 'K' field alignment,
     * corresponding to the {@link Calendar#HOUR} field.
     * HOUR0_FIELD is used for the zero-based 12-hour clock.
     * For example, 11:30 PM + 1 hour results in 00:30 AM.
     */
    public static final int HOUR0_FIELD = 16;

    /**
     * FieldPosition selector for 'h' field alignment,
     * corresponding to the {@link Calendar#HOUR} field.
     * HOUR1_FIELD is used for the one-based 12-hour clock.
     * For example, 11:30 PM + 1 hour results in 12:30 AM.
     */
    public static final int HOUR1_FIELD = 15;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with hour in 24-hour presentation.
     */
    public static final String HOUR24 = "H";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with hour and minute in 24-hour presentation.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String HOUR24_MINUTE = "Hm";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with hour, minute, and second in
     * 24-hour presentation.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String HOUR24_MINUTE_SECOND = "Hms";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with hour and minute, with the locale's preferred hour format (12 or 24).
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String HOUR_MINUTE = "jm";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with hour, minute, and second,
     * with the locale's preferred hour format (12 or 24).
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String HOUR_MINUTE_SECOND = "jms";

    /**
     * FieldPosition selector for 'H' field alignment,
     * corresponding to the {@link Calendar#HOUR_OF_DAY} field.
     * HOUR_OF_DAY0_FIELD is used for the zero-based 24-hour clock.
     * For example, 23:59 + 01:00 results in 00:59.
     */
    public static final int HOUR_OF_DAY0_FIELD = 5;

    /**
     * FieldPosition selector for 'k' field alignment,
     * corresponding to the {@link Calendar#HOUR_OF_DAY} field.
     * HOUR_OF_DAY1_FIELD is used for the one-based 24-hour clock.
     * For example, 23:59 + 01:00 results in 24:59.
     */
    public static final int HOUR_OF_DAY1_FIELD = 4;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'g' field alignment,
     * corresponding to the {@link Calendar#JULIAN_DAY} field.
     */
    public static final int JULIAN_DAY_FIELD = 21;

    /**
     * <strong>[icu]</strong> Constant for <i>generic location format</i>, such as Los Angeles Time;
     * used in combinations date + time + zone, or time + zone.
     * @see <a href="http://unicode.org/reports/tr35/#Date_Format_Patterns">LDML Date Format Patterns</a>
     * @see <a href="http://unicode.org/reports/tr35/#Time_Zone_Fallback">LDML Time Zone Fallback</a>
     */
    public static final String LOCATION_TZ = "VVVV";

    /**
     * Constant for long style pattern.
     */
    public static final int LONG = 1;

    /**
     * Constant for medium style pattern.
     */
    public static final int MEDIUM = 2;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'A' field alignment,
     * corresponding to the {@link Calendar#MILLISECONDS_IN_DAY} field.
     */
    public static final int MILLISECONDS_IN_DAY_FIELD = 22;

    /**
     * Alias for FRACTIONAL_SECOND_FIELD.
     */
    public static final int MILLISECOND_FIELD = 8;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with minute.
     */
    public static final String MINUTE = "m";

    /**
     * FieldPosition selector for 'm' field alignment,
     * corresponding to the {@link Calendar#MINUTE} field.
     */
    public static final int MINUTE_FIELD = 6;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with minute and second.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String MINUTE_SECOND = "ms";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with month.
     */
    public static final String MONTH = "MMMM";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with long month and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String MONTH_DAY = "MMMMd";

    /**
     * FieldPosition selector for 'M' field alignment,
     * corresponding to the {@link Calendar#MONTH} field.
     */
    public static final int MONTH_FIELD = 2;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with month, weekday, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String MONTH_WEEKDAY_DAY = "MMMMEEEEd";

    /**
     * <strong>[icu]</strong> Constant for empty style pattern.
     */
    public static final int NONE = -1;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with numeric month.
     */
    public static final String NUM_MONTH = "M";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with numeric month and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String NUM_MONTH_DAY = "Md";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with numeric month, weekday, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String NUM_MONTH_WEEKDAY_DAY = "MEd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with quarter.
     */
    public static final String QUARTER = "QQQQ";

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'Q' field alignment,
     * corresponding to the {@link Calendar#MONTH} field.
     * This displays the quarter.
     */
    public static final int QUARTER_FIELD = 27;

    /**
     * <strong>[icu]</strong> Constant for relative style mask.
     */
    public static final int RELATIVE = 128;

    /**
     * <strong>[icu]</strong> Constant for relative default style pattern.
     */
    public static final int RELATIVE_DEFAULT = 130;

    /**
     * <strong>[icu]</strong> Constant for relative full style pattern.
     */
    public static final int RELATIVE_FULL = 128;

    /**
     * <strong>[icu]</strong> Constant for relative style pattern.
     */
    public static final int RELATIVE_LONG = 129;

    /**
     * <strong>[icu]</strong> Constant for relative style pattern.
     */
    public static final int RELATIVE_MEDIUM = 130;

    /**
     * <strong>[icu]</strong> Constant for relative style pattern.
     */
    public static final int RELATIVE_SHORT = 131;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with second.
     */
    public static final String SECOND = "s";

    /**
     * FieldPosition selector for 's' field alignment,
     * corresponding to the {@link Calendar#SECOND} field.
     */
    public static final int SECOND_FIELD = 7;

    /**
     * Constant for short style pattern.
     */
    public static final int SHORT = 3;

    /**
     * <strong>[icu]</strong> Constant for <i>specific non-location format</i>, such as Pacific Daylight Time;
     * used in combinations date + time + zone, or time + zone.
     * @see <a href="http://unicode.org/reports/tr35/#Date_Format_Patterns">LDML Date Format Patterns</a>
     * @see <a href="http://unicode.org/reports/tr35/#Time_Zone_Fallback">LDML Time Zone Fallback</a>
     */
    public static final String SPECIFIC_TZ = "zzzz";

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'c' field alignment,
     * corresponding to the {@link Calendar#DAY_OF_WEEK} field.
     * This displays the stand alone day name, if available.
     */
    public static final int STANDALONE_DAY_FIELD = 25;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'L' field alignment,
     * corresponding to the {@link Calendar#MONTH} field.
     * This displays the stand alone month name, if available.
     */
    public static final int STANDALONE_MONTH_FIELD = 26;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'q' field alignment,
     * corresponding to the {@link Calendar#MONTH} field.
     * This displays the stand alone quarter, if available.
     */
    public static final int STANDALONE_QUARTER_FIELD = 28;

    /**
     * FieldPosition selector for 'z' field alignment,
     * corresponding to the {@link Calendar#ZONE_OFFSET} and
     * {@link Calendar#DST_OFFSET} fields.
     */
    public static final int TIMEZONE_FIELD = 17;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'v' field alignment,
     * corresponding to the {@link Calendar#ZONE_OFFSET} and
     * {@link Calendar#DST_OFFSET} fields.  This displays the generic zone
     * name, if available.
     */
    public static final int TIMEZONE_GENERIC_FIELD = 24;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'X' field alignment,
     * corresponding to the {@link Calendar#ZONE_OFFSET} and
     * {@link Calendar#DST_OFFSET} fields.  This displays the
     * ISO 8601 local time offset format or UTC indicator ("Z").
     */
    public static final int TIMEZONE_ISO_FIELD = 32;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'x' field alignment,
     * corresponding to the {@link Calendar#ZONE_OFFSET} and
     * {@link Calendar#DST_OFFSET} fields.  This displays the
     * ISO 8601 local time offset format.
     */
    public static final int TIMEZONE_ISO_LOCAL_FIELD = 33;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'O' field alignment,
     * corresponding to the {@link Calendar#ZONE_OFFSET} and
     * {@link Calendar#DST_OFFSET} fields.  This displays the
     * localized GMT format.
     */
    public static final int TIMEZONE_LOCALIZED_GMT_OFFSET_FIELD = 31;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'Z' field alignment,
     * corresponding to the {@link Calendar#ZONE_OFFSET} and
     * {@link Calendar#DST_OFFSET} fields.
     */
    public static final int TIMEZONE_RFC_FIELD = 23;

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'V' field alignment,
     * corresponding to the {@link Calendar#ZONE_OFFSET} and
     * {@link Calendar#DST_OFFSET} fields.  This displays the fallback timezone
     * name when VVVV is specified, and the short standard or daylight
     * timezone name ignoring commonlyUsed when a single V is specified.
     */
    public static final int TIMEZONE_SPECIAL_FIELD = 29;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with weekday.
     */
    public static final String WEEKDAY = "EEEE";

    /**
     * FieldPosition selector for 'W' field alignment,
     * corresponding to the {@link Calendar#WEEK_OF_MONTH} field.
     */
    public static final int WEEK_OF_MONTH_FIELD = 13;

    /**
     * FieldPosition selector for 'w' field alignment,
     * corresponding to the {@link Calendar#WEEK_OF_YEAR} field.
     */
    public static final int WEEK_OF_YEAR_FIELD = 12;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year.
     */
    public static final String YEAR = "y";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year and abbreviated month.
     */
    public static final String YEAR_ABBR_MONTH = "yMMM";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year, abbreviated month, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String YEAR_ABBR_MONTH_DAY = "yMMMd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year, abbreviated month, weekday, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String YEAR_ABBR_MONTH_WEEKDAY_DAY = "yMMMEd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year and abbreviated quarter.
     */
    public static final String YEAR_ABBR_QUARTER = "yQQQ";

    /**
     * FieldPosition selector for 'y' field alignment,
     * corresponding to the {@link Calendar#YEAR} field.
     */
    public static final int YEAR_FIELD = 1;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year and month.
     */
    public static final String YEAR_MONTH = "yMMMM";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year, month, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String YEAR_MONTH_DAY = "yMMMMd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year, month, weekday, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String YEAR_MONTH_WEEKDAY_DAY = "yMMMMEEEEd";

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'U' field alignment,
     * corresponding to the {@link Calendar#YEAR} field.
     * This displays the cyclic year name, if available.
     */
    public static final int YEAR_NAME_FIELD = 30;

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year and numeric month.
     */
    public static final String YEAR_NUM_MONTH = "yM";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year, numeric month, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String YEAR_NUM_MONTH_DAY = "yMd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year, numeric month, weekday, and day.
     * Used in combinations date + time, date + time + zone, or time + zone.
     */
    public static final String YEAR_NUM_MONTH_WEEKDAY_DAY = "yMEd";

    /**
     * <strong>[icu]</strong> Constant for date skeleton with year and quarter.
     */
    public static final String YEAR_QUARTER = "yQQQQ";

    /**
     * <strong>[icu]</strong> FieldPosition selector for 'Y' field alignment,
     * corresponding to the {@link Calendar#YEAR_WOY} field.
     */
    public static final int YEAR_WOY_FIELD = 18;

    private DateFormat() {
    }
}
