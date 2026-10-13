package android.icu.util;

/**
 * <code>IndianCalendar</code> is a subclass of <code>GregorianCalendar</code>
 * that numbers years since the birth of the Buddha.  This is the civil calendar
 * which is accepted by government of India as Indian National Calendar. 
 * The two calendars most widely used in India today are the Vikrama calendar 
 * followed in North India and the Shalivahana or Saka calendar which is followed 
 * in South India and Maharashtra.
 *
 * A variant of the Shalivahana Calendar was reformed and standardized as the 
 * Indian National calendar in 1957.
 * <p>
 * Some details of Indian National Calendar (to be implemented) :
 * The Months
 * Month          Length      Start date (Gregorian)
 * =================================================
 * 1 Chaitra      30/31          March 22*
 * 2 Vaisakha     31             April 21
 * 3 Jyaistha     31             May 22
 * 4 Asadha       31             June 22
 * 5 Sravana      31             July 23
 * 6 Bhadra       31             August 23
 * 7 Asvina       30             September 23
 * 8 Kartika      30             October 23
 * 9 Agrahayana   30             November 22
 * 10 Pausa       30             December 22
 * 11 Magha       30             January 21
 * 12 Phalguna    30             February 20
 *
 * In leap years, Chaitra has 31 days and starts on March 21 instead.
 * The leap years of Gregorian calendar and Indian National Calendar are in synchornization. 
 * So When its a leap year in Gregorian calendar then Chaitra has 31 days.
 *
 * The Years
 * Years are counted in the Saka Era, which starts its year 0 in 78AD (by gregorian calendar).
 * So for eg. 9th June 2006 by Gregorian Calendar, is same as 19th of Jyaistha in 1928 of Saka 
 * era by Indian National Calendar.
 * <p>
 * The Indian Calendar has only one allowable era: <code>Saka Era</code>.  If the
 * calendar is not in lenient mode (see <code>setLenient</code>), dates before
 * 1/1/1 Saka Era are rejected with an <code>IllegalArgumentException</code>.
 * <p>
 * This class should not be subclassed.</p>
 * <p>
 * IndianCalendar usually should be instantiated using 
 * {@link android.icu.util.Calendar#getInstance(ULocale)} passing in a <code>ULocale</code>
 * with the tag <code>"@calendar=Indian"</code>.</p>
 *
 * @see android.icu.util.Calendar
 * @see android.icu.util.GregorianCalendar
 */
public final class IndianCalendar {
    /**
     * Constant for Agrahayana, the 9th month of the Indian year.
     */
    public static final int AGRAHAYANA = 8;

    /**
     * Constant for Asadha, the 4th month of the Indian year.
     */
    public static final int ASADHA = 3;

    /**
     * Constant for Asvina, the 7th month of the Indian year.
     */
    public static final int ASVINA = 6;

    /**
     * Constant for Bhadra, the 6th month of the Indian year.
     */
    public static final int BHADRA = 5;

    /**
     * Constant for Chaitra, the 1st month of the Indian year.
     */
    public static final int CHAITRA = 0;

    /**
     * Constant for the Indian Era.  This is the only allowable <code>ERA</code>
     * value for the Indian calendar.
     *
     * @see android.icu.util.Calendar#ERA
     */
    public static final int IE = 0;

    /**
     * Constant for Jyaistha, the 3rd month of the Indian year.
     */
    public static final int JYAISTHA = 2;

    /**
     * Constant for Kartika, the 8th month of the Indian year.
     */
    public static final int KARTIKA = 7;

    /**
     * Constant for Magha, the 11th month of the Indian year.
     */
    public static final int MAGHA = 10;

    /**
     * Constant for Pausa, the 10th month of the Indian year.
     */
    public static final int PAUSA = 9;

    /**
     * Constant for Phalguna, the 12th month of the Indian year.
     */
    public static final int PHALGUNA = 11;

    /**
     * Constant for Sravana, the 5th month of the Indian year.
     */
    public static final int SRAVANA = 4;

    /**
     * Constant for Vaisakha, the 2nd month of the Indian year.
     */
    public static final int VAISAKHA = 1;

    private IndianCalendar() {
    }
}
