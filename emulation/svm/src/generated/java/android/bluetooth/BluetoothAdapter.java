package android.bluetooth;

/**
 * Represents the local device Bluetooth adapter. The {@link BluetoothAdapter} lets you perform
 * fundamental Bluetooth tasks, such as initiate device discovery, query a list of bonded (paired)
 * devices, instantiate a {@link BluetoothDevice} using a known MAC address, and create a {@link
 * BluetoothServerSocket} to listen for connection requests from other devices, and start a scan for
 * Bluetooth LE devices.
 *
 * <p>To get a {@link BluetoothAdapter} representing the local Bluetooth adapter, call the {@link
 * BluetoothManager#getAdapter} function on {@link BluetoothManager}. On JELLY_BEAN_MR1 and below
 * you will need to use the static {@link #getDefaultAdapter} method instead.
 *
 * <p>Fundamentally, this is your starting point for all Bluetooth actions. Once you have the local
 * adapter, you can get a set of {@link BluetoothDevice} objects representing all paired devices
 * with {@link #getBondedDevices()}; start device discovery with {@link #startDiscovery()}; or
 * create a {@link BluetoothServerSocket} to listen for incoming RFComm connection requests with
 * {@link #listenUsingRfcommWithServiceRecord(String, UUID)}; listen for incoming L2CAP
 * Connection-oriented Channels (CoC) connection requests with {@link #listenUsingL2capChannel()};
 * or start a scan for Bluetooth LE devices with {@link BluetoothLeScanner#startScan(ScanCallback)}
 * using the scanner from {@link #getBluetoothLeScanner()}.
 *
 * <p>This class is thread safe. <div class="special reference">
 *
 * <h3>Developer Guides</h3>
 *
 * <p>For more information about using Bluetooth, read the <a href=
 * "{@docRoot}guide/topics/connectivity/bluetooth.html">Bluetooth</a> developer guide. </div>
 *
 * @see BluetoothDevice
 * @see BluetoothServerSocket
 */
public final class BluetoothAdapter {
    /**
     * Intent used to broadcast the change in connection state of the local Bluetooth adapter to a
     * profile of the remote device. When the adapter is not connected to any profiles of any remote
     * devices and it attempts a connection to a profile this intent will be sent. Once connected,
     * this intent will not be sent for any more connection attempts to any profiles of any remote
     * device. When the adapter disconnects from the last profile its connected to of any remote
     * device, this intent will be sent.
     *
     * <p>This intent is useful for applications that are only concerned about whether the local
     * adapter is connected to any profile of any device and are not really concerned about which
     * profile. For example, an application which displays an icon to display whether Bluetooth is
     * connected or not can use this intent.
     *
     * <p>This intent will have 3 extras: {@link #EXTRA_CONNECTION_STATE} - The current connection
     * state. {@link #EXTRA_PREVIOUS_CONNECTION_STATE}- The previous connection state. {@link
     * BluetoothDevice#EXTRA_DEVICE} - The remote device.
     *
     * <p>{@link #EXTRA_CONNECTION_STATE} or {@link #EXTRA_PREVIOUS_CONNECTION_STATE} can be any of
     * {@link #STATE_DISCONNECTED}, {@link #STATE_CONNECTING}, {@link #STATE_CONNECTED}, {@link
     * #STATE_DISCONNECTING}.
     */
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED";

    /**
     * Broadcast Action: The local Bluetooth adapter has finished the device discovery process.
     */
    public static final String ACTION_DISCOVERY_FINISHED = "android.bluetooth.adapter.action.DISCOVERY_FINISHED";

    /**
     * Broadcast Action: The local Bluetooth adapter has started the remote device discovery
     * process.
     *
     * <p>This usually involves an inquiry scan of about 12 seconds, followed by a page scan of each
     * new device to retrieve its Bluetooth name.
     *
     * <p>Register for {@link BluetoothDevice#ACTION_FOUND} to be notified as remote Bluetooth
     * devices are found.
     *
     * <p>Device discovery is a heavyweight procedure. New connections to remote Bluetooth devices
     * should not be attempted while discovery is in progress, and existing connections will
     * experience limited bandwidth and high latency. Use {@link #cancelDiscovery()} to cancel an
     * ongoing discovery.
     */
    public static final String ACTION_DISCOVERY_STARTED = "android.bluetooth.adapter.action.DISCOVERY_STARTED";

    /**
     * Broadcast Action: The local Bluetooth adapter has changed its friendly Bluetooth name.
     *
     * <p>This name is visible to remote Bluetooth devices.
     *
     * <p>Always contains the extra field {@link #EXTRA_LOCAL_NAME} containing the name.
     */
    public static final String ACTION_LOCAL_NAME_CHANGED = "android.bluetooth.adapter.action.LOCAL_NAME_CHANGED";

    /**
     * Activity Action: Show a system activity that requests discoverable mode. This activity will
     * also request the user to turn on Bluetooth if it is not currently enabled.
     *
     * <p>Discoverable mode is equivalent to {@link #SCAN_MODE_CONNECTABLE_DISCOVERABLE}. It allows
     * remote devices to see this Bluetooth adapter when they perform a discovery.
     *
     * <p>For privacy, Android is not discoverable by default.
     *
     * <p>The sender of this Intent can optionally use extra field {@link
     * #EXTRA_DISCOVERABLE_DURATION} to request the duration of discoverability. Currently the
     * default duration is 120 seconds, and maximum duration is capped at 300 seconds for each
     * request.
     *
     * <p>Notification of the result of this activity is posted using the {@link
     * android.app.Activity#onActivityResult} callback. The <code>resultCode</code> will be the
     * duration (in seconds) of discoverability or {@link android.app.Activity#RESULT_CANCELED} if
     * the user rejected discoverability or an error has occurred.
     *
     * <p>Applications can also listen for {@link #ACTION_SCAN_MODE_CHANGED} for global notification
     * whenever the scan mode changes. For example, an application can be notified when the device
     * has ended discoverability.
     */
    public static final String ACTION_REQUEST_DISCOVERABLE = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE";

    /**
     * Activity Action: Show a system activity that allows the user to turn on Bluetooth.
     *
     * <p>This system activity will return once Bluetooth has completed turning on, or the user has
     * decided not to turn Bluetooth on.
     *
     * <p>Notification of the result of this activity is posted using the {@link
     * android.app.Activity#onActivityResult} callback. The <code>resultCode</code> will be {@link
     * android.app.Activity#RESULT_OK} if Bluetooth has been turned on or {@link
     * android.app.Activity#RESULT_CANCELED} if the user has rejected the request or an error has
     * occurred.
     *
     * <p>Applications can also listen for {@link #ACTION_STATE_CHANGED} for global notification
     * whenever Bluetooth is turned on or off.
     */
    public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";

    /**
     * Broadcast Action: Indicates the Bluetooth scan mode of the local Adapter has changed.
     *
     * <p>Always contains the extra fields {@link #EXTRA_SCAN_MODE} and {@link
     * #EXTRA_PREVIOUS_SCAN_MODE} containing the new and old scan modes respectively.
     */
    public static final String ACTION_SCAN_MODE_CHANGED = "android.bluetooth.adapter.action.SCAN_MODE_CHANGED";

    /**
     * Broadcast Action: The state of the local Bluetooth adapter has been changed.
     *
     * <p>For example, Bluetooth has been turned on or off.
     *
     * <p>Always contains the extra fields {@link #EXTRA_STATE} and {@link #EXTRA_PREVIOUS_STATE}
     * containing the new and old states respectively.
     */
    public static final String ACTION_STATE_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";

    /**
     * Sentinel error value for this class. Guaranteed to not equal any other integer constant in
     * this class. Provided as a convenience for functions that require a sentinel error value, for
     * example:
     *
     * <p><code>Intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
     * BluetoothAdapter.ERROR)</code>
     */
    public static final int ERROR = -2147483648;

    /**
     * Extra used by {@link #ACTION_CONNECTION_STATE_CHANGED}
     *
     * <p>This extra represents the current connection state.
     */
    public static final String EXTRA_CONNECTION_STATE = "android.bluetooth.adapter.extra.CONNECTION_STATE";

    /**
     * Used as an optional int extra field in {@link #ACTION_REQUEST_DISCOVERABLE} intents to
     * request a specific duration for discoverability in seconds. The current default is 120
     * seconds, and requests over 300 seconds will be capped. These values could change.
     */
    public static final String EXTRA_DISCOVERABLE_DURATION = "android.bluetooth.adapter.extra.DISCOVERABLE_DURATION";

    /**
     * Used as a String extra field in {@link #ACTION_LOCAL_NAME_CHANGED} intents to request the
     * local Bluetooth name.
     */
    public static final String EXTRA_LOCAL_NAME = "android.bluetooth.adapter.extra.LOCAL_NAME";

    /**
     * Extra used by {@link #ACTION_CONNECTION_STATE_CHANGED}
     *
     * <p>This extra represents the previous connection state.
     */
    public static final String EXTRA_PREVIOUS_CONNECTION_STATE = "android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE";

    /**
     * Used as an int extra field in {@link #ACTION_SCAN_MODE_CHANGED} intents to request the
     * previous scan mode. Possible values are: {@link #SCAN_MODE_NONE}, {@link
     * #SCAN_MODE_CONNECTABLE}, {@link #SCAN_MODE_CONNECTABLE_DISCOVERABLE},
     */
    public static final String EXTRA_PREVIOUS_SCAN_MODE = "android.bluetooth.adapter.extra.PREVIOUS_SCAN_MODE";

    /**
     * Used as an int extra field in {@link #ACTION_STATE_CHANGED} intents to request the previous
     * power state. Possible values are: {@link #STATE_OFF}, {@link #STATE_TURNING_ON}, {@link
     * #STATE_ON}, {@link #STATE_TURNING_OFF}
     */
    public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.adapter.extra.PREVIOUS_STATE";

    /**
     * Used as an int extra field in {@link #ACTION_SCAN_MODE_CHANGED} intents to request the
     * current scan mode. Possible values are: {@link #SCAN_MODE_NONE}, {@link
     * #SCAN_MODE_CONNECTABLE}, {@link #SCAN_MODE_CONNECTABLE_DISCOVERABLE},
     */
    public static final String EXTRA_SCAN_MODE = "android.bluetooth.adapter.extra.SCAN_MODE";

    /**
     * Used as an int extra field in {@link #ACTION_STATE_CHANGED} intents to request the current
     * power state. Possible values are: {@link #STATE_OFF}, {@link #STATE_TURNING_ON}, {@link
     * #STATE_ON}, {@link #STATE_TURNING_OFF},
     */
    public static final String EXTRA_STATE = "android.bluetooth.adapter.extra.STATE";

    /**
     * Indicates that inquiry scan is disabled, but page scan is enabled on the local Bluetooth
     * adapter. Therefore this device is not discoverable from remote Bluetooth devices, but is
     * connectable from remote devices that have previously discovered this device.
     */
    public static final int SCAN_MODE_CONNECTABLE = 21;

    /**
     * Indicates that both inquiry scan and page scan are enabled on the local Bluetooth adapter.
     * Therefore this device is both discoverable and connectable from remote Bluetooth devices.
     */
    public static final int SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23;

    /**
     * Indicates that both inquiry scan and page scan are disabled on the local Bluetooth adapter.
     * Therefore this device is neither discoverable nor connectable from remote Bluetooth devices.
     */
    public static final int SCAN_MODE_NONE = 20;

    /**
     * The profile is in connected state
     */
    public static final int STATE_CONNECTED = 2;

    /**
     * The profile is in connecting state
     */
    public static final int STATE_CONNECTING = 1;

    /**
     * The profile is in disconnected state
     */
    public static final int STATE_DISCONNECTED = 0;

    /**
     * The profile is in disconnecting state
     */
    public static final int STATE_DISCONNECTING = 3;

    /**
     * Indicates the local Bluetooth adapter is off.
     */
    public static final int STATE_OFF = 10;

    /**
     * Indicates the local Bluetooth adapter is on, and ready for use.
     */
    public static final int STATE_ON = 12;

    /**
     * Indicates the local Bluetooth adapter is turning off. Local clients should immediately
     * attempt graceful disconnection of any remote links.
     */
    public static final int STATE_TURNING_OFF = 13;

    /**
     * Indicates the local Bluetooth adapter is turning on. However local clients should wait for
     * {@link #STATE_ON} before attempting to use the adapter.
     */
    public static final int STATE_TURNING_ON = 11;

    private BluetoothAdapter() {
    }
}
