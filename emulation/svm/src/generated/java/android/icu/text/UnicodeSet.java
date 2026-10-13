package android.icu.text;

/**
 * A mutable set of Unicode characters and multicharacter strings.
 * Objects of this class represent <em>character classes</em> used
 * in regular expressions. A character specifies a subset of Unicode
 * code points.  Legal code points are U+0000 to U+10FFFF, inclusive.
 *
 * Note: method freeze() will not only make the set immutable, but
 * also makes important methods much higher performance:
 * contains(c), containsNone(...), span(...), spanBack(...) etc.
 * After the object is frozen, any subsequent call that wants to change
 * the object will throw UnsupportedOperationException.
 *
 * <p>The UnicodeSet class is not designed to be subclassed.
 *
 * <p><code>UnicodeSet</code> supports two APIs. The first is the
 * <em>operand</em> API that allows the caller to modify the value of
 * a <code>UnicodeSet</code> object. It conforms to Java 2's
 * <code>java.util.Set</code> interface, although
 * <code>UnicodeSet</code> does not actually implement that
 * interface. All methods of <code>Set</code> are supported, with the
 * modification that they take a character range or single character
 * instead of an <code>Object</code>, and they take a
 * <code>UnicodeSet</code> instead of a <code>Collection</code>.  The
 * operand API may be thought of in terms of boolean logic: a boolean
 * OR is implemented by <code>add</code>, a boolean AND is implemented
 * by <code>retain</code>, a boolean XOR is implemented by
 * <code>complement</code> taking an argument, and a boolean NOT is
 * implemented by <code>complement</code> with no argument.  In terms
 * of traditional set theory function names, <code>add</code> is a
 * union, <code>retain</code> is an intersection, <code>remove</code>
 * is an asymmetric difference, and <code>complement</code> with no
 * argument is a set complement with respect to the superset range
 * <code>MIN_VALUE-MAX_VALUE</code>
 *
 * <p>The second API is the
 * <code>applyPattern()</code>/<code>toPattern()</code> API from the
 * <code>java.text.Format</code>-derived classes.  Unlike the
 * methods that add characters, add categories, and control the logic
 * of the set, the method <code>applyPattern()</code> sets all
 * attributes of a <code>UnicodeSet</code> at once, based on a
 * string pattern.
 *
 * <p><b>Pattern syntax</b></p>
 *
 * Patterns are accepted by the constructors and the
 * <code>applyPattern()</code> methods and returned by the
 * <code>toPattern()</code> method.  These patterns follow a syntax
 * similar to that employed by version 8 regular expression character
 * classes.  Here are some simple examples:
 *
 * <blockquote>
 *   <table>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top; horizontal-align: left;"><code>[]</code></td>
 *       <td style="vertical-align: top;">No characters</td>
 *     </tr><tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top; horizontal-align: left;"><code>[a]</code></td>
 *       <td style="vertical-align: top;">The character 'a'</td>
 *     </tr><tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top; horizontal-align: left;"><code>[ae]</code></td>
 *       <td style="vertical-align: top;">The characters 'a' and 'e'</td>
 *     </tr>
 *     <tr>
 *       <td style="white-space: nowrap; vertical-align: top; horizontal-align: left;"><code>[a-e]</code></td>
 *       <td style="vertical-align: top;">The characters 'a' through 'e' inclusive, in Unicode code
 *       point order</td>
 *     </tr>
 *     <tr>
 *       <td style="white-space: nowrap; vertical-align: top; horizontal-align: left;"><code>[\\u4E01]</code></td>
 *       <td style="vertical-align: top;">The character U+4E01</td>
 *     </tr>
 *     <tr>
 *       <td style="white-space: nowrap; vertical-align: top; horizontal-align: left;"><code>[a{ab}{ac}]</code></td>
 *       <td style="vertical-align: top;">The character 'a' and the multicharacter strings &quot;ab&quot; and
 *       &quot;ac&quot;</td>
 *     </tr>
 *     <tr>
 *       <td style="white-space: nowrap; vertical-align: top; horizontal-align: left;"><code>[\p{Lu}]</code></td>
 *       <td style="vertical-align: top;">All characters in the general category Uppercase Letter</td>
 *     </tr>
 *   </table>
 * </blockquote>
 *
 * Any character may be preceded by a backslash in order to remove any special
 * meaning.  White space characters, as defined by the Unicode Pattern_White_Space property, are
 * ignored, unless they are escaped.
 *
 * <p>Property patterns specify a set of characters having a certain
 * property as defined by the Unicode standard.  Both the POSIX-like
 * "[:Lu:]" and the Perl-like syntax "\p{Lu}" are recognized.  For a
 * complete list of supported property patterns, see the User's Guide
 * for UnicodeSet at
 * <a href="https://unicode-org.github.io/icu/userguide/strings/unicodeset">
 * https://unicode-org.github.io/icu/userguide/strings/unicodeset</a>.
 * Actual determination of property data is defined by the underlying
 * Unicode database as implemented by UCharacter.
 *
 * <p>Patterns specify individual characters, ranges of characters, and
 * Unicode property sets.  When elements are concatenated, they
 * specify their union.  To complement a set, place a '^' immediately
 * after the opening '['.  Property patterns are inverted by modifying
 * their delimiters; "[:^foo]" and "\P{foo}".  In any other location,
 * '^' has no special meaning.
 *
 * <p>Since ICU 70, "[^...]", "[:^foo]", "\P{foo}", and "[:binaryProperty=No:]"
 * perform a “code point complement” (all code points minus the original set),
 * removing all multicharacter strings,
 * equivalent to .{@link #complement()}.{@link #removeAllStrings()} .
 * The {@link #complement()} API function continues to perform a
 * symmetric difference with all code points and thus retains all multicharacter strings.
 *
 * <p>Ranges are indicated by placing two a '-' between two
 * characters, as in "a-z".  This specifies the range of all
 * characters from the left to the right, in Unicode order.  If the
 * left character is greater than or equal to the
 * right character it is a syntax error.  If a '-' occurs as the first
 * character after the opening '[' or '[^', or if it occurs as the
 * last character before the closing ']', then it is taken as a
 * literal.  Thus "[a\\-b]", "[-ab]", and "[ab-]" all indicate the same
 * set of three characters, 'a', 'b', and '-'.
 *
 * <p>Sets may be intersected using the '&amp;' operator or the asymmetric
 * set difference may be taken using the '-' operator, for example,
 * "[[:L:]&amp;[\\u0000-\\u0FFF]]" indicates the set of all Unicode letters
 * with values less than 4096.  Operators ('&amp;' and '|') have equal
 * precedence and bind left-to-right.  Thus
 * "[[:L:]-[a-z]-[\\u0100-\\u01FF]]" is equivalent to
 * "[[[:L:]-[a-z]]-[\\u0100-\\u01FF]]".  This only really matters for
 * difference; intersection is commutative.
 *
 * <table>
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[a]</code><td>The set containing 'a'
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[a-z]</code><td>The set containing 'a'
 * through 'z' and all letters in between, in Unicode order
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[^a-z]</code><td>The set containing
 * all characters but 'a' through 'z',
 * that is, U+0000 through 'a'-1 and 'z'+1 through U+10FFFF
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[[<em>pat1</em>][<em>pat2</em>]]</code>
 * <td>The union of sets specified by <em>pat1</em> and <em>pat2</em>
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[[<em>pat1</em>]&amp;[<em>pat2</em>]]</code>
 * <td>The intersection of sets specified by <em>pat1</em> and <em>pat2</em>
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[[<em>pat1</em>]-[<em>pat2</em>]]</code>
 * <td>The asymmetric difference of sets specified by <em>pat1</em> and
 * <em>pat2</em>
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[:Lu:] or \p{Lu}</code>
 * <td>The set of characters having the specified
 * Unicode property; in
 * this case, Unicode uppercase letters
 * <tr style="vertical-align: top;"><td style="white-space: nowrap;"><code>[:^Lu:] or \P{Lu}</code>
 * <td>The set of characters <em>not</em> having the given
 * Unicode property
 * </table>
 *
 * <p><b>Formal syntax</b></p>
 *
 * <blockquote>
 *   <table>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>pattern :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><code>('[' '^'? item* ']') |
 *       property</code></td>
 *     </tr>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>item :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><code>char | (char '-' char) | pattern-expr<br>
 *       </code></td>
 *     </tr>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>pattern-expr :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><code>pattern | pattern-expr pattern |
 *       pattern-expr op pattern<br>
 *       </code></td>
 *     </tr>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>op :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><code>'&amp;' | '-'<br>
 *       </code></td>
 *     </tr>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>special :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><code>'[' | ']' | '-'<br>
 *       </code></td>
 *     </tr>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>char :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><em>any character that is not</em><code> special<br>
 *       | ('\\' </code><em>any character</em><code>)<br>
 *       | ('&#92;u' hex hex hex hex)<br>
 *       </code></td>
 *     </tr>
 *     <tr style="vertical-align: top">
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>hex :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><code>'0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' |<br>
 *       &nbsp;&nbsp;&nbsp;&nbsp;'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'a' | 'b' | 'c' | 'd' | 'e' | 'f'</code></td>
 *     </tr>
 *     <tr>
 *       <td style="white-space: nowrap; vertical-align: top;" align="right"><code>property :=&nbsp; </code></td>
 *       <td style="vertical-align: top;"><em>a Unicode property set pattern</em></td>
 *     </tr>
 *   </table>
 *   <br>
 *   <table border="1">
 *     <tr>
 *       <td>Legend: <table>
 *         <tr>
 *           <td style="white-space: nowrap; vertical-align: top;"><code>a := b</code></td>
 *           <td style="width: 20; vertical-align: top;">&nbsp; </td>
 *           <td style="vertical-align: top;"><code>a</code> may be replaced by <code>b</code> </td>
 *         </tr>
 *         <tr>
 *           <td style="white-space: nowrap; vertical-align: top;"><code>a?</code></td>
 *           <td style="vertical-align: top;"></td>
 *           <td style="vertical-align: top;">zero or one instance of <code>a</code><br>
 *           </td>
 *         </tr>
 *         <tr>
 *           <td style="white-space: nowrap; vertical-align: top;"><code>a*</code></td>
 *           <td style="vertical-align: top;"></td>
 *           <td style="vertical-align: top;">one or more instances of <code>a</code><br>
 *           </td>
 *         </tr>
 *         <tr>
 *           <td style="white-space: nowrap; vertical-align: top;"><code>a | b</code></td>
 *           <td style="vertical-align: top;"></td>
 *           <td style="vertical-align: top;">either <code>a</code> or <code>b</code><br>
 *           </td>
 *         </tr>
 *         <tr>
 *           <td style="white-space: nowrap; vertical-align: top;"><code>'a'</code></td>
 *           <td style="vertical-align: top;"></td>
 *           <td style="vertical-align: top;">the literal string between the quotes </td>
 *         </tr>
 *       </table>
 *       </td>
 *     </tr>
 *   </table>
 * </blockquote>
 *
 * <p>To iterate over contents of {@code UnicodeSet}, the following are available:
 * <ul>
 *   <li>to iterate over the ranges: {@link #ranges()}, {@link #rangeStream()}</li>
 *   <li>to iterate over the strings: {@link #strings()}, {@link #stringStream()}</li>
 *   <li>to iterate over the code points: {@link #codePoints()}, {@link #codePointStream()}</li>
 *   <li>to iterate over the entire contents in a single loop: this class itself is {@link Iterable},
 *       or use {@link #stream()}.<br>
 *       All of these method are, however, not particularly efficient,
 *       since they convert each individual code point to a {@code String}.
 * </ul>
 *
 * <p>The iterators and streams methods work as expected in idiomatic Java usage.<br>
 * The {@link UnicodeSetIterator} cannot be used in <b>for</b> loops, and it is not very Java-idiomatic, because it is old.
 * But it might be faster in certain use cases. We recommend that you measure in performance sensitive code.<br>
 *
 * <p>To replace, count elements, or delete spans, see {@link android.icu.text.UnicodeSetSpanner UnicodeSetSpanner}.
 *
 * @author Alan Liu
 * @see UnicodeSetIterator
 * @see UnicodeSetSpanner
 */
public final class UnicodeSet {
    /**
     * Adds all case mappings for each element in the set.
     * This adds the full lower-, title-, and uppercase mappings as well as the full case folding
     * of each existing element in the set.
     *
     * <p>This value is an options bit set value for some
     * constructors, applyPattern(), and closeOver().
     * It can be ORed together with other, unrelated options.
     *
     * <p>Unlike the “case insensitive” options, this does not perform a closure.
     * For example, it does not add 'ſ' (U+017F long s) for 's',
     * 'K' (U+212A Kelvin sign) for 'k', or replace set strings by their case-folded versions.
     */
    public static final int ADD_CASE_MAPPINGS = 4;

    /**
     * Alias for {@link #CASE_INSENSITIVE}.
     *
     * @deprecated ICU 73 Use {@link #CASE_INSENSITIVE} instead.
     */
    @Deprecated
    public static final int CASE = 2;

    /**
     * Enable case insensitive matching.  E.g., "[ab]" with this flag
     * will match 'a', 'A', 'b', and 'B'.  "[^ab]" with this flag will
     * match all except 'a', 'A', 'b', and 'B'. This performs a full
     * closure over case mappings, e.g. 'ſ' (U+017F long s) for 's'.
     *
     * <p>This value is an options bit set value for some
     * constructors, applyPattern(), and closeOver().
     * It can be ORed together with other, unrelated options.
     *
     * <p>The resulting set is a superset of the input for the code points but
     * not for the strings.
     * It performs a case mapping closure of the code points and adds
     * full case folding strings for the code points, and reduces strings of
     * the original set to their full case folding equivalents.
     *
     * <p>This is designed for case-insensitive matches, for example
     * in regular expressions. The full code point case closure allows checking of
     * an input character directly against the closure set.
     * Strings are matched by comparing the case-folded form from the closure
     * set with an incremental case folding of the string in question.
     *
     * <p>The closure set will also contain single code points if the original
     * set contained case-equivalent strings (like U+00DF for "ss" or "Ss" etc.).
     * This is not necessary (that is, redundant) for the above matching method
     * but results in the same closure sets regardless of whether the original
     * set contained the code point or a string.
     */
    public static final int CASE_INSENSITIVE = 2;

    /**
     * Bitmask for constructor and applyPattern() indicating that
     * white space should be ignored.  If set, ignore Unicode Pattern_White_Space characters,
     * unless they are quoted or escaped.  This may be ORed together
     * with other selectors.
     */
    public static final int IGNORE_SPACE = 1;

    /**
     * Maximum value that can be stored in a UnicodeSet.
     */
    public static final int MAX_VALUE = 1114111;

    /**
     * Minimum value that can be stored in a UnicodeSet.
     */
    public static final int MIN_VALUE = 0;

    /**
     * Enable case insensitive matching.
     * Same as {@link #CASE_INSENSITIVE} but using only Simple_Case_Folding (scf) mappings,
     * which map each code point to one code point,
     * not full Case_Folding (cf) mappings, which map some code points to multiple code points.
     *
     * <p>This is designed for case-insensitive matches, for example in certain
     * regular expression implementations where only Simple_Case_Folding mappings are used,
     * such as in ECMAScript (JavaScript) regular expressions.
     *
     * <p>This value is an options bit set value for some
     * constructors, applyPattern(), and closeOver().
     * It can be ORed together with other, unrelated options.
     */
    public static final int SIMPLE_CASE_INSENSITIVE = 6;

    private UnicodeSet() {
    }
}
