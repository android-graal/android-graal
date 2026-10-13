package android.service.carrier;

/**
 * A service that receives calls from the system when new SMS and MMS are
 * sent or received.
 * <p>To extend this class, you must declare the service in your manifest file with
 * the {@link android.Manifest.permission#BIND_CARRIER_SERVICES} permission
 * and include an intent filter with the {@link #SERVICE_INTERFACE} action. For example:</p>
 * <pre>
 * &lt;service android:name=".MyMessagingService"
 *          android:label="&#64;string/service_name"
 *          android:permission="android.permission.BIND_CARRIER_SERVICES">
 *     &lt;intent-filter>
 *         &lt;action android:name="android.service.carrier.CarrierMessagingService" />
 *     &lt;/intent-filter>
 * &lt;/service></pre>
 */
public final class CarrierMessagingService {
    /**
     * MMS downloading failed due to an unspecified issue. Downloading will not be retried via the
     * carrier network.
     *
     * <p>Maps to SmsManager.MMR_ERROR_UNSPECIFIED.
     */
    public static final int DOWNLOAD_STATUS_ERROR = 2;

    /**
     * The carrier-dependent configuration values could not be loaded.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_CONFIGURATION_ERROR
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_CONFIGURATION_ERROR = 606;

    /**
     * Data is disabled for the MMS APN.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_DATA_DISABLED
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_DATA_DISABLED = 610;

    /**
     * An error occurred during the HTTP client setup.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_HTTP_FAILURE
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_HTTP_FAILURE = 603;

    /**
     * The subscription id for the download is inactive.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_INACTIVE_SUBSCRIPTION
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_INACTIVE_SUBSCRIPTION = 609;

    /**
     * ApnException occurred during MMS network setup.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_INVALID_APN
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_INVALID_APN = 601;

    /**
     * The subscription id for the download is invalid.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_INVALID_SUBSCRIPTION_ID
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_INVALID_SUBSCRIPTION_ID = 608;

    /**
     * An I/O error occurred reading the PDU.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_IO_ERROR
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_IO_ERROR = 604;

    /**
     * MMS is disabled by a carrier.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_MMS_DISABLED_BY_CARRIER
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_MMS_DISABLED_BY_CARRIER = 611;

    /**
     * There is neither Wi-Fi nor mobile data network.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_NO_DATA_NETWORK
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_NO_DATA_NETWORK = 607;

    /**
     * An error occurred while retrying downloading the MMS.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_RETRY
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_RETRY = 605;

    /**
     * An error occurred during the MMS connection setup.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_UNABLE_CONNECT_MMS
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_UNABLE_CONNECT_MMS = 602;

    /**
     * Unspecific MMS error occurred during download.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_UNSPECIFIED
     */
    public static final int DOWNLOAD_STATUS_MMS_ERROR_UNSPECIFIED = 600;

    /**
     * Successfully downloaded an MMS message.
     */
    public static final int DOWNLOAD_STATUS_OK = 0;

    /**
     * MMS downloading failed. We should retry via the carrier network.
     */
    public static final int DOWNLOAD_STATUS_RETRY_ON_CARRIER_NETWORK = 1;

    /**
     * The default bitmask value passed to the callback of {@link #onReceiveTextSms} with all
     * {@code RECEIVE_OPTIONS_x} flags cleared to indicate that the message should be kept and a
     * new message notification should be shown.
     *
     * @see #RECEIVE_OPTIONS_DROP
     * @see #RECEIVE_OPTIONS_SKIP_NOTIFY_WHEN_CREDENTIAL_PROTECTED_STORAGE_UNAVAILABLE
     */
    public static final int RECEIVE_OPTIONS_DEFAULT = 0;

    /**
     * Used to set the flag in the bitmask passed to the callback of {@link #onReceiveTextSms} to
     * indicate that the inbound SMS should be dropped.
     */
    public static final int RECEIVE_OPTIONS_DROP = 1;

    /**
     * Used to set the flag in the bitmask passed to the callback of {@link #onReceiveTextSms} to
     * indicate that a new message notification should not be shown to the user when the
     * credential-encrypted storage of the device is not available before the user unlocks the
     * phone. It is only applicable to devices that support file-based encryption.
     */
    public static final int RECEIVE_OPTIONS_SKIP_NOTIFY_WHEN_CREDENTIAL_PROTECTED_STORAGE_UNAVAILABLE = 2;

    /**
     * Flag to request SMS delivery status report.
     */
    public static final int SEND_FLAG_REQUEST_DELIVERY_STATUS = 1;

    /**
     * SMS/MMS sending failed due to an unspecified issue. Sending will not be retried via the
     * carrier network.
     *
     * <p>Maps to SmsManager.RESULT_RIL_GENERIC_FAILURE for SMS and SmsManager.MMS_ERROR_UNSPECIFIED
     * for MMS.
     */
    public static final int SEND_STATUS_ERROR = 2;

    /**
     * The carrier-dependent configuration values could not be loaded.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_CONFIGURATION_ERROR
     */
    public static final int SEND_STATUS_MMS_ERROR_CONFIGURATION_ERROR = 406;

    /**
     * Data is disabled for the MMS APN.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_DATA_DISABLED
     */
    public static final int SEND_STATUS_MMS_ERROR_DATA_DISABLED = 410;

    /**
     * An error occurred during the HTTP client setup.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_HTTP_FAILURE
     */
    public static final int SEND_STATUS_MMS_ERROR_HTTP_FAILURE = 403;

    /**
     * The subscription id for the send is inactive.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_INACTIVE_SUBSCRIPTION
     */
    public static final int SEND_STATUS_MMS_ERROR_INACTIVE_SUBSCRIPTION = 409;

    /**
     * ApnException occurred during MMS network setup.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_INVALID_APN
     */
    public static final int SEND_STATUS_MMS_ERROR_INVALID_APN = 401;

    /**
     * The subscription id for the send is invalid.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_INVALID_SUBSCRIPTION_ID
     */
    public static final int SEND_STATUS_MMS_ERROR_INVALID_SUBSCRIPTION_ID = 408;

    /**
     * An I/O error occurred reading the PDU.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_IO_ERROR
     */
    public static final int SEND_STATUS_MMS_ERROR_IO_ERROR = 404;

    /**
     * MMS is disabled by a carrier.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_MMS_DISABLED_BY_CARRIER
     */
    public static final int SEND_STATUS_MMS_ERROR_MMS_DISABLED_BY_CARRIER = 411;

    /**
     * There is neither Wi-Fi nor mobile data network.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_NO_DATA_NETWORK
     */
    public static final int SEND_STATUS_MMS_ERROR_NO_DATA_NETWORK = 407;

    /**
     * An error occurred while retrying sending the MMS.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_RETRY
     */
    public static final int SEND_STATUS_MMS_ERROR_RETRY = 405;

    /**
     * An error occurred during the MMS connection setup.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_UNABLE_CONNECT_MMS
     */
    public static final int SEND_STATUS_MMS_ERROR_UNABLE_CONNECT_MMS = 402;

    /**
     * Unspecific MMS error occurred during send.
     *
     * @see android.telephony.SmsManager.MMS_ERROR_UNSPECIFIED
     */
    public static final int SEND_STATUS_MMS_ERROR_UNSPECIFIED = 400;

    /**
     * Indicates that an SMS or MMS message was successfully sent.
     */
    public static final int SEND_STATUS_OK = 0;

    /**
     * Failed because the operation was cancelled.
     *
     * @see android.telephony.SmsManager.RESULT_CANCELLED
     */
    public static final int SEND_STATUS_RESULT_CANCELLED = 215;

    /**
     * Failed because of an encoding error.
     *
     * @see android.telephony.SmsManager.RESULT_ENCODING_ERROR
     */
    public static final int SEND_STATUS_RESULT_ENCODING_ERROR = 212;

    /**
     * Failed because FDN is enabled.
     *
     * @see android.telephony.SmsManager.RESULT_ERROR_FDN_CHECK_FAILURE
     */
    public static final int SEND_STATUS_RESULT_ERROR_FDN_CHECK_FAILURE = 204;

    /**
     * Generic failure cause.
     *
     * @see android.telephony.SmsManager.RESULT_ERROR_GENERIC_FAILURE
     */
    public static final int SEND_STATUS_RESULT_ERROR_GENERIC_FAILURE = 200;

    /**
     * Failed because we reached the sending queue limit.
     *
     * @see android.telephony.SmsManager.RESULT_ERROR_LIMIT_EXCEEDED
     */
    public static final int SEND_STATUS_RESULT_ERROR_LIMIT_EXCEEDED = 203;

    /**
     * Failed because service is currently unavailable.
     *
     * @see android.telephony.SmsManager.RESULT_ERROR_NO_SERVICE
     */
    public static final int SEND_STATUS_RESULT_ERROR_NO_SERVICE = 202;

    /**
     * Failed because no pdu provided.
     *
     * @see android.telephony.SmsManager.RESULT_ERROR_NULL_PDU
     */
    public static final int SEND_STATUS_RESULT_ERROR_NULL_PDU = 201;

    /**
     * Failed because the user has denied this app ever send premium short codes.
     *
     * @see android.telephony.SmsManager.RESULT_ERROR_SHORT_CODE_NEVER_ALLOWED
     */
    public static final int SEND_STATUS_RESULT_ERROR_SHORT_CODE_NEVER_ALLOWED = 206;

    /**
     * Failed because user denied the sending of this short code.
     *
     * @see android.telephony.SmsManager.RESULT_ERROR_SHORT_CODE_NOT_ALLOWED
     */
    public static final int SEND_STATUS_RESULT_ERROR_SHORT_CODE_NOT_ALLOWED = 205;

    /**
     * Failed because of invalid arguments.
     *
     * @see android.telephony.SmsManager.RESULT_INVALID_ARGUMENTS
     */
    public static final int SEND_STATUS_RESULT_INVALID_ARGUMENTS = 208;

    /**
     * Failed because of an invalid smsc address
     *
     * @see android.telephony.SmsManager.RESULT_INVALID_SMSC_ADDRESS
     */
    public static final int SEND_STATUS_RESULT_INVALID_SMSC_ADDRESS = 213;

    /**
     * Failed because the sms format is not valid.
     *
     * @see android.telephony.SmsManager.RESULT_INVALID_SMS_FORMAT
     */
    public static final int SEND_STATUS_RESULT_INVALID_SMS_FORMAT = 210;

    /**
     * Failed because of an invalid state.
     *
     * @see android.telephony.SmsManager.RESULT_INVALID_STATE
     */
    public static final int SEND_STATUS_RESULT_INVALID_STATE = 209;

    /**
     * Failed because of a network error.
     *
     * @see android.telephony.SmsManager.RESULT_NETWORK_ERROR
     */
    public static final int SEND_STATUS_RESULT_NETWORK_ERROR = 211;

    /**
     * Failed because of network rejection.
     *
     * @see android.telephony.SmsManager.RESULT_NETWORK_REJECT
     */
    public static final int SEND_STATUS_RESULT_NETWORK_REJECT = 207;

    /**
     * Failed because the operation is not allowed.
     *
     * @see android.telephony.SmsManager.RESULT_OPERATION_NOT_ALLOWED
     */
    public static final int SEND_STATUS_RESULT_OPERATION_NOT_ALLOWED = 214;

    /**
     * Failed because the request is not supported.
     *
     * @see android.telephony.SmsManager.RESULT_REQUEST_NOT_SUPPORTED
     */
    public static final int SEND_STATUS_RESULT_REQUEST_NOT_SUPPORTED = 216;

    /**
     * Failed sending during an emergency call.
     *
     * @see android.telephony.SmsManager.RESULT_SMS_BLOCKED_DURING_EMERGENCY
     */
    public static final int SEND_STATUS_RESULT_SMS_BLOCKED_DURING_EMERGENCY = 217;

    /**
     * Failed to send an sms retry.
     *
     * @see android.telephony.SmsManager.RESULT_SMS_SEND_RETRY_FAILED
     */
    public static final int SEND_STATUS_RESULT_SMS_SEND_RETRY_FAILED = 218;

    /**
     * SMS/MMS sending failed. We should retry via the carrier network.
     */
    public static final int SEND_STATUS_RETRY_ON_CARRIER_NETWORK = 1;

    /**
     * The {@link android.content.Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.service.carrier.CarrierMessagingService";

    private CarrierMessagingService() {
    }
}
