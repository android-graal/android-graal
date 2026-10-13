package android.icu.text;

/**
 * <code>UnicodeMatcher</code> defines a protocol for objects that can
 * match a range of characters in a Replaceable string.
 */
public interface UnicodeMatcher {
    /**
     * The character at index i, where i &lt; contextStart || i &gt;= contextLimit,
     * is ETHER.  This allows explicit matching by rules and UnicodeSets
     * of text outside the context.  In traditional terms, this allows anchoring
     * at the start and/or end.
     */
    char ETHER = '\uffff';

    /**
     * Constant returned by <code>matches()</code> indicating a
     * complete match between the text and this matcher.  For an
     * incremental variable-length match, this value is returned if
     * the given text matches, and it is known that additional
     * characters would not alter the extent of the match.
     */
    int U_MATCH = 2;

    /**
     * Constant returned by <code>matches()</code> indicating a
     * mismatch between the text and this matcher.  The text contains
     * a character which does not match, or the text does not contain
     * all desired characters for a non-incremental match.
     */
    int U_MISMATCH = 0;

    /**
     * Constant returned by <code>matches()</code> indicating a
     * partial match between the text and this matcher.  This value is
     * only returned for incremental match operations.  All characters
     * of the text match, but more characters are required for a
     * complete match.  Alternatively, for variable-length matchers,
     * all characters of the text match, and if more characters were
     * supplied at limit, they might also match.
     */
    int U_PARTIAL_MATCH = 1;
}
