package android.telephony;

/**
 * DataFailCause collects data connection failure causes code from different sources.
 */
public final class DataFailCause {
    /**
     * Access attempt is already in progress.
     */
    public static final int ACCESS_ATTEMPT_ALREADY_IN_PROGRESS = 2219;

    /**
     * Access blocked by the base station.
     */
    public static final int ACCESS_BLOCK = 2087;

    /**
     * Access blocked by the base station for all mobile devices.
     */
    public static final int ACCESS_BLOCK_ALL = 2088;

    /**
     * Access class blocking restrictions for the current camped cell.
     */
    public static final int ACCESS_CLASS_DSAC_REJECTION = 2108;

    /**
     * Access control list check failure at the lower layer.
     */
    public static final int ACCESS_CONTROL_LIST_CHECK_FAILURE = 2128;

    /**
     * UE requested to modify QoS parameters or the bearer control mode, which is not compatible
     * with the selected bearer control mode.
     */
    public static final int ACTIVATION_REJECTED_BCM_VIOLATION = 48;

    /**
     * Activation rejected by Gateway GPRS Support Node (GGSN), Serving Gateway or PDN Gateway.
     */
    public static final int ACTIVATION_REJECT_GGSN = 30;

    /**
     * Activation rejected, unspecified.
     */
    public static final int ACTIVATION_REJECT_UNSPECIFIED = 31;

    /**
     * Max number of Packet Data Protocol (PDP) context reached.
     */
    public static final int ACTIVE_PDP_CONTEXT_MAX_NUMBER_REACHED = 65;

    /**
     * If connection failed for all matching URSP rules.
     */
    public static final int ALL_MATCHING_RULES_FAILED = 2254;

    /**
     * APN has been disabled.
     */
    public static final int APN_DISABLED = 2045;

    /**
     * PDN connection to the APN is disallowed on the roaming network.
     */
    public static final int APN_DISALLOWED_ON_ROAMING = 2059;

    /**
     * New PDN bring up is rejected during interface selection because the UE has already allotted
     * the available interfaces for other PDNs.
     */
    public static final int APN_MISMATCH = 2054;

    /**
     * APN-related parameters are changed.
     */
    public static final int APN_PARAMETERS_CHANGED = 2060;

    /**
     * Interface bring up is attempted for an APN that is yet to be handed over to target RAT.
     */
    public static final int APN_PENDING_HANDOVER = 2041;

    /**
     * APN type conflict.
     */
    public static final int APN_TYPE_CONFLICT = 112;

    /**
     * Authentication failure on emergency call.
     */
    public static final int AUTH_FAILURE_ON_EMERGENCY_CALL = 122;

    /**
     * Procedure requested by the UE was rejected because the bearer handling is not supported.
     */
    public static final int BEARER_HANDLING_NOT_SUPPORTED = 60;

    /**
     * Roaming is disallowed during call bring up.
     */
    public static final int CALL_DISALLOWED_IN_ROAMING = 2068;

    /**
     * Emergency call bring up on a different ePDG.
     */
    public static final int CALL_PREEMPT_BY_EMERGENCY_APN = 127;

    /**
     * Unable to encode the OTA message for MT PDP or deactivate PDP.
     */
    public static final int CANNOT_ENCODE_OTA_MESSAGE = 2159;

    /**
     * Received an alert stop from the base station due to incoming only.
     */
    public static final int CDMA_ALERT_STOP = 2077;

    /**
     * Receiving an incoming call from the base station.
     */
    public static final int CDMA_INCOMING_CALL = 2076;

    /**
     * Received an intercept order from the base station.
     */
    public static final int CDMA_INTERCEPT = 2073;

    /**
     * Device in CDMA locked state.
     */
    public static final int CDMA_LOCK = 2072;

    /**
     * Receiving a release from the base station with a SO (Service Option) Reject reason.
     */
    public static final int CDMA_RELEASE_DUE_TO_SO_REJECTION = 2075;

    /**
     * Receiving a reorder from the base station.
     */
    public static final int CDMA_REORDER = 2074;

    /**
     * Receiving a retry order from the base station.
     */
    public static final int CDMA_RETRY_ORDER = 2086;

    /**
     * Channel acquisition failures. This indicates that device has failed acquiring all the
     * channels in the PRL.
     */
    public static final int CHANNEL_ACQUISITION_FAILURE = 2078;

    /**
     * Tearing down is in progress.
     */
    public static final int CLOSE_IN_PROGRESS = 2030;

    /**
     * Network has already initiated the activation, modification, or deactivation of bearer
     * resources that was requested by the UE.
     */
    public static final int COLLISION_WITH_NETWORK_INITIATED_REQUEST = 56;

    /**
     * Companion interface in use.
     */
    public static final int COMPANION_IFACE_IN_USE = 118;

    /**
     * The concurrent services requested were not compatible.
     */
    public static final int CONCURRENT_SERVICES_INCOMPATIBLE = 2083;

    /**
     * In favor of a voice call or SMS when concurrent voice and data are not supported.
     */
    public static final int CONCURRENT_SERVICES_NOT_ALLOWED = 2091;

    /**
     * Concurrent service is not supported by base station.
     */
    public static final int CONCURRENT_SERVICE_NOT_SUPPORTED_BY_BASE_STATION = 2080;

    /**
     * Conditional Information Element (IE) error.
     */
    public static final int CONDITIONAL_IE_ERROR = 100;

    /**
     * Network cannot serve a request from the MS due to congestion.
     */
    public static final int CONGESTION = 2106;

    /**
     * Indicate the connection was released.
     */
    public static final int CONNECTION_RELEASED = 2113;

    /**
     * CS domain is not available.
     */
    public static final int CS_DOMAIN_NOT_AVAILABLE = 2181;

    /**
     * CS fallback call establishment is not allowed.
     */
    public static final int CS_FALLBACK_CALL_ESTABLISHMENT_NOT_ALLOWED = 2188;

    /**
     * Network initiates a detach on LTE with error cause ""data plan has been replenished or has
     * expired.
     */
    public static final int DATA_PLAN_EXPIRED = 2198;

    /**
     * PDN Connection to a given APN is disallowed because data roaming is disabled from the device
     * user interface settings and the UE is roaming.
     */
    public static final int DATA_ROAMING_SETTINGS_DISABLED = 2064;

    /**
     * PDN Connection to a given APN is disallowed because data is disabled from the device user
     * interface settings.
     */
    public static final int DATA_SETTINGS_DISABLED = 2063;

    /**
     * DBM or SMS is in progress.
     */
    public static final int DBM_OR_SMS_IN_PROGRESS = 2211;

    /**
     * DDS (Default data subscription) switch occurs.
     */
    public static final int DDS_SWITCHED = 2065;

    /**
     * Default data subscription switch is in progress.
     */
    public static final int DDS_SWITCH_IN_PROGRESS = 2067;

    /**
     * Data radio bearer is released by the RRC.
     */
    public static final int DRB_RELEASED_BY_RRC = 2112;

    /**
     * Dedicated bearer will be deactivated regardless of the network response.
     */
    public static final int DS_EXPLICIT_DEACTIVATION = 2125;

    /**
     * Dual switch from single standby to dual standby is in progress.
     */
    public static final int DUAL_SWITCH = 2227;

    /**
     * Dial up networking (DUN) call bring up is rejected since UE is in eHRPD RAT.
     */
    public static final int DUN_CALL_DISALLOWED = 2056;

    /**
     * Active dedicated bearer was requested using the same default bearer ID.
     */
    public static final int DUPLICATE_BEARER_ID = 2118;

    /**
     * Device falls back from eHRPD to HRPD.
     */
    public static final int EHRPD_TO_HRPD_FALLBACK = 2049;

    /**
     * Data call has been brought down because EMBMS is not enabled at the RRC layer.
     */
    public static final int EMBMS_NOT_ENABLED = 2193;

    /**
     * EMBMS data call has been successfully brought down.
     */
    public static final int EMBMS_REGULAR_DEACTIVATION = 2195;

    /**
     * Emergency interface only.
     */
    public static final int EMERGENCY_IFACE_ONLY = 116;

    /**
     * Device is operating in Emergency mode.
     */
    public static final int EMERGENCY_MODE = 2221;

    /**
     * EPS (Evolved Packet System) Mobility Management (EMM) access barred.
     */
    public static final int EMM_ACCESS_BARRED = 115;

    /**
     * EPS (Evolved Packet System) Mobility Management (EMM) access barred infinity retry.
     */
    public static final int EMM_ACCESS_BARRED_INFINITE_RETRY = 121;

    /**
     * Attach procedure is rejected by the network.
     */
    public static final int EMM_ATTACH_FAILED = 2115;

    /**
     * Attach procedure is started for EMC purposes.
     */
    public static final int EMM_ATTACH_STARTED = 2116;

    /**
     * UE is detached.
     */
    public static final int EMM_DETACHED = 2114;

    /**
     * T3417 timer expiration of the service request procedure.
     */
    public static final int EMM_T3417_EXPIRED = 2130;

    /**
     * Extended service request fails due to expiration of the T3417 EXT timer.
     */
    public static final int EMM_T3417_EXT_EXPIRED = 2131;

    /**
     * EPS and non-EPS services are not allowed by the network.
     */
    public static final int EPS_SERVICES_AND_NON_EPS_SERVICES_NOT_ALLOWED = 2178;

    /**
     * EPS services are not allowed in the PLMN.
     */
    public static final int EPS_SERVICES_NOT_ALLOWED_IN_PLMN = 2179;

    /**
     * Data call fail due to unspecific errors.
     */
    public static final int ERROR_UNSPECIFIED = 65535;

    /**
     * Bad OTA message is received from the network.
     */
    public static final int ESM_BAD_OTA_MESSAGE = 2122;

    /**
     * Bearer must be deactivated to synchronize with the network.
     */
    public static final int ESM_BEARER_DEACTIVATED_TO_SYNC_WITH_NETWORK = 2120;

    /**
     * Collision scenarios for the UE and network-initiated procedures.
     */
    public static final int ESM_COLLISION_SCENARIOS = 2119;

    /**
     * PDN was disconnected by the downlaod server due to IRAT.
     */
    public static final int ESM_CONTEXT_TRANSFERRED_DUE_TO_IRAT = 2124;

    /**
     * Download server rejected the call.
     */
    public static final int ESM_DOWNLOAD_SERVER_REJECTED_THE_CALL = 2123;

    /**
     * ESM level failure.
     */
    public static final int ESM_FAILURE = 2182;

    /**
     * EPS Session Management (ESM) information is not received.
     */
    public static final int ESM_INFO_NOT_RECEIVED = 53;

    /**
     * No specific local cause is mentioned, usually a valid OTA cause.
     */
    public static final int ESM_LOCAL_CAUSE_NONE = 2126;

    /**
     * Active dedicated bearer was requested for an existing default bearer.
     */
    public static final int ESM_NW_ACTIVATED_DED_BEARER_WITH_ID_OF_DEF_BEARER = 2121;

    /**
     * ESM procedure maximum attempt timeout failure.
     */
    public static final int ESM_PROCEDURE_TIME_OUT = 2155;

    /**
     * Invalid EPS bearer identity in the request.
     */
    public static final int ESM_UNKNOWN_EPS_BEARER_CONTEXT = 2111;

    /**
     * Received a connection deny due to billing or authentication failure on EVDO network.
     */
    public static final int EVDO_CONNECTION_DENY_BY_BILLING_OR_AUTHENTICATION_FAILURE = 2201;

    /**
     * Received a connection deny due to general or network busy on EVDO network.
     */
    public static final int EVDO_CONNECTION_DENY_BY_GENERAL_OR_NETWORK_BUSY = 2200;

    /**
     * HDR system has been changed due to redirection or the PRL was not preferred.
     */
    public static final int EVDO_HDR_CHANGED = 2202;

    /**
     * Connection setup on the HDR system was time out.
     */
    public static final int EVDO_HDR_CONNECTION_SETUP_TIMEOUT = 2206;

    /**
     * Device exited HDR due to redirection or the PRL was not preferred.
     */
    public static final int EVDO_HDR_EXITED = 2203;

    /**
     * Device does not have an HDR session.
     */
    public static final int EVDO_HDR_NO_SESSION = 2204;

    /**
     * It is ending an HDR call origination in favor of a GPS fix.
     */
    public static final int EVDO_USING_GPS_FIX_INSTEAD_OF_HDR_CALL = 2205;

    /**
     * Device lost the system due to fade.
     */
    public static final int FADE = 2217;

    /**
     * Device failed to acquire a co-located HDR for origination.
     */
    public static final int FAILED_TO_ACQUIRE_COLOCATED_HDR = 2207;

    /**
     * Feature not supported.
     */
    public static final int FEATURE_NOT_SUPP = 40;

    /**
     * Semantic errors in packet filter.
     */
    public static final int FILTER_SEMANTIC_ERROR = 44;

    /**
     * Syntactical errors in packet filter(s).
     */
    public static final int FILTER_SYTAX_ERROR = 45;

    /**
     * PDN being brought up with an APN that is part of forbidden APN Name list.
     */
    public static final int FORBIDDEN_APN_NAME = 2066;

    /**
     * Data fail due to GPRS registration failure.
     */
    public static final int GPRS_REGISTRATION_FAIL = -2;

    /**
     * Not allowed to operate either GPRS or non-GPRS services.
     */
    public static final int GPRS_SERVICES_AND_NON_GPRS_SERVICES_NOT_ALLOWED = 2097;

    /**
     * MS is not allowed to operate GPRS services.
     */
    public static final int GPRS_SERVICES_NOT_ALLOWED = 2098;

    /**
     * UE requests GPRS service or the network initiates a detach request in a PLMN that does not
     * offer roaming for GPRS services.
     */
    public static final int GPRS_SERVICES_NOT_ALLOWED_IN_THIS_PLMN = 2103;

    /**
     * System preference change back to SRAT during handoff
     */
    public static final int HANDOFF_PREFERENCE_CHANGED = 2251;

    /**
     * HDR system access failure.
     */
    public static final int HDR_ACCESS_FAILURE = 2213;

    /**
     * HDR module released the call due to fade.
     */
    public static final int HDR_FADE = 2212;

    /**
     * HDR module could not be obtained because of the RF locked.
     */
    public static final int HDR_NO_LOCK_GRANTED = 2210;

    public static final int IFACE_AND_POL_FAMILY_MISMATCH = 120;

    /**
     * Interface mismatch.
     */
    public static final int IFACE_MISMATCH = 117;

    /**
     * ME could not be authenticated and the ME used is not acceptable to the network.
     */
    public static final int ILLEGAL_ME = 2096;

    /**
     * Network refuses service to the MS because either an identity of the MS is not acceptable to
     * the network or the MS does not pass the authentication check.
     */
    public static final int ILLEGAL_MS = 2095;

    /**
     * IMEI of the UE is not accepted by the network.
     */
    public static final int IMEI_NOT_ACCEPTED = 2177;

    /**
     * Mobile reachable timer has expired, or the GMM context data related to the subscription does
     * not exist in the SGSN.
     */
    public static final int IMPLICITLY_DETACHED = 2100;

    /**
     * IMSI present in the UE is unknown in the home subscriber server.
     */
    public static final int IMSI_UNKNOWN_IN_HOME_SUBSCRIBER_SERVER = 2176;

    /**
     * The other clients rejected incoming call.
     */
    public static final int INCOMING_CALL_REJECTED = 2092;

    /**
     * Insufficient resources.
     */
    public static final int INSUFFICIENT_RESOURCES = 26;

    /**
     * The current interface is being in use.
     */
    public static final int INTERFACE_IN_USE = 2058;

    /**
     * Internal data call preempt by high priority APN.
     */
    public static final int INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN = 114;

    /**
     * Rejected/Brought down since UE is transition between EPC and NONEPC RAT.
     */
    public static final int INTERNAL_EPC_NONEPC_TRANSITION = 2057;

    /**
     * No PDP exists with the given connection ID while modifying or deactivating or activation for
     * an already active PDP.
     */
    public static final int INVALID_CONNECTION_ID = 2156;

    /**
     * Not receiving a DNS address that was mandatory.
     */
    public static final int INVALID_DNS_ADDR = 123;

    /**
     * Invalid EMM state.
     */
    public static final int INVALID_EMM_STATE = 2190;

    /**
     * Invalid mandatory information.
     */
    public static final int INVALID_MANDATORY_INFO = 96;

    /**
     * Device operational mode is different from the mode requested in the traffic channel bring up.
     */
    public static final int INVALID_MODE = 2223;

    /**
     * Invalid Proxy-Call Session Control Function (P-CSCF) address.
     */
    public static final int INVALID_PCSCF_ADDR = 113;

    /**
     * Not receiving either a PCSCF or a DNS address, one of them being mandatory.
     */
    public static final int INVALID_PCSCF_OR_DNS_ADDRESS = 124;

    /**
     * Primary context for NSAPI does not exist.
     */
    public static final int INVALID_PRIMARY_NSAPI = 2158;

    /**
     * SIM was marked by the network as invalid for the circuit and/or packet service domain.
     */
    public static final int INVALID_SIM_STATE = 2224;

    /**
     * Invalid transaction id.
     */
    public static final int INVALID_TRANSACTION_ID = 81;

    /**
     * IPv6 address transfer failed.
     */
    public static final int IPV6_ADDRESS_TRANSFER_FAILED = 2047;

    /**
     * Device failure to obtain the prefix from the network.
     */
    public static final int IPV6_PREFIX_UNAVAILABLE = 2250;

    /**
     * IP address mismatch.
     */
    public static final int IP_ADDRESS_MISMATCH = 119;

    /**
     * New call bring up is rejected since the existing data call IP type doesn't match the
     * requested IP.
     */
    public static final int IP_VERSION_MISMATCH = 2055;

    /**
     * Data call was unsuccessfully transferred during the IRAT handover.
     */
    public static final int IRAT_HANDOVER_FAILED = 2194;

    /**
     * Maximum access probes for the IS-707B call.
     */
    public static final int IS707B_MAX_ACCESS_PROBES = 2089;

    /**
     * The user is barred from using the non-3GPP access or the subscribed APN.
     */
    public static final int IWLAN_AUTHORIZATION_REJECTED = 9003;

    /**
     * The requested service was rejected because of congestion in the network while accessing the
     * IWLAN ePDG. Defined in 3GPP TS 24.502, Section 9.2.4.2.
     */
    public static final int IWLAN_CONGESTION = 15500;

    /**
     * Unable to resolve FQDN for the ePDG to an IP address
     */
    public static final int IWLAN_DNS_RESOLUTION_NAME_FAILURE = 16388;

    /**
     * No response received from the DNS Server due to a timeout
     */
    public static final int IWLAN_DNS_RESOLUTION_TIMEOUT = 16389;

    /**
     * Sent in the response to an IKE_AUTH message when, for some reason,
     * the authentication failed.
     */
    public static final int IWLAN_IKEV2_AUTH_FAILURE = 16385;

    /**
     * IKE Certification validation failure
     */
    public static final int IWLAN_IKEV2_CERT_INVALID = 16387;

    /**
     * IKE configuration error resulting in failure
     */
    public static final int IWLAN_IKEV2_CONFIG_FAILURE = 16384;

    /**
     * IKE message timeout, tunnel setup failed due to no response from EPDG
     */
    public static final int IWLAN_IKEV2_MSG_TIMEOUT = 16386;

    /**
     * The Mobile Equipment used is not acceptable to the network
     */
    public static final int IWLAN_ILLEGAL_ME = 9006;

    /**
     * The network does not accept emergency PDN bringup request using an IMEI
     */
    public static final int IWLAN_IMEI_NOT_ACCEPTED = 11005;

    /**
     * The PDN connection has been rejected. No additional PDN connections can be established
     * for the UE due to the network policies or capabilities.
     */
    public static final int IWLAN_MAX_CONNECTION_REACHED = 8193;

    /**
     * The network has determined that the requested procedure cannot be completed successfully
     * due to network failure.
     */
    public static final int IWLAN_NETWORK_FAILURE = 10500;

    /**
     * No non-3GPP subscription is associated with the IMSI.
     * The UE is not allowed to use non-3GPP access to EPC.
     */
    public static final int IWLAN_NON_3GPP_ACCESS_TO_EPC_NOT_ALLOWED = 9000;

    /**
     * The requested APN is not included in the user's profile,
     * and therefore is not authorized for that user.
     */
    public static final int IWLAN_NO_APN_SUBSCRIPTION = 9002;

    /**
     * The PDN connection corresponding to the requested APN has been rejected.
     */
    public static final int IWLAN_PDN_CONNECTION_REJECTION = 8192;

    /**
     * The ePDG performs PLMN filtering (based on roaming agreements) and rejects
     * the request from the UE.
     * The UE requests service in a PLMN where the UE is not allowed to operate.
     */
    public static final int IWLAN_PLMN_NOT_ALLOWED = 11011;

    /**
     * The access type is restricted to the user.
     */
    public static final int IWLAN_RAT_TYPE_NOT_ALLOWED = 11001;

    /**
     * The PDN connection has been rejected due to sematic errors in the packet filter.
     */
    public static final int IWLAN_SEMANTIC_ERRORS_IN_PACKET_FILTERS = 8244;

    /**
     * The PDN connection has been rejected due to a semantic error in TFT operation.
     */
    public static final int IWLAN_SEMANTIC_ERROR_IN_THE_TFT_OPERATION = 8241;

    /**
     * The PDN connection has been rejected due to syntactic errors in the packet filter.
     */
    public static final int IWLAN_SYNTACTICAL_ERRORS_IN_PACKET_FILTERS = 8245;

    /**
     * The PDN connection has been rejected due to a syntactic error in TFT operation.
     */
    public static final int IWLAN_SYNTACTICAL_ERROR_IN_THE_TFT_OPERATION = 8242;

    /**
     * Expected to update or bring down an ePDG tunnel, but no tunnel found
     */
    public static final int IWLAN_TUNNEL_NOT_FOUND = 16390;

    /**
     * The ePDG does not support un-authenticated IMSI based emergency PDN bringup
     */
    public static final int IWLAN_UNAUTHENTICATED_EMERGENCY_NOT_SUPPORTED = 11055;

    /**
     * The user identified by the IMSI is unknown.
     */
    public static final int IWLAN_USER_UNKNOWN = 9001;

    /**
     * IPv6 interface bring up fails because the network provided only the IPv4 address for the
     * upcoming PDN permanent client can reattempt a IPv6 call bring up after the IPv4 interface is
     * also brought down. However, there is no guarantee that the network will provide a IPv6
     * address.
     */
    public static final int LIMITED_TO_IPV4 = 2234;

    /**
     * IPv4 interface bring up fails because the network provided only the IPv6 address for the
     * upcoming PDN permanent client can reattempt a IPv4 call bring up after the IPv6 interface is
     * also brought down. However there is no guarantee that the network will provide a IPv4
     * address.
     */
    public static final int LIMITED_TO_IPV6 = 2235;

    /**
     * Logical Link Control (LLC) Sub Network Dependent Convergence Protocol (SNDCP).
     */
    public static final int LLC_SNDCP = 25;

    /**
     * Client ended the data call.
     */
    public static final int LOCAL_END = 2215;

    /**
     * MS requests service, or the network initiates a detach request, in a location area where the
     * HPLMN determines that the MS, by subscription, is not allowed to operate.
     */
    public static final int LOCATION_AREA_NOT_ALLOWED = 2102;

    /**
     * Data connection was lost.
     */
    public static final int LOST_CONNECTION = 65540;

    /**
     * Lower layer registration failure.
     */
    public static final int LOWER_LAYER_REGISTRATION_FAILURE = 2197;

    /**
     * Device is going into lower power mode or powering down.
     */
    public static final int LOW_POWER_MODE_OR_POWERING_DOWN = 2044;

    /**
     * Service request procedure failure.
     */
    public static final int LTE_NAS_SERVICE_REQUEST_FAILED = 2117;

    /**
     * Throttling is not needed for this service request failure.
     */
    public static final int LTE_THROTTLING_NOT_REQUIRED = 2127;

    /**
     * MAC level failure.
     */
    public static final int MAC_FAILURE = 2183;

    /**
     * No matching rule available for the request, and match-all rule is not allowed for it.
     */
    public static final int MATCH_ALL_RULE_NOT_ALLOWED = 2253;

    /**
     * Maximum NSAPIs have been exceeded during PDP activation.
     */
    public static final int MAXIMIUM_NSAPIS_EXCEEDED = 2157;

    /**
     * Maximum size of the L2 message was exceeded.
     */
    public static final int MAXINUM_SIZE_OF_L2_MESSAGE_EXCEEDED = 2166;

    /**
     * Maximum access probes transmitted.
     */
    public static final int MAX_ACCESS_PROBE = 2079;

    /**
     * IPv4 data call bring up is rejected because the UE already maintains the allotted maximum
     * number of IPv4 data connections.
     */
    public static final int MAX_IPV4_CONNECTIONS = 2052;

    /**
     * IPv6 data call bring up is rejected because the UE already maintains the allotted maximum
     * number of IPv6 data connections.
     */
    public static final int MAX_IPV6_CONNECTIONS = 2053;

    /**
     * Maximum PPP inactivity timer expired.
     */
    public static final int MAX_PPP_INACTIVITY_TIMER_EXPIRED = 2046;

    /**
     * Incorrect message semantic.
     */
    public static final int MESSAGE_INCORRECT_SEMANTIC = 95;

    /**
     * Unsupported message type.
     */
    public static final int MESSAGE_TYPE_UNSUPPORTED = 97;

    /**
     * UE is in MIP-only configuration but the MIP configuration fails on call bring up due to
     * incorrect provisioning.
     */
    public static final int MIP_CONFIG_FAILURE = 2050;

    /**
     * Foreign agent administratively prohibited MIP (Mobile IP) registration.
     */
    public static final int MIP_FA_ADMIN_PROHIBITED = 2001;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of delivery style was not
     * supported.
     */
    public static final int MIP_FA_DELIVERY_STYLE_NOT_SUPPORTED = 2012;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of requested encapsulation was
     * unavailable.
     */
    public static final int MIP_FA_ENCAPSULATION_UNAVAILABLE = 2008;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of home agent authentication
     * failure.
     */
    public static final int MIP_FA_HOME_AGENT_AUTHENTICATION_FAILURE = 2004;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of insufficient resources.
     */
    public static final int MIP_FA_INSUFFICIENT_RESOURCES = 2002;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of malformed reply.
     */
    public static final int MIP_FA_MALFORMED_REPLY = 2007;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of malformed request.
     */
    public static final int MIP_FA_MALFORMED_REQUEST = 2006;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of missing challenge.
     */
    public static final int MIP_FA_MISSING_CHALLENGE = 2017;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of missing Home Address.
     */
    public static final int MIP_FA_MISSING_HOME_ADDRESS = 2015;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of missing Home Agent.
     */
    public static final int MIP_FA_MISSING_HOME_AGENT = 2014;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of missing NAI (Network Access
     * Identifier).
     */
    public static final int MIP_FA_MISSING_NAI = 2013;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of MN-AAA authenticator was
     * wrong.
     */
    public static final int MIP_FA_MOBILE_NODE_AUTHENTICATION_FAILURE = 2003;

    /**
     * Reason unspecified for foreign agent rejected MIP (Mobile IP) registration.
     */
    public static final int MIP_FA_REASON_UNSPECIFIED = 2000;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of requested lifetime was too
     * long.
     */
    public static final int MIP_FA_REQUESTED_LIFETIME_TOO_LONG = 2005;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of reverse tunnel was mandatory
     * but not requested by device.
     */
    public static final int MIP_FA_REVERSE_TUNNEL_IS_MANDATORY = 2011;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of reverse tunnel was
     * unavailable.
     */
    public static final int MIP_FA_REVERSE_TUNNEL_UNAVAILABLE = 2010;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of stale challenge.
     */
    public static final int MIP_FA_STALE_CHALLENGE = 2018;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration because of unknown challenge.
     */
    public static final int MIP_FA_UNKNOWN_CHALLENGE = 2016;

    /**
     * Foreign agent rejected MIP (Mobile IP) registration of VJ Header Compression was
     * unavailable.
     */
    public static final int MIP_FA_VJ_HEADER_COMPRESSION_UNAVAILABLE = 2009;

    /**
     * Home agent administratively prohibited MIP (Mobile IP) registration.
     */
    public static final int MIP_HA_ADMIN_PROHIBITED = 2020;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of encapsulation unavailable.
     */
    public static final int MIP_HA_ENCAPSULATION_UNAVAILABLE = 2029;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of foreign agent authentication
     * failure.
     */
    public static final int MIP_HA_FOREIGN_AGENT_AUTHENTICATION_FAILURE = 2023;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of insufficient resources.
     */
    public static final int MIP_HA_INSUFFICIENT_RESOURCES = 2021;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of malformed request.
     */
    public static final int MIP_HA_MALFORMED_REQUEST = 2025;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of MN-HA authenticator was
     * wrong.
     */
    public static final int MIP_HA_MOBILE_NODE_AUTHENTICATION_FAILURE = 2022;

    /**
     * Reason unspecified for home agent rejected MIP (Mobile IP) registration.
     */
    public static final int MIP_HA_REASON_UNSPECIFIED = 2019;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of registration id mismatch.
     */
    public static final int MIP_HA_REGISTRATION_ID_MISMATCH = 2024;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of reverse tunnel is mandatory but
     * not requested by device.
     */
    public static final int MIP_HA_REVERSE_TUNNEL_IS_MANDATORY = 2028;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of reverse tunnel was
     * unavailable.
     */
    public static final int MIP_HA_REVERSE_TUNNEL_UNAVAILABLE = 2027;

    /**
     * Home agent rejected MIP (Mobile IP) registration because of unknown home agent address.
     */
    public static final int MIP_HA_UNKNOWN_HOME_AGENT_ADDRESS = 2026;

    /**
     * Missing or unknown APN.
     */
    public static final int MISSING_UNKNOWN_APN = 27;

    /**
     * Another application in modem preempts the data call.
     */
    public static final int MODEM_APP_PREEMPTED = 2032;

    /**
     * Modem restart.
     */
    public static final int MODEM_RESTART = 2037;

    /**
     * Mobile switching center is temporarily unreachable.
     */
    public static final int MSC_TEMPORARILY_NOT_REACHABLE = 2180;

    /**
     * Message and protocol state uncompatible.
     */
    public static final int MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE = 101;

    /**
     * Message type uncompatible.
     */
    public static final int MSG_TYPE_NONCOMPATIBLE_STATE = 98;

    /**
     * No matching identity or context could be found in the network.
     */
    public static final int MS_IDENTITY_CANNOT_BE_DERIVED_BY_THE_NETWORK = 2099;

    /**
     * Multiple PDP call feature is disabled.
     */
    public static final int MULTIPLE_PDP_CALL_NOT_ALLOWED = 2192;

    /**
     * Multiple connections to a same PDN is not allowed.
     */
    public static final int MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED = 55;

    /**
     * Non-Access Spectrum layer failure.
     */
    public static final int NAS_LAYER_FAILURE = 2191;

    /**
     * Non-access stratum (NAS) request was rejected by the network.
     */
    public static final int NAS_REQUEST_REJECTED_BY_NETWORK = 2167;

    /**
     * NAS signalling.
     */
    public static final int NAS_SIGNALLING = 14;

    /**
     * Network Failure.
     */
    public static final int NETWORK_FAILURE = 38;

    /**
     * Network-initiated detach without reattach.
     */
    public static final int NETWORK_INITIATED_DETACH_NO_AUTO_REATTACH = 2154;

    /**
     * Network-initiated detach with reattach.
     */
    public static final int NETWORK_INITIATED_DETACH_WITH_AUTO_REATTACH = 2153;

    /**
     * Brought down by the network.
     */
    public static final int NETWORK_INITIATED_TERMINATION = 2031;

    /**
     * There is no failure
     */
    public static final int NONE = 0;

    /**
     * UE is unable to bring up a non-IP data call because the device is not camped on a NB1 cell.
     */
    public static final int NON_IP_NOT_SUPPORTED = 2069;

    /**
     * Receiving a release from the base station with no reason.
     */
    public static final int NORMAL_RELEASE = 2218;

    /**
     * Device does not have CDMA service.
     */
    public static final int NO_CDMA_SERVICE = 2084;

    /**
     * There is no co-located HDR.
     */
    public static final int NO_COLLOCATED_HDR = 2225;

    /**
     * No EPS bearer context was activated.
     */
    public static final int NO_EPS_BEARER_CONTEXT_ACTIVATED = 2189;

    /**
     * GPRS context is not available.
     */
    public static final int NO_GPRS_CONTEXT = 2094;

    /**
     * Device has no hybrid HDR service.
     */
    public static final int NO_HYBRID_HDR_SERVICE = 2209;

    /**
     * MS requests an establishment of the radio access bearers for all active PDP contexts by
     * sending a service request message indicating data to the network, but the SGSN does not have
     * any active PDP context.
     */
    public static final int NO_PDP_CONTEXT_ACTIVATED = 2107;

    /**
     * There was no response received from the base station.
     */
    public static final int NO_RESPONSE_FROM_BASE_STATION = 2081;

    /**
     * Device has no service.
     */
    public static final int NO_SERVICE = 2216;

    /**
     * No service on the gateway.
     */
    public static final int NO_SERVICE_ON_GATEWAY = 2093;

    /**
     * The Network Service Access Point Identifier (NSAPI) is in use.
     */
    public static final int NSAPI_IN_USE = 35;

    /**
     * PDN is attempted to be brought up with NULL APN but NULL APN is not supported.
     */
    public static final int NULL_APN_DISALLOWED = 2061;

    public static final int OEM_DCFAILCAUSE_1 = 4097;

    public static final int OEM_DCFAILCAUSE_10 = 4106;

    public static final int OEM_DCFAILCAUSE_11 = 4107;

    public static final int OEM_DCFAILCAUSE_12 = 4108;

    public static final int OEM_DCFAILCAUSE_13 = 4109;

    public static final int OEM_DCFAILCAUSE_14 = 4110;

    public static final int OEM_DCFAILCAUSE_15 = 4111;

    public static final int OEM_DCFAILCAUSE_2 = 4098;

    public static final int OEM_DCFAILCAUSE_3 = 4099;

    public static final int OEM_DCFAILCAUSE_4 = 4100;

    public static final int OEM_DCFAILCAUSE_5 = 4101;

    public static final int OEM_DCFAILCAUSE_6 = 4102;

    public static final int OEM_DCFAILCAUSE_7 = 4103;

    public static final int OEM_DCFAILCAUSE_8 = 4104;

    public static final int OEM_DCFAILCAUSE_9 = 4105;

    /**
     * Network supports IPv4v6 PDP type only. Non-IP type is not allowed. In LTE mode of operation,
     * this is a PDN throttling cause code, meaning the UE may throttle further requests to the
     * same APN.
     */
    public static final int ONLY_IPV4V6_ALLOWED = 57;

    /**
     * Packet Data Protocol (PDP) type IPv4 only allowed.
     */
    public static final int ONLY_IPV4_ALLOWED = 50;

    /**
     * Packet Data Protocol (PDP) type IPv6 only allowed.
     */
    public static final int ONLY_IPV6_ALLOWED = 51;

    /**
     * Network supports non-IP PDP type only. IPv4, IPv6 and IPv4v6 is not allowed. In LTE mode of
     * operation, this is a PDN throttling cause code, meaning the UE can throttle further requests
     * to the same APN.
     */
    public static final int ONLY_NON_IP_ALLOWED = 58;

    /**
     * Single address bearers only allowed.
     */
    public static final int ONLY_SINGLE_BEARER_ALLOWED = 52;

    /**
     * Operator determined barring. (no retry)
     */
    public static final int OPERATOR_BARRED = 8;

    /**
     * OTASP commit is in progress.
     */
    public static final int OTASP_COMMIT_IN_PROGRESS = 2208;

    /**
     * PDN connection does not exist.
     */
    public static final int PDN_CONN_DOES_NOT_EXIST = 54;

    /**
     * PDN inactivity timer expired due to no data transmission in a configurable duration of time.
     */
    public static final int PDN_INACTIVITY_TIMER_EXPIRED = 2051;

    /**
     * IPV4 PDN is in throttled state due to network providing only IPV6 address during the
     * previous VSNCP bringup (subs_limited_to_v6).
     */
    public static final int PDN_IPV4_CALL_DISALLOWED = 2033;

    /**
     * IPV4 PDN is in throttled state due to previous VSNCP bringup failure(s).
     */
    public static final int PDN_IPV4_CALL_THROTTLED = 2034;

    /**
     * IPV6 PDN is in throttled state due to network providing only IPV4 address during the
     * previous VSNCP bringup (subs_limited_to_v4).
     */
    public static final int PDN_IPV6_CALL_DISALLOWED = 2035;

    /**
     * IPV6 PDN is in throttled state due to previous VSNCP bringup failure(s).
     */
    public static final int PDN_IPV6_CALL_THROTTLED = 2036;

    /**
     * Non-IP PDN is in disallowed state due to the network providing only an IP address.
     */
    public static final int PDN_NON_IP_CALL_DISALLOWED = 2071;

    /**
     * Non-IP PDN is in throttled state due to previous VSNCP bringup failure(s).
     */
    public static final int PDN_NON_IP_CALL_THROTTLED = 2070;

    /**
     * SM attempts PDP activation for a maximum of four attempts.
     */
    public static final int PDP_ACTIVATE_MAX_RETRY_FAILED = 2109;

    /**
     * PDP context already exists.
     */
    public static final int PDP_DUPLICATE = 2104;

    /**
     * Expiration of the PDP establish timer with a maximum of five retries.
     */
    public static final int PDP_ESTABLISH_TIMEOUT_EXPIRED = 2161;

    /**
     * Expiration of the PDP deactivate timer with a maximum of four retries.
     */
    public static final int PDP_INACTIVE_TIMEOUT_EXPIRED = 2163;

    /**
     * PDP activation failed due to RRC_ABORT or a forbidden PLMN.
     */
    public static final int PDP_LOWERLAYER_ERROR = 2164;

    /**
     * MO PDP modify collision when the MT PDP is already in progress.
     */
    public static final int PDP_MODIFY_COLLISION = 2165;

    /**
     * Expiration of the PDP modify timer with a maximum of four retries.
     */
    public static final int PDP_MODIFY_TIMEOUT_EXPIRED = 2162;

    /**
     * PDP PPP calls are not supported.
     */
    public static final int PDP_PPP_NOT_SUPPORTED = 2038;

    /**
     * Packet Data Protocol (PDP) without active traffic flow template (TFT).
     */
    public static final int PDP_WITHOUT_ACTIVE_TFT = 46;

    /**
     * Device is in use (e.g., voice call).
     */
    public static final int PHONE_IN_USE = 2222;

    /**
     * Physical link is in the process of cleanup.
     */
    public static final int PHYSICAL_LINK_CLOSE_IN_PROGRESS = 2040;

    /**
     * UE requests GPRS service, or the network initiates a detach request in a PLMN which does not
     * offer roaming for GPRS services to that MS.
     */
    public static final int PLMN_NOT_ALLOWED = 2101;

    /**
     * Data call bring up fails in the PPP setup due to an authorization failure.
     * (e.g., authorization is required, but not negotiated with the network during an LCP phase)
     */
    public static final int PPP_AUTH_FAILURE = 2229;

    /**
     * Data call bring up fails in the PPP setup due to a CHAP failure.
     */
    public static final int PPP_CHAP_FAILURE = 2232;

    /**
     * Data call bring up fails in the PPP setup because the PPP is in the process of cleaning the
     * previous PPP session.
     */
    public static final int PPP_CLOSE_IN_PROGRESS = 2233;

    /**
     * Data call bring up fails in the PPP setup due to an option mismatch.
     */
    public static final int PPP_OPTION_MISMATCH = 2230;

    /**
     * Data call bring up fails in the PPP setup due to a PAP failure.
     */
    public static final int PPP_PAP_FAILURE = 2231;

    /**
     * Data call bring up fails in the PPP setup due to a timeout.
     * (e.g., an LCP conf ack was not received from the network)
     */
    public static final int PPP_TIMEOUT = 2228;

    /**
     * Preferred technology has changed, must retry with parameters appropriate for new technology.
     */
    public static final int PREF_RADIO_TECH_CHANGED = -4;

    /**
     * APN bearer type in the profile does not match preferred network mode.
     */
    public static final int PROFILE_BEARER_INCOMPATIBLE = 2042;

    /**
     * Protocol errors.
     */
    public static final int PROTOCOL_ERRORS = 111;

    /**
     * Quality of service (QoS) is not accepted.
     */
    public static final int QOS_NOT_ACCEPTED = 37;

    /**
     * Radio access bearer failure.
     */
    public static final int RADIO_ACCESS_BEARER_FAILURE = 2110;

    /**
     * Radio access bearer is not established by the lower layers during activation, modification,
     * or deactivation.
     */
    public static final int RADIO_ACCESS_BEARER_SETUP_FAILURE = 2160;

    /**
     * Data fail due to radio not unavailable.
     */
    public static final int RADIO_NOT_AVAILABLE = 65537;

    /**
     * data call was disconnected because radio was resetting, powered off.
     */
    public static final int RADIO_POWER_OFF = -5;

    /**
     * Device is in the process of redirecting or handing off to a different target system.
     */
    public static final int REDIRECTION_OR_HANDOFF_IN_PROGRESS = 2220;

    /**
     * Data fail due to registration failure.
     */
    public static final int REGISTRATION_FAIL = -1;

    /**
     * Regular deactivation.
     */
    public static final int REGULAR_DEACTIVATION = 36;

    /**
     * The base station rejecting the call.
     */
    public static final int REJECTED_BY_BASE_STATION = 2082;

    /**
     * Radio resource control (RRC) connection was aborted by the non-access stratum (NAS) after an
     * IRAT to LTE IRAT handover.
     */
    public static final int RRC_CONNECTION_ABORTED_AFTER_HANDOVER = 2173;

    /**
     * Radio resource control (RRC) connection was aborted before deactivating the LTE stack after
     * a successful LTE to GSM/EDGE IRAT cell change order procedure.
     */
    public static final int RRC_CONNECTION_ABORTED_AFTER_IRAT_CELL_CHANGE = 2174;

    /**
     * Radio resource control (RRC) connection was aborted before deactivating the LTE stack due to
     * a successful LTE to WCDMA/GSM/TD-SCDMA IRAT change.
     */
    public static final int RRC_CONNECTION_ABORTED_DUE_TO_IRAT_CHANGE = 2171;

    /**
     * Radio resource control (RRC) connection was aborted in the middle of a LTE to GSM IRAT cell
     * change order procedure.
     */
    public static final int RRC_CONNECTION_ABORTED_DURING_IRAT_CELL_CHANGE = 2175;

    /**
     * Connection has been released by the radio resource control (RRC) due to an abort request.
     */
    public static final int RRC_CONNECTION_ABORT_REQUEST = 2151;

    /**
     * Radio resource control (RRC) connection establishment failed due to access barrred.
     */
    public static final int RRC_CONNECTION_ACCESS_BARRED = 2139;

    /**
     * Radio resource control (RRC) connection failure at access stratum.
     */
    public static final int RRC_CONNECTION_ACCESS_STRATUM_FAILURE = 2137;

    /**
     * Radio resource control (RRC) connection establishment is aborted due to another procedure.
     */
    public static final int RRC_CONNECTION_ANOTHER_PROCEDURE_IN_PROGRESS = 2138;

    /**
     * Connection establishment failed as the radio resource control (RRC) is not camped on any
     * cell.
     */
    public static final int RRC_CONNECTION_CELL_NOT_CAMPED = 2144;

    /**
     * Radio resource control (RRC) connection establishment failed due to cell reselection at
     * access stratum.
     */
    public static final int RRC_CONNECTION_CELL_RESELECTION = 2140;

    /**
     * Connection establishment failed due to configuration failure at the radio resource control
     * (RRC).
     */
    public static final int RRC_CONNECTION_CONFIG_FAILURE = 2141;

    /**
     * Radio resource control (RRC) connection establishment failure due to an error in the request
     * message.
     */
    public static final int RRC_CONNECTION_INVALID_REQUEST = 2168;

    /**
     * Connection establishment failed due to a link failure at the radio resource control (RRC).
     */
    public static final int RRC_CONNECTION_LINK_FAILURE = 2143;

    /**
     * Normal radio resource control (RRC) connection release.
     */
    public static final int RRC_CONNECTION_NORMAL_RELEASE = 2147;

    /**
     * UE is out of service during the call register.
     */
    public static final int RRC_CONNECTION_OUT_OF_SERVICE_DURING_CELL_REGISTER = 2150;

    /**
     * Radio resource control (RRC) connection release failed due to radio link failure conditions.
     */
    public static final int RRC_CONNECTION_RADIO_LINK_FAILURE = 2148;

    /**
     * Radio resource control (RRC) connection re-establishment failure.
     */
    public static final int RRC_CONNECTION_REESTABLISHMENT_FAILURE = 2149;

    /**
     * Radio resource control (RRC) connection establishment failed due to the network rejecting
     * the UE connection request.
     */
    public static final int RRC_CONNECTION_REJECT_BY_NETWORK = 2146;

    /**
     * If the UE has an LTE radio link failure before security is established, the radio resource
     * control (RRC) connection must be released and the UE must return to idle.
     */
    public static final int RRC_CONNECTION_RELEASED_SECURITY_NOT_ACTIVE = 2172;

    /**
     * Radio resource control (RRC) connection establishment failure due to the RF was unavailable.
     */
    public static final int RRC_CONNECTION_RF_UNAVAILABLE = 2170;

    /**
     * Radio resource control (RRC) connection released due to a system information block read
     * error.
     */
    public static final int RRC_CONNECTION_SYSTEM_INFORMATION_BLOCK_READ_ERROR = 2152;

    /**
     * Connection establishment failed due to a service interval failure at the radio resource
     * control (RRC).
     */
    public static final int RRC_CONNECTION_SYSTEM_INTERVAL_FAILURE = 2145;

    /**
     * Radio resource control (RRC) connection could not be established in the time limit.
     */
    public static final int RRC_CONNECTION_TIMER_EXPIRED = 2142;

    /**
     * Radio resource control (RRC) connection establishment failure due to a change in the
     * tracking area ID.
     */
    public static final int RRC_CONNECTION_TRACKING_AREA_ID_CHANGED = 2169;

    /**
     * Radio resource control (RRC) uplink data delivery failed due to a connection release.
     */
    public static final int RRC_UPLINK_CONNECTION_RELEASE = 2134;

    /**
     * Transmission failure of radio resource control (RRC) uplink data.
     */
    public static final int RRC_UPLINK_DATA_TRANSMISSION_FAILURE = 2132;

    /**
     * Radio resource control (RRC) uplink data delivery failed due to a handover.
     */
    public static final int RRC_UPLINK_DELIVERY_FAILED_DUE_TO_HANDOVER = 2133;

    /**
     * Radio resource control (RRC) is not connected but the non-access stratum (NAS) sends an
     * uplink data request.
     */
    public static final int RRC_UPLINK_ERROR_REQUEST_FROM_NAS = 2136;

    /**
     * Radio resource control (RRC) uplink data delivery failed due to a radio link failure.
     */
    public static final int RRC_UPLINK_RADIO_LINK_FAILURE = 2135;

    /**
     * RUIM not being present.
     */
    public static final int RUIM_NOT_PRESENT = 2085;

    /**
     * Unspecified security mode reject.
     */
    public static final int SECURITY_MODE_REJECTED = 2186;

    /**
     * Service is not allowed on the requested PLMN.
     */
    public static final int SERVICE_NOT_ALLOWED_ON_PLMN = 2129;

    /**
     * Requested service option not subscribed.
     */
    public static final int SERVICE_OPTION_NOT_SUBSCRIBED = 33;

    /**
     * Service option not supported.
     */
    public static final int SERVICE_OPTION_NOT_SUPPORTED = 32;

    /**
     * Service option temporarily out of order.
     */
    public static final int SERVICE_OPTION_OUT_OF_ORDER = 34;

    /**
     * Data call drop due to network/modem disconnect.
     */
    public static final int SIGNAL_LOST = -3;

    /**
     * Card was refreshed or removed.
     */
    public static final int SIM_CARD_CHANGED = 2043;

    /**
     * Data call fail due to the slice not being allowed for the data call.
     */
    public static final int SLICE_REJECTED = 2252;

    /**
     * Synchronization failure.
     */
    public static final int SYNCHRONIZATION_FAILURE = 2184;

    /**
     * Test loop-back data call has been successfully brought down.
     */
    public static final int TEST_LOOPBACK_REGULAR_DEACTIVATION = 2196;

    /**
     * Data call was disconnected by modem because tethered.
     */
    public static final int TETHERED_CALL_ACTIVE = -6;

    /**
     * Semantic error in the Traffic flow templates (TFT) operation.
     */
    public static final int TFT_SEMANTIC_ERROR = 41;

    /**
     * Syntactical error in the Traffic flow templates (TFT) operation.
     */
    public static final int TFT_SYTAX_ERROR = 42;

    /**
     * Put device in thermal emergency.
     */
    public static final int THERMAL_EMERGENCY = 2090;

    /**
     * Thermal level increases and causes calls to be torn down when normal mode of operation is
     * not allowed.
     */
    public static final int THERMAL_MITIGATION = 2062;

    /**
     * Target RAT swap failed.
     */
    public static final int TRAT_SWAP_FAILED = 2048;

    /**
     * UE performs a detach or disconnect PDN action based on TE requirements.
     */
    public static final int UE_INITIATED_DETACH_OR_DISCONNECT = 128;

    /**
     * UE is entering power save mode.
     */
    public static final int UE_IS_ENTERING_POWERSAVE_MODE = 2226;

    /**
     * RAT change on the UE.
     */
    public static final int UE_RAT_CHANGE = 2105;

    /**
     * UE security capabilities mismatch.
     */
    public static final int UE_SECURITY_CAPABILITIES_MISMATCH = 2185;

    /**
     * UMTS interface is brought down due to handover from UMTS to iWLAN.
     */
    public static final int UMTS_HANDOVER_TO_IWLAN = 2199;

    /**
     * Universal Mobile Telecommunications System (UMTS) reactivation request.
     */
    public static final int UMTS_REACTIVATION_REQ = 39;

    /**
     * Data fail due to unacceptable network parameter.
     */
    public static final int UNACCEPTABLE_NETWORK_PARAMETER = 65538;

    /**
     * Unacceptable non-EPS authentication.
     */
    public static final int UNACCEPTABLE_NON_EPS_AUTHENTICATION = 2187;

    /**
     * Unknown data failure cause.
     */
    public static final int UNKNOWN = 65536;

    /**
     * Unknown info element.
     */
    public static final int UNKNOWN_INFO_ELEMENT = 99;

    /**
     * Unknown Packet Data Protocol (PDP) address type.
     */
    public static final int UNKNOWN_PDP_ADDRESS_TYPE = 28;

    /**
     * Unknown Packet Data Protocol (PDP) context.
     */
    public static final int UNKNOWN_PDP_CONTEXT = 43;

    /**
     * RAT on which the data call is attempted/connected is no longer the preferred RAT.
     */
    public static final int UNPREFERRED_RAT = 2039;

    /**
     * P_rev supported by 1 base station is less than 6, which is not supported for a 1X data call.
     * The UE must be in the footprint of BS which has p_rev >= 6 to support this SO33 call.
     */
    public static final int UNSUPPORTED_1X_PREV = 2214;

    /**
     * Unsupported APN in current public land mobile network (PLMN).
     */
    public static final int UNSUPPORTED_APN_IN_CURRENT_PLMN = 66;

    /**
     * QCI (QoS Class Identifier) indicated in the UE request cannot be supported.
     */
    public static final int UNSUPPORTED_QCI_VALUE = 59;

    /**
     * User authentication.
     */
    public static final int USER_AUTHENTICATION = 29;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request with the reason of administratively prohibited at the HSGW.
     */
    public static final int VSNCP_ADMINISTRATIVELY_PROHIBITED = 2245;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request because the requested APN is unauthorized.
     */
    public static final int VSNCP_APN_UNAUTHORIZED = 2238;

    /**
     * Data call bring up fails in the VSNCP phase due to a general error. It's used when there is
     * no other specific error code available to report the failure.
     */
    public static final int VSNCP_GEN_ERROR = 2237;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request with the reason of insufficient parameter.
     */
    public static final int VSNCP_INSUFFICIENT_PARAMETERS = 2243;

    /**
     * Data call bring up fails in the VSNCP phase due to the network rejected the VSNCP
     * configuration request due to no PDN gateway address.
     */
    public static final int VSNCP_NO_PDN_GATEWAY_ADDRESS = 2240;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request because the PDN exists for this APN.
     */
    public static final int VSNCP_PDN_EXISTS_FOR_THIS_APN = 2248;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request due to a PDN gateway reject.
     */
    public static final int VSNCP_PDN_GATEWAY_REJECT = 2242;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request because the PDN gateway is unreachable.
     */
    public static final int VSNCP_PDN_GATEWAY_UNREACHABLE = 2241;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of PDN ID in use, or
     * all existing PDNs are brought down with this end reason because one of the PDN bring up was
     * rejected by the network with the reason of PDN ID in use.
     */
    public static final int VSNCP_PDN_ID_IN_USE = 2246;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request because the PDN limit has been exceeded.
     */
    public static final int VSNCP_PDN_LIMIT_EXCEEDED = 2239;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request with reconnect to this PDN not allowed, or an active data call is
     * terminated by the network because reconnection to this PDN is not allowed. Upon receiving
     * this error code from the network, the modem infinitely throttles the PDN until the next
     * power cycle.
     */
    public static final int VSNCP_RECONNECT_NOT_ALLOWED = 2249;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request with the reason of resource unavailable.
     */
    public static final int VSNCP_RESOURCE_UNAVAILABLE = 2244;

    /**
     * Data call bring up fails in the VSNCP phase due to a network rejection of the VSNCP
     * configuration request for the reason of subscriber limitation.
     */
    public static final int VSNCP_SUBSCRIBER_LIMITATION = 2247;

    /**
     * Data call bring up fails in the VSNCP phase due to a VSNCP timeout error.
     */
    public static final int VSNCP_TIMEOUT = 2236;

    private DataFailCause() {
    }
}
