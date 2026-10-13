package android.telephony;

/**
 * Various utilities for dealing with phone number strings.
 */
public final class PhoneNumberUtils {
    public static final int BCD_EXTENDED_TYPE_CALLED_PARTY = 2;

    public static final int BCD_EXTENDED_TYPE_EF_ADN = 1;

    /**
     * Japanese formatting
     */
    public static final int FORMAT_JAPAN = 2;

    /**
     * NANP formatting
     */
    public static final int FORMAT_NANP = 1;

    /**
     * The current locale is unknown, look for a country code or don't format
     */
    public static final int FORMAT_UNKNOWN = 0;

    public static final char PAUSE = ',';

    public static final int TOA_International = 145;

    public static final int TOA_Unknown = 129;

    public static final char WAIT = ';';

    public static final char WILD = 'N';

    private PhoneNumberUtils() {
    }
}
