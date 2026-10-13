package android.bluetooth;

/**
 * Public APIs for the Bluetooth Profiles.
 *
 * <p>Clients should call {@link BluetoothAdapter#getProfileProxy}, to get the Profile Proxy. Each
 * public profile implements this interface.
 */
public interface BluetoothProfile {
    /**
     * A2DP profile.
     */
    int A2DP = 2;

    /**
     * Coordinated Set Identification Profile set coordinator
     */
    int CSIP_SET_COORDINATOR = 25;

    /**
     * Extra for the connection state intents of the individual profiles.
     *
     * <p>This extra represents the previous connection state of the profile of the Bluetooth
     * device.
     */
    String EXTRA_PREVIOUS_STATE = "android.bluetooth.profile.extra.PREVIOUS_STATE";

    /**
     * Extra for the connection state intents of the individual profiles.
     *
     * <p>This extra represents the current connection state of the profile of the Bluetooth device.
     */
    String EXTRA_STATE = "android.bluetooth.profile.extra.STATE";

    /**
     * GATT
     */
    int GATT = 7;

    /**
     * GATT_SERVER
     */
    int GATT_SERVER = 8;

    int HAP_CLIENT = 28;

    /**
     * Headset and Handsfree profile
     */
    int HEADSET = 1;

    /**
     * Health Profile
     *
     * @deprecated Health Device Profile (HDP) and MCAP protocol are no longer used. New apps should
     *     use Bluetooth Low Energy based solutions such as {@link BluetoothGatt}, {@link
     *     BluetoothAdapter#listenUsingL2capChannel()}, or {@link
     *     BluetoothDevice#createL2capChannel(int)}
     */
    @Deprecated
    int HEALTH = 3;

    /**
     * Hearing Aid Device
     */
    int HEARING_AID = 21;

    /**
     * HID Device
     */
    int HID_DEVICE = 19;

    /**
     * LE Audio Device
     */
    int LE_AUDIO = 22;

    /**
     * SAP Profile
     */
    int SAP = 10;

    /**
     * The profile is in connected state
     */
    int STATE_CONNECTED = 2;

    /**
     * The profile is in connecting state
     */
    int STATE_CONNECTING = 1;

    /**
     * The profile is in disconnected state
     */
    int STATE_DISCONNECTED = 0;

    /**
     * The profile is in disconnecting state
     */
    int STATE_DISCONNECTING = 3;
}
