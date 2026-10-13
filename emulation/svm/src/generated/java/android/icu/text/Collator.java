package android.icu.text;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.text.Collator}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p>Collator performs locale-sensitive string comparison. A concrete
 * subclass, RuleBasedCollator, allows customization of the collation
 * ordering by the use of rule sets.
 *
 * <p>A Collator is thread-safe only when frozen. See {@link #isFrozen()} and {@link Freezable}.
 *
 * <p>Following the <a href=http://www.unicode.org>Unicode
 * Consortium</a>'s specifications for the
 * <a href="https://www.unicode.org/reports/tr10/">Unicode Collation
 * Algorithm (UCA)</a>, there are 5 different levels of strength used
 * in comparisons:
 *
 * <ul>
 * <li>PRIMARY strength: Typically, this is used to denote differences between
 *     base characters (for example, "a" &lt; "b").
 *     It is the strongest difference. For example, dictionaries are divided
 *     into different sections by base character.
 * <li>SECONDARY strength: Accents in the characters are considered secondary
 *     differences (for example, "as" &lt; "&agrave;s" &lt; "at"). Other
 *     differences
 *     between letters can also be considered secondary differences, depending
 *     on the language. A secondary difference is ignored when there is a
 *     primary difference anywhere in the strings.
 * <li>TERTIARY strength: Upper and lower case differences in characters are
 *     distinguished at tertiary strength (for example, "ao" &lt; "Ao" &lt;
 *     "a&ograve;"). In addition, a variant of a letter differs from the base
 *     form on the tertiary strength (such as "A" and "Ⓐ"). Another
 *     example is the
 *     difference between large and small Kana. A tertiary difference is ignored
 *     when there is a primary or secondary difference anywhere in the strings.
 * <li>QUATERNARY strength: When punctuation is ignored
 *     (see <a href="https://unicode-org.github.io/icu/userguide/collation/concepts#ignoring-punctuation">
 *     Ignoring Punctuations in the User Guide</a>) at PRIMARY to TERTIARY
 *     strength, an additional strength level can
 *     be used to distinguish words with and without punctuation (for example,
 *     "ab" &lt; "a-b" &lt; "aB").
 *     This difference is ignored when there is a PRIMARY, SECONDARY or TERTIARY
 *     difference. The QUATERNARY strength should only be used if ignoring
 *     punctuation is required.
 * <li>IDENTICAL strength:
 *     When all other strengths are equal, the IDENTICAL strength is used as a
 *     tiebreaker. The Unicode code point values of the NFD form of each string
 *     are compared, just in case there is no difference.
 *     For example, Hebrew cantillation marks are only distinguished at this
 *     strength. This strength should be used sparingly, as only code point
 *     value differences between two strings is an extremely rare occurrence.
 *     Using this strength substantially decreases the performance for both
 *     comparison and collation key generation APIs. This strength also
 *     increases the size of the collation key.
 * </ul>
 *
 * Unlike the JDK, ICU4J's Collator deals only with 2 decomposition modes,
 * the canonical decomposition mode and one that does not use any decomposition.
 * The compatibility decomposition mode, java.text.Collator.FULL_DECOMPOSITION
 * is not supported here. If the canonical
 * decomposition mode is set, the Collator handles un-normalized text properly,
 * producing the same results as if the text were normalized in NFD. If
 * canonical decomposition is turned off, it is the user's responsibility to
 * ensure that all text is already in the appropriate form before performing
 * a comparison or before getting a CollationKey.
 *
 * <p>For more information about the collation service see the
 * <a href="https://unicode-org.github.io/icu/userguide/collation">User Guide</a>.
 *
 * <p>Examples of use
 * <pre>
 * // Get the Collator for US English and set its strength to PRIMARY
 * Collator usCollator = Collator.getInstance(Locale.US);
 * usCollator.setStrength(Collator.PRIMARY);
 * if (usCollator.compare("abc", "ABC") == 0) {
 *     System.out.println("Strings are equivalent");
 * }
 *
 * The following example shows how to compare two strings using the
 * Collator for the default locale.
 *
 * // Compare two strings in the default locale
 * Collator myCollator = Collator.getInstance();
 * myCollator.setDecomposition(NO_DECOMPOSITION);
 * if (myCollator.compare("&agrave;&#92;u0325", "a&#92;u0325&#768;") != 0) {
 *     System.out.println("&agrave;&#92;u0325 is not equals to a&#92;u0325&#768; without decomposition");
 *     myCollator.setDecomposition(CANONICAL_DECOMPOSITION);
 *     if (myCollator.compare("&agrave;&#92;u0325", "a&#92;u0325&#768;") != 0) {
 *         System.out.println("Error: &agrave;&#92;u0325 should be equals to a&#92;u0325&#768; with decomposition");
 *     }
 *     else {
 *         System.out.println("&agrave;&#92;u0325 is equals to a&#92;u0325&#768; with decomposition");
 *     }
 * }
 * else {
 *     System.out.println("Error: &agrave;&#92;u0325 should be not equals to a&#92;u0325&#768; without decomposition");
 * }
 * </pre>
 *
 * @see RuleBasedCollator
 * @see CollationKey
 * @author Syn Wee Quek
 */
public final class Collator {
    /**
     * Decomposition mode value. With CANONICAL_DECOMPOSITION set,
     * characters that are canonical variants according to the Unicode standard
     * will be decomposed for collation.
     *
     * <p>CANONICAL_DECOMPOSITION corresponds to Normalization Form D as
     * described in <a href="https://www.unicode.org/reports/tr15/">
     * Unicode Technical Report #15</a>.
     *
     * @see #NO_DECOMPOSITION
     * @see #getDecomposition
     * @see #setDecomposition
     */
    public static final int CANONICAL_DECOMPOSITION = 17;

    /**
     * <strong>[icu] Note:</strong> This is for backwards compatibility with Java APIs only.  It
     * should not be used, IDENTICAL should be used instead.  ICU's
     * collation does not support Java's FULL_DECOMPOSITION mode.
     */
    public static final int FULL_DECOMPOSITION = 15;

    /**
     * Smallest Collator strength value. When all other strengths are equal,
     * the IDENTICAL strength is used as a tiebreaker. The Unicode code point
     * values of the NFD form of each string are compared, just in case there
     * is no difference.
     * See class documentation for more explanation.
     * <p>
     * Note this value is different from JDK's
     */
    public static final int IDENTICAL = 15;

    /**
     * Decomposition mode value. With NO_DECOMPOSITION set, Strings
     * will not be decomposed for collation. This is the default
     * decomposition setting unless otherwise specified by the locale
     * used to create the Collator.
     *
     * <p><strong>Note</strong> this value is different from the JDK's.
     * @see #CANONICAL_DECOMPOSITION
     * @see #getDecomposition
     * @see #setDecomposition
     */
    public static final int NO_DECOMPOSITION = 16;

    /**
     * Strongest collator strength value. Typically used to denote differences
     * between base characters. See class documentation for more explanation.
     * @see #setStrength
     * @see #getStrength
     */
    public static final int PRIMARY = 0;

    /**
     * <strong>[icu]</strong> Fourth level collator strength value.
     * When punctuation is ignored
     * (see <a href="https://unicode-org.github.io/icu/userguide/collation/concepts#ignoring-punctuation">
     * Ignoring Punctuation in the User Guide</a>) at PRIMARY to TERTIARY
     * strength, an additional strength level can
     * be used to distinguish words with and without punctuation.
     * See class documentation for more explanation.
     * @see #setStrength
     * @see #getStrength
     */
    public static final int QUATERNARY = 3;

    /**
     * Second level collator strength value.
     * Accents in the characters are considered secondary differences.
     * Other differences between letters can also be considered secondary
     * differences, depending on the language.
     * See class documentation for more explanation.
     * @see #setStrength
     * @see #getStrength
     */
    public static final int SECONDARY = 1;

    /**
     * Third level collator strength value.
     * Upper and lower case differences in characters are distinguished at this
     * strength level. In addition, a variant of a letter differs from the base
     * form on the tertiary level.
     * See class documentation for more explanation.
     * @see #setStrength
     * @see #getStrength
     */
    public static final int TERTIARY = 2;

    private Collator() {
    }

    /**
     * Reordering codes for non-script groups that can be reordered under collation.
     *
     * @see #getReorderCodes
     * @see #setReorderCodes
     * @see #getEquivalentReorderCodes
     */
    public interface ReorderCodes {
        /**
         * Characters with the currency property.
         * This is equivalent to the rule value "currency".
         */
        int CURRENCY = 4099;

        /**
         * A special reordering code that is used to specify the default reordering codes for a locale.
         */
        int DEFAULT = -1;

        /**
         * Characters with the digit property.
         * This is equivalent to the rule value "digit".
         */
        int DIGIT = 4100;

        /**
         * The first entry in the enumeration of reordering groups. This is intended for use in
         * range checking and enumeration of the reorder codes.
         */
        int FIRST = 4096;

        /**
         * A special reordering code that is used to specify no reordering codes.
         */
        int NONE = 103;

        /**
         * A special reordering code that is used to specify all other codes used for reordering except
         * for the codes listed as ReorderingCodes and those listed explicitly in a reordering.
         */
        int OTHERS = 103;

        /**
         * Characters with the punctuation property.
         * This is equivalent to the rule value "punct".
         */
        int PUNCTUATION = 4097;

        /**
         * Characters with the space property.
         * This is equivalent to the rule value "space".
         */
        int SPACE = 4096;

        /**
         * Characters with the symbol property.
         * This is equivalent to the rule value "symbol".
         */
        int SYMBOL = 4098;
    }
}
