package android.telecom;

/**
 * Provides access to information about active calls and registration/call-management functionality.
 * Apps can use methods in this class to determine the current call state.
 * <p>
 * Apps do not instantiate this class directly; instead, they retrieve a reference to an instance
 * through {@link Context#getSystemService Context.getSystemService(Context.TELECOM_SERVICE)}.
 * <p>
 * Note that access to some telecom information is permission-protected. Your app cannot access the
 * protected information or gain access to protected functionality unless it has the appropriate
 * permissions declared in its manifest file. Where permissions apply, they are noted in the method
 * descriptions.
 */
public final class TelecomManager {
    /**
     * Activity action: Shows a dialog asking the user whether or not they want to replace the
     * current default Dialer with the one specified in
     * {@link #EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME}.
     *
     * Usage example:
     * <pre>
     * Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
     * intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
     *         getActivity().getPackageName());
     * startActivity(intent);
     * </pre>
     * <p>
     * This is no longer supported since Q, please use
     * {@link android.app.role.RoleManager#createRequestRoleIntent(String)} with
     * {@link android.app.role.RoleManager#ROLE_DIALER} instead.
     */
    public static final String ACTION_CHANGE_DEFAULT_DIALER = "android.telecom.action.CHANGE_DEFAULT_DIALER";

    /**
     * The {@link android.content.Intent} action used to show the settings page used to configure
     * {@link PhoneAccount} preferences.
     */
    public static final String ACTION_CHANGE_PHONE_ACCOUNTS = "android.telecom.action.CHANGE_PHONE_ACCOUNTS";

    /**
     * An {@link android.content.Intent} action sent by the telecom framework to start a
     * configuration dialog for a registered {@link PhoneAccount}. There is no default dialog
     * and each app that registers a {@link PhoneAccount} should provide one if desired.
     * <p>
     * A user can access the list of enabled {@link android.telecom.PhoneAccount}s through the Phone
     * app's settings menu. For each entry, the settings app will add a click action. When
     * triggered, the click-action will start this intent along with the extra
     * {@link #EXTRA_PHONE_ACCOUNT_HANDLE} to indicate the {@link PhoneAccount} to configure. If the
     * {@link PhoneAccount} package does not register an {@link android.app.Activity} for this
     * intent, then it will not be sent.
     */
    public static final String ACTION_CONFIGURE_PHONE_ACCOUNT = "android.telecom.action.CONFIGURE_PHONE_ACCOUNT";

    /**
     * Broadcast intent action indicating that the current default call screening app has changed.
     * <p>
     * Note: This intent is NEVER actually broadcast and will be deprecated in the future.
     * <p>
     * An app that want to know if it holds the
     * {@link android.app.role.RoleManager#ROLE_CALL_SCREENING} role can use
     * {@link android.app.role.RoleManager#isRoleHeld(String)} to confirm if it holds the role or
     * not.
     */
    public static final String ACTION_DEFAULT_CALL_SCREENING_APP_CHANGED = "android.telecom.action.DEFAULT_CALL_SCREENING_APP_CHANGED";

    /**
     * Broadcast intent action indicating that the current default dialer has changed.
     * The string extra {@link #EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME} will contain the
     * name of the package that the default dialer was changed to.
     *
     * @see #EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME
     */
    public static final String ACTION_DEFAULT_DIALER_CHANGED = "android.telecom.action.DEFAULT_DIALER_CHANGED";

    /**
     * Activity action: Starts the UI for handing an incoming call. This intent starts the in-call
     * UI by notifying the Telecom system that an incoming call exists for a specific call service
     * (see {@link android.telecom.ConnectionService}). Telecom reads the Intent extras to find
     * and bind to the appropriate {@link android.telecom.ConnectionService} which Telecom will
     * ultimately use to control and get information about the call.
     * <p>
     * Input: get*Extra field {@link #EXTRA_PHONE_ACCOUNT_HANDLE} contains the component name of the
     * {@link android.telecom.ConnectionService} that Telecom should bind to. Telecom will then
     * ask the connection service for more information about the call prior to showing any UI.
     *
     * @deprecated Use {@link #addNewIncomingCall} instead.
     */
    @Deprecated
    public static final String ACTION_INCOMING_CALL = "android.telecom.action.INCOMING_CALL";

    /**
     * {@link android.content.Intent} action used indicate that a new phone account was just
     * registered.
     * <p>
     * The Intent {@link Intent#getExtras() extras} will contain {@link #EXTRA_PHONE_ACCOUNT_HANDLE}
     * to indicate which {@link PhoneAccount} was registered.
     * <p>
     * Will only be sent to the default dialer app (see {@link #getDefaultDialerPackage()}).
     */
    public static final String ACTION_PHONE_ACCOUNT_REGISTERED = "android.telecom.action.PHONE_ACCOUNT_REGISTERED";

    /**
     * {@link android.content.Intent} action used indicate that a phone account was just
     * unregistered.
     * <p>
     * The Intent {@link Intent#getExtras() extras} will contain {@link #EXTRA_PHONE_ACCOUNT_HANDLE}
     * to indicate which {@link PhoneAccount} was unregistered.
     * <p>
     * Will only be sent to the default dialer app (see {@link #getDefaultDialerPackage()}).
     */
    public static final String ACTION_PHONE_ACCOUNT_UNREGISTERED = "android.telecom.action.PHONE_ACCOUNT_UNREGISTERED";

    /**
     * Start an activity indicating that the completion of an outgoing call or an incoming call
     * which was not blocked by the {@link CallScreeningService}, and which was NOT terminated
     * while the call was in {@link Call#STATE_AUDIO_PROCESSING}.
     *
     * The {@link Uri} extra {@link #EXTRA_HANDLE} will contain the uri handle(phone number) for the
     * call which completed.
     *
     * The integer extra {@link #EXTRA_DISCONNECT_CAUSE} will indicate the reason for the call
     * disconnection. See {@link #EXTRA_DISCONNECT_CAUSE} for more information.
     *
     * The integer extra {@link #EXTRA_CALL_DURATION} will indicate the duration of the call. See
     * {@link #EXTRA_CALL_DURATION} for more information.
     */
    public static final String ACTION_POST_CALL = "android.telecom.action.POST_CALL";

    /**
     * The {@link android.content.Intent} action used to show the call accessibility settings page.
     */
    public static final String ACTION_SHOW_CALL_ACCESSIBILITY_SETTINGS = "android.telecom.action.SHOW_CALL_ACCESSIBILITY_SETTINGS";

    /**
     * The {@link android.content.Intent} action used to show the call settings page.
     */
    public static final String ACTION_SHOW_CALL_SETTINGS = "android.telecom.action.SHOW_CALL_SETTINGS";

    /**
     * Broadcast intent action for letting custom component know to show the missed call
     * notification. If no custom component exists then this is sent to the default dialer which
     * should post a missed-call notification.
     */
    public static final String ACTION_SHOW_MISSED_CALLS_NOTIFICATION = "android.telecom.action.SHOW_MISSED_CALLS_NOTIFICATION";

    /**
     * The {@link android.content.Intent} action used to show the respond via SMS settings page.
     */
    public static final String ACTION_SHOW_RESPOND_VIA_SMS_SETTINGS = "android.telecom.action.SHOW_RESPOND_VIA_SMS_SETTINGS";

    /**
     * The dual tone multi-frequency signaling character sent to indicate the dialing system should
     * pause for a predefined period.
     */
    public static final char DTMF_CHARACTER_PAUSE = ',';

    /**
     * The dual-tone multi-frequency signaling character sent to indicate the dialing system should
     * wait for user confirmation before proceeding.
     */
    public static final char DTMF_CHARACTER_WAIT = ';';

    /**
     * A integer value for {@link #EXTRA_CALL_DURATION}, indicates the duration of the completed
     * call was >= 120 seconds.
     */
    public static final int DURATION_LONG = 3;

    /**
     * A integer value for {@link #EXTRA_CALL_DURATION}, indicates the duration of the completed
     * call was >= 60 seconds and < 120 seconds.
     */
    public static final int DURATION_MEDIUM = 2;

    /**
     * A integer value for {@link #EXTRA_CALL_DURATION}, indicates the duration of the completed
     * call was >= 3 seconds and < 60 seconds.
     */
    public static final int DURATION_SHORT = 1;

    /**
     * A integer value for {@link #EXTRA_CALL_DURATION}, indicates the duration of the completed
     * call was < 3 seconds.
     */
    public static final int DURATION_VERY_SHORT = 0;

    /**
     * The number which the party on the other side of the line will see (and use to return the
     * call).
     * <p>
     * {@link ConnectionService}s which interact with {@link RemoteConnection}s should only populate
     * this if the {@link android.telephony.TelephonyManager#getLine1Number()} value, as that is the
     * user's expected caller ID.
     */
    public static final String EXTRA_CALL_BACK_NUMBER = "android.telecom.extra.CALL_BACK_NUMBER";

    /**
     * Optional extra for {@link android.telephony.TelephonyManager#ACTION_PHONE_STATE_CHANGED}
     * containing the disconnect code.
     */
    public static final String EXTRA_CALL_DISCONNECT_CAUSE = "android.telecom.extra.CALL_DISCONNECT_CAUSE";

    /**
     * Optional extra for {@link android.telephony.TelephonyManager#ACTION_PHONE_STATE_CHANGED}
     * containing the disconnect message.
     */
    public static final String EXTRA_CALL_DISCONNECT_MESSAGE = "android.telecom.extra.CALL_DISCONNECT_MESSAGE";

    /**
     * A integer value provided for completed calls to indicate the duration of the call.
     * <p>
     * Allowed values:
     * <ul>
     * <li>{@link #DURATION_VERY_SHORT}</li>
     * <li>{@link #DURATION_SHORT}</li>
     * <li>{@link #DURATION_MEDIUM}</li>
     * <li>{@link #DURATION_LONG}</li>
     * </ul>
     * </p>
     */
    public static final String EXTRA_CALL_DURATION = "android.telecom.extra.CALL_DURATION";

    /**
     * Extra URI that is used by a dialer to query the {@link android.provider.CallLog} content
     * provider and associate a missed call notification with a call log entry.
     */
    public static final String EXTRA_CALL_LOG_URI = "android.telecom.extra.CALL_LOG_URI";

    /**
     * Optional extra for communicating the call network technology used by a
     * {@link android.telecom.Connection} to Telecom and InCallUI.
     *
     * {@code NETWORK_TYPE_*} in {@link android.telephony.TelephonyManager}.
     */
    public static final String EXTRA_CALL_NETWORK_TYPE = "android.telecom.extra.CALL_NETWORK_TYPE";

    /**
     * Optional extra for {@link android.content.Intent#ACTION_CALL} containing a string call
     * subject which will be associated with an outgoing call.  Should only be specified if the
     * {@link PhoneAccount} supports the capability {@link PhoneAccount#CAPABILITY_CALL_SUBJECT}
     * or {@link PhoneAccount#CAPABILITY_CALL_COMPOSER}.
     */
    public static final String EXTRA_CALL_SUBJECT = "android.telecom.extra.CALL_SUBJECT";

    /**
     * Extra value used to provide the package name for {@link #ACTION_CHANGE_DEFAULT_DIALER}.
     */
    public static final String EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME = "android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME";

    /**
     * Extra value used with {@link #ACTION_DEFAULT_CALL_SCREENING_APP_CHANGED} broadcast to
     * indicate the ComponentName of the call screening app which has changed.
     * <p>
     * Note: This extra is NOT used and will be deprecated in the future.
     */
    public static final String EXTRA_DEFAULT_CALL_SCREENING_APP_COMPONENT_NAME = "android.telecom.extra.DEFAULT_CALL_SCREENING_APP_COMPONENT_NAME";

    /**
     * A integer value provided for completed calls to indicate the reason for the call
     * disconnection.
     * <p>
     * Allowed values:
     * <ul>
     * <li>{@link DisconnectCause#UNKNOWN}</li>
     * <li>{@link DisconnectCause#LOCAL}</li>
     * <li>{@link DisconnectCause#REMOTE}</li>
     * <li>{@link DisconnectCause#REJECTED}</li>
     * <li>{@link DisconnectCause#MISSED}</li>
     * </ul>
     * </p>
     */
    public static final String EXTRA_DISCONNECT_CAUSE = "android.telecom.extra.DISCONNECT_CAUSE";

    /**
     * A {@link Uri} extra, which when set on the {@link #ACTION_POST_CALL} intent, indicates the
     * uri handle(phone number) of the completed call.
     */
    public static final String EXTRA_HANDLE = "android.telecom.extra.HANDLE";

    /**
     * A boolean extra set on incoming calls to indicate that the call has a picture specified.
     * Given that image download could take a (short) time, the EXTRA is set immediately upon
     * adding the call to the Dialer app, this allows the Dialer app to reserve space for an image
     * if one is expected. The EXTRA may be unset if the image download ends up failing for some
     * reason.
     */
    public static final String EXTRA_HAS_PICTURE = "android.telecom.extra.HAS_PICTURE";

    /**
     * The extra used by a {@link ConnectionService} to provide the handle of the caller that
     * has initiated a new incoming call.
     */
    public static final String EXTRA_INCOMING_CALL_ADDRESS = "android.telecom.extra.INCOMING_CALL_ADDRESS";

    /**
     * Optional extra for {@link #ACTION_INCOMING_CALL} containing a {@link Bundle} which contains
     * metadata about the call. This {@link Bundle} will be returned to the
     * {@link ConnectionService}.
     */
    public static final String EXTRA_INCOMING_CALL_EXTRAS = "android.telecom.extra.INCOMING_CALL_EXTRAS";

    /**
     * Optional extra for {@link #addNewIncomingCall(PhoneAccountHandle, Bundle)} containing an
     * integer that determines the requested video state for an incoming call.
     * Valid options:
     * {@link VideoProfile#STATE_AUDIO_ONLY},
     * {@link VideoProfile#STATE_BIDIRECTIONAL},
     * {@link VideoProfile#STATE_RX_ENABLED},
     * {@link VideoProfile#STATE_TX_ENABLED}.
     */
    public static final String EXTRA_INCOMING_VIDEO_STATE = "android.telecom.extra.INCOMING_VIDEO_STATE";

    /**
     * Extra value used with {@link #ACTION_DEFAULT_CALL_SCREENING_APP_CHANGED} broadcast to
     * indicate whether an app is the default call screening app.
     * <p>
     * Note: This extra is NOT used and will be deprecated in the future.
     */
    public static final String EXTRA_IS_DEFAULT_CALL_SCREENING_APP = "android.telecom.extra.IS_DEFAULT_CALL_SCREENING_APP";

    /**
     * Extra for the call composer call location, an {@link android.location.Location} parcelable
     * class to represent the geolocation as a latitude and longitude pair.
     *
     * Reference: RCC.20 Section 2.4.3.2
     */
    public static final String EXTRA_LOCATION = "android.telecom.extra.LOCATION";

    /**
     * The number of calls associated with the notification. If the number is zero then the missed
     * call notification should be dismissed.
     */
    public static final String EXTRA_NOTIFICATION_COUNT = "android.telecom.extra.NOTIFICATION_COUNT";

    /**
     * The number associated with the missed calls. This number is only relevant
     * when EXTRA_NOTIFICATION_COUNT is 1.
     */
    public static final String EXTRA_NOTIFICATION_PHONE_NUMBER = "android.telecom.extra.NOTIFICATION_PHONE_NUMBER";

    /**
     * Optional extra for {@link android.content.Intent#ACTION_CALL} and
     * {@link android.content.Intent#ACTION_DIAL} {@code Intent} containing a {@link Bundle}
     * which contains metadata about the call. This {@link Bundle} will be saved into
     * {@code Call.Details} and passed to the {@link ConnectionService} when placing the call.
     */
    public static final String EXTRA_OUTGOING_CALL_EXTRAS = "android.telecom.extra.OUTGOING_CALL_EXTRAS";

    /**
     * A ParcelUuid used as a token to represent a picture that was uploaded prior to the call
     * being placed. The value of this extra should be set using the {@link android.os.ParcelUuid}
     * obtained from the callback in {@link TelephonyManager#uploadCallComposerPicture}.
     */
    public static final String EXTRA_OUTGOING_PICTURE = "android.telecom.extra.OUTGOING_PICTURE";

    /**
     * The extra used with an {@link android.content.Intent#ACTION_CALL} and
     * {@link android.content.Intent#ACTION_DIAL} {@code Intent} to specify a
     * {@link PhoneAccountHandle} to use when making the call.
     * <p class="note">
     * Retrieve with {@link android.content.Intent#getParcelableExtra(String)}.
     */
    public static final String EXTRA_PHONE_ACCOUNT_HANDLE = "android.telecom.extra.PHONE_ACCOUNT_HANDLE";

    /**
     * A {@link Uri} representing the picture that was downloaded when a call is received or
     * uploaded when a call is placed.
     *
     * This is a content URI within the call log provider which can be used to open a file
     * descriptor. This could be set a short time after a call is added to the Dialer app if the
     * download/upload is delayed for some reason. The Dialer app will receive a callback via
     * {@link Call.Callback#onDetailsChanged} when this value has changed.
     *
     * Reference: RCC.20 Section 2.4.3.2
     */
    public static final String EXTRA_PICTURE_URI = "android.telecom.extra.PICTURE_URI";

    /**
     * Extra for the call composer call priority, either {@link #PRIORITY_NORMAL} or
     * {@link #PRIORITY_URGENT}.
     *
     * Reference: RCC.20 Section 2.4.4.2
     */
    public static final String EXTRA_PRIORITY = "android.telecom.extra.PRIORITY";

    /**
     * A boolean extra, which when set on the {@link Intent#ACTION_CALL} intent or on the bundle
     * passed into {@link #placeCall(Uri, Bundle)}, indicates that the call should be initiated with
     * an RTT session open. See {@link android.telecom.Call.RttCall} for more information on RTT.
     */
    public static final String EXTRA_START_CALL_WITH_RTT = "android.telecom.extra.START_CALL_WITH_RTT";

    /**
     * Optional extra for {@link android.content.Intent#ACTION_CALL} containing a boolean that
     * determines whether the speakerphone should be automatically turned on for an outgoing call.
     */
    public static final String EXTRA_START_CALL_WITH_SPEAKERPHONE = "android.telecom.extra.START_CALL_WITH_SPEAKERPHONE";

    /**
     * Optional extra for {@link android.content.Intent#ACTION_CALL} containing an integer that
     * determines the desired video state for an outgoing call.
     * Valid options:
     * {@link VideoProfile#STATE_AUDIO_ONLY},
     * {@link VideoProfile#STATE_BIDIRECTIONAL},
     * {@link VideoProfile#STATE_RX_ENABLED},
     * {@link VideoProfile#STATE_TX_ENABLED}.
     */
    public static final String EXTRA_START_CALL_WITH_VIDEO_STATE = "android.telecom.extra.START_CALL_WITH_VIDEO_STATE";

    /**
     * The boolean indicated by this extra controls whether or not a call is eligible to undergo
     * assisted dialing. This extra is stored under {@link #EXTRA_OUTGOING_CALL_EXTRAS}.
     * <p>
     * The call initiator can use this extra to indicate that a call used assisted dialing to help
     * place the call.  This is most commonly used by a Dialer app which provides the ability to
     * automatically add dialing prefixes when placing international calls.
     * <p>
     * Setting this extra on the outgoing call extras will cause the
     * {@link Connection#PROPERTY_ASSISTED_DIALING} property and
     * {@link Call.Details#PROPERTY_ASSISTED_DIALING} property to be set on the
     * {@link Connection}/{@link Call} in question.  When the call is logged to the call log, the
     * {@link android.provider.CallLog.Calls#FEATURES_ASSISTED_DIALING_USED} call feature is set to
     * indicate that assisted dialing was used for the call.
     */
    public static final String EXTRA_USE_ASSISTED_DIALING = "android.telecom.extra.USE_ASSISTED_DIALING";

    /**
     * An optional {@link android.content.Intent#ACTION_CALL} intent extra corresponding to the
     * original address to dial for the call. This is used when an alternative gateway address is
     * provided to recall the original address.
     * The value is a {@link android.net.Uri}.
     *
     * (See {@link #GATEWAY_PROVIDER_PACKAGE} for details)
     */
    public static final String GATEWAY_ORIGINAL_ADDRESS = "android.telecom.extra.GATEWAY_ORIGINAL_ADDRESS";

    /**
     * An optional {@link android.content.Intent#ACTION_CALL} intent extra denoting the
     * package name of the app specifying an alternative gateway for the call.
     * The value is a string.
     *
     * (The following comment corresponds to the all GATEWAY_* extras)
     * An app which sends the {@link android.content.Intent#ACTION_CALL} intent can specify an
     * alternative address to dial which is different from the one specified and displayed to
     * the user. This alternative address is referred to as the gateway address.
     */
    public static final String GATEWAY_PROVIDER_PACKAGE = "android.telecom.extra.GATEWAY_PROVIDER_PACKAGE";

    /**
     * A boolean meta-data value indicating whether an {@link InCallService} wants to be informed of
     * calls which have the {@link Call.Details#PROPERTY_IS_EXTERNAL_CALL} property.  An external
     * call is one which a {@link ConnectionService} knows about, but is not connected to directly.
     * Dialer implementations (see {@link #getDefaultDialerPackage()}) which would like to be
     * informed of external calls should set this meta-data to {@code true} in the manifest
     * registration of their {@link InCallService}.  By default, the {@link InCallService} will NOT
     * be informed of external calls.
     */
    public static final String METADATA_INCLUDE_EXTERNAL_CALLS = "android.telecom.INCLUDE_EXTERNAL_CALLS";

    /**
     * A boolean meta-data value indicating whether an {@link InCallService} wants to be informed of
     * calls which have the {@link Call.Details#PROPERTY_SELF_MANAGED} property.  A self-managed
     * call is one which originates from a self-managed {@link ConnectionService} which has chosen
     * to implement its own call user interface.  An {@link InCallService} implementation which
     * would like to be informed of external calls should set this meta-data to {@code true} in the
     * manifest registration of their {@link InCallService}.  By default, the {@link InCallService}
     * will NOT be informed about self-managed calls.
     * <p>
     * An {@link InCallService} which receives self-managed calls is free to view and control the
     * state of calls in the self-managed {@link ConnectionService}.  An example use-case is
     * exposing these calls to an automotive device via its companion app.
     * <p>
     * See also {@link Connection#PROPERTY_SELF_MANAGED}.
     */
    public static final String METADATA_INCLUDE_SELF_MANAGED_CALLS = "android.telecom.INCLUDE_SELF_MANAGED_CALLS";

    /**
     * A boolean meta-data value indicating whether an {@link InCallService} implements an
     * in-call user interface to be used while the device is in car-mode (see
     * {@link android.content.res.Configuration#UI_MODE_TYPE_CAR}).
     */
    public static final String METADATA_IN_CALL_SERVICE_CAR_MODE_UI = "android.telecom.IN_CALL_SERVICE_CAR_MODE_UI";

    /**
     * A boolean meta-data value indicating whether an {@link InCallService} implements ringing.
     * Dialer implementations (see {@link #getDefaultDialerPackage()}) which would also like to
     * override the system provided ringing should set this meta-data to {@code true} in the
     * manifest registration of their {@link InCallService}.
     * <p>
     * When {@code true}, it is the {@link InCallService}'s responsibility to play a ringtone for
     * all incoming calls.
     */
    public static final String METADATA_IN_CALL_SERVICE_RINGING = "android.telecom.IN_CALL_SERVICE_RINGING";

    /**
     * A boolean meta-data value indicating whether an {@link InCallService} implements an
     * in-call user interface. Dialer implementations (see {@link #getDefaultDialerPackage()}) which
     * would also like to replace the in-call interface should set this meta-data to {@code true} in
     * the manifest registration of their {@link InCallService}.
     */
    public static final String METADATA_IN_CALL_SERVICE_UI = "android.telecom.IN_CALL_SERVICE_UI";

    /**
     * Indicates that the address or number of a call is allowed to be displayed for caller ID.
     */
    public static final int PRESENTATION_ALLOWED = 1;

    /**
     * Indicates that the address or number of a call belongs to a pay phone.
     */
    public static final int PRESENTATION_PAYPHONE = 4;

    /**
     * Indicates that the address or number of a call is blocked by the other party.
     */
    public static final int PRESENTATION_RESTRICTED = 2;

    /**
     * Indicates that the address or number of a call is unavailable.
     */
    public static final int PRESENTATION_UNAVAILABLE = 5;

    /**
     * Indicates that the address or number of a call is not specified or known by the carrier.
     */
    public static final int PRESENTATION_UNKNOWN = 3;

    /**
     * Indicates the call composer call priority is normal.
     *
     * Reference: RCC.20 Section 2.4.4.2
     */
    public static final int PRIORITY_NORMAL = 0;

    /**
     * Indicates the call composer call priority is urgent.
     *
     * Reference: RCC.20 Section 2.4.4.2
     */
    public static final int PRIORITY_URGENT = 1;

    private TelecomManager() {
    }
}
