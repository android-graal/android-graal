package android.icu.util;

/**
 * <code>IslamicCalendar</code> is a subclass of <code>Calendar</code>
 * that that implements the Islamic civil and religious calendars.  It
 * is used as the civil calendar in most of the Arab world and the
 * liturgical calendar of the Islamic faith worldwide.  This calendar
 * is also known as the "Hijri" calendar, since it starts at the time
 * of Mohammed's emigration (or "hijra") to Medinah on Thursday,
 * July 15, 622 AD (Julian).
 * <p>
 * The Islamic calendar is strictly lunar, and thus an Islamic year of twelve
 * lunar months does not correspond to the solar year used by most other
 * calendar systems, including the Gregorian.  An Islamic year is, on average,
 * about 354 days long, so each successive Islamic year starts about 11 days
 * earlier in the corresponding Gregorian year.
 * <p>
 * Each month of the calendar starts when the new moon's crescent is visible
 * at sunset.  However, in order to keep the time fields in this class
 * synchronized with those of the other calendars and with local clock time,
 * we treat days and months as beginning at midnight,
 * roughly 6 hours after the corresponding sunset.
 * <p>
 * There are three main variants of the Islamic calendar in existence.  The first
 * is the <em>civil</em> calendar, which uses a fixed cycle of alternating 29-
 * and 30-day months, with a leap day added to the last month of 11 out of
 * every 30 years.  This calendar is easily calculated and thus predictable in
 * advance, so it is used as the civil calendar in a number of Arab countries.
 * This is the default behavior of a newly-created <code>IslamicCalendar</code>
 * object.
 * <p>
 * The Islamic <em>religious</em> calendar and Saudi Arabia's <em>Umm al-Qura</em>
 * calendar, however, are based on the <em>observation</em> of the crescent moon.
 * It is thus affected by the position at which the
 * observations are made, seasonal variations in the time of sunset, the
 * eccentricities of the moon's orbit, and even the weather at the observation
 * site.  This makes it impossible to calculate in advance, and it causes the
 * start of a month in the religious calendar to differ from the civil calendar
 * by up to three days.
 * <p>
 * Using astronomical calculations for the position of the sun and moon, the
 * moon's illumination, and other factors, it is possible to determine the start
 * of a lunar month with a fairly high degree of certainty.  However, these
 * calculations are extremely complicated and thus slow, so most algorithms,
 * including the one used here, are only approximations of the true astronomical
 * calculations.  At present, the approximations used in this class are fairly
 * simplistic; they will be improved in later versions of the code.
 * <p>
 * Like the Islamic religious calendar, <em>Umm al-Qura</em> is also based
 * on the sighting method of the crescent moon but is standardized by Saudi Arabia.
 * <p>
 * The fixed-cycle <em>civil</em> calendar is used.
 * <p>
 * This class should not be subclassed.</p>
 * <p>
 * IslamicCalendar usually should be instantiated using
 * {@link android.icu.util.Calendar#getInstance(ULocale)} passing in a <code>ULocale</code>
 * with the tag <code>"@calendar=islamic"</code> or <code>"@calendar=islamic-civil"</code>
 * or <code>"@calendar=islamic-umalqura"</code>.</p>
 *
 * @see android.icu.util.GregorianCalendar
 * @see android.icu.util.Calendar
 *
 * @author Laura Werner
 * @author Alan Liu
 */
public final class IslamicCalendar {
    /**
     * Constant for Dhu al-Hijjah, the 12th month of the Islamic year.
     */
    public static final int DHU_AL_HIJJAH = 11;

    /**
     * Constant for Dhu al-Qi'dah, the 11th month of the Islamic year.
     */
    public static final int DHU_AL_QIDAH = 10;

    /**
     * Constant for Jumada al-awwal or (Jumada I), the 5th month of the Islamic year.
     */
    public static final int JUMADA_1 = 4;

    /**
     * Constant for Jumada al-thani or (Jumada II), the 6th month of the Islamic year.
     */
    public static final int JUMADA_2 = 5;

    /**
     * Constant for Muharram, the 1st month of the Islamic year.
     */
    public static final int MUHARRAM = 0;

    /**
     * Constant for Rabi' al-awwal (or Rabi' I), the 3rd month of the Islamic year.
     */
    public static final int RABI_1 = 2;

    /**
     * Constant for Rabi' al-thani or (Rabi' II), the 4th month of the Islamic year.
     */
    public static final int RABI_2 = 3;

    /**
     * Constant for Rajab, the 7th month of the Islamic year.
     */
    public static final int RAJAB = 6;

    /**
     * Constant for Ramadan, the 9th month of the Islamic year.
     */
    public static final int RAMADAN = 8;

    /**
     * Constant for Safar, the 2nd month of the Islamic year.
     */
    public static final int SAFAR = 1;

    /**
     * Constant for Sha'ban, the 8th month of the Islamic year.
     */
    public static final int SHABAN = 7;

    /**
     * Constant for Shawwal, the 10th month of the Islamic year.
     */
    public static final int SHAWWAL = 9;

    private IslamicCalendar() {
    }
}
