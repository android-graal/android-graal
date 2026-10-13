package android.icu.text;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.text.DecimalFormatSymbols}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * This class represents the set of symbols (such as the decimal separator, the grouping
 * separator, and so on) needed by <code>DecimalFormat</code> to format
 * numbers. <code>DecimalFormat</code> creates for itself an instance of
 * <code>DecimalFormatSymbols</code> from its locale data.  If you need to change any of
 * these symbols, you can get the <code>DecimalFormatSymbols</code> object from your
 * <code>DecimalFormat</code> and modify it.
 *
 * @see          java.util.Locale
 * @see          DecimalFormat
 * @author       Mark Davis
 * @author       Alan Liu
 */
public final class DecimalFormatSymbols {
    /**
     * <strong>[icu]</strong> Indicates the currency match pattern used in {@link #getPatternForCurrencySpacing}.
     */
    public static final int CURRENCY_SPC_CURRENCY_MATCH = 0;

    /**
     * <strong>[icu]</strong> Indicates the insertion value used in {@link #getPatternForCurrencySpacing}.
     */
    public static final int CURRENCY_SPC_INSERT = 2;

    /**
     * <strong>[icu]</strong> Indicates the surrounding match pattern used in {@link
     * #getPatternForCurrencySpacing}.
     */
    public static final int CURRENCY_SPC_SURROUNDING_MATCH = 1;

    private DecimalFormatSymbols() {
    }
}
