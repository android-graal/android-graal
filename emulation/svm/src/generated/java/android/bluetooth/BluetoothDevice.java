package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents a remote Bluetooth device. A {@link BluetoothDevice} lets you create a connection with
 * the respective device or query information about it, such as the name, address, class, and
 * bonding state.
 *
 * <p>This class is really just a thin wrapper for a Bluetooth hardware address. Objects of this
 * class are immutable. Operations on this class are performed on the remote Bluetooth hardware
 * address, using the {@link BluetoothAdapter} that was used to create this {@link BluetoothDevice}.
 *
 * <p>To get a {@link BluetoothDevice}, use {@link BluetoothAdapter#getRemoteDevice(String)
 * BluetoothAdapter.getRemoteDevice(String)} to create one representing a device of a known MAC
 * address (which you can get through device discovery with {@link BluetoothAdapter}) or get one
 * from the set of bonded devices returned by {@link BluetoothAdapter#getBondedDevices()
 * BluetoothAdapter.getBondedDevices()}. You can then open a {@link BluetoothSocket} for
 * communication with the remote device, using {@link #createRfcommSocketToServiceRecord(UUID)} over
 * Bluetooth BR/EDR or using {@link #createL2capChannel(int)} over Bluetooth LE.
 *
 * <p><div class="special reference">
 *
 * <h3>Developer Guides</h3>
 *
 * <p>For more information about using Bluetooth, read the <a href=
 * "{@docRoot}guide/topics/connectivity/bluetooth.html">Bluetooth</a> developer guide. </div>
 *
 * @see BluetoothAdapter
 * @see BluetoothSocket
 */
public final class BluetoothDevice implements Parcelable {
    /**
     * Broadcast Action: Indicates a low level (ACL) connection has been established with a remote
     * device.
     *
     * <p>Always contains the extra fields {@link #EXTRA_DEVICE} and {@link #EXTRA_TRANSPORT}.
     *
     * <p>ACL connections are managed automatically by the Android Bluetooth stack.
     */
    public static final String ACTION_ACL_CONNECTED = "android.bluetooth.device.action.ACL_CONNECTED";

    /**
     * Broadcast Action: Indicates a low level (ACL) disconnection from a remote device.
     *
     * <p>Always contains the extra fields {@link #EXTRA_DEVICE} and {@link #EXTRA_TRANSPORT}.
     *
     * <p>ACL connections are managed automatically by the Android Bluetooth stack.
     */
    public static final String ACTION_ACL_DISCONNECTED = "android.bluetooth.device.action.ACL_DISCONNECTED";

    /**
     * Broadcast Action: Indicates that a low level (ACL) disconnection has been requested for a
     * remote device, and it will soon be disconnected.
     *
     * <p>This is useful for graceful disconnection. Applications should use this intent as a hint
     * to immediately terminate higher level connections (RFCOMM, L2CAP, or profile connections) to
     * the remote device.
     *
     * <p>Always contains the extra field {@link #EXTRA_DEVICE}.
     */
    public static final String ACTION_ACL_DISCONNECT_REQUESTED = "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED";

    /**
     * Broadcast Action: Indicates the alias of a remote device has been changed.
     *
     * <p>Always contains the extra field {@link #EXTRA_DEVICE}.
     */
    public static final String ACTION_ALIAS_CHANGED = "android.bluetooth.device.action.ALIAS_CHANGED";

    /**
     * Broadcast Action: Indicates a change in the bond state of a remote device. For example, if a
     * device is bonded (paired).
     *
     * <p>Always contains the extra fields {@link #EXTRA_DEVICE}, {@link #EXTRA_BOND_STATE} and
     * {@link #EXTRA_PREVIOUS_BOND_STATE}.
     */
    public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";

    /**
     * Broadcast Action: Bluetooth class of a remote device has changed.
     *
     * <p>Always contains the extra fields {@link #EXTRA_DEVICE} and {@link #EXTRA_CLASS}.
     *
     * @see BluetoothClass
     */
    public static final String ACTION_CLASS_CHANGED = "android.bluetooth.device.action.CLASS_CHANGED";

    /**
     * Broadcast Action: Indicates that encryption state changed
     *
     * <p>Always contains the extra field {@link #EXTRA_DEVICE}
     *
     * <p>Always contains the extra field {@link #EXTRA_TRANSPORT}
     *
     * <p>Always contains the extra field {@link #EXTRA_ENCRYPTION_STATUS}
     *
     * <p>Always contains the extra field {@link #EXTRA_ENCRYPTION_ENABLED}
     *
     * <p>Always contains the extra field {@link #EXTRA_KEY_SIZE}
     *
     * <p>Always contains the extra field {@link #EXTRA_ENCRYPTION_ALGORITHM}
     */
    public static final String ACTION_ENCRYPTION_CHANGE = "android.bluetooth.device.action.ENCRYPTION_CHANGE";

    /**
     * Broadcast Action: Remote device discovered.
     *
     * <p>Sent when a remote device is found during discovery.
     *
     * <p>Always contains the extra fields {@link #EXTRA_DEVICE} and {@link #EXTRA_CLASS}. Can
     * contain the extra fields {@link #EXTRA_NAME} and/or {@link #EXTRA_RSSI} and/or {@link
     * #EXTRA_IS_COORDINATED_SET_MEMBER} if they are available.
     */
    public static final String ACTION_FOUND = "android.bluetooth.device.action.FOUND";

    /**
     * Broadcast Action: Indicates that previously bonded device couldn't provide keys to establish
     * encryption. This can have numerous reasons, i.e.:
     *
     * <ul>
     *   <li>remote was factory reset, or removed bond
     *   <li>spoofing attack, someone is impersonating remote device
     *   <li>in case of LE devices, very unlikely address collision
     * </ul>
     *
     * <p>Always contains the extra field {@link #EXTRA_DEVICE}
     *
     * <p>This method requires the calling app to have the {@link
     * android.Manifest.permission#BLUETOOTH_CONNECT} permission. Before {@link
     * android.os.Build.VERSION_CODES#BAKLAVA} this method also required {@link
     * android.Manifest.permission#BLUETOOTH_PRIVILEGED}
     */
    public static final String ACTION_KEY_MISSING = "android.bluetooth.device.action.KEY_MISSING";

    /**
     * Broadcast Action: Indicates the friendly name of a remote device has been retrieved for the
     * first time, or changed since the last retrieval.
     *
     * <p>Always contains the extra fields {@link #EXTRA_DEVICE} and {@link #EXTRA_NAME}.
     */
    public static final String ACTION_NAME_CHANGED = "android.bluetooth.device.action.NAME_CHANGED";

    /**
     * Broadcast Action: This intent is used to broadcast PAIRING REQUEST
     */
    public static final String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";

    /**
     * Broadcast Action: This intent is used to broadcast the {@link UUID} wrapped as a {@link
     * android.os.ParcelUuid} of the remote device after it has been fetched. This intent is sent
     * only when the UUIDs of the remote device are requested to be fetched using Service Discovery
     * Protocol
     *
     * <p>Always contains the extra field {@link #EXTRA_DEVICE}
     *
     * <p>Always contains the extra field {@link #EXTRA_UUID}
     */
    public static final String ACTION_UUID = "android.bluetooth.device.action.UUID";

    /**
     * Address type used to indicate an anonymous advertisement.
     */
    public static final int ADDRESS_TYPE_ANONYMOUS = 255;

    /**
     * Hardware MAC Address of the device
     */
    public static final int ADDRESS_TYPE_PUBLIC = 0;

    /**
     * Address is either resolvable, non-resolvable or static.
     */
    public static final int ADDRESS_TYPE_RANDOM = 1;

    /**
     * Address type is unknown or unavailable
     */
    public static final int ADDRESS_TYPE_UNKNOWN = 65535;

    /**
     * Indicates the remote device is bonded (paired).
     *
     * <p>A shared link keys exists locally for the remote device, so communication can be
     * authenticated and encrypted.
     *
     * <p><i>Being bonded (paired) with a remote device does not necessarily mean the device is
     * currently connected. It just means that the pending procedure was completed at some earlier
     * time, and the link key is still stored locally, ready to use on the next connection. </i>
     */
    public static final int BOND_BONDED = 12;

    /**
     * Indicates bonding (pairing) is in progress with the remote device.
     */
    public static final int BOND_BONDING = 11;

    /**
     * Indicates the remote device is not bonded (paired).
     *
     * <p>There is no shared link key with the remote device, so communication (if it is allowed at
     * all) will be unauthenticated and unencrypted.
     */
    public static final int BOND_NONE = 10;

    /**
     * Bluetooth device type, Classic - BR/EDR devices
     */
    public static final int DEVICE_TYPE_CLASSIC = 1;

    /**
     * Bluetooth device type, Dual Mode - BR/EDR/LE
     */
    public static final int DEVICE_TYPE_DUAL = 3;

    /**
     * Bluetooth device type, Low Energy - LE-only
     */
    public static final int DEVICE_TYPE_LE = 2;

    /**
     * Bluetooth device type, Unknown
     */
    public static final int DEVICE_TYPE_UNKNOWN = 0;

    /**
     * Indicates link was encrypted using AES algorithm
     */
    public static final int ENCRYPTION_ALGORITHM_AES = 2;

    /**
     * Indicates link was encrypted using E0 algorithm
     */
    public static final int ENCRYPTION_ALGORITHM_E0 = 1;

    /**
     * Indicates that link was not encrypted using any algorithm
     */
    public static final int ENCRYPTION_ALGORITHM_NONE = 0;

    /**
     * Sentinel error value for this class. Guaranteed to not equal any other integer constant in
     * this class. Provided as a convenience for functions that require a sentinel error value, for
     * example:
     *
     * <p><code>Intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE,
     * BluetoothDevice.ERROR)</code>
     */
    public static final int ERROR = -2147483648;

    /**
     * Used as an int extra field in {@link #ACTION_BOND_STATE_CHANGED} intents. Contains the bond
     * state of the remote device.
     *
     * <p>Possible values are: {@link #BOND_NONE}, {@link #BOND_BONDING}, {@link #BOND_BONDED}.
     */
    public static final String EXTRA_BOND_STATE = "android.bluetooth.device.extra.BOND_STATE";

    /**
     * Used as a Parcelable {@link BluetoothClass} extra field in {@link #ACTION_FOUND} and {@link
     * #ACTION_CLASS_CHANGED} intents.
     */
    public static final String EXTRA_CLASS = "android.bluetooth.device.extra.CLASS";

    /**
     * Used as a Parcelable {@link BluetoothDevice} extra field in every intent broadcast by this
     * class. It contains the {@link BluetoothDevice} that the intent applies to.
     */
    public static final String EXTRA_DEVICE = "android.bluetooth.device.extra.DEVICE";

    /**
     * Used as an int extra field in {@link #ACTION_ENCRYPTION_CHANGE} intents as the algorithm used
     * for encryption.
     *
     * <p>Possible values are: {@link #ENCRYPTION_ALGORITHM_NONE}, {@link #ENCRYPTION_ALGORITHM_E0},
     * {@link #ENCRYPTION_ALGORITHM_AES}.
     */
    public static final String EXTRA_ENCRYPTION_ALGORITHM = "android.bluetooth.device.extra.EXTRA_ENCRYPTION_ALGORITHM";

    /**
     * Used as a boolean extra field in {@link #ACTION_ENCRYPTION_CHANGE} intent. false mean
     * encryption is OFF, true means encryption is ON
     */
    public static final String EXTRA_ENCRYPTION_ENABLED = "android.bluetooth.device.extra.ENCRYPTION_ENABLED";

    /**
     * Used as an int extra field in {@link #ACTION_ENCRYPTION_CHANGE} intent. This is the status
     * value as returned from controller in "HCI Encryption Change event" i.e. value of 0 means
     * success.
     */
    public static final String EXTRA_ENCRYPTION_STATUS = "android.bluetooth.device.extra.ENCRYPTION_STATUS";

    /**
     * Used as a boolean extra field in {@link #ACTION_FOUND} intents. It contains the information
     * if device is discovered as member of a coordinated set or not. Pairing with device that
     * belongs to a set would trigger pairing with the rest of set members. See Bluetooth CSIP
     * specification for more details.
     */
    public static final String EXTRA_IS_COORDINATED_SET_MEMBER = "android.bluetooth.extra.IS_COORDINATED_SET_MEMBER";

    /**
     * Used as an int extra field in {@link #ACTION_ENCRYPTION_CHANGE} intents as the size of the
     * encryption key, in number of bytes. i.e. value of 16 means 16-byte, or 128 bit key size.
     */
    public static final String EXTRA_KEY_SIZE = "android.bluetooth.device.extra.KEY_SIZE";

    /**
     * Used as a String extra field in {@link #ACTION_NAME_CHANGED} and {@link #ACTION_FOUND}
     * intents. It contains the friendly Bluetooth name.
     */
    public static final String EXTRA_NAME = "android.bluetooth.device.extra.NAME";

    /**
     * Used as an int extra field in {@link #ACTION_PAIRING_REQUEST} intents as the value of
     * passkey. The Bluetooth Passkey is a 6-digit numerical value represented as integer value in
     * the range 0x00000000 – 0x000F423F (000000 to 999999).
     */
    public static final String EXTRA_PAIRING_KEY = "android.bluetooth.device.extra.PAIRING_KEY";

    /**
     * Used as an int extra field in {@link #ACTION_PAIRING_REQUEST} intents to indicate pairing
     * method used. Possible values are: {@link #PAIRING_VARIANT_PIN}, {@link
     * #PAIRING_VARIANT_PASSKEY_CONFIRMATION},
     */
    public static final String EXTRA_PAIRING_VARIANT = "android.bluetooth.device.extra.PAIRING_VARIANT";

    /**
     * Used as an int extra field in {@link #ACTION_BOND_STATE_CHANGED} intents. Contains the
     * previous bond state of the remote device.
     *
     * <p>Possible values are: {@link #BOND_NONE}, {@link #BOND_BONDING}, {@link #BOND_BONDED}.
     */
    public static final String EXTRA_PREVIOUS_BOND_STATE = "android.bluetooth.device.extra.PREVIOUS_BOND_STATE";

    /**
     * Used as an optional short extra field in {@link #ACTION_FOUND} intents. Contains the RSSI
     * value of the remote device as reported by the Bluetooth hardware.
     */
    public static final String EXTRA_RSSI = "android.bluetooth.device.extra.RSSI";

    /**
     * Used as an int extra field in {@link #ACTION_ACL_CONNECTED}, {@link #ACTION_ACL_DISCONNECTED}
     * and {@link #ACTION_ENCRYPTION_CHANGE} intents to indicate which transport is connected.
     * Possible values are: {@link #TRANSPORT_BREDR} and {@link #TRANSPORT_LE}.
     */
    public static final String EXTRA_TRANSPORT = "android.bluetooth.device.extra.TRANSPORT";

    /**
     * Used as an extra field in {@link #ACTION_UUID} intents, Contains the {@link
     * android.os.ParcelUuid}s of the remote device which is a parcelable version of {@link UUID}. A
     * {@code null} EXTRA_UUID indicates a timeout.
     */
    public static final String EXTRA_UUID = "android.bluetooth.device.extra.UUID";

    /**
     * The user will be prompted to confirm the passkey displayed on the screen or an app will
     * confirm the passkey for the user.
     */
    public static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;

    /**
     * The user will be prompted to enter a pin or an app will enter a pin for user.
     */
    public static final int PAIRING_VARIANT_PIN = 0;

    /**
     * Bluetooth LE 1M PHY. Used to refer to LE 1M Physical Channel for advertising, scanning or
     * connection.
     */
    public static final int PHY_LE_1M = 1;

    /**
     * Bluetooth LE 1M PHY mask. Used to specify LE 1M Physical Channel as one of many available
     * options in a bitmask.
     */
    public static final int PHY_LE_1M_MASK = 1;

    /**
     * Bluetooth LE 2M PHY. Used to refer to LE 2M Physical Channel for advertising, scanning or
     * connection.
     */
    public static final int PHY_LE_2M = 2;

    /**
     * Bluetooth LE 2M PHY mask. Used to specify LE 2M Physical Channel as one of many available
     * options in a bitmask.
     */
    public static final int PHY_LE_2M_MASK = 2;

    /**
     * Bluetooth LE Coded PHY. Used to refer to LE Coded Physical Channel for advertising, scanning
     * or connection.
     */
    public static final int PHY_LE_CODED = 3;

    /**
     * Bluetooth LE Coded PHY mask. Used to specify LE Coded Physical Channel as one of many
     * available options in a bitmask.
     */
    public static final int PHY_LE_CODED_MASK = 4;

    /**
     * No preferred coding when transmitting on the LE Coded PHY.
     */
    public static final int PHY_OPTION_NO_PREFERRED = 0;

    /**
     * Prefer the S=2 coding to be used when transmitting on the LE Coded PHY.
     */
    public static final int PHY_OPTION_S2 = 1;

    /**
     * Prefer the S=8 coding to be used when transmitting on the LE Coded PHY.
     */
    public static final int PHY_OPTION_S8 = 2;

    /**
     * No preference of physical transport for GATT connections to remote dual-mode devices
     */
    public static final int TRANSPORT_AUTO = 0;

    /**
     * Constant representing the BR/EDR transport.
     */
    public static final int TRANSPORT_BREDR = 1;

    /**
     * Constant representing the Bluetooth Low Energy (BLE) Transport.
     */
    public static final int TRANSPORT_LE = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private BluetoothDevice() {
    }
}
