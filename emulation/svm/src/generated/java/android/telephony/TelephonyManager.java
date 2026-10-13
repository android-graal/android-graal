package android.telephony;

/**
 * Provides access to information about the telephony services on
 * the device. Applications can use the methods in this class to
 * determine telephony services and states, as well as to access some
 * types of subscriber information. Applications can also register
 * a listener to receive notification of telephony state changes.
 * <p>
 * The returned TelephonyManager will use the default subscription for all calls.
 * To call an API for a specific subscription, use {@link #createForSubscriptionId(int)}. e.g.
 * <code>
 *   telephonyManager = defaultSubTelephonyManager.createForSubscriptionId(subId);
 * </code>
 * <p>
 * Note that access to some telephony information is
 * permission-protected. Your application cannot access the protected
 * information unless it has the appropriate permissions declared in
 * its manifest file. Where permissions apply, they are noted in the
 * methods through which you access the protected information.
 *
 * <p>TelephonyManager is intended for use on devices that implement
 * {@link android.content.pm.PackageManager#FEATURE_TELEPHONY FEATURE_TELEPHONY}. On devices
 * that do not implement this feature, the behavior is not reliable.
 */
public final class TelephonyManager {
    /**
     * A service action that identifies
     * a {@link android.service.carrier.CarrierMessagingClientService} subclass in the
     * AndroidManifest.xml.
     *
     * <p>See {@link android.service.carrier.CarrierMessagingClientService} for the details.
     */
    public static final String ACTION_CARRIER_MESSAGING_CLIENT_SERVICE = "android.telephony.action.CARRIER_MESSAGING_CLIENT_SERVICE";

    /**
     * Broadcast action sent when the availability of the system default network changes.
     *
     * @see ConnectivityManager#registerDefaultNetworkCallback(ConnectivityManager.NetworkCallback)
     *
     * This action is intended for carrier apps to set/reset carrier actions. It is only sent to the
     * carrier apps specified in the carrier config for the subscription ID attached to this intent.
     *
     * The intent will have the following extra values:</p>
     * <ul>
     *   <li>{@link #EXTRA_DEFAULT_NETWORK_AVAILABLE}</li>
     *   <dd>{@code true} if the default network is now available, {@code false} otherwise.</dd>
     *   <li>{@link SubscriptionManager#EXTRA_SUBSCRIPTION_INDEX}</li>
     *          <dd>The subscription ID on which the default network availability changed.</dd>
     * </ul>
     * <p class="note">This is a protected intent that can only be sent by the system. </p>
     */
    public static final String ACTION_CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE = "android.telephony.action.CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE";

    /**
     * Broadcast action sent when a PCO value becomes available from the modem.
     *
     * This action is intended for sim/account status checks and only sent to the carrier apps
     * specified in the carrier config for the subscription ID that's attached to this intent.
     *
     * The intent will have the following extra values:</p>
     * <ul>
     *   <li>{@link #EXTRA_APN_TYPE}</li><dd>An integer indicating the apn type.</dd>
     *   <li>{@link #EXTRA_APN_PROTOCOL}</li><dd>An integer indicating the protocol of the apn
     *      connection</dd>
     *   <li>{@link #EXTRA_PCO_ID}</li><dd>An integer indicating the PCO id for the data.</dd>
     *   <li>{@link #EXTRA_PCO_VALUE}</li><dd>A byte array of PCO data read from modem.</dd>
     *   <li>{@link SubscriptionManager#EXTRA_SUBSCRIPTION_INDEX}</li>
     *          <dd>The subscription ID for which the PCO info was received.</dd>
     * </ul>
     * <p class="note">This is a protected intent that can only be sent by the system. </p>
     */
    public static final String ACTION_CARRIER_SIGNAL_PCO_VALUE = "android.telephony.action.CARRIER_SIGNAL_PCO_VALUE";

    /**
     * Broadcast action sent when a data connection is redirected with validation failure.
     *
     * This action is intended for sim/account status checks and only sent to the carrier apps
     * specified in the carrier config for the subscription ID that's attached to this intent.
     *
     * The intent will have the following extra values:
     * <ul>
     *   <li>{@link #EXTRA_APN_TYPE}</li><dd>An integer indicating the apn type.</dd>
     *   <li>{@link #EXTRA_REDIRECTION_URL}</li><dd>A string indicating the redirection url</dd>
     *   <li>{@link SubscriptionManager#EXTRA_SUBSCRIPTION_INDEX}</li>
     *          <dd>The subscription ID on which the validation failure happened.</dd>
     * </ul>
     * <p class="note">This is a protected intent that can only be sent by the system.</p>
     */
    public static final String ACTION_CARRIER_SIGNAL_REDIRECTED = "android.telephony.action.CARRIER_SIGNAL_REDIRECTED";

    /**
     * Broadcast action sent when a data connection setup fails.
     *
     * This action is intended for sim/account status checks and only sent to the carrier apps
     * specified in the carrier config for the subscription ID that's attached to this intent.
     *
     * The intent will have the following extra values:
     * <ul>
     *   <li>{@link #EXTRA_APN_TYPE}</li><dd>An integer indicating the apn type.</dd>
     *   <li>{@link #EXTRA_DATA_FAIL_CAUSE}</li><dd>A integer indicating the data fail cause.</dd>
     *   <li>{@link SubscriptionManager#EXTRA_SUBSCRIPTION_INDEX}</li>
     *          <dd>The subscription ID on which the data setup failure happened.</dd>
     * </ul>
     * <p class="note">This is a protected intent that can only be sent by the system. </p>
     */
    public static final String ACTION_CARRIER_SIGNAL_REQUEST_NETWORK_FAILED = "android.telephony.action.CARRIER_SIGNAL_REQUEST_NETWORK_FAILED";

    /**
     * Broadcast action sent when carrier apps should reset their internal state.
     *
     * Sent when certain events such as turning on/off mobile data, removing the SIM, etc. require
     * carrier apps to reset their state.
     *
     * This action is intended to signal carrier apps to perform cleanup operations. It is only sent
     * to the carrier apps specified in the carrier config for the subscription ID attached to
     * this intent.
     *
     * The intent will have the following extra values:</p>
     * <ul>
     *   <li>{@link SubscriptionManager#EXTRA_SUBSCRIPTION_INDEX}</li>
     *          <dd>The subscription ID for which state should be reset.</dd>
     * </ul>
     * <p class="note">This is a protected intent that can only be sent by the system.</p>
     */
    public static final String ACTION_CARRIER_SIGNAL_RESET = "android.telephony.action.CARRIER_SIGNAL_RESET";

    /**
     * Open the voicemail settings activity to make changes to voicemail configuration.
     *
     * <p>
     * The {@link #EXTRA_PHONE_ACCOUNT_HANDLE} extra indicates which {@link PhoneAccountHandle} to
     * configure voicemail.
     * The {@link #EXTRA_HIDE_PUBLIC_SETTINGS} hides settings the dialer will modify through public
     * API if set.
     *
     * @see #EXTRA_PHONE_ACCOUNT_HANDLE
     * @see #EXTRA_HIDE_PUBLIC_SETTINGS
     */
    public static final String ACTION_CONFIGURE_VOICEMAIL = "android.telephony.action.CONFIGURE_VOICEMAIL";

    /**
     * Broadcast action to be received by Broadcast receivers.
     *
     * Indicates multi-SIM configuration is changed. For example, it changed
     * from single SIM capable to dual-SIM capable (DSDS or DSDA) or triple-SIM mode.
     *
     * It doesn't indicate how many subscriptions are actually active, or which states SIMs are,
     * or that all steps during multi-SIM change are done. To know those information you still need
     * to listen to SIM_STATE changes or active subscription changes.
     *
     * See extra of {@link #EXTRA_ACTIVE_SIM_SUPPORTED_COUNT} for updated value.
     */
    public static final String ACTION_MULTI_SIM_CONFIG_CHANGED = "android.telephony.action.MULTI_SIM_CONFIG_CHANGED";

    /**
     * Broadcast intent action for network country code changes.
     *
     * <p>
     * The {@link #EXTRA_NETWORK_COUNTRY} extra indicates the country code of the current
     * network returned by {@link #getNetworkCountryIso()}.
     *
     * <p>There may be a delay of several minutes before reporting that no country is detected.
     *
     * @see #EXTRA_NETWORK_COUNTRY
     * @see #getNetworkCountryIso()
     */
    public static final String ACTION_NETWORK_COUNTRY_CHANGED = "android.telephony.action.NETWORK_COUNTRY_CHANGED";

    /**
     * Broadcast intent action indicating that the call state
     * on the device has changed.
     *
     * <p>
     * The {@link #EXTRA_STATE} extra indicates the new call state.
     * If a receiving app has {@link android.Manifest.permission#READ_CALL_LOG} permission, a second
     * extra {@link #EXTRA_INCOMING_NUMBER} provides the phone number for incoming and outgoing
     * calls as a String.
     * <p>
     * If the receiving app has
     * {@link android.Manifest.permission#READ_CALL_LOG} and
     * {@link android.Manifest.permission#READ_PHONE_STATE} permission, it will receive the
     * broadcast twice; one with the {@link #EXTRA_INCOMING_NUMBER} populated with the phone number,
     * and another with it blank.  Due to the nature of broadcasts, you cannot assume the order
     * in which these broadcasts will arrive, however you are guaranteed to receive two in this
     * case.  Apps which are interested in the {@link #EXTRA_INCOMING_NUMBER} can ignore the
     * broadcasts where {@link #EXTRA_INCOMING_NUMBER} is not present in the extras (e.g. where
     * {@link Intent#hasExtra(String)} returns {@code false}).
     * <p class="note">
     * This was a {@link android.content.Context#sendStickyBroadcast sticky}
     * broadcast in version 1.0, but it is no longer sticky.
     * Instead, use {@link #getCallState} to synchronously query the current call state.
     *
     * @see #EXTRA_STATE
     * @see #EXTRA_INCOMING_NUMBER
     * @see #getCallState
     */
    public static final String ACTION_PHONE_STATE_CHANGED = "android.intent.action.PHONE_STATE";

    /**
     * Activity action: Show setting to reset mobile networks.
     *
     * <p>On devices with a settings activity to reset mobile networks, the activity should be
     * launched without additional permissions.
     *
     * <p>On some devices, this settings activity may not exist. Callers should ensure that this
     * case is appropriately handled.
     */
    public static final String ACTION_RESET_MOBILE_NETWORK_SETTINGS = "android.telephony.action.RESET_MOBILE_NETWORK_SETTINGS";

    /**
     * The Phone app sends this intent when a user opts to respond-via-message during an incoming
     * call. By default, the device's default SMS app consumes this message and sends a text message
     * to the caller. A third party app can also provide this functionality by consuming this Intent
     * with a {@link android.app.Service} and sending the message using its own messaging system.
     * <p>The intent contains a URI (available from {@link android.content.Intent#getData})
     * describing the recipient, using either the {@code sms:}, {@code smsto:}, {@code mms:},
     * or {@code mmsto:} URI schema. Each of these URI schema carry the recipient information the
     * same way: the path part of the URI contains the recipient's phone number or a comma-separated
     * set of phone numbers if there are multiple recipients. For example, {@code
     * smsto:2065551234}.</p>
     *
     * <p>The intent may also contain extras for the message text (in {@link
     * android.content.Intent#EXTRA_TEXT}) and a message subject
     * (in {@link android.content.Intent#EXTRA_SUBJECT}).</p>
     *
     * <p class="note"><strong>Note:</strong>
     * The intent-filter that consumes this Intent needs to be in a {@link android.app.Service}
     * that requires the
     * permission {@link android.Manifest.permission#SEND_RESPOND_VIA_MESSAGE}.</p>
     * <p>For example, the service that receives this intent can be declared in the manifest file
     * with an intent filter like this:</p>
     * <pre>
     * &lt;!-- Service that delivers SMS messages received from the phone "quick response" -->
     * &lt;service android:name=".HeadlessSmsSendService"
     *          android:permission="android.permission.SEND_RESPOND_VIA_MESSAGE"
     *          android:exported="true" >
     *   &lt;intent-filter>
     *     &lt;action android:name="android.intent.action.RESPOND_VIA_MESSAGE" />
     *     &lt;category android:name="android.intent.category.DEFAULT" />
     *     &lt;data android:scheme="sms" />
     *     &lt;data android:scheme="smsto" />
     *     &lt;data android:scheme="mms" />
     *     &lt;data android:scheme="mmsto" />
     *   &lt;/intent-filter>
     * &lt;/service></pre>
     * <p>
     * Output: nothing.
     */
    public static final String ACTION_RESPOND_VIA_MESSAGE = "android.intent.action.RESPOND_VIA_MESSAGE";

    /**
     * Broadcast Action: A debug code has been entered in the dialer.
     * <p>
     * This intent is broadcast by the system and OEM telephony apps may need to receive these
     * broadcasts. And it requires the sender to be default dialer or has carrier privileges
     * (see {@link #hasCarrierPrivileges}).
     * <p>
     * These "secret codes" are used to activate developer menus by dialing certain codes.
     * And they are of the form {@code *#*#<code>#*#*}. The intent will have the data
     * URI: {@code android_secret_code://<code>}. It is possible that a manifest
     * receiver would be woken up even if it is not currently running.
     * <p>
     * It is supposed to replace {@link android.provider.Telephony.Sms.Intents#SECRET_CODE_ACTION}
     * in the next Android version.
     * Before that both of these two actions will be broadcast.
     */
    public static final String ACTION_SECRET_CODE = "android.telephony.action.SECRET_CODE";

    /**
     * Broadcast intent action for letting the default dialer to know to show voicemail
     * notification.
     *
     * <p>
     * The {@link #EXTRA_PHONE_ACCOUNT_HANDLE} extra indicates which {@link PhoneAccountHandle} the
     * voicemail is received on.
     * The {@link #EXTRA_NOTIFICATION_COUNT} extra indicates the total numbers of unheard
     * voicemails.
     * The {@link #EXTRA_VOICEMAIL_NUMBER} extra indicates the voicemail number if available.
     * The {@link #EXTRA_CALL_VOICEMAIL_INTENT} extra is a {@link android.app.PendingIntent} that
     * will call the voicemail number when sent. This extra will be empty if the voicemail number
     * is not set, and {@link #EXTRA_LAUNCH_VOICEMAIL_SETTINGS_INTENT} will be set instead.
     * The {@link #EXTRA_LAUNCH_VOICEMAIL_SETTINGS_INTENT} extra is a
     * {@link android.app.PendingIntent} that will launch the voicemail settings. This extra is only
     * available when the voicemail number is not set.
     * The {@link #EXTRA_IS_REFRESH} extra indicates whether the notification is a refresh or a new
     * notification.
     *
     * @see #EXTRA_PHONE_ACCOUNT_HANDLE
     * @see #EXTRA_NOTIFICATION_COUNT
     * @see #EXTRA_VOICEMAIL_NUMBER
     * @see #EXTRA_CALL_VOICEMAIL_INTENT
     * @see #EXTRA_LAUNCH_VOICEMAIL_SETTINGS_INTENT
     * @see #EXTRA_IS_REFRESH
     */
    public static final String ACTION_SHOW_VOICEMAIL_NOTIFICATION = "android.telephony.action.SHOW_VOICEMAIL_NOTIFICATION";

    /**
     * Broadcast Action: The subscription carrier identity has changed.
     * This intent could be sent on the following events:
     * <ul>
     *   <li>Subscription absent. Carrier identity could change from a valid id to
     *   {@link TelephonyManager#UNKNOWN_CARRIER_ID}.</li>
     *   <li>Subscription loaded. Carrier identity could change from
     *   {@link TelephonyManager#UNKNOWN_CARRIER_ID} to a valid id.</li>
     *   <li>The subscription carrier is recognized after a remote update.</li>
     * </ul>
     * The intent will have the following extra values:
     * <ul>
     *   <li>{@link #EXTRA_CARRIER_ID} The up-to-date carrier id of the current subscription id.
     *   </li>
     *   <li>{@link #EXTRA_CARRIER_NAME} The up-to-date carrier name of the current subscription.
     *   </li>
     *   <li>{@link #EXTRA_SUBSCRIPTION_ID} The subscription id associated with the changed carrier
     *   identity.
     *   </li>
     * </ul>
     * <p class="note">This is a protected intent that can only be sent by the system.
     */
    public static final String ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED = "android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED";

    /**
     * Broadcast Action: The subscription specific carrier identity has changed.
     *
     * A specific carrier ID returns the fine-grained carrier ID of the current subscription.
     * It can represent the fact that a carrier may be in effect an aggregation of other carriers
     * (ie in an MVNO type scenario) where each of these specific carriers which are used to make
     * up the actual carrier service may have different carrier configurations.
     * A specific carrier ID could also be used, for example, in a scenario where a carrier requires
     * different carrier configuration for different service offering such as a prepaid plan.
     *
     * the specific carrier ID would be used for configuration purposes, but apps wishing to know
     * about the carrier itself should use the regular carrier ID returned by
     * {@link #getSimCarrierId()}.
     *
     * <p>Similar like {@link #ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED}, this intent will be
     * sent on the event of {@link #ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED} while its also
     * possible to be sent without {@link #ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED} when
     * specific carrier ID changes while carrier ID remains the same.
     * e.g, the same subscription switches to different IMSI could potentially change its
     * specific carrier ID while carrier id remains the same.
     * @see #getSimSpecificCarrierId()
     * @see #getSimCarrierId()
     *
     * The intent will have the following extra values:
     * <ul>
     *   <li>{@link #EXTRA_SPECIFIC_CARRIER_ID} The up-to-date specific carrier id of the
     *   current subscription.
     *   </li>
     *   <li>{@link #EXTRA_SPECIFIC_CARRIER_NAME} The up-to-date name of the specific carrier id.
     *   </li>
     *   <li>{@link #EXTRA_SUBSCRIPTION_ID} The subscription id associated with the changed carrier
     *   identity.
     *   </li>
     * </ul>
     * <p class="note">This is a protected intent that can only be sent by the system.
     */
    public static final String ACTION_SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED = "android.telephony.action.SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED";

    /**
     * To indicate allowed network type change is requested by carrier.
     * Carrier configuration won't affect the settings configured through
     * other reasons and will result in allowing network types that are in both
     * configurations (i.e intersection of both sets).
     */
    public static final int ALLOWED_NETWORK_TYPES_REASON_CARRIER = 2;

    /**
     * To indicate allowed network type change is requested by user.
     */
    public static final int ALLOWED_NETWORK_TYPES_REASON_USER = 0;

    /**
     * UICC application type is CSIM
     */
    public static final int APPTYPE_CSIM = 4;

    /**
     * UICC application type is ISIM
     */
    public static final int APPTYPE_ISIM = 5;

    /**
     * UICC application type is RUIM
     */
    public static final int APPTYPE_RUIM = 3;

    /**
     * UICC application type is SIM
     */
    public static final int APPTYPE_SIM = 1;

    /**
     * UICC application type is unknown or not specified
     */
    public static final int APPTYPE_UNKNOWN = 0;

    /**
     * UICC application type is USIM
     */
    public static final int APPTYPE_USIM = 2;

    /**
     * Authentication type for UICC challenge is EAP AKA. See RFC 4187 for details.
     */
    public static final int AUTHTYPE_EAP_AKA = 129;

    /**
     * Authentication type for UICC challenge is EAP SIM. See RFC 4186 for details.
     */
    public static final int AUTHTYPE_EAP_SIM = 128;

    /**
     * Authentication type for GBA Bootstrap Challenge.
     * Pass this authentication type into the {@link #getIccAuthentication} API to perform a GBA
     * Bootstrap challenge (BSF), with {@code data} (generated according to the procedure defined in
     * 3GPP 33.220 Section 5.3.2 step.4) in base64 encoding.
     * This method will return the Bootstrapping response in base64 encoding when ICC authentication
     * is completed.
     * Ref 3GPP 33.220 Section 5.3.2.
     */
    public static final int AUTHTYPE_GBA_BOOTSTRAP = 132;

    /**
     * Authentication type for GBA Network Application Functions (NAF) key External Challenge.
     * Pass this authentication type into the {@link #getIccAuthentication} API to perform a GBA
     * Network Applications Functions (NAF) key External challenge using the NAF_ID parameter
     * as the {@code data} in base64 encoding.
     * This method will return the Ks_Ext_Naf key in base64 encoding when ICC authentication
     * is completed.
     * Ref 3GPP 33.220 Section 5.3.2.
     */
    public static final int AUTHTYPE_GBA_NAF_KEY_EXTERNAL = 133;

    /**
     * Call composer status <b>Business Only</b> from user setting.
     */
    public static final int CALL_COMPOSER_STATUS_BUSINESS_ONLY = 2;

    /**
     * Call composer status <b>OFF</b> from user setting.
     */
    public static final int CALL_COMPOSER_STATUS_OFF = 0;

    /**
     * Call composer status <b>ON</b> from user setting.
     */
    public static final int CALL_COMPOSER_STATUS_ON = 1;

    /**
     * Device call state: No activity.
     */
    public static final int CALL_STATE_IDLE = 0;

    /**
     * Device call state: Off-hook. At least one call exists
     * that is dialing, active, or on hold, and no calls are ringing
     * or waiting.
     */
    public static final int CALL_STATE_OFFHOOK = 2;

    /**
     * Device call state: Ringing. A new call arrived and is
     *  ringing or waiting. In the latter case, another call is
     *  already active.
     */
    public static final int CALL_STATE_RINGING = 1;

    /**
     * Indicates whether {@link #getNetworkSlicingConfiguration} is supported. See comments on
     * respective methods for more information.
     */
    public static final String CAPABILITY_SLICING_CONFIG_SUPPORTED = "CAPABILITY_SLICING_CONFIG_SUPPORTED";

    /**
     * The device is not restricted to a carrier
     */
    public static final int CARRIER_RESTRICTION_STATUS_NOT_RESTRICTED = 1;

    /**
     * The device is restricted to a carrier.
     */
    public static final int CARRIER_RESTRICTION_STATUS_RESTRICTED = 2;

    /**
     * The device is restricted to the carrier of the calling application.
     */
    public static final int CARRIER_RESTRICTION_STATUS_RESTRICTED_TO_CALLER = 3;

    /**
     * Carrier restriction status value is unknown, in case modem did not provide any
     * information about carrier restriction status.
     */
    public static final int CARRIER_RESTRICTION_STATUS_UNKNOWN = 0;

    /**
     * Value for {@link CarrierConfigManager#KEY_CDMA_ROAMING_MODE_INT} which permits roaming on
     * affiliated networks.
     *
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int CDMA_ROAMING_MODE_AFFILIATED = 1;

    /**
     * Value for {@link CarrierConfigManager#KEY_CDMA_ROAMING_MODE_INT} which permits roaming on
     * any network.
     *
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int CDMA_ROAMING_MODE_ANY = 2;

    /**
     * Value for {@link CarrierConfigManager#KEY_CDMA_ROAMING_MODE_INT} which only permits
     * connections on home networks.
     *
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int CDMA_ROAMING_MODE_HOME = 0;

    /**
     * Value for {@link CarrierConfigManager#KEY_CDMA_ROAMING_MODE_INT} which leaves the roaming
     * mode set to the radio default or to the user's preference if they've indicated one.
     *
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int CDMA_ROAMING_MODE_RADIO_DEFAULT = -1;

    /**
     * Data connection is active, but physical link is down
     */
    public static final int DATA_ACTIVITY_DORMANT = 4;

    /**
     * Data connection activity: Currently receiving IP PPP traffic.
     */
    public static final int DATA_ACTIVITY_IN = 1;

    /**
     * Data connection activity: Currently both sending and receiving
     *   IP PPP traffic.
     */
    public static final int DATA_ACTIVITY_INOUT = 3;

    /**
     * Data connection activity: No traffic.
     */
    public static final int DATA_ACTIVITY_NONE = 0;

    /**
     * Data connection activity: Currently sending IP PPP traffic.
     */
    public static final int DATA_ACTIVITY_OUT = 2;

    /**
     * Data connection state: Connected. IP traffic should be available.
     */
    public static final int DATA_CONNECTED = 2;

    /**
     * Data connection state: Currently setting up a data connection.
     */
    public static final int DATA_CONNECTING = 1;

    /**
     * Data connection state: Disconnected. IP traffic not available.
     */
    public static final int DATA_DISCONNECTED = 0;

    /**
     * Data connection state: Disconnecting.
     *
     * IP traffic may be available but will cease working imminently.
     */
    public static final int DATA_DISCONNECTING = 4;

    /**
     * To indicate enable or disable carrier data by the system based on carrier signalling or
     * carrier privileged apps. Carrier data on/off won't affect user settings but will bypass the
     * settings and turns off data internally if set to {@code false}.
     */
    public static final int DATA_ENABLED_REASON_CARRIER = 2;

    /**
     * To indicate data was enabled or disabled due to mobile data policy overrides.
     * Note that this is not a valid reason for {@link #setDataEnabledForReason(int, boolean)} and
     * is only used to indicate that data enabled was changed due to an override.
     */
    public static final int DATA_ENABLED_REASON_OVERRIDE = 4;

    /**
     * To indicate that data control due to policy. Usually used when data limit is passed.
     * Policy data on/off won't affect user settings but will bypass the
     * settings and turns off data internally if set to {@code false}.
     */
    public static final int DATA_ENABLED_REASON_POLICY = 1;

    /**
     * To indicate enable or disable data by thermal service.
     * Thermal data on/off won't affect user settings but will bypass the
     * settings and turns off data internally if set to {@code false}.
     */
    public static final int DATA_ENABLED_REASON_THERMAL = 3;

    /**
     * To indicate that data was enabled or disabled due to an unknown reason.
     * Note that this is not a valid reason for {@link #setDataEnabledForReason(int, boolean)} and
     * is only used to indicate that data enabled was changed.
     */
    public static final int DATA_ENABLED_REASON_UNKNOWN = -1;

    /**
     * To indicate that user enabled or disabled data.
     */
    public static final int DATA_ENABLED_REASON_USER = 0;

    /**
     * Data connection state: Handover in progress. The connection is being transited from cellular
     * network to IWLAN, or from IWLAN to cellular network.
     */
    public static final int DATA_HANDOVER_IN_PROGRESS = 5;

    /**
     * Data connection state: Suspended. The connection is up, but IP
     *  traffic is temporarily unavailable. For example, in a 2G network,
     *  data activity may be suspended when a voice call arrives.
     */
    public static final int DATA_SUSPENDED = 3;

    /**
     * Data connection state: Unknown.  Used before we know the state.
     */
    public static final int DATA_UNKNOWN = -1;

    /**
     * Default port index for a UICC.
     *
     * On physical SIM cards the only available port is 0.
     * See {@link android.telephony.UiccPortInfo} for more information on ports.
     *
     * See {@link android.telephony.euicc.EuiccManager#isSimPortAvailable(int)} for information on
     * how portIndex is used on eUICCs.
     */
    public static final int DEFAULT_PORT_INDEX = 0;

    /**
     * ERI (Enhanced Roaming Indicator) is FLASH i.e value 2 defined by
     * 3GPP2 C.R1001-H v1.0 Table 8.1-1.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int ERI_FLASH = 2;

    /**
     * ERI (Enhanced Roaming Indicator) is OFF i.e value 1 defined by
     * 3GPP2 C.R1001-H v1.0 Table 8.1-1.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int ERI_OFF = 1;

    /**
     * ERI (Enhanced Roaming Indicator) is ON i.e value 0 defined by
     * 3GPP2 C.R1001-H v1.0 Table 8.1-1.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int ERI_ON = 0;

    /**
     * Event reported from the Telephony stack to indicate that the {@link Connection} is not
     * able to find any network and likely will not get connected. Upon receiving this event,
     * the dialer app should start the app included in the extras bundle of this event if satellite
     * is provisioned.
     * <p>
     * The dialer app receives this event via
     * {@link Call.Callback#onConnectionEvent(Call, String, Bundle)}.
     * <p>
     * The {@link Bundle} parameter is guaranteed to include the following extras if the below
     * conditions are met:
     * <ul>
     *     <li>{@link #EXTRA_EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE} - the recommending handover
     *         type.</li>
     *     <li>{@link #EXTRA_EMERGENCY_CALL_TO_SATELLITE_LAUNCH_INTENT} - the {@link PendingIntent}
     *         which will be launched by the Dialer app when receiving this connection event.</li>
     * </ul>
     * <p>
     * If the device is connected to satellite via carrier within the hysteresis time defined by
     * the carrier config
     * {@link CarrierConfigManager#KEY_SATELLITE_CONNECTION_HYSTERESIS_SEC_INT}, the component of
     * the {@link #EXTRA_EMERGENCY_CALL_TO_SATELLITE_LAUNCH_INTENT} will be set to the default SMS
     * app.
     * <p>
     * Otherwise, if the overlay config {@code config_oem_enabled_satellite_handover_app} is
     * present, the app defined by this config will be used as the component of the
     * {@link #EXTRA_EMERGENCY_CALL_TO_SATELLITE_LAUNCH_INTENT}. If this overlay config is empty,
     * {@link #EXTRA_EMERGENCY_CALL_TO_SATELLITE_LAUNCH_INTENT} will not be included in the event
     * {@link #EVENT_DISPLAY_EMERGENCY_MESSAGE}.
     */
    public static final String EVENT_DISPLAY_EMERGENCY_MESSAGE = "android.telephony.event.DISPLAY_EMERGENCY_MESSAGE";

    /**
     * The number of active SIM supported by current multi-SIM config. It's not related to how many
     * SIM/subscriptions are currently active.
     *
     * Same value will be returned by {@link #getActiveModemCount()}.
     *
     * For single SIM mode, it's 1.
     * For DSDS or DSDA mode, it's 2.
     * For triple-SIM mode, it's 3.
     *
     * Extra of {@link #ACTION_MULTI_SIM_CONFIG_CHANGED}.
     *
     * type: integer
     */
    public static final String EXTRA_ACTIVE_SIM_SUPPORTED_COUNT = "android.telephony.extra.ACTIVE_SIM_SUPPORTED_COUNT";

    /**
     * An integer extra containing the protocol of the apn connection.
     *
     * Sent with the {@link #ACTION_CARRIER_SIGNAL_PCO_VALUE} broadcast.
     * See the {@code PROTOCOL_*} constants in {@link ApnSetting} for a list of possible values.
     */
    public static final String EXTRA_APN_PROTOCOL = "android.telephony.extra.APN_PROTOCOL";

    /**
     * An integer extra containing the APN type.
     *
     * Sent with the  {@link #ACTION_CARRIER_SIGNAL_REQUEST_NETWORK_FAILED},
     * {@link #ACTION_CARRIER_SIGNAL_REDIRECTED}, and {@link #ACTION_CARRIER_SIGNAL_PCO_VALUE}
     * broadcasts.
     * See the {@code TYPE_} constants in {@link ApnSetting} for a list of possible values.
     */
    public static final String EXTRA_APN_TYPE = "android.telephony.extra.APN_TYPE";

    /**
     * The intent to call voicemail.
     */
    public static final String EXTRA_CALL_VOICEMAIL_INTENT = "android.telephony.extra.CALL_VOICEMAIL_INTENT";

    /**
     * An int extra used with {@link #ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED} which indicates
     * the updated carrier id returned by {@link TelephonyManager#getSimCarrierId()}.
     * <p>Will be {@link TelephonyManager#UNKNOWN_CARRIER_ID} if the subscription is unavailable or
     * the carrier cannot be identified.
     */
    public static final String EXTRA_CARRIER_ID = "android.telephony.extra.CARRIER_ID";

    /**
     * An string extra used with {@link #ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED} which
     * indicates the updated carrier name of the current subscription.
     * @see TelephonyManager#getSimCarrierIdName()
     * <p>Carrier name is a user-facing name of the carrier id {@link #EXTRA_CARRIER_ID},
     * usually the brand name of the subsidiary (e.g. T-Mobile).
     */
    public static final String EXTRA_CARRIER_NAME = "android.telephony.extra.CARRIER_NAME";

    /**
     * An integer extra containing the data fail cause.
     *
     * Sent with {@link #ACTION_CARRIER_SIGNAL_REQUEST_NETWORK_FAILED}. See {@link DataFailCause}
     * for a list of possible values.
     */
    public static final String EXTRA_DATA_FAIL_CAUSE = "android.telephony.extra.DATA_FAIL_CAUSE";

    /**
     * A boolean extra indicating the availability of the default network.
     * Sent with the {@link #ACTION_CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE} broadcast.
     */
    public static final String EXTRA_DEFAULT_NETWORK_AVAILABLE = "android.telephony.extra.DEFAULT_NETWORK_AVAILABLE";

    /**
     * Integer extra key used with {@link #EVENT_DISPLAY_EMERGENCY_MESSAGE} which indicates
     * the type of handover from emergency call to satellite messaging.
     * <p>
     * Will be either
     * android.telephony.satellite.SatelliteManager#EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE_SOS
     * or
     * android.telephony.satellite.SatelliteManager#EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE_T911
     * <p>
     * Set in the extras for the {@link #EVENT_DISPLAY_EMERGENCY_MESSAGE} connection event.
     */
    public static final String EXTRA_EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE = "android.telephony.extra.EMERGENCY_CALL_TO_SATELLITE_HANDOVER_TYPE";

    /**
     * Extra key used with the {@link #EVENT_DISPLAY_EMERGENCY_MESSAGE} for a {@link PendingIntent}
     * which will be launched by the Dialer app.
     */
    public static final String EXTRA_EMERGENCY_CALL_TO_SATELLITE_LAUNCH_INTENT = "android.telephony.extra.EMERGENCY_CALL_TO_SATELLITE_LAUNCH_INTENT";

    /**
     * The boolean value indicating whether the voicemail settings activity launched by {@link
     * #ACTION_CONFIGURE_VOICEMAIL} should hide settings accessible through public API. This is
     * used by dialer implementations which provides their own voicemail settings UI, but still
     * needs to expose device specific voicemail settings to the user.
     *
     * @see #ACTION_CONFIGURE_VOICEMAIL
     * @see #METADATA_HIDE_VOICEMAIL_SETTINGS_MENU
     */
    public static final String EXTRA_HIDE_PUBLIC_SETTINGS = "android.telephony.extra.HIDE_PUBLIC_SETTINGS";

    /**
     * Extra key used with the {@link #ACTION_PHONE_STATE_CHANGED} broadcast
     * for a String containing the incoming or outgoing phone number.
     * <p>
     * This extra is only populated for receivers of the {@link #ACTION_PHONE_STATE_CHANGED}
     * broadcast which have been granted the {@link android.Manifest.permission#READ_CALL_LOG} and
     * {@link android.Manifest.permission#READ_PHONE_STATE} permissions.
     * <p>
     * For incoming calls, the phone number is only guaranteed to be populated when the
     * {@link #EXTRA_STATE} changes from {@link #EXTRA_STATE_IDLE} to {@link #EXTRA_STATE_RINGING}.
     * If the incoming caller is from an unknown number, the extra will be populated with an empty
     * string.
     * For outgoing calls, the phone number is only guaranteed to be populated when the
     * {@link #EXTRA_STATE} changes from {@link #EXTRA_STATE_IDLE} to {@link #EXTRA_STATE_OFFHOOK}.
     * <p class="note">
     * Retrieve with
     * {@link android.content.Intent#getStringExtra(String)}.
     * <p>
     *
     * @deprecated Companion apps for wearable devices should use the {@link InCallService} API
     * to retrieve the phone number for calls instead.  Apps performing call screening should use
     * the {@link CallScreeningService} API instead.
     */
    @Deprecated
    public static final String EXTRA_INCOMING_NUMBER = "incoming_number";

    /**
     * Boolean value representing whether the {@link
     * TelephonyManager#ACTION_SHOW_VOICEMAIL_NOTIFICATION} is new or a refresh of an existing
     * notification. Notification refresh happens after reboot or connectivity changes. The user has
     * already been notified for the voicemail so it should not alert the user, and should not be
     * shown again if the user has dismissed it.
     */
    public static final String EXTRA_IS_REFRESH = "android.telephony.extra.IS_REFRESH";

    /**
     * The extra used with an {@link #ACTION_NETWORK_COUNTRY_CHANGED} to specify the
     * last known the country code in ISO-3166-1 alpha-2 format. This might be an empty string when
     * the country code was never available. The last known country code persists across reboot.
     *
     * <p class="note">
     * Retrieve with {@link android.content.Intent#getStringExtra(String)}.
     */
    public static final String EXTRA_LAST_KNOWN_NETWORK_COUNTRY = "android.telephony.extra.LAST_KNOWN_NETWORK_COUNTRY";

    /**
     * The intent to launch voicemail settings.
     */
    public static final String EXTRA_LAUNCH_VOICEMAIL_SETTINGS_INTENT = "android.telephony.extra.LAUNCH_VOICEMAIL_SETTINGS_INTENT";

    /**
     * The extra used with an {@link #ACTION_NETWORK_COUNTRY_CHANGED} to specify the
     * the country code in ISO-3166-1 alpha-2 format. This is the same country code returned by
     * {@link #getNetworkCountryIso()}. This might be an empty string when the country code is not
     * available.
     *
     * <p class="note">
     * Retrieve with {@link android.content.Intent#getStringExtra(String)}.
     */
    public static final String EXTRA_NETWORK_COUNTRY = "android.telephony.extra.NETWORK_COUNTRY";

    /**
     * The number of voice messages associated with the notification.
     */
    public static final String EXTRA_NOTIFICATION_COUNT = "android.telephony.extra.NOTIFICATION_COUNT";

    /**
     * An integer extra indicating the ID for the PCO data.
     * Sent with the {@link #ACTION_CARRIER_SIGNAL_PCO_VALUE} broadcast.
     */
    public static final String EXTRA_PCO_ID = "android.telephony.extra.PCO_ID";

    /**
     * A byte array extra containing PCO data read from the modem.
     * Sent with the {@link #ACTION_CARRIER_SIGNAL_PCO_VALUE} broadcast.
     */
    public static final String EXTRA_PCO_VALUE = "android.telephony.extra.PCO_VALUE";

    /**
     * The extra used with an {@link #ACTION_CONFIGURE_VOICEMAIL} and
     * {@link #ACTION_SHOW_VOICEMAIL_NOTIFICATION} {@code Intent} to specify the
     * {@link PhoneAccountHandle} the configuration or notification is for.
     * <p class="note">
     * Retrieve with {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_PHONE_ACCOUNT_HANDLE = "android.telephony.extra.PHONE_ACCOUNT_HANDLE";

    /**
     * String extra containing the redirection URL sent with
     * {@link #ACTION_CARRIER_SIGNAL_REDIRECTED}.
     */
    public static final String EXTRA_REDIRECTION_URL = "android.telephony.extra.REDIRECTION_URL";

    /**
     * An int extra used with {@link #ACTION_SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED} which
     * indicates the updated specific carrier id returned by
     * {@link TelephonyManager#getSimSpecificCarrierId()}. Note, its possible specific carrier id
     * changes while {@link #ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED} remains the same
     * e.g, when subscription switch to different IMSIs.
     * <p>Will be {@link TelephonyManager#UNKNOWN_CARRIER_ID} if the subscription is unavailable or
     * the carrier cannot be identified.
     */
    public static final String EXTRA_SPECIFIC_CARRIER_ID = "android.telephony.extra.SPECIFIC_CARRIER_ID";

    /**
     * An string extra used with {@link #ACTION_SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED}
     * which indicates the updated specific carrier name returned by
     * {@link TelephonyManager#getSimSpecificCarrierIdName()}.
     * <p>it's a user-facing name of the specific carrier id {@link #EXTRA_SPECIFIC_CARRIER_ID}
     * e.g, Tracfone-AT&T
     */
    public static final String EXTRA_SPECIFIC_CARRIER_NAME = "android.telephony.extra.SPECIFIC_CARRIER_NAME";

    /**
     * The lookup key used with the {@link #ACTION_PHONE_STATE_CHANGED} broadcast
     * for a String containing the new call state.
     *
     * <p class="note">
     * Retrieve with
     * {@link android.content.Intent#getStringExtra(String)}.
     *
     * @see #EXTRA_STATE_IDLE
     * @see #EXTRA_STATE_RINGING
     * @see #EXTRA_STATE_OFFHOOK
     */
    public static final String EXTRA_STATE = "state";

    /**
     * An int extra used with {@link #ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED} to indicate the
     * subscription which has changed; or in general whenever a subscription ID needs specified.
     */
    public static final String EXTRA_SUBSCRIPTION_ID = "android.telephony.extra.SUBSCRIPTION_ID";

    /**
     * The voicemail number.
     */
    public static final String EXTRA_VOICEMAIL_NUMBER = "android.telephony.extra.VOICEMAIL_NUMBER";

    /**
     * Include coarse location data.
     *
     * Indicates whether the caller would not like to receive
     * location related information which will be sent if the caller already possess
     * {@link android.Manifest.permission#ACCESS_COARSE_LOCATION} and do not renounce the
     * permissions.
     */
    public static final int INCLUDE_LOCATION_DATA_COARSE = 1;

    /**
     * Include fine location data.
     *
     * Indicates whether the caller would not like to receive
     * location related information which will be sent if the caller already possess
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION} and do not renounce the
     * permissions.
     */
    public static final int INCLUDE_LOCATION_DATA_FINE = 2;

    /**
     * Specifies to not include any location related data.
     *
     * Indicates whether the caller would not like to receive
     * location related information which will be sent if the caller already possess
     * {@link android.Manifest.permission#ACCESS_COARSE_LOCATION} and do not renounce the
     * permissions.
     */
    public static final int INCLUDE_LOCATION_DATA_NONE = 0;

    /**
     * A boolean meta-data value indicating whether the voicemail settings should be hidden in the
     * call settings page launched by
     * {@link android.telecom.TelecomManager#ACTION_SHOW_CALL_SETTINGS}.
     * Dialer implementations (see {@link android.telecom.TelecomManager#getDefaultDialerPackage()})
     * which would also like to manage voicemail settings should set this meta-data to {@code true}
     * in the manifest registration of their application.
     *
     * @see android.telecom.TelecomManager#ACTION_SHOW_CALL_SETTINGS
     * @see #ACTION_CONFIGURE_VOICEMAIL
     * @see #EXTRA_HIDE_PUBLIC_SETTINGS
     */
    public static final String METADATA_HIDE_VOICEMAIL_SETTINGS_MENU = "android.telephony.HIDE_VOICEMAIL_SETTINGS_MENU";

    /**
     * The usage of multiple SIM cards at the same time to register on the network (e.g. Dual
     * Standby or Dual Active) is supported.
     */
    public static final int MULTISIM_ALLOWED = 0;

    /**
     * The usage of multiple SIM cards at the same time to register on the network (e.g. Dual
     * Standby or Dual Active) is supported by the hardware, but restricted by the carrier.
     */
    public static final int MULTISIM_NOT_SUPPORTED_BY_CARRIER = 2;

    /**
     * The usage of multiple SIM cards at the same time to register on the network (e.g. Dual
     * Standby or Dual Active) is not supported by the hardware.
     */
    public static final int MULTISIM_NOT_SUPPORTED_BY_HARDWARE = 1;

    public static final int NETWORK_SELECTION_MODE_AUTO = 1;

    public static final int NETWORK_SELECTION_MODE_MANUAL = 2;

    public static final int NETWORK_SELECTION_MODE_UNKNOWN = 0;

    /**
     * Current network is 1xRTT
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int NETWORK_TYPE_1xRTT = 7;

    /**
     * network type bitmask indicating the support of radio tech 1xRTT.
     */
    public static final long NETWORK_TYPE_BITMASK_1xRTT = 64L;

    /**
     * network type bitmask indicating the support of radio tech CDMA(IS95A/IS95B).
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final long NETWORK_TYPE_BITMASK_CDMA = 8L;

    /**
     * network type bitmask indicating the support of radio tech EDGE.
     */
    public static final long NETWORK_TYPE_BITMASK_EDGE = 2L;

    /**
     * network type bitmask indicating the support of radio tech EHRPD.
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final long NETWORK_TYPE_BITMASK_EHRPD = 8192L;

    /**
     * network type bitmask indicating the support of radio tech EVDO 0.
     */
    public static final long NETWORK_TYPE_BITMASK_EVDO_0 = 16L;

    /**
     * network type bitmask indicating the support of radio tech EVDO A.
     */
    public static final long NETWORK_TYPE_BITMASK_EVDO_A = 32L;

    /**
     * network type bitmask indicating the support of radio tech EVDO B.
     */
    public static final long NETWORK_TYPE_BITMASK_EVDO_B = 2048L;

    /**
     * network type bitmask indicating the support of radio tech GPRS.
     */
    public static final long NETWORK_TYPE_BITMASK_GPRS = 1L;

    /**
     * network type bitmask indicating the support of radio tech GSM.
     */
    public static final long NETWORK_TYPE_BITMASK_GSM = 32768L;

    /**
     * network type bitmask indicating the support of radio tech HSDPA.
     */
    public static final long NETWORK_TYPE_BITMASK_HSDPA = 128L;

    /**
     * network type bitmask indicating the support of radio tech HSPA.
     */
    public static final long NETWORK_TYPE_BITMASK_HSPA = 512L;

    /**
     * network type bitmask indicating the support of radio tech HSPAP.
     */
    public static final long NETWORK_TYPE_BITMASK_HSPAP = 16384L;

    /**
     * network type bitmask indicating the support of radio tech HSUPA.
     */
    public static final long NETWORK_TYPE_BITMASK_HSUPA = 256L;

    /**
     * network type bitmask indicating the support of radio tech IWLAN.
     */
    public static final long NETWORK_TYPE_BITMASK_IWLAN = 131072L;

    /**
     * network type bitmask indicating the support of radio tech LTE.
     */
    public static final long NETWORK_TYPE_BITMASK_LTE = 4096L;

    /**
     * NOT USED; this bitmask is exposed accidentally.
     * If used, will be converted to {@link #NETWORK_TYPE_BITMASK_LTE}.
     * network type bitmask indicating the support of radio tech LTE CA (carrier aggregation).
     *
     * @deprecated Please use {@link #NETWORK_TYPE_BITMASK_LTE} instead. Deprecated in Android U.
     */
    @Deprecated
    public static final long NETWORK_TYPE_BITMASK_LTE_CA = 262144L;

    /**
     * network type bitmask indicating the support of radio tech NR(New Radio) 5G.
     */
    public static final long NETWORK_TYPE_BITMASK_NR = 524288L;

    /**
     * network type bitmask indicating the support of radio tech TD_SCDMA.
     */
    public static final long NETWORK_TYPE_BITMASK_TD_SCDMA = 65536L;

    /**
     * network type bitmask indicating the support of radio tech UMTS.
     */
    public static final long NETWORK_TYPE_BITMASK_UMTS = 4L;

    /**
     * network type bitmask unknown.
     */
    public static final long NETWORK_TYPE_BITMASK_UNKNOWN = 0L;

    /**
     * Current network is CDMA: Either IS95A or IS95B
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int NETWORK_TYPE_CDMA = 4;

    /**
     * Current network is EDGE
     */
    public static final int NETWORK_TYPE_EDGE = 2;

    /**
     * Current network is eHRPD
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int NETWORK_TYPE_EHRPD = 14;

    /**
     * Current network is EVDO revision 0
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int NETWORK_TYPE_EVDO_0 = 5;

    /**
     * Current network is EVDO revision A
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int NETWORK_TYPE_EVDO_A = 6;

    /**
     * Current network is EVDO revision B
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int NETWORK_TYPE_EVDO_B = 12;

    /**
     * Current network is GPRS
     */
    public static final int NETWORK_TYPE_GPRS = 1;

    /**
     * Current network is GSM
     */
    public static final int NETWORK_TYPE_GSM = 16;

    /**
     * Current network is HSDPA
     */
    public static final int NETWORK_TYPE_HSDPA = 8;

    /**
     * Current network is HSPA
     */
    public static final int NETWORK_TYPE_HSPA = 10;

    /**
     * Current network is HSPA+
     */
    public static final int NETWORK_TYPE_HSPAP = 15;

    /**
     * Current network is HSUPA
     */
    public static final int NETWORK_TYPE_HSUPA = 9;

    /**
     * Current network is iDen
     * @deprecated Legacy network type no longer being used starting in Android U.
     */
    @Deprecated
    public static final int NETWORK_TYPE_IDEN = 11;

    /**
     * Current network is IWLAN
     */
    public static final int NETWORK_TYPE_IWLAN = 18;

    /**
     * Current network is LTE
     */
    public static final int NETWORK_TYPE_LTE = 13;

    /**
     * Current network is NR (New Radio) 5G.
     * This will only be returned for 5G SA.
     * For 5G NSA, the network type will be {@link #NETWORK_TYPE_LTE}.
     */
    public static final int NETWORK_TYPE_NR = 20;

    /**
     * Current network is TD_SCDMA
     */
    public static final int NETWORK_TYPE_TD_SCDMA = 17;

    /**
     * Current network is UMTS
     */
    public static final int NETWORK_TYPE_UMTS = 3;

    /**
     * Network type is unknown
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;

    /**
     * Phone radio is CDMA.
     *
     * @deprecated Legacy CDMA is unsupported.
     */
    @Deprecated
    public static final int PHONE_TYPE_CDMA = 2;

    /**
     * Phone radio is GSM.
     */
    public static final int PHONE_TYPE_GSM = 1;

    /**
     * No phone radio.
     */
    public static final int PHONE_TYPE_NONE = 0;

    /**
     * Phone is via SIP.
     */
    public static final int PHONE_TYPE_SIP = 3;

    /**
     * A premium capability that boosts the network to allow for real-time interactive traffic
     * by prioritizing low latency communication.
     * Corresponds to {@link NetworkCapabilities#NET_CAPABILITY_PRIORITIZE_LATENCY}.
     */
    public static final int PREMIUM_CAPABILITY_PRIORITIZE_LATENCY = 34;

    /**
     * Purchase premium capability failed because a request was already made and is in progress.
     * This may have been requested by either the same app or another app.
     * Subsequent attempts will return the same error until the previous request completes.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_ALREADY_IN_PROGRESS = 4;

    /**
     * Purchase premium capability failed because it is already purchased and available.
     * Subsequent attempts will return the same error until the performance boost expires.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_ALREADY_PURCHASED = 3;

    /**
     * Purchase premium capability failed because the carrier disabled or does not support
     * the capability, as specified in
     * {@link CarrierConfigManager#KEY_SUPPORTED_PREMIUM_CAPABILITIES_INT_ARRAY}.
     * Subsequent attempts will return the same error until the carrier enables the feature.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_CARRIER_DISABLED = 7;

    /**
     * Purchase premium capability failed because the carrier app did not indicate success.
     * Subsequent attempts will be throttled for the amount of time specified by
     * {@link CarrierConfigManager
     * #KEY_PREMIUM_CAPABILITY_PURCHASE_CONDITION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG}
     * and return {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_THROTTLED}.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_CARRIER_ERROR = 8;

    /**
     * Purchase premium capability failed because the entitlement check failed.
     * Subsequent attempts will be throttled for the amount of time specified by
     * {@link CarrierConfigManager
     * #KEY_PREMIUM_CAPABILITY_PURCHASE_CONDITION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG}
     * and return {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_THROTTLED}.
     * Throttling will be reevaluated when the network is no longer congested.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_ENTITLEMENT_CHECK_FAILED = 13;

    /**
     * Purchase premium capability failed because the device does not support the feature.
     * Subsequent attempts will return the same error.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_FEATURE_NOT_SUPPORTED = 10;

    /**
     * Purchase premium capability failed because the network is not available.
     * Subsequent attempts will return the same error until network conditions change.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_NETWORK_NOT_AVAILABLE = 12;

    /**
     * Purchase premium capability failed because the request was not made on the default data
     * subscription, indicated by {@link SubscriptionManager#getDefaultDataSubscriptionId()}.
     * Subsequent attempts will return the same error until the request is made on the default
     * data subscription.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_NOT_DEFAULT_DATA_SUBSCRIPTION = 14;

    /**
     * Purchase premium capability failed because the requesting application is not in the
     * foreground. Subsequent attempts will return the same error until the requesting application
     * moves to the foreground.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_NOT_FOREGROUND = 5;

    /**
     * Purchase premium capability was successful and is waiting for the network to setup the
     * slicing configuration. If the setup is complete within the time specified by
     * {@link CarrierConfigManager#KEY_PREMIUM_CAPABILITY_NETWORK_SETUP_TIME_MILLIS_LONG},
     * subsequent requests will return {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_ALREADY_PURCHASED}
     * until the purchase expires. If the setup is not complete within the time specified above,
     * applications can request the premium capability again.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_PENDING_NETWORK_SETUP = 15;

    /**
     * Purchase premium capability failed because the telephony service is unavailable
     * or there was an error in the phone process.
     * Subsequent attempts will return the same error until request conditions are satisfied.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_REQUEST_FAILED = 11;

    /**
     * Purchase premium capability request was successful.
     * Once the purchase result is successful, the network must set up a slicing configuration
     * for the purchased premium capability within the timeout specified by
     * {@link CarrierConfigManager#KEY_PREMIUM_CAPABILITY_NETWORK_SETUP_TIME_MILLIS_LONG}.
     * During the setup time, subsequent attempts will return
     * {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_PENDING_NETWORK_SETUP}.
     * After setup is complete, subsequent attempts will return
     * {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_ALREADY_PURCHASED} until the boost expires.
     * The expiry time is determined by the type or duration of boost purchased from the carrier,
     * provided at {@link CarrierConfigManager#KEY_PREMIUM_CAPABILITY_PURCHASE_URL_STRING}.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_SUCCESS = 1;

    /**
     * Purchase premium capability failed because the request is throttled.
     * If purchasing premium capabilities is throttled, it will be for the amount of time
     * specified by {@link CarrierConfigManager
     * #KEY_PREMIUM_CAPABILITY_PURCHASE_CONDITION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG}.
     * If displaying the performance boost notification is throttled, it will be for the amount of
     * time specified by {@link CarrierConfigManager
     * #KEY_PREMIUM_CAPABILITY_NOTIFICATION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG}.
     * We will show the performance boost notification to the user up to the daily and monthly
     * maximum number of times specified by
     * {@link CarrierConfigManager#KEY_PREMIUM_CAPABILITY_MAXIMUM_DAILY_NOTIFICATION_COUNT_INT} and
     * {@link CarrierConfigManager#KEY_PREMIUM_CAPABILITY_MAXIMUM_MONTHLY_NOTIFICATION_COUNT_INT}.
     * Subsequent attempts will return the same error until the request is no longer throttled
     * or throttling conditions change.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_THROTTLED = 2;

    /**
     * Purchase premium capability failed because we did not receive a response from the user
     * for the performance boost notification within the time specified by
     * {@link CarrierConfigManager#KEY_PREMIUM_CAPABILITY_NOTIFICATION_DISPLAY_TIMEOUT_MILLIS_LONG}.
     * The performance boost notification will be automatically dismissed and subsequent attempts
     * will be throttled for the amount of time specified by
     * {@link CarrierConfigManager
     * #KEY_PREMIUM_CAPABILITY_NOTIFICATION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG}
     * and return {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_THROTTLED}.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_TIMEOUT = 9;

    /**
     * Purchase premium capability failed because the user canceled the operation.
     * Subsequent attempts will be throttled for the amount of time specified by
     * {@link CarrierConfigManager
     * #KEY_PREMIUM_CAPABILITY_NOTIFICATION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG}
     * and return {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_THROTTLED}.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_USER_CANCELED = 6;

    /**
     * Purchase premium capability failed because the user disabled the feature.
     * Subsequent attempts will be throttled for the amount of time specified by
     * {@link CarrierConfigManager
     * #KEY_PREMIUM_CAPABILITY_NOTIFICATION_BACKOFF_HYSTERESIS_TIME_MILLIS_LONG}
     * and return {@link #PURCHASE_PREMIUM_CAPABILITY_RESULT_THROTTLED}.
     */
    public static final int PURCHASE_PREMIUM_CAPABILITY_RESULT_USER_DISABLED = 16;

    /**
     * The subscription is not valid. It must be an active opportunistic subscription.
     */
    public static final int SET_OPPORTUNISTIC_SUB_INACTIVE_SUBSCRIPTION = 2;

    /**
     * The subscription is not valid. It must be an opportunistic subscription.
     */
    public static final int SET_OPPORTUNISTIC_SUB_NO_OPPORTUNISTIC_SUB_AVAILABLE = 3;

    /**
     * Subscription service happened remote exception.
     */
    public static final int SET_OPPORTUNISTIC_SUB_REMOTE_SERVICE_EXCEPTION = 4;

    /**
     * No error. Operation succeeded.
     */
    public static final int SET_OPPORTUNISTIC_SUB_SUCCESS = 0;

    /**
     * Validation failed when trying to switch to preferred subscription.
     */
    public static final int SET_OPPORTUNISTIC_SUB_VALIDATION_FAILED = 1;

    /**
     * SIM card state: no SIM card is available in the device
     */
    public static final int SIM_STATE_ABSENT = 1;

    /**
     * SIM card state: SIM Card Error, present but faulty
     */
    public static final int SIM_STATE_CARD_IO_ERROR = 8;

    /**
     * SIM card state: SIM Card restricted, present but not usable due to
     *  carrier restrictions.
     */
    public static final int SIM_STATE_CARD_RESTRICTED = 9;

    /**
     * SIM card state: Locked: requires a network PIN to unlock
     */
    public static final int SIM_STATE_NETWORK_LOCKED = 4;

    /**
     * SIM card state: SIM Card is NOT READY
     */
    public static final int SIM_STATE_NOT_READY = 6;

    /**
     * SIM card state: SIM Card Error, permanently disabled
     */
    public static final int SIM_STATE_PERM_DISABLED = 7;

    /**
     * SIM card state: Locked: requires the user's SIM PIN to unlock
     */
    public static final int SIM_STATE_PIN_REQUIRED = 2;

    /**
     * SIM card state: Locked: requires the user's SIM PUK to unlock
     */
    public static final int SIM_STATE_PUK_REQUIRED = 3;

    /**
     * SIM card state: Ready
     */
    public static final int SIM_STATE_READY = 5;

    /**
     * SIM card state: Unknown. Signifies that the SIM is in transition
     * between states. For example, when the user inputs the SIM pin
     * under PIN_REQUIRED state, a query for sim status returns
     * this state before turning to SIM_STATE_READY.
     *
     * These are the ordinal value of IccCardConstants.State.
     */
    public static final int SIM_STATE_UNKNOWN = 0;

    /**
     * A UICC card identifier used before the UICC card is loaded. See
     * {@link #getCardIdForDefaultEuicc()} and {@link UiccCardInfo#getCardId()}.
     * <p>
     * Note that once the UICC card is loaded, the card ID may become {@link #UNSUPPORTED_CARD_ID}.
     */
    public static final int UNINITIALIZED_CARD_ID = -2;

    /**
     * An unknown carrier id. It could either be subscription unavailable or the subscription
     * carrier cannot be recognized. Unrecognized carriers here means
     * {@link #getSimOperator() MCC+MNC} cannot be identified.
     */
    public static final int UNKNOWN_CARRIER_ID = -1;

    /**
     * A UICC card identifier used if the device does not support the operation.
     * For example, {@link #getCardIdForDefaultEuicc()} returns this value if the device has no
     * eUICC, or the eUICC cannot be read.
     */
    public static final int UNSUPPORTED_CARD_ID = -1;

    /**
     * The request is aborted.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_ABORTED = 2;

    /**
     * Disable modem fail.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_DISABLE_MODEM_FAIL = 5;

    /**
     * Enable modem fail.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_ENABLE_MODEM_FAIL = 6;

    /**
     * The parameter passed in is invalid.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_INVALID_ARGUMENTS = 3;

    /**
     * Carrier app does not support multiple available networks.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_MULTIPLE_NETWORKS_NOT_SUPPORTED = 7;

    /**
     * No carrier privilege.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_NO_CARRIER_PRIVILEGE = 4;

    /**
     * The subscription is not valid. It must be an opportunistic subscription.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_NO_OPPORTUNISTIC_SUB_AVAILABLE = 8;

    /**
     * There is no OpportunisticNetworkService.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_REMOTE_SERVICE_EXCEPTION = 9;

    /**
     * OpportunisticNetworkService is disabled.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_SERVICE_IS_DISABLED = 10;

    /**
     * No error. Operation succeeded.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_SUCCESS = 0;

    /**
     * There is a unknown failure happened.
     */
    public static final int UPDATE_AVAILABLE_NETWORKS_UNKNOWN_FAILURE = 1;

    /**
     * Failure code returned when a USSD request has failed to execute because the Telephony
     * service is unavailable.
     * <p>
     * Returned via {@link TelephonyManager.UssdResponseCallback#onReceiveUssdResponseFailed(
     * TelephonyManager, String, int)}.
     */
    public static final int USSD_ERROR_SERVICE_UNAVAIL = -2;

    /**
     * Failed code returned when the mobile network has failed to complete a USSD request.
     * <p>
     * Returned via {@link TelephonyManager.UssdResponseCallback#onReceiveUssdResponseFailed(
     * TelephonyManager, String, int)}.
     */
    public static final int USSD_RETURN_FAILURE = -1;

    /**
     * A flavor of OMTP protocol with a different mobile originated (MO) format
     */
    public static final String VVM_TYPE_CVVM = "vvm_type_cvvm";

    /**
     * The OMTP protocol.
     */
    public static final String VVM_TYPE_OMTP = "vvm_type_omtp";

    private TelephonyManager() {
    }

    /**
     * Exception that may be supplied to the callback in {@link #uploadCallComposerPicture} if
     * something goes awry.
     */
    public static final class CallComposerException {
        /**
         * Indicates that the device failed to authenticate with the carrier when uploading the
         * picture.
         *
         * Clients that encounter this error should not retry the upload unless a reboot or radio
         * reset has been performed in the interim.
         */
        public static final int ERROR_AUTHENTICATION_FAILED = 3;

        /**
         * Indicates that the file or stream supplied exceeds the size limit defined in
         * {@link #getMaximumCallComposerPictureSize()}.
         *
         * Clients that encounter this error should retry the upload after reducing the size of the
         * picture.
         */
        public static final int ERROR_FILE_TOO_LARGE = 2;

        /**
         * Indicates that the {@link InputStream} passed to {@link #uploadCallComposerPicture}
         * was closed.
         *
         * The caller should retry if this error is encountered, and be sure to not close the stream
         * before the callback is called this time.
         */
        public static final int ERROR_INPUT_CLOSED = 4;

        /**
         * Indicates that an {@link IOException} was encountered while reading the picture.
         *
         * The offending {@link IOException} will be available via {@link #getIOException()}.
         * Clients should use the contents of the exception to determine whether a retry is
         * warranted.
         */
        public static final int ERROR_IO_EXCEPTION = 5;

        /**
         * Indicates that the device is currently not connected to a network that's capable of
         * reaching a carrier's RCS servers.
         *
         * Clients should prompt the user to remedy the issue by moving to an area with better
         * signal, by connecting to a different network, or to retry at another time.
         */
        public static final int ERROR_NETWORK_UNAVAILABLE = 6;

        /**
         * Indicates that the phone process died or otherwise became unavailable while uploading the
         * call composer picture.
         *
         * Clients that encounter this error should retry the upload.
         */
        public static final int ERROR_REMOTE_END_CLOSED = 1;

        /**
         * Indicates that an unknown error was encountered when uploading the call composer picture.
         *
         * Clients that encounter this error should retry the upload.
         */
        public static final int ERROR_UNKNOWN = 0;

        private CallComposerException() {
        }
    }

    /**
     * Callback for providing asynchronous {@link CellInfo} on request
     */
    public static final class CellInfoCallback {
        /**
         * The modem returned a failure.
         */
        public static final int ERROR_MODEM_ERROR = 2;

        /**
         * The system timed out waiting for a response from the Radio.
         */
        public static final int ERROR_TIMEOUT = 1;

        private CellInfoCallback() {
        }
    }
}
