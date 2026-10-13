package android.telecom;

/**
 * An abstract service that should be implemented by any apps which either:
 * <ol>
 *     <li>Can make phone calls (VoIP or otherwise) and want those calls to be integrated into the
 *     built-in phone app.  Referred to as a <b>system managed</b> {@link ConnectionService}.</li>
 *     <li>Are a standalone calling app and don't want their calls to be integrated into the
 *     built-in phone app.  Referred to as a <b>self managed</b> {@link ConnectionService}.</li>
 * </ol>
 * Once implemented, the {@link ConnectionService} needs to take the following steps so that Telecom
 * will bind to it:
 * <p>
 * 1. <i>Registration in AndroidManifest.xml</i>
 * <br/>
 * <pre>
 * &lt;service android:name="com.example.package.MyConnectionService"
 *    android:label="@string/some_label_for_my_connection_service"
 *    android:permission="android.permission.BIND_TELECOM_CONNECTION_SERVICE"&gt;
 *  &lt;intent-filter&gt;
 *   &lt;action android:name="android.telecom.ConnectionService" /&gt;
 *  &lt;/intent-filter&gt;
 * &lt;/service&gt;
 * </pre>
 * <p>
 * 2. <i> Registration of {@link PhoneAccount} with {@link TelecomManager}.</i>
 * <br/>
 * See {@link PhoneAccount} and {@link TelecomManager#registerPhoneAccount} for more information.
 * <p>
 * System managed {@link ConnectionService}s must be enabled by the user in the phone app settings
 * before Telecom will bind to them.  Self-managed {@link ConnectionService}s must declare the
 * {@link android.Manifest.permission#MANAGE_OWN_CALLS} permission in their manifest before Telecom
 * will bind to them.
 * <p>
 * Once registered and enabled by the user in the phone app settings or granted permission, telecom
 * will bind to a {@link ConnectionService} implementation when it wants that
 * {@link ConnectionService} to place a call or the service has indicated that is has an incoming
 * call through {@link TelecomManager#addNewIncomingCall(PhoneAccountHandle, Bundle)}. The
 * {@link ConnectionService} can then expect a call to
 * {@link #onCreateIncomingConnection(PhoneAccountHandle, ConnectionRequest)} or
 * {@link #onCreateOutgoingConnection(PhoneAccountHandle, ConnectionRequest)}
 * wherein it should provide a new instance of a {@link Connection} object.  It is through this
 * {@link Connection} object that telecom receives state updates and the {@link ConnectionService}
 * receives call-commands such as answer, reject, hold and disconnect.
 * <p>
 * When there are no more live calls, telecom will unbind from the {@link ConnectionService}.
 * <p>
 * <h1>Self-Managed Connection Services</h1>
 * A VoIP app can implement a {@link ConnectionService} to ensure that its calls are integrated
 * into the Android platform.  There are numerous benefits to using the Telecom APIs for a VoIP app:
 * <ul>
 *     <li>Call concurrency is handled - the user is able to swap between calls in different
 *     apps and on the mobile network.</li>
 *     <li>Simplified audio routing - the platform provides your app with a unified list of the
 *     audio routes which are available
 *     (e.g. {@link android.telecom.Connection#onAvailableCallEndpointsChanged(List)}) and a
 *     standardized way to switch audio routes
 *     (e.g. {@link android.telecom.Connection#requestCallEndpointChange(CallEndpoint, Executor,
 *     OutcomeReceiver)} ).</li>
 *     <li>Bluetooth integration - your calls will be visible on and controllable via
 *     bluetooth devices (e.g. car head units and headsets).</li>
 *     <li>Companion device integration - wearable devices such as watches which implement an
 *     {@link InCallService} can optionally subscribe to see self-managed calls.  Similar to a
 *     bluetooth headunit, wearables will typically render your call using a generic call UX and
 *     provide the user with basic call controls such as hangup, answer, reject.</li>
 *     <li>Automotive calling experiences - Android supports automotive optimized experiences which
 *     provides a means for calls to be controlled and viewed in an automobile; these experiences
 *     are capable of leveraging call metadata provided by your app.</li>
 * </ul>
 * <h2>Registering a Phone Account</h2>
 * Before your app can handle incoming or outgoing calls through Telecom it needs to register a
 * {@link PhoneAccount} with Telecom indicating to the platform that your app is capable of calling.
 * <p>
 * Your app should create a new instance of {@link PhoneAccount} which meets the following
 * requirements:
 * <ul>
 *     <li>Has {@link PhoneAccount#CAPABILITY_SELF_MANAGED} (set using
 *     {@link PhoneAccount.Builder#setCapabilities(int)}).  This indicates to Telecom that your
 *     app will report calls but that it provides a primary UI for the calls by itself.</li>
 *     <li>Provide a unique identifier for the {@link PhoneAccount} via the
 *     {@link PhoneAccountHandle#getId()} attribute.  As per the {@link PhoneAccountHandle}
 *     documentation, you should NOT use an identifier which contains PII or other sensitive
 *     information.  A typical choice is a UUID.</li>
 * </ul>
 * Your app should register the new {@link PhoneAccount} with Telecom using
 * {@link TelecomManager#registerPhoneAccount(PhoneAccount)}.  {@link PhoneAccount}s persist across
 * reboot.  You can use {@link TelecomManager#getOwnSelfManagedPhoneAccounts()} to confirm the
 * {@link PhoneAccount} you registered.  Your app should generally only register a single
 * {@link PhoneAccount}.
 *
 * <h2>Implementing ConnectionService</h2>
 * Your app uses {@link TelecomManager#placeCall(Uri, Bundle)} to start new outgoing calls and
 * {@link TelecomManager#addNewIncomingCall(PhoneAccountHandle, Bundle)} to report new incoming
 * calls.  Calling these APIs causes the Telecom stack to bind to your app's
 * {@link ConnectionService} implementation.  Telecom will either inform your app that it cannot
 * handle a call request at the current time (i.e. there could be an ongoing emergency call, which
 * means your app is not allowed to handle calls at the current time), or it will ask your app to
 * create a new instance of {@link Connection} to represent a call in your app.
 *
 * Your app should implement the following {@link ConnectionService} methods:
 * <ul>
 *     <li>{@link ConnectionService#onCreateOutgoingConnection(PhoneAccountHandle,
 *     ConnectionRequest)} - called by Telecom to ask your app to make a new {@link Connection}
 *     to represent an outgoing call your app requested via
 *     {@link TelecomManager#placeCall(Uri, Bundle)}.</li>
 *     <li><{@link ConnectionService#onCreateOutgoingConnectionFailed(PhoneAccountHandle,
 *     ConnectionRequest)} - called by Telecom to inform your app that a call it reported via
 *     {@link TelecomManager#placeCall(Uri, Bundle)} cannot be handled at this time.  Your app
 *     should NOT place a call at the current time.</li>
 *     <li>{@link ConnectionService#onCreateIncomingConnection(PhoneAccountHandle,
 *     ConnectionRequest)} - called by Telecom to ask your app to make a new {@link Connection}
 *     to represent an incoming call your app reported via
 *     {@link TelecomManager#addNewIncomingCall(PhoneAccountHandle, Bundle)}.</li>
 *     <li>{@link ConnectionService#onCreateIncomingConnectionFailed(PhoneAccountHandle,
 *     ConnectionRequest)} - called by Telecom to inform your app that an incoming call it reported
 *     via {@link TelecomManager#addNewIncomingCall(PhoneAccountHandle, Bundle)} cannot be handled
 *     at this time.  Your app should NOT post a new incoming call notification and should silently
 *     reject the call.</li>
 * </ul>
 *
 * <h2>Implementing a Connection</h2>
 * Your app should extend the {@link Connection} class to represent calls in your app.  When you
 * create new instances of your {@link Connection}, you should ensure the following properties are
 * set on the new {@link Connection} instance returned by your {@link ConnectionService}:
 * <ul>
 *     <li>{@link Connection#setAddress(Uri, int)} - the identifier for the other party.  For
 *     apps that user phone numbers the {@link Uri} can be a {@link PhoneAccount#SCHEME_TEL} URI
 *     representing the phone number.</li>
 *     <li>{@link Connection#setCallerDisplayName(String, int)} - the display name of the other
 *     party.  This is what will be shown on Bluetooth devices and other calling surfaces such
 *     as wearable devices.  This is particularly important for calls that do not use a phone
 *     number to identify the caller or called party.</li>
 *     <li>{@link Connection#setConnectionProperties(int)} - ensure you set
 *     {@link Connection#PROPERTY_SELF_MANAGED} to identify to the platform that the call is
 *     handled by your app.</li>
 *     <li>{@link Connection#setConnectionCapabilities(int)} - if your app supports making calls
 *     inactive (i.e. holding calls) you should get {@link Connection#CAPABILITY_SUPPORT_HOLD} and
 *     {@link Connection#CAPABILITY_HOLD} to indicate to the platform that you calls can potentially
 *     be held for concurrent calling scenarios.</li>
 *     <li>{@link Connection#setAudioModeIsVoip(boolean)} - set to {@code true} to ensure that the
 *     platform knows your call is a VoIP call.</li>
 *     <li>For newly created {@link Connection} instances, do NOT change the state of your call
 *     using {@link Connection#setActive()}, {@link Connection#setOnHold()} until the call is added
 *     to Telecom (ie you have returned it via
 *     {@link ConnectionService#onCreateOutgoingConnection(PhoneAccountHandle, ConnectionRequest)}
 *     or
 *     {@link ConnectionService#onCreateIncomingConnection(PhoneAccountHandle, ConnectionRequest)}).
 *     </li>
 * </ul>
 *
 * <h2>How to Place Outgoing Calls</h2>
 * When your app wants to place an outgoing call it calls
 * {@link TelecomManager#placeCall(Uri, Bundle)}.  You should specify a {@link Uri} to identify
 * who the call is being placed to, and specify the {@link PhoneAccountHandle} associated with the
 * {@link PhoneAccount} you registered for your app using
 * {@link TelecomManager#EXTRA_PHONE_ACCOUNT_HANDLE} in the {@link Bundle} parameter.
 * <p>
 * Telecom will bind to your app's {@link ConnectionService} implementation and call either:
 * <ul>
 *     <li>{@link ConnectionService#onCreateOutgoingConnection(PhoneAccountHandle,
 *     ConnectionRequest)} - the {@link ConnectionRequest#getAddress()} will match the address
 *     you specified when placing the call.  You should return a new instance of your app's
 *     {@link Connection} class to represent the outgoing call.</li>
 *     <li>{@link ConnectionService#onCreateOutgoingConnectionFailed(PhoneAccountHandle,
 *     ConnectionRequest)} - your app should not place the call at this time; the call should be
 *     cancelled and the user informed that the call cannot be placed.</li>
 * </ul>
 * <p>
 * New outgoing calls will start in a {@link Connection#STATE_DIALING} state.  This state indicates
 * that your app is in the process of connecting the call to the other party.
 * <p>
 * Once the other party answers the call (or it is set up successfully), your app should call
 * {@link Connection#setActive()} to inform Telecom that the call is now active.
 *
 * <h2>How to Add Incoming Calls</h2>
 * When your app receives an incoming call, it should call
 * {@link TelecomManager#addNewIncomingCall(PhoneAccountHandle, Bundle)}.  Set the
 * {@link PhoneAccountHandle} parameter to the {@link PhoneAccountHandle} associated with your
 * app's {@link PhoneAccount}.
 * <p>
 * Telecom will bind to your app's {@link ConnectionService} implementation and call either:
 * <ul>
 *     <li>{@link ConnectionService#onCreateIncomingConnection(PhoneAccountHandle,
 *     ConnectionRequest)} - You should return a new instance of your app's
 *     {@link Connection} class to represent the incoming call.</li>
 *     <li>{@link ConnectionService#onCreateIncomingConnectionFailed(PhoneAccountHandle,
 *     ConnectionRequest)} - your app should not receive the call at this time; the call should be
 *     rejected silently; the user may be informed of a missed call.</li>
 * </ul>
 * <p>
 * New incoming calls will start with a {@link Connection#STATE_RINGING} state.  This state
 * indicates that your app has a new incoming call pending.  Telecom will NOT play a ringtone or
 * post a notification for your app.  It is up to your app to post an incoming call notification
 * with an associated ringtone.  Telecom will call {@link Connection#onShowIncomingCallUi()} on the
 * {@link Connection} when your app can post its incoming call notification.  See
 * {@link Connection#onShowIncomingCallUi() the docs} for more information on how to post the
 * notification.
 * <p>
 * Your incoming call notification (or full screen UI) will typically have an "answer" and "decline"
 * action which the user chooses.  When your app receives the "answer" or "decline"
 * {@link android.app.PendingIntent}, you should must call either {@link Connection#setActive()} to
 * inform Telecom that the call was answered, or
 * {@link Connection#setDisconnected(DisconnectCause)} to inform Telecom that the call was rejected.
 * If the call was rejected, supply an instance of {@link DisconnectCause} with
 * {@link DisconnectCause#REJECTED}, and then call {@link Connection#destroy()}.
 * <p>
 * In addition to handling requests to answer or decline the call via notification actions, your
 * app should also be implement the {@link Connection#onAnswer(int)} and
 * {@link Connection#onAnswer()} methods on the {@link Connection}.  These will be raised if the
 * user answers your call via a Bluetooth device or another device like a wearable or automotive
 * calling UX.  In response, your app should call {@link Connection#setActive()} to inform Telecom
 * that the call was answered.
 * <p>
 * Additionally, your app should implement {@link Connection#onReject()} to handle requests to
 * reject the call which are raised via Bluetooth or other calling surfaces.  Your app should call
 * {@link Connection#setDisconnected(DisconnectCause)} and supply an instance of
 * {@link DisconnectCause} with {@link DisconnectCause#REJECTED} in this case.
 *
 * <h2>Ending Calls</h2>
 * When an ongoing active call (incoming or outgoing) has ended, your app is responsible for
 * informing Telecom that the call ended.
 * <p>
 * Your app calls:
 * <ul>
 *     <li>{@link Connection#setDisconnected(DisconnectCause)} - this informs Telecom that the
 *     call has terminated.  You should provide a new instance of {@link DisconnectCause} with
 *     either {@link DisconnectCause#LOCAL} or {@link DisconnectCause#REMOTE} to indicate where the
 *     call disconnection took place.  {@link DisconnectCause#LOCAL} indicates that the call
 *     terminated in your app on the current device (i.e. via user action), where
 *     {@link DisconnectCause#REMOTE} indicates that the call terminates on the remote device.</li>
 *     <li>{@link Connection#destroy()} - this informs Telecom that your call instance can be
 *     cleaned up.  You should always call this when you are finished with a call.</li>
 * </ul>
 * <p>
 * Similar to answering incoming calls, requests to disconnect your call may originate from outside
 * your app.  You can handle these by implementing {@link Connection#onDisconnect()}.  Your app
 * should call {@link Connection#setDisconnected(DisconnectCause)} with an instance of
 * {@link DisconnectCause} and reason {@link DisconnectCause#LOCAL} to indicate to Telecom that your
 * app has disconnected the call as requested based on the user's request.
 *
 * <h2>Holding and Unholding Calls</h2>
 * When your app specifies {@link Connection#CAPABILITY_SUPPORT_HOLD} and
 * {@link Connection#CAPABILITY_HOLD} on your {@link Connection} instance, it is telling Telecom
 * that your calls can be placed into a suspended, or "held" state if required.  If your app
 * supports holding its calls, it will be possible for the user to switch between calls in your app
 * and holdable calls in another app or on the mobile network.  If your app does not support
 * holding its calls, you may receive a request to disconnect the call from Telecom if the user
 * opts to answer an incoming call in another app or on the mobile network; this ensures that the
 * user can only be in one call at a time.
 * <p>
 * Your app is free to change a call between the held and active state using
 * {@link Connection#setOnHold()} and {@link Connection#setActive()}.
 * <p>
 * Your app may receive a request from Telecom to hold or unhold a call via
 * {@link Connection#onHold()} and {@link Connection#onUnhold()}.  Telecom can ask your app to
 * hold or unhold its {@link Connection} either if the user requests this action through another
 * calling surface such as Bluetooth, or if the user answers or switches to a call in a different
 * app or on the mobile network.
 * <p>
 * When your app receives an {@link Connection#onHold()} it must call {@link Connection#setOnHold()}
 * to inform Telecom that the call has been held successfully.
 * <p>
 * When your app receives an {@link Connection#onUnhold()} it must call
 * {@link Connection#setActive()} to inform Telecom that the call has been resumed successfully.
 */
public final class ConnectionService {
    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.telecom.ConnectionService";

    private ConnectionService() {
    }
}
