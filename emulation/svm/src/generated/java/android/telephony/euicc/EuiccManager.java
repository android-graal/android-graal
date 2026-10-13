package android.telephony.euicc;

/**
 * EuiccManager is the application interface to eUICCs, or eSIMs/embedded SIMs.
 *
 * <p>You do not instantiate this class directly; instead, you retrieve an instance through
 * {@link Context#getSystemService(String)} and {@link Context#EUICC_SERVICE}. This instance will be
 * created using the default eUICC.
 *
 * <p>On a device with multiple eUICCs, you may want to create multiple EuiccManagers. To do this
 * you can call {@link #createForCardId}.
 *
 * <p>See {@link #isEnabled} before attempting to use these APIs.
 */
public final class EuiccManager {
    /**
     * Intent action to launch the embedded SIM (eUICC) management settings screen.
     *
     * <p>This screen shows a list of embedded profiles and offers the user the ability to switch
     * between them, download new profiles, and delete unused profiles.
     *
     * <p>The activity will immediately finish with {@link android.app.Activity#RESULT_CANCELED} if
     * {@link #isEnabled} is false.
     *
     * This is ued by non-LPA app to bring up LUI.
     */
    public static final String ACTION_MANAGE_EMBEDDED_SUBSCRIPTIONS = "android.telephony.euicc.action.MANAGE_EMBEDDED_SUBSCRIPTIONS";

    /**
     * Broadcast Action: The action sent to carrier app so it knows the carrier setup is not
     * completed.
     */
    public static final String ACTION_NOTIFY_CARRIER_SETUP_INCOMPLETE = "android.telephony.euicc.action.NOTIFY_CARRIER_SETUP_INCOMPLETE";

    /**
     * Intent action sent by a carrier app to launch the eSIM activation flow provided by the LPA UI
     * (LUI). The carrier app must send this intent with one of the following:
     *
     * <p>{@link #EXTRA_USE_QR_SCANNER} not set or set to false: The LPA should try to get an
     * activation code from the carrier app by binding to the carrier app service implementing
     * {@code android.service.euicc.EuiccService#ACTION_BIND_CARRIER_PROVISIONING_SERVICE}.
     * <p>{@link #EXTRA_USE_QR_SCANNER} set to true: The LPA should launch a QR scanner for the user
     * to scan an eSIM profile QR code.
     *
     * <p>Upon completion, the LPA should return one of the following results to the carrier app:
     *
     * <p>{@code Activity.RESULT_OK}: The LPA has succeeded in downloading the new eSIM profile.
     * <p>{@code Activity.RESULT_CANCELED}: The carrier app should treat this as if the user pressed
     * the back button.
     * <p>Anything else: The carrier app should treat this as an error.
     *
     * <p>LPA needs to check if caller's package name is allowed to perform this action.
     */
    public static final String ACTION_START_EUICC_ACTIVATION = "android.telephony.euicc.action.START_EUICC_ACTIVATION";

    /**
     * Result code for an operation indicating that an unresolvable error occurred.
     *
     * {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE} will be populated with a detailed error
     * code for logging/debugging purposes only.
     */
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_ERROR = 2;

    /**
     * Result code for an operation indicating that the operation succeeded.
     */
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_OK = 0;

    /**
     * Result code for an operation indicating that the user must take some action before the
     * operation can continue.
     *
     * @see #startResolutionActivity
     */
    public static final int EMBEDDED_SUBSCRIPTION_RESULT_RESOLVABLE_ERROR = 1;

    /**
     * Address is missing e.g SMDS/SMDP address is missing.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_ADDRESS_MISSING = 10011;

    /**
     * Operation such as downloading/switching to another profile failed due to device being
     * carrier locked.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_CARRIER_LOCKED = 10000;

    /**
     * Certificate needed for authentication is not valid or missing. E.g  SMDP/SMDS authentication
     * failed.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_CERTIFICATE_ERROR = 10012;

    /**
     * Failure to create a connection.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_CONNECTION_ERROR = 10014;

    /**
     * Failed to load profile onto eUICC due to Profile Policy Rules.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_DISALLOWED_BY_PPR = 10010;

    /**
     * There is no more space available on the eUICC for new profiles.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_EUICC_INSUFFICIENT_MEMORY = 10004;

    /**
     * eUICC is missing or defective on the device.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_EUICC_MISSING = 10006;

    /**
     * The profile's carrier is incompatible with the LPA.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_INCOMPATIBLE_CARRIER = 10003;

    /**
     * Failure to load the profile onto the eUICC card. e.g
     * 1. iccid of the profile already exists on the eUICC.
     * 2. GSMA(.22 v2.2) Profile Install Result - installFailedDueToDataMismatch
     * 3. operation was interrupted
     * 4. SIMalliance error in PEStatus(SGP.22 v2.2 section 2.5.6.1)
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_INSTALL_PROFILE = 10009;

    /**
     * The activation code(SGP.22 v2.2 section[4.1]) is invalid.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_INVALID_ACTIVATION_CODE = 10001;

    /**
     * The confirmation code(SGP.22 v2.2 section[4.7]) is invalid.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_INVALID_CONFIRMATION_CODE = 10002;

    /**
     * Failure due to target port is not supported.
     * @see #switchToSubscription(int, int, PendingIntent)
     */
    public static final int ERROR_INVALID_PORT = 10017;

    /**
     * Response format is invalid. e.g SMDP/SMDS response contains invalid json, header or/and ASN1.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_INVALID_RESPONSE = 10015;

    /**
     * No profiles available.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_NO_PROFILES_AVAILABLE = 10013;

    /**
     * The operation is currently busy, try again later.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_OPERATION_BUSY = 10016;

    /**
     * No SIM card is available in the device.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_SIM_MISSING = 10008;

    /**
     * Timed out while waiting for an operation to complete. i.e restart, disable,
     * switch reset etc.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_TIME_OUT = 10005;

    /**
     * The eUICC card(hardware) version is incompatible with the software
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int ERROR_UNSUPPORTED_VERSION = 10007;

    /**
     * Temporary failure to retrieve available memory because eUICC is not ready.
     */
    public static final long EUICC_MEMORY_FIELD_UNAVAILABLE = -1L;

    /**
     * Key for an extra set on {@link PendingIntent} result callbacks providing a detailed result
     * code.
     *
     * <p>The value of this key is an integer and contains two portions. The first byte is
     * OperationCode and the reaming three bytes is the ErrorCode.
     *
     * OperationCode is the first byte of the result code and is a categorization which defines what
     * type of operation took place when an error occurred. e.g {@link #OPERATION_DOWNLOAD} means
     * the error is related to download.Since the OperationCode only uses at most one byte, the
     * maximum allowed quantity is 255(0xFF).
     *
     * ErrorCode is the remaining three bytes of the result code, and it denotes what happened.
     * e.g a combination of {@link #OPERATION_DOWNLOAD} and {@link #ERROR_TIME_OUT} will suggest the
     * download operation has timed out. The only exception here is
     * {@link #OPERATION_SMDX_SUBJECT_REASON_CODE}, where instead of ErrorCode, SubjectCode[5.2.6.1
     * from GSMA (SGP.22 v2.2) and ReasonCode[5.2.6.2] from GSMA (SGP.22 v2.2) are encoded. @see
     * {@link #EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_SUBJECT_CODE} and
     * {@link #EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_REASON_CODE}
     *
     * In the case where ErrorCode contains a value of 0, it means it's an unknown error. E.g Intent
     * only contains {@link #OPERATION_DOWNLOAD} and ErrorCode is 0 implies this is an unknown
     * Download error.
     *
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_OPERATION_CODE
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_ERROR_CODE
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_SUBJECT_CODE
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_REASON_CODE
     */
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DETAILED_CODE";

    /**
     * Key for an extra set on {@code #getDownloadableSubscriptionMetadata} PendingIntent result
     * callbacks providing the downloadable subscription metadata.
     */
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTION = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_DOWNLOADABLE_SUBSCRIPTION";

    /**
     * Key for an extra set on {@link PendingIntent} result callbacks providing a
     * ErrorCode of {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE},
     * value will be an int.
     */
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_ERROR_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_ERROR_CODE";

    /**
     * Key for an extra set on {@link PendingIntent} result callbacks providing a
     * OperationCode of {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE},
     * value will be an int.
     */
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_OPERATION_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_OPERATION_CODE";

    /**
     * Key for an extra set on {@link PendingIntent} result callbacks providing a
     * ReasonCode[5.2.6.2] from GSMA (SGP.22 v2.2) decoded from
     * {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE}.
     * The value of this extra will be a String.
     */
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_REASON_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_SMDX_REASON_CODE";

    /**
     * Key for an extra set on {@link PendingIntent} result callbacks providing a
     * SubjectCode[5.2.6.1] from GSMA (SGP.22 v2.2) decoded from
     * {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE}.
     * The value of this extra will be a String.
     */
    public static final String EXTRA_EMBEDDED_SUBSCRIPTION_SMDX_SUBJECT_CODE = "android.telephony.euicc.extra.EMBEDDED_SUBSCRIPTION_SMDX_SUBJECT_CODE";

    /**
     * Key for an extra set on actions {@link #ACTION_START_EUICC_ACTIVATION} providing a boolean
     * value of whether to start eSIM activation with QR scanner.
     *
     * <p>Expected type of the extra data: boolean
     */
    public static final String EXTRA_USE_QR_SCANNER = "android.telephony.euicc.extra.USE_QR_SCANNER";

    /**
     * Optional meta-data attribute for a carrier app providing an icon to use to represent the
     * carrier. If not provided, the app's launcher icon will be used as a fallback.
     */
    public static final String META_DATA_CARRIER_ICON = "android.telephony.euicc.carriericon";

    /**
     * The exception of failing to execute an APDU command. It can be caused by an error
     * happening on opening the basic or logical channel, or the response of the APDU command is
     * not success (0x9000).
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_APDU = 8;

    /**
     * Download profile error.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_DOWNLOAD = 5;

    /**
     * eUICC card error.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_EUICC_CARD = 3;

    /**
     * eUICC returned an error defined in GSMA (SGP.22 v2.2) while running one of the ES10x
     * functions.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_EUICC_GSMA = 7;

    /**
     * HTTP error
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_HTTP = 11;

    /**
     * Subscription's metadata error
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_METADATA = 6;

    /**
     * SIM slot error. Failed to switch slot, failed to access the physical slot etc.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_SIM_SLOT = 2;

    /**
     * SMDX(SMDP/SMDS) error
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_SMDX = 9;

    /**
     * SubjectCode[5.2.6.1] and ReasonCode[5.2.6.2] error from GSMA (SGP.22 v2.2)
     * When {@link #OPERATION_SMDX_SUBJECT_REASON_CODE} is used as the
     * {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE}, the remaining three bytes of the integer
     * result from {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE} will be used to stored the
     * SubjectCode and ReasonCode from the GSMA spec and NOT ErrorCode.
     *
     * The encoding will follow the format of:
     * 1. The first byte of the result will be 255(0xFF).
     * 2. Remaining three bytes(24 bits) will be split into six sections, 4 bits in each section.
     * 3. A SubjectCode/ReasonCode will take 12 bits each.
     * 4. The maximum number can be represented per section is 15, as that is the maximum number
     * allowed to be stored into 4 bits
     * 5. Maximum supported nested category from GSMA is three layers. E.g 8.11.1.2 is not
     * supported.
     *
     * E.g given SubjectCode(8.11.1) and ReasonCode(5.1)
     *
     * Base10:  0       10      8       11      1       0       5       1
     * Base2:   0000    1010    1000    1011    0001    0000    0101    0001
     * Base16:  0       A       8       B       1       0       5       1
     *
     * Thus the integer stored in {@link #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE} is
     * 0xA8B1051(176885841)
     *
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_SMDX_SUBJECT_REASON_CODE = 10;

    /**
     * Generic switching profile error
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_SWITCH = 4;

    /**
     * Internal system error.
     * @see #EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE for details
     */
    public static final int OPERATION_SYSTEM = 1;

    private EuiccManager() {
    }
}
