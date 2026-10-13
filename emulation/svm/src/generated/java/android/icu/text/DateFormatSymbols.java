package android.icu.text;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.text.DateFormatSymbols}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p><code>DateFormatSymbols</code> is a public class for encapsulating
 * localizable date-time formatting data, such as the names of the
 * months, the names of the days of the week, and the time zone data.
 * <code>DateFormat</code> and <code>SimpleDateFormat</code> both use
 * <code>DateFormatSymbols</code> to encapsulate this information.
 *
 * <p>Typically you shouldn't use <code>DateFormatSymbols</code> directly.
 * Rather, you are encouraged to create a date-time formatter with the
 * <code>DateFormat</code> class's factory methods: <code>getTimeInstance</code>,
 * <code>getDateInstance</code>, or <code>getDateTimeInstance</code>.
 * These methods automatically create a <code>DateFormatSymbols</code> for
 * the formatter so that you don't have to. After the
 * formatter is created, you may modify its format pattern using the
 * <code>setPattern</code> method. For more information about
 * creating formatters using <code>DateFormat</code>'s factory methods,
 * see {@link DateFormat}.
 *
 * <p>If you decide to create a date-time formatter with a specific
 * format pattern for a specific locale, you can do so with:
 * <blockquote>
 * <pre>
 * new SimpleDateFormat(aPattern, new DateFormatSymbols(aLocale)).
 * </pre>
 * </blockquote>
 *
 * <p><code>DateFormatSymbols</code> objects are clonable. When you obtain
 * a <code>DateFormatSymbols</code> object, feel free to modify the
 * date-time formatting data. For instance, you can replace the localized
 * date-time format pattern characters with the ones that you feel easy
 * to remember. Or you can change the representative cities
 * to your favorite ones.
 *
 * <p>New <code>DateFormatSymbols</code> subclasses may be added to support
 * <code>SimpleDateFormat</code> for date-time formatting for additional locales.
 *
 * @see          DateFormat
 * @see          SimpleDateFormat
 * @author       Chen-Lieh Huang
 */
public final class DateFormatSymbols {
    /**
     * <strong>[icu]</strong> Constant for width.
     */
    public static final int ABBREVIATED = 0;

    /**
     * <strong>[icu]</strong> Constant for context.
     */
    public static final int FORMAT = 0;

    /**
     * <strong>[icu]</strong> Constant for width.
     */
    public static final int NARROW = 2;

    /**
     * <strong>[icu]</strong> Constant for width; only supported for weekdays.
     */
    public static final int SHORT = 3;

    /**
     * <strong>[icu]</strong> Constant for context.
     */
    public static final int STANDALONE = 1;

    /**
     * <strong>[icu]</strong> Constant for width.
     */
    public static final int WIDE = 1;

    private DateFormatSymbols() {
    }
}
