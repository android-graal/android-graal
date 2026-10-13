package android.icu.text;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.text.NumberFormat}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p>
 * <strong>IMPORTANT:</strong> New users are strongly encouraged to see if
 * {@link NumberFormatter} fits their use case.  Although not deprecated, this
 * class, NumberFormat, is only provided for java.text.NumberFormat compatibility.
 * <hr>
 *
 * <code>NumberFormat</code> is the abstract base class for all number
 * formats. This class provides the interface for formatting and parsing
 * numbers. <code>NumberFormat</code> also provides methods for determining
 * which locales have number formats, and what their names are.
 *
 * <code>NumberFormat</code> helps you to format and parse numbers for any locale.
 * Your code can be completely independent of the locale conventions for
 * decimal points, thousands-separators, or even the particular decimal
 * digits used, or whether the number format is even decimal.
 *
 * <p>
 * To format a number for the current Locale, use one of the factory
 * class methods:
 * <blockquote>
 * <pre>
 *  myString = NumberFormat.getInstance().format(myNumber);
 * </pre>
 * </blockquote>
 * If you are formatting multiple numbers, it is
 * more efficient to get the format and use it multiple times so that
 * the system doesn't have to fetch the information about the local
 * language and country conventions multiple times.
 * <blockquote>
 * <pre>
 * NumberFormat nf = NumberFormat.getInstance();
 * for (int i = 0; i &lt; a.length; ++i) {
 *     output.println(nf.format(myNumber[i]) + "; ");
 * }
 * </pre>
 * </blockquote>
 * To format a number for a different Locale, specify it in the
 * call to <code>getInstance</code>.
 * <blockquote>
 * <pre>
 * NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
 * </pre>
 * </blockquote>
 * You can also use a <code>NumberFormat</code> to parse numbers:
 * <blockquote>
 * <pre>
 * myNumber = nf.parse(myString);
 * </pre>
 * </blockquote>
 * Use <code>getInstance</code> or <code>getNumberInstance</code> to get the
 * normal number format. Use <code>getIntegerInstance</code> to get an
 * integer number format. Use <code>getCurrencyInstance</code> to get the
 * currency number format. And use <code>getPercentInstance</code> to get a
 * format for displaying percentages. Some factory methods are found within
 * subclasses of NumberFormat. With this format, a fraction like
 * 0.53 is displayed as 53%.
 *
 * <p>
 * Starting from ICU 4.2, you can use getInstance() by passing in a 'style'
 * as parameter to get the correct instance.
 * For example,
 * use getInstance(...NUMBERSTYLE) to get the normal number format,
 * getInstance(...PERCENTSTYLE) to get a format for displaying percentage,
 * getInstance(...SCIENTIFICSTYLE) to get a format for displaying scientific number,
 * getInstance(...INTEGERSTYLE) to get an integer number format,
 * getInstance(...CURRENCYSTYLE) to get the currency number format,
 * in which the currency is represented by its symbol, for example, "$3.00".
 * getInstance(...ISOCURRENCYSTYLE)  to get the currency number format,
 * in which the currency is represented by its ISO code, for example "USD3.00".
 * getInstance(...PLURALCURRENCYSTYLE) to get the currency number format,
 * in which the currency is represented by its full name in plural format,
 * for example, "3.00 US dollars" or "1.00 US dollar".
 *
 *
 * <p>
 * You can also control the display of numbers with such methods as
 * <code>setMinimumFractionDigits</code>.
 * If you want even more control over the format or parsing,
 * or want to give your users more control,
 * you can try casting the <code>NumberFormat</code> you get from the factory methods
 * to a <code>DecimalFormat</code>. This will work for the vast majority
 * of locales; just remember to put it in a <code>try</code> block in case you
 * encounter an unusual one.
 *
 * <p>
 * NumberFormat is designed such that some controls
 * work for formatting and others work for parsing.  The following is
 * the detailed description for each these control methods,
 * <p>
 * setParseIntegerOnly : only affects parsing, e.g.
 * if true,  "3456.78" -&gt; 3456 (and leaves the parse position just after '6')
 * if false, "3456.78" -&gt; 3456.78 (and leaves the parse position just after '8')
 * This is independent of formatting.  If you want to not show a decimal point
 * where there might be no digits after the decimal point, use
 * setDecimalSeparatorAlwaysShown on DecimalFormat.
 * <p>
 * You can also use forms of the <code>parse</code> and <code>format</code>
 * methods with <code>ParsePosition</code> and <code>FieldPosition</code> to
 * allow you to:
 * <ul>
 * <li> progressively parse through pieces of a string
 * <li> align the decimal point and other areas
 * </ul>
 * For example, you can align numbers in two ways:
 * <ol>
 * <li> If you are using a monospaced font with spacing for alignment,
 *      you can pass the <code>FieldPosition</code> in your format call, with
 *      <code>field</code> = <code>INTEGER_FIELD</code>. On output,
 *      <code>getEndIndex</code> will be set to the offset between the
 *      last character of the integer and the decimal. Add
 *      (desiredSpaceCount - getEndIndex) spaces at the front of the string.
 *
 * <li> If you are using proportional fonts,
 *      instead of padding with spaces, measure the width
 *      of the string in pixels from the start to <code>getEndIndex</code>.
 *      Then move the pen by
 *      (desiredPixelWidth - widthToAlignmentPoint) before drawing the text.
 *      It also works where there is no decimal, but possibly additional
 *      characters at the end, e.g., with parentheses in negative
 *      numbers: "(12)" for -12.
 * </ol>
 *
 * <h3>Synchronization</h3>
 * <p>
 * Number formats are generally not synchronized. It is recommended to create
 * separate format instances for each thread. If multiple threads access a format
 * concurrently, it must be synchronized externally.
 *
 * <h4>DecimalFormat</h4>
 * <p>DecimalFormat is the concrete implementation of NumberFormat, and the
 * NumberFormat API is essentially an abstraction from DecimalFormat's API.
 * Refer to DecimalFormat for more information about this API.</p>
 *
 * see          DecimalFormat
 * see          java.text.ChoiceFormat
 * @author       Mark Davis
 * @author       Helena Shih
 * @author       Alan Liu
 */
public final class NumberFormat {
    /**
     * <strong>[icu]</strong> Constant to specify currency style of format which uses currency symbol
     * to represent currency for accounting, for example: "($3.00), instead of
     * "-$3.00" ({@link #CURRENCYSTYLE}).
     * Overrides any style specified using -cf- key in locale.
     */
    public static final int ACCOUNTINGCURRENCYSTYLE = 7;

    /**
     * <strong>[icu]</strong> Constant to specify currency cash style of format which uses currency
     * ISO code to represent currency, for example: "NT$3" instead of "NT$3.23".
     */
    public static final int CASHCURRENCYSTYLE = 8;

    /**
     * <strong>[icu]</strong> Constant to specify general currency style of format. Defaults to
     * STANDARDCURRENCYSTYLE, using currency symbol, for example "$3.00", with
     * non-accounting style for negative values (e.g. minus sign).
     * The specific style may be specified using the -cf- locale key.
     */
    public static final int CURRENCYSTYLE = 1;

    /**
     * Field constant used to construct a FieldPosition object. Signifies that
     * the position of the fraction part of a formatted number should be returned.
     * @see java.text.FieldPosition
     */
    public static final int FRACTION_FIELD = 1;

    /**
     * <strong>[icu]</strong> Constant to specify a integer number style format.
     */
    public static final int INTEGERSTYLE = 4;

    /**
     * Field constant used to construct a FieldPosition object. Signifies that
     * the position of the integer part of a formatted number should be returned.
     * @see java.text.FieldPosition
     */
    public static final int INTEGER_FIELD = 0;

    /**
     * <strong>[icu]</strong> Constant to specify currency style of format which uses currency
     * ISO code to represent currency, for example: "USD3.00".
     */
    public static final int ISOCURRENCYSTYLE = 5;

    /**
     * <strong>[icu]</strong> Constant to specify normal number style of format.
     */
    public static final int NUMBERSTYLE = 0;

    /**
     * <strong>[icu]</strong> Constant to specify a style of format to display percent.
     */
    public static final int PERCENTSTYLE = 2;

    /**
     * <strong>[icu]</strong> Constant to specify currency style of format which uses currency
     * long name with plural format to represent currency, for example,
     * "3.00 US Dollars".
     */
    public static final int PLURALCURRENCYSTYLE = 6;

    /**
     * <strong>[icu]</strong> Constant to specify a style of format to display scientific number.
     */
    public static final int SCIENTIFICSTYLE = 3;

    /**
     * <strong>[icu]</strong> Constant to specify currency style of format which uses currency symbol
     * to represent currency, for example "$3.00", using non-accounting style for
     * negative values (e.g. minus sign).
     * Overrides any style specified using -cf- key in locale.
     */
    public static final int STANDARDCURRENCYSTYLE = 9;

    private NumberFormat() {
    }
}
