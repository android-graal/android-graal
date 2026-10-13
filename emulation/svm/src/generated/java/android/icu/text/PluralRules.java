package android.icu.text;

/**
 * <p>
 * Defines rules for mapping non-negative numeric values onto a small set of keywords.
 * </p>
 * <p>
 * Rules are constructed from a text description, consisting of a series of keywords and conditions. The {@link #select}
 * method examines each condition in order and returns the keyword for the first condition that matches the number. If
 * none match, {@link #KEYWORD_OTHER} is returned.
 * </p>
 * <p>
 * A PluralRules object is immutable. It contains caches for sample values, but those are synchronized.
 * <p>
 * PluralRules is Serializable so that it can be used in formatters, which are serializable.
 * </p>
 * <p>
 * For more information, details, and tips for writing rules, see the <a
 * href="https://www.unicode.org/reports/tr35/tr35-numbers.html#Language_Plural_Rules">LDML spec,
 * Part 3.5 Language Plural Rules</a>
 * </p>
 * <p>
 * Examples:
 * </p>
 *
 * <pre>
 * &quot;one: n is 1; few: n in 2..4&quot;
 * </pre>
 * <p>
 * This defines two rules, for 'one' and 'few'. The condition for 'one' is "n is 1" which means that the number must be
 * equal to 1 for this condition to pass. The condition for 'few' is "n in 2..4" which means that the number must be
 * between 2 and 4 inclusive - and be an integer - for this condition to pass. All other numbers are assigned the
 * keyword "other" by the default rule.
 * </p>
 *
 * <pre>
 * &quot;zero: n is 0; one: n is 1; zero: n mod 100 in 1..19&quot;
 * </pre>
 * <p>
 * This illustrates that the same keyword can be defined multiple times. Each rule is examined in order, and the first
 * keyword whose condition passes is the one returned. Also notes that a modulus is applied to n in the last rule. Thus
 * its condition holds for 119, 219, 319...
 * </p>
 *
 * <pre>
 * &quot;one: n is 1; few: n mod 10 in 2..4 and n mod 100 not in 12..14&quot;
 * </pre>
 * <p>
 * This illustrates conjunction and negation. The condition for 'few' has two parts, both of which must be met:
 * "n mod 10 in 2..4" and "n mod 100 not in 12..14". The first part applies a modulus to n before the test as in the
 * previous example. The second part applies a different modulus and also uses negation, thus it matches all numbers
 * <i>not</i> in 12, 13, 14, 112, 113, 114, 212, 213, 214...
 * </p>
 * <p>
 * Syntax:
 * </p>
 * <pre>
 * rules         = rule (';' rule)*
 * rule          = keyword ':' condition
 * keyword       = &lt;identifier&gt;
 * condition     = and_condition ('or' and_condition)*
 * and_condition = relation ('and' relation)*
 * relation      = not? expr not? rel not? range_list
 * expr          = ('n' | 'i' | 'f' | 'v' | 't') (mod value)?
 * not           = 'not' | '!'
 * rel           = 'in' | 'is' | '=' | '≠' | 'within'
 * mod           = 'mod' | '%'
 * range_list    = (range | value) (',' range_list)*
 * value         = digit+
 * digit         = 0|1|2|3|4|5|6|7|8|9
 * range         = value'..'value
 * </pre>
 * <p>Each <b>not</b> term inverts the meaning; however, there should not be more than one of them.</p>
 * <p>
 * The i, f, t, and v values are defined as follows:
 * </p>
 * <ul>
 * <li>i to be the integer digits.</li>
 * <li>f to be the visible decimal digits, as an integer.</li>
 * <li>t to be the visible decimal digits—without trailing zeros—as an integer.</li>
 * <li>v to be the number of visible fraction digits.</li>
 * <li>j is defined to only match integers. That is j is 3 fails if v != 0 (eg for 3.1 or 3.0).</li>
 * </ul>
 * <p>
 * Examples are in the following table:
 * </p>
 * <table border='1' style="border-collapse:collapse">
 * <tbody>
 * <tr>
 * <th>n</th>
 * <th>i</th>
 * <th>f</th>
 * <th>v</th>
 * </tr>
 * <tr>
 * <td>1.0</td>
 * <td>1</td>
 * <td align="right">0</td>
 * <td>1</td>
 * </tr>
 * <tr>
 * <td>1.00</td>
 * <td>1</td>
 * <td align="right">0</td>
 * <td>2</td>
 * </tr>
 * <tr>
 * <td>1.3</td>
 * <td>1</td>
 * <td align="right">3</td>
 * <td>1</td>
 * </tr>
 * <tr>
 * <td>1.03</td>
 * <td>1</td>
 * <td align="right">3</td>
 * <td>2</td>
 * </tr>
 * <tr>
 * <td>1.23</td>
 * <td>1</td>
 * <td align="right">23</td>
 * <td>2</td>
 * </tr>
 * </tbody>
 * </table>
 * <p>
 * An "identifier" is a sequence of characters that do not have the Unicode Pattern_Syntax or Pattern_White_Space
 * properties.
 * <p>
 * The difference between 'in' and 'within' is that 'in' only includes integers in the specified range, while 'within'
 * includes all values. Using 'within' with a range_list consisting entirely of values is the same as using 'in' (it's
 * not an error).
 * </p>
 */
public final class PluralRules {
    /**
     * Common name for the 'paucal' or other special plural form.
     */
    public static final String KEYWORD_FEW = "few";

    /**
     * Common name for the arabic (11 to 99) plural form.
     */
    public static final String KEYWORD_MANY = "many";

    /**
     * Common name for the 'singular' plural form.
     */
    public static final String KEYWORD_ONE = "one";

    /**
     * Common name for the default plural form.  This name is returned
     * for values to which no other form in the rule applies.  It
     * can additionally be assigned rules of its own.
     */
    public static final String KEYWORD_OTHER = "other";

    /**
     * Common name for the 'dual' plural form.
     */
    public static final String KEYWORD_TWO = "two";

    /**
     * Common name for the 'zero' plural form.
     */
    public static final String KEYWORD_ZERO = "zero";

    /**
     * Value returned by {@link #getUniqueKeywordValue} when there is no
     * unique value to return.
     */
    public static final double NO_UNIQUE_VALUE = -0.00123456777d;

    private PluralRules() {
    }
}
