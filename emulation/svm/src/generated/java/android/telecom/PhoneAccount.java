package android.telecom;

import android.os.Parcelable;

/**
 * Represents a distinct method to place or receive a phone call. Apps which can place calls and
 * want those calls to be integrated into the dialer and in-call UI should build an instance of
 * this class and register it with the system using {@link TelecomManager}.
 * <p>
 * {@link TelecomManager} uses registered {@link PhoneAccount}s to present the user with
 * alternative options when placing a phone call. When building a {@link PhoneAccount}, the app
 * should supply a valid {@link PhoneAccountHandle} that references the connection service
 * implementation Telecom will use to interact with the app.
 */
public final class PhoneAccount implements Parcelable {
    /**
     * An adhoc conference call is established by providing a list of addresses to
     * {@code TelecomManager#startConference(List<Uri>, int videoState)} where the
     * {@link ConnectionService} is responsible for connecting all indicated participants
     * to a conference simultaneously.
     * This is in contrast to conferences formed by merging calls together (e.g. using
     * {@link android.telecom.Call#mergeConference()}).
     */
    public static final int CAPABILITY_ADHOC_CONFERENCE_CALLING = 16384;

    /**
     * Flag indicating whether this {@link PhoneAccount} is capable of supporting the call composer
     * functionality for enriched calls.
     */
    public static final int CAPABILITY_CALL_COMPOSER = 32768;

    /**
     * Flag indicating that this {@code PhoneAccount} can make phone calls in place of
     * traditional SIM-based telephony calls. This account will be treated as a distinct method
     * for placing calls alongside the traditional SIM-based telephony stack. This flag is
     * distinct from {@link #CAPABILITY_CONNECTION_MANAGER} in that it is not allowed to manage
     * or place calls from the built-in telephony stack.
     * <p>
     * See {@link #getCapabilities}
     * <p>
     */
    public static final int CAPABILITY_CALL_PROVIDER = 2;

    /**
     * Flag indicating that this {@code PhoneAccount} supports a subject for Calls.  This means a
     * caller is able to specify a short subject line for an outgoing call.  A capable receiving
     * device displays the call subject on the incoming call screen.
     * <p>
     * See {@link #getCapabilities}
     */
    public static final int CAPABILITY_CALL_SUBJECT = 64;

    /**
     * Flag indicating that this {@code PhoneAccount} can act as a connection manager for
     * other connections. The {@link ConnectionService} associated with this {@code PhoneAccount}
     * will be allowed to manage phone calls including using its own proprietary phone-call
     * implementation (like VoIP calling) to make calls instead of the telephony stack.
     * <p>
     * When a user opts to place a call using the SIM-based telephony stack, the
     * {@link ConnectionService} associated with this {@code PhoneAccount} will be attempted first
     * if the user has explicitly selected it to be used as the default connection manager.
     * <p>
     * See {@link #getCapabilities}
     */
    public static final int CAPABILITY_CONNECTION_MANAGER = 1;

    /**
     * Flag indicating that this {@code PhoneAccount} is capable of placing emergency calls.
     * By default all PSTN {@code PhoneAccount}s are capable of placing emergency calls.
     * <p>
     * See {@link #getCapabilities}
     */
    public static final int CAPABILITY_PLACE_EMERGENCY_CALLS = 16;

    /**
     * Flag indicating that this {@link PhoneAccount} is capable of making a call with an
     * RTT (Real-time text) session.
     * When set, Telecom will attempt to open an RTT session on outgoing calls that specify
     * that they should be placed with an RTT session , and the in-call app will be displayed
     * with text entry fields for RTT. Likewise, the in-call app can request that an RTT
     * session be opened during a call if this bit is set.
     */
    public static final int CAPABILITY_RTT = 4096;

    /**
     * Flag indicating that this {@link PhoneAccount} is responsible for managing its own
     * {@link Connection}s.  This type of {@link PhoneAccount} is ideal for use with standalone
     * calling apps which do not wish to use the default phone app for {@link Connection} UX,
     * but which want to leverage the call and audio routing capabilities of the Telecom framework.
     * <p>
     * When set, {@link Connection}s created by the self-managed {@link ConnectionService} will not
     * be surfaced to implementations of the {@link InCallService} API.  Thus it is the
     * responsibility of a self-managed {@link ConnectionService} to provide a user interface for
     * its {@link Connection}s.
     * <p>
     * Self-managed {@link Connection}s will, however, be displayed on connected Bluetooth devices.
     */
    public static final int CAPABILITY_SELF_MANAGED = 2048;

    /**
     * Flag indicating that this {@code PhoneAccount} represents a built-in PSTN SIM
     * subscription.
     * <p>
     * Only the Android framework can register a {@code PhoneAccount} having this capability.
     * <p>
     * See {@link #getCapabilities}
     */
    public static final int CAPABILITY_SIM_SUBSCRIPTION = 4;

    /**
     * Flag indicating that this voip app {@link PhoneAccount} supports the call streaming session
     * to stream call audio to another remote device via streaming app.
     *
     * @see #getCapabilities
     */
    public static final int CAPABILITY_SUPPORTS_CALL_STREAMING = 524288;

    /**
     * Flag indicating that this {@link PhoneAccount} supports the use TelecomManager APIs that
     * utilize {@link android.os.OutcomeReceiver}s or {@link java.util.function.Consumer}s.
     * Be aware, if this capability is set, {@link #CAPABILITY_SELF_MANAGED} will be amended by
     * Telecom when this {@link PhoneAccount} is registered via
     * {@link TelecomManager#registerPhoneAccount(PhoneAccount)}.
     *
     * <p>
     * {@link android.os.OutcomeReceiver}s and {@link java.util.function.Consumer}s represent
     * transactional operations because the operation can succeed or fail.  An app wishing to use
     * transactional operations should define behavior for a successful and failed TelecomManager
     * API call.
     *
     * @see #CAPABILITY_SELF_MANAGED
     * @see #getCapabilities
     */
    public static final int CAPABILITY_SUPPORTS_TRANSACTIONAL_OPERATIONS = 262144;

    /**
     * Flag indicating that this {@link PhoneAccount} supports video calling.
     * This is not an indication that the {@link PhoneAccount} is currently able to make a video
     * call, but rather that it has the ability to make video calls (but not necessarily at this
     * time).
     * <p>
     * Whether a {@link PhoneAccount} can make a video call is ultimately controlled by
     * {@link #CAPABILITY_VIDEO_CALLING}, which indicates whether the {@link PhoneAccount} is
     * currently capable of making a video call.  Consider a case where, for example, a
     * {@link PhoneAccount} supports making video calls (e.g.
     * {@link #CAPABILITY_SUPPORTS_VIDEO_CALLING}), but a current lack of network connectivity
     * prevents video calls from being made (e.g. {@link #CAPABILITY_VIDEO_CALLING}).
     * <p>
     * See {@link #getCapabilities}
     */
    public static final int CAPABILITY_SUPPORTS_VIDEO_CALLING = 1024;

    /**
     * Flag indicating that this {@link PhoneAccount} provides SIM-based voice calls, potentially as
     * an over-the-top solution such as wi-fi calling.
     *
     * <p>Similar to {@link #CAPABILITY_SUPPORTS_VIDEO_CALLING}, this capability indicates this
     * {@link PhoneAccount} has the ability to make voice calls (but not necessarily at this time).
     * Whether this {@link PhoneAccount} can make a voice call is ultimately controlled by {@link
     * #CAPABILITY_VOICE_CALLING_AVAILABLE}, which indicates whether this {@link PhoneAccount} is
     * currently capable of making a voice call. Consider a case where, for example, a {@link
     * PhoneAccount} supports making voice calls (e.g. {@link
     * #CAPABILITY_SUPPORTS_VOICE_CALLING_INDICATIONS}), but a current lack of network connectivity
     * prevents voice calls from being made (e.g. {@link #CAPABILITY_VOICE_CALLING_AVAILABLE}).
     *
     * <p>In order to declare this capability, this {@link PhoneAccount} must also declare {@link
     * #CAPABILITY_SIM_SUBSCRIPTION} or {@link #CAPABILITY_CONNECTION_MANAGER} and satisfy the
     * associated requirements.
     *
     * @see #CAPABILITY_VOICE_CALLING_AVAILABLE
     * @see #getCapabilities
     */
    public static final int CAPABILITY_SUPPORTS_VOICE_CALLING_INDICATIONS = 65536;

    /**
     * Flag indicating that this {@code PhoneAccount} is currently able to place video calls.
     * <p>
     * See also {@link #CAPABILITY_SUPPORTS_VIDEO_CALLING} which indicates whether the
     * {@code PhoneAccount} supports placing video calls.
     * <p>
     * See {@link #getCapabilities}
     */
    public static final int CAPABILITY_VIDEO_CALLING = 8;

    /**
     * Flag indicating that for this {@code PhoneAccount}, the ability to make a video call to a
     * number relies on presence.  Should only be set if the {@code PhoneAccount} also has
     * {@link #CAPABILITY_VIDEO_CALLING}.
     * <p>
     * Note: As of Android 12, using the
     * {@link android.provider.ContactsContract.Data#CARRIER_PRESENCE_VT_CAPABLE} bit on the
     * {@link android.provider.ContactsContract.Data#CARRIER_PRESENCE} column to indicate whether
     * a contact's phone number supports video calling has been deprecated and should only be used
     * on devices where {@link CarrierConfigManager#KEY_USE_RCS_PRESENCE_BOOL} is set. On newer
     * devices, applications must use {@link android.telephony.ims.RcsUceAdapter} instead to
     * determine whether or not a contact's phone number supports carrier video calling.
     * <p>
     * See {@link #getCapabilities}
     */
    public static final int CAPABILITY_VIDEO_CALLING_RELIES_ON_PRESENCE = 256;

    /**
     * Flag indicating that this {@link PhoneAccount} is <em>currently</em> able to place SIM-based
     * voice calls, similar to {@link #CAPABILITY_VIDEO_CALLING}.
     *
     * <p>See also {@link #CAPABILITY_SUPPORTS_VOICE_CALLING_INDICATIONS}, which indicates whether
     * the {@code PhoneAccount} supports placing SIM-based voice calls or not.
     *
     * <p>In order to declare this capability, this {@link PhoneAccount} must also declare {@link
     * #CAPABILITY_SIM_SUBSCRIPTION} or {@link #CAPABILITY_CONNECTION_MANAGER} and satisfy the
     * associated requirements.
     *
     * @see #CAPABILITY_SUPPORTS_VOICE_CALLING_INDICATIONS
     * @see #getCapabilities
     */
    public static final int CAPABILITY_VOICE_CALLING_AVAILABLE = 131072;

    /**
     * Boolean {@link PhoneAccount} extras key (see {@link PhoneAccount#getExtras()}) which
     * indicates whether a Self-managed {@link PhoneAccount} want to expose its calls to all
     * {@link InCallService} which declares the metadata
     * {@link TelecomManager#METADATA_INCLUDE_SELF_MANAGED_CALLS}.
     */
    public static final String EXTRA_ADD_SELF_MANAGED_CALLS_TO_INCALLSERVICE = "android.telecom.extra.ADD_SELF_MANAGED_CALLS_TO_INCALLSERVICE";

    /**
     * Boolean {@link PhoneAccount} extras key (see {@link PhoneAccount#getExtras()}) which
     * indicates that all calls from this {@link PhoneAccount} should be treated as VoIP calls
     * rather than cellular calls by the Telecom audio handling logic.
     */
    public static final String EXTRA_ALWAYS_USE_VOIP_AUDIO_MODE = "android.telecom.extra.ALWAYS_USE_VOIP_AUDIO_MODE";

    /**
     * {@link PhoneAccount} extras key (see {@link PhoneAccount#getExtras()}) which determines the
     * character encoding to be used when determining the length of messages.
     * The user interface can use this when determining the number of characters the user may type
     * in a call subject.  If empty-string, the call subject message size limit will be enforced on
     * a 1:1 basis.  That is, each character will count towards the messages size limit as a single
     * character.  If a character encoding is specified, the message size limit will be based on the
     * number of bytes in the message per the specified encoding.  See
     * {@link #EXTRA_CALL_SUBJECT_MAX_LENGTH} for more information on the call subject maximum
     * length.
     */
    public static final String EXTRA_CALL_SUBJECT_CHARACTER_ENCODING = "android.telecom.extra.CALL_SUBJECT_CHARACTER_ENCODING";

    /**
     * {@link PhoneAccount} extras key (see {@link PhoneAccount#getExtras()}) which determines the
     * maximum permitted length of a call subject specified via the
     * {@link TelecomManager#EXTRA_CALL_SUBJECT} extra on an
     * {@link android.content.Intent#ACTION_CALL} intent.  Ultimately a {@link ConnectionService} is
     * responsible for enforcing the maximum call subject length when sending the message, however
     * this extra is provided so that the user interface can proactively limit the length of the
     * call subject as the user types it.
     */
    public static final String EXTRA_CALL_SUBJECT_MAX_LENGTH = "android.telecom.extra.CALL_SUBJECT_MAX_LENGTH";

    /**
     * Boolean {@link PhoneAccount} extras key (see {@link PhoneAccount#getExtras()}) which
     * indicates whether a Self-Managed {@link PhoneAccount} should log its calls to the call log.
     * Self-Managed {@link PhoneAccount}s are responsible for their own notifications, so the system
     * will not create a notification when a missed call is logged.
     * <p>
     * By default, Self-Managed {@link PhoneAccount}s do not log their calls to the call log.
     * Setting this extra to {@code true} provides a means for them to log their calls.
     * <p>
     * Note: Only calls where the {@link Call.Details#getHandle()} {@link Uri#getScheme()} is
     * {@link #SCHEME_SIP} or {@link #SCHEME_TEL} will be logged at the current time.
     */
    public static final String EXTRA_LOG_SELF_MANAGED_CALLS = "android.telecom.extra.LOG_SELF_MANAGED_CALLS";

    /**
     * Boolean {@link PhoneAccount} extras key (see {@link PhoneAccount#getExtras()}) which
     * indicates whether this {@link PhoneAccount} is capable of supporting a request to handover a
     * connection from this {@link PhoneAccount} to another {@link PhoneAccount}.
     * (see {@code android.telecom.Call#handoverTo()}) which specifies
     * {@link #EXTRA_SUPPORTS_HANDOVER_TO}.
     * <p>
     * A handover request is initiated by the user from the default dialer app to indicate a desire
     * to handover a call from one {@link PhoneAccount}/{@link ConnectionService} to another.
     */
    public static final String EXTRA_SUPPORTS_HANDOVER_FROM = "android.telecom.extra.SUPPORTS_HANDOVER_FROM";

    /**
     * Boolean {@link PhoneAccount} extras key (see {@link PhoneAccount#getExtras()}) which
     * indicates whether this {@link PhoneAccount} is capable of supporting a request to handover a
     * connection (see {@code android.telecom.Call#handoverTo()}) to this {@link PhoneAccount} from
     * a {@link PhoneAccount} specifying {@link #EXTRA_SUPPORTS_HANDOVER_FROM}.
     * <p>
     * A handover request is initiated by the user from the default dialer app to indicate a desire
     * to handover a call from one {@link PhoneAccount}/{@link ConnectionService} to another.
     */
    public static final String EXTRA_SUPPORTS_HANDOVER_TO = "android.telecom.extra.SUPPORTS_HANDOVER_TO";

    /**
     * Indicating no hightlight color is set.
     */
    public static final int NO_HIGHLIGHT_COLOR = 0;

    /**
     * Indicating no resource ID is set.
     */
    public static final int NO_RESOURCE_ID = -1;

    /**
     * URI scheme for SIP URIs.
     */
    public static final String SCHEME_SIP = "sip";

    /**
     * URI scheme for telephone number URIs.
     */
    public static final String SCHEME_TEL = "tel";

    /**
     * URI scheme for voicemail URIs.
     */
    public static final String SCHEME_VOICEMAIL = "voicemail";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private PhoneAccount() {
    }
}
