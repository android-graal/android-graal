package android.telephony.ims;

import android.os.Parcelable;

/**
 * Provides details on why an IMS call failed. Applications can use the methods in this class to
 * get local or network fault behind an IMS services failure. For example, if the code is
 * CODE_CALL_BARRED, then the call was blocked by network call barring configuration and it is not
 * the device's bug and the user can retry the call when network lift the barring.
 * Typical use case includes call backs when IMS call state changed with this class as a param
 * containing details on why IMS call changed state/failed.
 */
public final class ImsReasonInfo implements Parcelable {
    /**
     * Call failed because of access class barring
     */
    public static final int CODE_ACCESS_CLASS_BLOCKED = 1512;

    /**
     * MT call has ended due to a release from the network because the call was answered elsewhere.
     */
    public static final int CODE_ANSWERED_ELSEWHERE = 1014;

    /**
     * Device declined a call due to a denylisted caller ID.
     */
    public static final int CODE_BLACKLISTED_CALL_ID = 506;

    /**
     * The call was blocked by call barring configuration.
     */
    public static final int CODE_CALL_BARRED = 240;

    /**
     * CALL DROP error code for the case when a device is ePDG capable and when the user is on an
     * active wifi call and at the edge of coverage and there is no qualified LTE network available
     * to handover the call to. We get a handover NOT_TRIGERRED message from the modem. This error
     * code is received as part of the handover message.
     */
    public static final int CODE_CALL_DROP_IWLAN_TO_LTE_UNAVAILABLE = 1100;

    /**
     * For MultiEndpoint - Call has been pulled from primary to secondary.
     */
    public static final int CODE_CALL_END_CAUSE_CALL_PULL = 1016;

    /**
     * For MultiEndpoint - Call Pull request has failed.
     */
    public static final int CODE_CALL_PULL_OUT_OF_SYNC = 1015;

    /**
     * Indicates the call was disconnected due to the user disabling cellular data.
     */
    public static final int CODE_DATA_DISABLED = 1406;

    /**
     * Indicates the call was disconnected due to the user reaching their data limit.
     */
    public static final int CODE_DATA_LIMIT_REACHED = 1405;

    /**
     * Stk Call Control modified DIAL request to DIAL with modified data.
     */
    public static final int CODE_DIAL_MODIFIED_TO_DIAL = 246;

    /**
     * Stk Call Control modified DIAL request to Video DIAL request.
     */
    public static final int CODE_DIAL_MODIFIED_TO_DIAL_VIDEO = 247;

    /**
     * Stk Call Control modified DIAL request to SS request.
     */
    public static final int CODE_DIAL_MODIFIED_TO_SS = 245;

    /**
     * Stk Call Control modified DIAL request to USSD request.
     */
    public static final int CODE_DIAL_MODIFIED_TO_USSD = 244;

    /**
     * Stk Call Control modified Video DIAL request to DIAL request.
     */
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_DIAL = 248;

    /**
     * Stk Call Control modified Video DIAL request to Video DIAL request.
     */
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_DIAL_VIDEO = 249;

    /**
     * Stk Call Control modified Video DIAL request to SS request.
     */
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_SS = 250;

    /**
     * Stk Call Control modified Video DIAL request to USSD request.
     */
    public static final int CODE_DIAL_VIDEO_MODIFIED_TO_USSD = 251;

    /**
     * Emergency callback mode is not supported.
     */
    public static final int CODE_ECBM_NOT_SUPPORTED = 901;

    /**
     * Emergency call failed in the modem with a permanent fail cause and should not be redialed on
     * this slot. If there are any other slots available for emergency calling, try those.
     */
    public static final int CODE_EMERGENCY_PERM_FAILURE = 364;

    /**
     * Emergency call failed in the modem with a temporary fail cause and should be redialed on this
     * slot.
     */
    public static final int CODE_EMERGENCY_TEMP_FAILURE = 363;

    /**
     * Establishment of the ePDG Tunnel Failed.
     */
    public static final int CODE_EPDG_TUNNEL_ESTABLISH_FAILURE = 1400;

    /**
     * Connection to the packet gateway is lost.
     */
    public static final int CODE_EPDG_TUNNEL_LOST_CONNECTION = 1402;

    /**
     * Re-keying of the ePDG Tunnel Failed; may not always result in teardown.
     */
    public static final int CODE_EPDG_TUNNEL_REKEY_FAILURE = 1401;

    /**
     * The operation is restricted to fixed dialing numbers only.
     */
    public static final int CODE_FDN_BLOCKED = 241;

    /**
     * Indicates the registration attempt on IWLAN failed due to IKEv2 authetication failure
     * during tunnel establishment.
     */
    public static final int CODE_IKEV2_AUTH_FAILURE = 1408;

    /**
     * Network rejected the emergency call request because IMEI was used as identification
     * and this capability is not supported by the network.
     */
    public static final int CODE_IMEI_NOT_ACCEPTED = 243;

    /**
     * DPD Procedure received no response or send failed.
     */
    public static final int CODE_IWLAN_DPD_FAILURE = 1300;

    /**
     * The call is busy.
     */
    public static final int CODE_LOCAL_CALL_BUSY = 142;

    /**
     * VoLTE service can't be provided by the network or remote end, retry the call.
     * Resolve the extra code provided in (EXTRA_CODE_CALL_RETRY_*) if the below code is set
     */
    public static final int CODE_LOCAL_CALL_CS_RETRY_REQUIRED = 146;

    /**
     * The Call has been declined locally on this device.
     */
    public static final int CODE_LOCAL_CALL_DECLINE = 143;

    /**
     * Maximum number of simultaneous calls exceeded
     */
    public static final int CODE_LOCAL_CALL_EXCEEDED = 141;

    /**
     * Can not complete call; resource reservation is failed (QoS precondition)
     */
    public static final int CODE_LOCAL_CALL_RESOURCE_RESERVATION_FAILED = 145;

    /**
     * IMS call is already terminated (in TERMINATED state).
     */
    public static final int CODE_LOCAL_CALL_TERMINATED = 148;

    /**
     * Can not complete call; an SRVCC is in progress.
     */
    public static final int CODE_LOCAL_CALL_VCC_ON_PROGRESSING = 144;

    /**
     * VoLTE service can't be provided by the network temporarily, retry the call.
     */
    public static final int CODE_LOCAL_CALL_VOLTE_RETRY_REQUIRED = 147;

    /**
     * IMS Call ended during conference merge process
     */
    public static final int CODE_LOCAL_ENDED_BY_CONFERENCE_MERGE = 108;

    /**
     * Call was disconnected because a handover is not feasible due to network conditions.
     */
    public static final int CODE_LOCAL_HO_NOT_FEASIBLE = 149;

    /**
     * The passed argument is invalid.
     */
    public static final int CODE_LOCAL_ILLEGAL_ARGUMENT = 101;

    /**
     * The operation was invoked while in an invalid call state.
     */
    public static final int CODE_LOCAL_ILLEGAL_STATE = 102;

    /**
     * ImsService has crashed (service connection is lost).
     */
    public static final int CODE_LOCAL_IMS_SERVICE_DOWN = 106;

    /**
     * IMS service internal error
     */
    public static final int CODE_LOCAL_INTERNAL_ERROR = 103;

    /**
     * Service unavailable; low battery
     */
    public static final int CODE_LOCAL_LOW_BATTERY = 112;

    /**
     * Service unavailable; IP changed
     */
    public static final int CODE_LOCAL_NETWORK_IP_CHANGED = 124;

    /**
     * Service unavailable; no LTE coverage
     * (VoLTE is not supported even though IMS is registered)
     */
    public static final int CODE_LOCAL_NETWORK_NO_LTE_COVERAGE = 122;

    /**
     * Service unavailable; out of service (data service state)
     */
    public static final int CODE_LOCAL_NETWORK_NO_SERVICE = 121;

    /**
     * Service unavailable; located in roaming area
     */
    public static final int CODE_LOCAL_NETWORK_ROAMING = 123;

    /**
     * Service unavailable; IMS is not registered
     */
    public static final int CODE_LOCAL_NOT_REGISTERED = 132;

    /**
     * No pending incoming call exists
     */
    public static final int CODE_LOCAL_NO_PENDING_CALL = 107;

    /**
     * Service unavailable; radio power off
     */
    public static final int CODE_LOCAL_POWER_OFF = 111;

    /**
     * Service unavailable; for an unspecified reason
     */
    public static final int CODE_LOCAL_SERVICE_UNAVAILABLE = 131;

    /**
     * Device declined/ended a call due to a low battery condition.
     */
    public static final int CODE_LOW_BATTERY = 505;

    /**
     * The maximum number of calls allowed has been reached.  Used in a multi-endpoint scenario
     * where the number of calls across all connected devices has reached the maximum.
     */
    public static final int CODE_MAXIMUM_NUMBER_OF_CALLS_REACHED = 1403;

    /**
     * Media resource initialization failed
     */
    public static final int CODE_MEDIA_INIT_FAILED = 401;

    /**
     * Media is not supported; so dropped the call
     */
    public static final int CODE_MEDIA_NOT_ACCEPTABLE = 403;

    /**
     * RTP timeout (no audio / video traffic in the session)
     */
    public static final int CODE_MEDIA_NO_DATA = 402;

    /**
     * Unspecified media related error.
     */
    public static final int CODE_MEDIA_UNSPECIFIED = 404;

    /**
     * Fail code used to indicate that Multi-endpoint is not supported by the IMS framework.
     */
    public static final int CODE_MULTIENDPOINT_NOT_SUPPORTED = 902;

    /**
     * Call failed because of network congestion, resource is not available,
     * or no circuit or channel available, etc.
     */
    public static final int CODE_NETWORK_CONGESTION = 1624;

    /**
     * Call/IMS registration is failed/dropped because of a network detach
     */
    public static final int CODE_NETWORK_DETACH = 1513;

    /**
     * The failure is due to explicit reject from network
     */
    public static final int CODE_NETWORK_REJECT = 1504;

    /**
     * The failure is due to UE timer expired while waiting for a response from network
     */
    public static final int CODE_NETWORK_RESP_TIMEOUT = 1503;

    /**
     * Call failed because WiFi call could not complete and circuit switch silent redial
     * is not allowed while roaming on another network.
     */
    public static final int CODE_NO_CSFB_IN_CS_ROAM = 1516;

    /**
     * The call cannot be established because of no valid SIM
     */
    public static final int CODE_NO_VALID_SIM = 1501;

    public static final int CODE_OEM_CAUSE_1 = 61441;

    public static final int CODE_OEM_CAUSE_10 = 61450;

    public static final int CODE_OEM_CAUSE_11 = 61451;

    public static final int CODE_OEM_CAUSE_12 = 61452;

    public static final int CODE_OEM_CAUSE_13 = 61453;

    public static final int CODE_OEM_CAUSE_14 = 61454;

    public static final int CODE_OEM_CAUSE_15 = 61455;

    public static final int CODE_OEM_CAUSE_2 = 61442;

    public static final int CODE_OEM_CAUSE_3 = 61443;

    public static final int CODE_OEM_CAUSE_4 = 61444;

    public static final int CODE_OEM_CAUSE_5 = 61445;

    public static final int CODE_OEM_CAUSE_6 = 61446;

    public static final int CODE_OEM_CAUSE_7 = 61447;

    public static final int CODE_OEM_CAUSE_8 = 61448;

    public static final int CODE_OEM_CAUSE_9 = 61449;

    /**
     * The failure is due to radio access failure. ex. RACH failure
     */
    public static final int CODE_RADIO_ACCESS_FAILURE = 1505;

    /**
     * The failure is due internal error at modem
     */
    public static final int CODE_RADIO_INTERNAL_ERROR = 1502;

    /**
     * Call/IMS registration failed/dropped because of a RLF
     */
    public static final int CODE_RADIO_LINK_FAILURE = 1506;

    /**
     * Call/IMS registration failed/dropped because of radio link lost
     */
    public static final int CODE_RADIO_LINK_LOST = 1507;

    /**
     * The call cannot be established because RADIO is OFF
     */
    public static final int CODE_RADIO_OFF = 1500;

    /**
     * Call failed/dropped because of RRC abnormally released by modem/network
     */
    public static final int CODE_RADIO_RELEASE_ABNORMAL = 1511;

    /**
     * Call failed/dropped because of RRC connection release from NW
     */
    public static final int CODE_RADIO_RELEASE_NORMAL = 1510;

    /**
     * Call failed because of a RRC connection setup failure
     */
    public static final int CODE_RADIO_SETUP_FAILURE = 1509;

    /**
     * The call Call/IMS registration failed because of a radio uplink issue
     */
    public static final int CODE_RADIO_UPLINK_FAILURE = 1508;

    /**
     * IMS Registration error code
     */
    public static final int CODE_REGISTRATION_ERROR = 1000;

    /**
     * For MultiEndPoint - Call was rejected elsewhere
     */
    public static final int CODE_REJECTED_ELSEWHERE = 1017;

    /**
     * CDMA call collision.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_1X_COLLISION = 1603;

    /**
     * A call is ongoing on another sub.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_CALL_ON_OTHER_SUB = 1602;

    /**
     * The call type is not allowed on the current RAT.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_CALL_TYPE_NOT_ALLOWED = 1605;

    /**
     * Call from conference server, when TTY mode is ON.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_CONFERENCE_TTY_NOT_ALLOWED = 1617;

    /**
     * An internal error occured while processing the call.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_INTERNAL_ERROR = 1612;

    /**
     * Maximum number of allowed calls are already in progress.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_MAX_CALL_LIMIT_REACHED = 1608;

    /**
     * Another call is in the process of being establilshed.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_CALL_SETUP = 1607;

    /**
     * A call transfer is in progress.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_CALL_TRANSFER = 1611;

    /**
     * A call upgrade is in progress.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_CALL_UPGRADE = 1616;

    /**
     * Ongoing call, and call waiting is disabled.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_CALL_WAITING_DISABLED = 1601;

    /**
     * A conference call is ongoing.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_CONFERENCE_CALL = 1618;

    /**
     * A CS call is ongoing.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_CS_CALL = 1621;

    /**
     * And emergency call is ongoing.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_E911_CALL = 1606;

    /**
     * And encrypted call is ongoing; other calls not supported.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_ENCRYPTED_CALL = 1620;

    /**
     * A call handover is in progress.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_ONGOING_HANDOVER = 1614;

    /**
     * Call failure due to lack of dedicated bearer.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_QOS_FAILURE = 1613;

    /**
     * IMS is not registered for service yet.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_SERVICE_NOT_REGISTERED = 1604;

    /**
     * The rejection cause is not known.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_UNKNOWN = 1600;

    /**
     * Invalid/unsupported SDP headers received.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_UNSUPPORTED_SDP_HEADERS = 1610;

    /**
     * Invalid/unsupported SIP headers received.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_UNSUPPORTED_SIP_HEADERS = 1609;

    /**
     * A video call with AVPF is not supported.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_VT_AVPF_NOT_ALLOWED = 1619;

    /**
     * Video calling not supported with TTY.
     * <p>
     * Used with implicit call rejection.
     */
    public static final int CODE_REJECT_VT_TTY_NOT_ALLOWED = 1615;

    /**
     * Similar to {@link #CODE_LOCAL_CALL_DECLINE}, except indicates that a remote device has
     * declined the call.  Used in a multi-endpoint scenario where a remote device declined an
     * incoming call.
     */
    public static final int CODE_REMOTE_CALL_DECLINE = 1404;

    /**
     * UPGRADE DOWNGRADE operation failed
     * This can happen due to failure from SIP/RTP/SDP generation or a Call end is
     * triggered/received while Reinvite is in progress.
     */
    public static final int CODE_SESSION_MODIFICATION_FAILED = 1517;

    /**
     * Call failed due to SIP code 380 (Alternative Service response) while dialing an "undetected
     * emergency number".  This scenario is important in some regions where the carrier network will
     * identify other non-emergency help numbers (e.g. mountain rescue) when attempting to dial.
     */
    public static final int CODE_SIP_ALTERNATE_EMERGENCY_CALL = 1514;

    /**
     * SIP Response : 485
     * Request-URI is ambiguous
     */
    public static final int CODE_SIP_AMBIGUOUS = 376;

    /**
     * SIP response 484 : Address Incomplete
     */
    public static final int CODE_SIP_BAD_ADDRESS = 337;

    /**
     * Sip 400 response : Bad Request
     */
    public static final int CODE_SIP_BAD_REQUEST = 331;

    /**
     * Returned a busy response, may be one of the following:
     * SIP response 486 : Busy Here,
     * SIP response 600 : Busy Everywhere
     */
    public static final int CODE_SIP_BUSY = 338;

    /**
     * SIP Response : 481
     * Request received by the server does not match any dialog or transaction
     */
    public static final int CODE_SIP_CALL_OR_TRANS_DOES_NOT_EXIST = 372;

    /**
     * Received another unspecified error SIP response from the client.
     */
    public static final int CODE_SIP_CLIENT_ERROR = 342;

    /**
     * SIP Response : 421
     * Specific extension is required, which is not present in the HEADER
     */
    public static final int CODE_SIP_EXTENSION_REQUIRED = 370;

    /**
     * Sip 403 response : Forbidden
     */
    public static final int CODE_SIP_FORBIDDEN = 332;

    /**
     * Unspecified 6xx error.
     */
    public static final int CODE_SIP_GLOBAL_ERROR = 362;

    /**
     * SIP Response : 422
     * The session expiration field too small
     */
    public static final int CODE_SIP_INTERVAL_TOO_BRIEF = 371;

    /**
     * SIP Response : 482
     * Server has detected a loop
     */
    public static final int CODE_SIP_LOOP_DETECTED = 373;

    /**
     * SIP Response : 405
     * Method not allowed for the address in the Request URI
     */
    public static final int CODE_SIP_METHOD_NOT_ALLOWED = 366;

    /**
     * Received a not acceptable response, will be one of the following:
     * SIP response 406 : Not Acceptable
     * SIP response 488 : Not Acceptable Here
     * SIP response 606 : Not Acceptable
     */
    public static final int CODE_SIP_NOT_ACCEPTABLE = 340;

    /**
     * Sip 404 response : Not Found
     */
    public static final int CODE_SIP_NOT_FOUND = 333;

    /**
     * Received a not acceptable response, will be one of the following:
     * SIP response 410 : Gone
     * SIP response 604 : Does Not Exist Anywhere
     */
    public static final int CODE_SIP_NOT_REACHABLE = 341;

    /**
     * Not supported, because of one of the following:
     * SIP response 415 : Unsupported Media Type,
     * SIP response 416 : Unsupported URI Scheme,
     * SIP response 420 : Bad Extension
     */
    public static final int CODE_SIP_NOT_SUPPORTED = 334;

    /**
     * SIP Response : 407
     * The request requires user authentication
     */
    public static final int CODE_SIP_PROXY_AUTHENTICATION_REQUIRED = 367;

    /**
     * SIP 3xx response: SIP request is redirected
     */
    public static final int CODE_SIP_REDIRECTED = 321;

    /**
     * SIP response 487 : Request Terminated
     */
    public static final int CODE_SIP_REQUEST_CANCELLED = 339;

    /**
     * SIP Response : 413
     * Request body too large
     */
    public static final int CODE_SIP_REQUEST_ENTITY_TOO_LARGE = 368;

    /**
     * SIP Response : 491
     * Server has pending request for same dialog
     */
    public static final int CODE_SIP_REQUEST_PENDING = 377;

    /**
     * SIP response 408 : Request Timeout.
     */
    public static final int CODE_SIP_REQUEST_TIMEOUT = 335;

    /**
     * SIP Response : 414
     * Request-URI too large
     */
    public static final int CODE_SIP_REQUEST_URI_TOO_LARGE = 369;

    /**
     * Received an unspecified SIP server error response.
     */
    public static final int CODE_SIP_SERVER_ERROR = 354;

    /**
     * SIP response 501 : Server Internal Error
     */
    public static final int CODE_SIP_SERVER_INTERNAL_ERROR = 351;

    /**
     * SIP response 504 : Server Time-out
     */
    public static final int CODE_SIP_SERVER_TIMEOUT = 353;

    /**
     * SIP response 503 : Service Unavailable
     */
    public static final int CODE_SIP_SERVICE_UNAVAILABLE = 352;

    /**
     * SIP response 480 : Temporarily Unavailable
     */
    public static final int CODE_SIP_TEMPRARILY_UNAVAILABLE = 336;

    /**
     * SIP Response : 483
     * Max-Forwards value reached
     */
    public static final int CODE_SIP_TOO_MANY_HOPS = 374;

    /**
     * SIP response 481: Transaction Does Not Exist
     */
    public static final int CODE_SIP_TRANSACTION_DOES_NOT_EXIST = 343;

    /**
     * SIP Response : 493
     * The request cannot be decrypted by recipient
     */
    public static final int CODE_SIP_UNDECIPHERABLE = 378;

    /**
     * Call failure code during hangup/reject if user marked the call as unwanted.
     *
     * Android Telephony will receive information whether ROBO call feature is supported by the
     * network from modem and propagate the same to AOSP as new ImsCallProfile members. OEMs can
     * check this information and provide an option to the user to mark the call as unwanted.
     */
    public static final int CODE_SIP_USER_MARKED_UNWANTED = 365;

    /**
     * 603 : Decline
     */
    public static final int CODE_SIP_USER_REJECTED = 361;

    /**
     * Supplementary Services (HOLD/RESUME) - the command was cancelled.
     */
    public static final int CODE_SUPP_SVC_CANCELLED = 1202;

    /**
     * Supplementary Services (HOLD/RESUME) - the command failed.
     */
    public static final int CODE_SUPP_SVC_FAILED = 1201;

    /**
     * Supplementary Services (HOLD/RESUME) - the command resulted in a re-invite collision.
     */
    public static final int CODE_SUPP_SVC_REINVITE_COLLISION = 1203;

    /**
     * 1xx waiting timer is expired after sending INVITE request (MO calls only)
     */
    public static final int CODE_TIMEOUT_1XX_WAITING = 201;

    /**
     * User didn't answer during call setup operation (MO/MT)
     * MO : 200 OK to INVITE request is not received,
     * MT : No action from user after alerting the call
     */
    public static final int CODE_TIMEOUT_NO_ANSWER = 202;

    /**
     * User no answer during call update operation (MO/MT)
     * MO : 200 OK to re-INVITE request is not received,
     * MT : No action from user after alerting the call
     */
    public static final int CODE_TIMEOUT_NO_ANSWER_CALL_UPDATE = 203;

    /**
     * The Reason is unspecified.
     */
    public static final int CODE_UNSPECIFIED = 0;

    /**
     * Upgrade Downgrade request cancelled by the user who initiated it
     */
    public static final int CODE_USER_CANCELLED_SESSION_MODIFICATION = 512;

    /**
     * User declined an incoming call.
     */
    public static final int CODE_USER_DECLINE = 504;

    /**
     * User ignored an incoming call.
     */
    public static final int CODE_USER_IGNORE = 503;

    /**
     * No action was taken while an incoming call was ringing.
     */
    public static final int CODE_USER_NOANSWER = 502;

    /**
     * Upgrade Downgrade request rejected by
     * Remote user if the request is MO initiated
     * Local user if the request is MT initiated
     */
    public static final int CODE_USER_REJECTED_SESSION_MODIFICATION = 511;

    /**
     * User triggers the call to be terminated.
     */
    public static final int CODE_USER_TERMINATED = 501;

    /**
     * The call has been terminated by the network or remote user.
     */
    public static final int CODE_USER_TERMINATED_BY_REMOTE = 510;

    /**
     * The password entered for UT operations does not match the stored password.
     */
    public static final int CODE_UT_CB_PASSWORD_MISMATCH = 821;

    /**
     * The UT request resulted in a network error.
     */
    public static final int CODE_UT_NETWORK_ERROR = 804;

    /**
     * UT is currently not supported on this device.
     */
    public static final int CODE_UT_NOT_SUPPORTED = 801;

    /**
     * The requested UT operation is not allowed.
     */
    public static final int CODE_UT_OPERATION_NOT_ALLOWED = 803;

    /**
     * UT services are currently not available on this device.
     */
    public static final int CODE_UT_SERVICE_UNAVAILABLE = 802;

    /**
     * Sim Toolkit Call Control modified the UT operation to a dial command.
     */
    public static final int CODE_UT_SS_MODIFIED_TO_DIAL = 822;

    /**
     * Sim Toolkit Call Control modified the UT operation to a video call dial command.
     */
    public static final int CODE_UT_SS_MODIFIED_TO_DIAL_VIDEO = 825;

    /**
     * Sim Toolkit Call Control modified the UT operation to another supplementary service command.
     */
    public static final int CODE_UT_SS_MODIFIED_TO_SS = 824;

    /**
     * Sim Toolkit Call Control modified the UT operation to a USSD command.
     */
    public static final int CODE_UT_SS_MODIFIED_TO_USSD = 823;

    /**
     * Indicates a call was disconnected due to loss of wifi signal.
     */
    public static final int CODE_WIFI_LOST = 1407;

    /**
     * An extra that may be populated when the {@link #CODE_LOCAL_CALL_CS_RETRY_REQUIRED} result has
     * been returned.
     * <p>
     * Try to connect the call using CS by using the settings.
     */
    public static final int EXTRA_CODE_CALL_RETRY_BY_SETTINGS = 3;

    /**
     * An extra that may be populated when the {@link #CODE_LOCAL_CALL_CS_RETRY_REQUIRED} result has
     * been returned.
     * <p>
     * Try to connect the call using CS as emergency
     */
    public static final int EXTRA_CODE_CALL_RETRY_EMERGENCY = 4;

    /**
     * An extra that may be populated when the {@link #CODE_LOCAL_CALL_CS_RETRY_REQUIRED} result has
     * been returned.
     * <p>
     * Try to connect the call using CS
     */
    public static final int EXTRA_CODE_CALL_RETRY_NORMAL = 1;

    /**
     * An extra that may be populated when the {@link #CODE_LOCAL_CALL_CS_RETRY_REQUIRED} result has
     * been returned.
     * <p>
     * Try to connect the call using CS and do not notify the user.
     */
    public static final int EXTRA_CODE_CALL_RETRY_SILENT_REDIAL = 2;

    private ImsReasonInfo() {
    }
}
