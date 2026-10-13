package android.bluetooth;

/**
 * This class provides the public APIs to control the Bluetooth CSIP set coordinator.
 *
 * <p>BluetoothCsipSetCoordinator is a proxy object for controlling the Bluetooth CSIP set Service
 * via IPC. Use {@link BluetoothAdapter#getProfileProxy} to get the BluetoothCsipSetCoordinator
 * proxy object.
 */
public final class BluetoothCsipSetCoordinator implements BluetoothProfile {
    /**
     * Intent used to broadcast the change in connection state of the CSIS Client.
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
    public static final String ACTION_CSIS_CONNECTION_STATE_CHANGED = "android.bluetooth.action.CSIS_CONNECTION_STATE_CHANGED";

    private BluetoothCsipSetCoordinator() {
    }
}
