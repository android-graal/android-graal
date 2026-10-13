package android.telecom;

/**
 * Represents an ongoing phone call that the in-call app should present to the user.
 */
public final class Call {
    /**
     * The key to retrieve the optional {@code PhoneAccount}s Telecom can bundle with its Call
     * extras. Used to pass the phone accounts to display on the front end to the user in order to
     * select phone accounts to (for example) place a call.
     * @deprecated Use the list from {@link #EXTRA_SUGGESTED_PHONE_ACCOUNTS} instead.
     */
    @Deprecated
    public static final String AVAILABLE_PHONE_ACCOUNTS = "selectPhoneAccountAccounts";

    /**
     * Event reported from the telecom framework when a diagnostic message previously raised with
     * {@link #EVENT_DISPLAY_DIAGNOSTIC_MESSAGE} has cleared and is no longer pertinent.
     * <p>
     * The {@link #EXTRA_DIAGNOSTIC_MESSAGE_ID} indicates the diagnostic message which has been
     * cleared.
     * <p>
     * The dialer app receives this event via
     * {@link Call.Callback#onConnectionEvent(Call, String, Bundle)}.
     */
    public static final String EVENT_CLEAR_DIAGNOSTIC_MESSAGE = "android.telecom.event.CLEAR_DIAGNOSTIC_MESSAGE";

    /**
     * Event reported from the Telecom stack to report an in-call diagnostic message which the
     * dialer app may opt to display to the user.  A diagnostic message is used to communicate
     * scenarios the device has detected which may impact the quality of the ongoing call.
     * <p>
     * For example a problem with a bluetooth headset may generate a recommendation for the user to
     * try using the speakerphone instead, or if the device detects it has entered a poor service
     * area, the user might be warned so that they can finish their call prior to it dropping.
     * <p>
     * A diagnostic message is considered persistent in nature.  When the user enters a poor service
     * area, for example, the accompanying diagnostic message persists until they leave the area
     * of poor service.  Each message is accompanied with a {@link #EXTRA_DIAGNOSTIC_MESSAGE_ID}
     * which uniquely identifies the diagnostic condition being reported.  The framework raises a
     * call event of type {@link #EVENT_CLEAR_DIAGNOSTIC_MESSAGE} when the condition reported has
     * been cleared.  The dialer app should display the diagnostic message until it is cleared.
     * If multiple diagnostic messages are sent with different IDs (which have not yet been cleared)
     * the dialer app should prioritize the most recently received message, but still provide the
     * user with a means to review past messages.
     * <p>
     * The text of the message is found in {@link #EXTRA_DIAGNOSTIC_MESSAGE} in the form of a human
     * readable {@link CharSequence} which is intended for display in the call UX.
     * <p>
     * The telecom framework audibly notifies the user of the presence of a diagnostic message, so
     * the dialer app needs only to concern itself with visually displaying the message.
     * <p>
     * The dialer app receives this event via
     * {@link Call.Callback#onConnectionEvent(Call, String, Bundle)}.
     */
    public static final String EVENT_DISPLAY_DIAGNOSTIC_MESSAGE = "android.telecom.event.DISPLAY_DIAGNOSTIC_MESSAGE";

    /**
     * String value indicating the asserted display name reported via
     * ImsCallProfile#EXTRA_ASSERTED_DISPLAY_NAME.
     *
     * {@link Connection#setExtras(Bundle)} or {@link Connection#putExtras(Bundle)}
     * should be used to notify Telecom this extra has been set.
     */
    public static final String EXTRA_ASSERTED_DISPLAY_NAME = "android.telecom.extra.ASSERTED_DISPLAY_NAME";

    /**
     * {@link CharSequence} extra used with {@link #EVENT_DISPLAY_DIAGNOSTIC_MESSAGE}.  This is the
     * diagnostic message the dialer app should display.
     */
    public static final String EXTRA_DIAGNOSTIC_MESSAGE = "android.telecom.extra.DIAGNOSTIC_MESSAGE";

    /**
     * Integer extra representing a message ID for a message posted via
     * {@link #EVENT_DISPLAY_DIAGNOSTIC_MESSAGE}.  Used to ensure that the dialer app knows when
     * the message in question has cleared via {@link #EVENT_CLEAR_DIAGNOSTIC_MESSAGE}.
     */
    public static final String EXTRA_DIAGNOSTIC_MESSAGE_ID = "android.telecom.extra.DIAGNOSTIC_MESSAGE_ID";

    /**
     * Boolean indicating that the call is a verified business call.
     *
     * {@link Connection#setExtras(Bundle)} or {@link Connection#putExtras(Bundle)}
     * should be used to notify Telecom this extra has been set.
     */
    public static final String EXTRA_IS_BUSINESS_CALL = "android.telecom.extra.IS_BUSINESS_CALL";

    /**
     * Extra key intended for {@link InCallService}s that notify the user of an incoming call. When
     * EXTRA_IS_SUPPRESSED_BY_DO_NOT_DISTURB returns true, the {@link InCallService} should not
     * interrupt the user of the incoming call because the call is being suppressed by Do Not
     * Disturb settings.
     *
     * This extra will be removed from the {@link Call} object for {@link InCallService}s that do
     * not hold the {@link android.Manifest.permission#READ_CONTACTS} permission.
     */
    public static final String EXTRA_IS_SUPPRESSED_BY_DO_NOT_DISTURB = "android.telecom.extra.IS_SUPPRESSED_BY_DO_NOT_DISTURB";

    /**
     * Extra key used to indicate the time (in milliseconds since midnight, January 1, 1970 UTC)
     * when the last outgoing emergency call was made.  This is used to identify potential emergency
     * callbacks.
     */
    public static final String EXTRA_LAST_EMERGENCY_CALLBACK_TIME_MILLIS = "android.telecom.extra.LAST_EMERGENCY_CALLBACK_TIME_MILLIS";

    /**
     * Extra key used to indicate whether a {@link CallScreeningService} has requested to silence
     * the ringtone for a call.  If the {@link InCallService} declares
     * {@link TelecomManager#METADATA_IN_CALL_SERVICE_RINGING} in its manifest, it should not
     * play a ringtone for an incoming call with this extra key set.
     */
    public static final String EXTRA_SILENT_RINGING_REQUESTED = "android.telecom.extra.SILENT_RINGING_REQUESTED";

    /**
     * Key for extra used to pass along a list of {@link PhoneAccountSuggestion}s to the in-call
     * UI when a call enters the {@link #STATE_SELECT_PHONE_ACCOUNT} state. The list included here
     * will have the same length and be in the same order as the list passed with
     * {@link #AVAILABLE_PHONE_ACCOUNTS}.
     */
    public static final String EXTRA_SUGGESTED_PHONE_ACCOUNTS = "android.telecom.extra.SUGGESTED_PHONE_ACCOUNTS";

    /**
     * Reject reason used with {@link #reject(int)} to indicate that the user is rejecting this
     * call because they have declined to answer it.  This typically means that they are unable
     * to answer the call at this time and would prefer it be sent to voicemail.
     */
    public static final int REJECT_REASON_DECLINED = 1;

    /**
     * Reject reason used with {@link #reject(int)} to indicate that the user is rejecting this
     * call because it is an unwanted call.  This allows the user to indicate that they are
     * rejecting a call because it is likely a nuisance call.
     */
    public static final int REJECT_REASON_UNWANTED = 2;

    /**
     * The state of a {@code Call} when actively supporting conversation.
     */
    public static final int STATE_ACTIVE = 4;

    /**
     * The state of a call that is active with the network, but the audio from the call is
     * being intercepted by an app on the local device. Telecom does not hold audio focus in this
     * state, and the call will be invisible to the user except for a persistent notification.
     */
    public static final int STATE_AUDIO_PROCESSING = 12;

    /**
     * The initial state of an outgoing {@code Call}.
     * Common transitions are to {@link #STATE_DIALING} state for a successful call or
     * {@link #STATE_DISCONNECTED} if it failed.
     */
    public static final int STATE_CONNECTING = 9;

    /**
     * The state of an outgoing {@code Call} when dialing the remote number, but not yet connected.
     */
    public static final int STATE_DIALING = 1;

    /**
     * The state of a {@code Call} when no further voice or other communication is being
     * transmitted, the remote side has been or will inevitably be informed that the {@code Call}
     * is no longer active, and the local data transport has or inevitably will release resources
     * associated with this {@code Call}.
     */
    public static final int STATE_DISCONNECTED = 7;

    /**
     * The state of a {@code Call} when the user has initiated a disconnection of the call, but the
     * call has not yet been disconnected by the underlying {@code ConnectionService}.  The next
     * state of the call is (potentially) {@link #STATE_DISCONNECTED}.
     */
    public static final int STATE_DISCONNECTING = 10;

    /**
     * The state of a {@code Call} when in a holding state.
     */
    public static final int STATE_HOLDING = 3;

    /**
     * The state of a {@code Call} when newly created.
     */
    public static final int STATE_NEW = 0;

    /**
     * The state of an external call which is in the process of being pulled from a remote device to
     * the local device.
     * <p>
     * A call can only be in this state if the {@link Details#PROPERTY_IS_EXTERNAL_CALL} property
     * and {@link Details#CAPABILITY_CAN_PULL_CALL} capability are set on the call.
     * <p>
     * An {@link InCallService} will only see this state if it has the
     * {@link TelecomManager#METADATA_INCLUDE_EXTERNAL_CALLS} metadata set to {@code true} in its
     * manifest.
     */
    public static final int STATE_PULLING_CALL = 11;

    /**
     * The state of an incoming {@code Call} when ringing locally, but not yet connected.
     */
    public static final int STATE_RINGING = 2;

    /**
     * The state of an outgoing {@code Call} when waiting on user to select a
     * {@link PhoneAccount} through which to place the call.
     */
    public static final int STATE_SELECT_PHONE_ACCOUNT = 8;

    /**
     * The state of a call that is being presented to the user after being in
     * {@link #STATE_AUDIO_PROCESSING}. The call is still active with the network in this case, and
     * Telecom will hold audio focus and play a ringtone if appropriate.
     */
    public static final int STATE_SIMULATED_RINGING = 13;

    private Call() {
    }

    /**
     * Defines callbacks which inform the {@link InCallService} of changes to a {@link Call}.
     * These callbacks can originate from the Telecom framework, or a {@link ConnectionService}
     * implementation.
     * <p>
     * You can handle these callbacks by extending the {@link Callback} class and overriding the
     * callbacks that your {@link InCallService} is interested in.  The callback methods include the
     * {@link Call} for which the callback applies, allowing reuse of a single instance of your
     * {@link Callback} implementation, if desired.
     * <p>
     * Use {@link Call#registerCallback(Callback)} to register your callback(s).  Ensure
     * {@link Call#unregisterCallback(Callback)} is called when you no longer require callbacks
     * (typically in {@link InCallService#onCallRemoved(Call)}).
     * Note: Callbacks which occur before you call {@link Call#registerCallback(Callback)} will not
     * reach your implementation of {@link Callback}, so it is important to register your callback
     * as soon as your {@link InCallService} is notified of a new call via
     * {@link InCallService#onCallAdded(Call)}.
     */
    public static final class Callback {
        /**
         * Handover failure reason returned via {@link #onHandoverFailed(Call, int)} when the app
         * to handover the call to rejects the handover request.
         * <p>
         * Will be returned when {@link Call#handoverTo(PhoneAccountHandle, int, Bundle)} is called
         * and the destination {@link PhoneAccountHandle}'s {@link ConnectionService} returns a
         * {@code null} {@link Connection} from
         * {@link ConnectionService#onCreateOutgoingHandoverConnection(PhoneAccountHandle,
         * ConnectionRequest)}.
         * <p>
         * For more information on call handovers, see
         * {@link #handoverTo(PhoneAccountHandle, int, Bundle)}.
         */
        public static final int HANDOVER_FAILURE_DEST_APP_REJECTED = 1;

        /**
         * Handover failure reason returned via {@link #onHandoverFailed(Call, int)} when a handover
         * is initiated but the source or destination app does not support handover.
         * <p>
         * Will be returned when a handover is requested via
         * {@link #handoverTo(PhoneAccountHandle, int, Bundle)} and the destination
         * {@link PhoneAccountHandle} does not declare
         * {@link PhoneAccount#EXTRA_SUPPORTS_HANDOVER_TO}.  May also be returned when a handover is
         * requested at the {@link PhoneAccountHandle} for the current call (i.e. the source call's
         * {@link Details#getAccountHandle()}) does not declare
         * {@link PhoneAccount#EXTRA_SUPPORTS_HANDOVER_FROM}.
         * <p>
         * For more information on call handovers, see
         * {@link #handoverTo(PhoneAccountHandle, int, Bundle)}.
         */
        public static final int HANDOVER_FAILURE_NOT_SUPPORTED = 2;

        /**
         * Handover failure reason returned via {@link #onHandoverFailed(Call, int)} when there
         * is ongoing emergency call.
         * <p>
         * This error code is returned when {@link #handoverTo(PhoneAccountHandle, int, Bundle)} is
         * called on an emergency call, or if any other call is an emergency call.
         * <p>
         * Handovers are not permitted while there are ongoing emergency calls.
         * <p>
         * For more information on call handovers, see
         * {@link #handoverTo(PhoneAccountHandle, int, Bundle)}.
         */
        public static final int HANDOVER_FAILURE_ONGOING_EMERGENCY_CALL = 4;

        /**
         * Handover failure reason returned via {@link #onHandoverFailed(Call, int)} when a handover
         * fails for an unknown reason.
         * <p>
         * For more information on call handovers, see
         * {@link #handoverTo(PhoneAccountHandle, int, Bundle)}.
         */
        public static final int HANDOVER_FAILURE_UNKNOWN = 5;

        /**
         * Handover failure reason returned via {@link #onHandoverFailed(Call, int)} when the remote
         * user rejects the handover request.
         * <p>
         * For more information on call handovers, see
         * {@link #handoverTo(PhoneAccountHandle, int, Bundle)}.
         */
        public static final int HANDOVER_FAILURE_USER_REJECTED = 3;

        private Callback() {
        }
    }

    public static final class Details {
        /**
         * Call supports adding participants to the call via
         * {@link #addConferenceParticipants(List)}. Once participants are added, the call becomes
         * an adhoc conference call ({@link #PROPERTY_IS_ADHOC_CONFERENCE}).
         */
        public static final int CAPABILITY_ADD_PARTICIPANT = 33554432;

        /**
         * When set, prevents a video {@code Call} from being downgraded to an audio-only call.
         * <p>
         * Should be set when the VideoState has the {@link VideoProfile#STATE_TX_ENABLED} or
         * {@link VideoProfile#STATE_RX_ENABLED} bits set to indicate that the connection cannot be
         * downgraded from a video call back to a VideoState of
         * {@link VideoProfile#STATE_AUDIO_ONLY}.
         * <p>
         * Intuitively, a call which can be downgraded to audio should also have local and remote
         * video
         * capabilities (see {@link #CAPABILITY_SUPPORTS_VT_LOCAL_BIDIRECTIONAL} and
         * {@link #CAPABILITY_SUPPORTS_VT_REMOTE_BIDIRECTIONAL}).
         */
        public static final int CAPABILITY_CANNOT_DOWNGRADE_VIDEO_TO_AUDIO = 4194304;

        /**
         * For video calls, indicates whether the outgoing video for the call can be paused using
         * the {@link android.telecom.VideoProfile#STATE_PAUSED} VideoState.
         */
        public static final int CAPABILITY_CAN_PAUSE_VIDEO = 1048576;

        /**
         * When set for an external call, indicates that this {@code Call} can be pulled from a
         * remote device to the current device.
         * <p>
         * Should only be set on a {@code Call} where {@link #PROPERTY_IS_EXTERNAL_CALL} is set.
         * <p>
         * An {@link InCallService} will only see calls with this capability if it has the
         * {@link TelecomManager#METADATA_INCLUDE_EXTERNAL_CALLS} metadata set to {@code true}
         * in its manifest.
         * <p>
         * See {@link Connection#CAPABILITY_CAN_PULL_CALL} and
         * {@link Connection#PROPERTY_IS_EXTERNAL_CALL}.
         */
        public static final int CAPABILITY_CAN_PULL_CALL = 8388608;

        /**
         * Call is able to be individually disconnected when in a {@code Conference}.
         */
        public static final int CAPABILITY_DISCONNECT_FROM_CONFERENCE = 8192;

        /**
         * Call can currently be put on hold or unheld.
         */
        public static final int CAPABILITY_HOLD = 1;

        /**
         * Call supports conference call management. This capability only applies to {@link Conference}
         * calls which can have {@link Connection}s as children.
         */
        public static final int CAPABILITY_MANAGE_CONFERENCE = 128;

        /**
         * Calls within a conference can be merged. A {@link ConnectionService} has the option to
         * add a {@link Conference} call before the child {@link Connection}s are merged. This is how
         * CDMA-based {@link Connection}s are implemented. For these unmerged {@link Conference}s, this
         * capability allows a merge button to be shown while the conference call is in the foreground
         * of the in-call UI.
         * <p>
         * This is only intended for use by a {@link Conference}.
         */
        public static final int CAPABILITY_MERGE_CONFERENCE = 4;

        /**
         * Call can be muted.
         */
        public static final int CAPABILITY_MUTE = 64;

        /**
         * Indicates whether the remote party supports RTT or not to the UI.
         */
        public static final int CAPABILITY_REMOTE_PARTY_SUPPORTS_RTT = 268435456;

        /**
         * Call supports responding via text option.
         */
        public static final int CAPABILITY_RESPOND_VIA_TEXT = 32;

        /**
         * Call is able to be separated from its parent {@code Conference}, if any.
         */
        public static final int CAPABILITY_SEPARATE_FROM_CONFERENCE = 4096;

        /**
         * Local device supports bidirectional video calling.
         */
        public static final int CAPABILITY_SUPPORTS_VT_LOCAL_BIDIRECTIONAL = 768;

        /**
         * Local device supports receiving video.
         */
        public static final int CAPABILITY_SUPPORTS_VT_LOCAL_RX = 256;

        /**
         * Local device supports transmitting video.
         */
        public static final int CAPABILITY_SUPPORTS_VT_LOCAL_TX = 512;

        /**
         * Remote device supports bidirectional video calling.
         */
        public static final int CAPABILITY_SUPPORTS_VT_REMOTE_BIDIRECTIONAL = 3072;

        /**
         * Remote device supports receiving video.
         */
        public static final int CAPABILITY_SUPPORTS_VT_REMOTE_RX = 1024;

        /**
         * Remote device supports transmitting video.
         */
        public static final int CAPABILITY_SUPPORTS_VT_REMOTE_TX = 2048;

        /**
         * Call supports the deflect feature.
         */
        public static final int CAPABILITY_SUPPORT_DEFLECT = 16777216;

        /**
         * Call supports the hold feature.
         */
        public static final int CAPABILITY_SUPPORT_HOLD = 2;

        /**
         * Calls within a conference can be swapped between foreground and background.
         * See {@link #CAPABILITY_MERGE_CONFERENCE} for additional information.
         * <p>
         * This is only intended for use by a {@link Conference}.
         */
        public static final int CAPABILITY_SWAP_CONFERENCE = 8;

        /**
         * Indicates that the call is an incoming call.
         */
        public static final int DIRECTION_INCOMING = 0;

        /**
         * Indicates that the call is an outgoing call.
         */
        public static final int DIRECTION_OUTGOING = 1;

        /**
         * Indicates that the call is neither and incoming nor an outgoing call.  This can be the
         * case for calls reported directly by a {@link ConnectionService} in special cases such as
         * call handovers.
         */
        public static final int DIRECTION_UNKNOWN = -1;

        /**
         * Indicates the call used Assisted Dialing.
         *
         * @see TelecomManager#EXTRA_USE_ASSISTED_DIALING
         */
        public static final int PROPERTY_ASSISTED_DIALING = 512;

        /**
         * Whether the call is currently a conference.
         */
        public static final int PROPERTY_CONFERENCE = 1;

        /**
         * Connection is using cross sim technology.
         * <p>
         * Indicates that the {@link Connection} is using a cross sim technology which would
         * register IMS over internet APN of default data subscription.
         * <p>
         */
        public static final int PROPERTY_CROSS_SIM = 16384;

        /**
         * Whether the call is made while the device is in emergency callback mode.
         */
        public static final int PROPERTY_EMERGENCY_CALLBACK_MODE = 4;

        /**
         * Whether the call is associated with the work profile.
         */
        public static final int PROPERTY_ENTERPRISE_CALL = 32;

        /**
         * Whether the call is a generic conference, where we do not know the precise state of
         * participants in the conference (eg. on CDMA).
         */
        public static final int PROPERTY_GENERIC_CONFERENCE = 2;

        /**
         * Indicates that the call has CDMA Enhanced Voice Privacy enabled.
         */
        public static final int PROPERTY_HAS_CDMA_VOICE_PRIVACY = 128;

        /**
         * When set, the UI should indicate to the user that a call is using high definition
         * audio.
         * <p>
         * The underlying {@link ConnectionService} is responsible for reporting this
         * property.  It is important to note that this property is not intended to report the
         * actual audio codec being used for a Call, but whether the call should be indicated
         * to the user as high definition.
         * <p>
         * The Android Telephony stack reports this property for calls based on a number
         * of factors, including which audio codec is used and whether a call is using an HD
         * codec end-to-end.  Some mobile operators choose to suppress display of an HD indication,
         * and in these cases this property will not be set for a call even if the underlying audio
         * codec is in fact "high definition".
         */
        public static final int PROPERTY_HIGH_DEF_AUDIO = 16;

        /**
         * Indicates that the call is an adhoc conference call. This property can be set for both
         * incoming and outgoing calls. An adhoc conference call is formed using
         * {@link #addConferenceParticipants(List)},
         * {@link TelecomManager#addNewIncomingConference(PhoneAccountHandle, Bundle)}, or
         * {@link TelecomManager#startConference(List, Bundle)}, rather than by merging existing
         * call using {@link #conference(Call)}.
         */
        public static final int PROPERTY_IS_ADHOC_CONFERENCE = 8192;

        /**
         * When set, indicates that this {@code Call} does not actually exist locally for the
         * {@link ConnectionService}.
         * <p>
         * Consider, for example, a scenario where a user has two phones with the same phone number.
         * When a user places a call on one device, the telephony stack can represent that call on
         * the other device by adding it to the {@link ConnectionService} with the
         * {@link Connection#PROPERTY_IS_EXTERNAL_CALL} property set.
         * <p>
         * An {@link InCallService} will only see calls with this property if it has the
         * {@link TelecomManager#METADATA_INCLUDE_EXTERNAL_CALLS} metadata set to {@code true}
         * in its manifest.
         * <p>
         * See {@link Connection#PROPERTY_IS_EXTERNAL_CALL}.
         */
        public static final int PROPERTY_IS_EXTERNAL_CALL = 64;

        /**
         * The connection is using transactional call APIs.
         * <p>
         * The underlying connection was added as a transactional call via the
         * {@link TelecomManager#addCall} API.
         */
        public static final int PROPERTY_IS_TRANSACTIONAL = 32768;

        /**
         * Indicates that the call has been identified as the network as an emergency call. This
         * property may be set for both incoming and outgoing calls which the network identifies as
         * emergency calls.
         */
        public static final int PROPERTY_NETWORK_IDENTIFIED_EMERGENCY_CALL = 2048;

        /**
         * Indicates that the call is an RTT call. Use {@link #getRttCall()} to get the
         * {@link RttCall} object that is used to send and receive text.
         */
        public static final int PROPERTY_RTT = 1024;

        /**
         * Indicates that the call is from a self-managed {@link ConnectionService}.
         * <p>
         * See also {@link Connection#PROPERTY_SELF_MANAGED}
         */
        public static final int PROPERTY_SELF_MANAGED = 256;

        /**
         * Indicates that the call is using VoIP audio mode.
         * <p>
         * When this property is set, the {@link android.media.AudioManager} audio mode for this
         * call will be {@link android.media.AudioManager#MODE_IN_COMMUNICATION}.  When this
         * property is not set, the audio mode for this call will be
         * {@link android.media.AudioManager#MODE_IN_CALL}.
         * <p>
         * This property reflects changes made using {@link Connection#setAudioModeIsVoip(boolean)}.
         * <p>
         * You can use this property to determine whether an un-answered incoming call or a held
         * call will use VoIP audio mode (if the call does not currently have focus, the system
         * audio mode may not reflect the mode the call will use).
         */
        public static final int PROPERTY_VOIP_AUDIO_MODE = 4096;

        /**
         * Connection is using WIFI.
         */
        public static final int PROPERTY_WIFI = 8;

        private Details() {
        }
    }

    /**
     * A class that holds the state that describes the state of the RTT channel to the remote
     * party, if it is active.
     */
    public static final class RttCall {
        /**
         * Indicates that there should be a bidirectional audio stream between the two parties
         * on the call.
         */
        public static final int RTT_MODE_FULL = 1;

        /**
         * Indicates that the local user should be able to hear the audio stream from the remote
         * user, but not vice versa. Equivalent to muting the microphone.
         */
        public static final int RTT_MODE_HCO = 2;

        /**
         * Indicates that the remote user should be able to hear the audio stream from the local
         * user, but not vice versa. Equivalent to setting the volume to zero.
         */
        public static final int RTT_MODE_VCO = 3;

        private RttCall() {
        }
    }
}
