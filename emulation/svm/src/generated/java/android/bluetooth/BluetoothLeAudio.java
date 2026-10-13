package android.bluetooth;

/**
 * This class provides the public APIs to control the LeAudio profile.
 *
 * <p>BluetoothLeAudio is a proxy object for controlling the Bluetooth LE Audio Service via IPC. Use
 * {@link BluetoothAdapter#getProfileProxy} to get the BluetoothLeAudio proxy object.
 *
 * <p>Android only supports one set of connected Bluetooth LeAudio device at a time. Each method is
 * protected with its appropriate permission.
 */
public final class BluetoothLeAudio implements BluetoothProfile {
    /**
     * Intent used to broadcast the change in connection state of the LeAudio profile. Please note
     * that in the binaural case, there will be two different LE devices for the left and right side
     * and each device will have their own connection state changes.
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
    public static final String ACTION_LE_AUDIO_CONNECTION_STATE_CHANGED = "android.bluetooth.action.LE_AUDIO_CONNECTION_STATE_CHANGED";

    /**
     * This represents an invalid group ID.
     */
    public static final int GROUP_ID_INVALID = -1;

    private BluetoothLeAudio() {
    }
}
