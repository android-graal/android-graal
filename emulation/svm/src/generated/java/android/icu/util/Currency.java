package android.icu.util;

/**
 * A class encapsulating a currency, as defined by ISO 4217.  A
 * <tt>Currency</tt> object can be created given a <tt>Locale</tt> or
 * given an ISO 4217 code.  Once created, the <tt>Currency</tt> object
 * can return various data necessary to its proper display:
 *
 * <ul><li>A display symbol, for a specific locale
 * <li>The number of fraction digits to display
 * <li>A rounding increment
 * </ul>
 *
 * The <tt>DecimalFormat</tt> class uses these data to display
 * currencies.
 *
 * <p>Note: This class deliberately resembles
 * <tt>java.util.Currency</tt> but it has a completely independent
 * implementation, and adds features not present in the JDK.
 * @author Alan Liu
 */
public final class Currency {
    /**
     * Selector for getName() indicating the formal currency symbol.
     * <p>
     * The formal currency symbol is similar to the regular currency symbol,
     * but it always takes the form used in formal settings such as banking;
     * for example, "NT$" instead of "$" for TWD in zh-TW.
     */
    public static final int FORMAL_SYMBOL_NAME = 4;

    /**
     * Selector for getName() indicating the long name for a
     * currency, such as "US Dollar" for USD.
     */
    public static final int LONG_NAME = 1;

    /**
     * Selector for getName() indicating the narrow currency symbol.
     * <p>
     * The narrow currency symbol is similar to the regular currency symbol,
     * but it always takes the shortest form;
     * for example, "$" instead of "US$" for USD in en-CA.
     */
    public static final int NARROW_SYMBOL_NAME = 3;

    /**
     * Selector for getName() indicating the plural long name for a
     * currency, such as "US dollar" for USD in "1 US dollar",
     * and "US dollars" for USD in "2 US dollars".
     */
    public static final int PLURAL_LONG_NAME = 2;

    /**
     * Selector for getName() indicating a symbolic name for a
     * currency, such as "$" for USD.
     */
    public static final int SYMBOL_NAME = 0;

    /**
     * Selector for getName() indicating the variant currency symbol.
     * <p>
     * The variant symbol for a currency is an alternative symbol that is not
     * necessarily as widely used as the regular symbol.
     */
    public static final int VARIANT_SYMBOL_NAME = 5;

    private Currency() {
    }
}
