package android.telephony;

/**
 * A listener class for monitoring changes in specific telephony states
 * on the device, including service state, signal strength, message
 * waiting indicator (voicemail), and others.
 * <p>
 * Override the methods for the state that you wish to receive updates for, and
 * pass your PhoneStateListener object, along with bitwise-or of the LISTEN_
 * flags to {@link TelephonyManager#listen TelephonyManager.listen()}. Methods are
 * called when the state changes, as well as once on initial registration.
 * <p>
 * Note that access to some telephony information is
 * permission-protected. Your application won't receive updates for protected
 * information unless it has the appropriate permissions declared in
 * its manifest file. Where permissions apply, they are noted in the
 * appropriate LISTEN_ flags.
 *
 * @deprecated Use {@link TelephonyCallback} instead.
 */
@Deprecated
public final class PhoneStateListener {
    /**
     *  Listen for changes to active data subId. Active data subscription is
     *  the current subscription used to setup Cellular Internet data. For example,
     *  it could be the current active opportunistic subscription in use, or the
     *  subscription user selected as default data subscription in DSDS mode.
     *
     *  @see #onActiveDataSubscriptionIdChanged
     *  @deprecated Use {@link TelephonyCallback.ActiveDataSubscriptionIdListener} instead.
     */
    @Deprecated
    public static final int LISTEN_ACTIVE_DATA_SUBSCRIPTION_ID_CHANGE = 4194304;

    /**
     * Listen for Barring Information for the current registered / camped cell.
     *
     * <p>Requires permission {@link android.Manifest.permission#READ_PRECISE_PHONE_STATE} or
     * the calling app has carrier privileges (see {@link TelephonyManager#hasCarrierPrivileges}).
     *
     * <p>Also requires the {@link Manifest.permission#ACCESS_FINE_LOCATION} permission, regardless
     * of whether the calling app has carrier privileges.
     *
     * @see #onBarringInfoChanged
     * @deprecated Use {@link TelephonyCallback.BarringInfoListener} instead.
     */
    @Deprecated
    public static final int LISTEN_BARRING_INFO = -2147483648;

    /**
     * Listen for call disconnect causes which contains {@link DisconnectCause} and
     * the precise disconnect cause.
     *
     * <p>Requires permission {@link android.Manifest.permission#READ_PRECISE_PHONE_STATE}
     * or the calling app has carrier privileges
     * (see {@link TelephonyManager#hasCarrierPrivileges}).
     *
     * @deprecated Use {@link TelephonyCallback.CallDisconnectCauseListener} instead.
     */
    @Deprecated
    public static final int LISTEN_CALL_DISCONNECT_CAUSES = 33554432;

    /**
     * Listen for changes to the call-forwarding indicator.
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
     * READ_PHONE_STATE} or that the calling app has carrier privileges (see
     * {@link TelephonyManager#hasCarrierPrivileges}).
     *
     * @see #onCallForwardingIndicatorChanged
     * @deprecated Use {@link TelephonyCallback.CallForwardingIndicatorListener} instead.
     */
    @Deprecated
    public static final int LISTEN_CALL_FORWARDING_INDICATOR = 8;

    /**
     * Listen for changes to the device call state.
     * {@more}
     *
     * @see #onCallStateChanged
     * @deprecated Use {@link TelephonyCallback.CallStateListener} instead.
     */
    @Deprecated
    public static final int LISTEN_CALL_STATE = 32;

    /**
     * Listen for changes to observed cell info.
     *
     * Listening to this event requires the {@link Manifest.permission#READ_PHONE_STATE} and
     * {@link Manifest.permission#ACCESS_FINE_LOCATION}
     * permission.
     *
     * @see #onCellInfoChanged
     * @deprecated Use {@link TelephonyCallback.CellInfoListener} instead.
     */
    @Deprecated
    public static final int LISTEN_CELL_INFO = 1024;

    /**
     * Listen for changes to the device's cell location. Note that
     * this will result in frequent callbacks to the listener.
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#ACCESS_FINE_LOCATION
     * ACCESS_FINE_LOCATION}
     * <p>
     * If you need regular location updates but want more control over
     * the update interval or location precision, you can set up a listener
     * through the {@link android.location.LocationManager location manager}
     * instead.
     *
     * @see #onCellLocationChanged
     * @deprecated Use {@link TelephonyCallback.CellLocationListener} instead.
     */
    @Deprecated
    public static final int LISTEN_CELL_LOCATION = 16;

    /**
     * Listen for changes to the direction of data traffic on the data
     * connection (cellular).
     * {@more}
     * Example: The status bar uses this to display the appropriate
     * data-traffic icon.
     *
     * @see #onDataActivity
     * @deprecated Use {@link TelephonyCallback.DataActivityListener} instead.
     */
    @Deprecated
    public static final int LISTEN_DATA_ACTIVITY = 128;

    /**
     * Listen for changes to the data connection state (cellular).
     *
     * @see #onDataConnectionStateChanged
     * @deprecated Use {@link TelephonyCallback.DataConnectionStateListener} instead.
     */
    @Deprecated
    public static final int LISTEN_DATA_CONNECTION_STATE = 64;

    /**
     *  Listen for display info changed event.
     *
     * For clients compiled on Android 11 SDK, requires permission:
     * {@link android.Manifest.permission#READ_PHONE_STATE} or that the calling app has carrier
     * privileges (see {@link TelephonyManager#hasCarrierPrivileges}).
     * For clients compiled on Android 12 SDK or newer,
     * {@link android.Manifest.permission#READ_PHONE_STATE} or carrier privileges is not required
     * anymore.
     *
     * @see #onDisplayInfoChanged
     * @deprecated Use {@link TelephonyCallback.DisplayInfoListener} instead.
     */
    @Deprecated
    public static final int LISTEN_DISPLAY_INFO_CHANGED = 1048576;

    /**
     * Listen for changes to emergency number list based on all active subscriptions.
     *
     * <p>Requires permission {@link android.Manifest.permission#READ_PHONE_STATE} or the calling
     * app has carrier privileges (see {@link TelephonyManager#hasCarrierPrivileges}).
     *
     * @deprecated Use {@link TelephonyCallback.EmergencyNumberListListener} instead.
     */
    @Deprecated
    public static final int LISTEN_EMERGENCY_NUMBER_LIST = 16777216;

    /**
     * Listen for IMS call disconnect causes which contains
     * {@link android.telephony.ims.ImsReasonInfo}
     *
     * <p>Requires permission {@link android.Manifest.permission#READ_PRECISE_PHONE_STATE}
     * or the calling app has carrier privileges
     * (see {@link TelephonyManager#hasCarrierPrivileges}).
     *
     * @see #onImsCallDisconnectCauseChanged(ImsReasonInfo)
     * @deprecated Use {@link TelephonyCallback.ImsCallDisconnectCauseListener} instead.
     */
    @Deprecated
    public static final int LISTEN_IMS_CALL_DISCONNECT_CAUSES = 134217728;

    /**
     * Listen for changes to the message-waiting indicator.
     * {@more}
     * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
     * READ_PHONE_STATE} or that the calling app has carrier privileges (see
     * {@link TelephonyManager#hasCarrierPrivileges}).
     * <p>
     * Example: The status bar uses this to determine when to display the
     * voicemail icon.
     *
     * @see #onMessageWaitingIndicatorChanged
     * @deprecated Use {@link TelephonyCallback.MessageWaitingIndicatorListener} instead.
     */
    @Deprecated
    public static final int LISTEN_MESSAGE_WAITING_INDICATOR = 4;

    /**
     * Stop listening for updates.
     *
     * The PhoneStateListener is not tied to any subscription and unregistered for any update.
     */
    @Deprecated
    public static final int LISTEN_NONE = 0;

    /**
     * Listen for {@link PreciseDataConnectionState} on the data connection (cellular).
     *
     * <p>Requires permission {@link android.Manifest.permission#READ_PRECISE_PHONE_STATE}
     * or the calling app has carrier privileges
     * (see {@link TelephonyManager#hasCarrierPrivileges}).
     *
     * @see #onPreciseDataConnectionStateChanged
     * @deprecated Use {@link TelephonyCallback.PreciseDataConnectionStateListener} instead.
     */
    @Deprecated
    public static final int LISTEN_PRECISE_DATA_CONNECTION_STATE = 4096;

    /**
     * Listen for Registration Failures.
     *
     * Listen for indications that a registration procedure has failed in either the CS or PS
     * domain. This indication does not necessarily indicate a change of service state, which should
     * be tracked via {@link #LISTEN_SERVICE_STATE}.
     *
     * <p>Requires permission {@link android.Manifest.permission#READ_PRECISE_PHONE_STATE} or
     * the calling app has carrier privileges (see {@link TelephonyManager#hasCarrierPrivileges}).
     *
     * <p>Also requires the {@link Manifest.permission#ACCESS_FINE_LOCATION} permission, regardless
     * of whether the calling app has carrier privileges.
     *
     * @see #onRegistrationFailed
     * @deprecated Use {@link TelephonyCallback.RegistrationFailedListener} instead.
     */
    @Deprecated
    public static final int LISTEN_REGISTRATION_FAILURE = 1073741824;

    /**
     *  Listen for changes to the network service state (cellular).
     *
     *  @see #onServiceStateChanged
     *  @see ServiceState
     *  @deprecated Use {@link TelephonyCallback.ServiceStateListener} instead.
     */
    @Deprecated
    public static final int LISTEN_SERVICE_STATE = 1;

    /**
     * Listen for changes to the network signal strength (cellular).
     * {@more}
     *
     * @see #onSignalStrengthChanged
     * @deprecated Use {@link TelephonyCallback.SignalStrengthsListener} instead.
     */
    @Deprecated
    public static final int LISTEN_SIGNAL_STRENGTH = 2;

    /**
     * Listen for changes to the network signal strengths (cellular).
     * <p>
     * Example: The status bar uses this to control the signal-strength
     * icon.
     *
     * @see #onSignalStrengthsChanged
     * @deprecated Use {@link TelephonyCallback.SignalStrengthsListener} instead.
     */
    @Deprecated
    public static final int LISTEN_SIGNAL_STRENGTHS = 256;

    /**
     *  Listen for changes to the user mobile data state
     *
     *  @see #onUserMobileDataStateChanged
     *  @deprecated Use {@link TelephonyCallback.UserMobileDataStateListener} instead.
     */
    @Deprecated
    public static final int LISTEN_USER_MOBILE_DATA_STATE = 524288;

    private PhoneStateListener() {
    }
}
