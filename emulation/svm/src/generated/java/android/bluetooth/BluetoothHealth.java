package android.bluetooth;

/**
 * Public API for Bluetooth Health Profile.
 *
 * <p>BluetoothHealth is a proxy object for controlling the Bluetooth Service via IPC.
 *
 * <p>How to connect to a health device which is acting in the source role.
 * <li>Use {@link BluetoothAdapter#getProfileProxy} to get the BluetoothHealth proxy object.
 * <li>Create an {@link BluetoothHealth} callback and call {@link #registerSinkAppConfiguration} to
 *     register an application configuration
 * <li>Pair with the remote device. This currently needs to be done manually from Bluetooth Settings
 * <li>Connect to a health device using {@link #connectChannelToSource}. Some devices will connect
 *     the channel automatically. The {@link BluetoothHealth} callback will inform the application
 *     of channel state change.
 * <li>Use the file descriptor provided with a connected channel to read and write data to the
 *     health channel.
 * <li>The received data needs to be interpreted using a health manager which implements the IEEE
 *     11073-xxxxx specifications.
 * <li>When done, close the health channel by calling {@link #disconnectChannel} and unregister the
 *     application configuration calling {@link #unregisterAppConfiguration}
 *
 * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should use
 *     Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
 *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
 *     BluetoothDevice#createL2capChannel(int)}
 */
@Deprecated
public final class BluetoothHealth implements BluetoothProfile {
    /**
     * Health App Configuration registration failure
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int APP_CONFIG_REGISTRATION_FAILURE = 1;

    /**
     * Health App Configuration registration success
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int APP_CONFIG_REGISTRATION_SUCCESS = 0;

    /**
     * Health App Configuration un-registration failure
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int APP_CONFIG_UNREGISTRATION_FAILURE = 3;

    /**
     * Health App Configuration un-registration success
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int APP_CONFIG_UNREGISTRATION_SUCCESS = 2;

    /**
     * Health Profile - Channel Type used - Reliable
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int CHANNEL_TYPE_RELIABLE = 10;

    /**
     * Health Profile - Channel Type used - Streaming
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int CHANNEL_TYPE_STREAMING = 11;

    /**
     * Health Profile Sink Role the device talking to the health device.
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int SINK_ROLE = 2;

    /**
     * Health Profile Source Role - the health device.
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int SOURCE_ROLE = 1;

    /**
     * Health Channel Connection State - Connected
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int STATE_CHANNEL_CONNECTED = 2;

    /**
     * Health Channel Connection State - Connecting
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int STATE_CHANNEL_CONNECTING = 1;

    /**
     * Health Channel Connection State - Disconnected
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int STATE_CHANNEL_DISCONNECTED = 0;

    /**
     * Health Channel Connection State - Disconnecting
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    public static final int STATE_CHANNEL_DISCONNECTING = 3;

    private BluetoothHealth() {
    }
}
