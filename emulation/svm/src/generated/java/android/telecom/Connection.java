package android.telecom;

/**
 * Represents a phone call or connection to a remote endpoint that carries voice and/or video
 * traffic.
 * <p>
 * Implementations create a custom subclass of {@code Connection} and return it to the framework
 * as the return value of
 * {@link ConnectionService#onCreateIncomingConnection(PhoneAccountHandle, ConnectionRequest)}
 * or
 * {@link ConnectionService#onCreateOutgoingConnection(PhoneAccountHandle, ConnectionRequest)}.
 * Implementations are then responsible for updating the state of the {@code Connection}, and
 * must call {@link #destroy()} to signal to the framework that the {@code Connection} is no
 * longer used and associated resources may be recovered.
 * <p>
 * Subclasses of {@code Connection} override the {@code on*} methods to provide the
 * {@link ConnectionService}'s implementation of calling functionality.  The {@code on*} methods are
 * called by Telecom to inform an instance of a {@code Connection} of actions specific to that
 * {@code Connection} instance.
 * <p>
 * Basic call support requires overriding the following methods: {@link #onAnswer()},
 * {@link #onDisconnect()}, {@link #onReject()}, {@link #onAbort()}
 * <p>
 * Where a {@code Connection} has {@link #CAPABILITY_SUPPORT_HOLD}, the {@link #onHold()} and
 * {@link #onUnhold()} methods should be overridden to provide hold support for the
 * {@code Connection}.
 * <p>
 * Where a {@code Connection} supports a variation of video calling (e.g. the
 * {@code CAPABILITY_SUPPORTS_VT_*} capability bits), {@link #onAnswer(int)} should be overridden
 * to support answering a call as a video call.
 * <p>
 * Where a {@code Connection} has {@link #PROPERTY_IS_EXTERNAL_CALL} and
 * {@link #CAPABILITY_CAN_PULL_CALL}, {@link #onPullExternalCall()} should be overridden to provide
 * support for pulling the external call.
 * <p>
 * Where a {@code Connection} supports conference calling {@link #onSeparate()} should be
 * overridden.
 * <p>
 * There are a number of other {@code on*} methods which a {@code Connection} can choose to
 * implement, depending on whether it is concerned with the associated calls from Telecom.  If,
 * for example, call events from a {@link InCallService} are handled,
 * {@link #onCallEvent(String, Bundle)} should be overridden.  Another example is
 * {@link #onExtrasChanged(Bundle)}, which should be overridden if the {@code Connection} wishes to
 * make use of extra information provided via the {@link Call#putExtras(Bundle)} and
 * {@link Call#removeExtras(String...)} methods.
 */
public final class Connection {
    /**
     * Adaptive Multi-rate audio codec.
     */
    public static final int AUDIO_CODEC_AMR = 1;

    /**
     * Adaptive Multi-rate wideband audio codec.
     */
    public static final int AUDIO_CODEC_AMR_WB = 2;

    /**
     * Enhanced Variable Rate Codec.  See 3GPP2 C.S0014-A.
     */
    public static final int AUDIO_CODEC_EVRC = 4;

    /**
     * Enhanced Variable Rate Codec B.  Commonly used on CDMA networks.
     */
    public static final int AUDIO_CODEC_EVRC_B = 5;

    /**
     * Enhanced Variable Rate Narrowband-Wideband Codec.
     */
    public static final int AUDIO_CODEC_EVRC_NW = 7;

    /**
     * Enhanced Variable Rate Wideband Codec.  See RFC5188.
     */
    public static final int AUDIO_CODEC_EVRC_WB = 6;

    /**
     * Enhanced Voice Services Fullband audio codec.  See 3GPP TS 26.441.
     */
    public static final int AUDIO_CODEC_EVS_FB = 20;

    /**
     * Enhanced Voice Services Narrowband audio codec.  See 3GPP TS 26.441.
     */
    public static final int AUDIO_CODEC_EVS_NB = 17;

    /**
     * Enhanced Voice Services Super-Wideband audio codec.  See 3GPP TS 26.441.
     */
    public static final int AUDIO_CODEC_EVS_SWB = 19;

    /**
     * Enhanced Voice Services Wideband audio codec.  See 3GPP TS 26.441.
     */
    public static final int AUDIO_CODEC_EVS_WB = 18;

    /**
     * ITU-T G711A audio codec.
     */
    public static final int AUDIO_CODEC_G711A = 13;

    /**
     * ITU-T G711AB audio codec.
     */
    public static final int AUDIO_CODEC_G711AB = 15;

    /**
     * ITU-T G711U audio codec.
     */
    public static final int AUDIO_CODEC_G711U = 11;

    /**
     * ITU-T G722 audio codec.
     */
    public static final int AUDIO_CODEC_G722 = 14;

    /**
     * ITU-T G723 audio codec.
     */
    public static final int AUDIO_CODEC_G723 = 12;

    /**
     * ITU-T G729 audio codec.
     */
    public static final int AUDIO_CODEC_G729 = 16;

    /**
     * GSM Enhanced Full-Rate audio codec, also known as GSM-EFR, GSM 06.60, or simply EFR.
     */
    public static final int AUDIO_CODEC_GSM_EFR = 8;

    /**
     * GSM Full-Rate audio codec, also known as GSM-FR, GSM 06.10, GSM, or simply FR.
     */
    public static final int AUDIO_CODEC_GSM_FR = 9;

    /**
     * GSM Half Rate audio codec.
     */
    public static final int AUDIO_CODEC_GSM_HR = 10;

    /**
     * Indicates that the audio codec is currently not specified or is unknown.
     */
    public static final int AUDIO_CODEC_NONE = 0;

    /**
     * Qualcomm code-excited linear prediction 13 kilobit audio codec.
     */
    public static final int AUDIO_CODEC_QCELP13K = 3;

    /**
     * When set, indicates that this {@link Connection} supports initiation of a conference call
     * by directly adding participants using {@link #onAddConferenceParticipants(List)}. When
     * participants are added to a {@link Connection}, it will be replaced by a {@link Conference}
     * instance with {@link #PROPERTY_IS_ADHOC_CONFERENCE} set to indicate that it is an adhoc
     * conference call.
     */
    public static final int CAPABILITY_ADD_PARTICIPANT = 67108864;

    /**
     * When set, prevents a video call from being downgraded to an audio-only call.
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
    public static final int CAPABILITY_CANNOT_DOWNGRADE_VIDEO_TO_AUDIO = 8388608;

    /**
     * For video calls, indicates whether the outgoing video for the call can be paused using
     * the {@link android.telecom.VideoProfile#STATE_PAUSED} VideoState.
     */
    public static final int CAPABILITY_CAN_PAUSE_VIDEO = 1048576;

    /**
     * When set for an external connection, indicates that this {@code Connection} can be pulled
     * from a remote device to the current device.
     * <p>
     * Should only be set on a {@code Connection} where {@link #PROPERTY_IS_EXTERNAL_CALL}
     * is set.
     */
    public static final int CAPABILITY_CAN_PULL_CALL = 16777216;

    /**
     * Indicates that the connection itself wants to handle any sort of reply response, rather than
     * relying on SMS.
     */
    public static final int CAPABILITY_CAN_SEND_RESPONSE_VIA_CONNECTION = 4194304;

    /**
     * Call can be upgraded to a video call.
     * @deprecated Use {@link #CAPABILITY_SUPPORTS_VT_LOCAL_BIDIRECTIONAL} and
     * {@link #CAPABILITY_SUPPORTS_VT_REMOTE_BIDIRECTIONAL} to indicate for a call whether or not
     * video calling is supported.
     */
    @Deprecated
    public static final int CAPABILITY_CAN_UPGRADE_TO_VIDEO = 524288;

    /**
     * Connection is able to be individually disconnected when in a {@code Conference}.
     */
    public static final int CAPABILITY_DISCONNECT_FROM_CONFERENCE = 8192;

    /**
     * Connection can currently be put on hold or unheld. This is distinct from
     * {@link #CAPABILITY_SUPPORT_HOLD} in that although a connection may support 'hold' most times,
     * it does not at the moment support the function. This can be true while the call is in the
     * state {@link #STATE_DIALING}, for example. During this condition, an in-call UI may
     * display a disabled 'hold' button.
     */
    public static final int CAPABILITY_HOLD = 1;

    /**
     * Connection supports conference management. This capability only applies to
     * {@link Conference}s which can have {@link Connection}s as children.
     */
    public static final int CAPABILITY_MANAGE_CONFERENCE = 128;

    /**
     * Connections within a conference can be merged. A {@link ConnectionService} has the option to
     * add a {@link Conference} before the child {@link Connection}s are merged. This is how
     * CDMA-based {@link Connection}s are implemented. For these unmerged {@link Conference}s, this
     * capability allows a merge button to be shown while the conference is in the foreground
     * of the in-call UI.
     * <p>
     * This is only intended for use by a {@link Conference}.
     */
    public static final int CAPABILITY_MERGE_CONFERENCE = 4;

    /**
     * Connection can be muted.
     */
    public static final int CAPABILITY_MUTE = 64;

    /**
     * Indicates whether the remote party supports RTT or not to the UI.
     */
    public static final int CAPABILITY_REMOTE_PARTY_SUPPORTS_RTT = 536870912;

    /**
     * Connection supports responding via text option.
     */
    public static final int CAPABILITY_RESPOND_VIA_TEXT = 32;

    /**
     * Connection is able to be separated from its parent {@code Conference}, if any.
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
    public static final int CAPABILITY_SUPPORT_DEFLECT = 33554432;

    /**
     * Connection supports the hold feature.
     */
    public static final int CAPABILITY_SUPPORT_HOLD = 2;

    /**
     * Connections within a conference can be swapped between foreground and background.
     * See {@link #CAPABILITY_MERGE_CONFERENCE} for additional information.
     * <p>
     * This is only intended for use by a {@link Conference}.
     */
    public static final int CAPABILITY_SWAP_CONFERENCE = 8;

    /**
     * Connection event used to inform Telecom when a hold operation on a call has failed.
     * <p>
     * Sent via {@link #sendConnectionEvent(String, Bundle)}.  The {@link Bundle} parameter is
     * expected to be null when this connection event is used.
     */
    public static final String EVENT_CALL_HOLD_FAILED = "android.telecom.event.CALL_HOLD_FAILED";

    /**
     * Connection event used to inform {@link InCallService}s when the merging of two calls has
     * failed. The User Interface should use this message to inform the user of the error.
     * <p>
     * Sent via {@link #sendConnectionEvent(String, Bundle)}.  The {@link Bundle} parameter is
     * expected to be null when this connection event is used.
     */
    public static final String EVENT_CALL_MERGE_FAILED = "android.telecom.event.CALL_MERGE_FAILED";

    /**
     * Connection event used to inform {@link InCallService}s when pulling of an external call has
     * failed.  The user interface should inform the user of the error.
     * <p>
     * Expected to be used by the {@link ConnectionService} when the {@link Call#pullExternalCall()}
     * API is called on a {@link Call} with the properties
     * {@link Call.Details#PROPERTY_IS_EXTERNAL_CALL} and
     * {@link Call.Details#CAPABILITY_CAN_PULL_CALL}, but the {@link ConnectionService} could not
     * pull the external call due to an error condition.
     * <p>
     * Sent via {@link #sendConnectionEvent(String, Bundle)}.  The {@link Bundle} parameter is
     * expected to be null when this connection event is used.
     */
    public static final String EVENT_CALL_PULL_FAILED = "android.telecom.event.CALL_PULL_FAILED";

    /**
     * Connection event used to inform {@link InCallService}s when a call has been put on hold by
     * the remote party.
     * <p>
     * This is different than the {@link Connection#STATE_HOLDING} state which indicates that the
     * call is being held locally on the device.  When a capable {@link ConnectionService} receives
     * signalling to indicate that the remote party has put the call on hold, it can send this
     * connection event.
     */
    public static final String EVENT_CALL_REMOTELY_HELD = "android.telecom.event.CALL_REMOTELY_HELD";

    /**
     * Connection event used to inform {@link InCallService}s when a call which was remotely held
     * (see {@link #EVENT_CALL_REMOTELY_HELD}) has been un-held by the remote party.
     * <p>
     * This is different than the {@link Connection#STATE_HOLDING} state which indicates that the
     * call is being held locally on the device.  When a capable {@link ConnectionService} receives
     * signalling to indicate that the remote party has taken the call off hold, it can send this
     * connection event.
     */
    public static final String EVENT_CALL_REMOTELY_UNHELD = "android.telecom.event.CALL_REMOTELY_UNHELD";

    /**
     * Connection event used to inform Telecom when a switch operation on a call has failed.
     * <p>
     * Sent via {@link #sendConnectionEvent(String, Bundle)}.  The {@link Bundle} parameter is
     * expected to be null when this connection event is used.
     */
    public static final String EVENT_CALL_SWITCH_FAILED = "android.telecom.event.CALL_SWITCH_FAILED";

    /**
     * Connection event used to inform {@link InCallService}s when the process of merging a
     * Connection into a conference has completed.
     * <p>
     * Sent via {@link #sendConnectionEvent(String, Bundle)}.  The {@link Bundle} parameter is
     * expected to be null when this connection event is used.
     */
    public static final String EVENT_MERGE_COMPLETE = "android.telecom.event.MERGE_COMPLETE";

    /**
     * Connection event used to inform {@link InCallService}s when the process of merging a
     * Connection into a conference has begun.
     * <p>
     * Sent via {@link #sendConnectionEvent(String, Bundle)}.  The {@link Bundle} parameter is
     * expected to be null when this connection event is used.
     */
    public static final String EVENT_MERGE_START = "android.telecom.event.MERGE_START";

    /**
     * Connection event used to inform Telecom that it should stop the on hold tone.  This is used
     * to stop a tone when the peer puts the current call on hold.  Sent to Telecom via
     * {@link #sendConnectionEvent(String, Bundle)}.
     */
    public static final String EVENT_ON_HOLD_TONE_END = "android.telecom.event.ON_HOLD_TONE_END";

    /**
     * Connection event used to inform Telecom that it should play the on hold tone.  This is used
     * to play a tone when the peer puts the current call on hold.  Sent to Telecom via
     * {@link #sendConnectionEvent(String, Bundle)}.
     */
    public static final String EVENT_ON_HOLD_TONE_START = "android.telecom.event.ON_HOLD_TONE_START";

    /**
     * Connection event used to inform an {@link InCallService} that the RTT audio indication
     * has changed.
     */
    public static final String EVENT_RTT_AUDIO_INDICATION_CHANGED = "android.telecom.event.RTT_AUDIO_INDICATION_CHANGED";

    /**
     * Boolean connection extra key set on a {@link Connection} in
     * {@link Connection#STATE_RINGING} state to indicate that answering the call will cause the
     * current active foreground call to be dropped.
     */
    public static final String EXTRA_ANSWERING_DROPS_FG_CALL = "android.telecom.extra.ANSWERING_DROPS_FG_CALL";

    /**
     * String connection extra key set on a {@link Connection} in {@link Connection#STATE_RINGING}
     * state to indicate the name of the third-party app which is responsible for the current
     * foreground call.
     * <p>
     * Used when {@link #EXTRA_ANSWERING_DROPS_FG_CALL} is true to ensure that the default Phone app
     * is able to inform the user that answering the new incoming call will cause a call owned by
     * another app to be dropped when the incoming call is answered.
     */
    public static final String EXTRA_ANSWERING_DROPS_FG_CALL_APP_NAME = "android.telecom.extra.ANSWERING_DROPS_FG_CALL_APP_NAME";

    /**
     * The audio codec in use for the current {@link Connection}, if known.  Examples of valid
     * values include {@link #AUDIO_CODEC_AMR_WB} and {@link #AUDIO_CODEC_EVS_WB}.
     */
    public static final String EXTRA_AUDIO_CODEC = "android.telecom.extra.AUDIO_CODEC";

    /**
     * Float connection extra key used to store the audio codec bandwidth in khz for the current
     * {@link Connection}.
     */
    public static final String EXTRA_AUDIO_CODEC_BANDWIDTH_KHZ = "android.telecom.extra.AUDIO_CODEC_BANDWIDTH_KHZ";

    /**
     * Float connection extra key used to store the audio codec bitrate in kbps for the current
     * {@link Connection}.
     */
    public static final String EXTRA_AUDIO_CODEC_BITRATE_KBPS = "android.telecom.extra.AUDIO_CODEC_BITRATE_KBPS";

    /**
     * Connection extra key used to store the subject for an incoming call.  The user interface can
     * query this extra and display its contents for incoming calls.  Will only be used if the
     * {@link PhoneAccount} supports the capability {@link PhoneAccount#CAPABILITY_CALL_SUBJECT}.
     */
    public static final String EXTRA_CALL_SUBJECT = "android.telecom.extra.CALL_SUBJECT";

    /**
     * Connection extra key used to store a child number associated with the current connection.
     * Used to communicate to the user interface that the connection was received via
     * a child address (i.e. phone number) associated with the {@link PhoneAccount}'s primary
     * address.
     */
    public static final String EXTRA_CHILD_ADDRESS = "android.telecom.extra.CHILD_ADDRESS";

    /**
     * Boolean connection extra key set on the extras passed to
     * {@link Connection#sendConnectionEvent} which indicates that audio is present
     * on the RTT call when the extra value is true.
     */
    public static final String EXTRA_IS_RTT_AUDIO_PRESENT = "android.telecom.extra.IS_RTT_AUDIO_PRESENT";

    /**
     * Connection extra key used to store the last forwarded number associated with the current
     * connection.  Used to communicate to the user interface that the connection was forwarded via
     * the specified number.
     */
    public static final String EXTRA_LAST_FORWARDED_NUMBER = "android.telecom.extra.LAST_FORWARDED_NUMBER";

    /**
     * Last known cell identity key {@link CellIdentity} to be used to fill geo location header
     * in case of an emergency  call. This entry will not be filled if call is not identified as
     * an emergency call. Only provided to the {@link ConnectionService}  for the purpose of
     * placing an emergency call; will not be present in the  {@link InCallService} layer.
     * The {@link ConnectionService}'s implementation will be logged for fine location access
     * when an outgoing call is placed in this case.
     */
    public static final String EXTRA_LAST_KNOWN_CELL_IDENTITY = "android.telecom.extra.LAST_KNOWN_CELL_IDENTITY";

    /**
     * String Connection extra key used to store SIP invite fields for an incoming call for IMS call
     */
    public static final String EXTRA_SIP_INVITE = "android.telecom.extra.SIP_INVITE";

    /**
     * Set by the framework to indicate that a connection is using assisted dialing.
     * <p>
     * This is used for outgoing calls.
     *
     * @see TelecomManager#EXTRA_USE_ASSISTED_DIALING
     */
    public static final int PROPERTY_ASSISTED_DIALING = 512;

    /**
     * Connection is using cross sim technology.
     * <p>
     * Indicates that the {@link Connection} is using a cross sim technology which would
     * register IMS over internet APN of default data subscription.
     * <p>
     */
    public static final int PROPERTY_CROSS_SIM = 8192;

    /**
     * Indicates that the connection has CDMA Enhanced Voice Privacy enabled.
     */
    public static final int PROPERTY_HAS_CDMA_VOICE_PRIVACY = 32;

    /**
     * Connection is using high definition audio.
     * <p>
     * Indicates that the {@link Connection} is using a "high definition" audio codec.  This usually
     * implies something like AMR wideband, but the interpretation of when a call is considered high
     * definition is left to the {@link ConnectionService} to decide.
     * <p>
     * Translates to {@link android.telecom.Call.Details#PROPERTY_HIGH_DEF_AUDIO}.
     */
    public static final int PROPERTY_HIGH_DEF_AUDIO = 4;

    /**
     * Set by the framework to indicate that a call is an adhoc conference call.
     * <p>
     * This is used for outgoing and incoming conference calls.
     */
    public static final int PROPERTY_IS_ADHOC_CONFERENCE = 4096;

    /**
     * When set, indicates that the {@code Connection} does not actually exist locally for the
     * {@link ConnectionService}.
     * <p>
     * Consider, for example, a scenario where a user has two devices with the same phone number.
     * When a user places a call on one devices, the telephony stack can represent that call on the
     * other device by adding is to the {@link ConnectionService} with the
     * {@link #PROPERTY_IS_EXTERNAL_CALL} capability set.
     * <p>
     * An {@link ConnectionService} should not assume that all {@link InCallService}s will handle
     * external connections.  Only those {@link InCallService}s which have the
     * {@link TelecomManager#METADATA_INCLUDE_EXTERNAL_CALLS} metadata set to {@code true} in its
     * manifest will see external connections.
     */
    public static final int PROPERTY_IS_EXTERNAL_CALL = 16;

    /**
     * Set by the framework to indicate that a connection has an active RTT session associated with
     * it.
     */
    public static final int PROPERTY_IS_RTT = 256;

    /**
     * Set by the framework to indicate that the network has identified a Connection as an emergency
     * call.
     * <p>
     * This is used for incoming (mobile-terminated) calls to indicate the call is from emergency
     * services.
     */
    public static final int PROPERTY_NETWORK_IDENTIFIED_EMERGENCY_CALL = 1024;

    /**
     * Set by the framework to indicate that the {@link Connection} originated from a self-managed
     * {@link ConnectionService}.
     * <p>
     * See {@link PhoneAccount#CAPABILITY_SELF_MANAGED}.
     */
    public static final int PROPERTY_SELF_MANAGED = 128;

    /**
     * Connection is using WIFI.
     * <p>
     * Used to indicate that a call is taking place over WIFI versus a carrier network.
     * <p>
     * Translates to {@link android.telecom.Call.Details#PROPERTY_WIFI}.
     */
    public static final int PROPERTY_WIFI = 8;

    /**
     * A connection is active. Both parties are connected to the call and can actively communicate.
     */
    public static final int STATE_ACTIVE = 4;

    /**
     * An outgoing connection is in the dialing state. In this state the other party has not yet
     * answered the call and the user traditionally hears a ringback tone.
     */
    public static final int STATE_DIALING = 3;

    /**
     * A connection has been disconnected. This is the final state once the user has been
     * disconnected from a call either locally, remotely or by an error in the service.
     */
    public static final int STATE_DISCONNECTED = 6;

    /**
     * A connection is on hold.
     */
    public static final int STATE_HOLDING = 5;

    /**
     * The connection is initializing. This is generally the first state for a {@code Connection}
     * returned by a {@link ConnectionService}.
     */
    public static final int STATE_INITIALIZING = 0;

    /**
     * The connection is new and not connected.
     */
    public static final int STATE_NEW = 1;

    /**
     * The state of an external connection which is in the process of being pulled from a remote
     * device to the local device.
     * <p>
     * A connection can only be in this state if the {@link #PROPERTY_IS_EXTERNAL_CALL} property and
     * {@link #CAPABILITY_CAN_PULL_CALL} capability bits are set on the connection.
     */
    public static final int STATE_PULLING_CALL = 7;

    /**
     * An incoming connection is in the ringing state. During this state, the user's ringer or
     * vibration feature will be activated.
     */
    public static final int STATE_RINGING = 2;

    /**
     * Indicates that verification by the network failed.  This indicates there is a high likelihood
     * that the call did not originate from a valid source.
     */
    public static final int VERIFICATION_STATUS_FAILED = 2;

    /**
     * Indicates that the network could not perform verification.
     */
    public static final int VERIFICATION_STATUS_NOT_VERIFIED = 0;

    /**
     * Indicates that verification by the network passed.  This indicates there is a high likelihood
     * that the call originated from a valid source.
     */
    public static final int VERIFICATION_STATUS_PASSED = 1;

    private Connection() {
    }

    /**
     * Provides constants to represent the results of responses to session modify requests sent via
     * {@link Call#sendRttRequest()}
     */
    public static final class RttModifyStatus {
        /**
         * Session modify request failed.
         */
        public static final int SESSION_MODIFY_REQUEST_FAIL = 2;

        /**
         * Session modify request ignored due to invalid parameters.
         */
        public static final int SESSION_MODIFY_REQUEST_INVALID = 3;

        /**
         * Session modify request rejected by remote user.
         */
        public static final int SESSION_MODIFY_REQUEST_REJECTED_BY_REMOTE = 5;

        /**
         * Session modify request was successful.
         */
        public static final int SESSION_MODIFY_REQUEST_SUCCESS = 1;

        /**
         * Session modify request timed out.
         */
        public static final int SESSION_MODIFY_REQUEST_TIMED_OUT = 4;

        private RttModifyStatus() {
        }
    }

    /**
     * Provides a means of controlling the video session associated with a {@link Connection}.
     * <p>
     * Implementations create a custom subclass of {@link VideoProvider} and the
     * {@link ConnectionService} creates an instance sets it on the {@link Connection} using
     * {@link Connection#setVideoProvider(VideoProvider)}.  Any connection which supports video
     * should set the {@link VideoProvider}.
     * <p>
     * The {@link VideoProvider} serves two primary purposes: it provides a means for Telecom and
     * {@link InCallService} implementations to issue requests related to the video session;
     * it provides a means for the {@link ConnectionService} to report events and information
     * related to the video session to Telecom and the {@link InCallService} implementations.
     * <p>
     * {@link InCallService} implementations interact with the {@link VideoProvider} via
     * {@link android.telecom.InCallService.VideoCall}.
     */
    public static final class VideoProvider {
        /**
         * A camera failure has occurred for the selected camera.  The {@link VideoProvider} can use
         * this as a cue to inform the user the camera is not available.
         * @see #handleCallSessionEvent(int)
         */
        public static final int SESSION_EVENT_CAMERA_FAILURE = 5;

        /**
         * Session event raised by Telecom when
         * {@link android.telecom.InCallService.VideoCall#setCamera(String)} is called and the
         * caller does not have the necessary {@link android.Manifest.permission#CAMERA} permission.
         * @see #handleCallSessionEvent(int)
         */
        public static final int SESSION_EVENT_CAMERA_PERMISSION_ERROR = 7;

        /**
         * Issued after {@link #SESSION_EVENT_CAMERA_FAILURE} when the camera is once again ready
         * for operation.  The {@link VideoProvider} can use this as a cue to inform the user that
         * the camera has become available again.
         * @see #handleCallSessionEvent(int)
         */
        public static final int SESSION_EVENT_CAMERA_READY = 6;

        /**
         * Video is not being received (no protocol pause was issued).
         * @see #handleCallSessionEvent(int)
         */
        public static final int SESSION_EVENT_RX_PAUSE = 1;

        /**
         * Video reception has resumed after a {@link #SESSION_EVENT_RX_PAUSE}.
         * @see #handleCallSessionEvent(int)
         */
        public static final int SESSION_EVENT_RX_RESUME = 2;

        /**
         * Video transmission has begun. This occurs after a negotiated start of video transmission
         * when the underlying protocol has actually begun transmitting video to the remote party.
         * @see #handleCallSessionEvent(int)
         */
        public static final int SESSION_EVENT_TX_START = 3;

        /**
         * Video transmission has stopped. This occurs after a negotiated stop of video transmission
         * when the underlying protocol has actually stopped transmitting video to the remote party.
         * @see #handleCallSessionEvent(int)
         */
        public static final int SESSION_EVENT_TX_STOP = 4;

        /**
         * Session modify request failed.
         * @see #receiveSessionModifyResponse(int, VideoProfile, VideoProfile)
         */
        public static final int SESSION_MODIFY_REQUEST_FAIL = 2;

        /**
         * Session modify request ignored due to invalid parameters.
         * @see #receiveSessionModifyResponse(int, VideoProfile, VideoProfile)
         */
        public static final int SESSION_MODIFY_REQUEST_INVALID = 3;

        /**
         * Session modify request rejected by remote user.
         * @see #receiveSessionModifyResponse(int, VideoProfile, VideoProfile)
         */
        public static final int SESSION_MODIFY_REQUEST_REJECTED_BY_REMOTE = 5;

        /**
         * Session modify request was successful.
         * @see #receiveSessionModifyResponse(int, VideoProfile, VideoProfile)
         */
        public static final int SESSION_MODIFY_REQUEST_SUCCESS = 1;

        /**
         * Session modify request timed out.
         * @see #receiveSessionModifyResponse(int, VideoProfile, VideoProfile)
         */
        public static final int SESSION_MODIFY_REQUEST_TIMED_OUT = 4;

        private VideoProvider() {
        }
    }
}
