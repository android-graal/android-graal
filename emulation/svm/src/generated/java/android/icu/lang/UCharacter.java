package android.icu.lang;

/**
 * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.lang.Character}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
 *
 * <p>The UCharacter class provides extensions to the {@link java.lang.Character} class.
 * These extensions provide support for more Unicode properties.
 * Each ICU release supports the latest version of Unicode available at that time.
 *
 * <p>For some time before Java 5 added support for supplementary Unicode code points,
 * The ICU UCharacter class and many other ICU classes already supported them.
 * Some UCharacter methods and constants were widened slightly differently than
 * how the Character class methods and constants were widened later.
 * In particular, {@link Character#MAX_VALUE} is still a char with the value U+FFFF,
 * while the {@link UCharacter#MAX_VALUE} is an int with the value U+10FFFF.
 *
 * <p>Code points are represented in these API using ints. While it would be
 * more convenient in Java to have a separate primitive datatype for them,
 * ints suffice in the meantime.
 *
 * <p>Aside from the additions for UTF-16 support, and the updated Unicode
 * properties, the main differences between UCharacter and Character are:
 * <ul>
 * <li> UCharacter is not designed to be a char wrapper and does not have
 *      APIs to which involves management of that single char.<br>
 *      These include:
 *      <ul>
 *        <li> char charValue(),
 *        <li> int compareTo(java.lang.Character, java.lang.Character), etc.
 *      </ul>
 * <li> UCharacter does not include Character APIs that are deprecated, nor
 *      does it include the Java-specific character information, such as
 *      boolean isJavaIdentifierPart(char ch).
 * <li> Character maps characters 'A' - 'Z' and 'a' - 'z' to the numeric
 *      values '10' - '35'. UCharacter also does this in digit and
 *      getNumericValue, to adhere to the java semantics of these
 *      methods.  New methods unicodeDigit, and
 *      getUnicodeNumericValue do not treat the above code points
 *      as having numeric values.  This is a semantic change from ICU4J 1.3.1.
 * </ul>
 * <p>
 * In addition to Java compatibility functions, which calculate derived properties,
 * this API provides low-level access to the Unicode Character Database.
 * <p>
 * Unicode assigns each code point (not just assigned character) values for
 * many properties.
 * Most of them are simple boolean flags, or constants from a small enumerated list.
 * For some properties, values are strings or other relatively more complex types.
 * <p>
 * For more information see
 * <a href="http://www.unicode/org/ucd/">"About the Unicode Character Database"</a>
 * (http://www.unicode.org/ucd/)
 * and the <a href="https://unicode-org.github.io/icu/userguide/strings/properties">ICU
 * User Guide chapter on Properties</a>
 * (https://unicode-org.github.io/icu/userguide/strings/properties).
 * <p>
 * There are also functions that provide easy migration from C/POSIX functions
 * like isblank(). Their use is generally discouraged because the C/POSIX
 * standards do not define their semantics beyond the ASCII range, which means
 * that different implementations exhibit very different behavior.
 * Instead, Unicode properties should be used directly.
 * <p>
 * There are also only a few, broad C/POSIX character classes, and they tend
 * to be used for conflicting purposes. For example, the "isalpha()" class
 * is sometimes used to determine word boundaries, while a more sophisticated
 * approach would at least distinguish initial letters from continuation
 * characters (the latter including combining marks).
 * (In ICU, BreakIterator is the most sophisticated API for word boundaries.)
 * Another example: There is no "istitle()" class for titlecase characters.
 * <p>
 * ICU 3.4 and later provides API access for all twelve C/POSIX character classes.
 * ICU implements them according to the Standard Recommendations in
 * Annex C: Compatibility Properties of UTS #18 Unicode Regular Expressions
 * (http://www.unicode.org/reports/tr18/#Compatibility_Properties).
 * <p>
 * API access for C/POSIX character classes is as follows:
 * <pre>{@code
 * - alpha:     isUAlphabetic(c) or hasBinaryProperty(c, UProperty.ALPHABETIC)
 * - lower:     isULowercase(c) or hasBinaryProperty(c, UProperty.LOWERCASE)
 * - upper:     isUUppercase(c) or hasBinaryProperty(c, UProperty.UPPERCASE)
 * - punct:     ((1<<getType(c)) & ((1<<DASH_PUNCTUATION)|(1<<START_PUNCTUATION)|
 *               (1<<END_PUNCTUATION)|(1<<CONNECTOR_PUNCTUATION)|(1<<OTHER_PUNCTUATION)|
 *               (1<<INITIAL_PUNCTUATION)|(1<<FINAL_PUNCTUATION)))!=0
 * - digit:     isDigit(c) or getType(c)==DECIMAL_DIGIT_NUMBER
 * - xdigit:    hasBinaryProperty(c, UProperty.POSIX_XDIGIT)
 * - alnum:     hasBinaryProperty(c, UProperty.POSIX_ALNUM)
 * - space:     isUWhiteSpace(c) or hasBinaryProperty(c, UProperty.WHITE_SPACE)
 * - blank:     hasBinaryProperty(c, UProperty.POSIX_BLANK)
 * - cntrl:     getType(c)==CONTROL
 * - graph:     hasBinaryProperty(c, UProperty.POSIX_GRAPH)
 * - print:     hasBinaryProperty(c, UProperty.POSIX_PRINT)}</pre>
 * <p>
 * The C/POSIX character classes are also available in UnicodeSet patterns,
 * using patterns like [:graph:] or \p{graph}.
 *
 * <p><strong>[icu] Note:</strong> There are several ICU (and Java) whitespace functions.
 * Comparison:<ul>
 * <li> isUWhiteSpace=UCHAR_WHITE_SPACE: Unicode White_Space property;
 *       most of general categories "Z" (separators) + most whitespace ISO controls
 *       (including no-break spaces, but excluding IS1..IS4)
 * <li> isWhitespace: Java isWhitespace; Z + whitespace ISO controls but excluding no-break spaces
 * <li> isSpaceChar: just Z (including no-break spaces)</ul>
 *
 * <p>
 * This class is not subclassable.
 *
 * @author Syn Wee Quek
 * @see android.icu.lang.UCharacterEnums
 */
public final class UCharacter implements UCharacterEnums.ECharacterCategory, UCharacterEnums.ECharacterDirection {
    /**
     * <strong>[icu]</strong> Option value for case folding: use default mappings defined in
     * CaseFolding.txt.
     */
    public static final int FOLD_CASE_DEFAULT = 0;

    /**
     * <strong>[icu]</strong> Option value for case folding:
     * Use the modified set of mappings provided in CaseFolding.txt to handle dotted I
     * and dotless i appropriately for Turkic languages (tr, az).
     *
     * <p>Before Unicode 3.2, CaseFolding.txt contains mappings marked with 'I' that
     * are to be included for default mappings and
     * excluded for the Turkic-specific mappings.
     *
     * <p>Unicode 3.2 CaseFolding.txt instead contains mappings marked with 'T' that
     * are to be excluded for default mappings and
     * included for the Turkic-specific mappings.
     */
    public static final int FOLD_CASE_EXCLUDE_SPECIAL_I = 1;

    /**
     * Constant U+10FFFF, same as {@link Character#MAX_CODE_POINT}.
     */
    public static final int MAX_CODE_POINT = 1114111;

    /**
     * Constant U+DBFF, same as {@link Character#MAX_HIGH_SURROGATE}.
     */
    public static final char MAX_HIGH_SURROGATE = '\udbff';

    /**
     * Constant U+DFFF, same as {@link Character#MAX_LOW_SURROGATE}.
     */
    public static final char MAX_LOW_SURROGATE = '\udfff';

    /**
     * Compatibility constant for Java Character's MAX_RADIX.
     */
    public static final int MAX_RADIX = 36;

    /**
     * Constant U+DFFF, same as {@link Character#MAX_SURROGATE}.
     */
    public static final char MAX_SURROGATE = '\udfff';

    /**
     * The highest Unicode code point value (scalar value), constant U+10FFFF (uses 21 bits).
     * Same as {@link Character#MAX_CODE_POINT}.
     *
     * <p>Up-to-date Unicode implementation of {@link Character#MAX_VALUE}
     * which is still a char with the value U+FFFF.
     */
    public static final int MAX_VALUE = 1114111;

    /**
     * Constant U+0000, same as {@link Character#MIN_CODE_POINT}.
     */
    public static final int MIN_CODE_POINT = 0;

    /**
     * Constant U+D800, same as {@link Character#MIN_HIGH_SURROGATE}.
     */
    public static final char MIN_HIGH_SURROGATE = '\ud800';

    /**
     * Constant U+DC00, same as {@link Character#MIN_LOW_SURROGATE}.
     */
    public static final char MIN_LOW_SURROGATE = '\udc00';

    /**
     * Compatibility constant for Java Character's MIN_RADIX.
     */
    public static final int MIN_RADIX = 2;

    /**
     * Constant U+10000, same as {@link Character#MIN_SUPPLEMENTARY_CODE_POINT}.
     */
    public static final int MIN_SUPPLEMENTARY_CODE_POINT = 65536;

    /**
     * Constant U+D800, same as {@link Character#MIN_SURROGATE}.
     */
    public static final char MIN_SURROGATE = '\ud800';

    /**
     * The lowest Unicode code point value, constant 0.
     * Same as {@link Character#MIN_CODE_POINT}, same integer value as {@link Character#MIN_VALUE}.
     */
    public static final int MIN_VALUE = 0;

    /**
     * Special value that is returned by getUnicodeNumericValue(int) when no
     * numeric value is defined for a code point.
     * @see #getUnicodeNumericValue
     */
    public static final double NO_NUMERIC_VALUE = -1.23456789E8d;

    /**
     * Unicode value used when translating into Unicode encoding form and there
     * is no existing character.
     */
    public static final int REPLACEMENT_CHAR = 65533;

    /**
     * The minimum value for Supplementary code points, constant U+10000.
     * Same as {@link Character#MIN_SUPPLEMENTARY_CODE_POINT}.
     */
    public static final int SUPPLEMENTARY_MIN_VALUE = 65536;

    /**
     * Do not adjust the titlecasing indexes from BreakIterator::next() indexes;
     * titlecase exactly the characters at breaks from the iterator.
     * Option bit for titlecasing APIs that take an options bit set.
     *
     * By default, titlecasing will take each break iterator index,
     * adjust it by looking for the next cased character, and titlecase that one.
     * Other characters are lowercased.
     *
     * This follows Unicode 4 &amp; 5 section 3.13 Default Case Operations:
     *
     * R3  toTitlecase(X): Find the word boundaries based on Unicode Standard Annex
     * #29, "Text Boundaries." Between each pair of word boundaries, find the first
     * cased character F. If F exists, map F to default_title(F); then map each
     * subsequent character C to default_lower(C).
     *
     * @see #toTitleCase
     * @see #TITLECASE_NO_LOWERCASE
     */
    public static final int TITLECASE_NO_BREAK_ADJUSTMENT = 512;

    /**
     * Do not lowercase non-initial parts of words when titlecasing.
     * Option bit for titlecasing APIs that take an options bit set.
     *
     * By default, titlecasing will titlecase the first cased character
     * of a word and lowercase all other characters.
     * With this option, the other characters will not be modified.
     *
     * @see #toTitleCase
     */
    public static final int TITLECASE_NO_LOWERCASE = 256;

    private UCharacter() {
    }

    /**
     * Bidi Paired Bracket Type constants.
     *
     * @see UProperty#BIDI_PAIRED_BRACKET_TYPE
     */
    public interface BidiPairedBracketType {
        /**
         * Close paired bracket.
         */
        int CLOSE = 2;

        /**
         * Not a paired bracket.
         */
        int NONE = 0;

        /**
         * Open paired bracket.
         */
        int OPEN = 1;
    }

    /**
     * Decomposition Type constants.
     * @see UProperty#DECOMPOSITION_TYPE
     */
    public interface DecompositionType {
        /**
         *
         */
        int CANONICAL = 1;

        /**
         *
         */
        int CIRCLE = 3;

        /**
         *
         */
        int COMPAT = 2;

        /**
         *
         */
        int FINAL = 4;

        /**
         *
         */
        int FONT = 5;

        /**
         *
         */
        int FRACTION = 6;

        /**
         *
         */
        int INITIAL = 7;

        /**
         *
         */
        int ISOLATED = 8;

        /**
         *
         */
        int MEDIAL = 9;

        /**
         *
         */
        int NARROW = 10;

        /**
         *
         */
        int NOBREAK = 11;

        /**
         *
         */
        int NONE = 0;

        /**
         *
         */
        int SMALL = 12;

        /**
         *
         */
        int SQUARE = 13;

        /**
         *
         */
        int SUB = 14;

        /**
         *
         */
        int SUPER = 15;

        /**
         *
         */
        int VERTICAL = 16;

        /**
         *
         */
        int WIDE = 17;
    }

    /**
     * East Asian Width constants.
     * @see UProperty#EAST_ASIAN_WIDTH
     * @see UCharacter#getIntPropertyValue
     */
    public interface EastAsianWidth {
        /**
         *
         */
        int AMBIGUOUS = 1;

        /**
         *
         */
        int FULLWIDTH = 3;

        /**
         *
         */
        int HALFWIDTH = 2;

        /**
         *
         */
        int NARROW = 4;

        /**
         *
         */
        int NEUTRAL = 0;

        /**
         *
         */
        int WIDE = 5;
    }

    /**
     * Grapheme Cluster Break constants.
     * @see UProperty#GRAPHEME_CLUSTER_BREAK
     */
    public interface GraphemeClusterBreak {
        /**
         *
         */
        int CONTROL = 1;

        /**
         *
         */
        int CR = 2;

        /**
         *
         */
        int EXTEND = 3;

        /**
         *
         */
        int E_BASE = 13;

        /**
         *
         */
        int E_BASE_GAZ = 14;

        /**
         *
         */
        int E_MODIFIER = 15;

        /**
         *
         */
        int GLUE_AFTER_ZWJ = 16;

        /**
         *
         */
        int L = 4;

        /**
         *
         */
        int LF = 5;

        /**
         *
         */
        int LV = 6;

        /**
         *
         */
        int LVT = 7;

        /**
         *
         */
        int OTHER = 0;

        /**
         *
         */
        int PREPEND = 11;

        /**
         *
         */
        int REGIONAL_INDICATOR = 12;

        /**
         *
         */
        int SPACING_MARK = 10;

        /**
         *
         */
        int T = 8;

        /**
         *
         */
        int V = 9;

        /**
         *
         */
        int ZWJ = 17;
    }

    /**
     * Hangul Syllable Type constants.
     *
     * @see UProperty#HANGUL_SYLLABLE_TYPE
     */
    public interface HangulSyllableType {
        /**
         *
         */
        int LEADING_JAMO = 1;

        /**
         *
         */
        int LVT_SYLLABLE = 5;

        /**
         *
         */
        int LV_SYLLABLE = 4;

        /**
         *
         */
        int NOT_APPLICABLE = 0;

        /**
         *
         */
        int TRAILING_JAMO = 3;

        /**
         *
         */
        int VOWEL_JAMO = 2;
    }

    /**
     * Indic Positional Category constants.
     *
     * @see UProperty#INDIC_POSITIONAL_CATEGORY
     */
    public interface IndicPositionalCategory {
        /**
         *
         */
        int BOTTOM = 1;

        /**
         *
         */
        int BOTTOM_AND_LEFT = 2;

        /**
         *
         */
        int BOTTOM_AND_RIGHT = 3;

        /**
         *
         */
        int LEFT = 4;

        /**
         *
         */
        int LEFT_AND_RIGHT = 5;

        /**
         *
         */
        int NA = 0;

        /**
         *
         */
        int OVERSTRUCK = 6;

        /**
         *
         */
        int RIGHT = 7;

        /**
         *
         */
        int TOP = 8;

        /**
         *
         */
        int TOP_AND_BOTTOM = 9;

        /**
         *
         */
        int TOP_AND_BOTTOM_AND_LEFT = 15;

        /**
         *
         */
        int TOP_AND_BOTTOM_AND_RIGHT = 10;

        /**
         *
         */
        int TOP_AND_LEFT = 11;

        /**
         *
         */
        int TOP_AND_LEFT_AND_RIGHT = 12;

        /**
         *
         */
        int TOP_AND_RIGHT = 13;

        /**
         *
         */
        int VISUAL_ORDER_LEFT = 14;
    }

    /**
     * Indic Syllabic Category constants.
     *
     * @see UProperty#INDIC_SYLLABIC_CATEGORY
     */
    public interface IndicSyllabicCategory {
        /**
         *
         */
        int AVAGRAHA = 1;

        /**
         *
         */
        int BINDU = 2;

        /**
         *
         */
        int BRAHMI_JOINING_NUMBER = 3;

        /**
         *
         */
        int CANTILLATION_MARK = 4;

        /**
         *
         */
        int CONSONANT = 5;

        /**
         *
         */
        int CONSONANT_DEAD = 6;

        /**
         *
         */
        int CONSONANT_FINAL = 7;

        /**
         *
         */
        int CONSONANT_HEAD_LETTER = 8;

        /**
         *
         */
        int CONSONANT_INITIAL_POSTFIXED = 9;

        /**
         *
         */
        int CONSONANT_KILLER = 10;

        /**
         *
         */
        int CONSONANT_MEDIAL = 11;

        /**
         *
         */
        int CONSONANT_PLACEHOLDER = 12;

        /**
         *
         */
        int CONSONANT_PRECEDING_REPHA = 13;

        /**
         *
         */
        int CONSONANT_PREFIXED = 14;

        /**
         *
         */
        int CONSONANT_SUBJOINED = 15;

        /**
         *
         */
        int CONSONANT_SUCCEEDING_REPHA = 16;

        /**
         *
         */
        int CONSONANT_WITH_STACKER = 17;

        /**
         *
         */
        int GEMINATION_MARK = 18;

        /**
         *
         */
        int INVISIBLE_STACKER = 19;

        /**
         *
         */
        int JOINER = 20;

        /**
         *
         */
        int MODIFYING_LETTER = 21;

        /**
         *
         */
        int NON_JOINER = 22;

        /**
         *
         */
        int NUKTA = 23;

        /**
         *
         */
        int NUMBER = 24;

        /**
         *
         */
        int NUMBER_JOINER = 25;

        /**
         *
         */
        int OTHER = 0;

        /**
         *
         */
        int PURE_KILLER = 26;

        /**
         *
         */
        int REGISTER_SHIFTER = 27;

        /**
         *
         */
        int REORDERING_KILLER = 36;

        /**
         *
         */
        int SYLLABLE_MODIFIER = 28;

        /**
         *
         */
        int TONE_LETTER = 29;

        /**
         *
         */
        int TONE_MARK = 30;

        /**
         *
         */
        int VIRAMA = 31;

        /**
         *
         */
        int VISARGA = 32;

        /**
         *
         */
        int VOWEL = 33;

        /**
         *
         */
        int VOWEL_DEPENDENT = 34;

        /**
         *
         */
        int VOWEL_INDEPENDENT = 35;
    }

    /**
     * Joining Group constants.
     * @see UProperty#JOINING_GROUP
     */
    public interface JoiningGroup {
        /**
         *
         */
        int AFRICAN_FEH = 86;

        /**
         *
         */
        int AFRICAN_NOON = 87;

        /**
         *
         */
        int AFRICAN_QAF = 88;

        /**
         *
         */
        int AIN = 1;

        /**
         *
         */
        int ALAPH = 2;

        /**
         *
         */
        int ALEF = 3;

        /**
         *
         */
        int BEH = 4;

        /**
         *
         */
        int BETH = 5;

        /**
         *
         */
        int BURUSHASKI_YEH_BARREE = 54;

        /**
         *
         */
        int DAL = 6;

        /**
         *
         */
        int DALATH_RISH = 7;

        /**
         *
         */
        int E = 8;

        /**
         *
         */
        int FARSI_YEH = 55;

        /**
         *
         */
        int FE = 51;

        /**
         *
         */
        int FEH = 9;

        /**
         *
         */
        int FINAL_SEMKATH = 10;

        /**
         *
         */
        int GAF = 11;

        /**
         *
         */
        int GAMAL = 12;

        /**
         *
         */
        int HAH = 13;

        /**
         *
         */
        int HAMZA_ON_HEH_GOAL = 14;

        /**
         *
         */
        int HANIFI_ROHINGYA_KINNA_YA = 100;

        /**
         *
         */
        int HANIFI_ROHINGYA_PA = 101;

        /**
         *
         */
        int HE = 15;

        /**
         *
         */
        int HEH = 16;

        /**
         *
         */
        int HEH_GOAL = 17;

        /**
         *
         */
        int HETH = 18;

        /**
         *
         */
        int KAF = 19;

        /**
         *
         */
        int KAPH = 20;

        /**
         *
         */
        int KASHMIRI_YEH = 104;

        /**
         *
         */
        int KHAPH = 52;

        /**
         *
         */
        int KNOTTED_HEH = 21;

        /**
         *
         */
        int LAM = 22;

        /**
         *
         */
        int LAMADH = 23;

        /**
         *
         */
        int MALAYALAM_BHA = 89;

        /**
         *
         */
        int MALAYALAM_JA = 90;

        /**
         *
         */
        int MALAYALAM_LLA = 91;

        /**
         *
         */
        int MALAYALAM_LLLA = 92;

        /**
         *
         */
        int MALAYALAM_NGA = 93;

        /**
         *
         */
        int MALAYALAM_NNA = 94;

        /**
         *
         */
        int MALAYALAM_NNNA = 95;

        /**
         *
         */
        int MALAYALAM_NYA = 96;

        /**
         *
         */
        int MALAYALAM_RA = 97;

        /**
         *
         */
        int MALAYALAM_SSA = 98;

        /**
         *
         */
        int MALAYALAM_TTA = 99;

        /**
         *
         */
        int MANICHAEAN_ALEPH = 58;

        /**
         *
         */
        int MANICHAEAN_AYIN = 59;

        /**
         *
         */
        int MANICHAEAN_BETH = 60;

        /**
         *
         */
        int MANICHAEAN_DALETH = 61;

        /**
         *
         */
        int MANICHAEAN_DHAMEDH = 62;

        /**
         *
         */
        int MANICHAEAN_FIVE = 63;

        /**
         *
         */
        int MANICHAEAN_GIMEL = 64;

        /**
         *
         */
        int MANICHAEAN_HETH = 65;

        /**
         *
         */
        int MANICHAEAN_HUNDRED = 66;

        /**
         *
         */
        int MANICHAEAN_KAPH = 67;

        /**
         *
         */
        int MANICHAEAN_LAMEDH = 68;

        /**
         *
         */
        int MANICHAEAN_MEM = 69;

        /**
         *
         */
        int MANICHAEAN_NUN = 70;

        /**
         *
         */
        int MANICHAEAN_ONE = 71;

        /**
         *
         */
        int MANICHAEAN_PE = 72;

        /**
         *
         */
        int MANICHAEAN_QOPH = 73;

        /**
         *
         */
        int MANICHAEAN_RESH = 74;

        /**
         *
         */
        int MANICHAEAN_SADHE = 75;

        /**
         *
         */
        int MANICHAEAN_SAMEKH = 76;

        /**
         *
         */
        int MANICHAEAN_TAW = 77;

        /**
         *
         */
        int MANICHAEAN_TEN = 78;

        /**
         *
         */
        int MANICHAEAN_TETH = 79;

        /**
         *
         */
        int MANICHAEAN_THAMEDH = 80;

        /**
         *
         */
        int MANICHAEAN_TWENTY = 81;

        /**
         *
         */
        int MANICHAEAN_WAW = 82;

        /**
         *
         */
        int MANICHAEAN_YODH = 83;

        /**
         *
         */
        int MANICHAEAN_ZAYIN = 84;

        /**
         *
         */
        int MEEM = 24;

        /**
         *
         */
        int MIM = 25;

        /**
         *
         */
        int NOON = 26;

        /**
         *
         */
        int NO_JOINING_GROUP = 0;

        /**
         *
         */
        int NUN = 27;

        /**
         *
         */
        int NYA = 56;

        /**
         *
         */
        int PE = 28;

        /**
         *
         */
        int QAF = 29;

        /**
         *
         */
        int QAPH = 30;

        /**
         *
         */
        int REH = 31;

        /**
         *
         */
        int REVERSED_PE = 32;

        /**
         *
         */
        int ROHINGYA_YEH = 57;

        /**
         *
         */
        int SAD = 33;

        /**
         *
         */
        int SADHE = 34;

        /**
         *
         */
        int SEEN = 35;

        /**
         *
         */
        int SEMKATH = 36;

        /**
         *
         */
        int SHIN = 37;

        /**
         *
         */
        int STRAIGHT_WAW = 85;

        /**
         *
         */
        int SWASH_KAF = 38;

        /**
         *
         */
        int SYRIAC_WAW = 39;

        /**
         *
         */
        int TAH = 40;

        /**
         *
         */
        int TAW = 41;

        /**
         *
         */
        int TEH_MARBUTA = 42;

        /**
         *
         */
        int TEH_MARBUTA_GOAL = 14;

        /**
         *
         */
        int TETH = 43;

        /**
         *
         */
        int THIN_YEH = 102;

        /**
         *
         */
        int VERTICAL_TAIL = 103;

        /**
         *
         */
        int WAW = 44;

        /**
         *
         */
        int YEH = 45;

        /**
         *
         */
        int YEH_BARREE = 46;

        /**
         *
         */
        int YEH_WITH_TAIL = 47;

        /**
         *
         */
        int YUDH = 48;

        /**
         *
         */
        int YUDH_HE = 49;

        /**
         *
         */
        int ZAIN = 50;

        /**
         *
         */
        int ZHAIN = 53;
    }

    /**
     * Joining Type constants.
     * @see UProperty#JOINING_TYPE
     */
    public interface JoiningType {
        /**
         *
         */
        int DUAL_JOINING = 2;

        /**
         *
         */
        int JOIN_CAUSING = 1;

        /**
         *
         */
        int LEFT_JOINING = 3;

        /**
         *
         */
        int NON_JOINING = 0;

        /**
         *
         */
        int RIGHT_JOINING = 4;

        /**
         *
         */
        int TRANSPARENT = 5;
    }

    /**
     * Line Break constants.
     * @see UProperty#LINE_BREAK
     */
    public interface LineBreak {
        /**
         *
         */
        int AKSARA = 43;

        /**
         *
         */
        int AKSARA_PREBASE = 44;

        /**
         *
         */
        int AKSARA_START = 45;

        /**
         *
         */
        int ALPHABETIC = 2;

        /**
         *
         */
        int AMBIGUOUS = 1;

        /**
         *
         */
        int BREAK_AFTER = 4;

        /**
         *
         */
        int BREAK_BEFORE = 5;

        /**
         *
         */
        int BREAK_BOTH = 3;

        /**
         *
         */
        int BREAK_SYMBOLS = 27;

        /**
         *
         */
        int CARRIAGE_RETURN = 10;

        /**
         *
         */
        int CLOSE_PARENTHESIS = 36;

        /**
         *
         */
        int CLOSE_PUNCTUATION = 8;

        /**
         *
         */
        int COMBINING_MARK = 9;

        /**
         *
         */
        int COMPLEX_CONTEXT = 24;

        /**
         *
         */
        int CONDITIONAL_JAPANESE_STARTER = 37;

        /**
         *
         */
        int CONTINGENT_BREAK = 7;

        /**
         *
         */
        int EXCLAMATION = 11;

        /**
         *
         */
        int E_BASE = 40;

        /**
         *
         */
        int E_MODIFIER = 41;

        /**
         *
         */
        int GLUE = 12;

        /**
         *
         */
        int H2 = 31;

        /**
         *
         */
        int H3 = 32;

        /**
         *
         */
        int HEBREW_LETTER = 38;

        /**
         *
         */
        int HYPHEN = 13;

        /**
         *
         */
        int IDEOGRAPHIC = 14;

        /**
         *
         */
        int INFIX_NUMERIC = 16;

        /**
         * Renamed from the misspelled "inseperable" in Unicode 4.0.1.
         */
        int INSEPARABLE = 15;

        /**
         * @see #INSEPARABLE
         */
        int INSEPERABLE = 15;

        /**
         *
         */
        int JL = 33;

        /**
         *
         */
        int JT = 34;

        /**
         *
         */
        int JV = 35;

        /**
         *
         */
        int LINE_FEED = 17;

        /**
         *
         */
        int MANDATORY_BREAK = 6;

        /**
         *
         */
        int NEXT_LINE = 29;

        /**
         *
         */
        int NONSTARTER = 18;

        /**
         *
         */
        int NUMERIC = 19;

        /**
         *
         */
        int OPEN_PUNCTUATION = 20;

        /**
         *
         */
        int POSTFIX_NUMERIC = 21;

        /**
         *
         */
        int PREFIX_NUMERIC = 22;

        /**
         *
         */
        int QUOTATION = 23;

        /**
         *
         */
        int REGIONAL_INDICATOR = 39;

        /**
         *
         */
        int SPACE = 26;

        /**
         *
         */
        int SURROGATE = 25;

        /**
         *
         */
        int UNKNOWN = 0;

        /**
         *
         */
        int VIRAMA = 47;

        /**
         *
         */
        int VIRAMA_FINAL = 46;

        /**
         *
         */
        int WORD_JOINER = 30;

        /**
         *
         */
        int ZWJ = 42;

        /**
         *
         */
        int ZWSPACE = 28;
    }

    /**
     * Numeric Type constants.
     * @see UProperty#NUMERIC_TYPE
     */
    public interface NumericType {
        /**
         *
         */
        int DECIMAL = 1;

        /**
         *
         */
        int DIGIT = 2;

        /**
         *
         */
        int NONE = 0;

        /**
         *
         */
        int NUMERIC = 3;
    }

    /**
     * Sentence Break constants.
     * @see UProperty#SENTENCE_BREAK
     */
    public interface SentenceBreak {
        /**
         *
         */
        int ATERM = 1;

        /**
         *
         */
        int CLOSE = 2;

        /**
         *
         */
        int CR = 11;

        /**
         *
         */
        int EXTEND = 12;

        /**
         *
         */
        int FORMAT = 3;

        /**
         *
         */
        int LF = 13;

        /**
         *
         */
        int LOWER = 4;

        /**
         *
         */
        int NUMERIC = 5;

        /**
         *
         */
        int OLETTER = 6;

        /**
         *
         */
        int OTHER = 0;

        /**
         *
         */
        int SCONTINUE = 14;

        /**
         *
         */
        int SEP = 7;

        /**
         *
         */
        int SP = 8;

        /**
         *
         */
        int STERM = 9;

        /**
         *
         */
        int UPPER = 10;
    }

    /**
     * <strong>[icu enhancement]</strong> ICU's replacement for {@link java.lang.Character.UnicodeBlock}.&nbsp;Methods, fields, and other functionality specific to ICU are labeled '<strong>[icu]</strong>'.
     *
     * A family of character subsets representing the character blocks in the
     * Unicode specification, generated from Unicode Data file Blocks.txt.
     * Character blocks generally define characters used for a specific script
     * or purpose. A character is contained by at most one Unicode block.
     *
     * <strong>[icu] Note:</strong> All fields named XXX_ID are specific to ICU.
     */
    public static final class UnicodeBlock {
        /**
         *
         */
        public static final int ADLAM_ID = 263;

        /**
         *
         */
        public static final int AEGEAN_NUMBERS_ID = 119;

        /**
         *
         */
        public static final int AHOM_ID = 253;

        /**
         *
         */
        public static final int ALCHEMICAL_SYMBOLS_ID = 208;

        /**
         *
         */
        public static final int ALPHABETIC_PRESENTATION_FORMS_ID = 80;

        /**
         *
         */
        public static final int ANATOLIAN_HIEROGLYPHS_ID = 254;

        /**
         *
         */
        public static final int ANCIENT_GREEK_MUSICAL_NOTATION_ID = 126;

        /**
         *
         */
        public static final int ANCIENT_GREEK_NUMBERS_ID = 127;

        /**
         *
         */
        public static final int ANCIENT_SYMBOLS_ID = 165;

        /**
         *
         */
        public static final int ARABIC_EXTENDED_A_ID = 210;

        /**
         *
         */
        public static final int ARABIC_EXTENDED_B_ID = 309;

        /**
         *
         */
        public static final int ARABIC_EXTENDED_C_ID = 321;

        /**
         *
         */
        public static final int ARABIC_ID = 12;

        /**
         *
         */
        public static final int ARABIC_MATHEMATICAL_ALPHABETIC_SYMBOLS_ID = 211;

        /**
         *
         */
        public static final int ARABIC_PRESENTATION_FORMS_A_ID = 81;

        /**
         *
         */
        public static final int ARABIC_PRESENTATION_FORMS_B_ID = 85;

        /**
         *
         */
        public static final int ARABIC_SUPPLEMENT_ID = 128;

        /**
         *
         */
        public static final int ARMENIAN_ID = 10;

        /**
         *
         */
        public static final int ARROWS_ID = 46;

        /**
         *
         */
        public static final int AVESTAN_ID = 188;

        /**
         *
         */
        public static final int BALINESE_ID = 147;

        /**
         *
         */
        public static final int BAMUM_ID = 177;

        /**
         *
         */
        public static final int BAMUM_SUPPLEMENT_ID = 202;

        /**
         *
         */
        public static final int BASIC_LATIN_ID = 1;

        /**
         *
         */
        public static final int BASSA_VAH_ID = 221;

        /**
         *
         */
        public static final int BATAK_ID = 199;

        /**
         *
         */
        public static final int BENGALI_ID = 16;

        /**
         *
         */
        public static final int BHAIKSUKI_ID = 264;

        /**
         *
         */
        public static final int BLOCK_ELEMENTS_ID = 53;

        /**
         *
         */
        public static final int BOPOMOFO_EXTENDED_ID = 67;

        /**
         *
         */
        public static final int BOPOMOFO_ID = 64;

        /**
         *
         */
        public static final int BOX_DRAWING_ID = 52;

        /**
         *
         */
        public static final int BRAHMI_ID = 201;

        /**
         *
         */
        public static final int BRAILLE_PATTERNS_ID = 57;

        /**
         *
         */
        public static final int BUGINESE_ID = 129;

        /**
         *
         */
        public static final int BUHID_ID = 100;

        /**
         *
         */
        public static final int BYZANTINE_MUSICAL_SYMBOLS_ID = 91;

        /**
         *
         */
        public static final int CARIAN_ID = 168;

        /**
         *
         */
        public static final int CAUCASIAN_ALBANIAN_ID = 222;

        /**
         *
         */
        public static final int CHAKMA_ID = 212;

        /**
         *
         */
        public static final int CHAM_ID = 164;

        /**
         *
         */
        public static final int CHEROKEE_ID = 32;

        /**
         *
         */
        public static final int CHEROKEE_SUPPLEMENT_ID = 255;

        /**
         *
         */
        public static final int CHESS_SYMBOLS_ID = 281;

        /**
         *
         */
        public static final int CHORASMIAN_ID = 301;

        /**
         *
         */
        public static final int CJK_COMPATIBILITY_FORMS_ID = 83;

        /**
         *
         */
        public static final int CJK_COMPATIBILITY_ID = 69;

        /**
         *
         */
        public static final int CJK_COMPATIBILITY_IDEOGRAPHS_ID = 79;

        /**
         *
         */
        public static final int CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT_ID = 95;

        /**
         *
         */
        public static final int CJK_RADICALS_SUPPLEMENT_ID = 58;

        /**
         *
         */
        public static final int CJK_STROKES_ID = 130;

        /**
         *
         */
        public static final int CJK_SYMBOLS_AND_PUNCTUATION_ID = 61;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A_ID = 70;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B_ID = 94;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C_ID = 197;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D_ID = 209;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_E_ID = 256;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID = 274;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_G_ID = 302;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_H_ID = 322;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_EXTENSION_I_ID = 328;

        /**
         *
         */
        public static final int CJK_UNIFIED_IDEOGRAPHS_ID = 71;

        /**
         *
         */
        public static final int COMBINING_DIACRITICAL_MARKS_EXTENDED_ID = 224;

        /**
         *
         */
        public static final int COMBINING_DIACRITICAL_MARKS_ID = 7;

        /**
         *
         */
        public static final int COMBINING_DIACRITICAL_MARKS_SUPPLEMENT_ID = 131;

        /**
         *
         */
        public static final int COMBINING_HALF_MARKS_ID = 82;

        /**
         * Unicode 3.2 renames this block to "Combining Diacritical Marks for
         * Symbols".
         */
        public static final int COMBINING_MARKS_FOR_SYMBOLS_ID = 43;

        /**
         *
         */
        public static final int COMMON_INDIC_NUMBER_FORMS_ID = 178;

        /**
         *
         */
        public static final int CONTROL_PICTURES_ID = 49;

        /**
         *
         */
        public static final int COPTIC_EPACT_NUMBERS_ID = 223;

        /**
         *
         */
        public static final int COPTIC_ID = 132;

        /**
         *
         */
        public static final int COUNTING_ROD_NUMERALS_ID = 154;

        /**
         *
         */
        public static final int CUNEIFORM_ID = 152;

        /**
         *
         */
        public static final int CUNEIFORM_NUMBERS_AND_PUNCTUATION_ID = 153;

        /**
         *
         */
        public static final int CURRENCY_SYMBOLS_ID = 42;

        /**
         *
         */
        public static final int CYPRIOT_SYLLABARY_ID = 123;

        /**
         *
         */
        public static final int CYPRO_MINOAN_ID = 310;

        /**
         *
         */
        public static final int CYRILLIC_EXTENDED_A_ID = 158;

        /**
         *
         */
        public static final int CYRILLIC_EXTENDED_B_ID = 160;

        /**
         *
         */
        public static final int CYRILLIC_EXTENDED_C_ID = 265;

        /**
         *
         */
        public static final int CYRILLIC_EXTENDED_D_ID = 323;

        /**
         *
         */
        public static final int CYRILLIC_ID = 9;

        /**
         * Unicode 4.0.1 renames the "Cyrillic Supplementary" block to "Cyrillic Supplement".
         */
        public static final int CYRILLIC_SUPPLEMENTARY_ID = 97;

        /**
         * Unicode 4.0.1 renames the "Cyrillic Supplementary" block to "Cyrillic Supplement".
         */
        public static final int CYRILLIC_SUPPLEMENT_ID = 97;

        /**
         *
         */
        public static final int DESERET_ID = 90;

        /**
         *
         */
        public static final int DEVANAGARI_EXTENDED_A_ID = 324;

        /**
         *
         */
        public static final int DEVANAGARI_EXTENDED_ID = 179;

        /**
         *
         */
        public static final int DEVANAGARI_ID = 15;

        /**
         *
         */
        public static final int DINGBATS_ID = 56;

        /**
         *
         */
        public static final int DIVES_AKURU_ID = 303;

        /**
         *
         */
        public static final int DOGRA_ID = 282;

        /**
         *
         */
        public static final int DOMINO_TILES_ID = 171;

        /**
         *
         */
        public static final int DUPLOYAN_ID = 225;

        /**
         *
         */
        public static final int EARLY_DYNASTIC_CUNEIFORM_ID = 257;

        /**
         *
         */
        public static final int EGYPTIAN_HIEROGLYPHS_EXTENDED_A_ID = 329;

        /**
         *
         */
        public static final int EGYPTIAN_HIEROGLYPHS_ID = 194;

        /**
         *
         */
        public static final int EGYPTIAN_HIEROGLYPH_FORMAT_CONTROLS_ID = 292;

        /**
         *
         */
        public static final int ELBASAN_ID = 226;

        /**
         *
         */
        public static final int ELYMAIC_ID = 293;

        /**
         *
         */
        public static final int EMOTICONS_ID = 206;

        /**
         *
         */
        public static final int ENCLOSED_ALPHANUMERICS_ID = 51;

        /**
         *
         */
        public static final int ENCLOSED_ALPHANUMERIC_SUPPLEMENT_ID = 195;

        /**
         *
         */
        public static final int ENCLOSED_CJK_LETTERS_AND_MONTHS_ID = 68;

        /**
         *
         */
        public static final int ENCLOSED_IDEOGRAPHIC_SUPPLEMENT_ID = 196;

        /**
         *
         */
        public static final int ETHIOPIC_EXTENDED_A_ID = 200;

        /**
         *
         */
        public static final int ETHIOPIC_EXTENDED_B_ID = 311;

        /**
         *
         */
        public static final int ETHIOPIC_EXTENDED_ID = 133;

        /**
         *
         */
        public static final int ETHIOPIC_ID = 31;

        /**
         *
         */
        public static final int ETHIOPIC_SUPPLEMENT_ID = 134;

        /**
         *
         */
        public static final int GARAY_ID = 330;

        /**
         *
         */
        public static final int GENERAL_PUNCTUATION_ID = 40;

        /**
         *
         */
        public static final int GEOMETRIC_SHAPES_EXTENDED_ID = 227;

        /**
         *
         */
        public static final int GEOMETRIC_SHAPES_ID = 54;

        /**
         *
         */
        public static final int GEORGIAN_EXTENDED_ID = 283;

        /**
         *
         */
        public static final int GEORGIAN_ID = 29;

        /**
         *
         */
        public static final int GEORGIAN_SUPPLEMENT_ID = 135;

        /**
         *
         */
        public static final int GLAGOLITIC_ID = 136;

        /**
         *
         */
        public static final int GLAGOLITIC_SUPPLEMENT_ID = 266;

        /**
         *
         */
        public static final int GOTHIC_ID = 89;

        /**
         *
         */
        public static final int GRANTHA_ID = 228;

        /**
         *
         */
        public static final int GREEK_EXTENDED_ID = 39;

        /**
         * Unicode 3.2 renames this block to "Greek and Coptic".
         */
        public static final int GREEK_ID = 8;

        /**
         *
         */
        public static final int GUJARATI_ID = 18;

        /**
         *
         */
        public static final int GUNJALA_GONDI_ID = 284;

        /**
         *
         */
        public static final int GURMUKHI_ID = 17;

        /**
         *
         */
        public static final int GURUNG_KHEMA_ID = 331;

        /**
         *
         */
        public static final int HALFWIDTH_AND_FULLWIDTH_FORMS_ID = 87;

        /**
         *
         */
        public static final int HANGUL_COMPATIBILITY_JAMO_ID = 65;

        /**
         *
         */
        public static final int HANGUL_JAMO_EXTENDED_A_ID = 180;

        /**
         *
         */
        public static final int HANGUL_JAMO_EXTENDED_B_ID = 185;

        /**
         *
         */
        public static final int HANGUL_JAMO_ID = 30;

        /**
         *
         */
        public static final int HANGUL_SYLLABLES_ID = 74;

        /**
         *
         */
        public static final int HANIFI_ROHINGYA_ID = 285;

        /**
         *
         */
        public static final int HANUNOO_ID = 99;

        /**
         *
         */
        public static final int HATRAN_ID = 258;

        /**
         *
         */
        public static final int HEBREW_ID = 11;

        /**
         *
         */
        public static final int HIGH_PRIVATE_USE_SURROGATES_ID = 76;

        /**
         *
         */
        public static final int HIGH_SURROGATES_ID = 75;

        /**
         *
         */
        public static final int HIRAGANA_ID = 62;

        /**
         *
         */
        public static final int IDEOGRAPHIC_DESCRIPTION_CHARACTERS_ID = 60;

        /**
         *
         */
        public static final int IDEOGRAPHIC_SYMBOLS_AND_PUNCTUATION_ID = 267;

        /**
         *
         */
        public static final int IMPERIAL_ARAMAIC_ID = 186;

        /**
         *
         */
        public static final int INDIC_SIYAQ_NUMBERS_ID = 286;

        /**
         *
         */
        public static final int INSCRIPTIONAL_PAHLAVI_ID = 190;

        /**
         *
         */
        public static final int INSCRIPTIONAL_PARTHIAN_ID = 189;

        /**
         *
         */
        public static final int INVALID_CODE_ID = -1;

        /**
         *
         */
        public static final int IPA_EXTENSIONS_ID = 5;

        /**
         *
         */
        public static final int JAVANESE_ID = 181;

        /**
         *
         */
        public static final int KAITHI_ID = 193;

        /**
         *
         */
        public static final int KAKTOVIK_NUMERALS_ID = 325;

        /**
         *
         */
        public static final int KANA_EXTENDED_A_ID = 275;

        /**
         *
         */
        public static final int KANA_EXTENDED_B_ID = 312;

        /**
         *
         */
        public static final int KANA_SUPPLEMENT_ID = 203;

        /**
         *
         */
        public static final int KANBUN_ID = 66;

        /**
         *
         */
        public static final int KANGXI_RADICALS_ID = 59;

        /**
         *
         */
        public static final int KANNADA_ID = 22;

        /**
         *
         */
        public static final int KATAKANA_ID = 63;

        /**
         *
         */
        public static final int KATAKANA_PHONETIC_EXTENSIONS_ID = 107;

        /**
         *
         */
        public static final int KAWI_ID = 326;

        /**
         *
         */
        public static final int KAYAH_LI_ID = 162;

        /**
         *
         */
        public static final int KHAROSHTHI_ID = 137;

        /**
         *
         */
        public static final int KHITAN_SMALL_SCRIPT_ID = 304;

        /**
         *
         */
        public static final int KHMER_ID = 36;

        /**
         *
         */
        public static final int KHMER_SYMBOLS_ID = 113;

        /**
         *
         */
        public static final int KHOJKI_ID = 229;

        /**
         *
         */
        public static final int KHUDAWADI_ID = 230;

        /**
         *
         */
        public static final int KIRAT_RAI_ID = 332;

        /**
         *
         */
        public static final int LAO_ID = 26;

        /**
         *
         */
        public static final int LATIN_1_SUPPLEMENT_ID = 2;

        /**
         *
         */
        public static final int LATIN_EXTENDED_ADDITIONAL_ID = 38;

        /**
         *
         */
        public static final int LATIN_EXTENDED_A_ID = 3;

        /**
         *
         */
        public static final int LATIN_EXTENDED_B_ID = 4;

        /**
         *
         */
        public static final int LATIN_EXTENDED_C_ID = 148;

        /**
         *
         */
        public static final int LATIN_EXTENDED_D_ID = 149;

        /**
         *
         */
        public static final int LATIN_EXTENDED_E_ID = 231;

        /**
         *
         */
        public static final int LATIN_EXTENDED_F_ID = 313;

        /**
         *
         */
        public static final int LATIN_EXTENDED_G_ID = 314;

        /**
         *
         */
        public static final int LEPCHA_ID = 156;

        /**
         *
         */
        public static final int LETTERLIKE_SYMBOLS_ID = 44;

        /**
         *
         */
        public static final int LIMBU_ID = 111;

        /**
         *
         */
        public static final int LINEAR_A_ID = 232;

        /**
         *
         */
        public static final int LINEAR_B_IDEOGRAMS_ID = 118;

        /**
         *
         */
        public static final int LINEAR_B_SYLLABARY_ID = 117;

        /**
         *
         */
        public static final int LISU_ID = 176;

        /**
         *
         */
        public static final int LISU_SUPPLEMENT_ID = 305;

        /**
         *
         */
        public static final int LOW_SURROGATES_ID = 77;

        /**
         *
         */
        public static final int LYCIAN_ID = 167;

        /**
         *
         */
        public static final int LYDIAN_ID = 169;

        /**
         *
         */
        public static final int MAHAJANI_ID = 233;

        /**
         *
         */
        public static final int MAHJONG_TILES_ID = 170;

        /**
         *
         */
        public static final int MAKASAR_ID = 287;

        /**
         *
         */
        public static final int MALAYALAM_ID = 23;

        /**
         *
         */
        public static final int MANDAIC_ID = 198;

        /**
         *
         */
        public static final int MANICHAEAN_ID = 234;

        /**
         *
         */
        public static final int MARCHEN_ID = 268;

        /**
         *
         */
        public static final int MASARAM_GONDI_ID = 276;

        /**
         *
         */
        public static final int MATHEMATICAL_ALPHANUMERIC_SYMBOLS_ID = 93;

        /**
         *
         */
        public static final int MATHEMATICAL_OPERATORS_ID = 47;

        /**
         *
         */
        public static final int MAYAN_NUMERALS_ID = 288;

        /**
         *
         */
        public static final int MEDEFAIDRIN_ID = 289;

        /**
         *
         */
        public static final int MEETEI_MAYEK_EXTENSIONS_ID = 213;

        /**
         *
         */
        public static final int MEETEI_MAYEK_ID = 184;

        /**
         *
         */
        public static final int MENDE_KIKAKUI_ID = 235;

        /**
         *
         */
        public static final int MEROITIC_CURSIVE_ID = 214;

        /**
         *
         */
        public static final int MEROITIC_HIEROGLYPHS_ID = 215;

        /**
         *
         */
        public static final int MIAO_ID = 216;

        /**
         *
         */
        public static final int MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_ID = 102;

        /**
         *
         */
        public static final int MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_ID = 105;

        /**
         *
         */
        public static final int MISCELLANEOUS_SYMBOLS_AND_ARROWS_ID = 115;

        /**
         *
         */
        public static final int MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS_ID = 205;

        /**
         *
         */
        public static final int MISCELLANEOUS_SYMBOLS_ID = 55;

        /**
         *
         */
        public static final int MISCELLANEOUS_TECHNICAL_ID = 48;

        /**
         *
         */
        public static final int MODIFIER_TONE_LETTERS_ID = 138;

        /**
         *
         */
        public static final int MODI_ID = 236;

        /**
         *
         */
        public static final int MONGOLIAN_ID = 37;

        /**
         *
         */
        public static final int MONGOLIAN_SUPPLEMENT_ID = 269;

        /**
         *
         */
        public static final int MRO_ID = 237;

        /**
         *
         */
        public static final int MULTANI_ID = 259;

        /**
         *
         */
        public static final int MUSICAL_SYMBOLS_ID = 92;

        /**
         *
         */
        public static final int MYANMAR_EXTENDED_A_ID = 182;

        /**
         *
         */
        public static final int MYANMAR_EXTENDED_B_ID = 238;

        /**
         *
         */
        public static final int MYANMAR_EXTENDED_C_ID = 333;

        /**
         *
         */
        public static final int MYANMAR_ID = 28;

        /**
         *
         */
        public static final int NABATAEAN_ID = 239;

        /**
         *
         */
        public static final int NAG_MUNDARI_ID = 327;

        /**
         *
         */
        public static final int NANDINAGARI_ID = 294;

        /**
         *
         */
        public static final int NEWA_ID = 270;

        /**
         *
         */
        public static final int NEW_TAI_LUE_ID = 139;

        /**
         *
         */
        public static final int NKO_ID = 146;

        /**
         *
         */
        public static final int NUMBER_FORMS_ID = 45;

        /**
         *
         */
        public static final int NUSHU_ID = 277;

        /**
         *
         */
        public static final int NYIAKENG_PUACHUE_HMONG_ID = 295;

        /**
         *
         */
        public static final int OGHAM_ID = 34;

        /**
         *
         */
        public static final int OLD_HUNGARIAN_ID = 260;

        /**
         *
         */
        public static final int OLD_ITALIC_ID = 88;

        /**
         *
         */
        public static final int OLD_NORTH_ARABIAN_ID = 240;

        /**
         *
         */
        public static final int OLD_PERMIC_ID = 241;

        /**
         *
         */
        public static final int OLD_PERSIAN_ID = 140;

        /**
         *
         */
        public static final int OLD_SOGDIAN_ID = 290;

        /**
         *
         */
        public static final int OLD_SOUTH_ARABIAN_ID = 187;

        /**
         *
         */
        public static final int OLD_TURKIC_ID = 191;

        /**
         *
         */
        public static final int OLD_UYGHUR_ID = 315;

        /**
         *
         */
        public static final int OL_CHIKI_ID = 157;

        /**
         *
         */
        public static final int OL_ONAL_ID = 334;

        /**
         *
         */
        public static final int OPTICAL_CHARACTER_RECOGNITION_ID = 50;

        /**
         *
         */
        public static final int ORIYA_ID = 19;

        /**
         *
         */
        public static final int ORNAMENTAL_DINGBATS_ID = 242;

        /**
         *
         */
        public static final int OSAGE_ID = 271;

        /**
         *
         */
        public static final int OSMANYA_ID = 122;

        /**
         *
         */
        public static final int OTTOMAN_SIYAQ_NUMBERS_ID = 296;

        /**
         *
         */
        public static final int PAHAWH_HMONG_ID = 243;

        /**
         *
         */
        public static final int PALMYRENE_ID = 244;

        /**
         *
         */
        public static final int PAU_CIN_HAU_ID = 245;

        /**
         *
         */
        public static final int PHAGS_PA_ID = 150;

        /**
         *
         */
        public static final int PHAISTOS_DISC_ID = 166;

        /**
         *
         */
        public static final int PHOENICIAN_ID = 151;

        /**
         *
         */
        public static final int PHONETIC_EXTENSIONS_ID = 114;

        /**
         *
         */
        public static final int PHONETIC_EXTENSIONS_SUPPLEMENT_ID = 141;

        /**
         *
         */
        public static final int PLAYING_CARDS_ID = 204;

        /**
         * Same as public static final int PRIVATE_USE.
         * Until Unicode 3.1.1; the corresponding block name was "Private Use";
         * and multiple code point ranges had this block.
         * Unicode 3.2 renames the block for the BMP PUA to "Private Use Area"
         * and adds separate blocks for the supplementary PUAs.
         */
        public static final int PRIVATE_USE_AREA_ID = 78;

        /**
         * Same as public static final int PRIVATE_USE_AREA.
         * Until Unicode 3.1.1; the corresponding block name was "Private Use";
         * and multiple code point ranges had this block.
         * Unicode 3.2 renames the block for the BMP PUA to "Private Use Area"
         * and adds separate blocks for the supplementary PUAs.
         */
        public static final int PRIVATE_USE_ID = 78;

        /**
         *
         */
        public static final int PSALTER_PAHLAVI_ID = 246;

        /**
         *
         */
        public static final int REJANG_ID = 163;

        /**
         *
         */
        public static final int RUMI_NUMERAL_SYMBOLS_ID = 192;

        /**
         *
         */
        public static final int RUNIC_ID = 35;

        /**
         *
         */
        public static final int SAMARITAN_ID = 172;

        /**
         *
         */
        public static final int SAURASHTRA_ID = 161;

        /**
         *
         */
        public static final int SHARADA_ID = 217;

        /**
         *
         */
        public static final int SHAVIAN_ID = 121;

        /**
         *
         */
        public static final int SHORTHAND_FORMAT_CONTROLS_ID = 247;

        /**
         *
         */
        public static final int SIDDHAM_ID = 248;

        /**
         *
         */
        public static final int SINHALA_ARCHAIC_NUMBERS_ID = 249;

        /**
         *
         */
        public static final int SINHALA_ID = 24;

        /**
         *
         */
        public static final int SMALL_FORM_VARIANTS_ID = 84;

        /**
         *
         */
        public static final int SMALL_KANA_EXTENSION_ID = 297;

        /**
         *
         */
        public static final int SOGDIAN_ID = 291;

        /**
         *
         */
        public static final int SORA_SOMPENG_ID = 218;

        /**
         *
         */
        public static final int SOYOMBO_ID = 278;

        /**
         *
         */
        public static final int SPACING_MODIFIER_LETTERS_ID = 6;

        /**
         *
         */
        public static final int SPECIALS_ID = 86;

        /**
         *
         */
        public static final int SUNDANESE_ID = 155;

        /**
         *
         */
        public static final int SUNDANESE_SUPPLEMENT_ID = 219;

        /**
         *
         */
        public static final int SUNUWAR_ID = 335;

        /**
         *
         */
        public static final int SUPERSCRIPTS_AND_SUBSCRIPTS_ID = 41;

        /**
         *
         */
        public static final int SUPPLEMENTAL_ARROWS_A_ID = 103;

        /**
         *
         */
        public static final int SUPPLEMENTAL_ARROWS_B_ID = 104;

        /**
         *
         */
        public static final int SUPPLEMENTAL_ARROWS_C_ID = 250;

        /**
         *
         */
        public static final int SUPPLEMENTAL_MATHEMATICAL_OPERATORS_ID = 106;

        /**
         *
         */
        public static final int SUPPLEMENTAL_PUNCTUATION_ID = 142;

        /**
         *
         */
        public static final int SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS_ID = 261;

        /**
         *
         */
        public static final int SUPPLEMENTARY_PRIVATE_USE_AREA_A_ID = 109;

        /**
         *
         */
        public static final int SUPPLEMENTARY_PRIVATE_USE_AREA_B_ID = 110;

        /**
         *
         */
        public static final int SUTTON_SIGNWRITING_ID = 262;

        /**
         *
         */
        public static final int SYLOTI_NAGRI_ID = 143;

        /**
         *
         */
        public static final int SYMBOLS_AND_PICTOGRAPHS_EXTENDED_A_ID = 298;

        /**
         *
         */
        public static final int SYMBOLS_FOR_LEGACY_COMPUTING_ID = 306;

        /**
         *
         */
        public static final int SYMBOLS_FOR_LEGACY_COMPUTING_SUPPLEMENT_ID = 336;

        /**
         *
         */
        public static final int SYRIAC_ID = 13;

        /**
         *
         */
        public static final int SYRIAC_SUPPLEMENT_ID = 279;

        /**
         *
         */
        public static final int TAGALOG_ID = 98;

        /**
         *
         */
        public static final int TAGBANWA_ID = 101;

        /**
         *
         */
        public static final int TAGS_ID = 96;

        /**
         *
         */
        public static final int TAI_LE_ID = 112;

        /**
         *
         */
        public static final int TAI_THAM_ID = 174;

        /**
         *
         */
        public static final int TAI_VIET_ID = 183;

        /**
         *
         */
        public static final int TAI_XUAN_JING_SYMBOLS_ID = 124;

        /**
         *
         */
        public static final int TAKRI_ID = 220;

        /**
         *
         */
        public static final int TAMIL_ID = 20;

        /**
         *
         */
        public static final int TAMIL_SUPPLEMENT_ID = 299;

        /**
         *
         */
        public static final int TANGSA_ID = 316;

        /**
         *
         */
        public static final int TANGUT_COMPONENTS_ID = 273;

        /**
         *
         */
        public static final int TANGUT_ID = 272;

        /**
         *
         */
        public static final int TANGUT_SUPPLEMENT_ID = 307;

        /**
         *
         */
        public static final int TELUGU_ID = 21;

        /**
         *
         */
        public static final int THAANA_ID = 14;

        /**
         *
         */
        public static final int THAI_ID = 25;

        /**
         *
         */
        public static final int TIBETAN_ID = 27;

        /**
         *
         */
        public static final int TIFINAGH_ID = 144;

        /**
         *
         */
        public static final int TIRHUTA_ID = 251;

        /**
         *
         */
        public static final int TODHRI_ID = 337;

        /**
         *
         */
        public static final int TOTO_ID = 317;

        /**
         *
         */
        public static final int TRANSPORT_AND_MAP_SYMBOLS_ID = 207;

        /**
         *
         */
        public static final int TULU_TIGALARI_ID = 338;

        /**
         *
         */
        public static final int UGARITIC_ID = 120;

        /**
         *
         */
        public static final int UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_A_ID = 318;

        /**
         *
         */
        public static final int UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_ID = 173;

        /**
         *
         */
        public static final int UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_ID = 33;

        /**
         *
         */
        public static final int VAI_ID = 159;

        /**
         *
         */
        public static final int VARIATION_SELECTORS_ID = 108;

        /**
         *
         */
        public static final int VARIATION_SELECTORS_SUPPLEMENT_ID = 125;

        /**
         *
         */
        public static final int VEDIC_EXTENSIONS_ID = 175;

        /**
         *
         */
        public static final int VERTICAL_FORMS_ID = 145;

        /**
         *
         */
        public static final int VITHKUQI_ID = 319;

        /**
         *
         */
        public static final int WANCHO_ID = 300;

        /**
         *
         */
        public static final int WARANG_CITI_ID = 252;

        /**
         *
         */
        public static final int YEZIDI_ID = 308;

        /**
         *
         */
        public static final int YIJING_HEXAGRAM_SYMBOLS_ID = 116;

        /**
         *
         */
        public static final int YI_RADICALS_ID = 73;

        /**
         *
         */
        public static final int YI_SYLLABLES_ID = 72;

        /**
         *
         */
        public static final int ZANABAZAR_SQUARE_ID = 280;

        /**
         *
         */
        public static final int ZNAMENNY_MUSICAL_NOTATION_ID = 320;

        private UnicodeBlock() {
        }
    }

    /**
     * Vertical Orientation constants.
     *
     * @see UProperty#VERTICAL_ORIENTATION
     */
    public interface VerticalOrientation {
        /**
         *
         */
        int ROTATED = 0;

        /**
         *
         */
        int TRANSFORMED_ROTATED = 1;

        /**
         *
         */
        int TRANSFORMED_UPRIGHT = 2;

        /**
         *
         */
        int UPRIGHT = 3;
    }

    /**
     * Word Break constants.
     * @see UProperty#WORD_BREAK
     */
    public interface WordBreak {
        /**
         *
         */
        int ALETTER = 1;

        /**
         *
         */
        int CR = 8;

        /**
         *
         */
        int DOUBLE_QUOTE = 16;

        /**
         *
         */
        int EXTEND = 9;

        /**
         *
         */
        int EXTENDNUMLET = 7;

        /**
         *
         */
        int E_BASE = 17;

        /**
         *
         */
        int E_BASE_GAZ = 18;

        /**
         *
         */
        int E_MODIFIER = 19;

        /**
         *
         */
        int FORMAT = 2;

        /**
         *
         */
        int GLUE_AFTER_ZWJ = 20;

        /**
         *
         */
        int HEBREW_LETTER = 14;

        /**
         *
         */
        int KATAKANA = 3;

        /**
         *
         */
        int LF = 10;

        /**
         *
         */
        int MIDLETTER = 4;

        /**
         *
         */
        int MIDNUM = 5;

        /**
         *
         */
        int MIDNUMLET = 11;

        /**
         *
         */
        int NEWLINE = 12;

        /**
         *
         */
        int NUMERIC = 6;

        /**
         *
         */
        int OTHER = 0;

        /**
         *
         */
        int REGIONAL_INDICATOR = 13;

        /**
         *
         */
        int SINGLE_QUOTE = 15;

        /**
         *
         */
        int WSEGSPACE = 22;

        /**
         *
         */
        int ZWJ = 21;
    }
}
