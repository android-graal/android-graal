package android.telephony;

/**
 * Provides access to telephony configuration values that are carrier-specific.
 */
public final class CarrierConfigManager {
    /**
     * This intent is broadcast by the system when carrier config changes. An int is specified in
     * {@link #EXTRA_SLOT_INDEX} to indicate the slot index that this is for. An optional int extra
     * {@link #EXTRA_SUBSCRIPTION_INDEX} is included to indicate the subscription index if a valid
     * one is available for the slot index. An optional int extra
     * {@link TelephonyManager#EXTRA_CARRIER_ID} is included to indicate the carrier id for the
     * changed carrier configuration. An optional int extra
     * {@link TelephonyManager#EXTRA_SPECIFIC_CARRIER_ID} is included to indicate the precise
     * carrier id for the changed carrier configuration.
     * @see TelephonyManager#getSimCarrierId()
     * @see TelephonyManager#getSimSpecificCarrierId()
     */
    public static final String ACTION_CARRIER_CONFIG_CHANGED = "android.telephony.action.CARRIER_CONFIG_CHANGED";

    /**
     * Indicates CARRIER_NR_AVAILABILITY_NSA determine that the carrier enable the non-standalone
     * (NSA) mode of 5G NR.
     */
    public static final int CARRIER_NR_AVAILABILITY_NSA = 1;

    /**
     * Indicates CARRIER_NR_AVAILABILITY_SA determine that the carrier enable the standalone (SA)
     * mode of 5G NR.
     */
    public static final int CARRIER_NR_AVAILABILITY_SA = 2;

    /**
     * Specifies SPN format of displaying carrier name only.
     */
    public static final int CROSS_SIM_SPN_FORMAT_CARRIER_NAME_ONLY = 0;

    /**
     * Specifies SPN format of displaying carrier name along with "Cross-SIM calling".
     */
    public static final int CROSS_SIM_SPN_FORMAT_CARRIER_NAME_WITH_BRANDING = 1;

    /**
     * Flag indicating that a data cycle threshold should be disabled.
     * <p>
     * If {@link #KEY_DATA_WARNING_THRESHOLD_BYTES_LONG} is set to this value, the platform's
     * default data warning, if one exists, will be disabled. A user selected data warning will not
     * be overridden.
     * <p>
     * If {@link #KEY_DATA_LIMIT_THRESHOLD_BYTES_LONG} is set to this value, the platform's
     * default data limit, if one exists, will be disabled. A user selected data limit will not be
     * overridden.
     */
    public static final int DATA_CYCLE_THRESHOLD_DISABLED = -2;

    /**
     * When {@link #KEY_MONTHLY_DATA_CYCLE_DAY_INT}, {@link #KEY_DATA_LIMIT_THRESHOLD_BYTES_LONG},
     * or {@link #KEY_DATA_WARNING_THRESHOLD_BYTES_LONG} are set to this value, the platform default
     * value will be used for that key.
     */
    public static final int DATA_CYCLE_USE_PLATFORM_DEFAULT = -1;

    /**
     * Specifies whether the system should prefix the EAP method to the anonymous identity.
     * The following prefix will be added if this key is set to TRUE:
     *   EAP-AKA: "0"
     *   EAP-SIM: "1"
     *   EAP-AKA_PRIME: "6"
     */
    public static final String ENABLE_EAP_METHOD_PREFIX_BOOL = "enable_eap_method_prefix_bool";

    /**
     * {@link #ACTION_CARRIER_CONFIG_CHANGED} is broadcast once on device bootup and then again when
     * the device is unlocked. Direct-Boot-aware applications may use the first broadcast as an
     * early signal that the carrier config has been loaded, but other applications will only
     * receive the second broadcast, when the device is unlocked.
     *
     * This extra is included in {@link #ACTION_CARRIER_CONFIG_CHANGED} to indicate whether this is
     * a rebroadcast on unlock.
     */
    public static final String EXTRA_REBROADCAST_ON_UNLOCK = "android.telephony.extra.REBROADCAST_ON_UNLOCK";

    /**
     * Extra included in {@link #ACTION_CARRIER_CONFIG_CHANGED} to indicate the slot index that the
     * broadcast is for.
     */
    public static final String EXTRA_SLOT_INDEX = "android.telephony.extra.SLOT_INDEX";

    /**
     * Optional extra included in {@link #ACTION_CARRIER_CONFIG_CHANGED} to indicate the
     * subscription index that the broadcast is for, if a valid one is available.
     */
    public static final String EXTRA_SUBSCRIPTION_INDEX = "android.telephony.extra.SUBSCRIPTION_INDEX";

    /**
     * Identifies if the key is available for WLAN or EPDG or both. The value is a bitmask.
     * 0 indicates that neither EPDG or WLAN is enabled.
     * 1 indicates that key type TelephonyManager#KEY_TYPE_EPDG is enabled.
     * 2 indicates that key type TelephonyManager#KEY_TYPE_WLAN is enabled.
     * 3 indicates that both are enabled.
     */
    public static final String IMSI_KEY_AVAILABILITY_INT = "imsi_key_availability_int";

    /**
     * List of 4 customized 5G SS reference signal received power (SSRSRP) thresholds.
     * <p>
     * Reference: 3GPP TS 38.215
     * <p>
     * 4 threshold integers must be within the boundaries [-140 dB, -44 dB], and the levels are:
     * <UL>
     *     <LI>"NONE: [-140, threshold1)"</LI>
     *     <LI>"POOR: [threshold1, threshold2)"</LI>
     *     <LI>"MODERATE: [threshold2, threshold3)"</LI>
     *     <LI>"GOOD:  [threshold3, threshold4)"</LI>
     *     <LI>"EXCELLENT:  [threshold4, -44]"</LI>
     * </UL>
     * <p>
     * This key is considered invalid if the format is violated. If the key is invalid or
     * not configured, a default value set will apply.
     */
    public static final String KEY_5G_NR_SSRSRP_THRESHOLDS_INT_ARRAY = "5g_nr_ssrsrp_thresholds_int_array";

    /**
     * List of 4 customized 5G SS reference signal received quality (SSRSRQ) thresholds.
     * <p>
     * Reference: 3GPP TS 38.215; 3GPP TS 38.133 section 10
     * <p>
     * 4 threshold integers must be within the boundaries [-43 dB, 20 dB], and the levels are:
     * <UL>
     *     <LI>"NONE: [-43, threshold1)"</LI>
     *     <LI>"POOR: [threshold1, threshold2)"</LI>
     *     <LI>"MODERATE: [threshold2, threshold3)"</LI>
     *     <LI>"GOOD:  [threshold3, threshold4)"</LI>
     *     <LI>"EXCELLENT:  [threshold4, 20]"</LI>
     * </UL>
     * <p>
     * This key is considered invalid if the format is violated. If the key is invalid or
     * not configured, a default value set will apply.
     */
    public static final String KEY_5G_NR_SSRSRQ_THRESHOLDS_INT_ARRAY = "5g_nr_ssrsrq_thresholds_int_array";

    /**
     * List of 4 customized 5G SS signal-to-noise and interference ratio (SSSINR) thresholds.
     * <p>
     * Reference: 3GPP TS 38.215,
     *            3GPP TS 38.133 10.1.16.1
     * <p>
     * 4 threshold integers must be within the boundaries [-23 dB, 40 dB], and the levels are:
     * <UL>
     *     <LI>"NONE: [-23, threshold1)"</LI>
     *     <LI>"POOR: [threshold1, threshold2)"</LI>
     *     <LI>"MODERATE: [threshold2, threshold3)"</LI>
     *     <LI>"GOOD:  [threshold3, threshold4)"</LI>
     *     <LI>"EXCELLENT:  [threshold4, 40]"</LI>
     * </UL>
     * <p>
     * This key is considered invalid if the format is violated. If the key is invalid or
     * not configured, a default value set will apply.
     */
    public static final String KEY_5G_NR_SSSINR_THRESHOLDS_INT_ARRAY = "5g_nr_sssinr_thresholds_int_array";

    /**
     * Does not display additional call setting for IMS phone based on GSM Phone
     */
    public static final String KEY_ADDITIONAL_CALL_SETTING_BOOL = "additional_call_setting_bool";

    /**
     * Boolean indicating if the "Caller ID" item is visible in the Additional Settings menu.
     * true means visible. false means gone.
     *
     * The default value is true.
     */
    public static final String KEY_ADDITIONAL_SETTINGS_CALLER_ID_VISIBILITY_BOOL = "additional_settings_caller_id_visibility_bool";

    /**
     * Boolean indicating if the "Call Waiting" item is visible in the Additional Settings menu.
     * true means visible. false means gone.
     *
     * The default value is true.
     */
    public static final String KEY_ADDITIONAL_SETTINGS_CALL_WAITING_VISIBILITY_BOOL = "additional_settings_call_waiting_visibility_bool";

    /**
     * Allow user to add APNs
     */
    public static final String KEY_ALLOW_ADDING_APNS_BOOL = "allow_adding_apns_bool";

    /**
     * When true, indicates that adding a call is disabled when there is an ongoing video call
     * or when there is an ongoing call on wifi which was downgraded from video and VoWifi is
     * turned off.
     */
    public static final String KEY_ALLOW_ADD_CALL_DURING_VIDEO_CALL_BOOL = "allow_add_call_during_video_call";

    /**
     * Determines if the current device should allow emergency numbers to be logged in the Call Log.
     * (Some carriers require that emergency calls *not* be logged, presumably to avoid the risk of
     * accidental redialing from the call log UI. This is a good idea, so the default here is
     * false.)
     */
    public static final String KEY_ALLOW_EMERGENCY_NUMBERS_IN_CALL_LOG_BOOL = "allow_emergency_numbers_in_call_log_bool";

    /**
     * Flag indicating whether to allow carrier video calls to emergency numbers.
     * When {@code true}, video calls to emergency numbers will be allowed. When {@code false},
     * video calls to emergency numbers will be initiated as audio-only calls instead.
     */
    public static final String KEY_ALLOW_EMERGENCY_VIDEO_CALLS_BOOL = "allow_emergency_video_calls_bool";

    /**
     * Flag indicating whether or not an ongoing call will be held when an outgoing emergency call
     * is placed. If true, ongoing calls will be put on hold when an emergency call is placed. If
     * false, placing an emergency call will trigger the disconnect of all ongoing calls before
     * the emergency call is placed.
     */
    public static final String KEY_ALLOW_HOLD_CALL_DURING_EMERGENCY_BOOL = "allow_hold_call_during_emergency_bool";

    /**
     * When {@code true}, indicates that video calls can be put on hold in order to swap to another
     * call (e.g. a new outgoing call).
     * When {@code false}, indicates that video calls will be disconnected when swapping to another
     * call.
     * <p>
     * This is {@code true} by default.
     */
    public static final String KEY_ALLOW_HOLD_VIDEO_CALL_BOOL = "allow_hold_video_call_bool";

    /**
     * Determine whether we want to play local DTMF tones in a call, or just let the radio/BP handle
     * playing of the tones.
     */
    public static final String KEY_ALLOW_LOCAL_DTMF_TONES_BOOL = "allow_local_dtmf_tones_bool";

    /**
     * Flag indicating whether the carrier supports merging wifi calls when VoWIFI is disabled.
     * This can happen in the case of a carrier which allows offloading video calls to WIFI
     * separately of whether voice over wifi is enabled. In such a scenario when two video calls
     * are downgraded to voice, they remain over wifi. However, if VoWIFI is disabled, these calls
     * cannot be merged.
     */
    public static final String KEY_ALLOW_MERGE_WIFI_CALLS_WHEN_VOWIFI_OFF_BOOL = "allow_merge_wifi_calls_when_vowifi_off_bool";

    /**
     * Determines whether the carrier supports making non-emergency phone calls while the phone is
     * in emergency callback mode. Default value is {@code true}, meaning that non-emergency calls
     * are allowed in emergency callback mode.
     */
    public static final String KEY_ALLOW_NON_EMERGENCY_CALLS_IN_ECM_BOOL = "allow_non_emergency_calls_in_ecm_bool";

    /**
     * Whether system apps are allowed to use fallback if carrier video call is not available.
     * Defaults to {@code true}.
     */
    public static final String KEY_ALLOW_VIDEO_CALLING_FALLBACK_BOOL = "allow_video_calling_fallback_bool";

    /**
     * Boolean indicating if show data RAT icon on status bar even when data is disabled.
     */
    public static final String KEY_ALWAYS_SHOW_DATA_RAT_ICON_BOOL = "always_show_data_rat_icon_bool";

    /**
     * The default flag specifying whether "Allow alerts" option will be always shown in
     * emergency alerts settings regardless developer options is turned on or not.
     *
     * @deprecated The allow alerts option is always shown now. No longer need a config for that.
     */
    @Deprecated
    public static final String KEY_ALWAYS_SHOW_EMERGENCY_ALERT_ONOFF_BOOL = "always_show_emergency_alert_onoff_bool";

    /**
     * Indicates when a carrier has a primary subscription and an opportunistic subscription active,
     * and when Internet data is switched to opportunistic network, whether to still show
     * signal bar of primary network. By default it will be false, meaning whenever data
     * is going over opportunistic network, signal bar will reflect signal strength and rat
     * icon of that network.
     */
    public static final String KEY_ALWAYS_SHOW_PRIMARY_SIGNAL_BAR_IN_OPPORTUNISTIC_NETWORK_BOOLEAN = "always_show_primary_signal_bar_in_opportunistic_network_boolean";

    /**
     * Control whether users can edit APNs in Settings.
     */
    public static final String KEY_APN_EXPAND_BOOL = "apn_expand_bool";

    /**
     * Default value of APN types field if not specified by user when adding/modifying an APN.
     */
    public static final String KEY_APN_SETTINGS_DEFAULT_APN_TYPES_STRING_ARRAY = "apn_settings_default_apn_types_string_array";

    /**
     * Flag indicating if auto retry is enabled
     */
    public static final String KEY_AUTO_RETRY_ENABLED_BOOL = "auto_retry_enabled_bool";

    /**
     * Specifies the service class for call barring service. Default value is
     * {@link #SERVICE_CLASS_VOICE}.
     * The value set as below:
     * <ul>
     * <li>0: {@link #SERVICE_CLASS_NONE}</li>
     * <li>1: {@link #SERVICE_CLASS_VOICE}</li>
     * </ul>
     */
    public static final String KEY_CALL_BARRING_DEFAULT_SERVICE_CLASS_INT = "call_barring_default_service_class_int";

    /**
     * Flag indicating whether or not deactivating all call barring features via the "Call Barring"
     * settings menu is supported. If true, the option will be visible in the "Call
     * Barring" settings menu. If false, the option will not be visible.
     *
     * Enabled by default.
     */
    public static final String KEY_CALL_BARRING_SUPPORTS_DEACTIVATE_ALL_BOOL = "call_barring_supports_deactivate_all_bool";

    /**
     * Flag indicating whether or not changing the call barring password via the "Call Barring"
     * settings menu is supported. If true, the option will be visible in the "Call
     * Barring" settings menu. If false, the option will not be visible.
     *
     * Enabled by default.
     */
    public static final String KEY_CALL_BARRING_SUPPORTS_PASSWORD_CHANGE_BOOL = "call_barring_supports_password_change_bool";

    /**
     * Boolean indicating if the "Call barring" item is visible in the Call Settings menu.
     * If true, the "Call Barring" menu will be visible. If false, the menu will be gone.
     *
     * Disabled by default.
     */
    public static final String KEY_CALL_BARRING_VISIBILITY_BOOL = "call_barring_visibility_bool";

    /**
     * Indicates the carrier server url that serves the call composer picture.
     */
    public static final String KEY_CALL_COMPOSER_PICTURE_SERVER_URL_STRING = "call_composer_picture_server_url_string";

    /**
     * An array containing custom call forwarding number prefixes that will be blocked while the
     * device is reporting that it is roaming. By default, there are no custom call
     * forwarding prefixes and none of these numbers will be filtered. If one or more entries are
     * present, the system will not complete the call and display an error message.
     *
     * To display a message to the user when call forwarding fails for 3gpp MMI codes while roaming,
     * use the {@link #KEY_SUPPORT_3GPP_CALL_FORWARDING_WHILE_ROAMING_BOOL} option instead.
     */
    public static final String KEY_CALL_FORWARDING_BLOCKS_WHILE_ROAMING_STRING_ARRAY = "call_forwarding_blocks_while_roaming_string_array";

    /**
     * Specifies a carrier-defined {@link android.telecom.CallRedirectionService} which Telecom
     * will bind to for outgoing calls. An empty string indicates that no carrier-defined
     * {@link android.telecom.CallRedirectionService} is specified.
     */
    public static final String KEY_CALL_REDIRECTION_SERVICE_COMPONENT_NAME_STRING = "call_redirection_service_component_name_string";

    /**
     * Only apply if {@link #KEY_ONLY_SINGLE_DC_ALLOWED_INT_ARRAY} specifies the network types that
     * support a single data connection at a time. This key defines a list of network capabilities
     * which, if requested, will exempt the request from single data connection checks.
     * @see NetworkCapabilities NET_CAPABILITY_*
     */
    public static final String KEY_CAPABILITIES_EXEMPT_FROM_SINGLE_DC_CHECK_INT_ARRAY = "capabilities_exempt_from_single_dc_check_int_array";

    /**
     * Flag indicating whether the carrier supports call deflection for an incoming IMS call.
     */
    public static final String KEY_CARRIER_ALLOW_DEFLECT_IMS_CALL_BOOL = "carrier_allow_deflect_ims_call_bool";

    /**
     * Flag specifying whether IMS service can be turned off. If false then the service will not be
     * turned-off completely, but individual features can be disabled.
     */
    public static final String KEY_CARRIER_ALLOW_TURNOFF_IMS_BOOL = "carrier_allow_turnoff_ims_bool";

    /**
     * Determines whether the carrier app needed to be involved when users try to finish setting up
     * the SIM card to get network service.
     */
    public static final String KEY_CARRIER_APP_REQUIRED_DURING_SIM_SETUP_BOOL = "carrier_app_required_during_setup_bool";

    /**
     * The Component Name of a carrier-provided CallScreeningService implementation. Telecom will
     * bind to {@link android.telecom.CallScreeningService} for ALL incoming calls and provide
     * the carrier
     * CallScreeningService with the opportunity to allow or block calls.
     * <p>
     * The String includes the package name/the class name.
     * Example:
     * <item>com.android.carrier/com.android.carrier.callscreeningserviceimpl</item>
     * <p>
     * Using {@link ComponentName#flattenToString()} to convert a ComponentName object to String.
     * Using {@link ComponentName#unflattenFromString(String)} to convert a String object to a
     * ComponentName.
     */
    public static final String KEY_CARRIER_CALL_SCREENING_APP_STRING = "call_screening_app";

    /**
     * Holds the list of carrier certificate hashes, followed by optional package names.
     * Format: "sha1/256" or "sha1/256:package1,package2,package3..."
     * Note that each carrier has its own hashes.
     */
    public static final String KEY_CARRIER_CERTIFICATE_STRING_ARRAY = "carrier_certificate_string_array";

    /**
     * Determines whether any carrier has been identified and its specific config has been applied,
     * default to false.
     */
    public static final String KEY_CARRIER_CONFIG_APPLIED_BOOL = "carrier_config_applied_bool";

    /**
     * Specifies a value that identifies the version of the carrier configuration that is
     * currently in use. This string is displayed on the UI.
     * The format of the string is not specified.
     */
    public static final String KEY_CARRIER_CONFIG_VERSION_STRING = "carrier_config_version_string";

    /**
     * Flag specifying whether Cross SIM over IMS should be available for carrier.
     * When {@code false} the carrier does not support cross SIM calling.
     * When {@code true} the carrier does support cross sim calling, where available
     */
    public static final String KEY_CARRIER_CROSS_SIM_IMS_AVAILABLE_BOOL = "carrier_cross_sim_ims_available_bool";

    /**
     * Data call setup permanent failure causes by the carrier.
     *
     * @deprecated This API key was added in mistake and is not used anymore by the telephony data
     * frameworks.
     */
    @Deprecated
    public static final String KEY_CARRIER_DATA_CALL_PERMANENT_FAILURE_STRINGS = "carrier_data_call_permanent_failure_strings";

    /**
     * Defines carrier-specific actions which act upon CARRIER_SIGNAL_REQUEST_NETWORK_FAILED
     * and configured signal args:
     * android.telephony.TelephonyManager#EXTRA_APN_TYPE,
     * android.telephony.TelephonyManager#EXTRA_ERROR_CODE
     * used for customization of the default carrier app
     * Format:
     * {
     *     "APN_1, ERROR_CODE_1 : CARRIER_ACTION_IDX_1, CARRIER_ACTION_IDX_2...",
     *     "APN_1, ERROR_CODE_2 : CARRIER_ACTION_IDX_1 "
     * }
     * Where {@code APN_1} is an integer defined in {@link android.telephony.data.ApnSetting}
     * (e.g. {@link android.telephony.data.ApnSetting#TYPE_DEFAULT}
     *
     * {@code ERROR_CODE_1} is an integer defined in android.telephony.DataFailCause
     * Example:
     * android.telephony.DataFailCause#MISSING_UNKNOWN_APN
     *
     * {@code CARRIER_ACTION_IDX_1} is an integer defined in
     * com.android.carrierdefaultapp.CarrierActionUtils
     * Example:
     * com.android.carrierdefaultapp.CarrierActionUtils#CARRIER_ACTION_DISABLE_METERED_APNS
     * disables metered APNs
     */
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_DCFAILURE_STRING_ARRAY = "carrier_default_actions_on_dcfailure_string_array";

    /**
     * Defines carrier-specific actions which act upon
     * com.android.internal.telephony.CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE,
     * used for customization of the default carrier app.
     * Format:
     * {
     *     "true : CARRIER_ACTION_IDX_1",
     *     "false: CARRIER_ACTION_IDX_2"
     * }
     * Where {@code true} is a boolean indicates default network available/unavailable
     * Where {@code CARRIER_ACTION_IDX} is an integer defined in
     * com.android.carrierdefaultapp.CarrierActionUtils CarrierActionUtils
     * Example:
     * com.android.carrierdefaultapp.CarrierActionUtils#CARRIER_ACTION_ENABLE_DEFAULT_URL_HANDLER
     * enables the app as the default URL handler
     */
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_DEFAULT_NETWORK_AVAILABLE = "carrier_default_actions_on_default_network_available_string_array";

    /**
     * Defines carrier-specific actions which act upon
     * com.android.internal.telephony.CARRIER_SIGNAL_REDIRECTED, used for customization of the
     * default carrier app.
     * Format: "CARRIER_ACTION_IDX, ..."
     * Where {@code CARRIER_ACTION_IDX} is an integer defined in
     * com.android.carrierdefaultapp.CarrierActionUtils
     * Example:
     * com.android.carrierdefaultapp.CarrierActionUtils#CARRIER_ACTION_DISABLE_METERED_APNS
     * disables metered APNs
     */
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_REDIRECTION_STRING_ARRAY = "carrier_default_actions_on_redirection_string_array";

    /**
     * Defines carrier-specific actions which act upon CARRIER_SIGNAL_RESET,
     * used for customization of the default carrier app.
     * Format: "CARRIER_ACTION_IDX, ..."
     * Where {@code CARRIER_ACTION_IDX} is an integer defined in
     * com.android.carrierdefaultapp.CarrierActionUtils
     * Example:
     * com.android.carrierdefaultapp.CarrierActionUtils#CARRIER_ACTION_CANCEL_ALL_NOTIFICATIONS
     * clears all notifications on reset
     */
    public static final String KEY_CARRIER_DEFAULT_ACTIONS_ON_RESET = "carrier_default_actions_on_reset_string_array";

    /**
     * Defines a list of acceptable redirection url for default carrier app.
     */
    public static final String KEY_CARRIER_DEFAULT_REDIRECTION_URL_STRING_ARRAY = "carrier_default_redirection_url_string_array";

    /**
     * Default WFC_IMS_enabled: true VoWiFi by default is on
     *                          false VoWiFi by default is off
     */
    public static final String KEY_CARRIER_DEFAULT_WFC_IMS_ENABLED_BOOL = "carrier_default_wfc_ims_enabled_bool";

    /**
     * Default mode for WFC over IMS on home network:
     * <ul>
     *   <li>0: Wi-Fi only
     *   <li>1: prefer mobile network
     *   <li>2: prefer Wi-Fi
     * </ul>
     */
    public static final String KEY_CARRIER_DEFAULT_WFC_IMS_MODE_INT = "carrier_default_wfc_ims_mode_int";

    /**
     * Default mode for WFC over IMS on roaming network.
     * See {@link #KEY_CARRIER_DEFAULT_WFC_IMS_MODE_INT} for meaning of values.
     */
    public static final String KEY_CARRIER_DEFAULT_WFC_IMS_ROAMING_MODE_INT = "carrier_default_wfc_ims_roaming_mode_int";

    /**
     * The default flag specifying whether ETWS/CMAS test setting is forcibly disabled in
     * Settings->More->Emergency broadcasts menu even though developer options is turned on.
     * @deprecated Use {@code com.android.cellbroadcastreceiver.CellBroadcastReceiver} resource
     * {@code show_test_settings} to control whether to show test alert settings or not.
     */
    @Deprecated
    public static final String KEY_CARRIER_FORCE_DISABLE_ETWS_CMAS_TEST_BOOL = "carrier_force_disable_etws_cmas_test_bool";

    /**
     * Flag specifying whether Generic Bootstrapping Architecture capable SIM is required for IMS.
     */
    public static final String KEY_CARRIER_IMS_GBA_REQUIRED_BOOL = "carrier_ims_gba_required_bool";

    /**
     * Flag specifying whether IMS instant lettering is available for the carrier. {@code True} if
     * instant lettering is available for the carrier, {@code false} otherwise.
     */
    public static final String KEY_CARRIER_INSTANT_LETTERING_AVAILABLE_BOOL = "carrier_instant_lettering_available_bool";

    /**
     * When IMS instant lettering is available for a carrier (see
     * {@link #KEY_CARRIER_INSTANT_LETTERING_AVAILABLE_BOOL}), determines the character encoding
     * which will be used when determining the length of messages. Used in the InCall UI to limit
     * the number of characters the user may type. If empty-string, the instant lettering
     * message size limit will be enforced on a 1:1 basis. That is, each character will count
     * towards the messages size limit as a single byte. If a character encoding is specified, the
     * message size limit will be based on the number of bytes in the message per the specified
     * encoding.
     */
    public static final String KEY_CARRIER_INSTANT_LETTERING_ENCODING_STRING = "carrier_instant_lettering_encoding_string";

    /**
     * When IMS instant lettering is available for a carrier (see
     * {@link #KEY_CARRIER_INSTANT_LETTERING_AVAILABLE_BOOL}), determines a list of characters which
     * must be escaped with a backslash '\' character. Should be specified as a string containing
     * the characters to be escaped. For example to escape quote and backslash the string would be
     * a quote and a backslash.
     */
    public static final String KEY_CARRIER_INSTANT_LETTERING_ESCAPED_CHARS_STRING = "carrier_instant_lettering_escaped_chars_string";

    /**
     * When IMS instant lettering is available for a carrier (see
     * {@link #KEY_CARRIER_INSTANT_LETTERING_AVAILABLE_BOOL}), determines the list of characters
     * which may not be contained in messages. Should be specified as a regular expression suitable
     * for use with {@link String#matches(String)}.
     */
    public static final String KEY_CARRIER_INSTANT_LETTERING_INVALID_CHARS_STRING = "carrier_instant_lettering_invalid_chars_string";

    /**
     * When IMS instant lettering is available for a carrier (see
     * {@link #KEY_CARRIER_INSTANT_LETTERING_AVAILABLE_BOOL}), the length limit for messages. Used
     * in the InCall UI to ensure the user cannot enter more characters than allowed by the carrier.
     * See also {@link #KEY_CARRIER_INSTANT_LETTERING_ENCODING_STRING} for more information on how
     * the length of the message is calculated.
     */
    public static final String KEY_CARRIER_INSTANT_LETTERING_LENGTH_LIMIT_INT = "carrier_instant_lettering_length_limit_int";

    /**
     * A string array indicating the default APN types that are metered by the carrier.
     *
     * The string in the array is the name of the APN type. For example, "default" for
     * {@link ApnSetting#TYPE_DEFAULT}, "mms" for {@link ApnSetting#TYPE_MMS}, etc.
     *
     * The default value is {@code {"default", "mms", "dun", "supl"}}.
     *
     * @see ApnSetting#TYPE_DEFAULT
     * @see ApnSetting#TYPE_MMS
     * @see ApnSetting#TYPE_SUPL
     * @see ApnSetting#TYPE_DUN
     * @see ApnSetting#TYPE_HIPRI
     * @see ApnSetting#TYPE_FOTA
     * @see ApnSetting#TYPE_IMS
     * @see ApnSetting#TYPE_CBS
     * @see ApnSetting#TYPE_IA
     * @see ApnSetting#TYPE_EMERGENCY
     * @see ApnSetting#TYPE_MCX
     * @see ApnSetting#TYPE_XCAP
     * @see ApnSetting#TYPE_BIP
     * @see ApnSetting#TYPE_VSIM
     * @see ApnSetting#TYPE_ENTERPRISE
     */
    public static final String KEY_CARRIER_METERED_APN_TYPES_STRINGS = "carrier_metered_apn_types_strings";

    /**
     * A string array indicating the default APN types that are roaming-metered by the carrier.
     *
     * The string in the array is the name of the APN type. For example, "default" for
     * {@link ApnSetting#TYPE_DEFAULT}, "mms" for {@link ApnSetting#TYPE_MMS}, etc.
     *
     * The default value is {@code {"default", "mms", "dun", "supl"}}.
     *
     * @see ApnSetting#TYPE_DEFAULT
     * @see ApnSetting#TYPE_MMS
     * @see ApnSetting#TYPE_SUPL
     * @see ApnSetting#TYPE_DUN
     * @see ApnSetting#TYPE_HIPRI
     * @see ApnSetting#TYPE_FOTA
     * @see ApnSetting#TYPE_IMS
     * @see ApnSetting#TYPE_CBS
     * @see ApnSetting#TYPE_IA
     * @see ApnSetting#TYPE_EMERGENCY
     * @see ApnSetting#TYPE_MCX
     * @see ApnSetting#TYPE_XCAP
     * @see ApnSetting#TYPE_BIP
     * @see ApnSetting#TYPE_VSIM
     * @see ApnSetting#TYPE_ENTERPRISE
     */
    public static final String KEY_CARRIER_METERED_ROAMING_APN_TYPES_STRINGS = "carrier_metered_roaming_apn_types_strings";

    /**
     * Unconditionally override the carrier name string using #KEY_CARRIER_NAME_STRING.
     *
     * If true, then the carrier name string will be #KEY_CARRIER_NAME_STRING, unconditionally.
     *
     * <p>If false, then the override will be performed conditionally and the
     * #KEY_CARRIER_NAME_STRING will have the lowest-precedence; it will only be used in the event
     * that the name string would otherwise be empty, allowing it to serve as a last-resort. If
     * used, this value functions in place of the SPN on any/all ICC records for the corresponding
     * subscription.
     */
    public static final String KEY_CARRIER_NAME_OVERRIDE_BOOL = "carrier_name_override_bool";

    /**
     * String to identify carrier name in CarrierConfig app. This string overrides SPN if
     * #KEY_CARRIER_NAME_OVERRIDE_BOOL is true; otherwise, it will be used if its value is provided
     * and SPN is unavailable
     */
    public static final String KEY_CARRIER_NAME_STRING = "carrier_name_string";

    /**
     * A list of carrier nr availability is used to determine whether the carrier enable the
     * non-standalone (NSA) mode of 5G NR, standalone (SA) mode of 5G NR
     *
     * <p> The value of list is
     * {@link #CARRIER_NR_AVAILABILITY_NSA}, or {@link #CARRIER_NR_AVAILABILITY_SA}.
     *
     * <p> For example, if both NSA and SA are used, the list value is {
     * {@link #CARRIER_NR_AVAILABILITY_NSA},{@link #CARRIER_NR_AVAILABILITY_SA}}.
     * If the carrier doesn't support 5G NR, the value is the empty array.
     * If the key is invalid or not configured, the default value {
     * {@link #CARRIER_NR_AVAILABILITY_NSA},{@link #CARRIER_NR_AVAILABILITY_SA}} will apply.
     */
    public static final String KEY_CARRIER_NR_AVAILABILITIES_INT_ARRAY = "carrier_nr_availabilities_int_array";

    /**
     * Indicates whether or not the carrier will provision merged carrier Wi-Fi offload networks.
     * Such networks are considered part of the core carrier network.
     *
     * This configuration will be use to gate whether such configurations are allowed to the carrier
     * and correspondingly enable UI elements which are required for such configurations.
     */
    public static final String KEY_CARRIER_PROVISIONS_WIFI_MERGED_NETWORKS_BOOL = "carrier_provisions_wifi_merged_networks_bool";

    /**
     * Flag specifying whether provisioning is required for RCS.
     */
    public static final String KEY_CARRIER_RCS_PROVISIONING_REQUIRED_BOOL = "carrier_rcs_provisioning_required_bool";

    /**
     * Indicates carrier roaming non-terrestrial network emergency call handover type that the
     * device will use to perform a handover between ESOS or T911.
     * If this key is set to {@link SatelliteManager#EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE_SOS}
     * then the handover will be made to ESOS. If this key is set to
     * {@link SatelliteManager#EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE_T911} then the handover
     * will be made to T911.
     *
     * The default value is {@link SatelliteManager#EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE_T911}.
     */
    public static final String KEY_CARRIER_ROAMING_NTN_EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE_INT = "carrier_roaming_ntn_emergency_call_to_satellite_handover_type_int";

    /**
     * String array containing the list of names for service numbers provided by carriers. This key
     * should be used with {@link #KEY_CARRIER_SERVICE_NUMBER_STRING_ARRAY}. The names provided in
     * this array will be mapped 1:1 with the numbers provided in the {@link
     * #KEY_CARRIER_SERVICE_NUMBER_STRING_ARRAY} array.
     *
     * <p>The data would be considered valid if and only if:
     *
     * <ul>
     *   <li>The number of items in both the arrays are equal
     *   <li>The data added to the {@link #KEY_CARRIER_SERVICE_NUMBER_STRING_ARRAY} array is valid.
     *       See {@link #KEY_CARRIER_SERVICE_NUMBER_STRING_ARRAY} for more information.
     * </ul>
     *
     * <p>Example:
     *
     * <pre>{@code
     * <string-array name="carrier_service_name_array" num="2">
     *   <item value="Police"/>
     *   <item value="Ambulance"/>
     * </string-array>
     * }</pre>
     */
    public static final String KEY_CARRIER_SERVICE_NAME_STRING_ARRAY = "carrier_service_name_array";

    /**
     * String array containing the list of service numbers provided by carriers. This key should be
     * used with {@link #KEY_CARRIER_SERVICE_NAME_STRING_ARRAY}. The numbers provided in this array
     * will be mapped 1:1 with the names provided in the {@link
     * #KEY_CARRIER_SERVICE_NAME_STRING_ARRAY} array.
     *
     * <p>The data would be considered valid if and only if:
     *
     * <ul>
     *   <li>The number of items in both the arrays are equal
     *   <li>The item should contain dialable characters only which includes 0-9, -, *, #, (, ),
     *       SPACE.
     * </ul>
     *
     * <p>Example:
     *
     * <pre>{@code
     * <string-array name="carrier_service_number_array" num="2">
     *   <item value="*123"/>
     *   <item value="+ (111) 111-111"/>
     * </string-array>
     * }</pre>
     */
    public static final String KEY_CARRIER_SERVICE_NUMBER_STRING_ARRAY = "carrier_service_number_array";

    /**
     * Flatten {@link android.content.ComponentName} of the carrier's settings activity.
     */
    public static final String KEY_CARRIER_SETTINGS_ACTIVITY_COMPONENT_NAME_STRING = "carrier_settings_activity_component_name_string";

    /**
     * Display carrier settings menu if true
     */
    public static final String KEY_CARRIER_SETTINGS_ENABLE_BOOL = "carrier_settings_enable_bool";

    /**
     * A PersistableBundle that contains a list of key-value pairs, where the values are integer
     * arrays.
     * <p>
     * Keys are the PLMNs of satellite providers as strings and values are integer arrays of
     * supported services with the following value:
     * <ul>
     * <li>1 = {@link android.telephony.NetworkRegistrationInfo#SERVICE_TYPE_VOICE}</li>
     * <li>2 = {@link android.telephony.NetworkRegistrationInfo#SERVICE_TYPE_DATA}</li>
     * <li>3 = {@link android.telephony.NetworkRegistrationInfo#SERVICE_TYPE_SMS}</li>
     * <li>4 = {@link android.telephony.NetworkRegistrationInfo#SERVICE_TYPE_VIDEO}</li>
     * <li>5 = {@link android.telephony.NetworkRegistrationInfo#SERVICE_TYPE_EMERGENCY}</li>
     * <li>6 = {@link android.telephony.NetworkRegistrationInfo#SERVICE_TYPE_MMS}</li>
     * </ul>
     * <p>
     * An example config for two PLMNs "123411" and "123412":
     * <pre>{@code
     * <carrier_config>
     *   <pbundle_as_map name="carrier_supported_satellite_services_per_provider_bundle">
     *     <int-array name = "123411" num = "2">
     *       <item value = "3"/>
     *       <item value = "5"/>
     *     </int-array>
     *     <int-array name = "123412" num = "1">
     *       <item value = "3"/>
     *     </int-array>
     *   </pbundle_as_map>
     * </carrier_config>
     * }</pre>
     * <p>
     * This config is empty by default.
     */
    public static final String KEY_CARRIER_SUPPORTED_SATELLITE_SERVICES_PER_PROVIDER_BUNDLE = "carrier_supported_satellite_services_per_provider_bundle";

    /**
     * Configuration to indicate that the carrier supports opportunistic data
     * auto provisioning. Based on this flag, the device downloads and activates
     * corresponding opportunistic profile.
     */
    public static final String KEY_CARRIER_SUPPORTS_OPP_DATA_AUTO_PROVISIONING_BOOL = "carrier_supports_opp_data_auto_provisioning_bool";

    /**
     * Flag indicating whether or not the carrier supports Supplementary Services over the UT
     * interface for this subscription.
     *
     * If true, the device will use Supplementary Services over UT when provisioned (see
     * {@link #KEY_CARRIER_UT_PROVISIONING_REQUIRED_BOOL}). If false, this device will fallback to
     * circuit switch for supplementary services and will disable this capability for IMS entirely.
     *
     * The default value for this key is {@code false}.
     */
    public static final String KEY_CARRIER_SUPPORTS_SS_OVER_UT_BOOL = "carrier_supports_ss_over_ut_bool";

    /**
     * Flag indicating if the carrier supports tethering of mobile data.
     */
    public static final String KEY_CARRIER_SUPPORTS_TETHERING_BOOL = "carrier_supports_tethering_bool";

    /**
     * Flag specifying whether IMS should be the first phone attempted for E911 even if the
     * phone is not in service.
     */
    public static final String KEY_CARRIER_USE_IMS_FIRST_FOR_EMERGENCY_BOOL = "carrier_use_ims_first_for_emergency_bool";

    /**
     * Specify the method of selection for UE sending USSD requests. The default value is
     * {@link #USSD_OVER_CS_PREFERRED}.
     * <p> Available options:
     * <ul>
     *   <li>0: {@link #USSD_OVER_CS_PREFERRED} </li>
     *   <li>1: {@link #USSD_OVER_IMS_PREFERRED} </li>
     *   <li>2: {@link #USSD_OVER_CS_ONLY} </li>
     *   <li>3: {@link #USSD_OVER_IMS_ONLY} </li>
     * </ul>
     */
    public static final String KEY_CARRIER_USSD_METHOD_INT = "carrier_ussd_method_int";

    /**
     * Flag indicating whether or not the IMS MmTel UT capability requires carrier provisioning
     * before it can be set as enabled.
     *
     * If true, the UT capability will be set to false for the newly loaded subscription
     * and will require the carrier provisioning app to set the persistent provisioning result.
     * If false, the platform will not wait for provisioning status updates for the UT capability
     * and enable the UT over IMS capability for the subscription when the subscription is loaded.
     *
     * The default value for this key is {@code false}.
     *
     * @deprecated Use {@link Ims#KEY_MMTEL_REQUIRES_PROVISIONING_BUNDLE} instead for
     * determining if UT requires provisioning.
     */
    @Deprecated
    public static final String KEY_CARRIER_UT_PROVISIONING_REQUIRED_BOOL = "carrier_ut_provisioning_required_bool";

    /**
     * Flag specifying whether VoLTE should be available for carrier, independent of carrier
     * provisioning. If false: hard disabled. If true: then depends on carrier provisioning,
     * availability, etc.
     */
    public static final String KEY_CARRIER_VOLTE_AVAILABLE_BOOL = "carrier_volte_available_bool";

    /**
     * Flag specifying if WFC provisioning depends on VoLTE provisioning.
     *
     * {@code false}: default value; honor actual WFC provisioning state.
     * {@code true}: when VoLTE is not provisioned, treat WFC as not provisioned; when VoLTE is
     *               provisioned, honor actual WFC provisioning state.
     *
     * As of now, Verizon is the only carrier enforcing this dependency in their
     * WFC awareness and activation requirements.
     */
    public static final String KEY_CARRIER_VOLTE_OVERRIDE_WFC_PROVISIONING_BOOL = "carrier_volte_override_wfc_provisioning_bool";

    /**
     * This flag specifies whether VoLTE availability is based on provisioning. By default this is
     * false.
     * Used for UCE to determine if EAB provisioning checks should be based on provisioning.
     * @deprecated Use {@link Ims#KEY_CARRIER_RCS_PROVISIONING_REQUIRED_BOOL} instead.
     */
    @Deprecated
    public static final String KEY_CARRIER_VOLTE_PROVISIONED_BOOL = "carrier_volte_provisioned_bool";

    /**
     * Flag specifying whether provisioning is required for VoLTE, Video Telephony, and WiFi
     * Calling.
     *
     * Combines VoLTE, VT, VoWiFI calling provisioning into one parameter.
     * @deprecated Use {@link Ims#KEY_MMTEL_REQUIRES_PROVISIONING_BUNDLE} instead for
     * finer-grained control.
     * changing carrier_volte_provisioning_required_bool requires changes to
     * mmtel_requires_provisioning_bundle and vice versa
     * {@link Ims#KEY_MMTEL_REQUIRES_PROVISIONING_BUNDLE}
     */
    @Deprecated
    public static final String KEY_CARRIER_VOLTE_PROVISIONING_REQUIRED_BOOL = "carrier_volte_provisioning_required_bool";

    /**
     * Flag specifying whether VoLTE TTY is supported.
     */
    public static final String KEY_CARRIER_VOLTE_TTY_SUPPORTED_BOOL = "carrier_volte_tty_supported_bool";

    /**
     * Flag specifying whether video telephony is available for carrier. If false: hard disabled.
     * If true: then depends on carrier provisioning, availability, etc.
     */
    public static final String KEY_CARRIER_VT_AVAILABLE_BOOL = "carrier_vt_available_bool";

    /**
     * The package name of the carrier's visual voicemail app to ensure that dialer visual voicemail
     * and carrier visual voicemail are not active at the same time.
     *
     * @deprecated use {@link #KEY_CARRIER_VVM_PACKAGE_NAME_STRING_ARRAY}.
     */
    @Deprecated
    public static final String KEY_CARRIER_VVM_PACKAGE_NAME_STRING = "carrier_vvm_package_name_string";

    /**
     * A list of the carrier's visual voicemail app package names to ensure that dialer visual
     * voicemail and carrier visual voicemail are not active at the same time.
     */
    public static final String KEY_CARRIER_VVM_PACKAGE_NAME_STRING_ARRAY = "carrier_vvm_package_name_string_array";

    /**
     * Flag specifying whether WFC over IMS should be available for carrier: independent of
     * carrier provisioning. If false: hard disabled. If true: then depends on carrier
     * provisioning, availability etc.
     */
    public static final String KEY_CARRIER_WFC_IMS_AVAILABLE_BOOL = "carrier_wfc_ims_available_bool";

    /**
     * Flag specifying whether WFC over IMS supports the "wifi only" option. If false, the wifi
     * calling settings will not include an option for "wifi only". If true, the wifi calling
     * settings will include an option for "wifi only"
     * <p>
     * By default, it is assumed that WFC supports "wifi only".
     */
    public static final String KEY_CARRIER_WFC_SUPPORTS_WIFI_ONLY_BOOL = "carrier_wfc_supports_wifi_only_bool";

    /**
     * For carriers which require an empty flash to be sent before sending the normal 3-way calling
     * flash, the duration in milliseconds of the empty flash to send. When {@code 0}, no empty
     * flash is sent.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_CDMA_3WAYCALL_FLASH_DELAY_INT = "cdma_3waycall_flash_delay_int";

    /**
     * Specifies the amount of gap to be added in millis between postdial DTMF tones. When a
     * non-zero value is specified, the UE shall wait for the specified amount of time before it
     * sends out successive DTMF tones on the network.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_CDMA_DTMF_TONE_DELAY_INT = "cdma_dtmf_tone_delay_int";

    /**
     * Override the platform's notion of a network operator being considered non roaming.
     * Value is string array of SIDs to be considered not roaming for 3GPP2 RATs.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_CDMA_NONROAMING_NETWORKS_STRING_ARRAY = "cdma_nonroaming_networks_string_array";

    /**
     * The CDMA roaming mode (aka CDMA system select).
     *
     * <p>The value should be one of the CDMA_ROAMING_MODE_ constants in {@link TelephonyManager}.
     * Values other than {@link TelephonyManager#CDMA_ROAMING_MODE_RADIO_DEFAULT} (which is the
     * default) will take precedence over user selection.
     *
     * @see TelephonyManager#CDMA_ROAMING_MODE_RADIO_DEFAULT
     * @see TelephonyManager#CDMA_ROAMING_MODE_HOME
     * @see TelephonyManager#CDMA_ROAMING_MODE_AFFILIATED
     * @see TelephonyManager#CDMA_ROAMING_MODE_ANY
     *
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_CDMA_ROAMING_MODE_INT = "cdma_roaming_mode_int";

    /**
     * Override the platform's notion of a network operator being considered roaming.
     * Value is string array of SIDs to be considered roaming for 3GPP2 RATs.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_CDMA_ROAMING_NETWORKS_STRING_ARRAY = "cdma_roaming_networks_string_array";

    /**
     * An array of cellular services supported by a subscription.
     *
     * <p>Permissible values include:
     * <ul>
     *   <li>{@code SubscriptionManager#SERVICE_CAPABILITY_VOICE} for voice services</li>
     *   <li>{@code SubscriptionManager#SERVICE_CAPABILITY_SMS} for SMS services</li>
     *   <li>{@code SubscriptionManager#SERVICE_CAPABILITY_DATA} for data services</li>
     * </ul>
     *
     * <p>Carrier-specific factors may influence how these services are supported. Therefore,
     * modifying this carrier configuration might not always enable the specified services. These
     * capability bitmasks should be considered as indicators of a carrier's preferred services
     * to enhance user experience, rather than as absolute platform guarantees.
     *
     * <p>Device-level service capabilities, defined by
     * {@code TelephonyManager#isDeviceVoiceCapable} and
     * {@code TelephonyManager#isDeviceSmsCapable}, take precedence over these subscription-level
     * settings. For instance, a device where {@code TelephonyManager#isDeviceVoiceCapable} returns
     * false may not be able to make voice calls, even if subscribed to a service marked as
     * voice-capable.
     *
     * <p>To determine a subscription's cellular service capabilities, use
     * {@code SubscriptionInfo#getServiceCapabilities()}. To track changes in services, register
     * a {@link SubscriptionManager.OnSubscriptionsChangedListener} and invoke the
     * same method in its callback.
     *
     * <p>Emergency service availability may not depend on the cellular service capabilities.
     * For example, emergency calls might be possible on a subscription even if it lacks
     * {@code SubscriptionManager#SERVICE_CAPABILITY_VOICE}.
     *
     * <p>If unset, the default value is “[1, 2, 3]” (supports all cellular services).
     *
     * @see TelephonyManager#isDeviceVoiceCapable
     * @see TelephonyManager#isDeviceSmsCapable
     * @see SubscriptionInfo#getServiceCapabilities()
     * @see SubscriptionManager.OnSubscriptionsChangedListener
     */
    public static final String KEY_CELLULAR_SERVICE_CAPABILITIES_INT_ARRAY = "cellular_service_capabilities_int_array";

    /**
     * Controls the cellular usage setting.
     *
     * The usage setting indicates whether a device will remain attached to a network based on
     * the primary use case for the service. A device will detach and search for a more-preferred
     * network if the primary use case (voice or data) is not satisfied. Depending on the type
     * of device, it may operate in a voice or data-centric mode by default.
     *
     * <p>Sets the usage setting in accordance with 3gpp 24.301 sec 4.3 and 3gpp 24.501 sec 4.3.
     * Also refer to "UE's usage setting" as defined in 3gpp 24.301 section 3.1 and 3gpp 23.221
     * Annex A.
     *
     * Either omit this key or pass a value of
     * {@link SubscriptionManager#USAGE_SETTING_UNKNOWN unknown} to preserve the current setting.
     *
     * <p>Devices that support configuration of the cellular usage setting, including devices
     * with HAL capability to set the cellular usage setting, must honor this setting accordingly.
     *
     * {@link SubscriptionManager#USAGE_SETTING_DEFAULT default},
     * {@link SubscriptionManager#USAGE_SETTING_VOICE_CENTRIC voice-centric},
     * or {@link SubscriptionManager#USAGE_SETTING_DATA_CENTRIC data-centric}.
     * {@see SubscriptionInfo#getUsageSetting}
     */
    public static final String KEY_CELLULAR_USAGE_SETTING_INT = "cellular_usage_setting_int";

    /**
     * Determines whether we should show a warning asking the user to check with their carrier
     * on pricing when the user enabled data roaming,
     * default to false.
     */
    public static final String KEY_CHECK_PRICING_WITH_CARRIER_FOR_DATA_ROAMING_BOOL = "check_pricing_with_carrier_data_roaming_bool";

    /**
     * Flag specifying whether an additional (client initiated) intent needs to be sent on System
     * update
     */
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_BOOL = "ci_action_on_sys_update_bool";

    /**
     * Extra to be included in the intent sent for additional action on System update
     */
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_EXTRA_STRING = "ci_action_on_sys_update_extra_string";

    /**
     * Value of extra included in intent sent for additional action on System update
     */
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_EXTRA_VAL_STRING = "ci_action_on_sys_update_extra_val_string";

    /**
     * Intent to be sent for the additional action on System update
     */
    public static final String KEY_CI_ACTION_ON_SYS_UPDATE_INTENT_STRING = "ci_action_on_sys_update_intent_string";

    /**
     * The package name containing the ImsService that will be bound to the telephony framework to
     * support IMS MMTEL feature functionality instead of the device default ImsService for this
     * subscription.
     */
    public static final String KEY_CONFIG_IMS_MMTEL_PACKAGE_OVERRIDE_STRING = "config_ims_mmtel_package_override_string";

    /**
     * The package name containing the ImsService that will be bound to the telephony framework to
     * support both IMS MMTEL and RCS feature functionality instead of the device default
     * ImsService for this subscription.
     * @deprecated Use {@link #KEY_CONFIG_IMS_MMTEL_PACKAGE_OVERRIDE_STRING} and
     * {@link #KEY_CONFIG_IMS_RCS_PACKAGE_OVERRIDE_STRING} instead to configure these values
     * separately. If any of those values are not empty, they will override this value.
     */
    @Deprecated
    public static final String KEY_CONFIG_IMS_PACKAGE_OVERRIDE_STRING = "config_ims_package_override_string";

    /**
     * The package name containing the ImsService that will be bound to the telephony framework to
     * support IMS RCS feature functionality instead of the device default ImsService for this
     * subscription.
     */
    public static final String KEY_CONFIG_IMS_RCS_PACKAGE_OVERRIDE_STRING = "config_ims_rcs_package_override_string";

    /**
     * Override the package that will manage {@link SubscriptionPlan}
     * information instead of the {@link CarrierService} that defines this
     * value.
     *
     * @see SubscriptionManager#getSubscriptionPlans(int)
     * @see SubscriptionManager#setSubscriptionPlans(int, java.util.List)
     */
    public static final String KEY_CONFIG_PLANS_PACKAGE_OVERRIDE_STRING = "config_plans_package_override_string";

    /**
     * Flag that specifies to use the user's own phone number as the voicemail number when there is
     * no pre-loaded voicemail number on the SIM card.
     * <p>
     * {@link #KEY_DEFAULT_VM_NUMBER_STRING} takes precedence over this flag.
     * <p>
     * If false, the system default (*86) will be used instead.
     */
    public static final String KEY_CONFIG_TELEPHONY_USE_OWN_NUMBER_FOR_VOICEMAIL_BOOL = "config_telephony_use_own_number_for_voicemail_bool";

    /**
     * Flag to indicate if Wi-Fi needs to be disabled in ECBM.
     */
    public static final String KEY_CONFIG_WIFI_DISABLE_IN_ECBM = "config_wifi_disable_in_ecbm";

    /**
     * Indexes of SPN format strings in crossSimSpnFormats.
     *
     * <p>Available options are:
     * <ul>
     * <li>  {@link #CROSS_SIM_SPN_FORMAT_CARRIER_NAME_ONLY}: %s</li>
     * <li>  {@link #CROSS_SIM_SPN_FORMAT_CARRIER_NAME_WITH_BRANDING}: %s Cross-SIM Calling</li>
     * </ul>
     * %s will be filled with carrier name
     */
    public static final String KEY_CROSS_SIM_SPN_FORMAT_INT = "cross_sim_spn_format_int";

    /**
     * If this is true, the SIM card (through Customer Service Profile EF file) will be able to
     * prevent manual operator selection. If false, this SIM setting will be ignored and manual
     * operator selection will always be available. See CPHS4_2.WW6, CPHS B.4.7.1 for more
     * information
     */
    public static final String KEY_CSP_ENABLED_BOOL = "csp_enabled_bool";

    /**
     * Controls if the device should automatically notify the user as they reach
     * their cellular data limit. When set to {@code false} the carrier is
     * expected to have implemented their own notification mechanism. {@code true} by default.
     */
    public static final String KEY_DATA_LIMIT_NOTIFICATION_BOOL = "data_limit_notification_bool";

    /**
     * Controls the cellular data limit.
     * <p>
     * If the user uses more than this amount of data in their billing cycle, as defined by
     * {@link #KEY_MONTHLY_DATA_CYCLE_DAY_INT}, cellular data will be turned off by the user's
     * phone. If the value is set to {@link #DATA_CYCLE_THRESHOLD_DISABLED}, the data limit will be
     * disabled.
     * <p>
     * This setting may be overridden by explicit user choice. By default,
     * {@link #DATA_CYCLE_USE_PLATFORM_DEFAULT} will be used.
     */
    public static final String KEY_DATA_LIMIT_THRESHOLD_BYTES_LONG = "data_limit_threshold_bytes_long";

    /**
     * Controls if the device should automatically notify the user when rapid
     * cellular data usage is observed. When set to {@code false} the carrier is
     * expected to have implemented their own notification mechanism. {@code true} by default.
     */
    public static final String KEY_DATA_RAPID_NOTIFICATION_BOOL = "data_rapid_notification_bool";

    /**
     * Data switch validation minimal interval, in milliseconds.
     *
     * If a connection to the default (Internet) PDN for the current subscription is validated on
     * a given operator within a given tracking area, re-validations to that matching operator will
     * be skipped if they would occur within the specified interval. Instead, the connection will
     * automatically considered validated.
     *
     * If the network was validated within the interval but the latest validation result was false,
     * the validation will not be skipped. If not set or set to 0, validation will not be skipped.
     *
     * The valid range of value is between 0 millisecond and 24 hours, inclusive in both sides. The
     * default value is 24 hours.
     *
     * @see android.net.NetworkCapabilities#NET_CAPABILITY_VALIDATED
     */
    public static final String KEY_DATA_SWITCH_VALIDATION_MIN_INTERVAL_MILLIS_LONG = "data_switch_validation_min_gap_long";

    /**
     * Upon data switching between subscriptions within a carrier group, if switch depends on
     * validation result, this value defines customized value of how long we wait for validation
     * success before we fail and revoke the switch.
     * Time out is in milliseconds.
     */
    public static final String KEY_DATA_SWITCH_VALIDATION_TIMEOUT_LONG = "data_switch_validation_timeout_long";

    /**
     * Controls if the device should automatically notify the user as they reach
     * their cellular data warning. When set to {@code false} the carrier is
     * expected to have implemented their own notification mechanism. {@code true} by default.
     */
    public static final String KEY_DATA_WARNING_NOTIFICATION_BOOL = "data_warning_notification_bool";

    /**
     * Controls the data usage warning.
     * <p>
     * If the user uses more than this amount of data in their billing cycle, as defined by
     * {@link #KEY_MONTHLY_DATA_CYCLE_DAY_INT}, the user will be alerted about the usage.
     * If the value is set to {@link #DATA_CYCLE_THRESHOLD_DISABLED}, the data usage warning will
     * be disabled.
     * <p>
     * This setting may be overridden by explicit user choice. By default,
     * {@link #DATA_CYCLE_USE_PLATFORM_DEFAULT} will be used.
     */
    public static final String KEY_DATA_WARNING_THRESHOLD_BYTES_LONG = "data_warning_threshold_bytes_long";

    /**
     * The default sim call manager to use when the default dialer doesn't implement one. A sim call
     * manager can control and route outgoing and incoming phone calls, even if they're placed
     * using another connection service (PSTN, for example).
     */
    public static final String KEY_DEFAULT_SIM_CALL_MANAGER_STRING = "default_sim_call_manager_string";

    /**
     * Where there is no preloaded voicemail number on a SIM card, specifies the carrier's default
     * voicemail number while the device is both roaming and not registered for IMS.
     * When empty string, no default voicemail number is specified for roaming network and
     * unregistered state in IMS.
     */
    public static final String KEY_DEFAULT_VM_NUMBER_ROAMING_AND_IMS_UNREGISTERED_STRING = "default_vm_number_roaming_and_ims_unregistered_string";

    /**
     * Where there is no preloaded voicemail number on a SIM card, specifies the carrier's default
     * voicemail number.
     * When empty string, no default voicemail number is specified.
     */
    public static final String KEY_DEFAULT_VM_NUMBER_STRING = "default_vm_number_string";

    /**
     * Specifies a map from dialstrings to replacements for roaming network service numbers which
     * cannot be replaced on the carrier side.
     * <p>
     * Individual entries have the format:
     * [dialstring to replace]:[replacement]
     */
    public static final String KEY_DIAL_STRING_REPLACE_STRING_ARRAY = "dial_string_replace_string_array";

    /**
     * Disables dialing "*228" (OTASP provisioning) on CDMA carriers where it is not supported or is
     * potentially harmful by locking the SIM to 3G.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_DISABLE_CDMA_ACTIVATION_CODE_BOOL = "disable_cdma_activation_code_bool";

    /**
     * The flag to disable the popup dialog which warns the user of data charges.
     */
    public static final String KEY_DISABLE_CHARGE_INDICATION_BOOL = "disable_charge_indication_bool";

    /**
     * Boolean indicating the Supplementary Services(SS) is disable when airplane mode on in the
     * Call Settings menu.
     * {@code true}: SS is disable when airplane mode on.
     * {@code false}: SS is enable when airplane mode on.
     * The default value for this key is {@code false}
     */
    public static final String KEY_DISABLE_SUPPLEMENTARY_SERVICES_IN_AIRPLANE_MODE_BOOL = "disable_supplementary_services_in_airplane_mode_bool";

    /**
     * DisconnectCause array to play busy tone. Value should be array of
     * {@link android.telephony.DisconnectCause}.
     */
    public static final String KEY_DISCONNECT_CAUSE_PLAY_BUSYTONE_INT_ARRAY = "disconnect_cause_play_busytone_int_array";

    /**
     * Determine whether or not to display a call strength indicator for this carrier in the System
     * UI. Disabling the indication may be reasonable if the carrier's calling is not integrated
     * into the Android telephony stack (e.g. it is OTT).
     *
     * true: Use telephony APIs to detect the current networking medium of calling and display a
     *       UI indication based on the current strength (e.g. signal level) of that medium.
     * false: Do not display the call strength indicator.
     */
    public static final String KEY_DISPLAY_CALL_STRENGTH_INDICATOR_BOOL = "display_call_strength_indicator_bool";

    /**
     * Determines whether High Definition audio property is displayed in the dialer UI.
     * If {@code false}, remove the HD audio property from the connection so that HD audio related
     * UI is not displayed. If {@code true}, keep HD audio property as it is configured.
     */
    public static final String KEY_DISPLAY_HD_AUDIO_PROPERTY_BOOL = "display_hd_audio_property_bool";

    /**
     * When {@code true}, if the user is in an ongoing video call over WIFI and answers an incoming
     * audio call, the video call will be disconnected before the audio call is answered. This is
     * in contrast to the usual expected behavior where a foreground video call would be put into
     * the background and held when an incoming audio call is answered.
     */
    public static final String KEY_DROP_VIDEO_CALL_WHEN_ANSWERING_AUDIO_CALL_BOOL = "drop_video_call_when_answering_audio_call_bool";

    /**
     * Flag indicating if dtmf tone type is enabled
     */
    public static final String KEY_DTMF_TYPE_ENABLED_BOOL = "dtmf_type_enabled_bool";

    /**
     * The duration in seconds that platform call and message blocking is disabled after the user
     * contacts emergency services. Platform considers values for below cases:
     *  1) 0 <= VALUE <= 604800(one week): the value will be used as the duration directly.
     *  2) VALUE > 604800(one week): will use the default value as duration instead.
     *  3) VALUE < 0: block will be disabled forever until user re-enable block manually,
     *     the suggested value to disable forever is -1.
     * See {@code android.provider.BlockedNumberContract#notifyEmergencyContact(Context)}
     * See {@code android.provider.BlockedNumberContract#isBlocked(Context, String)}.
     */
    public static final String KEY_DURATION_BLOCKING_DISABLED_AFTER_EMERGENCY_INT = "duration_blocking_disabled_after_emergency_int";

    /**
     * Determine whether user can toggle Enhanced 4G LTE Mode in Settings.
     */
    public static final String KEY_EDITABLE_ENHANCED_4G_LTE_BOOL = "editable_enhanced_4g_lte_bool";

    /**
     * Since the default voicemail number is empty, if a SIM card does not have a voicemail number
     * available the user cannot use voicemail. This flag allows the user to edit the voicemail
     * number in such cases, and is false by default.
     */
    public static final String KEY_EDITABLE_VOICEMAIL_NUMBER_BOOL = "editable_voicemail_number_bool";

    /**
     * Determine whether user can edit voicemail number in Settings.
     */
    public static final String KEY_EDITABLE_VOICEMAIL_NUMBER_SETTING_BOOL = "editable_voicemail_number_setting_bool";

    /**
     * Determine whether user can switch Wi-Fi preferred or Cellular preferred
     * in calling preference.
     * Some operators support Wi-Fi Calling only, not VoLTE.
     * They don't need "Cellular preferred" option.
     * In this case, set uneditable attribute for preferred preference.
     */
    public static final String KEY_EDITABLE_WFC_MODE_BOOL = "editable_wfc_mode_bool";

    /**
     * Determine whether user can change Wi-Fi Calling preference in roaming.
     * {@code false} - roaming preference cannot be changed by user independently. If
     *                 {@link #KEY_USE_WFC_HOME_NETWORK_MODE_IN_ROAMING_NETWORK_BOOL} is false,
     *                 {@link #KEY_CARRIER_DEFAULT_WFC_IMS_ROAMING_MODE_INT} is used as the default
     *                 value. If {@link #KEY_USE_WFC_HOME_NETWORK_MODE_IN_ROAMING_NETWORK_BOOL} is
     *                 true, roaming preference is the same as home preference and
     *                 {@link #KEY_CARRIER_DEFAULT_WFC_IMS_MODE_INT} is used as the default value.
     * {@code true}  - roaming preference can be changed by user independently if
     *                 {@link #KEY_USE_WFC_HOME_NETWORK_MODE_IN_ROAMING_NETWORK_BOOL} is false. If
     *                 {@link #KEY_USE_WFC_HOME_NETWORK_MODE_IN_ROAMING_NETWORK_BOOL} is true, this
     *                 configuration is ignored and roaming preference cannot be changed.
     */
    public static final String KEY_EDITABLE_WFC_ROAMING_MODE_BOOL = "editable_wfc_roaming_mode_bool";

    /**
     * Time delay (in ms) after which we show the notification for emergency calls,
     * while the device is registered over WFC. Default value is -1, which indicates
     * that this notification is not pertinent for a particular carrier. We've added a delay
     * to prevent false positives.
     */
    public static final String KEY_EMERGENCY_NOTIFICATION_DELAY_INT = "emergency_notification_delay_int";

    /**
     * Indicates zero or more emergency number prefix(es), because some carrier requires
     * if users dial an emergency number address with a specific prefix, the combination of the
     * prefix and the address is also a valid emergency number to dial. For example, an emergency
     * number prefix is 318, and the emergency number is 911. Both 318911 and 911 can be dialed by
     * users for emergency call. An empty array of string indicates that current carrier does not
     * have this requirement.
     */
    public static final String KEY_EMERGENCY_NUMBER_PREFIX_STRING_ARRAY = "emergency_number_prefix_string_array";

    /**
     * Flag specifying whether cross sim calling on opportunistic data is supported for carrier.
     * When {@code false} the carrier does not support cross sim calling on opportunistic data.
     * When {@code true} the carrier does support cross sim calling on opportunistic data.
     */
    public static final String KEY_ENABLE_CROSS_SIM_CALLING_ON_OPPORTUNISTIC_DATA_BOOL = "enable_cross_sim_calling_on_opportunistic_data_bool";

    /**
     * If true, enable vibration (haptic feedback) for key presses in the EmergencyDialer activity.
     * The pattern is set on a per-platform basis using config_virtualKeyVibePattern. To be
     * consistent with the regular Dialer, this value should agree with the corresponding values
     * from config.xml under apps/Contacts.
     */
    public static final String KEY_ENABLE_DIALER_KEY_VIBRATION_BOOL = "enable_dialer_key_vibration_bool";

    /**
     * Sets the default state for the "Enhanced 4G LTE" or "Advanced Calling" mode toggle set by the
     * user. When this is {@code true}, this mode by default is on, otherwise if {@code false},
     * this mode by default is off.
     */
    public static final String KEY_ENHANCED_4G_LTE_ON_BY_DEFAULT_BOOL = "enhanced_4g_lte_on_by_default_bool";

    /**
     * The index indicates the carrier specified title string of Enhanced 4G LTE Mode settings.
     * Default value is 0, which indicates the default title string.
     */
    public static final String KEY_ENHANCED_4G_LTE_TITLE_VARIANT_INT = "enhanced_4g_lte_title_variant_int";

    /**
     * This timer value is used in the eSIM Exponential Backoff download retry algorithm.
     * Value should be in seconds.
     * <OL>
     *     <LI>When the first download failure occurs, retry download after BACKOFF_TIMER_VALUE
     * seconds.</LI>
     *
     * <LI>If download fails again then, retry after either BACKOFF_TIMER_VALUE,
     * 2xBACKOFF_TIMER_VALUE, or 3xBACKOFF_TIMER_VALUE seconds.</LI>
     *
     * <LI>In general after the cth failed attempt, retry after k * BACKOFF_TIMER_VALUE
     * seconds, where k is a random integer between 1 and 2^c − 1. Max c value is
     * {@link #KEY_ESIM_MAX_DOWNLOAD_RETRY_ATTEMPTS_INT}</LI>
     * </OL>
     */
    public static final String KEY_ESIM_DOWNLOAD_RETRY_BACKOFF_TIMER_SEC_INT = "esim_download_retry_backoff_timer_sec_int";

    /**
     * If eSIM profile download fails then, the number of retry attempts by UE
     * will be based on this configuration. If download still fails even after the
     * MAX attempts configured by this item then the retry is postponed until next
     * device bootup.
     */
    public static final String KEY_ESIM_MAX_DOWNLOAD_RETRY_ATTEMPTS_INT = "esim_max_download_retry_attempts_int";

    /**
     * Override the platform's notion of a network operator being considered non roaming.
     * If true all networks are considered as home network a.k.a. non-roaming. When false,
     * the 2 pairs of CMDA and GSM roaming/non-roaming arrays are consulted.
     *
     * @see #KEY_GSM_ROAMING_NETWORKS_STRING_ARRAY
     * @see #KEY_GSM_NONROAMING_NETWORKS_STRING_ARRAY
     * @see #KEY_CDMA_ROAMING_NETWORKS_STRING_ARRAY
     * @see #KEY_CDMA_NONROAMING_NETWORKS_STRING_ARRAY
     */
    public static final String KEY_FORCE_HOME_NETWORK_BOOL = "force_home_network_bool";

    /**
     * Specifies the amount of gap to be added in millis between postdial DTMF tones. When a
     * non-zero value is specified, the UE shall wait for the specified amount of time before it
     * sends out successive DTMF tones on the network.
     */
    public static final String KEY_GSM_DTMF_TONE_DELAY_INT = "gsm_dtmf_tone_delay_int";

    /**
     * Override the platform's notion of a network operator being considered not roaming.
     * Value is string array of MCCMNCs to be considered not roaming for 3GPP RATs.
     */
    public static final String KEY_GSM_NONROAMING_NETWORKS_STRING_ARRAY = "gsm_nonroaming_networks_string_array";

    /**
     * Override the platform's notion of a network operator being considered roaming.
     * Value is string array of MCCMNCs to be considered roaming for 3GPP RATs.
     */
    public static final String KEY_GSM_ROAMING_NETWORKS_STRING_ARRAY = "gsm_roaming_networks_string_array";

    /**
     * Determines if device implements a noise suppression device for in call audio.
     */
    public static final String KEY_HAS_IN_CALL_NOISE_SUPPRESSION_BOOL = "has_in_call_noise_suppression_bool";

    /**
     * Control whether users can reach the carrier portions of Cellular Network Settings.
     */
    public static final String KEY_HIDE_CARRIER_NETWORK_SETTINGS_BOOL = "hide_carrier_network_settings_bool";

    /**
     * Determines whether the Enhanced 4G LTE toggle will be shown in the settings. When this
     * option is {@code true}, the toggle will be hidden regardless of whether the device and
     * carrier supports 4G LTE or not.
     */
    public static final String KEY_HIDE_ENHANCED_4G_LTE_BOOL = "hide_enhanced_4g_lte_bool";

    /**
     * Determine whether IMS apn can be shown.
     */
    public static final String KEY_HIDE_IMS_APN_BOOL = "hide_ims_apn_bool";

    /**
     * Boolean indicating if LTE+ icon should be shown if available.
     */
    public static final String KEY_HIDE_LTE_PLUS_DATA_ICON_BOOL = "hide_lte_plus_data_icon_bool";

    /**
     * Determine whether preferred network type can be shown.
     */
    public static final String KEY_HIDE_PREFERRED_NETWORK_TYPE_BOOL = "hide_preferred_network_type_bool";

    /**
     * Flag to hide Preset APN details. If true, user cannot enter ApnEditor view of Preset APN,
     * and cannot view details of the APN. If false, user can enter ApnEditor view of Preset APN.
     * Default value is false.
     */
    public static final String KEY_HIDE_PRESET_APN_DETAILS_BOOL = "hide_preset_apn_details_bool";

    /**
     * Control whether users can reach the SIM lock settings.
     */
    public static final String KEY_HIDE_SIM_LOCK_SETTINGS_BOOL = "hide_sim_lock_settings_bool";

    /**
     * Indicates if the TTY HCO and VCO options should be hidden in the accessibility menu
     * if the device is capable of RTT.
     */
    public static final String KEY_HIDE_TTY_HCO_VCO_WITH_RTT_BOOL = "hide_tty_hco_vco_with_rtt";

    /**
     * When {@code true}, changes to the mobile data enabled switch will not cause the VT
     * registration state to change. That is, turning on or off mobile data will not cause VT to be
     * enabled or disabled.
     * When {@code false}, disabling mobile data will cause VT to be de-registered.
     */
    public static final String KEY_IGNORE_DATA_ENABLED_CHANGED_FOR_VIDEO_CALLS = "ignore_data_enabled_changed_for_video_calls";

    /**
     * When {@code true}, the phone will always tell the IMS stack to keep RTT enabled and
     * determine on a per-call basis (based on extras from the dialer app) whether a call should be
     * an RTT call or not.
     *
     * When {@code false}, the old behavior is used, where the toggle in accessibility settings is
     * used to set the IMS stack's RTT enabled state.
     */
    public static final String KEY_IGNORE_RTT_MODE_SETTING_BOOL = "ignore_rtt_mode_setting_bool";

    /**
     * Flag indicating whether the Phone app should ignore EVENT_SIM_NETWORK_LOCKED
     * events from the Sim.
     * If true, this will prevent the IccNetworkDepersonalizationPanel from being shown, and
     * effectively disable the "Sim network lock" feature.
     */
    public static final String KEY_IGNORE_SIM_NETWORK_LOCKED_EVENTS_BOOL = "ignore_sim_network_locked_events_bool";

    /**
     * Determines the maximum number of participants the carrier supports for a conference call.
     * This number is exclusive of the current device. A conference between 3 devices, for example,
     * would have a size limit of 2 participants.
     * Enforced when {@link #KEY_IS_IMS_CONFERENCE_SIZE_ENFORCED_BOOL} is {@code true}.
     */
    public static final String KEY_IMS_CONFERENCE_SIZE_LIMIT_INT = "ims_conference_size_limit_int";

    /**
     * Specifies the amount of gap to be added in millis between DTMF tones. When a non-zero value
     * is specified, the UE shall wait for the specified amount of time before it sends out
     * successive DTMF tones on the network.
     */
    public static final String KEY_IMS_DTMF_TONE_DELAY_INT = "ims_dtmf_tone_delay_int";

    /**
     * Indicating whether to include LTE cell bandwidths when determining whether the aggregated
     * cell bandwidth meets the required threshold for NR advanced.
     *
     * @see TelephonyDisplayInfo#OVERRIDE_NETWORK_TYPE_NR_ADVANCED
     */
    public static final String KEY_INCLUDE_LTE_FOR_NR_ADVANCED_THRESHOLD_BANDWIDTH_BOOL = "include_lte_for_nr_advanced_threshold_bandwidth_bool";

    /**
     * Determines whether a maximum size limit for IMS conference calls is enforced on the device.
     * When {@code true}, IMS conference calls will be limited to at most
     * {@link #KEY_IMS_CONFERENCE_SIZE_LIMIT_INT} participants. When {@code false}, no attempt is
     * made to limit the number of participants in a conference (the carrier will raise an error
     * when an attempt is made to merge too many participants into a conference).
     * <p>
     * Note: The maximum size of a conference can ONLY be supported where
     * {@link #KEY_SUPPORT_IMS_CONFERENCE_EVENT_PACKAGE_BOOL} is {@code true} since the platform
     * needs conference event package data to accurately know the number of participants in the
     * conference.
     */
    public static final String KEY_IS_IMS_CONFERENCE_SIZE_ENFORCED_BOOL = "is_ims_conference_size_enforced_bool";

    /**
     * A boolean property indicating whether this subscription should be managed as an opportunistic
     * subscription.
     *
     * If true, then this subscription will be selected based on available coverage and will not be
     * available for a user in settings menus for selecting macro network providers. If unset,
     * defaults to “false”.
     *
     * <p>This key will work all the way back to {@link android.os.Build.VERSION_CODES#Q}.
     */
    public static final String KEY_IS_OPPORTUNISTIC_SUBSCRIPTION_BOOL = "is_opportunistic_subscription_bool";

    /**
     * Boolean to decide whether LTE is enabled.
     */
    public static final String KEY_LTE_ENABLED_BOOL = "lte_enabled_bool";

    /**
     * A list of 4 customized LTE Reference Signal Received Quality (RSRQ) thresholds.
     *
     * Reference: TS 136.133 v12.6.0 section 9.1.7 - RSRQ Measurement Report Mapping.
     *
     * 4 threshold integers must be within the boundaries [-34 dB, 3 dB], and the levels are:
     *     "NONE: [-34, threshold1)"
     *     "POOR: [threshold1, threshold2)"
     *     "MODERATE: [threshold2, threshold3)"
     *     "GOOD:  [threshold3, threshold4)"
     *     "EXCELLENT:  [threshold4, 3]"
     *
     * This key is considered invalid if the format is violated. If the key is invalid or
     * not configured, a default value set will apply.
     */
    public static final String KEY_LTE_RSRQ_THRESHOLDS_INT_ARRAY = "lte_rsrq_thresholds_int_array";

    /**
     * A list of 4 customized LTE Reference Signal Signal to Noise Ratio (RSSNR) thresholds.
     *
     * 4 threshold integers must be within the boundaries [-20 dB, 30 dB], and the levels are:
     *     "NONE: [-20, threshold1)"
     *     "POOR: [threshold1, threshold2)"
     *     "MODERATE: [threshold2, threshold3)"
     *     "GOOD:  [threshold3, threshold4)"
     *     "EXCELLENT:  [threshold4, 30]"
     *
     * This key is considered invalid if the format is violated. If the key is invalid or
     * not configured, a default value set will apply.
     */
    public static final String KEY_LTE_RSSNR_THRESHOLDS_INT_ARRAY = "lte_rssnr_thresholds_int_array";

    /**
     * When checking if a given number is the voicemail number, if this flag is true
     * then in addition to comparing the given number to the voicemail number, we also compare it
     * to the mdn. If this flag is false, the given number is only compared to the voicemail number.
     * By default this value is false.
     */
    public static final String KEY_MDN_IS_ADDITIONAL_VOICEMAIL_NUMBER_BOOL = "mdn_is_additional_voicemail_number_bool";

    public static final String KEY_MMS_ALIAS_ENABLED_BOOL = "aliasEnabled";

    public static final String KEY_MMS_ALIAS_MAX_CHARS_INT = "aliasMaxChars";

    public static final String KEY_MMS_ALIAS_MIN_CHARS_INT = "aliasMinChars";

    public static final String KEY_MMS_ALLOW_ATTACH_AUDIO_BOOL = "allowAttachAudio";

    public static final String KEY_MMS_APPEND_TRANSACTION_ID_BOOL = "enabledTransID";

    /**
     * If true, add "Connection: close" header to MMS HTTP requests so the connection
     * is immediately closed (disabling keep-alive).
     */
    public static final String KEY_MMS_CLOSE_CONNECTION_BOOL = "mmsCloseConnection";

    public static final String KEY_MMS_EMAIL_GATEWAY_NUMBER_STRING = "emailGatewayNumber";

    public static final String KEY_MMS_GROUP_MMS_ENABLED_BOOL = "enableGroupMms";

    public static final String KEY_MMS_HTTP_PARAMS_STRING = "httpParams";

    public static final String KEY_MMS_HTTP_SOCKET_TIMEOUT_INT = "httpSocketTimeout";

    public static final String KEY_MMS_MAX_IMAGE_HEIGHT_INT = "maxImageHeight";

    public static final String KEY_MMS_MAX_IMAGE_WIDTH_INT = "maxImageWidth";

    public static final String KEY_MMS_MAX_MESSAGE_SIZE_INT = "maxMessageSize";

    public static final String KEY_MMS_MESSAGE_TEXT_MAX_SIZE_INT = "maxMessageTextSize";

    public static final String KEY_MMS_MMS_DELIVERY_REPORT_ENABLED_BOOL = "enableMMSDeliveryReports";

    public static final String KEY_MMS_MMS_ENABLED_BOOL = "enabledMMS";

    public static final String KEY_MMS_MMS_READ_REPORT_ENABLED_BOOL = "enableMMSReadReports";

    public static final String KEY_MMS_MULTIPART_SMS_ENABLED_BOOL = "enableMultipartSMS";

    public static final String KEY_MMS_NAI_SUFFIX_STRING = "naiSuffix";

    /**
     * Waiting time in milliseconds used before releasing an MMS data call. Not tearing down an MMS
     * data connection immediately helps to reduce the message delivering latency if messaging
     * continues between all parties in the conversation since the same data connection can be
     * reused for further messages.
     *
     * This timer will control how long the data call will be kept alive before being torn down.
     */
    public static final String KEY_MMS_NETWORK_RELEASE_TIMEOUT_MILLIS_INT = "mms_network_release_timeout_millis_int";

    public static final String KEY_MMS_NOTIFY_WAP_MMSC_ENABLED_BOOL = "enabledNotifyWapMMSC";

    public static final String KEY_MMS_RECIPIENT_LIMIT_INT = "recipientLimit";

    public static final String KEY_MMS_SEND_MULTIPART_SMS_AS_SEPARATE_MESSAGES_BOOL = "sendMultipartSmsAsSeparateMessages";

    public static final String KEY_MMS_SHOW_CELL_BROADCAST_APP_LINKS_BOOL = "config_cellBroadcastAppLinks";

    public static final String KEY_MMS_SMS_DELIVERY_REPORT_ENABLED_BOOL = "enableSMSDeliveryReports";

    public static final String KEY_MMS_SMS_TO_MMS_TEXT_LENGTH_THRESHOLD_INT = "smsToMmsTextLengthThreshold";

    public static final String KEY_MMS_SMS_TO_MMS_TEXT_THRESHOLD_INT = "smsToMmsTextThreshold";

    public static final String KEY_MMS_SUBJECT_MAX_LENGTH_INT = "maxSubjectLength";

    public static final String KEY_MMS_SUPPORT_HTTP_CHARSET_HEADER_BOOL = "supportHttpCharsetHeader";

    public static final String KEY_MMS_SUPPORT_MMS_CONTENT_DISPOSITION_BOOL = "supportMmsContentDisposition";

    public static final String KEY_MMS_UA_PROF_TAG_NAME_STRING = "uaProfTagName";

    public static final String KEY_MMS_UA_PROF_URL_STRING = "uaProfUrl";

    public static final String KEY_MMS_USER_AGENT_STRING = "userAgent";

    /**
     * The day of the month (1-31) on which the data cycle rolls over.
     * <p>
     * If the current month does not have this day, the cycle will roll over at
     * the start of the next month.
     * <p>
     * This setting may be still overridden by explicit user choice. By default,
     * {@link #DATA_CYCLE_USE_PLATFORM_DEFAULT} will be used.
     */
    public static final String KEY_MONTHLY_DATA_CYCLE_DAY_INT = "monthly_data_cycle_day_int";

    /**
     * This threshold is used when connected to a non-terrestrial LTE network.
     * A list of 4 NTN LTE RSRP thresholds above which a signal level is considered POOR,
     * MODERATE, GOOD, or EXCELLENT, to be used in SignalStrength reporting.
     *
     * Note that the min and max thresholds are fixed at -140 and -44, as explained in
     * TS 136.133 9.1.4 - RSRP Measurement Report Mapping.
     * <p>
     * See SignalStrength#MAX_LTE_RSRP and SignalStrength#MIN_LTE_RSRP. Any signal level outside
     * these boundaries is considered invalid.
     */
    public static final String KEY_NTN_LTE_RSRP_THRESHOLDS_INT_ARRAY = "ntn_lte_rsrp_thresholds_int_array";

    /**
     * This threshold is used when connected to a non-terrestrial LTE network.
     * A list of 4 customized NTN LTE Reference Signal Received Quality (RSRQ) thresholds.
     *
     * Reference: TS 136.133 v12.6.0 section 9.1.7 - RSRQ Measurement Report Mapping.
     *
     * 4 threshold integers must be within the boundaries [-34 dB, 3 dB], and the levels are:
     *     "NONE: [-34, threshold1)"
     *     "POOR: [threshold1, threshold2)"
     *     "MODERATE: [threshold2, threshold3)"
     *     "GOOD:  [threshold3, threshold4)"
     *     "EXCELLENT:  [threshold4, 3]"
     *
     * This key is considered invalid if the format is violated. If the key is invalid or
     * not configured, a default value set will apply.
     */
    public static final String KEY_NTN_LTE_RSRQ_THRESHOLDS_INT_ARRAY = "ntn_lte_rsrq_thresholds_int_array";

    /**
     * This threshold is used when connected to a non-terrestrial LTE network.
     * A list of 4 customized NTN LTE Reference Signal Signal to Noise Ratio (RSSNR) thresholds.
     *
     * 4 threshold integers must be within the boundaries [-20 dB, 30 dB], and the levels are:
     *     "NONE: [-20, threshold1)"
     *     "POOR: [threshold1, threshold2)"
     *     "MODERATE: [threshold2, threshold3)"
     *     "GOOD:  [threshold3, threshold4)"
     *     "EXCELLENT:  [threshold4, 30]"
     *
     * This key is considered invalid if the format is violated. If the key is invalid or
     * not configured, a default value set will apply.
     */
    public static final String KEY_NTN_LTE_RSSNR_THRESHOLDS_INT_ARRAY = "ntn_lte_rssnr_thresholds_int_array";

    /**
     * Only allow auto selection in Advanced Network Settings when in home network.
     * Manual selection is allowed when in roaming network.
     */
    public static final String KEY_ONLY_AUTO_SELECT_IN_HOME_NETWORK_BOOL = "only_auto_select_in_home_network";

    /**
     * List of network type constants which support only a single data connection at a time.
     * Some carriers do not support multiple PDP on UMTS.
     * @see TelephonyManager NETWORK_TYPE_*
     * @see #KEY_ONLY_SINGLE_DC_ALLOWED_INT_ARRAY
     */
    public static final String KEY_ONLY_SINGLE_DC_ALLOWED_INT_ARRAY = "only_single_dc_allowed_int_array";

    /**
     * Control whether users can choose a network operator.
     */
    public static final String KEY_OPERATOR_SELECTION_EXPAND_BOOL = "operator_selection_expand_bool";

    /**
     * Controls back off time in milli seconds for switching back to
     * opportunistic subscription. This time will be added to
     * {@link CarrierConfigManager#KEY_OPPORTUNISTIC_NETWORK_DATA_SWITCH_HYSTERESIS_TIME_LONG} to
     * determine hysteresis time if there is ping pong situation
     * (determined by system app or 1st party app) between primary and opportunistic
     * subscription. Ping ping situation is defined in
     * #KEY_OPPORTUNISTIC_NETWORK_PING_PONG_TIME_LONG.
     * If ping pong situation continuous #KEY_OPPORTUNISTIC_NETWORK_BACKOFF_TIME_LONG
     * will be added to previously determined hysteresis time.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_BACKOFF_TIME_LONG = "opportunistic_network_backoff_time_long";

    /**
     * Controls hysteresis time in milli seconds for which OpportunisticNetworkService
     * will wait before switching data from opportunistic network to primary network.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_DATA_SWITCH_EXIT_HYSTERESIS_TIME_LONG = "opportunistic_network_data_switch_exit_hysteresis_time_long";

    /**
     * Controls hysteresis time in milli seconds for which OpportunisticNetworkService
     * will wait before switching data to an opportunistic network.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_DATA_SWITCH_HYSTERESIS_TIME_LONG = "opportunistic_network_data_switch_hysteresis_time_long";

    /**
     * Controls hysteresis time in milli seconds for which OpportunisticNetworkService
     * will wait before attaching to a network.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_ENTRY_OR_EXIT_HYSTERESIS_TIME_LONG = "opportunistic_network_entry_or_exit_hysteresis_time_long";

    /**
     * Controls bandwidth threshold in Kbps at which OpportunisticNetworkService will decide whether
     * the opportunistic network is good enough for internet data.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_ENTRY_THRESHOLD_BANDWIDTH_INT = "opportunistic_network_entry_threshold_bandwidth_int";

    /**
     * Controls RSRP threshold, in dBm, at which OpportunisticNetworkService will decide whether
     * the opportunistic network is good enough for internet data.
     *
     * <p>The value of {@link CellSignalStrengthLte#getRsrp()} will be compared with this
     * threshold.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_ENTRY_THRESHOLD_RSRP_INT = "opportunistic_network_entry_threshold_rsrp_int";

    /**
     * Controls RSSNR threshold, in dB, at which OpportunisticNetworkService will
     * decide whether the opportunistic network is good enough for internet data.
     *
     * <p>The value of {@link CellSignalStrengthLte#getRssnr()} will be compared with this
     * threshold.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_ENTRY_THRESHOLD_RSSNR_INT = "opportunistic_network_entry_threshold_rssnr_int";

    /**
     * Controls RSRP threshold, in dBm, below which OpportunisticNetworkService will decide whether
     * the opportunistic network available is not good enough for internet data.
     *
     * <p>The value of {@link CellSignalStrengthLte#getRsrp()} will be compared with this
     * threshold.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_EXIT_THRESHOLD_RSRP_INT = "opportunistic_network_exit_threshold_rsrp_int";

    /**
     * Controls RSSNR threshold, in dB, below which OpportunisticNetworkService will
     * decide whether the opportunistic network available is not good enough for internet data.
     *
     * <p>The value of {@link CellSignalStrengthLte#getRssnr()} will be compared with this
     * threshold.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_EXIT_THRESHOLD_RSSNR_INT = "opportunistic_network_exit_threshold_rssnr_int";

    /**
     * Controls the max back off time in milli seconds for switching back to
     * opportunistic subscription.
     * This time will be the max hysteresis that can be determined irrespective of there is
     * continuous ping pong situation or not as described in
     * #KEY_OPPORTUNISTIC_NETWORK_PING_PONG_TIME_LONG and
     * #KEY_OPPORTUNISTIC_NETWORK_BACKOFF_TIME_LONG.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_MAX_BACKOFF_TIME_LONG = "opportunistic_network_max_backoff_time_long";

    /**
     * Controls the ping pong determination of opportunistic network.
     * If opportunistic network is determined as out of service or below
     * #KEY_OPPORTUNISTIC_NETWORK_EXIT_THRESHOLD_RSRP_INT or
     * #KEY_OPPORTUNISTIC_NETWORK_EXIT_THRESHOLD_RSSNR_INT within
     * #KEY_OPPORTUNISTIC_NETWORK_PING_PONG_TIME_LONG of switching to opportunistic network,
     * it will be determined as ping pong situation by system app or 1st party app.
     */
    public static final String KEY_OPPORTUNISTIC_NETWORK_PING_PONG_TIME_LONG = "opportunistic_network_ping_pong_time_long";

    /**
     * This threshold is used when connected to a non-terrestrial LTE network.
     * Bit-field integer to determine whether to use Reference Signal Received Power (RSRP),
     * Reference Signal Received Quality (RSRQ), or/and Reference Signal Signal to Noise Ratio
     * (RSSNR) for the number of NTN LTE signal bars and signal criteria reporting enabling.
     *
     * <p> If a measure is not set, signal criteria reporting from modem will not be triggered and
     * not be used for calculating signal level. If multiple measures are set bit, the parameter
     * whose value is smallest is used to indicate the signal level.
     * <UL>
     *  <LI>RSRP = 1 << 0</LI>
     *  <LI>RSRQ = 1 << 1</LI>
     *  <LI>RSSNR = 1 << 2</LI>
     * </UL>
     * <p> The value of this key must be bitwise OR of CellSignalStrengthLte#USE_RSRP,
     * CellSignalStrengthLte#USE_RSRQ, CellSignalStrengthLte#USE_RSSNR.
     *
     * <p> For example, if both RSRP and RSRQ are used, the value of key is 3 (1 << 0 | 1 << 1).
     * If the key is invalid or not configured, a default value (RSRP = 1 << 0) will apply.
     */
    public static final String KEY_PARAMETERS_USED_FOR_NTN_LTE_SIGNAL_BAR_INT = "parameters_used_for_ntn_lte_signal_bar_int";

    /**
     * Controls whether to do ping test before switching data to opportunistic network.
     * This carrier config is used to disable this feature.
     */
    public static final String KEY_PING_TEST_BEFORE_DATA_SWITCH_BOOL = "ping_test_before_data_switch_bool";

    /**
     * Used in the Preferred Network Types menu to determine if the 2G option is displayed.
     * Value defaults to false as of Android T to discourage the use of insecure 2G protocols.
     */
    public static final String KEY_PREFER_2G_BOOL = "prefer_2g_bool";

    /**
     * Used in the Preferred Network Types menu to determine if the 3G option is displayed.
     */
    public static final String KEY_PREFER_3G_VISIBILITY_BOOL = "prefer_3g_visibility_bool";

    /**
     * The maximum number of times in a day that we display the notification for a performance boost
     * via premium capabilities when
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}
     * returns a failure due to user action or timeout.
     *
     * The default value is 2 times.
     *
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_USER_CANCELED
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_TIMEOUT
     */
    public static final String KEY_PREMIUM_CAPABILITY_MAXIMUM_DAILY_NOTIFICATION_COUNT_INT = "premium_capability_maximum_daily_notification_count_int";

    /**
     * The maximum number of times in a month that we display the notification for a performance
     * boost via premium capabilities when
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}
     * returns a failure due to user action or timeout.
     *
     * The default value is 10 times.
     *
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_USER_CANCELED
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_TIMEOUT
     */
    public static final String KEY_PREMIUM_CAPABILITY_MAXIMUM_MONTHLY_NOTIFICATION_COUNT_INT = "premium_capability_maximum_monthly_notification_count_int";

    /**
     * The amount of time in milliseconds within which the network must set up a slicing
     * configuration for the premium capability after
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}
     * returns {@link TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_SUCCESS}.
     * During the setup time, calls to
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)} will return
     * {@link TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_PENDING_NETWORK_SETUP}.
     * If the network fails to set up a slicing configuration for the premium capability within the
     * setup time, subsequent purchase requests will be allowed to go through again.
     *
     * The default value is 5 minutes.
     */
    public static final String KEY_PREMIUM_CAPABILITY_NETWORK_SETUP_TIME_MILLIS_LONG = "premium_capability_network_setup_time_millis_long";

    /**
     * The amount of time in milliseconds that the notification for a performance boost via
     * premium capabilities should be blocked when
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}
     * returns a failure due to user action or timeout.
     * The maximum number of performance boost notifications to show the user are defined in
     * {@link #KEY_PREMIUM_CAPABILITY_MAXIMUM_DAILY_NOTIFICATION_COUNT_INT} and
     * {@link #KEY_PREMIUM_CAPABILITY_MAXIMUM_MONTHLY_NOTIFICATION_COUNT_INT}.
     *
     * The default value is 30 minutes.
     *
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_USER_CANCELED
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_TIMEOUT
     */
    public static final String KEY_PREMIUM_CAPABILITY_NOTIFICATION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG = "premium_capability_notification_backoff_hysteresis_time_millis_long";

    /**
     * The amount of time in milliseconds the notification for a performance boost via
     * premium capabilities will be visible to the user after
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}
     * requests user action to purchase the boost from the carrier. Once the timeout expires,
     * the performance boost notification will be automatically dismissed and the request will fail
     * with {@link TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_TIMEOUT}.
     *
     * The default value is 30 minutes.
     */
    public static final String KEY_PREMIUM_CAPABILITY_NOTIFICATION_DISPLAY_TIMEOUT_MILLIS_LONG = "premium_capability_notification_display_timeout_millis_long";

    /**
     * The amount of time in milliseconds that the purchase request should be throttled when
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}
     * returns a failure due to the carrier.
     *
     * The default value is 30 minutes.
     *
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_CARRIER_ERROR
     * @see TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_ENTITLEMENT_CHECK_FAILED
     */
    public static final String KEY_PREMIUM_CAPABILITY_PURCHASE_CONDITION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG = "premium_capability_purchase_condition_backoff_hysteresis_time_millis_long";

    /**
     * The URL to redirect to when the user clicks on the notification for a performance boost via
     * premium capabilities after applications call
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}.
     * If the URL is empty or invalid, the purchase request will return
     * {@link TelephonyManager#PURCHASE_PREMIUM_CAPABILITY_RESULT_FEATURE_NOT_SUPPORTED}.
     *
     * This is empty by default.
     */
    public static final String KEY_PREMIUM_CAPABILITY_PURCHASE_URL_STRING = "premium_capability_purchase_url_string";

    /**
     * Whether to allow premium capabilities to be purchased when the device is connected to LTE.
     * If this is {@code true}, applications can call
     * {@link TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)}
     * when connected to {@link TelephonyManager#NETWORK_TYPE_LTE} to purchase and use
     * premium capabilities.
     * If this is {@code false}, applications can only purchase and use premium capabilities when
     * connected to {@link TelephonyManager#NETWORK_TYPE_NR}.
     *
     * This is {@code false} by default.
     */
    public static final String KEY_PREMIUM_CAPABILITY_SUPPORTED_ON_LTE_BOOL = "premium_capability_supported_on_lte_bool";

    /**
     * Flag specifying whether to prevent sending CLIR activation("*31#") and deactivation("#31#")
     * code only without dialing number.
     * When {@code true}, these are prevented, {@code false} otherwise.
     */
    public static final String KEY_PREVENT_CLIR_ACTIVATION_AND_DEACTIVATION_CODE_BOOL = "prevent_clir_activation_and_deactivation_code_bool";

    /**
     * A list of failure cause codes that will trigger a modem restart when telephony receiving
     * one of those during data setup. The cause codes are defined in 3GPP TS 24.008 Annex I and
     * TS 24.301 Annex B.
     */
    public static final String KEY_RADIO_RESTART_FAILURE_CAUSES_INT_ARRAY = "radio_restart_failure_causes_int_array";

    /**
     * Indicating whether to ratchet the aggregated cell bandwidths on receiving new values when
     * the device is in RRC IDLE mode.
     * The aggregated cell bandwidths are used for determining NR advanced state.
     *
     * If this is {@code true}, we will only update the aggregate cell bandwidths if the new
     * aggregate is higher than the current aggregate and the anchor NR cell is the same.
     * If this is {@code false}, we will always update the aggregate cell bandwidths when receiving
     * new values.
     */
    public static final String KEY_RATCHET_NR_ADVANCED_BANDWIDTH_IF_RRC_IDLE_BOOL = "ratchet_nr_advanced_bandwidth_if_rrc_idle_bool";

    /**
     * The RCS configuration server URL. This URL is used to initiate RCS provisioning.
     */
    public static final String KEY_RCS_CONFIG_SERVER_URL_STRING = "rcs_config_server_url_string";

    /**
     * APN fields that user is not allowed to modify.
     */
    public static final String KEY_READ_ONLY_APN_FIELDS_STRING_ARRAY = "read_only_apn_fields_string_array";

    /**
     * APN types that user is not allowed to modify.
     */
    public static final String KEY_READ_ONLY_APN_TYPES_STRING_ARRAY = "read_only_apn_types_string_array";

    /**
     * Flag to require or skip entitlement checks.
     * If true, entitlement checks will be executed if device has been configured for it,
     * If false, entitlement checks will be skipped.
     */
    public static final String KEY_REQUIRE_ENTITLEMENT_CHECKS_BOOL = "require_entitlement_checks_bool";

    /**
     * Flag indicating whether radio is to be restarted on error PDP_FAIL_REGULAR_DEACTIVATION
     * This is false by default.
     *
     * @deprecated Use {@link #KEY_RADIO_RESTART_FAILURE_CAUSES_INT_ARRAY} instead
     */
    @Deprecated
    public static final String KEY_RESTART_RADIO_ON_PDP_FAIL_REGULAR_DEACTIVATION_BOOL = "restart_radio_on_pdp_fail_regular_deactivation_bool";

    /**
     * Indicates if the carrier supports auto-upgrading a call to RTT when receiving a call from a
     * RTT-supported device.
     */
    public static final String KEY_RTT_AUTO_UPGRADE_BOOL = "rtt_auto_upgrade_bool";

    /**
     * Indicates if the carrier supports downgrading a RTT call to a voice call during the call.
     */
    public static final String KEY_RTT_DOWNGRADE_SUPPORTED_BOOL = "rtt_downgrade_supported_bool";

    /**
     * Flag indicating whether the carrier supports RTT over IMS.
     */
    public static final String KEY_RTT_SUPPORTED_BOOL = "rtt_supported_bool";

    /**
     * Indicates if the carrier supports RTT during a video call.
     */
    public static final String KEY_RTT_SUPPORTED_FOR_VT_BOOL = "rtt_supported_for_vt_bool";

    /**
     * Indicates whether RTT is supported while roaming.
     */
    public static final String KEY_RTT_SUPPORTED_WHILE_ROAMING_BOOL = "rtt_supported_while_roaming_bool";

    /**
     * Indicates if the carrier supports upgrading a voice call to an RTT call during the call.
     */
    public static final String KEY_RTT_UPGRADE_SUPPORTED_BOOL = "rtt_upgrade_supported_bool";

    /**
     * Indicates if the carrier supports upgrading a call that was previously a VT call to RTT.
     */
    public static final String KEY_RTT_UPGRADE_SUPPORTED_FOR_DOWNGRADED_VT_CALL_BOOL = "rtt_upgrade_supported_for_downgraded_vt_call";

    /**
     * This config enables modem to scan satellite PLMNs specified as per
     * {@link #KEY_CARRIER_SUPPORTED_SATELLITE_SERVICES_PER_PROVIDER_BUNDLE} and attach to same
     * in case cellular networks are not enabled. This will need specific agreement between
     * satellite provider and the carrier before enabling this flag.
     *
     * The default value is false.
     */
    public static final String KEY_SATELLITE_ATTACH_SUPPORTED_BOOL = "satellite_attach_supported_bool";

    /**
     * The carrier-enabled satellite connection hysteresis time in seconds for which the device
     * continues in satellite mode after it loses the connection with the satellite network.
     * <p>
     * If the device is in satellite mode, the following actions will be taken by the device:
     * <ul>
     * <li>System UI will continue showing the satellite icon.</li>
     * <li>When there is an ongoing emergency call, and the IMS is not registered, and cellular
     * service is not available, and the device is in satellite mode, a timer with a duration
     * defined by the overlay config
     * {@code config_emergency_call_wait_for_connection_timeout_millis} will be started. When the
     * timer expires, Telephony will send the event
     * {@link TelephonyManager#EVENT_DISPLAY_EMERGENCY_MESSAGE} to Dialer, which will then prompt
     * users to switch to using satellite emergency messaging.</li>
     * </ul>
     * <p>
     * The default value is 180 seconds.
     */
    public static final String KEY_SATELLITE_CONNECTION_HYSTERESIS_SEC_INT = "satellite_connection_hysteresis_sec_int";

    /**
     * An integer key holds the time interval for refreshing or re-querying the satellite
     * entitlement status from the entitlement server to ensure it is the latest.
     *
     * The default value is 7 days.
     */
    public static final String KEY_SATELLITE_ENTITLEMENT_STATUS_REFRESH_DAYS_INT = "satellite_entitlement_status_refresh_days_int";

    /**
     * This configuration enables device to query the entitlement server to get the satellite
     * configuration.
     * This will need agreement the carrier before enabling this flag.
     *
     * The default value is false.
     */
    public static final String KEY_SATELLITE_ENTITLEMENT_SUPPORTED_BOOL = "satellite_entitlement_supported_bool";

    /**
     * Indicate whether carrier roaming to satellite is using ESOS (Emergency SOS) which connects
     * to an emergency provider instead of PSAP (Public Safety Answering Point) for emergency
     * messaging.
     *
     * This will need agreement with carriers before enabling this flag.
     *
     * The default value is false.
     */
    public static final String KEY_SATELLITE_ESOS_SUPPORTED_BOOL = "satellite_esos_supported_bool";

    /**
     * An integer key holds the timeout duration in seconds used to determine whether to exit ESOS
     * mode and start TN scanning.
     *
     * The timer is started when the device is not connected, user is not pointing to the
     * satellite and no data transfer is happening.
     * When the timer expires, the device will move to IDLE mode upon which TN scanning will start.
     *
     * The default value is 600 seconds.
     */
    public static final String KEY_SATELLITE_ROAMING_ESOS_INACTIVITY_TIMEOUT_SEC_INT = "satellite_roaming_esos_inactivity_timeout_sec_int";

    /**
     * An integer key holds the timeout duration in seconds used to determine whether to exit P2P
     * SMS mode and start TN scanning.
     *
     * The timer is started when the device is not connected, user is not pointing to the
     * satellite and no data transfer is happening.
     * When the timer expires, the device will move to IDLE mode upon which TN scanning will start.
     *
     * The default value is 180 seconds.
     */
    public static final String KEY_SATELLITE_ROAMING_P2P_SMS_INACTIVITY_TIMEOUT_SEC_INT = "satellite_roaming_p2p_sms_inactivity_timeout_sec_int";

    /**
     * Indicate whether carrier roaming to satellite is using P2P SMS.
     *
     * This will need agreement with carriers before enabling this flag.
     *
     * The default value is false.
     */
    public static final String KEY_SATELLITE_ROAMING_P2P_SMS_SUPPORTED_BOOL = "satellite_roaming_p2p_sms_supported_bool";

    /**
     * An integer key holds the timeout duration in seconds used to determine whether to exit
     * carrier-roaming NB-IOT satellite mode.
     *
     * The timer is started when the device screen is turned off during a satellite session.
     * When the timer expires, the device exits Carrier Roaming NB IOT NTN.
     *
     * The default value is 30 seconds.
     */
    public static final String KEY_SATELLITE_ROAMING_SCREEN_OFF_INACTIVITY_TIMEOUT_SEC_INT = "satellite_roaming_screen_off_inactivity_timeout_sec_int";

    /**
     * Boolean indicating if default data account should show 4G icon when in 3G.
     */
    public static final String KEY_SHOW_4G_FOR_3G_DATA_ICON_BOOL = "show_4g_for_3g_data_icon_bool";

    /**
     * Boolean indicating if default data account should show LTE or 4G icon.
     */
    public static final String KEY_SHOW_4G_FOR_LTE_DATA_ICON_BOOL = "show_4g_for_lte_data_icon_bool";

    /**
     * Show APN Settings for some CDMA carriers
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_SHOW_APN_SETTING_CDMA_BOOL = "show_apn_setting_cdma_bool";

    /**
     * Flag specifying whether to show blocking pay phone option in blocked numbers screen.
     * Only show the option if payphone call presentation is present in the carrier's region.
     */
    public static final String KEY_SHOW_BLOCKING_PAY_PHONE_OPTION_BOOL = "show_blocking_pay_phone_option_bool";

    /**
     * Flag specifying whether to show notification(call blocking disabled) when Enhanced Call
     * Blocking(KEY_SUPPORT_ENHANCED_CALL_BLOCKING_BOOL) is enabled and making emergency call.
     * When true, notification is shown always.
     * When false, notification is shown only when any setting of "Enhanced Blocked number" is
     * enabled.
     */
    public static final String KEY_SHOW_CALL_BLOCKING_DISABLED_NOTIFICATION_ALWAYS_BOOL = "show_call_blocking_disabled_notification_always_bool";

    /**
     * Show cdma network mode choices 1x, 3G, global etc.
     *  @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_SHOW_CDMA_CHOICES_BOOL = "show_cdma_choices_bool";

    /**
     * Flag specifying whether to show forwarded number on call-in-progress screen.
     * When true, forwarded number is shown.
     * When false, forwarded number is not shown.
     */
    public static final String KEY_SHOW_FORWARDED_NUMBER_BOOL = "show_forwarded_number_bool";

    /**
     * Flag specifying whether ICCID is showed in SIM Status screen, default to false.
     */
    public static final String KEY_SHOW_ICCID_IN_SIM_STATUS_BOOL = "show_iccid_in_sim_status_bool";

    /**
     * Flag specifying whether IMS registration state menu is shown in Status Info setting,
     * default to false.
     */
    public static final String KEY_SHOW_IMS_REGISTRATION_STATUS_BOOL = "show_ims_registration_status_bool";

    /**
     * If true, show an onscreen "Dial" button in the dialer. In practice this is used on all
     * platforms, even the ones with hard SEND/END keys, but for maximum flexibility it's controlled
     * by a flag here (which can be overridden on a per-product basis.)
     */
    public static final String KEY_SHOW_ONSCREEN_DIAL_BUTTON_BOOL = "show_onscreen_dial_button_bool";

    /**
     * Config to show the roaming indicator (i.e. the "R" icon) from the status bar when roaming.
     * The roaming indicator will be shown if this is {@code true} and will not be shown if this is
     * {@code false}.
     */
    public static final String KEY_SHOW_ROAMING_INDICATOR_BOOL = "show_roaming_indicator_bool";

    /**
     * Flag specifying whether the {@link android.telephony.SignalStrength} is shown in the SIM
     * Status screen. The default value is true.
     */
    public static final String KEY_SHOW_SIGNAL_STRENGTH_IN_SIM_STATUS_BOOL = "show_signal_strength_in_sim_status_bool";

    /**
     * Flag specifying whether to show an alert dialog for video call charges.
     * By default this value is {@code false}.
     */
    public static final String KEY_SHOW_VIDEO_CALL_CHARGES_ALERT_DIALOG_BOOL = "show_video_call_charges_alert_dialog_bool";

    /**
     * Determines whether wifi calling location privacy policy is shown.
     */
    public static final String KEY_SHOW_WFC_LOCATION_PRIVACY_POLICY_BOOL = "show_wfc_location_privacy_policy_bool";

    /**
     * Control whether users receive a simplified network settings UI and improved network
     * selection.
     *
     * @deprecated Never implemented. Has no behavior impact when override. DO NOT USE.
     */
    @Deprecated
    public static final String KEY_SIMPLIFIED_NETWORK_SETTINGS_BOOL = "simplified_network_settings_bool";

    /**
     * String to override sim country iso.
     * Sim country iso is based on sim MCC which is coarse and doesn't work with dual IMSI SIM where
     * a SIM can have multiple MCC from different countries.
     * Instead, each sim carrier should have a single country code, apply per carrier based iso
     * code as an override. The overridden value can be read from
     * {@link TelephonyManager#getSimCountryIso()} and {@link SubscriptionInfo#getCountryIso()}
     */
    public static final String KEY_SIM_COUNTRY_ISO_OVERRIDE_STRING = "sim_country_iso_override_string";

    /**
     * Flag indicating whether the Phone app should provide a "Dismiss" button on the SIM network
     * unlock screen. The default value is true. If set to false, there will be *no way* to dismiss
     * the SIM network unlock screen if you don't enter the correct unlock code. (One important
     * consequence: there will be no way to make an Emergency Call if your SIM is network-locked and
     * you don't know the PIN.)
     */
    public static final String KEY_SIM_NETWORK_UNLOCK_ALLOW_DISMISS_BOOL = "sim_network_unlock_allow_dismiss_bool";

    /**
     * SMDP+ server address for downloading opportunistic eSIM profile.
     * FQDN (Fully Qualified Domain Name) of the SM-DP+ (e.g., smdp.gsma.com) restricted to the
     * Alphanumeric mode character set defined in table 5 of ISO/IEC 18004 excluding '$'.
     */
    public static final String KEY_SMDP_SERVER_ADDRESS_STRING = "smdp_server_address_string";

    /**
     * Determines if the carrier requires converting the destination number before sending out an
     * SMS. Certain networks and numbering plans require different formats.
     */
    public static final String KEY_SMS_REQUIRES_DESTINATION_NUMBER_CONVERSION_BOOL = "sms_requires_destination_number_conversion_bool";

    /**
     * The UUID of a Group of related subscriptions in which to place the current subscription.
     *
     * A grouped subscription will behave for billing purposes and other UI purposes as though it
     * is a transparent extension of other subscriptions in the group.
     *
     * <p>If set to {@link #REMOVE_GROUP_UUID_STRING}, then the subscription will be removed from
     * its current group.
     *
     * <p>This key will work all the way back to {@link android.os.Build.VERSION_CODES#Q}.
     */
    public static final String KEY_SUBSCRIPTION_GROUP_UUID_STRING = "subscription_group_uuid_string";

    /**
     * A list of premium capabilities the carrier supports. Applications can prompt users to
     * purchase these premium capabilities from their carrier for a performance boost.
     * Valid values are any of {@link TelephonyManager}'s {@code PREMIUM_CAPABILITY_*} constants.
     *
     * This is empty by default, indicating that no premium capabilities are supported.
     *
     * @see TelephonyManager#isPremiumCapabilityAvailableForPurchase(int)
     * @see TelephonyManager#purchasePremiumCapability(int, Executor, Consumer)
     */
    public static final String KEY_SUPPORTED_PREMIUM_CAPABILITIES_INT_ARRAY = "supported_premium_capabilities_int_array";

    /**
     * Indicates if the carrier supports a business call composer.
     */
    public static final String KEY_SUPPORTS_BUSINESS_CALL_COMPOSER_BOOL = "supports_business_call_composer_bool";

    /**
     * Indicates if the carrier supports call composer.
     */
    public static final String KEY_SUPPORTS_CALL_COMPOSER_BOOL = "supports_call_composer_bool";

    /**
     * Indicates whether the carrier supports the use of DTMF digits A-D for the purpose of device
     * to device communication while in a call.
     */
    public static final String KEY_SUPPORTS_DEVICE_TO_DEVICE_COMMUNICATION_USING_DTMF_BOOL = "supports_device_to_device_communication_using_dtmf_bool";

    /**
     * Indicates whether the carrier supports the use of RFC8285 compliant RTP header extensions for
     * the purpose of device to device communication while in a call.
     * <p>
     * See also {@link #KEY_SUPPORTS_SDP_NEGOTIATION_OF_D2D_RTP_HEADER_EXTENSIONS_BOOL}.
     */
    public static final String KEY_SUPPORTS_DEVICE_TO_DEVICE_COMMUNICATION_USING_RTP_BOOL = "supports_device_to_device_communication_using_rtp_bool";

    /**
     * Indicates whether the carrier supports the negotiations of RFC8285 compliant RTP header
     * extensions supported on a call during the Session Description Protocol (SDP). This option
     * is only used when {@link #KEY_SUPPORTS_DEVICE_TO_DEVICE_COMMUNICATION_USING_RTP_BOOL} is
     * {@code true}.
     * <p>
     * When {@code true}, the RTP header extensions the platform uses for device to device
     * communication will be offered to the remote end during the SDP negotiation process.
     * When {@code false}, the RTP header extensions will not be negotiated during the SDP
     * negotiation process and the platform will send RTP header extensions without prior
     * negotiation if {@link #KEY_SUPPORTS_DEVICE_TO_DEVICE_COMMUNICATION_USING_RTP_BOOL} is
     * {@code true}.
     */
    public static final String KEY_SUPPORTS_SDP_NEGOTIATION_OF_D2D_RTP_HEADER_EXTENSIONS_BOOL = "supports_sdp_negotiation_of_d2d_rtp_header_extensions_bool";

    /**
     * Indicates whether the carrier supports 3gpp call forwarding MMI codes while roaming. If
     * false, the user will be notified that call forwarding is not available when the MMI code
     * fails.
     */
    public static final String KEY_SUPPORT_3GPP_CALL_FORWARDING_WHILE_ROAMING_BOOL = "support_3gpp_call_forwarding_while_roaming_bool";

    /**
     * Determines whether conference participants can be added to existing call to form an adhoc
     * conference call (in contrast to merging calls to form a conference). When {@code true},
     * adding conference participants to existing call is supported, {@code false otherwise}.
     */
    public static final String KEY_SUPPORT_ADD_CONFERENCE_PARTICIPANTS_BOOL = "support_add_conference_participants_bool";

    /**
     * Determines whether adhoc conference calls are supported by a carrier. When {@code true},
     * adhoc conference calling is supported, {@code false otherwise}.
     */
    public static final String KEY_SUPPORT_ADHOC_CONFERENCE_CALLS_BOOL = "support_adhoc_conference_calls_bool";

    /**
     * Flag indicating whether to support "Network default" option in Caller ID settings for Calling
     * Line Identification Restriction (CLIR).
     */
    public static final String KEY_SUPPORT_CLIR_NETWORK_DEFAULT_BOOL = "support_clir_network_default_bool";

    /**
     * Determines whether conference calls are supported by a carrier. When {@code true},
     * conference calling is supported, {@code false otherwise}.
     */
    public static final String KEY_SUPPORT_CONFERENCE_CALL_BOOL = "support_conference_call_bool";

    /**
     * Flag indicating whether or not sending emergency SMS messages over IMS
     * is supported when in LTE/limited LTE (Emergency only) service mode..
     */
    public static final String KEY_SUPPORT_EMERGENCY_SMS_OVER_IMS_BOOL = "support_emergency_sms_over_ims_bool";

    /**
     * Determines whether to enable enhanced call blocking feature on the device.
     * android.provider.BlockedNumberContract.SystemContract#ENHANCED_SETTING_KEY_BLOCK_UNREGISTERED
     * android.provider.BlockedNumberContract.SystemContract#ENHANCED_SETTING_KEY_BLOCK_PRIVATE
     * android.provider.BlockedNumberContract.SystemContract#ENHANCED_SETTING_KEY_BLOCK_PAYPHONE
     * android.provider.BlockedNumberContract.SystemContract#ENHANCED_SETTING_KEY_BLOCK_UNKNOWN
     * android.provider.BlockedNumberContract.SystemContract#ENHANCED_SETTING_KEY_BLOCK_UNAVAILABLE
     *
     * <p>
     * 1. For Single SIM(SS) device, it can be customized in both carrier_config_mccmnc.xml
     *    and vendor.xml.
     * <p>
     * 2. For Dual SIM(DS) device, it should be customized in vendor.xml, since call blocking
     *    function is used regardless of SIM.
     * <p>
     * If {@code true} enable enhanced call blocking feature on the device, {@code false} otherwise.
     */
    public static final String KEY_SUPPORT_ENHANCED_CALL_BLOCKING_BOOL = "support_enhanced_call_blocking_bool";

    /**
     * Determines whether the IMS conference merge process supports and returns its participants
     * data. When {@code true}, on merge complete, conference call would have a list of its
     * participants returned in XML format, {@code false otherwise}.
     */
    public static final String KEY_SUPPORT_IMS_CONFERENCE_EVENT_PACKAGE_BOOL = "support_ims_conference_event_package_bool";

    /**
     * For IMS video over LTE calls, determines whether video pause signalling is supported.
     */
    public static final String KEY_SUPPORT_PAUSE_IMS_VIDEO_CALLS_BOOL = "support_pause_ims_video_calls_bool";

    /**
     * After a CDMA conference call is merged, the swap button should be displayed.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_SUPPORT_SWAP_AFTER_MERGE_BOOL = "support_swap_after_merge_bool";

    /**
     * Boolean to decide whether TD-SCDMA is supported.
     */
    public static final String KEY_SUPPORT_TDSCDMA_BOOL = "support_tdscdma_bool";

    /**
     * A list of mcc/mnc that support TD-SCDMA for device when connect to the roaming network.
     */
    public static final String KEY_SUPPORT_TDSCDMA_ROAMING_NETWORKS_STRING_ARRAY = "support_tdscdma_roaming_networks_string_array";

    /**
     * Controls whether to switch data to primary from opportunistic subscription
     * if primary is out of service. This control only affects system or 1st party app
     * initiated data switch, but will not override data switch initiated by privileged carrier apps
     * This carrier config is used to disable this feature.
     */
    public static final String KEY_SWITCH_DATA_TO_PRIMARY_IF_PRIMARY_IS_OOS_BOOL = "switch_data_to_primary_if_primary_is_oos_bool";

    /**
     * Flag indicating whether some telephony logic will treat a call which was formerly a video
     * call as if it is still a video call. When {@code true}:
     * <p>
     * Logic which will automatically drop a video call which takes place over WIFI when a
     * voice call is answered (see {@link #KEY_DROP_VIDEO_CALL_WHEN_ANSWERING_AUDIO_CALL_BOOL}.
     * <p>
     * Logic which determines whether the user can use TTY calling.
     */
    public static final String KEY_TREAT_DOWNGRADED_VIDEO_CALLS_AS_VIDEO_CALLS_BOOL = "treat_downgraded_video_calls_as_video_calls_bool";

    /**
     * Boolean flag indicating whether the carrier supports TTY.
     * <p>
     * Note that {@link #KEY_CARRIER_VOLTE_TTY_SUPPORTED_BOOL} controls availability of TTY over
     * VoLTE; if this carrier configuration is disabled, then
     * {@link #KEY_CARRIER_VOLTE_TTY_SUPPORTED_BOOL} is also implicitly disabled.
     * <p>
     * {@link TelecomManager#isTtySupported()} should be used to determine if a device supports TTY,
     * and this carrier config key should be used to see if the current carrier supports it.
     */
    public static final String KEY_TTY_SUPPORTED_BOOL = "tty_supported_bool";

    /**
     * A string array containing numbers that shouldn't be included in the call log.
     */
    public static final String KEY_UNLOGGABLE_NUMBERS_STRING_ARRAY = "unloggable_numbers_string_array";

    /**
     * Indicates if auto-configuration server is used for the RCS config
     * Reference: GSMA RCC.14
     */
    public static final String KEY_USE_ACS_FOR_RCS_BOOL = "use_acs_for_rcs_bool";

    /**
     * CDMA activation goes through HFA
     */
    public static final String KEY_USE_HFA_FOR_PROVISIONING_BOOL = "use_hfa_for_provisioning_bool";

    /**
     * Determines whether or not to use (IP) data connectivity as a supplemental condition to
     * control the visibility of the no-calling indicator for this carrier in the System UI. Setting
     * the configuration to true may make sense for carriers that provide OTT calling.
     *
     * Config = true: show no-calling indication only if telephony does not have voice registration
     *                and if no (IP) data connectivity is available.
     * Config = false: show no-calling indication only if telephony does not have voice
     *                 registration.
     */
    public static final String KEY_USE_IP_FOR_CALLING_INDICATOR_BOOL = "use_ip_for_calling_indicator_bool";

    /**
     * CDMA activation goes through OTASP.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final String KEY_USE_OTASP_FOR_PROVISIONING_BOOL = "use_otasp_for_provisioning_bool";

    /**
     * Flag indicating whether or not the carrier supports the periodic exchange of phone numbers
     * in the user's address book with the carrier's presence server in order to retrieve the RCS
     * capabilities for each contact used in the RCS User Capability Exchange (UCE) procedure. See
     * RCC.71, section 3 for more information.
     * <p>
     * The flag {@link Ims#KEY_ENABLE_PRESENCE_PUBLISH_BOOL} must also be enabled if this flag is
     * enabled, as sending a periodic SIP PUBLISH with this device's RCS capabilities is a
     * requirement for capability exchange to begin.
     * <p>
     * When presence is supported, the device should use the
     * {@link android.provider.ContactsContract.Data#CARRIER_PRESENCE} bit mask and set the
     * {@link android.provider.ContactsContract.Data#CARRIER_PRESENCE_VT_CAPABLE} bit to indicate
     * whether each contact supports video calling. The UI is made aware that presence is enabled
     * via {@link android.telecom.PhoneAccount#CAPABILITY_VIDEO_CALLING_RELIES_ON_PRESENCE}
     * and can choose to hide or show the video calling icon based on whether a contact supports
     * video.
     *
     * @deprecated No longer used in framework code, however it may still be used by applications
     * that have not updated their code. This config should still be set to {@code true} if
     * {@link Ims#KEY_RCS_BULK_CAPABILITY_EXCHANGE_BOOL} is set to {@code true} and
     * {@link Ims#KEY_ENABLE_PRESENCE_CAPABILITY_EXCHANGE_BOOL} is set to {@code true}.
     */
    @Deprecated
    public static final String KEY_USE_RCS_PRESENCE_BOOL = "use_rcs_presence_bool";

    /**
     * Flag indicating whether the carrier supports RCS SIP OPTIONS indication for
     * User Capability Exchange (UCE).
     */
    public static final String KEY_USE_RCS_SIP_OPTIONS_BOOL = "use_rcs_sip_options_bool";

    /**
     * Flag specifying whether the carrier will use the
     * WFC home network mode in roaming network.
     * {@code false} - roaming preference can be selected separately from the home preference.
     * {@code true}  - roaming preference is the same as home preference and
     *                 {@link #KEY_CARRIER_DEFAULT_WFC_IMS_MODE_INT} is used as the default value.
     */
    public static final String KEY_USE_WFC_HOME_NETWORK_MODE_IN_ROAMING_NETWORK_BOOL = "use_wfc_home_network_mode_in_roaming_network_bool";

    /**
     * Determine whether the voicemail notification is persistent in the notification bar. If true,
     * the voicemail notifications cannot be dismissed from the notification bar.
     */
    public static final String KEY_VOICEMAIL_NOTIFICATION_PERSISTENT_BOOL = "voicemail_notification_persistent_bool";

    /**
     * If true, removes the Voice Privacy option from Call Settings
     */
    public static final String KEY_VOICE_PRIVACY_DISABLE_UI_BOOL = "voice_privacy_disable_ui_bool";

    /**
     * If Voice Radio Technology is RIL_RADIO_TECHNOLOGY_LTE:14 or RIL_RADIO_TECHNOLOGY_UNKNOWN:0
     * this is the value that should be used instead. A configuration value of
     * RIL_RADIO_TECHNOLOGY_UNKNOWN:0 means there is no replacement value and that the default
     * assumption for phone type (GSM) should be used.
     */
    public static final String KEY_VOLTE_REPLACEMENT_RAT_INT = "volte_replacement_rat_int";

    /**
     * Flag specifying whether VoNR should be enabled for carrier.
     * If true, VoNr will be enabled. If false, hard disabled.
     *
     * Disabled by default.
     */
    public static final String KEY_VONR_ENABLED_BOOL = "vonr_enabled_bool";

    /**
     * Boolean indicating the default VoNR user preference setting.
     * If true, the VoNR setting will be enabled. If false, it will be disabled initially.
     *
     * Enabled by default.
     */
    public static final String KEY_VONR_ON_BY_DEFAULT_BOOL = "vonr_on_by_default_bool";

    /**
     * Boolean indicating if the VoNR setting is visible in the Call Settings menu.
     * If this flag is set and VoNR is enabled for this carrier (see {@link #KEY_VONR_ENABLED_BOOL})
     * the VoNR setting menu will be visible. If {@link #KEY_VONR_ENABLED_BOOL} or
     * this setting is false, the menu will be gone.
     *
     * Enabled by default.
     */
    public static final String KEY_VONR_SETTING_VISIBILITY_BOOL = "vonr_setting_visibility_bool";

    /**
     * Indicates if the carrier supports upgrading a call that was previously an RTT call to VT.
     */
    public static final String KEY_VT_UPGRADE_SUPPORTED_FOR_DOWNGRADED_RTT_CALL_BOOL = "vt_upgrade_supported_for_downgraded_rtt_call";

    /**
     * Whether cellular data is required to access visual voicemail.
     */
    public static final String KEY_VVM_CELLULAR_DATA_REQUIRED_BOOL = "vvm_cellular_data_required_bool";

    /**
     * The default OMTP visual voicemail client prefix to use. Defaulted to "//VVM"
     */
    public static final String KEY_VVM_CLIENT_PREFIX_STRING = "vvm_client_prefix_string";

    /**
     * The carrier number mobile outgoing (MO) sms messages are sent to.
     */
    public static final String KEY_VVM_DESTINATION_NUMBER_STRING = "vvm_destination_number_string";

    /**
     * A set of capabilities that should not be used even if it is reported by the visual voicemail
     * IMAP CAPABILITY command.
     */
    public static final String KEY_VVM_DISABLED_CAPABILITIES_STRING_ARRAY = "vvm_disabled_capabilities_string_array";

    /**
     * Whether legacy mode should be used when the visual voicemail client is disabled.
     *
     * <p>Legacy mode is a mode that on the carrier side visual voicemail is still activated, but on
     * the client side all network operations are disabled. SMSs are still monitored so a new
     * message SYNC SMS will be translated to show a message waiting indicator, like traditional
     * voicemails.
     *
     * <p>This is for carriers that does not support VVM deactivation so voicemail can continue to
     * function without the data cost.
     */
    public static final String KEY_VVM_LEGACY_MODE_ENABLED_BOOL = "vvm_legacy_mode_enabled_bool";

    /**
     * The port through which the mobile outgoing (MO) sms messages are sent through.
     */
    public static final String KEY_VVM_PORT_NUMBER_INT = "vvm_port_number_int";

    /**
     * Whether to prefetch audio data on new voicemail arrival, defaulted to true.
     */
    public static final String KEY_VVM_PREFETCH_BOOL = "vvm_prefetch_bool";

    /**
     * Whether to use SSL to connect to the visual voicemail IMAP server. Defaulted to false.
     */
    public static final String KEY_VVM_SSL_ENABLED_BOOL = "vvm_ssl_enabled_bool";

    /**
     * The type of visual voicemail protocol the carrier adheres to. See {@link TelephonyManager}
     * for possible values. For example {@link TelephonyManager#VVM_TYPE_OMTP}.
     */
    public static final String KEY_VVM_TYPE_STRING = "vvm_type_string";

    /**
     * The Component Name of the activity that can setup the emergency address for WiFi Calling
     * as per carrier requirement.
     */
    public static final String KEY_WFC_EMERGENCY_ADDRESS_CARRIER_APP_STRING = "wfc_emergency_address_carrier_app_string";

    /**
     * Boolean to decide whether world mode is enabled.
     */
    public static final String KEY_WORLD_MODE_ENABLED_BOOL = "world_mode_enabled_bool";

    /**
     * Flag indicating if the phone is a world phone
     */
    public static final String KEY_WORLD_PHONE_BOOL = "world_phone_bool";

    /**
     * Passing this value as {@link #KEY_SUBSCRIPTION_GROUP_UUID_STRING} will remove the
     * subscription from a group instead of adding it to a group.
     *
     * <p>This value will work all the way back to {@link android.os.Build.VERSION_CODES#Q}.
     */
    public static final String REMOVE_GROUP_UUID_STRING = "00000000-0000-0000-0000-000000000000";

    /**
     * Service class flag if no specific service class is specified.
     * Reference: 3GPP TS 27.007 Section 7.4 Facility lock +CLCK
     */
    public static final int SERVICE_CLASS_NONE = 0;

    /**
     * Service class flag for voice telephony.
     * Reference: 3GPP TS 27.007 Section 7.4 Facility lock +CLCK
     */
    public static final int SERVICE_CLASS_VOICE = 1;

    /**
     * Only send USSD over CS.
     * {@link #KEY_CARRIER_USSD_METHOD_INT}
     */
    public static final int USSD_OVER_CS_ONLY = 2;

    /**
     * Only send USSD over IMS while CS is out of service, otherwise send USSD over CS.
     * {@link #KEY_CARRIER_USSD_METHOD_INT}
     */
    public static final int USSD_OVER_CS_PREFERRED = 0;

    /**
     * Only send USSD over IMS and disallow silent redial over CS.
     * {@link #KEY_CARRIER_USSD_METHOD_INT}
     */
    public static final int USSD_OVER_IMS_ONLY = 3;

    /**
     * Send USSD over IMS or CS while IMS is out of service or silent redial over CS if needed.
     * {@link #KEY_CARRIER_USSD_METHOD_INT}
     */
    public static final int USSD_OVER_IMS_PREFERRED = 1;

    private CarrierConfigManager() {
    }

    /**
     * Configs used for APN setup.
     */
    public static final class Apn {
        /**
         * Prefix of all Apn.KEY_* constants.
         *
         * @deprecated Since KEY_PREFIX is unnecessary to public, it will modify to private
         * next android generation.
         */
        @Deprecated
        public static final String KEY_PREFIX = "apn.";

        /**
         * Default value of APN protocol field if not specified by user when adding/modifying
         * an APN.
         *
         * Available options are: {@link #PROTOCOL_IPV4}, {@link #PROTOCOL_IPV6},
         * {@link #PROTOCOL_IPV4V6}
         */
        public static final String KEY_SETTINGS_DEFAULT_PROTOCOL_STRING = "apn.settings_default_protocol_string";

        /**
         * Default value of APN roaming protocol field if not specified by user when
         * adding/modifying an APN.
         *
         * Available options are: {@link #PROTOCOL_IPV4}, {@link #PROTOCOL_IPV6},
         * {@link #PROTOCOL_IPV4V6}
         */
        public static final String KEY_SETTINGS_DEFAULT_ROAMING_PROTOCOL_STRING = "apn.settings_default_roaming_protocol_string";

        /**
         * IPv4 internet protocol
         */
        public static final String PROTOCOL_IPV4 = "IP";

        /**
         * IPv4 or IPv6 internet protocol
         */
        public static final String PROTOCOL_IPV4V6 = "IPV4V6";

        /**
         * IPv6 internet protocol
         */
        public static final String PROTOCOL_IPV6 = "IPV6";

        private Apn() {
        }
    }

    /**
     * This groups the BSF (BootStrapping Function) related configs.
     * Reference: 3GPP TS 24.109.
     */
    public static final class Bsf {
        /**
         * Specifies the fully qualified domain name (FQDN) of BSF Server
         *  as per 3GPP 24.109.
         */
        public static final String KEY_BSF_SERVER_FQDN_STRING = "bsf.bsf_server_fqdn_string";

        /**
         * Specifies the port number of the BSF server as per 3GPP 24.109.
         * This is usually default port number of HTTP, i.e. 80.
         */
        public static final String KEY_BSF_SERVER_PORT_INT = "bsf.bsf_server_port_int";

        /**
         * Specifies the transport type used in communication with
         * BSF server.
         *
         * <p>Possible values are,
         * {@link Ims#PREFERRED_TRANSPORT_TCP},
         * {@link Ims#PREFERRED_TRANSPORT_TLS}
         *
         * <p>If key is invalid or not configured, the default value
         * {@link Ims#PREFERRED_TRANSPORT_TCP} will apply.
         */
        public static final String KEY_BSF_TRANSPORT_TYPE_INT = "bsf.bsf_transport_type_int";

        /**
         * Prefix of all bsf.KEY_* constants.
         */
        public static final String KEY_PREFIX = "bsf.";

        private Bsf() {
        }
    }

    /**
     * GPS configs. See the GNSS HAL documentation for more details.
     */
    public static final class Gps {
        /**
         * Determine whether to enable Net Initiated SUPL (NI SUPL) message injection.
         * If enabled, the GnssLocationProvider will monitor for WAP PUSH or MT SMS NI SUPL intents
         * and subsequently inject the NI SUPL packet into the GNSS HAL.
         * {@code false} - Disable NI SUPL message injection. This is default.
         * {@code true} - Enable NI SUPL message injection.
         */
        public static final String KEY_ENABLE_NI_SUPL_MESSAGE_INJECTION_BOOL = "gps.enable_ni_supl_message_injection_bool";

        /**
         * Determine whether current lpp_mode used for E-911 needs to be kept persistently.
         * {@code false} - not keeping the lpp_mode means using default configuration of gps.conf
         *                 when sim is not presented.
         * {@code true}  - current lpp_profile of carrier will be kepted persistently
         *                 even after sim is removed. This is default.
         */
        public static final String KEY_PERSIST_LPP_MODE_BOOL = "gps.persist_lpp_mode_bool";

        /**
         * Prefix of all Gps.KEY_* constants.
         */
        public static final String KEY_PREFIX = "gps.";

        private Gps() {
        }
    }

    /**
     * Configs used by the IMS stack.
     */
    public static final class Ims {
        /**
         * E911 RTCP inactivity occurred when call is connected.
         */
        public static final int E911_RTCP_INACTIVITY_ON_CONNECTED = 3;

        /**
         * E911 RTP inactivity occurred when call is connected.
         */
        public static final int E911_RTP_INACTIVITY_ON_CONNECTED = 4;

        /**
         * Indicates geolocation PIDF XML needs to be included for emergency
         * call scenario on Cellular
         */
        public static final int GEOLOCATION_PIDF_FOR_EMERGENCY_ON_CELLULAR = 4;

        /**
         * Indicates geolocation PIDF XML needs to be included for emergency
         * call scenario on WiFi
         */
        public static final int GEOLOCATION_PIDF_FOR_EMERGENCY_ON_WIFI = 2;

        /**
         * Indicates geolocation PIDF XML needs to be included for normal/non-emergency
         * call scenario on Cellular
         *
         * <p>Geolocation for normal/non-emergency call should only include
         * country code.
         */
        public static final int GEOLOCATION_PIDF_FOR_NON_EMERGENCY_ON_CELLULAR = 3;

        /**
         * Indicates geolocation PIDF XML needs to be included for
         * normal/non-emergency call scenario on WiFi
         *
         * <p>Geolocation for normal/non-emergency call should only include
         * country code.
         */
        public static final int GEOLOCATION_PIDF_FOR_NON_EMERGENCY_ON_WIFI = 1;

        /**
         * IPSec Authentication algorithm is HMAC-MD5. see Annex H of TS 33.203
         */
        public static final int IPSEC_AUTHENTICATION_ALGORITHM_HMAC_MD5 = 0;

        /**
         * IPSec Authentication algorithm is HMAC-SHA1. see Annex H of TS 33.203
         */
        public static final int IPSEC_AUTHENTICATION_ALGORITHM_HMAC_SHA1 = 1;

        /**
         * IPSec Encryption algorithm is AES_CBC. see Annex H of TS 33.203
         */
        public static final int IPSEC_ENCRYPTION_ALGORITHM_AES_CBC = 2;

        /**
         * IPSec Encryption algorithm is DES_EDE3_CBC. see Annex H of TS 33.203
         */
        public static final int IPSEC_ENCRYPTION_ALGORITHM_DES_EDE3_CBC = 1;

        /**
         * IPSec Encryption algorithm is NULL. see Annex H of TS 33.203
         */
        public static final int IPSEC_ENCRYPTION_ALGORITHM_NULL = 0;

        /**
         * List of different RAT technologies on which Provisioning for Call Composer
         * (section 2.4 of RCC.20) is supported.
         * <p>Possible values are,
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_LTE}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_IWLAN}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_CROSS_SIM}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_NR}
         * @see MmTelFeature.MmTelCapabilities#CAPABILITY_TYPE_CALL_COMPOSER
         */
        public static final String KEY_CAPABILITY_TYPE_CALL_COMPOSER_INT_ARRAY = "ims.capability_type_call_composer_int_array";

        /**
         * This carrier supports User Capability Exchange using SIP OPTIONS as defined by the
         * framework. If set, the RcsFeature should support capability exchange using SIP OPTIONS.
         * If not set, this RcsFeature should not service capability requests.
         * <p>Possible values are,
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_LTE}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_IWLAN}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_CROSS_SIM}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_NR}
         */
        public static final String KEY_CAPABILITY_TYPE_OPTIONS_UCE_INT_ARRAY = "ims.capability_type_options_uce_int_array";

        /**
         * This carrier supports User Capability Exchange using a presence server as defined by the
         * framework. If set, the RcsFeature should support capability exchange using a presence
         * server. If not set, this RcsFeature should not publish capabilities or service capability
         * requests using presence.
         * <p>Possible values are,
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_LTE}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_IWLAN}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_CROSS_SIM}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_NR}
         */
        public static final String KEY_CAPABILITY_TYPE_PRESENCE_UCE_INT_ARRAY = "ims.capability_type_presence_uce_int_array";

        /**
         * List of different RAT technologies on which Provisioning for SMS (IR.92) is supported.
         * <p>Possible values are,
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_LTE}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_IWLAN}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_CROSS_SIM}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_NR}
         * @see MmTelFeature.MmTelCapabilities#CAPABILITY_TYPE_SMS
         */
        public static final String KEY_CAPABILITY_TYPE_SMS_INT_ARRAY = "ims.capability_type_sms_int_array";

        /**
         * List of different RAT technologies on which Provisioning for XCAP over Ut for
         * supplementary services. (IR.92) is supported.
         * <p>Possible values are,
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_LTE}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_IWLAN}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_CROSS_SIM}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_NR}
         * @see MmTelFeature.MmTelCapabilities#CAPABILITY_TYPE_UT
         */
        public static final String KEY_CAPABILITY_TYPE_UT_INT_ARRAY = "ims.capability_type_ut_int_array";

        /**
         * List of different RAT technologies on which Provisioning for Video Telephony (IR.94)
         * is supported.
         * <p>Possible values are,
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_LTE}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_IWLAN}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_CROSS_SIM}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_NR}
         * @see MmTelFeature.MmTelCapabilities#CAPABILITY_TYPE_VIDEO
         */
        public static final String KEY_CAPABILITY_TYPE_VIDEO_INT_ARRAY = "ims.capability_type_video_int_array";

        /**
         * List of different RAT technologies on which Provisioning for Voice calling (IR.92)
         * is supported.
         * <p>Possible values are,
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_LTE}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_IWLAN}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_CROSS_SIM}
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase#REGISTRATION_TECH_NR}
         * @see MmTelFeature.MmTelCapabilities#CAPABILITY_TYPE_VOICE
         */
        public static final String KEY_CAPABILITY_TYPE_VOICE_INT_ARRAY = "ims.capability_type_voice_int_array";

        /**
         * Flag indicating whether or not this carrier supports the exchange of phone numbers with
         * the carrier's RCS presence server in order to retrieve the RCS capabilities of requested
         * contacts used in the RCS User Capability Exchange (UCE) procedure. See RCC.71, section 3
         * for more information.
         * <p>
         * When presence is supported, the device uses the SIP SUBSCRIBE/NOTIFY procedure internally
         * to retrieve the requested RCS capabilities. See
         * {@link android.telephony.ims.RcsUceAdapter} for more information on how RCS capabilities
         * can be retrieved from the carrier's network.
         */
        public static final String KEY_ENABLE_PRESENCE_CAPABILITY_EXCHANGE_BOOL = "ims.enable_presence_capability_exchange_bool";

        /**
         * Flag indicating whether or not the carrier supports capability exchange with a list of
         * contacts. When {@code true}, the device will batch together multiple requests and
         * construct a RLMI document in the SIP SUBSCRIBE request (see RFC 4662). If {@code false},
         * the request will be split up into one SIP SUBSCRIBE request per contact.
         */
        public static final String KEY_ENABLE_PRESENCE_GROUP_SUBSCRIBE_BOOL = "ims.enable_presence_group_subscribe_bool";

        /**
         * A boolean flag specifying whether or not this carrier supports the device notifying the
         * network of its RCS capabilities using the SIP PUBLISH procedure defined for User
         * Capability Exchange (UCE). See RCC.71, section 3 for more information.
         * <p>
         * If this key's value is set to false, the procedure for RCS contact capability exchange
         * via SIP SUBSCRIBE/NOTIFY will also be disabled internally, and this key must also be set
         * to false to ensure apps do not improperly think that capability exchange via SIP PUBLISH
         * is enabled.
         * <p> The default value for this key is {@code false}.
         */
        public static final String KEY_ENABLE_PRESENCE_PUBLISH_BOOL = "ims.enable_presence_publish_bool";

        /**
         * List of cases where geolocation PIDF XML needs to be included in the
         * SIP INVITE over WiFi and Cellular.
         *
         * <p>Possible values are,
         * {@link #GEOLOCATION_PIDF_FOR_NON_EMERGENCY_ON_WIFI},
         * {@link #GEOLOCATION_PIDF_FOR_EMERGENCY_ON_WIFI},
         * {@link #GEOLOCATION_PIDF_FOR_NON_EMERGENCY_ON_CELLULAR},
         * {@link #GEOLOCATION_PIDF_FOR_EMERGENCY_ON_CELLULAR}
         *
         * <p>An empty array indicates geolocation PIDF XML should not be included
         * in the SIP INVITE over WiFi and Cellular.
         */
        public static final String KEY_GEOLOCATION_PIDF_IN_SIP_INVITE_SUPPORT_INT_ARRAY = "ims.geolocation_pidf_in_sip_invite_support_int_array";

        /**
         * List of cases where geolocation PIDF XML needs to be included in the
         * SIP REGISTER over WiFi and Cellular.
         *
         * <p>Possible values are,
         * {@link #GEOLOCATION_PIDF_FOR_NON_EMERGENCY_ON_WIFI},
         * {@link #GEOLOCATION_PIDF_FOR_EMERGENCY_ON_WIFI},
         * {@link #GEOLOCATION_PIDF_FOR_NON_EMERGENCY_ON_CELLULAR},
         * {@link #GEOLOCATION_PIDF_FOR_EMERGENCY_ON_CELLULAR}
         *
         * <p>An empty array indicates geolocation PIDF XML should not be included in
         * the SIP REGISTER over WiFi and Cellular.
         */
        public static final String KEY_GEOLOCATION_PIDF_IN_SIP_REGISTER_SUPPORT_INT_ARRAY = "ims.geolocation_pidf_in_sip_register_support_int_array";

        /**
         * Flag indicating whether Globally Routable User agent (GRUU)
         * in supported HEADER is included or not.
         *
         * <p> Reference: RFC 5627.
         */
        public static final String KEY_GRUU_ENABLED_BOOL = "ims.gruu_enabled_bool";

        /**
         * This config determines whether IMS PDN needs to be enabled
         * when VOPS support is not available in both home and roaming scenarios.
         *
         * <p>This is applicable before IMS PDN is up, to decide whether
         * IMS PDN needs to be enabled based on VOPS support in home/roaming.
         *
         * <p>Possible values are,
         * {@link #NETWORK_TYPE_HOME},
         * {@link #NETWORK_TYPE_ROAMING}
         * An empty array indicates IMS PDN depends on VOPS on both home
         * and roaming scenarios.
         */
        public static final String KEY_IMS_PDN_ENABLED_IN_NO_VOPS_SUPPORT_INT_ARRAY = "ims.ims_pdn_enabled_in_no_vops_support_int_array";

        /**
         * A boolean flag specifying whether or not this carrier requires one IMS registration for
         * all IMS services (MMTEL and RCS).
         * <p>
         * If set to {@code true}, the IMS Service must use one IMS registration for all IMS
         * services. If set to {@code false}, IMS services may use separate IMS registrations for
         * MMTEL and RCS.
         * <p>
         * The default value for this configuration is {@code false}.
         */
        public static final String KEY_IMS_SINGLE_REGISTRATION_REQUIRED_BOOL = "ims.ims_single_registration_required_bool";

        /**
         * Specifies the IMS User Agent in template format.
         *
         * <p>Example: #MANUFACTURER#_#MODEL#_Android#AV#_#BUILD#".
         * IMS Stack should internally substitute the tokens with the
         * values from the respective android properties.
         *
         * <p>List of allowed tokens and the corresponding android properties are,
         * <UL>
         *   <LI>MANUFACTURER : ro.product.manufacturer</LI>
         *   <LI>MODEL :  ro.product.model</LI>
         *   <LI>AV : ro.build.version.release"</LI>
         *   <LI>BUILD : ro.build.id</LI>
         * </UL>
         * <p> Vendor IMS Stack should strip any whitespace characters present
         * in the android properties values before replacing the token.
         *
         * <p> An empty string is invalid as per IR92 section 2.6. This key is
         * considered invalid if the format is violated. If the key is invalid or
         * not configured, IMS stack should use internal default values.
         */
        public static final String KEY_IMS_USER_AGENT_STRING = "ims.ims_user_agent_string";

        /**
         * List of supported IPSEC Authentication algorithms.
         *
         * <p>Possible values are,
         * {@link #IPSEC_AUTHENTICATION_ALGORITHM_HMAC_MD5},
         * {@link #IPSEC_AUTHENTICATION_ALGORITHM_HMAC_SHA1}
         */
        public static final String KEY_IPSEC_AUTHENTICATION_ALGORITHMS_INT_ARRAY = "ims.ipsec_authentication_algorithms_int_array";

        /**
         * List of supported IPSEC encryption algorithms.
         *
         * <p>Possible values are,
         * {@link #IPSEC_ENCRYPTION_ALGORITHM_NULL},
         * {@link #IPSEC_ENCRYPTION_ALGORITHM_DES_EDE3_CBC},
         * {@link #IPSEC_ENCRYPTION_ALGORITHM_AES_CBC}
         */
        public static final String KEY_IPSEC_ENCRYPTION_ALGORITHMS_INT_ARRAY = "ims.ipsec_encryption_algorithms_int_array";

        /**
         * Specify the maximum IPV4 MTU size of SIP message on Cellular.
         *
         * <p>If {@link #KEY_SIP_PREFERRED_TRANSPORT_INT} is
         * {@link #PREFERRED_TRANSPORT_DYNAMIC_UDP_TCP} and SIP message MTU size
         * is more than this value, then SIP transport will be TCP, else the
         * SIP transport is UDP.
         */
        public static final String KEY_IPV4_SIP_MTU_SIZE_CELLULAR_INT = "ims.ipv4_sip_mtu_size_cellular_int";

        /**
         * Specify the maximum IPV6 MTU size of SIP message on Cellular.
         *
         * <p>If {@link #KEY_SIP_PREFERRED_TRANSPORT_INT} is
         * {@link #PREFERRED_TRANSPORT_DYNAMIC_UDP_TCP} and SIP message MTU size
         * is more than this value, then SIP transport will be TCP, else the
         * SIP transport is UDP.
         */
        public static final String KEY_IPV6_SIP_MTU_SIZE_CELLULAR_INT = "ims.ipv6_sip_mtu_size_cellular_int";

        /**
         * Flag indicating whether to keep/release IMS PDN in case of
         * moving to non VOPS area.
         *
         * <p>if {@code True}, keep IMS PDN in case of moving to non VOPS area.
         * if {@code false}, otherwise.
         */
        public static final String KEY_KEEP_PDN_UP_IN_NO_VOPS_BOOL = "ims.keep_pdn_up_in_no_vops_bool";

        /**
         * A bundle which specifies the MMTEL capability and registration technology
         * that requires provisioning. If a tuple is not present, the
         * framework will not require that the tuple requires provisioning before
         * enabling the capability.
         * <p> Possible keys in this bundle are
         * <ul>
         *     <li>{@link #KEY_CAPABILITY_TYPE_VOICE_INT_ARRAY}</li>
         *     <li>{@link #KEY_CAPABILITY_TYPE_VIDEO_INT_ARRAY}</li>
         *     <li>{@link #KEY_CAPABILITY_TYPE_UT_INT_ARRAY}</li>
         *     <li>{@link #KEY_CAPABILITY_TYPE_SMS_INT_ARRAY}</li>
         *     <li>{@link #KEY_CAPABILITY_TYPE_CALL_COMPOSER_INT_ARRAY}</li>
         * </ul>
         * <p> The values are defined as {@code REGISTRATION_TECH_*} constants in
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase}.
         *
         * changing mmtel_requires_provisioning_bundle requires changes to
         * carrier_volte_provisioning_required_bool and vice versa
         * {@link Ims#KEY_CARRIER_VOLTE_PROVISIONING_REQUIRED_BOOL}
         */
        public static final String KEY_MMTEL_REQUIRES_PROVISIONING_BUNDLE = "ims.mmtel_requires_provisioning_bundle";

        /**
         * An integer key associated with the period of time in seconds the non-rcs capability
         * information of each contact is cached on the device.
         * <p>
         * The rcs capability cache expiration sec is managed by
         * {@code android.telephony.ims.ProvisioningManager} but non-rcs capability is managed by
         * {@link CarrierConfigManager} since non-rcs capability will be provided via ACS or carrier
         * config.
         * <p>
         * The default value is 2592000 secs (30 days), see RCC.07 Annex A.1.9.
         */
        public static final String KEY_NON_RCS_CAPABILITIES_CACHE_EXPIRATION_SEC_INT = "ims.non_rcs_capabilities_cache_expiration_sec_int";

        /**
         * Specify the “phone-context” parameter as defined in
         * section 7.2A.10 in 3GPP TS 24.229.
         */
        public static final String KEY_PHONE_CONTEXT_DOMAIN_NAME_STRING = "ims.phone_context_domain_name_string";

        /**
         * Prefix of all Ims.KEY_* constants.
         */
        public static final String KEY_PREFIX = "ims.";

        /**
         * Flag indicating whether or not the carrier expects the RCS UCE service to periodically
         * refresh the RCS capabilities cache of the user's contacts as well as request the
         * capabilities of call contacts when the SIM card is first inserted or when a new contact
         * is added, removed, or modified. This corresponds to the RCC.07 A.19
         * "DISABLE INITIAL ADDRESS BOOK SCAN" parameter.
         * <p>
         * If this flag is disabled, the capabilities cache will not be refreshed internally at all
         * and will only be updated if the cached capabilities are stale when an application
         * requests them.
         *
         * @see RcsUceAdapter#isUceSettingEnabled() more information about this feature and how
         * it is enabled by the user.
         */
        public static final String KEY_RCS_BULK_CAPABILITY_EXCHANGE_BOOL = "ims.rcs_bulk_capability_exchange_bool";

        /**
         * Specifies the RCS feature tag allowed for the carrier.
         *
         * <p>The values refer to RCC.07 2.4.4.
         */
        public static final String KEY_RCS_FEATURE_TAG_ALLOWED_STRING_ARRAY = "ims.rcs_feature_tag_allowed_string_array";

        /**
         * A bundle which specifies the RCS capability and registration technology
         * that requires provisioning. If a tuple is not present, the
         * framework will not require that the tuple requires provisioning before
         * enabling the capability.
         * <p> Possible keys in this bundle are
         * <ul>
         *     <li>{@link #KEY_CAPABILITY_TYPE_OPTIONS_UCE_INT_ARRAY}</li>
         *     <li>{@link #KEY_CAPABILITY_TYPE_PRESENCE_UCE_INT_ARRAY}</li>
         * </ul>
         * <p> The values are defined as {@code REGISTRATION_TECH_*} constants in
         * {@link android.telephony.ims.stub.ImsRegistrationImplBase}.
         */
        public static final String KEY_RCS_REQUIRES_PROVISIONING_BUNDLE = "ims.rcs_requires_provisioning_bundle";

        /**
         * Flag indicating whether subscription to registration event package
         * is supported or not.
         */
        public static final String KEY_REGISTRATION_EVENT_PACKAGE_SUPPORTED_BOOL = "ims.registration_event_package_supported_bool";

        /**
         * Expiry timer for IMS Registration in seconds.
         * <p>Reference: RFC 3261 Section 20.19.
         */
        public static final String KEY_REGISTRATION_EXPIRY_TIMER_SEC_INT = "ims.registration_expiry_timer_sec_int";

        /**
         * Registration Retry Base-time as per RFC 5626 Section 4.5.
         */
        public static final String KEY_REGISTRATION_RETRY_BASE_TIMER_MILLIS_INT = "ims.registration_retry_base_timer_millis_int";

        /**
         * Registration Retry max-time as per RFC 5626 Section 4.5.
         */
        public static final String KEY_REGISTRATION_RETRY_MAX_TIMER_MILLIS_INT = "ims.registration_retry_max_timer_millis_int";

        /**
         * Expiry timer for SUBSCRIBE in seconds.
         * <p>Reference: RFC 3261 Section 20.19.
         */
        public static final String KEY_REGISTRATION_SUBSCRIBE_EXPIRY_TIMER_SEC_INT = "ims.registration_subscribe_expiry_timer_sec_int";

        /**
         * Specify whether the request URI is SIP URI
         * {@link #REQUEST_URI_FORMAT_SIP} or
         * TEL URI {@link #REQUEST_URI_FORMAT_TEL}.
         */
        public static final String KEY_REQUEST_URI_TYPE_INT = "ims.request_uri_type_int";

        /**
         * Flag indicating whether IPSec enabled for SIP messages.
         *
         * <p> Reference: 3GPP TS 33.203 and RFC 3329.
         */
        public static final String KEY_SIP_OVER_IPSEC_ENABLED_BOOL = "ims.sip_over_ipsec_enabled_bool";

        /**
         * Specify the preferred transport protocol for SIP messages.
         *
         * <p>Possible values are,
         * {@link #PREFERRED_TRANSPORT_UDP},
         * {@link #PREFERRED_TRANSPORT_TCP},
         * {@link #PREFERRED_TRANSPORT_DYNAMIC_UDP_TCP}
         */
        public static final String KEY_SIP_PREFERRED_TRANSPORT_INT = "ims.sip_preferred_transport_int";

        /**
         * Specifies the SIP Server default port.
         */
        public static final String KEY_SIP_SERVER_PORT_NUMBER_INT = "ims.sip_server_port_number_int";

        /**
         * SIP timer B as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_B_MILLIS_INT = "ims.sip_timer_b_millis_int";

        /**
         * SIP timer C as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_C_MILLIS_INT = "ims.sip_timer_c_millis_int";

        /**
         * SIP timer D as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_D_MILLIS_INT = "ims.sip_timer_d_millis_int";

        /**
         * SIP timer F as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_F_MILLIS_INT = "ims.sip_timer_f_millis_int";

        /**
         * SIP timer H as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_H_MILLIS_INT = "ims.sip_timer_h_millis_int";

        /**
         * SIP timer J as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_J_MILLIS_INT = "ims.sip_timer_j_millis_int";

        /**
         * SIP timer T1 as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_T1_MILLIS_INT = "ims.sip_timer_t1_millis_int";

        /**
         * SIP timer T2 as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_T2_MILLIS_INT = "ims.sip_timer_t2_millis_int";

        /**
         * SIP timer T4 as per 3GPP TS 24.229 Table 7.7.1
         */
        public static final String KEY_SIP_TIMER_T4_MILLIS_INT = "ims.sip_timer_t4_millis_int";

        /**
         * List of different RAT technologies on which IMS
         * is supported.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#IWLAN}
         * {@link AccessNetworkConstants.AccessNetworkType#UTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#GERAN}
         */
        public static final String KEY_SUPPORTED_RATS_INT_ARRAY = "ims.supported_rats_int_array";

        /**
         * Flag indicating whether or not to use SIP URI when send a presence subscribe.
         * When {@code true}, the device sets the To and Contact header to be SIP URI using
         * the TelephonyManager#getIsimDomain" API.
         * If {@code false}, the device uses a TEL URI.
         */
        public static final String KEY_USE_SIP_URI_FOR_PRESENCE_SUBSCRIBE_BOOL = "ims.use_sip_uri_for_presence_subscribe_bool";

        /**
         * Delay in milliseconds to turn off wifi when IMS is registered over wifi.
         */
        public static final String KEY_WIFI_OFF_DEFERRING_TIME_MILLIS_INT = "ims.wifi_off_deferring_time_millis_int";

        /**
         * Indicates HOME Network.
         */
        public static final int NETWORK_TYPE_HOME = 0;

        /**
         * Indicates Roaming Network.
         */
        public static final int NETWORK_TYPE_ROAMING = 1;

        /**
         *  Preferred Transport is both UDP and TCP and selected based
         *  on MTU size specified in {@link #KEY_IPV4_SIP_MTU_SIZE_CELLULAR_INT}
         *  and {@link #KEY_IPV6_SIP_MTU_SIZE_CELLULAR_INT}.
         *
         *  <p>Default transport is UDP. If message size is larger
         *  than MTU, then TCP shall be used.
         */
        public static final int PREFERRED_TRANSPORT_DYNAMIC_UDP_TCP = 2;

        /**
         * Preferred Transport is always TCP.
         */
        public static final int PREFERRED_TRANSPORT_TCP = 1;

        /**
         * Preferred Transport is TLS.
         */
        public static final int PREFERRED_TRANSPORT_TLS = 3;

        /**
         * Preferred Transport is always UDP.
         */
        public static final int PREFERRED_TRANSPORT_UDP = 0;

        /**
         *  Request URI is of type SIP URI.
         */
        public static final int REQUEST_URI_FORMAT_SIP = 1;

        /**
         *  Request URI is of type TEL URI.
         */
        public static final int REQUEST_URI_FORMAT_TEL = 0;

        /**
         * RTCP inactivity occurred when call is connected.
         */
        public static final int RTCP_INACTIVITY_ON_CONNECTED = 1;

        /**
         * RTCP inactivity occurred when call is on HOLD.
         */
        public static final int RTCP_INACTIVITY_ON_HOLD = 0;

        /**
         * RTP inactivity occurred when call is connected.
         */
        public static final int RTP_INACTIVITY_ON_CONNECTED = 2;

        private Ims() {
        }
    }

    /**
     * Emergency Call/E911. This groups the configs specific for emergency call
     * over IMS.
     *
     * <p> Reference: 3GPP 24.229, 3GPP 23.167 Annex H, 3GPP 24.301.
     */
    public static final class ImsEmergency {
        /**
         * Circuit switched domain.
         */
        public static final int DOMAIN_CS = 1;

        /**
         * Packet switched domain over 3GPP networks.
         */
        public static final int DOMAIN_PS_3GPP = 2;

        /**
         * Packet switched domain over non-3GPP networks such as Wi-Fi.
         */
        public static final int DOMAIN_PS_NON_3GPP = 3;

        /**
         * A timer to guard the max attempting time on current SIM slot so that modem will not
         * stuck in current SIM slot for long time. On timer expiry, if emergency call on the
         * other SIM slot is preferable, telephony shall cancel the emergency call and place the
         * call on the other SIM slot. If this value is set to {@link #REDIAL_TIMER_DISABLED}, then
         * the timer will never be started and domain selection continues on the current SIM slot.
         * This value should be greater than the value of {@link #KEY_EMERGENCY_SCAN_TIMER_SEC_INT}.
         *
         * The default value for the timer is 120 seconds.
         */
        public static final String KEY_CROSS_STACK_REDIAL_TIMER_SEC_INT = "imsemergency.cross_stack_redial_timer_sec_int";

        /**
         * Flag specifying whether UE should enter Emergency CallBack Mode(ECBM)
         * after E911 call is ended.
         *
         * <p>If {@code true}: Enter ECBM mode after E911 call is ended.
         * {@code false} otherwise.
         */
        public static final String KEY_EMERGENCY_CALLBACK_MODE_SUPPORTED_BOOL = "imsemergency.emergency_callback_mode_supported_bool";

        /**
         * Specifies the time by which a call should be set up on the current network
         * once the call is routed on the network. If the call cannot be set up by timer expiry,
         * call shall be re-dialed on the next available network.
         * If this value is set to 0, the timer shall be disabled.
         *
         * The default value for this key is 0.
         */
        public static final String KEY_EMERGENCY_CALL_SETUP_TIMER_ON_CURRENT_NETWORK_SEC_INT = "imsemergency.emergency_call_setup_timer_on_current_network_sec_int";

        /**
         * Specifies the numbers to be dialed over CDMA network in case of dialing over CS network.
         *
         * The default value for this key is an empty string array.
         */
        public static final String KEY_EMERGENCY_CDMA_PREFERRED_NUMBERS_STRING_ARRAY = "imsemergency.emergency_cdma_preferred_numbers_string_array";

        /**
         * Specifies the emergency call domain preference for the home network.
         * The domain selection service shall choose the domain in the order
         * for attempting the emergency call
         *
         * <p>Possible values are,
         * {@link #DOMAIN_CS}
         * {@link #DOMAIN_PS_3GPP}
         * {@link #DOMAIN_PS_NON_3GPP}.
         *
         * The default value for this key is
         * {{@link #DOMAIN_PS_3GPP},
         * {@link #DOMAIN_CS},
         * {@link #DOMAIN_PS_NON_3GPP}}.
         */
        public static final String KEY_EMERGENCY_DOMAIN_PREFERENCE_INT_ARRAY = "imsemergency.emergency_domain_preference_int_array";

        /**
         * Specifies the emergency call domain preference for the roaming network.
         * The domain selection service shall choose the domain in the order
         * for attempting the emergency call.
         *
         * <p>Possible values are,
         * {@link #DOMAIN_CS}
         * {@link #DOMAIN_PS_3GPP}
         * {@link #DOMAIN_PS_NON_3GPP}.
         *
         * The default value for this key is
         * {{@link #DOMAIN_PS_3GPP},
         * {@link #DOMAIN_CS},
         * {@link #DOMAIN_PS_NON_3GPP}}.
         */
        public static final String KEY_EMERGENCY_DOMAIN_PREFERENCE_ROAMING_INT_ARRAY = "imsemergency.emergency_domain_preference_roaming_int_array";

        /**
         * Specifies if LTE is preferred when re-scanning networks after the failure of dialing
         * over NR. If not, CS will be preferred.
         *
         * The default value for this key is {@code false}.
         */
        public static final String KEY_EMERGENCY_LTE_PREFERRED_AFTER_NR_FAILED_BOOL = "imsemergency.emergency_lte_preferred_after_nr_failed_bool";

        /**
         * Specifies the preferred emergency network scanning type.
         *
         * <p>Possible values are,
         * {@link #SCAN_TYPE_NO_PREFERENCE}
         * {@link #SCAN_TYPE_FULL_SERVICE}
         * {@link #SCAN_TYPE_FULL_SERVICE_FOLLOWED_BY_LIMITED_SERVICE}
         *
         * The default value for this key is {@link #SCAN_TYPE_NO_PREFERENCE}.
         */
        public static final String KEY_EMERGENCY_NETWORK_SCAN_TYPE_INT = "imsemergency.emergency_network_scan_type_int";

        /**
         * List of CS access network technologies where circuit-switched emergency calls are
         * supported in the roaming network and non-domestic 3rd-party networks. The order
         * in the list represents the preference. The domain selection service shall scan
         * the network type in the order of the preference.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#GERAN}
         * {@link AccessNetworkConstants.AccessNetworkType#UTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#CDMA2000}
         *
         * The default value for this key is
         * {{@link AccessNetworkConstants.AccessNetworkType#UTRAN},
         * {@link AccessNetworkConstants.AccessNetworkType#GERAN}}.
         */
        public static final String KEY_EMERGENCY_OVER_CS_ROAMING_SUPPORTED_ACCESS_NETWORK_TYPES_INT_ARRAY = "imsemergency.emergency_over_cs_roaming_supported_access_network_types_int_array";

        /**
         * List of CS access network technologies where circuit-switched emergency calls are
         * supported in the home network and domestic 3rd-party networks. The order in the list
         * represents the preference. The domain selection service shall scan the network type
         * in the order of the preference.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#GERAN}
         * {@link AccessNetworkConstants.AccessNetworkType#UTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#CDMA2000}
         *
         * The default value for this key is
         * {{@link AccessNetworkConstants.AccessNetworkType#UTRAN},
         * {@link AccessNetworkConstants.AccessNetworkType#GERAN}}.
         */
        public static final String KEY_EMERGENCY_OVER_CS_SUPPORTED_ACCESS_NETWORK_TYPES_INT_ARRAY = "imsemergency.emergency_over_cs_supported_access_network_types_int_array";

        /**
         * List of 3GPP access network technologies where e911 over IMS is supported
         * in the roaming network and non-domestic 3rd-party networks. The order in the list
         * represents the preference. The domain selection service shall scan the network type
         * in the order of the preference.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
         *
         * The default value for this key is
         * {{@link AccessNetworkConstants.AccessNetworkType#EUTRAN},
         */
        public static final String KEY_EMERGENCY_OVER_IMS_ROAMING_SUPPORTED_3GPP_NETWORK_TYPES_INT_ARRAY = "imsemergency.emergency_over_ims_roaming_supported_3gpp_network_types_int_array";

        /**
         * List of 3GPP access network technologies where e911 over IMS is supported
         * in the home network and domestic 3rd-party networks. The order in the list represents
         * the preference. The domain selection service shall scan the network type in the order
         * of the preference.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
         *
         * The default value for this key is
         * {{@link AccessNetworkConstants.AccessNetworkType#EUTRAN},
         */
        public static final String KEY_EMERGENCY_OVER_IMS_SUPPORTED_3GPP_NETWORK_TYPES_INT_ARRAY = "imsemergency.emergency_over_ims_supported_3gpp_network_types_int_array";

        /**
         * List of different RAT technologies on which emergency call using IMS
         * is supported.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#IWLAN}
         */
        public static final String KEY_EMERGENCY_OVER_IMS_SUPPORTED_RATS_INT_ARRAY = "imsemergency.emergency_over_ims_supported_rats_int_array";

        /**
         * Flag specifying whether QoS preconditions are supported for emergency
         * call setup.
         *
         * <p>If {@code true}: QoS Preconditions are supported.
         * {@code false} otherwise.
         *
         * <p>Reference: 3GPP TS 24.229
         */
        public static final String KEY_EMERGENCY_QOS_PRECONDITION_SUPPORTED_BOOL = "imsemergency.emergency_qos_precondition_supported_bool";

        /**
         * Specifies the maximum time from deciding that an emergency service is to
         * be established until completion of the emergency registration procedure.
         * Upon timer expiry, the UE considers the emergency REGISTER request or
         * the emergency call attempt as failed.
         */
        public static final String KEY_EMERGENCY_REGISTRATION_TIMER_MILLIS_INT = "imsemergency.emergency_registration_timer_millis_int";

        /**
         * Specifies if emergency call shall be attempted on IMS only when IMS is registered.
         * This is applicable only for the case PS is in service.
         *
         * The default value for this key is {@code false}.
         */
        public static final String KEY_EMERGENCY_REQUIRES_IMS_REGISTRATION_BOOL = "imsemergency.emergency_requires_ims_registration_bool";

        /**
         * Specifies if emergency call shall be attempted on IMS over cellular network
         * only when VoLTE is enabled.
         *
         * The default value for this key is {@code false}.
         */
        public static final String KEY_EMERGENCY_REQUIRES_VOLTE_ENABLED_BOOL = "imsemergency.emergency_requires_volte_enabled_bool";

        /**
         * Emergency scan timer to wait for scan results from radio before attempting the call
         * over Wi-Fi. On timer expiry, if emergency call on Wi-Fi is allowed and possible,
         * telephony shall cancel the scan and place the call on Wi-Fi. If emergency call on Wi-Fi
         * is not possible, then domain selection continues to wait for the scan result from the
         * radio. If an emergency scan result is received before the timer expires, the timer shall
         * be stopped and no dialing over Wi-Fi will be tried. If this value is set to 0, then
         * the timer is never started and domain selection waits for the scan result from the radio.
         *
         * The default value for the timer is 10 seconds.
         */
        public static final String KEY_EMERGENCY_SCAN_TIMER_SEC_INT = "imsemergency.emergency_scan_timer_sec_int";

        /**
         * Specifies the condition when emergency call shall be attempted on IMS over Wi-Fi.
         *
         * <p>Possible values are,
         * {@link #VOWIFI_REQUIRES_NONE}
         * {@link #VOWIFI_REQUIRES_SETTING_ENABLED}
         * {@link #VOWIFI_REQUIRES_VALID_EID}
         *
         * The default value for this key is {@link #VOWIFI_REQUIRES_NONE}.
         */
        public static final String KEY_EMERGENCY_VOWIFI_REQUIRES_CONDITION_INT = "imsemergency.emergency_vowifi_requires_condition_int";

        /**
         * The timer to wait for the call completion on the cellular network before attempting the
         * call over Wi-Fi. On timer expiry, if emergency call on Wi-Fi is allowed and possible,
         * telephony shall cancel the scan on the cellular network and place the call on Wi-Fi.
         * If dialing over cellular network is ongoing when timer expires, dialing over Wi-Fi
         * will be requested only when the ongoing dialing fails. If emergency call on Wi-Fi is not
         * possible, then domain selection continues to try dialing from the radio and the timer
         * remains expired. Later when calling over Wi-Fi is possible and dialing over cellular
         * networks fails, calling over Wi-Fi will be requested. The timer shall be restarted from
         * initial state if calling over Wi-Fi fails.
         * If this value is set to {@link #REDIAL_TIMER_DISABLED}, then the timer will never be
         * started.
         *
         * The default value for the timer is {@link #REDIAL_TIMER_DISABLED}.
         */
        public static final String KEY_MAXIMUM_CELLULAR_SEARCH_TIMER_SEC_INT = "imsemergency.maximum_cellular_search_timer_sec_int";

        /**
         * Specifies maximum number of emergency call retries over Wi-Fi.
         * This is valid only when {@link #DOMAIN_PS_NON_3GPP} is included in
         * {@link #KEY_EMERGENCY_DOMAIN_PREFERENCE_INT_ARRAY} or
         * {@link #KEY_EMERGENCY_DOMAIN_PREFERENCE_ROAMING_INT_ARRAY}.
         *
         * The default value for this key is 1.
         */
        public static final String KEY_MAXIMUM_NUMBER_OF_EMERGENCY_TRIES_OVER_VOWIFI_INT = "imsemergency.maximum_number_of_emergency_tries_over_vowifi_int";

        /**
         * Specifies whether the emergency call shall be preferred over IMS or not
         * irrespective of IMS registration status.
         * If the value of the config is {@code true} then emergency calls shall prefer IMS
         * when device is combined-attached in LTE network and IMS is not registered.
         * If the value of the config is {@code false} then emergency calls use CS domain
         * in the same scenario.
         *
         * The default value for this key is {@code false}.
         */
        public static final String KEY_PREFER_IMS_EMERGENCY_WHEN_VOICE_CALLS_ON_CS_BOOL = "imsemergency.prefer_ims_emergency_when_voice_calls_on_cs_bool";

        /**
         * Prefix of all imsemergency.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imsemergency.";

        /**
         * If emergency calls are only allowed with normal-registered service and UE should get
         * normal service in a short time with acquired band information, telephony
         * expects dialing emergency call will be completed in a short time.
         * If dialing is not completed with in a certain timeout, telephony shall place on
         * another SIM slot. If this value is set to {@link #REDIAL_TIMER_DISABLED}, then the timer
         * will never be started and domain selection continues on the current SIM slot.
         * The timer shall be started for the first trial of each subscription and shall be ignored
         * in the roaming networks and non-domestic networks.
         *
         * The default value for the timer is {@link #REDIAL_TIMER_DISABLED}.
         */
        public static final String KEY_QUICK_CROSS_STACK_REDIAL_TIMER_SEC_INT = "imsemergency.quick_cross_stack_redial_timer_sec_int";

        /**
         * This setting will be specify the wait time for refreshing
         * geolocation information before dialing emergency call.
         */
        public static final String KEY_REFRESH_GEOLOCATION_TIMEOUT_MILLIS_INT = "imsemergency.refresh_geolocation_timeout_millis_int";

        /**
         * Flag specifying whether UE would retry E911 call on
         * IMS PDN if emergency PDN setup failed.
         *
         * <p>If {@code true}: Allow UE to retry emergency call on
         * IMS PDN if emergency PDN setup failed.{@code false} otherwise.
         */
        public static final String KEY_RETRY_EMERGENCY_ON_IMS_PDN_BOOL = "imsemergency.retry_emergency_on_ims_pdn_bool";

        /**
         * Indicates whether limited service only scanning will be requested after VoLTE fails.
         * This value is applicable if the value of
         * {@link #KEY_EMERGENCY_NETWORK_SCAN_TYPE_INT} is any of {@link #SCAN_TYPE_NO_PREFERENCE}
         * or {@link #SCAN_TYPE_FULL_SERVICE_FOLLOWED_BY_LIMITED_SERVICE}.
         *
         * The default value is {@code false}.
         */
        public static final String KEY_SCAN_LIMITED_SERVICE_AFTER_VOLTE_FAILURE_BOOL = "imsemergency.scan_limited_service_after_volte_failure_bool";

        /**
         * Indicates whether the quick cross stack redial timer will be triggered only when
         * the device is registered to the network.
         *
         * The default value is {@code true}.
         */
        public static final String KEY_START_QUICK_CROSS_STACK_REDIAL_TIMER_WHEN_REGISTERED_BOOL = "imsemergency.start_quick_cross_stack_redial_timer_when_registered_bool";

        /**
         * This values indicates that the cross SIM redialing timer and maximum celluar search
         * timer shall be disabled.
         *
         * @see #KEY_CROSS_STACK_REDIAL_TIMER_SEC_INT
         * @see #KEY_QUICK_CROSS_STACK_REDIAL_TIMER_SEC_INT
         * @see #KEY_MAXIMUM_CELLULAR_SEARCH_TIMER_SEC_INT
         */
        public static final int REDIAL_TIMER_DISABLED = 0;

        /**
         * Modem will attempt to camp on a network with full service only.
         */
        public static final int SCAN_TYPE_FULL_SERVICE = 1;

        /**
         * Telephony shall attempt full service scan first.
         * If a full service network is not found, telephony shall attempt a limited service scan.
         */
        public static final int SCAN_TYPE_FULL_SERVICE_FOLLOWED_BY_LIMITED_SERVICE = 2;

        /**
         * No specific preference given to the modem. Modem can return an emergency
         * capable network either with limited service or full service.
         */
        public static final int SCAN_TYPE_NO_PREFERENCE = 0;

        /**
         * Default value.
         * If {@link ImsWfc#KEY_EMERGENCY_CALL_OVER_EMERGENCY_PDN_BOOL} is {@code true},
         * VoWi-Fi emergency call shall be attempted if Wi-Fi network is connected.
         * Otherwise, it shall be attempted if IMS is registered over Wi-Fi.
         */
        public static final int VOWIFI_REQUIRES_NONE = 0;

        /**
         * VoWi-Fi emergency call shall be attempted on IMS over Wi-Fi if Wi-Fi network is connected
         * and Wi-Fi calling setting is enabled. This value is applicable if the value of
         * {@link ImsWfc#KEY_EMERGENCY_CALL_OVER_EMERGENCY_PDN_BOOL} is {@code true}.
         */
        public static final int VOWIFI_REQUIRES_SETTING_ENABLED = 1;

        /**
         * VoWi-Fi emergency call shall be attempted on IMS over Wi-Fi if Wi-Fi network is connected
         * and Wi-Fi calling is activated successfully. The device shall have the valid
         * Entitlement ID if the user activates VoWi-Fi emergency calling successfully.
         * This value is applicable if the value of
         * {@link ImsWfc#KEY_EMERGENCY_CALL_OVER_EMERGENCY_PDN_BOOL} is {@code true}.
         */
        public static final int VOWIFI_REQUIRES_VALID_EID = 2;

        private ImsEmergency() {
        }
    }

    /**
     * IMS RTT configs. This groups the configs specific for text media,
     * RTT (Real Time Text).
     */
    public static final class ImsRtt {
        /**
         * Prefix of all imsrtt.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imsrtt.";

        /**
         * Integer representing payload type for RED/redundancy codec.
         *  <p>Payload type is an integer in dynamic payload type range 96-127
         *  as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_RED_PAYLOAD_TYPE_INT = "imsrtt.red_payload_type_int";

        /**
         * Integer representing payload type for T140 codec.
         *  <p>Payload type is an integer in dynamic payload type range 96-127
         *  as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_T140_PAYLOAD_TYPE_INT = "imsrtt.t140_payload_type_int";

        /**
         * Specifies the AS (Application Specific) SDP modifier for text media.
         *
         * <p>Expressed in kilobits per second as per RFC 3556 Section 2.
         */
        public static final String KEY_TEXT_AS_BANDWIDTH_KBPS_INT = "imsrtt.text_as_bandwidth_kbps_int";

        /**
         * Specifies the Text Codec capability.
         *
         * <p>Possible keys in this bundle are,
         * <UL>
         *     <LI>{@link #KEY_T140_PAYLOAD_TYPE_INT}</LI>
         *     <LI>{@link #KEY_RED_PAYLOAD_TYPE_INT}</LI>
         * </UL>
         */
        public static final String KEY_TEXT_CODEC_CAPABILITY_PAYLOAD_TYPES_BUNDLE = "imsrtt.text_codec_capability_payload_types_bundle";

        /**
         * Flag specifying whether text media is allowed on default bearer.
         *
         * <p>If {@code true}: text media can be sent on default bearer.
         * {@code false} otherwise.
         */
        public static final String KEY_TEXT_ON_DEFAULT_BEARER_SUPPORTED_BOOL = "imsrtt.text_on_default_bearer_supported_bool";

        /**
         * Flag specifying whether QoS preconditions are supported for text.
         *
         * <p>If {@code true}: QoS Preconditions are supported.
         * {@code false} otherwise.
         * <p>Reference: 3GPP TS 24.229
         */
        public static final String KEY_TEXT_QOS_PRECONDITION_SUPPORTED_BOOL = "imsrtt.text_qos_precondition_supported_bool";

        /**
         * Specifies the RR (RTCP bandwidth-Receiver) SDP modifier for
         * text media.
         *
         * <p>This indicates the RTCP bandwidth allocated to receivers
         * for text media.
         *
         * <p>Expressed in bits per second as per RFC 3556 Section 2.
         */
        public static final String KEY_TEXT_RR_BANDWIDTH_BPS_INT = "imsrtt.text_rr_bandwidth_bps_int";

        /**
         * Specifies the RS (RTCP bandwidth-Sender) SDP modifier for text media.
         *
         * <p>This indicates the RTCP bandwidth allocated to active data senders
         * for text media.
         *
         * <p>Expressed in bits per second as per RFC 3556 Section 2.
         */
        public static final String KEY_TEXT_RS_BANDWIDTH_BPS_INT = "imsrtt.text_rs_bandwidth_bps_int";

        private ImsRtt() {
        }
    }

    /**
     * Configs used by ImsServiceEntitlement.
     */
    public static final class ImsServiceEntitlement {
        /**
         * The address of the entitlement configuration server.
         *
         * Reference: GSMA TS.43-v5, section 2.1 Default Entitlement Configuration Server.
         */
        public static final String KEY_ENTITLEMENT_SERVER_URL_STRING = "imsserviceentitlement.entitlement_server_url_string";

        /**
         * The FCM sender ID for the carrier.
         * Used to trigger a carrier network requested entitlement configuration
         * via Firebase Cloud Messaging (FCM). Do not set if the carrier doesn't use FCM for network
         * requested entitlement configuration.
         *
         * Reference: GSMA TS.43-v5, section 2.4, Network Requested Entitlement Configuration.
         *
         * @see <a href="https://firebase.google.com/docs/cloud-messaging/concept-options#senderid">
         *     About FCM messages - Credentials</a>
         */
        public static final String KEY_FCM_SENDER_ID_STRING = "imsserviceentitlement.fcm_sender_id_string";

        /**
         * For some carriers, the network is not provisioned by default to support
         * IMS (VoLTE/VoWiFi/SMSoIP) service for all end users. Some type of network-side
         * provisioning must then take place before offering the IMS service to the end-user.
         *
         * {@code true} - need this ImsServiceEntitlement app to do IMS (VoLTE/VoWiFi/SMSoIP)
         * provisioning in the background before offering the IMS service to the end-user.
         *
         * Note: this is effective only if the carrier needs IMS provisioning, i.e.
         * {@link #KEY_CARRIER_VOLTE_PROVISIONING_REQUIRED_BOOL} is set to true.
         *
         * Reference: GSMA TS.43-v5, section 3 - 5, VoWiFi/VoLTE/SMSoIP entitlement configuration.
         */
        public static final String KEY_IMS_PROVISIONING_BOOL = "imsserviceentitlement.ims_provisioning_bool";

        /**
         * Prefix of all ImsServiceEntitlement.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imsserviceentitlement.";

        /**
         * For some carriers, end-users may be presented with a web portal of the carrier before
         * being allowed to use the VoWiFi service.
         * To support this feature, the app hosts a {@link android.webkit.WebView} in the foreground
         * VoWiFi entitlement configuration flow to show the web portal.
         *
         * {@code true} - show the VoWiFi portal in a webview.
         *
         * Note: this is effective only if the {@link #KEY_WFC_EMERGENCY_ADDRESS_CARRIER_APP_STRING}
         * is set to this app.
         *
         * Reference: GSMA TS.43-v5, section 3, VoWiFi entitlement configuration.
         */
        public static final String KEY_SHOW_VOWIFI_WEBVIEW_BOOL = "imsserviceentitlement.show_vowifi_webview_bool";

        private ImsServiceEntitlement() {
        }
    }

    /**
     * IMS SMS configs. This groups the configs specific for SMS over IMS
     */
    public static final class ImsSms {
        /**
         * Prefix of all imssms.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imssms.";

        /**
         * Flag specifying whether to allow SMS CSFB in case of
         * SMS over PS failure.
         *
         * <p>If {@code true}: allow SMS CSFB in case of SMS over PS failure.
         * {@code false} otherwise.
         */
        public static final String KEY_SMS_CSFB_RETRY_ON_FAILURE_BOOL = "imssms.sms_csfb_retry_on_failure_bool";

        /**
         * Maximum Retry Count for Failure, If the Retry Count exceeds this value,
         * it must display to User Interface as sending failed
         */
        public static final String KEY_SMS_MAX_RETRY_COUNT_INT = "imssms.sms_max_retry_count_int";

        /**
         * Maximum Retry Count for SMS over IMS on Failure, If the Retry Count exceeds this value,
         * and if the retry count is less than {@link #KEY_SMS_MAX_RETRY_COUNT_INT}
         * sending SMS should fallback to CS
         */
        public static final String KEY_SMS_MAX_RETRY_OVER_IMS_COUNT_INT = "imssms.sms_max_retry_over_ims_count_int";

        /**
         * Specifies the SMS over IMS format.
         *
         * <p>Possible values are,
         * {@link #SMS_FORMAT_3GPP},
         * {@link #SMS_FORMAT_3GPP2}
         */
        public static final String KEY_SMS_OVER_IMS_FORMAT_INT = "imssms.sms_over_ims_format_int";

        /**
         * Delay Timer Value in milliseconds
         * Retry SMS over IMS after this Timer expires
         */
        public static final String KEY_SMS_OVER_IMS_SEND_RETRY_DELAY_MILLIS_INT = "imssms.sms_over_ims_send_retry_delay_millis_int";

        /**
         * Flag specifying if SMS over IMS support is available or not.
         *
         * <p>If {@code true}: SMS over IMS support available.
         * {@code false}: otherwise.
         */
        public static final String KEY_SMS_OVER_IMS_SUPPORTED_BOOL = "imssms.sms_over_ims_supported_bool";

        /**
         * List of different RAT technologies on which SMS over IMS
         * is supported.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#IWLAN}
         * {@link AccessNetworkConstants.AccessNetworkType#UTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#GERAN}
         */
        public static final String KEY_SMS_OVER_IMS_SUPPORTED_RATS_INT_ARRAY = "imssms.sms_over_ims_supported_rats_int_array";

        /**
         * SMS RP-Cause Values for which Sending SMS should fallback
         */
        public static final String KEY_SMS_RP_CAUSE_VALUES_TO_FALLBACK_INT_ARRAY = "imssms.sms_rp_cause_values_to_fallback_int_array";

        /**
         * SMS RP-Cause Values for which SMS should be retried over IMS
         *
         * <p>Possible values are,
         * {@link SmsManager#SMS_RP_CAUSE_UNALLOCATED_NUMBER}
         * {@link SmsManager#SMS_RP_CAUSE_OPERATOR_DETERMINED_BARRING}
         * {@link SmsManager#SMS_RP_CAUSE_CALL_BARRING}
         * {@link SmsManager#SMS_RP_CAUSE_RESERVED}
         * {@link SmsManager#SMS_RP_CAUSE_SHORT_MESSAGE_TRANSFER_REJECTED}
         * {@link SmsManager#SMS_RP_CAUSE_DESTINATION_OUT_OF_ORDER}
         * {@link SmsManager#SMS_RP_CAUSE_UNIDENTIFIED_SUBSCRIBER}
         * {@link SmsManager#SMS_RP_CAUSE_FACILITY_REJECTED}
         * {@link SmsManager#SMS_RP_CAUSE_UNKNOWN_SUBSCRIBER}
         * {@link SmsManager#SMS_RP_CAUSE_NETWORK_OUT_OF_ORDER}
         * {@link SmsManager#SMS_RP_CAUSE_TEMPORARY_FAILURE}
         * {@link SmsManager#SMS_RP_CAUSE_CONGESTION}
         * {@link SmsManager#SMS_RP_CAUSE_RESOURCES_UNAVAILABLE}
         * {@link SmsManager#SMS_RP_CAUSE_FACILITY_NOT_SUBSCRIBED}
         * {@link SmsManager#SMS_RP_CAUSE_FACILITY_NOT_IMPLEMENTED}
         * {@link SmsManager#SMS_RP_CAUSE_INVALID_MESSAGE_REFERENCE_VALUE}
         * {@link SmsManager#SMS_RP_CAUSE_SEMANTICALLY_INCORRECT_MESSAGE}
         * {@link SmsManager#SMS_RP_CAUSE_INVALID_MANDATORY_INFORMATION}
         * {@link SmsManager#SMS_RP_CAUSE_MESSAGE_TYPE_NON_EXISTENT}
         * {@link SmsManager#SMS_RP_CAUSE_MESSAGE_INCOMPATIBLE_WITH_PROTOCOL_STATE}
         * {@link SmsManager#SMS_RP_CAUSE_INFORMATION_ELEMENT_NON_EXISTENT}
         * {@link SmsManager#SMS_RP_CAUSE_PROTOCOL_ERROR}
         * {@link SmsManager#SMS_RP_CAUSE_INTERWORKING_UNSPECIFIED
         */
        public static final String KEY_SMS_RP_CAUSE_VALUES_TO_RETRY_OVER_IMS_INT_ARRAY = "imssms.sms_rp_cause_values_to_retry_over_ims_int_array";

        /**
         * TR1 Timer Value in milliseconds,
         * Waits for RP-Ack from network for MO SMS.
         */
        public static final String KEY_SMS_TR1_TIMER_MILLIS_INT = "imssms.sms_tr1_timer_millis_int";

        /**
         * TR2 Timer Value in milliseconds,
         * Waits for RP-Ack from Transfer Layer for MT SMS.
         */
        public static final String KEY_SMS_TR2_TIMER_MILLIS_INT = "imssms.sms_tr2_timer_millis_int";

        /**
         * SMS format is 3GPP.
         */
        public static final int SMS_FORMAT_3GPP = 0;

        /**
         * SMS format is 3GPP2.
         */
        public static final int SMS_FORMAT_3GPP2 = 1;

        private ImsSms() {
        }
    }

    /**
     *   IMS supplementary services configs. This groups the configs required for
     *   supplementary services (SS) like XCAP over UT,
     *   Unstructured Supplementary Service Data(USSD).
     */
    public static final class ImsSs {
        /**
         * Activate call waiting on the carrier network when the user enables call waiting the
         * first time. Call waiting is then always considered enabled on the carrier network. If
         * the user disables call waiting, the setting will only be applied to the terminal based
         * call waiting service and the call will be rejected on the terminal.
         * The mismatch between CS calls and IMS calls can happen when the network based call
         * waiting service is in disabled state in the legacy 3G/2G networks while it's enabled
         * in the terminal side. However, if the user retrieves the setting again when the device
         * is in the legacy 3G/2G networks, the correct state will be shown to the user.
         */
        public static final int CALL_WAITING_SYNC_FIRST_CHANGE = 3;

        /**
         * Activate call waiting on the carrier network when the device boots or a subscription
         * using this carrier is loaded. Call waiting is always considered enabled on the carrier
         * network and the user setting for call waiting is applied on the terminal side only. If
         * the user disables call waiting, the call will be rejected on the terminal.
         * The mismatch between CS calls and IMS calls can happen when the network based call
         * waiting service is in disabled state in the legacy 3G/2G networks while it's enabled
         * in the terminal side.
         */
        public static final int CALL_WAITING_SYNC_FIRST_POWER_UP = 2;

        /**
         * Do not synchronize the call waiting service state between the carrier network and
         * the terminal based IMS call waiting service. If the user changes the call waiting setting
         * when IMS is registered, the change will only be applied to the terminal based call
         * waiting service. If IMS is not registered when call waiting is changed, synchronize this
         * setting with the carrier network.
         */
        public static final int CALL_WAITING_SYNC_IMS_ONLY = 4;

        /**
         * Do not synchronize the user's call waiting setting with the network. Call waiting is
         * always enabled on the carrier network and the user setting for call waiting is applied
         * on the terminal side. If the user disables call waiting, the call will be rejected on
         * the terminal.
         */
        public static final int CALL_WAITING_SYNC_NONE = 0;

        /**
         * The change of user’s setting is always passed to the carrier network
         * and then synchronized to the terminal based call waiting solution over IMS.
         * If changing the service over the carrier network is not successful,
         * the setting over IMS shall not be changed.
         */
        public static final int CALL_WAITING_SYNC_USER_CHANGE = 1;

        /**
         * Flag that controls whether network initiated USSD over IMS is
         * supported by the UE.
         *
         * <p>If {@code true}:  Support Available.{@code false}: Otherwise.
         * Reference: 3GPP 24.390.
         */
        public static final String KEY_NETWORK_INITIATED_USSD_OVER_IMS_SUPPORTED_BOOL = "imsss.network_initiated_ussd_over_ims_supported_bool";

        /**
         * Prefix of all imsss.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imsss.";

        /**
         * Flag indicating whether the user setting for terminal-based call waiting
         * is enabled by default or not.
         * This configuration is valid only when
         * {@link #KEY_UT_TERMINAL_BASED_SERVICES_INT_ARRAY} includes
         * {@link #SUPPLEMENTARY_SERVICE_CW}.
         *
         * The default value for this key is {@code true}.
         */
        public static final String KEY_TERMINAL_BASED_CALL_WAITING_DEFAULT_ENABLED_BOOL = "imsss.terminal_based_call_waiting_default_enabled_bool";

        /**
         * Flag indicating the way to synchronize the setting between CS and IMS.
         *
         * <p>Possible values are,
         * {@link #CALL_WAITING_SYNC_NONE},
         * {@link #CALL_WAITING_SYNC_USER_CHANGE},
         * {@link #CALL_WAITING_SYNC_FIRST_POWER_UP},
         * {@link #CALL_WAITING_SYNC_FIRST_CHANGE},
         * {@link #CALL_WAITING_SYNC_IMS_ONLY}.
         *
         * This configuration is valid only when
         * {@link #KEY_UT_TERMINAL_BASED_SERVICES_INT_ARRAY} includes
         * {@link #SUPPLEMENTARY_SERVICE_CW}.
         *
         * <p>If key is invalid or not configured, the default value
         * {@link #CALL_WAITING_SYNC_FIRST_CHANGE} will apply.
         */
        public static final String KEY_TERMINAL_BASED_CALL_WAITING_SYNC_TYPE_INT = "imsss.terminal_based_call_waiting_sync_type_int";

        /**
         * Flag that controls whether Circuit Switched Fallback (CSFB)
         * option is available when XCAP over UT fails.
         *
         * <p>If {@code false}:  XCAP over UT only with no CSFB option.
         * If XCAP over UT fails, return error.
         * if {@code true}, Use CSFB if XCAP over UT fails.
         */
        public static final String KEY_USE_CSFB_ON_XCAP_OVER_UT_FAILURE_BOOL = "imsss.use_csfb_on_xcap_over_ut_failure_bool";

        /**
         * Specifies the XCAP Application Server fully qualified domain name (FQDN).
         * <p> Reference: 24.623 Section 5.2.3.
         */
        public static final String KEY_UT_AS_SERVER_FQDN_STRING = "imsss.ut_as_server_fqdn_string";

        /**
         * Specifies the XCAP Application Server Remote port.
         * As XCAP is a usage of HTTP, the default value is same as HTTP, i.e. 80.
         */
        public static final String KEY_UT_AS_SERVER_PORT_INT = "imsss.ut_as_server_port_int";

        /**
         * Specifies the 'XCAP over UT' IP Type when device is
         * on Home Network.
         *
         * <p>Possible values are,
         * {@link ApnSetting#PROTOCOL_IPV4V6},
         * {@link ApnSetting#PROTOCOL_IP},
         * {@link ApnSetting#PROTOCOL_IPV6}
         *
         * If key is invalid or not configured, the default value
         * {@link ApnSetting#PROTOCOL_IPV4V6} will apply.
         */
        public static final String KEY_UT_IPTYPE_HOME_INT = "imsss.ut_iptype_home_int";

        /**
         * Specifies the 'XCAP over UT' IP Type when device is roaming.
         *
         * <p>Possible values are,
         * {@link ApnSetting#PROTOCOL_IPV4V6},
         * {@link ApnSetting#PROTOCOL_IP},
         * {@link ApnSetting#PROTOCOL_IPV6}
         *
         * If key is invalid or not configured, the default value
         * {@link ApnSetting#PROTOCOL_IPV4V6} will apply.
         */
        public static final String KEY_UT_IPTYPE_ROAMING_INT = "imsss.ut_iptype_roaming_int";

        /**
         * Flag that controls whether XCAP over UT status need to be
         * dependent on IMS registration.
         *
         * <p>If {@code true}: XCAP over UT status need to be
         * dependent on IMS registration.
         * {@code false} otherwise.
         */
        public static final String KEY_UT_REQUIRES_IMS_REGISTRATION_BOOL = "imsss.ut_requires_ims_registration_bool";

        /**
         * List of UT services that are Server based.
         *
         * <p>Possible values are,
         * <UL>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CW}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CF_ALL}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CF_CFU}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CF_ALL_CONDITONAL_FORWARDING}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CF_CFB}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CF_CFNRY}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CF_CFNRC}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CF_CFNL}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_IDENTIFICATION_OIP}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_IDENTIFICATION_TIP}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_IDENTIFICATION_OIR}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_IDENTIFICATION_TIR}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_ALL}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_OBS}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_IBS}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_BAOC}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_BOIC}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_BOIC_EXHC}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_BAIC}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_BIC_ROAM}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_ACR}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CB_BIL}</LI>
         * </UL>
         */
        public static final String KEY_UT_SERVER_BASED_SERVICES_INT_ARRAY = "imsss.ut_server_based_services_int_array";

        /**
         * Flag that controls whether XCAP over UT is enabled or not
         * when PS data is turned off.
         *
         * <p>If {@code true}: XCAP over UT is enabled when PS data is off.
         * {@code false}: Otherwise.
         *
         * Reference: IR.92 Section 5.5.1
         */
        public static final String KEY_UT_SUPPORTED_WHEN_PS_DATA_OFF_BOOL = "imsss.ut_supported_when_ps_data_off_bool";

        /**
         * Flag that controls whether XCAP over UT is supported
         * when on roaming network.
         *
         * <p>If {@code true}: XCAP over UT is supported when on
         * roaming network.
         * {@code false} otherwise.
         */
        public static final String KEY_UT_SUPPORTED_WHEN_ROAMING_BOOL = "imsss.ut_supported_when_roaming_bool";

        /**
         * List of UT services that are terminal based.
         *
         * By default, all services are server based and defined in
         * {@link #KEY_UT_SERVER_BASED_SERVICES_INT_ARRAY}.
         * Adding here will override that service setting to terminal based.
         *
         * <p>Possible values are,
         * <UL>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_CW}</LI>
         *     <LI>{@link #SUPPLEMENTARY_SERVICE_IDENTIFICATION_OIR}</LI>
         * </UL>
         */
        public static final String KEY_UT_TERMINAL_BASED_SERVICES_INT_ARRAY = "imsss.ut_terminal_based_services_int_array";

        /**
         * Specifies the preferred transport to be used for XCAP over UT.
         *
         * <p>Possible values are,
         * {@link Ims#PREFERRED_TRANSPORT_TCP},
         * {@link Ims#PREFERRED_TRANSPORT_TLS}
         *
         * <p>If key is invalid or not configured, the default value
         * {@link Ims#PREFERRED_TRANSPORT_TCP} will apply.
         */
        public static final String KEY_UT_TRANSPORT_TYPE_INT = "imsss.ut_transport_type_int";

        /**
         * List of different RAT technologies on which XCAP over UT
         * is supported.
         *
         * <p>Possible values are,
         * {@link AccessNetworkConstants.AccessNetworkType#NGRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#EUTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#IWLAN}
         * {@link AccessNetworkConstants.AccessNetworkType#UTRAN}
         * {@link AccessNetworkConstants.AccessNetworkType#GERAN}
         */
        public static final String KEY_XCAP_OVER_UT_SUPPORTED_RATS_INT_ARRAY = "imsss.xcap_over_ut_supported_rats_int_array";

        /**
         * Call Barring - Anonymous Call Rejection/Barring of all anonymous
         * incoming number support as per 3GPP TS 24.611.
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_ACR = 20;

        /**
         * Call Barring - All barring services,
         * This value is associated with MMI support service code 330
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_ALL = 12;

        /**
         * Call Barring - Barring of all incoming calls (BAIC)
         * support as per 3GPP TS 24.611.
         *
         * <p>This value is associated with MMI support service code 35
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_BAIC = 18;

        /**
         * Call Barring - Barring of all outgoing calls (BAOC)
         * support as per 3GPP TS 24.611.
         *
         * <p>This value is associated with MMI support service code 33
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_BAOC = 14;

        /**
         * Call Barring - Barring of incoming calls when roaming outside
         * the home PLMN country (BIC-ROAM) support as per 3GPP TS 24.611.
         *
         * <p>This value is associated with MMI support service code 351
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_BIC_ROAM = 19;

        /**
         * Call Barring - Barring list of incoming numbers support.
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_BIL = 21;

        /**
         * Call Barring - Barring of outgoing international calls
         * (BOIC) support as per 3GPP TS 24.611.
         *
         * <p>This value is associated with MMI support service code 331
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_BOIC = 15;

        /**
         * Call Barring - Barring of outgoing international calls
         * except those directed to the home PLMN country (BOIC-EXHC) support
         * as per 3GPP TS 24.611.
         *
         * <p>This value is associated with MMI support service code 332
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_BOIC_EXHC = 16;

        /**
         * Call Barring - Incoming barring services,
         * This value is associated with MMI support service code 353
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_IBS = 17;

        /**
         * Call Barring - Outgoing barring services,
         * This value is associated with MMI support service code 333
         * as indicated TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CB_OBS = 13;

        /**
         * Call Diversion - All call forwarding support as per 3GPP 24.604.
         *
         * <p>This value is associated with MMI support service code 002
         * as indicated in TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CF_ALL = 1;

        /**
         * Call Diversion - All conditional call forwarding support as
         * per 3GPP 24.604.
         *
         * <p>This value is associated with MMI support service code 004
         * as indicated in TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CF_ALL_CONDITONAL_FORWARDING = 3;

        /**
         * Call Diversion - Call forwarding on mobile subscriber busy (CFB)
         * support as per 3GPP 24.604.
         *
         * <p>This value is associated with MMI support service code 67
         * as indicated in TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CF_CFB = 4;

        /**
         * Communication Forwarding on Not Logged-in (CFNL).
         * support as per 3GPP 24.604 Section 4.2.1.7
         */
        public static final int SUPPLEMENTARY_SERVICE_CF_CFNL = 7;

        /**
         * Call Diversion - Call forwarding on mobile subscriber not reachable
         * (CFNRC) support as per 3GPP 24.604.
         *
         * <p>This value is associated with MMI support service code 62
         * as indicated in TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CF_CFNRC = 6;

        /**
         * Call Diversion - Call forwarding on no reply (CFNRY)
         * support as per 3GPP 24.604.
         *
         * <p>This value is associated with MMI support service code 61
         * as indicated in TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CF_CFNRY = 5;

        /**
         * Call Diversion - All Unconditional call forwarding support (CFU) as
         * per 3GPP 24.604.
         *
         * <p>This value is associated with MMI support service code 21
         * as indicated in TS 22.030 Table B.1
         */
        public static final int SUPPLEMENTARY_SERVICE_CF_CFU = 2;

        /**
         * Communication Waiting (CW) support as per 3GPP 24.615.
         */
        public static final int SUPPLEMENTARY_SERVICE_CW = 0;

        /**
         * Originating Identification Presentation (OIP) support
         * as per 3GPP 24.607.
         */
        public static final int SUPPLEMENTARY_SERVICE_IDENTIFICATION_OIP = 8;

        /**
         * Originating Identification Restriction (OIR) support
         * as per 3GPP 24.607.
         */
        public static final int SUPPLEMENTARY_SERVICE_IDENTIFICATION_OIR = 10;

        /**
         * Terminating Identification Presentation (TIP) support
         * as per 3GPP 24.608.
         */
        public static final int SUPPLEMENTARY_SERVICE_IDENTIFICATION_TIP = 9;

        /**
         * Terminating Identification Restriction (TIR) support
         * as per 3GPP 24.608.
         */
        public static final int SUPPLEMENTARY_SERVICE_IDENTIFICATION_TIR = 11;

        private ImsSs() {
        }
    }

    /**
     * IMS Voice configs. This groups the configs required for IMS Voice - VoNR/VoLTE
     *
     * <p>Reference: IR.92
     */
    public static final class ImsVoice {
        /**
         * SRVCC access transfer for calls in alerting phase as per 3GPP 24.237
         * and IR.64 Section 4.4.
         * Media feature tag used: g.3gpp.srvcc-alerting.
         */
        public static final int ALERTING_SRVCC_SUPPORT = 1;

        /**
         * AMR NB/WB Payload format is bandwidth-efficient.
         */
        public static final int BANDWIDTH_EFFICIENT = 0;

        /**
         * Indicates support for basic SRVCC, typically 1 active call
         * as detailed in IR.92 Section A.3.
         */
        public static final int BASIC_SRVCC_SUPPORT = 0;

        /**
         * The SIP SUBSCRIBE to conference state events is sent in the
         * SIP INVITE dialog between the UE and the conference server.
         *
         * <p>Reference: IR.92 Section 2.3.3.
         */
        public static final int CONFERENCE_SUBSCRIBE_TYPE_IN_DIALOG = 0;

        /**
         * The SIP SUBSCRIBE to conference state events is sent out of
         * the SIP INVITE dialog between the UE and the conference server.
         *
         * <p>Reference: IR.92 Section 2.3.3.
         */
        public static final int CONFERENCE_SUBSCRIBE_TYPE_OUT_OF_DIALOG = 1;

        /**
         * EVS encoded Bandwidth is Full Band (FB).
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_FB = 3;

        /**
         * EVS encoded Bandwidth is Narrow Band (NB).
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_NB = 0;

        /**
         * EVS encoded Bandwidth is in the range NB,WB.
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_NB_WB = 4;

        /**
         * EVS encoded Bandwidth is in the range NB,WB,SWB.
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_NB_WB_SWB = 5;

        /**
         * EVS encoded Bandwidth is in the range NB,WB,SWB,FB.
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_NB_WB_SWB_FB = 6;

        /**
         * EVS encoded Bandwidth is Super WideBand (SWB).
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_SWB = 2;

        /**
         * EVS encoded Bandwidth is Wide Band (WB).
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_WB = 1;

        /**
         * EVS encoded Bandwidth is in the range WB,SWB.
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_WB_SWB = 7;

        /**
         * EVS encoded Bandwidth is in the range WB,SWB,FB.
         * Reference: 3GPP 26.441 Table 1.
         */
        public static final int EVS_ENCODED_BW_TYPE_WB_SWB_FB = 8;

        /**
         * Indicates the EVS AMR-WB IO mode. 3GPP 26.445 Section 3.1
         */
        public static final int EVS_OPERATIONAL_MODE_AMRWB_IO = 1;

        /**
         * Indicates the EVS primary mode. 3GPP 26.445 Section 3.1
         */
        public static final int EVS_OPERATIONAL_MODE_PRIMARY = 0;

        /**
         * EVS primary mode with bitrate 128.0 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_128_0_KBPS = 11;

        /**
         * EVS primary mode with bitrate 13.2 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_13_2_KBPS = 4;

        /**
         * EVS primary mode with bitrate 16.4 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_16_4_KBPS = 5;

        /**
         * EVS primary mode with bitrate 24.4 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_24_4_KBPS = 6;

        /**
         * EVS primary mode with bitrate 32.0 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_32_0_KBPS = 7;

        /**
         * EVS primary mode with bitrate 48.0 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_48_0_KBPS = 8;

        /**
         * EVS primary mode with bitrate 5.9 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_5_9_KBPS = 0;

        /**
         * EVS primary mode with bitrate 64.0 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_64_0_KBPS = 9;

        /**
         * EVS primary mode with bitrate 7.2 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_7_2_KBPS = 1;

        /**
         * EVS primary mode with bitrate 8.0 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_8_0_KBPS = 2;

        /**
         * EVS primary mode with bitrate 96.0 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_96_0_KBPS = 10;

        /**
         * EVS primary mode with bitrate 9.6 kbps
         */
        public static final int EVS_PRIMARY_MODE_BITRATE_9_6_KBPS = 3;

        /**
         * Specifies the codec attributes of different payload types in
         * the AMR NarrowBand (AMR-NB) codec.
         *
         * <p> The keys in this bundle are payload types specified
         * in {@link #KEY_AMRNB_PAYLOAD_TYPE_INT_ARRAY}.
         *
         * <p>Codec attributes allowed as part of AMR-NB codec bundle are,
         * <UL>
         *     <LI>{@link #KEY_AMR_CODEC_ATTRIBUTE_PAYLOAD_FORMAT_INT}</LI>
         *     <LI>{@link #KEY_AMR_CODEC_ATTRIBUTE_MODESET_INT_ARRAY}</LI>
         * </UL>
         *
         * <p> If this bundle is not configured and AMRNB payload type is added
         * in {@link #KEY_AMRNB_PAYLOAD_TYPE_INT_ARRAY}, then default
         * values as in the individual codec attribute to be used
         * for that payload type.
         */
        public static final String KEY_AMRNB_PAYLOAD_DESCRIPTION_BUNDLE = "imsvoice.amrnb_payload_description_bundle";

        /**
         * A list of integers representing the different payload types
         * in AMR-NB codec in priority order from highest to lowest.
         * <p>Payload type is an integer in dynamic payload type range 96-127
         * as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_AMRNB_PAYLOAD_TYPE_INT_ARRAY = "imsvoice.amrnb_payload_type_int_array";

        /**
         * Specifies the codec attributes of different payload types in
         * the AMR WideBand (AMR-WB) codec.
         *
         * <p> The keys in this bundle are payload types specified
         * in {@link #KEY_AMRWB_PAYLOAD_TYPE_INT_ARRAY}.
         *
         * <p>Codec attributes allowed as part of AMR-NB codec bundle are,
         * <UL>
         *     <LI>{@link #KEY_AMR_CODEC_ATTRIBUTE_PAYLOAD_FORMAT_INT}</LI>
         *     <LI>{@link #KEY_AMR_CODEC_ATTRIBUTE_MODESET_INT_ARRAY}</LI>
         * </UL>
         *
         * <p> If this bundle is not configured and AMRWB payload type is added
         * in {@link #KEY_AMRWB_PAYLOAD_TYPE_INT_ARRAY}, then default
         * values as in the individual codec attribute to be used
         * for that payload type.
         */
        public static final String KEY_AMRWB_PAYLOAD_DESCRIPTION_BUNDLE = "imsvoice.amrwb_payload_description_bundle";

        /**
         * A list of integers representing the different payload types
         * in AMR-WB codec in priority order from highest to lowest.
         * <p>Payload type is an integer in dynamic payload type range 96-127
         * as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_AMRWB_PAYLOAD_TYPE_INT_ARRAY = "imsvoice.amrwb_payload_type_int_array";

        /**
         * Restricts the active mode set to a subset of all modes in the codec.
         *
         * <p>This attribute is optional. If value is set, then session mode
         * set is restricted to the modes specified in this list. If this value
         * is not specified, then all available modes in the codec are allowed.
         * This attribute is applicable for AMR-WB, AMR-NB,
         * and EVS codec (operating in AMR-WB IO Mode).
         *
         * <p>Possible values are subset of,
         * [0,1,2,3,4,5,6,7,8] - AMRWB with the modes representing nine speech codec modes
         * with bit rates of 6.6, 8.85, 12.65, 14.25,  15.85, 18.25, 19.85, 23.05, 23.85 kbps.
         * [0,1,2,3,4,5,6,7] - AMRNB with the modes representing eight speech codec modes
         * with bit rates of 4.75, 5.15, 5.90, 6.70, 7.40, 7.95, 10.2, 12.2 kbps.
         *
         * <p>If value is not specified, then it means device supports all
         * modes in the codec but not included in SDP.
         *
         * <p>Reference: RFC 4867 Section 8.1, 3GPP 26.445 A.3.1
         */
        public static final String KEY_AMR_CODEC_ATTRIBUTE_MODESET_INT_ARRAY = "imsvoice.amr_codec_attribute_modeset_int_array";

        /**
         * Specifies the payload format of the AMR-NB/AMR-WB codec.
         *
         * <p>Possible values are,
         * {@link #BANDWIDTH_EFFICIENT},
         * {@link #OCTET_ALIGNED}
         *
         * <p>If value is not specified, payload format is
         * {@link #BANDWIDTH_EFFICIENT}.
         *
         * <p>Reference: RFC 4867 Section 8.1.
         */
        public static final String KEY_AMR_CODEC_ATTRIBUTE_PAYLOAD_FORMAT_INT = "imsvoice.amr_codec_attribute_payload_format_int";

        /**
         * Specifies the AS (Application Specific) SDP modifier for audio media.
         *
         * <p>This value is expressed in kilobits per second.
         * Reference: RFC 3556 Section 2.
         */
        public static final String KEY_AUDIO_AS_BANDWIDTH_KBPS_INT = "imsvoice.audio_as_bandwidth_kbps_int";

        /**
         * Specifies the Audio Codec capability. This contains a list of payload types
         * representing different audio codec instances.
         *
         * <p> The priority of the codecs is EVS, AMRWB, AMRNB,  DTMF WB, DTMF NB
         * from highest to lowest. In each individual codec, the priority is determined
         * by the order of the payload types from highest to lowest.
         *
         * <p>Possible keys in this bundle are,
         * <UL>
         *     <LI>{@link #KEY_EVS_PAYLOAD_TYPE_INT_ARRAY}</LI>
         *     <LI>{@link #KEY_AMRWB_PAYLOAD_TYPE_INT_ARRAY}</LI>
         *     <LI>{@link #KEY_AMRNB_PAYLOAD_TYPE_INT_ARRAY}</LI>
         *     <LI>{@link #KEY_DTMFWB_PAYLOAD_TYPE_INT_ARRAY}</LI>
         *     <LI>{@link #KEY_DTMFNB_PAYLOAD_TYPE_INT_ARRAY}</LI>
         * </UL>
         * <p>To specify payload descriptions for each of the audio payload types, see
         * <UL>
         *     <LI>{@link #KEY_EVS_PAYLOAD_DESCRIPTION_BUNDLE}</LI>
         *     <LI>{@link #KEY_AMRNB_PAYLOAD_DESCRIPTION_BUNDLE}</LI>
         *     <LI>{@link #KEY_AMRWB_PAYLOAD_DESCRIPTION_BUNDLE}</LI>
         * </UL>
         */
        public static final String KEY_AUDIO_CODEC_CAPABILITY_PAYLOAD_TYPES_BUNDLE = "imsvoice.audio_codec_capability_payload_types_bundle";

        /**
         * List of various reasons of media inactivity for which
         * voice/emergency call will end.
         *
         * <p>Possible values are,
         * {@link Ims#RTCP_INACTIVITY_ON_HOLD},
         * {@link Ims#RTCP_INACTIVITY_ON_CONNECTED},
         * {@link Ims#RTP_INACTIVITY_ON_CONNECTED}
         * {@link Ims#E911_RTCP_INACTIVITY_ON_CONNECTED},
         * {@link Ims#E911_RTP_INACTIVITY_ON_CONNECTED}
         */
        public static final String KEY_AUDIO_INACTIVITY_CALL_END_REASONS_INT_ARRAY = "imsvoice.audio_inactivity_call_end_reasons_int_array";

        /**
         * Specifies the RR SDP modifier for audio media. This indicates the RTCP
         * bandwidth allocated to receivers for audio media.
         *
         * <p>This value is expressed in bits per second.
         * Reference: RFC 3556 Section 2.
         */
        public static final String KEY_AUDIO_RR_BANDWIDTH_BPS_INT = "imsvoice.audio_rr_bandwidth_bps_int";

        /**
         * Specifies the RS SDP modifier for audio media. This indicates the RTCP
         * bandwidth allocated to active data senders for audio media.
         *
         * <p>This value is expressed in bits per second.
         * Reference: RFC 3556 Section 2.
         */
        public static final String KEY_AUDIO_RS_BANDWIDTH_BPS_INT = "imsvoice.audio_rs_bandwidth_bps_int";

        /**
         * Specifies the timeout value for RTCP inactivity for audio media.
         * <p>On timer expiry, call will end.
         * See {@link #KEY_AUDIO_INACTIVITY_CALL_END_REASONS_INT_ARRAY} for more
         * details.
         * <p> Value of 0 means this timer is not enabled.
         */
        public static final String KEY_AUDIO_RTCP_INACTIVITY_TIMER_MILLIS_INT = "imsvoice.audio_rtcp_inactivity_timer_millis_int";

        /**
         * Specifies the timeout value for RTP inactivity for audio media.
         * <p>On timer expiry, call will end.
         * See {@link #KEY_AUDIO_INACTIVITY_CALL_END_REASONS_INT_ARRAY} for more
         * details.
         * <p> Value of 0 means this timer is not enabled.
         */
        public static final String KEY_AUDIO_RTP_INACTIVITY_TIMER_MILLIS_INT = "imsvoice.audio_rtp_inactivity_timer_millis_int";

        /**
         * Flag specifying whether VoLTE should be available when on
         * roaming network.
         *
         * <p>If {@code false}: hard disabled.
         * If {@code true}: then depends on availability, etc.
         */
        public static final String KEY_CARRIER_VOLTE_ROAMING_AVAILABLE_BOOL = "imsvoice.carrier_volte_roaming_available_bool";

        /**
         * Specifies if the client is capable to transmit with a restricted mode
         * change period. This attribute is applicable for EVS codec in
         * AMR-WB IO mode and AMR-WB.
         *
         * <p>Possible values are 1, 2. If this key is not present,
         * then behavior as per value 1 is applicable and this
         * parameter is not included in SDP.
         *
         * <p>Reference: RFC 4867 Section 8.1.
         */
        public static final String KEY_CODEC_ATTRIBUTE_MODE_CHANGE_CAPABILITY_INT = "imsvoice.codec_attribute_mode_change_capability_int";

        /**
         * Specifies the allowed mode changes for the sender in the active mode set.
         * This attribute is applicable for EVS codec in AMR-WB IO mode
         * and AMR-WB.
         *
         * <p>Possible values are 0, 1. If value is 1, then the sender should only
         * perform mode changes to the neighboring modes in the active codec mode set.
         * If value is 0, then mode changes between any two modes
         * in the active codec mode set is allowed.
         * If the key is not present, behavior as per value 0 is applicable and this
         * parameter is not included in SDP.
         *
         * <p>Reference: RFC 4867 Section 8.1.
         */
        public static final String KEY_CODEC_ATTRIBUTE_MODE_CHANGE_NEIGHBOR_INT = "imsvoice.codec_attribute_mode_change_neighbor_int";

        /**
         * Specifies the number of frame-blocks. This indicates the frame-block period
         * at which codec mode changes are allowed for the sender. This attribute is
         * applicable for EVS codec in AMR-WB IO mode and AMR-WB.
         *
         * <p>Possible values are 1, 2.
         * If the key is not present, behavior as per value 1 is applicable and this
         * parameter is not included in SDP.
         *
         * <p>Reference: RFC 4867 Section 8.1.
         */
        public static final String KEY_CODEC_ATTRIBUTE_MODE_CHANGE_PERIOD_INT = "imsvoice.codec_attribute_mode_change_period_int";

        /**
         * Used to specify the conference factory URI.
         *
         * <p>If this is empty, then conference URI is generated from MCC/MNC as
         * specified in clause 13.10 of 3GPP 23.003.
         */
        public static final String KEY_CONFERENCE_FACTORY_URI_STRING = "imsvoice.conference_factory_uri_string";

        /**
         * This is used to specify whether the SIP SUBSCRIBE to conference state events,
         * is sent in or out of the SIP INVITE dialog between the UE and the
         * conference server.
         *
         * <p>Reference: IR.92 Section 2.3.3.
         *
         * <p>Possible values are,
         * {@link #CONFERENCE_SUBSCRIBE_TYPE_IN_DIALOG},
         * {@link #CONFERENCE_SUBSCRIBE_TYPE_OUT_OF_DIALOG}
         *
         * An empty array indicates SUBSCRIBE to conference event package
         * is not required.
         */
        public static final String KEY_CONFERENCE_SUBSCRIBE_TYPE_INT = "imsvoice.conference_subscribe_type_int";

        /**
         * Specifies the dedicated bearer wait time during call establishment.
         *
         * <p>If dedicated bearer is not established within this time and if
         * {@link #KEY_VOICE_ON_DEFAULT_BEARER_SUPPORTED_BOOL} is false, then call setup would fail.
         * <p>If dedicated bearer is not established within this time and if
         * {@link #KEY_VOICE_ON_DEFAULT_BEARER_SUPPORTED_BOOL} is true, then the media is allowed
         * on default bearer.
         */
        public static final String KEY_DEDICATED_BEARER_WAIT_TIMER_MILLIS_INT = "imsvoice.dedicated_bearer_wait_timer_millis_int";

        /**
         * A list of integers representing the different payload types
         * in DTMF NB codec in priority order from highest to lowest.
         * <p>Payload type is an integer in dynamic payload type range 96-127
         * as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_DTMFNB_PAYLOAD_TYPE_INT_ARRAY = "imsvoice.dtmfnb_payload_type_int_array";

        /**
         * A list of integers representing the different payload types
         * in DTMF WB codec in priority order from highest to lowest.
         * <p>Payload type is an integer in dynamic payload type range 96-127
         * as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_DTMFWB_PAYLOAD_TYPE_INT_ARRAY = "imsvoice.dtmfwb_payload_type_int_array";

        /**
         * Specifies the EVS codec encoding bandwidth options.
         *
         * Possible values are,
         * {@link #EVS_ENCODED_BW_TYPE_NB},
         * {@link #EVS_ENCODED_BW_TYPE_WB},
         * {@link #EVS_ENCODED_BW_TYPE_SWB},
         * {@link #EVS_ENCODED_BW_TYPE_FB},
         * {@link #EVS_ENCODED_BW_TYPE_NB_WB},
         * {@link #EVS_ENCODED_BW_TYPE_NB_WB_SWB},
         * {@link #EVS_ENCODED_BW_TYPE_NB_WB_SWB_FB},
         * {@link #EVS_ENCODED_BW_TYPE_WB_SWB},
         * {@link #EVS_ENCODED_BW_TYPE_WB_SWB_FB}
         *
         * If this key is not specified, then the behavior is same as
         * value {@link #EVS_ENCODED_BW_TYPE_NB_WB_SWB}
         *
         * <p>Reference: 3GPP 26.441 Table 1.
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_BANDWIDTH_INT = "imsvoice.evs_codec_attribute_bandwidth_int";

        /**
         * Specifies the range of source codec bit-rate for EVS Primary mode
         * in the session. This is expressed in kilobits per second and
         * applicable for both the send and the receive directions.
         *
         * <p>The range is specified as integer aray of size 2,
         * represented as [low, high], where low <= high
         *
         * <p>Possible values for low and high are,
         * {@link #EVS_PRIMARY_MODE_BITRATE_5_9_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_7_2_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_8_0_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_9_6_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_13_2_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_16_4_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_24_4_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_32_0_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_48_0_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_64_0_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_96_0_KBPS},
         * {@link #EVS_PRIMARY_MODE_BITRATE_128_0_KBPS}
         *
         * If this key is not specified, then the behavior is same as
         * value {@link #EVS_PRIMARY_MODE_BITRATE_24_4_KBPS}
         *
         * <p>Reference: 3GPP 26.445 Section A.3.1
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_BITRATE_INT_ARRAY = "imsvoice.evs_codec_attribute_bitrate_int_array";

        /**
         * Specifies the number of audio channels.
         * If this is not present, then default value of 1 will apply.
         *
         * <p>Reference: RFC 3551
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_CHANNELS_INT = "imsvoice.evs_codec_attribute_channels_int";

        /**
         * Specifies the Channel aware mode (ch-aw-recv) for the receive direction.
         * This is applicable for EVS codec.
         *
         * <p> Permissible values are -1, 0, 2, 3, 5, and 7.
         * If this key is not specified, then the behavior is same as value 0
         * (channel aware mode disabled).
         * <p> If this key is configured, then device is expected to send
         * this parameter in the SDP offer.
         *
         * <p>Reference: 3GPP TS 26.445 section 4.4.5, 3GPP 26.445 Section A.3.1
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_CH_AW_RECV_INT = "imsvoice.evs_codec_attribute_ch_aw_recv_int";

        /**
         * Indicates whether the Codec Mode Request (CMR) is supported
         * for the session.
         * This attribute is applicable for EVS codec in Primary Mode only.
         *
         * <p>Possible values are -1, 0, 1. If this key is not present,
         * then behavior as per value 0 is applicable.
         *
         * <p>Reference: 3GPP 26.445 Section A.3.1, 3GPP 26.114 Table 6.2a
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_CMR_INT = "imsvoice.codec_attribute_cmr_int";

        /**
         * Specifies whether DTX (Discontinuous transmission) is enabled
         * or not. This applies to both directions in the session.
         * This attribute is applicable for EVS codec and can be used
         * in both EVS Primary mode and EVS AMR-WB IO mode.
         *
         * <p>If {@code true}: Indicates DTX is enabled.
         * If {@code false}: Indicates DTX is disabled.
         *
         * <p>If this is not present, then default value of {@code true}
         * will apply.
         * <p>Reference: 3GPP TS 26.445 Section A.3.1.
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_DTX_BOOL = "imsvoice.evs_codec_attribute_dtx_bool";

        /**
         * This is used if further restriction is required on DTX in the
         * receive direction. This attribute is applicable for EVS codec
         * and can be used in both EVS Primary mode and EVS AMR-WB IO mode.
         *
         * <p> If this value is true or not present, then DTX setting is
         * dependent on {@link #KEY_EVS_CODEC_ATTRIBUTE_DTX_BOOL}.
         *
         * <p> If this is not present, then default value of {@code true}
         * will apply.
         *
         * <p>Reference: 3GPP TS 26.445 Section A.3.1.
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_DTX_RECV_BOOL = "imsvoice.evs_codec_attribute_dtx_recv_bool";

        /**
         * Specifies whether to limit the session to header-full format.
         * This applies to both directions in the session. This attribute
         * is applicable for EVS codec.
         *
         * <p>Permissible values are 0, 1
         * If hf-only is 1, only Header-Full format is used and hf-only is
         * included in the SDP.
         * <p>If hf-only is 0, both Compact and Header-Full formats are used
         * and hf-only is included in the SDP.
         * <p>If this key is not present, then both Compact
         * and Header-Full formats are used and hf-only is not included in
         * the SDP.
         * <p> If this key is configured, then device is expected to send
         * this parameter in the SDP offer if operator required it.
         *
         * <p>Reference: 3GPP 26.445 Section A.3.1.
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_HF_ONLY_INT = "imsvoice.evs_codec_attribute_hf_only_int";

        /**
         * Specifies if the EVS mode used is EVS primary mode
         * or EVS AMR-WB IO mode.
         *
         * <p>Possible values are,
         * {@link #EVS_OPERATIONAL_MODE_PRIMARY},
         * {@link #EVS_OPERATIONAL_MODE_AMRWB_IO}
         *
         * <p>If this is not present, then {@link #EVS_OPERATIONAL_MODE_PRIMARY} is used.
         * <p>Reference: 3GPP 26.445 Section 3.1.
         */
        public static final String KEY_EVS_CODEC_ATTRIBUTE_MODE_SWITCH_INT = "imsvoice.evs_codec_attribute_mode_switch_int";

        /**
         * Specifies the codec attributes of different payload types in
         * the EVS codec.
         *
         * <p> The keys in this bundle are payload types specified
         * in {@link #KEY_EVS_PAYLOAD_TYPE_INT_ARRAY}.
         *
         * <p>Codec attributes allowed as part of EVS codec are,
         * <UL>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_BANDWIDTH_INT}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_BITRATE_INT_ARRAY}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_CH_AW_RECV_INT}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_HF_ONLY_INT}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_DTX_BOOL}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_DTX_RECV_BOOL}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_MODE_SWITCH_INT}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_CMR_INT}</LI>
         *     <LI>{@link #KEY_EVS_CODEC_ATTRIBUTE_CHANNELS_INT}</LI>
         *     <LI>{@link #KEY_CODEC_ATTRIBUTE_MODE_CHANGE_PERIOD_INT}</LI>
         *     <LI>{@link #KEY_CODEC_ATTRIBUTE_MODE_CHANGE_CAPABILITY_INT}</LI>
         *     <LI>{@link #KEY_CODEC_ATTRIBUTE_MODE_CHANGE_NEIGHBOR_INT}</LI>
         * </UL>
         */
        public static final String KEY_EVS_PAYLOAD_DESCRIPTION_BUNDLE = "imsvoice.evs_payload_description_bundle";

        /**
         * A list of integers representing the different payload types
         * in EVS codec in priority order from highest to lowest.
         * <p>Payload type is an integer in dynamic payload type range 96-127
         * as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_EVS_PAYLOAD_TYPE_INT_ARRAY = "imsvoice.evs_payload_type_int_array";

        /**
         * Flag specifying whether to send vertical caller id service codes
         * (*67 and *82) in the dialed string in the SIP:INVITE.
         *
         * <p>If {@code true}, vertical caller id service codes *67 and *82
         * will be sent in the dialed string in the SIP:INVITE.
         * If {@code false}, *67 and *82 will be removed.
         */
        public static final String KEY_INCLUDE_CALLER_ID_SERVICE_CODES_IN_SIP_INVITE_BOOL = "imsvoice.include_caller_id_service_codes_in_sip_invite_bool";

        /**
         * Indicates the minimum value for the session interval in seconds.
         * Represented as min-SE header field as per RFC 4028 Section 3.
         *
         * <p>This establishes the lower bound for the session refresh interval.
         */
        public static final String KEY_MINIMUM_SESSION_EXPIRES_TIMER_SEC_INT = "imsvoice.minimum_session_expires_timer_sec_int";

        /**
         * Specifies the timer value for INVITE to the first 1xx response
         * (including 100 trying). If no response is received at timer expiry,
         * call is redialed over CS.
         *
         * <p> Reference: 24.173 Table L.1
         */
        public static final String KEY_MO_CALL_REQUEST_TIMEOUT_MILLIS_INT = "imsvoice.mo_call_request_timeout_millis_int";

        /**
         * Flag indicating whether Multi-end point setting is enabled or not.
         */
        public static final String KEY_MULTIENDPOINT_SUPPORTED_BOOL = "imsvoice.multiendpoint_supported_bool";

        /**
         * Flag specifying whether the 'From' header field is used for determination of
         * the originating party identity in Originating Identification Presentation(OIP)
         * service.
         *
         * <p>If {@code true}: Indicates that the 'From' header field is used for
         * determination of the originating party identity in OIP.
         * {@code false} otherwise.
         */
        public static final String KEY_OIP_SOURCE_FROM_HEADER_BOOL = "imsvoice.oip_source_from_header_bool";

        /**
         * Flag indicating whether PRACK must be enabled for all 18x messages.
         *
         * <p>If {@code false}, only 18x responses with SDP are sent reliably.
         * If {@code true},  SIP 18x responses (other than SIP 183 response)
         * are sent reliably.
         */
        public static final String KEY_PRACK_SUPPORTED_FOR_18X_BOOL = "imsvoice.prack_supported_for_18x_bool";

        /**
         * Prefix of all imsvoice.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imsvoice.";

        /**
         * Specifies the ringback timer for Mobile originated calls.
         *
         * <p>Ringback timer starts when the device receives SIP 180 Ringing
         * in response to its SIP INVITE. If Ringback timer expires,
         * the device sends SIP CANCEL.
         */
        public static final String KEY_RINGBACK_TIMER_MILLIS_INT = "imsvoice.ringback_timer_millis_int";

        /**
         * Specifies the ringing timer for Mobile terminated calls.
         *
         * <p>Ringing timer starts when the device sends SIP 180 Ringing in
         * response to a received SIP INVITE. If Ringing timer expires,
         * the device sends SIP 486 response.
         */
        public static final String KEY_RINGING_TIMER_MILLIS_INT = "imsvoice.ringing_timer_millis_int";

        /**
         * Session-expires header field expressed in seconds as per
         * RFC 4028 Section 3.
         *
         * <p>This establishes the upper bound for the session refresh interval.
         */
        public static final String KEY_SESSION_EXPIRES_TIMER_SEC_INT = "imsvoice.session_expires_timer_sec_int";

        /**
         * Specify the session privacy type.
         *
         * <p>Reference: RFC 3323 Section 4.2, RFC 3325 Section 9.3.
         *
         * <p>Possible values are,
         * {@link #SESSION_PRIVACY_TYPE_HEADER},
         * {@link #SESSION_PRIVACY_TYPE_NONE},
         * {@link #SESSION_PRIVACY_TYPE_ID}
         */
        public static final String KEY_SESSION_PRIVACY_TYPE_INT = "imsvoice.session_privacy_type_int";

        /**
         * Session Refresher entity as per RFC 4028 and IR.92 Section 2.2.8.
         *
         * <p>This determines,
         * a) whether to include the "refresher" parameter
         * b) Type of refresher" parameter
         * in the Session-Expires header field of the SIP INVITE request.
         *
         * <p>Possible values are,
         * {@link #SESSION_REFRESHER_TYPE_UNKNOWN},
         * {@link #SESSION_REFRESHER_TYPE_UAC},
         * {@link #SESSION_REFRESHER_TYPE_UAS}
         */
        public static final String KEY_SESSION_REFRESHER_TYPE_INT = "imsvoice.session_refresher_type_int";

        /**
         * This is used to specify the method used for session refresh.
         *
         * <p>Possible values are,
         * {@link #SESSION_REFRESH_METHOD_INVITE},
         * {@link #SESSION_REFRESH_METHOD_UPDATE_PREFERRED}
         */
        public static final String KEY_SESSION_REFRESH_METHOD_INT = "imsvoice.session_refresh_method_int";

        /**
         * Flag indicating whether Supported header field with the option tag
         *  'timer' is enabled or not.
         *
         * <p>If {@code true}, session timer support is available.{@code false} otherwise.
         *
         * Reference: RFC 4028 Section 3
         */
        public static final String KEY_SESSION_TIMER_SUPPORTED_BOOL = "imsvoice.session_timer_supported_bool";

        /**
         * List of different SRVCC types supported as defined in 3GPP 24.237.
         *
         * <p> Possible values are,
         * {@link #BASIC_SRVCC_SUPPORT},
         * {@link #ALERTING_SRVCC_SUPPORT},
         * {@link #PREALERTING_SRVCC_SUPPORT},
         * {@link #MIDCALL_SRVCC_SUPPORT}
         *
         * <p> Reference: IR.64, 3GPP 24.237, 3GPP 23.216
         */
        public static final String KEY_SRVCC_TYPE_INT_ARRAY = "imsvoice.srvcc_type_int_array";

        /**
         * Flag specifying whether voice is allowed on default bearer.
         *
         * <p>If {@code true}: voice packets can be sent on default bearer. {@code false} otherwise.
         */
        public static final String KEY_VOICE_ON_DEFAULT_BEARER_SUPPORTED_BOOL = "imsvoice.voice_on_default_bearer_supported_bool";

        /**
         * Flag specifying whether QoS preconditions are supported during call setup.
         *
         * <p>If {@code true}: QoS Preconditions are supported during call setup and
         * 'precondition' tag is included in the SIP INVITE header and precondition
         * parameters are sent in SDP as required.
         * <p>If {@code false}: QoS Preconditions are not supported during call setup.
         *
         * <p>Reference: 3GPP TS 24.229
         */
        public static final String KEY_VOICE_QOS_PRECONDITION_SUPPORTED_BOOL = "imsvoice.voice_qos_precondition_supported_bool";

        /**
         * SRVCC access transfer for calls in mid-call phase as per 3GPP 24.237.
         * and IR.64 Section 4.4.
         * <p>This means UE supports the MSC server assisted mid-call feature.
         * Media feature tag used: g.3gpp.mid-call.
         */
        public static final int MIDCALL_SRVCC_SUPPORT = 3;

        /**
         * AMR NB/WB Payload format is octet-aligned.
         */
        public static final int OCTET_ALIGNED = 1;

        /**
         * SRVCC access transfer for calls in pre-alerting phase as per 3GPP 24.237.
         * Media feature tag used: g.3gpp.ps2cs-srvcc-orig-pre-alerting.
         */
        public static final int PREALERTING_SRVCC_SUPPORT = 2;

        /**
         * Session privacy type is HEADER as per RFC 3323 Section 4.2.
         */
        public static final int SESSION_PRIVACY_TYPE_HEADER = 0;

        /**
         * Session privacy type is ID as per RFC 3325 Section 9.3.
         */
        public static final int SESSION_PRIVACY_TYPE_ID = 2;

        /**
         * Session privacy type is NONE as per RFC 3323 Section 4.2.
         */
        public static final int SESSION_PRIVACY_TYPE_NONE = 1;

        /**
         * Session Refresher entity is User Agent Client (UAC).
         *
         * <p>Type of "refresher" parameter in the Session-Expires header field
         * of the SIP INVITE request is UAC.
         */
        public static final int SESSION_REFRESHER_TYPE_UAC = 1;

        /**
         * Session Refresher entity is User Agent Server (UAS).
         *
         * <p>Type of "refresher" parameter in the Session-Expires header field
         * of the SIP INVITE request is UAS.
         */
        public static final int SESSION_REFRESHER_TYPE_UAS = 2;

        /**
         * Session Refresher entity is unknown. This means UE does not include the
         * "refresher" parameter in the Session-Expires header field of
         * the SIP INVITE request.
         */
        public static final int SESSION_REFRESHER_TYPE_UNKNOWN = 0;

        /**
         * SIP INVITE is used for Session Refresh
         */
        public static final int SESSION_REFRESH_METHOD_INVITE = 0;

        /**
         * Both SIP INVITE and UPDATE are used for session refresh.
         *
         * <p>SIP UPDATE will be used if UPDATE is in 'Allow' header.
         * If UPDATE is not in 'Allow' header, then INVITE will be used.
         */
        public static final int SESSION_REFRESH_METHOD_UPDATE_PREFERRED = 1;

        private ImsVoice() {
        }
    }

    /**
     * IMS Video Telephony configs. This groups the configs that are specific for video call.
     */
    public static final class ImsVt {
        /**
         * Specifies the codec attributes of different payload types
         * representing H264 video codec instances.
         *
         * <p> The allowed payload types of the video codecs are specified in,
         * {@link #KEY_H264_PAYLOAD_TYPE_INT_ARRAY}.
         *
         * <p>Codec attributes allowed as part of H264 codec bundle are,
         * <UL>
         *     <LI>{@link #KEY_H264_VIDEO_CODEC_ATTRIBUTE_PROFILE_LEVEL_ID_STRING}</LI>
         *     <LI>{@link #KEY_VIDEO_CODEC_ATTRIBUTE_PACKETIZATION_MODE_INT}</LI>
         *     <LI>{@link #KEY_VIDEO_CODEC_ATTRIBUTE_FRAME_RATE_INT}</LI>
         *     <LI>{@link #KEY_VIDEO_CODEC_ATTRIBUTE_RESOLUTION_INT_ARRAY}</LI>
         * </UL>
         *
         * <p>If this bundle is not configured and
         * {@link #KEY_H264_PAYLOAD_TYPE_INT_ARRAY} is not empty,
         * then default values as in the individual codec attributes to
         * be used for that payload type.
         * <p>If the codec attributes in a particular codec instance bundle
         * is not valid together, then that codec instance should not be used.
         */
        public static final String KEY_H264_PAYLOAD_DESCRIPTION_BUNDLE = "imsvt.h264_payload_description_bundle";

        /**
         * A list of integers representing the different payload types
         * in H264 video codec in priority order from highest to lowest.
         * <p>Payload type is an integer in dynamic payload type range 96-127
         * as per RFC RFC 3551 Section 6.
         */
        public static final String KEY_H264_PAYLOAD_TYPE_INT_ARRAY = "imsvt.h264_payload_type_int_array";

        /**
         * Specifies the profile level id of the H264 video codec.
         * This value is represented as "profile-level-id" in the SDP offer
         * as per RFC 6184 Section 8.1.
         *
         * <p>If this key is not specified or invalid as per the video codec,
         * then default value of 42C00C to be used.
         *
         * <p>Reference: RFC 6184 Section 8.1, ITU-T Recommendation H.264
         */
        public static final String KEY_H264_VIDEO_CODEC_ATTRIBUTE_PROFILE_LEVEL_ID_STRING = "imsvt.h264_video_codec_attribute_profile_level_id_string";

        /**
         * Prefix of all imsvt.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imsvt.";

        /**
         * Specifies the AS (Application Specific) SDP modifier for video media.
         *
         * <p>Expressed in kilobits per second as per RFC 3556 Section 2.
         */
        public static final String KEY_VIDEO_AS_BANDWIDTH_KBPS_INT = "imsvt.video_as_bandwidth_kbps_int";

        /**
         * Specifies the maximum frame rate the offerer wishes to receive.
         * This gives the maximum video frame rate in frames/sec.
         *
         * <p>If this key is not specified or invalid, then the following
         * default value to be used.
         * <UL>
         *   <LI>For H264: 15 </LI>
         * <UL>
         * <p>Reference: RFC 4566 Section 6, 3GPP 26.114 Section 6.2.3.2
         */
        public static final String KEY_VIDEO_CODEC_ATTRIBUTE_FRAME_RATE_INT = "imsvt.video_codec_attribute_frame_rate_int";

        /**
         * Specifies the packetization mode of the video codec.
         *
         * <p>Permissible values are 0 (Single NAL unit mode),
         * 1(Non-interleaved mode).
         *
         * <p>If this key is not specified or invalid, then the following
         * default value to be used.
         * <UL>
         *   <LI>For H264: 1(Non-interleaved mode)</LI>
         * <UL>
         *
         * <p>Reference: RFC 6184 Section 5.4
         */
        public static final String KEY_VIDEO_CODEC_ATTRIBUTE_PACKETIZATION_MODE_INT = "imsvt.video_codec_attribute_packetization_mode_int";

        /**
         * Specifies the maximum resolution allowed for the video codec
         * instance.
         *
         * <p>This is specified as an array of two integers, with
         * index 0 : Width,
         * index 1 : Height
         *
         * <p>If this key is not specified or invalid as per the video codec,
         * then the following default value to be used.
         * <UL>
         *   <LI>For H264: 240 (WIDTH) x 320 (HEIGHT) </LI>
         * <UL>
         * <p>Reference: RFC 4566 Section 6, 3GPP 26.114 Section 6.2.3.2
         */
        public static final String KEY_VIDEO_CODEC_ATTRIBUTE_RESOLUTION_INT_ARRAY = "imsvt.video_codec_attribute_resolution_int_array";

        /**
         * Specifies the Video Codec capability. This contains a list of
         * payload types representing different Video codec instances.
         *
         * <p>Possible key(s) in this bundle are,
         * <UL>
         *     <LI>{@link #KEY_H264_PAYLOAD_TYPE_INT_ARRAY}</LI>
         * </UL>
         * <p>To specify payload descriptions for each of the payload types, see
         * <UL>
         *     <LI>{@link #KEY_H264_PAYLOAD_DESCRIPTION_BUNDLE}</LI>
         * </UL>
         */
        public static final String KEY_VIDEO_CODEC_CAPABILITY_PAYLOAD_TYPES_BUNDLE = "imsvt.video_codec_capability_payload_types_bundle";

        /**
         * Flag specifying whether video media is allowed on default bearer.
         *
         * <p>If {@code true}: video media can be sent on default bearer.
         * {@code false} otherwise.
         */
        public static final String KEY_VIDEO_ON_DEFAULT_BEARER_SUPPORTED_BOOL = "imsvt.video_on_default_bearer_supported_bool";

        /**
         * Flag specifying whether QoS preconditions are supported for Video.
         *
         * <p>If {@code true}: QoS Preconditions are supported.
         * {@code false} otherwise.
         * <p>Reference: 3GPP TS 24.229
         */
        public static final String KEY_VIDEO_QOS_PRECONDITION_SUPPORTED_BOOL = "imsvt.video_qos_precondition_supported_bool";

        /**
         * Specifies the RR (RTCP bandwidth-Receiver) SDP modifier
         * for video media.
         *
         * <p>This indicates the RTCP bandwidth allocated to receivers
         * for video media.
         *
         * <p>Expressed in bits per second as per RFC 3556 Section 2.
         */
        public static final String KEY_VIDEO_RR_BANDWIDTH_BPS_INT = "imsvt.video_rr_bandwidth_bps_int";

        /**
         * Specifies the RS (RTCP bandwidth-Sender) SDP modifier for video media.
         *
         * <p>This indicates the RTCP bandwidth allocated to active data senders
         * for video media.
         *
         * <p>Expressed in bits per second as per RFC 3556 Section 2.
         */
        public static final String KEY_VIDEO_RS_BANDWIDTH_BPS_INT = "imsvt.video_rs_bandwidth_bps_int";

        /**
         * Specifies the timeout value for no video RTCP packets received.
         * <p>On timer expiry, VT call can downgrade to voice call or end
         * or continue depending on the operator requirement.
         */
        public static final String KEY_VIDEO_RTCP_INACTIVITY_TIMER_MILLIS_INT = "imsvt.video_rtcp_inactivity_timer_millis_int";

        /**
         * Specifies the differentiated services code point (DSCP) value
         * for Video RTP.
         *
         * <p>Reference: RFC 4594 Section 1.4.4
         */
        public static final String KEY_VIDEO_RTP_DSCP_INT = "imsvt.video_rtp_dscp_int";

        /**
         * Specifies the timeout value for no video RTP packets received.
         * <p>On timer expiry, VT call can downgrade to voice call or end
         * or continue depending on the operator requirement.
         */
        public static final String KEY_VIDEO_RTP_INACTIVITY_TIMER_MILLIS_INT = "imsvt.video_rtp_inactivity_timer_millis_int";

        private ImsVt() {
        }
    }

    /**
     * WiFi Calling. This groups the configs specific for Voice over WiFi/WFC call.
     */
    public static final class ImsWfc {
        /**
         * Flag specifying whether emergency call over VoWiFi is requested over
         * emergency PDN or IMS PDN.
         *
         * <p>If {@code false}: E911 call uses IMS PDN for E911 call over VoWiFi.
         * If {@code true}: E911 call uses Emergency PDN for E911 call over VoWiFi.
         */
        public static final String KEY_EMERGENCY_CALL_OVER_EMERGENCY_PDN_BOOL = "imswfc.emergency_call_over_emergency_pdn_bool";

        /**
         *  List of MDNs for which Geo-location PIDF XML with country info
         *  needs to included for normal calls involving short code.
         */
        public static final String KEY_PIDF_SHORT_CODE_STRING_ARRAY = "imswfc.pidf_short_code_string_array";

        /**
         * Prefix of all imswfc.KEY_* constants.
         */
        public static final String KEY_PREFIX = "imswfc.";

        private ImsWfc() {
        }
    }

    /**
     * Configs used for epdg tunnel bring up.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7296">RFC 7296, Internet Key Exchange Protocol
     *     Version 2 (IKEv2)</a>
     */
    public static final class Iwlan {
        /**
         * Server is authenticated using its certificate.
         */
        public static final int AUTHENTICATION_METHOD_CERT = 1;

        /**
         * Certificate sent from the server is ignored. Only Extensible Authentication Protocol
         * (EAP) is used to authenticate the server. EAP_ONLY_AUTH payload is added to IKE_AUTH
         * request if supported.
         *
         * @see <a href="https://tools.ietf.org/html/rfc5998">RFC 5998</a>
         */
        public static final int AUTHENTICATION_METHOD_EAP_ONLY = 0;

        /**
         * Use cellular location to chose epdg server
         */
        public static final int EPDG_ADDRESS_CELLULAR_LOC = 3;

        /**
         * Use IPv4 ePDG addresses only.
         */
        public static final int EPDG_ADDRESS_IPV4_ONLY = 2;

        /**
         * Prioritize IPv4 ePDG addresses.
         */
        public static final int EPDG_ADDRESS_IPV4_PREFERRED = 0;

        /**
         * Prioritize IPv6 ePDG addresses
         */
        public static final int EPDG_ADDRESS_IPV6_PREFERRED = 1;

        /**
         * Use the epdg address received in protocol configuration options (PCO) from the network.
         */
        public static final int EPDG_ADDRESS_PCO = 2;

        /**
         * Construct the epdg address using plmn.
         */
        public static final int EPDG_ADDRESS_PLMN = 1;

        /**
         * Use static epdg address.
         */
        public static final int EPDG_ADDRESS_STATIC = 0;

        /**
         * Use Visited Country FQDN rule
         */
        public static final int EPDG_ADDRESS_VISITED_COUNTRY = 4;

        /**
         * Ike Identification Fully Qualified Domain Name
         *
         * @see <a href="https://tools.ietf.org/html/rfc7296#section-3.5">RFC 7296, Internet Key
         *     Exchange Protocol Version 2 (IKEv2)</a>
         */
        public static final int ID_TYPE_FQDN = 2;

        /**
         * Ike Identification opaque octet stream for vendor specific information
         *
         * @see <a href="https://tools.ietf.org/html/rfc7296#section-3.5">RFC 7296, Internet Key
         *     Exchange Protocol Version 2 (IKEv2)</a>
         */
        public static final int ID_TYPE_KEY_ID = 11;

        /**
         * Ike Identification Fully Qualified RFC 822 email address.
         *
         * @see <a href="https://tools.ietf.org/html/rfc7296#section-3.5">RFC 7296, Internet Key
         *     Exchange Protocol Version 2 (IKEv2)</a>
         */
        public static final int ID_TYPE_RFC822_ADDR = 3;

        /**
         * Controls if KE payload should be added during child session local rekey procedure.
         */
        public static final String KEY_ADD_KE_TO_CHILD_SESSION_REKEY_BOOL = "iwlan.add_ke_to_child_session_rekey_bool";

        /**
         * Time in seconds after which the child security association session is terminated if rekey
         * procedure is not successful. If not set or set to <= 0, the default value is 3600
         * seconds.
         */
        public static final String KEY_CHILD_SA_REKEY_HARD_TIMER_SEC_INT = "iwlan.child_sa_rekey_hard_timer_sec_int";

        /**
         * Time in seconds after which the child session rekey procedure is started. If not set or
         * set to <= 0, default value is 3000 seconds.
         */
        public static final String KEY_CHILD_SA_REKEY_SOFT_TIMER_SEC_INT = "iwlan.child_sa_rekey_soft_timer_sec_int";

        /**
         * List of supported key sizes for AES Cipher Block Chaining (CBC) encryption mode of child
         * session. Possible values are:
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_UNUSED},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_128},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_192},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_256}
         */
        public static final String KEY_CHILD_SESSION_AES_CBC_KEY_SIZE_INT_ARRAY = "iwlan.child_session_aes_cbc_key_size_int_array";

        /**
         * List of supported key sizes for AES Counter (CTR) encryption mode of child session.
         * Possible values are:
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_UNUSED},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_128},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_192},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_256}
         */
        public static final String KEY_CHILD_SESSION_AES_CTR_KEY_SIZE_INT_ARRAY = "iwlan.child_session_aes_ctr_key_size_int_array";

        /**
         * List of supported key sizes for AES Galois/Counter Mode (GCM) encryption mode
         * of child session.
         * Possible values are:
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_UNUSED},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_128},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_192},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_256}
         */
        public static final String KEY_CHILD_SESSION_AES_GCM_KEY_SIZE_INT_ARRAY = "iwlan.child_session_aes_gcm_key_size_int_array";

        /**
         * Supported DH groups for IKE negotiation. Possible values are:
         * {@link android.net.ipsec.ike.SaProposal#DH_GROUP_NONE},
         * {@link android.net.ipsec.ike.SaProposal#DH_GROUP_1024_BIT_MODP},
         * {@link android.net.ipsec.ike.SaProposal#DH_GROUP_1536_BIT_MODP},
         * {@link android.net.ipsec.ike.SaProposal#DH_GROUP_2048_BIT_MODP},
         * {@link android.net.ipsec.ike.SaProposal#DH_GROUP_3072_BIT_MODP},
         * {@link android.net.ipsec.ike.SaProposal#DH_GROUP_4096_BIT_MODP}
         */
        public static final String KEY_DIFFIE_HELLMAN_GROUPS_INT_ARRAY = "iwlan.diffie_hellman_groups_int_array";

        /**
         * Time in seconds after which a dead peer detection (DPD) request is sent. If not set or
         * set to <= 0, default value is 120 seconds.
         */
        public static final String KEY_DPD_TIMER_SEC_INT = "iwlan.dpd_timer_sec_int";

        /**
         * Type of IP preference used to prioritize ePDG servers. Possible values are
         * {@link #EPDG_ADDRESS_IPV4_PREFERRED}, {@link #EPDG_ADDRESS_IPV6_PREFERRED},
         * {@link #EPDG_ADDRESS_IPV4_ONLY}
         */
        public static final String KEY_EPDG_ADDRESS_IP_TYPE_PREFERENCE_INT = "iwlan.epdg_address_ip_type_preference_int";

        /**
         * A priority list of ePDG addresses to be used. Possible values are {@link
         * #EPDG_ADDRESS_STATIC}, {@link #EPDG_ADDRESS_PLMN}, {@link #EPDG_ADDRESS_PCO}, {@link
         * #EPDG_ADDRESS_CELLULAR_LOC}, {@link #EPDG_ADDRESS_VISITED_COUNTRY}
         */
        public static final String KEY_EPDG_ADDRESS_PRIORITY_INT_ARRAY = "iwlan.epdg_address_priority_int_array";

        /**
         * Method used to authenticate epdg server. Possible values are {@link
         * #AUTHENTICATION_METHOD_EAP_ONLY}, {@link #AUTHENTICATION_METHOD_CERT}
         */
        public static final String KEY_EPDG_AUTHENTICATION_METHOD_INT = "iwlan.epdg_authentication_method_int";

        /**
         * Specifies the PCO id for IPv4 Epdg server address
         */
        public static final String KEY_EPDG_PCO_ID_IPV4_INT = "iwlan.epdg_pco_id_ipv4_int";

        /**
         * Specifies the PCO id for IPv6 Epdg server address
         */
        public static final String KEY_EPDG_PCO_ID_IPV6_INT = "iwlan.epdg_pco_id_ipv6_int";

        /**
         * Epdg static IP address or FQDN for roaming
         */
        public static final String KEY_EPDG_STATIC_ADDRESS_ROAMING_STRING = "iwlan.epdg_static_address_roaming_string";

        /**
         * Epdg static IP address or FQDN
         */
        public static final String KEY_EPDG_STATIC_ADDRESS_STRING = "iwlan.epdg_static_address_string";

        /**
         * Specifies the local identity type for IKE negotiations. Possible values are {@link
         * #ID_TYPE_FQDN}, {@link #ID_TYPE_RFC822_ADDR}, {@link #ID_TYPE_KEY_ID}
         */
        public static final String KEY_IKE_LOCAL_ID_TYPE_INT = "iwlan.ike_local_id_type_int";

        /**
         * Time in seconds after which the IKE session is terminated if rekey procedure is not
         * successful. If not set or set to <= 0, default value is 3600 seconds.
         */
        public static final String KEY_IKE_REKEY_HARD_TIMER_SEC_INT = "iwlan.ike_rekey_hard_timer_in_sec";

        /**
         * Time in seconds after which the IKE session rekey procedure is started. If not set or set
         * to <= 0, default value is 3000 seconds.
         */
        public static final String KEY_IKE_REKEY_SOFT_TIMER_SEC_INT = "iwlan.ike_rekey_soft_timer_sec_int";

        /**
         * Specifies the remote identity type for IKE negotiations. Possible values are {@link
         * #ID_TYPE_FQDN}, {@link #ID_TYPE_RFC822_ADDR}, {@link #ID_TYPE_KEY_ID}
         */
        public static final String KEY_IKE_REMOTE_ID_TYPE_INT = "iwlan.ike_remote_id_type_int";

        /**
         * List of supported key sizes for AES Cipher Block Chaining (CBC) encryption mode of IKE
         * session. Possible values:
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_UNUSED},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_128},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_192},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_256}
         */
        public static final String KEY_IKE_SESSION_AES_CBC_KEY_SIZE_INT_ARRAY = "iwlan.ike_session_encryption_aes_cbc_key_size_int_array";

        /**
         * List of supported key sizes for AES Counter (CTR) encryption mode of IKE session.
         * Possible values -
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_UNUSED},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_128},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_192},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_256}
         */
        public static final String KEY_IKE_SESSION_AES_CTR_KEY_SIZE_INT_ARRAY = "iwlan.ike_session_encryption_aes_ctr_key_size_int_array";

        /**
         * List of supported key sizes for AES Galois/Counter Mode (GCM) encryption mode
         * of IKE session.
         * Possible values -
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_UNUSED},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_128},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_192},
         * {@link android.net.ipsec.ike.SaProposal#KEY_LEN_AES_256}
         */
        public static final String KEY_IKE_SESSION_AES_GCM_KEY_SIZE_INT_ARRAY = "iwlan.ike_session_encryption_aes_gcm_key_size_int_array";

        /**
         * Maximum number of retries for tunnel establishment.
         */
        public static final String KEY_MAX_RETRIES_INT = "iwlan.max_retries_int";

        /**
         * List of '-' separated MCC/MNCs used to create ePDG FQDN as per 3GPP TS 23.003
         */
        public static final String KEY_MCC_MNCS_STRING_ARRAY = "iwlan.mcc_mncs_string_array";

        /**
         * Time in seconds after which a NATT keep alive message is sent. If not set or set to <= 0,
         * default value is 20 seconds.
         */
        public static final String KEY_NATT_KEEP_ALIVE_TIMER_SEC_INT = "iwlan.natt_keep_alive_timer_sec_int";

        /**
         * Prefix of all Epdg.KEY_* constants.
         */
        public static final String KEY_PREFIX = "iwlan.";

        /**
         * List of IKE message retransmission timeouts in milliseconds, where each timeout
         * is the waiting time before next retry, except the last timeout which is the waiting time
         * before terminating the IKE Session. Min list length = 1, Max
         * list length = 10 Min timeout = 500 ms, Max timeout = 1800000 ms
         */
        public static final String KEY_RETRANSMIT_TIMER_MSEC_INT_ARRAY = "iwlan.retransmit_timer_sec_int_array";

        /**
         * List of supported AEAD algorithms for child session. Possible values are
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_GCM_8},
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_GCM_12},
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_GCM_16}
         */
        public static final String KEY_SUPPORTED_CHILD_SESSION_AEAD_ALGORITHMS_INT_ARRAY = "iwlan.supported_child_session_aead_algorithms_int_array";

        /**
         * List of supported encryption algorithms for child session. Possible values are
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_CBC},
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_CTR}
         */
        public static final String KEY_SUPPORTED_CHILD_SESSION_ENCRYPTION_ALGORITHMS_INT_ARRAY = "iwlan.supported_child_session_encryption_algorithms_int_array";

        /**
         * List of supported AEAD algorithms for IKE session. Possible values are
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_GCM_8},
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_GCM_12},
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_GCM_16}
         */
        public static final String KEY_SUPPORTED_IKE_SESSION_AEAD_ALGORITHMS_INT_ARRAY = "iwlan.supported_ike_session_aead_algorithms_int_array";

        /**
         * List of supported encryption algorithms for IKE session. Possible values are
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_CBC},
         * {@link android.net.ipsec.ike.SaProposal#ENCRYPTION_ALGORITHM_AES_CTR}
         */
        public static final String KEY_SUPPORTED_IKE_SESSION_ENCRYPTION_ALGORITHMS_INT_ARRAY = "iwlan.supported_ike_session_encryption_algorithms_int_array";

        /**
         * List of supported integrity algorithms for IKE session. Possible values are
         * {@link android.net.ipsec.ike.SaProposal#INTEGRITY_ALGORITHM_NONE},
         * {@link android.net.ipsec.ike.SaProposal#INTEGRITY_ALGORITHM_HMAC_SHA1_96},
         * {@link android.net.ipsec.ike.SaProposal#INTEGRITY_ALGORITHM_AES_XCBC_96},
         * {@link android.net.ipsec.ike.SaProposal#INTEGRITY_ALGORITHM_HMAC_SHA2_256_128},
         * {@link android.net.ipsec.ike.SaProposal#INTEGRITY_ALGORITHM_HMAC_SHA2_384_192},
         * {@link android.net.ipsec.ike.SaProposal#INTEGRITY_ALGORITHM_HMAC_SHA2_512_256}
         */
        public static final String KEY_SUPPORTED_INTEGRITY_ALGORITHMS_INT_ARRAY = "iwlan.supported_integrity_algorithms_int_array";

        /**
         * List of supported pseudo random function algorithms for IKE session. Possible values are
         * {@link android.net.ipsec.ike.SaProposal#PSEUDORANDOM_FUNCTION_HMAC_SHA1},
         * {@link android.net.ipsec.ike.SaProposal#PSEUDORANDOM_FUNCTION_AES128_XCBC},
         * {@link android.net.ipsec.ike.SaProposal#PSEUDORANDOM_FUNCTION_SHA2_256},
         * {@link android.net.ipsec.ike.SaProposal#PSEUDORANDOM_FUNCTION_SHA2_384},
         * {@link android.net.ipsec.ike.SaProposal#PSEUDORANDOM_FUNCTION_SHA2_512}
         */
        public static final String KEY_SUPPORTED_PRF_ALGORITHMS_INT_ARRAY = "iwlan.supported_prf_algorithms_int_array";

        /**
         * Enables the use of multiple Child SA proposals, encompassing both carrier-preferred
         * ciphers and all supported ciphers from 3GPP TS 33.210 and RFC 8221,
         * as defined in RFC 7296.
         */
        public static final String KEY_SUPPORTS_CHILD_SESSION_MULTIPLE_SA_PROPOSALS_BOOL = "iwlan.supports_child_session_multiple_sa_proposals_bool";

        /**
         * Controls if the IKE tunnel setup supports EAP-AKA fast reauth
         */
        public static final String KEY_SUPPORTS_EAP_AKA_FAST_REAUTH_BOOL = "iwlan.supports_eap_aka_fast_reauth_bool";

        /**
         * Enables the use of multiple IKE SA proposals, encompassing both carrier-preferred
         * ciphers and all supported ciphers from 3GPP TS 33.210 and RFC 8221,
         * as defined in RFC 7296.
         */
        public static final String KEY_SUPPORTS_IKE_SESSION_MULTIPLE_SA_PROPOSALS_BOOL = "iwlan.supports_ike_session_multiple_sa_proposals_bool";

        private Iwlan() {
        }
    }
}
