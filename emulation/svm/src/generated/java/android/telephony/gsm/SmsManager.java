package android.telephony.gsm;

/**
 * Manages SMS operations such as sending data, text, and pdu SMS messages.
 * Get this object by calling the static method SmsManager.getDefault().
 * @deprecated Replaced by android.telephony.SmsManager that supports both GSM and CDMA.
 */
@Deprecated
public final class SmsManager {
    /**
     * Generic failure cause
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int RESULT_ERROR_GENERIC_FAILURE = 1;

    /**
     * Failed because service is currently unavailable
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int RESULT_ERROR_NO_SERVICE = 4;

    /**
     * Failed because no pdu provided
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int RESULT_ERROR_NULL_PDU = 3;

    /**
     * Failed because radio was explicitly turned off
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int RESULT_ERROR_RADIO_OFF = 2;

    /**
     * Free space (TS 51.011 10.5.3).
     *   @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int STATUS_ON_SIM_FREE = 0;

    /**
     * Received and read (TS 51.011 10.5.3).
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int STATUS_ON_SIM_READ = 1;

    /**
     * Stored and sent (TS 51.011 10.5.3).
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int STATUS_ON_SIM_SENT = 5;

    /**
     * Received and unread (TS 51.011 10.5.3).
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int STATUS_ON_SIM_UNREAD = 3;

    /**
     * Stored and unsent (TS 51.011 10.5.3).
     *  @deprecated Use android.telephony.SmsManager.
     */
    @Deprecated
    public static final int STATUS_ON_SIM_UNSENT = 7;

    private SmsManager() {
    }
}
