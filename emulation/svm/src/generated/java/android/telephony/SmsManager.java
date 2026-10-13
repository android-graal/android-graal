package android.telephony;

/**
 * Manages SMS operations such as sending data, text, and pdu SMS messages.
 * Get this object by calling the static method {@link #getDefault()}. To create an instance of
 * {@link SmsManager} associated with a specific subscription ID, call
 * {@link #getSmsManagerForSubscriptionId(int)}. This is typically used for devices that support
 * multiple active subscriptions at once.
 *
 * <p>For information about how to behave as the default SMS app on Android 4.4 (API level 19)
 * and higher, see {@link android.provider.Telephony}.
 *
 * @see SubscriptionManager#getActiveSubscriptionInfoList()
 */
public final class SmsManager {
    /**
     * Intent extra name for MMS sending result data in byte array type
     */
    public static final String EXTRA_MMS_DATA = "android.telephony.extra.MMS_DATA";

    /**
     * Intent extra name for HTTP status code for MMS HTTP failure in integer type
     */
    public static final String EXTRA_MMS_HTTP_STATUS = "android.telephony.extra.MMS_HTTP_STATUS";

    /**
     * Whether alias is enabled (boolean type)
     */
    public static final String MMS_CONFIG_ALIAS_ENABLED = "aliasEnabled";

    /**
     * Max alias character count (int type)
     */
    public static final String MMS_CONFIG_ALIAS_MAX_CHARS = "aliasMaxChars";

    /**
     * Min alias character count (int type)
     */
    public static final String MMS_CONFIG_ALIAS_MIN_CHARS = "aliasMinChars";

    /**
     * Whether audio is allowed to be attached for MMS messages (boolean type)
     */
    public static final String MMS_CONFIG_ALLOW_ATTACH_AUDIO = "allowAttachAudio";

    /**
     * Whether to append transaction id to MMS WAP Push M-Notification.ind's content location URI
     * when constructing the download URL of a new MMS (boolean type)
     */
    public static final String MMS_CONFIG_APPEND_TRANSACTION_ID = "enabledTransID";

    /**
     * Email gateway number (String type)
     */
    public static final String MMS_CONFIG_EMAIL_GATEWAY_NUMBER = "emailGatewayNumber";

    /**
     * Whether group MMS is enabled for the current carrier (boolean type)
     */
    public static final String MMS_CONFIG_GROUP_MMS_ENABLED = "enableGroupMms";

    /**
     * A list of HTTP headers to add to MMS HTTP request, separated by "|" (String type)
     */
    public static final String MMS_CONFIG_HTTP_PARAMS = "httpParams";

    /**
     * MMS HTTP socket timeout in milliseconds (int type)
     */
    public static final String MMS_CONFIG_HTTP_SOCKET_TIMEOUT = "httpSocketTimeout";

    /**
     * Max MMS image height (int type)
     */
    public static final String MMS_CONFIG_MAX_IMAGE_HEIGHT = "maxImageHeight";

    /**
     * Max MMS image width (int type)
     */
    public static final String MMS_CONFIG_MAX_IMAGE_WIDTH = "maxImageWidth";

    /**
     * Max MMS message size in bytes (int type)
     */
    public static final String MMS_CONFIG_MAX_MESSAGE_SIZE = "maxMessageSize";

    /**
     * Max message text size (int type)
     */
    public static final String MMS_CONFIG_MESSAGE_TEXT_MAX_SIZE = "maxMessageTextSize";

    /**
     * Whether MMS delivery report is enabled (boolean type)
     */
    public static final String MMS_CONFIG_MMS_DELIVERY_REPORT_ENABLED = "enableMMSDeliveryReports";

    /**
     * Whether MMS is enabled for the current carrier (boolean type)
     */
    public static final String MMS_CONFIG_MMS_ENABLED = "enabledMMS";

    /**
     * Whether MMS read report is enabled (boolean type)
     */
    public static final String MMS_CONFIG_MMS_READ_REPORT_ENABLED = "enableMMSReadReports";

    /**
     * Whether multipart SMS is enabled (boolean type)
     */
    public static final String MMS_CONFIG_MULTIPART_SMS_ENABLED = "enableMultipartSMS";

    /**
     * The suffix to append to the NAI header value for MMS HTTP request (String type)
     */
    public static final String MMS_CONFIG_NAI_SUFFIX = "naiSuffix";

    /**
     * If this is enabled, M-NotifyResp.ind should be sent to the WAP Push content location instead
     * of the default MMSC (boolean type)
     */
    public static final String MMS_CONFIG_NOTIFY_WAP_MMSC_ENABLED = "enabledNotifyWapMMSC";

    /**
     * Limit of recipients of MMS messages (int type)
     */
    public static final String MMS_CONFIG_RECIPIENT_LIMIT = "recipientLimit";

    /**
     * Whether multipart SMS should be sent as separate messages
     */
    public static final String MMS_CONFIG_SEND_MULTIPART_SMS_AS_SEPARATE_MESSAGES = "sendMultipartSmsAsSeparateMessages";

    /**
     * If true, show the cell broadcast (amber alert) in the SMS settings. Some carriers don't want
     * this shown. (Boolean type)
     */
    public static final String MMS_CONFIG_SHOW_CELL_BROADCAST_APP_LINKS = "config_cellBroadcastAppLinks";

    /**
     * Whether SMS delivery report is enabled (boolean type)
     */
    public static final String MMS_CONFIG_SMS_DELIVERY_REPORT_ENABLED = "enableSMSDeliveryReports";

    /**
     * Some carriers require SMS to be converted into MMS when text length reaches this threshold
     * (int type)
     */
    public static final String MMS_CONFIG_SMS_TO_MMS_TEXT_LENGTH_THRESHOLD = "smsToMmsTextLengthThreshold";

    /**
     * When the number of parts of a multipart SMS reaches this threshold, it should be converted
     * into an MMS (int type)
     */
    public static final String MMS_CONFIG_SMS_TO_MMS_TEXT_THRESHOLD = "smsToMmsTextThreshold";

    /**
     * Max message subject length (int type)
     */
    public static final String MMS_CONFIG_SUBJECT_MAX_LENGTH = "maxSubjectLength";

    /**
     * Whether the carrier MMSC supports charset field in Content-Type header. If this is false,
     * then we don't add "charset" to "Content-Type"
     */
    public static final String MMS_CONFIG_SUPPORT_HTTP_CHARSET_HEADER = "supportHttpCharsetHeader";

    /**
     * Whether content-disposition field should be expected in an MMS PDU (boolean type)
     */
    public static final String MMS_CONFIG_SUPPORT_MMS_CONTENT_DISPOSITION = "supportMmsContentDisposition";

    /**
     * The name of the UA Prof URL HTTP header for MMS HTTP request (String type)
     */
    public static final String MMS_CONFIG_UA_PROF_TAG_NAME = "uaProfTagName";

    /**
     * The UA Profile URL header value for MMS HTTP request (String type)
     */
    public static final String MMS_CONFIG_UA_PROF_URL = "uaProfUrl";

    /**
     * The User-Agent header value for MMS HTTP request (String type)
     */
    public static final String MMS_CONFIG_USER_AGENT = "userAgent";

    /**
     * The carrier-dependent configuration values could not be loaded.
     */
    public static final int MMS_ERROR_CONFIGURATION_ERROR = 7;

    /**
     * Data is disabled for the MMS APN.
     */
    public static final int MMS_ERROR_DATA_DISABLED = 11;

    /**
     * An error occurred during the HTTP client setup.
     */
    public static final int MMS_ERROR_HTTP_FAILURE = 4;

    /**
     * The subscription id for the send/download is inactive.
     */
    public static final int MMS_ERROR_INACTIVE_SUBSCRIPTION = 10;

    /**
     * ApnException occurred during MMS network setup.
     */
    public static final int MMS_ERROR_INVALID_APN = 2;

    /**
     * The subscription id for the send/download is invalid.
     */
    public static final int MMS_ERROR_INVALID_SUBSCRIPTION_ID = 9;

    /**
     * An I/O error occurred reading the PDU.
     */
    public static final int MMS_ERROR_IO_ERROR = 5;

    /**
     * MMS is disabled by a carrier.
     */
    public static final int MMS_ERROR_MMS_DISABLED_BY_CARRIER = 12;

    /**
     * There is neither Wi-Fi nor mobile data network.
     */
    public static final int MMS_ERROR_NO_DATA_NETWORK = 8;

    /**
     * An error occurred while retrying sending/downloading the MMS.
     */
    public static final int MMS_ERROR_RETRY = 6;

    /**
     * An error occurred during the MMS connection setup.
     */
    public static final int MMS_ERROR_UNABLE_CONNECT_MMS = 3;

    /**
     * Unspecific MMS error occurred during send/download.
     */
    public static final int MMS_ERROR_UNSPECIFIED = 1;

    /**
     * Failed sending via bluetooth because bluetooth disconnected
     */
    public static final int RESULT_BLUETOOTH_DISCONNECTED = 27;

    /**
     * Failed because the operation was cancelled
     */
    public static final int RESULT_CANCELLED = 23;

    /**
     * Failed because of an encoding error
     */
    public static final int RESULT_ENCODING_ERROR = 18;

    /**
     * Failed because FDN is enabled.
     */
    public static final int RESULT_ERROR_FDN_CHECK_FAILURE = 6;

    /**
     * Generic failure cause
     */
    public static final int RESULT_ERROR_GENERIC_FAILURE = 1;

    /**
     * Failed because we reached the sending queue limit.
     */
    public static final int RESULT_ERROR_LIMIT_EXCEEDED = 5;

    /**
     * No error.
     */
    public static final int RESULT_ERROR_NONE = 0;

    /**
     * Failed because service is currently unavailable
     */
    public static final int RESULT_ERROR_NO_SERVICE = 4;

    /**
     * Failed because no pdu provided
     */
    public static final int RESULT_ERROR_NULL_PDU = 3;

    /**
     * Failed because radio was explicitly turned off
     */
    public static final int RESULT_ERROR_RADIO_OFF = 2;

    /**
     * Failed because the user has denied this app ever send premium short codes.
     */
    public static final int RESULT_ERROR_SHORT_CODE_NEVER_ALLOWED = 8;

    /**
     * Failed because user denied the sending of this short code.
     */
    public static final int RESULT_ERROR_SHORT_CODE_NOT_ALLOWED = 7;

    /**
     * Failed because of an internal error
     */
    public static final int RESULT_INTERNAL_ERROR = 21;

    /**
     * Failed because of invalid arguments
     */
    public static final int RESULT_INVALID_ARGUMENTS = 11;

    /**
     * Failed sending via bluetooth because the bluetooth device address is invalid
     */
    public static final int RESULT_INVALID_BLUETOOTH_ADDRESS = 26;

    /**
     * Failed because of an invalid smsc address
     */
    public static final int RESULT_INVALID_SMSC_ADDRESS = 19;

    /**
     * Failed because the sms format is not valid
     */
    public static final int RESULT_INVALID_SMS_FORMAT = 14;

    /**
     * Failed because of an invalid state
     */
    public static final int RESULT_INVALID_STATE = 12;

    /**
     * Failed because of a modem error
     */
    public static final int RESULT_MODEM_ERROR = 16;

    /**
     * Failed because of a network error
     */
    public static final int RESULT_NETWORK_ERROR = 17;

    /**
     * Failed because of network rejection
     */
    public static final int RESULT_NETWORK_REJECT = 10;

    /**
     * Failed sending via bluetooth because the bluetooth service is not available
     */
    public static final int RESULT_NO_BLUETOOTH_SERVICE = 25;

    /**
     * Set by BroadcastReceiver to indicate there's no default sms app.
     */
    public static final int RESULT_NO_DEFAULT_SMS_APP = 32;

    /**
     * Failed because there is no memory
     */
    public static final int RESULT_NO_MEMORY = 13;

    /**
     * Failed because there are no resources
     */
    public static final int RESULT_NO_RESOURCES = 22;

    /**
     * Failed because the operation is not allowed
     */
    public static final int RESULT_OPERATION_NOT_ALLOWED = 20;

    /**
     * Failed because the radio was not available
     */
    public static final int RESULT_RADIO_NOT_AVAILABLE = 9;

    /**
     * SMS receive dispatch failure.
     */
    public static final int RESULT_RECEIVE_DISPATCH_FAILURE = 500;

    /**
     * SMS receive injected null PDU.
     */
    public static final int RESULT_RECEIVE_INJECTED_NULL_PDU = 501;

    /**
     * SMS received null message from the radio interface layer.
     */
    public static final int RESULT_RECEIVE_NULL_MESSAGE_FROM_RIL = 503;

    /**
     * SMS receive encountered runtime exception.
     */
    public static final int RESULT_RECEIVE_RUNTIME_EXCEPTION = 502;

    /**
     * SMS receive encountered an SQL exception.
     */
    public static final int RESULT_RECEIVE_SQL_EXCEPTION = 505;

    /**
     * SMS receive an exception parsing a uri.
     */
    public static final int RESULT_RECEIVE_URI_EXCEPTION = 506;

    /**
     * SMS short code received while the phone is in encrypted state.
     */
    public static final int RESULT_RECEIVE_WHILE_ENCRYPTED = 504;

    /**
     * Set by BroadcastReceiver to indicate a remote exception while handling a message.
     */
    public static final int RESULT_REMOTE_EXCEPTION = 31;

    /**
     * Failed because the request is not supported
     */
    public static final int RESULT_REQUEST_NOT_SUPPORTED = 24;

    /**
     * Operation aborted
     */
    public static final int RESULT_RIL_ABORTED = 137;

    /**
     * Access is barred.
     */
    public static final int RESULT_RIL_ACCESS_BARRED = 122;

    /**
     * SMS is blocked due to call control, e.g., resource unavailable in the SMR entity.
     */
    public static final int RESULT_RIL_BLOCKED_DUE_TO_CALL = 123;

    /**
     * The request has been cancelled.
     */
    public static final int RESULT_RIL_CANCELLED = 119;

    /**
     * Operation cannot be performed because the device is currently in use
     */
    public static final int RESULT_RIL_DEVICE_IN_USE = 136;

    /**
     * The SMS message was not encoded properly.
     */
    public static final int RESULT_RIL_ENCODING_ERR = 109;

    /**
     * A RIL error occurred during the SMS send.
     */
    public static final int RESULT_RIL_GENERIC_ERROR = 124;

    /**
     * The modem encountered an unexpected error scenario while handling the request.
     */
    public static final int RESULT_RIL_INTERNAL_ERR = 113;

    /**
     * The radio received invalid arguments in the request.
     */
    public static final int RESULT_RIL_INVALID_ARGUMENTS = 104;

    /**
     * The radio cannot process the request in the current modem state.
     */
    public static final int RESULT_RIL_INVALID_MODEM_STATE = 115;

    /**
     * A RIL internal error when one of the RIL layers receives an unrecognized response from a
     * lower layer.
     */
    public static final int RESULT_RIL_INVALID_RESPONSE = 125;

    /**
     * Cannot process the request in current SIM state
     */
    public static final int RESULT_RIL_INVALID_SIM_STATE = 130;

    /**
     * The specified SMSC address was invalid.
     */
    public static final int RESULT_RIL_INVALID_SMSC_ADDRESS = 110;

    /**
     * The radio returned an error indicating invalid sms format.
     */
    public static final int RESULT_RIL_INVALID_SMS_FORMAT = 107;

    /**
     * The radio returned an unexpected request for the current state.
     */
    public static final int RESULT_RIL_INVALID_STATE = 103;

    /**
     * The vendor RIL received an unexpected or incorrect response.
     */
    public static final int RESULT_RIL_MODEM_ERR = 111;

    /**
     * The radio received an error from the network.
     */
    public static final int RESULT_RIL_NETWORK_ERR = 112;

    /**
     * The network is not ready to perform the request.
     */
    public static final int RESULT_RIL_NETWORK_NOT_READY = 116;

    /**
     * The sms request was rejected by the network.
     */
    public static final int RESULT_RIL_NETWORK_REJECT = 102;

    /**
     * The radio didn't have sufficient memory to process the request.
     */
    public static final int RESULT_RIL_NO_MEMORY = 105;

    /**
     * Network cannot be found
     */
    public static final int RESULT_RIL_NO_NETWORK_FOUND = 135;

    /**
     * There are insufficient resources to process the request.
     */
    public static final int RESULT_RIL_NO_RESOURCES = 118;

    /**
     * ACK received when there is no SMS to ack
     */
    public static final int RESULT_RIL_NO_SMS_TO_ACK = 131;

    /**
     * Device does not have subscription
     */
    public static final int RESULT_RIL_NO_SUBSCRIPTION = 134;

    /**
     * The radio reports the request is not allowed.
     */
    public static final int RESULT_RIL_OPERATION_NOT_ALLOWED = 117;

    /**
     * The radio did not start or is resetting.
     */
    public static final int RESULT_RIL_RADIO_NOT_AVAILABLE = 100;

    /**
     * The request was not supported by the radio.
     */
    public static final int RESULT_RIL_REQUEST_NOT_SUPPORTED = 114;

    /**
     * The radio denied the operation due to overly-frequent requests.
     */
    public static final int RESULT_RIL_REQUEST_RATE_LIMITED = 106;

    /**
     * 1X voice and SMS are not allowed simultaneously.
     */
    public static final int RESULT_RIL_SIMULTANEOUS_SMS_AND_CALL_NOT_ALLOWED = 121;

    /**
     * The radio failed to set the location where the CDMA subscription
     * can be retrieved because the SIM or RUIM is absent.
     */
    public static final int RESULT_RIL_SIM_ABSENT = 120;

    /**
     * SIM is busy
     */
    public static final int RESULT_RIL_SIM_BUSY = 132;

    /**
     * Received error from SIM card
     */
    public static final int RESULT_RIL_SIM_ERROR = 129;

    /**
     * The target EF is full
     */
    public static final int RESULT_RIL_SIM_FULL = 133;

    /**
     * Operation requires SIM PIN2 to be entered
     */
    public static final int RESULT_RIL_SIM_PIN2 = 126;

    /**
     * Operation requires SIM PUK2 to be entered
     */
    public static final int RESULT_RIL_SIM_PUK2 = 127;

    /**
     * The radio failed to send the sms and needs to retry.
     */
    public static final int RESULT_RIL_SMS_SEND_FAIL_RETRY = 101;

    /**
     * Fail to find CDMA subscription from specified location
     */
    public static final int RESULT_RIL_SUBSCRIPTION_NOT_AVAILABLE = 128;

    /**
     * The radio encountered a platform or system error.
     */
    public static final int RESULT_RIL_SYSTEM_ERR = 108;

    /**
     * Failed sending during an emergency call
     */
    public static final int RESULT_SMS_BLOCKED_DURING_EMERGENCY = 29;

    /**
     * Failed to send an sms retry
     */
    public static final int RESULT_SMS_SEND_RETRY_FAILED = 30;

    /**
     * Failed because of a system error
     */
    public static final int RESULT_SYSTEM_ERROR = 15;

    /**
     * Failed sending because the user denied or canceled the dialog displayed for a premium
     * shortcode sms or rate-limited sms.
     */
    public static final int RESULT_UNEXPECTED_EVENT_STOP_SENDING = 28;

    /**
     * User is not associated with the subscription.
     */
    public static final int RESULT_USER_NOT_ALLOWED = 33;

    /**
     * RP-Cause Value for Call Barring
     */
    public static final int SMS_RP_CAUSE_CALL_BARRING = 10;

    /**
     * RP-Cause Value for SMS Failure due to Congestion in network
     */
    public static final int SMS_RP_CAUSE_CONGESTION = 42;

    /**
     * RP-Cause Value for Destination is Out of Order
     */
    public static final int SMS_RP_CAUSE_DESTINATION_OUT_OF_ORDER = 27;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_FACILITY_NOT_IMPLEMENTED = 69;

    /**
     * RP-Cause Value when SMS Facilty is not subscribed by Reote device
     */
    public static final int SMS_RP_CAUSE_FACILITY_NOT_SUBSCRIBED = 50;

    /**
     * RP-Cause Value when SMS Facility if Rejected by Operator
     */
    public static final int SMS_RP_CAUSE_FACILITY_REJECTED = 29;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_INFORMATION_ELEMENT_NON_EXISTENT = 99;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_INTERWORKING_UNSPECIFIED = 127;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_INVALID_MANDATORY_INFORMATION = 96;

    /**
     * RP-Cause Value when RP-MessageRefere
     */
    public static final int SMS_RP_CAUSE_INVALID_MESSAGE_REFERENCE_VALUE = 81;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_MESSAGE_INCOMPATIBLE_WITH_PROTOCOL_STATE = 98;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_MESSAGE_TYPE_NON_EXISTENT = 97;

    /**
     * RP-Cause Value when network is out of order
     */
    public static final int SMS_RP_CAUSE_NETWORK_OUT_OF_ORDER = 38;

    /**
     * RP-Cause for Operator Barring
     */
    public static final int SMS_RP_CAUSE_OPERATOR_DETERMINED_BARRING = 8;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_PROTOCOL_ERROR = 111;

    /**
     * RP-Cause value for Reserved Number
     */
    public static final int SMS_RP_CAUSE_RESERVED = 11;

    /**
     * RP-Cause Value when Network Resources are unavailable
     */
    public static final int SMS_RP_CAUSE_RESOURCES_UNAVAILABLE = 47;

    /**
     * RP-Cause Value when network does not provide the received service
     */
    public static final int SMS_RP_CAUSE_SEMANTICALLY_INCORRECT_MESSAGE = 95;

    /**
     * RP-Cause Value for Message Transfer Rejected by Network
     */
    public static final int SMS_RP_CAUSE_SHORT_MESSAGE_TRANSFER_REJECTED = 21;

    /**
     * RP-Cause Value For Temporary failure
     */
    public static final int SMS_RP_CAUSE_TEMPORARY_FAILURE = 41;

    /**
     * Unallocated Number Cause
     */
    public static final int SMS_RP_CAUSE_UNALLOCATED_NUMBER = 1;

    /**
     * RP-Cause Value when Subscriber is not Identified
     */
    public static final int SMS_RP_CAUSE_UNIDENTIFIED_SUBSCRIBER = 28;

    /**
     * RP-Cause Value when Subscriber is not Identified
     */
    public static final int SMS_RP_CAUSE_UNKNOWN_SUBSCRIBER = 30;

    /**
     * Free space (TS 51.011 10.5.3 / 3GPP2 C.S0023 3.4.27).
     */
    public static final int STATUS_ON_ICC_FREE = 0;

    /**
     * Received and read (TS 51.011 10.5.3 / 3GPP2 C.S0023 3.4.27).
     */
    public static final int STATUS_ON_ICC_READ = 1;

    /**
     * Stored and sent (TS 51.011 10.5.3 / 3GPP2 C.S0023 3.4.27).
     */
    public static final int STATUS_ON_ICC_SENT = 5;

    /**
     * Received and unread (TS 51.011 10.5.3 / 3GPP2 C.S0023 3.4.27).
     */
    public static final int STATUS_ON_ICC_UNREAD = 3;

    /**
     * Stored and unsent (TS 51.011 10.5.3 / 3GPP2 C.S0023 3.4.27).
     */
    public static final int STATUS_ON_ICC_UNSENT = 7;

    private SmsManager() {
    }
}
