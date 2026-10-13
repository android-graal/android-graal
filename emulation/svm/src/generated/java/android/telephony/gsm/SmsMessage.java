package android.telephony.gsm;

/**
 * A Short Message Service message.
 * @deprecated Replaced by android.telephony.SmsMessage that supports both GSM and CDMA.
 */
@Deprecated
public final class SmsMessage {
    /**
     * 16-bit encoding scheme (see TS 23.038)
     *  @deprecated Use android.telephony.SmsMessage.
     */
    @Deprecated
    public static final int ENCODING_16BIT = 3;

    /**
     * 7-bit encoding scheme (see TS 23.038)
     *  @deprecated Use android.telephony.SmsMessage.
     */
    @Deprecated
    public static final int ENCODING_7BIT = 1;

    /**
     * 8-bit encoding scheme (see TS 23.038)
     *  @deprecated Use android.telephony.SmsMessage.
     */
    @Deprecated
    public static final int ENCODING_8BIT = 2;

    /**
     * Unknown encoding scheme (see TS 23.038)
     *  @deprecated Use android.telephony.SmsMessage.
     */
    @Deprecated
    public static final int ENCODING_UNKNOWN = 0;

    /**
     * The maximum number of payload bytes per message
     *  @deprecated Use android.telephony.SmsMessage.
     */
    @Deprecated
    public static final int MAX_USER_DATA_BYTES = 140;

    /**
     * The maximum number of payload septets per message
     *  @deprecated Use android.telephony.SmsMessage.
     */
    @Deprecated
    public static final int MAX_USER_DATA_SEPTETS = 160;

    /**
     * The maximum number of payload septets per message if a user data header
     * is present.  This assumes the header only contains the
     * CONCATENATED_8_BIT_REFERENCE element.
     * @deprecated Use android.telephony.SmsMessage.
     */
    @Deprecated
    public static final int MAX_USER_DATA_SEPTETS_WITH_HEADER = 153;

    private SmsMessage() {
    }
}
