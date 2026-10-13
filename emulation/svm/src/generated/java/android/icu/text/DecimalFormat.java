package android.icu.text;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.text.DecimalFormat}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p>
 * <strong>IMPORTANT:</strong> New users are strongly encouraged to see if
 * {@link NumberFormatter} fits their use case.  Although not deprecated, this
 * class, DecimalFormat, is only provided for java.text.DecimalFormat compatibility.
 * <hr>
 *
 * <code>DecimalFormat</code> is the primary
 * concrete subclass of {@link NumberFormat}. It has a variety of features designed to make it
 * possible to parse and format numbers in any locale, including support for Western, Arabic, or
 * Indic digits. It supports different flavors of numbers, including integers ("123"), fixed-point
 * numbers ("123.4"), scientific notation ("1.23E4"), percentages ("12%"), and currency amounts
 * ("$123.00", "USD123.00", "123.00 US dollars"). All of these flavors can be easily localized.
 *
 * <p>To obtain a number formatter for a specific locale (including the default locale), call one of
 * NumberFormat's factory methods such as {@link NumberFormat#getInstance}. Do not call
 * DecimalFormat constructors directly unless you know what you are doing.
 *
 * <p>DecimalFormat aims to comply with the specification <a
 * href="http://unicode.org/reports/tr35/tr35-numbers.html#Number_Format_Patterns">UTS #35</a>. Read
 * the specification for more information on how all the properties in DecimalFormat fit together.
 *
 * <p><strong>NOTE:</strong> Starting in ICU 60, there is a new set of APIs for localized number
 * formatting that are designed to be an improvement over DecimalFormat.  New users are discouraged
 * from using DecimalFormat.  For more information, see the package android.icu.number.
 *
 * <h3>Example Usage</h3>
 *
 * <p>Customize settings on a DecimalFormat instance from the NumberFormat factory:
 *
 * <blockquote>
 *
 * <pre>
 * NumberFormat f = NumberFormat.getInstance(loc);
 * if (f instanceof DecimalFormat) {
 *     ((DecimalFormat) f).setDecimalSeparatorAlwaysShown(true);
 *     ((DecimalFormat) f).setMinimumGroupingDigits(2);
 * }
 * </pre>
 *
 * </blockquote>
 *
 * <p>Quick and dirty print out a number using the localized number, currency, and percent format
 * for each locale:
 *
 * <blockquote>
 *
 * <pre>
 * for (ULocale uloc : ULocale.getAvailableLocales()) {
 *     System.out.print(uloc + ":\t");
 *     System.out.print(NumberFormat.getInstance(uloc).format(1.23));
 *     System.out.print("\t");
 *     System.out.print(NumberFormat.getCurrencyInstance(uloc).format(1.23));
 *     System.out.print("\t");
 *     System.out.print(NumberFormat.getPercentInstance(uloc).format(1.23));
 *     System.out.println();
 * }
 * </pre>
 *
 * </blockquote>
 *
 * <h3>Properties and Symbols</h3>
 *
 * <p>A DecimalFormat object encapsulates a set of <em>properties</em> and a set of
 * <em>symbols</em>. Grouping size, rounding mode, and affixes are examples of properties. Locale
 * digits and the characters used for grouping and decimal separators are examples of symbols.
 *
 * <p>To set a custom set of symbols, use {@link #setDecimalFormatSymbols}. Use the various other
 * setters in this class to set custom values for the properties.
 *
 * <h3>Rounding</h3>
 *
 * <p>DecimalFormat provides three main strategies to specify the position at which numbers should
 * be rounded:
 *
 * <ol>
 *   <li><strong>Magnitude:</strong> Display a fixed number of fraction digits; this is the most
 *       common form.
 *   <li><strong>Increment:</strong> Round numbers to the closest multiple of a certain increment,
 *       such as 0.05. This is common in currencies.
 *   <li><strong>Significant Digits:</strong> Round numbers such that a fixed number of nonzero
 *       digits are shown. This is most common in scientific notation.
 * </ol>
 *
 * <p>It is not possible to specify more than one rounding strategy. For example, setting a rounding
 * increment in conjunction with significant digits results in undefined behavior.
 *
 * <p>It is also possible to specify the <em>rounding mode</em> to use. The default rounding mode is
 * "half even", which rounds numbers to their closest increment, with ties broken in favor of
 * trailing numbers being even. For more information, see {@link #setRoundingMode} and <a
 * href="https://unicode-org.github.io/icu/userguide/format_parse/numbers/rounding-modes">the ICU
 * User Guide</a>.
 *
 * <h3>Pattern Strings</h3>
 *
 * <p>A <em>pattern string</em> is a way to serialize some of the available properties for decimal
 * formatting. However, not all properties are capable of being serialized into a pattern string;
 * see {@link #applyPattern} for more information.
 *
 * <p>Most users should not need to interface with pattern strings directly.
 *
 * <p>ICU DecimalFormat aims to follow the specification for pattern strings in <a
 * href="http://unicode.org/reports/tr35/tr35-numbers.html#Number_Format_Patterns">UTS #35</a>.
 * Refer to that specification for more information on pattern string syntax.
 *
 * <h4>Pattern String BNF</h4>
 *
 * The following BNF is used when parsing the pattern string into property values:
 *
 * <pre>
 * pattern    := subpattern (';' subpattern)?
 * subpattern := prefix? number exponent? suffix?
 * number     := (integer ('.' fraction)?) | sigDigits
 * prefix     := '&#92;u0000'..'&#92;uFFFD' - specialCharacters
 * suffix     := '&#92;u0000'..'&#92;uFFFD' - specialCharacters
 * integer    := '#'* '0'* '0'
 * fraction   := '0'* '#'*
 * sigDigits  := '#'* '@' '@'* '#'*
 * exponent   := 'E' '+'? '0'* '0'
 * padSpec    := '*' padChar
 * padChar    := '&#92;u0000'..'&#92;uFFFD' - quote
 * &#32;
 * Notation:
 *   X*       0 or more instances of X
 *   X?       0 or 1 instances of X
 *   X|Y      either X or Y
 *   C..D     any character from C up to D, inclusive
 *   S-T      characters in S, except those in T
 * </pre>
 *
 * <p>The first subpattern is for positive numbers. The second (optional) subpattern is for negative
 * numbers.
 *
 * <p>Not indicated in the BNF syntax above:
 *
 * <ul>
 *   <li>The grouping separator ',' can occur inside the integer and sigDigits elements, between any
 *       two pattern characters of that element, as long as the integer or sigDigits element is not
 *       followed by the exponent element.
 *   <li>Two grouping intervals are recognized: That between the decimal point and the first
 *       grouping symbol, and that between the first and second grouping symbols. These intervals
 *       are identical in most locales, but in some locales they differ. For example, the pattern
 *       &quot;#,##,###&quot; formats the number 123456789 as &quot;12,34,56,789&quot;.
 *   <li>The pad specifier <code>padSpec</code> may appear before the prefix, after the prefix,
 *       before the suffix, after the suffix, or not at all.
 *   <li>In place of '0', the digits '1' through '9' may be used to indicate a rounding increment.
 * </ul>
 *
 * <h3>Parsing</h3>
 *
 * <p>DecimalFormat aims to be able to parse anything that it can output as a formatted string.
 *
 * <p>There are two primary parse modes: <em>lenient</em> and <em>strict</em>. Lenient mode should
 * be used if the goal is to parse user input to a number; strict mode should be used if the goal is
 * validation. The default is lenient mode. For more information, see {@link #setParseStrict}.
 *
 * <p><code>DecimalFormat</code> parses all Unicode characters that represent decimal digits, as
 * defined by {@link UCharacter#digit}. In addition, <code>DecimalFormat</code> also recognizes as
 * digits the ten consecutive characters starting with the localized zero digit defined in the
 * {@link DecimalFormatSymbols} object. During formatting, the {@link DecimalFormatSymbols}-based
 * digits are output.
 *
 * <p>Grouping separators are ignored in lenient mode (default). In strict mode, grouping separators
 * must match the locale-specified grouping sizes.
 *
 * <p>When using {@link #parseCurrency}, all currencies are accepted, not just the currency
 * currently set in the formatter. In addition, the formatter is able to parse every currency style
 * format for a particular locale no matter which style the formatter is constructed with. For
 * example, a formatter instance gotten from NumberFormat.getInstance(ULocale,
 * NumberFormat.CURRENCYSTYLE) can parse both "USD1.00" and "3.00 US dollars".
 *
 * <p>Whitespace characters (lenient mode) and control characters (lenient and strict mode),
 * collectively called "ignorables", do not need to match in identity or quantity between the
 * pattern string and the input string. For example, the pattern "# %" matches "35 %" (with a single
 * space), "35%" (with no space), "35&nbsp;%" (with a non-breaking space), and "35&nbsp; %" (with
 * multiple spaces). Arbitrary ignorables are also allowed at boundaries between the parts of the
 * number: prefix, number, exponent separator, and suffix. Ignorable whitespace characters are those
 * having the Unicode "blank" property for regular expressions, defined in UTS #18 Annex C, which is
 * "horizontal" whitespace, like spaces and tabs, but not "vertical" whitespace, like line breaks.
 * Ignorable control characters are those in the Unicode set [:Default_Ignorable_Code_Point:].
 *
 * <p>If {@link #parse(String, ParsePosition)} fails to parse a string, it returns <code>null</code>
 * and leaves the parse position unchanged. The convenience method {@link #parse(String)} indicates
 * parse failure by throwing a {@link java.text.ParseException}.
 *
 * <p>Under the hood, a state table parsing engine is used. To debug a parsing failure during
 * development, use the following pattern to print details about the state table transitions:
 *
 * <pre>
 * android.icu.impl.number.Parse.DEBUGGING = true;
 * df.parse("123.45", ppos);
 * android.icu.impl.number.Parse.DEBUGGING = false;
 * </pre>
 *
 * <h3>Thread Safety and Best Practices</h3>
 *
 * <p>Starting with ICU 59, instances of DecimalFormat are thread-safe.
 *
 * <p>Under the hood, DecimalFormat maintains an immutable formatter object that is rebuilt whenever
 * any of the property setters are called. It is therefore best practice to call property setters
 * only during construction and not when formatting numbers online.
 *
 * @see java.text.Format
 * @see NumberFormat
 */
public final class DecimalFormat {
    /**
     * <strong>[icu]</strong> Constant for {@link #setMinimumGroupingDigits(int)} to specify display
     * grouping using the default strategy for all locales.
     *
     * @see #setMinimumGroupingDigits(int)
     * @see #MINIMUM_GROUPING_DIGITS_MIN2
     */
    public static final int MINIMUM_GROUPING_DIGITS_AUTO = -2;

    /**
     * <strong>[icu]</strong> Constant for {@link #setMinimumGroupingDigits(int)} to specify display
     * grouping using locale defaults, except do not show grouping on values smaller than
     * 10000 (such that there is a minimum of two digits before the first separator).
     *
     * @see #setMinimumGroupingDigits(int)
     * @see #MINIMUM_GROUPING_DIGITS_AUTO
     */
    public static final int MINIMUM_GROUPING_DIGITS_MIN2 = -3;

    /**
     * <strong>[icu]</strong> Constant for {@link #getPadPosition()} and {@link #setPadPosition(int)} to specify pad
     * characters inserted after the prefix.
     *
     * @see #setPadPosition
     * @see #getPadPosition
     * @see #PAD_BEFORE_PREFIX
     * @see #PAD_BEFORE_SUFFIX
     * @see #PAD_AFTER_SUFFIX
     */
    public static final int PAD_AFTER_PREFIX = 1;

    /**
     * <strong>[icu]</strong> Constant for {@link #getPadPosition()} and {@link #setPadPosition(int)} to specify pad
     * characters inserted after the suffix.
     *
     * @see #setPadPosition
     * @see #getPadPosition
     * @see #PAD_BEFORE_PREFIX
     * @see #PAD_AFTER_PREFIX
     * @see #PAD_BEFORE_SUFFIX
     */
    public static final int PAD_AFTER_SUFFIX = 3;

    /**
     * <strong>[icu]</strong> Constant for {@link #getPadPosition()} and {@link #setPadPosition(int)} to specify pad
     * characters inserted before the prefix.
     *
     * @see #setPadPosition
     * @see #getPadPosition
     * @see #PAD_AFTER_PREFIX
     * @see #PAD_BEFORE_SUFFIX
     * @see #PAD_AFTER_SUFFIX
     */
    public static final int PAD_BEFORE_PREFIX = 0;

    /**
     * <strong>[icu]</strong> Constant for {@link #getPadPosition()} and {@link #setPadPosition(int)} to specify pad
     * characters inserted before the suffix.
     *
     * @see #setPadPosition
     * @see #getPadPosition
     * @see #PAD_BEFORE_PREFIX
     * @see #PAD_AFTER_PREFIX
     * @see #PAD_AFTER_SUFFIX
     */
    public static final int PAD_BEFORE_SUFFIX = 2;

    private DecimalFormat() {
    }
}
