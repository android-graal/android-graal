package android.icu.lang;

/**
 * <p>Selection constants for Unicode properties.
 * <p>These constants are used in functions like
 * UCharacter.hasBinaryProperty(int) to select one of the Unicode properties.
 *
 * <p>The properties APIs are intended to reflect Unicode properties as
 * defined in the Unicode Character Database (UCD) and Unicode Technical
 * Reports (UTR).
 * <p>For details about the properties see
 * <a href="http://www.unicode.org/reports/tr44/">UAX #44: Unicode Character Database</a>.
 *
 * <p>Important: If ICU is built with UCD files from Unicode versions below
 * 3.2, then properties marked with "new" are not or not fully
 * available. Check UCharacter.getUnicodeVersion() to be sure.
 * @author Syn Wee Quek
 * @see android.icu.lang.UCharacter
 */
public interface UProperty {
    /**
     * String property Age.
     * Corresponds to UCharacter.getAge(int).
     */
    int AGE = 16384;

    /**
     * <p>Binary property Alphabetic.
     * <p>Property for UCharacter.isUAlphabetic(), different from the property
     * in UCharacter.isalpha().
     * <p>Lu + Ll + Lt + Lm + Lo + Nl + Other_Alphabetic.
     */
    int ALPHABETIC = 0;

    /**
     * Binary property ASCII_Hex_Digit (0-9 A-F a-f).
     */
    int ASCII_HEX_DIGIT = 1;

    /**
     * Binary property of strings Basic_Emoji.
     * See https://www.unicode.org/reports/tr51/#Emoji_Sets
     */
    int BASIC_EMOJI = 65;

    /**
     * Enumerated property Bidi_Class.
     * Same as UCharacter.getDirection(int), returns UCharacterDirection values.
     */
    int BIDI_CLASS = 4096;

    /**
     * <p>Binary property Bidi_Control.
     * <p>Format controls which have specific functions in the Bidi Algorithm.
     */
    int BIDI_CONTROL = 2;

    /**
     * <p>Binary property Bidi_Mirrored.
     * <p>Characters that may change display in RTL text.
     * <p>Property for UCharacter.isMirrored().
     * <p>See Bidi Algorithm; UTR 9.
     */
    int BIDI_MIRRORED = 3;

    /**
     * String property Bidi_Mirroring_Glyph.
     * Corresponds to UCharacter.getMirror(int).
     */
    int BIDI_MIRRORING_GLYPH = 16385;

    /**
     * String property Bidi_Paired_Bracket (new in Unicode 6.3).
     * Corresponds to UCharacter.getBidiPairedBracket.
     */
    int BIDI_PAIRED_BRACKET = 16397;

    /**
     * Enumerated property Bidi_Paired_Bracket_Type (new in Unicode 6.3).
     * Used in UAX #9: Unicode Bidirectional Algorithm
     * (http://www.unicode.org/reports/tr9/)
     * Returns UCharacter.BidiPairedBracketType values.
     */
    int BIDI_PAIRED_BRACKET_TYPE = 4117;

    /**
     * First constant for binary Unicode properties.
     */
    int BINARY_START = 0;

    /**
     * Enumerated property Block.
     * Same as UCharacter.UnicodeBlock.of(int), returns UCharacter.UnicodeBlock
     * values.
     */
    int BLOCK = 4097;

    /**
     * Enumerated property Canonical_Combining_Class.
     * Same as UCharacter.getCombiningClass(int), returns 8-bit numeric values.
     */
    int CANONICAL_COMBINING_CLASS = 4098;

    /**
     * Binary property Cased.
     * For Lowercase, Uppercase and Titlecase characters.
     */
    int CASED = 49;

    /**
     * String property Case_Folding.
     * Corresponds to UCharacter.foldCase(String, boolean).
     */
    int CASE_FOLDING = 16386;

    /**
     * Binary property Case_Ignorable.
     * Used in context-sensitive case mappings.
     */
    int CASE_IGNORABLE = 50;

    /**
     * <p>Binary property Case_Sensitive.
     * <p>Either the source of a case
     * mapping or <i>in</i> the target of a case mapping. Not the same as
     * the general category Cased_Letter.
     */
    int CASE_SENSITIVE = 34;

    /**
     * Binary property Changes_When_Casefolded.
     */
    int CHANGES_WHEN_CASEFOLDED = 54;

    /**
     * Binary property Changes_When_Casemapped.
     */
    int CHANGES_WHEN_CASEMAPPED = 55;

    /**
     * Binary property Changes_When_Lowercased.
     */
    int CHANGES_WHEN_LOWERCASED = 51;

    /**
     * Binary property Changes_When_NFKC_Casefolded.
     */
    int CHANGES_WHEN_NFKC_CASEFOLDED = 56;

    /**
     * Binary property Changes_When_Titlecased.
     */
    int CHANGES_WHEN_TITLECASED = 53;

    /**
     * Binary property Changes_When_Uppercased.
     */
    int CHANGES_WHEN_UPPERCASED = 52;

    /**
     * <p>Binary property Dash.
     * <p>Variations of dashes.
     */
    int DASH = 4;

    /**
     * Enumerated property Decomposition_Type.
     * Returns UCharacter.DecompositionType values.
     */
    int DECOMPOSITION_TYPE = 4099;

    /**
     * <p>Binary property Default_Ignorable_Code_Point (new).
     *
     * <p>Property that indicates codepoint is ignorable in most processing.
     *
     * <p>Codepoints (2060..206F, FFF0..FFFB, E0000..E0FFF) +
     * Other_Default_Ignorable_Code_Point + (Cf + Cc + Cs - White_Space)
     */
    int DEFAULT_IGNORABLE_CODE_POINT = 5;

    /**
     * <p>Binary property Deprecated (new).
     * <p>The usage of deprecated characters is strongly discouraged.
     */
    int DEPRECATED = 6;

    /**
     * <p>Binary property Diacritic.
     * <p>Characters that linguistically modify the meaning of another
     * character to which they apply.
     */
    int DIACRITIC = 7;

    /**
     * First constant for double Unicode properties.
     */
    int DOUBLE_START = 12288;

    /**
     * Enumerated property East_Asian_Width.
     * See http://www.unicode.org/reports/tr11/
     * Returns UCharacter.EastAsianWidth values.
     */
    int EAST_ASIAN_WIDTH = 4100;

    /**
     * Binary property Emoji.
     * See http://www.unicode.org/reports/tr51/#Emoji_Properties
     */
    int EMOJI = 57;

    /**
     * Binary property Emoji_Component.
     * See http://www.unicode.org/reports/tr51/#Emoji_Properties
     */
    int EMOJI_COMPONENT = 61;

    /**
     * Binary property of strings Emoji_Keycap_Sequence.
     * See https://www.unicode.org/reports/tr51/#Emoji_Sets
     */
    int EMOJI_KEYCAP_SEQUENCE = 66;

    /**
     * Binary property Emoji_Modifier.
     * See http://www.unicode.org/reports/tr51/#Emoji_Properties
     */
    int EMOJI_MODIFIER = 59;

    /**
     * Binary property Emoji_Modifier_Base.
     * See http://www.unicode.org/reports/tr51/#Emoji_Properties
     */
    int EMOJI_MODIFIER_BASE = 60;

    /**
     * Binary property Emoji_Presentation.
     * See http://www.unicode.org/reports/tr51/#Emoji_Properties
     */
    int EMOJI_PRESENTATION = 58;

    /**
     * Binary property Extended_Pictographic.
     * See http://www.unicode.org/reports/tr51/#Emoji_Properties
     */
    int EXTENDED_PICTOGRAPHIC = 64;

    /**
     * <p>Binary property Extender.
     * <p>Extend the value or shape of a preceding alphabetic character, e.g.
     * length and iteration marks.
     */
    int EXTENDER = 8;

    /**
     * <p>Binary property Full_Composition_Exclusion.
     * <p>CompositionExclusions.txt + Singleton Decompositions +
     * Non-Starter Decompositions.
     */
    int FULL_COMPOSITION_EXCLUSION = 9;

    /**
     * Enumerated property General_Category.
     * Same as UCharacter.getType(int), returns UCharacterCategory values.
     */
    int GENERAL_CATEGORY = 4101;

    /**
     * Bitmask property General_Category_Mask.
     * This is the General_Category property returned as a bit mask.
     * When used in UCharacter.getIntPropertyValue(c),
     * returns bit masks for UCharacterCategory values where exactly one bit is set.
     * When used with UCharacter.getPropertyValueName() and UCharacter.getPropertyValueEnum(),
     * a multi-bit mask is used for sets of categories like "Letters".
     */
    int GENERAL_CATEGORY_MASK = 8192;

    /**
     * <p>Binary property Grapheme_Base (new).
     * <p>For programmatic determination of grapheme cluster boundaries.
     * [0..10FFFF]-Cc-Cf-Cs-Co-Cn-Zl-Zp-Grapheme_Link-Grapheme_Extend-CGJ
     */
    int GRAPHEME_BASE = 10;

    /**
     * Enumerated property Grapheme_Cluster_Break (new in Unicode 4.1).
     * Used in UAX #29: Text Boundaries
     * (http://www.unicode.org/reports/tr29/)
     * Returns UCharacter.GraphemeClusterBreak values.
     */
    int GRAPHEME_CLUSTER_BREAK = 4114;

    /**
     * <p>Binary property Grapheme_Extend (new).
     * <p>For programmatic determination of grapheme cluster boundaries.
     * <p>Me+Mn+Mc+Other_Grapheme_Extend-Grapheme_Link-CGJ
     */
    int GRAPHEME_EXTEND = 11;

    /**
     * <p>Binary property Grapheme_Link (new).
     * <p>For programmatic determination of grapheme cluster boundaries.
     */
    int GRAPHEME_LINK = 12;

    /**
     * Enumerated property Hangul_Syllable_Type, new in Unicode 4.
     * Returns UCharacter.HangulSyllableType values.
     */
    int HANGUL_SYLLABLE_TYPE = 4107;

    /**
     * <p>Binary property Hex_Digit.
     * <p>Characters commonly used for hexadecimal numbers.
     */
    int HEX_DIGIT = 13;

    /**
     * <p>Binary property Hyphen.
     * <p>Dashes used to mark connections between pieces of words, plus the
     * Katakana middle dot.
     */
    int HYPHEN = 14;

    /**
     * <p>Binary property Ideographic.
     * <p>CJKV ideographs.
     */
    int IDEOGRAPHIC = 17;

    /**
     * <p>Binary property IDS_Binary_Operator (new).
     * <p>For programmatic determination of Ideographic Description Sequences.
     */
    int IDS_BINARY_OPERATOR = 18;

    /**
     * <p>Binary property IDS_Trinary_Operator (new).
     * <p>For programmatic determination of Ideographic Description
     * Sequences.
     */
    int IDS_TRINARY_OPERATOR = 19;

    /**
     * Binary property IDS_Unary_Operator.
     * For programmatic determination of Ideographic Description Sequences.
     */
    int IDS_UNARY_OPERATOR = 72;

    /**
     * Binary property ID_Compat_Math_Continue.
     * <p>Used in mathematical identifier profile in UAX #31.
     */
    int ID_COMPAT_MATH_CONTINUE = 74;

    /**
     * Binary property ID_Compat_Math_Start.
     * <p>Used in mathematical identifier profile in UAX #31.
     */
    int ID_COMPAT_MATH_START = 73;

    /**
     * <p>Binary property ID_Continue.
     * <p>Characters that can continue an identifier.
     * <p>ID_Start+Mn+Mc+Nd+Pc
     */
    int ID_CONTINUE = 15;

    /**
     * <p>Binary property ID_Start.
     * <p>Characters that can start an identifier.
     * <p>Lu+Ll+Lt+Lm+Lo+Nl
     */
    int ID_START = 16;

    /**
     * Enumerated property Indic_Positional_Category.
     * New in Unicode 6.0 as provisional property Indic_Matra_Category;
     * renamed and changed to informative in Unicode 8.0.
     * See http://www.unicode.org/reports/tr44/#IndicPositionalCategory.txt
     */
    int INDIC_POSITIONAL_CATEGORY = 4118;

    /**
     * Enumerated property Indic_Syllabic_Category.
     * New in Unicode 6.0 as provisional; informative since Unicode 8.0.
     * See http://www.unicode.org/reports/tr44/#IndicSyllabicCategory.txt
     */
    int INDIC_SYLLABIC_CATEGORY = 4119;

    /**
     * First constant for enumerated/integer Unicode properties.
     */
    int INT_START = 4096;

    /**
     * Enumerated property Joining_Group.
     * Returns UCharacter.JoiningGroup values.
     */
    int JOINING_GROUP = 4102;

    /**
     * Enumerated property Joining_Type.
     * Returns UCharacter.JoiningType values.
     */
    int JOINING_TYPE = 4103;

    /**
     * <p>Binary property Join_Control.
     * <p>Format controls for cursive joining and ligation.
     */
    int JOIN_CONTROL = 20;

    /**
     * Enumerated property Lead_Canonical_Combining_Class.
     * ICU-specific property for the ccc of the first code point
     * of the decomposition, or lccc(c)=ccc(NFD(c)[0]).
     * Useful for checking for canonically ordered text;
     * see Normalizer.FCD and http://www.unicode.org/notes/tn5/#FCD .
     * Returns 8-bit numeric values like CANONICAL_COMBINING_CLASS.
     */
    int LEAD_CANONICAL_COMBINING_CLASS = 4112;

    /**
     * Enumerated property Line_Break.
     * Returns UCharacter.LineBreak values.
     */
    int LINE_BREAK = 4104;

    /**
     * <p>Binary property Logical_Order_Exception (new).
     * <p>Characters that do not use logical order and require special
     * handling in most processing.
     */
    int LOGICAL_ORDER_EXCEPTION = 21;

    /**
     * <p>Binary property Lowercase.
     * <p>Same as UCharacter.isULowercase(), different from
     * UCharacter.islower().
     * <p>Ll+Other_Lowercase
     */
    int LOWERCASE = 22;

    /**
     * String property Lowercase_Mapping.
     * Corresponds to UCharacter.toLowerCase(String).
     */
    int LOWERCASE_MAPPING = 16388;

    /**
     * First constant for bit-mask Unicode properties.
     */
    int MASK_START = 8192;

    /**
     * <p>Binary property Math.
     *  <p>Sm+Other_Math
     */
    int MATH = 23;

    /**
     * String property Name.
     * Corresponds to UCharacter.getName(int).
     */
    int NAME = 16389;

    /**
     * Binary property NFC_Inert.
     * ICU-specific property for characters that are inert under NFC,
     * i.e., they do not interact with adjacent characters.
     * Used for example in normalizing transforms in incremental mode
     * to find the boundary of safely normalizable text despite possible
     * text additions.
     * @see #NFD_INERT
     */
    int NFC_INERT = 39;

    /**
     * Enumerated property NFC_Quick_Check.
     * Returns numeric values compatible with Normalizer.QuickCheckResult.
     */
    int NFC_QUICK_CHECK = 4110;

    /**
     * Binary property NFD_Inert.
     * ICU-specific property for characters that are inert under NFD,
     * i.e., they do not interact with adjacent characters.
     * Used for example in normalizing transforms in incremental mode
     * to find the boundary of safely normalizable text despite possible
     * text additions.
     *
     * There is one such property per normalization form.
     * These properties are computed as follows - an inert character is:
     * a) unassigned, or ALL of the following:
     * b) of combining class 0.
     * c) not decomposed by this normalization form.
     * AND if NFC or NFKC,
     * d) can never compose with a previous character.
     * e) can never compose with a following character.
     * f) can never change if another character is added.
     * Example: a-breve might satisfy all but f, but if you
     * add an ogonek it changes to a-ogonek + breve
     *
     * See also com.ibm.text.UCD.NFSkippable in the ICU4J repository,
     * and icu/source/common/unormimp.h .
     */
    int NFD_INERT = 37;

    /**
     * Enumerated property NFD_Quick_Check.
     * Returns numeric values compatible with Normalizer.QuickCheckResult.
     */
    int NFD_QUICK_CHECK = 4108;

    /**
     * Binary property NFKC_Inert.
     * ICU-specific property for characters that are inert under NFKC,
     * i.e., they do not interact with adjacent characters.
     * Used for example in normalizing transforms in incremental mode
     * to find the boundary of safely normalizable text despite possible
     * text additions.
     * @see #NFD_INERT
     */
    int NFKC_INERT = 40;

    /**
     * Enumerated property NFKC_Quick_Check.
     * Returns numeric values compatible with Normalizer.QuickCheckResult.
     */
    int NFKC_QUICK_CHECK = 4111;

    /**
     * Binary property NFKD_Inert.
     * ICU-specific property for characters that are inert under NFKD,
     * i.e., they do not interact with adjacent characters.
     * Used for example in normalizing transforms in incremental mode
     * to find the boundary of safely normalizable text despite possible
     * text additions.
     * @see #NFD_INERT
     */
    int NFKD_INERT = 38;

    /**
     * Enumerated property NFKD_Quick_Check.
     * Returns numeric values compatible with Normalizer.QuickCheckResult.
     */
    int NFKD_QUICK_CHECK = 4109;

    /**
     * <p>Binary property Noncharacter_Code_Point.
     * <p>Code points that are explicitly defined as illegal for the encoding
     * of characters.
     */
    int NONCHARACTER_CODE_POINT = 24;

    /**
     * Enumerated property Numeric_Type.
     * Returns UCharacter.NumericType values.
     */
    int NUMERIC_TYPE = 4105;

    /**
     * Double property Numeric_Value.
     * Corresponds to UCharacter.getUnicodeNumericValue(int).
     */
    int NUMERIC_VALUE = 12288;

    /**
     * First constant for Unicode properties with unusual value types.
     */
    int OTHER_PROPERTY_START = 28672;

    /**
     * Binary property Pattern_Syntax (new in Unicode 4.1).
     * See UAX #31 Identifier and Pattern Syntax
     * (http://www.unicode.org/reports/tr31/)
     */
    int PATTERN_SYNTAX = 42;

    /**
     * Binary property Pattern_White_Space (new in Unicode 4.1).
     * See UAX #31 Identifier and Pattern Syntax
     * (http://www.unicode.org/reports/tr31/)
     */
    int PATTERN_WHITE_SPACE = 43;

    /**
     * Binary property alnum (a C/POSIX character class).
     * Implemented according to the UTS #18 Annex C Standard Recommendation.
     * See the UCharacter class documentation.
     */
    int POSIX_ALNUM = 44;

    /**
     * Binary property blank (a C/POSIX character class).
     * Implemented according to the UTS #18 Annex C Standard Recommendation.
     * See the UCharacter class documentation.
     */
    int POSIX_BLANK = 45;

    /**
     * Binary property graph (a C/POSIX character class).
     * Implemented according to the UTS #18 Annex C Standard Recommendation.
     * See the UCharacter class documentation.
     */
    int POSIX_GRAPH = 46;

    /**
     * Binary property print (a C/POSIX character class).
     * Implemented according to the UTS #18 Annex C Standard Recommendation.
     * See the UCharacter class documentation.
     */
    int POSIX_PRINT = 47;

    /**
     * Binary property xdigit (a C/POSIX character class).
     * Implemented according to the UTS #18 Annex C Standard Recommendation.
     * See the UCharacter class documentation.
     */
    int POSIX_XDIGIT = 48;

    /**
     * Binary property Prepended_Concatenation_Mark.
     */
    int PREPENDED_CONCATENATION_MARK = 63;

    /**
     * <p>Binary property Quotation_Mark.
     */
    int QUOTATION_MARK = 25;

    /**
     * <p>Binary property Radical (new).
     * <p>For programmatic determination of Ideographic Description
     * Sequences.
     */
    int RADICAL = 26;

    /**
     * Binary property Regional_Indicator.
     */
    int REGIONAL_INDICATOR = 62;

    /**
     * Binary property of strings RGI_Emoji.
     * See https://www.unicode.org/reports/tr51/#Emoji_Sets
     */
    int RGI_EMOJI = 71;

    /**
     * Binary property of strings RGI_Emoji_Flag_Sequence.
     * See https://www.unicode.org/reports/tr51/#Emoji_Sets
     */
    int RGI_EMOJI_FLAG_SEQUENCE = 68;

    /**
     * Binary property of strings RGI_Emoji_Modifier_Sequence.
     * See https://www.unicode.org/reports/tr51/#Emoji_Sets
     */
    int RGI_EMOJI_MODIFIER_SEQUENCE = 67;

    /**
     * Binary property of strings RGI_Emoji_Tag_Sequence.
     * See https://www.unicode.org/reports/tr51/#Emoji_Sets
     */
    int RGI_EMOJI_TAG_SEQUENCE = 69;

    /**
     * Binary property of strings RGI_Emoji_ZWJ_Sequence.
     * See https://www.unicode.org/reports/tr51/#Emoji_Sets
     */
    int RGI_EMOJI_ZWJ_SEQUENCE = 70;

    /**
     * Enumerated property Script.
     * Same as UScript.getScript(int), returns UScript values.
     */
    int SCRIPT = 4106;

    /**
     * Miscellaneous property Script_Extensions (new in Unicode 6.0).
     * Some characters are commonly used in multiple scripts.
     * For more information, see UAX #24: http://www.unicode.org/reports/tr24/.
     * Corresponds to UScript.hasScript and UScript.getScriptExtensions.
     */
    int SCRIPT_EXTENSIONS = 28672;

    /**
     * Binary Property Segment_Starter.
     * ICU-specific property for characters that are starters in terms of
     * Unicode normalization and combining character sequences.
     * They have ccc=0 and do not occur in non-initial position of the
     * canonical decomposition of any character
     * (like " in NFD(a-umlaut) and a Jamo T in an NFD(Hangul LVT)).
     * ICU uses this property for segmenting a string for generating a set of
     * canonically equivalent strings, e.g. for canonical closure while
     * processing collation tailoring rules.
     */
    int SEGMENT_STARTER = 41;

    /**
     * Enumerated property Sentence_Break (new in Unicode 4.1).
     * Used in UAX #29: Text Boundaries
     * (http://www.unicode.org/reports/tr29/)
     * Returns UCharacter.SentenceBreak values.
     */
    int SENTENCE_BREAK = 4115;

    /**
     * String property Simple_Case_Folding.
     * Corresponds to UCharacter.foldCase(int, boolean).
     */
    int SIMPLE_CASE_FOLDING = 16390;

    /**
     * String property Simple_Lowercase_Mapping.
     * Corresponds to UCharacter.toLowerCase(int).
     */
    int SIMPLE_LOWERCASE_MAPPING = 16391;

    /**
     * String property Simple_Titlecase_Mapping.
     * Corresponds to UCharacter.toTitleCase(int).
     */
    int SIMPLE_TITLECASE_MAPPING = 16392;

    /**
     * String property Simple_Uppercase_Mapping.
     * Corresponds to UCharacter.toUpperCase(int).
     */
    int SIMPLE_UPPERCASE_MAPPING = 16393;

    /**
     * <p>Binary property Soft_Dotted (new).
     * <p>Characters with a "soft dot", like i or j.
     * <p>An accent placed on these characters causes the dot to disappear.
     */
    int SOFT_DOTTED = 27;

    /**
     * First constant for string Unicode properties.
     */
    int STRING_START = 16384;

    /**
     * Binary property STerm (new in Unicode 4.0.1).
     * Sentence Terminal. Used in UAX #29: Text Boundaries
     * (http://www.unicode.org/reports/tr29/)
     */
    int S_TERM = 35;

    /**
     * <p>Binary property Terminal_Punctuation.
     * <p>Punctuation characters that generally mark the end of textual
     * units.
     */
    int TERMINAL_PUNCTUATION = 28;

    /**
     * String property Titlecase_Mapping.
     * Corresponds to UCharacter.toTitleCase(String).
     */
    int TITLECASE_MAPPING = 16394;

    /**
     * Enumerated property Trail_Canonical_Combining_Class.
     * ICU-specific property for the ccc of the last code point
     * of the decomposition, or lccc(c)=ccc(NFD(c)[last]).
     * Useful for checking for canonically ordered text;
     * see Normalizer.FCD and http://www.unicode.org/notes/tn5/#FCD .
     * Returns 8-bit numeric values like CANONICAL_COMBINING_CLASS.
     */
    int TRAIL_CANONICAL_COMBINING_CLASS = 4113;

    /**
     * <p>Binary property Unified_Ideograph (new).
     * <p>For programmatic determination of Ideographic Description
     * Sequences.
     */
    int UNIFIED_IDEOGRAPH = 29;

    /**
     * <p>Binary property Uppercase.
     * <p>Same as UCharacter.isUUppercase(), different from
     * UCharacter.isUpperCase().
     * <p>Lu+Other_Uppercase
     */
    int UPPERCASE = 30;

    /**
     * String property Uppercase_Mapping.
     * Corresponds to UCharacter.toUpperCase(String).
     */
    int UPPERCASE_MAPPING = 16396;

    /**
     * Binary property Variation_Selector (new in Unicode 4.0.1).
     * Indicates all those characters that qualify as Variation Selectors.
     * For details on the behavior of these characters,
     * see StandardizedVariants.html and 15.6 Variation Selectors.
     */
    int VARIATION_SELECTOR = 36;

    /**
     * Enumerated property Vertical_Orientation.
     * Used for UAX #50 Unicode Vertical Text Layout (https://www.unicode.org/reports/tr50/).
     * New as a UCD property in Unicode 10.0.
     */
    int VERTICAL_ORIENTATION = 4120;

    /**
     * <p>Binary property White_Space.
     * <p>Same as UCharacter.isUWhiteSpace(), different from
     * UCharacter.isSpace() and UCharacter.isWhitespace().
     * Space characters+TAB+CR+LF-ZWSP-ZWNBSP
     */
    int WHITE_SPACE = 31;

    /**
     * Enumerated property Word_Break (new in Unicode 4.1).
     * Used in UAX #29: Text Boundaries
     * (http://www.unicode.org/reports/tr29/)
     * Returns UCharacter.WordBreak values.
     */
    int WORD_BREAK = 4116;

    /**
     * <p>Binary property XID_Continue.
     * <p>ID_Continue modified to allow closure under normalization forms
     * NFKC and NFKD.
     */
    int XID_CONTINUE = 32;

    /**
     * <p>Binary property XID_Start.
     * <p>ID_Start modified to allow closure under normalization forms NFKC
     * and NFKD.
     */
    int XID_START = 33;

    /**
     * Selector constants for UCharacter.getPropertyName() and
     * UCharacter.getPropertyValueName().  These selectors are used to
     * choose which name is returned for a given property or value.
     * All properties and values have a long name.  Most have a short
     * name, but some do not.  Unicode allows for additional names,
     * beyond the long and short name, which would be indicated by
     * LONG + i, where i=1, 2,...
     *
     * @see UCharacter#getPropertyName
     * @see UCharacter#getPropertyValueName
     */
    interface NameChoice {
        /**
         * Selector for the long name of a property or value.  All
         * properties and values have a long name.
         */
        int LONG = 1;

        /**
         * Selector for the abbreviated name of a property or value.
         * Most properties and values have a short name; those that do
         * not return null.
         */
        int SHORT = 0;
    }
}
