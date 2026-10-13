package android.text.style;

/**
 * A span that supplies additional meta-data for the associated text intended
 * for text-to-speech engines. If the text is being processed by a
 * text-to-speech engine, the engine may use the data in this span in addition
 * to or instead of its associated text.
 *
 * Each instance of a TtsSpan has a type, for example {@link #TYPE_DATE}
 * or {@link #TYPE_MEASURE}. And a list of arguments, provided as
 * key-value pairs in a bundle.
 *
 * The inner classes are there for convenience and provide builders for each
 * TtsSpan type.
 */
public final class TtsSpan {
    public static final String ANIMACY_ANIMATE = "android.animate";

    public static final String ANIMACY_INANIMATE = "android.inanimate";

    /**
     * String argument supplying animacy information. Can be
     * {@link #ANIMACY_ANIMATE} or
     * {@link #ANIMACY_INANIMATE}
     */
    public static final String ARG_ANIMACY = "android.arg.animacy";

    /**
     * String argument supplying case information. Can be any of
     * {@link #CASE_NOMINATIVE}, {@link #CASE_ACCUSATIVE}, {@link #CASE_DATIVE},
     * {@link #CASE_ABLATIVE}, {@link #CASE_GENITIVE}, {@link #CASE_VOCATIVE},
     * {@link #CASE_LOCATIVE} and {@link #CASE_INSTRUMENTAL}
     */
    public static final String ARG_CASE = "android.arg.case";

    /**
     * Argument used to specify the country code of a telephone number. Can be
     * a string of digits optionally prefixed with a "+".
     * Can be used with {@link #TYPE_TELEPHONE}.
     */
    public static final String ARG_COUNTRY_CODE = "android.arg.country_code";

    /**
     * Argument used to specify the currency. Should be a ISO4217 currency code,
     * e.g. "USD".
     * Can be used with {@link #TYPE_MONEY}.
     */
    public static final String ARG_CURRENCY = "android.arg.money";

    /**
     * Argument used to specify the day of the month of a date. The value should
     * be provided as an integer in the range from 1 up to and including 31.
     * Can be used with {@link #TYPE_DATE}.
     */
    public static final String ARG_DAY = "android.arg.day";

    /**
     * Argument used to specify the denominator of a fraction. The value can be
     * a string of digits of any size optionally prefixed with a + or -.
     * Can be used with {@link #TYPE_FRACTION}.
     */
    public static final String ARG_DENOMINATOR = "android.arg.denominator";

    /**
     * Argument used to specify a string of digits.
     * Can be used with {@link #TYPE_DIGITS}.
     */
    public static final String ARG_DIGITS = "android.arg.digits";

    /**
     * Argument used to specify the domain part of a URI. For example
     * "source.android.com".
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_DOMAIN = "android.arg.domain";

    /**
     * Argument used to specify the extension part of a telephone number. Can be
     * a string of digits.
     * Can be used with {@link #TYPE_TELEPHONE}.
     */
    public static final String ARG_EXTENSION = "android.arg.extension";

    /**
     * Argument used to specify the fractional part of a decimal. The value can
     * be a string of digits of any size.
     * Can be used with {@link #TYPE_DECIMAL}.
     */
    public static final String ARG_FRACTIONAL_PART = "android.arg.fractional_part";

    /**
     * Argument used to specify the fragment id of a URI. Should be specified as
     * a string.
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_FRAGMENT_ID = "android.arg.fragment_id";

    /**
     * String argument supplying gender information. Can be any of
     * {@link #GENDER_NEUTRAL}, {@link #GENDER_MALE} and
     * {@link #GENDER_FEMALE}.
     */
    public static final String ARG_GENDER = "android.arg.gender";

    /**
     * Argument used to specify the hours of a time or duration. The hours should be
     * provided as an integer in the range from 0 up to and including 24 for
     * {@link #TYPE_TIME}.
     * Can be used with {@link #TYPE_TIME} or {@link #TYPE_DURATION}.
     */
    public static final String ARG_HOURS = "android.arg.hours";

    /**
     * Argument used to specify the integer part of a decimal or fraction. The
     * value can be a string of digits of any size optionally prefixed with
     * a - or +.
     * Can be used with {@link #TYPE_DECIMAL} and {@link #TYPE_FRACTION}.
     */
    public static final String ARG_INTEGER_PART = "android.arg.integer_part";

    /**
     * Argument used to specify the minutes of a time or duration. The minutes should be
     * provided as an integer in the range from 0 up to and including 59 for
     * {@link #TYPE_TIME}.
     * Can be used with {@link #TYPE_TIME} or {@link #TYPE_DURATION}.
     */
    public static final String ARG_MINUTES = "android.arg.minutes";

    /**
     * Argument used to specify the month of a date. The value should be
     * provided as an integer and can be any of {@link #MONTH_JANUARY},
     * {@link #MONTH_FEBRUARY},  {@link #MONTH_MARCH}, {@link #MONTH_APRIL},
     * {@link #MONTH_MAY}, {@link #MONTH_JUNE}, {@link #MONTH_JULY},
     * {@link #MONTH_AUGUST}, {@link #MONTH_SEPTEMBER}, {@link #MONTH_OCTOBER},
     * {@link #MONTH_NOVEMBER} and {@link #MONTH_DECEMBER}.
     * Can be used with {@link #TYPE_DATE}.
     */
    public static final String ARG_MONTH = "android.arg.month";

    /**
     * String argument supplying multiplicity information. Can be any of
     * {@link #MULTIPLICITY_SINGLE}, {@link #MULTIPLICITY_DUAL} and
     * {@link #MULTIPLICITY_PLURAL}
     */
    public static final String ARG_MULTIPLICITY = "android.arg.multiplicity";

    /**
     * Argument used to specify a whole number. The value can be a string of
     * digits of any size optionally prefixed with a - or +.
     * Can be used with {@link #TYPE_CARDINAL} and {@link #TYPE_ORDINAL}.
     */
    public static final String ARG_NUMBER = "android.arg.number";

    /**
     * Argument used to specify the main number part of a telephone number. Can
     * be a string of digits where the different parts of the telephone number
     * can be separated with a space, '-', '/' or '.'.
     * Can be used with {@link #TYPE_TELEPHONE}.
     */
    public static final String ARG_NUMBER_PARTS = "android.arg.number_parts";

    /**
     * Argument used to specify the numerator of a fraction. The value can be a
     * string of digits of any size optionally prefixed with a - or +.
     * Can be used with {@link #TYPE_FRACTION}.
     */
    public static final String ARG_NUMERATOR = "android.arg.numerator";

    /**
     * Argument used to specify the password part of a URI. Should be set as a
     * string.
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_PASSWORD = "android.arg.password";

    /**
     * Argument used to specify the path part of a URI. For example
     * "source/index.html".
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_PATH = "android.arg.path";

    /**
     * Argument used to specify the port number of a URI. Should be specified as
     * an integer.
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_PORT = "android.arg.port";

    /**
     * Argument used to specify the protocol of a URI. Examples are "http" and
     * "ftp".
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_PROTOCOL = "android.arg.protocol";

    /**
     * Argument used to choose the suffix (thousand, million, etc) that is used
     * to pronounce large amounts of money. For example it can be used to
     * disambiguate between "two thousand five hundred dollars" and
     * "two point five thousand dollars".
     * If implemented, engines should support at least "1000", "1000000",
     * "1000000000" and "1000000000000".
     * Example: if the {@link #ARG_INTEGER_PART} argument is "10", the
     * {@link #ARG_FRACTIONAL_PART} argument is "4", the {@link #ARG_QUANTITY}
     * argument is "1000" and the {@link #ARG_CURRENCY} argument is "usd", the
     * TTS engine may pronounce the span as "ten point four thousand dollars".
     * With the same example but with the quantity set as "1000000" the TTS
     * engine may pronounce the span as "ten point four million dollars".
     * Can be used with {@link #TYPE_MONEY}.
     */
    public static final String ARG_QUANTITY = "android.arg.quantity";

    /**
     * Argument used to specify the query string of a URI. For example
     * "arg=value&argtwo=value".
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_QUERY_STRING = "android.arg.query_string";

    /**
     * Argument used to specify the seconds of a time or duration. The seconds should be
     * provided as an integer in the range from 0 up to and including 59 for
     * {@link #TYPE_TIME}.
     * Can be used with {@link #TYPE_TIME} or {@link #TYPE_DURATION}.
     */
    public static final String ARG_SECONDS = "android.arg.seconds";

    /**
     * String supplying the text to be synthesized. The synthesizer is free
     * to decide how to interpret the text.
     * Can be used with {@link #TYPE_TEXT}.
     */
    public static final String ARG_TEXT = "android.arg.text";

    /**
     * Argument used to specify the unit of a measure. The unit should always be
     * specified in English singular form. Prefixes may be used. Engines will do
     * their best to pronounce them correctly in the language used. Engines are
     * expected to at least support the most common ones like "meter", "second",
     * "degree celsius" and "degree fahrenheit" with some common prefixes like
     * "milli" and "kilo".
     * Can be used with {@link #TYPE_MEASURE}.
     */
    public static final String ARG_UNIT = "android.arg.unit";

    /**
     * Argument used to specify the username part of a URI. Should be set as a
     * string.
     * Can be used with {@link #TYPE_ELECTRONIC}.
     */
    public static final String ARG_USERNAME = "android.arg.username";

    /**
     * Argument used to specify a string where the characters are read verbatim,
     * except whitespace.
     * Can be used with {@link #TYPE_VERBATIM}.
     */
    public static final String ARG_VERBATIM = "android.arg.verbatim";

    /**
     * Argument used to specify the weekday of a date. The value should be
     * provided as an integer and can be any of {@link #WEEKDAY_SUNDAY},
     * {@link #WEEKDAY_MONDAY}, {@link #WEEKDAY_TUESDAY},
     * {@link #WEEKDAY_WEDNESDAY}, {@link #WEEKDAY_THURSDAY},
     * {@link #WEEKDAY_FRIDAY} and {@link #WEEKDAY_SATURDAY}.
     * Can be used with {@link #TYPE_DATE}.
     */
    public static final String ARG_WEEKDAY = "android.arg.weekday";

    /**
     * Argument used to specify the year of a date. The value should be provided
     * as a positive integer.
     * Can be used with {@link #TYPE_DATE}.
     */
    public static final String ARG_YEAR = "android.arg.year";

    public static final String CASE_ABLATIVE = "android.ablative";

    public static final String CASE_ACCUSATIVE = "android.accusative";

    public static final String CASE_DATIVE = "android.dative";

    public static final String CASE_GENITIVE = "android.genitive";

    public static final String CASE_INSTRUMENTAL = "android.instrumental";

    public static final String CASE_LOCATIVE = "android.locative";

    public static final String CASE_NOMINATIVE = "android.nominative";

    public static final String CASE_VOCATIVE = "android.vocative";

    public static final String GENDER_FEMALE = "android.female";

    public static final String GENDER_MALE = "android.male";

    public static final String GENDER_NEUTRAL = "android.neutral";

    public static final int MONTH_APRIL = 3;

    public static final int MONTH_AUGUST = 7;

    public static final int MONTH_DECEMBER = 11;

    public static final int MONTH_FEBRUARY = 1;

    public static final int MONTH_JANUARY = 0;

    public static final int MONTH_JULY = 6;

    public static final int MONTH_JUNE = 5;

    public static final int MONTH_MARCH = 2;

    public static final int MONTH_MAY = 4;

    public static final int MONTH_NOVEMBER = 10;

    public static final int MONTH_OCTOBER = 9;

    public static final int MONTH_SEPTEMBER = 8;

    public static final String MULTIPLICITY_DUAL = "android.dual";

    public static final String MULTIPLICITY_PLURAL = "android.plural";

    public static final String MULTIPLICITY_SINGLE = "android.single";

    /**
     * The text associated with this span is a cardinal. Must include the
     * number to be synthesized with {@link #ARG_NUMBER}.
     * Also accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and
     * {@link #ARG_CASE}.
     */
    public static final String TYPE_CARDINAL = "android.type.cardinal";

    /**
     * The text associated with this span is a date. At least one of the
     * arguments {@link #ARG_MONTH} and {@link #ARG_YEAR} has to be provided.
     * The argument {@link #ARG_DAY} is optional if {@link #ARG_MONTH} is set.
     * The argument {@link #ARG_WEEKDAY} is optional if {@link #ARG_DAY} is set.
     * Also accepts the arguments {@link #ARG_GENDER}, {@link #ARG_ANIMACY},
     * {@link #ARG_MULTIPLICITY} and {@link #ARG_CASE}.
     */
    public static final String TYPE_DATE = "android.type.date";

    /**
     * The text associated with this span is a decimal number. Must include the
     * number to be synthesized with {@link #ARG_INTEGER_PART} and
     * {@link #ARG_FRACTIONAL_PART}.
     * Also accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and
     * {@link #ARG_CASE}.
     */
    public static final String TYPE_DECIMAL = "android.type.decimal";

    /**
     * The text associated with this span is a series of digits that have to be
     * read sequentially. The digits can be set with {@link #ARG_DIGITS}.
     * Also accepts the arguments {@link #ARG_GENDER}, {@link #ARG_ANIMACY},
     * {@link #ARG_MULTIPLICITY} and {@link #ARG_CASE}.
     */
    public static final String TYPE_DIGITS = "android.type.digits";

    /**
     * The text associated with this span is a duration, consisting of a number of
     * hours, minutes, and seconds specified with {@link #ARG_HOURS},
     * {@link #ARG_MINUTES}, and {@link #ARG_SECONDS}. This is different from {@link #TYPE_TIME}.
     * This should be used to convey an interval of time, while {@link #TYPE_TIME} should be used to
     * convey a particular moment in time, such as a clock time.
     */
    public static final String TYPE_DURATION = "android.type.duration";

    /**
     * The text associated with this span is a URI (can be used for URLs and
     * email addresses). The full schema for URLs, which email addresses can
     * effectively be seen as a subset of, is:
     * protocol://username:password@domain:port/path?query_string#fragment_id
     * Hence populating just username and domain will read as an email address.
     * All arguments are optional, but at least one has to be provided:
     * {@link #ARG_PROTOCOL}, {@link #ARG_USERNAME}, {@link #ARG_PASSWORD},
     * {@link #ARG_DOMAIN}, {@link #ARG_PORT}, {@link #ARG_PATH},
     * {@link #ARG_QUERY_STRING} and {@link #ARG_FRAGMENT_ID}.
     * Also accepts the arguments {@link #ARG_GENDER}, {@link #ARG_ANIMACY},
     * {@link #ARG_MULTIPLICITY} and {@link #ARG_CASE}.
     */
    public static final String TYPE_ELECTRONIC = "android.type.electronic";

    /**
     * The text associated with this span is a fractional number. Must include
     * the number to be synthesized with {@link #ARG_NUMERATOR} and
     * {@link #ARG_DENOMINATOR}. {@link #ARG_INTEGER_PART} is optional
     * Also accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and
     * {@link #ARG_CASE}.
     */
    public static final String TYPE_FRACTION = "android.type.fraction";

    /**
     * The text associated with this span is a measure, consisting of a number
     * and a unit. The number can be a cardinal, decimal or a fraction. Set the
     * number with the same arguments as {@link #TYPE_CARDINAL},
     * {@link #TYPE_DECIMAL} or {@link #TYPE_FRACTION}. The unit can be
     * specified with {@link #ARG_UNIT}.
     * Also accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and
     * {@link #ARG_CASE}.
     */
    public static final String TYPE_MEASURE = "android.type.measure";

    /**
     * The text associated with this span is an amount of money. Set the amount
     * with the same arguments as {@link #TYPE_DECIMAL}.
     * {@link #ARG_CURRENCY} is used to set the currency. {@link #ARG_QUANTITY}
     * is optional.
     * Also accepts the arguments {@link #ARG_GENDER}, {@link #ARG_ANIMACY},
     * {@link #ARG_MULTIPLICITY} and {@link #ARG_CASE}.
     */
    public static final String TYPE_MONEY = "android.type.money";

    /**
     * The text associated with this span is an ordinal. Must include the
     * number to be synthesized with {@link #ARG_NUMBER}.
     * Also accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and
     * {@link #ARG_CASE}.
     */
    public static final String TYPE_ORDINAL = "android.type.ordinal";

    /**
     * The text associated with this span is a telephone number. The argument
     * {@link #ARG_NUMBER_PARTS} is required. {@link #ARG_COUNTRY_CODE} and
     * {@link #ARG_EXTENSION} are optional.
     * Also accepts the arguments {@link #ARG_GENDER}, {@link #ARG_ANIMACY},
     * {@link #ARG_MULTIPLICITY} and {@link #ARG_CASE}.
     */
    public static final String TYPE_TELEPHONE = "android.type.telephone";

    /**
     * This span type can be used to add morphosyntactic features to the text it
     * spans over, or synthesize a something else than the spanned text. Use
     * the argument {@link #ARG_TEXT} to set a different text.
     * Accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and
     * {@link #ARG_CASE}.
     */
    public static final String TYPE_TEXT = "android.type.text";

    /**
     * The text associated with this span is a time, consisting of a number of
     * hours, minutes, and seconds specified with {@link #ARG_HOURS}, {@link #ARG_MINUTES}, and
     * {@link #ARG_SECONDS}.
     * Also accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and
     * {@link #ARG_CASE}. This is different from {@link #TYPE_DURATION}. This should be used to
     * convey a particular moment in time, such as a clock time, while {@link #TYPE_DURATION} should
     * be used to convey an interval of time.
     */
    public static final String TYPE_TIME = "android.type.time";

    /**
     * The text associated with this span is a series of characters that have to
     * be read verbatim. The engine will attempt to read out any character like
     * punctuation but excluding whitespace. {@link #ARG_VERBATIM} is required.
     * Also accepts the arguments {@link #ARG_GENDER},
     * {@link #ARG_ANIMACY}, {@link #ARG_MULTIPLICITY} and {@link #ARG_CASE}.
     */
    public static final String TYPE_VERBATIM = "android.type.verbatim";

    public static final int WEEKDAY_FRIDAY = 6;

    public static final int WEEKDAY_MONDAY = 2;

    public static final int WEEKDAY_SATURDAY = 7;

    public static final int WEEKDAY_SUNDAY = 1;

    public static final int WEEKDAY_THURSDAY = 5;

    public static final int WEEKDAY_TUESDAY = 3;

    public static final int WEEKDAY_WEDNESDAY = 4;

    private TtsSpan() {
    }
}
