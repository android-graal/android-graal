package android.bluetooth;

/**
 * This class provides the public APIs to control the Bluetooth A2DP profile.
 *
 * <p>BluetoothA2dp is a proxy object for controlling the Bluetooth A2DP Service via IPC. Use {@link
 * BluetoothAdapter#getProfileProxy} to get the BluetoothA2dp proxy object.
 *
 * <p>Android only supports one connected Bluetooth A2dp device at a time. Each method is protected
 * with its appropriate permission.
 */
public final class BluetoothA2dp implements BluetoothProfile {
    /**
     * Intent used to broadcast the change in connection state of the A2DP profile.
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
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED";

    /**
     * Intent used to broadcast the change in the Playing state of the A2DP profile.
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
     * #STATE_PLAYING}, {@link #STATE_NOT_PLAYING},
     */
    public static final String ACTION_PLAYING_STATE_CHANGED = "android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED";

    /**
     * A2DP sink device is NOT streaming music. This state can be one of {@link #EXTRA_STATE} or
     * {@link #EXTRA_PREVIOUS_STATE} of {@link #ACTION_PLAYING_STATE_CHANGED} intent.
     */
    public static final int STATE_NOT_PLAYING = 11;

    /**
     * A2DP sink device is streaming music. This state can be one of {@link #EXTRA_STATE} or {@link
     * #EXTRA_PREVIOUS_STATE} of {@link #ACTION_PLAYING_STATE_CHANGED} intent.
     */
    public static final int STATE_PLAYING = 10;

    private BluetoothA2dp() {
    }
}
