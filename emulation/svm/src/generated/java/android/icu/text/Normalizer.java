package android.icu.text;

/**
 * Old Unicode normalization API.
 *
 * <p>This API has been replaced by the {@link Normalizer2} class and is only available
 * for backward compatibility. This class simply delegates to the Normalizer2 class.
 * There are two exceptions: The new API does not provide a replacement for
 * <code>QuickCheckResult</code> and <code>compare()</code>.
 *
 * <p><code>normalize</code> transforms Unicode text into an equivalent composed or
 * decomposed form, allowing for easier sorting and searching of text.
 * <code>normalize</code> supports the standard normalization forms described in
 * <a href="https://www.unicode.org/reports/tr15/" target="unicode">
 * Unicode Standard Annex #15 &mdash; Unicode Normalization Forms</a>.
 *
 * <p>Characters with accents or other adornments can be encoded in
 * several different ways in Unicode.  For example, take the character A-acute.
 * In Unicode, this can be encoded as a single character (the
 * "composed" form):
 *
 * <pre>
 *      00C1    LATIN CAPITAL LETTER A WITH ACUTE
 * </pre>
 *
 * or as two separate characters (the "decomposed" form):
 *
 * <pre>
 *      0041    LATIN CAPITAL LETTER A
 *      0301    COMBINING ACUTE ACCENT
 * </pre>
 *
 * <p>To a user of your program, however, both of these sequences should be
 * treated as the same "user-level" character "A with acute accent".  When you
 * are searching or comparing text, you must ensure that these two sequences are
 * treated equivalently.  In addition, you must handle characters with more than
 * one accent.  Sometimes the order of a character's combining accents is
 * significant, while in other cases accent sequences in different orders are
 * really equivalent.
 *
 * <p>Similarly, the string "ffi" can be encoded as three separate letters:
 *
 * <pre>
 *      0066    LATIN SMALL LETTER F
 *      0066    LATIN SMALL LETTER F
 *      0069    LATIN SMALL LETTER I
 * </pre>
 *
 * or as the single character
 *
 * <pre>
 *      FB03    LATIN SMALL LIGATURE FFI
 * </pre>
 *
 * <p>The ffi ligature is not a distinct semantic character, and strictly speaking
 * it shouldn't be in Unicode at all, but it was included for compatibility
 * with existing character sets that already provided it.  The Unicode standard
 * identifies such characters by giving them "compatibility" decompositions
 * into the corresponding semantic characters.  When sorting and searching, you
 * will often want to use these mappings.
 *
 * <p><code>normalize</code> helps solve these problems by transforming text into
 * the canonical composed and decomposed forms as shown in the first example
 * above. In addition, you can have it perform compatibility decompositions so
 * that you can treat compatibility characters the same as their equivalents.
 * Finally, <code>normalize</code> rearranges accents into the proper canonical
 * order, so that you do not have to worry about accent rearrangement on your
 * own.
 *
 * <p>Form FCD, "Fast C or D", is also designed for collation.
 * It allows to work on strings that are not necessarily normalized
 * with an algorithm (like in collation) that works under "canonical closure",
 * i.e., it treats precomposed characters and their decomposed equivalents the
 * same.
 *
 * <p>It is not a normalization form because it does not provide for uniqueness of
 * representation. Multiple strings may be canonically equivalent (their NFDs
 * are identical) and may all conform to FCD without being identical themselves.
 *
 * <p>The form is defined such that the "raw decomposition", the recursive
 * canonical decomposition of each character, results in a string that is
 * canonically ordered. This means that precomposed characters are allowed for
 * as long as their decompositions do not need canonical reordering.
 *
 * <p>Its advantage for a process like collation is that all NFD and most NFC texts
 * - and many unnormalized texts - already conform to FCD and do not need to be
 * normalized (NFD) for such a process. The FCD quick check will return YES for
 * most strings in practice.
 *
 * <p>normalize(FCD) may be implemented with NFD.
 *
 * <p>For more details on FCD see Unicode Technical Note #5 (Canonical Equivalence in Applications):
 * http://www.unicode.org/notes/tn5/#FCD
 *
 * <p>ICU collation performs either NFD or FCD normalization automatically if
 * normalization is turned on for the collator object. Beyond collation and
 * string search, normalized strings may be useful for string equivalence
 * comparisons, transliteration/transcription, unique representations, etc.
 *
 * <p>The W3C generally recommends to exchange texts in NFC.
 * Note also that most legacy character encodings use only precomposed forms and
 * often do not encode any combining marks by themselves. For conversion to such
 * character encodings the Unicode text needs to be normalized to NFC.
 * For more usage examples, see the Unicode Standard Annex.
 *
 * <p>Note: The Normalizer class also provides API for iterative normalization.
 * While the setIndex() and getIndex() refer to indices in the
 * underlying Unicode input text, the next() and previous() methods
 * iterate through characters in the normalized output.
 * This means that there is not necessarily a one-to-one correspondence
 * between characters returned by next() and previous() and the indices
 * passed to and returned from setIndex() and getIndex().
 * It is for this reason that Normalizer does not implement the CharacterIterator interface.
 */
public final class Normalizer {
    /**
     * Option bit for compare:
     * Compare strings in code point order instead of code unit order.
     */
    public static final int COMPARE_CODE_POINT_ORDER = 32768;

    /**
     * Option bit for compare:
     * Perform case-insensitive comparison.
     */
    public static final int COMPARE_IGNORE_CASE = 65536;

    /**
     * Option bit for compare:
     * Case sensitively compare the strings
     */
    public static final int FOLD_CASE_DEFAULT = 0;

    /**
     * Option value for case folding:
     * Use the modified set of mappings provided in CaseFolding.txt to handle dotted I
     * and dotless i appropriately for Turkic languages (tr, az).
     * @see UCharacter#FOLD_CASE_EXCLUDE_SPECIAL_I
     */
    public static final int FOLD_CASE_EXCLUDE_SPECIAL_I = 1;

    /**
     * Option bit for compare:
     * Both input strings are assumed to fulfill FCD conditions.
     */
    public static final int INPUT_IS_FCD = 131072;

    private Normalizer() {
    }
}
