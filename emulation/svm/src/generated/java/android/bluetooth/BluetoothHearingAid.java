package android.bluetooth;

/**
 * This class provides the public APIs to control the Hearing Aid profile.
 *
 * <p>BluetoothHearingAid is a proxy object for controlling the Bluetooth Hearing Aid Service via
 * IPC. Use {@link BluetoothAdapter#getProfileProxy} to get the BluetoothHearingAid proxy object.
 *
 * <p>Android only supports one set of connected Bluetooth Hearing Aid device at a time. Each method
 * is protected with its appropriate permission.
 */
public final class BluetoothHearingAid implements BluetoothProfile {
    /**
     * Intent used to broadcast the change in connection state of the Hearing Aid profile. Please
     * note that in the binaural case, there will be two different LE devices for the left and right
     * side and each device will have their own connection state changes.S
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
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED";

    private BluetoothHearingAid() {
    }
}
