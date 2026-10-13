package android.bluetooth;

/**
 * Public API for controlling the Bluetooth Headset Service. This includes both Bluetooth Headset
 * and Handsfree (v1.5) profiles.
 *
 * <p>BluetoothHeadset is a proxy object for controlling the Bluetooth Headset Service via IPC.
 *
 * <p>Use {@link BluetoothAdapter#getProfileProxy} to get the BluetoothHeadset proxy object. Use
 * {@link BluetoothAdapter#closeProfileProxy} to close the service connection.
 *
 * <p>Android only supports one connected Bluetooth Headset at a time. Each method is protected with
 * its appropriate permission.
 */
public final class BluetoothHeadset implements BluetoothProfile {
    /**
     * Intent used to broadcast the change in the Audio Connection state of the HFP profile.
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
     * #STATE_AUDIO_CONNECTED}, {@link #STATE_AUDIO_DISCONNECTED},
     */
    public static final String ACTION_AUDIO_STATE_CHANGED = "android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED";

    /**
     * Intent used to broadcast the change in connection state of the Headset profile.
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
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED";

    /**
     * Intent used to broadcast that the headset has posted a vendor-specific event.
     *
     * <p>This intent will have 4 extras and 1 category.
     *
     * <ul>
     *   <li>{@link BluetoothDevice#EXTRA_DEVICE} - The remote Bluetooth Device
     *   <li>{@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD} - The vendor specific command
     *   <li>{@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE} - The AT command type which can
     *       be one of {@link #AT_CMD_TYPE_READ}, {@link #AT_CMD_TYPE_TEST}, or {@link
     *       #AT_CMD_TYPE_SET}, {@link #AT_CMD_TYPE_BASIC},{@link #AT_CMD_TYPE_ACTION}.
     *   <li>{@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_ARGS} - Command arguments.
     * </ul>
     *
     * <p>The category is the Company ID of the vendor defining the vendor-specific command. {@link
     * BluetoothAssignedNumbers}
     *
     * <p>For example, for Plantronics specific events Category will be {@link
     * #VENDOR_SPECIFIC_HEADSET_EVENT_COMPANY_ID_CATEGORY}.55
     *
     * <p>For example, an AT+XEVENT=foo,3 will get translated into
     *
     * <ul>
     *   <li>EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD = +XEVENT
     *   <li>EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE = AT_CMD_TYPE_SET
     *   <li>EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_ARGS = foo, 3
     * </ul>
     */
    public static final String ACTION_VENDOR_SPECIFIC_HEADSET_EVENT = "android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT";

    /**
     * AT command type ACTION used with {@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE} For
     * example, AT+CHUP. There are no arguments for action commands.
     */
    public static final int AT_CMD_TYPE_ACTION = 4;

    /**
     * AT command type BASIC used with {@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE} For
     * example, ATD. Single character commands and everything following the character are arguments.
     */
    public static final int AT_CMD_TYPE_BASIC = 3;

    /**
     * AT command type READ used with {@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE} For
     * example, AT+VGM?. There are no arguments for this command type.
     */
    public static final int AT_CMD_TYPE_READ = 0;

    /**
     * AT command type SET used with {@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE} For
     * example, AT+VGM=<args>.
     */
    public static final int AT_CMD_TYPE_SET = 2;

    /**
     * AT command type TEST used with {@link #EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE} For
     * example, AT+VGM=?. There are no arguments for this command type.
     */
    public static final int AT_CMD_TYPE_TEST = 1;

    /**
     * A Parcelable String array extra field in {@link #ACTION_VENDOR_SPECIFIC_HEADSET_EVENT}
     * intents that contains the arguments to the vendor-specific command.
     */
    public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_ARGS = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS";

    /**
     * A String extra field in {@link #ACTION_VENDOR_SPECIFIC_HEADSET_EVENT} intents that contains
     * the name of the vendor-specific command.
     */
    public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD";

    /**
     * An int extra field in {@link #ACTION_VENDOR_SPECIFIC_HEADSET_EVENT} intents that contains the
     * AT command type of the vendor-specific command.
     */
    public static final String EXTRA_VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE = "android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE";

    /**
     * Headset state when SCO audio is connected. This state can be one of {@link #EXTRA_STATE} or
     * {@link #EXTRA_PREVIOUS_STATE} of {@link #ACTION_AUDIO_STATE_CHANGED} intent.
     */
    public static final int STATE_AUDIO_CONNECTED = 12;

    /**
     * Headset state when SCO audio is connecting. This state can be one of {@link #EXTRA_STATE} or
     * {@link #EXTRA_PREVIOUS_STATE} of {@link #ACTION_AUDIO_STATE_CHANGED} intent.
     */
    public static final int STATE_AUDIO_CONNECTING = 11;

    /**
     * Headset state when SCO audio is not connected. This state can be one of {@link #EXTRA_STATE}
     * or {@link #EXTRA_PREVIOUS_STATE} of {@link #ACTION_AUDIO_STATE_CHANGED} intent.
     */
    public static final int STATE_AUDIO_DISCONNECTED = 10;

    /**
     * A vendor-specific command for unsolicited result code.
     */
    public static final String VENDOR_RESULT_CODE_COMMAND_ANDROID = "+ANDROID";

    /**
     * The intent category to be used with {@link #ACTION_VENDOR_SPECIFIC_HEADSET_EVENT} for the
     * companyId
     */
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_COMPANY_ID_CATEGORY = "android.bluetooth.headset.intent.category.companyid";

    private BluetoothHeadset() {
    }
}
