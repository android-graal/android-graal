package android.icu.util;

/**
 * <code>BuddhistCalendar</code> is a subclass of <code>GregorianCalendar</code>
 * that numbers years since the birth of the Buddha.  This is the civil calendar
 * in some predominantly Buddhist countries such as Thailand, and it is used for
 * religious purposes elsewhere.
 * <p>
 * The Buddhist calendar is identical to the Gregorian calendar in all respects
 * except for the year and era.  Years are numbered since the birth of the
 * Buddha in 543 BC (Gregorian), so that 1 AD (Gregorian) is equivalent to 544
 * BE (Buddhist Era) and 1998 AD is 2541 BE.
 * <p>
 * The Buddhist Calendar has only one allowable era: <code>BE</code>.  If the
 * calendar is not in lenient mode (see <code>setLenient</code>), dates before
 * 1/1/1 BE are rejected with an <code>IllegalArgumentException</code>.
 * <p>
 * This class should not be subclassed.</p>
 * <p>
 * BuddhistCalendar usually should be instantiated using 
 * {@link android.icu.util.Calendar#getInstance(ULocale)} passing in a <code>ULocale</code>
 * with the tag <code>"@calendar=buddhist"</code>.</p>
 *
 * @see android.icu.util.Calendar
 * @see android.icu.util.GregorianCalendar
 *
 * @author Laura Werner
 * @author Alan Liu
 */
public final class BuddhistCalendar {
    /**
     * Constant for the Buddhist Era.  This is the only allowable <code>ERA</code>
     * value for the Buddhist calendar.
     *
     * @see android.icu.util.Calendar#ERA
     */
    public static final int BE = 0;

    private BuddhistCalendar() {
    }
}
