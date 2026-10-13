package android.icu.util;

/**
 * Implement the Coptic calendar system.
 * <p>
 * CopticCalendar usually should be instantiated using
 * {@link android.icu.util.Calendar#getInstance(ULocale)} passing in a <code>ULocale</code>
 * with the tag <code>"@calendar=coptic"</code>.</p>
 *
 * @see android.icu.util.Calendar
 */
public final class CopticCalendar {
    /**
     * Constant for Μεϣιρ / ﺮﻴﺸﻣأ,
     * the 6th month of the Coptic year.
     */
    public static final int AMSHIR = 5;

    /**
     * Constant for Παοπι / ﻪﺑﺎﺑ,
     * the 2nd month of the Coptic year.
     */
    public static final int BABA = 1;

    /**
     * Constant for Παρεμϩατ / تﺎﻬﻣﺮﺑ,
     * the 7th month of the Coptic year.
     */
    public static final int BARAMHAT = 6;

    /**
     * Constant for Φαρμοθι / هدﻮﻣﺮﺑ,
     * the 8th month of the Coptic year.
     */
    public static final int BARAMOUDA = 7;

    /**
     * Constant for Παϣαν / ﺲﻨﺸﺑ;,
     * the 9th month of the Coptic year.
     */
    public static final int BASHANS = 8;

    /**
     * Constant for Επηπ / ﺐﻴﺑأ,
     * the 11th month of the Coptic year.
     */
    public static final int EPEP = 10;

    /**
     * Constant for Αθορ / رﻮﺗﺎﻫ,
     * the 3rd month of the Coptic year.
     */
    public static final int HATOR = 2;

    /**
     * Constant for Χοιακ / ﻚﻬﻴﻛ;,
     * the 4th month of the Coptic year.
     */
    public static final int KIAHK = 3;

    /**
     * Constant for Μεϲωρη / ىﺮﺴﻣ,
     * the 12th month of the Coptic year.
     */
    public static final int MESRA = 11;

    /**
     * Constant for Πικογϫι μαβοτ / ﺮﻴﻐﺼﻟاﺮﻬﺸﻟا,
     * the 13th month of the Coptic year.
     */
    public static final int NASIE = 12;

    /**
     * Constant for Παωνι / ﻪﻧؤﻮﺑ,
     * the 10th month of the Coptic year.
     */
    public static final int PAONA = 9;

    /**
     * Constant for Τωβι / طﻮﺒﻫ,
     * the 5th month of the Coptic year.
     */
    public static final int TOBA = 4;

    /**
     * Constant for ωογτ / تﻮﺗ,
     * the 1st month of the Coptic year.
     */
    public static final int TOUT = 0;

    private CopticCalendar() {
    }
}
