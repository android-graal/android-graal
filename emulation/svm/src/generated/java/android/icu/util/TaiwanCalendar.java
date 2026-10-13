package android.icu.util;

/**
 * <code>TaiwanCalendar</code> is a subclass of <code>GregorianCalendar</code>
 * that numbers years since 1912. 
 * <p>
 * The Taiwan calendar is identical to the Gregorian calendar in all respects
 * except for the year and era.  Years are numbered since 1912 AD (Gregorian).
 * <p>
 * The Taiwan Calendar has one era: <code>MINGUO</code>.
 * <p>
 * This class should not be subclassed.</p>
 * <p>
 * TaiwanCalendar usually should be instantiated using 
 * {@link android.icu.util.Calendar#getInstance(ULocale)} passing in a <code>ULocale</code>
 * with the tag <code>"@calendar=roc"</code>.</p>
 *
 * @see android.icu.util.Calendar
 * @see android.icu.util.GregorianCalendar
 *
 * @author Laura Werner
 * @author Alan Liu
 * @author Steven R. Loomis
 */
public final class TaiwanCalendar {
    /**
     * Constant for the Taiwan Era for years before Minguo 1.
     * Before Minuo 1 is Gregorian 1911, Before Minguo 2 is Gregorian 1910
     * and so on.
     *
     * @see android.icu.util.Calendar#ERA
     */
    public static final int BEFORE_MINGUO = 0;

    /**
     * Constant for the Taiwan Era for Minguo.  Minguo 1 is 1912 in
     * Gregorian calendar.
     *
     * @see android.icu.util.Calendar#ERA
     */
    public static final int MINGUO = 1;

    private TaiwanCalendar() {
    }
}
