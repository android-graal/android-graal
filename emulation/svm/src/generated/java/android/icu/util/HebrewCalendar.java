package android.icu.util;

/**
 * <code>HebrewCalendar</code> is a subclass of <code>Calendar</code>
 * that that implements the traditional Hebrew calendar.
 * This is the civil calendar in Israel and the liturgical calendar
 * of the Jewish faith worldwide.
 * <p>
 * The Hebrew calendar is lunisolar and thus has a number of interesting
 * properties that distinguish it from the Gregorian.  Months start
 * on the day of (an arithmetic approximation of) each new moon.  Since the
 * solar year (approximately 365.24 days) is not an even multiple of
 * the lunar month (approximately 29.53 days) an extra "leap month" is
 * inserted in 7 out of every 19 years.  To make matters even more
 * interesting, the start of a year can be delayed by up to three days
 * in order to prevent certain holidays from falling on the Sabbath and
 * to prevent certain illegal year lengths.  Finally, the lengths of certain
 * months can vary depending on the number of days in the year.
 * <p>
 * The leap month is known as "Adar 1" and is inserted between the
 * months of Shevat and Adar in leap years.  Since the leap month does
 * not come at the end of the year, calculations involving
 * month numbers are particularly complex.  Users of this class should
 * make sure to use the {@link #roll roll} and {@link #add add} methods
 * rather than attempting to perform date arithmetic by manipulating
 * the fields directly.
 * <p>
 * <b>Note:</b> In the traditional Hebrew calendar, days start at sunset.
 * However, in order to keep the time fields in this class
 * synchronized with those of the other calendars and with local clock time,
 * we treat days and months as beginning at midnight,
 * roughly 6 hours after the corresponding sunset.
 * <p>
 * If you are interested in more information on the rules behind the Hebrew
 * calendar, see one of the following references:
 * <ul>
 * <li>"<a href="http://www.amazon.com/exec/obidos/ASIN/0521564743">Calendrical Calculations</a>",
 *      by Nachum Dershowitz &amp; Edward Reingold, Cambridge University Press, 1997, pages 85-91.
 *
 * <li>Hebrew Calendar Science and Myths,
 *      <a href="http://web.archive.org/web/20090423084613/http://www.geocities.com/Athens/1584/">
 *      http://web.archive.org/web/20090423084613/http://www.geocities.com/Athens/1584/</a>
 *
 * <li>The Calendar FAQ,
 *      <a href="http://www.faqs.org/faqs/calendars/faq/">
 *      http://www.faqs.org/faqs/calendars/faq/</a>
 * </ul>
 *
 * <p>
 * This class should not be subclassed.</p>
 * <p>
 * HebrewCalendar usually should be instantiated using
 * {@link android.icu.util.Calendar#getInstance(ULocale)} passing in a <code>ULocale</code>
 * with the tag <code>"@calendar=hebrew"</code>.</p>
 *
 * @see android.icu.util.GregorianCalendar
 * @see android.icu.util.Calendar
 *
 * @author Laura Werner
 * @author Alan Liu
 */
public final class HebrewCalendar {
    /**
     * Constant for the Adar, the 7th month of the Hebrew year.
     */
    public static final int ADAR = 6;

    /**
     * Constant for Adar I, the 6th month of the Hebrew year
     * (present in leap years only). In non-leap years, the calendar
     * jumps from Shevat (5th month) to Adar (7th month).
     */
    public static final int ADAR_1 = 5;

    /**
     * Constant for Av, the 12th month of the Hebrew year.
     */
    public static final int AV = 11;

    /**
     * Constant for Elul, the 13th month of the Hebrew year.
     */
    public static final int ELUL = 12;

    /**
     * Constant for Heshvan, the 2nd month of the Hebrew year.
     */
    public static final int HESHVAN = 1;

    /**
     * Constant for Iyar, the 9th month of the Hebrew year.
     */
    public static final int IYAR = 8;

    /**
     * Constant for Kislev, the 3rd month of the Hebrew year.
     */
    public static final int KISLEV = 2;

    /**
     * Constant for Nisan, the 8th month of the Hebrew year.
     */
    public static final int NISAN = 7;

    /**
     * Constant for Shevat, the 5th month of the Hebrew year.
     */
    public static final int SHEVAT = 4;

    /**
     * Constant for Sivan, the 10th month of the Hebrew year.
     */
    public static final int SIVAN = 9;

    /**
     * Constant for Tammuz, the 11th month of the Hebrew year.
     */
    public static final int TAMUZ = 10;

    /**
     * Constant for Tevet, the 4th month of the Hebrew year.
     */
    public static final int TEVET = 3;

    /**
     * Constant for Tishri, the 1st month of the Hebrew year.
     */
    public static final int TISHRI = 0;

    private HebrewCalendar() {
    }
}
