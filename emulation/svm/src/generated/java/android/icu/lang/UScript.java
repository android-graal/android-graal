package android.icu.lang;

/**
 * Constants for ISO 15924 script codes, and related functions.
 *
 * <p>The current set of script code constants supports at least all scripts
 * that are encoded in the version of Unicode which ICU currently supports.
 * The names of the constants are usually derived from the
 * Unicode script property value aliases.
 * See UAX #24 Unicode Script Property (http://www.unicode.org/reports/tr24/)
 * and http://www.unicode.org/Public/UCD/latest/ucd/PropertyValueAliases.txt .
 *
 * <p>In addition, constants for many ISO 15924 script codes
 * are included, for use with language tags, CLDR data, and similar.
 * Some of those codes are not used in the Unicode Character Database (UCD).
 * For example, there are no characters that have a UCD script property value of
 * Hans or Hant. All Han ideographs have the Hani script property value in Unicode.
 *
 * <p>Private-use codes Qaaa..Qabx are not included, except as used in the UCD or in CLDR.
 *
 * <p>Starting with ICU 55, script codes are only added when their scripts
 * have been or will certainly be encoded in Unicode,
 * and have been assigned Unicode script property value aliases,
 * to ensure that their script names are stable and match the names of the constants.
 * Script codes like Latf and Aran that are not subject to separate encoding
 * may be added at any time.
 */
public final class UScript {
    /**
     * ISO 15924 script code
     */
    public static final int ADLAM = 167;

    /**
     * ISO 15924 script code
     */
    public static final int AFAKA = 147;

    /**
     * ISO 15924 script code
     */
    public static final int AHOM = 161;

    /**
     * ISO 15924 script code
     */
    public static final int ANATOLIAN_HIEROGLYPHS = 156;

    /**
     * Arabic
     */
    public static final int ARABIC = 2;

    /**
     *
     */
    public static final int ARABIC_NASTALIQ = 200;

    /**
     * Armenian
     */
    public static final int ARMENIAN = 3;

    /**
     * ISO 15924 script code
     */
    public static final int AVESTAN = 117;

    /**
     * ISO 15924 script code
     */
    public static final int BALINESE = 62;

    /**
     * ISO 15924 script code
     */
    public static final int BAMUM = 130;

    /**
     * ISO 15924 script code
     */
    public static final int BASSA_VAH = 134;

    /**
     * ISO 15924 script code
     */
    public static final int BATAK = 63;

    /**
     * Bengali
     */
    public static final int BENGALI = 4;

    /**
     * ISO 15924 script code
     */
    public static final int BHAIKSUKI = 168;

    /**
     * ISO 15924 script code
     */
    public static final int BLISSYMBOLS = 64;

    /**
     * ISO 15924 script code
     */
    public static final int BOOK_PAHLAVI = 124;

    /**
     * Bopomofo
     */
    public static final int BOPOMOFO = 5;

    /**
     * ISO 15924 script code
     */
    public static final int BRAHMI = 65;

    /**
     * Braille
     * Script in Unicode 4
     */
    public static final int BRAILLE = 46;

    /**
     * Script in Unicode 4.1
     */
    public static final int BUGINESE = 55;

    /**
     * Buhid
     */
    public static final int BUHID = 44;

    /**
     * Unified Canadian Aboriginal Symbols
     */
    public static final int CANADIAN_ABORIGINAL = 40;

    /**
     * ISO 15924 script code
     */
    public static final int CARIAN = 104;

    /**
     * ISO 15924 script code
     */
    public static final int CAUCASIAN_ALBANIAN = 159;

    /**
     * ISO 15924 script code
     */
    public static final int CHAKMA = 118;

    /**
     * ISO 15924 script code
     */
    public static final int CHAM = 66;

    /**
     * Cherokee
     */
    public static final int CHEROKEE = 6;

    /**
     *
     */
    public static final int CHORASMIAN = 189;

    /**
     * ISO 15924 script code
     */
    public static final int CIRTH = 67;

    /**
     * Common
     */
    public static final int COMMON = 0;

    /**
     * Coptic
     */
    public static final int COPTIC = 7;

    /**
     * ISO 15924 script code
     */
    public static final int CUNEIFORM = 101;

    /**
     * Cypriot
     * Script in Unicode 4
     */
    public static final int CYPRIOT = 47;

    /**
     *
     */
    public static final int CYPRO_MINOAN = 193;

    /**
     * Cyrillic
     */
    public static final int CYRILLIC = 8;

    /**
     * ISO 15924 script code
     */
    public static final int DEMOTIC_EGYPTIAN = 69;

    /**
     * Deseret
     */
    public static final int DESERET = 9;

    /**
     * Devanagari
     */
    public static final int DEVANAGARI = 10;

    /**
     *
     */
    public static final int DIVES_AKURU = 190;

    /**
     * ISO 15924 script code
     */
    public static final int DOGRA = 178;

    /**
     * ISO 15924 script code
     */
    public static final int DUPLOYAN = 135;

    /**
     * ISO 15924 script code
     */
    public static final int EASTERN_SYRIAC = 97;

    /**
     * ISO 15924 script code
     */
    public static final int EGYPTIAN_HIEROGLYPHS = 71;

    /**
     * ISO 15924 script code
     */
    public static final int ELBASAN = 136;

    /**
     *
     */
    public static final int ELYMAIC = 185;

    /**
     * ISO 15924 script code
     */
    public static final int ESTRANGELO_SYRIAC = 95;

    /**
     * Ethiopic
     */
    public static final int ETHIOPIC = 11;

    /**
     *
     */
    public static final int GARAY = 201;

    /**
     * Georgian
     */
    public static final int GEORGIAN = 12;

    /**
     * Script in Unicode 4.1
     */
    public static final int GLAGOLITIC = 56;

    /**
     * Gothic
     */
    public static final int GOTHIC = 13;

    /**
     * ISO 15924 script code
     */
    public static final int GRANTHA = 137;

    /**
     * Greek
     */
    public static final int GREEK = 14;

    /**
     * Gujarati
     */
    public static final int GUJARATI = 15;

    /**
     *
     */
    public static final int GUNJALA_GONDI = 179;

    /**
     * Gurmukhi
     */
    public static final int GURMUKHI = 16;

    /**
     *
     */
    public static final int GURUNG_KHEMA = 202;

    /**
     * Han
     */
    public static final int HAN = 17;

    /**
     * Hangul
     */
    public static final int HANGUL = 18;

    /**
     *
     */
    public static final int HANIFI_ROHINGYA = 182;

    /**
     * Hanunooo
     */
    public static final int HANUNOO = 43;

    /**
     * ISO 15924 script code
     */
    public static final int HAN_WITH_BOPOMOFO = 172;

    /**
     * ISO 15924 script code
     */
    public static final int HARAPPAN_INDUS = 77;

    /**
     * ISO 15924 script code
     */
    public static final int HATRAN = 162;

    /**
     * Hebrew
     */
    public static final int HEBREW = 19;

    /**
     * ISO 15924 script code
     */
    public static final int HIERATIC_EGYPTIAN = 70;

    /**
     * Hiragana
     */
    public static final int HIRAGANA = 20;

    /**
     * ISO 15924 script code
     */
    public static final int IMPERIAL_ARAMAIC = 116;

    /**
     * Inherited
     */
    public static final int INHERITED = 1;

    /**
     * ISO 15924 script code
     */
    public static final int INSCRIPTIONAL_PAHLAVI = 122;

    /**
     * ISO 15924 script code
     */
    public static final int INSCRIPTIONAL_PARTHIAN = 125;

    /**
     * Invalid code
     */
    public static final int INVALID_CODE = -1;

    /**
     * ISO 15924 script code
     */
    public static final int JAMO = 173;

    /**
     * ISO 15924 script code
     */
    public static final int JAPANESE = 105;

    /**
     * ISO 15924 script code
     */
    public static final int JAVANESE = 78;

    /**
     * ISO 15924 script code
     */
    public static final int JURCHEN = 148;

    /**
     * ISO 15924 script code
     */
    public static final int KAITHI = 120;

    /**
     * Kannada
     */
    public static final int KANNADA = 21;

    /**
     * Katakana
     */
    public static final int KATAKANA = 22;

    /**
     * Script in Unicode 4.0.1
     */
    public static final int KATAKANA_OR_HIRAGANA = 54;

    /**
     *
     */
    public static final int KAWI = 198;

    /**
     * ISO 15924 script code
     */
    public static final int KAYAH_LI = 79;

    /**
     * Script in Unicode 4.1
     */
    public static final int KHAROSHTHI = 57;

    /**
     *
     */
    public static final int KHITAN_SMALL_SCRIPT = 191;

    /**
     * Khmer
     */
    public static final int KHMER = 23;

    /**
     * ISO 15924 script code
     */
    public static final int KHOJKI = 157;

    /**
     * ISO 15924 script code
     */
    public static final int KHUDAWADI = 145;

    /**
     * ISO 15924 script code
     */
    public static final int KHUTSURI = 72;

    /**
     *
     */
    public static final int KIRAT_RAI = 203;

    /**
     * ISO 15924 script code
     */
    public static final int KOREAN = 119;

    /**
     * ISO 15924 script code
     */
    public static final int KPELLE = 138;

    /**
     * ISO 15924 script code
     */
    public static final int LANNA = 106;

    /**
     * Lao
     */
    public static final int LAO = 24;

    /**
     * Latin
     */
    public static final int LATIN = 25;

    /**
     * ISO 15924 script code
     */
    public static final int LATIN_FRAKTUR = 80;

    /**
     * ISO 15924 script code
     */
    public static final int LATIN_GAELIC = 81;

    /**
     * ISO 15924 script code
     */
    public static final int LEPCHA = 82;

    /**
     * Limbu
     * Script in Unicode 4
     */
    public static final int LIMBU = 48;

    /**
     * ISO 15924 script code
     */
    public static final int LINEAR_A = 83;

    /**
     * Linear B
     * Script in Unicode 4
     */
    public static final int LINEAR_B = 49;

    /**
     * ISO 15924 script code
     */
    public static final int LISU = 131;

    /**
     * ISO 15924 script code
     */
    public static final int LOMA = 139;

    /**
     * ISO 15924 script code
     */
    public static final int LYCIAN = 107;

    /**
     * ISO 15924 script code
     */
    public static final int LYDIAN = 108;

    /**
     * ISO 15924 script code
     */
    public static final int MAHAJANI = 160;

    /**
     *
     */
    public static final int MAKASAR = 180;

    /**
     * Malayalam
     */
    public static final int MALAYALAM = 26;

    /**
     * ISO 15924 script code
     */
    public static final int MANDAEAN = 84;

    /**
     * ISO 15924 script code
     */
    public static final int MANDAIC = 84;

    /**
     * ISO 15924 script code
     */
    public static final int MANICHAEAN = 121;

    /**
     * ISO 15924 script code
     */
    public static final int MARCHEN = 169;

    /**
     * ISO 15924 script code
     */
    public static final int MASARAM_GONDI = 175;

    /**
     * ISO 15924 script code
     */
    public static final int MATHEMATICAL_NOTATION = 128;

    /**
     * ISO 15924 script code
     */
    public static final int MAYAN_HIEROGLYPHS = 85;

    /**
     *
     */
    public static final int MEDEFAIDRIN = 181;

    /**
     * ISO 15924 script code
     */
    public static final int MEITEI_MAYEK = 115;

    /**
     * Mende Kikakui
     * ISO 15924 script code
     */
    public static final int MENDE = 140;

    /**
     * ISO 15924 script code
     */
    public static final int MEROITIC = 86;

    /**
     * ISO 15924 script code
     */
    public static final int MEROITIC_CURSIVE = 141;

    /**
     * ISO 15924 script code
     */
    public static final int MEROITIC_HIEROGLYPHS = 86;

    /**
     * ISO 15924 script code
     */
    public static final int MIAO = 92;

    /**
     * ISO 15924 script code
     */
    public static final int MODI = 163;

    /**
     * Mangolian
     */
    public static final int MONGOLIAN = 27;

    /**
     * ISO 15924 script code
     */
    public static final int MOON = 114;

    /**
     * ISO 15924 script code
     */
    public static final int MRO = 149;

    /**
     * ISO 15924 script code
     */
    public static final int MULTANI = 164;

    /**
     * Myammar
     */
    public static final int MYANMAR = 28;

    /**
     * ISO 15924 script code
     */
    public static final int NABATAEAN = 143;

    /**
     *
     */
    public static final int NAG_MUNDARI = 199;

    /**
     * ISO 15924 script code
     */
    public static final int NAKHI_GEBA = 132;

    /**
     *
     */
    public static final int NANDINAGARI = 187;

    /**
     * ISO 15924 script code
     */
    public static final int NEWA = 170;

    /**
     * Script in Unicode 4.1
     */
    public static final int NEW_TAI_LUE = 59;

    /**
     * ISO 15924 script code
     */
    public static final int NKO = 87;

    /**
     * ISO 15924 script code
     */
    public static final int NUSHU = 150;

    /**
     *
     */
    public static final int NYIAKENG_PUACHUE_HMONG = 186;

    /**
     * Ogham
     */
    public static final int OGHAM = 29;

    /**
     * ISO 15924 script code
     */
    public static final int OLD_CHURCH_SLAVONIC_CYRILLIC = 68;

    /**
     * ISO 15924 script code
     */
    public static final int OLD_HUNGARIAN = 76;

    /**
     * Old Itallic
     */
    public static final int OLD_ITALIC = 30;

    /**
     * ISO 15924 script code
     */
    public static final int OLD_NORTH_ARABIAN = 142;

    /**
     * ISO 15924 script code
     */
    public static final int OLD_PERMIC = 89;

    /**
     * Script in Unicode 4.1
     */
    public static final int OLD_PERSIAN = 61;

    /**
     *
     */
    public static final int OLD_SOGDIAN = 184;

    /**
     * ISO 15924 script code
     */
    public static final int OLD_SOUTH_ARABIAN = 133;

    /**
     *
     */
    public static final int OLD_UYGHUR = 194;

    /**
     * ISO 15924 script code
     */
    public static final int OL_CHIKI = 109;

    /**
     *
     */
    public static final int OL_ONAL = 204;

    /**
     * Oriya
     */
    public static final int ORIYA = 31;

    /**
     * ISO 15924 script code
     */
    public static final int ORKHON = 88;

    /**
     * ISO 15924 script code
     */
    public static final int OSAGE = 171;

    /**
     * Osmanya
     * Script in Unicode 4
     */
    public static final int OSMANYA = 50;

    /**
     * ISO 15924 script code
     */
    public static final int PAHAWH_HMONG = 75;

    /**
     * ISO 15924 script code
     */
    public static final int PALMYRENE = 144;

    /**
     * ISO 15924 script code
     */
    public static final int PAU_CIN_HAU = 165;

    /**
     * ISO 15924 script code
     */
    public static final int PHAGS_PA = 90;

    /**
     * ISO 15924 script code
     */
    public static final int PHOENICIAN = 91;

    /**
     * ISO 15924 script code
     */
    public static final int PHONETIC_POLLARD = 92;

    /**
     * ISO 15924 script code
     */
    public static final int PSALTER_PAHLAVI = 123;

    /**
     * ISO 15924 script code
     */
    public static final int REJANG = 110;

    /**
     * ISO 15924 script code
     */
    public static final int RONGORONGO = 93;

    /**
     * Runic
     */
    public static final int RUNIC = 32;

    /**
     * ISO 15924 script code
     */
    public static final int SAMARITAN = 126;

    /**
     * ISO 15924 script code
     */
    public static final int SARATI = 94;

    /**
     * ISO 15924 script code
     */
    public static final int SAURASHTRA = 111;

    /**
     * ISO 15924 script code
     */
    public static final int SHARADA = 151;

    /**
     * Shavian
     * Script in Unicode 4
     */
    public static final int SHAVIAN = 51;

    /**
     * ISO 15924 script code
     */
    public static final int SIDDHAM = 166;

    /**
     * ISO 15924 script code for Sutton SignWriting
     */
    public static final int SIGN_WRITING = 112;

    /**
     * ISO 15924 script code
     */
    public static final int SIMPLIFIED_HAN = 73;

    /**
     * ISO 15924 script code
     */
    public static final int SINDHI = 145;

    /**
     * Sinhala
     */
    public static final int SINHALA = 33;

    /**
     *
     */
    public static final int SOGDIAN = 183;

    /**
     * ISO 15924 script code
     */
    public static final int SORA_SOMPENG = 152;

    /**
     * ISO 15924 script code
     */
    public static final int SOYOMBO = 176;

    /**
     * ISO 15924 script code
     */
    public static final int SUNDANESE = 113;

    /**
     *
     */
    public static final int SUNUWAR = 205;

    /**
     * Script in Unicode 4.1
     */
    public static final int SYLOTI_NAGRI = 58;

    /**
     * ISO 15924 script code
     */
    public static final int SYMBOLS = 129;

    /**
     * ISO 15924 script code
     */
    public static final int SYMBOLS_EMOJI = 174;

    /**
     * Syriac
     */
    public static final int SYRIAC = 34;

    /**
     * Tagalog
     */
    public static final int TAGALOG = 42;

    /**
     * Tagbanwa
     */
    public static final int TAGBANWA = 45;

    /**
     * Tai Le
     * Script in Unicode 4
     */
    public static final int TAI_LE = 52;

    /**
     * ISO 15924 script code
     */
    public static final int TAI_VIET = 127;

    /**
     * ISO 15924 script code
     */
    public static final int TAKRI = 153;

    /**
     * Tamil
     */
    public static final int TAMIL = 35;

    /**
     *
     */
    public static final int TANGSA = 195;

    /**
     * ISO 15924 script code
     */
    public static final int TANGUT = 154;

    /**
     * Telugu
     */
    public static final int TELUGU = 36;

    /**
     * ISO 15924 script code
     */
    public static final int TENGWAR = 98;

    /**
     * Thana
     */
    public static final int THAANA = 37;

    /**
     * Thai
     */
    public static final int THAI = 38;

    /**
     * Tibetan
     */
    public static final int TIBETAN = 39;

    /**
     * Script in Unicode 4.1
     */
    public static final int TIFINAGH = 60;

    /**
     * ISO 15924 script code
     */
    public static final int TIRHUTA = 158;

    /**
     *
     */
    public static final int TODHRI = 206;

    /**
     *
     */
    public static final int TOTO = 196;

    /**
     * ISO 15924 script code
     */
    public static final int TRADITIONAL_HAN = 74;

    /**
     *
     */
    public static final int TULU_TIGALARI = 207;

    /**
     * Unified Canadian Aboriginal Symbols (alias)
     */
    public static final int UCAS = 40;

    /**
     * Ugaritic
     * Script in Unicode 4
     */
    public static final int UGARITIC = 53;

    /**
     * ISO 15924 script code
     */
    public static final int UNKNOWN = 103;

    /**
     * ISO 15924 script code
     */
    public static final int UNWRITTEN_LANGUAGES = 102;

    /**
     * ISO 15924 script code
     */
    public static final int VAI = 99;

    /**
     * ISO 15924 script code
     */
    public static final int VISIBLE_SPEECH = 100;

    /**
     *
     */
    public static final int VITHKUQI = 197;

    /**
     *
     */
    public static final int WANCHO = 188;

    /**
     * ISO 15924 script code
     */
    public static final int WARANG_CITI = 146;

    /**
     * ISO 15924 script code
     */
    public static final int WESTERN_SYRIAC = 96;

    /**
     * ISO 15924 script code
     */
    public static final int WOLEAI = 155;

    /**
     *
     */
    public static final int YEZIDI = 192;

    /**
     * Yi syllables
     */
    public static final int YI = 41;

    /**
     * ISO 15924 script code
     */
    public static final int ZANABAZAR_SQUARE = 177;

    private UScript() {
    }
}
