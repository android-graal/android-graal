package android.icu.lang;

/**
 * A container for the different 'enumerated types' used by UCharacter.
 */
public final class UCharacterEnums {
    private UCharacterEnums() {
    }

    /**
     * 'Enum' for the CharacterCategory constants.  These constants are
     * compatible in name <b>but not in value</b> with those defined in
     * {@link java.lang.Character}.
     * @see UCharacterCategory
     */
    public interface ECharacterCategory {
        /**
         * Character type Mc
         */
        byte COMBINING_SPACING_MARK = 8;

        /**
         * Character type Pc
         */
        byte CONNECTOR_PUNCTUATION = 22;

        /**
         * Character type Cc
         */
        byte CONTROL = 15;

        /**
         * Character type Sc
         */
        byte CURRENCY_SYMBOL = 25;

        /**
         * Character type Pd
         */
        byte DASH_PUNCTUATION = 19;

        /**
         * Character type Nd
         */
        byte DECIMAL_DIGIT_NUMBER = 9;

        /**
         * Character type Me
         */
        byte ENCLOSING_MARK = 7;

        /**
         * Character type Pe
         */
        byte END_PUNCTUATION = 21;

        /**
         * Character type Pf
         * @see #FINAL_QUOTE_PUNCTUATION
         */
        byte FINAL_PUNCTUATION = 29;

        /**
         * Character type Pf
         * This name is compatible with java.lang.Character's name for this type.
         * @see #FINAL_PUNCTUATION
         */
        byte FINAL_QUOTE_PUNCTUATION = 29;

        /**
         * Character type Cf
         */
        byte FORMAT = 16;

        /**
         * Character type Cn
         * Not Assigned (no characters in [UnicodeData.txt] have this property)
         */
        byte GENERAL_OTHER_TYPES = 0;

        /**
         * Character type Pi
         * @see #INITIAL_QUOTE_PUNCTUATION
         */
        byte INITIAL_PUNCTUATION = 28;

        /**
         * Character type Pi
         * This name is compatible with java.lang.Character's name for this type.
         * @see #INITIAL_PUNCTUATION
         */
        byte INITIAL_QUOTE_PUNCTUATION = 28;

        /**
         * Character type Nl
         */
        byte LETTER_NUMBER = 10;

        /**
         * Character type Zl
         */
        byte LINE_SEPARATOR = 13;

        /**
         * Character type Ll
         */
        byte LOWERCASE_LETTER = 2;

        /**
         * Character type Sm
         */
        byte MATH_SYMBOL = 24;

        /**
         * Character type Lm
         */
        byte MODIFIER_LETTER = 4;

        /**
         * Character type Sk
         */
        byte MODIFIER_SYMBOL = 26;

        /**
         * Character type Mn
         */
        byte NON_SPACING_MARK = 6;

        /**
         * Character type Lo
         */
        byte OTHER_LETTER = 5;

        /**
         * Character type No
         */
        byte OTHER_NUMBER = 11;

        /**
         * Character type Po
         */
        byte OTHER_PUNCTUATION = 23;

        /**
         * Character type So
         */
        byte OTHER_SYMBOL = 27;

        /**
         * Character type Zp
         */
        byte PARAGRAPH_SEPARATOR = 14;

        /**
         * Character type Co
         */
        byte PRIVATE_USE = 17;

        /**
         * Character type Zs
         */
        byte SPACE_SEPARATOR = 12;

        /**
         * Character type Ps
         */
        byte START_PUNCTUATION = 20;

        /**
         * Character type Cs
         */
        byte SURROGATE = 18;

        /**
         * Character type Lt
         */
        byte TITLECASE_LETTER = 3;

        /**
         * Unassigned character type
         */
        byte UNASSIGNED = 0;

        /**
         * Character type Lu
         */
        byte UPPERCASE_LETTER = 1;
    }

    /**
     * 'Enum' for the CharacterDirection constants. Some constants are
     * compatible in name <b>but not in value</b> with those defined in
     * {@link java.lang.Character}.
     * @see UCharacterDirection
     */
    public interface ECharacterDirection {
        /**
         * Directional type AN
         */
        int ARABIC_NUMBER = 5;

        /**
         * Directional type B
         */
        int BLOCK_SEPARATOR = 7;

        /**
         * Directional type BN
         */
        int BOUNDARY_NEUTRAL = 18;

        /**
         * Directional type CS
         */
        int COMMON_NUMBER_SEPARATOR = 6;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_ARABIC_NUMBER}.
         * Synonym of {@link #ARABIC_NUMBER}.
         */
        byte DIRECTIONALITY_ARABIC_NUMBER = 5;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_BOUNDARY_NEUTRAL}.
         * Synonym of {@link #BOUNDARY_NEUTRAL}.
         */
        byte DIRECTIONALITY_BOUNDARY_NEUTRAL = 18;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_COMMON_NUMBER_SEPARATOR}.
         * Synonym of {@link #COMMON_NUMBER_SEPARATOR}.
         */
        byte DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = 6;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_EUROPEAN_NUMBER}.
         * Synonym of {@link #EUROPEAN_NUMBER}.
         */
        byte DIRECTIONALITY_EUROPEAN_NUMBER = 2;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR}.
         * Synonym of {@link #EUROPEAN_NUMBER_SEPARATOR}.
         */
        byte DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = 3;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR}.
         * Synonym of {@link #EUROPEAN_NUMBER_TERMINATOR}.
         */
        byte DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = 4;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_LEFT_TO_RIGHT}.
         * Synonym of {@link #LEFT_TO_RIGHT}.
         */
        byte DIRECTIONALITY_LEFT_TO_RIGHT = 0;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING}.
         * Synonym of {@link #LEFT_TO_RIGHT_EMBEDDING}.
         */
        byte DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 11;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE}.
         * Synonym of {@link #LEFT_TO_RIGHT_OVERRIDE}.
         */
        byte DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 12;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_NONSPACING_MARK}.
         * Synonym of {@link #DIR_NON_SPACING_MARK}.
         */
        byte DIRECTIONALITY_NONSPACING_MARK = 17;

        /**
         * Equivalent to {@link java.lang.Character#DIRECTIONALITY_OTHER_NEUTRALS}.
         * Synonym of {@link #OTHER_NEUTRAL}.
         */
        byte DIRECTIONALITY_OTHER_NEUTRALS = 10;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_PARAGRAPH_SEPARATOR}.
         * Synonym of {@link #BLOCK_SEPARATOR}.
         */
        byte DIRECTIONALITY_PARAGRAPH_SEPARATOR = 7;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_POP_DIRECTIONAL_FORMAT}.
         * Synonym of {@link #POP_DIRECTIONAL_FORMAT}.
         */
        byte DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 16;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_RIGHT_TO_LEFT}.
         * Synonym of {@link #RIGHT_TO_LEFT}.
         */
        byte DIRECTIONALITY_RIGHT_TO_LEFT = 1;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC}.
         * Synonym of {@link #RIGHT_TO_LEFT_ARABIC}.
         */
        byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 13;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING}.
         * Synonym of {@link #RIGHT_TO_LEFT_EMBEDDING}.
         */
        byte DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 14;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE}.
         * Synonym of {@link #RIGHT_TO_LEFT_OVERRIDE}.
         */
        byte DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 15;

        /**
         * Equivalent to {@link
         * java.lang.Character#DIRECTIONALITY_SEGMENT_SEPARATOR}.
         * Synonym of {@link #SEGMENT_SEPARATOR}.
         */
        byte DIRECTIONALITY_SEGMENT_SEPARATOR = 8;

        /**
         * Undefined bidirectional character type. Undefined <code>char</code>
         * values have undefined directionality in the Unicode specification.
         */
        byte DIRECTIONALITY_UNDEFINED = -1;

        /**
         * Equivalent to {@link java.lang.Character#DIRECTIONALITY_WHITESPACE}.
         * Synonym of {@link #WHITE_SPACE_NEUTRAL}.
         */
        byte DIRECTIONALITY_WHITESPACE = 9;

        /**
         * Directional type NSM
         */
        int DIR_NON_SPACING_MARK = 17;

        /**
         * Directional type EN
         */
        int EUROPEAN_NUMBER = 2;

        /**
         * Directional type ES
         */
        int EUROPEAN_NUMBER_SEPARATOR = 3;

        /**
         * Directional type ET
         */
        int EUROPEAN_NUMBER_TERMINATOR = 4;

        /**
         * Directional type FSI
         */
        byte FIRST_STRONG_ISOLATE = 19;

        /**
         * Directional type L
         */
        int LEFT_TO_RIGHT = 0;

        /**
         * Directional type LRE
         */
        int LEFT_TO_RIGHT_EMBEDDING = 11;

        /**
         * Directional type LRI
         */
        byte LEFT_TO_RIGHT_ISOLATE = 20;

        /**
         * Directional type LRO
         */
        int LEFT_TO_RIGHT_OVERRIDE = 12;

        /**
         * Directional type ON
         */
        int OTHER_NEUTRAL = 10;

        /**
         * Directional type PDF
         */
        int POP_DIRECTIONAL_FORMAT = 16;

        /**
         * Directional type PDI
         */
        byte POP_DIRECTIONAL_ISOLATE = 22;

        /**
         * Directional type R
         */
        int RIGHT_TO_LEFT = 1;

        /**
         * Directional type AL
         */
        int RIGHT_TO_LEFT_ARABIC = 13;

        /**
         * Directional type RLE
         */
        int RIGHT_TO_LEFT_EMBEDDING = 14;

        /**
         * Directional type RLI
         */
        byte RIGHT_TO_LEFT_ISOLATE = 21;

        /**
         * Directional type RLO
         */
        int RIGHT_TO_LEFT_OVERRIDE = 15;

        /**
         * Directional type S
         */
        int SEGMENT_SEPARATOR = 8;

        /**
         * Directional type WS
         */
        int WHITE_SPACE_NEUTRAL = 9;
    }
}
