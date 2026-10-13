package android.icu.util;

/**
 * Implement the Ethiopic calendar system.
 * <p>
 * EthiopicCalendar usually should be instantiated using
 * {@link android.icu.util.Calendar#getInstance(ULocale)} passing in a <code>ULocale</code>
 * with the tag <code>"@calendar=ethiopic"</code>.</p>
 *
 * @see android.icu.util.Calendar
 */
public final class EthiopicCalendar {
    /**
     * Constant for ግንቦት, the 9th month of the Ethiopic year.
     */
    public static final int GENBOT = 8;

    /**
     * Constant for ሐምሌ, the 11th month of the Ethiopic year.
     */
    public static final int HAMLE = 10;

    /**
     * Constant for ኅዳር, the 3rd month of the Ethiopic year.
     */
    public static final int HEDAR = 2;

    /**
     * Constant for መጋቢት, the 7th month of the Ethiopic year.
     */
    public static final int MEGABIT = 6;

    /**
     * Constant for መስከረም, the 1st month of the Ethiopic year.
     */
    public static final int MESKEREM = 0;

    /**
     * Constant for ሚያዝያ, the 8th month of the Ethiopic year.
     */
    public static final int MIAZIA = 7;

    /**
     * Constant for ነሐሴ, the 12th month of the Ethiopic year.
     */
    public static final int NEHASSE = 11;

    /**
     * Constant for ጳጉሜን, the 13th month of the Ethiopic year.
     */
    public static final int PAGUMEN = 12;

    /**
     * Constant for ሰኔ, the 10th month of the Ethiopic year.
     */
    public static final int SENE = 9;

    /**
     * Constant for ታኅሣሥ, the 4th month of the Ethiopic year.
     */
    public static final int TAHSAS = 3;

    /**
     * Constant for ጥቅምት, the 2nd month of the Ethiopic year.
     */
    public static final int TEKEMT = 1;

    /**
     * Constant for ጥር, the 5th month of the Ethiopic year.
     */
    public static final int TER = 4;

    /**
     * Constant for የካቲት, the 6th month of the Ethiopic year.
     */
    public static final int YEKATIT = 5;

    private EthiopicCalendar() {
    }
}
