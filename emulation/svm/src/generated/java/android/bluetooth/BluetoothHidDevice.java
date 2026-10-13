package android.bluetooth;

/**
 * Provides the public APIs to control the Bluetooth HID Device profile.
 *
 * <p>BluetoothHidDevice is a proxy object for controlling the Bluetooth HID Device Service via IPC.
 * Use {@link BluetoothAdapter#getProfileProxy} to get the BluetoothHidDevice proxy object.
 */
public final class BluetoothHidDevice implements BluetoothProfile {
    /**
     * Intent used to broadcast the change in connection state of the Input Host profile.
     *
     * <p>This intent will have 3 extras:
     *
     * <ul>
     *   <li>{@link #EXTRA_STATE} - The current state of the profile.
     *   <li>{@link #EXTRA_PREVIOUS_STATE}- The previous state of the profile.
     *   <li>{@link BluetoothDevice#EXTRA_DEVICE} - The remote device.
     * </ul>
     *
     * <p>{@link #EXTRA_STATE} or {@link #EXTRA_PREVIOUS_STATE} can be any of {@link
     * #STATE_DISCONNECTED}, {@link #STATE_CONNECTING}, {@link #STATE_CONNECTED}, {@link
     * #STATE_DISCONNECTING}.
     */
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED";

    /**
     * Constant representing error response for Set Report due to "invalid parameter".
     *
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     */
    public static final byte ERROR_RSP_INVALID_PARAM = 4;

    /**
     * Constant representing error response for Set Report due to "invalid report ID".
     *
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     */
    public static final byte ERROR_RSP_INVALID_RPT_ID = 2;

    /**
     * Constant representing error response for Set Report due to "not ready".
     *
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     */
    public static final byte ERROR_RSP_NOT_READY = 1;

    /**
     * Constant representing success response for Set Report.
     *
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     */
    public static final byte ERROR_RSP_SUCCESS = 0;

    /**
     * Constant representing error response for Set Report with unknown reason.
     *
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     */
    public static final byte ERROR_RSP_UNKNOWN = 14;

    /**
     * Constant representing error response for Set Report due to "unsupported request".
     *
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     */
    public static final byte ERROR_RSP_UNSUPPORTED_REQ = 3;

    /**
     * Constant representing boot protocol mode used set by host. Default is always {@link
     * #PROTOCOL_REPORT_MODE} unless notified otherwise.
     *
     * @see Callback#onSetProtocol(BluetoothDevice, byte)
     */
    public static final byte PROTOCOL_BOOT_MODE = 0;

    /**
     * Constant representing report protocol mode used set by host. Default is always {@link
     * #PROTOCOL_REPORT_MODE} unless notified otherwise.
     *
     * @see Callback#onSetProtocol(BluetoothDevice, byte)
     */
    public static final byte PROTOCOL_REPORT_MODE = 1;

    /**
     * Constant representing HID Feature Report type.
     *
     * @see Callback#onGetReport(BluetoothDevice, byte, byte, int)
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     * @see Callback#onInterruptData(BluetoothDevice, byte, byte[])
     */
    public static final byte REPORT_TYPE_FEATURE = 3;

    /**
     * Constant representing HID Input Report type.
     *
     * @see Callback#onGetReport(BluetoothDevice, byte, byte, int)
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     * @see Callback#onInterruptData(BluetoothDevice, byte, byte[])
     */
    public static final byte REPORT_TYPE_INPUT = 1;

    /**
     * Constant representing HID Output Report type.
     *
     * @see Callback#onGetReport(BluetoothDevice, byte, byte, int)
     * @see Callback#onSetReport(BluetoothDevice, byte, byte, byte[])
     * @see Callback#onInterruptData(BluetoothDevice, byte, byte[])
     */
    public static final byte REPORT_TYPE_OUTPUT = 2;

    /**
     * Constant representing combo keyboard and mouse subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS1_COMBO = -64;

    /**
     * Constant representing keyboard subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS1_KEYBOARD = 64;

    /**
     * Constant representing mouse subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS1_MOUSE = -128;

    /**
     * Constant representing unspecified HID device subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS1_NONE = 0;

    /**
     * Constant representing card reader subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS2_CARD_READER = 6;

    /**
     * Constant representing digitizer tablet subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS2_DIGITIZER_TABLET = 5;

    /**
     * Constant representing gamepad subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS2_GAMEPAD = 2;

    /**
     * Constant representing joystick subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS2_JOYSTICK = 1;

    /**
     * Constant representing remote control subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS2_REMOTE_CONTROL = 3;

    /**
     * Constant representing sensing device subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS2_SENSING_DEVICE = 4;

    /**
     * Constant representing uncategorized HID device subclass.
     *
     * @see #registerApp (BluetoothHidDeviceAppQosSettings, BluetoothHidDeviceAppQosSettings,
     *     BluetoothHidDeviceAppQosSettings, Executor, Callback)
     */
    public static final byte SUBCLASS2_UNCATEGORIZED = 0;

    private BluetoothHidDevice() {
    }
}
