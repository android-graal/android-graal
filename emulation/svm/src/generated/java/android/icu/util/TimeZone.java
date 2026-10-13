package android.icu.util;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.util.TimeZone}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p><code>TimeZone</code> represents a time zone offset, and also computes daylight
 * savings.
 *
 * <p>Typically, you get a <code>TimeZone</code> using {@link #getDefault()}
 * which creates a <code>TimeZone</code> based on the time zone where the program
 * is running. For example, for a program running in Japan, <code>getDefault</code>
 * creates a <code>TimeZone</code> object based on Japanese Standard Time.
 *
 * <p>You can also get a <code>TimeZone</code> using {@link #getTimeZone(String)}
 * along with a time zone ID. For instance, the time zone ID for the
 * U.S. Pacific Time zone is "America/Los_Angeles". So, you can get a
 * U.S. Pacific Time <code>TimeZone</code> object with:
 *
 * <blockquote>
 * <pre>
 * TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
 * </pre>
 * </blockquote>
 * You can use the {@link #getAvailableIDs()} method to iterate through
 * all the supported time zone IDs, or getCanonicalID method to check
 * if a time zone ID is supported or not. You can then choose a
 * supported ID to get a <code>TimeZone</code>.
 * If the time zone you want is not represented by one of the
 * supported IDs, then you can create a custom time zone ID with
 * the following syntax:
 *
 * <blockquote>
 * <pre>
 * GMT[+|-]hh[[:]mm]
 * </pre>
 * </blockquote>
 *
 * For example, you might specify GMT+14:00 as a custom
 * time zone ID.  The <code>TimeZone</code> that is returned
 * when you specify a custom time zone ID uses the specified
 * offset from GMT(=UTC) and does not observe daylight saving
 * time. For example, you might specify GMT+14:00 as a custom
 * time zone ID to create a TimeZone representing 14 hours ahead
 * of GMT (with no daylight saving time). In addition,
 * <code>getCanonicalID</code> can also be used to
 * normalize a custom time zone ID.
 *
 * <p>For compatibility with JDK 1.1.x, some other three-letter time zone IDs
 * (such as "PST", "CTT", "AST") are also supported. However, <strong>their
 * use is deprecated</strong> because the same abbreviation is often used
 * for multiple time zones (for example, "CST" could be U.S. "Central Standard
 * Time" and "China Standard Time"), and the Java platform can then only
 * recognize one of them.
 *
 * @see          Calendar
 * @see          GregorianCalendar
 * @author       Mark Davis, Deborah Goldsmith, Chen-Lieh Huang, Alan Liu
 */
public final class TimeZone {
    /**
     * <strong>[icu]</strong> A style specifier for <code>getDisplayName()</code> indicating
     * a long name derived from the timezone's fallback name, such as
     * "United States (Los Angeles)."
     */
    public static final int GENERIC_LOCATION = 7;

    /**
     * A style specifier for <code>getDisplayName()</code> indicating
     * a long name, such as "Pacific Standard Time."
     * @see #SHORT
     */
    public static final int LONG = 1;

    /**
     * <strong>[icu]</strong> A style specifier for <code>getDisplayName()</code> indicating
     * a long generic name, such as "Pacific Time."
     * @see #SHORT_GENERIC
     */
    public static final int LONG_GENERIC = 3;

    /**
     * <strong>[icu]</strong> A style specifier for <code>getDisplayName()</code> indicating
     * a long name derived from the timezone's offset, such as "GMT-08:00."
     * @see #SHORT_GMT
     */
    public static final int LONG_GMT = 5;

    /**
     * A style specifier for <code>getDisplayName()</code> indicating
     * a short name, such as "PST."
     * @see #LONG
     */
    public static final int SHORT = 0;

    /**
     * <strong>[icu]</strong> A style specifier for <code>getDisplayName()</code> indicating
     * a short name derived from the timezone's short standard or daylight
     * timezone name ignoring commonlyUsed, such as "PDT."
     */
    public static final int SHORT_COMMONLY_USED = 6;

    /**
     * <strong>[icu]</strong> A style specifier for <code>getDisplayName()</code> indicating
     * a short generic name, such as "PT."
     * @see #LONG_GENERIC
     */
    public static final int SHORT_GENERIC = 2;

    /**
     * <strong>[icu]</strong> A style specifier for <code>getDisplayName()</code> indicating
     * a short name derived from the timezone's offset, such as "-0800."
     * @see #LONG_GMT
     */
    public static final int SHORT_GMT = 4;

    /**
     * <strong>[icu]</strong> A time zone implementation type indicating ICU's own TimeZone used by
     * <code>getTimeZone</code>.
     */
    public static final int TIMEZONE_ICU = 0;

    /**
     * <strong>[icu]</strong> A time zone implementation type indicating the {@link java.util.TimeZone}
     * used by <code>getTimeZone</code>.
     */
    public static final int TIMEZONE_JDK = 1;

    /**
     * <strong>[icu]</strong> The time zone ID reserved for unknown time zone.
     * @see #getTimeZone(String)
     */
    public static final String UNKNOWN_ZONE_ID = "Etc/Unknown";

    private TimeZone() {
    }
}
