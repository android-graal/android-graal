package android.companion;

/**
 * Public interfaces for managing companion devices.
 *
 * <p>The interfaces in this class allow companion apps to
 * {@link #associate(AssociationRequest, Executor, Callback)} discover and request device profiles}
 * for companion devices, {@link #startObservingDevicePresence(String) listen to device presence
 * events}, {@link #startSystemDataTransfer(int, Executor, OutcomeReceiver) transfer system level
 * data} via {@link #attachSystemDataTransport(int, InputStream, OutputStream) the reported
 * channel} and more.</p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about managing companion devices, read the <a href=
 * "{@docRoot}guide/topics/connectivity/companion-device-pairing">Companion Device Pairing</a>
 * developer guide.
 * </div>
 */
public final class CompanionDeviceManager {
    /**
     * Extra field name for the {@link AssociationInfo} object, included into
     * {@link android.content.Intent} which application receive in
     * {@link Activity#onActivityResult(int, int, Intent)} after the application's
     * {@link AssociationRequest} was successfully processed and an association was created.
     */
    public static final String EXTRA_ASSOCIATION = "android.companion.extra.ASSOCIATION";

    /**
     * A device, returned in the activity result of the {@link IntentSender} received in
     * {@link Callback#onDeviceFound}
     *
     * Type is:
     * <ul>
     *     <li>for classic Bluetooth - {@link android.bluetooth.BluetoothDevice}</li>
     *     <li>for Bluetooth LE - {@link android.bluetooth.le.ScanResult}</li>
     *     <li>for WiFi - {@link android.net.wifi.ScanResult}</li>
     * </ul>
     *
     * @deprecated use {@link AssociationInfo#getAssociatedDevice()} instead.
     */
    @Deprecated
    public static final String EXTRA_DEVICE = "android.companion.extra.DEVICE";

    /**
     * Used by {@link #enableSystemDataSyncForTypes(int, int)}}.
     * Sync call metadata like muting, ending and silencing a call.
     */
    public static final int FLAG_CALL_METADATA = 1;

    /**
     * The result code to propagate back to the user activity, indicates if the association dialog
     * is implicitly cancelled.
     * E.g. phone is locked, switch to another app or press outside the dialog.
     */
    public static final int RESULT_CANCELED = 0;

    /**
     * The result code to propagate back to the user activity, indicates the association
     * dialog is dismissed if there's no device found after 20 seconds.
     */
    public static final int RESULT_DISCOVERY_TIMEOUT = 2;

    /**
     * The result code to propagate back to the user activity, indicates the internal error
     * in CompanionDeviceManager.
     */
    public static final int RESULT_INTERNAL_ERROR = 3;

    /**
     * The result code to propagate back to the user activity, indicates the association
     * is created successfully.
     */
    public static final int RESULT_OK = -1;

    /**
     * The result code to propagate back to the user activity and
     * {@link Callback#onFailure(int, CharSequence)}, indicates app is not allow to create the
     * association due to the security issue.
     * E.g. There are missing necessary permissions when creating association.
     */
    public static final int RESULT_SECURITY_ERROR = 4;

    /**
     * The result code to propagate back to the user activity, indicates the association dialog
     * is explicitly declined by the users.
     */
    public static final int RESULT_USER_REJECTED = 1;

    private CompanionDeviceManager() {
    }
}
